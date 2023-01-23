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
public class EstablecimientoDto {
    private String numeroEstablecimiento;
    private String nombreEstablecimiento;
    private String direccion;
    private String estado;

    /**
     * @return the numeroEstablecimiento
     */
    public String getNumeroEstablecimiento() {
        return numeroEstablecimiento;
    }

    /**
     * @param pNumeroEstablecimiento the numeroEstablecimiento to set
     */
    public void setNumeroEstablecimiento(String pNumeroEstablecimiento) {
        this.numeroEstablecimiento = pNumeroEstablecimiento;
    }

    /**
     * @return the nombreEstablecimiento
     */
    public String getNombreEstablecimiento() {
        return nombreEstablecimiento;
    }

    /**
     * @param pNombreEstablecimiento the nombreEstablecimiento to set
     */
    public void setNombreEstablecimiento(String pNombreEstablecimiento) {
        this.nombreEstablecimiento = pNombreEstablecimiento;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param pDireccion the direccion to set
     */
    public void setDireccion(String pDireccion) {
        this.direccion = pDireccion;
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
