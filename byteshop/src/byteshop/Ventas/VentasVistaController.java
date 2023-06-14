/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package byteshop.Ventas;

import byteshop.Distribucion.DatamodelDistribucion;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
 * @author Migue
 */
public class VentasVistaController implements Initializable {

    @FXML
    private TableView<DataModelVentas> TableViewventas;
    @FXML
    private TableColumn<DataModelVentas, Integer> colVenta;
    @FXML
    private TableColumn<DataModelVentas, Integer> colclie;
    @FXML
    private TableColumn<DataModelVentas, Date> colFecha;
    @FXML
    private TableColumn<DataModelVentas, Integer> colFormapago;
    @FXML
    private Button Actualizaventas;
    @FXML
    private Button Agregadatosventas;
    @FXML
    private TextField txtidventas;
    @FXML
    private TextField txtidFormapago;
    @FXML
    private TextField txtFechacompra;
    @FXML
    private TextField txtidcliente;
    private ObservableList<DataModelVentas> data;

    public VentasVistaController() {
        data = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colVenta.setCellValueFactory(new PropertyValueFactory<>("idVenta"));
        colclie.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaDeCompra"));
        colFormapago.setCellValueFactory(new PropertyValueFactory<>("idFormaDePago"));
        TableViewventas.setItems(data);
        loadDataFromDatabase();
    }

    public void loadDataFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/byteshop";
        String username = "root";

        try (Connection conn = DriverManager.getConnection(url, username, "616263646566676869"); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Ventas"); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idVenta = rs.getInt("Idventa");
                int idCliente = rs.getInt("idCliente");
                Date fechaDeCompra = rs.getDate("fechaDeCompra");
                int idFormaDePago = rs.getInt("idFormaDePago");

                DataModelVentas DataModelVent = new DataModelVentas(idVenta, idCliente, fechaDeCompra, idFormaDePago);
                data.add(DataModelVent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void IngresaVentas(ActionEvent event) {
        System.out.println("Si ingresa Venta");
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
                PreparedStatement stmt = conn.prepareStatement("insert into ventas(idVenta, idCliente, fechaDeCompra, idFormaDePago) values (?,?,?,?)");
                stmt.setString(1, txtidventas.getText());
                stmt.setString(2, txtidcliente.getText());
                stmt.setString(3, txtFechacompra.getText());
                stmt.setString(4, txtidFormapago.getText());
     
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
    private void ActualizaVent(ActionEvent event) {
        data.clear();
        loadDataFromDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Datos Actualizados");
        alert.setHeaderText(null);
        alert.setContentText("Datos Actualizados");
        alert.showAndWait();
    }
}