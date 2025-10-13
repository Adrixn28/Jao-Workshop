package Percistencia;

import Model.Administrador;
import listaDoble.Lista;
import listaDoble.Nodo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Osvaldo
 */
public class RepositorioAdministrador {
    private Lista listaAdministradores;

    public RepositorioAdministrador() {
        this.listaAdministradores = new Lista();
    }

    public void guardarAdministrador(Administrador administrador) {
        // Verificar si ya existe un administrador con ese ID
        if (buscarPorId(administrador.getIdAdministrador()) == null) {
            listaAdministradores.insertarFinal(administrador);
            System.out.println("Administrador guardado exitosamente.");
        } else {
            System.out.println("Ya existe un administrador con ese ID.");
        }
    }

    public Administrador buscarPorId(String idAdministrador) {
        Nodo actual = listaAdministradores.buscar(new Administrador(idAdministrador, "", "", 
            "", "", "", "", "", "", "", "", ""));
        return actual != null ? (Administrador) actual.getDato() : null;
    }

    public void eliminarAdministrador(String id) {
        Administrador admin = buscarPorId(id);
        if (admin != null) {
            listaAdministradores.eliminar(admin);
            System.out.println("Administrador eliminado exitosamente.");
        } else {
            System.out.println("No se encontró el administrador con ID: " + id);
        }
    }

    public void actualizarAdministrador(Administrador administrador) {
        Administrador adminExistente = buscarPorId(administrador.getIdAdministrador());
        if (adminExistente != null) {
            listaAdministradores.eliminar(adminExistente);
            listaAdministradores.insertarFinal(administrador);
            System.out.println("Administrador actualizado exitosamente.");
        } else {
            System.out.println("No se encontró el administrador para actualizar.");
        }
    }

    public void listarAdministradores() {
        if (listaAdministradores != null) {
            System.out.println("Lista de Administradores:");
            listaAdministradores.recorrer();
        } else {
            System.out.println("No hay administradores registrados.");
        }
    }
}
