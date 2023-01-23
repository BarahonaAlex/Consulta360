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
public class IngresosFelvsDeclaradoIvaPequeDto {
    
    private String anio;
    private String mes;
    private String cantidad_receptores;
    private String cantidad_facturas;
    private BigDecimal base_imponible;
    private BigDecimal monto_fel;
    private BigDecimal ingresos_afectos_2046;
    private String omiso;
    private BigDecimal diferencia;
    private BigDecimal iva_no_declarado;
    private BigDecimal ventas_y_serv_prestados;

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getCantidad_receptores() {
        return cantidad_receptores;
    }

    public void setCantidad_receptores(String cantidad_receptores) {
        this.cantidad_receptores = cantidad_receptores;
    }

    public String getCantidad_facturas() {
        return cantidad_facturas;
    }

    public void setCantidad_facturas(String cantidad_facturas) {
        this.cantidad_facturas = cantidad_facturas;
    }

    public BigDecimal getBase_imponible() {
        return base_imponible;
    }

    public void setBase_imponible(BigDecimal base_imponible) {
        this.base_imponible = base_imponible;
    }

    public BigDecimal getMonto_fel() {
        return monto_fel;
    }

    public void setMonto_fel(BigDecimal monto_fel) {
        this.monto_fel = monto_fel;
    }

    public BigDecimal getIngresos_afectos_2046() {
        return ingresos_afectos_2046;
    }

    public void setIngresos_afectos_2046(BigDecimal ingresos_afectos_2046) {
        this.ingresos_afectos_2046 = ingresos_afectos_2046;
    }

    public String getOmiso() {
        return omiso;
    }

    public void setOmiso(String omiso) {
        this.omiso = omiso;
    }

    public BigDecimal getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(BigDecimal diferencia) {
        this.diferencia = diferencia;
    }

    public BigDecimal getIva_no_declarado() {
        return iva_no_declarado;
    }

    public void setIva_no_declarado(BigDecimal iva_no_declarado) {
        this.iva_no_declarado = iva_no_declarado;
    }

    public BigDecimal getVentas_y_serv_prestados() {
        return ventas_y_serv_prestados;
    }

    public void setVentas_y_serv_prestados(BigDecimal ventas_y_serv_prestados) {
        this.ventas_y_serv_prestados = ventas_y_serv_prestados;
    }
    
    
    
   
       

}
