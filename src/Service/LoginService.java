package Service;

import Model.*;
import listaDoble.Lista;
import listaDoble.Nodo;
import Percistencia.SesionUsuarioActual;
import Percistencia.RedireccionPorRol;

public class LoginService {
    
    // Listas enlazadas para cada tipo de usuario
    private static Lista listaAdministradores;
    private static Lista listaClientes;
    private static Lista listaRecepcionistas;
    private static Lista listaProveedores;
    
    // Persistencias organizacionales
    private SesionUsuarioActual sesionActual;
    private RedireccionPorRol redireccionRol;
    
    // Constructor que inicializa solo las listas
    public LoginService() {
        System.out.println("Constructor LoginService - Iniciando...");
        inicializarListas();
        System.out.println("Constructor LoginService - Completado.");
    }
    
    // Método para inicializar las persistencias solo cuando se necesiten (lazy)
    private void inicializarPersistenciasLazy() {
        if (sesionActual == null) {
            System.out.println("Inicializando persistencias organizacionales (lazy)...");
            sesionActual = SesionUsuarioActual.getInstance();
            // Actualizar LoginService en la sesión para evitar referencias null
            sesionActual.actualizarLoginService(this);
        }
        if (redireccionRol == null) {
            redireccionRol = new RedireccionPorRol();
        }
        System.out.println("Persistencias verificadas/inicializadas correctamente.");
    }
    
    // Método para inicializar las listas con datos de prueba
    private void inicializarListas() {
        if (listaAdministradores == null) {
            System.out.println("Inicializando listas por primera vez...");
            listaAdministradores = new Lista();
            listaClientes = new Lista();
            listaRecepcionistas = new Lista();
            listaProveedores = new Lista();
            
            // Agregar algunos usuarios de prueba
            agregarUsuariosPrueba();
            System.out.println("Listas inicializadas con usuarios de prueba.");
        } else {
            System.out.println("Listas ya están inicializadas.");
        }
    }
    
    // Método para agregar usuarios de prueba
    private void agregarUsuariosPrueba() {
        // Administrador de prueba
        Administrador admin = new Administrador(
            "ADM001", "Juan", "Carlos", "Pérez", "González", 
            "Masculino", "12345678", "3001234567", "admin@jao.com", 
            "admin", "123", "Administrador"
        );
        listaAdministradores.insertarFinal(admin);
        
        // Cliente de prueba
        Cliente cliente = new Cliente(
            "CLI001", "María", "Elena", "Rodríguez", "López", 
            "Femenino", "87654321", "3009876543", "maria@email.com", 
            "maria", "456", "Calle 123 #45-67", "Cliente"
        );
        listaClientes.insertarFinal(cliente);
        
        // Recepcionista de prueba
        Recepcionista recepcionista = new Recepcionista(
            "REC001", "Ana", "Sofía", "Martínez", "Castro", 
            "Femenino", "11223344", "3005566778", "ana@jao.com", 
            "Susana", "789", "Recepcionista"
        );
        listaRecepcionistas.insertarFinal(recepcionista);
        
        // Proveedor de prueba
        Proveedor proveedor = new Proveedor(
            "PRV001", "Carlos", "Alberto", "Gómez", "Hernández", 
            "Masculino", "55667788", "3002233445", "carlos@proveedor.com", 
            5, "carlos", "101", "Proveedor"
        );
        listaProveedores.insertarFinal(proveedor);
    }
    
    // Método principal para autenticar usuario
    public Usuario autenticarUsuario(String usuario, String contraseña, String rol) {
       
        
        switch (rol) {
            case "Administrador":
                System.out.println("Buscando en lista de Administradores...");
                return buscarEnLista(listaAdministradores, usuario, contraseña);
            case "Cliente":
                System.out.println("Buscando en lista de Clientes...");
                return buscarEnLista(listaClientes, usuario, contraseña);
            case "Recepcionista":
                System.out.println("Buscando en lista de Recepcionistas...");
                return buscarEnLista(listaRecepcionistas, usuario, contraseña);
            case "Proveedor":
                System.out.println("Buscando en lista de Proveedores...");
                return buscarEnLista(listaProveedores, usuario, contraseña);
            default:
                System.out.println("Rol no reconocido: " + rol);
                return null;
        }
    }
    
    // Método para buscar usuario en una lista específica
    private Usuario buscarEnLista(Lista lista, String usuario, String contraseña) {
        System.out.println("  buscarEnLista - Lista es null? " + (lista == null));
        if (lista == null || lista.getPrimero() == null) {
            System.out.println("  buscarEnLista - Lista vacía o null");
            return null;
        }
        System.out.println("  buscarEnLista - Iniciando búsqueda...");
        
        Nodo actual = lista.getPrimero();
        int contador = 0;
        while (actual != null) {
            Usuario usuarioObj = (Usuario) actual.getDato();
            contador++;
            System.out.println("  Revisando usuario #" + contador + ": '" + usuarioObj.getUsuario() + "'");
            
            if (usuarioObj.getUsuario().equals(usuario) && 
                usuarioObj.getContraseña().equals(contraseña)) {
                System.out.println("  ¡USUARIO ENCONTRADO! " + usuarioObj.getPrimerNombre());
                return usuarioObj;
            }
            actual = actual.getSiguiente();
        }
        System.out.println("  Usuario no encontrado después de revisar " + contador + " registros");
        return null;
    }
    
