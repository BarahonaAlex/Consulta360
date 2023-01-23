/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

import java.math.BigDecimal;

/**
 *
 * @author cgsamayo
 */
public class IngresosIvavsIngresosIsrDto {
    
private String periodoDesde;
private String periodoHasta;
private String fechaRecaudoIva;
private String codigoFormularioIva;
private String numeroFormularioIva;
private String statusDelFormularioIva;
private String ventasExentas;
private String ventasMedicamentosGenericosAlternantivosYAntirretrovirales;
private String ventasNoAfectadasRealizadasAContribuyentesCalificadosConElDecreto29_28_y_SusReformas;
private String ventasGravadas;
private String serviciosGravados;
private String exportacionesACentroAmerica;
private String exportacionesAlRestoDelMundo;
private String transferenciasConFyduca;
private String ventasdeVehículosTerrestresEnCursoCasilla10;
private String ventasDeVehiculosTerrestreSiguienteoAnteriorAlDelAñoEnCurso;
private String ventasDeVehículosTerrestresDelAñoEnCursoDelAñoSiguienteAlDelAñoEnCurso;   
private String ventasDeVehículosTerrestresAñoSiguienteAñoAnteriorAñoCursoCas23;
private String sumaBaseDebitos;
private String sumaBaseDebitosExpo;
private String fechaRecaudoIsr;
private String codigoFormularioIsr;
private String numeroFormularioIsr;
private String statusDelFormularioIsr;
private String rentaDeActividadesLucrativas;
private String montoTotalDeRentasExentas;
private String rentaImponible;
private String totalIva;
private String totalIsr;
private String diferenciaIsrIva;
private String recaudoPotencialIsr;
private String diferenciaIvaIsr;
private String recaudoPotencialIva;
private String totalVentasExentasIva;
private String potencialRecaudoIvaEIsr;
private String segmentacion;

    public String getPeriodoDesde() {
        return periodoDesde;
    }

    public void setPeriodoDesde(String periodoDesde) {
        this.periodoDesde = periodoDesde;
    }

    public String getPeriodoHasta() {
        return periodoHasta;
    }

    public void setPeriodoHasta(String periodoHasta) {
        this.periodoHasta = periodoHasta;
    }

    public String getFechaRecaudoIva() {
        return fechaRecaudoIva;
    }

    public void setFechaRecaudoIva(String fechaRecaudoIva) {
        this.fechaRecaudoIva = fechaRecaudoIva;
    }

    public String getCodigoFormularioIva() {
        return codigoFormularioIva;
    }

    public void setCodigoFormularioIva(String codigoFormularioIva) {
        this.codigoFormularioIva = codigoFormularioIva;
    }

    public String getNumeroFormularioIva() {
        return numeroFormularioIva;
    }

    public void setNumeroFormularioIva(String numeroFormularioIva) {
        this.numeroFormularioIva = numeroFormularioIva;
    }

    public String getStatusDelFormularioIva() {
        return statusDelFormularioIva;
    }

    public void setStatusDelFormularioIva(String statusDelFormularioIva) {
        this.statusDelFormularioIva = statusDelFormularioIva;
    }

    public String getVentasExentas() {
        return ventasExentas;
    }

    public void setVentasExentas(String ventasExentas) {
        this.ventasExentas = ventasExentas;
    }

    public String getVentasMedicamentosGenericosAlternantivosYAntirretrovirales() {
        return ventasMedicamentosGenericosAlternantivosYAntirretrovirales;
    }

    public void setVentasMedicamentosGenericosAlternantivosYAntirretrovirales(String ventasMedicamentosGenericosAlternantivosYAntirretrovirales) {
        this.ventasMedicamentosGenericosAlternantivosYAntirretrovirales = ventasMedicamentosGenericosAlternantivosYAntirretrovirales;
    }

    public String getVentasNoAfectadasRealizadasAContribuyentesCalificadosConElDecreto29_28_y_SusReformas() {
        return ventasNoAfectadasRealizadasAContribuyentesCalificadosConElDecreto29_28_y_SusReformas;
    }

