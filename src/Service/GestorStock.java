package Service;

import Model.Repuesto;
import Model.ItemCarrito;
import Model.Carrito;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar el stock de repuestos
 * Maneja la actualización de inventario según las ventas
 */
public class GestorStock {
    
    private ClienteService clienteService;
    private LoginService loginService;
    
    public GestorStock() {
        // IMPORTANTE: Usar la instancia compartida de ClienteService para que el stock se actualice correctamente
        this.loginService = new LoginService();
        this.clienteService = loginService.getClienteService();
    }
    
    /**
     * Procesa la actualización de stock según el tipo de venta
     * @param carrito Carrito con los items vendidos
     * @param opcionPago Opción de pago seleccionada
     * @return Lista de repuestos que se quedaron sin stock (para quitar de la vista)
     */
    public List<Repuesto> procesarVentaStock(Carrito carrito, String opcionPago) {
        List<Repuesto> repuestosSinStock = new ArrayList<>();
        boolean debeRestarStock = debeRestarStock(opcionPago);
        
        if (!debeRestarStock) {
            System.out.println("📍 Opción 'Recoger en Local → Falta por pagar' - Stock reservado, no se resta");
            return repuestosSinStock; // Lista vacía
        }
        
        System.out.println("📦 Restando stock para opción: " + opcionPago);
        
        // Restar stock de cada item del carrito
        for (ItemCarrito item : carrito.obtenerItemsArray()) {
            Repuesto repuesto = item.getRepuesto();
            int cantidadVendida = item.getCantidad();
            int stockActual = repuesto.getStock();
            int nuevoStock = stockActual - cantidadVendida;
            
            System.out.println("🔧 " + repuesto.getNombre() + 
                             " | Stock actual: " + stockActual + 
                             " | Vendido: " + cantidadVendida + 
                             " | Nuevo stock: " + nuevoStock);
            
            // IMPORTANTE: Actualizar stock tanto en el objeto del carrito como en ClienteService
            // 1. Actualizar stock del repuesto del carrito
            repuesto.setStock(nuevoStock);
            
            // 2. Actualizar stock en ClienteService (la fuente de datos principal)
            boolean actualizado = clienteService.actualizarStock(repuesto.getIdRepuesto(), nuevoStock);
            if (!actualizado) {
                System.out.println("⚠️ No se pudo actualizar stock en ClienteService para: " + repuesto.getNombre());
            } else {
                System.out.println("✅ Stock actualizado en ClienteService para: " + repuesto.getNombre());
            }
            
            // Si se quedó sin stock, agregarlo a la lista (pero NO será removido, solo se mostrará con stock 0)
            if (nuevoStock <= 0) {
                repuesto.setStock(0); // Asegurar que no sea negativo
                repuestosSinStock.add(repuesto);
                System.out.println("⚠️ " + repuesto.getNombre() + " se quedó SIN STOCK - se mostrará con stock 0");
            }
        }
        
        // Actualizar el servicio de cliente con los cambios (remover repuestos sin stock)
        clienteService.actualizarInventarioDespuesVenta(repuestosSinStock);
        
        return repuestosSinStock;
    }
    
    /**
     * Determina si se debe restar stock según la opción de pago
     * @param opcionPago Opción seleccionada por el cliente
     * @return true si se debe restar stock
     */
    private boolean debeRestarStock(String opcionPago) {
        // Solo "Recoger en Local → Falta por pagar" NO resta stock
        // Todo lo demás SÍ resta stock (incluye "Pagar Online + Recoger en Local")
        return !opcionPago.contains("Recoger en Local → Falta por pagar");
    }
    
    /**
     * Revierte el stock en caso de cancelación (futuro)
     * @param carrito Carrito de la venta cancelada
     */
    public void revertirStock(Carrito carrito) {
        System.out.println("🔄 Revirtiendo stock...");
        
        for (ItemCarrito item : carrito.obtenerItemsArray()) {
            Repuesto repuesto = item.getRepuesto();
            int cantidadARevertir = item.getCantidad();
            int stockActual = repuesto.getStock();
            int nuevoStock = stockActual + cantidadARevertir;
            
            repuesto.setStock(nuevoStock);
            System.out.println("↩️ " + repuesto.getNombre() + " stock revertido a: " + nuevoStock);
        }
    }
    
    /**
     * Verifica disponibilidad antes de procesar venta
     * IMPORTANTE: Verifica el stock desde ClienteService (fuente de datos principal)
     * @param carrito Carrito a verificar
     * @return true si todos los items tienen stock suficiente
     */
    public boolean verificarDisponibilidad(Carrito carrito) {
        for (ItemCarrito item : carrito.obtenerItemsArray()) {
            Repuesto repuesto = item.getRepuesto();
            // Obtener el stock actualizado desde ClienteService (fuente de datos principal)
            Repuesto repuestoActualizado = clienteService.buscarPorId(repuesto.getIdRepuesto());
            int stockDisponible = (repuestoActualizado != null) ? repuestoActualizado.getStock() : repuesto.getStock();
            
            if (stockDisponible < item.getCantidad()) {
                System.out.println("⚠️ Stock insuficiente para: " + repuesto.getNombre() + 
                                 " (Stock disponible: " + stockDisponible + ", Solicitado: " + item.getCantidad() + ")");
                return false;
            }
        }
        return true;
    }
    
    /**
     * Obtiene un reporte del cambio de stock
     * @param carrito Carrito procesado
     * @param opcionPago Opción de pago
     * @return String con el reporte
     */
    public String generarReporteStock(Carrito carrito, String opcionPago) {
        StringBuilder reporte = new StringBuilder();
        reporte.append("📊 REPORTE DE STOCK\n");
        reporte.append("═══════════════════\n");
        reporte.append("Opción: ").append(opcionPago).append("\n");
        reporte.append("Stock actualizado: ").append(debeRestarStock(opcionPago) ? "SÍ" : "NO (Reservado)").append("\n\n");
        
        for (ItemCarrito item : carrito.obtenerItemsArray()) {
            Repuesto repuesto = item.getRepuesto();
            reporte.append("🔧 ").append(repuesto.getNombre()).append("\n");
            reporte.append("   Cantidad vendida: ").append(item.getCantidad()).append("\n");
            reporte.append("   Stock restante: ").append(repuesto.getStock()).append("\n");
            if (repuesto.getStock() == 0) {
                reporte.append("   ⚠️ PRODUCTO SIN STOCK\n");
            }
            reporte.append("\n");
        }
        
        return reporte.toString();
    }
}