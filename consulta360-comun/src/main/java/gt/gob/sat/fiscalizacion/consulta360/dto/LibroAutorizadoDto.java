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
public class LibroAutorizadoDto {
    private String fechaAutorizacion;
    private String noResolucionAutorizacion;
    private String descripcion;
    private String tipoLibro;
    private String numeroHojas;
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
     * @return the tipoLibro
     */
    public String getTipoLibro() {
        return tipoLibro;
    }

    /**
     * @param pTipoLibro the tipoLibro to set
     */
    public void setTipoLibro(String pTipoLibro) {
        this.tipoLibro = pTipoLibro;
    }

    /**
     * @return the numeroHojas
     */
    public String getNumeroHojas() {
        return numeroHojas;
    }

    /**
     * @param pNumeroHojas the numeroHojas to set
     */
    public void setNumeroHojas(String pNumeroHojas) {
        this.numeroHojas = pNumeroHojas;
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