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
public class IngresosFelvsDeclaradoDto {
       
    private String anio;
    private String mes;
    private String cantidad_clientes_proveedores;
    private String cantidad_facturas_proveedores;
    private BigDecimal monto_proveedores;
    private BigDecimal ingresos_gravados;
    private BigDecimal diferencia;
    private BigDecimal potencial_proveedores;
    private String omiso;
    private String declarante_cero_proveedores;
    private String subdeclarante_proveedores;
    private String declarante_proveedores;
    private String meses_cons_inconsist_proveedores;

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

    public String getCantidad_clientes_proveedores() {
        return cantidad_clientes_proveedores;
    }

    public void setCantidad_clientes_proveedores(String cantidad_clientes_proveedores) {
        this.cantidad_clientes_proveedores = cantidad_clientes_proveedores;
    }

    public String getCantidad_facturas_proveedores() {
        return cantidad_facturas_proveedores;
    }

    public void setCantidad_facturas_proveedores(String cantidad_facturas_proveedores) {
        this.cantidad_facturas_proveedores = cantidad_facturas_proveedores;
    }

    public BigDecimal getMonto_proveedores() {
        return monto_proveedores;
    }

    public void setMonto_proveedores(BigDecimal monto_proveedores) {
        this.monto_proveedores = monto_proveedores;
    }

    public BigDecimal getIngresos_gravados() {
        return ingresos_gravados;
    }

    public void setIngresos_gravados(BigDecimal ingresos_gravados) {
        this.ingresos_gravados = ingresos_gravados;
    }

    
    public BigDecimal getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(BigDecimal diferencia) {
        this.diferencia = diferencia;
    }

    public BigDecimal getPotencial_proveedores() {
        return potencial_proveedores;
    }

    public void setPotencial_proveedores(BigDecimal potencial_proveedores) {
        this.potencial_proveedores = potencial_proveedores;
    }

    public String getOmiso() {
        return omiso;
    }

    public void setOmiso(String omiso) {
        this.omiso = omiso;
    }

    public String getDeclarante_cero_proveedores() {
        return declarante_cero_proveedores;
    }

    public void setDeclarante_cero_proveedores(String declarante_cero_proveedores) {
        this.declarante_cero_proveedores = declarante_cero_proveedores;
    }

    public String getSubdeclarante_proveedores() {
        return subdeclarante_proveedores;
    }

    public void setSubdeclarante_proveedores(String subdeclarante_proveedores) {
        this.subdeclarante_proveedores = subdeclarante_proveedores;
    }

    public String getDeclarante_proveedores() {
        return declarante_proveedores;
    }

    public void setDeclarante_proveedores(String declarante_proveedores) {
        this.declarante_proveedores = declarante_proveedores;
    }

    public String getMeses_cons_inconsist_proveedores() {
        return meses_cons_inconsist_proveedores;
    }

    public void setMeses_cons_inconsist_proveedores(String meses_cons_inconsist_proveedores) {
        this.meses_cons_inconsist_proveedores = meses_cons_inconsist_proveedores;
    }

    
}
