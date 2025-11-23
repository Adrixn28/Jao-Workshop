package Model;

/**
 * Representa un item en el carrito de compras (antes ItemVenta)
 * Este item se usa tanto en el carrito del cliente como del recepcionista
 * Contiene un repuesto, cantidad y precios calculados
 */
public class ItemCarrito {
    
    private String cedulaCliente;  // Cedula del cliente (para identificar el carrito)
    private Repuesto repuesto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    /**
     * Constructor para crear un item del carrito
     * @param cedulaCliente Cedula del cliente propietario del carrito
     * @param repuesto Repuesto seleccionado
     * @param cantidad Cantidad deseada
     * @param precioUnitario Precio unitario del repuesto
     */
    public ItemCarrito(String cedulaCliente, Repuesto repuesto, int cantidad, double precioUnitario) {
        this.cedulaCliente = cedulaCliente;
        this.repuesto = repuesto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario; // Se calcula automáticamente
    }

    /**
     * Actualiza la cantidad y recalcula el subtotal
     */
    public void actualizarCantidad(int nuevaCantidad) {
        this.cantidad = nuevaCantidad;
        this.subtotal = cantidad * precioUnitario;
    }

    // Getters y Setters
    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
        // Recalcular subtotal si cambia el repuesto
        this.subtotal = cantidad * precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = cantidad * precioUnitario;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Convierte este ItemCarrito a ItemVenta cuando se procesa la compra
     * @param idVenta ID de la venta a la que pertenecerá
     * @return ItemVenta equivalente
     */
    public ItemVenta toItemVenta(int idVenta) {
        return new ItemVenta(idVenta, this.repuesto, this.cantidad, this.precioUnitario, this.subtotal);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ItemCarrito that = (ItemCarrito) obj;
        return cedulaCliente.equals(that.cedulaCliente) && 
               repuesto.getIdRepuesto() == that.repuesto.getIdRepuesto();
    }

    @Override
    public String toString() {
        return "ItemCarrito{" +
               "cliente='" + cedulaCliente + '\'' +
               ", repuesto=" + repuesto.getNombre() +
               ", cantidad=" + cantidad +
               ", subtotal=" + subtotal +
               '}';
    }
}