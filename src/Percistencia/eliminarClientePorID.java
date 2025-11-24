/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Percistencia;

import Model.Cliente;
import javax.swing.JOptionPane;
import listaDoble.Lista;
import listaDoble.Nodo;

/**
 *
 * @author JABER
 */
public class eliminarClientePorID {
    public void eliminarclientePorId(Lista ListaClientes, int idRef) {

        if (ListaClientes == null || ListaClientes.getPrimero() == null) {
            JOptionPane.showMessageDialog(null, "¡Lista vacía!");
            return;
        }

        // Buscar el nodo recorriendo desde el primero (misma lógica que buscarPorId)
        Nodo actual = ListaClientes.getPrimero();
        Nodo nodoEncontrado = null;

        while (actual != null) {
            Object dato = actual.getDato();
            if (dato instanceof Cliente) {
                Cliente clie = (Cliente) dato;
                if (Integer.parseInt(clie.getIdCliente()) == idRef) {
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
        if (nodoEncontrado ==ListaClientes.getPrimero() && nodoEncontrado == ListaClientes.getUltimo()) {
            // Caso 1: es el único nodo de la lista
            ListaClientes.setPrimero(null);
            ListaClientes.setUltimo(null);
        }

        // Caso 2: es el primero (pero no el único)
        if (nodoEncontrado == ListaClientes.getPrimero() && nodoEncontrado != ListaClientes.getUltimo()) {
            ListaClientes.setPrimero(nodoEncontrado.getSiguiente());
            if (ListaClientes.getPrimero() != null) {
                ListaClientes.getPrimero().setAnterior(null);
            }
        }

        // Caso 3: es el último (pero no el único)
        if (nodoEncontrado == ListaClientes.getUltimo() && nodoEncontrado != ListaClientes.getPrimero()) {
            ListaClientes.setUltimo(nodoEncontrado.getAnterior());
            if (ListaClientes.getUltimo() != null) {
                ListaClientes.getUltimo().setSiguiente(null);
            }
        }

        // Caso 4: está en el medio
        if (nodoEncontrado != ListaClientes.getPrimero() && nodoEncontrado != ListaClientes.getUltimo()) {
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

        JOptionPane.showMessageDialog(null, "El Cliente fue eliminado.", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
    } 
}
