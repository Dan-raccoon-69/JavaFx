/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Daniel
 */
public class Tabla extends Application{

    public static final String url = "jdbc:mysql://localhost:3306/byteshop";
    public static final String usuario = "root";
    public static final String contraseña = "616263646566676869";

    private TableView<DataModel> table;
    private ObservableList<DataModel> data;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        table = new TableView<>();
        data = FXCollections.observableArrayList();

        TableColumn<DataModel, Integer> idProducto = new TableColumn<>("ID");
        idProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));

        TableColumn<DataModel, String> marcaProducto = new TableColumn<>("Marca Producto");
        marcaProducto.setCellValueFactory(new PropertyValueFactory<>("marcaProducto"));

        TableColumn<DataModel, String> fabricante = new TableColumn<>("Fabricante");
        fabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));

        TableColumn<DataModel, Integer> modelo = new TableColumn<>("Modelo");
        modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<DataModel, String> tipoDeProducto = new TableColumn<>("Tipo de Producto");
        tipoDeProducto.setCellValueFactory(new PropertyValueFactory<>("tipoDeProducto"));

        TableColumn<DataModel, String> especificacionesProducto = new TableColumn<>("Especificaciones");
        especificacionesProducto.setCellValueFactory(new PropertyValueFactory<>("especificacionesProducto"));

        TableColumn<DataModel, String> existencias = new TableColumn<>("Existencias");
        existencias.setCellValueFactory(new PropertyValueFactory<>("existencias"));

        TableColumn<DataModel, String> precio = new TableColumn<>("Precio");
        precio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        table.getColumns().add(idProducto);
        table.getColumns().add(marcaProducto);
        table.getColumns().add(fabricante);
        table.getColumns().add(modelo);
        table.getColumns().add(tipoDeProducto);
        table.getColumns().add(especificacionesProducto);
        table.getColumns().add(existencias);
        table.getColumns().add(precio);

        VBox vbox = new VBox(table);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();

        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        //String url = "jdbc:mysql://localhost:3306/byteshop";
        //String username = "root";

        try (
                Connection conn = DriverManager.getConnection(url, usuario, contraseña); 
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Productos"); 
                ResultSet rs = stmt.executeQuery()) {

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

            table.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class DataModel {

        private int idProducto;
        private String marcaProducto;
        private String fabricante;
        private String modelo;
        private String tipoDeProducto;
        private String especificacionesProducto;
        private int existencias;
        private int precio;

        public DataModel(int idProducto, String marcaProducto, String fabricante, String modelo, String tipoDeProducto,
                String especificacionesProducto, int existencias, int precio) {
            this.idProducto = idProducto;
            this.marcaProducto = marcaProducto;
            this.fabricante = fabricante;
            this.modelo = modelo;
            this.tipoDeProducto = tipoDeProducto;
            this.especificacionesProducto = especificacionesProducto;
            this.existencias = existencias;
            this.precio = precio;
        }

        public int getIdProducto() {
            return idProducto;
        }

        public void setIdProducto(int idProducto) {
            this.idProducto = idProducto;
        }

        public String getMarcaProducto() {
            return marcaProducto;
        }

        public void setMarcaProducto(String marcaProducto) {
            this.marcaProducto = marcaProducto;
        }

        public String getFabricante() {
            return fabricante;
        }

        public void setFabricante(String fabricante) {
            this.fabricante = fabricante;
        }

        public String getModelo() {
            return modelo;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        public String getTipoDeProducto() {
            return tipoDeProducto;
        }

        public void setTipoDeProducto(String tipoDeProducto) {
            this.tipoDeProducto = tipoDeProducto;
        }

        public String getEspecificacionesProducto() {
            return especificacionesProducto;
        }

        public void setEspecificacionesProducto(String especificacionesProducto) {
            this.especificacionesProducto = especificacionesProducto;
        }

        public int getExistencias() {
            return existencias;
        }

        public void setExistencias(int existencias) {
            this.existencias = existencias;
        }

        public int getPrecio() {
            return precio;
        }

        public void setPrecio(int precio) {
            this.precio = precio;
        }

    }

}
