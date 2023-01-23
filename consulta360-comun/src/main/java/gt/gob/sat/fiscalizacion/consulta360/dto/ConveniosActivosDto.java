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
 * @author adlabrigo
 */
public class ConveniosActivosDto {

    private BigDecimal correlativo;
    private BigDecimal idExpediente;
    private String fechaRuat;
    private BigDecimal montoAutorizado;
    private BigDecimal valorPago;
    private BigDecimal valorIncumplido;
    private BigDecimal valorRestante;

    public BigDecimal getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(BigDecimal correlativo) {
        this.correlativo = correlativo;
    }

    public BigDecimal getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(BigDecimal idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getFechaRuat() {
        return fechaRuat;
    }

    public void setFechaRuat(String fechaRuat) {
        this.fechaRuat = fechaRuat;
    }

  

    public BigDecimal getMontoAutorizado() {
        return montoAutorizado;
    }

    public void setMontoAutorizado(BigDecimal montoAutorizado) {
        this.montoAutorizado = montoAutorizado;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public BigDecimal getValorIncumplido() {
        return valorIncumplido;
    }

    public void setValorIncumplido(BigDecimal valorIncumplido) {
        this.valorIncumplido = valorIncumplido;
    }

    public BigDecimal getValorRestante() {
        return valorRestante;
    }

    public void setValorRestante(BigDecimal valorRestante) {
        this.valorRestante = valorRestante;
    }

   
}
