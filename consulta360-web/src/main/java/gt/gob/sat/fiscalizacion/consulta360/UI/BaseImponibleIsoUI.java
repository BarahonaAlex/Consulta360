/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.BaseImponibleIsoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ContribuyenteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ImpuestoIsoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ImpuestoIsrDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ImpuestoIvaGeneralDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.PequenoContribuyenteEncabezadoDto;
import java.io.IOException;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author cgsamayo
 */
@Controller
@Scope("view")
public class BaseImponibleIsoUI extends AbstractUI implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(VerificacionesEnCampoUI.class);
    private String pnit = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private ArrayList<BaseImponibleIsoDto> listDatosGeneral = new ArrayList();
    private ArrayList<BaseImponibleIsoDto> listDatosBaseImponible = new ArrayList();
    private List<PequenoContribuyenteEncabezadoDto> listDatosEncabezado;
    private int fecha_fin;
    private int fecha_ini;
    private ArrayList<ImpuestoIsrDto> listaRegimenIsr;
    private ArrayList<ImpuestoIsoDto> listaRegimenIso;

    @PostConstruct
    public void inicializar() {
        Date date = new Date();
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(date);
        fecha_fin = fecha.get(Calendar.YEAR);

        Calendar fecha2 = Calendar.getInstance();
        fecha2.setTime(date);
        fecha2.add(Calendar.YEAR, -5);
        int fecha_ini = fecha2.get(Calendar.YEAR);

        String SQL = "SELECT "
                + "ISNULL(PERIODO_DESDE_ISR, '0')       AS PERIODO_DESDE_ISR, "
                + "ISNULL(PERIODO_HASTA_ISR, '0')       AS PERIODO_HASTA_ISR, "
                + "ISNULL(FECHA_RECAUDO_ISR, '0')       AS FECHA_RECAUDO_ISR, "
                + "ISNULL(ANIO_IMPOSITIVO_ISR, '0')     AS ANIO_IMPOSITIVO_ISR, "
                + "ISNULL(CODIGO_FORMULARIO_ISR, '0')   AS CODIGO_FORMULARIO_ISR, "
                + "ISNULL(NUMERO_FORMULARIO_ISR, '0')   AS NUMERO_FORMULARIO_ISR, "
                + "ISNULL(STATUS_FORMULARIO_ISR, '0')   AS STATUS_FORMULARIO_ISR, "
                + "ISNULL(PERIODO_DESDE_ISO, '0')       AS PERIODO_DESDE_ISO, "
                + "ISNULL(PERIODO_HASTA_ISO, '0')       AS PERIODO_HASTA_ISO, "
                + "ISNULL(FECHA_RECAUDO_ISO, '0')       AS FECHA_RECAUDO_ISO, "
                + "ISNULL(ANIO_IMPOSITIVO_ISO,  '0')    AS ANIO_IMPOSITIVO_ISO, "
                + "ISNULL(CODIGO_FORMULARIO_ISO,   '0')    AS CODIGO_FORMULARIO_ISO, "
                + "ISNULL(NUMERO_FORMULARIO_ISO,    '0')    AS NUMERO_FORMULARIO_ISO, "
                + "ISNULL(TRIMESTRE,     '0')    AS TRIMESTRE,  "
                + "ISNULL(format(cast(Round(TOTAL_INGRESOS_BRUTOS_POR_SERVICIOS_PRESTADOS,2) as decimal(20,2)),'C','Es-Gt'),0.00)                             AS  TOTAL_INGRESOS_BRUTOS_POR_SERVICIOS_PRESTADOS,  "
                + "ISNULL(format(cast(Round(TOTAL_INGRESOS_BRUTOS_POR_VENTAS,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                          AS  TOTAL_INGRESOS_BRUTOS_POR_VENTAS, "
                + "ISNULL(format(cast(Round(COSTO_DE_VENTAS,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                           AS  COSTO_DE_VENTAS, "
                + "ISNULL(format(cast(Round(TOTAL_INGRESOS_BRUTOS_COSTO_DE_VENTAS,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                     AS  TOTAL_INGRESOS_BRUTOS_COSTO_DE_VENTAS,  "
                + "ISNULL(PORC_MARGEN_BRUTO, '0')    AS PORC_MARGEN_BRUTO,   "
                + "ISNULL(PORC_MARGEN_BRUTO_SEGUN_CONTRIBUYENTE,  '0')    AS PORC_MARGEN_BRUTO_SEGUN_CONTRIBUYENTE,  "
                + "ISNULL(format(cast(Round(DIFERENCIA_PORC_MARGEN_BRUTO,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                              AS  DIFERENCIA_PORC_MARGEN_BRUTO,  "
                + "ISNULL(format(cast(Round(TOTAL_ACTIVO,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                              AS  TOTAL_ACTIVO, "
                + "ISNULL(format(cast(Round(DEPR_AMOR_CREDITOS_L_Y_RESERVA,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                            AS  DEPR_AMOR_CREDITOS_L_Y_RESERVA,  "
                + "ISNULL(format(cast(Round(ACTIVO_NETO_SEGUN_SAT,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                     AS  ACTIVO_NETO_SEGUN_SAT, "
                + "ISNULL(format(cast(Round(ACTIVO_NETO_SEGUN_CONTRIBUYENTE,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                           AS  ACTIVO_NETO_SEGUN_CONTRIBUYENTE,  "
                + "ISNULL(format(cast(Round(DIFERENCIA_ACTIVO_NETO,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                    AS  DIFERENCIA_ACTIVO_NETO, "
                + "ISNULL(format(cast(Round(BASE_IMPONIBLE_SEGUN_ACTIVO_NETO_SEGUN_SAT,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                AS  BASE_IMPONIBLE_SEGUN_ACTIVO_NETO_SEGUN_SAT,  "
                + "ISNULL(format(cast(Round(IMPUESTO_DETERMINADO_SEGUN_ACTIVO_NETO,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                    AS  IMPUESTO_DETERMINADO_SEGUN_ACTIVO_NETO, "
                + "ISNULL(format(cast(Round(RENTA_BRUTA,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                               AS  RENTA_BRUTA, "
                + "ISNULL(format(cast(Round(RENTAS_NO_AFECTAS,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                         AS  RENTAS_NO_AFECTAS, "
                + "ISNULL(format(cast(Round(TOTAL_DE_INGRESOS_OTRAS_CATEGORIAS_DE_RENTA,2) as decimal(20,2)),'C','Es-Gt'),0.00)                               AS  TOTAL_DE_INGRESOS_OTRAS_CATEGORIAS_DE_RENTA,"
                + "ISNULL(format(cast(Round(INGRESOS_POR_NEGOCIACION_DE_BIENES_Y_O_DERECHOS_QUE_NO_SEAN_DEL_GIRO_HABITUAL,2) as decimal(20,2)),'C','Es-Gt'),0.00)    AS  INGRESOS_POR_NEGOCIACION_DE_BIENES_Y_O_DERECHOS_QUE_NO_SEAN_DEL_GIRO_HABITUAL, "
                + "ISNULL(format(cast(Round(TOTAL_INGRESOS_BRUTOS,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                            AS  TOTAL_INGRESOS_BRUTOS,"
                + "ISNULL(format(cast(Round(INGRESOS_POR_RESARCIMIENTOS_PROVENIENTES_DE_CONTRATOS_DE_SEGURO,2) as decimal(20,2)),'C','Es-Gt'),0.00)                        AS  INGRESOS_POR_RESARCIMIENTOS_PROVENIENTES_DE_CONTRATOS_DE_SEGURO, "
                + "ISNULL(format(cast(Round(INGRESOS_POR_RESARCIMIENTOS_PROVENIENTES_DE_CONTRATOS_DE_REASEGURO,2) as decimal(20,2)),'C','Es-Gt'),0.00)                     AS  INGRESOS_POR_RESARCIMIENTOS_PROVENIENTES_DE_CONTRATOS_DE_REASEGURO, "
                + "ISNULL(format(cast(Round(INGRESOS_POR_RESARCIMIENTOS_PROVENIENTES_DE_CONTRATOS_DE_REAFIANZAMIENTO,2) as decimal(20,2)),'C','Es-Gt'),0.00)               AS  INGRESOS_POR_RESARCIMIENTOS_PROVENIENTES_DE_CONTRATOS_DE_REAFIANZAMIENTO, "
                + "ISNULL(format(cast(Round(PRIMAS_CEDIDAS_DE_SEGURO_Y_DE_REAFIANZAMIENTO,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                          AS  PRIMAS_CEDIDAS_DE_SEGURO_Y_DE_REAFIANZAMIENTO,  "
                + "ISNULL(format(cast(Round(INGRESOS_BRUTOS_MENOS_EXCLUSIONES_SEGUN_SAT,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                            AS  INGRESOS_BRUTOS_MENOS_EXCLUSIONES_SEGUN_SAT,"
                + "ISNULL(format(cast(Round(INGRESOS_BRUTOS_MENOS_EXCLUSIONES_SEGUN_CONTRIBUYENTE,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                  AS  INGRESOS_BRUTOS_MENOS_EXCLUSIONES_SEGUN_CONTRIBUYENTE,  "
                + "ISNULL(format(cast(Round(DIFERENCIA_EN_INGRESOS_BRUTOS_MENOS_EXCLUSIONES,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                        AS  DIFERENCIA_EN_INGRESOS_BRUTOS_MENOS_EXCLUSIONES, "
                + "ISNULL(format(cast(Round(BASE_IMPONIBLE_SEGUN_INGRESOS_BRUTOS,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                   AS  BASE_IMPONIBLE_SEGUN_INGRESOS_BRUTOS,  "
                + "ISNULL(format(cast(Round(IMPUESTO_SEGUN_INGRESOS_BRUTOS,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                         AS  IMPUESTO_SEGUN_INGRESOS_BRUTOS, "
                + "ISNULL(format(cast(Round(IMPUESTO_SEGUN_ACTIVO_NETO,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                             AS  IMPUESTO_SEGUN_ACTIVO_NETO,  "
                + "ISNULL(format(cast(Round(IMPUESTO_DETERMINADO,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                                   AS  IMPUESTO_DETERMINADO,  "
                + "ISNULL(format(cast(Round(IMPUESTO_DETERMINADO_SEGUN_CONTRIBUYENTE,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                               AS  IMPUESTO_DETERMINADO_SEGUN_CONTRIBUYENTE, "
                + "ISNULL(format(cast(Round(IUSI,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                                                   AS  IUSI, "
                + "ISNULL(format(cast(Round(POTENCIAL_IMPUESTO_DE_SOLIDARIDAD,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                      AS  POTENCIAL_IMPUESTO_DE_SOLIDARIDAD, "
                + "ISNULL(format(cast(Round(SALDO_NO_ACREDITADO,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                                    AS  SALDO_NO_ACREDITADO, "
                + "ISNULL(format(cast(Round(VALOR_DE_ISR_A_ACREDITAR_EN_ESTE_PERIODO,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                               AS  VALOR_DE_ISR_A_ACREDITAR_EN_ESTE_PERIODO, "
                + "ISNULL(format(cast(Round(IMPUESTO,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                                               AS  IMPUESTO, "
                + "ISNULL(format(cast(Round(IMPUESTO_SOBRE_LA_RENTA,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                                AS  IMPUESTO_SOBRE_LA_RENTA, "
                + "ISNULL(format(cast(Round(POTENCIAL_IMPUESTO_DE_SOLIDARIDAD_4TO_TRIMESTRE,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                        AS  POTENCIAL_IMPUESTO_DE_SOLIDARIDAD_4TO_TRIMESTRE, "
                + "ISNULL(format(cast(Round(POTENCIAL_DE_RECAUDO,2) as decimal(20,2)),'C','Es-Gt'),0.00)                                                                   AS  POTENCIAL_DE_RECAUDO "
                + "FROM sat_cruces.o_iso_homologado_vs_isr_anual_homologado_ge "
                + "WHERE nit_contribuyente = '" + pnit + "' "
                + " AND ANIO_IMPOSITIVO_ISR between '" + fecha_ini + "' AND '" + fecha_fin + "'"
                + " AND PERIODO_DESDE_ISR >=  '01/01/" + fecha_ini + "'"
                + " AND PERIODO_HASTA_ISR <= '31/12/" + fecha_fin + "'"
                + " ORDER BY 1,2 DESC ";

        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                BaseImponibleIsoDto datos = new BaseImponibleIsoDto();
                datos.setPeriodo_desde_isr(rs.getString("PERIODO_DESDE_ISR"));
                datos.setPeriodo_hasta_isr(rs.getString("PERIODO_HASTA_ISR"));
                datos.setFecha_recaudo_isr(rs.getString("FECHA_RECAUDO_ISR"));
                datos.setAnio_impositivo_isr(rs.getString("ANIO_IMPOSITIVO_ISR"));
                datos.setCodigo_formulario_isr(rs.getString("CODIGO_FORMULARIO_ISR"));
                datos.setNumero_formulario_isr(rs.getString("NUMERO_FORMULARIO_ISR"));
                datos.setStatus_formulario_isr(rs.getString("STATUS_FORMULARIO_ISR"));
                datos.setPeriodo_desde_iso(rs.getString("PERIODO_DESDE_ISO"));
                datos.setPeriodo_hasta_iso(rs.getString("PERIODO_HASTA_ISO"));
                datos.setFecha_recaudo_iso(rs.getString("FECHA_RECAUDO_ISO"));
                datos.setAnio_impositivo_iso(rs.getString("ANIO_IMPOSITIVO_ISO"));
                datos.setCodigo_formulario_iso(rs.getString("CODIGO_FORMULARIO_ISO"));
                datos.setNumero_formulario_iso(rs.getString("NUMERO_FORMULARIO_ISO"));
                datos.setTrimestre(rs.getString("TRIMESTRE"));
                datos.setTotal_ingresos_brutos_por_servicios_prestados(rs.getString("TOTAL_INGRESOS_BRUTOS_POR_SERVICIOS_PRESTADOS"));
                datos.setTotal_ingresos_brutos_por_ventas(rs.getString("TOTAL_INGRESOS_BRUTOS_POR_VENTAS"));
                datos.setCosto_de_ventas(rs.getString("COSTO_DE_VENTAS"));
                datos.setTotal_ingresos_brutos_costo_de_ventas(rs.getString("TOTAL_INGRESOS_BRUTOS_COSTO_DE_VENTAS"));
                datos.setPorcentaje_margen_bruto(rs.getString("PORC_MARGEN_BRUTO"));
                datos.setPorcentaje_margen_bruto_segun_contribuyente(rs.getString("PORC_MARGEN_BRUTO_SEGUN_CONTRIBUYENTE"));
                datos.setDiferencia_porcentaje_margen_bruto(rs.getString("DIFERENCIA_PORC_MARGEN_BRUTO"));
                datos.setTotal_activo(rs.getString("TOTAL_ACTIVO"));
                datos.setDepr_amor_creditos_l_y_reserva(rs.getString("DEPR_AMOR_CREDITOS_L_Y_RESERVA"));
                datos.setActivo_neto_segun_sat(rs.getString("ACTIVO_NETO_SEGUN_SAT"));
                datos.setActivo_neto_segun_contribuyente(rs.getString("ACTIVO_NETO_SEGUN_CONTRIBUYENTE"));
                datos.setDiferencia_activo_neto(rs.getString("DIFERENCIA_ACTIVO_NETO"));
                datos.setBase_imponible_segun_activo_neto_segun_sat(rs.getString("BASE_IMPONIBLE_SEGUN_ACTIVO_NETO_SEGUN_SAT"));
                datos.setImpuesto_determinado_segun_activo_neto(rs.getString("IMPUESTO_DETERMINADO_SEGUN_ACTIVO_NETO"));
                datos.setRenta_bruta(rs.getString("RENTA_BRUTA"));
                datos.setRentas_no_afectas(rs.getString("RENTAS_NO_AFECTAS"));
                datos.setTotal_ingresos_otras_categorias_de_renta(rs.getString("TOTAL_DE_INGRESOS_OTRAS_CATEGORIAS_DE_RENTA"));
                datos.setIngresos_por_negociacion_de_bienes_derechos_y_o_derechos_que_no_sean_del_giro_habitual(rs.getString("INGRESOS_POR_NEGOCIACION_DE_BIENES_Y_O_DERECHOS_QUE_NO_SEAN_DEL_GIRO_HABITUAL"));
                datos.setTotal_ingresos_brutos(rs.getString("TOTAL_INGRESOS_BRUTOS"));
                datos.setIngresos_por_resarcimientos_provenientes_de_contratos_de_seguro(rs.getString("INGRESOS_POR_RESARCIMIENTOS_PROVENIENTES_DE_CONTRATOS_DE_SEGURO"));
                datos.setIngresos_por_resarcimientos_provenientes_de_contratos_de_reaseguro(rs.getString("INGRESOS_POR_RESARCIMIENTOS_PROVENIENTES_DE_CONTRATOS_DE_REASEGURO"));
                datos.setIngresos_por_resarcimientos_provenientes_de_contratos_de_reafianzamiento(rs.getString("INGRESOS_POR_RESARCIMIENTOS_PROVENIENTES_DE_CONTRATOS_DE_REAFIANZAMIENTO"));
                datos.setPrimas_cedidas_de_seguro_y_reafianzamiento(rs.getString("PRIMAS_CEDIDAS_DE_SEGURO_Y_DE_REAFIANZAMIENTO"));
                datos.setIngresos_brutos_menos_exclusiones_segun_sat(rs.getString("INGRESOS_BRUTOS_MENOS_EXCLUSIONES_SEGUN_SAT"));
                datos.setIngresos_brutos_menos_exclusiones_segun_contribuyente(rs.getString("INGRESOS_BRUTOS_MENOS_EXCLUSIONES_SEGUN_CONTRIBUYENTE"));
                datos.setDiferencia_en_ingresos_brutos_menos_exclusiones(rs.getString("DIFERENCIA_EN_INGRESOS_BRUTOS_MENOS_EXCLUSIONES"));
                datos.setBase_imponible_segun_ingresos_brutos(rs.getString("BASE_IMPONIBLE_SEGUN_INGRESOS_BRUTOS"));
                datos.setImpuesto_segun_ingresos_brutos(rs.getString("IMPUESTO_SEGUN_INGRESOS_BRUTOS"));
                datos.setImpuesto_segun_activo_neto(rs.getString("IMPUESTO_SEGUN_ACTIVO_NETO"));
                datos.setImpuesto_determinado(rs.getString("IMPUESTO_DETERMINADO"));
                datos.setImpuesto_determinado_segun_contribuyente(rs.getString("IMPUESTO_DETERMINADO_SEGUN_CONTRIBUYENTE"));
                datos.setIusi(rs.getString("IUSI"));
                datos.setPotencial_de_impuesto_de_solidaridad(rs.getString("POTENCIAL_IMPUESTO_DE_SOLIDARIDAD"));
                datos.setSaldo_no_acreditado(rs.getString("SALDO_NO_ACREDITADO"));
                datos.setValor_de_isr_a_acreditar_en_este_periodo(rs.getString("VALOR_DE_ISR_A_ACREDITAR_EN_ESTE_PERIODO"));
                datos.setImpuesto(rs.getString("IMPUESTO"));
                datos.setImpuesto_sobre_la_renta(rs.getString("IMPUESTO_SOBRE_LA_RENTA"));
                datos.setPotencial_impuesto_de_solidaridad_4to_trimestre(rs.getString("POTENCIAL_IMPUESTO_DE_SOLIDARIDAD_4TO_TRIMESTRE"));
                datos.setPotencial_de_recaudo(rs.getString("POTENCIAL_DE_RECAUDO"));
                listDatosBaseImponible.add(datos);

            }

            conn.close();
            statement.close();

        } catch (Exception ex) {
            listDatosBaseImponible.clear();
            errorMsg("Tabla no encontrada");
            /*  errorMsg(ex.getMessage());*/
            LOG.error(ex.getMessage());
        }

        if (listDatosBaseImponible.isEmpty() || listDatosBaseImponible == null) {
            warnMsg("No existen registros para el NIT consultado");

        } else {

            listDatosEncabezado = this.generalSrvImpl.obtenerEncabezadoPeqContribuyente(pnit);

        }
    }

    public Connection getConnection() throws Exception {
        InitialContext inc = new InitialContext();
        DataSource ds = (DataSource) inc.lookup("java:comp/env/jdbc/consulta360azure");
        return ds.getConnection();
    }

    public ArrayList<BaseImponibleIsoDto> getListDatosGeneral() {
        return listDatosGeneral;
    }

    public void setListDatosGeneral(ArrayList<BaseImponibleIsoDto> listDatosGeneral) {
        this.listDatosGeneral = listDatosGeneral;
    }

    public ArrayList<BaseImponibleIsoDto> getListDatosBaseImponible() {
        return listDatosBaseImponible;
    }

    public void setListDatosBaseImponible(ArrayList<BaseImponibleIsoDto> listDatosBaseImponible) {
        this.listDatosBaseImponible = listDatosBaseImponible;
    }

    public List<PequenoContribuyenteEncabezadoDto> getListDatosEncabezado() {
        return listDatosEncabezado;
    }

    public void setListDatosEncabezado(List<PequenoContribuyenteEncabezadoDto> listDatosEncabezado) {
        this.listDatosEncabezado = listDatosEncabezado;
    }

    public int getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(int fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(int fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public ArrayList<ImpuestoIsrDto> getListaRegimenIsr() {
        return listaRegimenIsr;
    }

    public void setListaRegimenIsr(ArrayList<ImpuestoIsrDto> listaRegimenIsr) {
        this.listaRegimenIsr = listaRegimenIsr;
    }

    public ArrayList<ImpuestoIsoDto> getListaRegimenIso() {
        return listaRegimenIso;
    }

    public void setListaRegimenIso(ArrayList<ImpuestoIsoDto> listaRegimenIso) {
        this.listaRegimenIso = listaRegimenIso;
    }



    public void exportare() throws IOException {
        ContribuyenteDto contribuyente = generalSrvImpl.obtenerContribuyenteByNit(pnit);

        HSSFWorkbook libro = new HSSFWorkbook();
        HSSFSheet hoja = libro.createSheet("HOJA1");

        /*FONT STYLE*/
        HSSFFont headerFont = libro.createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        /*COLOR FONT*/
        HSSFPalette palette = libro.getCustomPalette();
        HSSFColor myColor = palette.findSimilarColor(50, 135, 155);
        short palIndex = myColor.getIndex();

        /*ROW STYLE*/
        HSSFCellStyle rowStyle = hoja.getWorkbook().createCellStyle();
        rowStyle.setFillForegroundColor(palIndex);
        rowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        rowStyle.setFont(headerFont);

        /*CELL STYLE*/
        HSSFCellStyle cellStyle = hoja.getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);

        /*FORMATO MONEDA PARA CELDA*/
        DataFormat format = libro.createDataFormat();
        HSSFCellStyle styleMoneda = libro.createCellStyle();
        styleMoneda.setDataFormat(format.getFormat("Q #,##0.00; Q (#,##0.00)"));

        hoja.setColumnWidth(0, 10000);

        /*ENCABEZADO PRINCIPAL DE LA HOJA DE EXCEL*/
        HSSFRow filaEnc1 = hoja.createRow(1);

        HSSFCell celdaEnc1 = filaEnc1.createCell(0);
        celdaEnc1.setCellValue("Nombre Cruce de Información");

        HSSFCell celdaEnc2 = filaEnc1.createCell(1);
        celdaEnc2.setCellValue("Base Imponible ISO");
        /*---------------------------------------------------*/

        HSSFRow filaEnc2 = hoja.createRow(2);

        HSSFCell celdaEnc3 = filaEnc2.createCell(0);
        celdaEnc3.setCellValue("Periodo Desde");

        HSSFCell celdaEnc4 = filaEnc2.createCell(1);
        celdaEnc4.setCellValue(fecha_ini - 1);

        HSSFCell celdaEnc5 = filaEnc2.createCell(7);
        celdaEnc5.setCellValue("Marca omiso");

        HSSFCell celdaEnc6 = filaEnc2.createCell(8);
        celdaEnc6.setCellValue(listDatosEncabezado.get(0).getOmiso());

        /*---------------------------------------------------*/
        HSSFRow filaEnc3 = hoja.createRow(3);

        HSSFCell celdaEnc7 = filaEnc3.createCell(0);
        celdaEnc7.setCellValue("Periodo Hasta");

        HSSFCell celdaEnc8 = filaEnc3.createCell(1);
        celdaEnc8.setCellValue(fecha_fin - 12);

        HSSFCell celdaEnc9 = filaEnc3.createCell(7);
        celdaEnc9.setCellValue("Marca establecimiento");

        HSSFCell celdaEnc10 = filaEnc3.createCell(8);
        celdaEnc10.setCellValue(listDatosEncabezado.get(0).getMarcaEstablecimiento());

        /*---------------------------------------------------*/
        HSSFRow filaEnc4 = hoja.createRow(4);

        HSSFCell celdaEnc11 = filaEnc4.createCell(0);
        celdaEnc11.setCellValue("NIT del contribuyente ");

        HSSFCell celdaEnc12 = filaEnc4.createCell(1);
        celdaEnc12.setCellValue(listDatosEncabezado.get(0).getNit());

        HSSFCell celdaEnc13 = filaEnc4.createCell(7);
        celdaEnc13.setCellValue("Color Plan");

        HSSFCell celdaEnc14 = filaEnc4.createCell(8);
        celdaEnc14.setCellValue(listDatosEncabezado.get(0).getNombreColor());

        /*---------------------------------------------------*/
        HSSFRow filaEnc5 = hoja.createRow(5);

        HSSFCell celdaEnc15 = filaEnc5.createCell(0);
        celdaEnc15.setCellValue("Nombre del contribuyente ");

        HSSFCell celdaEnc16 = filaEnc5.createCell(1);
        celdaEnc16.setCellValue(listDatosEncabezado.get(0).getNombreOrdenado());

        HSSFCell celdaEnc17 = filaEnc5.createCell(7);
        celdaEnc17.setCellValue("Dirección");

        HSSFCell celdaEnc18 = filaEnc5.createCell(8);
        celdaEnc18.setCellValue(listDatosEncabezado.get(0).getDireccion());

        /*---------------------------------------------------*/
        HSSFRow filaEnc6 = hoja.createRow(6);

        HSSFCell celdaEnc19 = filaEnc6.createCell(0);
        celdaEnc19.setCellValue("Gerencia");

        HSSFCell celdaEnc20 = filaEnc6.createCell(1);
        celdaEnc20.setCellValue(listDatosEncabezado.get(0).getGerencia());

        HSSFCell celdaEnc21 = filaEnc6.createCell(7);
        celdaEnc21.setCellValue("Inicio de operaciones negocio 1");
        /*FALTA CAMPO*/

 /*---------------------------------------------------*/
        HSSFRow filaEnc7 = hoja.createRow(7);

        HSSFCell celdaEnc22 = filaEnc7.createCell(0);
        celdaEnc22.setCellValue("Clasificación");

        HSSFCell celdaEnc23 = filaEnc7.createCell(1);
        celdaEnc23.setCellValue(listDatosEncabezado.get(0).getClasificacion());

        HSSFCell celdaEnc24 = filaEnc7.createCell(7);
        celdaEnc24.setCellValue("Status");

        HSSFCell celdaEnc25 = filaEnc7.createCell(8);
        celdaEnc25.setCellValue(listDatosEncabezado.get(0).getStatus());

        /*---------------------------------------------------*/
        HSSFRow filaEnc8 = hoja.createRow(8);

        HSSFCell celdaEnc26 = filaEnc8.createCell(0);
        celdaEnc26.setCellValue("Región");

        HSSFCell celdaEnc27 = filaEnc8.createCell(1);
        celdaEnc27.setCellValue(listDatosEncabezado.get(0).getRegion());

        HSSFCell celdaEnc28 = filaEnc8.createCell(7);
        celdaEnc28.setCellValue("Afiliación a ISR");
        
        HSSFCell celdaEnc29 = filaEnc8.createCell(7);
        celdaEnc29.setCellValue(listaRegimenIsr.get(0).getNombreRegimenISR());

    
 /*---------------------------------------------------*/
        HSSFRow filaEnc9 = hoja.createRow(9);

        HSSFCell celdaEnc30 = filaEnc9.createCell(0);
        celdaEnc30.setCellValue("Nombre actividad");

        HSSFCell celdaEnc31 = filaEnc9.createCell(1);
        celdaEnc31.setCellValue(listDatosEncabezado.get(0).getActividadEconomica());

        HSSFCell celdaEnc32 = filaEnc9.createCell(7);
        celdaEnc32.setCellValue("Fecha afiliación a ISR");
        
        HSSFCell celdaEnc33 = filaEnc9.createCell(8);
        celdaEnc33.setCellValue(listaRegimenIsr.get(0).getFechaAdicionAfiliacionISR());

   
 /*---------------------------------------------------*/
        HSSFRow filaEnc10 = hoja.createRow(10);

        HSSFCell celdaEnc34 = filaEnc10.createCell(0);
        celdaEnc34.setCellValue("Personería");

        HSSFCell celdaEnc35 = filaEnc10.createCell(1);
        celdaEnc35.setCellValue(listDatosEncabezado.get(0).getPersoneria());

        HSSFCell celdaEnc36 = filaEnc10.createCell(7);
        celdaEnc36.setCellValue("Afiliación a ISO");
        
        HSSFCell celdaEnc37 = filaEnc10.createCell(8);
        celdaEnc37.setCellValue(listaRegimenIso.get(0).getNombreRegimenISO());

       
 /*---------------------------------------------------*/
        HSSFRow filaEnc11 = hoja.createRow(11);

        HSSFCell celdaEnc38 = filaEnc11.createCell(0);
        celdaEnc38.setCellValue("Person Marca de no Localizadoería");

        HSSFCell celdaEnc39 = filaEnc11.createCell(1);
        celdaEnc39.setCellValue(listDatosEncabezado.get(0).getMarcaNoLocalizado());

        HSSFCell celdaEnc40 = filaEnc11.createCell(7);
        celdaEnc40.setCellValue("Fecha afiliación a ISO");
        
        HSSFCell celdaEnc41 = filaEnc11.createCell(8);
        celdaEnc41.setCellValue(listaRegimenIso.get(0).getFechaAdicionAfiliacionISO());


 /*---------------------------------------------------*/
        HSSFRow filaEnc12 = hoja.createRow(12);

        HSSFCell celdaEnc42 = filaEnc12.createCell(7);
        celdaEnc42.setCellValue("Perdida Consecutiva");

        /*falta campo*/
 /*---------------------------------------------------*/
        if (listDatosBaseImponible.isEmpty() || listDatosBaseImponible == null) {

            /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
            HSSFRow filaH = hoja.createRow(15);

            HSSFCell celdaH0 = filaH.createCell(0);
            celdaH0.setCellStyle(rowStyle);
            celdaH0.setCellValue("Periodo Desde ISR");

            HSSFCell celdaH1 = filaH.createCell(1);
            celdaH1.setCellStyle(rowStyle);
            celdaH1.setCellValue("Periodo Hasta ISR");

            HSSFCell celdaH2 = filaH.createCell(2);
            celdaH2.setCellStyle(rowStyle);
            celdaH2.setCellValue("Fecha recaudo ISR");

            HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("Año Impositivo ISR");

            HSSFCell celdaH4 = filaH.createCell(4);
            celdaH4.setCellStyle(rowStyle);
            celdaH4.setCellValue("Código formulario ISR ");

            HSSFCell celdaH5 = filaH.createCell(5);
            celdaH5.setCellStyle(rowStyle);
            celdaH5.setCellValue("Número formulario ISR");

            HSSFCell celdaH6 = filaH.createCell(6);
            celdaH6.setCellStyle(rowStyle);
            celdaH6.setCellValue("Status formulario ISR");

            HSSFCell celdaH7 = filaH.createCell(7);
            celdaH7.setCellStyle(rowStyle);
            celdaH7.setCellValue("Periodo Desde ISO");

            HSSFCell celdaH8 = filaH.createCell(8);
            celdaH8.setCellStyle(rowStyle);
            celdaH8.setCellValue("Periodo Hasta ISO");

            HSSFCell celdaH9 = filaH.createCell(9);
            celdaH9.setCellStyle(rowStyle);
            celdaH9.setCellValue("Fecha recaudo ISO");

            HSSFCell celdaH10 = filaH.createCell(10);
            celdaH10.setCellStyle(rowStyle);
            celdaH10.setCellValue("Año impositivo ISO");

            HSSFCell celdaH11 = filaH.createCell(11);
            celdaH11.setCellStyle(rowStyle);
            celdaH11.setCellValue("Código formulario ISO");

            HSSFCell celdaH12 = filaH.createCell(12);
            celdaH12.setCellStyle(rowStyle);
            celdaH12.setCellValue("Número formulario ISO");

            HSSFCell celdaH13 = filaH.createCell(13);
            celdaH13.setCellStyle(rowStyle);
            celdaH13.setCellValue("Trimestre");

            HSSFCell celdaH14 = filaH.createCell(14);
            celdaH14.setCellStyle(rowStyle);
            celdaH14.setCellValue("Total ingresos brutos por servicios prestados");

            HSSFCell celdaH15 = filaH.createCell(15);
            celdaH15.setCellStyle(rowStyle);
            celdaH15.setCellValue("Total ingresos brutos por ventas");

            HSSFCell celdaH16 = filaH.createCell(16);
            celdaH16.setCellStyle(rowStyle);
            celdaH16.setCellValue("costo de ventas");

            HSSFCell celdaH17 = filaH.createCell(17);
            celdaH17.setCellStyle(rowStyle);
            celdaH17.setCellValue("Total ingresos brutos (-) costo de ventas");

            HSSFCell celdaH18 = filaH.createCell(18);
            celdaH18.setCellStyle(rowStyle);
            celdaH18.setCellValue("% Margen Bruto");

            HSSFCell celdaH19 = filaH.createCell(19);
            celdaH19.setCellStyle(rowStyle);
            celdaH19.setCellValue("% Margen bruto según contribuyente");

            HSSFCell celdaH20 = filaH.createCell(20);
            celdaH20.setCellStyle(rowStyle);
            celdaH20.setCellValue("Diferencia % margen bruto");

            HSSFCell celdaH21 = filaH.createCell(21);
            celdaH21.setCellStyle(rowStyle);
            celdaH21.setCellValue("Total Activo");

            HSSFCell celdaH22 = filaH.createCell(22);
            celdaH22.setCellStyle(rowStyle);
            celdaH22.setCellValue("Depr.Amor.Creditos L.y reserva");

            HSSFCell celdaH23 = filaH.createCell(23);
            celdaH23.setCellStyle(rowStyle);
            celdaH23.setCellValue("Activo neto según SAT");

            HSSFCell celdaH24 = filaH.createCell(24);
            celdaH24.setCellStyle(rowStyle);
            celdaH24.setCellValue("Activo neto según contribuyente");

            HSSFCell celdaH25 = filaH.createCell(25);
            celdaH25.setCellStyle(rowStyle);
            celdaH25.setCellValue("Diferencia activo neto");

            HSSFCell celdaH26 = filaH.createCell(26);
            celdaH26.setCellStyle(rowStyle);
            celdaH26.setCellValue("Base Imponible según activo neto según SAT");

            HSSFCell celdaH27 = filaH.createCell(27);
            celdaH27.setCellStyle(rowStyle);
            celdaH27.setCellValue("Impuesto determinado según activo neto");

            HSSFCell celdaH28 = filaH.createCell(28);
            celdaH28.setCellStyle(rowStyle);
            celdaH28.setCellValue("Renta bruta");

            HSSFCell celdaH29 = filaH.createCell(29);
            celdaH29.setCellStyle(rowStyle);
            celdaH29.setCellValue("Rentas no afectas");

            HSSFCell celdaH30 = filaH.createCell(30);
            celdaH30.setCellStyle(rowStyle);
            celdaH30.setCellValue("Total de ingresos otras categorias de rentas");

            HSSFCell celdaH31 = filaH.createCell(31);
            celdaH31.setCellStyle(rowStyle);
            celdaH31.setCellValue("Ingresos por negociación de bienes y/o derechos que no sean del giro habitual");

            HSSFCell celdaH32 = filaH.createCell(32);
            celdaH32.setCellStyle(rowStyle);
            celdaH32.setCellValue("Total ingresos brutos");

            HSSFCell celdaH33 = filaH.createCell(33);
            celdaH33.setCellStyle(rowStyle);
            celdaH33.setCellValue("Ingresos por resarcimientos provenientes de contratos de seguro");

            HSSFCell celdaH34 = filaH.createCell(34);
            celdaH34.setCellStyle(rowStyle);
            celdaH34.setCellValue("Ingresos por resarcimientos provenientes de contratos de reaseguro");

            HSSFCell celdaH35 = filaH.createCell(35);
            celdaH35.setCellStyle(rowStyle);
            celdaH35.setCellValue("Ingresos por resarcimiento provenientes de contratos de reafianzamiento");

            HSSFCell celdaH36 = filaH.createCell(36);
            celdaH36.setCellStyle(rowStyle);
            celdaH36.setCellValue("Primas cedidas de seguro y de reafianzamiento");

            HSSFCell celdaH37 = filaH.createCell(37);
            celdaH37.setCellStyle(rowStyle);
            celdaH37.setCellValue("Ingresos brutos menos exclusiones según SAT");

            HSSFCell celdaH38 = filaH.createCell(38);
            celdaH38.setCellStyle(rowStyle);
            celdaH38.setCellValue("Ingresos brutos menos exclusiones según contribuyente");

            HSSFCell celdaH39 = filaH.createCell(39);
            celdaH39.setCellStyle(rowStyle);
            celdaH39.setCellValue("Diferencia en ingresos brutos menos exclusiones");

            HSSFCell celdaH40 = filaH.createCell(40);
            celdaH40.setCellStyle(rowStyle);
            celdaH40.setCellValue("Base Imponible (según ingresos brutos)");

            HSSFCell celdaH41 = filaH.createCell(41);
            celdaH41.setCellStyle(rowStyle);
            celdaH41.setCellValue("Impuesto (según ingresos brutos)");

            HSSFCell celdaH42 = filaH.createCell(42);
            celdaH42.setCellStyle(rowStyle);
            celdaH42.setCellValue("Impuesto (según Activo Neto)");

            HSSFCell celdaH43 = filaH.createCell(43);
            celdaH43.setCellStyle(rowStyle);
            celdaH43.setCellValue("Impuesto determinado");

            HSSFCell celdaH44 = filaH.createCell(44);
            celdaH44.setCellStyle(rowStyle);
            celdaH44.setCellValue("Impuesto determinado según contribuyente");

            HSSFCell celdaH45 = filaH.createCell(45);
            celdaH45.setCellStyle(rowStyle);
            celdaH45.setCellValue("IUSI");

            HSSFCell celdaH46 = filaH.createCell(46);
            celdaH46.setCellStyle(rowStyle);
            celdaH46.setCellValue("Potencial Impuesto de Solidaridad");

            HSSFCell celdaH47 = filaH.createCell(47);
            celdaH47.setCellStyle(rowStyle);
            celdaH47.setCellValue("Saldo no acreditado");

            HSSFCell celdaH48 = filaH.createCell(48);
            celdaH48.setCellStyle(rowStyle);
            celdaH48.setCellValue("Valor de ISR a acreditar en este periodo");

            HSSFCell celdaH49 = filaH.createCell(49);
            celdaH49.setCellStyle(rowStyle);
            celdaH49.setCellValue("Impuesto");

            HSSFCell celdaH50 = filaH.createCell(50);
            celdaH50.setCellStyle(rowStyle);
            celdaH50.setCellValue("Impuesto sobre la renta");

            HSSFCell celdaH51 = filaH.createCell(51);
            celdaH51.setCellStyle(rowStyle);
            celdaH51.setCellValue("Potencial impuesto de solidaridad 4to. Trimestre");

            HSSFCell celdaH52 = filaH.createCell(52);
            celdaH52.setCellStyle(rowStyle);
            celdaH52.setCellValue("Potencial de recaudo");

            for (int x = 0; x < listDatosBaseImponible.size(); x++) {

                HSSFRow fila = hoja.createRow(x + 14);

                HSSFCell celda0 = fila.createCell(0);
                celda0.setCellStyle(cellStyle);
                celda0.setCellValue(listDatosBaseImponible.get(x).getPeriodo_desde_isr());

                HSSFCell celda1 = fila.createCell(1);
                celda1.setCellStyle(cellStyle);
                celda1.setCellValue(listDatosBaseImponible.get(x).getPeriodo_hasta_isr());

                HSSFCell celda2 = fila.createCell(2);
                celda2.setCellStyle(cellStyle);
                celda2.setCellValue(listDatosBaseImponible.get(x).getFecha_recaudo_isr());

                HSSFCell celda3 = fila.createCell(3);
                celda3.setCellStyle(cellStyle);
                celda3.setCellValue(listDatosBaseImponible.get(x).getAnio_impositivo_isr());

                HSSFCell celda4 = fila.createCell(4);
                celda4.setCellStyle(styleMoneda);
                celda4.setCellValue(listDatosBaseImponible.get(x).getCodigo_formulario_isr());

                HSSFCell celda5 = fila.createCell(5);
                celda5.setCellStyle(cellStyle);
                celda5.setCellValue(listDatosBaseImponible.get(x).getNumero_formulario_isr());

                HSSFCell celda6 = fila.createCell(6);
                celda6.setCellStyle(styleMoneda);
                celda6.setCellValue(listDatosBaseImponible.get(x).getStatus_formulario_isr());

                HSSFCell celda7 = fila.createCell(7);
                celda7.setCellStyle(cellStyle);
                celda7.setCellValue(listDatosBaseImponible.get(x).getPeriodo_desde_iso());

                HSSFCell celda8 = fila.createCell(8);
                celda8.setCellStyle(styleMoneda);
                celda8.setCellValue(listDatosBaseImponible.get(x).getPeriodo_hasta_iso());

                HSSFCell celda9 = fila.createCell(9);
                celda9.setCellStyle(styleMoneda);
                celda9.setCellValue(listDatosBaseImponible.get(x).getFecha_recaudo_iso());

                HSSFCell celda10 = fila.createCell(10);
                celda10.setCellStyle(styleMoneda);
                celda10.setCellValue(listDatosBaseImponible.get(x).getAnio_impositivo_iso());

                HSSFCell celda11 = fila.createCell(11);
                celda11.setCellStyle(styleMoneda);
                celda11.setCellValue(listDatosBaseImponible.get(x).getCodigo_formulario_iso());

                HSSFCell celda12 = fila.createCell(12);
                celda12.setCellStyle(styleMoneda);
                celda12.setCellValue(listDatosBaseImponible.get(x).getNumero_formulario_iso());

                HSSFCell celda13 = fila.createCell(13);
                celda13.setCellStyle(styleMoneda);
                celda13.setCellValue(listDatosBaseImponible.get(x).getTrimestre());

                HSSFCell celda14 = fila.createCell(14);
                celda14.setCellStyle(styleMoneda);
                celda14.setCellValue(listDatosBaseImponible.get(x).getTotal_ingresos_brutos_por_servicios_prestados());

                HSSFCell celda15 = fila.createCell(15);
                celda15.setCellStyle(styleMoneda);
                celda15.setCellValue(listDatosBaseImponible.get(x).getTotal_ingresos_brutos_por_ventas());

                HSSFCell celda16 = fila.createCell(16);
                celda16.setCellStyle(styleMoneda);
                celda16.setCellValue(listDatosBaseImponible.get(x).getCosto_de_ventas());

                HSSFCell celda17 = fila.createCell(17);
                celda17.setCellStyle(styleMoneda);
                celda17.setCellValue(listDatosBaseImponible.get(x).getTotal_ingresos_brutos_costo_de_ventas());

                HSSFCell celda18 = fila.createCell(18);
                celda18.setCellStyle(styleMoneda);
                celda18.setCellValue(listDatosBaseImponible.get(x).getPorcentaje_margen_bruto());

                HSSFCell celda19 = fila.createCell(19);
                celda19.setCellStyle(styleMoneda);
                celda19.setCellValue(listDatosBaseImponible.get(x).getPorcentaje_margen_bruto_segun_contribuyente());

                HSSFCell celda20 = fila.createCell(20);
                celda20.setCellStyle(styleMoneda);
                celda20.setCellValue(listDatosBaseImponible.get(x).getDiferencia_porcentaje_margen_bruto());

                HSSFCell celda21 = fila.createCell(21);
                celda21.setCellStyle(styleMoneda);
                celda21.setCellValue(listDatosBaseImponible.get(x).getTotal_activo());

                HSSFCell celda22 = fila.createCell(21);
                celda22.setCellStyle(styleMoneda);
                celda22.setCellValue(listDatosBaseImponible.get(x).getDepr_amor_creditos_l_y_reserva());

                HSSFCell celda23 = fila.createCell(23);
                celda23.setCellStyle(styleMoneda);
                celda23.setCellValue(listDatosBaseImponible.get(x).getActivo_neto_segun_sat());

                HSSFCell celda24 = fila.createCell(24);
                celda24.setCellStyle(styleMoneda);
                celda24.setCellValue(listDatosBaseImponible.get(x).getActivo_neto_segun_contribuyente());

                HSSFCell celda25 = fila.createCell(25);
                celda25.setCellStyle(styleMoneda);
                celda25.setCellValue(listDatosBaseImponible.get(x).getDiferencia_activo_neto());

                HSSFCell celda26 = fila.createCell(26);
                celda26.setCellStyle(styleMoneda);
                celda26.setCellValue(listDatosBaseImponible.get(x).getBase_imponible_segun_activo_neto_segun_sat());

                HSSFCell celda27 = fila.createCell(27);
                celda27.setCellStyle(styleMoneda);
                celda27.setCellValue(listDatosBaseImponible.get(x).getImpuesto_determinado_segun_activo_neto());

                HSSFCell celda28 = fila.createCell(28);
                celda28.setCellStyle(styleMoneda);
                celda28.setCellValue(listDatosBaseImponible.get(x).getRenta_bruta());

                HSSFCell celda29 = fila.createCell(29);
                celda29.setCellStyle(styleMoneda);
                celda29.setCellValue(listDatosBaseImponible.get(x).getRentas_no_afectas());

                HSSFCell celda30 = fila.createCell(30);
                celda30.setCellStyle(styleMoneda);
                celda30.setCellValue(listDatosBaseImponible.get(x).getTotal_ingresos_otras_categorias_de_renta());

                HSSFCell celda31 = fila.createCell(31);
                celda31.setCellStyle(styleMoneda);
                celda31.setCellValue(listDatosBaseImponible.get(x).getIngresos_por_negociacion_de_bienes_derechos_y_o_derechos_que_no_sean_del_giro_habitual());

                HSSFCell celda32 = fila.createCell(32);
                celda32.setCellStyle(styleMoneda);
                celda32.setCellValue(listDatosBaseImponible.get(x).getTotal_ingresos_brutos());

                HSSFCell celda33 = fila.createCell(33);
                celda33.setCellStyle(styleMoneda);
                celda33.setCellValue(listDatosBaseImponible.get(x).getIngresos_por_resarcimientos_provenientes_de_contratos_de_seguro());

                HSSFCell celda34 = fila.createCell(34);
                celda34.setCellStyle(styleMoneda);
                celda34.setCellValue(listDatosBaseImponible.get(x).getIngresos_por_resarcimientos_provenientes_de_contratos_de_reaseguro());

                HSSFCell celda35 = fila.createCell(35);
                celda35.setCellStyle(styleMoneda);
                celda35.setCellValue(listDatosBaseImponible.get(x).getIngresos_por_resarcimientos_provenientes_de_contratos_de_reafianzamiento());

                HSSFCell celda36 = fila.createCell(36);
                celda36.setCellStyle(styleMoneda);
                celda36.setCellValue(listDatosBaseImponible.get(x).getPrimas_cedidas_de_seguro_y_reafianzamiento());

                HSSFCell celda37 = fila.createCell(37);
                celda37.setCellStyle(styleMoneda);
                celda37.setCellValue(listDatosBaseImponible.get(x).getIngresos_brutos_menos_exclusiones_segun_sat());

                HSSFCell celda38 = fila.createCell(38);
                celda38.setCellStyle(styleMoneda);
                celda38.setCellValue(listDatosBaseImponible.get(x).getIngresos_brutos_menos_exclusiones_segun_contribuyente());

                HSSFCell celda39 = fila.createCell(39);
                celda39.setCellStyle(styleMoneda);
                celda39.setCellValue(listDatosBaseImponible.get(x).getDiferencia_en_ingresos_brutos_menos_exclusiones());

                HSSFCell celda40 = fila.createCell(40);
                celda40.setCellStyle(styleMoneda);
                celda40.setCellValue(listDatosBaseImponible.get(x).getBase_imponible_segun_ingresos_brutos());

                HSSFCell celda41 = fila.createCell(41);
                celda41.setCellStyle(styleMoneda);
                celda41.setCellValue(listDatosBaseImponible.get(x).getImpuesto_segun_ingresos_brutos());

                HSSFCell celda42 = fila.createCell(42);
                celda42.setCellStyle(styleMoneda);
                celda42.setCellValue(listDatosBaseImponible.get(x).getImpuesto_segun_activo_neto());

                HSSFCell celda43 = fila.createCell(43);
                celda43.setCellStyle(styleMoneda);
                celda43.setCellValue(listDatosBaseImponible.get(x).getImpuesto_determinado());

                HSSFCell celda44 = fila.createCell(44);
                celda44.setCellStyle(styleMoneda);
                celda44.setCellValue(listDatosBaseImponible.get(x).getImpuesto_determinado_segun_contribuyente());

                HSSFCell celda45 = fila.createCell(45);
                celda45.setCellStyle(styleMoneda);
                celda45.setCellValue(listDatosBaseImponible.get(x).getIusi());

                HSSFCell celda46 = fila.createCell(46);
                celda46.setCellStyle(styleMoneda);
                celda46.setCellValue(listDatosBaseImponible.get(x).getPotencial_de_impuesto_de_solidaridad());

                HSSFCell celda47 = fila.createCell(47);
                celda47.setCellStyle(styleMoneda);
                celda47.setCellValue(listDatosBaseImponible.get(x).getSaldo_no_acreditado());

                HSSFCell celda48 = fila.createCell(48);
                celda48.setCellStyle(styleMoneda);
                celda48.setCellValue(listDatosBaseImponible.get(x).getValor_de_isr_a_acreditar_en_este_periodo());

                HSSFCell celda49 = fila.createCell(49);
                celda49.setCellStyle(styleMoneda);
                celda49.setCellValue(listDatosBaseImponible.get(x).getImpuesto());

                HSSFCell celda50 = fila.createCell(50);
                celda50.setCellStyle(styleMoneda);
                celda50.setCellValue(listDatosBaseImponible.get(x).getImpuesto_sobre_la_renta());

                HSSFCell celda51 = fila.createCell(51);
                celda51.setCellStyle(styleMoneda);
                celda51.setCellValue(listDatosBaseImponible.get(x).getPotencial_impuesto_de_solidaridad_4to_trimestre());

                HSSFCell celda52 = fila.createCell(52);
                celda52.setCellStyle(styleMoneda);
                celda52.setCellValue(listDatosBaseImponible.get(x).getPotencial_de_recaudo());

            }
        }

        if (listDatosGeneral.isEmpty() || listDatosGeneral == null) {

            /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
            HSSFRow filaH = hoja.createRow(14);

            HSSFCell celdaH0 = filaH.createCell(0);
            celdaH0.setCellStyle(rowStyle);
            celdaH0.setCellValue("Periodo Desde ISR");

            HSSFCell celdaH1 = filaH.createCell(1);
            celdaH1.setCellStyle(rowStyle);
            celdaH1.setCellValue("Periodo Hasta ISR");

            HSSFCell celdaH2 = filaH.createCell(2);
            celdaH2.setCellStyle(rowStyle);
            celdaH2.setCellValue("Fecha recaudo ISR");

            HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("Año Impositivo ISR");

            HSSFCell celdaH4 = filaH.createCell(4);
            celdaH4.setCellStyle(rowStyle);
            celdaH4.setCellValue("Código formulario ISR ");

            HSSFCell celdaH5 = filaH.createCell(5);
            celdaH5.setCellStyle(rowStyle);
            celdaH5.setCellValue("Número formulario ISR");

            HSSFCell celdaH6 = filaH.createCell(6);
            celdaH6.setCellStyle(rowStyle);
            celdaH6.setCellValue("Status formulario ISR");

            HSSFCell celdaH7 = filaH.createCell(7);
            celdaH7.setCellStyle(rowStyle);
            celdaH7.setCellValue("Periodo Desde ISO");

            HSSFCell celdaH8 = filaH.createCell(8);
            celdaH8.setCellStyle(rowStyle);
            celdaH8.setCellValue("Periodo Hasta ISO");

            HSSFCell celdaH9 = filaH.createCell(9);
            celdaH9.setCellStyle(rowStyle);
            celdaH9.setCellValue("Fecha recaudo ISO");

            HSSFCell celdaH10 = filaH.createCell(10);
            celdaH10.setCellStyle(rowStyle);
            celdaH10.setCellValue("Año impositivo ISO");

            HSSFCell celdaH11 = filaH.createCell(11);
            celdaH11.setCellStyle(rowStyle);
            celdaH11.setCellValue("Código formulario ISO");

            HSSFCell celdaH12 = filaH.createCell(12);
            celdaH12.setCellStyle(rowStyle);
            celdaH12.setCellValue("Número formulario ISO");

            HSSFCell celdaH13 = filaH.createCell(13);
            celdaH13.setCellStyle(rowStyle);
            celdaH13.setCellValue("Trimestre");

            HSSFCell celdaH14 = filaH.createCell(14);
            celdaH14.setCellStyle(rowStyle);
            celdaH14.setCellValue("Total ingresos brutos por servicios prestados");

            HSSFCell celdaH15 = filaH.createCell(15);
            celdaH15.setCellStyle(rowStyle);
            celdaH15.setCellValue("Total ingresos brutos por ventas");

            HSSFCell celdaH16 = filaH.createCell(16);
            celdaH16.setCellStyle(rowStyle);
            celdaH16.setCellValue("costo de ventas");

            HSSFCell celdaH17 = filaH.createCell(17);
            celdaH17.setCellStyle(rowStyle);
            celdaH17.setCellValue("Total ingresos brutos (-) costo de ventas");

            HSSFCell celdaH18 = filaH.createCell(18);
            celdaH18.setCellStyle(rowStyle);
            celdaH18.setCellValue("% Margen Bruto");

            HSSFCell celdaH19 = filaH.createCell(19);
            celdaH19.setCellStyle(rowStyle);
            celdaH19.setCellValue("% Margen bruto según contribuyente");

            HSSFCell celdaH20 = filaH.createCell(20);
            celdaH20.setCellStyle(rowStyle);
            celdaH20.setCellValue("Diferencia % margen bruto");

            HSSFCell celdaH21 = filaH.createCell(21);
            celdaH21.setCellStyle(rowStyle);
            celdaH21.setCellValue("Total Activo");

            HSSFCell celdaH22 = filaH.createCell(22);
            celdaH22.setCellStyle(rowStyle);
            celdaH22.setCellValue("Depr.Amor.Creditos L.y reserva");

            HSSFCell celdaH23 = filaH.createCell(23);
            celdaH23.setCellStyle(rowStyle);
            celdaH23.setCellValue("Activo neto según SAT");

            HSSFCell celdaH24 = filaH.createCell(24);
            celdaH24.setCellStyle(rowStyle);
            celdaH24.setCellValue("Activo neto según contribuyente");

            HSSFCell celdaH25 = filaH.createCell(25);
            celdaH25.setCellStyle(rowStyle);
            celdaH25.setCellValue("Diferencia activo neto");

            HSSFCell celdaH26 = filaH.createCell(26);
            celdaH26.setCellStyle(rowStyle);
            celdaH26.setCellValue("Base Imponible según activo neto según SAT");

            HSSFCell celdaH27 = filaH.createCell(27);
            celdaH27.setCellStyle(rowStyle);
            celdaH27.setCellValue("Impuesto determinado según activo neto");

            HSSFCell celdaH28 = filaH.createCell(28);
            celdaH28.setCellStyle(rowStyle);
            celdaH28.setCellValue("Renta bruta");

            HSSFCell celdaH29 = filaH.createCell(29);
            celdaH29.setCellStyle(rowStyle);
            celdaH29.setCellValue("Rentas no afectas");

            HSSFCell celdaH30 = filaH.createCell(30);
            celdaH30.setCellStyle(rowStyle);
            celdaH30.setCellValue("Total de ingresos otras categorias de rentas");

            HSSFCell celdaH31 = filaH.createCell(31);
            celdaH31.setCellStyle(rowStyle);
            celdaH31.setCellValue("Ingresos por negociación de bienes y/o derechos que no sean del giro habitual");

            HSSFCell celdaH32 = filaH.createCell(32);
            celdaH32.setCellStyle(rowStyle);
            celdaH32.setCellValue("Total ingresos brutos");

            HSSFCell celdaH33 = filaH.createCell(33);
            celdaH33.setCellStyle(rowStyle);
            celdaH33.setCellValue("Ingresos por resarcimientos provenientes de contratos de seguro");

            HSSFCell celdaH34 = filaH.createCell(34);
            celdaH34.setCellStyle(rowStyle);
            celdaH34.setCellValue("Ingresos por resarcimientos provenientes de contratos de reaseguro");

            HSSFCell celdaH35 = filaH.createCell(35);
            celdaH35.setCellStyle(rowStyle);
            celdaH35.setCellValue("Ingresos por resarcimiento provenientes de contratos de reafianzamiento");

            HSSFCell celdaH36 = filaH.createCell(36);
            celdaH36.setCellStyle(rowStyle);
            celdaH36.setCellValue("Primas cedidas de seguro y de reafianzamiento");

            HSSFCell celdaH37 = filaH.createCell(37);
            celdaH37.setCellStyle(rowStyle);
            celdaH37.setCellValue("Ingresos brutos menos exclusiones según SAT");

            HSSFCell celdaH38 = filaH.createCell(38);
            celdaH38.setCellStyle(rowStyle);
            celdaH38.setCellValue("Ingresos brutos menos exclusiones según contribuyente");

            HSSFCell celdaH39 = filaH.createCell(39);
            celdaH39.setCellStyle(rowStyle);
            celdaH39.setCellValue("Diferencia en ingresos brutos menos exclusiones");

            HSSFCell celdaH40 = filaH.createCell(40);
            celdaH40.setCellStyle(rowStyle);
            celdaH40.setCellValue("Base Imponible (según ingresos brutos)");

            HSSFCell celdaH41 = filaH.createCell(41);
            celdaH41.setCellStyle(rowStyle);
            celdaH41.setCellValue("Impuesto (según ingresos brutos)");

            HSSFCell celdaH42 = filaH.createCell(42);
            celdaH42.setCellStyle(rowStyle);
            celdaH42.setCellValue("Impuesto (según Activo Neto)");

            HSSFCell celdaH43 = filaH.createCell(43);
            celdaH43.setCellStyle(rowStyle);
            celdaH43.setCellValue("Impuesto determinado");

            HSSFCell celdaH44 = filaH.createCell(44);
            celdaH44.setCellStyle(rowStyle);
            celdaH44.setCellValue("Impuesto determinado según contribuyente");

            HSSFCell celdaH45 = filaH.createCell(45);
            celdaH45.setCellStyle(rowStyle);
            celdaH45.setCellValue("iusi");

            HSSFCell celdaH46 = filaH.createCell(46);
            celdaH46.setCellStyle(rowStyle);
            celdaH46.setCellValue("Potencial Impuesto de Solidaridad");

            HSSFCell celdaH47 = filaH.createCell(47);
            celdaH47.setCellStyle(rowStyle);
            celdaH47.setCellValue("Saldo no acreditado");

            HSSFCell celdaH48 = filaH.createCell(48);
            celdaH48.setCellStyle(rowStyle);
            celdaH48.setCellValue("Valor de ISR a acreditar en este periodo");

            HSSFCell celdaH49 = filaH.createCell(49);
            celdaH49.setCellStyle(rowStyle);
            celdaH49.setCellValue("Impuesto");

            HSSFCell celdaH50 = filaH.createCell(50);
            celdaH50.setCellStyle(rowStyle);
            celdaH50.setCellValue("Impuesto sobre la renta");

            HSSFCell celdaH51 = filaH.createCell(51);
            celdaH51.setCellStyle(rowStyle);
            celdaH51.setCellValue("Potencial impuesto de solidaridad 4to. Trimestre");

            HSSFCell celdaH52 = filaH.createCell(52);
            celdaH52.setCellStyle(rowStyle);
            celdaH52.setCellValue("Potencial de recaudo");

            for (int x = 0; x < listDatosBaseImponible.size(); x++) {

                HSSFRow fila = hoja.createRow(x + 15);

                HSSFCell celda0 = fila.createCell(0);
                celda0.setCellStyle(cellStyle);
                celda0.setCellValue(listDatosBaseImponible.get(x).getPeriodo_desde_isr());

                HSSFCell celda1 = fila.createCell(1);
                celda1.setCellStyle(cellStyle);
                celda1.setCellValue(listDatosBaseImponible.get(x).getPeriodo_hasta_isr());

                HSSFCell celda2 = fila.createCell(2);
                celda2.setCellStyle(cellStyle);
                celda2.setCellValue(listDatosBaseImponible.get(x).getFecha_recaudo_isr());

                HSSFCell celda3 = fila.createCell(3);
                celda3.setCellStyle(cellStyle);
                celda3.setCellValue(listDatosBaseImponible.get(x).getAnio_impositivo_isr());

                HSSFCell celda4 = fila.createCell(4);
                celda4.setCellStyle(styleMoneda);
                celda4.setCellValue(listDatosBaseImponible.get(x).getCodigo_formulario_isr());

                HSSFCell celda5 = fila.createCell(5);
                celda5.setCellStyle(cellStyle);
                celda5.setCellValue(listDatosBaseImponible.get(x).getNumero_formulario_isr());

                HSSFCell celda6 = fila.createCell(6);
                celda6.setCellStyle(styleMoneda);
                celda6.setCellValue(listDatosBaseImponible.get(x).getStatus_formulario_isr());

                HSSFCell celda7 = fila.createCell(7);
                celda7.setCellStyle(cellStyle);
                celda7.setCellValue(listDatosBaseImponible.get(x).getPeriodo_desde_iso());

                HSSFCell celda8 = fila.createCell(8);
                celda8.setCellStyle(styleMoneda);
                celda8.setCellValue(listDatosBaseImponible.get(x).getPeriodo_hasta_iso());

                HSSFCell celda9 = fila.createCell(9);
                celda9.setCellStyle(styleMoneda);
                celda9.setCellValue(listDatosBaseImponible.get(x).getFecha_recaudo_iso());

                HSSFCell celda10 = fila.createCell(10);
                celda10.setCellStyle(styleMoneda);
                celda10.setCellValue(listDatosBaseImponible.get(x).getAnio_impositivo_iso());

                HSSFCell celda11 = fila.createCell(11);
                celda11.setCellStyle(styleMoneda);
                celda11.setCellValue(listDatosBaseImponible.get(x).getCodigo_formulario_iso());

                HSSFCell celda12 = fila.createCell(12);
                celda12.setCellStyle(styleMoneda);
                celda12.setCellValue(listDatosBaseImponible.get(x).getNumero_formulario_iso());

                HSSFCell celda13 = fila.createCell(13);
                celda13.setCellStyle(styleMoneda);
                celda13.setCellValue(listDatosBaseImponible.get(x).getTrimestre());

                HSSFCell celda14 = fila.createCell(14);
                celda14.setCellStyle(styleMoneda);
                celda14.setCellValue(listDatosBaseImponible.get(x).getTotal_ingresos_brutos_por_servicios_prestados());

                HSSFCell celda15 = fila.createCell(15);
                celda15.setCellStyle(styleMoneda);
                celda15.setCellValue(listDatosBaseImponible.get(x).getTotal_ingresos_brutos_por_ventas());

                HSSFCell celda16 = fila.createCell(16);
                celda16.setCellStyle(styleMoneda);
                celda16.setCellValue(listDatosBaseImponible.get(x).getCosto_de_ventas());

                HSSFCell celda17 = fila.createCell(17);
                celda17.setCellStyle(styleMoneda);
                celda17.setCellValue(listDatosBaseImponible.get(x).getTotal_ingresos_brutos_costo_de_ventas());

                HSSFCell celda18 = fila.createCell(18);
                celda18.setCellStyle(styleMoneda);
                celda18.setCellValue(listDatosBaseImponible.get(x).getPorcentaje_margen_bruto());

                HSSFCell celda19 = fila.createCell(19);
                celda19.setCellStyle(styleMoneda);
                celda19.setCellValue(listDatosBaseImponible.get(x).getPorcentaje_margen_bruto_segun_contribuyente());

                HSSFCell celda20 = fila.createCell(20);
                celda20.setCellStyle(styleMoneda);
                celda20.setCellValue(listDatosBaseImponible.get(x).getDiferencia_porcentaje_margen_bruto());

                HSSFCell celda21 = fila.createCell(21);
                celda21.setCellStyle(styleMoneda);
                celda21.setCellValue(listDatosBaseImponible.get(x).getTotal_activo());

                HSSFCell celda22 = fila.createCell(21);
                celda22.setCellStyle(styleMoneda);
                celda22.setCellValue(listDatosBaseImponible.get(x).getDepr_amor_creditos_l_y_reserva());

                HSSFCell celda23 = fila.createCell(23);
                celda23.setCellStyle(styleMoneda);
                celda23.setCellValue(listDatosBaseImponible.get(x).getActivo_neto_segun_sat());

                HSSFCell celda24 = fila.createCell(24);
                celda24.setCellStyle(styleMoneda);
                celda24.setCellValue(listDatosBaseImponible.get(x).getActivo_neto_segun_contribuyente());

                HSSFCell celda25 = fila.createCell(25);
                celda25.setCellStyle(styleMoneda);
                celda25.setCellValue(listDatosBaseImponible.get(x).getDiferencia_activo_neto());

                HSSFCell celda26 = fila.createCell(26);
                celda26.setCellStyle(styleMoneda);
                celda26.setCellValue(listDatosBaseImponible.get(x).getBase_imponible_segun_activo_neto_segun_sat());

                HSSFCell celda27 = fila.createCell(27);
                celda27.setCellStyle(styleMoneda);
                celda27.setCellValue(listDatosBaseImponible.get(x).getImpuesto_determinado_segun_activo_neto());

                HSSFCell celda28 = fila.createCell(28);
                celda28.setCellStyle(styleMoneda);
                celda28.setCellValue(listDatosBaseImponible.get(x).getRenta_bruta());

                HSSFCell celda29 = fila.createCell(29);
                celda29.setCellStyle(styleMoneda);
                celda29.setCellValue(listDatosBaseImponible.get(x).getRentas_no_afectas());

                HSSFCell celda30 = fila.createCell(30);
                celda30.setCellStyle(styleMoneda);
                celda30.setCellValue(listDatosBaseImponible.get(x).getTotal_ingresos_otras_categorias_de_renta());

                HSSFCell celda31 = fila.createCell(31);
                celda31.setCellStyle(styleMoneda);
                celda31.setCellValue(listDatosBaseImponible.get(x).getIngresos_por_negociacion_de_bienes_derechos_y_o_derechos_que_no_sean_del_giro_habitual());

                HSSFCell celda32 = fila.createCell(32);
                celda32.setCellStyle(styleMoneda);
                celda32.setCellValue(listDatosBaseImponible.get(x).getTotal_ingresos_brutos());

                HSSFCell celda33 = fila.createCell(33);
                celda33.setCellStyle(styleMoneda);
                celda33.setCellValue(listDatosBaseImponible.get(x).getIngresos_por_resarcimientos_provenientes_de_contratos_de_seguro());

                HSSFCell celda34 = fila.createCell(34);
                celda34.setCellStyle(styleMoneda);
                celda34.setCellValue(listDatosBaseImponible.get(x).getIngresos_por_resarcimientos_provenientes_de_contratos_de_reaseguro());

                HSSFCell celda35 = fila.createCell(35);
                celda35.setCellStyle(styleMoneda);
                celda35.setCellValue(listDatosBaseImponible.get(x).getIngresos_por_resarcimientos_provenientes_de_contratos_de_reafianzamiento());

                HSSFCell celda36 = fila.createCell(36);
                celda36.setCellStyle(styleMoneda);
                celda36.setCellValue(listDatosBaseImponible.get(x).getPrimas_cedidas_de_seguro_y_reafianzamiento());

                HSSFCell celda37 = fila.createCell(37);
                celda37.setCellStyle(styleMoneda);
                celda37.setCellValue(listDatosBaseImponible.get(x).getIngresos_brutos_menos_exclusiones_segun_sat());

                HSSFCell celda38 = fila.createCell(38);
                celda38.setCellStyle(styleMoneda);
                celda38.setCellValue(listDatosBaseImponible.get(x).getIngresos_brutos_menos_exclusiones_segun_contribuyente());

                HSSFCell celda39 = fila.createCell(39);
                celda39.setCellStyle(styleMoneda);
                celda39.setCellValue(listDatosBaseImponible.get(x).getDiferencia_en_ingresos_brutos_menos_exclusiones());

                HSSFCell celda40 = fila.createCell(40);
                celda40.setCellStyle(styleMoneda);
                celda40.setCellValue(listDatosBaseImponible.get(x).getBase_imponible_segun_ingresos_brutos());

                HSSFCell celda41 = fila.createCell(41);
                celda41.setCellStyle(styleMoneda);
                celda41.setCellValue(listDatosBaseImponible.get(x).getImpuesto_segun_ingresos_brutos());

                HSSFCell celda42 = fila.createCell(42);
                celda42.setCellStyle(styleMoneda);
                celda42.setCellValue(listDatosBaseImponible.get(x).getImpuesto_segun_activo_neto());

                HSSFCell celda43 = fila.createCell(43);
                celda43.setCellStyle(styleMoneda);
                celda43.setCellValue(listDatosBaseImponible.get(x).getImpuesto_determinado());

                HSSFCell celda44 = fila.createCell(44);
                celda44.setCellStyle(styleMoneda);
                celda44.setCellValue(listDatosBaseImponible.get(x).getImpuesto_determinado_segun_contribuyente());

                HSSFCell celda45 = fila.createCell(45);
                celda45.setCellStyle(styleMoneda);
                celda45.setCellValue(listDatosBaseImponible.get(x).getIusi());

                HSSFCell celda46 = fila.createCell(46);
                celda46.setCellStyle(styleMoneda);
                celda46.setCellValue(listDatosBaseImponible.get(x).getPotencial_de_impuesto_de_solidaridad());

                HSSFCell celda47 = fila.createCell(47);
                celda47.setCellStyle(styleMoneda);
                celda47.setCellValue(listDatosBaseImponible.get(x).getSaldo_no_acreditado());

                HSSFCell celda48 = fila.createCell(48);
                celda48.setCellStyle(styleMoneda);
                celda48.setCellValue(listDatosBaseImponible.get(x).getValor_de_isr_a_acreditar_en_este_periodo());

                HSSFCell celda49 = fila.createCell(49);
                celda49.setCellStyle(styleMoneda);
                celda49.setCellValue(listDatosBaseImponible.get(x).getImpuesto());

                HSSFCell celda50 = fila.createCell(50);
                celda50.setCellStyle(styleMoneda);
                celda50.setCellValue(listDatosBaseImponible.get(x).getImpuesto_sobre_la_renta());

                HSSFCell celda51 = fila.createCell(51);
                celda51.setCellStyle(styleMoneda);
                celda51.setCellValue(listDatosBaseImponible.get(x).getPotencial_impuesto_de_solidaridad_4to_trimestre());

                HSSFCell celda52 = fila.createCell(52);
                celda52.setCellStyle(styleMoneda);
                celda52.setCellValue(listDatosBaseImponible.get(x).getPotencial_de_recaudo());

            }
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        externalContext.setResponseContentType("application/vnd.ms-excel; charset=utf-8");

        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"Base_imponible_ISO.xls\"");

        libro.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();
    }

}
