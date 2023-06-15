package byteshop;

import byteshop.DataModel;
import byteshop.clientes.ClientesVistaController;
import byteshop.formaPago.*;
import byteshop.direccion.*;
import byteshop.Ventas.*;
import byteshop.Distribucion.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class TablaVistaController implements Initializable {

    @FXML
    private TableView<DataModel> tableView;
    @FXML
    private TableColumn<DataModel, Integer> idColumn;
    @FXML
    private TableColumn<DataModel, String> marcaColumn;
    @FXML
    private TableColumn<DataModel, String> fabricanteColumn;
    @FXML
    private TableColumn<DataModel, String> modeloColumn;
    @FXML
    private TableColumn<DataModel, String> tipoProductoColumn;
    @FXML
    private TableColumn<DataModel, String> especificacionesColumn;
    @FXML
    private TableColumn<DataModel, Integer> existenciasColumn;
    @FXML
    private TableColumn<DataModel, Integer> precioColumn;

    private ObservableList<DataModel> data;
    @FXML
    private Button btnIngresar;
    @FXML
    private Button Refresh;
    @FXML
    private Button btnIngresarFPago;
    @FXML
    private Button btnDireccion;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnVentas;
    @FXML
    private Button btnDistribucion;
    // Variables conexion 
    public static final String url = "jdbc:mysql://localhost:3306/byteshop";
    public static final String usuario = "root";
    public static final String contraseña = "616263646566676869";

    public TablaVistaController() {
        data = FXCollections.observableArrayList();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marcaProducto"));
        fabricanteColumn.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tipoProductoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoDeProducto"));
        especificacionesColumn.setCellValueFactory(new PropertyValueFactory<>("especificacionesProducto"));
        existenciasColumn.setCellValueFactory(new PropertyValueFactory<>("existencias"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        // Configura las demás columnas

        tableView.setItems(data);
        loadDataFromDatabase();
    }

    public void loadDataFromDatabase() {


        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Productos"); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String marcaProducto = rs.getString("marcaProducto");
                String fabricante = rs.getString("fabricante");
                String modelo = rs.getString("modelo");
                String tipoDeProducto = rs.getString("tipoDeProducto");
                String especificacionesProducto = rs.getString("especificacionesProducto");
                int existencias = rs.getInt("existencias");
                int precio = rs.getInt("precio");

                DataModel dataModel = new DataModel(idProducto, marcaProducto, fabricante, modelo, tipoDeProducto, especificacionesProducto, existencias, precio);
                data.add(dataModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ingresarRegistros(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(agregadatosController.class.getResource("agregaDatos.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ingresar Datos");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.NONE);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    public void Actualiza(ActionEvent event) {
        data.clear();
        loadDataFromDatabase();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Datos Actualizados");
        alert.setHeaderText(null);
        alert.setContentText("Datos Actualizados");
        alert.showAndWait();
        
    }

    @FXML
    private void ingresarFpagos(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(FormaPagoVistaController.class.getResource("formaPagoVista.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ingresar F. Pago");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.NONE);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    private void ingresarDireccion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(DireccionController.class.getResource("direccion.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ingresar Dirección");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.NONE);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    private void ingresarClliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ClientesVistaController.class.getResource("clientesVista.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ingresar Cliente");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.NONE);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    private void ingresarVentas(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(VentasVistaController.class.getResource("VentasVista.fxml"));
        Stage stage = new Stage();
        stage.setTitle("ingresar Ventas");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.NONE);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    private void ingresarDistribucion(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(DistribucionController.class.getResource("Distribucion.fxml"));
        Stage stage = new Stage();
        stage.setTitle("ingresar Distribucion");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.NONE);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
        stage.setResizable(false);
    }
}
