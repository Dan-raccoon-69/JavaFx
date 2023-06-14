/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package byteshop.Busqueda;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> marcaBusqueda;
    @FXML
    private TableColumn<?, ?> FabricanteBusqueda;
    @FXML
    private TableColumn<?, ?> ModeloBusqueda;
    @FXML
    private TableColumn<?, ?> TproductoBusqueda;
    @FXML
    private TableColumn<?, ?> EspecificacionBusqueda;
    @FXML
    private TableColumn<?, ?> ExistenciaBusqueda;
    @FXML
    private TableColumn<?, ?> PrecioBusqueda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RealizarBusqueda(ActionEvent event) {
    }

    @FXML
    private void RealizarCompra(ActionEvent event) {
    }
    
}
