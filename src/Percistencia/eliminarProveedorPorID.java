/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Percistencia;

import Model.Proveedor;
import javax.swing.JOptionPane;
import listaDoble.Lista;
import listaDoble.Nodo;

/**
 *
 * @author JABER
 */
public class eliminarProveedorPorID {
     public void eliminarclientePorId(Lista ListaProveedores, int idRef) {

        if (ListaProveedores == null || ListaProveedores.getPrimero() == null) {
            JOptionPane.showMessageDialog(null, "¡Lista vacía!");
            return;
        }

        // Buscar el nodo recorriendo desde el primero (misma lógica que buscarPorId)
        Nodo actual = ListaProveedores.getPrimero();
        Nodo nodoEncontrado = null;

        while (actual != null) {
            Object dato = actual.getDato();
            if (dato instanceof Proveedor) {
                Proveedor prov = (Proveedor) dato;
                if (Integer.parseInt(prov.getIdProveedor()) == idRef) {
                    nodoEncontrado = actual;
                    break;
                }
            }
            actual = actual.getSiguiente();
        }

        if (nodoEncontrado == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un Cliente con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // --- Eliminación del nodo encontrado ---
        if (nodoEncontrado ==ListaProveedores.getPrimero() && nodoEncontrado == ListaProveedores.getUltimo()) {
            // Caso 1: es el único nodo de la lista
            ListaProveedores.setPrimero(null);
            ListaProveedores.setUltimo(null);
        }

        // Caso 2: es el primero (pero no el único)
        if (nodoEncontrado == ListaProveedores.getPrimero() && nodoEncontrado != ListaProveedores.getUltimo()) {
            ListaProveedores.setPrimero(nodoEncontrado.getSiguiente());
            if (ListaProveedores.getPrimero() != null) {
                ListaProveedores.getPrimero().setAnterior(null);
            }
        }

        // Caso 3: es el último (pero no el único)
        if (nodoEncontrado == ListaProveedores.getUltimo() && nodoEncontrado != ListaProveedores.getPrimero()) {
            ListaProveedores.setUltimo(nodoEncontrado.getAnterior());
            if (ListaProveedores.getUltimo() != null) {
                ListaProveedores.getUltimo().setSiguiente(null);
            }
        }

        // Caso 4: está en el medio
        if (nodoEncontrado != ListaProveedores.getPrimero() && nodoEncontrado != ListaProveedores.getUltimo()) {
            Nodo anterior = nodoEncontrado.getAnterior();
            Nodo siguiente = nodoEncontrado.getSiguiente();
            if (anterior != null) {
                anterior.setSiguiente(siguiente);
            }
            if (siguiente != null) {
                siguiente.setAnterior(anterior);
            }
        }

// Desconectar completamente el nodo eliminado
        nodoEncontrado.setAnterior(null);
        nodoEncontrado.setSiguiente(null);

        JOptionPane.showMessageDialog(null, "El Proveedor fue eliminado.", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
    }  
}