    public void setVentasNoAfectadasRealizadasAContribuyentesCalificadosConElDecreto29_28_y_SusReformas(String ventasNoAfectadasRealizadasAContribuyentesCalificadosConElDecreto29_28_y_SusReformas) {
        this.ventasNoAfectadasRealizadasAContribuyentesCalificadosConElDecreto29_28_y_SusReformas = ventasNoAfectadasRealizadasAContribuyentesCalificadosConElDecreto29_28_y_SusReformas;
    }

    public String getVentasGravadas() {
        return ventasGravadas;
    }

    public void setVentasGravadas(String ventasGravadas) {
        this.ventasGravadas = ventasGravadas;
    }

    public String getServiciosGravados() {
        return serviciosGravados;
    }

    public void setServiciosGravados(String serviciosGravados) {
        this.serviciosGravados = serviciosGravados;
    }

    public String getExportacionesACentroAmerica() {
        return exportacionesACentroAmerica;
    }

    public void setExportacionesACentroAmerica(String exportacionesACentroAmerica) {
        this.exportacionesACentroAmerica = exportacionesACentroAmerica;
    }

    public String getExportacionesAlRestoDelMundo() {
        return exportacionesAlRestoDelMundo;
    }

    public void setExportacionesAlRestoDelMundo(String exportacionesAlRestoDelMundo) {
        this.exportacionesAlRestoDelMundo = exportacionesAlRestoDelMundo;
    }

    public String getTransferenciasConFyduca() {
        return transferenciasConFyduca;
    }

    public void setTransferenciasConFyduca(String transferenciasConFyduca) {
        this.transferenciasConFyduca = transferenciasConFyduca;
    }

    public String getVentasdeVehículosTerrestresEnCursoCasilla10() {
        return ventasdeVehículosTerrestresEnCursoCasilla10;
    }

    public void setVentasdeVehículosTerrestresEnCursoCasilla10(String ventasdeVehículosTerrestresEnCursoCasilla10) {
        this.ventasdeVehículosTerrestresEnCursoCasilla10 = ventasdeVehículosTerrestresEnCursoCasilla10;
    }

    public String getVentasDeVehiculosTerrestreSiguienteoAnteriorAlDelAñoEnCurso() {
        return ventasDeVehiculosTerrestreSiguienteoAnteriorAlDelAñoEnCurso;
    }

    public void setVentasDeVehiculosTerrestreSiguienteoAnteriorAlDelAñoEnCurso(String ventasDeVehiculosTerrestreSiguienteoAnteriorAlDelAñoEnCurso) {
        this.ventasDeVehiculosTerrestreSiguienteoAnteriorAlDelAñoEnCurso = ventasDeVehiculosTerrestreSiguienteoAnteriorAlDelAñoEnCurso;
    }

    public String getVentasDeVehículosTerrestresDelAñoEnCursoDelAñoSiguienteAlDelAñoEnCurso() {
        return ventasDeVehículosTerrestresDelAñoEnCursoDelAñoSiguienteAlDelAñoEnCurso;
    }

    public void setVentasDeVehículosTerrestresDelAñoEnCursoDelAñoSiguienteAlDelAñoEnCurso(String ventasDeVehículosTerrestresDelAñoEnCursoDelAñoSiguienteAlDelAñoEnCurso) {
        this.ventasDeVehículosTerrestresDelAñoEnCursoDelAñoSiguienteAlDelAñoEnCurso = ventasDeVehículosTerrestresDelAñoEnCursoDelAñoSiguienteAlDelAñoEnCurso;
    }

    public String getVentasDeVehículosTerrestresAñoSiguienteAñoAnteriorAñoCursoCas23() {
        return ventasDeVehículosTerrestresAñoSiguienteAñoAnteriorAñoCursoCas23;
    }

    public void setVentasDeVehículosTerrestresAñoSiguienteAñoAnteriorAñoCursoCas23(String ventasDeVehículosTerrestresAñoSiguienteAñoAnteriorAñoCursoCas23) {
        this.ventasDeVehículosTerrestresAñoSiguienteAñoAnteriorAñoCursoCas23 = ventasDeVehículosTerrestresAñoSiguienteAñoAnteriorAñoCursoCas23;
    }

