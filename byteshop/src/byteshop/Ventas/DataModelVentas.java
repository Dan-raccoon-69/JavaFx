/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package byteshop.Ventas;

import java.sql.Date;

/**
 *
 * @author Migue
 */
public class DataModelVentas {
    private int idVenta;
    private int idCliente;
    private Date fechaDeCompra;
    private int idFormaDePago;

    public DataModelVentas(int idVenta, int idCliente, Date fechaDeCompra, int idFormaDePago) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.fechaDeCompra = fechaDeCompra;
        this.idFormaDePago = idFormaDePago;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaDeCompra() {
        return fechaDeCompra;
    }

    public void setFechaDeCompra(Date fechaDeCompra) {
        this.fechaDeCompra = fechaDeCompra;
    }

    public int getIdFormaDePago() {
        return idFormaDePago;
    }

    public void setIdFormaDePago(int idFormaDePago) {
        this.idFormaDePago = idFormaDePago;
    }
}
