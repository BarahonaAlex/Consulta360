/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.aduanas.utilidades.rmi.ImpresionDeclaracionRmi;
import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DeclaracionAduaneraDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ItemDto;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
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
public class DeclaracionesAduanerasUI extends AbstractUI implements Serializable{          
    @Resource
    private ImpresionDeclaracionRmi impresionDeclaracionRmi;
    
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DeclaracionesAduanerasUI.class);
    
    private String nit = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private List<ItemDto> listTiposOperacion;
    private List<ItemDto> listEstadosDua;
    private List<ItemDto> listAduanas;
    private List<ItemDto> listModalidades;
    private List<DeclaracionAduaneraDto> listDeclaraciones;
    private List<DeclaracionAduaneraDto> listDeclaracionesTemp;
    private DeclaracionAduaneraDto declaracionSeleccionada=new DeclaracionAduaneraDto();
    private String tipoOperacion="-";
    private String[] modalidades;
    private Date fechaInicio;
    private Date fechaFin;
    private String estadoDua="-";
    private String aduana="-";
    private boolean filtrosInactivos=false;
    private Double totalFob=new Double("0");
    private Double totalSeguro=new Double("0");
    private Double totalCif=new Double("0");
    private Double totalDai=new Double("0");
    private Double totalIva=new Double("0");
    
    @PostConstruct
    private void init(){
        try {
            if (nit==null || nit.isEmpty()) {
                this.errorMsg("No se ha recibido el par\u00e1metro esperado");
                LOG.error("No se ha recibido el parametro esperado");
            } else {
                setListTiposOperacion(generalSrvImpl.obtenerTiposOperacionAduanas());
                setListEstadosDua(generalSrvImpl.obtenerEstadosDua());
                setListAduanas(generalSrvImpl.obtenerAduanas());
                infoMsg("Defina el periodo de consulta como m\u00ednimo");
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la p\u00e1gina de declaraciones aduaneras");
            LOG.error("Error al cargar la pagina de declaraciones aduaneras", e);
        }
    }
    
    public void consultarModalidadesPorTipoOperacion(){
        try {
            modalidades=null;
            listModalidades=tipoOperacion.equals("-") ? null : generalSrvImpl.obtenerModalidadesPorRegimen(tipoOperacion);
            if(listModalidades!=null && !listModalidades.isEmpty()){
                modalidades=new String[listModalidades.size()];
                for (int i = 0; i < listModalidades.size(); i++) {
                    modalidades[i]=listModalidades.get(i).getId();
                }
            }
        } catch (Exception e) {
            errorMsg("Error al consultar la lista de modalidades");
            LOG.error("Error al consultar la lista de modalidades", e);
        }
    }
    
    public void consultar(){
        try {
            totalFob=new Double("0");
            totalSeguro=new Double("0");
            totalCif=new Double("0");
            totalDai=new Double("0");
            totalIva=new Double("0");
            
            if(filtrosInactivos){
                tipoOperacion="-";
                modalidades=null;
                listModalidades=null;
                fechaInicio=null;
                fechaFin=null;
                estadoDua="-";
                aduana="-";
                listDeclaraciones=null;
                filtrosInactivos=false;
            }else{
                if(fechaInicio.after(fechaFin)){
                    warnMsg("El per\u00edodo de consulta no es correcto");
                }else if(cantidadDiasEntreFechas(fechaInicio, fechaFin)>365){
                    warnMsg("La consulta no puede ser mayor a un a\u00f1o");
                }else{
                    listDeclaraciones=generalSrvImpl.obtenerDeclaracionesAduaneras(nit, modalidades, fechaInicio, fechaFin, estadoDua, aduana);
                    generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(22,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
                    if(listDeclaraciones==null || listDeclaraciones.isEmpty()){
                        warnMsg("No existen declaraciones que coincidan con los filtros definidos");
                    }else{                            
                        for (DeclaracionAduaneraDto declaracion : listDeclaraciones) {
                            totalFob+=Double.valueOf(declaracion.getMontoFob().replace(",", ""));
                            totalSeguro+=Double.valueOf(declaracion.getMontoSeguro().replace(",", ""));
                            totalCif+=Double.valueOf(declaracion.getMontoCif().replace(",", ""));
                            totalDai+=Double.valueOf(declaracion.getMontoDai().replace(",", ""));
                            totalIva+=Double.valueOf(declaracion.getMontoIva().replace(",", ""));
                        }
                        filtrosInactivos=true;
                    }
                }
            }
        } catch (Exception e) {
            this.errorMsg("Error al consultar las declaraciones aduaneras");
            LOG.error("Error al consultar las declaraciones aduaneras", e);
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
    
    public StreamedContent getDuaPdf(){
        try {
            byte[] dua=impresionDeclaracionRmi.obtenerFormatoDuaPDF(declaracionSeleccionada.getDeclaracion(), new ArrayList<>());

            return new DefaultStreamedContent(new ByteArrayInputStream(dua),"application/pdf","DUA_"+declaracionSeleccionada.getDeclaracion()+".pdf");
        } catch (Exception e) {
            this.errorMsg("Error al generar la DUA en formato PDF");
            LOG.error("Error al generar la DUA en formato PDF", e);
            return null;
        }
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
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

    public String getEstadoDua() {
        return estadoDua;
    }

    public void setEstadoDua(String estadoDua) {
        this.estadoDua = estadoDua;
    }

    public String getAduana() {
        return aduana;
    }

    public void setAduana(String aduana) {
        this.aduana = aduana;
    }

    public List<ItemDto> getListTiposOperacion() {
        return listTiposOperacion;
    }

    public void setListTiposOperacion(List<ItemDto> listTiposOperacion) {
        this.listTiposOperacion = listTiposOperacion;
    }

    public List<ItemDto> getListEstadosDua() {
        return listEstadosDua;
    }

    public void setListEstadosDua(List<ItemDto> listEstadosDua) {
        this.listEstadosDua = listEstadosDua;
    }

    public List<ItemDto> getListAduanas() {
        return listAduanas;
    }

    public void setListAduanas(List<ItemDto> listAduanas) {
        this.listAduanas = listAduanas;
    }

    public String[] getModalidades() {
        return modalidades;
    }

    public void setModalidades(String[] modalidades) {
        this.modalidades = modalidades;
    }

    public List<ItemDto> getListModalidades() {
        return listModalidades;
    }

    public void setListModalidades(List<ItemDto> listModalidades) {
        this.listModalidades = listModalidades;
    }

    public List<DeclaracionAduaneraDto> getListDeclaraciones() {
        return listDeclaraciones;
    }

    public void setListDeclaraciones(List<DeclaracionAduaneraDto> listDeclaraciones) {
        this.listDeclaraciones = listDeclaraciones;
    }

    public List<DeclaracionAduaneraDto> getListDeclaracionesTemp() {
        return listDeclaracionesTemp;
    }

    public void setListDeclaracionesTemp(List<DeclaracionAduaneraDto> listDeclaracionesTemp) {
        this.listDeclaracionesTemp = listDeclaracionesTemp;
    }

    public boolean isFiltrosInactivos() {
        return filtrosInactivos;
    }

    public void setFiltrosInactivos(boolean filtrosInactivos) {
        this.filtrosInactivos = filtrosInactivos;
    }

    public String getTotalFob() {
        return new DecimalFormat("###,##0.00").format(totalFob);
    }

    public void setTotalFob(Double totalFob) {
        this.totalFob = totalFob;
    }

    public String getTotalSeguro() {
        return new DecimalFormat("###,##0.00").format(totalSeguro);
    }

    public void setTotalSeguro(Double totalSeguro) {
        this.totalSeguro = totalSeguro;
    }

    public String getTotalCif() {
        return new DecimalFormat("###,##0.00").format(totalCif);
    }

    public void setTotalCif(Double totalCif) {
        this.totalCif = totalCif;
    }

    public String getTotalDai() {
        return new DecimalFormat("###,##0.00").format(totalDai);
    }

    public void setTotalDai(Double totalDai) {
        this.totalDai = totalDai;
    }

    public String getTotalIva() {
        return new DecimalFormat("###,##0.00").format(totalIva);
    }

    public void setTotalIva(Double totalIva) {
        this.totalIva = totalIva;
    }

    public DeclaracionAduaneraDto getDeclaracionSeleccionada() {
        return declaracionSeleccionada;
    }

    public void setDeclaracionSeleccionada(DeclaracionAduaneraDto declaracionSeleccionada) {
        this.declaracionSeleccionada = declaracionSeleccionada;
    }
}