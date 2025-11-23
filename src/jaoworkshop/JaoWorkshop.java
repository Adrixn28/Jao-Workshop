/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jaoworkshop;

import View.Bievenida;
import javax.swing.SwingUtilities;

/**
 * Clase principal de la aplicación JaoWorkshop
 * @author Adrian
 */
public class JaoWorkshop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Ejecutar la aplicación en el Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Iniciando aplicación JaoWorkshop...");
                    
                    // Crear y mostrar la ventana de bienvenida
                    Bievenida ventanaBienvenida = new Bievenida();
                    ventanaBienvenida.setVisible(true);
                    
                    System.out.println("Aplicación iniciada correctamente.");
                } catch (Exception e) {
                    System.err.println("Error al iniciar la aplicación: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}
