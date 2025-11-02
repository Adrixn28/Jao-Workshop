/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.*;
import listaDoble.Lista;
import listaDoble.Nodo;
import Percistencia.RepositorioAdministrador;

/**
 *
 * @author Osvaldo
 */
public class AdministradorService {
    private RepositorioAdministrador repositorio;
    private LoginService loginService;

    public AdministradorService() {
        this.repositorio = new RepositorioAdministrador();
        this.loginService = new LoginService();
    }
    
    // Constructor que recibe el LoginService para acceder a las listas
    public AdministradorService(LoginService loginService) {
        this.repositorio = new RepositorioAdministrador();
        this.loginService = loginService;
    }

    public boolean registrarAdministrador(String idAdministrador, 
                                     String primerNombre, 
                                     String segundoNombre,
                                     String primerApellido, 
                                     String segundoApellido,
                                     String genero,
                                     String cedula, 
                                     String telefono, 
                                     String correo,
                                     String usuario, 
                                     String contraseña,
                                     String rol) {
        try {
            Administrador admin = new Administrador(idAdministrador, primerNombre, 
                segundoNombre, primerApellido, segundoApellido, genero, cedula, 
                telefono, correo, usuario, contraseña, rol);
            repositorio.guardarAdministrador(admin);
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar administrador: " + e.getMessage());
            return false;
        }
    }

    public Administrador buscarAdministrador(String id) {
        return repositorio.buscarPorId(id);
    }

