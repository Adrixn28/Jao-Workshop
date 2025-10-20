package Percistencia;

import Model.Repuesto;
import listaDoble.Lista;
import listaDoble.Nodo;
import javax.swing.JOptionPane;

public class EliminarRepuestoId {

    public void eliminarPorId(Lista listaRepuestos, int idRef) {

        if (listaRepuestos.getPrimero() == null) {
            JOptionPane.showMessageDialog(null, "¡Lista vacía!");
            return;
        }

        Nodo sapito = listaRepuestos.buscar(idRef);
        boolean encontrado = false;
        
        while(sapito != null){
            Repuesto repuesto = (Repuesto) sapito.getDato();
            
            if(repuesto.getIdRepuesto() == idRef){
                encontrado = true;
                break;
            }
            sapito = sapito.getSiguiente();
        }
        
        if(!encontrado){
            JOptionPane.showMessageDialog(null, "No se encontró un repuesto con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(sapito == listaRepuestos.getPrimero() && sapito == listaRepuestos.getUltimo()){
          listaRepuestos.setPrimero(null);
          listaRepuestos.setUltimo(null);
        } else if (sapito == listaRepuestos.getPrimero()){
            //Verificar
            listaRepuestos.setPrimero(sapito.getSiguiente());
            listaRepuestos.getPrimero().setAnterior(null);
        } else if (sapito == listaRepuestos.getUltimo()){
            listaRepuestos.setUltimo(sapito.getAnterior());
            listaRepuestos.getUltimo().setSiguiente(null);
        } else {
            Nodo sapo = sapito.getAnterior();
            Nodo sapa = sapito.getSiguiente();
            sapo.setSiguiente(sapa);
            sapa.setAnterior(sapo);
        }
        
        sapito.setAnterior(null);
        sapito.setSiguiente(null);
         JOptionPane.showMessageDialog(null, "El repuesto fue eliminado.", "Exitoso", JOptionPane.INFORMATION_MESSAGE);    
    }
}