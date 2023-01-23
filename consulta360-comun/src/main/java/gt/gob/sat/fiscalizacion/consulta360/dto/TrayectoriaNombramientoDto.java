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
public class TrayectoriaNombramientoDto {
    private String fecha;
    private String etapa;
    private String fechaReal;
    private String informacionEtapa;

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param pFecha the fecha to set
     */
    public void setFecha(String pFecha) {
        this.fecha = pFecha;
    }

    /**
     * @return the etapa
     */
    public String getEtapa() {
        return etapa;
    }

    /**
     * @param pEtapa the etapa to set
     */
    public void setEtapa(String pEtapa) {
        this.etapa = pEtapa;
    }

    /**
     * @return the fechaReal
     */
    public String getFechaReal() {
        return fechaReal;
    }

    /**
     * @param pFechaReal the fechaReal to set
     */
    public void setFechaReal(String pFechaReal) {
        this.fechaReal = pFechaReal;
    }

    /**
     * @return the informacionEtapa
     */
    public String getInformacionEtapa() {
        return informacionEtapa;
    }

    /**
     * @param pInformacionEtapa the informacionEtapa to set
     */
    public void setInformacionEtapa(String pInformacionEtapa) {
        this.informacionEtapa = pInformacionEtapa;
    }
}