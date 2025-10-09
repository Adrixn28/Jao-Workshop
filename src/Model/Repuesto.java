package Model;

import ListaDoble.Lista;

public class Repuesto {

    private int idRepuesto;
    private String nombre;
    private String descripcion;
    private String marca;
    private String categoria;
    private double precio;
    private int stock;
    private Proveedor proveedor;
    
    //Creación de la lista!
     private Lista listaRepuestos ; // usa la misma estructura


    public Repuesto(int idRepuesto, String nombre, String descripcion, String marca, String categoria, double precio, int stock, Object proovedor) {
        this.idRepuesto = idRepuesto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.proveedor = proveedor;
    }

    public int getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(int idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Object getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    

}
