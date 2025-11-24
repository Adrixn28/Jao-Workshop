package Percistencia;

import Model.Repuesto;
import listaDoble.Lista;
import listaDoble.Nodo;

/**
 *
 * @author Adrian
 */
public class ActualizarRepuestoId {

    public boolean actualizarPorId(Lista listaRepuestos, int idRef, String campo, String nuevoValor) {

        Nodo actual = listaRepuestos.getPrimero();

        while (actual != null) {

            Object dato = actual.getDato();

            if (dato instanceof Repuesto) {
                Repuesto rep = (Repuesto) dato;

                if (rep.getIdRepuesto() == idRef) {

                    switch (campo) {

                        case "Precio":
                            rep.setPrecio(Double.parseDouble(nuevoValor));
                            break;

                        case "Stock":
                            rep.setStock(Integer.parseInt(nuevoValor));
                            break;

                        case "Estado":
                            rep.setEstado(nuevoValor);
                            break;

                        default:
                            return false;
                    }
                    return true;
                }
            }

            actual = actual.getSiguiente();
        }
        return false;
    }
}
