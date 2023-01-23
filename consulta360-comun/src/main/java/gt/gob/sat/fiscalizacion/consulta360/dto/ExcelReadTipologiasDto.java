/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

import com.opencsv.bean.CsvBindByPosition;

/**
 *
 * @author rfsartio
 */
public class ExcelReadTipologiasDto {

    @CsvBindByPosition(position = 1)
    private String id_tipologia;

    @CsvBindByPosition(position = 2)
    private String nit;

    @CsvBindByPosition(position = 3)
    private String referencias;

    @CsvBindByPosition(position = 4)
    private String fecha_registro;

    @CsvBindByPosition(position = 5)
    private String descripción_tipologia;

    @CsvBindByPosition(position = 6)
    private String anio_informacion;

    @CsvBindByPosition(position = 7)
    private String indicador;

    @CsvBindByPosition(position = 8)
    private String color_indicador;

    @CsvBindByPosition(position = 9)
    private String monto_discrepancia;

    @CsvBindByPosition(position = 10)
    private String dependencia;

    @CsvBindByPosition(position = 11)
    private String estado;

    @CsvBindByPosition(position = 12)
    private String fecha_estado;

    @CsvBindByPosition(position = 13)
    private String observaciones_estado;

    public String getId_tipologia() {
        return id_tipologia;
    }

    public void setId_tipologia(String id_tipologia) {
        this.id_tipologia = id_tipologia;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getReferencias() {
        return referencias;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getDescripción_tipologia() {
        return descripción_tipologia;
    }

    public void setDescripción_tipologia(String descripción_tipologia) {
        this.descripción_tipologia = descripción_tipologia;
    }

    public String getAnio_informacion() {
        return anio_informacion;
    }

    public void setAnio_informacion(String anio_informacion) {
        this.anio_informacion = anio_informacion;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getColor_indicador() {
        return color_indicador;
    }

    public void setColor_indicador(String color_indicador) {
        this.color_indicador = color_indicador;
    }

    public String getMonto_discrepancia() {
        return monto_discrepancia;
    }

    public void setMonto_discrepancia(String monto_discrepancia) {
        this.monto_discrepancia = monto_discrepancia;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_estado() {
        return fecha_estado;
    }

    public void setFecha_estado(String fecha_estado) {
        this.fecha_estado = fecha_estado;
    }

    public String getObservaciones_estado() {
        return observaciones_estado;
    }

    public void setObservaciones_estado(String observaciones_estado) {
        this.observaciones_estado = observaciones_estado;
    }

}
