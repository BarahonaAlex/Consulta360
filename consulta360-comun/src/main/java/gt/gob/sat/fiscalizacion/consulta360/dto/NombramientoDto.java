/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gt.gob.sat.fiscalizacion.consulta360.dto;

/**
 *
 * @author dealonzo
 */
public class NombramientoDto {
    private String nombramiento;
    private String expediente;
    private String periodoDel;
    private String periodoAl;
    private String estadoNombramiento;
    private String fechaEmision;
    private String nombrePlan;

    /**
     * @return the nombramiento
     */
    public String getNombramiento() {
        return nombramiento;
    }

    /**
     * @param pNombramiento the nombramiento to set
     */
    public void setNombramiento(String pNombramiento) {
        this.nombramiento = pNombramiento;
    }

    /**
     * @return the estadoNombramiento
     */
    public String getEstadoNombramiento() {
        return estadoNombramiento;
    }

    /**
     * @param pEstadoNombramiento the estadoNombramiento to set
     */
    public void setEstadoNombramiento(String pEstadoNombramiento) {
        this.estadoNombramiento = pEstadoNombramiento;
    }

    /**
     * @return the fechaEmision
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param pFechaEmision the fechaEmision to set
     */
    public void setFechaEmision(String pFechaEmision) {
        this.fechaEmision = pFechaEmision;
    }

    /**
     * @return the nombrePlan
     */
    public String getNombrePlan() {
        return nombrePlan;
    }

    /**
     * @param pNombrePlan the nombrePlan to set
     */
    public void setNombrePlan(String pNombrePlan) {
        this.nombrePlan = pNombrePlan;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getPeriodoDel() {
        return periodoDel;
    }

    public void setPeriodoDel(String periodoDel) {
        this.periodoDel = periodoDel;
    }

    public String getPeriodoAl() {
        return periodoAl;
    }

    public void setPeriodoAl(String periodoAl) {
        this.periodoAl = periodoAl;
    }
}