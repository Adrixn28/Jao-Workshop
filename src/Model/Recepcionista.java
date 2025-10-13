package Model;

public class Recepcionista extends Usuario {
    private String idRecepcionista;

    public Recepcionista(String idRecepcionista,
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
        this.idRecepcionista = idRecepcionista;
    }

    // Solo necesitamos el getter y setter específico de Recepcionista
    public String getIdRecepcionista() {
        return idRecepcionista;
    }

    public void setIdRecepcionista(String idRecepcionista) {
        this.idRecepcionista = idRecepcionista;
    }
}
