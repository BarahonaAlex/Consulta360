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
public class MaquinaDto {
    private String fechaAutorizacion;
    private String numeroResolucion;
    private String marca;
    private String modelo;
    private String numeroSerie;
    private String numeroTerminales;
    private String estadoMaquina;
    private String estadoImpresor;

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
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param pMarca the marca to set
     */
    public void setMarca(String pMarca) {
        this.marca = pMarca;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param pModelo the modelo to set
     */
    public void setModelo(String pModelo) {
        this.modelo = pModelo;
    }

    /**
     * @return the numeroSerie
     */
    public String getNumeroSerie() {
        return numeroSerie;
    }

    /**
     * @param pNumeroSerie the numeroSerie to set
     */
    public void setNumeroSerie(String pNumeroSerie) {
        this.numeroSerie = pNumeroSerie;
    }

    /**
     * @return the numeroTerminales
     */
    public String getNumeroTerminales() {
        return numeroTerminales;
    }

    /**
     * @param pNumeroTerminales the numeroTerminales to set
     */
    public void setNumeroTerminales(String pNumeroTerminales) {
        this.numeroTerminales = pNumeroTerminales;
    }

    /**
     * @return the estadoMaquina
     */
    public String getEstadoMaquina() {
        return estadoMaquina;
    }

    /**
     * @param pEstadoMaquina the estadoMaquina to set
     */
    public void setEstadoMaquina(String pEstadoMaquina) {
        this.estadoMaquina = pEstadoMaquina;
    }

    /**
     * @return the estadoImpresor
     */
    public String getEstadoImpresor() {
        return estadoImpresor;
    }

    /**
     * @param pEstadoImpresor the estadoImpresor to set
     */
    public void setEstadoImpresor(String pEstadoImpresor) {
        this.estadoImpresor = pEstadoImpresor;
    }
}