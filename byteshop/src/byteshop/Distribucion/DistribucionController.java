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
        String url = "jdbc:mysql://localhost:3306/byteshop";
        String username = "root";

        try (Connection conn = DriverManager.getConnection(url, username, "616263646566676869"); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Distribucion"); ResultSet rs = stmt.executeQuery()) {

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

    @FXML
    private void IngresaDistribucion(ActionEvent event) {
        System.out.println("Si ingresa Distribucion");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
    
        if (result == ButtonType.OK) {
            String url = "jdbc:mysql://localhost:3306/byteshop";
            String username = "root";
        try {
                Connection conn = DriverManager.getConnection(url, username, "616263646566676869");
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
                    //limpiarCampos();
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
    private void ActualizaDistribucion(ActionEvent event) {
        data.clear();
        loadDataFromDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Datos Actualizados");
        alert.setHeaderText(null);
        alert.setContentText("Datos Actualizados");
        alert.showAndWait();
    }
}
