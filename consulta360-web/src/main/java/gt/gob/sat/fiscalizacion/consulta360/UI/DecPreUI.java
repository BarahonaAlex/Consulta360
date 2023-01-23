/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this temp4late file, choose Tools | Templates
 * and open the temp4late in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.DeclaracionesPresentadasDto;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author cgsamayo
 */
@Controller
@Scope("view")
public class DecPreUI extends AbstractUI implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(VerificacionesEnCampoUI.class);
    private String pnit = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");

    private ArrayList<ArrayList<String>> listaDatos = new ArrayList<>();
    private List<DeclaracionesPresentadasDto> descripciones;

    /*variables de los filtros principales*/
    private String clase;
    private String tipo;
    /*Iva General*/
    private String ig_anio_mes_ini;
    private String ig_anio_mes_fin;
    /*Iva Pequeño Contribuyente*/
    private String ip_anio_mes_ini;
    private String ip_anio_mes_fin;
    /*Isr Anual*/
    private String ia_anio_ini;
    private String ia_anio_fin;
    /*ISO*/
    private String iso_trimestre_ini;
    private String iso_trimestre_fin;
    private String iso_anio;

    /*Iva General Especial*/
    private String ig_anio_mes_ini_pr;
    private String ig_anio_mes_fin_pr;
    private String ig_anio_mes_ini_sr;
    private String ig_anio_mes_fin_sr;
    /*Iva Pequeño Contribuyente Especial*/
    private String ip_anio_mes_ini_pr;
    private String ip_anio_mes_fin_pr;
    private String ip_anio_mes_ini_sr;
    private String ip_anio_mes_fin_sr;
    /*Isr Anual Especial*/
    private String ia_anio_ini_pr;
    private String ia_anio_fin_pr;
    private String ia_anio_ini_sr;
    private String ia_anio_fin_sr;
    /*ISO Especial*/
    private String iso_trimestre_ini_pr;
    private String iso_trimestre_fin_pr;
    private String iso_anio_pr;
    private String iso_trimestre_ini_sr;
    private String iso_trimestre_fin_sr;
    private String iso_anio_sr;

    @PostConstruct
    public void inicializar() {

    }

    public void generarReporte() {
        if (!listaDatos.isEmpty()) {
            listaDatos.clear();
        }
        if (descripciones != null) {
            descripciones.clear();
        }

        switch (tipo) {
            case "1"://NORMAL
                switch (clase) {
                    case "1"://IVA GRAL.
                        if (check_iva_gral_normal() == false) {
                            break;
                        }
                        getRepIvaGral("N");
                        break;
                    case "2"://PEQUEÑO CONT.
                        if (check_iva_pcont_normal() == false) {
                            break;
                        }
                        getRepIvaPeqCon("N");
                        break;
                    case "3"://ISR
                        if (check_isr_anual_normal() == false) {
                            break;
                        }
                        getRepIsrAnual("N");
                        break;
                    case "4"://ISO
                        if (check_iso_normal() == false) {
                            break;
                        }
                        getRepIso("N");
                        break;
                }
                break;

            case "2"://ESPECIAL
                switch (clase) {
                    case "1"://IVA GRAL.
                        if (check_iva_gral_especial() == false) {
                            break;
                        }
                        getRepIvaGral("E");
                        break;
                    case "2"://PEQUEÑO CONT.
                        if (check_iva_pcont_especial() == false) {
                            break;
                        }
                        getRepIvaPeqCon("E");
                        break;
                    case "3"://ISR
                        if (check_isr_anual_especial() == false) {
                            break;
                        }
                        getRepIsrAnual("E");
                        break;
                    case "4"://ISO
                        if (check_iso_especial() == false) {
                            break;
                        }
                        getRepIso("E");
                        break;
                }
                break;
        }
    }

    public void getRepIvaGral(String tipo) {
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> ivagral = new ArrayList<>();

        descripciones = this.generalSrvImpl.obtenerDescripcionesFormulario(new BigDecimal(1));

        for (int i = 0; i < descripciones.size(); i++) {
            ivagral.add(descripciones.get(i).getDescripcion());
        }

        /*SE LLENA LA PRIMER LINEA CONTENIENDO LA DESCRIPCION DE LOS CAMPOS*/
        listaDatos.add(new ArrayList(ivagral));

        String SQL = new String();

        String columnas = new String();

        columnas = " periodo_fiscal, "
                + " format(cast(round(ventas_exentas,2) as decimal(20,2)),'c','es-gt')                 as 'ventas_exentas',"
                + " format(cast(round(ventas_medicamentos,2) as decimal(20,2)),'c','es-gt')            as 'ventas_medicamentos',"
                + " format(cast(round(ventas_no_afecta,2) as decimal(20,2)),'c','es-gt')               as 'ventas_no_afecta',"
                + " format(cast(round(ventas_vehiculos,2) as decimal(20,2)),'c','es-gt')               as 'ventas_vehiculos',"
                + " format(cast(round(ventas_vehiculos_act,2) as decimal(20,2)),'c','es-gt')           as 'ventas_vehiculos_act',"
                + " format(cast(round(ventas_vehiculos_act_deb,2) as decimal(20,2)),'c','es-gt')       as 'ventas_vehiculos_act_deb',"
                + " format(cast(round(ventas,2) as decimal(20,2)),'c','es-gt')          	       as 'ventas',"
                + " format(cast(round(ventas_gravadas_deb,2) as decimal(20,2)),'c','es-gt')            as 'ventas_gravadas_deb',"
                + " format(cast(round(serv_prestados,2) as decimal(20,2)),'c','es-gt')                 as 'serv_prestados',"
                + " format(cast(round(serv_prestados_deb,2) as decimal(20,2)),'c','es-gt')             as 'serv_prestados_deb',"
                + " format(cast(round(suma_base_deb,2) as decimal(20,2)),'c','es-gt')                  as 'suma_base_deb',"
                + " format(cast(round(suma_deb_local,2) as decimal(20,2)),'c','es-gt')                 as 'suma_deb_local',"
                + " format(cast(round(expo_ca,2) as decimal(20,2)),'c','es-gt')          	       as 'expo_ca',"
                + " format(cast(round(expo_mundo,2) as decimal(20,2)),'c','es-gt')          	       as 'expo_mundo',"
                + " format(cast(round(ventas_medicamentos_expo,2) as decimal(20,2)),'c','es-gt')       as 'ventas_medicamentos_expo',"
                + " format(cast(round(ventas_vehiculos_expo,2) as decimal(20,2)),'c','es-gt')          as 'ventas_vehiculos_expo',"
                + " format(cast(round(ventas_vehiculos_act_expo,2) as decimal(20,2)),'c','es-gt')      as 'ventas_vehiculos_act_expo',"
                + " format(cast(round(suma_base_deb_expo,2) as decimal(20,2)),'c','es-gt')             as 'suma_base_deb_expo',"
                + " format(cast(round(cred_recibido_expor,2) as decimal(20,2)),'c','es-gt')            as 'cred_recibido_expor',"
                + " format(cast(round(fact_esp_monto_deb_expor,2) as decimal(20,2)),'c','es-gt')       as 'fact_esp_monto_deb_expor',"
                + " format(cast(round(suma_deb_expo,2) as decimal(20,2)),'c','es-gt')                  as 'suma_deb_expo',"
                + " format(cast(round(compras_medicamentos,2) as decimal(20,2)),'c','es-gt')           as 'compras_medicamentos',"
                + " format(cast(round(compras_peq_contrib,2) as decimal(20,2)),'c','es-gt')            as 'compras_peq_contrib',"
                + " format(cast(round(compras_no_afecta,2) as decimal(20,2)),'c','es-gt')              as 'compras_no_afecta',"
                + " format(cast(round(compras_vehiculos_cred,2) as decimal(20,2)),'c','es-gt')         as 'compras_vehiculos_cred',"
                + " format(cast(round(compras_vehiculos_act,2) as decimal(20,2)),'c','es-gt')          as 'compras_vehiculos_act',"
                + " format(cast(round(compras_vehiculos_act_cred,2) as decimal(20,2)),'c','es-gt')     as 'compras_vehiculos_act_cred',"
                + " format(cast(round(compras_combust,2) as decimal(20,2)),'c','es-gt')                as 'compras_combust',"
                + " format(cast(round(compras_combust_cred,2) as decimal(20,2)),'c','es-gt')           as 'compras_combust_cred',"
                + " format(cast(round(compras,2) as decimal(20,2)),'c','es-gt')          	       as 'compras',"
                + " format(cast(round(tot_compras_cred,2) as decimal(20,2)),'c','es-gt')               as 'tot_compras_cred',"
                + " format(cast(round(serv_adqui,2) as decimal(20,2)),'c','es-gt')          	       as 'serv_adqui',"
                + " format(cast(round(tot_serv_adqui_cred,2) as decimal(20,2)),'c','es-gt')            as 'tot_serv_adqui_cred',"
                + " format(cast(round(impo_ca,2) as decimal(20,2)),'c','es-gt')          	       as 'impo_ca',"
                + " format(cast(round(impo_ca_cred,2) as decimal(20,2)),'c','es-gt')          	       as 'impo_ca_cred',"
                + " format(cast(round(impo_mundo,2) as decimal(20,2)),'c','es-gt')          	       as 'impo_mundo',"
                + " format(cast(round(tot_impo_cred,2) as decimal(20,2)),'c','es-gt')          	       as 'tot_impo_cred',"
                + " format(cast(round(compras_activ,2) as decimal(20,2)),'c','es-gt')          	       as 'compras_activ',"
                + " format(cast(round(compras_activ_cred,2) as decimal(20,2)),'c','es-gt')             as 'compras_activ_cred',"
                + " format(cast(round(impo_activ,2) as decimal(20,2)),'c','es-gt')          	       as 'impo_activ',"
                + " format(cast(round(impo_activ_cred,2) as decimal(20,2)),'c','es-gt')                as 'impo_activ_cred',"
                + " format(cast(round(val_exeniva_cred,2) as decimal(20,2)),'c','es-gt')               as 'val_exeniva_cred',"
                + " format(cast(round(remanente_cred_per_ant_local,2) as decimal(20,2)),'c','es-gt')   as 'remanente_cred_per_ant_local',"
                + " format(cast(round(suma_base_cred_local,2) as decimal(20,2)),'c','es-gt')           as 'suma_base_cred_local',"
                + " format(cast(round(suma_cred_local,2) as decimal(20,2)),'c','es-gt')                as 'suma_cred_local',"
                + " format(cast(round(compras_peq_contrib_expo,2) as decimal(20,2)),'c','es-gt')       as 'compras_peq_contrib_expo',"
                + " format(cast(round(compras_combust_expo,2) as decimal(20,2)),'c','es-gt')           as 'compras_combust_expo',"
                + " format(cast(round(compras_combust_expo_cred,2) as decimal(20,2)),'c','es-gt')      as 'compras_combust_expo_cred',"
                + " format(cast(round(compras_expo,2) as decimal(20,2)),'c','es-gt')          	       as 'compras_expo',"
                + " format(cast(round(compras_expo_cred,2) as decimal(20,2)),'c','es-gt')              as 'compras_expo_cred',"
                + " format(cast(round(serv_adqui_expo,2) as decimal(20,2)),'c','es-gt')                as 'serv_adqui_expo',"
                + " format(cast(round(serv_adqui_expo_cred,2) as decimal(20,2)),'c','es-gt')           as 'serv_adqui_expo_cred',"
                + " format(cast(round(impo_ca_expo,2) as decimal(20,2)),'c','es-gt')          	       as 'impo_ca_expo',"
                + " format(cast(round(impo_ca_expo_cred,2) as decimal(20,2)),'c','es-gt')              as 'impo_ca_expo_cred',"
                + " format(cast(round(impo_mundo_expo,2) as decimal(20,2)),'c','es-gt')                as 'impo_mundo_expo',"
                + " format(cast(round(impo_mundo_expo_cred,2) as decimal(20,2)),'c','es-gt')           as 'impo_mundo_expo_cred',"
                + " format(cast(round(compras_activ_expo,2) as decimal(20,2)),'c','es-gt')             as 'compras_activ_expo',"
                + " format(cast(round(compras_activ_expo_cred,2) as decimal(20,2)),'c','es-gt')        as 'compras_activ_expo_cred',"
                + " format(cast(round(impo_activ_expo,2) as decimal(20,2)),'c','es-gt')                as 'impo_activ_expo',"
                + " format(cast(round(impo_activ_expo_cred,2) as decimal(20,2)),'c','es-gt')           as 'impo_activ_expo_cred',"
                + " format(cast(round(remanente_cred_per_ant,2) as decimal(20,2)),'c','es-gt')         as 'remanente_cred_per_ant',"
                + " format(cast(round(cred_fact_esp_exp,2) as decimal(20,2)),'c','es-gt')              as 'cred_fact_esp_exp',"
                + " format(cast(round(reteniva_x_expor,2) as decimal(20,2)),'c','es-gt')               as 'reteniva_x_expor',"
                + " format(cast(round(suma_base_cred_expo,2) as decimal(20,2)),'c','es-gt')            as 'suma_base_cred_expo',"
                + " format(cast(round(suma_cred_expo,2) as decimal(20,2)),'c','es-gt')                 as 'suma_cred_expo',"
                + " format(cast(round(suma_cred,2) as decimal(20,2)),'c','es-gt')          	       as 'suma_cred',"
                + " format(cast(round(cred_local_sig_per,2) as decimal(20,2)),'c','es-gt')             as 'cred_local_sig_per',"
                + " format(cast(round(cred_fisc_expo,2) as decimal(20,2)),'c','es-gt')                 as 'cred_fisc_expo',"
                + " format(cast(round(imp_pagar_deb_cred_local,2) as decimal(20,2)),'c','es-gt')       as 'imp_pagar_deb_cred_local',"
                + " format(cast(round(imp_pagar_deb_cred_expo,2) as decimal(20,2)),'c','es-gt')        as 'imp_pagar_deb_cred_expo',"
                + " format(cast(round(cred_per_sig,2) as decimal(20,2)),'c','es-gt')          	       as 'cred_per_sig',"
                + " format(cast(round(remanente_reteniva_per_ant,2) as decimal(20,2)),'c','es-gt')     as 'remanente_reteniva_per_ant',"
                + " format(cast(round(num_res_cuenta_bancaria,2) as decimal(20,2)),'c','es-gt')        as 'num_res_cuenta_bancaria',"
                + " format(cast(round(val_res_cuenta_bancaria,2) as decimal(20,2)),'c','es-gt')        as 'val_res_cuenta_bancaria',"
                + " format(cast(round(remanente_reteniva_per_act,2) as decimal(20,2)),'c','es-gt')     as 'remanente_reteniva_per_act',"
                + " format(cast(round(monto_reteniva_recibidas,2) as decimal(20,2)),'c','es-gt')       as 'monto_reteniva_recibidas',"
                + " format(cast(round(saldo_reteniva_sig_per,2) as decimal(20,2)),'c','es-gt')         as 'saldo_reteniva_sig_per',"
                + " format(cast(round(saldo_imp_local,2) as decimal(20,2)),'c','es-gt')                as 'saldo_imp_local',"
                + " format(cast(round(num_resolucion_compensa,2) as decimal(20,2)),'c','es-gt')        as 'num_resolucion_compensa',"
                + " format(cast(round(saldo_no_compensado,2) as decimal(20,2)),'c','es-gt')            as 'saldo_no_compensado',"
                + " format(cast(round(monto_resolucion_compensa,2) as decimal(20,2)),'c','es-gt')      as 'monto_resolucion_compensa',"
                + " format(cast(round(imp_a_pagar_deb_cred,2) as decimal(20,2)),'c','es-gt')           as 'imp_a_pagar_deb_cred',"
                + " format(cast(round(imp_a_pagar_deb_cred,2) as decimal(20,2)),'c','es-gt')           as 'imp_a_pagar_deb_cred',"
                + " format(cast(round(razon_deb_cred,2) as decimal(20,2)),'c','es-gt')                 as 'razon_deb_cred',"
                + " format(cast(round(fact_cant_emitidas,2) as decimal(20,2)),'c','es-gt')             as 'fact_cant_emitidas',"
                + " format(cast(round(fact_recibidas_cant,2) as decimal(20,2)),'c','es-gt')            as 'fact_recibidas_cant',"
                + " format(cast(round(cant_exeniva_emitidas,2) as decimal(20,2)),'c','es-gt')          as 'cant_exeniva_emitidas',"
                + " format(cast(round(exeniva_cant_recibidas,2) as decimal(20,2)),'c','es-gt')         as 'exeniva_cant_recibidas',"
                + " format(cast(round(cant_cai_emitidas,2) as decimal(20,2)),'c','es-gt')              as 'cant_cai_emitidas',"
                + " format(cast(round(cant_cai_recibidas,2) as decimal(20,2)),'c','es-gt')             as 'cant_cai_recibidas',"
                + " format(cast(round(cant_reteniva_emitidas,2) as decimal(20,2)),'c','es-gt')         as 'cant_reteniva_emitidas',"
                + " format(cast(round(cant_reteniva_recibidas,2) as decimal(20,2)),'c','es-gt')        as 'cant_reteniva_recibidas',"
                + " format(cast(round(fact_esp_cant,2) as decimal(20,2)),'c','es-gt')          	       as 'fact_esp_cant',"
                + " format(cast(round(fact_esp_cant_recibidas,2) as decimal(20,2)),'c','es-gt')        as 'fact_esp_cant_recibidas',"
                + " cast(round(num_decla_rect,2) as decimal(20,0))                                     as 'num_decla_rect',"
                + " format(cast(round(val_rect,2) as decimal(20,2)),'c','es-gt')          	       as 'val_rect',"
                + " format(cast(round(imp_a_pagar_rect,2) as decimal(20,2)),'c','es-gt')               as 'imp_a_pagar_rect',"
                + " format(cast(round(saldo_a_favor_contrib,2) as decimal(20,2)),'c','es-gt')          as 'saldo_a_favor_contrib',"
                + " fecha_maxima_pago, "
                + " cuando_pagara_1, "
                + " cuando_pagara_2, "
                + " format(cast(round(multa_formal,2) as decimal(20,2)),'c','es-gt')          	       as 'multa_formal',"
                + " format(cast(round(multa_omision,2) as decimal(20,2)),'c','es-gt')          	       as 'multa_omision',"
                + " format(cast(round(multa_rect,2) as decimal(20,2)),'c','es-gt')          	       as 'multa_rect',"
                + " format(cast(round(intereses,2) as decimal(20,2)),'c','es-gt')          	       as 'intereses',"
                + " format(cast(round(mora,2) as decimal(20,2)),'c','es-gt')          		       as 'mora',"
                + " format(cast(round(acce_a_pagar,2) as decimal(20,2)),'c','es-gt')          	       as 'acce_a_pagar',"
                + " format(cast(round(tot_a_pagar,2) as decimal(20,2)),'c','es-gt')          	       as 'tot_a_pagar',"
                + " nit_contador, "
                + " format(cast(round(cod_res_fact_esp,2) as decimal(20,2)),'c','es-gt')               as 'cod_res_fact_esp',"
                + " cod_res_fact_elect, "
                + " format(cast(round(saldo_imp,2) as decimal(20,2)),'c','es-gt')          	       as 'saldo_imp',"
                + " format(cast(round(transfe_fyduca,2) as decimal(20,2)),'c','es-gt')                 as 'transfe_fyduca',"
                + " format(cast(round(adqui_fyduca,2) as decimal(20,2)),'c','es-gt')                   as 'adqui_fyduca',"
                + " format(cast(round(adqui_fyduca_cred,2) as decimal(20,2)),'c','es-gt')              as 'adqui_fyduca_cred',"
                + " format(cast(round(adqui_fyduca_expo,2) as decimal(20,2)),'c','es-gt')              as 'adqui_fyduca_expo',"
                + " format(cast(round(adqui_fyduca_expo_cred,2) as decimal(20,2)),'c','es-gt')         as 'adqui_fyduca_expo_cred',"
                + " format(cast(round(fyducas_emitidas,2) as decimal(20,2)),'c','es-gt')               as 'fyducas_emitidas',"
                + " format(cast(round(fyducas_recibidas,2) as decimal(20,2)),'c','es-gt')              as 'fyducas_recibidas',"
                + " format(cast(round(cant_nc_emitidas,2) as decimal(20,2)) ,'c','es-gt')              as 'cant_nc_emitidas',"
                + " format(cast(round(cant_nc_recibidas,2) as decimal(20,2)),'c','es-gt')              as 'cant_nc_recibidas',"
                + " format(cast(round(cant_nd_emitidas,2) as decimal(20,2)),'c','es-gt')               as 'cant_nd_emitidas',"
                + " format(cast(round(cant_nd_recibidas,2) as decimal(20,2)),'c','es-gt')              as 'cant_nd_recibidas',"
                + " format(cast(round(val_nc_emitidas,2) as decimal(20,2)),'c','es-gt')                as 'val_nc_emitidas',"
                + " format(cast(round(val_nc_recibidas,2) as decimal(20,2)),'c','es-gt')               as 'val_nc_recibidas',"
                + " format(cast(round(val_nd_emitidas,2) as decimal(20,2)),'c','es-gt')                as 'val_nd_emitidas',"
                + " format(cast(round(val_nd_recibidas,2) as decimal(20,2)),'c','es-gt')               as 'val_nd_recibidas'";

        if ("N".equals(tipo)) {
            SQL = "SELECT  " + columnas
                    + " From  sat_declaraciones.o_iva_homologada_ag "
                    + " WHERE nit = '" + pnit + "' "
                    + " AND  periodo_fiscal between '" + ig_anio_mes_ini + "' AND '" + ig_anio_mes_fin + "' "
                    + " AND rank = 1 "
                    + " ORDER BY periodo_fiscal ";
        } else if ("E".equals(tipo)) {
            SQL = "SELECT  " + columnas
                    + " From  sat_declaraciones.o_iva_homologada_ag "
                    + " WHERE nit = '" + pnit + "' "
                    + " AND  periodo_fiscal between '" + ig_anio_mes_ini_pr + "' AND '" + ig_anio_mes_fin_pr + "' "
                    + " AND rank = 1 "
                    + " UNION "
                    + " SELECT  " + columnas
                    + " From  sat_declaraciones.o_iva_homologada_ag "
                    + " WHERE nit = '" + pnit + "' "
                    + " AND  periodo_fiscal between '" + ig_anio_mes_ini_sr + "' AND '" + ig_anio_mes_fin_sr + "' "
                    + " AND rank = 1 "
                    + " ORDER BY periodo_fiscal ";
        }

        Statement statement;
        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                temp.add(rs.getString("periodo_fiscal"));
                temp.add(rs.getString("ventas_exentas"));
                temp.add(rs.getString("ventas_medicamentos"));
                temp.add(rs.getString("ventas_no_afecta"));
                temp.add(rs.getString("ventas_vehiculos"));
                temp.add(rs.getString("ventas_vehiculos_act"));
                temp.add(rs.getString("ventas_vehiculos_act_deb"));
                temp.add(rs.getString("ventas"));
                temp.add(rs.getString("ventas_gravadas_deb"));
                temp.add(rs.getString("serv_prestados"));
                temp.add(rs.getString("serv_prestados_deb"));
                temp.add(rs.getString("suma_base_deb"));
                temp.add(rs.getString("suma_deb_local"));
                temp.add(rs.getString("expo_ca"));
                temp.add(rs.getString("expo_mundo"));
                temp.add(rs.getString("ventas_medicamentos_expo"));
                temp.add(rs.getString("ventas_vehiculos_expo"));
                temp.add(rs.getString("ventas_vehiculos_act_expo"));
                temp.add(rs.getString("suma_base_deb_expo"));
                temp.add(rs.getString("cred_recibido_expor"));
                temp.add(rs.getString("fact_esp_monto_deb_expor"));
                temp.add(rs.getString("suma_deb_expo"));
                temp.add(rs.getString("compras_medicamentos"));
                temp.add(rs.getString("compras_peq_contrib"));
                temp.add(rs.getString("compras_no_afecta"));
                temp.add(rs.getString("compras_vehiculos_cred"));
                temp.add(rs.getString("compras_vehiculos_act"));
                temp.add(rs.getString("compras_vehiculos_act_cred"));
                temp.add(rs.getString("compras_combust"));
                temp.add(rs.getString("compras_combust_cred"));
                temp.add(rs.getString("compras"));
                temp.add(rs.getString("tot_compras_cred"));
                temp.add(rs.getString("serv_adqui"));
                temp.add(rs.getString("tot_serv_adqui_cred"));
                temp.add(rs.getString("impo_ca"));
                temp.add(rs.getString("impo_ca_cred"));
                temp.add(rs.getString("impo_mundo"));
                temp.add(rs.getString("tot_impo_cred"));
                temp.add(rs.getString("compras_activ"));
                temp.add(rs.getString("compras_activ_cred"));
                temp.add(rs.getString("impo_activ"));
                temp.add(rs.getString("impo_activ_cred"));
                temp.add(rs.getString("val_exeniva_cred"));
                temp.add(rs.getString("remanente_cred_per_ant_local"));
                temp.add(rs.getString("suma_base_cred_local"));
                temp.add(rs.getString("suma_cred_local"));
                temp.add(rs.getString("compras_peq_contrib_expo"));
                temp.add(rs.getString("compras_combust_expo"));
                temp.add(rs.getString("compras_combust_expo_cred"));
                temp.add(rs.getString("compras_expo"));
                temp.add(rs.getString("compras_expo_cred"));
                temp.add(rs.getString("serv_adqui_expo"));
                temp.add(rs.getString("serv_adqui_expo_cred"));
                temp.add(rs.getString("impo_ca_expo"));
                temp.add(rs.getString("impo_ca_expo_cred"));
                temp.add(rs.getString("impo_mundo_expo"));
                temp.add(rs.getString("impo_mundo_expo_cred"));
                temp.add(rs.getString("compras_activ_expo"));
                temp.add(rs.getString("compras_activ_expo_cred"));
                temp.add(rs.getString("impo_activ_expo"));
                temp.add(rs.getString("impo_activ_expo_cred"));
                temp.add(rs.getString("remanente_cred_per_ant"));
                temp.add(rs.getString("cred_fact_esp_exp"));
                temp.add(rs.getString("reteniva_x_expor"));
                temp.add(rs.getString("suma_base_cred_expo"));
                temp.add(rs.getString("suma_cred_expo"));
                temp.add(rs.getString("suma_cred"));
                temp.add(rs.getString("cred_local_sig_per"));
                temp.add(rs.getString("cred_fisc_expo"));
                temp.add(rs.getString("imp_pagar_deb_cred_local"));
                temp.add(rs.getString("imp_pagar_deb_cred_expo"));
                temp.add(rs.getString("cred_per_sig"));
                temp.add(rs.getString("remanente_reteniva_per_ant"));
                temp.add(rs.getString("num_res_cuenta_bancaria"));
                temp.add(rs.getString("val_res_cuenta_bancaria"));
                temp.add(rs.getString("remanente_reteniva_per_act"));
                temp.add(rs.getString("monto_reteniva_recibidas"));
                temp.add(rs.getString("saldo_reteniva_sig_per"));
                temp.add(rs.getString("saldo_imp_local"));
                temp.add(rs.getString("num_resolucion_compensa"));
                temp.add(rs.getString("saldo_no_compensado"));
                temp.add(rs.getString("monto_resolucion_compensa"));
                temp.add(rs.getString("imp_a_pagar_deb_cred"));
                temp.add(rs.getString("imp_a_pagar_deb_cred"));
                temp.add(rs.getString("razon_deb_cred"));
                temp.add(rs.getString("fact_cant_emitidas"));
                temp.add(rs.getString("fact_recibidas_cant"));
                temp.add(rs.getString("cant_exeniva_emitidas"));
                temp.add(rs.getString("exeniva_cant_recibidas"));
                temp.add(rs.getString("cant_cai_emitidas"));
                temp.add(rs.getString("cant_cai_recibidas"));
                temp.add(rs.getString("cant_reteniva_emitidas"));
                temp.add(rs.getString("cant_reteniva_recibidas"));
                temp.add(rs.getString("fact_esp_cant"));
                temp.add(rs.getString("fact_esp_cant_recibidas"));
                temp.add(rs.getString("num_decla_rect"));
                temp.add(rs.getString("val_rect"));
                temp.add(rs.getString("imp_a_pagar_rect"));
                temp.add(rs.getString("saldo_a_favor_contrib"));
                temp.add(rs.getString("fecha_maxima_pago"));
                temp.add(rs.getString("cuando_pagara_1"));
                temp.add(rs.getString("cuando_pagara_2"));
                temp.add(rs.getString("multa_formal"));
                temp.add(rs.getString("multa_omision"));
                temp.add(rs.getString("multa_rect"));
                temp.add(rs.getString("intereses"));
                temp.add(rs.getString("mora"));
                temp.add(rs.getString("acce_a_pagar"));
                temp.add(rs.getString("tot_a_pagar"));
                temp.add(rs.getString("nit_contador"));
                temp.add(rs.getString("cod_res_fact_esp"));
                temp.add(rs.getString("cod_res_fact_elect"));
                temp.add(rs.getString("saldo_imp"));
                temp.add(rs.getString("transfe_fyduca"));
                temp.add(rs.getString("adqui_fyduca"));
                temp.add(rs.getString("adqui_fyduca_cred"));
                temp.add(rs.getString("adqui_fyduca_expo"));
                temp.add(rs.getString("adqui_fyduca_expo_cred"));
                temp.add(rs.getString("fyducas_emitidas"));
                temp.add(rs.getString("fyducas_recibidas"));
                temp.add(rs.getString("cant_nc_emitidas"));
                temp.add(rs.getString("cant_nc_recibidas"));
                temp.add(rs.getString("cant_nd_emitidas"));
                temp.add(rs.getString("cant_nd_recibidas"));
                temp.add(rs.getString("val_nc_emitidas"));
                temp.add(rs.getString("val_nc_recibidas"));
                temp.add(rs.getString("val_nd_emitidas"));
                temp.add(rs.getString("val_nd_recibidas"));

                listaDatos.add(new ArrayList(temp));
                temp.clear();

            }

            conn.close();
            statement.close();

        } catch (Exception ex) {
            listaDatos.clear();
            errorMsg("Tabla no encontrada");
            /*  errorMsg(ex.getMessage());*/
            LOG.error(ex.getMessage());
        }

        if (listaDatos.isEmpty() || listaDatos.size() == 1) {
            listaDatos.clear();
            warnMsg("No existen registros para el NIT consultado");
        } else {
            listaDatos = transponer(listaDatos);
        }

    } // Handle any errors that may have occurred. // Handle any errors that may have occurred.

    public void getRepIvaPeqCon(String tipo) {
        ArrayList<String> temp2 = new ArrayList<>();
        ArrayList<String> peqcon = new ArrayList<>();

        descripciones = this.generalSrvImpl.obtenerDescripcionesFormulario(new BigDecimal(2));

        for (int i = 0; i < descripciones.size(); i++) {
            peqcon.add(descripciones.get(i).getDescripcion());
        }

        /*SE LLENA LA PRIMER LINEA CONTENIENDO LA DESCRIPCION DE LOS CAMPOS*/
        listaDatos.add(new ArrayList(peqcon));

        String SQL = new String();
        String columnas = new String();

        columnas = "periodo_fiscal,  "
                + "format(cast(round(ventas_y_serv_prestados,2) as decimal(20,2)),'c','es-gt')      as 'ventas_y_serv_prestados', "
                + "format(cast(round(imp_determinado_fija,2) as decimal(20,2)),'c','es-gt')         as 'imp_determinado_fija',  "
                + "format(cast(round(remanente_reteniva_per_ant,2) as decimal(20,2)),'c','es-gt')   as 'remanente_reteniva_per_ant', "
                + "format(cast(round(monto_reteniva_recibidas,2) as decimal(20,2)),'c','es-gt')     as 'monto_reteniva_recibidas', "
                + "format(cast(round(saldo_reteniva_sig_per,2) as decimal(20,2)),'c','es-gt')       as 'saldo_reteniva_sig_per', "
                + "format(cast(round(imp_despues_reteniva_fija,2) as decimal(20,2)),'c','es-gt')    as 'imp_despues_reteniva_fija', "
                + "format(cast(round(num_decla_rect,2) as decimal(20,2)),'c','es-gt')               as 'num_decla_rect', "
                + "format(cast(round(val_decla_rect,2) as decimal(20,2)),'c','es-gt')               as 'val_decla_rect', "
                + "format(cast(round(imp_despues_rect,2) as decimal(20,2)),'c','es-gt')             as 'imp_despues_rect', "
                + "format(cast(round(imp_a_favor_contrib,2) as decimal(20,2)),'c','es-gt')          as 'imp_a_favor_contrib', "
                + "fecha_maxima_pago, "
                + "cuando_pagara_1, "
                + "cuando_pagara_2, "
                + "format(cast(round(multa_formal,2) as decimal(20,2)),'c','es-gt')    as 'multa_formal', "
                + "format(cast(round(multa_omision,2) as decimal(20,2)),'c','es-gt')   as 'multa_omision', "
                + "format(cast(round(multa_rect,2) as decimal(20,2)),'c','es-gt')      as 'multa_rect', "
                + "format(cast(round(intereses,2) as decimal(20,2)),'c','es-gt')       as 'intereses', "
                + "format(cast(round(mora,2) as decimal(20,2)),'c','es-gt')            as 'mora', "
                + "format(cast(round(acce_a_pagar,2) as decimal(20,2)),'c','es-gt')    as 'acce_a_pagar', "
                + "format(cast(round(tot_a_pagar,2) as decimal(20,2)),'c','es-gt')     as 'tot_a_pagar'";

        if ("N".equals(tipo)) {
            SQL = "SELECT  " + columnas
                    + " From  sat_declaraciones.o_iva_pq_homologada_ag "
                    + " WHERE nit = '" + pnit + "' "
                    + " AND  periodo_fiscal between '" + ip_anio_mes_ini + "' AND '" + ip_anio_mes_fin + "' "
                    + " AND rank = 1 "
                    + " ORDER BY periodo_fiscal ";
        } else if ("E".equals(tipo)) {
            SQL = "SELECT  " + columnas
                    + " From  sat_declaraciones.o_iva_pq_homologada_ag "
                    + " WHERE nit = '" + pnit + "' "
                    + " AND  periodo_fiscal between '" + ip_anio_mes_ini_pr + "' AND '" + ip_anio_mes_fin_pr + "' "
                    + " AND rank = 1 "
                    + " UNION "
                    + " SELECT  " + columnas
                    + " From  sat_declaraciones.o_iva_pq_homologada_ag "
                    + " WHERE nit = '" + pnit + "' "
                    + " AND  periodo_fiscal between '" + ip_anio_mes_ini_sr + "' AND '" + ip_anio_mes_fin_sr + "' "
                    + " AND rank = 1 "
                    + " ORDER BY periodo_fiscal ";
        }

        Statement statement;
        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                temp2.add(rs.getString("periodo_fiscal"));
                temp2.add(rs.getString("ventas_y_serv_prestados"));
                temp2.add(rs.getString("imp_determinado_fija"));
                temp2.add(rs.getString("remanente_reteniva_per_ant"));
                temp2.add(rs.getString("monto_reteniva_recibidas"));
                temp2.add(rs.getString("saldo_reteniva_sig_per"));
                temp2.add(rs.getString("imp_despues_reteniva_fija"));
                temp2.add(rs.getString("num_decla_rect"));
                temp2.add(rs.getString("val_decla_rect"));
                temp2.add(rs.getString("imp_despues_rect"));
                temp2.add(rs.getString("imp_a_favor_contrib"));
                temp2.add(rs.getString("fecha_maxima_pago"));
                temp2.add(rs.getString("cuando_pagara_1"));
                temp2.add(rs.getString("cuando_pagara_2"));
                temp2.add(rs.getString("multa_formal"));
                temp2.add(rs.getString("multa_omision"));
                temp2.add(rs.getString("multa_rect"));
                temp2.add(rs.getString("intereses"));
                temp2.add(rs.getString("mora"));
                temp2.add(rs.getString("acce_a_pagar"));
                temp2.add(rs.getString("tot_a_pagar"));

                listaDatos.add(new ArrayList(temp2));
                temp2.clear();

            }
            conn.close();
            statement.close();

        } catch (Exception ex) {
            listaDatos.clear();
            errorMsg("Tabla no encontrada");
            /* errorMsg(ex.getMessage());*/
            LOG.error(ex.getMessage());
        }

        if (listaDatos.isEmpty() || listaDatos.size() == 1) {
            listaDatos.clear();
            warnMsg("No existen registros para el NIT consultado");
        } else {
            listaDatos = transponer(listaDatos);
        }

    } // Handle any errors that may have occurred. // Handle any errors that may have occurred.

    public void getRepIsrAnual(String tipo) {
        ArrayList<String> temp3 = new ArrayList<>();
        ArrayList<String> isranual = new ArrayList<>();

        descripciones = this.generalSrvImpl.obtenerDescripcionesFormulario(new BigDecimal(3));

        for (int i = 0; i < descripciones.size(); i++) {
            isranual.add(descripciones.get(i).getDescripcion());
        }

        /*SE LLENA LA PRIMER LINEA CONTENIENDO LA DESCRIPCION DE LOS CAMPOS*/
        listaDatos.add(new ArrayList(isranual));

        String SQL = new String();

        String columnas = new String();
        columnas = " periodo_fiscal,"
                + " format(cast(round(reg_isr_declara,2) as decimal(20,2)),'c','es-gt')              as 'reg_isr_declara',"
                + " format(cast(round(acti_econo_prin,2) as decimal(20,2)),'c','es-gt')              as 'acti_econo_prin',"
                + " format(cast(round(acti_econo_secun,2) as decimal(20,2)),'c','es-gt')             as 'acti_econo_secun',"
                + " format(cast(round(opera_relacionadas_extran,2) as decimal(20,2)),'c','es-gt')    as 'opera_relacionadas_extran',"
                + " format(cast(round(efectivo,2) as decimal(20,2)),'c','es-gt')                     as 'efectivo',"
                + " format(cast(round(cta_x_cobrar,2) as decimal(20,2)),'c','es-gt')                 as 'cta_x_cobrar',"
                + " format(cast(round(cta_x_cobrar_otras,2) as decimal(20,2)),'c','es-gt')           as 'cta_x_cobrar_otras',"
                + " format(cast(round(inventario_final_activ,2) as decimal(20,2)),'c','es-gt')       as 'inventario_final_activ',"
                + " format(cast(round(cred_pend_reintegro,2) as decimal(20,2)),'c','es-gt')          as 'cred_pend_reintegro',"
                + " format(cast(round(inmuebles,2) as decimal(20,2)),'c','es-gt')                    as 'inmuebles',"
                + " format(cast(round(mobi_equipo,2) as decimal(20,2)),'c','es-gt')                 as 'mobi_equipo',"
                + " format(cast(round(maquinaria,2) as decimal(20,2)),'c','es-gt')                   as 'maquinaria',"
                + " format(cast(round(vehiculos,2) as decimal(20,2)),'c','es-gt')                    as 'vehiculos',"
                + " format(cast(round(equipo_computo,2) as decimal(20,2)),'c','es-gt')               as 'equipo_computo',"
                + " format(cast(round(otros_activ_depreciables,2) as decimal(20,2)),'c','es-gt')     as 'otros_activ_depreciables',"
                + " format(cast(round(activ_amorti,2) as decimal(20,2)),'c','es-gt')                 as 'activ_amorti',"
                + " format(cast(round(inver,2) as decimal(20,2)),'c','es-gt')                        as 'inver',"
                + " format(cast(round(deprecia_acum,2) as decimal(20,2)),'c','es-gt')                as 'deprecia_acum',"
                + " format(cast(round(amorti_acum,2) as decimal(20,2)),'c','es-gt')                  as 'amorti_acum',"
                + " format(cast(round(otros_activ,2) as decimal(20,2)),'c','es-gt')                  as 'otros_activ',"
                + " format(cast(round(cta_x_pagar,2) as decimal(20,2)),'c','es-gt')                  as 'cta_x_pagar',"
                + " format(cast(round(prestamos_banca_finan,2) as decimal(20,2)),'c','es-gt')        as 'prestamos_banca_finan',"
                + " format(cast(round(otros_pasivos,2) as decimal(20,2)),'c','es-gt')                as 'otros_pasivos',"
                + " format(cast(round(reserva_indemnizaciones,2) as decimal(20,2)),'c','es-gt')      as 'reserva_indemnizaciones',"
                + " format(cast(round(reserva_legal_acum,2) as decimal(20,2)),'c','es-gt')           as 'reserva_legal_acum',"
                + " format(cast(round(otras_reservas_acum,2) as decimal(20,2)),'c','es-gt')          as 'otras_reservas_acum',"
                + " format(cast(round(utilidad_acum,2) as decimal(20,2)),'c','es-gt')                as 'utilidad_acum',"
                + " format(cast(round(perdida_acum,2) as decimal(20,2)) ,'c','es-gt')                as 'perdida_acum',"
                + " format(cast(round(utilidad_per,2) as decimal(20,2)),'c','es-gt')                 as 'utilidad_per',"
                + " format(cast(round(perdida_per,2) as decimal(20,2)),'c','es-gt')                  as 'perdida_per',"
                + " format(cast(round(superavit_x_rev_acum,2) as decimal(20,2)),'c','es-gt')         as 'superavit_x_rev_acum',"
                + " format(cast(round(rentas_no_afecta,2) as decimal(20,2)),'c','es-gt')             as 'rentas_no_afecta',"
                + " format(cast(round(rentas_cap_ret_def,2) as decimal(20,2)),'c','es-gt')          as 'rentas_cap_ret_def',"
                + " format(cast(round(rentas_cap_pago_directo,2) as decimal(20,2)),'c','es-gt')      as 'rentas_cap_pago_directo',"
                + " format(cast(round(otras_rentas_cap_ret_def,2) as decimal(20,2)),'c','es-gt')     as 'otras_rentas_cap_ret_def',"
                + " format(cast(round(ventas_a_expores_fact_esp,2) as decimal(20,2)),'c','es-gt')    as 'ventas_a_expores_fact_esp',"
                + " format(cast(round(tot_otras_rentas_cap,2) as decimal(20,2)),'c','es-gt')         as 'tot_otras_rentas_cap',"
                + " format(cast(round(ing_giro_no_habitual,2) as decimal(20,2)),'c','es-gt')         as 'ing_giro_no_habitual',"
                + " format(cast(round(costo_del_bien,2) as decimal(20,2)),'c','es-gt')               as 'costo_del_bien',"
                + " format(cast(round(deprecia_acum_cpgc,2) as decimal(20,2)),'c','es-gt')           as 'deprecia_acum_cpgc',"
                + " format(cast(round(amorti_acum_cpgc,2) as decimal(20,2)),'c','es-gt')             as 'amorti_acum_cpgc',"
                + " format(cast(round(val_libros,2) as decimal(20,2)),'c','es-gt')                   as 'val_libros',"
                + " format(cast(round(otros_gto,2) as decimal(20,2)),'c','es-gt')                    as 'otros_gto',"
                + " format(cast(round(ganan_cap,2) as decimal(20,2)),'c','es-gt')                    as 'ganan_cap',"
                + " format(cast(round(perdida_cap,2) as decimal(20,2)),'c','es-gt')                  as 'perdida_cap',"
                + " format(cast(round(perdida_cap_ant,2) as decimal(20,2)),'c','es-gt')              as 'perdida_cap_ant',"
                + " format(cast(round(ganan_cap_pago_imp,2) as decimal(20,2)),'c','es-gt')           as 'ganan_cap_pago_imp',"
                + " format(cast(round(imp_ganan_cap,2) as decimal(20,2)),'c','es-gt')                as 'imp_ganan_cap',"
                + " format(cast(round(perdida_cap_sig_per,2) as decimal(20,2)),'c','es-gt')          as 'perdida_cap_sig_per',"
                + " format(cast(round(ventas,2) as decimal(20,2)),'c','es-gt')                       as 'ventas',"
                + " format(cast(round(expo,2) as decimal(20,2)),'c','es-gt')                 as 'expo',"
                + " format(cast(round(serv_expo,2) as decimal(20,2)),'c','es-gt')            as 'serv_expo',"
                + " format(cast(round(serv,2) as decimal(20,2)),'c','es-gt')                 as 'serv',"
                + " format(cast(round(arrend,2) as decimal(20,2)),'c','es-gt')               as 'arrend',"
                + " format(cast(round(serv_transp,2) as decimal(20,2)),'c','es-gt')          as 'serv_transp',"
                + " format(cast(round(serv_comunicacion,2) as decimal(20,2)),'c','es-gt')    as 'serv_comunicacion',"
                + " format(cast(round(serv_asesoria,2) as decimal(20,2)),'c','es-gt')        as 'serv_asesoria',"
                + " format(cast(round(espectaculos,2) as decimal(20,2)),'c','es-gt')         as 'espectaculos',"
                + " format(cast(round(peliculas_cine,2) as decimal(20,2)),'c','es-gt')       as 'peliculas_cine',"
                + " format(cast(round(subsidios_percibidos,2) as decimal(20,2)),'c','es-gt') as 'subsidios_percibidos',"
                + " format(cast(round(dietas,2) as decimal(20,2)),'c','es-gt')               as 'dietas',"
                + " format(cast(round(honor_ing,2) as decimal(20,2)),'c','es-gt')            as 'honor_ing',"
                + " format(cast(round(rentas_exentas_ing,2) as decimal(20,2)),'c','es-gt')   as 'rentas_exentas_ing',"
                + " format(cast(round(renta_bruta,2) as decimal(20,2)),'c','es-gt')          as 'renta_bruta',"
                + " format(cast(round(inventario_inicial_mat_prima,2) as decimal(20,2)),'c','es-gt') as 'inventario_inicial_mat_prima',"
                + " format(cast(round(compras_netas_mat_prima,2) as decimal(20,2)),'c','es-gt')      as 'compras_netas_mat_prima',"
                + " format(cast(round(impo_mat_prima,2) as decimal(20,2)),'c','es-gt')               as 'impo_mat_prima',"
                + " format(cast(round(gto_compras_mat_prima,2) as decimal(20,2)),'c','es-gt')        as 'gto_compras_mat_prima',"
                + " format(cast(round(rebajas_dev_mat_prima,2) as decimal(20,2)),'c','es-gt')        as 'rebajas_dev_mat_prima',"
                + " format(cast(round(inventario_final_mat_prima,2) as decimal(20,2)),'c','es-gt')   as 'inventario_final_mat_prima',"
                + " format(cast(round(mano_obra_directa,2) as decimal(20,2)),'c','es-gt')            as 'mano_obra_directa',"
                + " format(cast(round(costo_primo,2) as decimal(20,2)),'c','es-gt')                  as 'costo_primo',"
                + " format(cast(round(gto_indirectos_fabricacion,2) as decimal(20,2)),'c','es-gt')   as 'gto_indirectos_fabricacion',"
                + " format(cast(round(inventario_inicial_prod_proc,2) as decimal(20,2)),'c','es-gt') as 'inventario_inicial_prod_proc',"
                + " format(cast(round(inventario_final_prod_proc,2) as decimal(20,2)),'c','es-gt')   as 'inventario_final_prod_proc',"
                + " format(cast(round(oculta,2) as decimal(20,2)),'c','es-gt')                       as 'oculta',"
                + " format(cast(round(costo_produccion,2) as decimal(20,2)),'c','es-gt')             as 'costo_produccion',"
                + " format(cast(round(inventario_inicial_costo,2) as decimal(20,2)),'c','es-gt')     as 'inventario_inicial_costo',"
                + " format(cast(round(compras_costo,2) as decimal(20,2)),'c','es-gt')                as 'compras_costo',"
                + " format(cast(round(impo_costo,2) as decimal(20,2)),'c','es-gt')                   as 'impo_costo',"
                + " format(cast(round(inventario_final_costo,2) as decimal(20,2)),'c','es-gt')       as 'inventario_final_costo',"
                + " format(cast(round(oculta,2) as decimal(20,2)),'c','es-gt')                       as 'oculta',"
                + " format(cast(round(costo_ventas,2) as decimal(20,2)),'c','es-gt')                 as 'costo_ventas',"
                + " format(cast(round(gto_serv,2) as decimal(20,2)),'c','es-gt')                     as 'gto_serv',"
                + " format(cast(round(combust_y_lubricantes,2) as decimal(20,2)),'c','es-gt')        as 'combust_y_lubricantes',"
                + " format(cast(round(gto_transp,2) as decimal(20,2)),'c','es-gt')                   as 'gto_transp',"
                + " format(cast(round(sueldos_paga,2) as decimal(20,2)),'c','es-gt')                 as 'sueldos_paga',"
                + " format(cast(round(salarios_socios,2) as decimal(20,2)),'c','es-gt')              as 'salarios_socios',"
                + " format(cast(round(aguinaldos_paga,2) as decimal(20,2)),'c','es-gt')              as 'aguinaldos_paga',"
                + " format(cast(round(bonificaciones_paga,2) as decimal(20,2)),'c','es-gt')          as 'bonificaciones_paga',"
                + " format(cast(round(dietas_paga,2) as decimal(20,2)),'c','es-gt')                  as 'dietas_paga',"
                + " format(cast(round(cuota_igss_paga,2) as decimal(20,2)),'c','es-gt')              as 'cuota_igss_paga',"
                + " format(cast(round(cuota_irtra_intecap_paga,2) as decimal(20,2)),'c','es-gt')     as 'cuota_irtra_intecap_paga',"
                + " format(cast(round(cuota_jub_pen_primas_prevsoc,2) as decimal(20,2)),'c','es-gt') as 'cuota_jub_pen_primas_prevsoc',"
                + " format(cast(round(indemnizaciones,2) as decimal(20,2)),'c','es-gt')              as 'indemnizaciones',"
                + " format(cast(round(inver_en_beneficio_trab,2) as decimal(20,2)),'c','es-gt')      as 'inver_en_beneficio_trab',"
                + " format(cast(round(tierra_lab_adju_gratuita_trab,2) as decimal(20,2)),'c','es-gt') as 'tierra_lab_adju_gratuita_trab',"
                + " format(cast(round(primas_segvida_acci_enf_empl,2) as decimal(20,2)),'c','es-gt') as 'primas_segvida_acci_enf_empl',"
                + " format(cast(round(primas_seg_incend_robo_otros,2) as decimal(20,2)),'c','es-gt') as 'primas_seg_incend_robo_otros',"
                + " format(cast(round(reaseguros_reafianza,2) as decimal(20,2)),'c','es-gt')         as 'reaseguros_reafianza',"
                + " format(cast(round(arrend_bien_mueble,2) as decimal(20,2)),'c','es-gt')           as 'arrend_bien_mueble',"
                + " format(cast(round(arrend_bien_inmueble,2) as decimal(20,2)),'c','es-gt')         as 'arrend_bien_inmueble',"
                + " format(cast(round(mejora_efec_x_arrend,2) as decimal(20,2)),'c','es-gt')         as 'mejora_efec_x_arrend',"
                + " format(cast(round(imp_tasas_contrib_arbit_paga,2) as decimal(20,2)),'c','es-gt') as 'imp_tasas_contrib_arbit_paga',"
                + " format(cast(round(intereses_finan_gto,2) as decimal(20,2)),'c','es-gt')          as 'intereses_finan_gto',"
                + " format(cast(round(perdida_danios_fuerza_mayor,2) as decimal(20,2)),'c','es-gt')  as 'perdida_danios_fuerza_mayor',"
                + " format(cast(round(gto_man_y_rep,2) as decimal(20,2)),'c','es-gt')                as 'gto_man_y_rep',"
                + " format(cast(round(deprecia_gto,2) as decimal(20,2)),'c','es-gt')                 as 'deprecia_gto',"
                + " format(cast(round(amorti_gto,2) as decimal(20,2)),'c','es-gt')                   as 'amorti_gto',"
                + " format(cast(round(cta_incobra,2) as decimal(20,2)),'c','es-gt')                  as 'cta_incobra',"
                + " format(cast(round(reserva_tecnicas_y_matemati,2) as decimal(20,2)),'c','es-gt')  as 'reserva_tecnicas_y_matemati',"
                + " format(cast(round(donaciones_gto,2) as decimal(20,2)),'c','es-gt')               as 'donaciones_gto',"
                + " format(cast(round(honor_x_serv,2) as decimal(20,2)),'c','es-gt')                 as 'honor_x_serv',"
                + " format(cast(round(honor_x_serv_desde_extran,2) as decimal(20,2)),'c','es-gt')    as 'honor_x_serv_desde_extran',"
                + " format(cast(round(viaticos,2) as decimal(20,2)),'c','es-gt')                     as 'viaticos',"
                + " format(cast(round(regalias,2) as decimal(20,2)),'c','es-gt')                     as 'regalias',"
                + " format(cast(round(gto_promo_publi_y_propaganda,2) as decimal(20,2)),'c','es-gt') as 'gto_promo_publi_y_propaganda',"
                + " format(cast(round(donaciones_estado_uni_ent_cul,2) as decimal(20,2)),'c','es-gt') as 'donaciones_estado_uni_ent_cul',"
                + " format(cast(round(exceso_costo_gto_97,2) as decimal(20,2)),'c','es-gt')          as 'exceso_costo_gto_97',"
                + " format(cast(round(perdida_cambiarias,2) as decimal(20,2)),'c','es-gt')           as 'perdida_cambiarias',"
                + " format(cast(round(gto_ventas,2) as decimal(20,2)),'c','es-gt')                   as 'gto_ventas',"
                + " format(cast(round(gto_generales,2) as decimal(20,2)),'c','es-gt')                as 'gto_generales',"
                + " format(cast(round(tot_gto,2) as decimal(20,2)),'c','es-gt')                      as 'tot_gto',"
                + " format(cast(round(renta_neta,2) as decimal(20,2)),'c','es-gt')                   as 'renta_neta',"
                + " format(cast(round(perdida_neta,2) as decimal(20,2)),'c','es-gt')                 as 'perdida_neta',"
                + " format(cast(round(rentas_exentas_desp_cred,2) as decimal(20,2)),'c','es-gt')     as 'rentas_exentas_desp_cred',"
                + " format(cast(round(oculta,2) as decimal(20,2)),'c','es-gt')                       as 'oculta',"
                + " format(cast(round(oculta,2) as decimal(20,2)),'c','es-gt')                       as 'oculta',"
                + " format(cast(round(costos_gto_rentas_exentas,2) as decimal(20,2)),'c','es-gt')    as 'costos_gto_rentas_exentas',"
                + " format(cast(round(costos_gto_rentas_no_afecta,2) as decimal(20,2)),'c','es-gt')  as 'costos_gto_rentas_no_afecta',"
                + " format(cast(round(costos_gto_rentas_reten_def,2) as decimal(20,2)),'c','es-gt')  as 'costos_gto_rentas_reten_def',"
                + " format(cast(round(costos_gto_rentas_cap,2) as decimal(20,2)),'c','es-gt')        as 'costos_gto_rentas_cap',"
                + " format(cast(round(costo_gto_no_deducible,2) as decimal(20,2)),'c','es-gt')       as 'costo_gto_no_deducible',"
                + " format(cast(round(renta_imponib,2) as decimal(20,2)),'c','es-gt')                as 'renta_imponib',"
                + " format(cast(round(perdida_fisc,2) as decimal(20,2)),'c','es-gt')                 as 'perdida_fisc',"
                + " format(cast(round(imp_determinado,2) as decimal(20,2)),'c','es-gt')              as 'imp_determinado',"
                + " format(cast(round(saldo_acred_iso,2) as decimal(20,2)),'c','es-gt')              as 'saldo_acred_iso',"
                + " format(cast(round(acred_iso,2) as decimal(20,2)),'c','es-gt')                    as 'acred_iso',"
                + " format(cast(round(saldo_acred_ietaap,2) as decimal(20,2)),'c','es-gt')           as 'saldo_acred_ietaap',"
                + " format(cast(round(acred_ietaap,2) as decimal(20,2)),'c','es-gt')                 as 'acred_ietaap',"
                + " format(cast(round(saldo_acred_iema,2) as decimal(20,2)),'c','es-gt')             as 'saldo_acred_iema',"
                + " format(cast(round(acred_iema,2) as decimal(20,2)),'c','es-gt')                   as 'acred_iema',"
                + " format(cast(round(saldo_acred_fuente_renov,2) as decimal(20,2)),'c','es-gt')     as 'saldo_acred_fuente_renov',"
                + " format(cast(round(acred_fuente_renov,2) as decimal(20,2)),'c','es-gt')           as 'acred_fuente_renov',"
                + " format(cast(round(oculta,2) as decimal(20,2)),'c','es-gt')                       as 'oculta',"
                + " format(cast(round(oculta,2) as decimal(20,2)),'c','es-gt')                       as 'oculta',"
                + " format(cast(round(saldo_acred_incent_forest,2) as decimal(20,2)),'c','es-gt')    as 'saldo_acred_incent_forest',"
                + " format(cast(round(acred_incent_forest,2) as decimal(20,2)),'c','es-gt')          as 'acred_incent_forest',"
                + " format(cast(round(num_acred_otros,2) as decimal(20,2)),'c','es-gt')              as 'num_acred_otros',"
                + " format(cast(round(saldo_acred_otros,2) as decimal(20,2)),'c','es-gt')            as 'saldo_acred_otros',"
                + " format(cast(round(acred_otros,2) as decimal(20,2)),'c','es-gt')                  as 'acred_otros',"
                + " format(cast(round(num_resolucion_incent_fisc,2) as decimal(20,2)),'c','es-gt')   as 'num_resolucion_incent_fisc',"
                + " format(cast(round(val_resolucion_incent_fisc,2) as decimal(20,2)),'c','es-gt')   as 'val_resolucion_incent_fisc',"
                + " format(cast(round(tot_acred,2) as decimal(20,2)),'c','es-gt')                    as 'tot_acred',"
                + " format(cast(round(imp_despues_compensa,2) as decimal(20,2)),'c','es-gt')         as 'imp_despues_compensa',"
                + " format(cast(round(pagos_trim,2) as decimal(20,2)),'c','es-gt')                   as 'pagos_trim',"
                + " format(cast(round(saldo_en_exceso_ant,2) as decimal(20,2)),'c','es-gt')          as 'saldo_en_exceso_ant',"
                + " format(cast(round(imp_despues_trim,2) as decimal(20,2)),'c','es-gt')             as 'imp_despues_trim',"
                + " format(cast(round(pago_en_exceso,2) as decimal(20,2)),'c','es-gt')                as 'pago_en_exceso',"
                + " format(cast(round(renta_bruta_os,2) as decimal(20,2)),'c','es-gt')               as 'renta_bruta_os',"
                + " format(cast(round(renta_exenta_os,2) as decimal(20,2)),'c','es-gt')              as 'renta_exenta_os',"
                + " format(cast(round(renta_imponib_os,2) as decimal(20,2)),'c','es-gt')             as 'renta_imponib_os',"
                + " format(cast(round(imp_determinado_os,2) as decimal(20,2)),'c','es-gt')           as 'imp_determinado_os',"
                + " format(cast(round(reten_practicadas_os,2) as decimal(20,2)),'c','es-gt')         as 'reten_practicadas_os',"
                + " format(cast(round(imp_despues_reten_os,2) as decimal(20,2)),'c','es-gt')         as 'imp_despues_reten_os',"
                + " format(cast(round(acred_iso_os,2) as decimal(20,2)),'c','es-gt')                 as 'acred_iso_os',"
                + " format(cast(round(imp_paga_decla_mensual_os,2) as decimal(20,2)),'c','es-gt')    as 'imp_paga_decla_mensual_os',"
                + " format(cast(round(exceden_reten_os,2) as decimal(20,2)),'c','es-gt')             as 'exceden_reten_os',"
                + " format(cast(round(imp_no_reten_os,2) as decimal(20,2)),'c','es-gt')              as 'imp_no_reten_os',"
                + " format(cast(round(num_decla_rect,2) as decimal(20,2)),'c','es-gt')               as 'num_decla_rect',"
                + " format(cast(round(val_decla_rect,2) as decimal(20,2)),'c','es-gt')               as 'val_decla_rect',"
                + " format(cast(round(imp_despues_rect,2) as decimal(20,2)),'c','es-gt')             as 'imp_despues_rect',"
                + " format(cast(round(imp_a_favor_contrib,2) as decimal(20,2)),'c','es-gt')          as 'imp_a_favor_contrib',"
                + " format(cast(round(desea_calcular_acce,2) as decimal(20,2)),'c','es-gt')          as 'desea_calcular_acce',"
                + " fecha_maxima_pago,"
                + " cuando_pagara_1,"
                + " cuando_pagara_2,"
                + " format(cast(round(multa_formal,2) as decimal(20,2)),'c','es-gt')         as 'multa_formal',"
                + " format(cast(round(multa_omision,2) as decimal(20,2)),'c','es-gt')        as 'multa_omision',"
                + " format(cast(round(multa_rect,2) as decimal(20,2)),'c','es-gt')           as 'multa_rect',"
                + " format(cast(round(intereses,2) as decimal(20,2)),'c','es-gt')            as 'intereses',"
                + " format(cast(round(mora,2) as decimal(20,2)),'c','es-gt')                 as 'mora',"
                + " format(cast(round(acce_a_pagar,2) as decimal(20,2)),'c','es-gt')         as 'acce_a_pagar',"
                + " format(cast(round(tot_a_pagar,2) as decimal(20,2)),'c','es-gt')          as 'tot_a_pagar',"
                + " format(cast(round(num_contador_registrado,2) as decimal(20,2)),'c','es-gt')      as 'num_contador_registrado',"
                + " cast(nit_contrib as int) as 'nit_contrib', "
                + " format(cast(round(per_de_imposicion,2) as decimal(20,2)),'c','es-gt')            as 'per_de_imposicion',"
                + " format(cast(round(cap,2) as decimal(20,2)),'c','es-gt')                          as 'cap',"
                + " format(cast(round(ganan_cambiarias,2) as decimal(20,2)),'c','es-gt')             as 'ganan_cambiarias',"
                + " format(cast(round(cta_incobra_recuperada,2) as decimal(20,2)),'c','es-gt')       as 'cta_incobra_recuperada',"
                + " format(cast(round(otros_ing,2) as decimal(20,2)),'c','es-gt')                    as 'otros_ing',"
                + " fecha_del_informe,"
                + " nit_cpa,"
                + " nit_firma_de_auditoria,"
                + " format(cast(round(opinion_del_dictamen,2) as decimal(20,2)),'c','es-gt')         as 'opinion_del_dictamen',"
                + " format(cast(round(reserva_cta_incobra,2) as decimal(20,2)),'c','es-gt')          as 'reserva_cta_incobra',"
                + " format(cast(round(tot_activ,2) as decimal(20,2)),'c','es-gt')                    as 'tot_activ',"
                + " format(cast(round(tot_pasivos_y_cap,2) as decimal(20,2)),'c','es-gt')            as 'tot_pasivos_y_cap',"
                + " format(cast(round(emision_acciones_ing,2) as decimal(20,2)),'c','es-gt')         as 'emision_acciones_ing',"
                + " format(cast(round(donaciones_ing,2) as decimal(20,2)),'c','es-gt')               as 'donaciones_ing',"
                + " format(cast(round(resarci_perdida,2) as decimal(20,2)),'c','es-gt')              as 'resarci_perdida',"
                + " format(cast(round(num_resolucion_incent_fisc_os,2) as decimal(20,2)),'c','es-gt')  as 'num_resolucion_incent_fisc_os',"
                + " format(cast(round(val_resolucion_incent_fisc_os,2) as decimal(20,2)),'c','es-gt')  as 'val_resolucion_incent_fisc_os'";

        if ("N".equals(tipo)) {
            SQL = "SELECT  " + columnas
                    + " From  sat_declaraciones.o_isr_homologada_ag "
                    + " WHERE nit = '" + pnit + "' "
                    + " AND  periodo_fiscal between '" + ia_anio_ini + "' AND '" + ia_anio_fin + "' "
                    + " AND rank = 1 "
                    + " ORDER BY periodo_fiscal ";

        } else if ("E".equals(tipo)) {
            SQL = "SELECT  " + columnas
                    + " From  sat_declaraciones.o_isr_homologada_ag "
                    + " WHERE nit = '" + pnit + "' "
                    + " AND  periodo_fiscal between '" + ia_anio_ini_pr + "' AND '" + ia_anio_fin_pr + "' "
                    + " AND rank = 1 "
                    + " UNION "
                    + " SELECT  " + columnas
                    + " From  sat_declaraciones.o_isr_homologada_ag "
                    + " WHERE nit = '" + pnit + "' "
                    + " AND  periodo_fiscal between '" + ia_anio_ini_sr + "' AND '" + ia_anio_fin_sr + "' "
                    + " AND rank = 1 "
                    + " ORDER BY periodo_fiscal ";
        }

        Statement statement;
        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                temp3.add(rs.getString("periodo_fiscal"));
                temp3.add(rs.getString("reg_isr_declara"));
                temp3.add(rs.getString("acti_econo_prin"));
                temp3.add(rs.getString("acti_econo_secun"));
                temp3.add(rs.getString("opera_relacionadas_extran"));
                temp3.add(rs.getString("efectivo"));
                temp3.add(rs.getString("cta_x_cobrar"));
                temp3.add(rs.getString("cta_x_cobrar_otras"));
                temp3.add(rs.getString("inventario_final_activ"));
                temp3.add(rs.getString("cred_pend_reintegro"));
                temp3.add(rs.getString("inmuebles"));
                temp3.add(rs.getString("mobi_equipo"));
                temp3.add(rs.getString("maquinaria"));
                temp3.add(rs.getString("vehiculos"));
                temp3.add(rs.getString("equipo_computo"));
                temp3.add(rs.getString("otros_activ_depreciables"));
                temp3.add(rs.getString("activ_amorti"));
                temp3.add(rs.getString("inver"));
                temp3.add(rs.getString("deprecia_acum"));
                temp3.add(rs.getString("amorti_acum"));
                temp3.add(rs.getString("otros_activ"));
                temp3.add(rs.getString("cta_x_pagar"));
                temp3.add(rs.getString("prestamos_banca_finan"));
                temp3.add(rs.getString("otros_pasivos"));
                temp3.add(rs.getString("reserva_indemnizaciones"));
                temp3.add(rs.getString("reserva_legal_acum"));
                temp3.add(rs.getString("otras_reservas_acum"));
                temp3.add(rs.getString("utilidad_acum"));
                temp3.add(rs.getString("perdida_acum"));
                temp3.add(rs.getString("utilidad_per"));
                temp3.add(rs.getString("perdida_per"));
                temp3.add(rs.getString("superavit_x_rev_acum"));
                temp3.add(rs.getString("rentas_no_afecta"));
                temp3.add(rs.getString("rentas_cap_ret_def"));
                temp3.add(rs.getString("rentas_cap_pago_directo"));
                temp3.add(rs.getString("otras_rentas_cap_ret_def"));
                temp3.add(rs.getString("ventas_a_expores_fact_esp"));
                temp3.add(rs.getString("tot_otras_rentas_cap"));
                temp3.add(rs.getString("ing_giro_no_habitual"));
                temp3.add(rs.getString("costo_del_bien"));
                temp3.add(rs.getString("deprecia_acum_cpgc"));
                temp3.add(rs.getString("amorti_acum_cpgc"));
                temp3.add(rs.getString("val_libros"));
                temp3.add(rs.getString("otros_gto"));
                temp3.add(rs.getString("ganan_cap"));
                temp3.add(rs.getString("perdida_cap"));
                temp3.add(rs.getString("perdida_cap_ant"));
                temp3.add(rs.getString("ganan_cap_pago_imp"));
                temp3.add(rs.getString("imp_ganan_cap"));
                temp3.add(rs.getString("perdida_cap_sig_per"));
                temp3.add(rs.getString("ventas"));
                temp3.add(rs.getString("expo"));
                temp3.add(rs.getString("serv_expo"));
                temp3.add(rs.getString("serv"));
                temp3.add(rs.getString("arrend"));
                temp3.add(rs.getString("serv_transp"));
                temp3.add(rs.getString("serv_comunicacion"));
                temp3.add(rs.getString("serv_asesoria"));
                temp3.add(rs.getString("espectaculos"));
                temp3.add(rs.getString("peliculas_cine"));
                temp3.add(rs.getString("subsidios_percibidos"));
                temp3.add(rs.getString("dietas"));
                temp3.add(rs.getString("honor_ing"));
                temp3.add(rs.getString("rentas_exentas_ing"));
                temp3.add(rs.getString("renta_bruta"));
                temp3.add(rs.getString("inventario_inicial_mat_prima"));
                temp3.add(rs.getString("compras_netas_mat_prima"));
                temp3.add(rs.getString("impo_mat_prima"));
                temp3.add(rs.getString("gto_compras_mat_prima"));
                temp3.add(rs.getString("rebajas_dev_mat_prima"));
                temp3.add(rs.getString("inventario_final_mat_prima"));
                temp3.add(rs.getString("mano_obra_directa"));
                temp3.add(rs.getString("costo_primo"));
                temp3.add(rs.getString("gto_indirectos_fabricacion"));
                temp3.add(rs.getString("inventario_inicial_prod_proc"));
                temp3.add(rs.getString("inventario_final_prod_proc"));
                temp3.add(rs.getString("oculta"));
                temp3.add(rs.getString("costo_produccion"));
                temp3.add(rs.getString("inventario_inicial_costo"));
                temp3.add(rs.getString("compras_costo"));
                temp3.add(rs.getString("impo_costo"));
                temp3.add(rs.getString("inventario_final_costo"));
                temp3.add(rs.getString("oculta"));
                temp3.add(rs.getString("costo_ventas"));
                temp3.add(rs.getString("gto_serv"));
                temp3.add(rs.getString("combust_y_lubricantes"));
                temp3.add(rs.getString("gto_transp"));
                temp3.add(rs.getString("sueldos_paga"));
                temp3.add(rs.getString("salarios_socios"));
                temp3.add(rs.getString("aguinaldos_paga"));
                temp3.add(rs.getString("bonificaciones_paga"));
                temp3.add(rs.getString("dietas_paga"));
                temp3.add(rs.getString("cuota_igss_paga"));
                temp3.add(rs.getString("cuota_irtra_intecap_paga"));
                temp3.add(rs.getString("cuota_jub_pen_primas_prevsoc"));
                temp3.add(rs.getString("indemnizaciones"));
                temp3.add(rs.getString("inver_en_beneficio_trab"));
                temp3.add(rs.getString("tierra_lab_adju_gratuita_trab"));
                temp3.add(rs.getString("primas_segvida_acci_enf_empl"));
                temp3.add(rs.getString("primas_seg_incend_robo_otros"));
                temp3.add(rs.getString("reaseguros_reafianza"));
                temp3.add(rs.getString("arrend_bien_mueble"));
                temp3.add(rs.getString("arrend_bien_inmueble"));
                temp3.add(rs.getString("mejora_efec_x_arrend"));
                temp3.add(rs.getString("imp_tasas_contrib_arbit_paga"));
                temp3.add(rs.getString("intereses_finan_gto"));
                temp3.add(rs.getString("perdida_danios_fuerza_mayor"));
                temp3.add(rs.getString("gto_man_y_rep"));
                temp3.add(rs.getString("deprecia_gto"));
                temp3.add(rs.getString("amorti_gto"));
                temp3.add(rs.getString("cta_incobra"));
                temp3.add(rs.getString("reserva_tecnicas_y_matemati"));
                temp3.add(rs.getString("donaciones_gto"));
                temp3.add(rs.getString("honor_x_serv"));
                temp3.add(rs.getString("honor_x_serv_desde_extran"));
                temp3.add(rs.getString("viaticos"));
                temp3.add(rs.getString("regalias"));
                temp3.add(rs.getString("gto_promo_publi_y_propaganda"));
                temp3.add(rs.getString("donaciones_estado_uni_ent_cul"));
                temp3.add(rs.getString("exceso_costo_gto_97"));
                temp3.add(rs.getString("perdida_cambiarias"));
                temp3.add(rs.getString("gto_ventas"));
                temp3.add(rs.getString("gto_generales"));
                temp3.add(rs.getString("tot_gto"));
                temp3.add(rs.getString("renta_neta"));
                temp3.add(rs.getString("perdida_neta"));
                temp3.add(rs.getString("rentas_exentas_desp_cred"));
                temp3.add(rs.getString("oculta"));
                temp3.add(rs.getString("oculta"));
                temp3.add(rs.getString("costos_gto_rentas_exentas"));
                temp3.add(rs.getString("costos_gto_rentas_no_afecta"));
                temp3.add(rs.getString("costos_gto_rentas_reten_def"));
                temp3.add(rs.getString("costos_gto_rentas_cap"));
                temp3.add(rs.getString("costo_gto_no_deducible"));
                temp3.add(rs.getString("renta_imponib"));
                temp3.add(rs.getString("perdida_fisc"));
                temp3.add(rs.getString("imp_determinado"));
                temp3.add(rs.getString("saldo_acred_iso"));
                temp3.add(rs.getString("acred_iso"));
                temp3.add(rs.getString("saldo_acred_ietaap"));
                temp3.add(rs.getString("acred_ietaap"));
                temp3.add(rs.getString("saldo_acred_iema"));
                temp3.add(rs.getString("acred_iema"));
                temp3.add(rs.getString("saldo_acred_fuente_renov"));
                temp3.add(rs.getString("acred_fuente_renov"));
                temp3.add(rs.getString("oculta"));
                temp3.add(rs.getString("oculta"));
                temp3.add(rs.getString("saldo_acred_incent_forest"));
                temp3.add(rs.getString("acred_incent_forest"));
                temp3.add(rs.getString("num_acred_otros"));
                temp3.add(rs.getString("saldo_acred_otros"));
                temp3.add(rs.getString("acred_otros"));
                temp3.add(rs.getString("num_resolucion_incent_fisc"));
                temp3.add(rs.getString("val_resolucion_incent_fisc"));
                temp3.add(rs.getString("tot_acred"));
                temp3.add(rs.getString("imp_despues_compensa"));
                temp3.add(rs.getString("pagos_trim"));
                temp3.add(rs.getString("saldo_en_exceso_ant"));
                temp3.add(rs.getString("imp_despues_trim"));
                temp3.add(rs.getString("pago_en_exceso"));
                temp3.add(rs.getString("renta_bruta_os"));
                temp3.add(rs.getString("renta_exenta_os"));
                temp3.add(rs.getString("renta_imponib_os"));
                temp3.add(rs.getString("imp_determinado_os"));
                temp3.add(rs.getString("reten_practicadas_os"));
                temp3.add(rs.getString("imp_despues_reten_os"));
                temp3.add(rs.getString("acred_iso_os"));
                temp3.add(rs.getString("imp_paga_decla_mensual_os"));
                temp3.add(rs.getString("exceden_reten_os"));
                temp3.add(rs.getString("imp_no_reten_os"));
                temp3.add(rs.getString("num_decla_rect"));
                temp3.add(rs.getString("val_decla_rect"));
                temp3.add(rs.getString("imp_despues_rect"));
                temp3.add(rs.getString("imp_a_favor_contrib"));
                temp3.add(rs.getString("desea_calcular_acce"));
                temp3.add(rs.getString("fecha_maxima_pago"));
                temp3.add(rs.getString("cuando_pagara_1"));
                temp3.add(rs.getString("cuando_pagara_2"));
                temp3.add(rs.getString("multa_formal"));
                temp3.add(rs.getString("multa_omision"));
                temp3.add(rs.getString("multa_rect"));
                temp3.add(rs.getString("intereses"));
                temp3.add(rs.getString("mora"));
                temp3.add(rs.getString("acce_a_pagar"));
                temp3.add(rs.getString("tot_a_pagar"));
                temp3.add(rs.getString("num_contador_registrado"));
                temp3.add(rs.getString("nit_contrib"));
                temp3.add(rs.getString("per_de_imposicion"));
                temp3.add(rs.getString("cap"));
                temp3.add(rs.getString("ganan_cambiarias"));
                temp3.add(rs.getString("cta_incobra_recuperada"));
                temp3.add(rs.getString("otros_ing"));
                temp3.add(rs.getString("fecha_del_informe"));
                temp3.add(rs.getString("nit_cpa"));
                temp3.add(rs.getString("nit_firma_de_auditoria"));
                temp3.add(rs.getString("opinion_del_dictamen"));
                temp3.add(rs.getString("reserva_cta_incobra"));
                temp3.add(rs.getString("tot_activ"));
                temp3.add(rs.getString("tot_pasivos_y_cap"));
                temp3.add(rs.getString("emision_acciones_ing"));
                temp3.add(rs.getString("donaciones_ing"));
                temp3.add(rs.getString("resarci_perdida"));
                temp3.add(rs.getString("num_resolucion_incent_fisc_os"));
                temp3.add(rs.getString("val_resolucion_incent_fisc_os"));

                listaDatos.add(new ArrayList(temp3));
                temp3.clear();

            }
            conn.close();
            statement.close();

        } catch (Exception ex) {
            listaDatos.clear();
            errorMsg("Tabla no encontrada");
            /*  errorMsg(ex.getMessage());*/
            LOG.error(ex.getMessage());
        }

        if (listaDatos.isEmpty() || listaDatos.size() == 1) {
            listaDatos.clear();
            warnMsg("No existen registros para el NIT consultado");
        } else {
            listaDatos = transponer(listaDatos);
        }

    } // Handle any errors that may have occurred. // Handle any errors that may have occurred.

    public void getRepIso(String tipo) {
        ArrayList<String> temp4 = new ArrayList<>();
        ArrayList<String> isoanual = new ArrayList<>();

        descripciones = this.generalSrvImpl.obtenerDescripcionesFormulario(new BigDecimal(4));

        for (int i = 0; i < descripciones.size(); i++) {
            isoanual.add(descripciones.get(i).getDescripcion());
        }


        /*SE LLENA LA PRIMER LINEA CONTENIENDO LA DESCRIPCION DE LOS CAMPOS*/
        listaDatos.add(new ArrayList(isoanual));

        String SQL = new String();

        String columnas = new String();
        columnas = "periodo_fiscal, "
                + " format(cast(round(ing_brutos_serv_anio_ant,2) as decimal(20,2)),'c','es-gt')    as 'ing_brutos_serv_anio_ant',"
                + " format(cast(round(ing_brutos_ventas_anio_ant,2) as decimal(20,2)),'c','es-gt')   as 'ing_brutos_ventas_anio_ant',"
                + " format(cast(round(costo_ventas,2) as decimal(20,2)),'c','es-gt')                 as 'costo_ventas',"
                + " format(cast(round(ventas_menos_costo_ventas,2) as decimal(20,2)),'c','es-gt')    as 'ventas_menos_costo_ventas',"
                + " format(cast(round(margen_bruto,2) as decimal(20,2)),'c','es-gt')                 as 'margen_bruto',"
                + " format(cast(round(activ_tot,2) as decimal(20,2)),'c','es-gt')                    as 'activ_tot',"
                + " format(cast(round(deprecia_acum,2) as decimal(20,2)),'c','es-gt')                as 'deprecia_acum',"
                + " format(cast(round(amorti_acum,2) as decimal(20,2)),'c','es-gt')                  as 'amorti_acum',"
                + " format(cast(round(reserva_cta_incobra,2) as decimal(20,2)),'c','es-gt')          as 'reserva_cta_incobra',"
                + " format(cast(round(num_cred_pend_reintegro,2) as decimal(20,2)),'c','es-gt')      as 'num_cred_pend_reintegro',"
                + " format(cast(round(cred_pend_reintegro,2) as decimal(20,2)),'c','es-gt')          as 'cred_pend_reintegro',"
                + " format(cast(round(activ_neto,2) as decimal(20,2)),'c','es-gt')                   as 'activ_neto',"
                + " format(cast(round(base_imponib_sobre_activ,2) as decimal(20,2)),'c','es-gt')     as 'base_imponib_sobre_activ',"
                + " format(cast(round(imp_determinado_sobre_activ,2) as decimal(20,2)),'c','es-gt')  as 'imp_determinado_sobre_activ',"
                + " format(cast(round(acred_iusi,2) as decimal(20,2)),'c','es-gt')                   as 'acred_iusi',"
                + " format(cast(round(imp_despues_iusi,2) as decimal(20,2)),'c','es-gt')             as 'imp_despues_iusi',"
                + " format(cast(round(renta_bruta_anio_ant,2) as decimal(20,2)),'c','es-gt')         as 'renta_bruta_anio_ant',"
                + " format(cast(round(ing_x_resarci_seg,2) as decimal(20,2)),'c','es-gt')            as 'ing_x_resarci_seg',"
                + " format(cast(round(ing_x_resarci_reaseguro,2) as decimal(20,2)),'c','es-gt')      as 'ing_x_resarci_reaseguro',"
                + " format(cast(round(ing_x_resarci_reafianza,2) as decimal(20,2)),'c','es-gt')      as 'ing_x_resarci_reafianza',"
                + " format(cast(round(prima_seg_y_reafianza,2) as decimal(20,2)),'c','es-gt')        as 'prima_seg_y_reafianza',"
                + " format(cast(round(ing_netos,2) as decimal(20,2)),'c','es-gt')                    as 'ing_netos',"
                + " format(cast(round(base_imponib_sobre_ing,2) as decimal(20,2)),'c','es-gt')       as 'base_imponib_sobre_ing',"
                + " format(cast(round(imp_determinado_sobre_ing,2) as decimal(20,2)),'c','es-gt')    as 'imp_determinado_sobre_ing',"
                + " format(cast(round(imp_determinado,2) as decimal(20,2)),'c','es-gt')              as 'imp_determinado',"
                + " format(cast(round(saldo_acred_isr_al_iso,2) as decimal(20,2)),'c','es-gt')       as 'saldo_acred_isr_al_iso',"
                + " format(cast(round(acred_isr_al_iso,2) as decimal(20,2)),'c','es-gt')             as 'acred_isr_al_iso',"
                + " format(cast(round(imp_despues_acred,2) as decimal(20,2)),'c','es-gt')            as 'imp_despues_acred',"
                + " format(cast(round(num_decla_rect,2) as decimal(20,2)),'c','es-gt')               as 'num_decla_rect',"
                + " format(cast(round(val_decla_rect,2) as decimal(20,2)),'c','es-gt')               as 'val_decla_rect',"
                + " format(cast(round(imp_despues_rect,2) as decimal(20,2)),'c','es-gt')             as 'imp_despues_rect',"
                + " format(cast(round(imp_a_favor_contrib,2) as decimal(20,2)),'c','es-gt')          as 'imp_a_favor_contrib',"
                + " fecha_maxima_pago,"
                + " cuando_pagara_1,"
                + " cuando_pagara_2,"
                + " format(cast(round(multa_formal,2) as decimal(20,2)),'c','es-gt')                 as 'multa_formal',"
                + " format(cast(round(multa_omision,2) as decimal(20,2)),'c','es-gt')                as 'multa_omision',"
                + " format(cast(round(multa_rect,2) as decimal(20,2)),'c','es-gt')                   as 'multa_rect',"
                + " format(cast(round(intereses,2) as decimal(20,2)),'c','es-gt')                    as 'intereses',"
                + " format(cast(round(mora,2) as decimal(20,2)),'c','es-gt')                         as 'mora',"
                + " format(cast(round(acce_a_pagar,2) as decimal(20,2)),'c','es-gt')                 as 'acce_a_pagar',"
                + " format(cast(round(tot_a_pagar,2) as decimal(20,2)),'c','es-gt')                  as 'tot_a_pagar'";

        if ("N".equals(tipo)) {

            SQL = "SELECT  " + columnas
                    + " From  sat_declaraciones.o_iso_homologada_ag "
                    + " WHERE nit = '" + pnit + "' "
                    + " AND  periodo_fiscal between '" + iso_trimestre_ini + "' AND '" + iso_trimestre_fin + "' "
                    + " AND rank = 1 "
                    + " ORDER BY periodo_fiscal ";

        } else if ("E".equals(tipo)) {

            SQL = "SELECT  " + columnas
                    + " From  sat_declaraciones.o_iso_homologada_ag "
                    + " WHERE nit = '" + pnit + "' "
                    + " AND  periodo_fiscal between '" + iso_trimestre_ini_pr + "' AND '" + iso_trimestre_fin_pr + "' "
                    + " AND rank = 1 "
                    + " UNION "
                    + " SELECT  " + columnas
                    + " From  sat_declaraciones.o_iso_homologada_ag "
                    + " WHERE nit = '" + pnit + "' "
                    + " AND  periodo_fiscal between '" + iso_trimestre_ini_sr + "' AND '" + iso_trimestre_fin_sr + "' "
                    + " AND rank = 1 "
                    + " ORDER BY periodo_fiscal ";
        }

        Statement statement;
        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                temp4.add(rs.getString("periodo_fiscal"));
                temp4.add(rs.getString("ing_brutos_serv_anio_ant"));
                temp4.add(rs.getString("ing_brutos_ventas_anio_ant"));
                temp4.add(rs.getString("costo_ventas"));
                temp4.add(rs.getString("ventas_menos_costo_ventas"));
                temp4.add(rs.getString("margen_bruto"));
                temp4.add(rs.getString("activ_tot"));
                temp4.add(rs.getString("deprecia_acum"));
                temp4.add(rs.getString("amorti_acum"));
                temp4.add(rs.getString("reserva_cta_incobra"));
                temp4.add(rs.getString("num_cred_pend_reintegro"));
                temp4.add(rs.getString("cred_pend_reintegro"));
                temp4.add(rs.getString("activ_neto"));
                temp4.add(rs.getString("base_imponib_sobre_activ"));
                temp4.add(rs.getString("imp_determinado_sobre_activ"));
                temp4.add(rs.getString("acred_iusi"));
                temp4.add(rs.getString("imp_despues_iusi"));
                temp4.add(rs.getString("renta_bruta_anio_ant"));
                temp4.add(rs.getString("ing_x_resarci_seg"));
                temp4.add(rs.getString("ing_x_resarci_reaseguro"));
                temp4.add(rs.getString("ing_x_resarci_reafianza"));
                temp4.add(rs.getString("prima_seg_y_reafianza"));
                temp4.add(rs.getString("ing_netos"));
                temp4.add(rs.getString("base_imponib_sobre_ing"));
                temp4.add(rs.getString("imp_determinado_sobre_ing"));
                temp4.add(rs.getString("imp_determinado"));
                temp4.add(rs.getString("saldo_acred_isr_al_iso"));
                temp4.add(rs.getString("acred_isr_al_iso"));
                temp4.add(rs.getString("imp_despues_acred"));
                temp4.add(rs.getString("num_decla_rect"));
                temp4.add(rs.getString("val_decla_rect"));
                temp4.add(rs.getString("imp_despues_rect"));
                temp4.add(rs.getString("imp_a_favor_contrib"));
                temp4.add(rs.getString("fecha_maxima_pago"));
                temp4.add(rs.getString("cuando_pagara_1"));
                temp4.add(rs.getString("cuando_pagara_2"));
                temp4.add(rs.getString("multa_formal"));
                temp4.add(rs.getString("multa_omision"));
                temp4.add(rs.getString("multa_rect"));
                temp4.add(rs.getString("intereses"));
                temp4.add(rs.getString("mora"));
                temp4.add(rs.getString("acce_a_pagar"));
                temp4.add(rs.getString("tot_a_pagar"));

                listaDatos.add(new ArrayList(temp4));
                temp4.clear();

            }
            conn.close();
            statement.close();
        } catch (Exception ex) {
            listaDatos.clear();
            errorMsg("Tabla no encontrada");
            /*  errorMsg(ex.getMessage());*/
            LOG.error(ex.getMessage());
        }

        if (listaDatos.isEmpty() || listaDatos.size() == 1) {
            listaDatos.clear();
            warnMsg("No existen registros para el NIT consultado");
        } else {
            listaDatos = transponer(listaDatos);
        }

    }

    public Connection getConnection() throws Exception {
        InitialContext inc = new InitialContext();
        DataSource ds = (DataSource) inc.lookup("java:comp/env/jdbc/consulta360azure");
        return ds.getConnection();
    }

    public ArrayList<ArrayList<String>> getListaDatos() {
        return listaDatos;
    }

    public void setListaDatos(ArrayList<ArrayList<String>> listaDatos) {
        this.listaDatos = listaDatos;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIg_anio_mes_ini() {
        return ig_anio_mes_ini;
    }

    public void setIg_anio_mes_ini(String ig_anio_mes_ini) {
        this.ig_anio_mes_ini = ig_anio_mes_ini;
    }

    public String getIg_anio_mes_fin() {
        return ig_anio_mes_fin;
    }

    public void setIg_anio_mes_fin(String ig_anio_mes_fin) {
        this.ig_anio_mes_fin = ig_anio_mes_fin;
    }

    public String getIp_anio_mes_ini() {
        return ip_anio_mes_ini;
    }

    public void setIp_anio_mes_ini(String ip_anio_mes_ini) {
        this.ip_anio_mes_ini = ip_anio_mes_ini;
    }

    public String getIp_anio_mes_fin() {
        return ip_anio_mes_fin;
    }

    public void setIp_anio_mes_fin(String ip_anio_mes_fin) {
        this.ip_anio_mes_fin = ip_anio_mes_fin;
    }

    public String getIa_anio_ini() {
        return ia_anio_ini;
    }

    public void setIa_anio_ini(String ia_anio_ini) {
        this.ia_anio_ini = ia_anio_ini;
    }

    public String getIa_anio_fin() {
        return ia_anio_fin;
    }

    public void setIa_anio_fin(String ia_anio_fin) {
        this.ia_anio_fin = ia_anio_fin;
    }

    public String getIso_trimestre_ini() {
        return iso_trimestre_ini;
    }

    public void setIso_trimestre_ini(String iso_trimestre_ini) {
        this.iso_trimestre_ini = iso_trimestre_ini;
    }

    public String getIso_trimestre_fin() {
        return iso_trimestre_fin;
    }

    public void setIso_trimestre_fin(String iso_trimestre_fin) {
        this.iso_trimestre_fin = iso_trimestre_fin;
    }

    public String getIso_anio() {
        return iso_anio;
    }

    public void setIso_anio(String iso_anio) {
        this.iso_anio = iso_anio;
    }

    public String getIg_anio_mes_ini_pr() {
        return ig_anio_mes_ini_pr;
    }

    public void setIg_anio_mes_ini_pr(String ig_anio_mes_ini_pr) {
        this.ig_anio_mes_ini_pr = ig_anio_mes_ini_pr;
    }

    public String getIg_anio_mes_fin_pr() {
        return ig_anio_mes_fin_pr;
    }

    public void setIg_anio_mes_fin_pr(String ig_anio_mes_fin_pr) {
        this.ig_anio_mes_fin_pr = ig_anio_mes_fin_pr;
    }

    public String getIg_anio_mes_ini_sr() {
        return ig_anio_mes_ini_sr;
    }

    public void setIg_anio_mes_ini_sr(String ig_anio_mes_ini_sr) {
        this.ig_anio_mes_ini_sr = ig_anio_mes_ini_sr;
    }

    public String getIg_anio_mes_fin_sr() {
        return ig_anio_mes_fin_sr;
    }

    public void setIg_anio_mes_fin_sr(String ig_anio_mes_fin_sr) {
        this.ig_anio_mes_fin_sr = ig_anio_mes_fin_sr;
    }

    public String getIp_anio_mes_ini_pr() {
        return ip_anio_mes_ini_pr;
    }

    public void setIp_anio_mes_ini_pr(String ip_anio_mes_ini_pr) {
        this.ip_anio_mes_ini_pr = ip_anio_mes_ini_pr;
    }

    public String getIp_anio_mes_fin_pr() {
        return ip_anio_mes_fin_pr;
    }

    public void setIp_anio_mes_fin_pr(String ip_anio_mes_fin_pr) {
        this.ip_anio_mes_fin_pr = ip_anio_mes_fin_pr;
    }

    public String getIp_anio_mes_ini_sr() {
        return ip_anio_mes_ini_sr;
    }

    public void setIp_anio_mes_ini_sr(String ip_anio_mes_ini_sr) {
        this.ip_anio_mes_ini_sr = ip_anio_mes_ini_sr;
    }

    public String getIp_anio_mes_fin_sr() {
        return ip_anio_mes_fin_sr;
    }

    public void setIp_anio_mes_fin_sr(String ip_anio_mes_fin_sr) {
        this.ip_anio_mes_fin_sr = ip_anio_mes_fin_sr;
    }

    public String getIa_anio_ini_pr() {
        return ia_anio_ini_pr;
    }

    public void setIa_anio_ini_pr(String ia_anio_ini_pr) {
        this.ia_anio_ini_pr = ia_anio_ini_pr;
    }

    public String getIa_anio_fin_pr() {
        return ia_anio_fin_pr;
    }

    public void setIa_anio_fin_pr(String ia_anio_fin_pr) {
        this.ia_anio_fin_pr = ia_anio_fin_pr;
    }

    public String getIa_anio_ini_sr() {
        return ia_anio_ini_sr;
    }

    public void setIa_anio_ini_sr(String ia_anio_ini_sr) {
        this.ia_anio_ini_sr = ia_anio_ini_sr;
    }

    public String getIa_anio_fin_sr() {
        return ia_anio_fin_sr;
    }

    public void setIa_anio_fin_sr(String ia_anio_fin_sr) {
        this.ia_anio_fin_sr = ia_anio_fin_sr;
    }

    public String getIso_trimestre_ini_pr() {
        return iso_trimestre_ini_pr;
    }

    public void setIso_trimestre_ini_pr(String iso_trimestre_ini_pr) {
        this.iso_trimestre_ini_pr = iso_trimestre_ini_pr;
    }

    public String getIso_trimestre_fin_pr() {
        return iso_trimestre_fin_pr;
    }

    public void setIso_trimestre_fin_pr(String iso_trimestre_fin_pr) {
        this.iso_trimestre_fin_pr = iso_trimestre_fin_pr;
    }

    public String getIso_anio_pr() {
        return iso_anio_pr;
    }

    public void setIso_anio_pr(String iso_anio_pr) {
        this.iso_anio_pr = iso_anio_pr;
    }

    public String getIso_trimestre_ini_sr() {
        return iso_trimestre_ini_sr;
    }

    public void setIso_trimestre_ini_sr(String iso_trimestre_ini_sr) {
        this.iso_trimestre_ini_sr = iso_trimestre_ini_sr;
    }

    public String getIso_trimestre_fin_sr() {
        return iso_trimestre_fin_sr;
    }

    public void setIso_trimestre_fin_sr(String iso_trimestre_fin_sr) {
        this.iso_trimestre_fin_sr = iso_trimestre_fin_sr;
    }

    public String getIso_anio_sr() {
        return iso_anio_sr;
    }

    public void setIso_anio_sr(String iso_anio_sr) {
        this.iso_anio_sr = iso_anio_sr;
    }

    public ArrayList<ArrayList<String>> transponer(ArrayList<ArrayList<String>> table) {
        ArrayList<ArrayList<String>> tmp = new ArrayList<>();
        final int N = table.get(0).size();
        for (int i = 0; i < N; i++) {
            List<String> col = new ArrayList<>();
            for (List<String> row : table) {
                col.add(row.get(i));
            }
            tmp.add((ArrayList<String>) col);
        }
        return tmp;
    }

    /*IVA GENERAL NORMAL*/
    public boolean check_iva_gral_normal() {

        ig_anio_mes_ini = ig_anio_mes_ini.replace("-", "");
        ig_anio_mes_fin = ig_anio_mes_fin.replace("-", "");

        if ("".equals(ig_anio_mes_ini)) {
            warnMsg("no se ingreso el período inicial*");
            return false;
        }

        if ("".equals(ig_anio_mes_fin)) {
            warnMsg("no se ingreso el período final");
            return false;
        }

        if (Integer.parseInt(ig_anio_mes_ini) > Integer.parseInt(ig_anio_mes_fin)) {
            warnMsg("el período inicial, no puede ser mayor que el período final");
            return false;
        }

        if (Integer.parseInt(ig_anio_mes_fin) < Integer.parseInt(ig_anio_mes_ini)) {
            warnMsg("el período final, no puede ser menor que el período inicial");
            return false;
        }

        if ((Integer.parseInt(ig_anio_mes_fin) - Integer.parseInt(ig_anio_mes_ini)) > 200) {

            warnMsg("Debe seleccionar un rango de 24 meses máximo");

            return false;
        }

        if ((Integer.parseInt(ig_anio_mes_ini.substring(4, 6)) > 12) || (Integer.parseInt(ig_anio_mes_ini.substring(4, 6)) < 1)) {

            errorMsg("el mes ingresado en el rango inicial es erróneo");

            return false;

        }

        if ((Integer.parseInt(ig_anio_mes_fin.substring(4, 6)) > 12) || (Integer.parseInt(ig_anio_mes_fin.substring(4, 6)) < 1)) {

            errorMsg("el mes ingresado en el rango final es erróneo");

            return false;

        }

        if (Integer.parseInt(ig_anio_mes_ini.substring(0, 4)) < 2016) {

            errorMsg("el año en el rango inicial no puede ser menor a 2016");

            return false;

        }

        return true;

    }

    /*IVA GENERAL ESPECIAL*/
    public boolean check_iva_gral_especial() {
        ig_anio_mes_ini_pr = ig_anio_mes_ini_pr.replace("-", "");
        ig_anio_mes_fin_pr = ig_anio_mes_fin_pr.replace("-", "");

        ig_anio_mes_ini_sr = ig_anio_mes_ini_sr.replace("-", "");
        ig_anio_mes_fin_sr = ig_anio_mes_fin_sr.replace("-", "");

        if ("".equals(ig_anio_mes_ini_pr)) {
            warnMsg("no se ingreso el período inicial");
            return false;
        }

        if ("".equals(ig_anio_mes_fin_pr)) {
            warnMsg("no se ingreso el período final");
            return false;
        }

        if (Integer.parseInt(ig_anio_mes_ini_pr) > Integer.parseInt(ig_anio_mes_fin_pr)) {
            warnMsg("el período inicial, no puede ser mayor que el período final");
            return false;
        }

        if (Integer.parseInt(ig_anio_mes_fin_pr) < Integer.parseInt(ig_anio_mes_ini_pr)) {
            warnMsg("el período final, no puede ser menor que el período inicial");
            return false;
        }

        if ((Integer.parseInt(ig_anio_mes_fin_pr) - Integer.parseInt(ig_anio_mes_ini_pr)) > 100) {

            warnMsg("Debe seleccionar un rango de 12 meses máximo por año");

            return false;
        }

        if ((Integer.parseInt(ig_anio_mes_ini_pr.substring(4, 6)) > 12) || (Integer.parseInt(ig_anio_mes_ini_pr.substring(4, 6)) < 1)) {

            errorMsg("el mes ingresado en el rango inicial es erróneo");

            return false;
        }
        /*Segundo Rango*/

        if ("".equals(ig_anio_mes_ini_sr)) {
            warnMsg("no se ingreso el período inicial");
            return false;
        }

        if ("".equals(ig_anio_mes_fin_sr)) {
            warnMsg("no se ingreso el período final");
            return false;
        }

        if (Integer.parseInt(ig_anio_mes_ini_sr) > Integer.parseInt(ig_anio_mes_fin_sr)) {
            warnMsg("el período inicial, no puede ser mayor que el período final");
            return false;
        }

        if (Integer.parseInt(ig_anio_mes_fin_sr) < Integer.parseInt(ig_anio_mes_ini_sr)) {
            warnMsg("el período final, no puede ser menor que el período inicial");
            return false;
        }

        if ((Integer.parseInt(ig_anio_mes_fin_sr) - Integer.parseInt(ig_anio_mes_ini_sr)) > 100) {

            warnMsg("Debe seleccionar un rango de 12 meses máximo por año");

            return false;
        }

        if ((Integer.parseInt(ig_anio_mes_ini_sr.substring(4, 6)) > 12) || (Integer.parseInt(ig_anio_mes_ini_sr.substring(4, 6)) < 1)) {

            errorMsg("el mes ingresado en el rango inicial es erróneo");

            return false;
        }

        return true;
    }

    /*IVA PEQUEÑO CONTRIBUYENTE NORMAL*/
    public boolean check_iva_pcont_normal() {

        ip_anio_mes_ini = ip_anio_mes_ini.replace("-", "");
        ip_anio_mes_fin = ip_anio_mes_fin.replace("-", "");

        if ("".equals(ip_anio_mes_ini)) {
            warnMsg("no se ingreso el período inicial");
            return false;
        }

        if ("".equals(ip_anio_mes_fin)) {
            warnMsg("no se ingreso el período final");
            return false;
        }

        if (Integer.parseInt(ip_anio_mes_ini) > Integer.parseInt(ip_anio_mes_fin)) {
            warnMsg("el período inicial, no puede ser mayor que el período final");
            return false;
        }

        if (Integer.parseInt(ip_anio_mes_fin) < Integer.parseInt(ip_anio_mes_ini)) {
            warnMsg("el período final, no puede ser menor que el período inicial");
            return false;
        }

        if ((Integer.parseInt(ip_anio_mes_fin) - Integer.parseInt(ip_anio_mes_ini)) > 200) {

            warnMsg("Debe seleccionar un rango de 24 meses máximo");

            return false;
        }

        if ((Integer.parseInt(ip_anio_mes_ini.substring(4, 6)) > 12) || (Integer.parseInt(ip_anio_mes_ini.substring(4, 6)) < 1)) {

            errorMsg("el mes ingresado en el rango inicial es erróneo");

            return false;

        }

        if ((Integer.parseInt(ip_anio_mes_fin.substring(4, 6)) > 12) || (Integer.parseInt(ip_anio_mes_fin.substring(4, 6)) < 1)) {

            errorMsg("el mes ingresado en el rango final es erróneo");

            return false;

        }

        if (Integer.parseInt(ip_anio_mes_ini.substring(0, 4)) < 2016) {

            errorMsg("el año en el rango inicial no puede ser menor a 2016");

            return false;

        }

        return true;
    }

    /*IVA PEQUEÑO CONTRIBUYENTE ESPECIAL*/
    public boolean check_iva_pcont_especial() {
        ip_anio_mes_ini_pr = ip_anio_mes_ini_pr.replace("-", "");
        ip_anio_mes_fin_pr = ip_anio_mes_fin_pr.replace("-", "");

        ip_anio_mes_ini_sr = ip_anio_mes_ini_sr.replace("-", "");
        ip_anio_mes_fin_sr = ip_anio_mes_fin_sr.replace("-", "");

        if ("".equals(ip_anio_mes_ini_pr)) {
            warnMsg("no se ingreso el período inicial");
            return false;
        }

        if ("".equals(ip_anio_mes_fin_pr)) {
            warnMsg("no se ingreso el período final");
            return false;
        }

        if (Integer.parseInt(ip_anio_mes_ini_pr) > Integer.parseInt(ip_anio_mes_fin_pr)) {
            warnMsg("el período inicial, no puede ser mayor que el período final");
            return false;
        }

        if (Integer.parseInt(ip_anio_mes_fin_pr) < Integer.parseInt(ip_anio_mes_ini_pr)) {
            warnMsg("el período final, no puede ser menor que el período inicial");
            return false;
        }

        if ((Integer.parseInt(ip_anio_mes_fin_pr) - Integer.parseInt(ip_anio_mes_ini_pr)) > 100) {

            warnMsg("Debe seleccionar un rango de 12 meses máximo por año");

            return false;
        }

        if ((Integer.parseInt(ip_anio_mes_ini_pr.substring(4, 6)) > 12) || (Integer.parseInt(ip_anio_mes_ini_pr.substring(4, 6)) < 1)) {

            errorMsg("el mes ingresado en el rango inicial es erróneo");

            return false;
        }
        /*Segundo Rango*/

        if ("".equals(ip_anio_mes_ini_sr)) {
            warnMsg("no se ingreso el período inicial");
            return false;
        }

        if ("".equals(ip_anio_mes_fin_sr)) {
            warnMsg("no se ingreso el período final");
            return false;
        }

        if (Integer.parseInt(ip_anio_mes_ini_sr) > Integer.parseInt(ip_anio_mes_fin_sr)) {
            warnMsg("el período inicial, no puede ser mayor que el período final");
            return false;
        }

        if (Integer.parseInt(ip_anio_mes_fin_sr) < Integer.parseInt(ip_anio_mes_ini_sr)) {
            warnMsg("el período final, no puede ser menor que el período inicial");
            return false;
        }

        if ((Integer.parseInt(ip_anio_mes_fin_sr) - Integer.parseInt(ip_anio_mes_ini_sr)) > 100) {

            warnMsg("Debe seleccionar un rango de 12 meses máximo por año");

            return false;
        }

        if ((Integer.parseInt(ip_anio_mes_ini_sr.substring(4, 6)) > 12) || (Integer.parseInt(ip_anio_mes_ini_sr.substring(4, 6)) < 1)) {

            errorMsg("el mes ingresado en el rango inicial es erróneo");

            return false;
        }

        return true;
    }

    /*IVA ISR ANUAL NORMAL*/
    public boolean check_isr_anual_normal() {

        if ("".equals(ia_anio_ini)) {
            warnMsg("no se ingreso el período inicial");
            return false;
        }

        if ("".equals(ia_anio_fin)) {
            warnMsg("no se ingreso el período final");
            return false;
        }

        if (Integer.parseInt(ia_anio_ini) > Integer.parseInt(ia_anio_fin)) {
            warnMsg("el período inicial, no puede ser mayor que el período final");
            return false;
        }

        if (Integer.parseInt(ia_anio_fin) < Integer.parseInt(ia_anio_ini)) {
            warnMsg("el período final, no puede ser menor que el período inicial");
            return false;
        }

        if ((Integer.parseInt(ia_anio_fin) - Integer.parseInt(ia_anio_ini)) > 5) {

            warnMsg("Debe seleccionar un rango de 5 años máximo");

            return false;
        }

        if (Integer.parseInt(ia_anio_ini) < 2016) {

            errorMsg("el año en el rango inicial no puede ser menor a 2016");

            return false;

        }

        return true;

    }

    /*IVA ISR ANUAL ESPECIAL*/
    public boolean check_isr_anual_especial() {
        ia_anio_ini_pr = ia_anio_ini_pr.replace("-", "");
        ia_anio_fin_pr = ia_anio_fin_pr.replace("-", "");

        ia_anio_ini_sr = ia_anio_ini_sr.replace("-", "");
        ia_anio_fin_sr = ia_anio_fin_sr.replace("-", "");

        if ("".equals(ia_anio_ini_pr)) {
            warnMsg("no se ingreso el período inicial");
            return false;
        }

        if ("".equals(ia_anio_fin_pr)) {
            warnMsg("no se ingreso el período final");
            return false;
        }

        if ("".equals(ia_anio_ini_sr)) {
            warnMsg("no se ingreso el período inicial");
            return false;
        }

        if ("".equals(ia_anio_fin_sr)) {
            warnMsg("no se ingreso el período final");
            return false;
        }

        if (Integer.parseInt(ia_anio_ini_pr) > Integer.parseInt(ia_anio_fin_pr)) {
            warnMsg("el período inicial, no puede ser mayor que el período final");
            return false;
        }

        if (Integer.parseInt(ia_anio_ini_pr) < Integer.parseInt(ia_anio_fin_pr)) {
            warnMsg("el período final, no puede ser menor que el período inicial");
            return false;
        }

        if ((Integer.parseInt(ia_anio_ini_pr) - Integer.parseInt(ia_anio_fin_pr)) > 100) {

            warnMsg("Debe seleccionar un rango de 12 meses máximo por año");

            return false;
        }

        /*if ((Integer.parseInt(ia_anio_ini_pr.substring(4, 6)) > 12) || (Integer.parseInt(ia_anio_ini_pr.substring(4, 6)) < 1)) {

            errorMsg("el mes ingresado en el rango inicial es erróneo");

            return false;
        }*/
 /*Segundo Rango*/
        if ("".equals(ia_anio_ini_sr)) {
            warnMsg("no se ingreso el período inicial");
            return false;
        }

        if ("".equals(ia_anio_fin_sr)) {
            warnMsg("no se ingreso el período final");
            return false;
        }

        if (Integer.parseInt(ia_anio_ini_sr) > Integer.parseInt(ia_anio_fin_sr)) {
            warnMsg("el período inicial, no puede ser mayor que el período final");
            return false;
        }

        if (Integer.parseInt(ia_anio_fin_sr) < Integer.parseInt(ia_anio_ini_sr)) {
            warnMsg("el período final, no puede ser menor que el período inicial");
            return false;
        }

        if ((Integer.parseInt(ia_anio_fin_sr) - Integer.parseInt(ia_anio_ini_sr)) > 100) {

            warnMsg("Debe seleccionar un rango de 12 meses máximo por año");

            return false;
        }

        /* if ((Integer.parseInt(ia_anio_ini_sr.substring(4, 6)) > 12) || (Integer.parseInt(ia_anio_ini_sr.substring(4, 6)) < 1)) {

            errorMsg("el mes ingresado en el rango inicial es erróneo");

            return false;
        }*/
        return true;

    }

    /*IVA ISO NORMAL*/
    public boolean check_iso_normal() {

        if ("".equals(iso_trimestre_ini)) {
            warnMsg("no se ingreso el período inicial");
            return false;
        }

        if ("".equals(iso_trimestre_fin)) {
            warnMsg("no se ingreso el período final");
            return false;
        }

        if (Integer.parseInt(iso_trimestre_ini) > Integer.parseInt(iso_trimestre_fin)) {
            warnMsg("el trimestre inicial, no puede ser mayor que el trimestre final");
            return false;
        }

        if (Integer.parseInt(iso_trimestre_fin) < Integer.parseInt(iso_trimestre_ini)) {
            warnMsg("el trimestre final, no puede ser menor que el trimestre inicial");
            return false;
        }

        return true;

    }

    /*IVA ISO ESPECIAL*/
    public boolean check_iso_especial() {
        if ("0".equals(iso_trimestre_ini_pr)) {
            warnMsg("no se ingreso el período inicial del primer rango");
            return false;
        }

        if ("0".equals(iso_trimestre_fin_pr)) {
            warnMsg("no se ingreso el período final del primer rango");
            return false;
        }

        if (Integer.parseInt(iso_trimestre_ini_pr) > Integer.parseInt(iso_trimestre_fin_pr)) {
            warnMsg("el trimestre inicial, no puede ser mayor que el trimestre final del primer rango");
            return false;
        }

        if (Integer.parseInt(iso_trimestre_fin_pr) < Integer.parseInt(iso_trimestre_ini_pr)) {
            warnMsg("el trimestre final, no puede ser menor que el trimestre inicial del primer rango");
            return false;
        }

        /*Segundo Rango*/
        if ("0".equals(iso_trimestre_ini_sr)) {
            warnMsg("no se ingreso el período inicial segundo rango");
            return false;
        }

        if ("0".equals(iso_trimestre_fin_sr)) {
            warnMsg("no se ingreso el período final segundo rango");
            return false;
        }

        if (Integer.parseInt(iso_trimestre_ini_sr) > Integer.parseInt(iso_trimestre_fin_sr)) {
            warnMsg("el período inicial, no puede ser mayor que el período final del segundo rango");
            return false;
        }

        if (Integer.parseInt(iso_trimestre_fin_sr) < Integer.parseInt(iso_trimestre_ini_sr)) {
            warnMsg("el período final, no puede ser menor que el período inicial del segundo rango");
            return false;
        }

        if ((Integer.parseInt(iso_trimestre_fin_sr) - Integer.parseInt(iso_trimestre_ini_sr)) > 100) {

            warnMsg("Debe seleccionar un rango de 12 meses máximo por año");

            return false;
        }

        /*if ((Integer.parseInt(iso_trimestre_ini_sr.substring(4, 6)) > 12) || (Integer.parseInt(iso_trimestre_ini_sr.substring(4, 6)) < 1)) {

            errorMsg("el mes ingresado en el rango inicial es erróneo");

            return false;
        }*/
        if (Integer.parseInt(iso_anio_sr) < Integer.parseInt(iso_anio_pr)) {
            warnMsg("el año final, no puede ser menor que el año inicial");
            return false;
        }

        if (Integer.parseInt(iso_anio_pr) > Integer.parseInt(iso_anio_sr)) {
            warnMsg("el año inicial, no puede ser mayor que el año final");
            return false;
        }

        return true;
    }

    public void exportare() throws IOException {
        String tmp = "";

        HSSFWorkbook libro = new HSSFWorkbook();
        HSSFSheet hoja = libro.createSheet("hoja1");

        /*DATA FORMAT*/
        HSSFDataFormat format = libro.createDataFormat();

        /*CELL STYLE*/
        HSSFCellStyle cellStyle = hoja.getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);

        /*ROW STYLE*/
        HSSFCellStyle rowStyle = hoja.getWorkbook().createCellStyle();
        rowStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        rowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        hoja.setColumnWidth(0, 40000);

        for (int x = 0; x < listaDatos.size(); x++) {

            HSSFRow fila = hoja.createRow(x);

            for (int y = 0; y < listaDatos.get(x).size(); y++) {
                HSSFCell celda = fila.createCell(y);

                tmp = listaDatos.get(x).get(y);
                if (tmp != null) {
                    tmp = tmp.replace("E7", "");
                }

                if (y > 0) {
                    celda.setCellStyle(cellStyle);
                }

                if (x == 0) {
                    celda.setCellStyle(rowStyle);
                }

                celda.setCellValue(tmp);

            }
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        externalContext.setResponseContentType("application/vnd.ms-excel; charset=utf-8");

        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"reporte.xls\"");

        libro.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();

    }

}
