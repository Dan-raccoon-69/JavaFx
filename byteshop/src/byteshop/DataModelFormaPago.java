package byteshop;

/**
 *
 * @author Daniel
 */
public class DataModelFormaPago {
    private int idFormaDePago;
    private String nombreFormaDePago;

    public DataModelFormaPago(int idFormaDePago, String nombreFormaDePago) {
        this.idFormaDePago = idFormaDePago;
        this.nombreFormaDePago = nombreFormaDePago;
    }

    public int getIdFormaDePago() {
        return idFormaDePago;
    }

    public void setIdFormaDePago(int idFormaDePago) {
        this.idFormaDePago = idFormaDePago;
    }

    public String getNombreFormaDePago() {
        return nombreFormaDePago;
    }

    public void setNombreFormaDePago(String nombreFormaDePago) {
        this.nombreFormaDePago = nombreFormaDePago;
    }

}
