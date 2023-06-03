/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class Vista1Controller implements Initializable {

    @FXML
    private Button btnClick;
    @FXML
    private TextField txtnum1;
    @FXML
    private TextField txtnum2;
    @FXML
    private TextField txtResult;
    @FXML
    private Button btnCalcular;
    @FXML
    private Button btnLimipar;
    @FXML
    private Button btnConectar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void click(ActionEvent event) {
        System.out.println("Diste click!!!");
    }

    @FXML
    private void calcular(ActionEvent event) {
        try {
            int a = Integer.parseInt(txtnum1.getText());
            int b = Integer.parseInt(txtnum2.getText());
            String c = (a + b) + "";
            txtResult.setText(c);
        } catch (NumberFormatException e) {
            System.out.println("Solo puedes colocar Numeros!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setTitle("Error");
            alert.setContentText("FORMATO ERRONEO EN CAMPOS");
            alert.showAndWait();
        }

    }

    @FXML
    private void clean(ActionEvent event) {
        txtResult.setText(null);
        txtnum1.setText(null);
        txtnum2.setText(null);
    }

    @FXML
    private void conectar(ActionEvent event) {
        System.out.println("Diste conectar");

        Connection cnx = null;
        String cadena = "jdbc:mysql://localhost/byteshop";
        String drv = "com.mysql.cj.jdbc.Driver";

        try {

            Class.forName(drv);
            cnx = DriverManager.getConnection(cadena, "root", "616263646566676869");
            System.out.println("Conexion Exitosa!!");
            cnx.close();
        } catch (ClassNotFoundException ex) {
            System.err.println("Error1:" + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error2:" + ex.getMessage());
        }
    }

}
