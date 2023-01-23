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
public class DocumentoDto {
    private String inconsistencia;
    private String fechaAutorizacion;
    private String numeroResolucion;
    private String tipoDocumento;
    private String serie;
    private String numeroInicial;
    private String numeroFinal;
    private String total;
    private String totalCruce;
    private String estadoResolucion;
    private String documento;
    private String fechaSolicito;
    private String fechaDocumento;
    private String fechaNotifico;
    private String fechaCaptura;

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
     * @return the numeroResolucion
     */
    public String getNumeroResolucion() {
        return numeroResolucion;
    }

    /**
     * @param pNumeroResolucion the numeroResolucion to set
     */
    public void setNumeroResolucion(String pNumeroResolucion) {
        this.numeroResolucion = pNumeroResolucion;
    }

    /**
     * @return the tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param pTipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(String pTipoDocumento) {
        this.tipoDocumento = pTipoDocumento;
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
     * @return the numeroInicial
     */
    public String getNumeroInicial() {
        return numeroInicial;
    }

    /**
     * @param pNumeroInicial the numeroInicial to set
     */
    public void setNumeroInicial(String pNumeroInicial) {
        this.numeroInicial = pNumeroInicial;
    }

    /**
     * @return the numeroFinal
     */
    public String getNumeroFinal() {
        return numeroFinal;
    }

    /**
     * @param pNumeroFinal the numeroFinal to set
     */
    public void setNumeroFinal(String pNumeroFinal) {
        this.numeroFinal = pNumeroFinal;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param pTotal the total to set
     */
    public void setTotal(String pTotal) {
        this.total = pTotal;
    }

    /**
     * @return the estadoResolucion
     */
    public String getEstadoResolucion() {
        return estadoResolucion;
    }

    /**
     * @param pEstadoResolucion the estadoResolucion to set
     */
    public void setEstadoResolucion(String pEstadoResolucion) {
        this.estadoResolucion = pEstadoResolucion;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param pDocumento the documento to set
     */
    public void setDocumento(String pDocumento) {
        this.documento = pDocumento;
    }

    /**
     * @return the fechaSolicito
     */
    public String getFechaSolicito() {
        return fechaSolicito;
    }

    /**
     * @param pFechaSolicito the fechaSolicito to set
     */
    public void setFechaSolicito(String pFechaSolicito) {
        this.fechaSolicito = pFechaSolicito;
    }

    /**
     * @return the fechaDocumento
     */
    public String getFechaDocumento() {
        return fechaDocumento;
    }

    /**
     * @param pFechaDocumento the fechaDocumento to set
     */
    public void setFechaDocumento(String pFechaDocumento) {
        this.fechaDocumento = pFechaDocumento;
    }

    /**
     * @return the fechaNotifico
     */
    public String getFechaNotifico() {
        return fechaNotifico;
    }

    /**
     * @param pFechaNotifico the fechaNotifico to set
     */
    public void setFechaNotifico(String pFechaNotifico) {
        this.fechaNotifico = pFechaNotifico;
    }

    public String getInconsistencia() {
        return inconsistencia;
    }

    public void setInconsistencia(String inconsistencia) {
        this.inconsistencia = inconsistencia;
    }

    public String getTotalCruce() {
        return totalCruce;
    }

    public void setTotalCruce(String totalCruce) {
        this.totalCruce = totalCruce;
    }

    public String getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(String fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }
}