/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Percistencia;

import Service.LoginService;
import Model.*;
import listaDoble.Lista;
import listaDoble.Nodo;

/**
 * Persistencia especializada para manejar la sesión del usuario logueado
 * Obtiene y almacena el ID del usuario actual de forma simple
 * 
 * @author Osvaldo
 */
public class SesionUsuarioActual {
    
    // ============================
    // SINGLETON PATTERN - INSTANCIA ÚNICA
    // ============================
    private static SesionUsuarioActual instancia;
    
    // ============================
    // CAMPOS PARA ALMACENAR SESIÓN
    // ============================
    private String idUsuarioActual;
    private String rolUsuarioActual;
    private String nombreUsuarioActual;
    private LoginService loginService;
    
    // Constructor privado para singleton
    private SesionUsuarioActual() {
        this.idUsuarioActual = null;
        this.rolUsuarioActual = null;
        this.nombreUsuarioActual = null;
        this.loginService = null; // Se inicializa después mediante actualizarLoginService()
    }
    
    // Obtener instancia única
    public static SesionUsuarioActual getInstance() {
        if (instancia == null) {
            instancia = new SesionUsuarioActual();
        }
        return instancia;
    }
    
    // ============================
    // MÉTODOS PRINCIPALES DE SESIÓN
    // ============================
    
    /**
     * Establece la sesión del usuario logueado
     * @param nombreUsuario Nombre de usuario logueado
     * @param rol Rol del usuario
     */
    public void establecerSesion(String nombreUsuario, String rol) {
        this.nombreUsuarioActual = nombreUsuario;
        this.rolUsuarioActual = rol;
        
        // Obtener el ID según el rol
        this.idUsuarioActual = obtenerIdPorRolYUsuario(rol, nombreUsuario);
        
        System.out.println("=== SESIÓN ESTABLECIDA ===");
        System.out.println("Usuario: " + nombreUsuario);
        System.out.println("Rol: " + rol);
        System.out.println("ID: " + idUsuarioActual);
        System.out.println("========================");
    }
    
    /**
     * Cierra la sesión actual, poniendo todos los campos en null
     */
    public void cerrarSesion() {
        System.out.println("=== CERRANDO SESIÓN ===");
        System.out.println("Usuario: " + nombreUsuarioActual + " ha cerrado sesión");
        
        this.idUsuarioActual = null;
        this.rolUsuarioActual = null;
        this.nombreUsuarioActual = null;
        
        System.out.println("Sesión cerrada exitosamente");
        System.out.println("=====================");
    }
    
    /**
     * Verifica si hay una sesión activa
     * @return true si hay usuario logueado, false si no
     */
    public boolean haySesionActiva() {
        return idUsuarioActual != null && 
               rolUsuarioActual != null && 
               nombreUsuarioActual != null;
    }
    
    // ============================
    // GETTERS PARA OBTENER DATOS
    // ============================
    
    /**
     * Obtiene el ID del usuario logueado actual
     * @return ID del usuario o null si no hay sesión
     */
    public String getIdUsuarioActual() {
        return idUsuarioActual;
    }
    
    /**
     * Obtiene el rol del usuario logueado actual
     * @return Rol del usuario o null si no hay sesión
     */
    public String getRolUsuarioActual() {
        return rolUsuarioActual;
    }
    
    /**
     * Obtiene el nombre de usuario logueado actual
     * @return Nombre de usuario o null si no hay sesión
     */
    public String getNombreUsuarioActual() {
        return nombreUsuarioActual;
    }
    
    // ============================
    // MÉTODOS INTERNOS PARA BUSCAR IDs
    // ============================
    
