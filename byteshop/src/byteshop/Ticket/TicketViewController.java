package byteshop.Ticket;

import byteshop.Busqueda.BusquedaViewController;
import byteshop.Busqueda.Producto;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TicketViewController implements Initializable {

    
    // Varibles de conexion
    public static final String url = "jdbc:mysql://localhost:3306/byteshop";
    public static final String usuario = "root";
    public static final String contraseña = "616263646566676869";
    ResultSet rs;
    @FXML
    private TableView<Producto> tableViewTicket;
    @FXML
    private TableColumn<Producto, String> marcaColumn;
    @FXML
    private TableColumn<Producto, String> tipoColumn;
    @FXML
    private TableColumn<Producto, String> modeloColumn;
    @FXML
    private TableColumn<Producto, Integer> precioColumn;

    private ObservableList<Producto> data2;
    @FXML
    private Label labelPrecio;
    @FXML
    private Label labelNombreUser;
    @FXML
    private Label labelFormaPago;
    @FXML
    private Label labelFecha;
    LocalDate fechaActual = LocalDate.now();
    String fecha = fechaActual + "";
    public String formaPago;
    int idC = 0;
    int idFormaDePago = 0;
    java.sql.Date fechaDeCompra;
    

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marcaProducto"));
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoDeProducto"));
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        clienteId(obtenerIdCliente());
        ingresarVenta(idC, idFormaDePago, fechaDeCompra);
    }

    public void actualizarTicket(List<Producto> carrito) {
        data2 = FXCollections.observableArrayList(carrito);
        tableViewTicket.setItems(data2);
        //clienteId(obtenerIdCliente()); 
    }

    public void setTotal(String total) {
        labelPrecio.setText(total);
    }
    
    public int obtenerIdCliente() {
        Random random = new Random();
        int numero = random.nextInt(5) + 1;
        return numero;
    }
    
    public void ingresarVenta(int idC, int idFormaPago, Date fecha){
        try {
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement psInsert = conn.prepareStatement("INSERT INTO ventas (idCliente, fechaDeCompra, idFormaDePago) VALUES (?, ?, ?)");
            psInsert.setInt(1, idC);
            psInsert.setDate(2, fecha);
            psInsert.setInt(3, idFormaPago);
            psInsert.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }
    
     public void clienteId(int idP) {
        
        try {
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement stmt = conn.prepareStatement("SELECT c.nombreCliente, fp.nombreFormaDePago, c.idFormaDePago FROM clientes c JOIN formasDePago fp ON c.idFormaDePago = fp.idFormaDePago where c.idCliente = ?");
            stmt.setInt(1, idP);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                labelNombreUser.setText(rs.getString("c.nombreCliente"));
                labelFormaPago.setText(rs.getString("fp.nombreFormaDePago"));
                labelFecha.setText(fecha);
                
                // AGREGA LA VENTA  LA TABLA VENTAS CON SUS RESPECTIVOS DATOS
                idC = idP;
                idFormaDePago = rs.getInt("c.idFormaDePago");
                fechaDeCompra = new java.sql.Date(System.currentTimeMillis());
                
                /*
                PreparedStatement psInsert = conn.prepareStatement("INSERT INTO ventas (idCliente, fechaDeCompra, idFormaDePago) VALUES (?, ?, ?)");
                psInsert.setInt(1, idP);
                psInsert.setDate(2, fechaDeCompra);
                psInsert.setInt(3, idFormaDePago);
                psInsert.executeUpdate();
                */
            } 
            System.out.println(idC + "," + idFormaDePago + "," + fechaActual);

        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }
}