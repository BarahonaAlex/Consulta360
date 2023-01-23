/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DeclaracionDto;
import gt.gob.sat.properties.util.ConfigurationUtil;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class DeclaracionesUI extends AbstractUI implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DeclaracionesUI.class);

    private String nit = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private String fechaActual;
    private Date fechaInicio;
    private Date fechaFin;
    private String codigoFormulario;
    private List<DeclaracionDto> listDeclaraciones;
    private List<DeclaracionDto> listDeclaracionesTemp;
    private DeclaracionDto declaracionSeleccionada;
    String urlVisorFormulariosDeclaraguate;

    @PostConstruct
    public void inicializar() {
        try {
            if (nit==null || nit.isEmpty()) {
                this.errorMsg("No se ha recibido el par\u00e1metro esperado");
                LOG.error("No se ha recibido el parametro esperado");
            } else {
                fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                urlVisorFormulariosDeclaraguate = new ConfigurationUtil("constantes").getKey("visorFormulariosDeclaraguate");
                infoMsg("Defina el periodo de consulta");
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la p\u00e1gina de declaraciones");
            LOG.error("Error al cargar la pagina de declaraciones", e);
        }
    }

    public void verFormulario() {
        try {
            if (declaracionSeleccionada.getNumeroAcceso() == null) {
                this.warnMsg("S\u00f3lo se pueden visualizar formularios de Declaraguate");
            } else {
                RequestContext.getCurrentInstance().execute("window.open('" + urlVisorFormulariosDeclaraguate + "?pNoFormulario=" + declaracionSeleccionada.getNumeroFormulario()+ "&pNoAcceso=" + declaracionSeleccionada.getNumeroAcceso() + "&" + this.sesion.getTicket() + "', '_newtab')");
            }
        } catch (Exception ex) {
            this.errorMsg("Error al visualizar el formulario");
            LOG.error("Error al visualizar el formulario", ex);
        }
    }

    public void consultarDeclaraciones() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (fechaInicio.after(fechaFin)) {
                this.warnMsg("El rango de fechas no es correcto");
            } else {
                if (this.cantidadDiasEntreFechas(fechaInicio, fechaFin) > 365) {
                    this.warnMsg("La consulta no puede ser mayor a un a\u00f1o");
                } else {
                    listDeclaraciones = this.generalSrvImpl.obtenerDeclaraciones(nit, fechaInicio, fechaFin, codigoFormulario);
                    generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(10,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
                    if(listDeclaraciones.isEmpty()){
                        warnMsg("No hay registros relacionados a la consulta");
                    }
                }
            }
        } catch (Exception e) {
            this.errorMsg("Error al consultar las declaraciones");
            LOG.error("Error al consultar las declaraciones", e);
        }
    }

    private int cantidadDiasEntreFechas(Date pFechaInicial, Date pFechaFinal) {
        Date fechaInicial;
        Date fechaFinal;
        double dias = 0;

        try {
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

            fechaInicial = df.parse(df.format(pFechaInicial));
            fechaFinal = df.parse(df.format(pFechaFinal));

            long diferencia = fechaFinal.getTime() - fechaInicial.getTime();
            dias = diferencia / (1000 * 60 * 60 * 24);
        } catch (ParseException ex) {
            this.errorMsg("Error al validar las fechas de la consulta de declaraciones");
            LOG.error("Error al validar las fechas de la consulta de declaraciones", ex);
        }

        return (int) Math.round(dias);
    }

    /**
     * @return the fechaActual
     */
    public String getFechaActual() {
        return fechaActual;
    }

    /**
     * @param pFechaActual the fechaActual to set
     */
    public void setFechaActual(String pFechaActual) {
        this.fechaActual = pFechaActual;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    /**
     * @param pFechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date pFechaInicio) {
        this.fechaInicio = pFechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return this.fechaFin;
    }

    /**
     * @param pFechaFin the fechaFin to set
     */
    public void setFechaFin(Date pFechaFin) {
        this.fechaFin = pFechaFin;
    }

    /**
     * @return the listDeclaraciones
     */
    public List<DeclaracionDto> getListDeclaraciones() {
        return this.listDeclaraciones;
    }

    /**
     * @param pListDeclaraciones the listDeclaraciones to set
     */
    public void setListDeclaraciones(List<DeclaracionDto> pListDeclaraciones) {
        this.listDeclaraciones = pListDeclaraciones;
    }

    /**
     * @return the declaracionSeleccionada
     */
    public DeclaracionDto getDeclaracionSeleccionada() {
        return declaracionSeleccionada;
    }

    /**
     * @param pDeclaracionSeleccionada the declaracionSeleccionada to set
     */
    public void setDeclaracionSeleccionada(DeclaracionDto pDeclaracionSeleccionada) {
        this.declaracionSeleccionada = pDeclaracionSeleccionada;
    }

    /**
     * @return the listDeclaracionesTemp
     */
    public List<DeclaracionDto> getListDeclaracionesTemp() {
        return this.listDeclaracionesTemp;
    }

    /**
     * @param pListDeclaracionesTemp the listDeclaracionesTemp to set
     */
    public void setListDeclaracionesTemp(List<DeclaracionDto> pListDeclaracionesTemp) {
        this.listDeclaracionesTemp = pListDeclaracionesTemp;
    }

    /**
     * @return the codigoFormulario
     */
    public String getCodigoFormulario() {
        return codigoFormulario;
    }

    /**
     * @param codigoFormulario the codigoFormulario to set
     */
    public void setCodigoFormulario(String codigoFormulario) {
        this.codigoFormulario = codigoFormulario;
    }
}