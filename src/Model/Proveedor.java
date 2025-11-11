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
    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
}
