/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Daniel
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/Vista1.fxml"));
            Pane ventana = (Pane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
