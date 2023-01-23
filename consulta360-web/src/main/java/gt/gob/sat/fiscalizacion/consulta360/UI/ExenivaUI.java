/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.ContribuyenteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ExenivaDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ImpuestoIvaGeneralDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IngresosFelvsDeclaradoIvaPequeDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.PequenoContribuyenteEncabezadoDto;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ExenivaUI extends AbstractUI implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(VerificacionesEnCampoUI.class);
    private String pnit = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private ArrayList<IngresosFelvsDeclaradoIvaPequeDto> listDatosGeneral = new ArrayList();
    private ArrayList<ExenivaDto> listDatosExniva = new ArrayList();
    private ArrayList<PequenoContribuyenteEncabezadoDto> listaDatosEncabezado;
    
    private ArrayList<ImpuestoIvaGeneralDto> listaIvaGeneral;
    

    @PostConstruct
    public void inicializar() {
        
//        listaIvaGeneral = this.generalSrvImpl.DetalleIvaGeneral(pnit);
        
        Date date = new Date();
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(date);
        int fecha_fin = fecha.get(Calendar.YEAR);

        Calendar fecha2 = Calendar.getInstance();
        fecha2.setTime(date);
        fecha2.add(Calendar.YEAR, -4);
        int fecha_ini = fecha2.get(Calendar.YEAR);

        String SQL = "SELECT "
                + "PERIODO_DESDE, "
                + "PERIODO_HASTA, "
                + "ISNULL(SUBSTRING(CONVERT(VARCHAR, FECHA_RECAUDO),5,1),0)                AS MES, "
                + "ISNULL(CODIGO_FORMULARIO, '0')                                          AS CODIGO_FORMULARIO, "
                + "ISNULL(NUMERO_FORMULARIO, '0')                                          AS NUMERO_FORMULARIO, "
                + "ISNULL(STATUS_FORMULARIO, '0')                                          AS STATUS_FORMULARIO,  "
                + "ISNULL(FORMAT(CAST(ROUND(VENTAS_EXENTAS,2)  AS DECIMAL(20,2)),'C','ES-GT'),0)                                                                 AS   VENTAS_EXENTAS,  "
                + "ISNULL(FORMAT(CAST(ROUND(SUMA_BASE_DEBITOS_BASE,2)  AS DECIMAL(20,2)),'C','ES-GT'),0.00)                                                      AS   SUMA_BASE_DEBITOS_BASE, "
                + "ISNULL(FORMAT(CAST(ROUND(SUMA_BASE_DEBITOS_DEBITOS,2) AS DECIMAL(20,2)),'C','ES-GT'),0.00)                                                    AS   SUMA_BASE_DEBITOS_DEBITOS, "
                + "ISNULL(FORMAT(CAST(ROUND(IVA_CONFORME_CONSTANCIAS_DE_EXENCION_RECIBIDAS,2) AS DECIMAL(20,2)),'C','ES-GT'),0.00)                               AS   IVA_CONFORME_CONSTANCIAS_DE_EXENCION_RECIBIDAS, "
                + "ISNULL(FORMAT(CAST(ROUND(REMANENTE_DE_CREDITO_FISCAL_DEL_PERIODO_ANTERIOR,2) AS DECIMAL(20,2)),'C','ES-GT'),0.00)                             AS   REMANENTE_DE_CREDITO_FISCAL_DEL_PERIODO_ANTERIOR, "
                + "ISNULL(FORMAT(CAST(ROUND(REMANENTE_DE_RETENCIONES_DE_IVA_DEL_PERIODO_ANTERIOR,2) AS DECIMAL(20,2)),'C','ES-GT'),0.00)                         AS   RENAMENTE_DE_RETENCIONES_DE_IVA_DEL_PERIODO_ANTERIOR,"
                + "ISNULL(FORMAT(CAST(ROUND(CREDITO_FISCAL_PARA_EL_SIGUIENTE_PERIODO_CREDITO_MAYOR_QUE_DEBITOS_OPERACIONES_LOCALES,2) AS DECIMAL(20,2)),'C','ES-GT'),0.00)      AS  CREDITO_FISCAL_PARA_EL_SIGUIENTE_PERIODO_CREDITO_MAYOR_QUE_DEBITOS_OPERACIONES_LOCALES, "
                + "ISNULL(FORMAT(CAST(ROUND(REMANENTE_DE_RETENCIONES_DE_IVA_RECIBIDAS_DEL_PERIODO,2)AS DECIMAL(20,2)),'C','ES-GT'),0.00)                                        AS  REMANENTE_DE_RETENCIONES_DE_IVA_RECIBIDAS_DEL_PERIODO, "
                + "ISNULL(CONSTANCIAS_DE_RETENCIONES_DEL_IVA_RECIBIDAS_EN_EL_PERIODO_A_DECLARAR, '0')                                                                           AS CONSTANCIAS_DE_RETENCIONES_DEL_IVA_RECIBIDAS_EN_EL_PERIODO_A_DECLARAR, "
                + "ISNULL(CANTIDAD_DE_CONSTANCIAS_DE_EXENCION_EMITIDAS, '0')                                            AS CANTIDAD_DE_CONSTANCIAS_DE_EXENCION_EMITIDAS, "
                + "ISNULL(CANTIDAD_DE_CONSTANCIAS_DE_EXENCION_RECIBIDAS, '0')                                           AS CANTIDAD_DE_CONSTANCIAS_DE_EXENCION_RECIBIDAS,  "
                + "ISNULL(ANIO_HERAMIENTA_EXENIVA, '0')                                                                  AS ANIO_HERAMIENTA_EXENIVA, "
                + "ISNULL(MES_HERRAMIENTA_EXENIVA, '0')                                                                 AS MES_HERRAMIENTA_EXENIVA, "
                + "ISNULL(FORMAT(CAST(ROUND(MONTO_NETO_FACTURA_HERRAMIENTA_EXENIVA,2) AS DECIMAL(20,2)),'C','ES-GT'),0.00)                                       AS  MONTO_NETO_FACTURA_HERRAMIENTA_EXENIVA, "
                + "ISNULL(FORMAT(CAST(ROUND(MONTO_IVA_EXENTO_HERRAMIENTA_EXENIVA,2) AS DECIMAL(20,2)),'C','ES-GT'),0.00)                                         AS  MONTO_IVA_EXENTO_HERRAMIENTA_EXENIVA, "
                + "ISNULL(ANIO_FACTURA_HERRAMIENTA_RETENIVA, '0')                                                       AS  ANIO_FACTURA_HERRAMIENTA_RETENIVA, "
                + "ISNULL(MES_FACTURA_HERRAMIENTA_RETENIVA,  '0')                                                       AS  MES_FACTURA_HERRAMIENTA_RETENIVA, "
                + "ISNULL(FORMAT(CAST(ROUND(IMPORTE_NETO_DEL_BIEN_O_SERVICIO,2) AS DECIMAL(20,2)),'C','ES-GT'),0.00)                                             AS  IMPORTE_NETO_DEL_BIEN_O_SERVICIO, "
                + "ISNULL(FORMAT(CAST(ROUND(IMPUESTO_RETENIDO,2) AS DECIMAL(20,2)),'C','ES-GT'),0.00)                                                            AS  IMPUESTO_RETENIDO, "
                + "FORMAT(CAST(ROUND(DIF_EXENIVA,2) AS DECIMAL(20,2)),'C','ES-GT')                                                                               AS  DIF_EXENIVA,  "
                + "FORMAT(CAST(ROUND(DIF_DEB_EX,2) AS DECIMAL(20,2)),'C','ES-GT')                                                                                AS  DIF_DEB_EX,  "
                + "FORMAT(CAST(ROUND(RECAUDO_POTENCIA,2) AS DECIMAL(20,2)),'C','ES-GT')                                                                          AS  RECAUDO_POTENCIA  "
                + "FROM  SAT_CRUCES.O_IVA_VS_EXENIVA_AG "
                + "where NIT_DEL_CONTRIBUYENTE = '" + pnit + "' "
                + "AND  substring(PERIODO_DESDE,7,4) >= '" + Integer.toString(fecha_ini) + "'"
                + "AND  substring(PERIODO_HASTA,7,4) <= '" + Integer.toString(fecha_fin) + "'";

        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            //System.out.print(SQL);
            rs = statement.executeQuery(SQL);
     
                while (rs.next()) {
                    ExenivaDto datos = new ExenivaDto();
                    datos.setPeriodoDesde(rs.getString("PERIODO_DESDE"));
                    datos.setPeriodoHasta(rs.getString("PERIODO_HASTA"));
                    datos.setFechaRecaudo(rs.getString("MES"));
                    datos.setCodigoFormulario(rs.getString("CODIGO_FORMULARIO"));
                    datos.setNumeroFormulario(rs.getString("NUMERO_FORMULARIO"));
                    datos.setStatusFormulario(rs.getString("STATUS_FORMULARIO"));
                    datos.setVentasExentas(rs.getString("VENTAS_EXENTAS"));
                    datos.setSumaBaseDebitosBase(rs.getString("SUMA_BASE_DEBITOS_BASE"));
                    datos.setSumaBaseDebitosDebitos(rs.getString("SUMA_BASE_DEBITOS_DEBITOS"));
                    datos.setIvaConformeConstaciasDeExencionRecibidas(rs.getString("IVA_CONFORME_CONSTANCIAS_DE_EXENCION_RECIBIDAS"));
                    datos.setRenamenteDeCreditoFiscalDelPeriodoAnterior(rs.getString("REMANENTE_DE_CREDITO_FISCAL_DEL_PERIODO_ANTERIOR"));
                    datos.setRenamenteDeRetencionesDeIvaDelPeriodoAnterior(rs.getString("RENAMENTE_DE_RETENCIONES_DE_IVA_DEL_PERIODO_ANTERIOR"));
                    datos.setCreFiSiguientePeriodoCreMayorDebiOpeLocales(rs.getString("CREDITO_FISCAL_PARA_EL_SIGUIENTE_PERIODO_CREDITO_MAYOR_QUE_DEBITOS_OPERACIONES_LOCALES"));
                    datos.setRenamenteDeRetencionesDeIVaRecibidasDelPeriodo(rs.getString("REMANENTE_DE_RETENCIONES_DE_IVA_RECIBIDAS_DEL_PERIODO"));
                    datos.setConstaciasDeRetencionesDelIvaRecibidasEnElPeriodoDeclarar(rs.getString("CONSTANCIAS_DE_RETENCIONES_DEL_IVA_RECIBIDAS_EN_EL_PERIODO_A_DECLARAR"));
                    datos.setCantidadDeConstanciasDeExencionEmitidas(rs.getString("CANTIDAD_DE_CONSTANCIAS_DE_EXENCION_EMITIDAS"));
                    datos.setCantidadDeConstanciasDeExencionRecibidas(rs.getString("CANTIDAD_DE_CONSTANCIAS_DE_EXENCION_RECIBIDAS"));
                    datos.setAnioHerramientaExeniva(rs.getString("ANIO_HERAMIENTA_EXENIVA"));
                    datos.setMesHerramientaExeniva(rs.getString("MES_HERRAMIENTA_EXENIVA"));
                    datos.setMontoNetoFacturaHerramientaExeniva(rs.getString("MONTO_NETO_FACTURA_HERRAMIENTA_EXENIVA"));
                    datos.setMontoIvaExentoHerramientaExeniva(rs.getString("MONTO_IVA_EXENTO_HERRAMIENTA_EXENIVA"));
                    datos.setAnioFacturaHerramientaReteniva(rs.getString("ANIO_FACTURA_HERRAMIENTA_RETENIVA"));
                    datos.setMesFacturaHerramientaReteIva(rs.getString("MES_FACTURA_HERRAMIENTA_RETENIVA"));
                    datos.setImporteNetoDelBienOServicio(rs.getString("IMPORTE_NETO_DEL_BIEN_O_SERVICIO"));
                    datos.setImpuestoRetenido(rs.getString("IMPUESTO_RETENIDO"));
                    datos.setDifExeniva(rs.getString("DIF_EXENIVA"));
                    datos.setDibDebEx(rs.getString("DIF_DEB_EX"));
                    datos.setRecaudoPotencial(rs.getString("RECAUDO_POTENCIA"));
                    listDatosExniva.add(datos);

                }
                conn.close();
                statement.close();

            }catch (Exception ex) {
            listDatosExniva.clear();
            errorMsg("Tabla no encontrada");
            /*  errorMsg(ex.getMessage());*/
            LOG.error(ex.getMessage());
        }

            if (listDatosExniva.isEmpty() || listDatosExniva == null) {
                warnMsg("No existen registros para el NIT consultado");

            } else {

                listaDatosEncabezado = this.generalSrvImpl.obtenerEncabezadoPeqContribuyente(pnit);

            }
        }

    

    public Connection getConnection() throws Exception {
        InitialContext inc = new InitialContext();
        DataSource ds = (DataSource) inc.lookup("java:comp/env/jdbc/consulta360azure");
        return ds.getConnection();
    }

    public ArrayList<IngresosFelvsDeclaradoIvaPequeDto> getListDatosGeneral() {
        return listDatosGeneral;
    }

    public void setListDatosGeneral(ArrayList<IngresosFelvsDeclaradoIvaPequeDto> listDatosGeneral) {
        this.listDatosGeneral = listDatosGeneral;
    }

    public ArrayList<PequenoContribuyenteEncabezadoDto> getListaDatosEncabezado() {
        return listaDatosEncabezado;
    }

    public void setListaDatosEncabezado(ArrayList<PequenoContribuyenteEncabezadoDto> listaDatosEncabezado) {
        this.listaDatosEncabezado = listaDatosEncabezado;
    }

    public ArrayList<ExenivaDto> getListDatosExniva() {
        return listDatosExniva;
    }

    public void setListDatosExniva(ArrayList<ExenivaDto> listDatosExniva) {
        this.listDatosExniva = listDatosExniva;
    }

    public ArrayList<ImpuestoIvaGeneralDto> getListaIvaGeneral() {
        return listaIvaGeneral;
    }

    public void setListaIvaGeneral(ArrayList<ImpuestoIvaGeneralDto> listaIvaGeneral) {
        this.listaIvaGeneral = listaIvaGeneral;
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
        celdaEnc2.setCellValue("IVA por Constancias de Exención Vs.ExenIVA");
        /*---------------------------------------------------*/

        HSSFRow filaEnc2 = hoja.createRow(2);

        HSSFCell celdaEnc3 = filaEnc2.createCell(0);
        celdaEnc3.setCellValue("NIT del contribuyente");

        HSSFCell celdaEnc4 = filaEnc2.createCell(1);
        celdaEnc4.setCellValue(listaDatosEncabezado.get(0).getNit());

        HSSFCell celdaEnc5 = filaEnc2.createCell(7);
        celdaEnc5.setCellValue("Marcas de no Localizado");

        HSSFCell celdaEnc6 = filaEnc2.createCell(8);
        celdaEnc6.setCellValue(listaDatosEncabezado.get(0).getMarcaNoLocalizado());

        /*---------------------------------------------------*/
        HSSFRow filaEnc3 = hoja.createRow(3);

        HSSFCell celdaEnc7 = filaEnc3.createCell(0);
        celdaEnc7.setCellValue("Nombre del Contribuyente");

        HSSFCell celdaEnc8 = filaEnc3.createCell(1);
        celdaEnc8.setCellValue(listaDatosEncabezado.get(0).getNombreOrdenado());

        HSSFCell celdaEnc9 = filaEnc3.createCell(7);
        celdaEnc9.setCellValue("Marca Omiso");

        /*---------------------------------------------------*/
        HSSFRow filaEnc4 = hoja.createRow(4);

        HSSFCell celdaEnc11 = filaEnc4.createCell(0);
        celdaEnc11.setCellValue("Gerencia");

        HSSFCell celdaEnc12 = filaEnc4.createCell(1);
        celdaEnc12.setCellValue(listaDatosEncabezado.get(0).getGerencia());

        HSSFCell celdaEnc13 = filaEnc4.createCell(7);
        celdaEnc13.setCellValue("Color plan operativo");

        HSSFCell celdaEnc14 = filaEnc4.createCell(8);
        celdaEnc14.setCellValue(listaDatosEncabezado.get(0).getNombreColor());

        /*---------------------------------------------------*/
        HSSFRow filaEnc5 = hoja.createRow(5);

        HSSFCell celdaEnc15 = filaEnc5.createCell(0);
        celdaEnc15.setCellValue("Región");

        HSSFCell celdaEnc16 = filaEnc5.createCell(1);
        celdaEnc16.setCellValue(listaDatosEncabezado.get(0).getRegion());

        HSSFCell celdaEnc17 = filaEnc5.createCell(7);
        celdaEnc17.setCellValue("Dirección");

        HSSFCell celdaEnc18 = filaEnc5.createCell(8);
        celdaEnc18.setCellValue(listaDatosEncabezado.get(0).getDireccion());

        /*---------------------------------------------------*/
        HSSFRow filaEnc6 = hoja.createRow(6);

        HSSFCell celdaEnc19 = filaEnc6.createCell(0);
        celdaEnc19.setCellValue("Actividad económica");

        HSSFCell celdaEnc20 = filaEnc6.createCell(1);
        celdaEnc20.setCellValue(listaDatosEncabezado.get(0).getActividadEconomica());

        HSSFCell celdaEnc21 = filaEnc6.createCell(7);
        celdaEnc21.setCellValue("Status");

        HSSFCell celdaEnc22 = filaEnc6.createCell(1);
        celdaEnc22.setCellValue(listaDatosEncabezado.get(0).getStatus());

        /*---------------------------------------------------*/
        HSSFRow filaEnc7 = hoja.createRow(7);

        HSSFCell celdaEnc23 = filaEnc7.createCell(0);
        celdaEnc23.setCellValue("Personería");

        HSSFCell celdaEnc24 = filaEnc7.createCell(1);
        celdaEnc24.setCellValue(listaDatosEncabezado.get(0).getPersoneria());

        HSSFCell celdaEnc25 = filaEnc7.createCell(7);
        celdaEnc25.setCellValue("Afiliación a IVA");
        
        HSSFCell celdaEnc26 = filaEnc7.createCell(7);
        celdaEnc26.setCellValue(listaIvaGeneral.get(0).getNombreRegimen());

       

        /*---------------------------------------------------*/
        HSSFRow filaEnc8 = hoja.createRow(8);

        HSSFCell celdaEnc27 = filaEnc8.createCell(0);
        celdaEnc27.setCellValue("Nombre tipo");

        HSSFCell celdaEnc28 = filaEnc8.createCell(1);
        celdaEnc28.setCellValue(listaDatosEncabezado.get(0).getTipoPersona());

        HSSFCell celdaEnc29 = filaEnc8.createCell(7);
        celdaEnc29.setCellValue("Fecha afiliación a IVA");
        
        HSSFCell celdaEnc30 = filaEnc8.createCell(1);
        celdaEnc30.setCellValue(listaIvaGeneral.get(0).getFechaAdicionAfiliacionIVA());

    
 /*---------------------------------------------------*/
        HSSFRow filaEnc9 = hoja.createRow(9);

//        HSSFCell celdaEnc31 = filaEnc9.createCell(0);
//        celdaEnc31.setCellValue("");
//
//        HSSFCell celdaEnc32 = filaEnc9.createCell(1);
//        celdaEnc32.setCellValue(listaDatosEncabezado.get(0).getTipoPersona());

        HSSFCell celdaEnc33 = filaEnc9.createCell(7);
        celdaEnc33.setCellValue("Marca establecimiento");

        HSSFCell celdaEnc34 = filaEnc9.createCell(8);
        celdaEnc34.setCellValue(listaDatosEncabezado.get(0).getMarcaEstablecimiento());

        /*---------------------------------------------------*/
        if (listDatosExniva.isEmpty() || listDatosExniva == null) {

            /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
            HSSFRow filaH = hoja.createRow(7);

            HSSFCell celdaH0 = filaH.createCell(0);
            celdaH0.setCellStyle(rowStyle);
            celdaH0.setCellValue("Periodo Desde");

            HSSFCell celdaH1 = filaH.createCell(1);
            celdaH1.setCellStyle(rowStyle);
            celdaH1.setCellValue("Periodo Hasta");

            HSSFCell celdaH2 = filaH.createCell(2);
            celdaH2.setCellStyle(rowStyle);
            celdaH2.setCellValue("Fecha de Recaudo");

            HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("MES");

            /*HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("Codigo formulario ");*/
            HSSFCell celdaH4 = filaH.createCell(4);
            celdaH4.setCellStyle(rowStyle);
            celdaH4.setCellValue("Numero de formulario");

            HSSFCell celdaH5 = filaH.createCell(5);
            celdaH5.setCellStyle(rowStyle);
            celdaH5.setCellValue("Estatus del formulario");

            HSSFCell celdaH6 = filaH.createCell(6);
            celdaH6.setCellStyle(rowStyle);
            celdaH6.setCellValue("Ventas exentas");

            HSSFCell celdaH7 = filaH.createCell(7);
            celdaH7.setCellStyle(rowStyle);
            celdaH7.setCellValue("Suma Base debitos base");

            HSSFCell celdaH8 = filaH.createCell(8);
            celdaH8.setCellStyle(rowStyle);
            celdaH8.setCellValue("Suma Base debitos debitos");

            HSSFCell celdaH9 = filaH.createCell(9);
            celdaH9.setCellStyle(rowStyle);
            celdaH9.setCellValue("IVA conforme Constancias de Exención Recibidas");

            HSSFCell celdaH10 = filaH.createCell(10);
            celdaH10.setCellStyle(rowStyle);
            celdaH10.setCellValue("Remanente de Credito Fiscal del periodo anterior");

            HSSFCell celdaH11 = filaH.createCell(11);
            celdaH11.setCellStyle(rowStyle);
            celdaH11.setCellValue("Credito Fiscal para el Siguiente Periodo credito mayor que debitos operaciones locales");

            HSSFCell celdaH12 = filaH.createCell(12);
            celdaH12.setCellStyle(rowStyle);
            celdaH12.setCellValue("Remanente de Retenciones de IVA del periodo anterior");

            HSSFCell celdaH13 = filaH.createCell(13);
            celdaH13.setCellStyle(rowStyle);
            celdaH13.setCellValue("Remanente de retenciones de IVA recibidas del periodo");

            HSSFCell celdaH14 = filaH.createCell(14);
            celdaH14.setCellStyle(rowStyle);
            celdaH14.setCellValue("Constancias de Retenciones del IVA Recibidas en el periodo a declarar ");

            HSSFCell celdaH15 = filaH.createCell(15);
            celdaH15.setCellStyle(rowStyle);
            celdaH15.setCellValue("Cantidad de Constancias de Exencion emitidas");

            HSSFCell celdaH16 = filaH.createCell(16);
            celdaH16.setCellStyle(rowStyle);
            celdaH16.setCellValue("Cantidad de Constancias de Exencion recibidas");

            HSSFCell celdaH17 = filaH.createCell(17);
            celdaH17.setCellStyle(rowStyle);
            celdaH17.setCellValue("Año herramienta exeniva");

            HSSFCell celdaH18 = filaH.createCell(18);
            celdaH18.setCellStyle(rowStyle);
            celdaH18.setCellValue("Mes herramienta exeniva");

            HSSFCell celdaH19 = filaH.createCell(19);
            celdaH19.setCellStyle(rowStyle);
            celdaH19.setCellValue("Monto Neto Factura herramienta exeniva");

            HSSFCell celdaH20 = filaH.createCell(20);
            celdaH20.setCellStyle(rowStyle);
            celdaH20.setCellValue("Monto IVA EXENTO herramienta exeniva");

            HSSFCell celdaH21 = filaH.createCell(21);
            celdaH21.setCellStyle(rowStyle);
            celdaH21.setCellValue("Año Factura herramienta reteniva");

            HSSFCell celdaH22 = filaH.createCell(22);
            celdaH22.setCellStyle(rowStyle);
            celdaH22.setCellValue("Mes Factura herramienta reteniva");

            HSSFCell celdaH23 = filaH.createCell(23);
            celdaH23.setCellStyle(rowStyle);
            celdaH23.setCellValue("Importe neto del bien o servicio");

            HSSFCell celdaH24 = filaH.createCell(24);
            celdaH24.setCellStyle(rowStyle);
            celdaH24.setCellValue("IMPUESTO RETENIDO");

            HSSFCell celdaH25 = filaH.createCell(25);
            celdaH25.setCellStyle(rowStyle);
            celdaH25.setCellValue("DIF EXENIVA");

            HSSFCell celdaH26 = filaH.createCell(26);
            celdaH26.setCellStyle(rowStyle);
            celdaH26.setCellValue("DIF DEB EX");

            HSSFCell celdaH27 = filaH.createCell(27);
            celdaH27.setCellStyle(rowStyle);
            celdaH27.setCellValue("RECAUDO POTENCIAL CRUCE");

            for (int x = 0; x < listDatosExniva.size(); x++) {

                HSSFRow fila = hoja.createRow(x + 8);

                HSSFCell celda0 = fila.createCell(0);
                celda0.setCellStyle(cellStyle);
                celda0.setCellValue(listDatosExniva.get(x).getPeriodoDesde());

                HSSFCell celda1 = fila.createCell(1);
                celda1.setCellStyle(cellStyle);
                celda1.setCellValue(listDatosExniva.get(x).getPeriodoHasta());

                HSSFCell celda2 = fila.createCell(2);
                celda2.setCellStyle(cellStyle);
                celda2.setCellValue(listDatosExniva.get(x).getFechaRecaudo());

                HSSFCell celda3 = fila.createCell(3);
                celda3.setCellStyle(cellStyle);
                celda3.setCellValue(listDatosExniva.get(x).getCodigoFormulario());

                HSSFCell celda4 = fila.createCell(4);
                celda4.setCellStyle(styleMoneda);
                celda4.setCellValue(listDatosExniva.get(x).getNumeroFormulario());

                HSSFCell celda5 = fila.createCell(5);
                celda5.setCellStyle(cellStyle);
                celda5.setCellValue(listDatosExniva.get(x).getStatusFormulario());

                HSSFCell celda6 = fila.createCell(6);
                celda6.setCellStyle(styleMoneda);
                celda6.setCellValue(listDatosExniva.get(x).getVentasExentas());

                HSSFCell celda7 = fila.createCell(7);
                celda7.setCellStyle(cellStyle);
                celda7.setCellValue(listDatosExniva.get(x).getSumaBaseDebitosBase());

                HSSFCell celda8 = fila.createCell(8);
                celda8.setCellStyle(styleMoneda);
                celda8.setCellValue(listDatosExniva.get(x).getSumaBaseDebitosDebitos());

                HSSFCell celda9 = fila.createCell(9);
                celda9.setCellStyle(styleMoneda);
                celda9.setCellValue(listDatosExniva.get(x).getIvaConformeConstaciasDeExencionRecibidas());

                HSSFCell celda10 = fila.createCell(10);
                celda10.setCellStyle(styleMoneda);
                celda10.setCellValue(listDatosExniva.get(x).getRenamenteDeCreditoFiscalDelPeriodoAnterior());

                HSSFCell celda11 = fila.createCell(11);
                celda11.setCellStyle(cellStyle);
                celda11.setCellValue(listDatosExniva.get(x).getCreFiSiguientePeriodoCreMayorDebiOpeLocales());

                HSSFCell celda12 = fila.createCell(12);
                celda12.setCellStyle(styleMoneda);
                celda12.setCellValue(listDatosExniva.get(x).getRenamenteDeRetencionesDeIvaDelPeriodoAnterior());

                HSSFCell celda13 = fila.createCell(13);
                celda13.setCellStyle(styleMoneda);
                celda13.setCellValue(listDatosExniva.get(x).getRenamenteDeRetencionesDeIVaRecibidasDelPeriodo());

                HSSFCell celda14 = fila.createCell(14);
                celda14.setCellStyle(styleMoneda);
                celda14.setCellValue(listDatosExniva.get(x).getConstaciasDeRetencionesDelIvaRecibidasEnElPeriodoDeclarar());

                HSSFCell celda15 = fila.createCell(15);
                celda15.setCellStyle(styleMoneda);
                celda15.setCellValue(listDatosExniva.get(x).getCantidadDeConstanciasDeExencionEmitidas());

                HSSFCell celda16 = fila.createCell(16);
                celda16.setCellStyle(styleMoneda);
                celda16.setCellValue(listDatosExniva.get(x).getCantidadDeConstanciasDeExencionRecibidas());

                HSSFCell celda17 = fila.createCell(17);
                celda17.setCellStyle(styleMoneda);
                celda17.setCellValue(listDatosExniva.get(x).getAnioHerramientaExeniva());

                HSSFCell celda18 = fila.createCell(18);
                celda18.setCellStyle(styleMoneda);
                celda18.setCellValue(listDatosExniva.get(x).getMesHerramientaExeniva());

                HSSFCell celda19 = fila.createCell(19);
                celda19.setCellStyle(styleMoneda);
                celda19.setCellValue(listDatosExniva.get(x).getMontoNetoFacturaHerramientaExeniva());

                HSSFCell celda20 = fila.createCell(20);
                celda20.setCellStyle(styleMoneda);
                celda20.setCellValue(listDatosExniva.get(x).getMontoIvaExentoHerramientaExeniva());

                HSSFCell celda21 = fila.createCell(21);
                celda21.setCellStyle(styleMoneda);
                celda21.setCellValue(listDatosExniva.get(x).getAnioFacturaHerramientaReteniva());

                HSSFCell celda22 = fila.createCell(22);
                celda22.setCellStyle(styleMoneda);
                celda22.setCellValue(listDatosExniva.get(x).getMesFacturaHerramientaReteIva());

                HSSFCell celda23 = fila.createCell(23);
                celda23.setCellStyle(styleMoneda);
                celda23.setCellValue(listDatosExniva.get(x).getImporteNetoDelBienOServicio());

                HSSFCell celda24 = fila.createCell(24);
                celda24.setCellStyle(styleMoneda);
                celda24.setCellValue(listDatosExniva.get(x).getImpuestoRetenido());

                HSSFCell celda25 = fila.createCell(25);
                celda25.setCellStyle(styleMoneda);
                celda25.setCellValue(listDatosExniva.get(x).getDifExeniva());

                HSSFCell celda26 = fila.createCell(26);
                celda26.setCellStyle(styleMoneda);
                celda26.setCellValue(listDatosExniva.get(x).getDibDebEx());

                HSSFCell celda27 = fila.createCell(27);
                celda27.setCellStyle(styleMoneda);
                celda27.setCellValue(listDatosExniva.get(x).getRecaudoPotencial());

            }
        }

        if (listDatosExniva.isEmpty() || listDatosExniva == null) {

            /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
            HSSFRow filaH = hoja.createRow(7);

            HSSFCell celdaH0 = filaH.createCell(0);
            celdaH0.setCellStyle(rowStyle);
            celdaH0.setCellValue("Periodo Desde");

            HSSFCell celdaH1 = filaH.createCell(1);
            celdaH1.setCellStyle(rowStyle);
            celdaH1.setCellValue("Periodo Hasta");

            HSSFCell celdaH2 = filaH.createCell(2);
            celdaH2.setCellStyle(rowStyle);
            celdaH2.setCellValue("Fecha de Recaudo");

            /*   HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("MES");*/
            HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("Codigo formulario ");

            HSSFCell celdaH4 = filaH.createCell(4);
            celdaH4.setCellStyle(rowStyle);
            celdaH4.setCellValue("Numero de formulario");

            HSSFCell celdaH5 = filaH.createCell(5);
            celdaH5.setCellStyle(rowStyle);
            celdaH5.setCellValue("Estatus del formulario");

            HSSFCell celdaH6 = filaH.createCell(6);
            celdaH6.setCellStyle(rowStyle);
            celdaH6.setCellValue("Ventas exentas");

            HSSFCell celdaH7 = filaH.createCell(7);
            celdaH7.setCellStyle(rowStyle);
            celdaH7.setCellValue("Suma Base debitos base");

            HSSFCell celdaH8 = filaH.createCell(8);
            celdaH8.setCellStyle(rowStyle);
            celdaH8.setCellValue("Suma Base debitos debitos");

            HSSFCell celdaH9 = filaH.createCell(9);
            celdaH9.setCellStyle(rowStyle);
            celdaH9.setCellValue("IVA conforme Constancias de Exención Recibidas");

            HSSFCell celdaH10 = filaH.createCell(10);
            celdaH10.setCellStyle(rowStyle);
            celdaH10.setCellValue("Remanente de Credito Fiscal del periodo anterior");

            HSSFCell celdaH11 = filaH.createCell(11);
            celdaH11.setCellStyle(rowStyle);
            celdaH11.setCellValue("Credito Fiscal para el Siguiente Periodo credito mayor que debitos operaciones locales");

            HSSFCell celdaH12 = filaH.createCell(12);
            celdaH12.setCellStyle(rowStyle);
            celdaH12.setCellValue("Remanente de Retenciones de IVA del periodo anterior");

            HSSFCell celdaH13 = filaH.createCell(13);
            celdaH13.setCellStyle(rowStyle);
            celdaH13.setCellValue("Remanente de retenciones de IVA recibidas del periodo");

            HSSFCell celdaH14 = filaH.createCell(14);
            celdaH14.setCellStyle(rowStyle);
            celdaH14.setCellValue("Constancias de Retenciones del IVA Recibidas en el periodo a declarar ");

            HSSFCell celdaH15 = filaH.createCell(15);
            celdaH15.setCellStyle(rowStyle);
            celdaH15.setCellValue("Cantidad de Constancias de Exencion emitidas");

            HSSFCell celdaH16 = filaH.createCell(16);
            celdaH16.setCellStyle(rowStyle);
            celdaH16.setCellValue("Cantidad de Constancias de Exencion recibidas");

            /*HSSFCell celdaH17 = filaH.createCell(17);
            celdaH17.setCellStyle(rowStyle);
            celdaH17.setCellValue("Año herramienta exeniva");*/
            HSSFCell celdaH18 = filaH.createCell(18);
            celdaH18.setCellStyle(rowStyle);
            celdaH18.setCellValue("Mes herramienta exeniva");

            HSSFCell celdaH19 = filaH.createCell(19);
            celdaH19.setCellStyle(rowStyle);
            celdaH19.setCellValue("Monto Neto Factura herramienta exeniva");

            HSSFCell celdaH20 = filaH.createCell(20);
            celdaH20.setCellStyle(rowStyle);
            celdaH20.setCellValue("Monto IVA EXENTO herramienta exeniva");

            HSSFCell celdaH21 = filaH.createCell(21);
            celdaH21.setCellStyle(rowStyle);
            celdaH21.setCellValue("Año Factura herramienta reteniva");

            HSSFCell celdaH22 = filaH.createCell(22);
            celdaH22.setCellStyle(rowStyle);
            celdaH22.setCellValue("Mes Factura herramienta reteniva");

            HSSFCell celdaH23 = filaH.createCell(23);
            celdaH23.setCellStyle(rowStyle);
            celdaH23.setCellValue("Importe neto del bien o servicio");

            HSSFCell celdaH24 = filaH.createCell(24);
            celdaH24.setCellStyle(rowStyle);
            celdaH24.setCellValue("IMPUESTO RETENIDO");

            HSSFCell celdaH25 = filaH.createCell(25);
            celdaH25.setCellStyle(rowStyle);
            celdaH25.setCellValue("DIF EXENIVA");

            HSSFCell celdaH26 = filaH.createCell(26);
            celdaH26.setCellStyle(rowStyle);
            celdaH26.setCellValue("DIF DEB EX");

            HSSFCell celdaH27 = filaH.createCell(27);
            celdaH27.setCellStyle(rowStyle);
            celdaH27.setCellValue("RECAUDO POTENCIAL CRUCE");

            for (int x = 0; x < listDatosExniva.size(); x++) {

                HSSFRow fila = hoja.createRow(x + 8);

                HSSFCell celda0 = fila.createCell(0);
                celda0.setCellStyle(cellStyle);
                celda0.setCellValue(listDatosExniva.get(x).getPeriodoDesde());

                HSSFCell celda1 = fila.createCell(1);
                celda1.setCellStyle(cellStyle);
                celda1.setCellValue(listDatosExniva.get(x).getPeriodoHasta());

                HSSFCell celda2 = fila.createCell(2);
                celda2.setCellStyle(cellStyle);
                celda2.setCellValue(listDatosExniva.get(x).getFechaRecaudo());

                HSSFCell celda3 = fila.createCell(3);
                celda3.setCellStyle(cellStyle);
                celda3.setCellValue(listDatosExniva.get(x).getCodigoFormulario());

                HSSFCell celda4 = fila.createCell(4);
                celda4.setCellStyle(styleMoneda);
                celda4.setCellValue(listDatosExniva.get(x).getNumeroFormulario());

                HSSFCell celda5 = fila.createCell(5);
                celda5.setCellStyle(cellStyle);
                celda5.setCellValue(listDatosExniva.get(x).getStatusFormulario());

                HSSFCell celda6 = fila.createCell(6);
                celda6.setCellStyle(styleMoneda);
                celda6.setCellValue(listDatosExniva.get(x).getVentasExentas());

                HSSFCell celda7 = fila.createCell(7);
                celda7.setCellStyle(cellStyle);
                celda7.setCellValue(listDatosExniva.get(x).getSumaBaseDebitosBase());

                HSSFCell celda8 = fila.createCell(8);
                celda8.setCellStyle(styleMoneda);
                celda8.setCellValue(listDatosExniva.get(x).getSumaBaseDebitosDebitos());

                HSSFCell celda9 = fila.createCell(9);
                celda9.setCellStyle(styleMoneda);
                celda9.setCellValue(listDatosExniva.get(x).getIvaConformeConstaciasDeExencionRecibidas());

                HSSFCell celda10 = fila.createCell(10);
                celda10.setCellStyle(styleMoneda);
                celda10.setCellValue(listDatosExniva.get(x).getRenamenteDeCreditoFiscalDelPeriodoAnterior());

                HSSFCell celda11 = fila.createCell(11);
                celda11.setCellStyle(cellStyle);
                celda11.setCellValue(listDatosExniva.get(x).getCreFiSiguientePeriodoCreMayorDebiOpeLocales());

                HSSFCell celda12 = fila.createCell(12);
                celda12.setCellStyle(styleMoneda);
                celda12.setCellValue(listDatosExniva.get(x).getRenamenteDeRetencionesDeIvaDelPeriodoAnterior());

                HSSFCell celda13 = fila.createCell(13);
                celda13.setCellStyle(styleMoneda);
                celda13.setCellValue(listDatosExniva.get(x).getRenamenteDeRetencionesDeIVaRecibidasDelPeriodo());

                HSSFCell celda14 = fila.createCell(14);
                celda14.setCellStyle(styleMoneda);
                celda14.setCellValue(listDatosExniva.get(x).getConstaciasDeRetencionesDelIvaRecibidasEnElPeriodoDeclarar());

                HSSFCell celda15 = fila.createCell(15);
                celda15.setCellStyle(styleMoneda);
                celda15.setCellValue(listDatosExniva.get(x).getCantidadDeConstanciasDeExencionEmitidas());

                HSSFCell celda16 = fila.createCell(16);
                celda16.setCellStyle(styleMoneda);
                celda16.setCellValue(listDatosExniva.get(x).getCantidadDeConstanciasDeExencionRecibidas());

                /*HSSFCell celda17 = fila.createCell(17);
                celda17.setCellStyle(styleMoneda);
                celda17.setCellValue(listDatosExniva.get(x).getAnioHerramientaExeniva());*/
                HSSFCell celda18 = fila.createCell(18);
                celda18.setCellStyle(styleMoneda);
                celda18.setCellValue(listDatosExniva.get(x).getMesHerramientaExeniva());

                HSSFCell celda19 = fila.createCell(19);
                celda19.setCellStyle(styleMoneda);
                celda19.setCellValue(listDatosExniva.get(x).getMontoNetoFacturaHerramientaExeniva());

                HSSFCell celda20 = fila.createCell(20);
                celda20.setCellStyle(styleMoneda);
                celda20.setCellValue(listDatosExniva.get(x).getMontoIvaExentoHerramientaExeniva());

                HSSFCell celda21 = fila.createCell(21);
                celda21.setCellStyle(styleMoneda);
                celda21.setCellValue(listDatosExniva.get(x).getAnioFacturaHerramientaReteniva());

                HSSFCell celda22 = fila.createCell(22);
                celda22.setCellStyle(styleMoneda);
                celda22.setCellValue(listDatosExniva.get(x).getMesFacturaHerramientaReteIva());

                /*HSSFCell celda23 = fila.createCell(23);
                celda23.setCellStyle(styleMoneda);
                celda23.setCellValue(listDatosExniva.get(x).getImporteNetoDelBienOServicio());*/

 /*HSSFCell celda24 = fila.createCell(24);
                celda24.setCellStyle(styleMoneda);
                celda24.setCellValue(listDatosExniva.get(x).getImpuestoRetenido());*/
                HSSFCell celda25 = fila.createCell(25);
                celda25.setCellStyle(styleMoneda);
                celda25.setCellValue(listDatosExniva.get(x).getDifExeniva());

                HSSFCell celda26 = fila.createCell(26);
                celda26.setCellStyle(styleMoneda);
                celda26.setCellValue(listDatosExniva.get(x).getDibDebEx());

                HSSFCell celda27 = fila.createCell(27);
                celda27.setCellStyle(styleMoneda);
                celda27.setCellValue(listDatosExniva.get(x).getRecaudoPotencial());

            }
        }

        if (listDatosGeneral.isEmpty() || listDatosGeneral == null) {

            /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
            HSSFRow filaH = hoja.createRow(13);

            HSSFCell celdaH0 = filaH.createCell(0);
            celdaH0.setCellStyle(rowStyle);
            celdaH0.setCellValue("Periodo Desde");

            HSSFCell celdaH1 = filaH.createCell(1);
            celdaH1.setCellStyle(rowStyle);
            celdaH1.setCellValue("Periodo Hasta");

            HSSFCell celdaH2 = filaH.createCell(2);
            celdaH2.setCellStyle(rowStyle);
            celdaH2.setCellValue("Fecha de Recaudo");

            /*   HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("MES");*/
            HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("Codigo formulario ");

            HSSFCell celdaH4 = filaH.createCell(4);
            celdaH4.setCellStyle(rowStyle);
            celdaH4.setCellValue("Numero de formulario");

            HSSFCell celdaH5 = filaH.createCell(5);
            celdaH5.setCellStyle(rowStyle);
            celdaH5.setCellValue("Estatus del formulario");

            HSSFCell celdaH6 = filaH.createCell(6);
            celdaH6.setCellStyle(rowStyle);
            celdaH6.setCellValue("Ventas exentas");

            HSSFCell celdaH7 = filaH.createCell(7);
            celdaH7.setCellStyle(rowStyle);
            celdaH7.setCellValue("Suma Base debitos base");

            HSSFCell celdaH8 = filaH.createCell(8);
            celdaH8.setCellStyle(rowStyle);
            celdaH8.setCellValue("Suma Base debitos debitos");

            HSSFCell celdaH9 = filaH.createCell(9);
            celdaH9.setCellStyle(rowStyle);
            celdaH9.setCellValue("IVA conforme Constancias de Exención Recibidas");

            HSSFCell celdaH10 = filaH.createCell(10);
            celdaH10.setCellStyle(rowStyle);
            celdaH10.setCellValue("Remanente de Credito Fiscal del periodo anterior");

            HSSFCell celdaH11 = filaH.createCell(11);
            celdaH11.setCellStyle(rowStyle);
            celdaH11.setCellValue("Credito Fiscal para el Siguiente Periodo credito mayor que debitos operaciones locales");

            HSSFCell celdaH12 = filaH.createCell(12);
            celdaH12.setCellStyle(rowStyle);
            celdaH12.setCellValue("Remanente de Retenciones de IVA del periodo anterior");

            HSSFCell celdaH13 = filaH.createCell(13);
            celdaH13.setCellStyle(rowStyle);
            celdaH13.setCellValue("Remanente de retenciones de IVA recibidas del periodo");

            HSSFCell celdaH14 = filaH.createCell(14);
            celdaH14.setCellStyle(rowStyle);
            celdaH14.setCellValue("Constancias de Retenciones del IVA Recibidas en el periodo a declarar ");

            HSSFCell celdaH15 = filaH.createCell(15);
            celdaH15.setCellStyle(rowStyle);
            celdaH15.setCellValue("Cantidad de Constancias de Exencion emitidas");

            HSSFCell celdaH16 = filaH.createCell(16);
            celdaH16.setCellStyle(rowStyle);
            celdaH16.setCellValue("Cantidad de Constancias de Exencion recibidas");

            HSSFCell celdaH17 = filaH.createCell(17);
            celdaH17.setCellStyle(rowStyle);
            celdaH17.setCellValue("Año herramienta exeniva");

            HSSFCell celdaH18 = filaH.createCell(18);
            celdaH18.setCellStyle(rowStyle);
            celdaH18.setCellValue("Mes herramienta exeniva");

            HSSFCell celdaH19 = filaH.createCell(19);
            celdaH19.setCellStyle(rowStyle);
            celdaH19.setCellValue("Monto Neto Factura herramienta exeniva");

            HSSFCell celdaH20 = filaH.createCell(20);
            celdaH20.setCellStyle(rowStyle);
            celdaH20.setCellValue("Monto IVA EXENTO herramienta exeniva");

            HSSFCell celdaH21 = filaH.createCell(21);
            celdaH21.setCellStyle(rowStyle);
            celdaH21.setCellValue("Año Factura herramienta reteniva");

            HSSFCell celdaH22 = filaH.createCell(22);
            celdaH22.setCellStyle(rowStyle);
            celdaH22.setCellValue("Mes Factura herramienta reteniva");

            HSSFCell celdaH23 = filaH.createCell(23);
            celdaH23.setCellStyle(rowStyle);
            celdaH23.setCellValue("Importe neto del bien o servicio");

            HSSFCell celdaH24 = filaH.createCell(24);
            celdaH24.setCellStyle(rowStyle);
            celdaH24.setCellValue("IMPUESTO RETENIDO");

            HSSFCell celdaH25 = filaH.createCell(25);
            celdaH25.setCellStyle(rowStyle);
            celdaH25.setCellValue("DIF EXENIVA");

            HSSFCell celdaH26 = filaH.createCell(26);
            celdaH26.setCellStyle(rowStyle);
            celdaH26.setCellValue("DIF DEB EX");

            HSSFCell celdaH27 = filaH.createCell(27);
            celdaH27.setCellStyle(rowStyle);
            celdaH27.setCellValue("RECAUDO POTENCIAL CRUCE");

            for (int x = 0; x < listDatosExniva.size(); x++) {

                HSSFRow fila = hoja.createRow(x + 15);

                HSSFCell celda0 = fila.createCell(0);
                celda0.setCellStyle(cellStyle);
                celda0.setCellValue(listDatosExniva.get(x).getPeriodoDesde());

                HSSFCell celda1 = fila.createCell(1);
                celda1.setCellStyle(cellStyle);
                celda1.setCellValue(listDatosExniva.get(x).getPeriodoHasta());

                HSSFCell celda2 = fila.createCell(2);
                celda2.setCellStyle(cellStyle);
                celda2.setCellValue(listDatosExniva.get(x).getFechaRecaudo());

                HSSFCell celda3 = fila.createCell(3);
                celda3.setCellStyle(cellStyle);
                celda3.setCellValue(listDatosExniva.get(x).getCodigoFormulario());

                HSSFCell celda4 = fila.createCell(4);
                celda4.setCellStyle(styleMoneda);
                celda4.setCellValue(listDatosExniva.get(x).getNumeroFormulario());

                HSSFCell celda5 = fila.createCell(5);
                celda5.setCellStyle(cellStyle);
                celda5.setCellValue(listDatosExniva.get(x).getStatusFormulario());

                HSSFCell celda6 = fila.createCell(6);
                celda6.setCellStyle(styleMoneda);
                celda6.setCellValue(listDatosExniva.get(x).getVentasExentas());

                HSSFCell celda7 = fila.createCell(7);
                celda7.setCellStyle(cellStyle);
                celda7.setCellValue(listDatosExniva.get(x).getSumaBaseDebitosBase());

                HSSFCell celda8 = fila.createCell(8);
                celda8.setCellStyle(styleMoneda);
                celda8.setCellValue(listDatosExniva.get(x).getSumaBaseDebitosDebitos());

                HSSFCell celda9 = fila.createCell(9);
                celda9.setCellStyle(styleMoneda);
                celda9.setCellValue(listDatosExniva.get(x).getIvaConformeConstaciasDeExencionRecibidas());

                HSSFCell celda10 = fila.createCell(10);
                celda10.setCellStyle(styleMoneda);
                celda10.setCellValue(listDatosExniva.get(x).getRenamenteDeCreditoFiscalDelPeriodoAnterior());

                HSSFCell celda11 = fila.createCell(11);
                celda11.setCellStyle(cellStyle);
                celda11.setCellValue(listDatosExniva.get(x).getCreFiSiguientePeriodoCreMayorDebiOpeLocales());

                HSSFCell celda12 = fila.createCell(12);
                celda12.setCellStyle(styleMoneda);
                celda12.setCellValue(listDatosExniva.get(x).getRenamenteDeRetencionesDeIvaDelPeriodoAnterior());

                HSSFCell celda13 = fila.createCell(13);
                celda13.setCellStyle(styleMoneda);
                celda13.setCellValue(listDatosExniva.get(x).getRenamenteDeRetencionesDeIVaRecibidasDelPeriodo());

                HSSFCell celda14 = fila.createCell(14);
                celda14.setCellStyle(styleMoneda);
                celda14.setCellValue(listDatosExniva.get(x).getConstaciasDeRetencionesDelIvaRecibidasEnElPeriodoDeclarar());

                HSSFCell celda15 = fila.createCell(15);
                celda15.setCellStyle(styleMoneda);
                celda15.setCellValue(listDatosExniva.get(x).getCantidadDeConstanciasDeExencionEmitidas());

                HSSFCell celda16 = fila.createCell(16);
                celda16.setCellStyle(styleMoneda);
                celda16.setCellValue(listDatosExniva.get(x).getCantidadDeConstanciasDeExencionRecibidas());

                /*HSSFCell celda17 = fila.createCell(17);
                celda17.setCellStyle(styleMoneda);
                celda17.setCellValue(listDatosExniva.get(x).getAnioHerramientaExeniva());*/
                HSSFCell celda18 = fila.createCell(18);
                celda18.setCellStyle(styleMoneda);
                celda18.setCellValue(listDatosExniva.get(x).getMesHerramientaExeniva());

                HSSFCell celda19 = fila.createCell(19);
                celda19.setCellStyle(styleMoneda);
                celda19.setCellValue(listDatosExniva.get(x).getMontoNetoFacturaHerramientaExeniva());

                HSSFCell celda20 = fila.createCell(20);
                celda20.setCellStyle(styleMoneda);
                celda20.setCellValue(listDatosExniva.get(x).getMontoIvaExentoHerramientaExeniva());

                HSSFCell celda21 = fila.createCell(21);
                celda21.setCellStyle(styleMoneda);
                celda21.setCellValue(listDatosExniva.get(x).getAnioFacturaHerramientaReteniva());

                HSSFCell celda22 = fila.createCell(22);
                celda22.setCellStyle(styleMoneda);
                celda22.setCellValue(listDatosExniva.get(x).getMesFacturaHerramientaReteIva());

                /*HSSFCell celda23 = fila.createCell(23);
                celda23.setCellStyle(styleMoneda);
                celda23.setCellValue(listDatosExniva.get(x).getImporteNetoDelBienOServicio());*/

 /*HSSFCell celda24 = fila.createCell(24);
                celda24.setCellStyle(styleMoneda);
                celda24.setCellValue(listDatosExniva.get(x).getImpuestoRetenido());*/
                HSSFCell celda25 = fila.createCell(25);
                celda25.setCellStyle(styleMoneda);
                celda25.setCellValue(listDatosExniva.get(x).getDifExeniva());

                HSSFCell celda26 = fila.createCell(26);
                celda26.setCellStyle(styleMoneda);
                celda26.setCellValue(listDatosExniva.get(x).getDibDebEx());

                HSSFCell celda27 = fila.createCell(27);
                celda27.setCellStyle(styleMoneda);
                celda27.setCellValue(listDatosExniva.get(x).getRecaudoPotencial());

            }
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        externalContext.setResponseContentType("application/vnd.ms-excel; charset=utf-8");

        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"IVA_por_Constancias_de_Exención_Vs._ExenIVA.xls\"");

        libro.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();
    }

}
