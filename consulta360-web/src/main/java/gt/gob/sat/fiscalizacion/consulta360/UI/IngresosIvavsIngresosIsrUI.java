/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.ContribuyenteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ImpuestoIsrDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ImpuestoIvaGeneralDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IngresosFelvsDeclaradoIvaPequeDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IngresosIvavsIngresosIsrDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.PequenoContribuyenteEncabezadoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.PequenoContribuyenteEncabezadoGenericoDto;
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
public class IngresosIvavsIngresosIsrUI extends AbstractUI implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(IngresosIvavsIngresosIsrUI.class);
    private String pnit = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private ArrayList<IngresosIvavsIngresosIsrDto> listDatosGeneral = new ArrayList();
    private ArrayList<IngresosIvavsIngresosIsrDto> listaIngresosIsr = new ArrayList();
    private List<PequenoContribuyenteEncabezadoDto> listaDatosEncabezado;
    private List<PequenoContribuyenteEncabezadoGenericoDto> listaDatosEncabezadoGenerico = new ArrayList();
    private PequenoContribuyenteEncabezadoGenericoDto dtoGenericoEncabezado = new PequenoContribuyenteEncabezadoGenericoDto();
    
    private ImpuestoIsrDto listaRegimenIsr;
    private ImpuestoIvaGeneralDto lista;
    
    @PostConstruct
    public void inicializar() {
        
       lista = this.generalSrvImpl.DetalleIvaGeneral(pnit);
       listaRegimenIsr = this.generalSrvImpl.DetalleISR(pnit);
      
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
                + "ISNULL (FECHA_RECAUDO_IVA, '0')       AS FECHA_RECAUDO_IVA,"
                + "ISNULL (CODIGO_FORMULARIO_IVA, '0')   AS CODIGO_FORMULARIO_IVA,"
                + "ISNULL (NUMERO_FORMULARIO_IVA, '0')   AS NUMERO_FORMULARIO_IVA,"
                + "ISNULL (STATUS_DEL_FORMULARIO_IVA, '0')  AS STATUS_DEL_FORMULARIO_IVA,"
                + "format(cast(Round(VENTAS_EXENTAS,2) as decimal(20,2)),'C','Es-Gt')                                                                                                          AS  VENTAS_EXENTAS, "
                + "format(cast(Round(VENTAS_MEDICAMENTOS_GENERICOS_ALTERNATIVOS_Y_ANTIRRETROVIRALES,2) as decimal(20,2)),'C','Es-Gt')                                                          AS  VENTAS_MEDICAMENTOS_GENERICOS_ALTERNATIVOS_Y_ANTIRRETROVIRALES, "
                + "format(cast(Round(VENTAS_NO_AFECTADAS_REALIZADAS_A_CONTRIBUYENTES_CALIFICADOS_CON_EL_DECRETO_29_89_Y_SUS_REFORMAS,2) as decimal(20,2)),'C','Es-Gt')                         AS  VENTAS_NO_AFECTADAS_REALIZADAS_A_CONTRIBUYENTES_CALIFICADOS_CON_EL_DECRETO_29_89_Y_SUS_REFORMAS, "
                + "format(cast(Round(VENTAS_GRAVADAS,2) as decimal(20,2)),'C','Es-Gt')                                                                                                         AS  VENTAS_GRAVADAS, "
                + "format(cast(Round(SERVICIOS_GRAVADOS,2) as decimal(20,2)),'C','Es-Gt')                                                                                                      AS  SERVICIOS_GRAVADOS, "
                + "format(cast(Round(EXPORTACIONES_A_CENTRO_AMERICA,2) as decimal(20,2)),'C','Es-Gt')                                                                                          AS  EXPORTACIONES_A_CENTRO_AMERICA, "
                + "format(cast(Round(EXPORTACIONES_AL_RESTO_DEL_MUNDO,2) as decimal(20,2)),'C','Es-Gt')                                                                                        AS  EXPORTACIONES_AL_RESTO_DEL_MUNDO, "
                + "format(cast(Round(TRANSFERENCIAS_CON_FYDUCA,2) as decimal(20,2)),'C','Es-Gt')                                                                                               AS  TRANSFERENCIAS_CON_FYDUCA, "
                + "format(cast(Round(VENTAS_DE_VEHÍCULOS_TERRESTRES_DEL_MODELO_DE_DOS_AÑOS_O_MAS_ANTERIORES_AL_DEL_AÑO_EN_CURSO_CASILLA_10,2) as decimal(20,2)),'C','Es-Gt')                   AS  VENTAS_DE_VEHÍCULOS_TERRESTRES_DEL_MODELO_DE_DOS_AÑOS_O_MAS_ANTERIORES_AL_DEL_AÑO_EN_CURSO_CASILLA_10, "
                + "format(cast(Round(VENTAS_DE_VEHICULOS_TERRESTRES_DEL_MODELO_DEL_AÑO_EN_CURSO_DEL_AÑO_SIGUIENTE_O_ANTERIOR_AL_DEL_AÑO_EN_CURSO,2) as decimal(20,2)),'C','Es-Gt')             AS  VENTAS_DE_VEHICULOS_TERRESTRES_DEL_MODELO_DEL_AÑO_EN_CURSO_DEL_AÑO_SIGUIENTE_O_ANTERIOR_AL_DEL_AÑO_EN_CURSO, "
                + "format(cast(Round(VENTAS_DE_VEHÍCULOS_TERRESTRES_DEL_MODELO_DE_DOS_AÑOS_O_MÁS_ANTERIORES_AL_DEL_AÑO_EN_CURSO,2) as decimal(20,2)),'C','Es-Gt')                              AS  VENTAS_DE_VEHÍCULOS_TERRESTRES_DEL_MODELO_DE_DOS_AÑOS_O_MÁS_ANTERIORES_AL_DEL_AÑO_EN_CURSO, "
                + "format(cast(Round(VENTAS_DE_VEHÍCULOS_TERRESTRES_DEL_MODELO_DEL_AÑO_EN_CURSO_DEL_AÑO_SIGUIENTE_O_ANTERIOR_AL_DEL_AÑO_EN_CURSO_CASILLA_23,2) as decimal(20,2)),'C','Es-Gt')  AS  VENTAS_DE_VEHÍCULOS_TERRESTRES_DEL_MODELO_DEL_AÑO_EN_CURSO_DEL_AÑO_SIGUIENTE_O_ANTERIOR_AL_DEL_AÑO_EN_CURSO_CASILLA_23, "
                + "format(cast(Round(SUMA_BASE_DEBITOS,2) as decimal(20,2)),'C','Es-Gt')                                                                                                       AS  SUMA_BASE_DEBITOS, "
                + "format(cast(Round(SUMA_BASE_DEBITOS_EXPO,2) as decimal(20,2)),'C','Es-Gt')                                                                                                  AS  SUMA_BASE_DEBITOS_EXPO, "
                + "ISNULL(FECHA_RECAUDO_ISR,  '0')       AS FECHA_RECAUDO_ISR,"
                + "ISNULL(CODIGO_FORMULARIO_ISR,  '0')   AS CODIGO_FORMULARIO_ISR, "
                + "ISNULL(NUMERO_FORMULARIO_ISR,  '0')   AS NUMERO_FORMULARIO_ISR, "
                + "ISNULL(STATUS_DEL_FORMULARIO_ISR, '0')   AS STATUS_DEL_FORMULARIO_ISR, "
                + "format(cast(Round(RENTA_DE_ACTIVIDADES_LUCRATIVAS,2) as decimal(20,2)),'C','Es-Gt')                                                                                         AS  RENTA_DE_ACTIVIDADES_LUCRATIVAS, "
                + "format(cast(Round(MONTO_TOTAL_DE_RENTAS_EXENTAS,2) as decimal(20,2)),'C','Es-Gt')                                                                                           AS  MONTO_TOTAL_DE_RENTAS_EXENTAS, "
                + "format(cast(Round(RENTAIMPONIBLE,2) as decimal(20,2)),'C','Es-Gt')                                                                                                          AS  RENTAIMPONIBLE, "
                + "format(cast(Round(TOTAL_IVA,2) as decimal(20,2)),'C','Es-Gt')                                                                                                               AS  TOTAL_IVA, "
                + "format(cast(Round(TOTAL_ISR,2) as decimal(20,2)),'C','Es-Gt')                                                                                                               AS  TOTAL_ISR, "
                + "format(cast(Round(DIFERENCIA_ISR_IVA,2) as decimal(20,2)),'C','Es-Gt')                                                                                                      AS  DIFERENCIA_ISR_IVA, "
                + "format(cast(Round(RECAUDO_POTENCIAL_ISR,2) as decimal(20,2)),'C','Es-Gt')                                                                                                   AS  RECAUDO_POTENCIAL_ISR, "
                + "format(cast(Round(DIFERENCIA_IVA_ISR,2) as decimal(20,2)),'C','Es-Gt')                                                                                                      AS  DIFERENCIA_IVA_ISR, "
                + "format(cast(Round(RECAUDO_POTENCIAL_IVA,2) as decimal(20,2)),'C','Es-Gt')                                                                                                   AS  RECAUDO_POTENCIAL_IVA, "
                + "format(cast(Round(TOTAL_VENTAS_EXENTAS_IVA,2) as decimal(20,2)),'C','Es-Gt')                                                                                                AS  TOTAL_VENTAS_EXENTAS_IVA, "
                + "format(cast(Round(POTENCIAL_RECAUDO_IVA_E_ISR,2) as decimal(20,2)),'C','Es-Gt')                                                                                             AS  POTENCIAL_RECAUDO_IVA_E_ISR, "
                + "SEGMENTACION "
                + "FROM sat_cruces.o_iva_vs_isr_regimen_opcional_simplificado_ge "
                /* + "WHERE NIT_DEl_CONTRIBUYENTE = '" + pnit + "'"*/
                + "WHERE NIT_DEL_CONTRIBUYENTE = '" + pnit + "' "
                + "AND  substring(PERIODO_DESDE,7,4) >= '" + Integer.toString(fecha_ini) + "'"
                + "AND  substring(PERIODO_HASTA,7,4) <= '" + Integer.toString(fecha_fin) + "'";
        
        Statement statement;
        ResultSet rs;
        
        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
//            System.out.print(SQL);
            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                IngresosIvavsIngresosIsrDto datos = new IngresosIvavsIngresosIsrDto();
                datos.setPeriodoDesde(rs.getString("PERIODO_DESDE"));
                datos.setPeriodoHasta(rs.getString("PERIODO_HASTA"));
                datos.setFechaRecaudoIva(rs.getString("FECHA_RECAUDO_IVA"));
                datos.setCodigoFormularioIva(rs.getString("CODIGO_FORMULARIO_IVA"));
                datos.setNumeroFormularioIva(rs.getString("NUMERO_FORMULARIO_IVA"));
                datos.setStatusDelFormularioIva(rs.getString("STATUS_DEL_FORMULARIO_IVA"));
                datos.setVentasExentas(rs.getString("VENTAS_EXENTAS"));
                datos.setVentasMedicamentosGenericosAlternantivosYAntirretrovirales(rs.getString("VENTAS_MEDICAMENTOS_GENERICOS_ALTERNATIVOS_Y_ANTIRRETROVIRALES"));
                datos.setVentasNoAfectadasRealizadasAContribuyentesCalificadosConElDecreto29_28_y_SusReformas(rs.getString("VENTAS_NO_AFECTADAS_REALIZADAS_A_CONTRIBUYENTES_CALIFICADOS_CON_EL_DECRETO_29_89_Y_SUS_REFORMAS"));
                datos.setVentasGravadas(rs.getString("VENTAS_GRAVADAS"));
                datos.setServiciosGravados(rs.getString("SERVICIOS_GRAVADOS"));
                datos.setExportacionesACentroAmerica(rs.getString("EXPORTACIONES_A_CENTRO_AMERICA"));
                datos.setExportacionesAlRestoDelMundo(rs.getString("EXPORTACIONES_AL_RESTO_DEL_MUNDO"));
                datos.setTransferenciasConFyduca(rs.getString("TRANSFERENCIAS_CON_FYDUCA"));
                datos.setVentasdeVehículosTerrestresEnCursoCasilla10(rs.getString("VENTAS_DE_VEHÍCULOS_TERRESTRES_DEL_MODELO_DE_DOS_AÑOS_O_MAS_ANTERIORES_AL_DEL_AÑO_EN_CURSO_CASILLA_10"));
                datos.setVentasDeVehículosTerrestresDelAñoEnCursoDelAñoSiguienteAlDelAñoEnCurso(rs.getString("VENTAS_DE_VEHICULOS_TERRESTRES_DEL_MODELO_DEL_AÑO_EN_CURSO_DEL_AÑO_SIGUIENTE_O_ANTERIOR_AL_DEL_AÑO_EN_CURSO"));
                datos.setVentasDeVehiculosTerrestreSiguienteoAnteriorAlDelAñoEnCurso(rs.getString("VENTAS_DE_VEHÍCULOS_TERRESTRES_DEL_MODELO_DE_DOS_AÑOS_O_MÁS_ANTERIORES_AL_DEL_AÑO_EN_CURSO"));
                datos.setVentasDeVehículosTerrestresAñoSiguienteAñoAnteriorAñoCursoCas23(rs.getString("VENTAS_DE_VEHÍCULOS_TERRESTRES_DEL_MODELO_DEL_AÑO_EN_CURSO_DEL_AÑO_SIGUIENTE_O_ANTERIOR_AL_DEL_AÑO_EN_CURSO_CASILLA_23"));
                datos.setSumaBaseDebitos(rs.getString("SUMA_BASE_DEBITOS"));
                datos.setSumaBaseDebitosExpo(rs.getString("SUMA_BASE_DEBITOS_EXPO"));
                datos.setFechaRecaudoIsr(rs.getString("FECHA_RECAUDO_ISR"));
                datos.setCodigoFormularioIsr(rs.getString("CODIGO_FORMULARIO_ISR"));
                datos.setNumeroFormularioIsr(rs.getString("NUMERO_FORMULARIO_ISR"));
                datos.setStatusDelFormularioIsr(rs.getString("STATUS_DEL_FORMULARIO_ISR"));
                datos.setRentaDeActividadesLucrativas(rs.getString("RENTA_DE_ACTIVIDADES_LUCRATIVAS"));
                datos.setMontoTotalDeRentasExentas(rs.getString("MONTO_TOTAL_DE_RENTAS_EXENTAS"));
                datos.setRentaImponible(rs.getString("RENTAIMPONIBLE"));
                datos.setTotalIva(rs.getString("TOTAL_IVA"));
                datos.setTotalIsr(rs.getString("TOTAL_ISR"));
                datos.setDiferenciaIsrIva(rs.getString("DIFERENCIA_ISR_IVA"));
                datos.setRecaudoPotencialIsr(rs.getString("RECAUDO_POTENCIAL_ISR"));
                datos.setDiferenciaIvaIsr(rs.getString("DIFERENCIA_IVA_ISR"));
                datos.setRecaudoPotencialIva(rs.getString("RECAUDO_POTENCIAL_IVA"));
                datos.setTotalVentasExentasIva(rs.getString("TOTAL_VENTAS_EXENTAS_IVA"));
                datos.setPotencialRecaudoIvaEIsr(rs.getString("POTENCIAL_RECAUDO_IVA_E_ISR"));
                datos.setSegmentacion(rs.getString("SEGMENTACION"));
                listaIngresosIsr.add(datos);
                
            }
            conn.close();
            statement.close();
            
        } catch (Exception ex) {
            listaIngresosIsr.clear();
            errorMsg("Tabla no encontrada");
            /*  errorMsg(ex.getMessage());*/
            LOG.error(ex.getMessage());
        }
        
        if (listaIngresosIsr.isEmpty() || listaIngresosIsr == null) {
            warnMsg("No existen registros para el NIT consultado");
            
        } else {
            
            listaDatosEncabezado = this.generalSrvImpl.obtenerEncabezadoPeqContribuyente(pnit);
            
            this.listaDatosEncabezado.forEach(data -> {
                this.dtoGenericoEncabezado.setNit(data.getNit());
                this.dtoGenericoEncabezado.setNombreOrdenado(data.getNombreOrdenado());
                this.dtoGenericoEncabezado.setGerencia(data.getGerencia());
                this.dtoGenericoEncabezado.setClasificacion(data.getClasificacion());
                this.dtoGenericoEncabezado.setRegion(data.getRegion());
                this.dtoGenericoEncabezado.setActividadEconomica(data.getActividadEconomica());
                this.dtoGenericoEncabezado.setPersoneria(data.getPersoneria());
                this.dtoGenericoEncabezado.setTipoPersona(data.getTipoPersona());
                this.dtoGenericoEncabezado.setMarcaNoLocalizado(data.getMarcaNoLocalizado());
                this.dtoGenericoEncabezado.setNombreColor(data.getNombreColor());
                this.dtoGenericoEncabezado.setDireccion(data.getDireccion());
                this.dtoGenericoEncabezado.setStatus(data.getStatus());
                
            });
            
            this.dtoGenericoEncabezado.setNombreRegimenIVA(lista.getNombreRegimen());
            this.dtoGenericoEncabezado.setFechaAdicionAfiliacionIVA(lista.getFechaAdicionAfiliacionIVA());
            this.dtoGenericoEncabezado.setFechaAdicionAfiliacionISR(listaRegimenIsr.getFechaAdicionAfiliacionISR());
            this.dtoGenericoEncabezado.setNombreRegimenISR(listaRegimenIsr.getNombreRegimenISR());
            
            this.listaDatosEncabezadoGenerico.add(dtoGenericoEncabezado);
            
        }
    }
    
    public Connection getConnection() throws Exception {
        InitialContext inc = new InitialContext();
        DataSource ds = (DataSource) inc.lookup("java:comp/env/jdbc/consulta360azure");
        return ds.getConnection();
    }
    
    public ArrayList<IngresosIvavsIngresosIsrDto> getListDatosGeneral() {
        return listDatosGeneral;
    }
    
    public void setListDatosGeneral(ArrayList<IngresosIvavsIngresosIsrDto> listDatosGeneral) {
        this.listDatosGeneral = listDatosGeneral;
    }
    
    public ArrayList<IngresosIvavsIngresosIsrDto> getListaIngresosIsr() {
        return listaIngresosIsr;
    }
    
    public void setListaIngresosIsr(ArrayList<IngresosIvavsIngresosIsrDto> listaIngresosIsr) {
        this.listaIngresosIsr = listaIngresosIsr;
    }
    
    public List<PequenoContribuyenteEncabezadoDto> getListaDatosEncabezado() {
        return listaDatosEncabezado;
    }
    
    public void setListaDatosEncabezado(List<PequenoContribuyenteEncabezadoDto> listaDatosEncabezado) {
        this.listaDatosEncabezado = listaDatosEncabezado;
    }
    
    public ImpuestoIsrDto getListaRegimenIsr() {
        return listaRegimenIsr;
    }
    
    public void setListaRegimenIsr(ImpuestoIsrDto listaRegimenIsr) {
        this.listaRegimenIsr = listaRegimenIsr;
    }
    
    public ImpuestoIvaGeneralDto getLista() {
        return lista;
    }
    
    public void setLista(ImpuestoIvaGeneralDto lista) {
        this.lista = lista;
    }

    public List<PequenoContribuyenteEncabezadoGenericoDto> getListaDatosEncabezadoGenerico() {
        return listaDatosEncabezadoGenerico;
    }

    public void setListaDatosEncabezadoGenerico(List<PequenoContribuyenteEncabezadoGenericoDto> listaDatosEncabezadoGenerico) {
        this.listaDatosEncabezadoGenerico = listaDatosEncabezadoGenerico;
    }

    public PequenoContribuyenteEncabezadoGenericoDto getDtoGenericoEncabezado() {
        return dtoGenericoEncabezado;
    }

    public void setDtoGenericoEncabezado(PequenoContribuyenteEncabezadoGenericoDto dtoGenericoEncabezado) {
        this.dtoGenericoEncabezado = dtoGenericoEncabezado;
    }
    
    
    
    public void exportare() throws IOException {
        ContribuyenteDto contribuyente = generalSrvImpl.obtenerInfoContribuyenteByNit(pnit);
        
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
        celdaEnc2.setCellValue("Ingresos reportados en IVA e ISR régimen opcional simplificado");
        /*---------------------------------------------------*/
        
        HSSFRow filaEnc2 = hoja.createRow(2);
        
        HSSFCell celdaEnc3 = filaEnc2.createCell(0);
        celdaEnc3.setCellValue("NIT del contribuyente");
        
        HSSFCell celdaEnc4 = filaEnc2.createCell(1);
        celdaEnc4.setCellValue(listaDatosEncabezado.get(0).getNit());
        
        HSSFCell celdaEnc5 = filaEnc2.createCell(7);
        celdaEnc5.setCellValue("Omiso IVA");

        /*---------------------------------------------------*/
        HSSFRow filaEnc3 = hoja.createRow(3);
        
        HSSFCell celdaEnc7 = filaEnc3.createCell(0);
        celdaEnc7.setCellValue("Nombre del contribuyente");
        
        HSSFCell celdaEnc8 = filaEnc3.createCell(1);
        celdaEnc8.setCellValue(listaDatosEncabezado.get(0).getNombreOrdenado());
        
        HSSFCell celdaEnc9 = filaEnc3.createCell(7);
        celdaEnc9.setCellValue("Omiso ISR");

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
        celdaEnc15.setCellValue("Clasificación");
        
        HSSFCell celdaEnc16 = filaEnc5.createCell(1);
        celdaEnc16.setCellValue(listaDatosEncabezado.get(0).getClasificacion());
        
        HSSFCell celdaEnc17 = filaEnc5.createCell(7);
        celdaEnc17.setCellValue("Dirección");
        
        HSSFCell celdaEnc18 = filaEnc5.createCell(8);
        celdaEnc18.setCellValue(listaDatosEncabezado.get(0).getDireccion());

        /*---------------------------------------------------*/
        HSSFRow filaEnc6 = hoja.createRow(6);
        
        HSSFCell celdaEnc19 = filaEnc6.createCell(0);
        celdaEnc19.setCellValue("Región");
        
        HSSFCell celdaEnc20 = filaEnc6.createCell(1);
        celdaEnc20.setCellValue(listaDatosEncabezado.get(0).getRegion());
        
        HSSFCell celdaEnc21 = filaEnc6.createCell(7);
        celdaEnc21.setCellValue("Status contribuyente");
        
        HSSFCell celdaEnc22 = filaEnc6.createCell(1);
        celdaEnc22.setCellValue(listaDatosEncabezado.get(0).getStatus());

        /*---------------------------------------------------*/
        HSSFRow filaEnc7 = hoja.createRow(7);
        
        HSSFCell celdaEnc23 = filaEnc7.createCell(0);
        celdaEnc23.setCellValue("Actividad Ecnonomica");

        /*FALTA CAMPO*/
        HSSFCell celdaEnc25 = filaEnc7.createCell(7);
        celdaEnc25.setCellValue("Nombre regimen ISR ");

        /*FALTA CAMPO/

        /*---------------------------------------------------*/
        HSSFRow filaEnc8 = hoja.createRow(8);
        
        HSSFCell celdaEnc27 = filaEnc8.createCell(0);
        celdaEnc27.setCellValue("Personería");
        
        HSSFCell celdaEnc28 = filaEnc8.createCell(1);
        celdaEnc28.setCellValue(listaDatosEncabezado.get(0).getPersoneria());
        
        HSSFCell celdaEnc29 = filaEnc8.createCell(7);
        celdaEnc29.setCellValue("Fecha afiliación ISR ");

        /*FALTA CAMPO*/
 /*---------------------------------------------------*/
        HSSFRow filaEnc9 = hoja.createRow(9);
        
        HSSFCell celdaEnc31 = filaEnc9.createCell(0);
        celdaEnc31.setCellValue("Nombre tipo");
        
        HSSFCell celdaEnc32 = filaEnc9.createCell(1);
        celdaEnc32.setCellValue(listaDatosEncabezado.get(0).getTipoPersona());
        
        HSSFCell celdaEnc33 = filaEnc9.createCell(7);
        celdaEnc33.setCellValue("Nombre regimen IVA");
        
        HSSFCell celdaEnc34 = filaEnc9.createCell(1);
        celdaEnc34.setCellValue(listaDatosEncabezado.get(0).getNombreRegimen());

        /*---------------------------------------------------*/
        HSSFRow filaEnc10 = hoja.createRow(10);
        
        HSSFCell celdaEnc35 = filaEnc10.createCell(0);
        celdaEnc35.setCellValue("Marca no localizado");
        
        HSSFCell celdaEnc36 = filaEnc10.createCell(1);
        celdaEnc36.setCellValue(listaDatosEncabezado.get(0).getMarcaNoLocalizado());
        
        HSSFCell celdaEnc37 = filaEnc10.createCell(7);
        celdaEnc37.setCellValue("Fecha afiliación IVA");
        
        if (listaIngresosIsr.isEmpty() || listaIngresosIsr == null) {

            /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
            HSSFRow filaH = hoja.createRow(15);
            
            HSSFCell celdaH0 = filaH.createCell(0);
            celdaH0.setCellStyle(rowStyle);
            celdaH0.setCellValue("PERIODO DESDE");
            
            HSSFCell celdaH1 = filaH.createCell(1);
            celdaH1.setCellStyle(rowStyle);
            celdaH1.setCellValue("PERIODO HASTA");
            
            HSSFCell celdaH2 = filaH.createCell(2);
            celdaH2.setCellStyle(rowStyle);
            celdaH2.setCellValue("FECHA RECAUDO IVA");
            
            HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("CODIGO FORMULARIO IVA");
            
            HSSFCell celdaH4 = filaH.createCell(4);
            celdaH4.setCellStyle(rowStyle);
            celdaH4.setCellValue("NUMERO FORMULARIO IVA ");
            
            HSSFCell celdaH5 = filaH.createCell(5);
            celdaH5.setCellStyle(rowStyle);
            celdaH5.setCellValue("STATUS DEL FORMULARIO IVA");
            
            HSSFCell celdaH6 = filaH.createCell(6);
            celdaH6.setCellStyle(rowStyle);
            celdaH6.setCellValue("VENTAS EXENTAS");
            
            HSSFCell celdaH7 = filaH.createCell(7);
            celdaH7.setCellStyle(rowStyle);
            celdaH7.setCellValue("VENTAS MEDICAMENTOS GENERICOS ALTERNATIVOS Y ANTIRRETROVIRALES");
            
            HSSFCell celdaH8 = filaH.createCell(8);
            celdaH8.setCellStyle(rowStyle);
            celdaH8.setCellValue("VENTAS NO AFECTADAS REALIZADAS A CONTRIBUYENTES CALIFICADOS CON EL DECRETO #29-89 Y SUS REFORMAS");
            
            HSSFCell celdaH9 = filaH.createCell(9);
            celdaH9.setCellStyle(rowStyle);
            celdaH9.setCellValue("VENTAS GRAVADAS");
            
            HSSFCell celdaH10 = filaH.createCell(10);
            celdaH10.setCellStyle(rowStyle);
            celdaH10.setCellValue("SERVICIOS GRAVADAS");
            
            HSSFCell celdaH11 = filaH.createCell(11);
            celdaH11.setCellStyle(rowStyle);
            celdaH11.setCellValue("EXPORTACIONES A CENTRO AMERICA");

            /*HSSFCell celdaH12 = filaH.createCell(12);
            celdaH12.setCellStyle(rowStyle);
            celdaH12.setCellValue("EXPORTACIONES ");*/
            HSSFCell celdaH12 = filaH.createCell(13);
            celdaH12.setCellStyle(rowStyle);
            celdaH12.setCellValue("EXPORTACIONES AL RESTO DEL MUNDO");
            
            HSSFCell celdaH13 = filaH.createCell(13);
            celdaH13.setCellStyle(rowStyle);
            celdaH13.setCellValue("VENTAS DE MEDICAMENTOS GENERICOS ALTERNATIVOS Y ANTIRRETROVIRALES");
            
            HSSFCell celdaH14 = filaH.createCell(14);
            celdaH14.setCellStyle(rowStyle);
            celdaH14.setCellValue("TRANSFERENCIAS CON FYDUCA");
            
            HSSFCell celdaH15 = filaH.createCell(15);
            celdaH15.setCellStyle(rowStyle);
            celdaH15.setCellValue("VENTAS DE VEHICULOS TERRESTRES DEL MODELO DE DOS AÑOS O MAS ANTERIORES AL DEL AÑO EN CURSO CASILLA 10");
            
            HSSFCell celdaH16 = filaH.createCell(16);
            celdaH16.setCellStyle(rowStyle);
            celdaH16.setCellValue("VENTAS DE VEHICULOS TERRESTRES DEL MODELO DEL AÑO EN CURSO DEL AÑO SIGUIENTE O ANTERIOR AL DEL AÑO EN CURSO");
            
            HSSFCell celdaH17 = filaH.createCell(17);
            celdaH17.setCellStyle(rowStyle);
            celdaH17.setCellValue("VENTAS DE VEHICULOS TERRESTRES DEL MODELO DE DOS AÑOS O MAS ANTERIORES AL DEL AÑO EN CURSO ");
            
            HSSFCell celdaH18 = filaH.createCell(18);
            celdaH18.setCellStyle(rowStyle);
            celdaH18.setCellValue("VENTAS DE VEHICULOS TERRESTRES DEL MODELO DEL AÑO EN CURSO DEL AÑO SIGUIENTE O ANTERIOR AL DEL AÑO EN CURSO CASILLA 23");
            
            HSSFCell celdaH19 = filaH.createCell(19);
            celdaH19.setCellStyle(rowStyle);
            celdaH19.setCellValue("SUMA BASE DEBITOS");
            
            HSSFCell celdaH20 = filaH.createCell(20);
            celdaH20.setCellStyle(rowStyle);
            celdaH20.setCellValue("SUMA BASE DEBITOS EXPO");
            
            HSSFCell celdaH21 = filaH.createCell(21);
            celdaH21.setCellStyle(rowStyle);
            celdaH21.setCellValue("FECHA RECAUDO ISR");
            
            HSSFCell celdaH22 = filaH.createCell(22);
            celdaH22.setCellStyle(rowStyle);
            celdaH22.setCellValue("CODIGO FORMULARIO ISR");
            
            HSSFCell celdaH23 = filaH.createCell(23);
            celdaH23.setCellStyle(rowStyle);
            celdaH23.setCellValue("NUMERO FORMULARIO ISR");
            
            HSSFCell celdaH24 = filaH.createCell(24);
            celdaH24.setCellStyle(rowStyle);
            celdaH24.setCellValue("STATUS DEL FORMULARIO ISR");
            
            HSSFCell celdaH25 = filaH.createCell(25);
            celdaH25.setCellStyle(rowStyle);
            celdaH25.setCellValue("RENTA DE ACTIVIDADES LUCRATIVAS");
            
            HSSFCell celdaH26 = filaH.createCell(26);
            celdaH26.setCellStyle(rowStyle);
            celdaH26.setCellValue("MONTO TOTAL DE RENTAS EXENTAS");
            
            HSSFCell celdaH27 = filaH.createCell(27);
            celdaH27.setCellStyle(rowStyle);
            celdaH27.setCellValue("RENTAIMPONIBLE");
            
            HSSFCell celdaH28 = filaH.createCell(28);
            celdaH28.setCellStyle(rowStyle);
            celdaH28.setCellValue("TOTAL IVA");
            
            HSSFCell celdaH29 = filaH.createCell(29);
            celdaH29.setCellStyle(rowStyle);
            celdaH29.setCellValue("TOTAL ISR");
            
            HSSFCell celdaH30 = filaH.createCell(30);
            celdaH30.setCellStyle(rowStyle);
            celdaH30.setCellValue("DIFERENCIAS ISR IVA");
            
            HSSFCell celdaH31 = filaH.createCell(32);
            celdaH31.setCellStyle(rowStyle);
            celdaH31.setCellValue("RECAUDO POTENCIAL ISR");
            
            HSSFCell celdaH32 = filaH.createCell(32);
            celdaH32.setCellStyle(rowStyle);
            celdaH32.setCellValue("DIFERENCIA IVA ISR");
            
            HSSFCell celdaH33 = filaH.createCell(33);
            celdaH33.setCellStyle(rowStyle);
            celdaH33.setCellValue("RECAUDO POTENCIAL IVA");
            
            HSSFCell celdaH34 = filaH.createCell(35);
            celdaH34.setCellStyle(rowStyle);
            celdaH34.setCellValue("TOTAL VENTAS EXENTAS IVA");
            
            HSSFCell celdaH35 = filaH.createCell(35);
            celdaH35.setCellStyle(rowStyle);
            celdaH35.setCellValue("MONTO TOTAL DE RENTAS EXENTAS 2");
            
            HSSFCell celdaH36 = filaH.createCell(36);
            celdaH36.setCellStyle(rowStyle);
            celdaH36.setCellValue("POTENCIAL RECAUDO IVA E ISR");
            
            HSSFCell celdaH37 = filaH.createCell(37);
            celdaH37.setCellStyle(rowStyle);
            celdaH37.setCellValue("SEGMENTACIÓN");
            
            for (int x = 0; x < listaIngresosIsr.size(); x++) {
                
                HSSFRow fila = hoja.createRow(x + 14);
                
                HSSFCell celda0 = fila.createCell(0);
                celda0.setCellStyle(cellStyle);
                celda0.setCellValue(listaIngresosIsr.get(x).getPeriodoDesde());
                
                HSSFCell celda1 = fila.createCell(1);
                celda1.setCellStyle(cellStyle);
                celda1.setCellValue(listaIngresosIsr.get(x).getPeriodoHasta());
                
                HSSFCell celda2 = fila.createCell(2);
                celda2.setCellStyle(cellStyle);
                celda2.setCellValue(listaIngresosIsr.get(x).getFechaRecaudoIva());
                
                HSSFCell celda3 = fila.createCell(3);
                celda3.setCellStyle(cellStyle);
                celda3.setCellValue(listaIngresosIsr.get(x).getCodigoFormularioIva());
                
                HSSFCell celda4 = fila.createCell(4);
                celda4.setCellStyle(cellStyle);
                celda4.setCellValue(listaIngresosIsr.get(x).getNumeroFormularioIva());
                
                HSSFCell celda5 = fila.createCell(5);
                celda5.setCellStyle(cellStyle);
                celda5.setCellValue(listaIngresosIsr.get(x).getStatusDelFormularioIva());
                
                HSSFCell celda6 = fila.createCell(6);
                celda6.setCellStyle(cellStyle);
                celda6.setCellValue(listaIngresosIsr.get(x).getVentasExentas());
                
                HSSFCell celda7 = fila.createCell(7);
                celda7.setCellStyle(cellStyle);
                celda7.setCellValue(listaIngresosIsr.get(x).getVentasMedicamentosGenericosAlternantivosYAntirretrovirales());
                
                HSSFCell celda8 = fila.createCell(8);
                celda8.setCellStyle(cellStyle);
                celda8.setCellValue(listaIngresosIsr.get(x).getVentasNoAfectadasRealizadasAContribuyentesCalificadosConElDecreto29_28_y_SusReformas());
                
                HSSFCell celda9 = fila.createCell(9);
                celda9.setCellStyle(cellStyle);
                celda9.setCellValue(listaIngresosIsr.get(x).getVentasGravadas());
                
                HSSFCell celda10 = fila.createCell(10);
                celda10.setCellStyle(cellStyle);
                celda10.setCellValue(listaIngresosIsr.get(x).getServiciosGravados());
                
                HSSFCell celda11 = fila.createCell(11);
                celda11.setCellStyle(cellStyle);
                celda11.setCellValue(listaIngresosIsr.get(x).getExportacionesACentroAmerica());
                
                HSSFCell celda12 = fila.createCell(12);
                celda12.setCellStyle(cellStyle);
                celda12.setCellValue(listaIngresosIsr.get(x).getExportacionesAlRestoDelMundo());
                
                HSSFCell celda13 = fila.createCell(13);
                celda13.setCellStyle(cellStyle);
                celda13.setCellValue(listaIngresosIsr.get(x).getVentasMedicamentosGenericosAlternantivosYAntirretrovirales());
                
                HSSFCell celda14 = fila.createCell(14);
                celda14.setCellStyle(cellStyle);
                celda14.setCellValue(listaIngresosIsr.get(x).getTransferenciasConFyduca());
                
                HSSFCell celda15 = fila.createCell(15);
                celda15.setCellStyle(cellStyle);
                celda15.setCellValue(listaIngresosIsr.get(x).getVentasdeVehículosTerrestresEnCursoCasilla10());
                
                HSSFCell celda16 = fila.createCell(16);
                celda16.setCellStyle(cellStyle);
                celda16.setCellValue(listaIngresosIsr.get(x).getVentasDeVehículosTerrestresDelAñoEnCursoDelAñoSiguienteAlDelAñoEnCurso());
                
                HSSFCell celda17 = fila.createCell(17);
                celda17.setCellStyle(cellStyle);
                celda17.setCellValue(listaIngresosIsr.get(x).getVentasDeVehiculosTerrestreSiguienteoAnteriorAlDelAñoEnCurso());
                
                HSSFCell celda18 = fila.createCell(18);
                celda18.setCellStyle(cellStyle);
                celda18.setCellValue(listaIngresosIsr.get(x).getVentasDeVehículosTerrestresAñoSiguienteAñoAnteriorAñoCursoCas23());
                
                HSSFCell celda19 = fila.createCell(19);
                celda19.setCellStyle(cellStyle);
                celda19.setCellValue(listaIngresosIsr.get(x).getSumaBaseDebitos());
                
                HSSFCell celda20 = fila.createCell(20);
                celda20.setCellStyle(cellStyle);
                celda20.setCellValue(listaIngresosIsr.get(x).getSumaBaseDebitosExpo());
                
                HSSFCell celda21 = fila.createCell(21);
                celda21.setCellStyle(cellStyle);
                celda21.setCellValue(listaIngresosIsr.get(x).getFechaRecaudoIsr());
                
                HSSFCell celda22 = fila.createCell(22);
                celda22.setCellStyle(cellStyle);
                celda22.setCellValue(listaIngresosIsr.get(x).getCodigoFormularioIsr());
                
                HSSFCell celda23 = fila.createCell(23);
                celda23.setCellStyle(cellStyle);
                celda23.setCellValue(listaIngresosIsr.get(x).getNumeroFormularioIsr());
                
                HSSFCell celda24 = fila.createCell(24);
                celda24.setCellStyle(cellStyle);
                celda24.setCellValue(listaIngresosIsr.get(x).getStatusDelFormularioIsr());
                
                HSSFCell celda25 = fila.createCell(25);
                celda25.setCellStyle(cellStyle);
                celda25.setCellValue(listaIngresosIsr.get(x).getRentaDeActividadesLucrativas());
                
                HSSFCell celda26 = fila.createCell(26);
                celda26.setCellStyle(cellStyle);
                celda26.setCellValue(listaIngresosIsr.get(x).getMontoTotalDeRentasExentas());
                
                HSSFCell celda27 = fila.createCell(27);
                celda27.setCellStyle(cellStyle);
                celda27.setCellValue(listaIngresosIsr.get(x).getRentaImponible());
                
                HSSFCell celda28 = fila.createCell(28);
                celda28.setCellStyle(cellStyle);
                celda28.setCellValue(listaIngresosIsr.get(x).getTotalIva());
                
                HSSFCell celda29 = fila.createCell(29);
                celda29.setCellStyle(cellStyle);
                celda29.setCellValue(listaIngresosIsr.get(x).getTotalIsr());
                
                HSSFCell celda30 = fila.createCell(30);
                celda30.setCellStyle(cellStyle);
                celda30.setCellValue(listaIngresosIsr.get(x).getDiferenciaIsrIva());
                
                HSSFCell celda31 = fila.createCell(32);
                celda31.setCellStyle(cellStyle);
                celda31.setCellValue(listaIngresosIsr.get(x).getRecaudoPotencialIsr());
                
                HSSFCell celda32 = fila.createCell(32);
                celda32.setCellStyle(cellStyle);
                celda32.setCellValue(listaIngresosIsr.get(x).getDiferenciaIvaIsr());
                
                HSSFCell celda33 = fila.createCell(33);
                celda33.setCellStyle(cellStyle);
                celda33.setCellValue(listaIngresosIsr.get(x).getRecaudoPotencialIva());
                
                HSSFCell celda34 = fila.createCell(34);
                celda34.setCellStyle(cellStyle);
                celda34.setCellValue(listaIngresosIsr.get(x).getTotalVentasExentasIva());
                
                HSSFCell celda35 = fila.createCell(35);
                celda35.setCellStyle(cellStyle);
                celda35.setCellValue(listaIngresosIsr.get(x).getMontoTotalDeRentasExentas());
                
                HSSFCell celda36 = fila.createCell(36);
                celda36.setCellStyle(cellStyle);
                celda36.setCellValue(listaIngresosIsr.get(x).getPotencialRecaudoIvaEIsr());
                
                HSSFCell celda37 = fila.createCell(37);
                celda37.setCellStyle(cellStyle);
                celda37.setCellValue(listaIngresosIsr.get(x).getSegmentacion());
                
            }
        }
        
        if (listDatosGeneral.isEmpty() || listDatosGeneral == null) {

            /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
            HSSFRow filaH = hoja.createRow(14);
            
            HSSFCell celdaH0 = filaH.createCell(0);
            celdaH0.setCellStyle(rowStyle);
            celdaH0.setCellValue("PERIODO DESDE");
            
            HSSFCell celdaH1 = filaH.createCell(1);
            celdaH1.setCellStyle(rowStyle);
            celdaH1.setCellValue("PERIODO HASTA");
            
            HSSFCell celdaH2 = filaH.createCell(2);
            celdaH2.setCellStyle(rowStyle);
            celdaH2.setCellValue("FECHA RECAUDO IVA");
            
            HSSFCell celdaH3 = filaH.createCell(3);
            celdaH3.setCellStyle(rowStyle);
            celdaH3.setCellValue("CODIGO FORMULARIO IVA");
            
            HSSFCell celdaH4 = filaH.createCell(4);
            celdaH4.setCellStyle(rowStyle);
            celdaH4.setCellValue("NUMERO FORMULARIO IVA ");
            
            HSSFCell celdaH5 = filaH.createCell(5);
            celdaH5.setCellStyle(rowStyle);
            celdaH5.setCellValue("STATUS DEL FORMULARIO IVA");
            
            HSSFCell celdaH6 = filaH.createCell(6);
            celdaH6.setCellStyle(rowStyle);
            celdaH6.setCellValue("VENTAS EXENTAS");
            
            HSSFCell celdaH7 = filaH.createCell(7);
            celdaH7.setCellStyle(rowStyle);
            celdaH7.setCellValue("VENTAS MEDICAMENTOS GENERICOS ALTERNATIVOS Y ANTIRRETROVIRALES");
            
            HSSFCell celdaH8 = filaH.createCell(8);
            celdaH8.setCellStyle(rowStyle);
            celdaH8.setCellValue("VENTAS NO AFECTADAS REALIZADAS A CONTRIBUYENTES CALIFICADOS CON EL DECRETO #29-89 Y SUS REFORMAS");
            
            HSSFCell celdaH9 = filaH.createCell(9);
            celdaH9.setCellStyle(rowStyle);
            celdaH9.setCellValue("VENTAS GRAVADAS");
            
            HSSFCell celdaH10 = filaH.createCell(10);
            celdaH10.setCellStyle(rowStyle);
            celdaH10.setCellValue("SERVICIOS GRAVADAS");
            
            HSSFCell celdaH11 = filaH.createCell(11);
            celdaH11.setCellStyle(rowStyle);
            celdaH11.setCellValue("EXPORTACIONES A CENTRO AMERICA");

            /*HSSFCell celdaH12 = filaH.createCell(12);
            celdaH12.setCellStyle(rowStyle);
            celdaH12.setCellValue("EXPORTACIONES ");*/
            HSSFCell celdaH12 = filaH.createCell(12);
            celdaH12.setCellStyle(rowStyle);
            celdaH12.setCellValue("EXPORTACIONES AL RESTO DEL MUNDO");
            
            HSSFCell celdaH13 = filaH.createCell(13);
            celdaH13.setCellStyle(rowStyle);
            celdaH13.setCellValue("VENTAS DE MEDICAMENTOS GENERICOS ALTERNATIVOS Y ANTIRRETROVIRALES");
            
            HSSFCell celdaH14 = filaH.createCell(14);
            celdaH14.setCellStyle(rowStyle);
            celdaH14.setCellValue("TRANSFERENCIAS CON FYDUCA");
            
            HSSFCell celdaH15 = filaH.createCell(15);
            celdaH15.setCellStyle(rowStyle);
            celdaH15.setCellValue("VENTAS DE VEHICULOS TERRESTRES DEL MODELO DE DOS AÑOS O MAS ANTERIORES AL DEL AÑO EN CURSO CASILLA 10");
            
            HSSFCell celdaH16 = filaH.createCell(16);
            celdaH16.setCellStyle(rowStyle);
            celdaH16.setCellValue("VENTAS DE VEHICULOS TERRESTRES DEL MODELO DEL AÑO EN CURSO DEL AÑO SIGUIENTE O ANTERIOR AL DEL AÑO EN CURSO");
            
            HSSFCell celdaH17 = filaH.createCell(17);
            celdaH17.setCellStyle(rowStyle);
            celdaH17.setCellValue("VENTAS DE VEHICULOS TERRESTRES DEL MODELO DE DOS AÑOS O MAS ANTERIORES AL DEL AÑO EN CURSO ");
            
            HSSFCell celdaH18 = filaH.createCell(18);
            celdaH18.setCellStyle(rowStyle);
            celdaH18.setCellValue("VENTAS DE VEHICULOS TERRESTRES DEL MODELO DEL AÑO EN CURSO DEL AÑO SIGUIENTE O ANTERIOR AL DEL AÑO EN CURSO CASILLA 23");
            
            HSSFCell celdaH19 = filaH.createCell(19);
            celdaH19.setCellStyle(rowStyle);
            celdaH19.setCellValue("SUMA BASE DEBITOS");
            
            HSSFCell celdaH20 = filaH.createCell(20);
            celdaH20.setCellStyle(rowStyle);
            celdaH20.setCellValue("SUMA BASE DEBITOS EXPO");
            
            HSSFCell celdaH21 = filaH.createCell(21);
            celdaH21.setCellStyle(rowStyle);
            celdaH21.setCellValue("FECHA RECAUDO ISR");
            
            HSSFCell celdaH22 = filaH.createCell(22);
            celdaH22.setCellStyle(rowStyle);
            celdaH22.setCellValue("CODIGO FORMULARIO ISR");
            
            HSSFCell celdaH23 = filaH.createCell(23);
            celdaH23.setCellStyle(rowStyle);
            celdaH23.setCellValue("NUMERO FORMULARIO ISR");
            
            HSSFCell celdaH24 = filaH.createCell(24);
            celdaH24.setCellStyle(rowStyle);
            celdaH24.setCellValue("STATUS DEL FORMULARIO ISR");
            
            HSSFCell celdaH25 = filaH.createCell(25);
            celdaH25.setCellStyle(rowStyle);
            celdaH25.setCellValue("RENTA DE ACTIVIDADES LUCRATIVAS");
            
            HSSFCell celdaH26 = filaH.createCell(26);
            celdaH26.setCellStyle(rowStyle);
            celdaH26.setCellValue("MONTO TOTAL DE RENTAS EXENTAS");
            
            HSSFCell celdaH27 = filaH.createCell(27);
            celdaH27.setCellStyle(rowStyle);
            celdaH27.setCellValue("RENTAIMPONIBLE");
            
            HSSFCell celdaH28 = filaH.createCell(28);
            celdaH28.setCellStyle(rowStyle);
            celdaH28.setCellValue("TOTAL IVA");
            
            HSSFCell celdaH29 = filaH.createCell(29);
            celdaH29.setCellStyle(rowStyle);
            celdaH29.setCellValue("TOTAL ISR");
            
            HSSFCell celdaH30 = filaH.createCell(30);
            celdaH30.setCellStyle(rowStyle);
            celdaH30.setCellValue("DIFERENCIAS ISR IVA");
            
            HSSFCell celdaH31 = filaH.createCell(31);
            celdaH31.setCellStyle(rowStyle);
            celdaH31.setCellValue("RECAUDO POTENCIAL ISR");
            
            HSSFCell celdaH32 = filaH.createCell(32);
            celdaH32.setCellStyle(rowStyle);
            celdaH32.setCellValue("DIFERENCIA IVA ISR");
            
            HSSFCell celdaH33 = filaH.createCell(33);
            celdaH33.setCellStyle(rowStyle);
            celdaH33.setCellValue("RECAUDO POTENCIAL IVA");
            
            HSSFCell celdaH34 = filaH.createCell(34);
            celdaH34.setCellStyle(rowStyle);
            celdaH34.setCellValue("TOTAL VENTAS EXENTAS IVA");
            
            HSSFCell celdaH35 = filaH.createCell(35);
            celdaH35.setCellStyle(rowStyle);
            celdaH35.setCellValue("MONTO TOTAL DE RENTAS EXENTAS 2");
            
            HSSFCell celdaH36 = filaH.createCell(36);
            celdaH36.setCellStyle(rowStyle);
            celdaH36.setCellValue("POTENCIAL RECAUDO IVA E ISR");
            
            HSSFCell celdaH37 = filaH.createCell(37);
            celdaH37.setCellStyle(rowStyle);
            celdaH37.setCellValue("SEGMENTACIÓN");
            
            for (int x = 0; x < listaIngresosIsr.size(); x++) {
                
                HSSFRow fila = hoja.createRow(x + 15);
                
                HSSFCell celda0 = fila.createCell(0);
                celda0.setCellStyle(cellStyle);
                celda0.setCellValue(listaIngresosIsr.get(x).getPeriodoDesde());
                
                HSSFCell celda1 = fila.createCell(1);
                celda1.setCellStyle(cellStyle);
                celda1.setCellValue(listaIngresosIsr.get(x).getPeriodoHasta());
                
                HSSFCell celda2 = fila.createCell(2);
                celda2.setCellStyle(cellStyle);
                celda2.setCellValue(listaIngresosIsr.get(x).getFechaRecaudoIva());
                
                HSSFCell celda3 = fila.createCell(3);
                celda3.setCellStyle(cellStyle);
                celda3.setCellValue(listaIngresosIsr.get(x).getCodigoFormularioIva());
                
                HSSFCell celda4 = fila.createCell(4);
                celda4.setCellStyle(cellStyle);
                celda4.setCellValue(listaIngresosIsr.get(x).getNumeroFormularioIva());
                
                HSSFCell celda5 = fila.createCell(5);
                celda5.setCellStyle(cellStyle);
                celda5.setCellValue(listaIngresosIsr.get(x).getStatusDelFormularioIva());
                
                HSSFCell celda6 = fila.createCell(6);
                celda6.setCellStyle(cellStyle);
                celda6.setCellValue(listaIngresosIsr.get(x).getVentasExentas());
                
                HSSFCell celda7 = fila.createCell(7);
                celda7.setCellStyle(cellStyle);
                celda7.setCellValue(listaIngresosIsr.get(x).getVentasMedicamentosGenericosAlternantivosYAntirretrovirales());
                
                HSSFCell celda8 = fila.createCell(8);
                celda8.setCellStyle(cellStyle);
                celda8.setCellValue(listaIngresosIsr.get(x).getVentasNoAfectadasRealizadasAContribuyentesCalificadosConElDecreto29_28_y_SusReformas());
                
                HSSFCell celda9 = fila.createCell(9);
                celda9.setCellStyle(cellStyle);
                celda9.setCellValue(listaIngresosIsr.get(x).getVentasGravadas());
                
                HSSFCell celda10 = fila.createCell(10);
                celda10.setCellStyle(cellStyle);
                celda10.setCellValue(listaIngresosIsr.get(x).getServiciosGravados());
                
                HSSFCell celda11 = fila.createCell(11);
                celda11.setCellStyle(cellStyle);
                celda11.setCellValue(listaIngresosIsr.get(x).getExportacionesACentroAmerica());
                
                HSSFCell celda12 = fila.createCell(12);
                celda12.setCellStyle(cellStyle);
                celda12.setCellValue(listaIngresosIsr.get(x).getExportacionesAlRestoDelMundo());
                
                HSSFCell celda13 = fila.createCell(13);
                celda13.setCellStyle(cellStyle);
                celda13.setCellValue(listaIngresosIsr.get(x).getVentasMedicamentosGenericosAlternantivosYAntirretrovirales());
                
                HSSFCell celda14 = fila.createCell(14);
                celda14.setCellStyle(cellStyle);
                celda14.setCellValue(listaIngresosIsr.get(x).getTransferenciasConFyduca());
                
                HSSFCell celda15 = fila.createCell(15);
                celda15.setCellStyle(cellStyle);
                celda15.setCellValue(listaIngresosIsr.get(x).getVentasdeVehículosTerrestresEnCursoCasilla10());
                
                HSSFCell celda16 = fila.createCell(16);
                celda16.setCellStyle(cellStyle);
                celda16.setCellValue(listaIngresosIsr.get(x).getVentasDeVehículosTerrestresDelAñoEnCursoDelAñoSiguienteAlDelAñoEnCurso());
                
                HSSFCell celda17 = fila.createCell(17);
                celda17.setCellStyle(cellStyle);
                celda17.setCellValue(listaIngresosIsr.get(x).getVentasDeVehiculosTerrestreSiguienteoAnteriorAlDelAñoEnCurso());
                
                HSSFCell celda18 = fila.createCell(18);
                celda18.setCellStyle(cellStyle);
                celda18.setCellValue(listaIngresosIsr.get(x).getVentasDeVehículosTerrestresAñoSiguienteAñoAnteriorAñoCursoCas23());
                
                HSSFCell celda19 = fila.createCell(19);
                celda19.setCellStyle(cellStyle);
                celda19.setCellValue(listaIngresosIsr.get(x).getSumaBaseDebitos());
                
                HSSFCell celda20 = fila.createCell(20);
                celda20.setCellStyle(cellStyle);
                celda20.setCellValue(listaIngresosIsr.get(x).getSumaBaseDebitosExpo());
                
                HSSFCell celda21 = fila.createCell(21);
                celda21.setCellStyle(cellStyle);
                celda21.setCellValue(listaIngresosIsr.get(x).getFechaRecaudoIsr());
                
                HSSFCell celda22 = fila.createCell(22);
                celda22.setCellStyle(cellStyle);
                celda22.setCellValue(listaIngresosIsr.get(x).getCodigoFormularioIsr());
                
                HSSFCell celda23 = fila.createCell(23);
                celda23.setCellStyle(cellStyle);
                celda23.setCellValue(listaIngresosIsr.get(x).getNumeroFormularioIsr());
                
                HSSFCell celda24 = fila.createCell(24);
                celda24.setCellStyle(cellStyle);
                celda24.setCellValue(listaIngresosIsr.get(x).getStatusDelFormularioIsr());
                
                HSSFCell celda25 = fila.createCell(25);
                celda25.setCellStyle(cellStyle);
                celda25.setCellValue(listaIngresosIsr.get(x).getRentaDeActividadesLucrativas());
                
                HSSFCell celda26 = fila.createCell(26);
                celda26.setCellStyle(cellStyle);
                celda26.setCellValue(listaIngresosIsr.get(x).getMontoTotalDeRentasExentas());
                
                HSSFCell celda27 = fila.createCell(27);
                celda27.setCellStyle(cellStyle);
                celda27.setCellValue(listaIngresosIsr.get(x).getRentaImponible());
                
                HSSFCell celda28 = fila.createCell(28);
                celda28.setCellStyle(cellStyle);
                celda28.setCellValue(listaIngresosIsr.get(x).getTotalIva());
                
                HSSFCell celda29 = fila.createCell(29);
                celda29.setCellStyle(cellStyle);
                celda29.setCellValue(listaIngresosIsr.get(x).getTotalIsr());
                
                HSSFCell celda30 = fila.createCell(30);
                celda30.setCellStyle(cellStyle);
                celda30.setCellValue(listaIngresosIsr.get(x).getDiferenciaIsrIva());
                
                HSSFCell celda31 = fila.createCell(32);
                celda31.setCellStyle(cellStyle);
                celda31.setCellValue(listaIngresosIsr.get(x).getRecaudoPotencialIsr());
                
                HSSFCell celda32 = fila.createCell(32);
                celda32.setCellStyle(cellStyle);
                celda32.setCellValue(listaIngresosIsr.get(x).getDiferenciaIvaIsr());
                
                HSSFCell celda33 = fila.createCell(33);
                celda33.setCellStyle(cellStyle);
                celda33.setCellValue(listaIngresosIsr.get(x).getRecaudoPotencialIva());
                
                HSSFCell celda34 = fila.createCell(34);
                celda34.setCellStyle(cellStyle);
                celda34.setCellValue(listaIngresosIsr.get(x).getTotalVentasExentasIva());
                
                HSSFCell celda35 = fila.createCell(35);
                celda35.setCellStyle(cellStyle);
                celda35.setCellValue(listaIngresosIsr.get(x).getMontoTotalDeRentasExentas());
                
                HSSFCell celda36 = fila.createCell(36);
                celda36.setCellStyle(cellStyle);
                celda36.setCellValue(listaIngresosIsr.get(x).getPotencialRecaudoIvaEIsr());
                
                HSSFCell celda37 = fila.createCell(37);
                celda37.setCellStyle(cellStyle);
                celda37.setCellValue(listaIngresosIsr.get(x).getSegmentacion());
                
            }
        }
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        externalContext.setResponseContentType("application/vnd.ms-excel; charset=utf-8");
        
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"ingresos_reportados_en_IVA_e_ISR_régimen_opcional_simplificado.xls\"");
        
        libro.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();
    }
    
}
