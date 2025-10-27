package Service;

import Model.Proveedor;
import listaDoble.Lista;
import listaDoble.Nodo;

/**
 * No tocar este modelo, sólo es para pre-cargar en el View.Proveedor
 * @author Adrian
 */
public class ProveedorService {

    private static Proveedor proveedorActual; // Para mantener el proveedor logueado
    private static Lista listaProveedores; // Luego puedes conectarla a tu persistencia

    public ProveedorService() {
        // Inicializar lista si no existe
        if (listaProveedores == null) {
            listaProveedores = new Lista();

            // 🔹 Datos de ejemplo temporales
            listaProveedores.insertarFinal(new Proveedor(
                    "PRV001", "Carlos", "Andrés", "Pérez", "López", "Masculino",
                    "100200300", "3123456789", "carlos@proveedor.com", 5,
                    "carlosprv", "123", "Proveedor"
            ));
        }
    }

    // 🔹 Buscar proveedor por ID
    public Proveedor buscarProveedorPorId(String idProveedor) {
        if (idProveedor == null || idProveedor.isEmpty()) return null;

        Nodo actual = listaProveedores.getPrimero();

        while (actual != null) {
            Object dato = actual.getDato();

            if (dato instanceof Proveedor) {
                Proveedor p = (Proveedor) dato;

                if (p.getIdProveedor().equalsIgnoreCase(idProveedor)) {
                    return p;
                }
            }

            actual = actual.getSiguiente();
        }

        return null; // No encontrado
    }

    // Método para establecer el proveedor que acaba de iniciar sesión
    public static void setProveedorActual(Proveedor proveedor) {
        proveedorActual = proveedor;

    }

    // Método para obtener los datos del proveedor logueado
    public static Proveedor getProveedorActual() {
        return proveedorActual;
    }

    // Método para limpiar los datos del proveedor cuando cierre sesión
    public static void limpiarProveedorActual() {
        proveedorActual = null;
    }
}
