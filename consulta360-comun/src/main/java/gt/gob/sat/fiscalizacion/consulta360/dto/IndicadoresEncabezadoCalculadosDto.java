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
public class IndicadoresEncabezadoCalculadosDto {
     /*VARIABLES TABLA SICT_INDICADORES_ENCABEZADO */
    
     private String nombreIndicador;
     private String nombreCortoIndicador;
     private String datosNumerador;
     private String datosDenominador;
     private String impuesto;
     private String periodoInicio;
     private String periodoFin;
    
    
     /*VARIABLES TABLA SICT_INDICADORES_CALCULADOS */
    
     private BigDecimal idIndicadoresCalculados;
     private BigDecimal idIndicador;
     private String nit;
     private String nombreContrIns;
     private String clasificacion;
     private String region;
     private String sectorEconomico;
     private String anio;
     private String numerador;
     private String denominador;
     private String coeficiente;
     private String coeficienteSectorEco;
     private String modaSectorEc;
     private String modaGeneral;
     private String medianaGeneral;
     private String color;
     private String tipoIndicador;
     private String fechaIva;
     private String afiliacionIva;
     private String status;
     private String personeria;
     private String fechaIsr;
     private String afiliacionIsr;
     private String establecimientosActivos;
     private String establecimientosInactivos;
     private String plOpActual;


    public String getNombreIndicador() {
        return nombreIndicador;
    }

    public void setNombreIndicador(String nombreIndicador) {
        this.nombreIndicador = nombreIndicador;
    }

    public String getNombreCortoIndicador() {
        return nombreCortoIndicador;
    }

    public void setNombreCortoIndicador(String nombreCortoIndicador) {
        this.nombreCortoIndicador = nombreCortoIndicador;
    }

    public String getDatosNumerador() {
        return datosNumerador;
    }

    public void setDatosNumerador(String datosNumerador) {
        this.datosNumerador = datosNumerador;
    }

    public String getDatosDenominador() {
        return datosDenominador;
    }

    public void setDatosDenominador(String datosDenominador) {
        this.datosDenominador = datosDenominador;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public String getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(String periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public String getPeriodoFin() {
        return periodoFin;
    }

    public void setPeriodoFin(String periodoFin) {
        this.periodoFin = periodoFin;
    }

    public BigDecimal getIdIndicadoresCalculados() {
        return idIndicadoresCalculados;
    }

    public void setIdIndicadoresCalculados(BigDecimal idIndicadoresCalculados) {
        this.idIndicadoresCalculados = idIndicadoresCalculados;
    }

    public BigDecimal getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(BigDecimal idIndicador) {
        this.idIndicador = idIndicador;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombreContrIns() {
        return nombreContrIns;
    }

    public void setNombreContrIns(String nombreContrIns) {
        this.nombreContrIns = nombreContrIns;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSectorEconomico() {
        return sectorEconomico;
    }

    public void setSectorEconomico(String sectorEconomico) {
        this.sectorEconomico = sectorEconomico;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getNumerador() {
        return numerador;
    }

    public void setNumerador(String numerador) {
        this.numerador = numerador;
    }

    public String getDenominador() {
        return denominador;
    }

    public void setDenominador(String denominador) {
        this.denominador = denominador;
    }

    public String getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(String coeficiente) {
        this.coeficiente = coeficiente;
    }

    public String getCoeficienteSectorEco() {
        return coeficienteSectorEco;
    }

    public void setCoeficienteSectorEco(String coeficienteSectorEco) {
        this.coeficienteSectorEco = coeficienteSectorEco;
    }

    public String getModaSectorEc() {
        return modaSectorEc;
    }

    public void setModaSectorEc(String modaSectorEc) {
        this.modaSectorEc = modaSectorEc;
    }

    public String getModaGeneral() {
        return modaGeneral;
    }

    public void setModaGeneral(String modaGeneral) {
        this.modaGeneral = modaGeneral;
    }

    public String getMedianaGeneral() {
        return medianaGeneral;
    }

    public void setMedianaGeneral(String medianaGeneral) {
        this.medianaGeneral = medianaGeneral;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(String tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }

    public String getFechaIva() {
        return fechaIva;
    }

    public void setFechaIva(String fechaIva) {
        this.fechaIva = fechaIva;
    }

    public String getAfiliacionIva() {
        return afiliacionIva;
    }

    public void setAfiliacionIva(String afiliacionIva) {
        this.afiliacionIva = afiliacionIva;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPersoneria() {
        return personeria;
    }

    public void setPersoneria(String personeria) {
        this.personeria = personeria;
    }

    public String getFechaIsr() {
        return fechaIsr;
    }

    public void setFechaIsr(String fechaIsr) {
        this.fechaIsr = fechaIsr;
    }

    public String getAfiliacionIsr() {
        return afiliacionIsr;
    }

    public void setAfiliacionIsr(String afiliacionIsr) {
        this.afiliacionIsr = afiliacionIsr;
    }

    public String getEstablecimientosActivos() {
        return establecimientosActivos;
    }

    public void setEstablecimientosActivos(String establecimientosActivos) {
        this.establecimientosActivos = establecimientosActivos;
    }

    public String getEstablecimientosInactivos() {
        return establecimientosInactivos;
    }

    public void setEstablecimientosInactivos(String establecimientosInactivos) {
        this.establecimientosInactivos = establecimientosInactivos;
    }

    public String getPlOpActual() {
        return plOpActual;
    }

    public void setPlOpActual(String plOpActual) {
        this.plOpActual = plOpActual;
    }
     
      
    
}

    
    

