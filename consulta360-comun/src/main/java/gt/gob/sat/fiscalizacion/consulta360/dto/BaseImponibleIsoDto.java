/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;



/**
 *
 * @author cgsamayo
 */
public class BaseImponibleIsoDto {
    
    private String periodo_desde_isr;
    private String periodo_hasta_isr;
    private String fecha_recaudo_isr;
    private String anio_impositivo_isr;
    private String codigo_formulario_isr; 
    private String numero_formulario_isr;
    private String status_formulario_isr;
    private String periodo_desde_iso;
    private String periodo_hasta_iso;
    private String fecha_recaudo_iso;
    private String anio_impositivo_iso;
    private String codigo_formulario_iso; 
    private String numero_formulario_iso;
    private String trimestre; 
    private String total_ingresos_brutos_por_servicios_prestados;
    private String total_ingresos_brutos_por_ventas;
    private String costo_de_ventas;
    private String total_ingresos_brutos_costo_de_ventas;
    private String porcentaje_margen_bruto;
    private String porcentaje_margen_bruto_segun_contribuyente; 
    private String diferencia_porcentaje_margen_bruto; 
    private String total_activo;
    private String depr_amor_creditos_l_y_reserva;
    private String activo_neto_segun_sat;
    private String activo_neto_segun_contribuyente; 
    private String diferencia_activo_neto;
    private String base_imponible_segun_activo_neto_segun_sat;
    private String impuesto_determinado_segun_activo_neto;
    private String renta_bruta;
    private String rentas_no_afectas;
    private String total_ingresos_otras_categorias_de_renta;
    private String ingresos_por_negociacion_de_bienes_derechos_y_o_derechos_que_no_sean_del_giro_habitual;
    private String total_ingresos_brutos;
    private String ingresos_por_resarcimientos_provenientes_de_contratos_de_seguro;
    private String ingresos_por_resarcimientos_provenientes_de_contratos_de_reaseguro;
    private String ingresos_por_resarcimientos_provenientes_de_contratos_de_reafianzamiento;
    private String primas_cedidas_de_seguro_y_reafianzamiento;
    private String ingresos_brutos_menos_exclusiones_segun_sat;
    private String ingresos_brutos_menos_exclusiones_segun_contribuyente;
    private String diferencia_en_ingresos_brutos_menos_exclusiones; 
    private String base_imponible_segun_ingresos_brutos;
    private String impuesto_segun_ingresos_brutos;
    private String impuesto_segun_activo_neto;
    private String impuesto_determinado; 
    private String impuesto_determinado_segun_contribuyente; 
    private String iusi;
    private String potencial_de_impuesto_de_solidaridad;
    private String saldo_no_acreditado; 
    private String valor_de_isr_a_acreditar_en_este_periodo;
    private String impuesto;
    private String impuesto_sobre_la_renta;
    private String potencial_impuesto_de_solidaridad_4to_trimestre;
    private String potencial_de_recaudo;

    public String getPeriodo_desde_isr() {
        return periodo_desde_isr;
    }

    public void setPeriodo_desde_isr(String periodo_desde_isr) {
        this.periodo_desde_isr = periodo_desde_isr;
    }

    public String getPeriodo_hasta_isr() {
        return periodo_hasta_isr;
    }

    public void setPeriodo_hasta_isr(String periodo_hasta_isr) {
        this.periodo_hasta_isr = periodo_hasta_isr;
    }

    public String getFecha_recaudo_isr() {
        return fecha_recaudo_isr;
    }

    public void setFecha_recaudo_isr(String fecha_recaudo_isr) {
        this.fecha_recaudo_isr = fecha_recaudo_isr;
    }

    public String getAnio_impositivo_isr() {
        return anio_impositivo_isr;
    }

    public void setAnio_impositivo_isr(String anio_impositivo_isr) {
        this.anio_impositivo_isr = anio_impositivo_isr;
    }

    

    public String getCodigo_formulario_isr() {
        return codigo_formulario_isr;
    }

    public void setCodigo_formulario_isr(String codigo_formulario_isr) {
        this.codigo_formulario_isr = codigo_formulario_isr;
    }

    public String getNumero_formulario_isr() {
        return numero_formulario_isr;
    }

    public void setNumero_formulario_isr(String numero_formulario_isr) {
        this.numero_formulario_isr = numero_formulario_isr;
    }

    public String getStatus_formulario_isr() {
        return status_formulario_isr;
    }

    public void setStatus_formulario_isr(String status_formulario_isr) {
        this.status_formulario_isr = status_formulario_isr;
    }

    

    public String getPeriodo_desde_iso() {
        return periodo_desde_iso;
    }

    public void setPeriodo_desde_iso(String periodo_desde_iso) {
        this.periodo_desde_iso = periodo_desde_iso;
    }
    

