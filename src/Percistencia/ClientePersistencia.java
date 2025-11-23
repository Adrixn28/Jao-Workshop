/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Percistencia;

import Model.Cliente;
import Service.AdministradorService;
import listaDoble.Lista;
import listaDoble.Nodo;
import javax.swing.JOptionPane;

/**
 * Clase de persistencia para operaciones específicas de Clientes
 * Contiene validaciones y operaciones CRUD especializadas
 * 
 * @author Osvaldo
 */
public class ClientePersistencia {
    
    private AdministradorService administradorService;
    
    public ClientePersistencia(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }
    
    /**
     * Valida si un cliente puede ser agregado (sin duplicados)
     * @param usuario Usuario a validar
     * @param correo Correo a validar
     * @param telefono Teléfono a validar
     * @param cedula Cédula a validar
     * @return true si no hay duplicados, false si encuentra duplicados
     */
    public boolean validarClienteSinDuplicados(String usuario, String correo, String telefono, String cedula) {
        return validarClienteConExcepcion(usuario, correo, telefono, cedula, null);
    }
    
    /**
     * Valida si un cliente puede ser editado (excluye el cliente actual)
     * @param usuario Usuario a validar
     * @param correo Correo a validar  
     * @param telefono Teléfono a validar
     * @param cedula Cédula a validar
     * @param idClienteExcluir ID del cliente a excluir de la validación
     * @return true si no hay duplicados, false si encuentra duplicados
     */
    public boolean validarClienteParaEdicion(String usuario, String correo, String telefono, String cedula, String idClienteExcluir) {
        return validarClienteConExcepcion(usuario, correo, telefono, cedula, idClienteExcluir);
    }
    
