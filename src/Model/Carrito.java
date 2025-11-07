package Model;

import listaDoble.Lista;
import listaDoble.Nodo;

/**
 * Maneja el carrito de compras usando la Lista Doble personalizada
 * Funciona tanto para clientes como para recepcionistas
 */
public class Carrito {
    
    private String cedulaCliente;
    private Lista itemsCarrito;  // Lista Doble que contiene ItemCarrito
    private double totalCarrito;

    /**
     * Constructor del carrito
     * @param cedulaCliente Cedula del cliente propietario del carrito
     */
    public Carrito(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
        this.itemsCarrito = new Lista();
        this.totalCarrito = 0.0;
    }

    /**
     * Agrega un repuesto al carrito
     * Si ya existe, actualiza la cantidad
     */
    public boolean agregarRepuesto(Repuesto repuesto, int cantidad) {
        // Verificar stock disponible
        if (repuesto.getStock() < cantidad) {
            return false; // No hay suficiente stock
        }

        // Buscar si el repuesto ya existe en el carrito
        ItemCarrito itemExistente = buscarItemPorRepuesto(repuesto.getIdRepuesto());
        
        if (itemExistente != null) {
            // Ya existe, actualizar cantidad
            int nuevaCantidad = itemExistente.getCantidad() + cantidad;
            if (repuesto.getStock() >= nuevaCantidad) {
                itemExistente.actualizarCantidad(nuevaCantidad);
                calcularTotal();
                return true;
            } else {
                return false; // No hay suficiente stock para la nueva cantidad
            }
        } else {
            // No existe, crear nuevo item
            ItemCarrito nuevoItem = new ItemCarrito(cedulaCliente, repuesto, cantidad, repuesto.getPrecio());
            itemsCarrito.insertarFinal(nuevoItem);
            calcularTotal();
            return true;
        }
    }

    /**
     * Elimina un repuesto del carrito
     */
    public boolean eliminarRepuesto(int idRepuesto) {
        ItemCarrito item = buscarItemPorRepuesto(idRepuesto);
        if (item != null) {
            itemsCarrito.eliminar(item);
            calcularTotal();
            return true;
        }
        return false;
    }

    /**
     * Actualiza la cantidad de un repuesto en el carrito
     */
    public boolean actualizarCantidad(int idRepuesto, int nuevaCantidad) {
        ItemCarrito item = buscarItemPorRepuesto(idRepuesto);
        if (item != null) {
            if (nuevaCantidad <= 0) {
                // Si la cantidad es 0 o negativa, eliminar del carrito
                return eliminarRepuesto(idRepuesto);
            } else if (item.getRepuesto().getStock() >= nuevaCantidad) {
                item.actualizarCantidad(nuevaCantidad);
                calcularTotal();
                return true;
            }
        }
        return false;
    }

    /**
     * Busca un item en el carrito por ID de repuesto
     */
    private ItemCarrito buscarItemPorRepuesto(int idRepuesto) {
        Nodo actual = itemsCarrito.getPrimero();
        while (actual != null) {
            ItemCarrito item = (ItemCarrito) actual.getDato();
            if (item.getRepuesto().getIdRepuesto() == idRepuesto) {
                return item;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    /**
     * Limpia completamente el carrito
     */
    public void limpiarCarrito() {
        itemsCarrito = new Lista();
        totalCarrito = 0.0;
    }

    /**
     * Calcula el total del carrito
     */
    private void calcularTotal() {
        totalCarrito = 0.0;
        Nodo actual = itemsCarrito.getPrimero();
        while (actual != null) {
            ItemCarrito item = (ItemCarrito) actual.getDato();
            totalCarrito += item.getSubtotal();
            actual = actual.getSiguiente();
        }
    }

    /**
     * Obtiene todos los items del carrito como array para mostrar en UI
     */
    public ItemCarrito[] obtenerItemsArray() {
        // Contar items
        int contador = 0;
        Nodo actual = itemsCarrito.getPrimero();
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }

        // Crear array y llenarlo
        ItemCarrito[] items = new ItemCarrito[contador];
        actual = itemsCarrito.getPrimero();
        for (int i = 0; i < contador; i++) {
            items[i] = (ItemCarrito) actual.getDato();
            actual = actual.getSiguiente();
        }

        return items;
    }

    /**
     * Verifica si el carrito está vacío
     */
    public boolean estaVacio() {
        return itemsCarrito.getPrimero() == null;
    }

    /**
     * Obtiene el número de items únicos en el carrito
     */
    public int getNumeroItems() {
        int contador = 0;
        Nodo actual = itemsCarrito.getPrimero();
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    /**
     * Convierte el carrito a una lista de ItemVenta para procesamiento de venta
     */
    public java.util.List<ItemVenta> convertirAItemsVenta(int idVenta) {
        java.util.List<ItemVenta> itemsVenta = new java.util.ArrayList<>();
        Nodo actual = itemsCarrito.getPrimero();
        
        while (actual != null) {
            ItemCarrito item = (ItemCarrito) actual.getDato();
            itemsVenta.add(item.toItemVenta(idVenta));
            actual = actual.getSiguiente();
        }
        
        return itemsVenta;
    }

    // Getters
    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public double getTotalCarrito() {
        return totalCarrito;
    }

    public Lista getItemsCarrito() {
        return itemsCarrito;
    }
}