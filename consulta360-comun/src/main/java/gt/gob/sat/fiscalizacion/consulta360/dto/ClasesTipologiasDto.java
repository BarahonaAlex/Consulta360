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
public class ClasesTipologiasDto {
    
    private BigDecimal idTipologia;
    private String tipologia;
    private String descripcion;
    private Date fechaCreacion;

    public BigDecimal getIdTipologia() {
        return idTipologia;
    }

    public void setIdTipologia(BigDecimal idTipologia) {
        this.idTipologia = idTipologia;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
        
}
