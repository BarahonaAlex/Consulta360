/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

import java.math.BigDecimal;

/**
 *
 * @author adlabrigo
 */
public class ImpuestosDto {
    
    private int id;
    private int codigoImpuesto;
    private String nombreImpuesto;
    private String regimen;
    private String fechaAdiciono;
    private String acreditamiento;
    private BigDecimal morosidad;
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoImpuesto() {
        return codigoImpuesto;
    }

    public void setCodigoImpuesto(int codigoImpuesto) {
        this.codigoImpuesto = codigoImpuesto;
    }

    public String getNombreImpuesto() {
        return nombreImpuesto;
    }

    public void setNombreImpuesto(String nombreImpuesto) {
        this.nombreImpuesto = nombreImpuesto;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getFechaAdiciono() {
        return fechaAdiciono;
    }

    public void setFechaAdiciono(String fechaAdiciono) {
        this.fechaAdiciono = fechaAdiciono;
    }

    public String getAcreditamiento() {
        return acreditamiento;
    }

    public void setAcreditamiento(String acreditamiento) {
        this.acreditamiento = acreditamiento;
    }

    public BigDecimal getMorosidad() {
        return morosidad;
    }

    public void setMorosidad(BigDecimal morosidad) {
        this.morosidad = morosidad;
    }

   
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
