/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ejercicio_22;

import Modelo.Operaciones;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class VistaController implements Initializable {

    @FXML
    private TextField txtnum1;
    @FXML
    private TextField txtnum2;
    @FXML
    private RadioButton rbtnsumar;
    @FXML
    private RadioButton rbtnrestar;
    @FXML
    private RadioButton rbtndividir;
    @FXML
    private RadioButton rbtnmultiplicar;
    @FXML
    private Button btnCalcular;
    @FXML
    private TextField txtresultado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup tg1 = new ToggleGroup();
        this.rbtnsumar.setToggleGroup(tg1);
        this.rbtnrestar.setToggleGroup(tg1);
        this.rbtndividir.setToggleGroup(tg1);
        this.rbtnmultiplicar.setToggleGroup(tg1);
    }

    @FXML
    private void calcular(ActionEvent event) {
        try {
            double a = Integer.parseInt(txtnum1.getText());
            double b = Integer.parseInt(txtnum2.getText());
            Operaciones s = new Operaciones(a, b);

            if (rbtnsumar.isSelected()) {
                System.out.println("Se selecciono suma");
                this.txtresultado.setText(s.Sumar() + "");
            } else if (rbtnrestar.isSelected()) {
                System.out.println("Se selecciono resta");
                this.txtresultado.setText(s.restar() + "");
            } else if (rbtndividir.isSelected()) {
                try {
                    System.out.println("Se selecciono division");
                    this.txtresultado.setText(s.division() + "");
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("No se puede dividir entre 0");
                    alert.showAndWait();
                }
            } else if (rbtnmultiplicar.isSelected()) {
                System.out.println("Se selecciono multiplicacion");
                this.txtresultado.setText(s.multiplicacion() + "");
            }
        } catch (NumberFormatException e) {
            System.out.println("Solo puedes colocar Numeros!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setTitle("Error");
            alert.setContentText("FORMATO ERRONEO EN CAMPOS");
            alert.showAndWait();
        }

    }

}
