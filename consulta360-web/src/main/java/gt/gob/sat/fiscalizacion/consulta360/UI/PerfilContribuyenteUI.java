/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import com.itextpdf.text.BaseColor;
import gt.gob.sat.arquitectura.fwk.dto.ParameterDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.AfiliacionDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.AfiliacionesMSRTUNubeDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ApoderadoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.CantidadVehiculosDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ConteoVehiculosActivosInactivosDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ContribuyenteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.GeneralVerificacionesDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HistorialRiesgoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ImpuestosDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.InfoConteoEstablecimientosEstadoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.InfoEstablecimientosDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.PartesRelacionadasDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.PequenoContribuyenteEncabezadoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.PerfilContribuyenteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.TipologiasEvasionAnualDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.TotalDto;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.collections.CollectionUtils;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gt.gob.sat.fiscalizacion.consulta360.dto.CamEspecialesContriDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ConsultaCantidadDeclaracionesAduanerasConAnioDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ConveniosActivosDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HandledException;
import gt.gob.sat.fiscalizacion.consulta360.dto.IndicadoresCobProveedoresDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IndicadoresCreditoDebitoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IndicadoresGastosGeneralesDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IndicadoresRentabilidadDto;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.time.Year;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/*import org.apache.poi.xwpf*/
/**
 *
 * @author dealonzo
 */
@Controller
@Scope("view")
public class PerfilContribuyenteUI extends AbstractUI implements Serializable {

    @Autowired
    ConsumoServicios ConsumoServicios;

    /**
     * @return the mostrarHistorialDFC
     */
    public boolean isMostrarHistorialDFC() {
        return mostrarHistorialDFC;
    }

    /**
     * @param mostrarHistorialDFC the mostrarHistorialDFC to set
     */
    public void setMostrarHistorialDFC(boolean mostrarHistorialDFC) {
        this.mostrarHistorialDFC = mostrarHistorialDFC;
    }

    /**
     * @return the mostrarBotonHistorialDCF
     */
    public boolean isMostrarBotonHistorialDCF() {
        return mostrarBotonHistorialDCF;
    }

    /**
     * @param mostrarBotonHistorialDCF the mostrarBotonHistorialDCF to set
     */
    public void setMostrarBotonHistorialDCF(boolean mostrarBotonHistorialDCF) {
        this.mostrarBotonHistorialDCF = mostrarBotonHistorialDCF;
    }

    /**
     * @return the mostrarBotonHistorial
     */
    public boolean isMostrarBotonHistorial() {
        return mostrarBotonHistorial;
    }

    /**
     * @param mostrarBotonHistorial the mostrarBotonHistorial to set
     */
    public void setMostrarBotonHistorial(boolean mostrarBotonHistorial) {
        this.mostrarBotonHistorial = mostrarBotonHistorial;
    }

    /**
     * @return the mostrarHistorial
     */
    public boolean isMostrarHistorial() {
        return mostrarHistorial;
    }

    /**
     * @param mostrarHistorial the mostrarHistorial to set
     */
    public void setMostrarHistorial(boolean mostrarHistorial) {
        this.mostrarHistorial = mostrarHistorial;
    }

    /**
     * @return the historialRiesgoInstitcional
     */
    public List<HistorialRiesgoDto> getHistorialRiesgoInstitcional() {
        return historialRiesgoInstitcional;
    }

    /**
     * @param historialRiesgoInstitcional the historialRiesgoInstitcional to set
     */
    public void setHistorialRiesgoInstitcional(List<HistorialRiesgoDto> historialRiesgoInstitcional) {
        this.historialRiesgoInstitcional = historialRiesgoInstitcional;
    }

    /**
     * @return the historialDCFRiesgoInstitcional
     */
    public List<HistorialRiesgoDto> getHistorialDCFRiesgoInstitcional() {
        return historialDCFRiesgoInstitcional;
    }

    /**
     * @param historialDCFRiesgoInstitcional the historialDCFRiesgoInstitcional
     * to set
     */
    public void setHistorialDCFRiesgoInstitcional(List<HistorialRiesgoDto> historialDCFRiesgoInstitcional) {
        this.historialDCFRiesgoInstitcional = historialDCFRiesgoInstitcional;
    }

    public BigDecimal getSumatoriaValorCIF() {
        return sumatoriaValorCIF;
    }

    public void setSumatoriaValorCIF(BigDecimal sumatoriaValorCIF) {
        this.sumatoriaValorCIF = sumatoriaValorCIF;
    }

    public BigDecimal getSumatoriaValorFob() {
        return sumatoriaValorFob;
    }

    public void setSumatoriaValorFob(BigDecimal sumatoriaValorFob) {
        this.sumatoriaValorFob = sumatoriaValorFob;
    }

    public BigDecimal getSumatoriaDeclaraciones() {
        return sumatoriaDeclaraciones;
    }

