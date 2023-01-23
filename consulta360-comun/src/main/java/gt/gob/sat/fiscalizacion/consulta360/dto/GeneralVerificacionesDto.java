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
public class GeneralVerificacionesDto {

    private String direccionVerificada;
    private BigDecimal longitud;
    private BigDecimal latitud;
    private String fechaCreacion;
    private String estadoDireccion;
 

    public String getDireccionVerificada() {
        return direccionVerificada;
    }

    public void setDireccionVerificada(String direccionVerificada) {
        this.direccionVerificada = direccionVerificada;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    
    public String getEstadoDireccion() {
        return estadoDireccion;
    }

    public void setEstadoDireccion(String estadoDireccion) {
        this.estadoDireccion = estadoDireccion;
    }
    
    
    
    
}
