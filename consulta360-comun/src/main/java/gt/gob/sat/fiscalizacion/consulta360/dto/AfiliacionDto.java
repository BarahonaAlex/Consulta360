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
public class AfiliacionDto {
    private String nombreImpuesto;
    private String nombreRegimen;
    private String nombrePeriodo;
    private String codigoImpuesto;
    private String codigoTipo;
    private String fechaAfiliacion;

    /**
     * @return the nombreImpuesto
     */
    public String getNombreImpuesto() {
        return nombreImpuesto;
    }

    /**
     * @param pNombreImpuesto the nombreImpuesto to set
     */
    public void setNombreImpuesto(String pNombreImpuesto) {
        this.nombreImpuesto = pNombreImpuesto;
    }

    /**
     * @return the nombreRegimen
     */
    public String getNombreRegimen() {
        return nombreRegimen;
    }

    /**
     * @param pNombreRegimen the nombreRegimen to set
     */
    public void setNombreRegimen(String pNombreRegimen) {
        this.nombreRegimen = pNombreRegimen;
    }

    /**
     * @return the nombrePeriodo
     */
    public String getNombrePeriodo() {
        return nombrePeriodo;
    }

    /**
     * @param pNombrePeriodo the nombrePeriodo to set
     */
    public void setNombrePeriodo(String pNombrePeriodo) {
        this.nombrePeriodo = pNombrePeriodo;
    }

    /**
     * @return the codigoImpuesto
     */
    public String getCodigoImpuesto() {
        return codigoImpuesto;
    }

    /**
     * @param pCodigoImpuesto the codigoImpuesto to set
     */
    public void setCodigoImpuesto(String pCodigoImpuesto) {
        this.codigoImpuesto = pCodigoImpuesto;
    }

    /**
     * @return the codigoTipo
     */
    public String getCodigoTipo() {
        return codigoTipo;
    }

    /**
     * @param pCodigoTipo the codigoTipo to set
     */
    public void setCodigoTipo(String pCodigoTipo) {
        this.codigoTipo = pCodigoTipo;
    }

    /**
     * @return the fechaAfiliacion
     */
    public String getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    /**
     * @param fechaAfiliacion the fechaAfiliacion to set
     */
    public void setFechaAfiliacion(String fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }
}