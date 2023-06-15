/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package byteshop.direccion;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class DireccionController implements Initializable {

    @FXML
    private TableView<DataModelDireccion> tableViewDireccion;
    @FXML
    private TableColumn<DataModelDireccion, Integer> idDireccion;
    @FXML
    private TableColumn<DataModelDireccion, String> Pais;
    @FXML
    private TableColumn<DataModelDireccion, String> estadoDeResidencia;
    @FXML
    private TableColumn<DataModelDireccion, String> alcaldia;
    @FXML
    private TableColumn<DataModelDireccion, String> colonia;
    @FXML
    private TableColumn<DataModelDireccion, String> codigoPostal;
    @FXML
    private TableColumn<DataModelDireccion, String> calle;
    @FXML
    private TableColumn<DataModelDireccion, Integer> numInterior;
    @FXML
    private TableColumn<DataModelDireccion, Integer> numEnterior;
    private ObservableList<DataModelDireccion> data;
    @FXML
    private Button btnIngresarDireccion;
    @FXML
    private Button RefreshDireccion;
    @FXML
    private TextField txtpais;
    @FXML
    private TextField txtEResidencia;
    @FXML
    private TextField txtidDireccion;
    @FXML
    private TextField txtCodigoPostal;
    @FXML
    private TextField txtAlcaldia;
    @FXML
    private TextField txtColonia;
    @FXML
    private TextField txtCalle;
    @FXML
    private TextField txtNumeroInterior;
    @FXML
    private TextField txtNumeroExterior;
    @FXML
    private Button btnModificarDireccion;
    @FXML
    private Button btnEliminarDirecciones;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtBuscarId;
    @FXML
    private Button btnBuscarById;

    // Varibles de conexion
    public static final String url = "jdbc:mysql://localhost:3306/byteshop";
    public static final String usuario = "root";
    public static final String contraseña = "616263646566676869";
    ResultSet rs;

    public DireccionController() {
        data = FXCollections.observableArrayList();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idDireccion.setCellValueFactory(new PropertyValueFactory<>("idDireccion"));
        Pais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        estadoDeResidencia.setCellValueFactory(new PropertyValueFactory<>("estadoDeResidencia"));
        alcaldia.setCellValueFactory(new PropertyValueFactory<>("alcaldia"));
        colonia.setCellValueFactory(new PropertyValueFactory<>("colonia"));
        codigoPostal.setCellValueFactory(new PropertyValueFactory<>("codigoPostal"));
        calle.setCellValueFactory(new PropertyValueFactory<>("calle"));
        numInterior.setCellValueFactory(new PropertyValueFactory<>("numInterior"));
        numEnterior.setCellValueFactory(new PropertyValueFactory<>("numExterior"));
        tableViewDireccion.setItems(data);
        loadDataFromDatabase();
    }

    public void loadDataFromDatabase() {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM direccion"); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idDireccion = rs.getInt("idDireccion");
                String pais = rs.getString("pais");
                String estadoDeResidencia = rs.getString("estadoDeResidencia");
                String alcaldia = rs.getString("alcaldia");
                String colonia = rs.getString("colonia");
                String codigoPostal = rs.getString("codigoPostal");
                String calle = rs.getString("calle");
                int numInterior = rs.getInt("numInterior");
                int numExterior = rs.getInt("numExterior");

                DataModelDireccion dataModeldireccion = new DataModelDireccion(idDireccion, pais, estadoDeResidencia, alcaldia, colonia, codigoPostal, calle, numInterior, numExterior);
                data.add(dataModeldireccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void limpiarCampos() {
        txtidDireccion.setText(null);
        txtpais.setText(null);
        txtEResidencia.setText(null);
        txtAlcaldia.setText(null);
        txtColonia.setText(null);
        txtCodigoPostal.setText(null);
        txtCalle.setText(null);
        txtNumeroInterior.setText(null);
        txtNumeroExterior.setText(null);
    }

    @FXML
    private void ingresarDirecciones(ActionEvent event) {
        System.out.println("Si ingresa");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            String url = "jdbc:mysql://localhost:3306/byteshop";
            String username = "root";

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement stmt = conn.prepareStatement("insert into direccion(idDireccion, pais, estadoDeResidencia, alcaldia, colonia, codigoPostal, calle, numInterior, numExterior) values (?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, txtidDireccion.getText());
                stmt.setString(2, txtpais.getText());
                stmt.setString(3, txtEResidencia.getText());
                stmt.setString(4, txtAlcaldia.getText());
                stmt.setString(5, txtColonia.getText());
                stmt.setString(6, txtCodigoPostal.getText());
                stmt.setString(7, txtCalle.getText());
                stmt.setString(8, txtNumeroInterior.getText());
                stmt.setString(9, txtNumeroExterior.getText());
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
    private void ActualizaDirecciones(ActionEvent event) {
        data.clear();
        loadDataFromDatabase();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Datos Actualizados");
        alert.setHeaderText(null);
        alert.setContentText("Datos Actualizados");
        alert.showAndWait();
    }

    @FXML
    private void modificarDirecciones(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement ps = conn.prepareStatement("update direccion set idDireccion = ?, pais = ?, estadoDeResidencia = ?, alcaldia = ?, colonia = ?, codigoPostal = ?, calle = ?, numInterior = ?, numExterior = ? where idDireccion = ?");
                ps.setInt(10, Integer.parseInt(txtBuscarId.getText()));
                ps.setString(1, txtidDireccion.getText());
                ps.setString(2, txtpais.getText());
                ps.setString(3, txtEResidencia.getText());
                ps.setString(4, txtAlcaldia.getText());
                ps.setString(5, txtColonia.getText());
                ps.setString(6, txtCodigoPostal.getText());
                ps.setString(7, txtCalle.getText());
                ps.setString(8, txtNumeroInterior.getText());
                ps.setString(9, txtNumeroExterior.getText());

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
    private void eliminarDirecciones(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement ps = conn.prepareStatement("delete from direccion where idDireccion = ?");
                ps.setInt(1, Integer.parseInt(txtBuscarId.getText()));
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
        txtidDireccion.setText(null);
        txtpais.setText(null);
        txtCalle.setText(null);
        txtCodigoPostal.setText(null);
        txtAlcaldia.setText(null);
        txtColonia.setText(null);
        txtNumeroInterior.setText(null);
        txtNumeroExterior.setText(null);
        txtEResidencia.setText(null);
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void buscarById() {
        try {
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement stmt = conn.prepareStatement("select * from direccion where idDireccion = ?");
            stmt.setString(1, txtBuscarId.getText());
            rs = stmt.executeQuery();

            limpiarCampos();
            if (rs.next()) {
                txtidDireccion.setText(String.valueOf(rs.getInt("idDireccion")));
                txtpais.setText(rs.getString("pais"));
                txtCalle.setText(rs.getString("calle"));
                txtCodigoPostal.setText(rs.getString("codigoPostal"));
                txtAlcaldia.setText(rs.getString("alcaldia"));
                txtColonia.setText(rs.getString("colonia"));
                txtNumeroInterior.setText(rs.getString("numInterior"));
                txtNumeroExterior.setText(rs.getString("numExterior"));
                txtEResidencia.setText(rs.getString("estadoDeResidencia"));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("Direccion no encontrada...");
                alert.showAndWait();
                limpiarCampos();
            }
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }

}