    /**
     * Método interno para obtener ID según rol y usuario
     * @param rol Rol del usuario
     * @param nombreUsuario Nombre de usuario
     * @return ID del usuario o null si no se encuentra
     */
    private String obtenerIdPorRolYUsuario(String rol, String nombreUsuario) {
        try {
            switch (rol.toLowerCase()) {
                case "administrador":
                    return obtenerIdAdministrador(nombreUsuario);
                case "cliente":
                    return obtenerIdCliente(nombreUsuario);
                case "recepcionista":
                    return obtenerIdRecepcionista(nombreUsuario);
                case "proveedor":
                    return obtenerIdProveedor(nombreUsuario);
                default:
                    System.out.println("Rol no reconocido: " + rol);
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener ID para rol " + rol + ": " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Busca el ID del administrador por nombre de usuario
     */
    private String obtenerIdAdministrador(String nombreUsuario) {
        if (loginService == null) {
            System.out.println("LoginService no inicializado en SesionUsuarioActual");
            return null;
        }
        Lista listaAdministradores = loginService.getListaAdministradores();
        if (listaAdministradores == null) return null;
        
        Nodo actual = listaAdministradores.getPrimero();
        while (actual != null) {
            Administrador admin = (Administrador) actual.getDato();
            if (admin.getUsuario().equals(nombreUsuario)) {
                return admin.getIdAdministrador();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    /**
     * Busca el ID del cliente por nombre de usuario
     */
    private String obtenerIdCliente(String nombreUsuario) {
        if (loginService == null) {
            System.out.println("LoginService no inicializado en SesionUsuarioActual");
            return null;
        }
        Lista listaClientes = loginService.getListaClientes();
        if (listaClientes == null) return null;
        
        Nodo actual = listaClientes.getPrimero();
        while (actual != null) {
            Cliente cliente = (Cliente) actual.getDato();
            if (cliente.getUsuario().equals(nombreUsuario)) {
                return cliente.getIdCliente();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    /**
     * Busca el ID del recepcionista por nombre de usuario
     */
    private String obtenerIdRecepcionista(String nombreUsuario) {
        if (loginService == null) {
            System.out.println("LoginService no inicializado en SesionUsuarioActual");
            return null;
        }
        Lista listaRecepcionistas = loginService.getListaRecepcionistas();
        if (listaRecepcionistas == null) return null;
        
        Nodo actual = listaRecepcionistas.getPrimero();
        while (actual != null) {
            Recepcionista recepcionista = (Recepcionista) actual.getDato();
            if (recepcionista.getUsuario().equals(nombreUsuario)) {
                return recepcionista.getIdRecepcionista();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    /**
     * Busca el ID del proveedor por nombre de usuario
     */
    private String obtenerIdProveedor(String nombreUsuario) {
        if (loginService == null) {
            System.out.println("LoginService no inicializado en SesionUsuarioActual");
            return null;
        }
        Lista listaProveedores = loginService.getListaProveedores();
        if (listaProveedores == null) return null;
        
        Nodo actual = listaProveedores.getPrimero();
        while (actual != null) {
            Proveedor proveedor = (Proveedor) actual.getDato();
            if (proveedor.getUsuario().equals(nombreUsuario)) {
                return proveedor.getIdProveedor();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    // ============================
    // MÉTODOS DE UTILIDAD/DEBUG
    // ============================
    
    /**
     * Imprime información de la sesión actual (para debug)
     */
    public void imprimirEstadoSesion() {
        System.out.println("=== ESTADO DE SESIÓN ===");
        System.out.println("Sesión activa: " + haySesionActiva());
        System.out.println("Usuario: " + nombreUsuarioActual);
        System.out.println("Rol: " + rolUsuarioActual);
        System.out.println("ID: " + idUsuarioActual);
        System.out.println("======================");
    }
    
    /**
     * Actualiza el LoginService (útil si se crea una nueva instancia)
     * @param nuevoLoginService Nueva instancia de LoginService
     */
    public void actualizarLoginService(LoginService nuevoLoginService) {
        this.loginService = nuevoLoginService;
        System.out.println("LoginService actualizado en SesionUsuarioActual");
    }
}