    public String getPeriodo_hasta_iso() {
        return periodo_hasta_iso;
    }

    public void setPeriodo_hasta_iso(String periodo_hasta_iso) {
        this.periodo_hasta_iso = periodo_hasta_iso;
    }

    public String getFecha_recaudo_iso() {
        return fecha_recaudo_iso;
    }

    public void setFecha_recaudo_iso(String fecha_recaudo_iso) {
        this.fecha_recaudo_iso = fecha_recaudo_iso;
    }

    public String getAnio_impositivo_iso() {
        return anio_impositivo_iso;
    }

    public void setAnio_impositivo_iso(String anio_impositivo_iso) {
        this.anio_impositivo_iso = anio_impositivo_iso;
    }
    

    

    public String getCodigo_formulario_iso() {
        return codigo_formulario_iso;
    }

    public void setCodigo_formulario_iso(String codigo_formulario_iso) {
        this.codigo_formulario_iso = codigo_formulario_iso;
    }

    public String getNumero_formulario_iso() {
        return numero_formulario_iso;
    }

    public void setNumero_formulario_iso(String numero_formulario_iso) {
        this.numero_formulario_iso = numero_formulario_iso;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public String getTotal_ingresos_brutos_por_servicios_prestados() {
        return total_ingresos_brutos_por_servicios_prestados;
    }

    public void setTotal_ingresos_brutos_por_servicios_prestados(String total_ingresos_brutos_por_servicios_prestados) {
        this.total_ingresos_brutos_por_servicios_prestados = total_ingresos_brutos_por_servicios_prestados;
    }

    public String getTotal_ingresos_brutos_por_ventas() {
        return total_ingresos_brutos_por_ventas;
    }

    public void setTotal_ingresos_brutos_por_ventas(String total_ingresos_brutos_por_ventas) {
        this.total_ingresos_brutos_por_ventas = total_ingresos_brutos_por_ventas;
    }

    public String getCosto_de_ventas() {
        return costo_de_ventas;
    }

    public void setCosto_de_ventas(String costo_de_ventas) {
        this.costo_de_ventas = costo_de_ventas;
    }

    public String getTotal_ingresos_brutos_costo_de_ventas() {
        return total_ingresos_brutos_costo_de_ventas;
    }

    public void setTotal_ingresos_brutos_costo_de_ventas(String total_ingresos_brutos_costo_de_ventas) {
        this.total_ingresos_brutos_costo_de_ventas = total_ingresos_brutos_costo_de_ventas;
    }

    public String getPorcentaje_margen_bruto() {
        return porcentaje_margen_bruto;
    }

    public void setPorcentaje_margen_bruto(String porcentaje_margen_bruto) {
        this.porcentaje_margen_bruto = porcentaje_margen_bruto;
    }

    public String getPorcentaje_margen_bruto_segun_contribuyente() {
        return porcentaje_margen_bruto_segun_contribuyente;
    }

    public void setPorcentaje_margen_bruto_segun_contribuyente(String porcentaje_margen_bruto_segun_contribuyente) {
        this.porcentaje_margen_bruto_segun_contribuyente = porcentaje_margen_bruto_segun_contribuyente;
    }

    public String getDiferencia_porcentaje_margen_bruto() {
        return diferencia_porcentaje_margen_bruto;
    }

    public void setDiferencia_porcentaje_margen_bruto(String diferencia_porcentaje_margen_bruto) {
        this.diferencia_porcentaje_margen_bruto = diferencia_porcentaje_margen_bruto;
    }

    public String getTotal_activo() {
        return total_activo;
    }

    public void setTotal_activo(String total_activo) {
        this.total_activo = total_activo;
    }

    public String getDepr_amor_creditos_l_y_reserva() {
        return depr_amor_creditos_l_y_reserva;
    }

    public void setDepr_amor_creditos_l_y_reserva(String depr_amor_creditos_l_y_reserva) {
        this.depr_amor_creditos_l_y_reserva = depr_amor_creditos_l_y_reserva;
    }

    public String getActivo_neto_segun_sat() {
        return activo_neto_segun_sat;
    }

    public void setActivo_neto_segun_sat(String activo_neto_segun_sat) {
        this.activo_neto_segun_sat = activo_neto_segun_sat;
    }

    public String getActivo_neto_segun_contribuyente() {
        return activo_neto_segun_contribuyente;
    }

    public void setActivo_neto_segun_contribuyente(String activo_neto_segun_contribuyente) {
        this.activo_neto_segun_contribuyente = activo_neto_segun_contribuyente;
    }

    public String getDiferencia_activo_neto() {
        return diferencia_activo_neto;
    }

    public void setDiferencia_activo_neto(String diferencia_activo_neto) {
        this.diferencia_activo_neto = diferencia_activo_neto;
    }

    public String getBase_imponible_segun_activo_neto_segun_sat() {
        return base_imponible_segun_activo_neto_segun_sat;
    }

    public void setBase_imponible_segun_activo_neto_segun_sat(String base_imponible_segun_activo_neto_segun_sat) {
        this.base_imponible_segun_activo_neto_segun_sat = base_imponible_segun_activo_neto_segun_sat;
    }

    public String getImpuesto_determinado_segun_activo_neto() {
        return impuesto_determinado_segun_activo_neto;
    }

    public void setImpuesto_determinado_segun_activo_neto(String impuesto_determinado_segun_activo_neto) {
        this.impuesto_determinado_segun_activo_neto = impuesto_determinado_segun_activo_neto;
    }

    public String getRenta_bruta() {
        return renta_bruta;
    }

    public void setRenta_bruta(String renta_bruta) {
        this.renta_bruta = renta_bruta;
    }

    public String getRentas_no_afectas() {
        return rentas_no_afectas;
    }

    public void setRentas_no_afectas(String rentas_no_afectas) {
        this.rentas_no_afectas = rentas_no_afectas;
    }

    public String getTotal_ingresos_otras_categorias_de_renta() {
        return total_ingresos_otras_categorias_de_renta;
    }

    public void setTotal_ingresos_otras_categorias_de_renta(String total_ingresos_otras_categorias_de_renta) {
        this.total_ingresos_otras_categorias_de_renta = total_ingresos_otras_categorias_de_renta;
    }

    public String getIngresos_por_negociacion_de_bienes_derechos_y_o_derechos_que_no_sean_del_giro_habitual() {
        return ingresos_por_negociacion_de_bienes_derechos_y_o_derechos_que_no_sean_del_giro_habitual;
    }

    public void setIngresos_por_negociacion_de_bienes_derechos_y_o_derechos_que_no_sean_del_giro_habitual(String ingresos_por_negociacion_de_bienes_derechos_y_o_derechos_que_no_sean_del_giro_habitual) {
        this.ingresos_por_negociacion_de_bienes_derechos_y_o_derechos_que_no_sean_del_giro_habitual = ingresos_por_negociacion_de_bienes_derechos_y_o_derechos_que_no_sean_del_giro_habitual;
    }

    public String getTotal_ingresos_brutos() {
        return total_ingresos_brutos;
    }

    public void setTotal_ingresos_brutos(String total_ingresos_brutos) {
        this.total_ingresos_brutos = total_ingresos_brutos;
    }

    public String getIngresos_por_resarcimientos_provenientes_de_contratos_de_seguro() {
        return ingresos_por_resarcimientos_provenientes_de_contratos_de_seguro;
    }

    public void setIngresos_por_resarcimientos_provenientes_de_contratos_de_seguro(String ingresos_por_resarcimientos_provenientes_de_contratos_de_seguro) {
        this.ingresos_por_resarcimientos_provenientes_de_contratos_de_seguro = ingresos_por_resarcimientos_provenientes_de_contratos_de_seguro;
    }

    public String getIngresos_por_resarcimientos_provenientes_de_contratos_de_reaseguro() {
        return ingresos_por_resarcimientos_provenientes_de_contratos_de_reaseguro;
    }

    public void setIngresos_por_resarcimientos_provenientes_de_contratos_de_reaseguro(String ingresos_por_resarcimientos_provenientes_de_contratos_de_reaseguro) {
        this.ingresos_por_resarcimientos_provenientes_de_contratos_de_reaseguro = ingresos_por_resarcimientos_provenientes_de_contratos_de_reaseguro;
    }

    public String getIngresos_por_resarcimientos_provenientes_de_contratos_de_reafianzamiento() {
        return ingresos_por_resarcimientos_provenientes_de_contratos_de_reafianzamiento;
    }

    public void setIngresos_por_resarcimientos_provenientes_de_contratos_de_reafianzamiento(String ingresos_por_resarcimientos_provenientes_de_contratos_de_reafianzamiento) {
        this.ingresos_por_resarcimientos_provenientes_de_contratos_de_reafianzamiento = ingresos_por_resarcimientos_provenientes_de_contratos_de_reafianzamiento;
    }

    public String getPrimas_cedidas_de_seguro_y_reafianzamiento() {
        return primas_cedidas_de_seguro_y_reafianzamiento;
    }

    public void setPrimas_cedidas_de_seguro_y_reafianzamiento(String primas_cedidas_de_seguro_y_reafianzamiento) {
        this.primas_cedidas_de_seguro_y_reafianzamiento = primas_cedidas_de_seguro_y_reafianzamiento;
    }

  

    public String getIngresos_brutos_menos_exclusiones_segun_sat() {
        return ingresos_brutos_menos_exclusiones_segun_sat;
    }

    public void setIngresos_brutos_menos_exclusiones_segun_sat(String ingresos_brutos_menos_exclusiones_segun_sat) {
        this.ingresos_brutos_menos_exclusiones_segun_sat = ingresos_brutos_menos_exclusiones_segun_sat;
    }

    public String getIngresos_brutos_menos_exclusiones_segun_contribuyente() {
        return ingresos_brutos_menos_exclusiones_segun_contribuyente;
    }

    public void setIngresos_brutos_menos_exclusiones_segun_contribuyente(String ingresos_brutos_menos_exclusiones_segun_contribuyente) {
        this.ingresos_brutos_menos_exclusiones_segun_contribuyente = ingresos_brutos_menos_exclusiones_segun_contribuyente;
    }

    public String getDiferencia_en_ingresos_brutos_menos_exclusiones() {
        return diferencia_en_ingresos_brutos_menos_exclusiones;
    }

    public void setDiferencia_en_ingresos_brutos_menos_exclusiones(String diferencia_en_ingresos_brutos_menos_exclusiones) {
        this.diferencia_en_ingresos_brutos_menos_exclusiones = diferencia_en_ingresos_brutos_menos_exclusiones;
    }

    public String getBase_imponible_segun_ingresos_brutos() {
        return base_imponible_segun_ingresos_brutos;
    }

    public void setBase_imponible_segun_ingresos_brutos(String base_imponible_segun_ingresos_brutos) {
        this.base_imponible_segun_ingresos_brutos = base_imponible_segun_ingresos_brutos;
    }

    public String getImpuesto_segun_ingresos_brutos() {
        return impuesto_segun_ingresos_brutos;
    }

    public void setImpuesto_segun_ingresos_brutos(String impuesto_segun_ingresos_brutos) {
        this.impuesto_segun_ingresos_brutos = impuesto_segun_ingresos_brutos;
    }

    public String getImpuesto_segun_activo_neto() {
        return impuesto_segun_activo_neto;
    }

    public void setImpuesto_segun_activo_neto(String impuesto_segun_activo_neto) {
        this.impuesto_segun_activo_neto = impuesto_segun_activo_neto;
    }

    public String getImpuesto_determinado() {
        return impuesto_determinado;
    }

    public void setImpuesto_determinado(String impuesto_determinado) {
        this.impuesto_determinado = impuesto_determinado;
    }

    public String getImpuesto_determinado_segun_contribuyente() {
        return impuesto_determinado_segun_contribuyente;
    }

    public void setImpuesto_determinado_segun_contribuyente(String impuesto_determinado_segun_contribuyente) {
        this.impuesto_determinado_segun_contribuyente = impuesto_determinado_segun_contribuyente;
    }

    public String getIusi() {
        return iusi;
    }

    public void setIusi(String iusi) {
        this.iusi = iusi;
    }

    public String getPotencial_de_impuesto_de_solidaridad() {
        return potencial_de_impuesto_de_solidaridad;
    }

    public void setPotencial_de_impuesto_de_solidaridad(String potencial_de_impuesto_de_solidaridad) {
        this.potencial_de_impuesto_de_solidaridad = potencial_de_impuesto_de_solidaridad;
    }

    public String getSaldo_no_acreditado() {
        return saldo_no_acreditado;
    }

    public void setSaldo_no_acreditado(String saldo_no_acreditado) {
        this.saldo_no_acreditado = saldo_no_acreditado;
    }

    public String getValor_de_isr_a_acreditar_en_este_periodo() {
        return valor_de_isr_a_acreditar_en_este_periodo;
    }

    public void setValor_de_isr_a_acreditar_en_este_periodo(String valor_de_isr_a_acreditar_en_este_periodo) {
        this.valor_de_isr_a_acreditar_en_este_periodo = valor_de_isr_a_acreditar_en_este_periodo;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public String getImpuesto_sobre_la_renta() {
        return impuesto_sobre_la_renta;
    }

    public void setImpuesto_sobre_la_renta(String impuesto_sobre_la_renta) {
        this.impuesto_sobre_la_renta = impuesto_sobre_la_renta;
    }

    public String getPotencial_impuesto_de_solidaridad_4to_trimestre() {
        return potencial_impuesto_de_solidaridad_4to_trimestre;
    }

    public void setPotencial_impuesto_de_solidaridad_4to_trimestre(String potencial_impuesto_de_solidaridad_4to_trimestre) {
        this.potencial_impuesto_de_solidaridad_4to_trimestre = potencial_impuesto_de_solidaridad_4to_trimestre;
    }

    public String getPotencial_de_recaudo() {
        return potencial_de_recaudo;
    }

    public void setPotencial_de_recaudo(String potencial_de_recaudo) {
        this.potencial_de_recaudo = potencial_de_recaudo;
    }
    
    
            
    
    
  

    
}
