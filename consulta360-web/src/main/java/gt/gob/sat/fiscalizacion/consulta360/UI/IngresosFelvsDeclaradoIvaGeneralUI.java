/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.ContribuyenteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IngresosFelvsDeclaradoIvaGeneralDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.PequenoContribuyenteEncabezadoDto;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
public class IngresosFelvsDeclaradoIvaGeneralUI extends AbstractUI implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(VerificacionesEnCampoUI.class);
    private String pnit = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private ArrayList<IngresosFelvsDeclaradoIvaGeneralDto> listDatosGeneral = new ArrayList();
    private ArrayList<PequenoContribuyenteEncabezadoDto> listaDatosEncabezado;
    private int fecha_fin;
    private int fecha_ini;

    @PostConstruct
    public void inicializar() {
        Date date = new Date();
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(date);
        fecha_fin = fecha.get(Calendar.YEAR);

        Calendar fecha2 = Calendar.getInstance();
        fecha2.setTime(date);
        fecha2.add(Calendar.YEAR, -4);
        fecha_ini = fecha2.get(Calendar.YEAR);

        String SQL = "SELECT "
                + "anio, "
                + "mes, "
                + "cantidad_receptores, "
                + "cantidad_facturas, "
                + "cast(round(base_imponible,2) as decimal(20,2)) as base_imponible, "
                + "cantidad_doctos_ncre,  "
                + "cast(round(monto_ncre,2) as decimal(20,2)) as monto_ncre, "
                + "cantidad_doctos_nabn, "
                + "cast(round(monto_nabn,2) as decimal(20,2)) as monto_nabn, "
                + "cast(round(monto_fel,2) as decimal(20,2)) as monto_fel, "
                + "cast(round(ingresos_afectos_2237,2) as decimal(20,2)) as ingresos_afectos_2237, "
                + "omiso, "
                + "cast(round(diferencia,2) as decimal(20,2)) as diferencia, "
                + "cast(round(iva_no_declarado,2) as decimal(20,2)) as iva_no_declarado  "
                + "from  sat_cruces.o_fel_vs_iva_general_ge "
                + "where nit = '" + pnit + "' "
                + " and anio between '" + fecha_ini + "' and '" + fecha_fin + "'"
                + " order by 1,2 desc ";

        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            rs = statement.executeQuery(SQL);
            while (rs.next()) {

                IngresosFelvsDeclaradoIvaGeneralDto datos = new IngresosFelvsDeclaradoIvaGeneralDto();
                datos.setAnio(rs.getString("anio"));
                datos.setMes(rs.getString("mes"));
                datos.setCantidad_receptores(rs.getString("cantidad_receptores"));
                datos.setCantidad_facturas(rs.getString("cantidad_facturas"));
                datos.setBase_imponible(rs.getBigDecimal("base_imponible"));
                datos.setCantidad_doctos_ncre(rs.getString("cantidad_doctos_ncre"));
                datos.setMonto_ncre(rs.getBigDecimal("monto_ncre"));
                datos.setCantidad_doctos_nabn(rs.getString("cantidad_doctos_nabn"));
                datos.setMonto_nabn(rs.getBigDecimal("monto_nabn"));
                datos.setMonto_fel(rs.getBigDecimal("monto_fel"));
                datos.setIngresos_afectos_2237(rs.getBigDecimal("ingresos_afectos_2237"));
                datos.setOmiso(rs.getString("omiso"));
                datos.setDiferencia(rs.getBigDecimal("diferencia"));
                datos.setIva_no_declarado(rs.getBigDecimal("iva_no_declarado"));

                listDatosGeneral.add(datos);
            }
            conn.close();
            statement.close();

        } catch (Exception ex) {
            listDatosGeneral.clear();
            errorMsg("Tabla no encontrada");
            /*  errorMsg(ex.getMessage());*/
            LOG.error(ex.getMessage());
        }

        if (listDatosGeneral.isEmpty() || listDatosGeneral == null) {
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

    public ArrayList<IngresosFelvsDeclaradoIvaGeneralDto> getListDatosGeneral() {
        return listDatosGeneral;
    }

    public void setListDatos(ArrayList<IngresosFelvsDeclaradoIvaGeneralDto> listDatosGeneral) {
        this.listDatosGeneral = listDatosGeneral;
    }

    public ArrayList<PequenoContribuyenteEncabezadoDto> getListaDatosEncabezado() {
        return listaDatosEncabezado;
    }

    public void setListaDatosEncabezado(ArrayList<PequenoContribuyenteEncabezadoDto> listaDatosEncabezado) {
        this.listaDatosEncabezado = listaDatosEncabezado;
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
        celdaEnc2.setCellValue("Ingresos Fel vs Declarados Iva General");
        /*---------------------------------------------------*/

        HSSFRow filaEnc2 = hoja.createRow(2);

        HSSFCell celdaEnc3 = filaEnc2.createCell(0);
        celdaEnc3.setCellValue("Periodo Desde");

        HSSFCell celdaEnc4 = filaEnc2.createCell(1);
        celdaEnc4.setCellValue(fecha_ini - 1);

        /*---------------------------------------------------*/
        HSSFRow filaEnc3 = hoja.createRow(3);

        HSSFCell celdaEnc5 = filaEnc3.createCell(0);
        celdaEnc5.setCellValue("Periodo Hasta");

        HSSFCell celdaEnc6 = filaEnc3.createCell(1);
        celdaEnc6.setCellValue(fecha_fin - 12);

        /*---------------------------------------------------*/
        HSSFRow filaEnc4 = hoja.createRow(4);

        HSSFCell celdaEnc7 = filaEnc4.createCell(0);
        celdaEnc7.setCellValue("NIT contribuyente");

        HSSFCell celdaEnc8 = filaEnc4.createCell(1);
        celdaEnc8.setCellValue(listaDatosEncabezado.get(0).getNit());

        /*---------------------------------------------------*/
        HSSFRow filaEnc5 = hoja.createRow(5);

        HSSFCell celdaEnc9 = filaEnc5.createCell(0);
        celdaEnc9.setCellValue("Nombre contribuyente");

        HSSFCell celdaEnc10 = filaEnc5.createCell(1);
        celdaEnc10.setCellValue(listaDatosEncabezado.get(0).getNombreOrdenado());

        /*---------------------------------------------------*/
        HSSFRow filaEnc6 = hoja.createRow(6);

        HSSFCell celdaEnc11 = filaEnc6.createCell(0);
        celdaEnc11.setCellValue("Clasificación");

        HSSFCell celdaEnc12 = filaEnc6.createCell(1);
        celdaEnc12.setCellValue(listaDatosEncabezado.get(0).getClasificacion());

        /*---------------------------------------------------*/
        HSSFRow filaEnc7 = hoja.createRow(7);

        HSSFCell celdaEnc13 = filaEnc7.createCell(0);
        celdaEnc13.setCellValue("Gerencia");

        HSSFCell celdaEnc14 = filaEnc7.createCell(1);
        celdaEnc14.setCellValue(listaDatosEncabezado.get(0).getGerencia());
        /*---------------------------------------------------*/

        HSSFRow filaEnc8 = hoja.createRow(8);

        HSSFCell celdaEnc15 = filaEnc8.createCell(0);
        celdaEnc15.setCellValue("Región");

        HSSFCell celdaEnc16 = filaEnc8.createCell(1);
        celdaEnc16.setCellValue(listaDatosEncabezado.get(0).getRegion());

        if (!listDatosGeneral.isEmpty() || listDatosGeneral != null) {

            /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
            HSSFRow filaH = hoja.createRow(12);

            HSSFCell celdaH0 = filaH.createCell(0);
            celdaH0.setCellStyle(rowStyle);
            celdaH0.setCellValue("AÑO FISCAL");

            HSSFCell celdaH1 = filaH.createCell(1);
            celdaH1.setCellStyle(rowStyle);
            celdaH1.setCellValue("MES FISCAL");

            HSSFCell celdaH2 = filaH.createCell(2);
            celdaH2.setCellStyle(rowStyle);
            celdaH2.setCellValue("CANTIDAD RECEPTORES");

            HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("CANTIDAD FACTURAS");

            HSSFCell celdaH4 = filaH.createCell(4);
            celdaH4.setCellStyle(rowStyle);
            celdaH4.setCellValue("BASE IMPONIBLE");

            HSSFCell celdaH5 = filaH.createCell(5);
            celdaH5.setCellStyle(rowStyle);
            celdaH5.setCellValue("CANTIDAD DOCTOS. NCRE");

            HSSFCell celdaH6 = filaH.createCell(6);
            celdaH6.setCellStyle(rowStyle);
            celdaH6.setCellValue("MONTO NCRE");

            HSSFCell celdaH7 = filaH.createCell(7);
            celdaH7.setCellStyle(rowStyle);
            celdaH7.setCellValue("CANTIDAD DOCTOS. NABN");

            HSSFCell celdaH8 = filaH.createCell(8);
            celdaH8.setCellStyle(rowStyle);
            celdaH8.setCellValue("MONTO NABN");

            HSSFCell celdaH9 = filaH.createCell(9);
            celdaH9.setCellStyle(rowStyle);
            celdaH9.setCellValue("MONTO FEL");

            HSSFCell celdaH10 = filaH.createCell(10);
            celdaH10.setCellStyle(rowStyle);
            celdaH10.setCellValue("INGRESOS AFECTOS 2237");

            HSSFCell celdaH11 = filaH.createCell(11);
            celdaH11.setCellStyle(rowStyle);
            celdaH11.setCellValue("OMISO");

            HSSFCell celdaH12 = filaH.createCell(12);
            celdaH12.setCellStyle(rowStyle);
            celdaH12.setCellValue("DIFERENCIA");

            HSSFCell celdaH13 = filaH.createCell(13);
            celdaH13.setCellStyle(rowStyle);
            celdaH13.setCellValue("IVA NO DECLARADO");

            for (int x = 0; x < listDatosGeneral.size(); x++) {

                HSSFRow fila = hoja.createRow(x + 12);

                HSSFCell celda0 = fila.createCell(0);
                celda0.setCellStyle(cellStyle);
                celda0.setCellValue(listDatosGeneral.get(x).getAnio());

                HSSFCell celda1 = fila.createCell(1);
                celda1.setCellStyle(cellStyle);
                celda1.setCellValue(listDatosGeneral.get(x).getMes());

                HSSFCell celda2 = fila.createCell(2);
                celda2.setCellStyle(cellStyle);
                celda2.setCellValue(listDatosGeneral.get(x).getCantidad_receptores());

                HSSFCell celda3 = fila.createCell(3);
                celda3.setCellStyle(cellStyle);
                celda3.setCellValue(listDatosGeneral.get(x).getCantidad_facturas());

                HSSFCell celda4 = fila.createCell(4);
                celda4.setCellStyle(styleMoneda);
                celda4.setCellValue(listDatosGeneral.get(x).getBase_imponible().doubleValue());

                HSSFCell celda5 = fila.createCell(5);
                celda5.setCellStyle(cellStyle);
                celda5.setCellValue(listDatosGeneral.get(x).getCantidad_doctos_ncre());

                HSSFCell celda6 = fila.createCell(6);
                celda6.setCellStyle(styleMoneda);
                celda6.setCellValue(listDatosGeneral.get(x).getMonto_ncre().doubleValue());

                HSSFCell celda7 = fila.createCell(7);
                celda7.setCellStyle(cellStyle);
                celda7.setCellValue(listDatosGeneral.get(x).getCantidad_doctos_nabn());

                HSSFCell celda8 = fila.createCell(8);
                celda8.setCellStyle(styleMoneda);
                celda8.setCellValue(listDatosGeneral.get(x).getMonto_nabn().doubleValue());

                HSSFCell celda9 = fila.createCell(9);
                celda9.setCellStyle(styleMoneda);
                celda9.setCellValue(listDatosGeneral.get(x).getMonto_fel().doubleValue());

                HSSFCell celda10 = fila.createCell(10);
                celda10.setCellStyle(styleMoneda);
                celda10.setCellValue(listDatosGeneral.get(x).getIngresos_afectos_2237().doubleValue());

                HSSFCell celda11 = fila.createCell(11);
                celda11.setCellStyle(cellStyle);
                celda11.setCellValue(listDatosGeneral.get(x).getOmiso());

                HSSFCell celda12 = fila.createCell(12);
                celda12.setCellStyle(styleMoneda);
                celda12.setCellValue(listDatosGeneral.get(x).getDiferencia().doubleValue());

                HSSFCell celda13 = fila.createCell(13);
                celda13.setCellStyle(styleMoneda);
                celda13.setCellValue(listDatosGeneral.get(x).getIva_no_declarado().doubleValue());

            }
        }

        if (!listDatosGeneral.isEmpty() || listDatosGeneral != null) {

            /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
            HSSFRow filaH = hoja.createRow(11);

            HSSFCell celdaH0 = filaH.createCell(0);
            celdaH0.setCellStyle(rowStyle);
            celdaH0.setCellValue("AÑO FISCAL");

            HSSFCell celdaH1 = filaH.createCell(1);
            celdaH1.setCellStyle(rowStyle);
            celdaH1.setCellValue("MES FISCAL");

            HSSFCell celdaH2 = filaH.createCell(2);
            celdaH2.setCellStyle(rowStyle);
            celdaH2.setCellValue("CANTIDAD RECEPTORES");

            HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("CANTIDAD FACTURAS");

            HSSFCell celdaH4 = filaH.createCell(4);
            celdaH4.setCellStyle(rowStyle);
            celdaH4.setCellValue("BASE IMPONIBLE");

            HSSFCell celdaH5 = filaH.createCell(5);
            celdaH5.setCellStyle(rowStyle);
            celdaH5.setCellValue("CANTIDAD DOCTOS. NCRE");

            HSSFCell celdaH6 = filaH.createCell(6);
            celdaH6.setCellStyle(rowStyle);
            celdaH6.setCellValue("MONTO NCRE");

            HSSFCell celdaH7 = filaH.createCell(7);
            celdaH7.setCellStyle(rowStyle);
            celdaH7.setCellValue("CANTIDAD DOCTOS. NABN");

            HSSFCell celdaH8 = filaH.createCell(8);
            celdaH8.setCellStyle(rowStyle);
            celdaH8.setCellValue("MONTO NABN");

            HSSFCell celdaH9 = filaH.createCell(9);
            celdaH9.setCellStyle(rowStyle);
            celdaH9.setCellValue("MONTO FEL");

            HSSFCell celdaH10 = filaH.createCell(10);
            celdaH10.setCellStyle(rowStyle);
            celdaH10.setCellValue("INGRESOS AFECTOS 2237");

            HSSFCell celdaH11 = filaH.createCell(11);
            celdaH11.setCellStyle(rowStyle);
            celdaH11.setCellValue("OMISO");

            HSSFCell celdaH12 = filaH.createCell(12);
            celdaH12.setCellStyle(rowStyle);
            celdaH12.setCellValue("DIFERENCIA");

            HSSFCell celdaH13 = filaH.createCell(13);
            celdaH13.setCellStyle(rowStyle);
            celdaH13.setCellValue("IVA NO DECLARADO");

            for (int x = 0; x < listDatosGeneral.size(); x++) {

                HSSFRow fila = hoja.createRow(x + 12);

                HSSFCell celda0 = fila.createCell(0);
                celda0.setCellStyle(cellStyle);
                celda0.setCellValue(listDatosGeneral.get(x).getAnio());

                HSSFCell celda1 = fila.createCell(1);
                celda1.setCellStyle(cellStyle);
                celda1.setCellValue(listDatosGeneral.get(x).getMes());

                HSSFCell celda2 = fila.createCell(2);
                celda2.setCellStyle(cellStyle);
                celda2.setCellValue(listDatosGeneral.get(x).getCantidad_receptores());

                HSSFCell celda3 = fila.createCell(3);
                celda3.setCellStyle(cellStyle);
                celda3.setCellValue(listDatosGeneral.get(x).getCantidad_facturas());

                HSSFCell celda4 = fila.createCell(4);
                celda4.setCellStyle(styleMoneda);
                celda4.setCellValue(listDatosGeneral.get(x).getBase_imponible().doubleValue());

                HSSFCell celda5 = fila.createCell(5);
                celda5.setCellStyle(cellStyle);
                celda5.setCellValue(listDatosGeneral.get(x).getCantidad_doctos_ncre());

                HSSFCell celda6 = fila.createCell(6);
                celda6.setCellStyle(styleMoneda);
                celda6.setCellValue(listDatosGeneral.get(x).getMonto_ncre().doubleValue());

                HSSFCell celda7 = fila.createCell(7);
                celda7.setCellStyle(cellStyle);
                celda7.setCellValue(listDatosGeneral.get(x).getCantidad_doctos_nabn());

                HSSFCell celda8 = fila.createCell(8);
                celda8.setCellStyle(styleMoneda);
                celda8.setCellValue(listDatosGeneral.get(x).getMonto_nabn().doubleValue());

                HSSFCell celda9 = fila.createCell(9);
                celda9.setCellStyle(styleMoneda);
                celda9.setCellValue(listDatosGeneral.get(x).getMonto_fel().doubleValue());

                HSSFCell celda10 = fila.createCell(10);
                celda10.setCellStyle(styleMoneda);
                celda10.setCellValue(listDatosGeneral.get(x).getIngresos_afectos_2237().doubleValue());

                HSSFCell celda11 = fila.createCell(11);
                celda11.setCellStyle(cellStyle);
                celda11.setCellValue(listDatosGeneral.get(x).getOmiso());

                HSSFCell celda12 = fila.createCell(12);
                celda12.setCellStyle(styleMoneda);
                celda12.setCellValue(listDatosGeneral.get(x).getDiferencia().doubleValue());

                HSSFCell celda13 = fila.createCell(13);
                celda13.setCellStyle(styleMoneda);
                celda13.setCellValue(listDatosGeneral.get(x).getIva_no_declarado().doubleValue());

            }
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        externalContext.setResponseContentType("application/vnd.ms-excel; charset=utf-8");

        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"ingresos_fel_vs_declarado_iva_general.xls\"");

        libro.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();
    }

}
