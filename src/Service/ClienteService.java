package Service;

import Model.Repuesto;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para manejar operaciones relacionadas con clientes
 * Incluye datos de ejemplo y operaciones de consulta de repuestos
 */
public class ClienteService {
    
    private List<Repuesto> repuestosDisponibles;
    
    public ClienteService() {
        this.repuestosDisponibles = new ArrayList<>();
        inicializarRepuestosDeEjemplo();
    }
    
    /**
     * Inicializar datos de ejemplo de repuestos
     * En producción esto vendría de una base de datos
     */
    private void inicializarRepuestosDeEjemplo() {
        // Motor
        repuestosDisponibles.add(new Repuesto(1, "Filtro de Aceite", "Filtro de aceite original para motor", "Bosch", "Motor", 25.50, 15));
        repuestosDisponibles.add(new Repuesto(2, "Bujías NGK", "Juego de 4 bujías de platino", "NGK", "Motor", 45.99, 25));
        repuestosDisponibles.add(new Repuesto(3, "Correa de Distribución", "Correa de distribución original", "Gates", "Motor", 89.50, 8));
        repuestosDisponibles.add(new Repuesto(4, "Bomba de Agua", "Bomba de agua con junta", "Hepu", "Motor", 125.00, 6));
        
        // Frenos  
        repuestosDisponibles.add(new Repuesto(5, "Pastillas de Freno", "Pastillas de freno cerámicas delanteras", "Brembo", "Frenos", 85.00, 8));
        repuestosDisponibles.add(new Repuesto(6, "Discos de Freno", "Par de discos ventilados delanteros", "ATE", "Frenos", 155.75, 4));
        repuestosDisponibles.add(new Repuesto(7, "Líquido de Frenos", "Líquido DOT 4 - 500ml", "Bosch", "Frenos", 18.99, 30));
        
        // Suspensión
        repuestosDisponibles.add(new Repuesto(8, "Amortiguador Trasero", "Amortiguador trasero para suspensión", "Monroe", "Suspensión", 120.75, 5));
        repuestosDisponibles.add(new Repuesto(9, "Resorte Delantero", "Resorte espiral delantero", "Eibach", "Suspensión", 95.50, 7));
        repuestosDisponibles.add(new Repuesto(10, "Rotula de Dirección", "Rotula de dirección izquierda", "TRW", "Suspensión", 65.25, 10));
        
        // Eléctrico
        repuestosDisponibles.add(new Repuesto(11, "Batería 12V", "Batería de 12V 60Ah para automóvil", "Varta", "Eléctrico", 95.00, 12));
        repuestosDisponibles.add(new Repuesto(12, "Alternador", "Alternador 90A para sistema eléctrico", "Valeo", "Eléctrico", 180.50, 6));
        repuestosDisponibles.add(new Repuesto(13, "Starter Motor", "Motor de arranque reconstruido", "Bosch", "Eléctrico", 220.00, 3));
        repuestosDisponibles.add(new Repuesto(14, "Cables de Bujía", "Juego de cables de alta tensión", "NGK", "Eléctrico", 55.75, 18));
        
        // Transmisión
        repuestosDisponibles.add(new Repuesto(15, "Kit de Embrague", "Kit completo de embrague para transmisión manual", "LUK", "Transmisión", 450.00, 3));
        repuestosDisponibles.add(new Repuesto(16, "Aceite de Transmisión", "Aceite ATF para transmisión automática", "Mobil 1", "Transmisión", 42.99, 14));
        repuestosDisponibles.add(new Repuesto(17, "Filtro de Transmisión", "Filtro interno para caja automática", "Mann", "Transmisión", 38.50, 9));
        
        // Carrocería
        repuestosDisponibles.add(new Repuesto(18, "Parachoques Delantero", "Parachoques delantero original", "OEM", "Carrocería", 280.00, 2));
        repuestosDisponibles.add(new Repuesto(19, "Faro Derecho", "Faro delantero derecho con regulación", "Hella", "Carrocería", 165.99, 4));
        repuestosDisponibles.add(new Repuesto(20, "Espejo Retrovisor", "Espejo lateral izquierdo eléctrico", "TYC", "Carrocería", 89.75, 6));
        
        // Lubricantes
        repuestosDisponibles.add(new Repuesto(21, "Aceite Motor 5W-30", "Aceite sintético para motor 4 litros", "Castrol", "Lubricantes", 35.99, 20));
        repuestosDisponibles.add(new Repuesto(22, "Aceite Motor 15W-60", "Aceite mineral para motor diesel 5 litros", "Shell", "Lubricantes", 28.75, 25));
    }
    
