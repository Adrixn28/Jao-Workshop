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
    
    // Lista compartida de facturas (accesible desde cualquier parte del sistema)
    private static Lista listaFacturas;
    
    // Singleton de ClienteService para compartir repuestos entre todos los frames
    private static ClienteService clienteServiceSingleton;
    
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
            listaFacturas = new Lista();
            
            // Inicializar singleton de ClienteService (compartido entre todos los frames)
            if (clienteServiceSingleton == null) {
                clienteServiceSingleton = new ClienteService();
                System.out.println("ClienteService singleton inicializado.");
            }
            
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
    
    public Lista getListaFacturas() {
        if (listaFacturas == null) {
            listaFacturas = new Lista();
        }
        return listaFacturas;
    }
    
    /**
     * Agrega una factura a la lista compartida
     */
    public void agregarFactura(Factura factura) {
        if (factura != null) {
            getListaFacturas().insertarFinal(factura);
            System.out.println("Factura agregada a LoginService: " + factura.getCodigoFactura());
        }
    }
    
    /**
     * Busca una factura por código de factura, código de venta o ID de venta
     */
    public Factura buscarFacturaPorCualquierCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }
        
        Lista facturas = getListaFacturas();
        if (facturas == null || facturas.getPrimero() == null) {
            return null;
        }
        
        codigo = codigo.trim();
        Nodo actual = facturas.getPrimero();
        
        while (actual != null) {
            Factura factura = (Factura) actual.getDato();
            
            // Buscar por código de factura
            if (factura.getCodigoFactura().equals(codigo)) {
                return factura;
            }
            
            // Buscar por código de venta
            if (factura.getCodigoSerial().equals(codigo)) {
                return factura;
            }
            
            // Buscar por ID de venta (si es numérico)
            try {
                int idVenta = Integer.parseInt(codigo);
                if (factura.getIdVenta() == idVenta) {
                    return factura;
                }
            } catch (NumberFormatException e) {
                // No es un número, continuar
            }
            
            actual = actual.getSiguiente();
        }
        
        return null;
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
    
    public Proveedor buscarProveedorPorId(String idProveedor) {
    Nodo actual = listaProveedores.getPrimero();
    while (actual != null) {
        Proveedor p = (Proveedor) actual.getDato();
        if (p.getIdProveedor().equalsIgnoreCase(idProveedor)) {
            return p;
        }
        actual = actual.getSiguiente();
    }
    return null;
}
    
    // ===============================
    // MÉTODOS PARA INTEGRACIÓN CON CLIENTE
    // ===============================
    
    /**
     * Obtiene el objeto Usuario completo basado en el ID de la sesión actual
     * @return Usuario logueado o null si no se encuentra
     */
    public Usuario getUsuarioActualCompleto() {
        inicializarPersistenciasLazy();
        
        if (!sesionActual.haySesionActiva()) {
            return null;
        }
        
        String idUsuario = sesionActual.getIdUsuarioActual();
        String rol = sesionActual.getRolUsuarioActual();
        
        return buscarUsuarioPorId(idUsuario, rol);
    }
    
    /**
     * Busca un usuario por su ID en la lista correspondiente a su rol
     * @param id ID del usuario a buscar
     * @param rol Rol del usuario para saber en qué lista buscar
     * @return Usuario encontrado o null
     */
    public Usuario buscarUsuarioPorId(String id, String rol) {
        Lista lista = obtenerListaPorRol(rol);
        
        if (lista == null || lista.getPrimero() == null) {
            return null;
        }
        
        Nodo actual = lista.getPrimero();
        while (actual != null) {
            Usuario usuario = (Usuario) actual.getDato();
            
            // Usar el método de ID específico según el rol
            String usuarioId = null;
            switch (rol.toLowerCase()) {
                case "administrador":
                    usuarioId = ((Administrador) usuario).getIdAdministrador();
                    break;
                case "cliente":
                    usuarioId = ((Cliente) usuario).getIdCliente();
                    break;
                case "recepcionista":
                    usuarioId = ((Recepcionista) usuario).getIdRecepcionista();
                    break;
                case "proveedor":
                    usuarioId = ((Proveedor) usuario).getIdProveedor();
                    break;
            }
            
            if (usuarioId != null && usuarioId.equals(id)) {
                return usuario;
            }
            actual = actual.getSiguiente();
        }
        
        return null;
    }
    
    /**
     * Obtiene el nombre completo del usuario actual (Nombre + Apellido)
     * @return Nombre completo formateado o "Usuario no identificado"
     */
    public String getNombreCompletoUsuarioActual() {
        Usuario usuario = getUsuarioActualCompleto();
        
        if (usuario != null) {
            return usuario.getPrimerNombre() + " " + usuario.getPrimerApellido();
        } else {
            return "Usuario no identificado";
        }
    }
    
    /**
     * Abre el frame Cliente con la información del usuario logueado
     * Este método puede ser llamado desde cualquier parte del sistema
     * @return true si se abrió exitosamente
     */
    public boolean abrirFrameCliente() {
        try {
            inicializarPersistenciasLazy();
            
            if (!sesionActual.haySesionActiva()) {
                System.out.println("No hay sesión activa para abrir Cliente");
                return false;
            }
            
            String nombreCompleto = getNombreCompletoUsuarioActual();
            String rol = sesionActual.getRolUsuarioActual();
            
            System.out.println("Abriendo frame Cliente para: " + nombreCompleto + " (" + rol + ")");
            
            // Crear Cliente con nombre completo
            new View.Cliente(nombreCompleto).setVisible(true);
            
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al abrir frame Cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Método de testing para probar la apertura del Cliente con usuario específico
     * @param nombreUsuario Nombre del usuario para mostrar
     * @return true si se abrió exitosamente
     */
    public static boolean testAbrirCliente(String nombreUsuario) {
        try {
            System.out.println("TEST: Abriendo Cliente con usuario: " + nombreUsuario);
            new View.Cliente(nombreUsuario).setVisible(true);
            return true;
        } catch (Exception e) {
            System.err.println("ERROR en test Cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Obtiene la instancia compartida de ClienteService (singleton)
     * Todos los frames deben usar esta instancia para que el stock se actualice correctamente
     * @return Instancia compartida de ClienteService
     */
    public ClienteService getClienteService() {
        if (clienteServiceSingleton == null) {
            clienteServiceSingleton = new ClienteService();
            System.out.println("ClienteService singleton creado.");
        }
        return clienteServiceSingleton;
    }
}