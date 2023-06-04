/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package byteshop;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private void loadDataFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/byteshop";
        String username = "root";
        
        try (Connection conn = DriverManager.getConnection(url, username, "616263646566676869");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Productos");
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                int idProducto  = rs.getInt("idProducto");
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
    
}