    /**
     * Obtener todos los repuestos disponibles
     * @return Lista de repuestos
     */
    public List<Repuesto> obtenerTodosLosRepuestos() {
        return new ArrayList<>(repuestosDisponibles);
    }
    
    /**
     * Buscar repuestos por texto
     * @param texto Texto a buscar en nombre, descripción o marca
     * @return Lista de repuestos que coinciden
     */
    public List<Repuesto> buscarRepuestos(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return obtenerTodosLosRepuestos();
        }
        
        List<Repuesto> resultado = new ArrayList<>();
        String textoBusqueda = texto.toLowerCase().trim();
        
        for (Repuesto repuesto : repuestosDisponibles) {
            if (repuesto.getNombre().toLowerCase().contains(textoBusqueda) ||
                repuesto.getDescripcion().toLowerCase().contains(textoBusqueda) ||
                repuesto.getMarca().toLowerCase().contains(textoBusqueda)) {
                resultado.add(repuesto);
            }
        }
        
        return resultado;
    }
    
    /**
     * Filtrar repuestos por categoría
     * @param categoria Categoría a filtrar (null o "Todas las categorias" para todas)
     * @return Lista de repuestos filtrados
     */
    public List<Repuesto> filtrarPorCategoria(String categoria) {
        if (categoria == null || categoria.equals("Todas las categorias")) {
            return obtenerTodosLosRepuestos();
        }
        
        List<Repuesto> resultado = new ArrayList<>();
        for (Repuesto repuesto : repuestosDisponibles) {
            if (repuesto.getCategoria().equals(categoria)) {
                resultado.add(repuesto);
            }
        }
        
        return resultado;
    }
    
    /**
     * Buscar repuesto por ID
     * @param id ID del repuesto
     * @return Repuesto encontrado o null
     */
    public Repuesto buscarPorId(int id) {
        for (Repuesto repuesto : repuestosDisponibles) {
            if (repuesto.getIdRepuesto() == id) {
                return repuesto;
            }
        }
        return null;
    }
    
    /**
     * Obtener repuestos con stock bajo (menos de 10 unidades)
     * @return Lista de repuestos con stock bajo
     */
    public List<Repuesto> obtenerRepuestosConStockBajo() {
        List<Repuesto> resultado = new ArrayList<>();
        for (Repuesto repuesto : repuestosDisponibles) {
            if (repuesto.getStock() < 10) {
                resultado.add(repuesto);
            }
        }
        return resultado;
    }
    
    /**
     * Actualizar stock de un repuesto
     * @param id ID del repuesto
     * @param nuevoStock Nuevo stock
     * @return true si se actualizó, false si no se encontró
     */
    public boolean actualizarStock(int id, int nuevoStock) {
        Repuesto repuesto = buscarPorId(id);
        if (repuesto != null) {
            repuesto.setStock(nuevoStock);
            return true;
        }
        return false;
    }
    
    /**
     * Aplicar filtros múltiples a los repuestos
     * @param textoBusqueda Texto a buscar
     * @param categoria Categoría a filtrar
     * @return Lista filtrada
     */
    public List<Repuesto> aplicarFiltros(String textoBusqueda, String categoria) {
        List<Repuesto> resultado = filtrarPorCategoria(categoria);
        
        if (textoBusqueda != null && !textoBusqueda.trim().isEmpty()) {
            List<Repuesto> resultadoFiltrado = new ArrayList<>();
            String texto = textoBusqueda.toLowerCase().trim();
            
            for (Repuesto repuesto : resultado) {
                if (repuesto.getNombre().toLowerCase().contains(texto) ||
                    repuesto.getDescripcion().toLowerCase().contains(texto) ||
                    repuesto.getMarca().toLowerCase().contains(texto)) {
                    resultadoFiltrado.add(repuesto);
                }
            }
            return resultadoFiltrado;
        }
        
        return resultado;
    }
    
    /**
     * Actualiza el inventario después de una venta
     * Remueve repuestos que se quedaron sin stock
     * @param repuestosSinStock Lista de repuestos que se quedaron sin stock
     */
    public void actualizarInventarioDespuesVenta(java.util.List<Repuesto> repuestosSinStock) {
        // Remover repuestos sin stock de la lista principal
        for (Repuesto repuestoSinStock : repuestosSinStock) {
            repuestosDisponibles.removeIf(repuesto -> 
                repuesto.getIdRepuesto() == repuestoSinStock.getIdRepuesto()
            );
            System.out.println(" Removido del inventario: " + repuestoSinStock.getNombre());
        }
        
        System.out.println(" Inventario actualizado. Repuestos disponibles: " + repuestosDisponibles.size());
    }
    
    /**
     * Obtiene un repuesto específico por ID
     * @param idRepuesto ID del repuesto a buscar
     * @return Repuesto encontrado o null
     */
    public Repuesto obtenerRepuestoPorId(int idRepuesto) {
        return repuestosDisponibles.stream()
                .filter(repuesto -> repuesto.getIdRepuesto() == idRepuesto)
                .findFirst()
                .orElse(null);
    }
    
    // ========== MÉTODOS DE LÓGICA DE NEGOCIO PARA VENTAS ==========
    
    /**
     * Crea objetos ItemVenta a partir de los items del carrito
     * SOLO lógica de transformación de datos, sin UI
     */
    public java.util.List<Model.ItemVenta> crearItemsVenta(Model.Carrito carrito) {
        java.util.List<Model.ItemVenta> itemsVenta = new java.util.ArrayList<>();
        
        for (Model.ItemCarrito itemCarrito : carrito.obtenerItemsArray()) {
            Model.ItemVenta itemVenta = new Model.ItemVenta(
                1, // idVenta temporal
                itemCarrito.getRepuesto(),
                itemCarrito.getCantidad(),
                itemCarrito.getPrecioUnitario(),
                itemCarrito.getSubtotal()
            );
            itemsVenta.add(itemVenta);
        }
        
        return itemsVenta;
    }
    
    /**
     * Crea un objeto Venta con todos los datos necesarios
     * SOLO lógica de negocio, sin UI
     */
    public Model.Venta crearVenta(String codigoVenta, Model.Cliente cliente, 
                                 java.util.List<Model.ItemVenta> itemsVenta,
                                 double total, String opcionPago) {
        return new Model.Venta(
            1, // ID temporal
            codigoVenta,
            new java.util.Date(),
            cliente,
            itemsVenta,
            total,
            determinarEstadoVenta(opcionPago),
            opcionPago.contains("Domicilio"),
            opcionPago
        );
    }
    
    /**
     * Crea una factura basada en una venta
     * SOLO lógica de negocio, sin UI
     */
    public Model.Factura crearFactura(Model.Venta venta, String codigoFactura, String opcionPago) {
        String metodoPago = opcionPago.contains("Online") ? "online" : "contraentrega";
        return new Model.Factura(venta, codigoFactura, metodoPago);
    }
    
    /**
     * Determina el estado de una venta según la opción de pago
     * SOLO lógica de negocio, sin UI
     */
    public int determinarEstadoVenta(String opcionPago) {
        if (opcionPago.contains("Pagar Online")) {
            return 1; // Pagada
        } else {
            return 2; // Falta por pagar
        }
    }
    
    /**
     * Construye el mensaje de confirmación de venta
     * SOLO lógica de construcción de texto, sin UI
     */
    public String construirMensajeConfirmacion(String codigoVenta, String codigoFactura,
                                              Model.Carrito carrito, String opcionPago,
                                              String direccion, String rutaFactura,
                                              String reporteStock) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("🎉 ¡VENTA PROCESADA EXITOSAMENTE!\n\n");
        mensaje.append("📋 DETALLES:\n");
        mensaje.append("• Código de venta: ").append(codigoVenta).append("\n");
        mensaje.append("• Código de factura: ").append(codigoFactura).append("\n");
        mensaje.append("• Items: ").append(carrito.getNumeroItems()).append("\n");
        mensaje.append("• Total: $").append(String.format("%.2f", carrito.getTotalCarrito())).append("\n");
        mensaje.append("• Modalidad: ").append(opcionPago).append("\n");
        
        // Estado de la venta
        String estado = determinarEstadoVenta(opcionPago) == 1 ? "Pagada ✅" : "Pendiente de pago ⏳";
        mensaje.append("• Estado: ").append(estado).append("\n");
        
        if (opcionPago.contains("Domicilio")) {
            mensaje.append("• Dirección: ").append(direccion).append("\n");
        }
        
        if (rutaFactura != null) {
            mensaje.append("\n📄 Factura generada y abierta automáticamente");
        }
        
        // Información sobre stock
        mensaje.append("\n\n").append(reporteStock);
        
        return mensaje.toString();
    }
    
    /**
     * Construye el mensaje de productos removidos por falta de stock
     * SOLO lógica de construcción de texto, sin UI
     */
    public String construirMensajeProductosRemovidos(java.util.List<Repuesto> repuestosSinStock) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("🗑️ Productos removidos por falta de stock:\n");
        for (Repuesto repuesto : repuestosSinStock) {
            mensaje.append("• ").append(repuesto.getNombre()).append("\n");
        }
        return mensaje.toString();
    }
}