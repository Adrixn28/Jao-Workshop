/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Percistencia;

import View.*;
import javax.swing.JOptionPane;

/**
 * Persistencia especializada para manejar la redirección según el rol del usuario
 * Centraliza la lógica de apertura de frames según el tipo de usuario
 * 
 * @author Osvaldo
 */
public class RedireccionPorRol {
    
    private SesionUsuarioActual sesion;
    
    /**
     * Constructor que inicializa la persistencia de redirecciónd
     */
    public RedireccionPorRol() {
        this.sesion = SesionUsuarioActual.getInstance();
    }
    
    /**
     * Redirige al frame correspondiente según el rol del usuario
     * Utiliza la sesión actual para obtener ID y rol
     * @param frameLogin Frame de login a cerrar después de redirigir
     * @return true si redirigió exitosamente, false si hubo error
     */
    public boolean redirigirSegunRol(Login frameLogin) {
        // Verificar que hay sesión activa
        if (!sesion.haySesionActiva()) {
            JOptionPane.showMessageDialog(frameLogin, 
                "Error: No hay una sesión activa para redireccionar.", 
                "Error de Sesión", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String rol = sesion.getRolUsuarioActual();
        String id = sesion.getIdUsuarioActual();
        String usuario = sesion.getNombreUsuarioActual();
        
        System.out.println("=== REDIRECCIONANDO ===");
        System.out.println("Rol: " + rol);
        System.out.println("ID: " + id);
        System.out.println("Usuario: " + usuario);
        System.out.println("=====================");
        
        try {
            switch (rol.toLowerCase()) {
                case "administrador":
                    return abrirFrameAdministrador(id, frameLogin);
                    
                case "cliente":
                    return abrirFrameCliente(id, frameLogin);
                    
                case "recepcionista":
                    return abrirFrameRecepcionista(id, frameLogin);
                    
                case "proveedor":
                    return abrirFrameProveedor(id, frameLogin);
                    
                default:
                    JOptionPane.showMessageDialog(frameLogin, 
                        "Rol no reconocido: " + rol, 
                        "Error de Rol", JOptionPane.ERROR_MESSAGE);
                    return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frameLogin, 
                "Error al abrir la ventana para el rol " + rol + ": " + e.getMessage(), 
                "Error de Sistema", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Abre el frame del Administrador
     */
    private boolean abrirFrameAdministrador(String id, Login frameLogin) {
        if (id == null) {
            JOptionPane.showMessageDialog(frameLogin, 
                "Error: No se pudo obtener el ID del administrador", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        System.out.println("Abriendo frame Administrador con ID: " + id);
        new Administrador(id).setVisible(true);
        return true;
    }
    
    /**
     * Abre el frame del Cliente
     */
    private boolean abrirFrameCliente(String id, Login frameLogin) {
        if (id == null) {
            JOptionPane.showMessageDialog(frameLogin, 
                "Error: No se pudo obtener el ID del cliente", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            System.out.println("Abriendo frame Cliente con ID: " + id);
            
            // Obtener el nombre completo del usuario de la sesión
            String nombreUsuario = sesion.getNombreUsuarioActual();
            
            // Crear y mostrar el frame Cliente con el nombre del usuario
            new Cliente(nombreUsuario).setVisible(true);
            
            return true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frameLogin, 
                "Error al abrir el frame Cliente: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Abre el frame del Recepcionista
     */
    private boolean abrirFrameRecepcionista(String id, Login frameLogin) {
        if (id == null) {
            JOptionPane.showMessageDialog(frameLogin, 
                "Error: No se pudo obtener el ID del recepcionista", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        System.out.println("Frame Recepcionista pendiente de implementar con ID: " + id);
        
        // ⭐ PARA TUS COMPAÑEROS: Cuando implementen Recepcionista.java, cambiar esta línea:
        // new Recepcionista(id).setVisible(true);
        
        // Por ahora mostrar mensaje informativo
        JOptionPane.showMessageDialog(frameLogin, 
            "Recepcionista ID: " + id + "\\n\\nPara implementar:\\n" +
            "1. Crear constructor Recepcionista(String id)\\n" +
            "2. Descomentar línea en abrirFrameRecepcionista()\\n" +
            "3. Seguir guía GUIA_IMPLEMENTACION_ID.md", 
            "Frame Recepcionista - En Desarrollo", JOptionPane.INFORMATION_MESSAGE);
        
        return false; // Retornar false para no cerrar login hasta implementar
    }
    
    /**
     * Abre el frame del Proveedor
     */
    private boolean abrirFrameProveedor(String id, Login frameLogin) {
        if (id == null) {
            JOptionPane.showMessageDialog(frameLogin, 
                "Error: No se pudo obtener el ID del proveedor", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        System.out.println("Abriendo frame Proveedor con ID: " + id);
        new Proveedor().setVisible(true);
        return true;
    }
    
    /**
     * Método de utilidad para cerrar sesión y volver al login
     * @param frameActual Frame actual a cerrar
     */
    public void cerrarSesionYVolverLogin(javax.swing.JFrame frameActual) {
        int opcion = JOptionPane.showConfirmDialog(frameActual,
            "¿Está seguro de cerrar la sesión?\\n" +
            "Volverá a la pantalla de login.",
            "Confirmar Cierre de Sesión",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (opcion == JOptionPane.YES_OPTION) {
            System.out.println("Cerrando sesión y volviendo al login...");
            
            // Cerrar la sesión
            sesion.cerrarSesion();
            
            // Cerrar frame actual
            frameActual.dispose();
            
            // Abrir login nuevamente
            new Login().setVisible(true);
            
            System.out.println("Sesión cerrada exitosamente. Login abierto.");
        }
    }
    
    /**
     * Método de utilidad para debugging - imprime estado actual
     */
    public void imprimirEstadoRedireccion() {
        System.out.println("=== ESTADO REDIRECCION ===");
        sesion.imprimirEstadoSesion();
        System.out.println("=========================");
    }
}