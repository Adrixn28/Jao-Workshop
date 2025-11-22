package Service;

import Model.*;
import listaDoble.Lista;
import listaDoble.Nodo;
import java.util.*;

/**
 * Servicio que gestiona toda la lógica del carrito y ventas
 * Maneja múltiples carritos usando Lista Doble personalizada
 */
public class GestorCarrito {
    
    private Lista carritosPorCliente; // Lista que contiene objetos Carrito
    private int contadorVentas;
    private int contadorFacturas;
    private Lista ventasActivas;      // Lista de ventas activas
    private Lista facturasGeneradas;  // Lista de facturas generadas

    public GestorCarrito() {
        this.carritosPorCliente = new Lista();
        this.contadorVentas = 1;
        this.contadorFacturas = 1;
        this.ventasActivas = new Lista();
        this.facturasGeneradas = new Lista();
    }

    /**
     * Obtiene o crea un carrito para un cliente específico
     */
    public Carrito obtenerCarritoCliente(String cedulaCliente) {
        // Buscar carrito existente
        Nodo actual = carritosPorCliente.getPrimero();
        while (actual != null) {
            Carrito carrito = (Carrito) actual.getDato();
            if (carrito.getCedulaCliente().equals(cedulaCliente)) {
                return carrito;
            }
            actual = actual.getSiguiente();
        }
        
        // No existe, crear nuevo carrito
        Carrito nuevoCarrito = new Carrito(cedulaCliente);
        carritosPorCliente.insertarFinal(nuevoCarrito);
        return nuevoCarrito;
    }

    /**
     * Agrega un repuesto al carrito de un cliente
     */
    public boolean agregarAlCarrito(String cedulaCliente, Repuesto repuesto, int cantidad) {
        Carrito carrito = obtenerCarritoCliente(cedulaCliente);
        return carrito.agregarRepuesto(repuesto, cantidad);
    }

    /**
     * Elimina un repuesto del carrito
     */
    public boolean eliminarDelCarrito(String cedulaCliente, int idRepuesto) {
        Carrito carrito = obtenerCarritoCliente(cedulaCliente);
        return carrito.eliminarRepuesto(idRepuesto);
    }

    /**
     * Actualiza cantidad de un repuesto en el carrito
     */
    public boolean actualizarCantidadCarrito(String cedulaCliente, int idRepuesto, int nuevaCantidad) {
        Carrito carrito = obtenerCarritoCliente(cedulaCliente);
        return carrito.actualizarCantidad(idRepuesto, nuevaCantidad);
    }

    /**
     * Limpia completamente el carrito de un cliente
     */
    public void limpiarCarrito(String cedulaCliente) {
        Carrito carrito = obtenerCarritoCliente(cedulaCliente);
        carrito.limpiarCarrito();
    }

    /**
     * Procesa la compra y crea una venta
     */
    public Venta procesarCompra(String cedulaCliente, Cliente cliente, String tipoPago) {
        Carrito carrito = obtenerCarritoCliente(cedulaCliente);
        
        if (carrito.estaVacio()) {
            return null; // No se puede procesar carrito vacío
        }

        // Determinar estado y configuración según tipo de pago
        int estado;
        boolean esEnvioADomicilio;
        
        switch (tipoPago) {
            case "online_domicilio":
                estado = 1; // Pagada
                esEnvioADomicilio = true;
                break;
            case "online_local":
                estado = 1; // Pagada
                esEnvioADomicilio = false;
                break;
            case "contraentrega":
                estado = 2; // Falta por pagar
                esEnvioADomicilio = true;
                break;
            case "local":
                estado = 2; // Falta por pagar
                esEnvioADomicilio = false;
                break;
            default:
                return null; // Tipo de pago inválido
        }

        // Generar código serial de venta
        String codigoVenta = "VEN" + String.format("%03d", contadorVentas++);

        // Convertir carrito a items de venta
        List<ItemVenta> itemsVenta = carrito.convertirAItemsVenta(contadorVentas - 1);

        // Crear venta
        Venta nuevaVenta = new Venta(contadorVentas - 1, codigoVenta, new Date(), 
                                   cliente, itemsVenta, carrito.getTotalCarrito(), 
                                   estado, esEnvioADomicilio, tipoPago);

        // Agregar a ventas activas
        ventasActivas.insertarFinal(nuevaVenta);

        // Manejar stock si es necesario
        if (nuevaVenta.debeRestarStock()) {
            restarStockRepuestos(itemsVenta);
        }

        // Limpiar carrito después de procesar
        carrito.limpiarCarrito();

        return nuevaVenta;
    }

