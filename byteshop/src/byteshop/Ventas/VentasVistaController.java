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
import javafx.stage.Stage;

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
    @FXML
    private Button btnModificarVenta;
    @FXML
    private Button btnEliminarVenta;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtbuscarIdVenta;
    @FXML
    private Button btnBuscarVentaById;
    // Varibles de conexion
    public static final String url = "jdbc:mysql://localhost:3306/byteshop";
    public static final String usuario = "root";
    public static final String contraseña = "616263646566676869";
    ResultSet rs;
    
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
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Ventas"); ResultSet rs = stmt.executeQuery()) {

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
            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
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
                    limpiarCampos();
                }
                conn.close();
            } catch (SQLException e) {
                if (e.getErrorCode() == 1452) {
                    // Manejo específico para el error de clave externa
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("NO SE ENCONTRO EL ID CLIENTE O ID FORMA DE PAGO EN LA BASE DE DATOS.");
                    alert.showAndWait();
                } else if (e.getErrorCode() == 1062) {
                    // Manejo específico para el error de clave primaria duplicada
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("VIOLACION DE CLAVE PRIMARIA, EL ID VENTA YA EXISTE EN LA TABLA.");
                    alert.showAndWait();
                } else {
                    // Otro manejo de excepciones o re-lanzamiento
                    System.out.println("Error desconocido: " + e.getMessage());
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                    alert.showAndWait();
                }
            } catch (Exception ex){
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
    
    public void limpiarCampos() {
        txtidventas.setText(null);
        txtFechacompra.setText(null);
        txtidcliente.setText(null);
        txtidFormapago.setText(null);
    }

    @FXML
    private void modificarVenta(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement ps = conn.prepareStatement("update ventas set idVenta = ?, idCliente = ?, fechaDeCompra = ?, idFormaDePago = ? where idVenta = ?");
                ps.setInt(5, Integer.parseInt(txtbuscarIdVenta.getText()));
                ps.setInt(1, Integer.parseInt(txtidventas.getText()));
                ps.setInt(2, Integer.parseInt(txtidcliente.getText()));
                ps.setDate(3, Date.valueOf(txtFechacompra.getText()));
                ps.setInt(4, Integer.parseInt(txtidFormapago.getText()));

                int resultado = ps.executeUpdate();
                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingreso exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Has Modificado tus datos correctamente.");
                    alert.showAndWait();
                    limpiarCampos();
                }

                conn.close();
            } catch (SQLException e) {
                if (e.getErrorCode() == 1452) {
                    // Manejo específico para el error de clave externa
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("NO SE ENCONTRO EL ID CLIENTE O ID FORMA DE PAGO EN LA BASE DE DATOS.");
                    alert.showAndWait();
                } else if (e.getErrorCode() == 1062) {
                    // Manejo específico para el error de clave primaria duplicada
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("VIOLACION DE CLAVE PRIMARIA, EL ID VENTA YA EXISTE EN LA TABLA.");
                    alert.showAndWait();
                } else {
                    // Otro manejo de excepciones o re-lanzamiento
                    System.out.println("Error desconocido: " + e.getMessage());
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                    alert.showAndWait();
                }
            } catch (Exception ex){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                alert.showAndWait();
            }  

        }
    }

    @FXML
    private void eliminaVenta(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement ps = conn.prepareStatement("delete from ventas where idVenta = ?");
                ps.setInt(1, Integer.parseInt(txtbuscarIdVenta.getText()));
                int resultado = ps.executeUpdate();
                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingreso exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Has Eliminado el dato correctamente.");
                    alert.showAndWait();
                    limpiarCampos();
                }
                conn.close();
            } catch (SQLException e ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("ERROR CON LA BASE DE DATOS");
                alert.showAndWait();
            } catch (Exception e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                alert.showAndWait();
            }

        }
    }

    @FXML
    private void limpiar(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    private void Cancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void buscarVentaById(ActionEvent event) {
        try {
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement stmt = conn.prepareStatement("select * from ventas where idVenta = ?");
            stmt.setString(1, txtbuscarIdVenta.getText());
            rs = stmt.executeQuery();

            limpiarCampos();

            if (rs.next()) {
                txtidventas.setText(String.valueOf(rs.getInt("idVenta")));
                txtFechacompra.setText(rs.getString("fechaDeCompra"));
                txtidcliente.setText(String.valueOf(rs.getString("idCliente")));
                txtidFormapago.setText(rs.getString("idFormaDePago"));

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("Venta no encontrada...");
                alert.showAndWait();
                limpiarCampos();
            }
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }
}