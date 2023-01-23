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
public class DatosContadorDto {
    private String nitContador;
    private String nombreContador;
    private String fechaNombramiento;
    private String status;

    /**
     * @return the nitContador
     */
    public String getNitContador() {
        return nitContador;
    }

    /**
     * @param pNitContador the nitContador to set
     */
    public void setNitContador(String pNitContador) {
        this.nitContador = pNitContador;
    }

    /**
     * @return the nombreContador
     */
    public String getNombreContador() {
        return nombreContador;
    }

    /**
     * @param pNombreContador the nombreContador to set
     */
    public void setNombreContador(String pNombreContador) {
        this.nombreContador = pNombreContador;
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
}