    /**
     * Genera una factura basada en una venta
     */
    public Factura generarFactura(Venta venta, String metodoPago) {
        if (!venta.puedeGenerarFactura()) {
            return null; // Solo ventas pagadas o que faltan por pagar pueden generar factura
        }

        String codigoFactura = "FACT" + String.format("%03d", contadorFacturas++);
        Factura nuevaFactura = new Factura(venta, codigoFactura, metodoPago);
        
        facturasGeneradas.insertarFinal(nuevaFactura);
        return nuevaFactura;
    }

    /**
     * Busca una factura por código serial
     */
    public Factura buscarFacturaPorCodigo(String codigoFactura) {
        Nodo actual = facturasGeneradas.getPrimero();
        while (actual != null) {
            Factura factura = (Factura) actual.getDato();
            if (factura.getCodigoFactura().equals(codigoFactura)) {
                return factura;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    /**
     * Busca una venta por código serial
     */
    public Venta buscarVentaPorCodigo(String codigoVenta) {
        Nodo actual = ventasActivas.getPrimero();
        while (actual != null) {
            Venta venta = (Venta) actual.getDato();
            if (venta.getCodigoSerial().equals(codigoVenta)) {
                return venta;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    /**
     * Marca una factura como despachada
     */
    public boolean marcarComoDespachadaFactura(String codigoFactura) {
        Factura factura = buscarFacturaPorCodigo(codigoFactura);
        if (factura != null && factura.puedeSerDespachada()) {
            factura.marcarComoDespachada();
            return true;
        }
        return false;
    }

    /**
     * Resta stock de los repuestos después de una compra
     */
    private void restarStockRepuestos(List<ItemVenta> itemsVenta) {
        for (ItemVenta item : itemsVenta) {
            Repuesto repuesto = item.getRespuesto();
            int nuevoStock = repuesto.getStock() - item.getCantidad();
            repuesto.setStock(nuevoStock);
            
            // Verificar si alguna venta debe vencerse por falta de stock
            verificarVencimientoPorStock(repuesto.getIdRepuesto());
        }
    }

    /**
     * Verifica si hay ventas que deben vencerse por falta de stock
     */
    private void verificarVencimientoPorStock(int idRepuesto) {
        Nodo actual = ventasActivas.getPrimero();
        while (actual != null) {
            Venta venta = (Venta) actual.getDato();
            
            // Solo verificar ventas estado 2 (falta por pagar) sin envío
            if (venta.getEstado() == 2 && !venta.isEsEnvioADomicilio()) {
                // Verificar si esta venta tiene el repuesto y si hay stock insuficiente
                for (ItemVenta item : venta.getListaDetalle()) {
                    if (item.getRespuesto().getIdRepuesto() == idRepuesto && 
                        item.getRespuesto().getStock() < item.getCantidad()) {
                        venta.procesarVencimiento(); // Cambiar a vencida
                        break;
                    }
                }
            }
            actual = actual.getSiguiente();
        }
    }

    /**
     * Obtiene todas las ventas para el recepcionista (estados 0, 1, 2)
     */
    public List<Venta> obtenerVentasParaRecepcionista() {
        List<Venta> ventasRecepcionista = new ArrayList<>();
        Nodo actual = ventasActivas.getPrimero();
        
        while (actual != null) {
            Venta venta = (Venta) actual.getDato();
            // Solo estados 0, 1, 2
            if (venta.getEstado() >= 0 && venta.getEstado() <= 2) {
                ventasRecepcionista.add(venta);
            }
            actual = actual.getSiguiente();
        }
        
        return ventasRecepcionista;
    }

    /**
     * Obtiene todas las facturas generadas
     */
    public List<Factura> obtenerTodasLasFacturas() {
        List<Factura> todasFacturas = new ArrayList<>();
        Nodo actual = facturasGeneradas.getPrimero();
        
        while (actual != null) {
            Factura factura = (Factura) actual.getDato();
            todasFacturas.add(factura);
            actual = actual.getSiguiente();
        }
        
        return todasFacturas;
    }

    // Getters para estadísticas y debugging
    public int getNumeroCarritosActivos() {
        int contador = 0;
        Nodo actual = carritosPorCliente.getPrimero();
        while (actual != null) {
            Carrito carrito = (Carrito) actual.getDato();
            if (!carrito.estaVacio()) {
                contador++;
            }
            actual = actual.getSiguiente();
        }
        return contador;
    }

    public int getContadorVentas() {
        return contadorVentas - 1; // Restamos 1 porque se incrementa antes de usar
    }

    public int getContadorFacturas() {
        return contadorFacturas - 1;
    }
}