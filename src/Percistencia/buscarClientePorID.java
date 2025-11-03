/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Percistencia;

import listaDoble.Lista;
import listaDoble.Nodo;
import Model.Cliente;
import javax.swing.JOptionPane;

/**
 *
 * @author JABER
 */
public class buscarClientePorID {

    public Cliente buscarClientePorId(Lista ListaClientes, int idBuscar) {
        Nodo actual = ListaClientes.getPrimero();
        
         while (actual != null) {
            Object dato = actual.getDato();
            if (dato instanceof Cliente) {
             //Hace un cast de dato a Cliente y lo guarda en la variable clie. Ahora se puede acceder a los métodos de Cliente.    
                Cliente clie = (Cliente) dato;
                if(Integer.parseInt(clie.getIdCliente()) == idBuscar){
                    return clie;
                }
            } 
            actual = actual.getSiguiente();
        }
        return null;
        
    }

}
