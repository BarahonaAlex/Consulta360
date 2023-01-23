/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

import java.math.BigDecimal;

/**
 *
 * @author rfsartio
 */
public class ConsultaCantidadDeclaracionesAduanerasConAnioDto {

    private Integer anio;
    private BigDecimal cantidad_declaraciones;
    private String fob_suma;
    private String cif_suma;

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public BigDecimal getCantidad_declaraciones() {
        return cantidad_declaraciones;
    }

    public void setCantidad_declaraciones(BigDecimal cantidad_declaraciones) {
        this.cantidad_declaraciones = cantidad_declaraciones;
    }

    public String getFob_suma() {
        return fob_suma;
    }

    public void setFob_suma(String fob_suma) {
        this.fob_suma = fob_suma;
    }

    public String getCif_suma() {
        return cif_suma;
    }

    public void setCif_suma(String cif_suma) {
        this.cif_suma = cif_suma;
    }

}
