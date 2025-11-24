package Percistencia;

import Model.Repuesto;
import listaDoble.Lista;
import listaDoble.Nodo;
import javax.swing.JOptionPane;

public class EliminarRepuestoId {

    public void eliminarPorId(Lista listaRepuestos, int idRef) {

        if (listaRepuestos == null || listaRepuestos.getPrimero() == null) {
            JOptionPane.showMessageDialog(null, "¡Lista vacía!");
            return;
        }

        // Buscar el nodo recorriendo desde el primero (misma lógica que buscarPorId)
        Nodo actual = listaRepuestos.getPrimero();
        Nodo nodoEncontrado = null;

        while (actual != null) {
            Object dato = actual.getDato();
            if (dato instanceof Repuesto) {
                Repuesto rep = (Repuesto) dato;
                if (rep.getIdRepuesto() == idRef) {
                    nodoEncontrado = actual;
                    break;
                }
            }
            actual = actual.getSiguiente();
        }

        if (nodoEncontrado == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un repuesto con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // --- Eliminación del nodo encontrado ---
        if (nodoEncontrado == listaRepuestos.getPrimero() && nodoEncontrado == listaRepuestos.getUltimo()) {
            // Caso 1: es el único nodo de la lista
            listaRepuestos.setPrimero(null);
            listaRepuestos.setUltimo(null);
        }

        // Caso 2: es el primero (pero no el único)
        if (nodoEncontrado == listaRepuestos.getPrimero() && nodoEncontrado != listaRepuestos.getUltimo()) {
            listaRepuestos.setPrimero(nodoEncontrado.getSiguiente());
            if (listaRepuestos.getPrimero() != null) {
                listaRepuestos.getPrimero().setAnterior(null);
            }
        }

        // Caso 3: es el último (pero no el único)
        if (nodoEncontrado == listaRepuestos.getUltimo() && nodoEncontrado != listaRepuestos.getPrimero()) {
            listaRepuestos.setUltimo(nodoEncontrado.getAnterior());
            if (listaRepuestos.getUltimo() != null) {
                listaRepuestos.getUltimo().setSiguiente(null);
            }
        }

        // Caso 4: está en el medio
        if (nodoEncontrado != listaRepuestos.getPrimero() && nodoEncontrado != listaRepuestos.getUltimo()) {
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

        JOptionPane.showMessageDialog(null, "El repuesto fue eliminado.", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
    }
}
