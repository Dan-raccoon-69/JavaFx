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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import byteshop.TablaVistaController;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class agregadatosController implements Initializable {

    @FXML
    private Button Agrega;
    @FXML
    private Button Cancela;
    @FXML
    private TextField idproducto;
    @FXML
    private TextField marca;
    @FXML
    private TextField Fabricante;
    @FXML
    private TextField Precio;
    @FXML
    private TextField Existencias;
    @FXML
    private TextField Especificaciones;
    @FXML
    private TextField Tipo;
    @FXML
    private TextField Modelo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void limpiarCampos() {
        idproducto.setText(null);
        marca.setText(null);
        Fabricante.setText(null);
        Precio.setText(null);
        Existencias.setText(null);
        Especificaciones.setText(null);
        Tipo.setText(null);
        Modelo.setText(null);
    }

    @FXML
    private void ingresarRegistros(ActionEvent event) {
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
                PreparedStatement stmt = conn.prepareStatement("insert into Productos(idProducto, marcaProducto, fabricante, modelo, tipoDeProducto, especificacionesProducto, existencias, precio) values (?,?,?,?,?,?,?,?)");
                stmt.setString(1, idproducto.getText());
                stmt.setString(2, marca.getText());
                stmt.setString(3, Fabricante.getText());
                stmt.setString(4, Modelo.getText());
                stmt.setString(5, Tipo.getText());
                stmt.setString(6, Especificaciones.getText());
                stmt.setString(7, Existencias.getText());
                stmt.setString(8, Precio.getText());

                int resultado = stmt.executeUpdate();
                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingreso exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Has ingresado tus datos correctamente.");
                    //alert.showAndWait();
                    ButtonType result2 = alert.showAndWait().orElse(ButtonType.CANCEL);
                    if (result2 == ButtonType.OK) {
                        TablaVistaController loa = new TablaVistaController();
                        loa.loadDataFromDatabase();
                    }
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
    private void cancelarIngreso(ActionEvent event) {
        Stage stage = (Stage) Cancela.getScene().getWindow();
        stage.close();
    }
}
