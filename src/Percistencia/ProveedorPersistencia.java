/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Percistencia;

import Model.Proveedor;
import Service.AdministradorService;
import listaDoble.Lista;
import listaDoble.Nodo;
import javax.swing.JOptionPane;

/**
 * Clase de persistencia para operaciones específicas de Proveedores
 * Contiene validaciones y operaciones CRUD especializadas
 * 
 * @author Osvaldo
 */
public class ProveedorPersistencia {
    
    private AdministradorService administradorService;
    
    public ProveedorPersistencia(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }
    
    /**
     * Valida si un proveedor puede ser agregado (sin duplicados)
     * @param usuario Usuario a validar
     * @param correo Correo a validar
     * @param telefono Teléfono a validar
     * @param cedula Cédula a validar
     * @return true si no hay duplicados, false si encuentra duplicados
     */
    public boolean validarProveedorSinDuplicados(String usuario, String correo, String telefono, String cedula) {
        return validarProveedorConExcepcion(usuario, correo, telefono, cedula, null);
    }
    
    /**
     * Valida si un proveedor puede ser editado (excluye el proveedor actual)
     * @param usuario Usuario a validar
     * @param correo Correo a validar  
     * @param telefono Teléfono a validar
     * @param cedula Cédula a validar
     * @param idProveedorExcluir ID del proveedor a excluir de la validación
     * @return true si no hay duplicados, false si encuentra duplicados
     */
    public boolean validarProveedorParaEdicion(String usuario, String correo, String telefono, String cedula, String idProveedorExcluir) {
        return validarProveedorConExcepcion(usuario, correo, telefono, cedula, idProveedorExcluir);
    }
    
    /**
     * Método interno para validar duplicados con opción de excluir un proveedor específico
     */
    private boolean validarProveedorConExcepcion(String usuario, String correo, String telefono, String cedula, String idProveedorExcluir) {
        Lista listaProveedores = administradorService.obtenerTodosLosProveedores();
        
        if (listaProveedores != null && listaProveedores.getPrimero() != null) {
            Nodo actual = listaProveedores.getPrimero();
            
            while (actual != null) {
                Proveedor proveedor = (Proveedor) actual.getDato();
                
                // Saltar el proveedor excluido (para casos de edición)
                if (idProveedorExcluir != null && proveedor.getIdProveedor().equals(idProveedorExcluir)) {
                    actual = actual.getSiguiente();
                    continue;
                }
                
                // Validar duplicados
                if (proveedor.getUsuario().equals(usuario)) {
                    mostrarMensajeDuplicado("USUARIO", proveedor);
                    return false;
                }
                
                if (proveedor.getCorreo().equals(correo)) {
                    mostrarMensajeDuplicado("CORREO ELECTRÓNICO", proveedor);
                    return false;
                }
                
                if (proveedor.getTelefono().equals(telefono)) {
                    mostrarMensajeDuplicado("TELÉFONO", proveedor);
                    return false;
                }
                
                if (proveedor.getCedula().equals(cedula)) {
                    mostrarMensajeDuplicado("CÉDULA", proveedor);
                    return false;
                }
                
                actual = actual.getSiguiente();
            }
        }
        
        return true; // No hay duplicados
    }
    
    /**
     * Muestra mensaje de duplicado encontrado
     */
    private void mostrarMensajeDuplicado(String campo, Proveedor proveedor) {
        JOptionPane.showMessageDialog(null, 
            "Este usuario ya fue creado.\nCoincidencia encontrada en: " + campo + "\n" +
            "Proveedor existente: " + proveedor.getPrimerNombre() + " " + proveedor.getPrimerApellido() + 
            " (ID: " + proveedor.getIdProveedor() + ")", 
            campo.substring(0, 1) + campo.substring(1).toLowerCase() + " Duplicado", 
            JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Elimina un proveedor con confirmación
     * @param idProveedor ID del proveedor a eliminar
     * @param usuarioSeleccionado Nombre de usuario para confirmación
     * @return true si se eliminó exitosamente, false si se canceló o falló
     */
    public boolean eliminarProveedorConConfirmacion(String idProveedor, String usuarioSeleccionado) {
        // Confirmación de eliminación con advertencia
        int confirmacion = JOptionPane.showConfirmDialog(null,
            "⚠️ CUIDADO ⚠️\n\n" +
            "Vas a eliminar el usuario: " + usuarioSeleccionado + "\n" +
            "Esta acción NO se puede deshacer.\n\n" +
            "¿Está completamente seguro?",
            "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirmacion != JOptionPane.YES_OPTION) {
            return false; // Usuario canceló la operación
        }
        
        try {
            boolean eliminado = administradorService.eliminarProveedor(idProveedor);
            
            if (eliminado) {
                JOptionPane.showMessageDialog(null, 
                    "Proveedor eliminado exitosamente.", 
                    "Eliminación Completada", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Error al eliminar el proveedor.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al eliminar proveedor: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Guarda la edición de un proveedor con validación y confirmación
     * @param idProveedor ID del proveedor a actualizar
     * @param proveedorActualizado Datos actualizados del proveedor
     * @param usuarioSeleccionado Nombre de usuario para confirmación
     * @return true si se guardó exitosamente, false si se canceló o falló
     */
    public boolean guardarEdicionProveedorConConfirmacion(String idProveedor, Proveedor proveedorActualizado, String usuarioSeleccionado) {
        // Confirmación de edición
        int confirmacion = JOptionPane.showConfirmDialog(null,
            "¿Está seguro que quiere editar los datos del usuario: " + usuarioSeleccionado + "?",
            "Confirmar Edición",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacion != JOptionPane.YES_OPTION) {
            return false; // Usuario canceló la operación
        }
        
        // Validar duplicados (excluyendo el proveedor actual)
        if (!validarProveedorParaEdicion(
                proveedorActualizado.getUsuario(),
                proveedorActualizado.getCorreo(),
                proveedorActualizado.getTelefono(),
                proveedorActualizado.getCedula(),
                idProveedor)) {
            return false; // Validación falló
        }
        
        try {
            // Actualizar proveedor en el servicio
            boolean actualizado = administradorService.actualizarProveedor(proveedorActualizado);
            
            if (actualizado) {
                JOptionPane.showMessageDialog(null, 
                    "Proveedor actualizado exitosamente.", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Error al actualizar el proveedor.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al actualizar proveedor: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}