    public String getSumaBaseDebitos() {
        return sumaBaseDebitos;
    }

    public void setSumaBaseDebitos(String sumaBaseDebitos) {
        this.sumaBaseDebitos = sumaBaseDebitos;
    }

    public String getSumaBaseDebitosExpo() {
        return sumaBaseDebitosExpo;
    }

    public void setSumaBaseDebitosExpo(String sumaBaseDebitosExpo) {
        this.sumaBaseDebitosExpo = sumaBaseDebitosExpo;
    }

    public String getFechaRecaudoIsr() {
        return fechaRecaudoIsr;
    }

    public void setFechaRecaudoIsr(String fechaRecaudoIsr) {
        this.fechaRecaudoIsr = fechaRecaudoIsr;
    }

    public String getCodigoFormularioIsr() {
        return codigoFormularioIsr;
    }

    public void setCodigoFormularioIsr(String codigoFormularioIsr) {
        this.codigoFormularioIsr = codigoFormularioIsr;
    }

    public String getNumeroFormularioIsr() {
        return numeroFormularioIsr;
    }

    public void setNumeroFormularioIsr(String numeroFormularioIsr) {
        this.numeroFormularioIsr = numeroFormularioIsr;
    }

    public String getStatusDelFormularioIsr() {
        return statusDelFormularioIsr;
    }

    public void setStatusDelFormularioIsr(String statusDelFormularioIsr) {
        this.statusDelFormularioIsr = statusDelFormularioIsr;
    }

    public String getRentaDeActividadesLucrativas() {
        return rentaDeActividadesLucrativas;
    }

    public void setRentaDeActividadesLucrativas(String rentaDeActividadesLucrativas) {
        this.rentaDeActividadesLucrativas = rentaDeActividadesLucrativas;
    }

    

    public String getMontoTotalDeRentasExentas() {
        return montoTotalDeRentasExentas;
    }

    public void setMontoTotalDeRentasExentas(String montoTotalDeRentasExentas) {
        this.montoTotalDeRentasExentas = montoTotalDeRentasExentas;
    }

    public String getRentaImponible() {
        return rentaImponible;
    }

    public void setRentaImponible(String rentaImponible) {
        this.rentaImponible = rentaImponible;
    }

    public String getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(String totalIva) {
        this.totalIva = totalIva;
    }

    public String getTotalIsr() {
        return totalIsr;
    }

    public void setTotalIsr(String totalIsr) {
        this.totalIsr = totalIsr;
    }

    public String getDiferenciaIsrIva() {
        return diferenciaIsrIva;
    }

    public void setDiferenciaIsrIva(String diferenciaIsrIva) {
        this.diferenciaIsrIva = diferenciaIsrIva;
    }

    public String getRecaudoPotencialIsr() {
        return recaudoPotencialIsr;
    }

    public void setRecaudoPotencialIsr(String recaudoPotencialIsr) {
        this.recaudoPotencialIsr = recaudoPotencialIsr;
    }

    public String getDiferenciaIvaIsr() {
        return diferenciaIvaIsr;
    }

    public void setDiferenciaIvaIsr(String diferenciaIvaIsr) {
        this.diferenciaIvaIsr = diferenciaIvaIsr;
    }

    public String getRecaudoPotencialIva() {
        return recaudoPotencialIva;
    }

    public void setRecaudoPotencialIva(String recaudoPotencialIva) {
        this.recaudoPotencialIva = recaudoPotencialIva;
    }

    public String getTotalVentasExentasIva() {
        return totalVentasExentasIva;
    }

    public void setTotalVentasExentasIva(String totalVentasExentasIva) {
        this.totalVentasExentasIva = totalVentasExentasIva;
    }

    public String getPotencialRecaudoIvaEIsr() {
        return potencialRecaudoIvaEIsr;
    }

    public void setPotencialRecaudoIvaEIsr(String potencialRecaudoIvaEIsr) {
        this.potencialRecaudoIvaEIsr = potencialRecaudoIvaEIsr;
    }

    public String getSegmentacion() {
        return segmentacion;
    }

    public void setSegmentacion(String segmentacion) {
        this.segmentacion = segmentacion;
    }






        
        


}