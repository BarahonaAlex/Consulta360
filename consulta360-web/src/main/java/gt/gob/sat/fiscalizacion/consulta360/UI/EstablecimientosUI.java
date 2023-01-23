/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DocumentoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.EstablecimientoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.LibroAutorizadoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.LineaAereaDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.MaquinaDto;
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
public class EstablecimientosUI extends AbstractUI implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(EstablecimientosUI.class);

    private String nit=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private List<EstablecimientoDto> listEstablecimientos;
    private List<EstablecimientoDto> listEstablecimientosTemp;
    private EstablecimientoDto registroSeleccionado;
    private List<DocumentoDto> listDocumentos;
    private List<DocumentoDto> listDocumentosTemp;
    private List<MaquinaDto> listMaquinas;
    private List<MaquinaDto> listMaquinasTemp;
    private List<LibroAutorizadoDto> listLibros;
    private List<LibroAutorizadoDto> listLibrosTemp;
    private List<LineaAereaDto> listLineasAereas;
    private List<LineaAereaDto> listLineasAereasTemp;
    private int seccionActiva = 1;

    @PostConstruct
    public void inicializar() {
        try {
            if (nit==null || nit.isEmpty()) {
                this.errorMsg("No se ha recibido el par\u00e1metro esperado");
                LOG.error("No se ha recibido el parametro esperado");
            } else {
                listEstablecimientos = this.generalSrvImpl.obtenerEstablecimientosByNit(nit);
                generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(5,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
                if(listEstablecimientos==null || listEstablecimientos.isEmpty()){
                    warnMsg("No hay establecimientos asociados");
                }
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la p&aacute;gina");
            LOG.error("Error al cargar la pagina", e);
        }
    }

    public void verDocumentos() {
        try {
            listDocumentos = this.generalSrvImpl.obtenerDocumentosByNitAndEstablecimiento(nit, registroSeleccionado.getNumeroEstablecimiento());
            generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(6,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
            if(listDocumentos==null || listDocumentos.isEmpty()){
                warnMsg("No hay documentos asociados");
            }else{
                setSeccionActiva(2);
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la lista de documentos asociados");
            LOG.error("Error al cargar la lista de documentos asociados", e);
        }
    }

    public void verMaquinas() {
        try {
            listMaquinas = this.generalSrvImpl.obtenerMaquinasByNitAndEstablecimiento(nit, registroSeleccionado.getNumeroEstablecimiento());
            generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(7,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
            if(listMaquinas==null || listMaquinas.isEmpty()){
                warnMsg("No hay m\u00e1quinas asociadas");
            }else{
                setSeccionActiva(3);
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la lista de m&aacute;quinas asociados");
            LOG.error("Error al cargar la lista de m&aacute;quinas asociados", e);
        }
    }

    public void verLibros() {
        try {
            listLibros = this.generalSrvImpl.obtenerLibrosByNitAndEstablecimiento(nit, registroSeleccionado.getNumeroEstablecimiento());
            generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(8,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
            if (listLibros==null || listLibros.isEmpty()) {
                warnMsg("No hay libros asociados");
            }else{
                setSeccionActiva(4);
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la lista de libros asociados");
            LOG.error("Error al cargar la lista de libros asociados", e);
        }
    }

    public void verLineasAereas() {
        try {
            listLineasAereas = this.generalSrvImpl.obtenerLineasAereasByNitAndEstablecimiento(nit, registroSeleccionado.getNumeroEstablecimiento());
            generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(9,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
            if(listLineasAereas==null || listLineasAereas.isEmpty()){
                warnMsg("No hay l\u00edneas a\u00e9reas asociadas");
            }else{
                setSeccionActiva(5);
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la lista de l&iacute;neas a&eacute;reas asociados");
            LOG.error("Error al cargar la lista de l&iacute;neas a&eacute;reas asociados", e);
        }
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
        RequestContext.getCurrentInstance().execute("PF('dtEstablecimientos').filter();");
    }

    /**
     * @return the listEstablecimientos
     */
    public List<EstablecimientoDto> getListEstablecimientos() {
        return this.listEstablecimientos;
    }

    /**
     * @param pListEstablecimientos the listEstablecimientos to set
     */
    public void setListEstablecimientos(List<EstablecimientoDto> pListEstablecimientos) {
        this.listEstablecimientos = pListEstablecimientos;
    }

    /**
     * @return the registroSeleccionado
     */
    public EstablecimientoDto getRegistroSeleccionado() {
        return registroSeleccionado;
    }

    /**
     * @param pRegistroSeleccionado the registroSeleccionado to set
     */
    public void setRegistroSeleccionado(EstablecimientoDto pRegistroSeleccionado) {
        this.registroSeleccionado = pRegistroSeleccionado;
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
     * @return the listMaquinas
     */
    public List<MaquinaDto> getListMaquinas() {
        return this.listMaquinas;
    }

    /**
     * @param pListMaquinas the listMaquinas to set
     */
    public void setListMaquinas(List<MaquinaDto> pListMaquinas) {
        this.listMaquinas = pListMaquinas;
    }

    /**
     * @return the listLibros
     */
    public List<LibroAutorizadoDto> getListLibros() {
        return this.listLibros;
    }

    /**
     * @param pListLibros the listLibros to set
     */
    public void setListLibros(List<LibroAutorizadoDto> pListLibros) {
        this.listLibros = pListLibros;
    }

    /**
     * @return the listLineasAereas
     */
    public List<LineaAereaDto> getListLineasAereas() {
        return this.listLineasAereas;
    }

    /**
     * @param pListLineasAereas the listLineasAereas to set
     */
    public void setListLineasAereas(List<LineaAereaDto> pListLineasAereas) {
        this.listLineasAereas = pListLineasAereas;
    }

    /**
     * @return the listEstablecimientosTemp
     */
    public List<EstablecimientoDto> getListEstablecimientosTemp() {
        return this.listEstablecimientosTemp;
    }

    /**
     * @param pListEstablecimientosTemp the listEstablecimientosTemp to set
     */
    public void setListEstablecimientosTemp(List<EstablecimientoDto> pListEstablecimientosTemp) {
        this.listEstablecimientosTemp = pListEstablecimientosTemp;
    }

    /**
     * @return the listDocumentosTemp
     */
    public List<DocumentoDto> getListDocumentosTemp() {
        return this.listDocumentosTemp;
    }

    /**
     * @param pListDocumentosTemp the listDocumentosTemp to set
     */
    public void setListDocumentosTemp(List<DocumentoDto> pListDocumentosTemp) {
        this.listDocumentosTemp = pListDocumentosTemp;
    }

    /**
     * @return the listMaquinasTemp
     */
    public List<MaquinaDto> getListMaquinasTemp() {
        return this.listMaquinasTemp;
    }

    /**
     * @param pListMaquinasTemp the listMaquinasTemp to set
     */
    public void setListMaquinasTemp(List<MaquinaDto> pListMaquinasTemp) {
        this.listMaquinasTemp = pListMaquinasTemp;
    }

    /**
     * @return the listLibrosTemp
     */
    public List<LibroAutorizadoDto> getListLibrosTemp() {
        return this.listLibrosTemp;
    }

    /**
     * @param pListLibrosTemp the listLibrosTemp to set
     */
    public void setListLibrosTemp(List<LibroAutorizadoDto> pListLibrosTemp) {
        this.listLibrosTemp = pListLibrosTemp;
    }

    /**
     * @return the listLineasAereasTemp
     */
    public List<LineaAereaDto> getListLineasAereasTemp() {
        return this.listLineasAereasTemp;
    }

    /**
     * @param pListLineasAereasTemp the listLineasAereasTemp to set
     */
    public void setListLineasAereasTemp(List<LineaAereaDto> pListLineasAereasTemp) {
        this.listLineasAereasTemp = pListLineasAereasTemp;
    }
}