/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author kaaguilr
 */
public class HistorialRiesgoDto implements Serializable {

    /**
     * @return the riesgo
     */
    public String getRiesgo() {
        return riesgo;
    }

    /**
     * @param riesgo the riesgo to set
     */
    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    /**
     * @return the vigenteAPartirDe
     */
    public Date getVigenteAPartirDe() {
        return vigenteAPartirDe;
    }

    /**
     * @param vigenteAPartirDe the vigenteAPartirDe to set
     */
    public void setVigenteAPartirDe(Date vigenteAPartirDe) {
        this.vigenteAPartirDe = vigenteAPartirDe;
    }

    /**
     * @return the anioInformacionBase
     */
    public BigDecimal getAnioInformacionBase() {
        return anioInformacionBase;
    }

    /**
     * @param anioInformacionBase the anioInformacionBase to set
     */
    public void setAnioInformacionBase(BigDecimal anioInformacionBase) {
        this.anioInformacionBase = anioInformacionBase;
    }

    public String getNitContribuyente() {
        return nitContribuyente;
    }

    public void setNitContribuyente(String nitContribuyente) {
        this.nitContribuyente = nitContribuyente;
    }

  
private String riesgo;
private Date vigenteAPartirDe;
private BigDecimal anioInformacionBase;
private String nitContribuyente;
}
