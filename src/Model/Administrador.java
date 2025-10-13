package Model;

public class Administrador extends Usuario {
    private String idAdministrador;

    public Administrador(String idAdministrador,
                        String primerNombre, 
                        String segundoNombre, 
                        String primerApellido, 
                        String segundoApellido, 
                        String genero,
                        String cedula, 
                        String telefono, 
                        String correo,
                        String usuario,
                        String contraseña,
                        String rol) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido,
               genero, cedula, telefono, correo, usuario, contraseña, rol);
        this.idAdministrador = idAdministrador;
    }

    // Solo necesitamos el getter y setter específico de Administrador
    public String getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(String idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Administrador other = (Administrador) obj;
        return idAdministrador != null && idAdministrador.equals(other.idAdministrador);
    }
}
