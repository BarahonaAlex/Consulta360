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
public class obtenerConteoDto {

    private BigDecimal cantidadRegistros;

    public BigDecimal getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(BigDecimal cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
