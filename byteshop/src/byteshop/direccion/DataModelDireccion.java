package byteshop.direccion;

/**
 *
 * @author Daniel
 */
public class DataModelDireccion {
    private int idDireccion;
    private String pais;
    private String estadoDeResidencia;
    private String alcaldia;
    private String colonia;
    private String codigoPostal;
    private String calle;
    private int numInterior;
    private int numExterior;

    public DataModelDireccion(int idDireccion, String pais, String estadoDeResidencia, String alcaldia, String colonia, String codigoPostal, String calle, int numInterior, int numExterior) {
        this.idDireccion = idDireccion;
        this.pais = pais;
        this.estadoDeResidencia = estadoDeResidencia;
        this.alcaldia = alcaldia;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.calle = calle;
        this.numInterior = numInterior;
        this.numExterior = numExterior;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstadoDeResidencia() {
        return estadoDeResidencia;
    }

    public void setEstadoDeResidencia(String estadoDeResidencia) {
        this.estadoDeResidencia = estadoDeResidencia;
    }

    public String getAlcaldia() {
        return alcaldia;
    }

    public void setAlcaldia(String alcaldia) {
        this.alcaldia = alcaldia;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumInterior() {
        return numInterior;
    }

    public void setNumInterior(int numInterior) {
        this.numInterior = numInterior;
    }

    public int getNumExterior() {
        return numExterior;
    }

    public void setNumExterior(int numExterior) {
        this.numExterior = numExterior;
    }
    
    
}
