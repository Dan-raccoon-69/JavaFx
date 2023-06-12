package byteshop.clientes;

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

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class ClientesVistaController implements Initializable {

    @FXML
    private TableView<DataModelClientes> tableViewClientes;
    @FXML
    private TableColumn<DataModelClientes, Integer> idCliente;
    @FXML
    private TableColumn<DataModelClientes, String> nombreCliente;
    @FXML
    private TableColumn<DataModelClientes, Integer> idFormaDePago;
    @FXML
    private TableColumn<DataModelClientes, String> correo;
    @FXML
    private TableColumn<DataModelClientes, String> numeroTelefonico;
    @FXML
    private TableColumn<DataModelClientes, Integer> idDireccion;
    @FXML
    private Button btnIngresarCliente;
    @FXML
    private Button RefreshCliente;
    private ObservableList<DataModelClientes> data;
    @FXML
    private TextField txtIdCliente;
    @FXML
    private TextField txtNombreCliente;
    @FXML
    private TextField txtIdFPago;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;

    public ClientesVistaController() {
        data = FXCollections.observableArrayList();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        nombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        idFormaDePago.setCellValueFactory(new PropertyValueFactory<>("idFormaDePago"));
        correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        numeroTelefonico.setCellValueFactory(new PropertyValueFactory<>("numeroTelefonico"));
        idDireccion.setCellValueFactory(new PropertyValueFactory<>("idDireccion"));
        // Configura las demás columnas

        tableViewClientes.setItems(data);
        loadDataFromDatabase();
    }

    public void loadDataFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/byteshop";
        String username = "root";

        try (Connection conn = DriverManager.getConnection(url, username, "616263646566676869"); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clientes"); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nombreCliente = rs.getString("nombreCliente");
                int idFormaDePago = rs.getInt("idFormaDePago");
                String correo = rs.getString("correo");
                String numeroTelefonico = rs.getString("numeroTelefonico");
                int idDireccion = rs.getInt("idDireccion");

                DataModelClientes dataModelClientes = new DataModelClientes(idCliente, nombreCliente, idFormaDePago, correo, numeroTelefonico, idDireccion);
                data.add(dataModelClientes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void limpiarCampos() {
        txtIdCliente.setText(null);
        txtNombreCliente.setText(null);
        txtIdFPago.setText(null);
        txtCorreo.setText(null);
        txtTelefono.setText(null);
        txtDireccion.setText(null);
    }

    @FXML
    private void ingresarCliente(ActionEvent event) {
        System.out.println("Si ingresa Cliente");
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
                PreparedStatement stmt = conn.prepareStatement("insert into clientes(idCliente, nombreCliente, idFormaDePago, correo, numeroTelefonico, idDireccion) values (?,?,?,?,?,?)");
                stmt.setString(1, txtIdCliente.getText());
                stmt.setString(2, txtNombreCliente.getText());
                stmt.setString(3, txtIdFPago.getText());
                stmt.setString(4, txtCorreo.getText());
                stmt.setString(5, txtTelefono.getText());
                stmt.setString(6, txtDireccion.getText());

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
                    alert.setContentText("NO SE ENCONTRO EL ID DE F. Pago O ID DE DIRECCION EN LA BASE DE DATOS.");
                    alert.showAndWait();
                } else if (e.getErrorCode() == 1062) {
                    // Manejo específico para el error de clave primaria duplicada
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("VIOLACION DE CLAVE PRIMARIA, EL ID YA EXISTE EN LA TABLA.");
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
            }

        }
    }

    @FXML
    private void ActualizaCliente(ActionEvent event) {
        data.clear();
        loadDataFromDatabase();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Datos Actualizados");
        alert.setHeaderText(null);
        alert.setContentText("Datos Actualizados");
        alert.showAndWait();
    }

}
