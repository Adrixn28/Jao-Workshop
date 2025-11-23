package Model;


public class Usuario {
    protected String primerNombre;
    protected String segundoNombre;
    protected String primerApellido;
    protected String segundoApellido;
    protected String genero;
    protected String cedula;
    protected String telefono;
    protected String correo;
    protected String usuario;
    protected String contraseña;
    protected String rol; 
    
    //Constructor por default
    public Usuario(){
      
    } 

    public Usuario(String primerNombre, String segundoNombre, String primerApellido, 
                  String segundoApellido,String genero, String cedula, String telefono, 
                  String correo, String usuario, String contraseña, String rol) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.genero = genero;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    // Getters y Setters
    public String getPrimerNombre() { return primerNombre; }
    public void setPrimerNombre(String primerNombre) { this.primerNombre = primerNombre; }
    
    public String getSegundoNombre() { return segundoNombre; }
    public void setSegundoNombre(String segundoNombre) { this.segundoNombre = segundoNombre; }
    
    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }
    
    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}