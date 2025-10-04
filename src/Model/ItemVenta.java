package Model;

public class ItemVenta {
   
    private int idVenta;
    private Repuesto respuesto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public ItemVenta(int idVenta, Repuesto respuesto, int cantidad, double precioUnitario, double subtotal) {
        this.idVenta = idVenta;
        this.respuesto = respuesto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Repuesto getRespuesto() {
        return respuesto;
    }

    public void setRespuesto(Repuesto respuesto) {
        this.respuesto = respuesto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
