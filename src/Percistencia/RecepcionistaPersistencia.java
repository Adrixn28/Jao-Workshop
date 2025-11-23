/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Percistencia;

import Model.Recepcionista;
import Service.AdministradorService;
import listaDoble.Lista;
import listaDoble.Nodo;
import javax.swing.JOptionPane;

/**
 * Clase de persistencia para operaciones específicas de Recepcionistas
 * Contiene validaciones y operaciones CRUD especializadas
 * 
 * @author Osvaldo
 */
public class RecepcionistaPersistencia {
    
    private AdministradorService administradorService;
    
    public RecepcionistaPersistencia(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }
    
    /**
     * Valida si un recepcionista puede ser agregado (sin duplicados)
     * @param usuario Usuario a validar
     * @param correo Correo a validar
     * @param telefono Teléfono a validar
     * @param cedula Cédula a validar
     * @return true si no hay duplicados, false si encuentra duplicados
     */
    public boolean validarRecepcionistaSinDuplicados(String usuario, String correo, String telefono, String cedula) {
        return validarRecepcionistaConExcepcion(usuario, correo, telefono, cedula, null);
    }
    
    /**
     * Valida si un recepcionista puede ser editado (excluye el recepcionista actual)
     * @param usuario Usuario a validar
     * @param correo Correo a validar  
     * @param telefono Teléfono a validar
     * @param cedula Cédula a validar
     * @param idRecepcionistaExcluir ID del recepcionista a excluir de la validación
     * @return true si no hay duplicados, false si encuentra duplicados
     */
    public boolean validarRecepcionistaParaEdicion(String usuario, String correo, String telefono, String cedula, String idRecepcionistaExcluir) {
        return validarRecepcionistaConExcepcion(usuario, correo, telefono, cedula, idRecepcionistaExcluir);
    }
    
    /**
     * Método interno para validar duplicados con opción de excluir un recepcionista específico
     */
    private boolean validarRecepcionistaConExcepcion(String usuario, String correo, String telefono, String cedula, String idRecepcionistaExcluir) {
        Lista listaRecepcionistas = administradorService.obtenerTodosLosRecepcionistas();
        
        if (listaRecepcionistas != null && listaRecepcionistas.getPrimero() != null) {
            Nodo actual = listaRecepcionistas.getPrimero();
            
            while (actual != null) {
                Recepcionista recepcionista = (Recepcionista) actual.getDato();
                
                // Saltar el recepcionista excluido (para casos de edición)
                if (idRecepcionistaExcluir != null && recepcionista.getIdRecepcionista().equals(idRecepcionistaExcluir)) {
                    actual = actual.getSiguiente();
                    continue;
                }
                
                // Validar duplicados
                if (recepcionista.getUsuario().equals(usuario)) {
                    mostrarMensajeDuplicado("USUARIO", recepcionista);
                    return false;
                }
                
                if (recepcionista.getCorreo().equals(correo)) {
                    mostrarMensajeDuplicado("CORREO ELECTRÓNICO", recepcionista);
                    return false;
                }
                
                if (recepcionista.getTelefono().equals(telefono)) {
                    mostrarMensajeDuplicado("TELÉFONO", recepcionista);
                    return false;
                }
                
                if (recepcionista.getCedula().equals(cedula)) {
                    mostrarMensajeDuplicado("CÉDULA", recepcionista);
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
    private void mostrarMensajeDuplicado(String campo, Recepcionista recepcionista) {
        JOptionPane.showMessageDialog(null, 
            "Este usuario ya fue creado.\nCoincidencia encontrada en: " + campo + "\n" +
            "Recepcionista existente: " + recepcionista.getPrimerNombre() + " " + recepcionista.getPrimerApellido() + 
            " (ID: " + recepcionista.getIdRecepcionista() + ")", 
            campo.substring(0, 1) + campo.substring(1).toLowerCase() + " Duplicado", 
            JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Elimina un recepcionista con confirmación
     * @param idRecepcionista ID del recepcionista a eliminar
     * @param usuarioSeleccionado Nombre de usuario para confirmación
     * @return true si se eliminó exitosamente, false si se canceló o falló
     */
    public boolean eliminarRecepcionistaConConfirmacion(String idRecepcionista, String usuarioSeleccionado) {
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
            boolean eliminado = administradorService.eliminarRecepcionista(idRecepcionista);
            
            if (eliminado) {
                JOptionPane.showMessageDialog(null, 
                    "Recepcionista eliminado exitosamente.", 
                    "Eliminación Completada", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Error al eliminar el recepcionista.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("Error al eliminar recepcionista: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al eliminar recepcionista: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Guarda la edición de un recepcionista con validación y confirmación
     * @param idRecepcionista ID del recepcionista a actualizar
     * @param recepcionistaActualizado Datos actualizados del recepcionista
     * @param usuarioSeleccionado Nombre de usuario para confirmación
     * @return true si se guardó exitosamente, false si se canceló o falló
     */
    public boolean guardarEdicionRecepcionistaConConfirmacion(String idRecepcionista, Recepcionista recepcionistaActualizado, String usuarioSeleccionado) {
        // Confirmación de edición
        int confirmacion = JOptionPane.showConfirmDialog(null,
            "¿Está seguro que quiere editar los datos del usuario: " + usuarioSeleccionado + "?",
            "Confirmar Edición",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacion != JOptionPane.YES_OPTION) {
            return false; // Usuario canceló la operación
        }
        
        // Validar duplicados (excluyendo el recepcionista actual)
        if (!validarRecepcionistaParaEdicion(
                recepcionistaActualizado.getUsuario(),
                recepcionistaActualizado.getCorreo(),
                recepcionistaActualizado.getTelefono(),
                recepcionistaActualizado.getCedula(),
                idRecepcionista)) {
            return false; // Validación falló
        }
        
        try {
            // Actualizar recepcionista en el servicio
            boolean actualizado = administradorService.actualizarRecepcionista(recepcionistaActualizado);
            
            if (actualizado) {
                JOptionPane.showMessageDialog(null, 
                    "Recepcionista actualizado exitosamente.", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Error al actualizar el recepcionista.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("Error al actualizar recepcionista: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al actualizar recepcionista: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Valida los formatos de los campos de recepcionista
     * @param nombres Nombres del recepcionista
     * @param apellidos Apellidos del recepcionista
     * @param correo Correo electrónico
     * @param telefono Número de teléfono
     * @param cedula Cédula de identidad
     * @param aniosExperiencia Años de experiencia (puede estar vacío)
     * @return true si todos los formatos son válidos, false si hay errores
     */
    public boolean validarFormatosRecepcionista(String nombres, String apellidos, String correo, String telefono, String cedula, String aniosExperiencia) {
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
        
        // Validar años de experiencia (si no está vacío, debe ser un número)
        if (!aniosExperiencia.isEmpty() && !aniosExperiencia.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, 
                "Los años de experiencia deben ser un número válido.", 
                "Formato de Años Inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar rango de años de experiencia (0-50)
        if (!aniosExperiencia.isEmpty()) {
            try {
                int anios = Integer.parseInt(aniosExperiencia);
                if (anios < 0 || anios > 50) {
                    JOptionPane.showMessageDialog(null, 
                        "Los años de experiencia deben estar entre 0 y 50.", 
                        "Rango de Años Inválido", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (NumberFormatException e) {
                return false; // Ya validado arriba
            }
        }
        
        return true;
    }
}