/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ExpedienteDto;
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
public class ExpedientesJuridicoUI extends AbstractUI implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ExpedientesJuridicoUI.class);

    private String nit=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private List<ExpedienteDto> listExpedientesJuridico;
    private List<ExpedienteDto> listExpedientesJuridicoTemp;
    private Date fechaInicio;
    private Date fechaFin;
    private String fechaActual;

    @PostConstruct
    public void inicializar() {
        try {
            if(nit==null || nit.isEmpty()) {
                this.errorMsg("No se ha recibido el par\u00e1metro esperado");
                LOG.error("No se ha recibido el parametro esperado");
            }else{
                fechaActual=new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                infoMsg("Defina el periodo de consulta");
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la p\u00e1gina para consulta de expedientes");
            LOG.error("Error al cargar la pagina para consulta de expedientes", e);
        }
    }
    
    public void consultarExpedientes(){
        try {
            if(listExpedientesJuridico==null || listExpedientesJuridico.isEmpty()){
                if(fechaInicio.after(fechaFin)){
                    warnMsg("Verifique el per\u00edodo de consulta");
                }else{
                    listExpedientesJuridico = this.generalSrvImpl.obtenerExpedientesJuridicoByNit(nit,fechaInicio,fechaFin);
                    generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(21,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
                    if(listExpedientesJuridico==null || listExpedientesJuridico.isEmpty()){
                        warnMsg("No hay expedientes asociados");
                    }
                }
            }else{
                listExpedientesJuridico=null;
                fechaInicio=null;
                fechaFin=null;
            }
        } catch (Exception e) {
            errorMsg("Error al consultar la lista de expedientes en jur\u00eddico");
            LOG.error("Error al consultar la lista de expedientes en juridico", e);
        }
    }

    /**
     * @return the listExpedientesJuridico
     */
    public List<ExpedienteDto> getListExpedientesJuridico() {
        return this.listExpedientesJuridico;
    }

    /**
     * @param pListExpedientesJuridico the listExpedientesJuridico to set
     */
    public void setListExpedientesJuridico(List<ExpedienteDto> pListExpedientesJuridico) {
        this.listExpedientesJuridico = pListExpedientesJuridico;
    }
    
    /**
     * @return the listExpedientesJuridicoTemp
     */
    public List<ExpedienteDto> getListExpedientesJuridicoTemp() {
        return this.listExpedientesJuridicoTemp;
    }

    /**
     * @param pListExpedientesJuridicoTemp the listExpedientesJuridicoTemp to set
     */
    public void setListExpedientesJuridicoTemp(List<ExpedienteDto> pListExpedientesJuridicoTemp) {
        this.listExpedientesJuridicoTemp = pListExpedientesJuridicoTemp;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }
}