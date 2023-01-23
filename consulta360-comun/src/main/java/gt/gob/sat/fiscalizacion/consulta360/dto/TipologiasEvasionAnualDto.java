/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

import java.math.BigDecimal;

/**
 *
 * @author cgsamayo
 */
public class TipologiasEvasionAnualDto {
     private BigDecimal nombreAnio;
     private BigDecimal cantidadAnios;

    public BigDecimal getNombreAnio() {
        return nombreAnio;
    }

    public void setNombreAnio(BigDecimal nombreAnio) {
        this.nombreAnio = nombreAnio;
    }

    public BigDecimal getCantidadAnios() {
        return cantidadAnios;
    }

    public void setCantidadAnios(BigDecimal cantidadAnios) {
        this.cantidadAnios = cantidadAnios;
    }
     
}
