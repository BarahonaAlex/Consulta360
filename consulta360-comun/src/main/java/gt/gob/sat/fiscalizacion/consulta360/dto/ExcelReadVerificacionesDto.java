/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

import com.opencsv.bean.CsvBindByPosition;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author adlabrigo
 */
public class ExcelReadVerificacionesDto {
   
    @CsvBindByPosition(position = 1)
    private String nit;
    
    @CsvBindByPosition(position = 2)
    private String contribuyente;
        
    @CsvBindByPosition(position = 3)
    private String direccionVerificada;
    
    @CsvBindByPosition(position = 4)
    private String departamento;
    
    @CsvBindByPosition(position = 5)
    private String municipio;
    
    @CsvBindByPosition(position = 6)
    private String longitud;
    
    @CsvBindByPosition(position = 7)
    private String latitud;
    
    @CsvBindByPosition(position = 8)
    private String tipoDireccion;
    
    @CsvBindByPosition(position = 9)
    private String estadoDireccion;
    
    @CsvBindByPosition(position = 10)
    private String fuenteDeInformacion;
    
    @CsvBindByPosition(position = 11)
    private String estimacionCapacidadInstalada;
    
    @CsvBindByPosition(position = 12)
    private String contadorEnergiaElectrica;
    
    @CsvBindByPosition(position = 13)
    private String referenciaVerificacion;
    
    @CsvBindByPosition(position = 14)
    private String responsableDeLaVerificacion;
    
    @CsvBindByPosition(position = 15)
    private String estadoRegistro;
    
    @CsvBindByPosition(position = 16)
    private String fechaCreacion;
    
    
   
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

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
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

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

   
    
   
    
}
