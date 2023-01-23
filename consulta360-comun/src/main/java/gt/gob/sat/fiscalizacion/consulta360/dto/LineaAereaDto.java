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
public class LineaAereaDto {
    private String fechaAutorizacion;
    private String noResolucionAutorizacion;
    private String descripcion;
    private String serie;
    private String numeracionInicial;
    private String numeracionFinal;
    private String estado;

    /**
     * @return the fechaAutorizacion
     */
    public String getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    /**
     * @param pFechaAutorizacion the fechaAutorizacion to set
     */
    public void setFechaAutorizacion(String pFechaAutorizacion) {
        this.fechaAutorizacion = pFechaAutorizacion;
    }

    /**
     * @return the noResolucionAutorizacion
     */
    public String getNoResolucionAutorizacion() {
        return noResolucionAutorizacion;
    }

    /**
     * @param pNoResolucionAutorizacion the noResolucionAutorizacion to set
     */
    public void setNoResolucionAutorizacion(String pNoResolucionAutorizacion) {
        this.noResolucionAutorizacion = pNoResolucionAutorizacion;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param pDescripcion the descripcion to set
     */
    public void setDescripcion(String pDescripcion) {
        this.descripcion = pDescripcion;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param pSerie the serie to set
     */
    public void setSerie(String pSerie) {
        this.serie = pSerie;
    }

    /**
     * @return the numeracionInicial
     */
    public String getNumeracionInicial() {
        return numeracionInicial;
    }

    /**
     * @param pNumeracionInicial the numeracionInicial to set
     */
    public void setNumeracionInicial(String pNumeracionInicial) {
        this.numeracionInicial = pNumeracionInicial;
    }

    /**
     * @return the numeracionFinal
     */
    public String getNumeracionFinal() {
        return numeracionFinal;
    }

    /**
     * @param pNumeracionFinal the numeracionFinal to set
     */
    public void setNumeracionFinal(String pNumeracionFinal) {
        this.numeracionFinal = pNumeracionFinal;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param pEstado the estado to set
     */
    public void setEstado(String pEstado) {
        this.estado = pEstado;
    }
}