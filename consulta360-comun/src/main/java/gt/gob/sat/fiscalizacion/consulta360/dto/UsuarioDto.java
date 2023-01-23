/**
 * Superintendencia de Administracion Tributaria (SAT) Gerencia de Informatica
 * Desarrollo de Sistemas
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

/**
 *
 * @author dealonzo
 */
public class UsuarioDto{
    private String usuario;
    private String nit;
    private String nombre;

    public UsuarioDto() {
        this.usuario = "Invitado";
        this.nit = "Invitado";
        this.nombre = "Invitado";
    }

    public String getNit() {
        return this.nit;
    }

    public void setNit(String pNit) {
        this.nit = pNit;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String pUsuario) {
        this.usuario = pUsuario;
    }
}