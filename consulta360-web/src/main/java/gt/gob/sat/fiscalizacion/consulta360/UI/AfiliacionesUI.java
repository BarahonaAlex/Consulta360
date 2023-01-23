/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.AfiliacionDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ObligacionDto;
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
public class AfiliacionesUI extends AbstractUI implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AfiliacionesUI.class);

    private String nit = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private List<AfiliacionDto> listAfiliaciones;
    private List<AfiliacionDto> listAfiliacionesTemp;
    private List<ObligacionDto> listObligaciones;
    private AfiliacionDto registroSeleccionado;
    private int seccionActiva = 1;

    @PostConstruct
    public void inicializar() {
        try {
            if (nit==null || nit.isEmpty()) {
                this.errorMsg("No se ha recibido el par\u00e1metro esperado");
                LOG.error("No se ha recibido el parametro esperado");
            } else {
                listAfiliaciones = this.generalSrvImpl.obtenerAfiliacionesByNit(nit);
                generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(13,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
                if(listAfiliaciones==null || listAfiliaciones.isEmpty()){
                    warnMsg("No se tienen afiliaciones registradas");
                }
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la p\u00e1gina de afiliaciones");
            LOG.error("Error al cargar la pagina de afiliaciones", e);
        }
    }

    public void verDetalle() {
        try {
            listObligaciones = this.generalSrvImpl.obtenerObligacionesByAfiliacionAndNit(nit, registroSeleccionado.getCodigoImpuesto(), registroSeleccionado.getCodigoTipo());
            generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(14,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
            if(listObligaciones==null || listObligaciones.isEmpty()){
                warnMsg("No se tienen obligaciones registradas");
            }else{
                seccionActiva = 2;
            }
        } catch (Exception e) {
            this.errorMsg("Error al consultar las obligaciones");
            LOG.error("Error al consultar las obligaciones", e);
        }
    }

    /**
     * @return the listAfiliaciones
     */
    public List<AfiliacionDto> getListAfiliaciones() {
        return this.listAfiliaciones;
    }

    /**
     * @param pListAfiliaciones the listAfiliaciones to set
     */
    public void setListAfiliaciones(List<AfiliacionDto> pListAfiliaciones) {
        this.listAfiliaciones = pListAfiliaciones;
    }

    /**
     * @return the registroSeleccionado
     */
    public AfiliacionDto getRegistroSeleccionado() {
        return registroSeleccionado;
    }

    /**
     * @param pRegistroSeleccionado the registroSeleccionado to set
     */
    public void setRegistroSeleccionado(AfiliacionDto pRegistroSeleccionado) {
        this.registroSeleccionado = pRegistroSeleccionado;
    }

    /**
     * @return the listObligaciones
     */
    public List<ObligacionDto> getListObligaciones() {
        return this.listObligaciones;
    }

    /**
     * @param pListObligaciones the listObligaciones to set
     */
    public void setListObligaciones(List<ObligacionDto> pListObligaciones) {
        this.listObligaciones = pListObligaciones;
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
        RequestContext.getCurrentInstance().execute("PF('dtAfiliaciones').filter();");
    }

    /**
     * @return the listAfiliacionesTemp
     */
    public List<AfiliacionDto> getListAfiliacionesTemp() {
        return this.listAfiliacionesTemp;
    }

    /**
     * @param pListAfiliacionesTemp the listAfiliacionesTemp to set
     */
    public void setListAfiliacionesTemp(List<AfiliacionDto> pListAfiliacionesTemp) {
        this.listAfiliacionesTemp = pListAfiliacionesTemp;
    }
}