    public void setSumatoriaDeclaraciones(BigDecimal sumatoriaDeclaraciones) {
        this.sumatoriaDeclaraciones = sumatoriaDeclaraciones;
    }

    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PerfilContribuyenteUI.class);

    private int anioActual = LocalDate.now().getYear();
    private PerfilContribuyenteDto datos = new PerfilContribuyenteDto();
    private ArrayList<PerfilContribuyenteDto> ListPerfil = new ArrayList();
    private List<AfiliacionDto> listAfiliaciones;
    private List<String> listAcreditaciones;
    private List<ApoderadoDto> listApoderados;
    private List<TotalDto> listSolicitudesDCF;
    private List<TotalDto> listOmisos;
    private List<TotalDto> listOmisosInactivos;
    private List<TotalDto> listLibrosAutorizados;
    private List<TotalDto> listOtrosLibrosAutorizados;
    private List<TotalDto> listFacturasAutorizadas;
    private TotalDto vehiculosActivos;
    private TotalDto cargaTributaria;
    private List<TotalDto> listAuditorias;
    private List<TotalDto> listExpedientesJuridico;
    private List<TotalDto> listExpedientesTributa;
    private List<TotalDto> listExpedientesAdm;
    private List<HistorialRiesgoDto> historialRiesgoInstitcional;
    private List<HistorialRiesgoDto> historialDCFRiesgoInstitcional;
    private boolean mostrarHistorial;
    private boolean mostrarBotonHistorial = true;
    private boolean mostrarHistorialDFC;
    private boolean mostrarBotonHistorialDCF = true;
    private String pNitContribuyente;

    private BigDecimal sumatoriaValorCIF = new BigDecimal(BigInteger.ZERO);
    private BigDecimal sumatoriaValorFob = new BigDecimal(BigInteger.ZERO);
    private BigDecimal sumatoriaDeclaraciones = new BigDecimal(BigInteger.ZERO);

    private int tamanioPagina = 5;
    private long totalPaginas = 0;
    private Integer paginaActual = 0;
    
    private int tamanioPaginaEstab = 5;
    private long totalPaginasEstab = 0;
    private Integer paginaActualEstab = 0;
    private Boolean bloquearMostrarMasEstab = false;
    
    List<GeneralVerificacionesDto> data = new ArrayList<>();
    private Boolean bloquearMostrarMas = false;
    private String pnit = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    /*Variable para exportar a excel*/
    private PerfilContribuyenteDto listaPerfil = new PerfilContribuyenteDto();
    /*variables para el componente verificaciones en campo*/
    private List<GeneralVerificacionesDto> listVerificaciones = new ArrayList<>();
    /*variables para las afiliaciones del contribuyente en la nube*/
    private List<ImpuestosDto> listaAfiliacionesNube = new ArrayList<>();
    /*variables para el componente Tipologias de evasion*/
    private List<TipologiasEvasionAnualDto> listTipologiasAnual = new ArrayList<>();
    /*variables para el componente Verificaciones en campo*/
    private List<PartesRelacionadasDto> listContadorRelacionamientos = new ArrayList<>();
    private List<PartesRelacionadasDto> listRepresentanteRelacionamientos = new ArrayList<>();
    private List<PartesRelacionadasDto> listNotarioRelacionamientos = new ArrayList<>();
    /*variables para el componente Establecimientos*/
    private List<InfoEstablecimientosDto> listEstablecimientos = new ArrayList<>();
    /*variables para el componente vehiculos activos segun decreto 70-94*/
    private List<ConteoVehiculosActivosInactivosDto> listVehiculosAnio;
    /*variables para los componentes estado de establecimientos*/
    private List<InfoConteoEstablecimientosEstadoDto> listEstablecimientosActivos = new ArrayList<>();
    private List<InfoConteoEstablecimientosEstadoDto> listEstablecimientosInactivos = new ArrayList<>();
    /*variable para el total de establecimientos activos e inactivos*/
    private int totalEstablecimientos = 0;
    /*variable para el modulo de indicadores*/
    private List<IndicadoresCreditoDebitoDto> listaIndicadoresDebitoCredito;
    private List<IndicadoresGastosGeneralesDto> listaIndicadoresGastosGenerales;
    private List<IndicadoresRentabilidadDto> listaIndicadoresRentabilidad;
    private List<IndicadoresCobProveedoresDto> listaIndicadoresProveedores;
    private List<ConsultaCantidadDeclaracionesAduanerasConAnioDto> obtenerConteoDeclaracionesAduanas;
   
    private List<ConveniosActivosDto> listConveniosActivos;
    
    private List<InfoEstablecimientosDto> listEstablecimientosPagineo = new ArrayList<>();
    private PequenoContribuyenteEncabezadoDto encabezado = new PequenoContribuyenteEncabezadoDto();
   
    
   private List<CamEspecialesContriDto> listaCamposEspeciales = new ArrayList<>();


    @PostConstruct
    public void inicializar() {
        try {
            datos.setNit(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit"));

            if (datos.getNit() == null || datos.getNit().isEmpty()) {
                errorMsg("No se ha recibido el par\u00e1metro esperado");
                LOG.error("No se ha recibido el parametro esperado");
            } else {
                datos = generalSrvImpl.findPerfilContribuyenteByNit(datos.getNit());
                encabezado = generalSrvImpl.datosPerfilContribuyente(datos.getNit());
                listEstablecimientos = Arrays.asList(this.ConsumoServicios.obtenerEstablecimientos(datos.getNit()));
                listaCamposEspeciales = Arrays.asList(this.ConsumoServicios.obtenerCaracteristicasEspeciales(datos.getNit()));
                
                totalEstablecimientos = listEstablecimientos.size();
                generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(4, datos.getNit(), sesion.getUsuarioDto().getNit(), sesion.getIpCliente()));
                setHistorialRiesgoInstitcional(generalSrvImpl.findHistorialRiesgoInstitucional(datos.getNit()));
                //System.out.println("HISTORIAL DE RIESGO INTITUCIONAL: "+historialRiesgoInstitcional.size());
                setHistorialDCFRiesgoInstitcional(generalSrvImpl.findHistorialDCFRiesgoInstitucional(datos.getNit()));
                //  System.out.println("HISOTIRAL DE RIESGO DCF INTITUCIONAL: "+historialDCFRiesgoInstitcional.size());
                pNitContribuyente = datos.getNit();

                ListPerfil.add(datos);

            }

        } catch (Exception e) {
            errorMsg("Error al cargar el perfil del contribuyente");
            LOG.error("Error al cargar el perfil del contribuyente", e);
        }

    }

    public void onTabChange(TabChangeEvent pEvent) {
        try {
            switch (pEvent.getTab().getId()) {
                case "tab1":
                    listaAfiliacionesNube = listaAfiliacionesNube.isEmpty() ? Arrays.asList(this.ConsumoServicios.obtenerAfiliacionesRtu(datos.getNit())) : listaAfiliacionesNube;
                    if (listaAfiliacionesNube != null && !listaAfiliacionesNube.isEmpty()) {
                        for (int i = 0; i < listaAfiliacionesNube.size(); i++) {
                            if (listaAfiliacionesNube.get(i).getCodigoImpuesto() == 19) {
                                listaAfiliacionesNube.get(i).setMorosidad(this.generalSrvImpl.obtenerImpuestoISCV(datos.getNit()).getImpuesto());
                            }

                        }
                    }
                    break;
                case "tab2":
                    listAcreditaciones = listAcreditaciones == null ? generalSrvImpl.findAcreditamientosByNit(datos.getNit()) : listAcreditaciones;
                    break;
                case "tab3":
                    listApoderados = listApoderados == null ? generalSrvImpl.findApoderadosByRepresentante(datos.getNit()) : listApoderados;
                    break;
                case "tab4":
                    listSolicitudesDCF = listSolicitudesDCF == null ? generalSrvImpl.findSolicitudesDevolucionCF(datos.getNit()) : listSolicitudesDCF;
                    break;
                case "tab5":
                    listOmisos = listOmisos == null ? generalSrvImpl.findOmisos(datos.getNit()) : listOmisos;
                    break;
                case "tab6":
                    listLibrosAutorizados = listLibrosAutorizados == null ? generalSrvImpl.findLibrosAutorizados(datos.getNit()) : listLibrosAutorizados;
                    break;
                case "tab7":
                    listOtrosLibrosAutorizados = listOtrosLibrosAutorizados == null ? generalSrvImpl.findOtrosLibrosAutorizados(datos.getNit()) : listOtrosLibrosAutorizados;
                    break;
                case "tab8":
                    listFacturasAutorizadas = listFacturasAutorizadas == null ? generalSrvImpl.findFacturasAutorizadas(datos.getNit()) : listFacturasAutorizadas;
                    break;
                case "tab9":
                    List<Integer> anioTab9 = new ArrayList();

                    int yearTab9 = Calendar.getInstance().get(Calendar.YEAR);
                    int restaAniosTab9 = yearTab9;

                    for (int i = yearTab9 - 5; i < yearTab9; i++) {
                        anioTab9.add(restaAniosTab9--);
                    }

                    listVehiculosAnio = listVehiculosAnio == null || listVehiculosAnio.isEmpty() ? generalSrvImpl.obtenerConteoVehiculosActivInactiv(anioTab9, datos.getNit()) : listVehiculosAnio;

                    break;
                case "tab10":
                    cargaTributaria = cargaTributaria == null ? generalSrvImpl.findCargaTributaria(datos.getNit()) : cargaTributaria;
                    break;
                case "tab11":
                    listAuditorias = listAuditorias == null ? generalSrvImpl.findAuditorias(datos.getNit()) : listAuditorias;
                    break;
                case "tab12":
                    listExpedientesJuridico = listExpedientesJuridico == null ? generalSrvImpl.findExpedientesJuridico(datos.getNit()) : listExpedientesJuridico;
                    break;
                case "tab13":
                    listExpedientesTributa = listExpedientesTributa == null ? generalSrvImpl.findExpedientesTributa(datos.getNit()) : listExpedientesTributa;
                    break;
                case "tab14":
                    listExpedientesAdm = listExpedientesAdm == null ? generalSrvImpl.findExpedientesAdm(datos.getNit()) : listExpedientesAdm;
                    break;

                case "tab15":
                    listOmisosInactivos = listOmisosInactivos == null ? generalSrvImpl.findOmisosinactivos(datos.getNit()) : listOmisosInactivos;
                    break;

                case "tab16":
                    this.pagineoListaVerificacion();
                    break;
                case "tab17":
                    listEstablecimientosActivos = listEstablecimientosActivos.isEmpty() ? Arrays.asList(this.ConsumoServicios.obtenerEstablecimientoActivoInactivo(datos.getNit(), 1020)) : listEstablecimientosActivos;
                    listEstablecimientosInactivos = listEstablecimientosInactivos.isEmpty() ? Arrays.asList(this.ConsumoServicios.obtenerEstablecimientoActivoInactivo(datos.getNit(), 1022)) : listEstablecimientosInactivos;
                    break;

                case "tab18":
                    listContadorRelacionamientos = listContadorRelacionamientos.isEmpty() ? Arrays.asList(this.ConsumoServicios.obtenerPartesRelacionadas(datos.getNit(), 1)) : listContadorRelacionamientos;
                    listRepresentanteRelacionamientos = listRepresentanteRelacionamientos.isEmpty() ? Arrays.asList(this.ConsumoServicios.obtenerPartesRelacionadas(datos.getNit(), 2)) : listRepresentanteRelacionamientos;
                    listNotarioRelacionamientos = listNotarioRelacionamientos.isEmpty() ? Arrays.asList(this.ConsumoServicios.obtenerPartesRelacionadas(datos.getNit(), 3)) : listNotarioRelacionamientos;
                    break;
                case "tab19":
                    List<Integer> anio = new ArrayList();

                    int year = Calendar.getInstance().get(Calendar.YEAR);
                    int restaAnios = year;

                    for (int i = year - 5; i < year; i++) {
                        anio.add(restaAnios--);
                    }
                    listTipologiasAnual = listTipologiasAnual.isEmpty() ? generalSrvImpl.obtenerTipologiasEvasionAnualByNitFiveYears(datos.getNit(), anio) : listTipologiasAnual;
                    break;

                case "tab20":
                    listaIndicadoresDebitoCredito = listaIndicadoresDebitoCredito == null ? generalSrvImpl.obtenerIndicadoresDebitoCredito(datos.getNit()) : listaIndicadoresDebitoCredito;
                    listaIndicadoresGastosGenerales = listaIndicadoresGastosGenerales == null ? generalSrvImpl.obtenerIndicadoresGatosGenerales(datos.getNit()) : listaIndicadoresGastosGenerales;
                    listaIndicadoresRentabilidad = listaIndicadoresRentabilidad == null ? generalSrvImpl.obtenerIndicadoresRentabilidad(datos.getNit()) : listaIndicadoresRentabilidad;
                    listaIndicadoresProveedores = listaIndicadoresProveedores == null ? generalSrvImpl.obtenerIndicadoresProveedores(datos.getNit()) : listaIndicadoresProveedores;
                    break;

                case "tab21":
                    List<Integer> anios = new ArrayList<>();
                    sumatoriaValorCIF = new BigDecimal(BigInteger.ZERO);
                    sumatoriaValorFob = new BigDecimal(BigInteger.ZERO);
                    sumatoriaDeclaraciones = new BigDecimal(BigInteger.ZERO);

                    int anioInicioDeclaraciones = Year.now().getValue();
                    for (int i = 0; i < 5; i++) {
                        anios.add(anioInicioDeclaraciones - i);
                    }

                    obtenerConteoDeclaracionesAduanas = obtenerConteoDeclaracionesAduanas == null ? this.generalSrvImpl.obtenerConteoDeclaracionesAduanas(anios, datos.getNit()) : obtenerConteoDeclaracionesAduanas;
                    if (obtenerConteoDeclaracionesAduanas != null) {
                        for (ConsultaCantidadDeclaracionesAduanerasConAnioDto dataShow : obtenerConteoDeclaracionesAduanas) {
                            sumatoriaDeclaraciones = sumatoriaDeclaraciones.add(dataShow.getCantidad_declaraciones());
                            System.out.println("dataShow.getCif_suma() " + dataShow.getCif_suma());
                            sumatoriaValorCIF = sumatoriaValorCIF.add(new BigDecimal(dataShow.getCif_suma().replaceAll("\\s*,*", "")));
                            sumatoriaValorFob = sumatoriaValorFob.add(new BigDecimal(dataShow.getFob_suma().replaceAll("\\s*,*", "")));
                        }
                    }
                    break;
                    
                   case "tab22":
                   listConveniosActivos = listConveniosActivos == null ? generalSrvImpl.conveniosActivos(datos.getNit()) : listConveniosActivos;  
                     break;
            }
        } catch (Exception e) {
            LOG.error("Error al cargar esta seccion de informacion", e);
            errorMsg("Error al cargar esta secci\u00f3n de informaci\u00f3n");
        }
    }

    private void bloquearBotonMostrarMas() {
        if (paginaActual == Math.toIntExact(totalPaginas)) {
            //bloquear mostrar mas
            this.setBloquearMostrarMas(true);
            RequestContext.getCurrentInstance().update("formContent:commandMostrarMasTab16");
            
        }
    }

    private void pagineoListaVerificacion() {

        data = generalSrvImpl.VerificacionesEnCampo(datos.getNit());
        this.listVerificaciones.clear();
        this.setBloquearMostrarMas(false);
        long totalCount = data.size();
        totalPaginas = totalCount < tamanioPagina ? 1 : (long) Math.ceil((double) totalCount / tamanioPagina);
        listVerificaciones.addAll(getPagineoLimite(data, 1, tamanioPagina));
        paginaActual = 1;
        this.bloquearBotonMostrarMas();

    }
    
    
  
    public void mostrarMas() {
        paginaActual++;
        this.bloquearBotonMostrarMas();
        listVerificaciones.addAll(getPagineoLimite(data, paginaActual, tamanioPagina));
        RequestContext.getCurrentInstance().update("formContent:tab16");
        this.bloquearBotonMostrarMas();
    }

    public List getPagineoLimite(List dataList, int pageNum, int pageSize) {
        if (CollectionUtils.isEmpty(dataList)) {
            return dataList;
        }
        List resultList = new ArrayList();
        //What is the number of items in all datalist data
        int currIdx = pageNum > 1 ? (pageNum - 1) * pageSize : 0;
        for (int i = 0; i < pageSize && i < dataList.size() - currIdx; i++) {
            resultList.add(dataList.get(currIdx + i));
        }
        return resultList;
    }
    
    
    private void pagineoEstablecimientosContribuyente() {

 

        try {
            listEstablecimientos = Arrays.asList(this.ConsumoServicios.obtenerEstablecimientos(datos.getNit()));
        } catch (HandledException ex) {
            Logger.getLogger(PerfilContribuyenteUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.listEstablecimientosPagineo.clear();
        this.setBloquearMostrarMasEstab(false);
        long totalCount = listEstablecimientos.size();
        totalPaginasEstab = totalCount < tamanioPaginaEstab ? 1 : (long) Math.ceil((double) totalCount / tamanioPaginaEstab);
        listEstablecimientosPagineo.addAll(getPagineoLimite(listEstablecimientos, 1, tamanioPaginaEstab));
        paginaActualEstab = 1;
        this.bloquearBotonMostrarMasEstab();

 

    }
    
    private void bloquearBotonMostrarMasEstab() {
        if (paginaActualEstab == Math.toIntExact(totalPaginasEstab)) {
            //bloquear mostrar mas
            this.setBloquearMostrarMasEstab(true);
            RequestContext.getCurrentInstance().update("formContent:commandMostrarMasTab16");
        }
    }


     public void mostrarMasEstab() {
        paginaActualEstab++;
        this.bloquearBotonMostrarMasEstab();
        listVerificaciones.addAll(getPagineoLimite(listEstablecimientos, paginaActualEstab, tamanioPaginaEstab));
        RequestContext.getCurrentInstance().update("formContent:tab16");
        this.bloquearBotonMostrarMasEstab();
    }
    
    

    public void mostrarRiesgoGeneral() {
        setMostrarHistorial(true);
        setMostrarBotonHistorial(false);
    }

    public void ocultarRiesgoGeneral() {
        setMostrarHistorial(false);
        setMostrarBotonHistorial(true);
    }

    public void mostrarRiesgoGeneralDCF() {
        setMostrarHistorialDFC(true);
        setMostrarBotonHistorialDCF(false);
    }

    public void ocultarRiesgoGeneralDCF() {
        setMostrarHistorialDFC(false);
        setMostrarBotonHistorialDCF(true);
    }

    public ByteArrayOutputStream createDocument() {
        Document document = new Document();
        ByteArrayOutputStream bite = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, bite);
            document.open();
            Font fontT1 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
            Font fontH1 = new Font(Font.FontFamily.HELVETICA, 8);
            Font fontName = new Font(Font.FontFamily.HELVETICA, 8);

            PdfPTable table = new PdfPTable(2);
            table.setTotalWidth(new float[]{175, 300});
            table.setLockedWidth(true);

            PdfPCell cell1 = new PdfPCell(new Phrase("Nombre", fontT1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBackgroundColor(BaseColor.WHITE);
            cell1.setFixedHeight(20f);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Phrase(datos.getNombre(), fontH1));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setBackgroundColor(BaseColor.WHITE);
            cell2.setFixedHeight(20f);
            table.addCell(cell2);

            PdfPCell cell3 = new PdfPCell(new Phrase("Nit", fontT1));
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setBackgroundColor(BaseColor.WHITE);
            cell3.setFixedHeight(20f);
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Phrase(datos.getNit(), fontH1));
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell4.setBackgroundColor(BaseColor.WHITE);
            cell4.setFixedHeight(20f);
            table.addCell(cell4);

            PdfPCell cell5 = new PdfPCell(new Phrase("Personería jurídica", fontT1));
            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setBackgroundColor(BaseColor.WHITE);
            cell5.setFixedHeight(20f);
            table.addCell(cell5);

            PdfPCell cell6 = new PdfPCell(new Phrase(datos.getPersoneriaJuridica(), fontH1));
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6.setBackgroundColor(BaseColor.WHITE);
            cell6.setFixedHeight(20f);
            table.addCell(cell6);

            PdfPCell cell7 = new PdfPCell(new Phrase("Clasificación", fontT1));
            cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell7.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell7);

            PdfPCell cell8 = new PdfPCell(new Phrase(datos.getClasificacion(), fontH1));
            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell8.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell8);

            PdfPCell cell9 = new PdfPCell(new Phrase("Actividad económica", fontT1));
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell9.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell9);

            PdfPCell cell10 = new PdfPCell(new Phrase(datos.getActividadEconomica(), fontH1));
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell10.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell10);

            PdfPCell cell11 = new PdfPCell(new Phrase("Estado", fontT1));
            cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell11.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell11);

            PdfPCell cell12 = new PdfPCell(new Phrase(datos.getEstado(), fontH1));
            cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell12.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell12);

            PdfPCell cell13 = new PdfPCell(new Phrase("Exento", fontT1));
            cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell13.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell13);

            PdfPCell cell14 = new PdfPCell(new Phrase(datos.getExento(), fontH1));
            cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell14.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell14);

            PdfPCell cell15 = new PdfPCell(new Phrase("Fec. Nac./Const.", fontT1));
            cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell15.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell15.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell15);

            PdfPCell cell16 = new PdfPCell(new Phrase(datos.getFechaNacConst(), fontH1));
            cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell16.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell16.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell16);

            PdfPCell cell17 = new PdfPCell(new Phrase("Fecha de inscripción", fontT1));
            cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell17.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell17.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell17);

            PdfPCell celll8 = new PdfPCell(new Phrase(datos.getFechaInscripcion(), fontH1));
            celll8.setHorizontalAlignment(Element.ALIGN_LEFT);
            celll8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celll8.setBackgroundColor(BaseColor.WHITE);
            table.addCell(celll8);

            PdfPCell cell19 = new PdfPCell(new Phrase("Domicilio fiscal", fontT1));
            cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell19.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell19.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell19);

            PdfPCell cell20 = new PdfPCell(new Phrase(datos.getDomicilioFiscal(), fontH1));
            cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell20.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell20.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell20);

            PdfPCell cell21 = new PdfPCell(new Phrase("Teléfono", fontT1));
            cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell21.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell21);

            PdfPCell cell22 = new PdfPCell(new Phrase(datos.getTelefono(), fontH1));
            cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell22.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell22.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell22);

            PdfPCell cell23 = new PdfPCell(new Phrase("Representante legal principal", fontT1));
            cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell23.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell23.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell23);

            PdfPCell cell24 = new PdfPCell(new Phrase(datos.getRepresentanteLegal(), fontH1));
            cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell24.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell24.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell24);

            PdfPCell cell25 = new PdfPCell(new Phrase("Contador actual registrado", fontT1));
            cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell25.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell25.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell25);

            PdfPCell cell26 = new PdfPCell(new Phrase(datos.getContadorActual(), fontH1));
            cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell26.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell26.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell26);

            PdfPCell cell27 = new PdfPCell(new Phrase("Cantidad de negocios", fontT1));
            cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell27.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell27.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell27);

            PdfPCell cell28 = new PdfPCell(new Phrase(datos.getNegocios(), fontH1));
            cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell28.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell28.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell28);

            PdfPCell cell29 = new PdfPCell(new Phrase("Decreto 29-89", fontT1));
            cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell29.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell29.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell29);

            PdfPCell cell30 = new PdfPCell(new Phrase(datos.getDecreto2989(), fontH1));
            cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell30.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell30.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell30);

            PdfPCell cell31 = new PdfPCell(new Phrase("Agente de retención", fontT1));
            cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell31.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell31);

            PdfPCell cell32 = new PdfPCell(new Phrase(datos.getAgenteRetencion(), fontH1));
            cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell32.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell32.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell32);

            PdfPCell cell33 = new PdfPCell(new Phrase("Importador registrado", fontT1));
            cell33.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell33.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell33.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell33);

            PdfPCell cell34 = new PdfPCell(new Phrase(datos.getImportadorRegistrado(), fontH1));
            cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell34.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell34.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell34);

            PdfPCell cell35 = new PdfPCell(new Phrase("Usuario zona franca", fontT1));
            cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell35.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell35.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell35);

            PdfPCell cell36 = new PdfPCell(new Phrase(datos.getUsuarioZonaFranca(), fontH1));
            cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell36.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell36.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell36);

            PdfPCell cell37 = new PdfPCell(new Phrase("Agente aduanero", fontT1));
            cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell37.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell37.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell37);

            PdfPCell cell38 = new PdfPCell(new Phrase(datos.getUsuarioZonaFranca(), fontH1));
            cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell38.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell38.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell38);

            PdfPCell cell39 = new PdfPCell(new Phrase("Proveedor del estado", fontT1));
            cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell39.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell39.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell39);

            PdfPCell cell40 = new PdfPCell(new Phrase(datos.getProveedorEstado(), fontH1));
            cell40.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell40.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell40.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell40);

            PdfPCell cell41 = new PdfPCell(new Phrase("Registro fiscal de imprentas", fontT1));
            cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell41.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell41.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell41);

            PdfPCell cell42 = new PdfPCell(new Phrase(datos.getRegistroImprentas(), fontH1));
            cell42.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell42.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell42.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell42);

            PdfPCell cell43 = new PdfPCell(new Phrase("Registro fiscal de vehículos", fontT1));
            cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell43.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell43.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell43);

            PdfPCell cell44 = new PdfPCell(new Phrase(datos.getRegistroVehiculos(), fontH1));
            cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell44.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell44.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell44);

            PdfPCell cell45 = new PdfPCell(new Phrase("Exportador", fontT1));
            cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell45.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell45.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell45);

            PdfPCell cell46 = new PdfPCell(new Phrase(datos.getExportador(), fontH1));
            cell46.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell46.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell46.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell46);

            PdfPCell cell47 = new PdfPCell(new Phrase("Omiso", fontT1));
            cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell47.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell47.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell47);

            PdfPCell cell48 = new PdfPCell(new Phrase(datos.getOmiso(), fontH1));
            cell48.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell48.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell48.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell48);

            PdfPCell cell49 = new PdfPCell(new Phrase("Cuenta con usuario de Agencia Virtual", fontT1));
            cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell49.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell49.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell49);

            PdfPCell cell50 = new PdfPCell(new Phrase(datos.getAgenciaVirtual(), fontH1));
            cell50.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell50.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell50.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell50);

            PdfPCell cell51 = new PdfPCell(new Phrase("Emisor factura electrónica", fontT1));
            cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell51.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell51.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell51);

            PdfPCell cell52 = new PdfPCell(new Phrase());
            cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell52.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell52.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell52);
            
            PdfPCell cell53 = new PdfPCell(new Phrase("Email", fontT1));
            cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell53.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell53.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell53);

            PdfPCell cell54 = new PdfPCell(new Phrase());
            cell54.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell54.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell54.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell54);
            
            PdfPCell cell55 = new PdfPCell(new Phrase("Email notificaciones", fontT1));
            cell55.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell55.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell55.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell55);

            PdfPCell cell56 = new PdfPCell(new Phrase());
            cell56.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell56.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell56.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell56);
            
            PdfPCell cell57 = new PdfPCell(new Phrase("Plan operativo", fontT1));
            cell57.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell57.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell57.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell57);

            PdfPCell cell58 = new PdfPCell(new Phrase());
            cell58.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell58.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell58.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell58);
            
            PdfPCell cell59 = new PdfPCell(new Phrase("Total direcciones comerciales", fontT1));
            cell59.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell59.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell59.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell59);

            PdfPCell cell60 =new PdfPCell(new Phrase());
            cell60.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell60.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell60.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell60);
            
            table.setWidthPercentage(100);
            document.add(table);

            PdfPTable table1 = new PdfPTable(2);
            table1.setTotalWidth(new float[]{100, 300});
            table1.setLockedWidth(true);

            PdfPCell cell78 = new PdfPCell(new Phrase("  "));
            cell78.setBorder(PdfPCell.NO_BORDER);
            table1.addCell(cell78);

            PdfPCell cell79 = new PdfPCell(new Phrase("  "));
            cell79.setBorder(PdfPCell.NO_BORDER);
            table1.addCell(cell79);

            table1.setWidthPercentage(100);
            document.add(table1);

            PdfPTable table2 = new PdfPTable(2);
            table2.setTotalWidth(new float[]{175, 300});
            table2.setLockedWidth(true);

            PdfPCell cell72 = new PdfPCell(new Phrase("Nombre", fontH1));
            cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell72.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell72.setBackgroundColor(BaseColor.WHITE);
            table2.addCell(cell72);

            PdfPCell cell73 = new PdfPCell(new Phrase("Dirección", fontH1));
            cell73.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell73.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell73.setBackgroundColor(BaseColor.WHITE);
            table2.addCell(cell73);

            table2.setWidthPercentage(100);
            document.add(table2);

            PdfPTable table3 = new PdfPTable(2);
            table3.setTotalWidth(new float[]{175, 300});
            table3.setLockedWidth(true);

            Iterator<InfoEstablecimientosDto> establecimientos = this.listEstablecimientos.iterator();

            int i = 0;

            while (establecimientos.hasNext()) {
                InfoEstablecimientosDto establecimiento = establecimientos.next();

                PdfPCell cell70 = new PdfPCell(new Phrase(establecimiento.getNombreComercial(), fontT1));
                cell70.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell70.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell70.setBackgroundColor(BaseColor.WHITE);
                table3.addCell(cell70);

                PdfPCell cell71 = new PdfPCell(new Phrase(establecimiento.getDireccionEstablecimiento(), fontT1));
                cell71.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell71.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell71.setBackgroundColor(BaseColor.WHITE);
                table3.addCell(cell71);
                i++;

            }

            table3.setWidthPercentage(100);
            document.add(table3);

        } catch (DocumentException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        document.close();
        System.out.println("se cerro el documento");

        return bite;
    }

    public void descargarPDF() {
        ByteArrayOutputStream doc = this.createDocument();
        FacesContext context = FacesContext.getCurrentInstance();
        Object response = context.getExternalContext().getResponse();

        if (response instanceof HttpServletResponse) {
            HttpServletResponse hsr = (HttpServletResponse) response;
            hsr.setContentType("application/pdf");
            hsr.setHeader("Content-disposition", "attachment;filename=\"perfilContribuyente.pdf\"");
            hsr.setContentLength(doc.size());
            try {
                ServletOutputStream out = hsr.getOutputStream();
                doc.writeTo(out);
                out.flush();
            } catch (IOException ex) {
                System.out.println("Error:  " + ex.getMessage());
            }
            context.responseComplete();
        }
    }

    public ArrayList<PerfilContribuyenteDto> getListPerfil() {
        return ListPerfil;
    }

    public void setListPerfil(ArrayList<PerfilContribuyenteDto> ListPerfil) {
        this.ListPerfil = ListPerfil;
    }

    public PerfilContribuyenteDto getDatos() {
        return datos;
    }

    public void setDatos(PerfilContribuyenteDto datos) {
        this.datos = datos;
    }

    public List<AfiliacionDto> getListAfiliaciones() {
        return listAfiliaciones;
    }

    public void setListAfiliaciones(List<AfiliacionDto> listAfiliaciones) {
        this.listAfiliaciones = listAfiliaciones;
    }

    public List<TotalDto> getListSolicitudesDCF() {
        return listSolicitudesDCF;
    }

    public void setListSolicitudesDCF(List<TotalDto> listSolicitudesDCF) {
        this.listSolicitudesDCF = listSolicitudesDCF;
    }

    public int getAnioActual() {
        return anioActual;
    }

    public void setAnioActual(int anioActual) {
        this.anioActual = anioActual;
    }

    public List<TotalDto> getListOmisos() {
        return listOmisos;
    }

    public void setListOmisos(List<TotalDto> listOmisos) {
        this.listOmisos = listOmisos;
    }

    public List<TotalDto> getListOmisosInactivos() {
        return listOmisosInactivos;
    }

    public void setListOmisosInactivos(List<TotalDto> listOmisosInactivos) {
        this.listOmisosInactivos = listOmisosInactivos;
    }

    public List<TotalDto> getListLibrosAutorizados() {
        return listLibrosAutorizados;
    }

    public void setListLibrosAutorizados(List<TotalDto> listLibrosAutorizados) {
        this.listLibrosAutorizados = listLibrosAutorizados;
    }

    public List<TotalDto> getListOtrosLibrosAutorizados() {
        return listOtrosLibrosAutorizados;
    }

    public void setListOtrosLibrosAutorizados(List<TotalDto> listOtrosLibrosAutorizados) {
        this.listOtrosLibrosAutorizados = listOtrosLibrosAutorizados;
    }

    public TotalDto getVehiculosActivos() {
        return vehiculosActivos;
    }

    public void setVehiculosActivos(TotalDto vehiculosActivos) {
        this.vehiculosActivos = vehiculosActivos;
    }

    public TotalDto getCargaTributaria() {
        return cargaTributaria;
    }

    public void setCargaTributaria(TotalDto cargaTributaria) {
        this.cargaTributaria = cargaTributaria;
    }

    public List<TotalDto> getListAuditorias() {
        return listAuditorias;
    }

    public void setListAuditorias(List<TotalDto> listAuditorias) {
        this.listAuditorias = listAuditorias;
    }

    public List<TotalDto> getListExpedientesJuridico() {
        return listExpedientesJuridico;
    }

    public void setListExpedientesJuridico(List<TotalDto> listExpedientesJuridico) {
        this.listExpedientesJuridico = listExpedientesJuridico;
    }

    public List<TotalDto> getListExpedientesTributa() {
        return listExpedientesTributa;
    }

    public void setListExpedientesTributa(List<TotalDto> listExpedientesTributa) {
        this.listExpedientesTributa = listExpedientesTributa;
    }

    public List<TotalDto> getListExpedientesAdm() {
        return listExpedientesAdm;
    }

    public void setListExpedientesAdm(List<TotalDto> listExpedientesAdm) {
        this.listExpedientesAdm = listExpedientesAdm;
    }

    public List<TotalDto> getListFacturasAutorizadas() {
        return listFacturasAutorizadas;
    }

    public void setListFacturasAutorizadas(List<TotalDto> listFacturasAutorizadas) {
        this.listFacturasAutorizadas = listFacturasAutorizadas;
    }

    public List<ApoderadoDto> getListApoderados() {
        return listApoderados;
    }

    public void setListApoderados(List<ApoderadoDto> listApoderados) {
        this.listApoderados = listApoderados;
    }

    public List<String> getListAcreditaciones() {
        return listAcreditaciones;
    }

    public void setListAcreditaciones(List<String> listAcreditaciones) {
        this.listAcreditaciones = listAcreditaciones;
    }

    public List<GeneralVerificacionesDto> getListVerificaciones() {
        return listVerificaciones;
    }

    public void setListVerificaciones(List<GeneralVerificacionesDto> listVerificaciones) {
        this.listVerificaciones = listVerificaciones;
    }

    public List<GeneralVerificacionesDto> getData() {
        return data;
    }

    public void setData(List<GeneralVerificacionesDto> data) {
        this.data = data;
    }

    public Boolean getBloquearMostrarMas() {
        return bloquearMostrarMas;
    }

    public void setBloquearMostrarMas(Boolean bloquearMostrarMas) {
        this.bloquearMostrarMas = bloquearMostrarMas;
    }

    public List<ImpuestosDto> getListaAfiliacionesNube() {
        return listaAfiliacionesNube;
    }

    public void setListaAfiliacionesNube(List<ImpuestosDto> listaAfiliacionesNube) {
        this.listaAfiliacionesNube = listaAfiliacionesNube;
    }

    public List<TipologiasEvasionAnualDto> getListTipologiasAnual() {
        return listTipologiasAnual;
    }

    public void setListTipologiasAnual(List<TipologiasEvasionAnualDto> listTipologiasAnual) {
        this.listTipologiasAnual = listTipologiasAnual;
    }

    public List<PartesRelacionadasDto> getListContadorRelacionamientos() {
        return listContadorRelacionamientos;
    }

    public void setListContadorRelacionamientos(List<PartesRelacionadasDto> listContadorRelacionamientos) {
        this.listContadorRelacionamientos = listContadorRelacionamientos;
    }

    public List<PartesRelacionadasDto> getListRepresentanteRelacionamientos() {
        return listRepresentanteRelacionamientos;
    }

    public void setListRepresentanteRelacionamientos(List<PartesRelacionadasDto> listRepresentanteRelacionamientos) {
        this.listRepresentanteRelacionamientos = listRepresentanteRelacionamientos;
    }

    public List<PartesRelacionadasDto> getListNotarioRelacionamientos() {
        return listNotarioRelacionamientos;
    }

    public void setListNotarioRelacionamientos(List<PartesRelacionadasDto> listNotarioRelacionamientos) {
        this.listNotarioRelacionamientos = listNotarioRelacionamientos;
    }

    public List<InfoEstablecimientosDto> getListEstablecimientos() {
        return listEstablecimientos;
    }

    public void setListEstablecimientos(List<InfoEstablecimientosDto> listEstablecimientos) {
        this.listEstablecimientos = listEstablecimientos;
    }

    public List<InfoConteoEstablecimientosEstadoDto> getListEstablecimientosActivos() {
        return listEstablecimientosActivos;
    }

    public void setListEstablecimientosActivos(List<InfoConteoEstablecimientosEstadoDto> listEstablecimientosActivos) {
        this.listEstablecimientosActivos = listEstablecimientosActivos;
    }

    public List<InfoConteoEstablecimientosEstadoDto> getListEstablecimientosInactivos() {
        return listEstablecimientosInactivos;
    }

    public void setListEstablecimientosInactivos(List<InfoConteoEstablecimientosEstadoDto> listEstablecimientosInactivos) {
        this.listEstablecimientosInactivos = listEstablecimientosInactivos;
    }

    public PerfilContribuyenteDto getListaPerfil() {
        return listaPerfil;
    }

    public void setListaPerfil(PerfilContribuyenteDto listaPerfil) {
        this.listaPerfil = listaPerfil;
    }

    public int getTotalEstablecimientos() {
        return totalEstablecimientos;
    }

    public void setTotalEstablecimientos(int totalEstablecimientos) {
        this.totalEstablecimientos = totalEstablecimientos;
    }

    public List<ConteoVehiculosActivosInactivosDto> getListVehiculosAnio() {
        return listVehiculosAnio;
    }

    public void setListVehiculosAnio(List<ConteoVehiculosActivosInactivosDto> listVehiculosAnio) {
        this.listVehiculosAnio = listVehiculosAnio;
    }

    public List<IndicadoresCreditoDebitoDto> getListaIndicadoresDebitoCredito() {
        return listaIndicadoresDebitoCredito;
    }

    public void setListaIndicadoresDebitoCredito(List<IndicadoresCreditoDebitoDto> listaIndicadoresDebitoCredito) {
        this.listaIndicadoresDebitoCredito = listaIndicadoresDebitoCredito;
    }

    public List<IndicadoresGastosGeneralesDto> getListaIndicadoresGastosGenerales() {
        return listaIndicadoresGastosGenerales;
    }

    public void setListaIndicadoresGastosGenerales(List<IndicadoresGastosGeneralesDto> listaIndicadoresGastosGenerales) {
        this.listaIndicadoresGastosGenerales = listaIndicadoresGastosGenerales;
    }

    public List<IndicadoresRentabilidadDto> getListaIndicadoresRentabilidad() {
        return listaIndicadoresRentabilidad;
    }

    public void setListaIndicadoresRentabilidad(List<IndicadoresRentabilidadDto> listaIndicadoresRentabilidad) {
        this.listaIndicadoresRentabilidad = listaIndicadoresRentabilidad;
    }

    public List<IndicadoresCobProveedoresDto> getListaIndicadoresProveedores() {
        return listaIndicadoresProveedores;
    }

    public void setListaIndicadoresProveedores(List<IndicadoresCobProveedoresDto> listaIndicadoresProveedores) {
        this.listaIndicadoresProveedores = listaIndicadoresProveedores;
    }

    public List<ConveniosActivosDto> getListConveniosActivos() {
        return listConveniosActivos;
    }

    public void setListConveniosActivos(List<ConveniosActivosDto> listConveniosActivos) {
        this.listConveniosActivos = listConveniosActivos;
    }

   

    public List<ConsultaCantidadDeclaracionesAduanerasConAnioDto> getObtenerConteoDeclaracionesAduanas() {
        return obtenerConteoDeclaracionesAduanas;
    }

    public void setObtenerConteoDeclaracionesAduanas(List<ConsultaCantidadDeclaracionesAduanerasConAnioDto> obtenerConteoDeclaracionesAduanas) {
        this.obtenerConteoDeclaracionesAduanas = obtenerConteoDeclaracionesAduanas;
    }

    public Boolean getBloquearMostrarMasEstab() {
        return bloquearMostrarMasEstab;
    }

    public void setBloquearMostrarMasEstab(Boolean bloquearMostrarMasEstab) {
        this.bloquearMostrarMasEstab = bloquearMostrarMasEstab;
    }

    public List<InfoEstablecimientosDto> getListEstablecimientosPagineo() {
        return listEstablecimientosPagineo;
    }

    public void setListEstablecimientosPagineo(List<InfoEstablecimientosDto> listEstablecimientosPagineo) {
        this.listEstablecimientosPagineo = listEstablecimientosPagineo;
    }


    public PequenoContribuyenteEncabezadoDto getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(PequenoContribuyenteEncabezadoDto encabezado) {
        this.encabezado = encabezado;
    }

   public List<CamEspecialesContriDto> getListaCamposEspeciales() {
        return listaCamposEspeciales;
    }

    public void setListaCamposEspeciales(List<CamEspecialesContriDto> listaCamposEspeciales) {
        this.listaCamposEspeciales = listaCamposEspeciales;
    }

 
    



    
    public void exportare() throws IOException {
        listaPerfil = this.generalSrvImpl.findPerfilContribuyenteByNit(pnit);

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
        celdaEnc1.setCellValue("Perfil del contribuyente");

        /*---------------------------------------------------*/
        HSSFRow filaEnc2 = hoja.createRow(2);

        HSSFCell celdaEnc3 = filaEnc2.createCell(0);
        celdaEnc3.setCellValue("Personería Jurídica");

        HSSFCell celdaEnc4 = filaEnc2.createCell(1);
        celdaEnc4.setCellValue(listaPerfil.getPersoneriaJuridica());

        /*---------------------------------------------------*/
        HSSFRow filaEnc3 = hoja.createRow(3);

        HSSFCell celdaEnc5 = filaEnc3.createCell(0);
        celdaEnc5.setCellValue("Clasificación");

        HSSFCell celdaEnc6 = filaEnc3.createCell(1);
        celdaEnc6.setCellValue(listaPerfil.getClasificacion());

        /*---------------------------------------------------*/
        HSSFRow filaEnc4 = hoja.createRow(4);

        HSSFCell celdaEnc7 = filaEnc4.createCell(0);
        celdaEnc7.setCellValue("Actividad Económica");

        HSSFCell celdaEnc8 = filaEnc4.createCell(1);
        celdaEnc8.setCellValue(listaPerfil.getActividadEconomica());

        /*---------------------------------------------------*/
        HSSFRow filaEnc5 = hoja.createRow(5);

        HSSFCell celdaEnc9 = filaEnc5.createCell(0);
        celdaEnc9.setCellValue("Estado");

        HSSFCell celdaEnc10 = filaEnc5.createCell(1);
        celdaEnc10.setCellValue(listaPerfil.getEstado());

        /*---------------------------------------------------*/
        HSSFRow filaEnc6 = hoja.createRow(6);

        HSSFCell celdaEnc11 = filaEnc6.createCell(0);
        celdaEnc11.setCellValue("Exento");

        HSSFCell celdaEnc12 = filaEnc6.createCell(1);
        celdaEnc12.setCellValue(listaPerfil.getExento());

        /*---------------------------------------------------*/
        HSSFRow filaEnc7 = hoja.createRow(7);

        HSSFCell celdaEnc13 = filaEnc7.createCell(0);
        celdaEnc13.setCellValue("Fec.Nac/Const");

        HSSFCell celdaEnc14 = filaEnc7.createCell(1);
        celdaEnc14.setCellValue(listaPerfil.getFechaNacConst());

        /*---------------------------------------------------*/
        HSSFRow filaEnc8 = hoja.createRow(8);

        HSSFCell celdaEnc15 = filaEnc8.createCell(0);
        celdaEnc15.setCellValue("Fecha de inscripción");

        HSSFCell celdaEnc16 = filaEnc8.createCell(1);
        celdaEnc16.setCellValue(listaPerfil.getFechaInscripcion());
        /*---------------------------------------------------*/

        HSSFRow filaEnc9 = hoja.createRow(9);

        HSSFCell celdaEnc17 = filaEnc9.createCell(0);
        celdaEnc17.setCellValue("Domicilio Fiscal");

        HSSFCell celdaEnc18 = filaEnc9.createCell(1);
        celdaEnc18.setCellValue(listaPerfil.getDomicilioFiscal());
        /*---------------------------------------------------*/

        HSSFRow filaEnc10 = hoja.createRow(10);

        HSSFCell celdaEnc19 = filaEnc10.createCell(0);
        celdaEnc19.setCellValue("Teléfono");

        HSSFCell celdaEnc20 = filaEnc10.createCell(1);
        celdaEnc20.setCellValue(listaPerfil.getTelefono());
        /*---------------------------------------------------*/
        HSSFRow filaEnc11 = hoja.createRow(11);

        HSSFCell celdaEnc21 = filaEnc11.createCell(0);
        celdaEnc21.setCellValue("Representante legal principal");

        HSSFCell celdaEnc22 = filaEnc11.createCell(1);
        celdaEnc22.setCellValue(listaPerfil.getRepresentanteLegal());
        /*---------------------------------------------------*/

        HSSFRow filaEnc12 = hoja.createRow(12);

        HSSFCell celdaEnc23 = filaEnc12.createCell(0);
        celdaEnc23.setCellValue("Contador Actual Registrado");

        HSSFCell celdaEnc24 = filaEnc12.createCell(1);
        celdaEnc24.setCellValue(listaPerfil.getContadorActual());
        /*---------------------------------------------------*/

        HSSFRow filaEnc13 = hoja.createRow(13);

        HSSFCell celdaEnc25 = filaEnc13.createCell(0);
        celdaEnc25.setCellValue("Cantidad de negocios");

        HSSFCell celdaEnc26 = filaEnc13.createCell(1);
        celdaEnc26.setCellValue(listaPerfil.getNegocios());
        /*---------------------------------------------------*/
        
         HSSFRow filaEnc14 = hoja.createRow(14);

        HSSFCell celdaEnc27 = filaEnc14.createCell(0);
        celdaEnc27.setCellValue("Decreto 29-89");

        HSSFCell celdaEnc28 = filaEnc14.createCell(1);
        celdaEnc28.setCellValue(listaPerfil.getDecreto2989());

        HSSFCell celdaEnc29 = filaEnc14.createCell(4);
        celdaEnc29.setCellValue("Status");

        /* HSSFCell celdaEnc30 = filaEnc14.createCell(5);
        celdaEnc30.setCellValue(listaPerfil.getActividadEconomica());*/
        
        HSSFCell celdaEnc31 = filaEnc14.createCell(8);
        celdaEnc31.setCellValue("Fecha status");

        /*HSSFCell celdaEnc32= filaEnc16.createCell(9);
        celdaEnc32.setCellValue(listaPerfil.getActividadEconomica());*/

 /*---------------------------------------------------*/
          
        HSSFRow filaEnc15 = hoja.createRow(15);

        HSSFCell celdaEnc33 = filaEnc15.createCell(0);
        celdaEnc33.setCellValue("Agente de Retención ");

        HSSFCell celdaEnc34 = filaEnc15.createCell(1);
        celdaEnc34.setCellValue(listaPerfil.getAgenteRetencion());

        HSSFCell celdaEnc35 = filaEnc15.createCell(4);
        celdaEnc35.setCellValue("Status ");

        /* HSSFCell celdaEnc36 = filaEnc14.createCell(5);
        celdaEnc36.setCellValue(listaPerfil.getTelefono());*/
        
        HSSFCell celdaEnc37 = filaEnc15.createCell(8);
        celdaEnc37.setCellValue("Fecha status");

        /*HSSFCell celdaEnc38 = filaEnc14.createCell(9);
        celdaEnc38.setCellValue(listaPerfil.getFechaInscripcion());*/

 /*---------------------------------------------------*/
        HSSFRow filaEnc16 = hoja.createRow(16);

        HSSFCell celdaEnc39 = filaEnc16.createCell(0);
        celdaEnc39.setCellValue("Importador registrado");

        HSSFCell celdaEnc40 = filaEnc16.createCell(1);
        celdaEnc40.setCellValue(listaPerfil.getImportadorRegistrado());

        HSSFCell celdaEnc41 = filaEnc16.createCell(4);
        celdaEnc41.setCellValue("Status ");

        /*HSSFCell celdaEnc42 = filaEnc16.createCell(5);
        celdaEnc42.setCellValue(listaPerfil.getFechaNacConst());*/
        
        HSSFCell celdaEnc43 = filaEnc16.createCell(8);
        celdaEnc43.setCellValue("Fecha status");

        /* HSSFCell celdaEnc44 = filaEnc16.createCell(9);
        celdaEnc44.setCellValue(listaPerfil.getFechaInscripcion());*/



 /*---------------------------------------------------*/
        HSSFRow filaEnc17 = hoja.createRow(17);

        HSSFCell celdaEnc45 = filaEnc17.createCell(0);
        celdaEnc45.setCellValue("Usuario zona franca");

        HSSFCell celdaEnc46 = filaEnc17.createCell(1);
        celdaEnc46.setCellValue(listaPerfil.getUsuarioZonaFranca());

        HSSFCell celdaEnc47 = filaEnc17.createCell(4);
        celdaEnc47.setCellValue("Status");

        /* HSSFCell celdaEnc48 = filaEnc17.createCell(5);
        celdaEnc48.setCellValue(listaPerfil.getActividadEconomica());*/
        
        HSSFCell celdaEnc49 = filaEnc17.createCell(8);
        celdaEnc49.setCellValue("Fecha status");

        /* HSSFCell celdaEnc50 = filaEnc17.createCell(9);
        celdaEnc50.setCellValue(listaPerfil.getFechaInscripcion());*/

 /*---------------------------------------------------*/
        HSSFRow filaEnc18 = hoja.createRow(18);

        HSSFCell celdaEnc51 = filaEnc18.createCell(0);
        celdaEnc51.setCellValue("Agente Aduanero");

        HSSFCell celdaEnc52 = filaEnc18.createCell(1);
        celdaEnc52.setCellValue(listaPerfil.getAgenteAduanero());

        HSSFCell celdaEnc53  = filaEnc18.createCell(4);
        celdaEnc53.setCellValue("Status");
        
       /* HSSFCell celdaEnc54 = filaEnc18.createCell(5);
        celdaEnc54.setCellValue(listaPerfil.getActividadEconomica());*/

        HSSFCell celdaEnc55 = filaEnc18.createCell(8);
        celdaEnc55.setCellValue("Fecha status");

        /*HSSFCell celdaEnc56 = filaEnc18.createCell(9);
        celdaEnc56.setCellValue(listaPerfil.getActividadEconomica());*/

 /*---------------------------------------------------*/
        HSSFRow filaEnc19 = hoja.createRow(19);

        HSSFCell celdaEnc57 = filaEnc19.createCell(0);
        celdaEnc57.setCellValue("Proveedor del estado");

        HSSFCell celdaEnc58 = filaEnc19.createCell(1);
        celdaEnc58.setCellValue(listaPerfil.getProveedorEstado());

        HSSFCell celdaEnc59 = filaEnc19.createCell(4);
        celdaEnc59.setCellValue("Status");

        /* HSSFCell celdaEnc60 = filaEnc20.createCell(5);
        celdaEnc60.setCellValue(listaPerfil.getActividadEconomica());*/
        
        HSSFCell celdaEnc61 = filaEnc19.createCell(8);
        celdaEnc61.setCellValue("Fecha status");

        /* HSSFCell celdaEnc62 = filaEnc19.createCell(9);
        celdaEnc62.setCellValue();*/

 /*---------------------------------------------------*/
        HSSFRow filaEnc20 = hoja.createRow(20);

        HSSFCell celdaEnc63 = filaEnc20.createCell(0);
        celdaEnc63.setCellValue("Registro Fiscal de imprentas");

        HSSFCell celdaEnc64 = filaEnc20.createCell(1);
        celdaEnc64.setCellValue(listaPerfil.getRegistroImprentas());

        HSSFCell celdaEnc65 = filaEnc20.createCell(4);
        celdaEnc65.setCellValue("Status");

        /*HSSFCell celdaEnc66 = filaEnc20.createCell(5);
        celdaEnc65.setCellValue(listaPerfil.getEstado());*/
        
        HSSFCell celdaEnc67 = filaEnc20.createCell(8);
        celdaEnc67.setCellValue("Fecha status");

        /* HSSFCell celdaEnc68 = filaEnc20.createCell(9);
        celdaEnc68.setCellValue(listaPerfil.getFechaNacConst());*/

 /*---------------------------------------------------*/
        HSSFRow filaEnc21 = hoja.createRow(21);

        HSSFCell celdaEnc69 = filaEnc21.createCell(0);
        celdaEnc69.setCellValue("Registro Fiscal de Vehículos");

        HSSFCell celdaEnc70 = filaEnc21.createCell(1);
        celdaEnc70.setCellValue(listaPerfil.getRegistroVehiculos());

        HSSFCell celdaEnc71 = filaEnc21.createCell(4);
        celdaEnc71.setCellValue("Status");

        /*HSSFCell celdaEnc72 = filaEnc21.createCell(5);
        celdaEnc72.setCellValue(listaPerfil.getActividadEconomica());*/
        
        HSSFCell celdaEnc73 = filaEnc21.createCell(8);
        celdaEnc73.setCellValue("Fecha status");

        /* HSSFCell celdaEnc74 = filaEnc21.createCell(9);
        celdaEnc74.setCellValue(listaPerfil.getActividadEconomica());*/

 /*---------------------------------------------------*/
        HSSFRow filaEnc22 = hoja.createRow(22);

        HSSFCell celdaEnc75 = filaEnc22.createCell(0);
        celdaEnc75.setCellValue("Exportador");

        HSSFCell celdaEnc76 = filaEnc22.createCell(1);
        celdaEnc76.setCellValue(listaPerfil.getExportador());

        HSSFCell celdaEnc77 = filaEnc22.createCell(4);
        celdaEnc77.setCellValue("Status");

        /*HSSFCell celdaEnc78 = filaEnc22.createCell(5);
        celdaEnc78.setCellValue(listaPerfil.getActividadEconomica());*/
        
        HSSFCell celdaEnc79 = filaEnc22.createCell(8);
        celdaEnc79.setCellValue("Fecha status");

        /* HSSFCell celdaEnc80 = filaEnc22.createCell(9);
        celdaEnc80.setCellValue(listaPerfil.getActividadEconomica());*/

 /*---------------------------------------------------*/
        HSSFRow filaEnc23 = hoja.createRow(23);

        HSSFCell celdaEnc81 = filaEnc23.createCell(0);
        celdaEnc81.setCellValue("Omiso");

        HSSFCell celdaEnc82 = filaEnc23.createCell(1);
        celdaEnc82.setCellValue(listaPerfil.getOmiso());

        /*---------------------------------------------------*/
        HSSFRow filaEnc24 = hoja.createRow(24);

        HSSFCell celdaEnc83 = filaEnc24.createCell(0);
        celdaEnc83.setCellValue("Cuenta con usuario de Agencia Virtual");

        HSSFCell celdaEnc84 = filaEnc24.createCell(1);
        celdaEnc84.setCellValue(listaPerfil.getAgenciaVirtual());
        
         HSSFCell celdaEnc85 = filaEnc24.createCell(4);
        celdaEnc85.setCellValue("Status ");

        /*HSSFCell celdaEnc86 = filaEnc24.createCell(1);
        celdaEnc90.setCellValue(listaPerfil.getAgenciaVirtual());*/
        
        HSSFCell celdaEnc87 = filaEnc24.createCell(8);
        celdaEnc87.setCellValue("Fecha Status");

        /*HSSFCell celdaEnc88 = filaEnc24.createCell(1);
        celdaEnc88.setCellValue(listaPerfil.getAgenciaVirtual());*/
        
        
        /*---------------------------------------------------*/
        HSSFRow filaEnc25 = hoja.createRow(25);

        HSSFCell celdaEnc89 = filaEnc25.createCell(0);
        celdaEnc89.setCellValue("Emisor factura electrónica");

       /*HSSFCell celdaEnc90 = filaEnc25.createCell(1);
        celdaEnc90.setCellValue(listaPerfil.getFechaNacConst());*/
       
        HSSFCell celdaEnc91 = filaEnc25.createCell(4);
        celdaEnc91.setCellValue("Status");

       /*HSSFCell celdaEnc92 = filaEnc25.createCell(4);
        celdaEnc92.setCellValue(listaPerfil.getFechaNacConst());*/
       
        HSSFCell celdaEnc93 = filaEnc25.createCell(8);
        celdaEnc93.setCellValue("Fecha Status");

       /*HSSFCell celdaEnc94 = filaEnc26.createCell(1);
        celdaEnc94.setCellValue(listaPerfil.getFechaNacConst());*/
       

        /*---------------------------------------------------*/
        HSSFRow filaEnc26 = hoja.createRow(26);

        HSSFCell celdaEnc95 = filaEnc26.createCell(0);
        celdaEnc95.setCellValue("Email");

        /*HSSFCell celdaEnc96 = filaEnc26.createCell(1);
        celdaEnc96.setCellValue(listaPerfil.getFechaNacConst());*/

        /*---------------------------------------------------*/
        HSSFRow filaEnc27 = hoja.createRow(27);

        HSSFCell celdaEnc97 = filaEnc27.createCell(0);
        celdaEnc97.setCellValue("Email notificaciones");

       /* HSSFCell celdaEnc98 = filaEnc27.createCell(1);
        celdaEnc98.setCellValue(listaPerfil.getFechaNacConst());*/

        /*---------------------------------------------------*/
        HSSFRow filaEnc28 = hoja.createRow(28);

        HSSFCell celdaEnc99 = filaEnc28.createCell(0);
        celdaEnc99.setCellValue("Plan Operativo");

       /* HSSFCell celdaEnc100 = filaEnc28.createCell(1);
        celdaEnc100.setCellValue(listaPerfil.getFechaNacConst());*/

        /*---------------------------------------------------*/
        HSSFRow filaEnc29 = hoja.createRow(29);

        HSSFCell celdaEnc101 = filaEnc29.createCell(0);
        celdaEnc101.setCellValue("Total direcciones comerciales");

        HSSFCell celdaEnc102 = filaEnc29.createCell(1);
        celdaEnc102.setCellValue(totalEstablecimientos);

        /*---------------------------------------------------*/
        HSSFRow filaEnc31= hoja.createRow(31);

        HSSFCell celdaEnc103 = filaEnc31.createCell(0);
        celdaEnc103.setCellValue("Nombre");

        HSSFCell celdaEnc104 = filaEnc31.createCell(1);
        celdaEnc104.setCellValue("Dirección");

        Iterator<InfoEstablecimientosDto> establecimientos = this.listEstablecimientos.iterator();

        int i = 32;
        while (establecimientos.hasNext()) {
            InfoEstablecimientosDto establecimiento = establecimientos.next();

            HSSFRow filaEnc32 = hoja.createRow(i);
            i++;

            HSSFCell celdaEnc31a = filaEnc32.createCell(0);
            celdaEnc31a.setCellValue(establecimiento.getNombreComercial());

            HSSFCell celdaEnc32b = filaEnc32.createCell(1);
            celdaEnc32b.setCellValue(establecimiento.getDireccionEstablecimiento());

        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        externalContext.setResponseContentType("application/vnd.ms-excel; charset=utf-8");

        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"perfil_del_contribuyente.xls\"");

        libro.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();

    }

}
