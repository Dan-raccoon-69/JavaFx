package byteshop.formaPago;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class FormaPagoVistaController implements Initializable {

    @FXML
    private TableView<DataModelFormaPago> tableViewFormaPago;
    @FXML
    private TableColumn<DataModelFormaPago, Integer> idFormaPago;
    @FXML
    private TableColumn<DataModelFormaPago, String> tipoFormaPago;
    private ObservableList<DataModelFormaPago> data;
    @FXML
    private Button btnIngresarFormaPago;
    @FXML
    private Button RefreshFormaPago;
    @FXML
    private TextField txtIdFPago;
    @FXML
    private TextField txtnombreFPago;
    @FXML
    private TextField txtFPagoBuscar;
    @FXML
    private Button btnFPagoBuscar;
    @FXML
    private Button btnModificarFPago;
    @FXML
    private Button btnEliminarFP;
    @FXML
    private Button btnCancelar;
    
    // Varibles de conexion
    public static final String url = "jdbc:mysql://localhost:3306/byteshop";
    public static final String usuario = "root";
    public static final String contraseña = "616263646566676869";
    ResultSet rs;
    
    public FormaPagoVistaController() {
        data = FXCollections.observableArrayList();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idFormaPago.setCellValueFactory(new PropertyValueFactory<>("idFormaDePago"));
        tipoFormaPago.setCellValueFactory(new PropertyValueFactory<>("nombreFormaDePago"));
        tableViewFormaPago.setItems(data);
        loadDataFromDatabase();
    }    
    
    public void loadDataFromDatabase() {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM formasDePago"); 
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idFormaDePago = rs.getInt("idFormaDePago");
                String nombreFormaDePago = rs.getString("nombreFormaDePago");
                
                DataModelFormaPago dataModelForma = new DataModelFormaPago(idFormaDePago, nombreFormaDePago);
                data.add(dataModelForma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ingresarFormaPago(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement stmt = conn.prepareStatement("insert into formasDePago(idFormaDePago, nombreFormaDePago) values (?,?)");
                stmt.setString(1, txtIdFPago.getText());
                stmt.setString(2, txtnombreFPago.getText());

                int resultado = stmt.executeUpdate();
                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingreso exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Has ingresado los datos correctamente.");
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
    
    public void limpiarCampos() {
        txtIdFPago.setText(null);
        txtnombreFPago.setText(null);
    }

    @FXML
    private void ActualizaFormasPago(ActionEvent event) {
        data.clear();
        loadDataFromDatabase();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Datos Actualizados");
        alert.setHeaderText(null);
        alert.setContentText("Datos Actualizados");
        alert.showAndWait();
    }

    @FXML
    private void buscarFormaPago(ActionEvent event) {
        try {
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement stmt = conn.prepareStatement("select * from formasDePago where idFormaDePago = ?");
            stmt.setString(1, txtFPagoBuscar.getText());
            rs = stmt.executeQuery();
            
            limpiarCampos();
            if (rs.next()) {
                txtIdFPago.setText(String.valueOf(rs.getInt("idFormaDePago")));
                txtnombreFPago.setText(rs.getString("nombreFormaDePago"));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("Forma de Pago no encontrada...");
                alert.showAndWait();
                limpiarCampos();
            }
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }

    @FXML
    private void modificarFormaPago(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement ps = conn.prepareStatement("update formasDePago set idFormaDePago = ?, nombreFormaDePago = ? where idFormaDePago = ?");
                ps.setInt(3, Integer.parseInt(txtFPagoBuscar.getText()));
                ps.setString(1, txtIdFPago.getText());
                ps.setString(2, txtnombreFPago.getText());
                int resultado = ps.executeUpdate();
                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingreso exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Has Modificado la Forma de Pago correctamente.");
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
    private void eliminarFormaPago(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement ps = conn.prepareStatement("delete from formasDePago where idFormaDePago = ?");
                ps.setInt(1, Integer.parseInt(txtFPagoBuscar.getText()));
                int resultado = ps.executeUpdate();
                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingreso exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Has Eliminado la Forma de Pago correctamente.");
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
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}