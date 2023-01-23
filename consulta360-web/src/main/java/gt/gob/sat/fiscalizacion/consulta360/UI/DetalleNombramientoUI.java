/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.ArchivoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.EncabezadoNombramientoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.SupervisorAuditorDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.TrayectoriaNombramientoDto;
import gt.gob.sat.properties.exception.ConfigurationException;
import gt.gob.sat.properties.util.ConfigurationUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author dealonzo
 */
@Controller
@Scope("view")
public class DetalleNombramientoUI extends AbstractUI implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DetalleNombramientoUI.class);
    
    private int seccionActiva=1;
    private EncabezadoNombramientoDto encabezadoNombramiento=new EncabezadoNombramientoDto();
    private List<SupervisorAuditorDto> listSupervisoresAuditores;
    private List<TrayectoriaNombramientoDto> trayectoriaNombramiento;
    private List<ArchivoDto> listInformesAuditoria;
    private ArchivoDto informeSeleccionado=new ArchivoDto();
    
    @PostConstruct
    public void inicializar(){
        try {
            Map params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            
            encabezadoNombramiento.setNit((String) params.get("pNit"));
            encabezadoNombramiento.setNombramiento((String) params.get("pNombramiento"));
            
            if(encabezadoNombramiento.getNit()==null || encabezadoNombramiento.getNit().isEmpty() || encabezadoNombramiento.getNombramiento()==null || encabezadoNombramiento.getNombramiento().isEmpty()){
                this.errorMsg("No se han recibido los par\u00e1metros esperados");
                LOG.error("No se han recibido los parametros esperados");
            }else{
                encabezadoNombramiento=this.generalSrvImpl.obtenerEncabezadoNombramiento(encabezadoNombramiento.getNombramiento());
                listSupervisoresAuditores=this.generalSrvImpl.obtenerSupervisoresAuditoresByNombramiento(encabezadoNombramiento.getNombramiento());
                trayectoriaNombramiento=this.generalSrvImpl.obtenerTrayectoriaNombramiento(encabezadoNombramiento.getNombramiento());
                generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(17,encabezadoNombramiento.getNit(),sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar el detalle del nombramiento");
            LOG.error("Error al cargar el detalle del nombramiento", e);
        }
    }
    
    private String urlBaseReporte(String pKeyNombreReporte) {
        String url=null;
        try {
            ConfigurationUtil urlArchivo = new ConfigurationUtil("reportesBIRT");
            StringBuilder urlReporte = new StringBuilder();

            urlReporte.append(urlArchivo.getKey("urlBirt"));
            urlReporte.append(urlArchivo.getKey("urlReporte"));
            urlReporte.append(urlArchivo.getKey(pKeyNombreReporte));
            urlReporte.append("&__format=").append(urlArchivo.getKey("formatoReporte"));

            url=urlReporte.toString();
        } catch (ConfigurationException ex) {
            LOG.error("Error al leer el archivo property", ex);
        }

        return url;
    }
    
    public void generarHojaPreliminar() {
        String url = this.urlBaseReporte("liquidacionIVA");
        if (StringUtils.isNotBlank(url)) {
            url = url + "&pNombramiento=" + encabezadoNombramiento.getNombramiento() + "&" + this.sesion.getTicket();
            RequestContext.getCurrentInstance().execute("window.open('" + url + "', '_newtab')");
        }else{
            this.errorMsg("Error al visualizar el reporte");
        }
    }

    public void reimprimirNombramiento() {
        String url = this.urlBaseReporte("nombramiento");
        if (StringUtils.isNotBlank(url)) {
            url = url + "&pNombramiento=" + encabezadoNombramiento.getNombramiento() + "&pUsuario=" + encabezadoNombramiento.getNit() + "&" + this.sesion.getTicket();
            RequestContext.getCurrentInstance().execute("window.open('" + url + "', '_newtab')");
        } else {
            this.errorMsg("Error al visualizar el reporte");
        }
    }
    
    public void reimprimirRequerimientosInformacion() {
        String url = this.urlBaseReporte("elijeRequerimiento");
        if (StringUtils.isNotBlank(url)) {
            url = url + "&pNombramiento=" + encabezadoNombramiento.getNombramiento() + "&" + this.sesion.getTicket();
            RequestContext.getCurrentInstance().execute("window.open('" + url + "', '_newtab')");
        } else {
            this.errorMsg("Error al visualizar el reporte");
        }
    }

    public void generarResumenImpuestosMultas() {
        //Pendiente de implementar
    }

    public void generarResumenMultas() {
        //Pendiente de implementar
    }
    
    public void generarInformeBanguat() {
        if (this.generalSrvImpl.existeInformeBanguatActivo(encabezadoNombramiento.getNombramiento())) {
            String url = this.urlBaseReporte("informeBanguat");

            if (StringUtils.isNotBlank(url)) {
                url = url + "&pNombramiento=" + encabezadoNombramiento.getNombramiento() + "&" + this.sesion.getTicket();
                RequestContext.getCurrentInstance().execute("window.open('" + url + "', '_newtab')");
            } else {
                this.errorMsg("Error al visualizar el reporte");
            }
        } else {
            this.warnMsg("No existe informe activo para el nombramiento " + encabezadoNombramiento.getNombramiento());
        }
    }

    public void generarResolucionDevolucion() {
        if (this.generalSrvImpl.existeResolucionFiscalActiva(encabezadoNombramiento.getNombramiento())) {
            String url = this.urlBaseReporte("resolucionCF");

            if (StringUtils.isNotBlank(url)) {
                url = url.substring(0, url.length() - 3) + "html";
                url = url + "&pNombramiento=" + encabezadoNombramiento.getNombramiento() + "&" + this.sesion.getTicket();
                RequestContext.getCurrentInstance().execute("window.open('" + url + "', '_newtab')");
            } else {
                this.errorMsg("Error al visualizar el reporte");
            }
        } else {
            this.warnMsg("No existe resoluci\u00f3n activa para el nombramiento " + encabezadoNombramiento.getNombramiento());
        }
    }

    public void reimprimirAudiencia() {
        if (this.generalSrvImpl.existenAudiencias(encabezadoNombramiento.getNombramiento())) {
            String url = this.urlBaseReporte("listaAudiencias");
            if (StringUtils.isNotBlank(url)) {
                url = url + "&pNombramiento=" + encabezadoNombramiento.getNombramiento() + "&" + this.sesion.getTicket();
                RequestContext.getCurrentInstance().execute("window.open('" + url + "', '_newtab')");
            } else {
                this.errorMsg("Error al visualizar el reporte");
            }
        } else {
            this.warnMsg("No existen audiencias para el nombramiento " + encabezadoNombramiento.getNombramiento());
        }
    }
    
    public void generarInforme() {
        BigDecimal noInforme = this.generalSrvImpl.findNoInforme(encabezadoNombramiento.getNombramiento());

        if (!noInforme.toString().equals("0")) {
            String url = this.urlBaseReporte("nombreReporteInforme");
            if (StringUtils.isNotBlank(url)) {
                url = url + "&v_nombramiento=" + encabezadoNombramiento.getNombramiento() + "&txtinforme=" + noInforme + "&" + this.sesion.getTicket();
                RequestContext.getCurrentInstance().execute("window.open('" + url + "', '_newtab')");
            } else {
                this.errorMsg("Error al visualizar el reporte");
            }
        } else {
            this.warnMsg("No existe informe para el nombramiento " + encabezadoNombramiento.getNombramiento());
        }
    }
    
    public void listarInformesFinAuditoria(){
        try {
            listInformesAuditoria=listInformesAuditoria==null ? this.generalSrvImpl.obtenerInformesFinAuditoria(encabezadoNombramiento.getNombramiento()) : listInformesAuditoria;
            if(listInformesAuditoria==null){
                warnMsg("No se tienen informes regitrados para el nombramiento "+encabezadoNombramiento.getNombramiento());
            }else{
                seccionActiva=2;
            }
        } catch (Exception e) {
            errorMsg("Error al listar los informes (Fin de Auditor\u00eda)");
            LOG.error("Error al listar los informes (Fin de Auditoria)", e);
        }
    }

    public void generarSeguimientoHallazgosInconsistencias() {
        String url = this.urlBaseReporte("seguimientoHallazgos");
        if (StringUtils.isNotBlank(url)) {
            url = url + "&v_nombramiento=" + encabezadoNombramiento.getNombramiento() + "&" + this.sesion.getTicket();
            RequestContext.getCurrentInstance().execute("window.open('" + url + "', '_newtab')");
        } else {
            this.errorMsg("Error al visualizar el reporte");
        }
    }
    
    public StreamedContent getInforme(){
        try {
            return new DefaultStreamedContent(informeSeleccionado.getArchivo().getBinaryStream(),"application/pdf",encabezadoNombramiento.getNombramiento()+".pdf");
        } catch (SQLException e) {
            this.errorMsg("Error al descargar el informe");
            LOG.error("Error al descargar el informe", e);
        }
        
        return null;
    }

    /**
     * @return the encabezadoNombramiento
     */
    public EncabezadoNombramientoDto getEncabezadoNombramiento() {
        return encabezadoNombramiento;
    }

    /**
     * @param pEncabezadoNombramiento the encabezadoNombramiento to set
     */
    public void setEncabezadoNombramiento(EncabezadoNombramientoDto pEncabezadoNombramiento) {
        this.encabezadoNombramiento = pEncabezadoNombramiento;
    }

    /**
     * @return the listSupervisoresAuditores
     */
    public List<SupervisorAuditorDto> getListSupervisoresAuditores() {
        return this.listSupervisoresAuditores;
    }

    /**
     * @param pListSupervisoresAuditores the listSupervisoresAuditores to set
     */
    public void setListSupervisoresAuditores(List<SupervisorAuditorDto> pListSupervisoresAuditores) {
        this.listSupervisoresAuditores = pListSupervisoresAuditores;
    }

    /**
     * @return the trayectoriaNombramiento
     */
    public List<TrayectoriaNombramientoDto> getTrayectoriaNombramiento() {
        return this.trayectoriaNombramiento;
    }

    /**
     * @param pTrayectoriaNombramiento the trayectoriaNombramiento to set
     */
    public void setTrayectoriaNombramiento(List<TrayectoriaNombramientoDto> pTrayectoriaNombramiento) {
        this.trayectoriaNombramiento = pTrayectoriaNombramiento;
    }

    public List<ArchivoDto> getListInformesAuditoria() {
        return listInformesAuditoria;
    }

    public void setListInformesAuditoria(List<ArchivoDto> listInformesAuditoria) {
        this.listInformesAuditoria = listInformesAuditoria;
    }

    public ArchivoDto getInformeSeleccionado() {
        return informeSeleccionado;
    }

    public void setInformeSeleccionado(ArchivoDto informeSeleccionado) {
        this.informeSeleccionado = informeSeleccionado;
    }

    public int getSeccionActiva() {
        return seccionActiva;
    }

    public void setSeccionActiva(int seccionActiva) {
        this.seccionActiva = seccionActiva;
    }
}