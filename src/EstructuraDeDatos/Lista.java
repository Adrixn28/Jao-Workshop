package EstructuraDeDatos;
public class Lista {
     private Nodo primero;
    private Nodo ultimo;

    public Lista() {
        this.primero = null;
        this.ultimo = null;
    }

    //---------------------
    //Métodos de insercción
    //---------------------
    public void insertarInicio(Nodo nuevo) {
        if (this.primero == null && this.ultimo == null) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            nuevo.setSiguiente(nuevo);
            primero.setAnterior(nuevo);
            primero = nuevo;
        }
    }

    public void insertarFinal(Nodo nuevoNodo) {
        if (this.primero == null && this.ultimo == null) {
            this.primero = nuevoNodo;
            this.ultimo = nuevoNodo;
        } else {
            ultimo.setSiguiente(nuevoNodo);
            nuevoNodo.setAnterior(ultimo);
            ultimo = nuevoNodo;

        }
    }

    public void insertarAntes(String datoRef, Nodo nuevo) {
        if (this.primero == null && this.ultimo == null) {
            System.out.println("¡Lista vacía!");
            return;
        } else {

            Nodo sapo = null;
            Nodo sapa = null;
            Nodo sapito = buscar(datoRef);

            if (sapito == null) {
                System.out.println("Nodo no existente");
            } else if (sapito == primero) {
                insertarInicio(nuevo);
            } else {
                nuevo.setSiguiente(sapito);
                nuevo.setAnterior(sapito.getAnterior());
                sapito.getAnterior().setSiguiente(nuevo);
                sapito.setAnterior(nuevo);
            }
        }
    }

    public void insertarDespues(String datoRef, Nodo nuevo) {
        if (this.primero == null && this.ultimo == null) {
            System.out.println("¡Lista vacía!");
        } else {
            Nodo sapo = null;
            Nodo sapa = null;
            Nodo sapito = buscar(datoRef);

            if (sapito == null) {
                System.out.println("Nodo no existe.");
            } else if (sapito == ultimo) {
                insertarFinal(nuevo);
            } else {
                sapa = sapito.getSiguiente();
                nuevo.setAnterior(sapito);
                nuevo.setSiguiente(sapa);
                sapito.setSiguiente(nuevo);
                sapa.setAnterior(nuevo);
            }
        }
    }

    //---------------------
    //Métodos de eliminación
    //---------------------
    public void eliminarInicio() {
        if (this.primero == null && this.ultimo == null) {
            System.out.println("Lista vacía.");
        } else {

            if (primero == ultimo) {
                primero = null;
                ultimo = null;
            } else {
                Nodo sapo = primero;
                primero = primero.getSiguiente(); //El primero pasa a la siguiente posición que será la primera.
                primero.setAnterior(null); //Primero se desconecta del anterior.
                sapo.setSiguiente(null); //El que era primero (que se va a eliminar) se desconecta del primero.
                sapo = null; //Se elimina
            }
        }
    }

    public void eliminarFinal() {
        if (this.primero == null && this.ultimo == null) {
            System.out.println("La Lista está vacía.");
        } else {

            if (primero == ultimo) {
                primero = null;
                ultimo = null;
            } else {
                Nodo sapo = ultimo;
                ultimo = ultimo.getAnterior();
                ultimo.setSiguiente(null);
                sapo.setAnterior(null);
                sapo = null;
            }

        }
    }

    public void eliminarDato(String datoRef) {
        if (this.primero == null && this.ultimo == null) {
            System.out.println("Lista vacía ");
        } else {
            Nodo sapo = null;
            Nodo sapa = null;
            Nodo sapito = buscar(datoRef);

            if (sapito == null) {
                System.out.println("Nodo no existente.");
            } else if (sapito == primero && sapito == ultimo) {
                // Solo hay un nodo en la lista
                primero = null;
                ultimo = null;
            } else if (sapito == primero) {
                // Es el primer nodo
                sapa = sapito.getSiguiente();
                sapa.setAnterior(null);
                primero = sapa;
            } else if (sapito == ultimo) {
                // Es el último nodo
                sapo = sapito.getAnterior();
                sapo.setSiguiente(null);
                ultimo = sapo;
            } else {
                // Nodo intermedio
                sapo = sapito.getAnterior();
                sapa = sapito.getSiguiente();
                sapo.setSiguiente(sapa);
                sapa.setAnterior(sapo);
            }

            // Limpieza final del nodo eliminado
            if (sapito != null) {
                sapito.setAnterior(null);
                sapito.setSiguiente(null);
                sapito = null;
            }

            System.out.println("Nodo eliminado correctamente.");
        }
    }

    public void eliminarAntes(String datoRef) {
        if (this.primero == null && this.ultimo == null) {
            System.out.println("¡Lista vacía!");
        } else {
            Nodo sapo = null;
            Nodo sapito = buscar(datoRef);

            if (sapito == null) {
                System.out.println("Nodo no existente");
            } else {
                sapo = sapito.getAnterior();

                if (sapo == null) {
                    System.out.println("No hay nodo antes del primero.");
                } else {
                    if (sapo == primero) {
                        primero = sapito;
                        sapito.setAnterior(null);
                    } else {
                        sapito.setAnterior(sapo.getAnterior());
                        sapo.getAnterior().setSiguiente(sapito);
                    }

                    sapo.setAnterior(null);
                    sapo.setSiguiente(null);
                    sapo = null;
                    System.out.println("Nodo eliminado correctamente.");
                }
            }
        }
    }

    public void eliminarDespues(String datoRef) { //q no esté vacía, que si es el unico se elimine y que no sea ultimo
        if (this.primero == null && this.ultimo == null) {
            System.out.println("¡Lista vacía!");
        } else {
            Nodo sapo = null;
            Nodo sapito = buscar(datoRef);

            if (sapito == null) {
                System.out.println("Nodo no existente");
            } else {
                sapo = sapito.getSiguiente();

                sapito.setSiguiente(sapo.getSiguiente());
                sapo.getSiguiente().setAnterior(sapito);
                sapo.setAnterior(null);
                sapo.setSiguiente(null);
                sapo = null;
            }

        }
    }

    //Métodos de búsqueda
    public Nodo buscar(String datoBuscar) {
        Nodo sapo = primero;
        Nodo sapa = ultimo;
        Nodo sapito = null;

        do {
            if (sapo.getDato().equals(datoBuscar)) {
                sapito = sapo;
                break;
            }

            if (sapa.getDato().equals(datoBuscar)) {
                sapito = sapa;
                break;
            }

            sapo = sapo.getSiguiente(); //Salta al siguiente dato.
            sapa = sapa.getAnterior();
        } while (sapo.getAnterior() != sapa.getSiguiente() || (sapo.getAnterior() == sapa.getSiguiente()));

        return sapito;
    }

    public Nodo buscarPares(String datoBuscar) {
        Nodo sapo = primero;
        Nodo sapa = ultimo;
        Nodo sapito = null;

        do {
            if (sapo.getDato().equals(datoBuscar)) {
                sapito = sapo;
                break;
            }

            if (sapa.getDato().equals(datoBuscar)) {
                sapito = sapa;
                break;
            }

            sapo = sapo.getSiguiente(); //Salta al siguiente dato.
            sapa = sapa.getAnterior();
        } while (sapo.getAnterior() == sapa || sapa.getSiguiente() == sapo);

        return sapito;
    }

    //Métodos de recorrer
    public void recorrer() {
        Nodo sapo = primero;
        while (sapo != null) { //Para hasta que sea null (Se entiende que en ´null´ es el último nodo.
            System.out.println(sapo.getDato()); //Muestra el dato primero.
            sapo = sapo.getSiguiente(); //Salta al siguiente dato.
        }
    }

    public void recorrerBack() {
        Nodo sapo = ultimo;
        while (sapo != null) { //Para hasta que sea null (Se entiende que en ´null´ es el último nodo.
            System.out.println(sapo.getDato()); //Muestra el dato primero.
            sapo = sapo.getSiguiente(); //Salta al siguiente dato.
        }
    }


}
