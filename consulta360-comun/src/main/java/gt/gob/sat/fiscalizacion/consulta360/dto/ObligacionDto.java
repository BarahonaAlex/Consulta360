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
public class ObligacionDto {
    private String nombreObligacion;
    private String frecuenciaPago;
    private String numeroFormulario;
    private String formaCalculo;

    /**
     * @return the nombreObligacion
     */
    public String getNombreObligacion() {
        return nombreObligacion;
    }

    /**
     * @param pNombreObligacion the nombreObligacion to set
     */
    public void setNombreObligacion(String pNombreObligacion) {
        this.nombreObligacion = pNombreObligacion;
    }

    /**
     * @return the frecuenciaPago
     */
    public String getFrecuenciaPago() {
        return frecuenciaPago;
    }

    /**
     * @param pFrecuenciaPago the frecuenciaPago to set
     */
    public void setFrecuenciaPago(String pFrecuenciaPago) {
        this.frecuenciaPago = pFrecuenciaPago;
    }

    /**
     * @return the numeroFormulario
     */
    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    /**
     * @param pNumeroFormulario the numeroFormulario to set
     */
    public void setNumeroFormulario(String pNumeroFormulario) {
        this.numeroFormulario = pNumeroFormulario;
    }

    /**
     * @return the formaCalculo
     */
    public String getFormaCalculo() {
        return formaCalculo;
    }

    /**
     * @param pFormaCalculo the formaCalculo to set
     */
    public void setFormaCalculo(String pFormaCalculo) {
        this.formaCalculo = pFormaCalculo;
    }
}