    public boolean actualizarAdministrador(Administrador administrador) {
        try {
            repositorio.actualizarAdministrador(administrador);
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar administrador: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarAdministrador(String id) {
        try {
            repositorio.eliminarAdministrador(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar administrador: " + e.getMessage());
            return false;
        }
    }

    public void listarAdministradores() {
        repositorio.listarAdministradores();
    }
    
    // Método para buscar administrador por ID en las listas enlazadas
    public Administrador buscarAdministradorPorIdEnLista(String idAdministrador) {
        Lista listaAdministradores = loginService.getListaAdministradores();
        if (listaAdministradores == null || listaAdministradores.getPrimero() == null) {
            System.out.println("Lista de administradores vacía o null");
            return null;
        }
        
        Nodo actual = listaAdministradores.getPrimero();
        while (actual != null) {
            Administrador admin = (Administrador) actual.getDato();
            if (admin.getIdAdministrador().equals(idAdministrador)) {
                System.out.println("Administrador encontrado: " + admin.getPrimerNombre() + " " + admin.getPrimerApellido());
                return admin;
            }
            actual = actual.getSiguiente();
        }
        
        System.out.println("Administrador con ID " + idAdministrador + " no encontrado");
        return null;
    }
    
    // Método para obtener el ID del administrador logueado desde el usuario
    public String obtenerIdAdministradorPorUsuario(String usuario) {
        Lista listaAdministradores = loginService.getListaAdministradores();
        if (listaAdministradores == null || listaAdministradores.getPrimero() == null) {
            return null;
        }
        
        Nodo actual = listaAdministradores.getPrimero();
        while (actual != null) {
            Administrador admin = (Administrador) actual.getDato();
            if (admin.getUsuario().equals(usuario)) {
                return admin.getIdAdministrador();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    // ===============================
    // MÉTODOS PARA GESTIONAR CLIENTES
    // ===============================
    
    public void agregarCliente(Cliente cliente) {
        loginService.agregarCliente(cliente);
        System.out.println("Cliente agregado: " + cliente.getUsuario());
    }
    
    public boolean eliminarCliente(String idCliente) {
        Lista listaClientes = loginService.getListaClientes();
        if (listaClientes == null || listaClientes.getPrimero() == null) {
            return false;
        }
        
        Nodo actual = listaClientes.getPrimero();
        while (actual != null) {
            Cliente cliente = (Cliente) actual.getDato();
            if (cliente.getIdCliente().equals(idCliente)) {
                listaClientes.eliminar(cliente);
                System.out.println("Cliente eliminado: " + cliente.getUsuario());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public Cliente buscarClientePorId(String idCliente) {
        Lista listaClientes = loginService.getListaClientes();
        if (listaClientes == null || listaClientes.getPrimero() == null) {
            return null;
        }
        
        Nodo actual = listaClientes.getPrimero();
        while (actual != null) {
            Cliente cliente = (Cliente) actual.getDato();
            if (cliente.getIdCliente().equals(idCliente)) {
                return cliente;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    public boolean actualizarCliente(Cliente clienteActualizado) {
        Lista listaClientes = loginService.getListaClientes();
        if (listaClientes == null || listaClientes.getPrimero() == null) {
            return false;
        }
        
        Nodo actual = listaClientes.getPrimero();
        while (actual != null) {
            Cliente cliente = (Cliente) actual.getDato();
            if (cliente.getIdCliente().equals(clienteActualizado.getIdCliente())) {
                // Actualizar todos los campos
                cliente.setPrimerNombre(clienteActualizado.getPrimerNombre());
                cliente.setSegundoNombre(clienteActualizado.getSegundoNombre());
                cliente.setPrimerApellido(clienteActualizado.getPrimerApellido());
                cliente.setSegundoApellido(clienteActualizado.getSegundoApellido());
                cliente.setGenero(clienteActualizado.getGenero());
                cliente.setCedula(clienteActualizado.getCedula());
                cliente.setTelefono(clienteActualizado.getTelefono());
                cliente.setCorreo(clienteActualizado.getCorreo());
                cliente.setUsuario(clienteActualizado.getUsuario());
                cliente.setContraseña(clienteActualizado.getContraseña());
                cliente.setDireccion(clienteActualizado.getDireccion());
                
                System.out.println("Cliente actualizado: " + cliente.getUsuario());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public Lista obtenerTodosLosClientes() {
        return loginService.getListaClientes();
    }
    
    // =========================================
    // MÉTODOS PARA GESTIONAR RECEPCIONISTAS
    // =========================================
    
    public void agregarRecepcionista(Recepcionista recepcionista) {
        loginService.agregarRecepcionista(recepcionista);
        System.out.println("Recepcionista agregado: " + recepcionista.getUsuario());
    }
    
    public boolean eliminarRecepcionista(String idRecepcionista) {
        Lista listaRecepcionistas = loginService.getListaRecepcionistas();
        if (listaRecepcionistas == null || listaRecepcionistas.getPrimero() == null) {
            return false;
        }
        
        Nodo actual = listaRecepcionistas.getPrimero();
        while (actual != null) {
            Recepcionista recepcionista = (Recepcionista) actual.getDato();
            if (recepcionista.getIdRecepcionista().equals(idRecepcionista)) {
                listaRecepcionistas.eliminar(recepcionista);
                System.out.println("Recepcionista eliminado: " + recepcionista.getUsuario());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public Recepcionista buscarRecepcionistaPorId(String idRecepcionista) {
        Lista listaRecepcionistas = loginService.getListaRecepcionistas();
        if (listaRecepcionistas == null || listaRecepcionistas.getPrimero() == null) {
            return null;
        }
        
        Nodo actual = listaRecepcionistas.getPrimero();
        while (actual != null) {
            Recepcionista recepcionista = (Recepcionista) actual.getDato();
            if (recepcionista.getIdRecepcionista().equals(idRecepcionista)) {
                return recepcionista;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    public boolean actualizarRecepcionista(Recepcionista recepcionistaActualizado) {
        Lista listaRecepcionistas = loginService.getListaRecepcionistas();
        if (listaRecepcionistas == null || listaRecepcionistas.getPrimero() == null) {
            return false;
        }
        
        Nodo actual = listaRecepcionistas.getPrimero();
        while (actual != null) {
            Recepcionista recepcionista = (Recepcionista) actual.getDato();
            if (recepcionista.getIdRecepcionista().equals(recepcionistaActualizado.getIdRecepcionista())) {
                // Actualizar todos los campos
                recepcionista.setPrimerNombre(recepcionistaActualizado.getPrimerNombre());
                recepcionista.setSegundoNombre(recepcionistaActualizado.getSegundoNombre());
                recepcionista.setPrimerApellido(recepcionistaActualizado.getPrimerApellido());
                recepcionista.setSegundoApellido(recepcionistaActualizado.getSegundoApellido());
                recepcionista.setGenero(recepcionistaActualizado.getGenero());
                recepcionista.setCedula(recepcionistaActualizado.getCedula());
                recepcionista.setTelefono(recepcionistaActualizado.getTelefono());
                recepcionista.setCorreo(recepcionistaActualizado.getCorreo());
                recepcionista.setUsuario(recepcionistaActualizado.getUsuario());
                recepcionista.setContraseña(recepcionistaActualizado.getContraseña());
                
                System.out.println("Recepcionista actualizado: " + recepcionista.getUsuario());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public Lista obtenerTodosLosRecepcionistas() {
        return loginService.getListaRecepcionistas();
    }
    
    // ===================================
    // MÉTODOS PARA GESTIONAR PROVEEDORES
    // ===================================
    
    public void agregarProveedor(Proveedor proveedor) {
        loginService.agregarProveedor(proveedor);
        System.out.println("Proveedor agregado: " + proveedor.getUsuario());
    }
    
    public boolean eliminarProveedor(String idProveedor) {
        Lista listaProveedores = loginService.getListaProveedores();
        if (listaProveedores == null || listaProveedores.getPrimero() == null) {
            return false;
        }
        
        Nodo actual = listaProveedores.getPrimero();
        while (actual != null) {
            Proveedor proveedor = (Proveedor) actual.getDato();
            if (proveedor.getIdProveedor().equals(idProveedor)) {
                listaProveedores.eliminar(proveedor);
                System.out.println("Proveedor eliminado: " + proveedor.getUsuario());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public Proveedor buscarProveedorPorId(String idProveedor) {
        Lista listaProveedores = loginService.getListaProveedores();
        if (listaProveedores == null || listaProveedores.getPrimero() == null) {
            return null;
        }
        
        Nodo actual = listaProveedores.getPrimero();
        while (actual != null) {
            Proveedor proveedor = (Proveedor) actual.getDato();
            if (proveedor.getIdProveedor().equals(idProveedor)) {
                return proveedor;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    public boolean actualizarProveedor(Proveedor proveedorActualizado) {
        Lista listaProveedores = loginService.getListaProveedores();
        if (listaProveedores == null || listaProveedores.getPrimero() == null) {
            return false;
        }
        
        Nodo actual = listaProveedores.getPrimero();
        while (actual != null) {
            Proveedor proveedor = (Proveedor) actual.getDato();
            if (proveedor.getIdProveedor().equals(proveedorActualizado.getIdProveedor())) {
                // Actualizar todos los campos
                proveedor.setPrimerNombre(proveedorActualizado.getPrimerNombre());
                proveedor.setSegundoNombre(proveedorActualizado.getSegundoNombre());
                proveedor.setPrimerApellido(proveedorActualizado.getPrimerApellido());
                proveedor.setSegundoApellido(proveedorActualizado.getSegundoApellido());
                proveedor.setGenero(proveedorActualizado.getGenero());
                proveedor.setCedula(proveedorActualizado.getCedula());
                proveedor.setTelefono(proveedorActualizado.getTelefono());
                proveedor.setCorreo(proveedorActualizado.getCorreo());
                proveedor.setUsuario(proveedorActualizado.getUsuario());
                proveedor.setContraseña(proveedorActualizado.getContraseña());
                proveedor.setAniosExperiencia(proveedorActualizado.getAniosExperiencia());
                
                System.out.println("Proveedor actualizado: " + proveedor.getUsuario());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public Lista obtenerTodosLosProveedores() {
        return loginService.getListaProveedores();
    }
    
    // ========================================
    // MÉTODOS ADICIONALES PARA ADMINISTRADOR
    // ========================================
    
    public Administrador buscarAdministradorPorId(String idAdministrador) {
        Lista listaAdministradores = loginService.getListaAdministradores();
        if (listaAdministradores == null || listaAdministradores.getPrimero() == null) {
            return null;
        }
        
        Nodo actual = listaAdministradores.getPrimero();
        while (actual != null) {
            Administrador administrador = (Administrador) actual.getDato();
            if (administrador.getIdAdministrador().equals(idAdministrador)) {
                return administrador;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    public boolean actualizarAdministradorEnLista(Administrador administradorActualizado) {
        Lista listaAdministradores = loginService.getListaAdministradores();
        if (listaAdministradores == null || listaAdministradores.getPrimero() == null) {
            return false;
        }
        
        Nodo actual = listaAdministradores.getPrimero();
        while (actual != null) {
            Administrador administrador = (Administrador) actual.getDato();
            if (administrador.getIdAdministrador().equals(administradorActualizado.getIdAdministrador())) {
                // Actualizar todos los campos
                administrador.setPrimerNombre(administradorActualizado.getPrimerNombre());
                administrador.setSegundoNombre(administradorActualizado.getSegundoNombre());
                administrador.setPrimerApellido(administradorActualizado.getPrimerApellido());
                administrador.setSegundoApellido(administradorActualizado.getSegundoApellido());
                administrador.setGenero(administradorActualizado.getGenero());
                administrador.setCedula(administradorActualizado.getCedula());
                administrador.setTelefono(administradorActualizado.getTelefono());
                administrador.setCorreo(administradorActualizado.getCorreo());
                administrador.setUsuario(administradorActualizado.getUsuario());
                administrador.setContraseña(administradorActualizado.getContraseña());
                
                System.out.println("Administrador actualizado en lista: " + administrador.getUsuario());
                
                // Actualizar también la sesión si es el usuario logueado
                
                
                
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    // ===========================
    // MÉTODOS DE UTILIDAD
    // ===========================
    
    public String generarSiguienteId(String tipoUsuario) {
        String prefijo = "";
        Lista lista = null;
        
        switch (tipoUsuario.toLowerCase()) {
            case "cliente":
                prefijo = "CLI";
                lista = loginService.getListaClientes();
                break;
            case "recepcionista":
                prefijo = "REC";
                lista = loginService.getListaRecepcionistas();
                break;
            case "proveedor":
                prefijo = "PRV";
                lista = loginService.getListaProveedores();
                break;
            case "administrador":
                prefijo = "ADM";
                lista = loginService.getListaAdministradores();
                break;
            default:
                return null;
        }
        
        int contador = 1;
        if (lista != null && lista.getPrimero() != null) {
            Nodo actual = lista.getPrimero();
            while (actual != null) {
                contador++;
                actual = actual.getSiguiente();
            }
        }
        
        return prefijo + String.format("%03d", contador);
    }
}
