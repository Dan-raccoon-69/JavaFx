package byteshop;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import byteshop.TablaVistaController;
import java.sql.SQLIntegrityConstraintViolationException;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class agregadatosController implements Initializable {

    @FXML
    private Button Agrega;
    @FXML
    private Button Cancela;
    @FXML
    private TextField idproducto;
    @FXML
    private TextField marca;
    @FXML
    private TextField Fabricante;
    @FXML
    private TextField Precio;
    @FXML
    private TextField Existencias;
    @FXML
    private TextField Especificaciones;
    @FXML
    private TextField Tipo;
    @FXML
    private TextField Modelo;
    @FXML
    private TextField txtidBuscar;
    @FXML
    private Button btnBuscarbyId;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminarR;
    @FXML
    private Button btnLimpiar;
    // Varibles de conexion
    public static final String url = "jdbc:mysql://localhost:3306/byteshop";
    public static final String usuario = "root";
    public static final String contraseña = "616263646566676869";
    ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void limpiarCampos() {
        btnBuscarbyId.setText(null);
        idproducto.setText(null);
        marca.setText(null);
        Fabricante.setText(null);
        Precio.setText(null);
        Existencias.setText(null);
        Especificaciones.setText(null);
        Tipo.setText(null);
        Modelo.setText(null);
    }

    @FXML
    private void ingresarRegistros(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement stmt = conn.prepareStatement("insert into Productos(idProducto, marcaProducto, fabricante, modelo, tipoDeProducto, especificacionesProducto, existencias, precio) values (?,?,?,?,?,?,?,?)");
                stmt.setString(1, idproducto.getText());
                stmt.setString(2, marca.getText());
                stmt.setString(3, Fabricante.getText());
                stmt.setString(4, Modelo.getText());
                stmt.setString(5, Tipo.getText());
                stmt.setString(6, Especificaciones.getText());
                stmt.setString(7, Existencias.getText());
                stmt.setString(8, Precio.getText());

                int resultado = stmt.executeUpdate();
                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingreso exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Has ingresado tus datos correctamente.");
                    alert.showAndWait();
                    limpiarCampos();
                }
                conn.close();
            } catch (SQLIntegrityConstraintViolationException e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("Violación de clave primaria. El id ya existe en la tabla.");
                alert.showAndWait();
            } catch (SQLException e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                alert.showAndWait();
            }

        }

    }

    @FXML
    private void cancelarIngreso(ActionEvent event) {
        Stage stage = (Stage) Cancela.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void buscarById(ActionEvent event) {
        try {
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement stmt = conn.prepareStatement("select * from Productos where idProducto = ?");
            stmt.setString(1, txtidBuscar.getText());
            rs = stmt.executeQuery();

            limpiarCampos();
            if (rs.next()) {
                idproducto.setText(String.valueOf(rs.getInt("idProducto")));
                marca.setText(rs.getString("marcaProducto"));
                Fabricante.setText(rs.getString("fabricante"));
                Modelo.setText(rs.getString("modelo"));
                Existencias.setText(String.valueOf(rs.getString("existencias")));
                Especificaciones.setText(rs.getString("especificacionesProducto"));
                Tipo.setText(rs.getString("tipoDeProducto"));
                Precio.setText(String.valueOf(rs.getString("precio")));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("Producto no encontrado...");
                alert.showAndWait();
                limpiarCampos();
            }
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }

    @FXML
    private void modificarRegistros(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement ps = conn.prepareStatement("update Productos set idProducto = ?, marcaProducto = ?, fabricante = ?, modelo = ?, tipoDeProducto = ?, especificacionesProducto = ?, existencias = ?, precio = ? where idProducto = ?");
                ps.setInt(9, Integer.parseInt(txtidBuscar.getText()));
                ps.setString(1, idproducto.getText());
                ps.setString(2, marca.getText());
                ps.setString(3, Fabricante.getText());
                ps.setString(4, Modelo.getText());
                ps.setString(5, Tipo.getText());
                ps.setString(6, Especificaciones.getText());
                ps.setInt(7, Integer.parseInt(Existencias.getText()));
                ps.setInt(8, Integer.parseInt(Precio.getText()));

                int resultado = ps.executeUpdate();
                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingreso exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Has Modificado tus datos correctamente.");
                    alert.showAndWait();
                    limpiarCampos();
                }

                conn.close();
            } catch (SQLIntegrityConstraintViolationException e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("Violación de clave primaria. El id ya existe en la tabla.");
                alert.showAndWait();
            } catch (SQLException e ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("ERROR CON LA BASE DE DATOS");
                alert.showAndWait();
            } catch (Exception e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                alert.showAndWait();
            }

        }
    }


    @FXML
    private void eliminarRegistros(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement ps = conn.prepareStatement("delete from Productos where idProducto = ?");
                ps.setInt(1, Integer.parseInt(txtidBuscar.getText()));
                int resultado = ps.executeUpdate();
                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingreso exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Has Eliminado el dato correctamente.");
                    alert.showAndWait();
                    limpiarCampos();
                }
                conn.close();
            } catch (SQLException e ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("ERROR CON LA BASE DE DATOS");
                alert.showAndWait();
            } catch (Exception e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                alert.showAndWait();
            }

        }
    }

    @FXML
    private void limpiar(ActionEvent event) {
        limpiarCampos();
    }
}
