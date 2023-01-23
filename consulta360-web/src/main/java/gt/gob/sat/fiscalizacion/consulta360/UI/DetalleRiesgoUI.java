/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;


import gt.gob.sat.fiscalizacion.consulta360.dto.DetalleRiesgoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DetalleRiesgoSentenciaDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HistorialRiesgoDto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;



/**
 *
 * @author eogranad
 */
@Controller
@Scope("session")
public class DetalleRiesgoUI extends AbstractUI implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DetalleRiesgoUI.class);

    private  HistorialRiesgoDto  historialRiesgoSeleccionado;
    private BigDecimal codigoRegimen;
    String urlVisorDetalleRiesgo;
    String urlVisorDetalleRiesgoAD;
    private List<DetalleRiesgoSentenciaDto> listaSentenciasRiesgo;
    private List<DetalleRiesgoDto> listaVariableRiesgo;
    private int seccionActiva = 1;
   // private String pNit;


    @PostConstruct
    public void inicializar() {
        sesion.getOpcion();
        try {
          //  pNit = contribuyenteSeleccionado.getNitContribuyente(); //sesion.getUsuarioDto().getNit(); ///---contribuyenteSeleccionado.getNitContribuyente();
          //datos.setNit(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit"));
         //pNit = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
          //pNit = historialRiesgoSeleccionado.getNitContribuyente();
                  
                  
                seccionActiva = 1;
                urlVisorDetalleRiesgo = "detalleRiesgo.jsf";
                urlVisorDetalleRiesgoAD = "detalleRiesgoAD.jsf";
               /* 
                listAfiliaciones = this.generalSrvImpl.obtenerAfiliacionesByNit(nit);
                generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(13,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
                if(listAfiliaciones==null || listAfiliaciones.isEmpty()){
                    warnMsg("No se tienen afiliaciones registradas");
                }*/
 
        } catch (Exception e) {
            this.errorMsg("Error al cargar la p\u00e1gina de Detalle de Variables de Riesgo");
            LOG.error("Error al cargar la pagina de Detalle de Variables de Riesgo", e);
        }
    }

    public HistorialRiesgoDto getHistorialRiesgoSeleccionado() {
        return historialRiesgoSeleccionado;
    }

    public void setHistorialRiesgoSeleccionado(HistorialRiesgoDto historialRiesgoSeleccionado) {
        this.historialRiesgoSeleccionado = historialRiesgoSeleccionado;
    }

    public BigDecimal getCodigoRegimen() {
        return codigoRegimen;
    }

    public void setCodigoRegimen(BigDecimal codigoRegimen) {
        this.codigoRegimen = codigoRegimen;
    }

    
    public List<DetalleRiesgoSentenciaDto> getListaSentenciasRiesgo() {
        return listaSentenciasRiesgo;
    }

    public void setListaSentenciasRiesgo(List<DetalleRiesgoSentenciaDto> listaSentenciasRiesgo) {
        this.listaSentenciasRiesgo = listaSentenciasRiesgo;
    }

    public List<DetalleRiesgoDto> getListaVariableRiesgo() {
        
        return listaVariableRiesgo;
    }

    public void setListaVariableRiesgo(List<DetalleRiesgoDto> listaVariableRiesgo) {
        this.listaVariableRiesgo = listaVariableRiesgo;
    }

    public int getSeccionActiva() {
        return seccionActiva;
    }

    public void setSeccionActiva(int seccionActiva) {
        this.seccionActiva = seccionActiva;
    }

    
    
    public void verDetalleRiesgo(){
        String temp;
        String rol;
        String paginaDetalle;
        
        try{
                codigoRegimen = this.generalSrvImpl.obtenerCodigoRegimenRiesgo(historialRiesgoSeleccionado.getNitContribuyente(), historialRiesgoSeleccionado.getAnioInformacionBase().toString());
                listaSentenciasRiesgo = this.generalSrvImpl.obtenerSentenciaMapeoPorRegimen(historialRiesgoSeleccionado.getNitContribuyente(), historialRiesgoSeleccionado.getAnioInformacionBase().toString(), codigoRegimen.toString());
                listaVariableRiesgo = null;    
                if(listaSentenciasRiesgo != null &&!listaSentenciasRiesgo.isEmpty()) {
                       
                       for(int i = 0; i < listaSentenciasRiesgo.size(); i++)
                       {
                           temp = listaSentenciasRiesgo.get(i).getSentenciaDetalleRiesgo();
                           //LOG.error(i+"-"+temp);
                       }
                       
                       
                       //LOG.error("Paso 5 ->"+listaSentenciasRiesgo.size());
                       listaVariableRiesgo = this.generalSrvImpl.obtenerVariablesDeRiesgo(listaSentenciasRiesgo);
                              
                       
                       //LOG.error("Paso 6 ->"+listaVariableRiesgo.size());
                               
                               
                       for(int i = 0; i < listaVariableRiesgo.size(); i++)
                       {
                           temp = listaVariableRiesgo.get(i).getSignificado();
                           //LOG.error(i+"-"+temp);
                       }
                          
                   }
                   else{
                       //LOG.error("Paso 9");
                        LOG.error("No se localiza detalle de Conceptos asociados al nivel de Riesgo seleccionado.");
                        warnMsg("No se localiza detalle de Conceptos asociados al nivel de Riesgo seleccionado.");   
                    }
                   
                   
                   
                   if (StringUtils.isNotBlank(urlVisorDetalleRiesgo)) {
                       
                       rol = sesion.geRoles();
                      // LOG.error("El rol es -|" + rol+"|-");
                       
                       String palabra = "GeneralConsultaCalificacion";
                       String texto = rol;
                       boolean resultado = texto.contains(palabra);
                        if(resultado){
                          LOG.error("Autorizado");
                         //  RequestContext.getCurrentInstance().execute("window.open('" + urlVisorDetalleRiesgo+"', '_blank')");
                           paginaDetalle= urlVisorDetalleRiesgo;
                       }else{
                           LOG.error("No Autorizado");
                          //  RequestContext.getCurrentInstance().execute("window.open('../error/error401.jsf', '_blank')");
                            paginaDetalle= urlVisorDetalleRiesgoAD;
                       }
                      
                        RequestContext.getCurrentInstance().execute("window.open('" + paginaDetalle+"', '_blank')");
                        
                        
                   }else{

                       LOG.error("URL del reporte de detalle de Variables de Riesgos viene vacia, error al visualizar el reporte.");
                        } 
            
        } catch(Exception ex){
            LOG.error("Error al visualizar el Detalle de Riesgo... ", ex);
            LOG.error("Error al obtener el Regimen de Riesgo --> |"+ historialRiesgoSeleccionado.getNitContribuyente() +"|-|"+ historialRiesgoSeleccionado.getAnioInformacionBase()+"|-" );
        }
    }    

}