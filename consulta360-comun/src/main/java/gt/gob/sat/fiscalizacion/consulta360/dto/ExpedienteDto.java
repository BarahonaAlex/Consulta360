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
public class ExpedienteDto {
    private String numeroExpediente;
    private String nombreResponsable;
    private String nombreEtapa;
    private String fechaIngreso;
    private String motivoIngreso;
    private String periodoInicial;
    private String periodoFinal;
    private String nombreUnidad;

    /**
     * @return the fechaIngreso
     */
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param pFechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(String pFechaIngreso) {
        this.fechaIngreso = pFechaIngreso;
    }

    /**
     * @return the numeroExpediente
     */
    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    /**
     * @param pNumeroExpediente the numeroExpediente to set
     */
    public void setNumeroExpediente(String pNumeroExpediente) {
        this.numeroExpediente = pNumeroExpediente;
    }

    /**
     * @return the nombreResponsable
     */
    public String getNombreResponsable() {
        return nombreResponsable;
    }

    /**
     * @param pNombreResponsable the nombreResponsable to set
     */
    public void setNombreResponsable(String pNombreResponsable) {
        this.nombreResponsable = pNombreResponsable;
    }

    /**
     * @return the periodoInicial
     */
    public String getPeriodoInicial() {
        return periodoInicial;
    }

    /**
     * @param pPeriodoInicial the periodoInicial to set
     */
    public void setPeriodoInicial(String pPeriodoInicial) {
        this.periodoInicial = pPeriodoInicial;
    }

    /**
     * @return the periodoFinal
     */
    public String getPeriodoFinal() {
        return periodoFinal;
    }

    /**
     * @param pPeriodoFinal the periodoFinal to set
     */
    public void setPeriodoFinal(String pPeriodoFinal) {
        this.periodoFinal = pPeriodoFinal;
    }

    /**
     * @return the nombreEtapa
     */
    public String getNombreEtapa() {
        return nombreEtapa;
    }

    /**
     * @param pNombreEtapa the nombreEtapa to set
     */
    public void setNombreEtapa(String pNombreEtapa) {
        this.nombreEtapa = pNombreEtapa;
    }

    /**
     * @return the nombreUnidad
     */
    public String getNombreUnidad() {
        return nombreUnidad;
    }

    /**
     * @param pNombreUnidad the nombreUnidad to set
     */
    public void setNombreUnidad(String pNombreUnidad) {
        this.nombreUnidad = pNombreUnidad;
    }

    /**
     * @return the motivoIngreso
     */
    public String getMotivoIngreso() {
        return motivoIngreso;
    }

    /**
     * @param motivoIngreso the motivoIngreso to set
     */
    public void setMotivoIngreso(String motivoIngreso) {
        this.motivoIngreso = motivoIngreso;
    }
}