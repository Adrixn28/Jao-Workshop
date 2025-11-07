package Model;

import java.util.Date;
import java.util.List;

public class Venta {

    private int idVenta;
    private String codigoSerial;     // VEN01, VEN02, etc.
    private Date fecha;
    private Cliente cliente;
    private List<ItemVenta> listaDetalle;
    private double total;
    private int estado;              // 0=progreso, 1=pagada, 2=falta_pagar, 3=vencida, 4=cancelada, 5=despachada
    private boolean esEnvioADomicilio; // true si es envío a domicilio
    private boolean despachada;        // true si ya fue despachada
    private String tipoPago;           // "online_domicilio", "online_local", "contraentrega", "local"

    public Venta(int idVenta, String codigoSerial, Date fecha, Cliente cliente, List<ItemVenta> listaDetalle, double total, int estado, boolean esEnvioADomicilio, String tipoPago) {
        this.idVenta = idVenta;
        this.codigoSerial = codigoSerial;
        this.fecha = fecha;
        this.cliente = cliente;
        this.listaDetalle = listaDetalle;
        this.total = total;
        this.estado = estado;
        this.esEnvioADomicilio = esEnvioADomicilio;
        this.despachada = false; // Por defecto no despachada
        this.tipoPago = tipoPago;
    }

    // Constructor simplificado para crear venta en progreso (carrito)
    public Venta(int idVenta, String codigoSerial, Cliente cliente) {
        this.idVenta = idVenta;
        this.codigoSerial = codigoSerial;
        this.fecha = new Date();
        this.cliente = cliente;
        this.listaDetalle = null;
        this.total = 0.0;
        this.estado = 0; // Estado en progreso
        this.esEnvioADomicilio = false;
        this.despachada = false;
        this.tipoPago = "";
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

    public String getCodigoSerial() {
        return codigoSerial;
    }

    public void setCodigoSerial(String codigoSerial) {
        this.codigoSerial = codigoSerial;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean isEsEnvioADomicilio() {
        return esEnvioADomicilio;
    }

    public void setEsEnvioADomicilio(boolean esEnvioADomicilio) {
        this.esEnvioADomicilio = esEnvioADomicilio;
    }

    public boolean isDespachada() {
        return despachada;
    }

    public void setDespachada(boolean despachada) {
        this.despachada = despachada;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    // Métodos de utilidad para estados
    public String getEstadoTexto() {
        switch (estado) {
            case 0: return "En Progreso";
            case 1: return "Pagada";
            case 2: return "Falta por Pagar";
            case 3: return "Vencida";
            case 4: return "Cancelada";
            case 5: return "Despachada";
            default: return "Desconocido";
        }
    }

    public boolean puedeGenerarFactura() {
        return estado == 1 || estado == 2; // Solo pagadas o falta por pagar
    }

    public boolean debeRestarStock() {
        // Resta stock si está pagada O si falta por pagar pero es envío a domicilio
        return estado == 1 || (estado == 2 && esEnvioADomicilio);
    }

    public void procesarVencimiento() {
        // Solo las ventas estado 2 (falta por pagar) sin envío pueden vencerse
        if (estado == 2 && !esEnvioADomicilio) {
            estado = 3; // Cambiar a vencida
        }
    }
    
    
   
    
    
    
    
    
    
}
