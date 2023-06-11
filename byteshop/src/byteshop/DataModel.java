package byteshop;

/**
 *
 * @author Daniel
 */
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
