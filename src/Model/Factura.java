package Model;

import java.util.Date;
import java.util.List;

/**
 * Factura que hereda de Venta
 * Se genera cuando una venta está pagada o falta por pagar
 * Tiene funcionalidad adicional de impresión y estado de despacho
 */
public class Factura extends Venta {
    
    private String codigoFactura;    // FACT01, FACT02, etc.
    private boolean impresa;         // Si la factura ha sido impresa
    private Date fechaImpresion;     // Cuando fue impresa
    private String metodoPago;       // "online", "contraentrega", "efectivo"

    /**
     * Constructor que crea una factura basada en una venta
     */
    public Factura(Venta venta, String codigoFactura, String metodoPago) {
        super(venta.getIdVenta(), venta.getCodigoSerial(), venta.getFecha(), 
              venta.getCliente(), venta.getListaDetalle(), venta.getTotal(), 
              venta.getEstado(), venta.isEsEnvioADomicilio(), venta.getTipoPago());
        
        this.codigoFactura = codigoFactura;
        this.metodoPago = metodoPago;
        this.impresa = false;
        this.fechaImpresion = null;
        
        // Una factura por defecto no está despachada
        this.setDespachada(false);
    }

    /**
     * Constructor completo para factura
     */
    public Factura(int idVenta, String codigoSerial, String codigoFactura, Date fecha, 
                   Cliente cliente, List<ItemVenta> listaDetalle, double total, 
                   int estado, boolean esEnvioADomicilio, String tipoPago, String metodoPago) {
        super(idVenta, codigoSerial, fecha, cliente, listaDetalle, total, estado, esEnvioADomicilio, tipoPago);
        this.codigoFactura = codigoFactura;
        this.metodoPago = metodoPago;
        this.impresa = false;
        this.fechaImpresion = null;
    }

    /**
     * Marca la factura como impresa
     */
    public void marcarComoImpresa() {
        this.impresa = true;
        this.fechaImpresion = new Date();
    }

    /**
     * Marca la factura como despachada (override del método padre)
     */
    public void marcarComoDespachada() {
        this.setDespachada(true);
        // Si es envío a domicilio y se marca como despachada, el estado cambia a 5
        if (this.isEsEnvioADomicilio()) {
            this.setEstado(5); // Estado despachada
        }
    }

    /**
     * Verifica si la factura puede ser despachada
     */
    public boolean puedeSerDespachada() {
        // Solo se puede despachar si está pagada o falta por pagar, y no está ya despachada
        return (getEstado() == 1 || getEstado() == 2) && !isDespachada();
    }

    /**
     * Genera el contenido de la factura para impresión
     */
    public String generarContenidoFactura() {
        StringBuilder contenido = new StringBuilder();
        
        contenido.append("================================\n");
        contenido.append("         JAO WORKSHOP\n");
        contenido.append("    Repuestos Kawasaki\n");
        contenido.append("================================\n");
        contenido.append("Factura: ").append(codigoFactura).append("\n");
        contenido.append("Venta: ").append(getCodigoSerial()).append("\n");
        contenido.append("Fecha: ").append(getFecha().toString()).append("\n");
        contenido.append("Cliente: ").append(getCliente().getPrimerNombre()).append(" ").append(getCliente().getPrimerApellido()).append("\n");
        contenido.append("Cédula: ").append(getCliente().getCedula()).append("\n");
        contenido.append("Teléfono: ").append(getCliente().getTelefono()).append("\n");
        
        if (isEsEnvioADomicilio()) {
            contenido.append("Dirección: ").append(getCliente().getDireccion()).append("\n");
            contenido.append("ENVÍO A DOMICILIO\n");
        } else {
            contenido.append("RECOGER EN TIENDA\n");
        }
        
        contenido.append("--------------------------------\n");
        contenido.append("PRODUCTOS:\n");
        
        for (ItemVenta item : getListaDetalle()) {
            contenido.append(String.format("%-20s x%d\n", 
                item.getRespuesto().getNombre(), item.getCantidad()));
            contenido.append(String.format("  $%.2f c/u = $%.2f\n", 
                item.getPrecioUnitario(), item.getSubtotal()));
        }
        
        contenido.append("--------------------------------\n");
        contenido.append(String.format("TOTAL: $%.2f\n", getTotal()));
        contenido.append("Método de Pago: ").append(metodoPago).append("\n");
        contenido.append("Estado: ").append(getEstadoTexto()).append("\n");
        contenido.append("================================\n");
        
        if (impresa) {
            contenido.append("Impresa: ").append(fechaImpresion.toString()).append("\n");
        }
        
        return contenido.toString();
    }

    // Getters y Setters
    public String getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public boolean isImpresa() {
        return impresa;
    }

    public void setImpresa(boolean impresa) {
        this.impresa = impresa;
    }

    public Date getFechaImpresion() {
        return fechaImpresion;
    }

    public void setFechaImpresion(Date fechaImpresion) {
        this.fechaImpresion = fechaImpresion;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return "Factura{" +
               "codigo='" + codigoFactura + '\'' +
               ", venta='" + getCodigoSerial() + '\'' +
               ", cliente='" + getCliente().getPrimerNombre() + " " + getCliente().getPrimerApellido() + '\'' +
               ", total=" + getTotal() +
               ", estado='" + getEstadoTexto() + '\'' +
               ", despachada=" + isDespachada() +
               '}';
    }
}