    /**
     * Método interno para validar duplicados con opción de excluir un cliente específico
     */
    private boolean validarClienteConExcepcion(String usuario, String correo, String telefono, String cedula, String idClienteExcluir) {
        Lista listaClientes = administradorService.obtenerTodosLosClientes();
        
        if (listaClientes != null && listaClientes.getPrimero() != null) {
            Nodo actual = listaClientes.getPrimero();
            
            while (actual != null) {
                Cliente cliente = (Cliente) actual.getDato();
                
                // Saltar el cliente excluido (para casos de edición)
                if (idClienteExcluir != null && cliente.getIdCliente().equals(idClienteExcluir)) {
                    actual = actual.getSiguiente();
                    continue;
                }
                
                // Validar duplicados
                if (cliente.getUsuario().equals(usuario)) {
                    mostrarMensajeDuplicado("USUARIO", cliente);
                    return false;
                }
                
                if (cliente.getCorreo().equals(correo)) {
                    mostrarMensajeDuplicado("CORREO ELECTRÓNICO", cliente);
                    return false;
                }
                
                if (cliente.getTelefono().equals(telefono)) {
                    mostrarMensajeDuplicado("TELÉFONO", cliente);
                    return false;
                }
                
                if (cliente.getCedula().equals(cedula)) {
                    mostrarMensajeDuplicado("CÉDULA", cliente);
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
    private void mostrarMensajeDuplicado(String campo, Cliente cliente) {
        JOptionPane.showMessageDialog(null, 
            "Este usuario ya fue creado.\nCoincidencia encontrada en: " + campo + "\n" +
            "Cliente existente: " + cliente.getPrimerNombre() + " " + cliente.getPrimerApellido() + 
            " (ID: " + cliente.getIdCliente() + ")", 
            campo.substring(0, 1) + campo.substring(1).toLowerCase() + " Duplicado", 
            JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Elimina un cliente con confirmación
     * @param idCliente ID del cliente a eliminar
     * @param usuarioSeleccionado Nombre de usuario para confirmación
     * @return true si se eliminó exitosamente, false si se canceló o falló
     */
    public boolean eliminarClienteConConfirmacion(String idCliente, String usuarioSeleccionado) {
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
            boolean eliminado = administradorService.eliminarCliente(idCliente);
            
            if (eliminado) {
                JOptionPane.showMessageDialog(null, 
                    "Cliente eliminado exitosamente.", 
                    "Eliminación Completada", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Error al eliminar el cliente.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al eliminar cliente: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Guarda la edición de un cliente con validación y confirmación
     * @param idCliente ID del cliente a actualizar
     * @param clienteActualizado Datos actualizados del cliente
     * @param usuarioSeleccionado Nombre de usuario para confirmación
     * @return true si se guardó exitosamente, false si se canceló o falló
     */
    public boolean guardarEdicionClienteConConfirmacion(String idCliente, Cliente clienteActualizado, String usuarioSeleccionado) {
        // Confirmación de edición
        int confirmacion = JOptionPane.showConfirmDialog(null,
            "¿Está seguro que quiere editar los datos del usuario: " + usuarioSeleccionado + "?",
            "Confirmar Edición",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacion != JOptionPane.YES_OPTION) {
            return false; // Usuario canceló la operación
        }
        
        // Validar duplicados (excluyendo el cliente actual)
        if (!validarClienteParaEdicion(
                clienteActualizado.getUsuario(),
                clienteActualizado.getCorreo(),
                clienteActualizado.getTelefono(),
                clienteActualizado.getCedula(),
                idCliente)) {
            return false; // Validación falló
        }
        
        try {
            // Actualizar cliente en el servicio
            boolean actualizado = administradorService.actualizarCliente(clienteActualizado);
            
            if (actualizado) {
                JOptionPane.showMessageDialog(null, 
                    "Cliente actualizado exitosamente.", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Error al actualizar el cliente.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al actualizar cliente: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Valida los formatos de los campos de cliente
     * @param nombres Nombres del cliente
     * @param apellidos Apellidos del cliente
     * @param correo Correo electrónico
     * @param telefono Número de teléfono
     * @param cedula Cédula de identidad
     * @return true si todos los formatos son válidos, false si hay errores
     */
    public boolean validarFormatosCliente(String nombres, String apellidos, String correo, String telefono, String cedula) {
        // Validar que nombres y apellidos no contengan números
        if (!nombres.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            JOptionPane.showMessageDialog(null, 
                "Los nombres solo pueden contener letras y espacios.\n" +
                "No se permiten números ni caracteres especiales.", 
                "Formato de Nombre Inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!apellidos.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            JOptionPane.showMessageDialog(null, 
                "Los apellidos solo pueden contener letras y espacios.\n" +
                "No se permiten números ni caracteres especiales.", 
                "Formato de Apellido Inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar que el correo contenga @
        if (!correo.contains("@")) {
            JOptionPane.showMessageDialog(null, 
                "El correo electrónico debe contener el símbolo '@'.\n" +
                "Ejemplo: usuario@dominio.com", 
                "Formato de Correo Inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar formato más completo del correo
        if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(null, 
                "El formato del correo electrónico no es válido.\n" +
                "Ejemplo: usuario@dominio.com", 
                "Formato de Correo Inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar que teléfono solo contenga números
        if (!telefono.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, 
                "El teléfono solo puede contener números.\n" +
                "No se permiten espacios, guiones o caracteres especiales.", 
                "Formato de Teléfono Inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar longitud del teléfono (10 dígitos)
        if (telefono.length() != 10) {
            JOptionPane.showMessageDialog(null, 
                "El teléfono debe tener 10 dígitos.", 
                "Longitud de Teléfono Inválida", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar que cédula solo contenga números
        if (!cedula.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, 
                "La cédula solo puede contener números.\n" +
                "No se permiten espacios, guiones o caracteres especiales.", 
                "Formato de Cédula Inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar longitud de la cédula (entre 6 y 15 dígitos)
        if (cedula.length() < 6 || cedula.length() > 15) {
            JOptionPane.showMessageDialog(null, 
                "La cédula debe tener entre 6 y 15 dígitos.", 
                "Longitud de Cédula Inválida", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}