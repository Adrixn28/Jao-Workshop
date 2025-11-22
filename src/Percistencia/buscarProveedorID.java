/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Percistencia;

import Model.Proveedor;
import listaDoble.Lista;
import listaDoble.Nodo;

/**
 *
 * @author JABER
 */
public class buscarProveedorID {
   public Proveedor buscarProveedorId(Lista ListaProveedores, int idBuscar){
    Nodo actual = ListaProveedores.getPrimero();
    while(actual != null){
        Object dato = actual.getDato();
        if (dato instanceof Proveedor ) {
    //Hace un cast de dato a Proveedor y lo guarda en la variable prov. Ahora se puede acceder a los métodos de Proveedor.
          Proveedor prov = (Proveedor) dato;  
            if (Integer.parseInt(prov.getIdProveedor()) == idBuscar) {
                return prov;
            }
            
        }
        actual = actual.getSiguiente();
    }
       return null;
    
} 
}
