package Model;

public class Proveedor extends Usuario {

    private String idProveedor;
    private int aniosExperiencia;

    public Proveedor(String idProveedor,
            String primerNombre,
            String segundoNombre,
            String primerApellido,
            String segundoApellido,
            String genero,
            String cedula,
            String telefono,
            String correo,
            int aniosExperiencia,
            String usuario,
            String contraseña,
            String rol) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido,
                genero, cedula, telefono, correo, usuario, contraseña, rol);
        this.idProveedor = idProveedor;
        this.aniosExperiencia = aniosExperiencia;
    }

    public Proveedor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Solo necesitamos los getters y setters específicos de Proveedor
    public String getIdProveedor() { return idProveedor; }
    public void setIdProveedor(String idProveedor) { this.idProveedor = idProveedor; }
    
    public int getAniosExperiencia() { return aniosExperiencia; }
    public void setAniosExperiencia(int aniosExperiencia) { this.aniosExperiencia = aniosExperiencia; }

    public String getPrimerNombre() {
        return primerNombre;
    }

   
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
}
