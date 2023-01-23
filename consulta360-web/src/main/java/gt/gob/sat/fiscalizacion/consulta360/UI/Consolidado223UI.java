/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.Consolidado223Dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author arapenam
 */
@Controller
@Scope("session")
public class Consolidado223UI extends AbstractUI implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Consolidado223UI.class);

    private String nit;
    private String nombreContribuyente;
    private Consolidado223Dto consolidado;
    private Consolidado223Dto remanenteConsolidado;
    private Consolidado223Dto creditoFiscalConsolidado;
    private Date fechaDel;
    private Date fechaAl;
    private String anio;
    private BigDecimal sumatoriaCredLocal;
    private BigDecimal sumatoriaCredExpo;
    private BigDecimal totalDeterminacion;
    private BigDecimal creditoFiscalExp;
    private BigDecimal saldoImpuesto;
    private BigDecimal remanenteRetencionIVA;
    private BigDecimal saldoRetenciones;
    private BigDecimal subTotalImpuesto;
    private BigDecimal impuestoPagar;
    private String periodo;

    public void consolidado223() throws ParseException {

        if (fechaDel == null || fechaAl == null) {
            this.warnMsg("Complete la informaci\u00f3n solicitada");
        } else {
            LOG.info("Ingresando a consolidado");
            SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
            Locale locale = new Locale("es", "ES");
            DateFormat formFe = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);            
            setPeriodo("DEL " + formFe.format(fechaDel) + " AL " + formFe.format(fechaAl));
            setPeriodo(periodo.toUpperCase());
            setAnio(new SimpleDateFormat("yyyy").format(fechaDel));
            setConsolidado(this.generalSrvImpl.findDatosConsolidado223(getNit(), form.format(fechaDel), form.format(fechaAl)));
            setRemanenteConsolidado(this.generalSrvImpl.findDatosRemanenteConsolidado223(getNit(), form.format(fechaDel), form.format(fechaAl)));
            setCreditoFiscalConsolidado(this.generalSrvImpl.findDatosCreditoFiscalConsolidado223(getNit(), form.format(fechaDel), form.format(fechaAl)));
            generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(11,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
            operacionesRemanente();
            
            RequestContext.getCurrentInstance().execute("window.open('" + "consolidado223.jsf" + "', '_newtab')");
        }

    }

    public void operacionesRemanente() {

        //-------------Sumatoria de las columnas  CRÉDITOS LOCAL--------------
        sumatoriaCredLocal = this.consolidado.getSumatoriaColBaseCreditosCred();
        sumatoriaCredLocal = sumatoriaCredLocal.subtract(getConsolidado().getRemanenteCreditoPeriodoAnt());
        sumatoriaCredLocal = sumatoriaCredLocal.add(getRemanenteConsolidado().getRemanente5());
        //Validar si el resultado de la operaciÃƒÂ³n es menos a cero (0), se mostrara cero.
        this.sumatoriaCredLocal = (this.sumatoriaCredLocal.intValue()) < 0 ? new BigDecimal(0) : this.sumatoriaCredLocal;

        
        
        /*---------------Sumatoria de las columnas  CRÉDITOS exportacion-----------*/
        sumatoriaCredExpo = consolidado.getSumatoriaColBaseCredCredExpo();
        sumatoriaCredExpo = sumatoriaCredExpo.subtract(consolidado.getRemanenteCreditoPerAntExpo());
        sumatoriaCredExpo = sumatoriaCredExpo.add(remanenteConsolidado.getRemanente6());
        //Validar si el resultado de la operaciÃƒÂ³n es menos a cero (0), se mostrara cero.
        this.sumatoriaCredExpo = (this.sumatoriaCredExpo.intValue()) < 0 ? new BigDecimal(0) : this.sumatoriaCredExpo;

        
        
        /*---------------Total determinación del Crédito Fiscal------------------*/
        totalDeterminacion = consolidado.getToralDeterminacionCredFiscal();
        totalDeterminacion = totalDeterminacion.subtract(consolidado.getRemanenteCreditoPeriodoAnt());
        totalDeterminacion = totalDeterminacion.add(getRemanenteConsolidado().getRemanente5());

        totalDeterminacion = totalDeterminacion.subtract(consolidado.getRemanenteCreditoPerAntExpo());
        totalDeterminacion = totalDeterminacion.add(remanenteConsolidado.getRemanente6());
        //Validar si el resultado de la operaciÃƒÂ³n es menos a cero (0), se mostrara cero.
        this.totalDeterminacion = (this.totalDeterminacion.intValue()) < 0 ? new BigDecimal(0) : this.totalDeterminacion;

        
        
        /*---------------Crédito fiscal para el período siguiente por operaciones de exportación-----------------*/
        creditoFiscalExp = sumatoriaCredExpo.subtract(consolidado.getCreditoRecibidoExportadores()).subtract(consolidado.getDebitoFactExportadores());
        //Validar si el resultado de la operaciÃƒÂ³n es menos a cero (0), se mostrara cero.
        this.creditoFiscalExp = (this.creditoFiscalExp.intValue()) < 0 ? new BigDecimal(0) : this.creditoFiscalExp;

        /*-------------------------------------SALDO DEL IMPUESTO--------------------------------*/
        BigDecimal debitoMayor = consolidado.getImpuestoTotalDeterminadoLocal().add(consolidado.getImpuestoTotalDeterminadoExpo());
        BigDecimal creditoMayor = consolidado.getCreditoFiscalSigPeriodo().add(consolidado.getCreditoFiscalOperacionesExpo());
        saldoImpuesto = debitoMayor.subtract(creditoMayor);
        this.saldoImpuesto = (this.saldoImpuesto.intValue()) < 0 ? new BigDecimal(0) : this.saldoImpuesto;

        
        /*---------------------(=) Remanente de retenciones del IVA recibidas en el período---------------------*/
        remanenteRetencionIVA = remanenteConsolidado.getRemanente7().subtract(consolidado.getMontoAcreditado());
        //Validar si el resultado de la operaciÃƒÂ³n es menos a cero (0), se mostrara cero.
        this.remanenteRetencionIVA = (this.remanenteRetencionIVA.intValue()) < 0 ? new BigDecimal(0) : this.remanenteRetencionIVA;

        
        
        /**---------------------Saldo de retenciones para el período siguiente------------------------------------*/
        saldoRetenciones = remanenteRetencionIVA.add(consolidado.getConstanciasRetencionIvaRecPer()).subtract(this.saldoImpuesto);
        this.saldoRetenciones = (this.saldoRetenciones.intValue()) < 0 ? new BigDecimal(0) : this.saldoRetenciones;

        

        /*------------------------------------------ SUB-TOTAL DEL IMPUESTO------------------------------*/
        subTotalImpuesto = this.saldoImpuesto.subtract(remanenteRetencionIVA).subtract(consolidado.getConstanciasRetencionIvaRecPer());
        this.subTotalImpuesto = (this.subTotalImpuesto.intValue()) < 0 ? new BigDecimal(0) : this.subTotalImpuesto;

        

        /*--------------------------------------------(=) Impuesto a pagar-----------------------------------*/
        impuestoPagar = this.subTotalImpuesto.subtract(consolidado.getValorCompensarPeriodoActual());
        this.impuestoPagar = (this.impuestoPagar.intValue()) < 0 ? new BigDecimal(0) : this.impuestoPagar;
    }

    //Total determinación del Crédito Fiscal
    /**
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param pNit the nit to set
     */
    public void setNit(String pNit) {
        this.nit = pNit;
    }

    /**
     * @return the nombreContribuyente
     */
    public String getNombreContribuyente() {
        return nombreContribuyente;
    }

    /**
     * @param pNombreContribuyente the nombreContribuyente to set
     */
    public void setNombreContribuyente(String pNombreContribuyente) {
        this.nombreContribuyente = pNombreContribuyente;
    }

    /**
     * @return the consolidado
     */
    public Consolidado223Dto getConsolidado() {
        if (consolidado == null) {
            consolidado = new Consolidado223Dto();
        }
        return consolidado;
    }

    /**
     * @param pConsolidado the consolidado to set
     */
    public void setConsolidado(Consolidado223Dto pConsolidado) {
        this.consolidado = pConsolidado;
    }

    /**
     * @return the fechaDel
     */
    public Date getFechaDel() {
        return this.fechaDel;
    }

    /**
     * @param pFechaDel the fechaDel to set
     */
    public void setFechaDel(Date pFechaDel) {
        this.fechaDel = pFechaDel;
    }

    /**
     * @return the fechaAl
     */
    public Date getFechaAl() {
        return this.fechaAl;
    }

    /**
     * @param pFechaAl the fechaAl to set
     */
    public void setFechaAl(Date pFechaAl) {
        this.fechaAl = pFechaAl;
    }

    /**
     * @return the anio
     */
    public String getAnio() {
        return anio;
    }

    /**
     * @param pAnio the anio to set
     */
    public void setAnio(String pAnio) {
        this.anio = pAnio;
    }

    /**
     * @return the remanenteConsolidado
     */
    public Consolidado223Dto getRemanenteConsolidado() {
        if (remanenteConsolidado == null) {
            remanenteConsolidado = new Consolidado223Dto();
        }
        return remanenteConsolidado;
    }

    /**
     * @param pRemanenteConsolidado the remanenteConsolidado to set
     */
    public void setRemanenteConsolidado(Consolidado223Dto pRemanenteConsolidado) {
        this.remanenteConsolidado = pRemanenteConsolidado;
    }

    /**
     * @return the sumatoriaCredLocal
     */
    public BigDecimal getSumatoriaCredLocal() {
        return sumatoriaCredLocal;
    }

    /**
     * @param pSumatoriaCredLocal the sumatoriaCredLocal to set
     */
    public void setSumatoriaCredLocal(BigDecimal pSumatoriaCredLocal) {
        this.sumatoriaCredLocal = pSumatoriaCredLocal;
    }

    /**
     * @return the sumatoriaCredExpo
     */
    public BigDecimal getSumatoriaCredExpo() {
        return sumatoriaCredExpo;
    }

    /**
     * @param pSumatoriaCredExpo the sumatoriaCredExpo to set
     */
    public void setSumatoriaCredExpo(BigDecimal pSumatoriaCredExpo) {
        this.sumatoriaCredExpo = pSumatoriaCredExpo;
    }

    /**
     * @return the totalDeterminacion
     */
    public BigDecimal getTotalDeterminacion() {
        return totalDeterminacion;
    }

    /**
     * @param pTotalDeterminacion the totalDeterminacion to set
     */
    public void setTotalDeterminacion(BigDecimal pTotalDeterminacion) {
        this.totalDeterminacion = pTotalDeterminacion;
    }

    /**
     * @return the creditoFiscalExp
     */
    public BigDecimal getCreditoFiscalExp() {
        return creditoFiscalExp;
    }

    /**
     * @param pCreditoFiscalExp the creditoFiscalExp to set
     */
    public void setCreditoFiscalExp(BigDecimal pCreditoFiscalExp) {
        this.creditoFiscalExp = pCreditoFiscalExp;
    }

    /**
     * @return the creditoFiscalConsolidado
     */
    public Consolidado223Dto getCreditoFiscalConsolidado() {
        if (creditoFiscalConsolidado == null) {
            creditoFiscalConsolidado = new Consolidado223Dto();
        }
        return creditoFiscalConsolidado;
    }

    /**
     * @param pCreditoFiscalConsolidado the creditoFiscalConsolidado to set
     */
    public void setCreditoFiscalConsolidado(Consolidado223Dto pCreditoFiscalConsolidado) {
        this.creditoFiscalConsolidado = pCreditoFiscalConsolidado;
    }

    /**
     * @return the saldoImpuesto
     */
    public BigDecimal getSaldoImpuesto() {
        return saldoImpuesto;
    }

    /**
     * @param pSaldoImpuesto the saldoImpuesto to set
     */
    public void setSaldoImpuesto(BigDecimal pSaldoImpuesto) {
        this.saldoImpuesto = pSaldoImpuesto;
    }

    /**
     * @return the remanenteRetencionIVA
     */
    public BigDecimal getRemanenteRetencionIVA() {
        return remanenteRetencionIVA;
    }

    /**
     * @param pRemanenteRetencionIVA the remanenteRetencionIVA to set
     */
    public void setRemanenteRetencionIVA(BigDecimal pRemanenteRetencionIVA) {
        this.remanenteRetencionIVA = pRemanenteRetencionIVA;
    }

    /**
     * @return the saldoRetenciones
     */
    public BigDecimal getSaldoRetenciones() {
        return saldoRetenciones;
    }

    /**
     * @param pSaldoRetenciones the saldoRetenciones to set
     */
    public void setSaldoRetenciones(BigDecimal pSaldoRetenciones) {
        this.saldoRetenciones = pSaldoRetenciones;
    }

    /**
     * @return the subTotalImpuesto
     */
    public BigDecimal getSubTotalImpuesto() {
        return subTotalImpuesto;
    }

    /**
     * @param pSubTotalImpuesto the subTotalImpuesto to set
     */
    public void setSubTotalImpuesto(BigDecimal pSubTotalImpuesto) {
        this.subTotalImpuesto = pSubTotalImpuesto;
    }

    /**
     * @return the impuestoPagar
     */
    public BigDecimal getImpuestoPagar() {
        return impuestoPagar;
    }

    /**
     * @param pImpuestoPagar the impuestoPagar to set
     */
    public void setImpuestoPagar(BigDecimal pImpuestoPagar) {
        this.impuestoPagar = pImpuestoPagar;
    }

    /**
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

}
