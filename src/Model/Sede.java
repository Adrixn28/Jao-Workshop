package Model;

public class Sede {
    private String idSede;
    private String nombre;
    private String direccion;
    private String telefono;
    private String ciudad;
    private String correo;
    private boolean estado;

    public Sede(String idSede, 
                String nombre, 
                String direccion, 
                String telefono, 
                String ciudad, 
                String correo, 
                boolean estado) {
        this.idSede = idSede;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.correo = correo;
        this.estado = estado;
    }

    // Getters y Setters
    public String getIdSede() {
        return idSede;
    }

    public void setIdSede(String idSede) {
        this.idSede = idSede;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Sede other = (Sede) obj;
        return idSede != null && idSede.equals(other.idSede);
    }
}