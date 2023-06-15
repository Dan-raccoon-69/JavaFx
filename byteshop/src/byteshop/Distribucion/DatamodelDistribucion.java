package byteshop.Distribucion;

/**
 *
 * @author Migue
 */
public class DatamodelDistribucion {
  
    private int idPaquete;
    private int idVenta;
    private String nombreDeEmpresaPaquete;
    private int idCliente;
    private int idDireccion;
    private String codigoRastreo;

    public DatamodelDistribucion(int idPaquete, int idVenta, String nombreDeEmpresaPaquete, int idCliente, int idDireccion, String codigoRastreo) {
        this.idPaquete = idPaquete;
        this.idVenta = idVenta;
        this.nombreDeEmpresaPaquete = nombreDeEmpresaPaquete;
        this.idCliente = idCliente;
        this.idDireccion = idDireccion;
        this.codigoRastreo = codigoRastreo;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getNombreDeEmpresaPaquete() {
        return nombreDeEmpresaPaquete;
    }

    public void setNombreDeEmpresaPaquete(String nombreDeEmpresaPaquete) {
        this.nombreDeEmpresaPaquete = nombreDeEmpresaPaquete;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCodigoRastreo() {
        return codigoRastreo;
    }

    public void setCodigoRastreo(String codigoRastreo) {
        this.codigoRastreo = codigoRastreo;
    }
}
