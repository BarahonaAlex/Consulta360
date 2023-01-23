/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;



/**
 *
 * @author ejtovars
 */
public class DatosRepresentantesDto {
    
    private String nitRepresentante;
    private String nombreRepresentante;
    private String fechaNombramiento;
    private String status;
    private String representantePrincipal;

    /**
     * @return the nitRepresentante
     */
    public String getNitRepresentante() {
        return nitRepresentante;
    }

    /**
     * @param pNitRepresentante the nitRepresentante to set
     */
    public void setNitRepresentante(String pNitRepresentante) {
        this.nitRepresentante = pNitRepresentante;
    }

    /**
     * @return the nombreRepresentante
     */
    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    /**
     * @param pNombreRepresentante the nombreRepresentante to set
     */
    public void setNombreRepresentante(String pNombreRepresentante) {
        this.nombreRepresentante = pNombreRepresentante;
    }

    /**
     * @return the fechaNombramiento
     */
    public String getFechaNombramiento() {
        return fechaNombramiento;
    }

    /**
     * @param pFechaNombramiento the fechaNombramiento to set
     */
    public void setFechaNombramiento(String pFechaNombramiento) {
        this.fechaNombramiento = pFechaNombramiento;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param pStatus the status to set
     */
    public void setStatus(String pStatus) {
        this.status = pStatus;
    }

    public String getRepresentantePrincipal() {
        return representantePrincipal;
    }

    public void setRepresentantePrincipal(String representantePrincipal) {
        this.representantePrincipal = representantePrincipal;
    }
    
}
