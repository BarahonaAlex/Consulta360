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
public class IngresosFelvsDeclaradoIvaGeneralDto {
    
    private String anio;
    private String mes;
    private String cantidad_receptores;
    private String cantidad_facturas;
    private BigDecimal base_imponible;
    private String cantidad_doctos_ncre;
    private BigDecimal monto_ncre;
    private String cantidad_doctos_nabn;
    private BigDecimal monto_nabn;
    private BigDecimal monto_fel;
    private BigDecimal ingresos_afectos_2237;
    private String omiso;
    private BigDecimal diferencia;
    private BigDecimal iva_no_declarado;
    private BigDecimal ingresos_afectos_2046;

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

    public String getCantidad_doctos_ncre() {
        return cantidad_doctos_ncre;
    }

    public void setCantidad_doctos_ncre(String cantidad_doctos_ncre) {
        this.cantidad_doctos_ncre = cantidad_doctos_ncre;
    }

    public BigDecimal getMonto_ncre() {
        return monto_ncre;
    }

    public void setMonto_ncre(BigDecimal monto_ncre) {
        this.monto_ncre = monto_ncre;
    }

    public String getCantidad_doctos_nabn() {
        return cantidad_doctos_nabn;
    }

    public void setCantidad_doctos_nabn(String cantidad_doctos_nabn) {
        this.cantidad_doctos_nabn = cantidad_doctos_nabn;
    }

    public BigDecimal getMonto_nabn() {
        return monto_nabn;
    }

    public void setMonto_nabn(BigDecimal monto_nabn) {
        this.monto_nabn = monto_nabn;
    }

    public BigDecimal getMonto_fel() {
        return monto_fel;
    }

    public void setMonto_fel(BigDecimal monto_fel) {
        this.monto_fel = monto_fel;
    }

    public BigDecimal getIngresos_afectos_2237() {
        return ingresos_afectos_2237;
    }

    public void setIngresos_afectos_2237(BigDecimal ingresos_afectos_2237) {
        this.ingresos_afectos_2237 = ingresos_afectos_2237;
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

    public BigDecimal getIngresos_afectos_2046() {
        return ingresos_afectos_2046;
    }

    public void setIngresos_afectos_2046(BigDecimal ingresos_afectos_2046) {
        this.ingresos_afectos_2046 = ingresos_afectos_2046;
    }
        
    
   

}
