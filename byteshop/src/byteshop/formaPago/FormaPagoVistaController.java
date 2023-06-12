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
        String url = "jdbc:mysql://localhost:3306/byteshop";
        String username = "root";

        try (Connection conn = DriverManager.getConnection(url, username, "616263646566676869"); 
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
            String url = "jdbc:mysql://localhost:3306/byteshop";
            String username = "root";

            try {
                Connection conn = DriverManager.getConnection(url, username, "616263646566676869");
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
        idFormaPago.setText(null);
        tipoFormaPago.setText(null);
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
    
}
