/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gt.gob.sat.fiscalizacion.consulta360.dto;

import java.math.BigDecimal;

/**
 *
 * @author dealonzo
 */
public class DeclaracionDto {
    private String numeroFormulario;
    private String numeroAcceso;
    private String nombreFormulario;
    private String origen;
    private String fechaRecaudo;
    private String fechaDel;
    private String fechaAl;
    private String monto;
    private BigDecimal numeroDocumento;

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param pOrigen the origen to set
     */
    public void setOrigen(String pOrigen) {
        this.origen = pOrigen;
    }

    /**
     * @return the fechaRecaudo
     */
    public String getFechaRecaudo() {
        return fechaRecaudo;
    }

    /**
     * @param pFechaRecaudo the fechaRecaudo to set
     */
    public void setFechaRecaudo(String pFechaRecaudo) {
        this.fechaRecaudo = pFechaRecaudo;
    }

    /**
     * @return the fechaDel
     */
    public String getFechaDel() {
        return fechaDel;
    }

    /**
     * @param pFechaDel the fechaDel to set
     */
    public void setFechaDel(String pFechaDel) {
        this.fechaDel = pFechaDel;
    }

    /**
     * @return the fechaAl
     */
    public String getFechaAl() {
        return fechaAl;
    }

    /**
     * @param pFechaAl the fechaAl to set
     */
    public void setFechaAl(String pFechaAl) {
        this.fechaAl = pFechaAl;
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
     * @return the numeroAcceso
     */
    public String getNumeroAcceso() {
        return numeroAcceso;
    }

    /**
     * @param pNumeroAcceso the numeroAcceso to set
     */
    public void setNumeroAcceso(String pNumeroAcceso) {
        this.numeroAcceso = pNumeroAcceso;
    }

    /**
     * @return the nombreFormulario
     */
    public String getNombreFormulario() {
        return nombreFormulario;
    }

    /**
     * @param pNombreFormulario the nombreFormulario to set
     */
    public void setNombreFormulario(String pNombreFormulario) {
        this.nombreFormulario = pNombreFormulario;
    }

    /**
     * @return the monto
     */
    public String getMonto() {
        return monto;
    }

    /**
     * @param pMonto the monto to set
     */
    public void setMonto(String pMonto) {
        this.monto = pMonto;
    }

    /**
     * @return the numeroDocumento
     */
    public BigDecimal getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param pNumeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(BigDecimal pNumeroDocumento) {
        this.numeroDocumento = pNumeroDocumento;
    }

 
}