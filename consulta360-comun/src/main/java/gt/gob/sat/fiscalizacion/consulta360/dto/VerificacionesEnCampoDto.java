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
public class VerificacionesEnCampoDto {
     private BigDecimal id;
     private String nit;
     private String contribuyente;
     private String direccionVerificada;
     private String departamento;
     private String municipio;
     private BigDecimal longitud;
     private BigDecimal latitud;
     private String tipoDireccion;
     private String estadoDireccion;
     private String fuenteDeInformacion;
     private String estimacionCapacidadInstalada;
     private String contadorEnergiaElectrica;
     private String referenciaVerificacion;
     private String responsableDeLaVerificacion;
     private String estadoRegistro;
     private Date fechaCreacion;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(String contribuyente) {
        this.contribuyente = contribuyente;
    }

    public String getDireccionVerificada() {
        return direccionVerificada;
    }

    public void setDireccionVerificada(String direccionVerificada) {
        this.direccionVerificada = direccionVerificada;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
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

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public String getEstadoDireccion() {
        return estadoDireccion;
    }

    public void setEstadoDireccion(String estadoDireccion) {
        this.estadoDireccion = estadoDireccion;
    }

    public String getFuenteDeInformacion() {
        return fuenteDeInformacion;
    }

    public void setFuenteDeInformacion(String fuenteDeInformacion) {
        this.fuenteDeInformacion = fuenteDeInformacion;
    }

    public String getEstimacionCapacidadInstalada() {
        return estimacionCapacidadInstalada;
    }

    public void setEstimacionCapacidadInstalada(String estimacionCapacidadInstalada) {
        this.estimacionCapacidadInstalada = estimacionCapacidadInstalada;
    }

    public String getContadorEnergiaElectrica() {
        return contadorEnergiaElectrica;
    }

    public void setContadorEnergiaElectrica(String contadorEnergiaElectrica) {
        this.contadorEnergiaElectrica = contadorEnergiaElectrica;
    }

    public String getReferenciaVerificacion() {
        return referenciaVerificacion;
    }

    public void setReferenciaVerificacion(String referenciaVerificacion) {
        this.referenciaVerificacion = referenciaVerificacion;
    }

    public String getResponsableDeLaVerificacion() {
        return responsableDeLaVerificacion;
    }

    public void setResponsableDeLaVerificacion(String responsableDeLaVerificacion) {
        this.responsableDeLaVerificacion = responsableDeLaVerificacion;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
