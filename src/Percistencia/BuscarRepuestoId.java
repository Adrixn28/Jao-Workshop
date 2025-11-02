package Percistencia;

import Model.Repuesto;
import listaDoble.Lista;
import listaDoble.Nodo;

/**
 *
 * @author Adrian
 */
public class BuscarRepuestoId {
  public Repuesto buscarPorId(Lista listaRepuestos, int idBuscar) {
        Nodo actual = listaRepuestos.getPrimero();

        while (actual != null) {
            Object dato = actual.getDato();
            if (dato instanceof Repuesto) {
                Repuesto rep = (Repuesto) dato;
                if (rep.getIdRepuesto() == idBuscar) {
                    return rep; 
                }
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}
