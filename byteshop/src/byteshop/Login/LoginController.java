package byteshop.Login;

import byteshop.*;
import byteshop.Busqueda.BusquedaViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnIngresa;

    // variables de ingreso
    private String usuario = null;
    private String contraseniaUsuario = "washimido15";
    private String contraseniaAdmi = "admi12345";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Ingresauser(ActionEvent event) throws IOException {
        String user = txtUsername.getText();
        user.toLowerCase();
        String password = txtPassword.getText();

        if (user.equals("admi") && password.equals(contraseniaAdmi)) {
            System.out.println("Entras a ADMI");
            Parent root = FXMLLoader.load(TablaVistaController.class.getResource("tablaVista.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ingresar Datos");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.NONE);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
            stage.setResizable(false);
            
        } else if (user.equals("user") && password.equals(contraseniaUsuario)) {
            System.out.println("Entras a USUARIO");
            Parent root = FXMLLoader.load(BusquedaViewController.class.getResource("BusquedaView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Productos");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.NONE);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
            stage.setResizable(false);
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setTitle("Error");
            alert.setContentText("USUARIO O CONTRASEÑA INCORRECTOS");
            alert.showAndWait();
            alert.setResizable(false);
        }
    }
}
