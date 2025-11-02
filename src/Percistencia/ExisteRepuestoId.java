package Percistencia;

import Model.Repuesto;
import listaDoble.Lista;
import listaDoble.Nodo;

/**
 *
 * @author Adrian
 */
public class ExisteRepuestoId {
    
    public boolean existeRepuestoPorId(Lista listaRepuestos, int idBuscar) {
    Nodo actual = listaRepuestos.getPrimero();

    while (actual != null) {
        Repuesto rep = (Repuesto) actual.getDato(); 
        if (rep.getIdRepuesto() == idBuscar) {
            return true;
        }
        actual = actual.getSiguiente();
    }
    return false;
}
    
}