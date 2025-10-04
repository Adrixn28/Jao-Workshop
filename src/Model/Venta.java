package Model;

import java.util.Date;
import java.util.List;

public class Venta {

    private int idVenta;
    private Date fecha;
    private Cliente cliente;
    private List<ItemVenta> listaDetalle;
    private double total;

    public Venta(int idVenta, Date fecha, Cliente cliente, List<ItemVenta> listaDetalle, double total) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.cliente = cliente;
        this.listaDetalle = listaDetalle;
        this.total = total;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenta> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<ItemVenta> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
    
    
    
    
    
    
    
}
