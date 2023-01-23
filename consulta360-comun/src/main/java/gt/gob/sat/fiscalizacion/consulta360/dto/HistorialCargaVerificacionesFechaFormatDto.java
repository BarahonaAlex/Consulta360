/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

import java.math.BigDecimal;
import java.sql.Blob;

/**
 *
 * @author adlabrigo
 */
public class HistorialCargaVerificacionesFechaFormatDto {
    private BigDecimal idCargaCampo;
    private String nombreArchivo;
    private String fechaCargaRegistro;
    private BigDecimal cantidadRegistrosCargados;
    private String usuarioRegistro;
    private Blob documentoArchivo;

    public BigDecimal getIdCargaCampo() {
        return idCargaCampo;
    }

    public void setIdCargaCampo(BigDecimal idCargaCampo) {
        this.idCargaCampo = idCargaCampo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getFechaCargaRegistro() {
        return fechaCargaRegistro;
    }

    public void setFechaCargaRegistro(String fechaCargaRegistro) {
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
