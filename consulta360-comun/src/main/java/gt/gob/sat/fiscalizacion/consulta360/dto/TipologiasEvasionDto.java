/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author cgsamayo
 */
public class TipologiasEvasionDto {
   
     private BigDecimal id;
     private BigDecimal IdTipologia;
     private String Tipologia;
     private String nit;
     private String referencia;
     private Date fechaRegistro;
     private String descripcionTipologia;
     private BigDecimal anioInformacion;
     private BigDecimal indicador;
     private String colorIndicador;
     private BigDecimal montoDiscrepancia;
     private String dependencia;
     private String estado;
     private Date fechaEstado;
     private String observacionesEstado;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getIdTipologia() {
        return IdTipologia;
    }

    public void setIdTipologia(BigDecimal IdTipologia) {
        this.IdTipologia = IdTipologia;
    }

    public String getTipologia() {
        return Tipologia;
    }

    public void setTipologia(String Tipologia) {
        this.Tipologia = Tipologia;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDescripcionTipologia() {
        return descripcionTipologia;
    }

    public void setDescripcionTipologia(String descripcionTipologia) {
        this.descripcionTipologia = descripcionTipologia;
    }

    public BigDecimal getAnioInformacion() {
        return anioInformacion;
    }

    public void setAnioInformacion(BigDecimal anioInformacion) {
        this.anioInformacion = anioInformacion;
    }

    public BigDecimal getIndicador() {
        return indicador;
    }

    public void setIndicador(BigDecimal indicador) {
        this.indicador = indicador;
    }

    public String getColorIndicador() {
        return colorIndicador;
    }

    public void setColorIndicador(String colorIndicador) {
        this.colorIndicador = colorIndicador;
    }

    public BigDecimal getMontoDiscrepancia() {
        return montoDiscrepancia;
    }

    public void setMontoDiscrepancia(BigDecimal montoDiscrepancia) {
        this.montoDiscrepancia = montoDiscrepancia;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public String getObservacionesEstado() {
        return observacionesEstado;
    }

    public void setObservacionesEstado(String observacionesEstado) {
        this.observacionesEstado = observacionesEstado;
    }
   
}
