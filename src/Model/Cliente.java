package Model;

public class Cliente extends Usuario {
    private String idCliente;
    private String direccion;

    public Cliente(String idCliente,
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
                  String direccion,
                  String rol) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido,
              genero, cedula, telefono, correo, usuario, contraseña, rol);
        this.idCliente = idCliente;
        this.direccion = direccion;
    }

    // Solo necesitamos los getters y setters específicos de Cliente
    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
