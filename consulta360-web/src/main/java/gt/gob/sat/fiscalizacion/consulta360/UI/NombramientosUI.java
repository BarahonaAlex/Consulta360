/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.NombramientoDto;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author dealonzo
 */
@Controller
@Scope("view")
public class NombramientosUI extends AbstractUI implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(NombramientosUI.class);

    private String nit = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private List<NombramientoDto> listNombramientos;
    private List<NombramientoDto> listNombramientosTemp;
    private NombramientoDto registroSeleccionado;
    private String fechaActual=new SimpleDateFormat("dd/MM/yyyy").format(new Date());

    @PostConstruct
    public void inicializar() {
        try {
            if(nit==null || nit.isEmpty()){
                this.errorMsg("No se ha recibido el par\u00e1metro esperado");
                LOG.error("No se ha recibido el parametro esperado");
            }else{
                infoMsg("Defina el periodo de consulta");
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la consulta de nombramientos");
            LOG.error("Error al cargar la consulta de nombramientos", e);
        }
    }
    
    public void consultar(Date pFechaInicio, Date pFechaFin){
        try {
            if(pFechaInicio.after(pFechaFin)){
                warnMsg("Verifique el per\u00edodo de consulta");
            }else{
                setListNombramientos(this.generalSrvImpl.obtenerNombramientosByNitAndDates(nit,pFechaInicio,pFechaFin));
                generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(16,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
                if(listNombramientos==null || listNombramientos.isEmpty()){
                    warnMsg("La consulta no devolvi\u00f3 resultados");
                }
            }
        } catch (Exception e) {
            errorMsg("Error al consultar nombramientos");
            LOG.error("Error al consultar nombramientos", e);
        }
    }

    public void verNombramiento() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("detalleNombramiento.jsf?pNit=" + nit + "&pNombramiento=" + registroSeleccionado.getNombramiento());
        } catch (IOException ex) {
            this.errorMsg("Error al ver el detalle del nombramiento");
            LOG.error("Error al ver el detalle del nombramiento", ex);
        }
    }

    public void verProveedores() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("proveedores.jsf?pNit=" + nit + "&pNombramiento=" + registroSeleccionado.getNombramiento());
        } catch (IOException ex) {
            this.errorMsg("Error al visualizar los papeles de trabajo");
            LOG.error("Error al visualizar los papeles de trabajo", ex);
        }
    }

    /**
     * @return the listNombramientos
     */
    public List<NombramientoDto> getListNombramientos() {
        return this.listNombramientos;
    }

    /**
     * @param pListNombramientos the listNombramientos to set
     */
    public void setListNombramientos(List<NombramientoDto> pListNombramientos) {
        this.listNombramientos = pListNombramientos;
    }

    /**
     * @return the registroSeleccionado
     */
    public NombramientoDto getRegistroSeleccionado() {
        return registroSeleccionado;
    }

    /**
     * @param pRegistroSeleccionado the registroSeleccionado to set
     */
    public void setRegistroSeleccionado(NombramientoDto pRegistroSeleccionado) {
        this.registroSeleccionado = pRegistroSeleccionado;
    }

    /**
     * @return the listNombramientosTemp
     */
    public List<NombramientoDto> getListNombramientosTemp() {
        return this.listNombramientosTemp;
    }

    /**
     * @param pListNombramientosTemp the listNombramientosTemp to set
     */
    public void setListNombramientosTemp(List<NombramientoDto> pListNombramientosTemp) {
        this.listNombramientosTemp = pListNombramientosTemp;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }
}