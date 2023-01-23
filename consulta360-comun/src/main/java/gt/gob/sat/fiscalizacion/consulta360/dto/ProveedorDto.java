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
public class ProveedorDto {
    private String nivel;
    private String nitProveedor;
    private String nombreProveedor;
    private String monto;
    private String mesPeriodo;
    private String numeroMesPeriodo;
    private String anioPeriodo;
    private String semaforoDeclaracionIVA;
    private String semaforoPerfil;
    private BigDecimal codigoFormulario;
    private String numeroVersion;
    private BigDecimal numeroDocumento;
    private BigDecimal anioFiscal;
    private String nombramiento;
    
    

    /**
     * @return the nitProveedor
     */
    public String getNitProveedor() {
        return nitProveedor;
    }

    /**
     * @param pNitProveedor the nitProveedor to set
     */
    public void setNitProveedor(String pNitProveedor) {
        this.nitProveedor = pNitProveedor;
    }

    /**
     * @return the nombreProveedor
     */
    public String getNombreProveedor() {
        return nombreProveedor;
    }

    /**
     * @param pNombreProveedor the nombreProveedor to set
     */
    public void setNombreProveedor(String pNombreProveedor) {
        this.nombreProveedor = pNombreProveedor;
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
     * @return the mesPeriodo
     */
    public String getMesPeriodo() {
        return mesPeriodo;
    }

    /**
     * @param pMesPeriodo the mesPeriodo to set
     */
    public void setMesPeriodo(String pMesPeriodo) {
        this.mesPeriodo = pMesPeriodo;
    }

    /**
     * @return the anioPeriodo
     */
    public String getAnioPeriodo() {
        return anioPeriodo;
    }

    /**
     * @param pAnioPeriodo the anioPeriodo to set
     */
    public void setAnioPeriodo(String pAnioPeriodo) {
        this.anioPeriodo = pAnioPeriodo;
    }

    /**
     * @return the numeroMesPeriodo
     */
    public String getNumeroMesPeriodo() {
        return numeroMesPeriodo;
    }

    /**
     * @param pNumeroMesPeriodo the numeroMesPeriodo to set
     */
    public void setNumeroMesPeriodo(String pNumeroMesPeriodo) {
        this.numeroMesPeriodo = pNumeroMesPeriodo;
    }

    /**
     * @return the semaforoDeclaracionIVA
     */
    public String getSemaforoDeclaracionIVA() {
        return semaforoDeclaracionIVA;
    }

    /**
     * @param pSemaforoDeclaracionIVA the semaforoDeclaracionIVA to set
     */
    public void setSemaforoDeclaracionIVA(String pSemaforoDeclaracionIVA) {
        this.semaforoDeclaracionIVA = pSemaforoDeclaracionIVA;
    }

    /**
     * @return the semaforoPerfil
     */
    public String getSemaforoPerfil() {
        return semaforoPerfil;
    }

    /**
     * @param pSemaforoPerfil the semaforoPerfil to set
     */
    public void setSemaforoPerfil(String pSemaforoPerfil) {
        this.semaforoPerfil = pSemaforoPerfil;
    }

    /**
     * @return the codigoFormulario
     */
    public BigDecimal getCodigoFormulario() {
        return codigoFormulario;
    }

    /**
     * @param pCodigoFormulario the codigoFormulario to set
     */
    public void setCodigoFormulario(BigDecimal pCodigoFormulario) {
        this.codigoFormulario = pCodigoFormulario;
    }

    /**
     * @return the numeroVersion
     */
    public String getNumeroVersion() {
        return numeroVersion;
    }

    /**
     * @param pNumeroVersion the numeroVersion to set
     */
    public void setNumeroVersion(String pNumeroVersion) {
        this.numeroVersion = pNumeroVersion;
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

    /**
     * @return the anioFiscal
     */
    public BigDecimal getAnioFiscal() {
        return anioFiscal;
    }

    /**
     * @param pAnioFiscal the anioFiscal to set
     */
    public void setAnioFiscal(BigDecimal pAnioFiscal) {
        this.anioFiscal = pAnioFiscal;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }
}