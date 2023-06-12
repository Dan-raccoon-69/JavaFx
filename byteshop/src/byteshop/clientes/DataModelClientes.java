package byteshop.clientes;

/**
 *
 * @author Daniel
 */
public class DataModelClientes {
    private int idCliente;
    private String nombreCliente;
    private int idFormaDePago;
    private String correo;
    private String numeroTelefonico;
    private int idDireccion;

    public DataModelClientes(int idCliente, String nombreCliente, int idFormaDePago, String correo, String numeroTelefonico, int idDireccion) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.idFormaDePago = idFormaDePago;
        this.correo = correo;
        this.numeroTelefonico = numeroTelefonico;
        this.idDireccion = idDireccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getIdFormaDePago() {
        return idFormaDePago;
    }

    public void setIdFormaDePago(int idFormaDePago) {
        this.idFormaDePago = idFormaDePago;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
     
}


