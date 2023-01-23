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
public class ConteoVehiculosActivosInactivosDto {

    private BigDecimal nombreAnio;
    private BigDecimal cantidadVehiculos;

    public BigDecimal getNombreAnio() {
        return nombreAnio;
    }

    public void setNombreAnio(BigDecimal nombreAnio) {
        this.nombreAnio = nombreAnio;
    }

    public BigDecimal getCantidadVehiculos() {
        return cantidadVehiculos;
    }

    public void setCantidadVehiculos(BigDecimal cantidadVehiculos) {
        this.cantidadVehiculos = cantidadVehiculos;
    }

    @Override
    public String toString() {
        return "ConteoVehiculosActivosInactivosDto{" + "nombreAnio=" + nombreAnio + ", cantidadVehiculos=" + cantidadVehiculos + '}';
    }

}
