package byteshop.Distribucion;

import byteshop.Ventas.DataModelVentas;
import byteshop.formaPago.DataModelFormaPago;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class DistribucionController implements Initializable {

    @FXML
    private TableView<DatamodelDistribucion> TableviewDistribucion;
    @FXML
    private TableColumn<DatamodelDistribucion, Integer> Idpaquete;
    @FXML
    private TableColumn<DatamodelDistribucion, String> Colpaqueteria;
    @FXML
    private TableColumn<DatamodelDistribucion, Integer> Idcliente;
    @FXML
    private TableColumn<DatamodelDistribucion, Integer> IdDir;
    @FXML
    private TableColumn<DatamodelDistribucion, String> ColCodigoRas;
    @FXML
    private TextField txtidclie;
    @FXML
    private Button AgregaDatos;
    @FXML
    private Button Actualizadist;
    @FXML
    private TableColumn<DatamodelDistribucion, Integer> colventas;
    @FXML
    private TextField txtidpaq;
    @FXML
    private TextField txtidvent;
    @FXML
    private TextField txtpaq;
    @FXML
    private TextField txtidDir;
    @FXML
    private TextField txtcodrast;
    private ObservableList<DatamodelDistribucion> data;
    @FXML
    private Button btnModificarDistribucion;
    @FXML
    private Button btnEliminarDistribucion;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtBuscarDistribucionById;
    @FXML
    private Button btnBuscarDistribucionById;
    // Varibles de conexion
    public static final String url = "jdbc:mysql://localhost:3306/byteshop";
    public static final String usuario = "root";
    public static final String contraseña = "616263646566676869";
    ResultSet rs;

    public DistribucionController() {
        data = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Idpaquete.setCellValueFactory(new PropertyValueFactory<>("idPaquete"));
        colventas.setCellValueFactory(new PropertyValueFactory<>("idVenta"));
        Colpaqueteria.setCellValueFactory(new PropertyValueFactory<>("nombreDeEmpresaPaquete"));
        Idcliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        IdDir.setCellValueFactory(new PropertyValueFactory<>("idDireccion"));
        ColCodigoRas.setCellValueFactory(new PropertyValueFactory<>("codigoRastreo"));
        TableviewDistribucion.setItems(data);
        loadDataFromDatabase();
    }

    public void loadDataFromDatabase() {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña); 
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM distribucion"); 
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idPaquete = rs.getInt("idPaquete");
                int idVenta = rs.getInt("idVenta");
                String nombreDeEmpresaPaquete = rs.getString("nombreDeEmpresaPaquete");
                int idDireccion = rs.getInt("idDireccion");
                int idCliente = rs.getInt("idCliente");
                String codigoRastreo = rs.getString("codigoRastreo");

                DatamodelDistribucion DatamodelDis = new DatamodelDistribucion(idPaquete, idVenta, nombreDeEmpresaPaquete, idCliente, idDireccion, codigoRastreo);
                data.add(DatamodelDis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void limpiarCampos() {
        txtidpaq.setText(null);
        txtidclie.setText(null);
        txtidvent.setText(null);
        txtidDir.setText(null);
        txtpaq.setText(null);
        txtcodrast.setText(null);
    }

    @FXML
    private void IngresaDistribucion(ActionEvent event) {
        System.out.println("Si ingresa Distribucion");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
    
        if (result == ButtonType.OK) {
        try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement stmt = conn.prepareStatement("insert into distribucion(idPaquete, idVenta, nombreDeEmpresaPaquete, idCliente, idDireccion, codigoRastreo) values (?,?,?,?,?,?)");
                stmt.setString(1, txtidpaq.getText());
                stmt.setString(2, txtidvent.getText());
                stmt.setString(3, txtpaq.getText());
                stmt.setString(4, txtidclie.getText());
                stmt.setString(5, txtidDir.getText());
                stmt.setString(6, txtcodrast.getText());
        
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
            } catch (SQLException e) {
                if (e.getErrorCode() == 1452) {
                    // Manejo específico para el error de clave externa
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("NO SE ENCONTRO EL ID VENTA, ID CLIENTE O ID DIRECCION DE PAGO EN LA BASE DE DATOS.");
                    alert.showAndWait();
                } else if (e.getErrorCode() == 1062) {
                    // Manejo específico para el error de clave primaria duplicada
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("VIOLACION DE CLAVE PRIMARIA, EL ID DISTRIBUCION YA EXISTE EN LA TABLA.");
                    alert.showAndWait();
                } else {
                    // Otro manejo de excepciones o re-lanzamiento
                    System.out.println("Error desconocido: " + e.getMessage());
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                    alert.showAndWait();
                }
            } catch (Exception ex){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                alert.showAndWait();
            } 
        }
   }

    @FXML
    private void ActualizaDistribucion(ActionEvent event) {
        data.clear();
        loadDataFromDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Datos Actualizados");
        alert.setHeaderText(null);
        alert.setContentText("Datos Actualizados");
        alert.showAndWait();
    }

    @FXML
    private void modificarDistribucion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement ps = conn.prepareStatement("update distribucion set idPaquete = ?, idVenta = ?, nombreDeEmpresaPaquete = ?, idCliente = ?, idDireccion = ?, codigoRastreo = ? where idPaquete = ?");
                ps.setInt(7, Integer.parseInt(txtBuscarDistribucionById.getText()));
                ps.setInt(1, Integer.parseInt(txtidpaq.getText()));
                ps.setInt(2, Integer.parseInt(txtidvent.getText()));
                ps.setString(3, txtpaq.getText());
                ps.setInt(4, Integer.parseInt(txtidclie.getText()));
                ps.setInt(5, Integer.parseInt(txtidDir.getText()));
                ps.setString(6, txtcodrast.getText());

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
            } catch (SQLException e) {
                if (e.getErrorCode() == 1452) {
                    // Manejo específico para el error de clave externa
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("NO SE ENCONTRO EL ID CLIENTE O ID FORMA DE PAGO EN LA BASE DE DATOS.");
                    alert.showAndWait();
                } else if (e.getErrorCode() == 1062) {
                    // Manejo específico para el error de clave primaria duplicada
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("VIOLACION DE CLAVE PRIMARIA, EL ID VENTA YA EXISTE EN LA TABLA.");
                    alert.showAndWait();
                } else {
                    // Otro manejo de excepciones o re-lanzamiento
                    System.out.println("Error desconocido: " + e.getMessage());
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                    alert.showAndWait();
                }
            } catch (Exception ex){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                alert.showAndWait();
            }  

        }
    }

    @FXML
    private void eliminarDistribucion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement ps = conn.prepareStatement("delete from distribucion where idPaquete = ?");
                ps.setInt(1, Integer.parseInt(txtBuscarDistribucionById.getText()));
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

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void buscarDistribucionById(ActionEvent event) {
        try {
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement stmt = conn.prepareStatement("select * from distribucion where idPaquete = ?");
            stmt.setString(1, txtBuscarDistribucionById.getText());
            rs = stmt.executeQuery();

            limpiarCampos();

            if (rs.next()) {
                txtidpaq.setText(String.valueOf(rs.getInt("idPaquete")));
                txtidclie.setText(String.valueOf(rs.getString("idCliente")));
                txtidvent.setText(String.valueOf(rs.getString("idVenta")));
                txtidDir.setText(String.valueOf(rs.getString("idDireccion")));
                txtpaq.setText(rs.getString("nombreDeEmpresaPaquete"));
                txtcodrast.setText(rs.getString("codigoRastreo"));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("Paquete no encontrado...");
                alert.showAndWait();
                limpiarCampos();
            }
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }
}
