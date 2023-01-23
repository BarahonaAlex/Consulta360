/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author adlabrigo
 */
public class HistorialCargaDto {
    
    private BigDecimal idCarga;
     private String nombreArchivo;
     private Date fechaCargaRegistro;
     private BigDecimal cantidadRegistrosCargados;
     private String usuarioRegistro;
     private Blob documentoArchivo;

    public BigDecimal getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(BigDecimal idCarga) {
        this.idCarga = idCarga;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Date getFechaCargaRegistro() {
        return fechaCargaRegistro;
    }

    public void setFechaCargaRegistro(Date fechaCargaRegistro) {
        this.fechaCargaRegistro = fechaCargaRegistro;
    }

    
   

    public BigDecimal getCantidadRegistrosCargados() {
        return cantidadRegistrosCargados;
    }

    public void setCantidadRegistrosCargados(BigDecimal cantidadRegistrosCargados) {
        this.cantidadRegistrosCargados = cantidadRegistrosCargados;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Blob getDocumentoArchivo() {
        return documentoArchivo;
    }

    public void setDocumentoArchivo(Blob documentoArchivo) {
        this.documentoArchivo = documentoArchivo;
    }
     
     
    
}