    // Métodos para agregar usuarios a las listas correspondientes
    public void agregarAdministrador(Administrador administrador) {
        listaAdministradores.insertarFinal(administrador);
    }
    
    public void agregarCliente(Cliente cliente) {
        listaClientes.insertarFinal(cliente);
    }
    
    public void agregarRecepcionista(Recepcionista recepcionista) {
        listaRecepcionistas.insertarFinal(recepcionista);
    }
    
    public void agregarProveedor(Proveedor proveedor) {
        listaProveedores.insertarFinal(proveedor);
    }
    
    // Métodos para verificar si un usuario ya existe
    public boolean existeUsuario(String usuario, String rol) {
        Lista lista = obtenerListaPorRol(rol);
        if (lista == null || lista.getPrimero() == null) {
            return false;
        }
        
        Nodo actual = lista.getPrimero();
        while (actual != null) {
            Usuario usuarioObj = (Usuario) actual.getDato();
            if (usuarioObj.getUsuario().equals(usuario)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    // Método auxiliar para obtener la lista según el rol
    private Lista obtenerListaPorRol(String rol) {
        switch (rol) {
            case "Administrador":
                return listaAdministradores;
            case "Cliente":
                return listaClientes;
            case "Recepcionista":
                return listaRecepcionistas;
            case "Proveedor":
                return listaProveedores;
            default:
                return null;
        }
    }
    
    // Método para obtener todos los usuarios de un rol específico
    public void mostrarUsuariosPorRol(String rol) {
        Lista lista = obtenerListaPorRol(rol);
        if (lista == null || lista.getPrimero() == null) {
            System.out.println("No hay usuarios registrados para el rol: " + rol);
            return;
        }
        
        System.out.println("Usuarios registrados como " + rol + ":");
        Nodo actual = lista.getPrimero();
        while (actual != null) {
            Usuario usuario = (Usuario) actual.getDato();
            System.out.println("- Usuario: " + usuario.getUsuario() + 
                             " | Nombre: " + usuario.getPrimerNombre() + 
                             " " + usuario.getPrimerApellido());
            actual = actual.getSiguiente();
        }
    }
    
    // Getters para acceder a las listas (para testing o administración)
    public Lista getListaAdministradores() {
        return listaAdministradores;
    }
    
    public Lista getListaClientes() {
        return listaClientes;
    }
    
    public Lista getListaRecepcionistas() {
        return listaRecepcionistas;
    }
    
    public Lista getListaProveedores() {
        return listaProveedores;
    }
    
    // ===============================
    // MÉTODOS DE PERSISTENCIA ORGANIZACIONAL
    // ===============================
    
    /**
     * Establece la sesión del usuario logueado
     * @param nombreUsuario Nombre de usuario
     * @param rol Rol del usuario
     */
    public void establecerSesion(String nombreUsuario, String rol) {
        System.out.println("LoginService: Estableciendo sesión para " + nombreUsuario);
        inicializarPersistenciasLazy();
        sesionActual.establecerSesion(nombreUsuario, rol);
    }
    
    /**
     * Redirige según el rol usando las persistencias
     * @param frameLogin Frame de login a cerrar
     * @return true si redirigió exitosamente
     */
    public boolean redirigirSegunRol(javax.swing.JFrame frameLogin) {
        System.out.println("LoginService: Iniciando redirección...");
        inicializarPersistenciasLazy();
        return redireccionRol.redirigirSegunRol((View.Login) frameLogin);
    }
    
    /**
     * Cierra la sesión actual
     */
    public void cerrarSesion() {
        System.out.println("LoginService: Cerrando sesión...");
        inicializarPersistenciasLazy();
        sesionActual.cerrarSesion();
    }
    
    /**
     * Verifica si hay una sesión activa
     * @return true si hay sesión activa
     */
    public boolean haySesionActiva() {
        inicializarPersistenciasLazy();
        return sesionActual.haySesionActiva();
    }
    
    /**
     * Obtiene el ID del usuario actual
     * @return ID del usuario logueado o null
     */
    public String getIdUsuarioActual() {
        inicializarPersistenciasLazy();
        return sesionActual.getIdUsuarioActual();
    }
    
    /**
     * Obtiene el rol del usuario actual
     * @return Rol del usuario logueado o null
     */
    public String getRolUsuarioActual() {
        inicializarPersistenciasLazy();
        return sesionActual.getRolUsuarioActual();
    }
    
    /**
     * Obtiene el nombre del usuario actual
     * @return Nombre del usuario logueado o null
     */
    public String getNombreUsuarioActual() {
        inicializarPersistenciasLazy();
        return sesionActual.getNombreUsuarioActual();
    }
    
    /**
     * Cierra sesión y vuelve al login desde cualquier frame
     * @param frameActual Frame actual a cerrar
     */
    public void cerrarSesionYVolverLogin(javax.swing.JFrame frameActual) {
        System.out.println("LoginService: Cerrando sesión y volviendo al login...");
        inicializarPersistenciasLazy();
        redireccionRol.cerrarSesionYVolverLogin(frameActual);
    }
    
    /**
     * Imprime el estado actual de la sesión (para debug)
     */
    public void imprimirEstadoSesion() {
        inicializarPersistenciasLazy();
        sesionActual.imprimirEstadoSesion();
    }
}