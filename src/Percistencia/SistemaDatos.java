package Percistencia;

import Model.Repuesto;
import listaDoble.Lista;

public class SistemaDatos {

    private static SistemaDatos instancia;

    public Lista listaRepuestos;

    private SistemaDatos() {
        listaRepuestos = new Lista();
        cargarRepuestosIniciales();
    }

    public static SistemaDatos getInstancia() {
        if (instancia == null) {
            instancia = new SistemaDatos();
        }
        return instancia;
    }

    private void cargarRepuestosIniciales() {

        if (!listaRepuestos.estaVacia()) return;

        Repuesto repuesto1 = new Repuesto(
                987654321,
                "Filtro de aceite",
                "Filtro de alta eficiencia para motor 1.6",
                "Bosch",
                "Motor",
                45000.0,
                20,
                "Disponible"
        );

        Repuesto repuesto2 = new Repuesto(
                87654321,
                "Pastillas de freno",
                "Pastillas cerámicas para alto rendimiento",
                "Brembo",
                "Frenos",
                95000.0,
                15,
                "Agotado"
        );

        Repuesto repuesto3 = new Repuesto(
                12345678,
                "Bujías de iridio NGK",
                "Set de 4 bujías NGK Iridium IX",
                "NGK",
                "Encendido",
                120000.0,
                25,
                "Retirado"
        );

        listaRepuestos.insertarFinal(repuesto1);
        listaRepuestos.insertarFinal(repuesto2);
        listaRepuestos.insertarFinal(repuesto3);

        System.out.println("Repuestos cargados desde Singleton.");
    }
}

