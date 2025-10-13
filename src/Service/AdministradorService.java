/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Administrador;
import Percistencia.RepositorioAdministrador;

/**
 *
 * @author Osvaldo
 */
public class AdministradorService {
    private RepositorioAdministrador repositorio;

    public AdministradorService() {
        this.repositorio = new RepositorioAdministrador();
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
}
