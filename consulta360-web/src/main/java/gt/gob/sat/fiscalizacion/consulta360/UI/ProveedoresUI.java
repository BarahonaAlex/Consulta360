/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DetalleProveedorDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DocumentoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ProveedorDto;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
public class ProveedoresUI extends AbstractUI implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProveedoresUI.class);

    private String nit=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private String nombramiento=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNombramiento");
    private List<ProveedorDto> listProveedores;
    private ProveedorDto registroSeleccionado;
    private List<ProveedorDto> listDatos=new ArrayList<>();
    private List<DetalleProveedorDto> listDetalleProveedor;
    private List<DocumentoDto> listVerificacionesProveedor;
    private String cfTotal="0.00";
    private String cfTotalDeclarado="0.00";
    private String totalDocumentos="0.00";
    private String totalDocumentosContribuyente="0.00";
    private int seccionActiva=1;
    
    @PostConstruct
    public void inicializar(){
        try {
            if(nit==null || nit.isEmpty() || nombramiento==null || nombramiento.isEmpty()){
                this.errorMsg("No se han recibido los par\u00e1metros esperados");
                LOG.error("No se han recibido los parametros esperados");
            }else{
                setListProveedores(this.generalSrvImpl.obtenerProveedoresByNitAndNombramiento(nit, nombramiento));
                generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(18,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
                if (listProveedores==null || listProveedores.isEmpty()) {
                    warnMsg("No hay proveedores asociados");
                }
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la lista de proveedores");
            LOG.error("Error al cargar la lista de proveedores", e);
        }
    }
    
    public void verDetalle(){
        try {
            listDetalleProveedor=this.generalSrvImpl.obtenerDetalleProveedor(registroSeleccionado.getNitProveedor(), registroSeleccionado.getNumeroMesPeriodo(), registroSeleccionado.getAnioPeriodo());
            cfTotalDeclarado=this.generalSrvImpl.obtenerMontoDeclaradoByProveedor(registroSeleccionado.getNitProveedor(), registroSeleccionado.getNumeroMesPeriodo(), registroSeleccionado.getAnioPeriodo());
            cfTotal=calcularCfTotal();
            generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(19,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
            seccionActiva=2;
        } catch (Exception e) {
            this.errorMsg("Error al consultar la informaci\u00f3n del proveedor");
            LOG.error("Error al consultar la informacion del proveedor", e);
        }
    }
    
    public void listarVerificaciones(){
        try {
            listVerificacionesProveedor=this.generalSrvImpl.obtenerDocumentosVerificacion(nombramiento, registroSeleccionado);
            generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(20,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
            if(listVerificacionesProveedor==null || listVerificacionesProveedor.isEmpty()){
                this.warnMsg("No existen verificaciones registradas");
            }else{
                listDatos=new ArrayList<>();
                listDatos.add(registroSeleccionado);
                calcularTotalesIngresoResultados();
                seccionActiva=3;
            }
        } catch (Exception e) {
            this.errorMsg("Error al consultar las verificaciones");
            LOG.error("Error al consultar las verificaciones", e);
        }
    }
    
    private String calcularCfTotal(){
        float monto=0;
        for (DetalleProveedorDto row : listDetalleProveedor) {
            monto+=Float.valueOf(row.getMonto().replace(",", ""));
        }
        
        return new DecimalFormat("###,##0.00").format(monto);
    }
    
    private void calcularTotalesIngresoResultados(){
        float totalDocumentosTemp=0, totalDocumentosContribuyenteTemp=0;
        for (DocumentoDto verificacion : listVerificacionesProveedor) {
            totalDocumentosTemp+=Float.valueOf(verificacion.getTotal().replace(",", ""));
            totalDocumentosContribuyenteTemp+=Float.valueOf(verificacion.getTotalCruce().replace(",", ""));
        }
        
        totalDocumentos=new DecimalFormat("###,##0.00").format(totalDocumentosTemp);
        totalDocumentosContribuyente=new DecimalFormat("###,##0.00").format(totalDocumentosContribuyenteTemp);
    }
    
    /**
     * @return the listProveedores
     */
    public List<ProveedorDto> getListProveedores() {
        return this.listProveedores;
    }

    /**
     * @param pListProveedores the listProveedores to set
     */
    public void setListProveedores(List<ProveedorDto> pListProveedores) {
        this.listProveedores = pListProveedores;
    }

    /**
     * @return the registroSeleccionado
     */
    public ProveedorDto getRegistroSeleccionado() {
        return this.registroSeleccionado;
    }

    /**
     * @param pRegistroSeleccionado the registroSeleccionado to set
     */
    public void setRegistroSeleccionado(ProveedorDto pRegistroSeleccionado) {
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
    }

    /**
     * @return the listDetalleProveedor
     */
    public List<DetalleProveedorDto> getListDetalleProveedor() {
        return this.listDetalleProveedor;
    }

    /**
     * @param pListDetalleProveedor the listDetalleProveedor to set
     */
    public void setListDetalleProveedor(List<DetalleProveedorDto> pListDetalleProveedor) {
        this.listDetalleProveedor = pListDetalleProveedor;
    }

    public List<DocumentoDto> getListVerificacionesProveedor() {
        return listVerificacionesProveedor;
    }

    public void setListVerificacionesProveedor(List<DocumentoDto> listVerificacionesProveedor) {
        this.listVerificacionesProveedor = listVerificacionesProveedor;
    }

    /**
     * @return the nombramiento
     */
    public String getNombramiento() {
        return nombramiento;
    }

    /**
     * @param nombramiento the nombramiento to set
     */
    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public List<ProveedorDto> getListDatos() {
        return listDatos;
    }

    public void setListDatos(List<ProveedorDto> listDatos) {
        this.listDatos = listDatos;
    }

    public String getTotalDocumentos() {
        return totalDocumentos;
    }

    public void setTotalDocumentos(String totalDocumentos) {
        this.totalDocumentos = totalDocumentos;
    }

    public String getTotalDocumentosContribuyente() {
        return totalDocumentosContribuyente;
    }

    public void setTotalDocumentosContribuyente(String totalDocumentosContribuyente) {
        this.totalDocumentosContribuyente = totalDocumentosContribuyente;
    }

    public String getCfTotal() {
        return cfTotal;
    }

    public void setCfTotal(String cfTotal) {
        this.cfTotal = cfTotal;
    }

    public String getCfTotalDeclarado() {
        return cfTotalDeclarado;
    }

    public void setCfTotalDeclarado(String cfTotalDeclarado) {
        this.cfTotalDeclarado = cfTotalDeclarado;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
}