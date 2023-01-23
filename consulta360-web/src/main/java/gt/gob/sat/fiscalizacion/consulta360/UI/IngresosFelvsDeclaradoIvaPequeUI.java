/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;
import gt.gob.sat.fiscalizacion.consulta360.dto.ContribuyenteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IngresosFelvsDeclaradoIvaPequeDto;
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
import static org.apache.poi.ss.formula.functions.NumericFunction.LOG;
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
public class IngresosFelvsDeclaradoIvaPequeUI extends AbstractUI implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(VerificacionesEnCampoUI.class);
    private String pnit = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private ArrayList<IngresosFelvsDeclaradoIvaPequeDto> listDatosGeneral = new ArrayList();
    private ArrayList<IngresosFelvsDeclaradoIvaPequeDto> listDatosPeqCont = new ArrayList();
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
                + "ANIO, "
                + "MES, "
                + "CANTIDAD_RECEPTORES, "
                + "CANTIDAD_FACTURAS, "
                + "cast(Round(BASE_IMPONIBLE,2) as decimal(20,2))                                  AS BASE_IMPONIBLE,"
                + "cast(Round(MONTO_FEL,2) as decimal(20,2))                                       AS MONTO_FEL, "
                + "cast(Round(INGRESOS_AFECTOS_2046,2) as decimal(20,2))                           AS INGRESOS_AFECTOS_2046, "
                + "omiso, "
                + "cast(round(monto_fel-ventas_y_serv_prestados,2) as decimal(20,2))                 AS DIFERENCIA,"
                + "cast(Round(IVA_NO_DECLARADO,2) as decimal(20,2))                                AS IVA_NO_DECLARADO "
                + "From  sat_cruces.o_fel_vs_iva_peqcontrib_ge "
                + "WHERE nit = '" + pnit + "' "
                + "AND ANIO between '" + fecha_ini + "' AND '" + fecha_fin + "'"
                + "ORDER BY 1,2 DESC ";

        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            rs = statement.executeQuery(SQL);
            while (rs.next()) {

                IngresosFelvsDeclaradoIvaPequeDto datos = new IngresosFelvsDeclaradoIvaPequeDto();
                datos.setAnio(rs.getString("ANIO"));
                datos.setMes(rs.getString("MES"));
                datos.setCantidad_receptores(rs.getString("CANTIDAD_RECEPTORES"));
                datos.setCantidad_facturas(rs.getString("CANTIDAD_FACTURAS"));
                datos.setBase_imponible(rs.getBigDecimal("BASE_IMPONIBLE"));
                datos.setMonto_fel(rs.getBigDecimal("MONTO_FEL"));
                datos.setIngresos_afectos_2046(rs.getBigDecimal("INGRESOS_AFECTOS_2046"));
                datos.setOmiso(rs.getString("omiso"));
                datos.setDiferencia(rs.getBigDecimal("DIFERENCIA"));
                datos.setIva_no_declarado(rs.getBigDecimal("IVA_NO_DECLARADO"));

                listDatosPeqCont.add(datos);

            }
            conn.close();
            statement.close();


        } catch (Exception ex) {
            listDatosPeqCont.clear();
            errorMsg("Tabla no encontrada");
            /*  errorMsg(ex.getMessage());*/
            LOG.error(ex.getMessage());
        }

        if (listDatosPeqCont.isEmpty() || listDatosPeqCont == null) {
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

    public ArrayList<IngresosFelvsDeclaradoIvaPequeDto> getListDatosPeqCont() {
        return listDatosPeqCont;
    }

    public void setListDatosPeqCont(ArrayList<IngresosFelvsDeclaradoIvaPequeDto> listDatosPeqCont) {
        this.listDatosPeqCont = listDatosPeqCont;
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
        celdaEnc2.setCellValue("Ingresos FEL vs Declarado IVA PEQUEÑO CONTRIBUYENTE");
        /*---------------------------------------------------*/

        HSSFRow filaEnc2 = hoja.createRow(2);

        HSSFCell celdaEnc3 = filaEnc2.createCell(0);
        celdaEnc3.setCellValue("Periodo Desde");

        HSSFCell celdaEnc4 = filaEnc2.createCell(1);
        celdaEnc4.setCellValue(this.fecha_ini);

        HSSFCell celdaEnc5 = filaEnc2.createCell(7);
        celdaEnc5.setCellValue("Gerencia");

        HSSFCell celdaEnc6 = filaEnc2.createCell(8);
        celdaEnc6.setCellValue(listaDatosEncabezado.get(0).getGerencia());

        /*---------------------------------------------------*/
        HSSFRow filaEnc3 = hoja.createRow(3);

        HSSFCell celdaEnc7 = filaEnc3.createCell(0);
        celdaEnc7.setCellValue("Periodo Hasta");

        HSSFCell celdaEnc8 = filaEnc3.createCell(1);
        celdaEnc8.setCellValue(this.fecha_fin);

        HSSFCell celdaEnc9 = filaEnc3.createCell(7);
        celdaEnc9.setCellValue("Región");

        HSSFCell celdaEnc10 = filaEnc3.createCell(8);
        celdaEnc10.setCellValue(listaDatosEncabezado.get(0).getRegion());

        /*---------------------------------------------------*/
        HSSFRow filaEnc4 = hoja.createRow(4);

        HSSFCell celdaEnc11 = filaEnc4.createCell(0);
        celdaEnc11.setCellValue("Nit");

        HSSFCell celdaEnc12 = filaEnc4.createCell(1);
        celdaEnc12.setCellValue(listaDatosEncabezado.get(0).getNit());

        HSSFCell celdaEnc13 = filaEnc4.createCell(7);
        celdaEnc13.setCellValue("Nombre Actividad");

        HSSFCell celdaEnc14 = filaEnc4.createCell(8);
        celdaEnc14.setCellValue(listaDatosEncabezado.get(0).getActividadEconomica());

        /*---------------------------------------------------*/
        HSSFRow filaEnc5 = hoja.createRow(5);

        HSSFCell celdaEnc15 = filaEnc5.createCell(0);
        celdaEnc15.setCellValue("Nombre");

        HSSFCell celdaEnc16 = filaEnc5.createCell(1);
        celdaEnc16.setCellValue(listaDatosEncabezado.get(0).getNombreOrdenado());

        HSSFCell celdaEnc17 = filaEnc5.createCell(7);
        celdaEnc17.setCellValue("Dirección Invalida");

        /*---------------------------------------------------*/
        HSSFRow filaEnc6 = hoja.createRow(6);

        HSSFCell celdaEnc18 = filaEnc6.createCell(0);
        celdaEnc18.setCellValue("Dirección");

        HSSFCell celdaEnc19 = filaEnc6.createCell(1);
        celdaEnc19.setCellValue(listaDatosEncabezado.get(0).getDireccion());

        HSSFCell celdaEnc20 = filaEnc6.createCell(7);
        celdaEnc20.setCellValue("Nombre Regimen");

        HSSFCell celdaEnc21 = filaEnc6.createCell(8);
        celdaEnc21.setCellValue(listaDatosEncabezado.get(0).getNombreRegimen());

        /*---------------------------------------------------*/
        HSSFRow filaEnc7 = hoja.createRow(7);

        HSSFCell celdaEnc22 = filaEnc7.createCell(0);
        celdaEnc22.setCellValue("Clasificación");

        HSSFCell celdaEnc23 = filaEnc7.createCell(1);
        celdaEnc23.setCellValue(listaDatosEncabezado.get(0).getClasificacion());

        HSSFCell celdaEnc24 = filaEnc7.createCell(7);
        celdaEnc24.setCellValue("Status");

        HSSFCell celdaEnc25 = filaEnc7.createCell(8);
        celdaEnc25.setCellValue(listaDatosEncabezado.get(0).getStatus());

        /*---------------------------------------------------*/
        HSSFRow filaEnc8 = hoja.createRow(8);

        HSSFCell celdaEnc26 = filaEnc8.createCell(7);
        celdaEnc26.setCellValue("Fecha cambio regimen");

        HSSFCell celdaEnc27 = filaEnc8.createCell(8);
        celdaEnc27.setCellValue(listaDatosEncabezado.get(0).getFechaCambioRegimen());

        if (listDatosPeqCont.isEmpty() || listDatosPeqCont == null) {

            /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
            HSSFRow filaH = hoja.createRow(12);

            HSSFCell celdaH0 = filaH.createCell(0);
            celdaH0.setCellStyle(rowStyle);
            celdaH0.setCellValue("Anio");

            HSSFCell celdaH1 = filaH.createCell(1);
            celdaH1.setCellStyle(rowStyle);
            celdaH1.setCellValue("Mes");

            HSSFCell celdaH2 = filaH.createCell(2);
            celdaH2.setCellStyle(rowStyle);
            celdaH2.setCellValue("Cantidad Receptores");

            HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("Cantidad Facturas");

            HSSFCell celdaH4 = filaH.createCell(4);
            celdaH4.setCellStyle(rowStyle);
            celdaH4.setCellValue("Base Imponible");

            HSSFCell celdaH5 = filaH.createCell(5);
            celdaH5.setCellStyle(rowStyle);
            celdaH5.setCellValue("Monto Fel");

            HSSFCell celdaH6 = filaH.createCell(6);
            celdaH6.setCellStyle(rowStyle);
            celdaH6.setCellValue("Ingresos Afectos 2046 ");

            HSSFCell celdaH7 = filaH.createCell(7);
            celdaH7.setCellStyle(rowStyle);
            celdaH7.setCellValue("Omiso");

            HSSFCell celdaH8 = filaH.createCell(8);
            celdaH8.setCellStyle(rowStyle);
            celdaH8.setCellValue("Diferencia");

            HSSFCell celdaH9 = filaH.createCell(9);
            celdaH9.setCellStyle(rowStyle);
            celdaH9.setCellValue("Iva no declarado");

            for (int x = 0; x < listDatosPeqCont.size(); x++) {

                HSSFRow fila = hoja.createRow(x + 12);

                HSSFCell celda0 = fila.createCell(0);
                celda0.setCellStyle(cellStyle);
                celda0.setCellValue(listDatosPeqCont.get(x).getAnio());

                HSSFCell celda1 = fila.createCell(1);
                celda1.setCellStyle(cellStyle);
                celda1.setCellValue(listDatosPeqCont.get(x).getMes());

                HSSFCell celda2 = fila.createCell(2);
                celda2.setCellStyle(cellStyle);
                celda2.setCellValue(listDatosPeqCont.get(x).getCantidad_receptores());

                HSSFCell celda3 = fila.createCell(3);
                celda3.setCellStyle(cellStyle);
                celda3.setCellValue(listDatosPeqCont.get(x).getCantidad_facturas());

                HSSFCell celda4 = fila.createCell(4);
                celda4.setCellStyle(styleMoneda);
                celda4.setCellValue(listDatosPeqCont.get(x).getBase_imponible().doubleValue());

                HSSFCell celda5 = fila.createCell(5);
                celda5.setCellStyle(cellStyle);
                celda5.setCellValue(listDatosPeqCont.get(x).getMonto_fel().doubleValue());

                HSSFCell celda6 = fila.createCell(6);
                celda6.setCellStyle(styleMoneda);
                celda6.setCellValue(listDatosPeqCont.get(x).getIngresos_afectos_2046().doubleValue());

                HSSFCell celda7 = fila.createCell(7);
                celda7.setCellStyle(cellStyle);
                celda7.setCellValue(listDatosPeqCont.get(x).getOmiso());

                HSSFCell celda8 = fila.createCell(8);
                celda8.setCellStyle(styleMoneda);
                celda8.setCellValue(listDatosPeqCont.get(x).getDiferencia().doubleValue());

                HSSFCell celda9 = fila.createCell(9);
                celda9.setCellStyle(styleMoneda);
                celda9.setCellValue(listDatosPeqCont.get(x).getIva_no_declarado().doubleValue());

            }
        }

        if (listDatosGeneral.isEmpty() || listDatosGeneral == null) {

            /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
            HSSFRow filaH = hoja.createRow(11);

            HSSFCell celdaH0 = filaH.createCell(0);
            celdaH0.setCellStyle(rowStyle);
            celdaH0.setCellValue("Anio");

            HSSFCell celdaH1 = filaH.createCell(1);
            celdaH1.setCellStyle(rowStyle);
            celdaH1.setCellValue("Mes");

            HSSFCell celdaH2 = filaH.createCell(2);
            celdaH2.setCellStyle(rowStyle);
            celdaH2.setCellValue("Cantidad Recpetores");

            HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("Cantidad Facturas");

            HSSFCell celdaH4 = filaH.createCell(4);
            celdaH4.setCellStyle(rowStyle);
            celdaH4.setCellValue("Base Imponible");

            HSSFCell celdaH5 = filaH.createCell(5);
            celdaH5.setCellStyle(rowStyle);
            celdaH5.setCellValue("Monto Fel");

            HSSFCell celdaH6 = filaH.createCell(6);
            celdaH6.setCellStyle(rowStyle);
            celdaH6.setCellValue("Ingresos Afectos 2046");

            HSSFCell celdaH7 = filaH.createCell(7);
            celdaH7.setCellStyle(rowStyle);
            celdaH7.setCellValue("Omiso");

            HSSFCell celdaH8 = filaH.createCell(8);
            celdaH8.setCellStyle(rowStyle);
            celdaH8.setCellValue("Diferencia");

            HSSFCell celdaH9 = filaH.createCell(9);
            celdaH9.setCellStyle(rowStyle);
            celdaH9.setCellValue("Iva no declarado");

            for (int x = 0; x < listDatosPeqCont.size(); x++) {

                HSSFRow fila = hoja.createRow(x + 12);

                 HSSFCell celda0 = fila.createCell(0);
                celda0.setCellStyle(cellStyle);
                celda0.setCellValue(listDatosPeqCont.get(x).getAnio());

                HSSFCell celda1 = fila.createCell(1);
                celda1.setCellStyle(cellStyle);
                celda1.setCellValue(listDatosPeqCont.get(x).getMes());

                HSSFCell celda2 = fila.createCell(2);
                celda2.setCellStyle(cellStyle);
                celda2.setCellValue(listDatosPeqCont.get(x).getCantidad_receptores());

                HSSFCell celda3 = fila.createCell(3);
                celda3.setCellStyle(cellStyle);
                celda3.setCellValue(listDatosPeqCont.get(x).getCantidad_facturas());

                HSSFCell celda4 = fila.createCell(4);
                celda4.setCellStyle(styleMoneda);
                celda4.setCellValue(listDatosPeqCont.get(x).getBase_imponible().doubleValue());

                HSSFCell celda5 = fila.createCell(5);
                celda5.setCellStyle(cellStyle);
                celda5.setCellValue(listDatosPeqCont.get(x).getMonto_fel().doubleValue());

                HSSFCell celda6 = fila.createCell(6);
                celda6.setCellStyle(styleMoneda);
                celda6.setCellValue(listDatosPeqCont.get(x).getIngresos_afectos_2046().doubleValue());

                HSSFCell celda7 = fila.createCell(7);
                celda7.setCellStyle(cellStyle);
                celda7.setCellValue(listDatosPeqCont.get(x).getOmiso());

                HSSFCell celda8 = fila.createCell(8);
                celda8.setCellStyle(styleMoneda);
                celda8.setCellValue(listDatosPeqCont.get(x).getDiferencia().doubleValue());

                HSSFCell celda9 = fila.createCell(9);
                celda9.setCellStyle(styleMoneda);
                celda9.setCellValue(listDatosPeqCont.get(x).getIva_no_declarado().doubleValue());

            }
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        externalContext.setResponseContentType("application/vnd.ms-excel; charset=utf-8");

        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"ingresos_fel_vs_declarado_iva_pequeño_contribuyente.xls\"");

        libro.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();
    }

}
