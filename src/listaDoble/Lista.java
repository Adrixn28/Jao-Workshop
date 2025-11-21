package listaDoble;

public class Lista {

    private Nodo primero;
    private Nodo ultimo;

    public Lista() {
        this.primero = null;
        this.ultimo = null;
    }

    //---------------------
    // MÉTODOS DE INSERCIÓN
    //---------------------
    public void insertarInicio(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (this.primero == null && this.ultimo == null) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            nuevo.setSiguiente(primero);
            primero.setAnterior(nuevo);
            primero = nuevo;
        }
    }

    public void insertarFinal(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (this.primero == null && this.ultimo == null) {
            this.primero = nuevoNodo;
            this.ultimo = nuevoNodo;
        } else {
            ultimo.setSiguiente(nuevoNodo);
            nuevoNodo.setAnterior(ultimo);
            ultimo = nuevoNodo;
        }
    }

    public void insertarAntes(Object datoRef, Object datoNuevo) {
        if (this.primero == null && this.ultimo == null) {
            System.out.println("¡Lista vacía!");
            return;
        }

        Nodo sapito = buscar(datoRef);
        if (sapito == null) {
            System.out.println("Nodo no existente.");
        } else if (sapito == primero) {
            insertarInicio(datoNuevo);
        } else {
            Nodo nuevo = new Nodo(datoNuevo);
            nuevo.setSiguiente(sapito);
            nuevo.setAnterior(sapito.getAnterior());
            sapito.getAnterior().setSiguiente(nuevo);
            sapito.setAnterior(nuevo);
        }
    }

    public void insertarDespues(Object datoRef, Object datoNuevo) {
        if (this.primero == null && this.ultimo == null) {
            System.out.println("¡Lista vacía!");
            return;
        }

        Nodo sapito = buscar(datoRef);
        if (sapito == null) {
            System.out.println("Nodo no existente.");
        } else if (sapito == ultimo) {
            insertarFinal(datoNuevo);
        } else {
            Nodo nuevo = new Nodo(datoNuevo);
            Nodo siguiente = sapito.getSiguiente();
            nuevo.setAnterior(sapito);
            nuevo.setSiguiente(siguiente);
            sapito.setSiguiente(nuevo);
            siguiente.setAnterior(nuevo);
        }
    }

    //---------------------
    // MÉTODOS DE ELIMINACIÓN
    //---------------------
    public void eliminarInicio() {
        if (this.primero == null) {
            System.out.println("Lista vacía.");
            return;
        }

        if (primero == ultimo) {
            primero = null;
            ultimo = null;
        } else {
            Nodo eliminar = primero;
            primero = primero.getSiguiente();
            primero.setAnterior(null);
            eliminar.setSiguiente(null);
        }
        System.out.println("Nodo eliminado al inicio.");
    }

    public void eliminarFinal() {
        if (this.primero == null) {
            System.out.println("Lista vacía.");
            return;
        }

        if (primero == ultimo) {
            primero = null;
            ultimo = null;
        } else {
            Nodo eliminar = ultimo;
            ultimo = ultimo.getAnterior();
            ultimo.setSiguiente(null);
            eliminar.setAnterior(null);
        }
        System.out.println("Nodo eliminado al final.");
    }

    public void eliminar(Object datoRef) {
        if (this.primero == null) {
            System.out.println("Lista vacía.");
            return;
        }

        Nodo sapito = buscar(datoRef);
        if (sapito == null) {
            System.out.println("Nodo no existente.");
            return;
        }

        if (sapito == primero && sapito == ultimo) {
            primero = null;
            ultimo = null;
        } else if (sapito == primero) {
            primero = sapito.getSiguiente();
            primero.setAnterior(null);
        } else if (sapito == ultimo) {
            ultimo = sapito.getAnterior();
            ultimo.setSiguiente(null);
        } else {
            Nodo anterior = sapito.getAnterior();
            Nodo siguiente = sapito.getSiguiente();
            anterior.setSiguiente(siguiente);
            siguiente.setAnterior(anterior);
        }

        sapito.setAnterior(null);
        sapito.setSiguiente(null);
        System.out.println("Nodo eliminado correctamente.");
    }

    public void eliminarAntes(Object datoRef) {
        if (this.primero == null) {
            System.out.println("Lista vacía.");
            return;
        }

        Nodo sapito = buscar(datoRef);
        if (sapito == null) {
            System.out.println("Nodo no existente.");
            return;
        }

        Nodo anterior = sapito.getAnterior();
        if (anterior == null) {
            System.out.println("No hay nodo antes del primero.");
            return;
        }

        eliminar(anterior.getDato());
    }

    public void eliminarDespues(Object datoRef) {
        if (this.primero == null) {
            System.out.println("Lista vacía.");
            return;
        }

        Nodo sapito = buscar(datoRef);
        if (sapito == null) {
            System.out.println("Nodo no existente.");
            return;
        }

        Nodo siguiente = sapito.getSiguiente();
        if (siguiente == null) {
            System.out.println("No hay nodo después del último.");
            return;
        }

        eliminar(siguiente.getDato());
    }

    //---------------------
    // MÉTODOS DE BÚSQUEDA
    //---------------------
    public Nodo buscar(Object datoBuscar) {
        Nodo sapo = primero;
        while (sapo != null) {
            if (sapo.getDato().equals(datoBuscar)) {
                return sapo;
            }
            sapo = sapo.getSiguiente();
        }
        return null;
    }

    //---------------------
    // MÉTODOS DE RECORRIDO
    //---------------------
    public void recorrer() {
        Nodo sapo = primero;
        while (sapo != null) {
            System.out.println(sapo.getDato());
            sapo = sapo.getSiguiente();
        }
    }

    public void recorrerBack() {
        Nodo sapo = ultimo;
        while (sapo != null) {
            System.out.println(sapo.getDato());
            sapo = sapo.getAnterior();
        }
    }

    public Nodo getPrimero() {
        return primero;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }

    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }
   
    public boolean estaVacia() {
    return this.primero == null;
}
    
}
