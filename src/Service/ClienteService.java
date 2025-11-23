package Service;

import Model.Repuesto;
import Percistencia.SistemaDatos;
import listaDoble.Lista;
import listaDoble.Nodo;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para manejar operaciones relacionadas con clientes
 * Carga repuestos desde SistemaDatos (persistencia)
 */
public class ClienteService {
    
    private List<Repuesto> repuestosDisponibles;
    
    public ClienteService() {
        this.repuestosDisponibles = new ArrayList<>();
        cargarRepuestosDesdeSistemaDatos();
    }
    
    /**
     * Cargar repuestos desde SistemaDatos (persistencia)
     * Convierte la Lista doble a ArrayList para compatibilidad
     */
    private void cargarRepuestosDesdeSistemaDatos() {
        try {
            // Obtener la instancia de SistemaDatos
            SistemaDatos sistemaDatos = SistemaDatos.getInstancia();
            Lista listaRepuestos = sistemaDatos.listaRepuestos;
            
            // Si la lista está vacía, no hay repuestos disponibles
            if (listaRepuestos == null || listaRepuestos.estaVacia()) {
                System.out.println("⚠️ No hay repuestos disponibles en SistemaDatos");
                return;
            }
            
            // Recorrer la lista doble y convertir a ArrayList
            Nodo actual = listaRepuestos.getPrimero();
            while (actual != null) {
                Object dato = actual.getDato();
                if (dato instanceof Repuesto) {
                    Repuesto repuesto = (Repuesto) dato;
                    
                    // Asegurar que el repuesto tenga estado válido
                    if (repuesto.getEstado() == null || repuesto.getEstado().trim().isEmpty()) {
                        repuesto.setEstado("Disponible");
                    }
                    
                    // Solo agregar repuestos con estado "Disponible" o que tengan stock > 0
                    // Esto permite que se muestren repuestos disponibles para venta
                    if ("Disponible".equalsIgnoreCase(repuesto.getEstado()) || repuesto.getStock() > 0) {
                        repuestosDisponibles.add(repuesto);
                    }
                }
                actual = actual.getSiguiente();
            }
            
            System.out.println("✅ Repuestos cargados desde SistemaDatos: " + repuestosDisponibles.size() + " repuestos disponibles");
            
        } catch (Exception e) {
            System.err.println("❌ Error al cargar repuestos desde SistemaDatos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Recargar repuestos desde SistemaDatos
     * Útil cuando se agregan, actualizan o eliminan repuestos desde Proveedor
     */
    public void recargarRepuestos() {
        repuestosDisponibles.clear();
        cargarRepuestosDesdeSistemaDatos();
        System.out.println("🔄 Repuestos recargados desde SistemaDatos");
    }
    
    /**
     * Obtener todos los repuestos disponibles
     * IMPORTANTE: Recarga desde SistemaDatos cada vez para obtener los repuestos más actualizados
     * @return Lista de repuestos actualizada
     */
    public List<Repuesto> obtenerTodosLosRepuestos() {
        // Recargar desde SistemaDatos para obtener los repuestos más recientes
        // Esto asegura que cuando se agreguen nuevos repuestos en Proveedor, se vean en Cliente y VentaRecepcionista
        recargarRepuestos();
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
     * IMPORTANTE: NO remueve repuestos sin stock, solo los mantiene con stock 0 para que se sigan mostrando
     * @param repuestosSinStock Lista de repuestos que se quedaron sin stock
     */
    public void actualizarInventarioDespuesVenta(java.util.List<Repuesto> repuestosSinStock) {
        // NO remover repuestos sin stock, solo asegurar que tengan stock 0
        // Los repuestos se seguirán mostrando en las cards pero sin controles de carrito
        for (Repuesto repuestoSinStock : repuestosSinStock) {
            Repuesto repuestoEnLista = buscarPorId(repuestoSinStock.getIdRepuesto());
            if (repuestoEnLista != null) {
                repuestoEnLista.setStock(0); // Asegurar que el stock sea 0
                System.out.println("✅ Repuesto con stock 0 mantenido en inventario: " + repuestoSinStock.getNombre());
            }
        }
        
        System.out.println("✅ Inventario actualizado. Repuestos disponibles: " + repuestosDisponibles.size() + 
                          " (incluyendo " + repuestosSinStock.size() + " con stock 0)");
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
        String metodoPago;
        if (opcionPago.contains("Efectivo")) {
            metodoPago = "efectivo";
        } else if (opcionPago.contains("Tarjeta Débito")) {
            metodoPago = "tarjeta_debito";
        } else if (opcionPago.contains("Tarjeta Crédito")) {
            metodoPago = "tarjeta_credito";
        } else if (opcionPago.contains("Transferencia")) {
            metodoPago = "transferencia";
        } else if (opcionPago.contains("Online")) {
            metodoPago = "online";
        } else {
            metodoPago = "contraentrega";
        }
        return new Model.Factura(venta, codigoFactura, metodoPago);
    }
    
    /**
     * Determina el estado de una venta según la opción de pago
     * SOLO lógica de negocio, sin UI
     */
    public int determinarEstadoVenta(String opcionPago) {
        // Todos los métodos de pago físico/tarjeta se consideran pagados
        if (opcionPago.contains("Efectivo") || 
            opcionPago.contains("Tarjeta") || 
            opcionPago.contains("Transferencia") ||
            opcionPago.contains("Pagar Online")) {
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
    /**
     * Construye mensaje sobre productos que se quedaron sin stock
     * IMPORTANTE: Los productos NO se remueven, solo se muestran con stock 0
     */
    public String construirMensajeProductosRemovidos(java.util.List<Repuesto> repuestosSinStock) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("⚠️ Productos que se quedaron sin stock (se mantienen visibles con stock 0):\n\n");
        for (Repuesto repuesto : repuestosSinStock) {
            mensaje.append("• ").append(repuesto.getNombre()).append(" - Stock: 0\n");
        }
        mensaje.append("\nEstos productos seguirán apareciendo en el catálogo pero sin opción de agregar al carrito.");
        return mensaje.toString();
    }
}