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
public class VerificacionesEnCampoConteoDto {
    
    private String estados;
    private BigDecimal cantidadRegistros;

    public String getEstados() {
        return estados;
    }

    public void setEstados(String estados) {
        this.estados = estados;
    }

    public BigDecimal getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(BigDecimal cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }
    
}
