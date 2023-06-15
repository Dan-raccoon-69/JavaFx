/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package byteshop.Busqueda;

import byteshop.Ventas.DataModelVentas;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class BusquedaViewController implements Initializable {

    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnComprar;
    @FXML
    private TextField txtbarrabusqueda;
    @FXML
    private TableColumn<DataModelBusqueda, String> marcaBusqueda;
    @FXML
    private TableColumn<DataModelBusqueda, String> FabricanteBusqueda;
    @FXML
    private TableColumn<DataModelBusqueda, String> ModeloBusqueda;
    @FXML
    private TableColumn<DataModelBusqueda, String> TproductoBusqueda;
    @FXML
    private TableColumn<DataModelBusqueda, String> EspecificacionBusqueda;
    @FXML
    private TableColumn<DataModelBusqueda, Integer> ExistenciaBusqueda;
    @FXML
    private TableColumn<DataModelBusqueda, Integer> PrecioBusqueda;
    @FXML
    private TableView<DataModelBusqueda> tableViewBusqueda;
    private ObservableList<DataModelBusqueda> data;
    // Varibles de conexion
    public static final String url = "jdbc:mysql://localhost:3306/byteshop";
    public static final String usuario = "root";
    public static final String contraseña = "616263646566676869";
    ResultSet rs;

    public BusquedaViewController() {
        data = FXCollections.observableArrayList();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        marcaBusqueda.setCellValueFactory(new PropertyValueFactory<>("marcaProducto"));
        FabricanteBusqueda.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
        ModeloBusqueda.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        TproductoBusqueda.setCellValueFactory(new PropertyValueFactory<>("tipoDeProducto"));
        EspecificacionBusqueda.setCellValueFactory(new PropertyValueFactory<>("especificacionesProducto"));
        ExistenciaBusqueda.setCellValueFactory(new PropertyValueFactory<>("existencias"));
        PrecioBusqueda.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tableViewBusqueda.setItems(data);
        loadDataFromDatabase();
    }

    public void loadDataFromDatabase() {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);  PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Productos"); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String marcaProducto = rs.getString("marcaProducto");
                String fabricante = rs.getString("fabricante");
                String modelo = rs.getString("modelo");
                String tipoDeProducto = rs.getString("tipoDeProducto");
                String especificacionesProducto = rs.getString("especificacionesProducto");
                int existencias = rs.getInt("existencias");
                int precio = rs.getInt("precio");

                DataModelBusqueda DataModelBusca = new DataModelBusqueda(idProducto, marcaProducto, fabricante, modelo, especificacionesProducto, existencias, precio);
                data.add(DataModelBusca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   @FXML
private void RealizarBusqueda(ActionEvent event) {
    String textoBusqueda = txtbarrabusqueda.getText().trim();

    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña); 
         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Productos WHERE modelo LIKE ? OR tipoDeProducto LIKE ? OR marcaProducto LIKE ? OR precio LIKE ?")) {

        stmt.setString(1, "%" + textoBusqueda + "%");
        stmt.setString(2, "%" + textoBusqueda + "%");
        stmt.setString(3, "%" + textoBusqueda + "%");
        stmt.setString(4, "%" + textoBusqueda + "%");

        ResultSet rs = stmt.executeQuery();

        // Limpiar la lista actual antes de agregar los nuevos resultados de la búsqueda
        data.clear();

        while (rs.next()) {
            int idProducto = rs.getInt("idProducto");
            String marcaProducto = rs.getString("marcaProducto");
            String fabricante = rs.getString("fabricante");
            String modelo = rs.getString("modelo");
            String tipoDeProducto = rs.getString("tipoDeProducto");
            String especificacionesProducto = rs.getString("especificacionesProducto");
            int existencias = rs.getInt("existencias");
            int precio = rs.getInt("precio");

            DataModelBusqueda dataModelBusca = new DataModelBusqueda(idProducto, marcaProducto, fabricante, modelo, especificacionesProducto, existencias, precio);
            data.add(dataModelBusca);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void RealizarCompra(ActionEvent event) {
        
    }
}