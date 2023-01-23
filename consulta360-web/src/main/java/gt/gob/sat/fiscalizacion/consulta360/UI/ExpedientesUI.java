/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DocumentoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ExpedienteDto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author dealonzo
 */
@Controller
@Scope("view")
public class ExpedientesUI extends AbstractUI implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ExpedientesUI.class);

    private String nit=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private List<ExpedienteDto> listExpedientes;
    private List<ExpedienteDto> listExpedientesTemp;
    private ExpedienteDto registroSeleccionado=new ExpedienteDto();
    private List<DocumentoDto> listDocumentos;
    private int seccionActiva=1;

    @PostConstruct
    public void inicializar() {
        try {
            if(nit==null || nit.isEmpty()){
                this.errorMsg("No se ha recibido el par\u00e1metro esperado");
                LOG.error("No se ha recibido el parametro esperado");
            }else{
                listExpedientes = this.generalSrvImpl.obtenerExpedientesByNit(nit);
                generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(2,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
                if (listExpedientes==null || listExpedientes.isEmpty()) {
                    warnMsg("No hay expedientes asociados");
                }
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la consulta de expedientes");
            LOG.error("Error al cargar la consulta de expedientes", e);
        }
    }

    public void verDocumentos() {
        try {
            listDocumentos=this.generalSrvImpl.obtenerDocumentosByExpediente(registroSeleccionado.getNumeroExpediente());
            generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(3,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
            if(listDocumentos==null || listDocumentos.isEmpty()){
                warnMsg("No hay documentos asociados");
            }else{
                seccionActiva=2;
            }
        } catch (Exception e) {
            this.errorMsg("Error al consultar los documentos asociados");
            LOG.error("Error al consultar los documentos asociados", e);
        }
    }

    /**
     * @return the listExpedientes
     */
    public List<ExpedienteDto> getListExpedientes() {
        return this.listExpedientes;
    }

    /**
     * @param pListExpedientes the listExpedientes to set
     */
    public void setListExpedientes(List<ExpedienteDto> pListExpedientes) {
        this.listExpedientes = pListExpedientes;
    }

    /**
     * @return the registroSeleccionado
     */
    public ExpedienteDto getRegistroSeleccionado() {
        return registroSeleccionado;
    }

    /**
     * @param pRegistroSeleccionado the registroSeleccionado to set
     */
    public void setRegistroSeleccionado(ExpedienteDto pRegistroSeleccionado) {
        this.registroSeleccionado = pRegistroSeleccionado;
    }

    /**
     * @return the seccionActiva
     */
    public int getSeccionActiva() {
        return seccionActiva;
    }

    /**
     * @param pSeccionActiva the seccionActiva to set
     */
    public void setSeccionActiva(int pSeccionActiva) {
        this.seccionActiva = pSeccionActiva;
        RequestContext.getCurrentInstance().execute("PF('dtExpedientes').filter();");
    }

    /**
     * @return the listDocumentos
     */
    public List<DocumentoDto> getListDocumentos() {
        return this.listDocumentos;
    }

    /**
     * @param pListDocumentos the listDocumentos to set
     */
    public void setListDocumentos(List<DocumentoDto> pListDocumentos) {
        this.listDocumentos = pListDocumentos;
    }

    /**
     * @return the listExpedientesTemp
     */
    public List<ExpedienteDto> getListExpedientesTemp() {
        return this.listExpedientesTemp;
    }

    /**
     * @param pListExpedientesTemp the listExpedientesTemp to set
     */
    public void setListExpedientesTemp(List<ExpedienteDto> pListExpedientesTemp) {
        this.listExpedientesTemp = pListExpedientesTemp;
    }
}