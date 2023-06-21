/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package byteshop.Busqueda;

import byteshop.Ticket.TicketViewController;
import byteshop.Ventas.DataModelVentas;
import byteshop.direccion.DireccionController;
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class BusquedaViewController implements Initializable {

    public static int total;

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
    private ObservableList<Producto> data2;

    private DataModelBusqueda productoSeleccionado;
    private List<Producto> carrito = new ArrayList<>();
    private List<Producto> listaProductos = new ArrayList<>();

    // Varibles de conexion
    public static final String url = "jdbc:mysql://localhost:3306/byteshop";
    public static final String usuario = "root";
    public static final String contraseña = "616263646566676869";
    ResultSet rs, rs2;
    @FXML
    private TableColumn<Producto, String> marcaColumn;
    @FXML
    private TableColumn<Producto, String> tipoColumn;
    @FXML
    private TableColumn<Producto, String> modeloColumn;
    @FXML
    private TableColumn<Producto, Integer> precioColumn;
    @FXML
    private TableView<Producto> tableViewCarrito;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button btnBorrarprod;
    @FXML
    private Button btnBorrarprod1;
    @FXML
    private Button btnComprar11;
    @FXML
    private Button Refresh;
    private Object tblTicket;
    private Object tableViewTicket;

    /**
     *
     */
    public BusquedaViewController() {
        data = FXCollections.observableArrayList();
        data2 = FXCollections.observableArrayList();
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

        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marcaProducto"));
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoDeProducto"));
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tableViewCarrito.setItems(data2);

    }

    public void loadDataFromDatabase() {

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Productos"); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String marcaProducto = rs.getString("marcaProducto");
                String fabricante = rs.getString("fabricante");
                String modelo = rs.getString("modelo");
                String tipoDeProducto = rs.getString("tipoDeProducto");
                String especificacionesProducto = rs.getString("especificacionesProducto");
                int existencias = rs.getInt("existencias");
                int precio = rs.getInt("precio");

                DataModelBusqueda DataModelBusca = new DataModelBusqueda(idProducto, marcaProducto, fabricante, modelo, tipoDeProducto, especificacionesProducto, existencias, precio);
                data.add(DataModelBusca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void RealizarBusqueda(ActionEvent event) {
        String textoBusqueda = txtbarrabusqueda.getText().trim();
        // Realiza la búsqueda en la base de datos utilizando el texto de búsqueda
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Productos WHERE modelo LIKE ? OR tipoDeProducto LIKE ? OR marcaProducto LIKE ? OR precio LIKE ?")) {

            stmt.setString(1, "%" + textoBusqueda + "%");
            stmt.setString(2, "%" + textoBusqueda + "%");
            stmt.setString(3, "%" + textoBusqueda + "%");
            stmt.setString(4, "%" + textoBusqueda + "%");

            ResultSet rs = stmt.executeQuery();

            // Limpiar la lista actual antes de agregar los nuevos resultados de la búsqueda
            data.clear();

            boolean resultadosEncontrados = false;

            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String marcaProducto = rs.getString("marcaProducto");
                String fabricante = rs.getString("fabricante");
                String modelo = rs.getString("modelo");
                String tipoDeProducto = rs.getString("tipoDeProducto");
                String especificacionesProducto = rs.getString("especificacionesProducto");
                int existencias = rs.getInt("existencias");
                int precio = rs.getInt("precio");

                DataModelBusqueda dataModelBusca = new DataModelBusqueda(idProducto, marcaProducto, fabricante, modelo, tipoDeProducto, especificacionesProducto, existencias, precio);
                data.add(dataModelBusca);

                resultadosEncontrados = true;
            }

            if (!resultadosEncontrados) {
                // Mostrar mensaje de error de producto inexistente
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de búsqueda");
                alert.setHeaderText(null);
                alert.setContentText("No se encontraron productos que coincidan con la búsqueda.");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void RealizarCompra(ActionEvent event) {

        try {
            int existenciasProducto = 0;
            DataModelBusqueda DataModelbuscar = tableViewBusqueda.getSelectionModel().getSelectedItem();
            int idProducto = DataModelbuscar.getIdProducto();
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);

            // consulta para saber si hay existencias
            PreparedStatement stmt2 = conn.prepareStatement("select existencias from Productos where idProducto = ?");
            stmt2.setInt(1, idProducto);
            rs2 = stmt2.executeQuery();

            if (rs2.next()) {
                existenciasProducto = Integer.parseInt(rs2.getString("existencias"));
            }

            if (existenciasProducto >= 1) {
                // Consulta para agregar - mostrar los datos en el carrito
                PreparedStatement stmt = conn.prepareStatement("select * from Productos where idProducto = ?");
                stmt.setInt(1, idProducto);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    int idProductoC = rs.getInt("idProducto");
                    String marca = rs.getString("marcaProducto");
                    String tipoProducto = rs.getString("tipoDeProducto");
                    String modelo = rs.getString("modelo");
                    int precio = rs.getInt("precio");
                    int existencias = rs.getInt("existencias");

                    Producto productoCarrito = new Producto(idProductoC, marca, tipoProducto, modelo, precio, existencias);
                    data2.add(productoCarrito);

                    System.out.println("Se obtuvieron los datos");

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("Error...");
                    alert.showAndWait();
                }
                realizaCompra(event);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("NO CONTAMOS CON EXISTENCIAS DE ESTE PRODUCTO");
                alert.showAndWait();
            }

        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setTitle("Error");
            alert.setContentText("NO SELECCIONASTE NINGUN PRODUCTO");
            alert.showAndWait();
        }

    }

    private void realizaCompra(ActionEvent event) {
        int total = 0;
        for (Producto elemento : data2) {
            total += elemento.getPrecio();
        }
        txtTotal.setText("$ " + total + "");
    }

    @FXML
    private void vaciarCarrito(ActionEvent event) {
        data2.clear();
        realizaCompra(event);
    }

    @FXML
    private void quitarElementoCarrito(ActionEvent event) {
        try {
            data2.remove(0);
            realizaCompra(event);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setTitle("Error");
            alert.setContentText("NO HAY ELEMENTOS EN EL CARRITO");
            alert.showAndWait();
        }

    }

    @FXML
    private void Actualiza(ActionEvent event) {
        data.clear();
        loadDataFromDatabase();
        /*
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Datos Actualizados");
        alert.setHeaderText(null);
        alert.setContentText("Datos Actualizados");
        alert.showAndWait();
         */
    }
    
    public int obtenerIdCliente() {
        Random random = new Random();
        int numero = random.nextInt(5) + 1;
        return numero;
    }

    @FXML
    private void genTicket(ActionEvent event) {
        TicketViewController contarid = new TicketViewController();
        int resultado = 0;
        try {
            if (!data2.isEmpty()) {
                Iterator<Producto> iterator = data2.iterator();
                while (iterator.hasNext()) {
                    Producto producto = iterator.next();
                    int idProducto = producto.getIdProducto();
                    int existencias = producto.getExistencias();
                    existencias--;
                    try {
                        Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                        PreparedStatement ps = conn.prepareStatement("update Productos set existencias = ? where idProducto = ?");
                        ps.setInt(2, idProducto);
                        ps.setInt(1, existencias);
                        resultado = ps.executeUpdate();
                        conn.close();
                    } catch (Exception ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("");
                        alert.setTitle("Error");
                        alert.setContentText("ERROR DESCONOCIDO " + ex);
                        alert.showAndWait();
                    }
                } // while - for
                if (resultado > 0) {
                    Actualiza(event);
                    // Crear una instancia del controlador del ticket
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/byteshop/Ticket/TicketView.fxml"));
                    Parent root = loader.load();
                    TicketViewController ticketController = loader.getController();
                    // Pasar los datos del carrito al controlador del ticket
                    ticketController.actualizarTicket(data2);
                    // Calcular el total y pasarlo al controlador del ticket
                    int total = 0;
                    for (Producto elemento : data2) {
                        total += elemento.getPrecio();
                    }
                    ticketController.setTotal("$ " + total);

                    // Mostrar la ventana del ticket
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("NO HAY PRODUCTOS EN EL CARRITO");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
