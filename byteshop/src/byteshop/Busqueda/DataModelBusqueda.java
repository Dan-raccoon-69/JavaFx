package byteshop.Busqueda;

public class DataModelBusqueda {
    private int idProducto;
    private String marcaProducto;
    private String fabricante;
    private String modelo;
    private String especificacionesProducto;
    private int existencias;
    private int precio;

    public DataModelBusqueda(int idProducto, String marcaProducto, String fabricante, String modelo, String especificacionesProducto, int existencias, int precio) {
        this.idProducto = idProducto;
        this.marcaProducto = marcaProducto;
        this.fabricante = fabricante;
        this.modelo = modelo;
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
