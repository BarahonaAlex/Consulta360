/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.ContribuyenteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IngreRetenIvaVsIngresosDeclaIvaGeneralDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IngresosFelvsDeclaradoIvaPequeDto;
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
public class IngreRetenIvaVsIngresosDeclaIVaGeneralUI extends AbstractUI implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(VerificacionesEnCampoUI.class);
    private String pnit = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private ArrayList<IngresosFelvsDeclaradoIvaPequeDto> listDatosGeneral = new ArrayList();
    private ArrayList<IngreRetenIvaVsIngresosDeclaIvaGeneralDto> listDatosReten = new ArrayList();
    private ArrayList<PequenoContribuyenteEncabezadoDto> listaDatosEncabezado;

    private int fecha_ini;
    private int fecha_fin;
    
    private ArrayList<ImpuestoIvaGeneralDto> lista;
 

    @PostConstruct
    public void inicializar() {
        
//        lista = this.generalSrvImpl.DetalleIvaGeneral(pnit);
        Date date = new Date();
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(date);
        this.fecha_fin = fecha.get(Calendar.YEAR);

        Calendar fecha2 = Calendar.getInstance();
        fecha2.setTime(date);
        fecha2.add(Calendar.YEAR, -4);
        this.fecha_ini = fecha2.get(Calendar.YEAR);

        String SQL = "SELECT "
                + "periodo_desde, "
                + "perido_hasta, "
                + "fecha_recaudo, "
                + "codigo_formulario, "
                + "numero_formulario, "
                + "status_declaracion, "
                + "format(cast(round(ventas_exentas,2) as decimal(20,2)),'c','es-gt')               as   ventas_exentas, "
                + "format(cast(Round(ventas_medicamentos,2) as decimal(20,2)),'C','Es-Gt')          as   ventas_medicamentos, "
                + "format(cast(Round(ventas_no_afecta,2) as decimal(20,2)),'C','Es-Gt')             as   ventas_no_afecta, "
                + "format(cast(Round(ventas_vehiculos,2) as decimal(20,2)),'C','Es-Gt')             as   ventas_vehiculos, "
                + "format(cast(Round(ventas_vehiculos_act,2) as decimal(20,2)),'C','Es-Gt')         as   ventas_vehiculos_act, "
                + "format(cast(Round(ventas,2) as decimal(20,2)),'C','Es-Gt')                       as   ventas, "
                + "format(cast(Round(serv_prestados,2) as decimal(20,2)),'C','Es-Gt')               as   serv_prestados, "
                + "format(cast(Round(suma_base_deb,2) as decimal(20,2)),'C','Es-Gt')                as   suma_base_deb, "
                + "format(cast(Round(remanente_reteniva_per_act,2) as decimal(20,2)),'C','Es-Gt')   as   remanente_reteniva_per_act, "
                + "format(cast(Round(saldo_reteniva_sig_per,2) as decimal(20,2)),'C','Es-Gt')       as   saldo_reteniva_sig_per, "
                + "format(cast(Round(ingresos_afectos,2) as decimal(20,2)),'C','Es-Gt')             as   ingresos_afectos, "
                + "cantidad_facturas, "
                + "format(cast(Round(potencial_iva,2) as decimal(20,2)),'C','Es-Gt')                as   potencial_iva, "
                + "anio, "
                + "mes, "
                + "nit, "
                + "tipo_persona, "
                + "format(cast(Round(suma_importe_neto,2) as decimal(20,2)),'C','Es-Gt')            as   suma_importe_neto, "
                + "cantidad_constancias, "
                + "cantidad_retenedores, "
                + "suma_valor_retencion, "
                + "format(cast(Round(monto_reteniva_recibidas,2) as decimal(20,2)),'C','Es-Gt')     as   monto_reteniva_recibidas, "
                + "format(cast(Round(diferencia_base_reteniva,2) as decimal(20,2)),'C','Es-Gt')     as   diferencia_base_reteniva, "
                + "format(cast(Round(potencial_iva,2) as decimal(20,2)),'C','Es-Gt')                as   potencial_iva, "
                + "format(cast(Round(difencia_constancia_reteniva,2) as decimal(20,2)),'C','Es-Gt') as   difencia_constancia_reteniva, "
                + "format(cast(Round(potencial_constacias,2) as decimal(20,2)),'C','Es-Gt')         as   potencial_constacias, "
                + "format(cast(Round(potencial_total_iva,2) as decimal(20,2)),'C','Es-Gt')          as   potencial_total_iva, "
                + "format(cast(Round(suma_potencial_iva,2) as decimal(20,2)),'C','Es-Gt')           as   suma_potencial_iva "
                + "from  sat_cruces.o_reteniva_vs_iva_general_ag "
                + "WHERE  nit = '" + pnit + "' "
                + "AND  YEAR(periodo_desde) >= '" + Integer.toString(fecha_ini) + "'"
                + "AND  YEAR(perido_hasta) <=  '" + Integer.toString(fecha_fin) + "'";

        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            //System.out.print(SQL);
            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                IngreRetenIvaVsIngresosDeclaIvaGeneralDto datos = new IngreRetenIvaVsIngresosDeclaIvaGeneralDto();
                datos.setPeriodo_desde(rs.getString("periodo_desde"));
                datos.setPeriodo_hasta(rs.getString("perido_hasta"));
                datos.setFecha_recaudo(rs.getString("fecha_recaudo"));
                datos.setCodigo_formulario(rs.getString("codigo_formulario"));
                datos.setNumero_formulario(rs.getString("numero_formulario"));
                datos.setStatus(rs.getString("status_declaracion"));
                datos.setVentas_exentas(rs.getString("ventas_exentas"));
                datos.setVentas_medicamentos(rs.getString("ventas_medicamentos"));
                datos.setVentas_no_afecta(rs.getString("ventas_no_afecta"));
                datos.setVentas_vehiculos(rs.getString("ventas_vehiculos"));
                datos.setVentas_vehiculos_act(rs.getString("ventas_vehiculos_act"));
                datos.setVentas(rs.getString("ventas"));
                datos.setServ_prestados(rs.getString("serv_prestados"));
                datos.setSuma_base_deb(rs.getString("suma_base_deb"));
                datos.setRemanente_reteniva_per_act(rs.getString("remanente_reteniva_per_act"));
                datos.setSaldo_reteniva_sig_per(rs.getString("saldo_reteniva_sig_per"));
                datos.setIngresos_afectos(rs.getString("ingresos_afectos"));
                datos.setAnio(rs.getString("anio"));
                datos.setMes(rs.getString("mes"));
                datos.setNit(rs.getString("nit"));
                datos.setTipo_persona(rs.getString("tipo_persona"));
                datos.setSuma_importe_neto(rs.getString("suma_importe_neto"));
                datos.setCantidad_constancias(rs.getString("cantidad_constancias"));
                datos.setCantidad_retenedores(rs.getString("cantidad_retenedores"));
                datos.setCantidad_facturas(rs.getString("cantidad_facturas"));
                datos.setSuma_valor_retencion(rs.getString("suma_valor_retencion"));
                datos.setMonto_reteniva_recibidas(rs.getString("monto_reteniva_recibidas"));
                datos.setDiferencia_base_reteniva(rs.getString("diferencia_base_reteniva"));
                datos.setPotencial_iva(rs.getString("potencial_iva"));
                datos.setDiferencia_constancias_reteniva(rs.getString("difencia_constancia_reteniva"));
                datos.setPotencial_constancias(rs.getString("potencial_constacias"));
                datos.setPotencial_total_iva(rs.getString("potencial_total_iva"));
                datos.setSuma_potencial_iva(rs.getString("suma_potencial_iva"));
                listDatosReten.add(datos);

            }
            conn.close();
            statement.close();

        } catch (Exception ex) {
            listDatosReten.clear();
            errorMsg("Tabla no encontrada");
            /*  errorMsg(ex.getMessage());*/
            LOG.error(ex.getMessage());
        }

        if (listDatosReten.isEmpty() || listDatosReten == null) {
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

    public ArrayList<IngreRetenIvaVsIngresosDeclaIvaGeneralDto> getListDatosReten() {
        return listDatosReten;
    }

    public void setListDatosReten(ArrayList<IngreRetenIvaVsIngresosDeclaIvaGeneralDto> listDatosReten) {
        this.listDatosReten = listDatosReten;
    }

    public int getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(int fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public int getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(int fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public ArrayList<ImpuestoIvaGeneralDto> getLista() {
        return lista;
    }

    public void setLista(ArrayList<ImpuestoIvaGeneralDto> lista) {
        this.lista = lista;
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
        celdaEnc2.setCellValue("Ingresos RetenIVA Vs. Ingresos declardos IVA General");
        /*---------------------------------------------------*/

        HSSFRow filaEnc2 = hoja.createRow(2);

        HSSFCell celdaEnc3 = filaEnc2.createCell(0);
        celdaEnc3.setCellValue("NIT");

        HSSFCell celdaEnc4 = filaEnc2.createCell(1);
        celdaEnc4.setCellValue(listaDatosEncabezado.get(0).getNit());

        HSSFCell celdaEnc5 = filaEnc2.createCell(7);
        celdaEnc5.setCellValue("Dirección");

        HSSFCell celdaEnc6 = filaEnc2.createCell(8);
        celdaEnc6.setCellValue(listaDatosEncabezado.get(0).getDireccion());

        /*---------------------------------------------------*/
        HSSFRow filaEnc3 = hoja.createRow(3);

        HSSFCell celdaEnc7 = filaEnc3.createCell(0);
        celdaEnc7.setCellValue("Nombre");

        HSSFCell celdaEnc8 = filaEnc3.createCell(1);
        celdaEnc8.setCellValue(listaDatosEncabezado.get(0).getNombreOrdenado());

        HSSFCell celdaEnc9 = filaEnc3.createCell(7);
        celdaEnc9.setCellValue("Municipio");

        HSSFCell celdaEnc10 = filaEnc3.createCell(8);
        celdaEnc10.setCellValue(listaDatosEncabezado.get(0).getNombreMunicipio());

        /*---------------------------------------------------*/
        HSSFRow filaEnc4 = hoja.createRow(4);

        HSSFCell celdaEnc11 = filaEnc4.createCell(0);
        celdaEnc11.setCellValue("Gerencia");

        HSSFCell celdaEnc12 = filaEnc4.createCell(1);
        celdaEnc12.setCellValue(listaDatosEncabezado.get(0).getGerencia());

        HSSFCell celdaEnc13 = filaEnc4.createCell(7);
        celdaEnc13.setCellValue("Departamento");

        HSSFCell celdaEnc14 = filaEnc4.createCell(8);
        celdaEnc14.setCellValue(listaDatosEncabezado.get(0).getNombreDepartamento());

        /*---------------------------------------------------*/
        HSSFRow filaEnc5 = hoja.createRow(5);

        HSSFCell celdaEnc15 = filaEnc5.createCell(0);
        celdaEnc15.setCellValue("Actividad Económica");

        HSSFCell celdaEnc16 = filaEnc5.createCell(1);
        celdaEnc16.setCellValue(listaDatosEncabezado.get(0).getActividadEconomica());

        HSSFCell celdaEnc17 = filaEnc5.createCell(7);
        celdaEnc17.setCellValue("Correo electrónico RTU");

        HSSFCell celdaEnc18 = filaEnc5.createCell(8);
        celdaEnc18.setCellValue(listaDatosEncabezado.get(0).getCorreoRtu());

        /*---------------------------------------------------*/
        HSSFRow filaEnc6 = hoja.createRow(6);

        HSSFCell celdaEnc19 = filaEnc6.createCell(0);
        celdaEnc19.setCellValue("Personería");

        HSSFCell celdaEnc20 = filaEnc6.createCell(1);
        celdaEnc20.setCellValue(listaDatosEncabezado.get(0).getPersoneria());

        HSSFCell celdaEnc21 = filaEnc6.createCell(7);
        celdaEnc21.setCellValue("Correo electrónico Agencia Virtual");

        HSSFCell celdaEnc22 = filaEnc6.createCell(8);
        celdaEnc22.setCellValue(listaDatosEncabezado.get(0).getCorreoAgenciaVirtual());

        /*---------------------------------------------------*/
        HSSFRow filaEnc7 = hoja.createRow(7);

        HSSFCell celdaEnc23 = filaEnc7.createCell(0);
        celdaEnc23.setCellValue("Status contribuyente");

        HSSFCell celdaEnc24 = filaEnc7.createCell(1);
        celdaEnc24.setCellValue(listaDatosEncabezado.get(0).getStatus());

        HSSFCell celdaEnc25 = filaEnc7.createCell(7);
        celdaEnc25.setCellValue("Teléfono");

        HSSFCell celdaEnc26 = filaEnc7.createCell(8);
        celdaEnc26.setCellValue(listaDatosEncabezado.get(0).getTelefono());

        /*---------------------------------------------------*/
        HSSFRow filaEnc8 = hoja.createRow(8);

        HSSFCell celdaEnc27 = filaEnc8.createCell(0);
        celdaEnc27.setCellValue("Fecha fallecimiento");

        HSSFCell celdaEnc28 = filaEnc8.createCell(1);
        celdaEnc28.setCellValue(listaDatosEncabezado.get(0).getFechafallecimiento());

        HSSFCell celdaEnc29 = filaEnc8.createCell(7);
        celdaEnc29.setCellValue("Régimen de IVA");

        HSSFCell celdaEnc30 = filaEnc8.createCell(8);
        celdaEnc30.setCellValue(lista.get(0).getNombreRegimen());
 /*---------------------------------------------------*/
        HSSFRow filaEnc9 = hoja.createRow(9);

        HSSFCell celdaEnc31 = filaEnc9.createCell(0);
        celdaEnc31.setCellValue("Plan operativo");

        /*HSSFCell celdaEnc32 = filaEnc9.createCell(1);
        celdaEnc32.setCellValue(listaDatosEncabezado.get(0).get);*/
        
        HSSFCell celdaEnc33 = filaEnc9.createCell(7);
        celdaEnc33.setCellValue("Fecha status de IVA");

        HSSFCell celdaEnc34 = filaEnc9.createCell(8);
        celdaEnc34.setCellValue(lista.get(0).getFechaAdicionAfiliacionIVA());
 /*---------------------------------------------------*/
        HSSFRow filaEnc10 = hoja.createRow(10);

        HSSFCell celdaEnc35 = filaEnc10.createCell(0);
        celdaEnc35.setCellValue("Omiso");

        HSSFCell celdaEnc36 = filaEnc10.createCell(1);
        celdaEnc36.setCellValue(listaDatosEncabezado.get(0).getOmiso());

        HSSFCell celdaEnc37 = filaEnc10.createCell(7);
        celdaEnc37.setCellValue("Cambio régimen de IVA");

        /* HSSFCell celdaEnc38 = filaEnc10.createCell(8);
        celdaEnc38.setCellValue(listaDatosEncabezado.get(0).getTelefono());*/
 /*---------------------------------------------------*/
        HSSFRow filaEnc11 = hoja.createRow(11);

        HSSFCell celdaEnc39 = filaEnc11.createCell(0);
        celdaEnc39.setCellValue("Marca establecimiento");

        HSSFCell celdaEnc40 = filaEnc11.createCell(1);
        celdaEnc40.setCellValue(listaDatosEncabezado.get(0).getMarcaEstablecimiento());

        HSSFCell celdaEnc41 = filaEnc11.createCell(7);
        celdaEnc41.setCellValue("NIT contador");

        HSSFCell celdaEnc42 = filaEnc11.createCell(8);
        celdaEnc42.setCellValue(listaDatosEncabezado.get(0).getNitContador());

        /*---------------------------------------------------*/
        HSSFRow filaEnc12 = hoja.createRow(12);

        HSSFCell celdaEnc45 = filaEnc12.createCell(7);
        celdaEnc45.setCellValue("Teléfono contador");

        HSSFCell celdaEnc46 = filaEnc12.createCell(8);
        celdaEnc46.setCellValue(listaDatosEncabezado.get(0).getTelefonoContador());

        if (listDatosReten.isEmpty() || listDatosReten == null) {

            /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
            HSSFRow filaH = hoja.createRow(16);

             HSSFCell celdaH0 = filaH.createCell(0);
            celdaH0.setCellStyle(rowStyle);
            celdaH0.setCellValue("PERIODO DESDE");

            HSSFCell celdaH1 = filaH.createCell(1);
            celdaH1.setCellStyle(rowStyle);
            celdaH1.setCellValue("PERIODO HASTA");

            HSSFCell celdaH2 = filaH.createCell(2);
            celdaH2.setCellStyle(rowStyle);
            celdaH2.setCellValue("FECHA RECAUDO");

            HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("CODIGO FORMULARIO");

            HSSFCell celdaH4 = filaH.createCell(4);
            celdaH4.setCellStyle(rowStyle);
            celdaH4.setCellValue("NÚMERO FORMULARIO");

            HSSFCell celdaH5 = filaH.createCell(5);
            celdaH5.setCellStyle(rowStyle);
            celdaH5.setCellValue("STATUS");

            HSSFCell celdaH6 = filaH.createCell(6);
            celdaH6.setCellStyle(rowStyle);
            celdaH6.setCellValue("VENTAS EXENTAS");

            HSSFCell celdaH7 = filaH.createCell(7);
            celdaH7.setCellStyle(rowStyle);
            celdaH7.setCellValue("VENTAS MEDICAMENTO GENÉRICOS, ALTERNATIVAS Y ANTRIRRETROVIRALES");

            HSSFCell celdaH8 = filaH.createCell(8);
            celdaH8.setCellStyle(rowStyle);
            celdaH8.setCellValue("VENTAS NO AFECTAS REALIZADAS A CONTRIBUYENTES CALIFICADOS CON DEC. NO. 28-89 Y SUS REFORMAS");

            HSSFCell celdaH9 = filaH.createCell(9);
            celdaH9.setCellStyle(rowStyle);
            celdaH9.setCellValue("VENTAS VEHÍCULOS TERRESTRES DEL MODELO DE DOS AÑOS O MAS ANTERIORES AL DEL AÑO EN CURSO");

            HSSFCell celdaH10 = filaH.createCell(10);
            celdaH10.setCellStyle(rowStyle);
            celdaH10.setCellValue("VENTA VENÍCULOS TERRESTRES DEL MODELO DEL AÑO EN CUROS, DEL AÑO SIGUIENTE O ANTERIOR AL DEL AÑO EN CURSO BASE");

            HSSFCell celdaH11 = filaH.createCell(11);
            celdaH11.setCellStyle(rowStyle);
            celdaH11.setCellValue("VENTAS GRAVADAS");

            HSSFCell celdaH12 = filaH.createCell(12);
            celdaH12.setCellStyle(rowStyle);
            celdaH12.setCellValue("SERVICIOS GRAVADAS");

            HSSFCell celdaH13 = filaH.createCell(13);
            celdaH13.setCellStyle(rowStyle);
            celdaH13.setCellValue("SUMA BASE DÉBITOS");

            HSSFCell celdaH14 = filaH.createCell(14);
            celdaH14.setCellStyle(rowStyle);
            celdaH14.setCellValue("RENAMENTE DE RETENCIONES DE IVA RECIBIDAS EN EL PERIODO");

            HSSFCell celdaH15 = filaH.createCell(15);
            celdaH15.setCellStyle(rowStyle);
            celdaH15.setCellValue("CONSTANCIAS DE RETENCIÓN DEL IVA RECIBIDAS EN EL PERIODO A DECLARAR");

            HSSFCell celdaH16 = filaH.createCell(16);
            celdaH16.setCellStyle(rowStyle);
            celdaH16.setCellValue("SALDO DE RETENCIONES PARA EL PERIODO SIGUIENTE");

            HSSFCell celdaH17 = filaH.createCell(17);
            celdaH17.setCellStyle(rowStyle);
            celdaH17.setCellValue("INGRESOS AFECTOS");

            HSSFCell celdaH18 = filaH.createCell(18);
            celdaH18.setCellStyle(rowStyle);
            celdaH18.setCellValue("CANTIDAD DE FACTURAS");

            HSSFCell celdaH19 = filaH.createCell(19);
            celdaH19.setCellStyle(rowStyle);
            celdaH19.setCellValue("CONSTANCIAS DE RETENCIÓN DEL IVA (Recibidas)");

            HSSFCell celdaH20 = filaH.createCell(20);
            celdaH20.setCellStyle(rowStyle);
            celdaH20.setCellValue("AÑO (RETENCIÓN)");

            HSSFCell celdaH21 = filaH.createCell(21);
            celdaH21.setCellStyle(rowStyle);
            celdaH21.setCellValue("MES (RETENCIÓN)");

            HSSFCell celdaH22 = filaH.createCell(22);
            celdaH22.setCellStyle(rowStyle);
            celdaH22.setCellValue("NIT");

            HSSFCell celdaH23 = filaH.createCell(23);
            celdaH23.setCellStyle(rowStyle);
            celdaH23.setCellValue("TIPO");

            HSSFCell celdaH24 = filaH.createCell(24);
            celdaH24.setCellStyle(rowStyle);
            celdaH24.setCellValue("IMPORTE NETO DEL BIEN O SERVICIO");

            HSSFCell celdaH25 = filaH.createCell(25);
            celdaH25.setCellStyle(rowStyle);
            celdaH25.setCellValue("IMPORTE RETENIDO");

            HSSFCell celdaH26 = filaH.createCell(26);
            celdaH26.setCellStyle(rowStyle);
            celdaH26.setCellValue("SUMA DE IMPORTE NETO");

            HSSFCell celdaH27 = filaH.createCell(27);
            celdaH27.setCellStyle(rowStyle);
            celdaH27.setCellValue("CANTIDAD DE CONSTANCIAS");

           
            HSSFCell celdaH28 = filaH.createCell(28);
            celdaH28.setCellStyle(rowStyle);
            celdaH28.setCellValue("CANTIDAD DE RETENEDORES");

            HSSFCell celdaH29 = filaH.createCell(29);
            celdaH29.setCellStyle(rowStyle);
            celdaH29.setCellValue("SUMA IMPORTE RETENIDO");

            HSSFCell celdaH30 = filaH.createCell(30);
            celdaH30.setCellStyle(rowStyle);
            celdaH30.setCellValue("DIFERENCIA BASE RETENIVA");

            HSSFCell celdaH31 = filaH.createCell(31);
            celdaH31.setCellStyle(rowStyle);
            celdaH31.setCellValue("PORENCIAL IVA");

            HSSFCell celdaH32 = filaH.createCell(32);
            celdaH32.setCellStyle(rowStyle);
            celdaH32.setCellValue("DIFERENCIA CONSTANCIAS RETENIVA");

            HSSFCell celdaH33 = filaH.createCell(33);
            celdaH33.setCellStyle(rowStyle);
            celdaH33.setCellValue("POTENCIAL CONSTANCIAS");

            HSSFCell celdaH34 = filaH.createCell(34);
            celdaH34.setCellStyle(rowStyle);
            celdaH34.setCellValue("POTENCIAL TOTAL IVA");
            
            HSSFCell celdaH35 = filaH.createCell(35);
            celdaH35.setCellStyle(rowStyle);
            celdaH35.setCellValue("POTENCIAL POR NIT");

            for (int x = 0; x < listDatosReten.size(); x++) {

                HSSFRow fila = hoja.createRow(x + 17);

                HSSFCell celda0 = fila.createCell(0);
                celda0.setCellStyle(cellStyle);
                celda0.setCellValue(listDatosReten.get(x).getPeriodo_desde());

                HSSFCell celda1 = fila.createCell(1);
                celda1.setCellStyle(cellStyle);
                celda1.setCellValue(listDatosReten.get(x).getPeriodo_hasta());

                HSSFCell celda2 = fila.createCell(2);
                celda2.setCellStyle(cellStyle);
                celda2.setCellValue(listDatosReten.get(x).getFecha_recaudo());

                HSSFCell celda3 = fila.createCell(3);
                celda3.setCellStyle(cellStyle);
                celda3.setCellValue(listDatosReten.get(x).getCodigo_formulario());

                HSSFCell celda4 = fila.createCell(4);
                celda4.setCellStyle(styleMoneda);
                celda4.setCellValue(listDatosReten.get(x).getNumero_formulario());

                HSSFCell celda5 = fila.createCell(5);
                celda5.setCellStyle(cellStyle);
                celda5.setCellValue(listDatosReten.get(x).getStatus());

                HSSFCell celda6 = fila.createCell(6);
                celda6.setCellStyle(styleMoneda);
                celda6.setCellValue(listDatosReten.get(x).getVentas_exentas());

                HSSFCell celda7 = fila.createCell(7);
                celda7.setCellStyle(cellStyle);
                celda7.setCellValue(listDatosReten.get(x).getVentas_medicamentos());

                HSSFCell celda8 = fila.createCell(8);
                celda8.setCellStyle(styleMoneda);
                celda8.setCellValue(listDatosReten.get(x).getVentas_no_afecta());

                HSSFCell celda9 = fila.createCell(9);
                celda9.setCellStyle(styleMoneda);
                celda9.setCellValue(listDatosReten.get(x).getVentas_vehiculos());

                HSSFCell celda10 = fila.createCell(10);
                celda10.setCellStyle(styleMoneda);
                celda10.setCellValue(listDatosReten.get(x).getVentas_vehiculos_act());

                HSSFCell celda11 = fila.createCell(11);
                celda11.setCellStyle(styleMoneda);
                celda11.setCellValue(listDatosReten.get(x).getVentas());

                HSSFCell celda12 = fila.createCell(12);
                celda12.setCellStyle(styleMoneda);
                celda12.setCellValue(listDatosReten.get(x).getServ_prestados());

                HSSFCell celda13 = fila.createCell(13);
                celda13.setCellStyle(styleMoneda);
                celda13.setCellValue(listDatosReten.get(x).getSuma_base_deb());

                HSSFCell celda14 = fila.createCell(14);
                celda14.setCellStyle(styleMoneda);
                celda14.setCellValue(listDatosReten.get(x).getRemanente_reteniva_per_act());
                
                HSSFCell celda15 = fila.createCell(15);
                celda15.setCellStyle(styleMoneda);
                celda15.setCellValue(listDatosReten.get(x).getCantidad_constancias());
                

                HSSFCell celda16 = fila.createCell(16);
                celda16.setCellStyle(styleMoneda);
                celda16.setCellValue(listDatosReten.get(x).getSaldo_reteniva_sig_per());

                HSSFCell celda17 = fila.createCell(17);
                celda17.setCellStyle(styleMoneda);
                celda17.setCellValue(listDatosReten.get(x).getIngresos_afectos());
                
                HSSFCell celda18 = fila.createCell(18);
                celda18.setCellStyle(styleMoneda);
                celda18.setCellValue(listDatosReten.get(x).getCantidad_facturas());
                
                HSSFCell celda19 = fila.createCell(19);
                celda19.setCellStyle(styleMoneda);
                celda19.setCellValue(listDatosReten.get(x).getDiferencia_constancias_reteniva());
                

                HSSFCell celda20 = fila.createCell(20);
                celda20.setCellStyle(styleMoneda);
                celda20.setCellValue(listDatosReten.get(x).getAnio());

                HSSFCell celda21 = fila.createCell(21);
                celda21.setCellStyle(styleMoneda);
                celda21.setCellValue(listDatosReten.get(x).getMes());

                HSSFCell celda22 = fila.createCell(22);
                celda22.setCellStyle(styleMoneda);
                celda22.setCellValue(listDatosReten.get(x).getNit());

                HSSFCell celda23 = fila.createCell(23);
                celda23.setCellStyle(styleMoneda);
                celda23.setCellValue(listDatosReten.get(x).getTipo_persona());

                HSSFCell celda24 = fila.createCell(24);
                celda24.setCellStyle(styleMoneda);
                celda24.setCellValue(listDatosReten.get(x).getSuma_importe_neto());

                   /*HSSFCell celda25 = fila.createCell(25);
                celda25.setCellStyle(styleMoneda);
                celda25.setCellValue(listDatosReten.get(x).getCantidad_constancias())*/
               

                HSSFCell celda26 = fila.createCell(26);
                celda26.setCellStyle(styleMoneda);
                celda26.setCellValue(listDatosReten.get(x).getSuma_valor_retencion());
                
           
                
                 HSSFCell celda27 = fila.createCell(27);
                celda27.setCellStyle(styleMoneda);
                celda27.setCellValue(listDatosReten.get(x).getCantidad_constancias());


                HSSFCell celda28 = fila.createCell(28);
                celda28.setCellStyle(styleMoneda);
                celda28.setCellValue(listDatosReten.get(x).getCantidad_retenedores());

                HSSFCell celda29 = fila.createCell(29);
                celda29.setCellStyle(styleMoneda);
                celda29.setCellValue(listDatosReten.get(x).getSuma_importe_neto());

                HSSFCell celda30 = fila.createCell(30);
                celda30.setCellStyle(styleMoneda);
                celda30.setCellValue(listDatosReten.get(x).getDiferencia_base_reteniva());

                HSSFCell celda31 = fila.createCell(31);
                celda31.setCellStyle(styleMoneda);
                celda31.setCellValue(listDatosReten.get(x).getPotencial_iva());

                HSSFCell celda32 = fila.createCell(32);
                celda32.setCellStyle(styleMoneda);
                celda32.setCellValue(listDatosReten.get(x).getDiferencia_constancias_reteniva());

                HSSFCell celda33 = fila.createCell(33);
                celda33.setCellStyle(styleMoneda);
                celda33.setCellValue(listDatosReten.get(x).getPotencial_constancias());

                HSSFCell celda34 = fila.createCell(34);
                celda34.setCellStyle(styleMoneda);
                celda34.setCellValue(listDatosReten.get(x).getPotencial_total_iva());
                
             
            }
        }

        if (listDatosGeneral.isEmpty() || listDatosGeneral == null) {

            /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
            HSSFRow filaH = hoja.createRow(16);

             HSSFCell celdaH0 = filaH.createCell(0);
            celdaH0.setCellStyle(rowStyle);
            celdaH0.setCellValue("PERIODO DESDE");

            HSSFCell celdaH1 = filaH.createCell(1);
            celdaH1.setCellStyle(rowStyle);
            celdaH1.setCellValue("PERIODO HASTA");

            HSSFCell celdaH2 = filaH.createCell(2);
            celdaH2.setCellStyle(rowStyle);
            celdaH2.setCellValue("FECHA RECAUDO");

            HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("CODIGO FORMULARIO");

            HSSFCell celdaH4 = filaH.createCell(4);
            celdaH4.setCellStyle(rowStyle);
            celdaH4.setCellValue("NÚMERO FORMULARIO");

            HSSFCell celdaH5 = filaH.createCell(5);
            celdaH5.setCellStyle(rowStyle);
            celdaH5.setCellValue("STATUS");

            HSSFCell celdaH6 = filaH.createCell(6);
            celdaH6.setCellStyle(rowStyle);
            celdaH6.setCellValue("VENTAS EXENTAS");

            HSSFCell celdaH7 = filaH.createCell(7);
            celdaH7.setCellStyle(rowStyle);
            celdaH7.setCellValue("VENTAS MEDICAMENTO GENÉRICOS, ALTERNATIVAS Y ANTRIRRETROVIRALES");

            HSSFCell celdaH8 = filaH.createCell(8);
            celdaH8.setCellStyle(rowStyle);
            celdaH8.setCellValue("VENTAS NO AFECTAS REALIZADAS A CONTRIBUYENTES CALIFICADOS CON DEC. NO. 28-89 Y SUS REFORMAS");

            HSSFCell celdaH9 = filaH.createCell(9);
            celdaH9.setCellStyle(rowStyle);
            celdaH9.setCellValue("VENTAS VEHÍCULOS TERRESTRES DEL MODELO DE DOS AÑOS O MAS ANTERIORES AL DEL AÑO EN CURSO");

            HSSFCell celdaH10 = filaH.createCell(10);
            celdaH10.setCellStyle(rowStyle);
            celdaH10.setCellValue("VENTA VENÍCULOS TERRESTRES DEL MODELO DEL AÑO EN CUROS, DEL AÑO SIGUIENTE O ANTERIOR AL DEL AÑO EN CURSO BASE");

            HSSFCell celdaH11 = filaH.createCell(11);
            celdaH11.setCellStyle(rowStyle);
            celdaH11.setCellValue("VENTAS GRAVADAS");

            HSSFCell celdaH12 = filaH.createCell(12);
            celdaH12.setCellStyle(rowStyle);
            celdaH12.setCellValue("SERVICIOS GRAVADAS");

            HSSFCell celdaH13 = filaH.createCell(13);
            celdaH13.setCellStyle(rowStyle);
            celdaH13.setCellValue("SUMA BASE DÉBITOS");

            HSSFCell celdaH14 = filaH.createCell(14);
            celdaH14.setCellStyle(rowStyle);
            celdaH14.setCellValue("RENAMENTE DE RETENCIONES DE IVA RECIBIDAS EN EL PERIODO");

            HSSFCell celdaH15 = filaH.createCell(15);
            celdaH15.setCellStyle(rowStyle);
            celdaH15.setCellValue("CONSTANCIAS DE RETENCIÓN DEL IVA RECIBIDAS EN EL PERIODO A DECLARAR");

            HSSFCell celdaH16 = filaH.createCell(16);
            celdaH16.setCellStyle(rowStyle);
            celdaH16.setCellValue("SALDO DE RETENCIONES PARA EL PERIODO SIGUIENTE");

            HSSFCell celdaH17 = filaH.createCell(17);
            celdaH17.setCellStyle(rowStyle);
            celdaH17.setCellValue("INGRESOS AFECTOS");

            HSSFCell celdaH18 = filaH.createCell(18);
            celdaH18.setCellStyle(rowStyle);
            celdaH18.setCellValue("CANTIDAD DE FACTURAS");

            HSSFCell celdaH19 = filaH.createCell(19);
            celdaH19.setCellStyle(rowStyle);
            celdaH19.setCellValue("CONSTANCIAS DE RETENCIÓN DEL IVA (Recibidas)");

            HSSFCell celdaH20 = filaH.createCell(20);
            celdaH20.setCellStyle(rowStyle);
            celdaH20.setCellValue("AÑO (RETENCIÓN)");

            HSSFCell celdaH21 = filaH.createCell(21);
            celdaH21.setCellStyle(rowStyle);
            celdaH21.setCellValue("MES (RETENCIÓN)");

            HSSFCell celdaH22 = filaH.createCell(22);
            celdaH22.setCellStyle(rowStyle);
            celdaH22.setCellValue("NIT");

            HSSFCell celdaH23 = filaH.createCell(23);
            celdaH23.setCellStyle(rowStyle);
            celdaH23.setCellValue("TIPO");

            HSSFCell celdaH24 = filaH.createCell(24);
            celdaH24.setCellStyle(rowStyle);
            celdaH24.setCellValue("IMPORTE NETO DEL BIEN O SERVICIO");

            HSSFCell celdaH25 = filaH.createCell(25);
            celdaH25.setCellStyle(rowStyle);
            celdaH25.setCellValue("IMPORTE RETENIDO");

            HSSFCell celdaH26 = filaH.createCell(26);
            celdaH26.setCellStyle(rowStyle);
            celdaH26.setCellValue("SUMA DE IMPORTE NETO");

            HSSFCell celdaH27 = filaH.createCell(27);
            celdaH27.setCellStyle(rowStyle);
            celdaH27.setCellValue("CANTIDAD DE CONSTANCIAS");

           
            HSSFCell celdaH28 = filaH.createCell(28);
            celdaH28.setCellStyle(rowStyle);
            celdaH28.setCellValue("CANTIDAD DE RETENEDORES");

            HSSFCell celdaH29 = filaH.createCell(29);
            celdaH29.setCellStyle(rowStyle);
            celdaH29.setCellValue("SUMA IMPORTE RETENIDO");

            HSSFCell celdaH30 = filaH.createCell(30);
            celdaH30.setCellStyle(rowStyle);
            celdaH30.setCellValue("DIFERENCIA BASE RETENIVA");

            HSSFCell celdaH31 = filaH.createCell(31);
            celdaH31.setCellStyle(rowStyle);
            celdaH31.setCellValue("PORENCIAL IVA");

            HSSFCell celdaH32 = filaH.createCell(32);
            celdaH32.setCellStyle(rowStyle);
            celdaH32.setCellValue("DIFERENCIA CONSTANCIAS RETENIVA");

            HSSFCell celdaH33 = filaH.createCell(33);
            celdaH33.setCellStyle(rowStyle);
            celdaH33.setCellValue("POTENCIAL CONSTANCIAS");

            HSSFCell celdaH34 = filaH.createCell(34);
            celdaH34.setCellStyle(rowStyle);
            celdaH34.setCellValue("POTENCIAL TOTAL IVA");
            
            HSSFCell celdaH35 = filaH.createCell(35);
            celdaH35.setCellStyle(rowStyle);
            celdaH35.setCellValue("POTENCIAL POR NIT");

            for (int x = 0; x < listDatosReten.size(); x++) {

                HSSFRow fila = hoja.createRow(x + 17);

                HSSFCell celda0 = fila.createCell(0);
                celda0.setCellStyle(cellStyle);
                celda0.setCellValue(listDatosReten.get(x).getPeriodo_desde());

                HSSFCell celda1 = fila.createCell(1);
                celda1.setCellStyle(cellStyle);
                celda1.setCellValue(listDatosReten.get(x).getPeriodo_hasta());

                HSSFCell celda2 = fila.createCell(2);
                celda2.setCellStyle(cellStyle);
                celda2.setCellValue(listDatosReten.get(x).getFecha_recaudo());

                HSSFCell celda3 = fila.createCell(3);
                celda3.setCellStyle(cellStyle);
                celda3.setCellValue(listDatosReten.get(x).getCodigo_formulario());

                HSSFCell celda4 = fila.createCell(4);
                celda4.setCellStyle(styleMoneda);
                celda4.setCellValue(listDatosReten.get(x).getNumero_formulario());

                HSSFCell celda5 = fila.createCell(5);
                celda5.setCellStyle(cellStyle);
                celda5.setCellValue(listDatosReten.get(x).getStatus());

                HSSFCell celda6 = fila.createCell(6);
                celda6.setCellStyle(styleMoneda);
                celda6.setCellValue(listDatosReten.get(x).getVentas_exentas());

                HSSFCell celda7 = fila.createCell(7);
                celda7.setCellStyle(cellStyle);
                celda7.setCellValue(listDatosReten.get(x).getVentas_medicamentos());

                HSSFCell celda8 = fila.createCell(8);
                celda8.setCellStyle(styleMoneda);
                celda8.setCellValue(listDatosReten.get(x).getVentas_no_afecta());

                HSSFCell celda9 = fila.createCell(9);
                celda9.setCellStyle(styleMoneda);
                celda9.setCellValue(listDatosReten.get(x).getVentas_vehiculos());

                HSSFCell celda10 = fila.createCell(10);
                celda10.setCellStyle(styleMoneda);
                celda10.setCellValue(listDatosReten.get(x).getVentas_vehiculos_act());

                HSSFCell celda11 = fila.createCell(11);
                celda11.setCellStyle(styleMoneda);
                celda11.setCellValue(listDatosReten.get(x).getVentas());

                HSSFCell celda12 = fila.createCell(12);
                celda12.setCellStyle(styleMoneda);
                celda12.setCellValue(listDatosReten.get(x).getServ_prestados());

                HSSFCell celda13 = fila.createCell(13);
                celda13.setCellStyle(styleMoneda);
                celda13.setCellValue(listDatosReten.get(x).getSuma_base_deb());

                HSSFCell celda14 = fila.createCell(14);
                celda14.setCellStyle(styleMoneda);
                celda14.setCellValue(listDatosReten.get(x).getRemanente_reteniva_per_act());
                
                HSSFCell celda15 = fila.createCell(15);
                celda15.setCellStyle(styleMoneda);
                celda15.setCellValue(listDatosReten.get(x).getCantidad_constancias());
                

                HSSFCell celda16 = fila.createCell(16);
                celda16.setCellStyle(styleMoneda);
                celda16.setCellValue(listDatosReten.get(x).getSaldo_reteniva_sig_per());

                HSSFCell celda17 = fila.createCell(17);
                celda17.setCellStyle(styleMoneda);
                celda17.setCellValue(listDatosReten.get(x).getIngresos_afectos());
                
                HSSFCell celda18 = fila.createCell(18);
                celda18.setCellStyle(styleMoneda);
                celda18.setCellValue(listDatosReten.get(x).getCantidad_facturas());
                
                HSSFCell celda19 = fila.createCell(19);
                celda19.setCellStyle(styleMoneda);
                celda19.setCellValue(listDatosReten.get(x).getDiferencia_constancias_reteniva());
                

                HSSFCell celda20 = fila.createCell(20);
                celda20.setCellStyle(styleMoneda);
                celda20.setCellValue(listDatosReten.get(x).getAnio());

                HSSFCell celda21 = fila.createCell(21);
                celda21.setCellStyle(styleMoneda);
                celda21.setCellValue(listDatosReten.get(x).getMes());

                HSSFCell celda22 = fila.createCell(22);
                celda22.setCellStyle(styleMoneda);
                celda22.setCellValue(listDatosReten.get(x).getNit());

                HSSFCell celda23 = fila.createCell(23);
                celda23.setCellStyle(styleMoneda);
                celda23.setCellValue(listDatosReten.get(x).getTipo_persona());

                HSSFCell celda24 = fila.createCell(24);
                celda24.setCellStyle(styleMoneda);
                celda24.setCellValue(listDatosReten.get(x).getSuma_importe_neto());

                   /*HSSFCell celda25 = fila.createCell(25);
                celda25.setCellStyle(styleMoneda);
                celda25.setCellValue(listDatosReten.get(x).getCantidad_constancias())*/
               

                HSSFCell celda26 = fila.createCell(26);
                celda26.setCellStyle(styleMoneda);
                celda26.setCellValue(listDatosReten.get(x).getSuma_valor_retencion());
                
           
                
                 HSSFCell celda27 = fila.createCell(27);
                celda27.setCellStyle(styleMoneda);
                celda27.setCellValue(listDatosReten.get(x).getCantidad_constancias());


                HSSFCell celda28 = fila.createCell(28);
                celda28.setCellStyle(styleMoneda);
                celda28.setCellValue(listDatosReten.get(x).getCantidad_retenedores());

                HSSFCell celda29 = fila.createCell(29);
                celda29.setCellStyle(styleMoneda);
                celda29.setCellValue(listDatosReten.get(x).getSuma_importe_neto());

                HSSFCell celda30 = fila.createCell(30);
                celda30.setCellStyle(styleMoneda);
                celda30.setCellValue(listDatosReten.get(x).getDiferencia_base_reteniva());

                HSSFCell celda31 = fila.createCell(31);
                celda31.setCellStyle(styleMoneda);
                celda31.setCellValue(listDatosReten.get(x).getPotencial_iva());

                HSSFCell celda32 = fila.createCell(32);
                celda32.setCellStyle(styleMoneda);
                celda32.setCellValue(listDatosReten.get(x).getDiferencia_constancias_reteniva());

                HSSFCell celda33 = fila.createCell(33);
                celda33.setCellStyle(styleMoneda);
                celda33.setCellValue(listDatosReten.get(x).getPotencial_constancias());

                HSSFCell celda34 = fila.createCell(34);
                celda34.setCellStyle(styleMoneda);
                celda34.setCellValue(listDatosReten.get(x).getPotencial_total_iva());
                
                HSSFCell celda35 = fila.createCell(35);
                celda35.setCellStyle(styleMoneda);
                celda35.setCellValue(listDatosReten.get(x).getSuma_potencial_iva());
                
            }
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        externalContext.setResponseContentType("application/vnd.ms-excel; charset=utf-8");

        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"Ingresos_RetenIVA_Vs_Ingresos_declarados_IVA_General.xls\"");

        libro.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();
    }

}
