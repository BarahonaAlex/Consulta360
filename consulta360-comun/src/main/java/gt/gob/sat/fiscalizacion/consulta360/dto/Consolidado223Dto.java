package gt.gob.sat.fiscalizacion.consulta360.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author arapenam
 */
public class Consolidado223Dto implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal ventaExenta;
    private BigDecimal ventaMedicamentos;
    private BigDecimal ventasNoAfectas;
    private BigDecimal ventasVehiculos;
    private BigDecimal ventaVehiculosAct;
    private BigDecimal ventaVehiculoActDeb;
    private BigDecimal baseVentasGrabadas;
    private BigDecimal debitoVentasGrabadas;
    private BigDecimal baseServiciosPrestados;
    private BigDecimal debidoServiciosPrestados;
    private BigDecimal sumaBaseDebitos;
    private BigDecimal sumatoriaDebLocales;
    private BigDecimal exportacionCa;
    private BigDecimal exportacionMundo;
    private BigDecimal ventasMedicamentosExpo;
    private BigDecimal ventasVehiculosExpo;
    private BigDecimal ventasVehiculoActExpo;
    private BigDecimal sumaBaseDebitosExpo;
    private BigDecimal creditoRecibidoExportadores;
    private BigDecimal debitoFactExportadores;
    private BigDecimal sumatoriaDebitosExp;
    private BigDecimal compraMedicamentos;
    private BigDecimal comprasPeqContribuyentes;
    private BigDecimal comprasNoAfectas;
    private BigDecimal comprasVehiculos;
    private BigDecimal comprasVehiculosAct;
    private BigDecimal comprasVehiculosActCred;
    private BigDecimal comprasCombustibles;
    private BigDecimal creditosComprasCombustible;
    private BigDecimal otrasComprasBase;
    private BigDecimal otrasComprasCreditos;
    private BigDecimal baseServAdquirido;
    private BigDecimal creditosServAdquiridos;
    private BigDecimal importacionesCaBase;
    private BigDecimal importacionesCaCreditos;
    private BigDecimal importacionesMundoBase;
    private BigDecimal importacionesMundoCreditos;
    private BigDecimal comprasActivosFijosBase;
    private BigDecimal comprasActivosFijosCreditos;
    private BigDecimal importacionesActivosFijosBase;
    private BigDecimal importacionesActivosFijosCred;
    private BigDecimal ivaExencionRecibida;
    private BigDecimal remanenteCreditoPeriodoAnt;
    private BigDecimal sumatoriaColBaseCreditosBase;
    private BigDecimal sumatoriaColBaseCreditosCred;
    private BigDecimal comprasServiciosAdquiridosPeq;
    private BigDecimal comprasCombustiblesBaseExpo;
    private BigDecimal comprasCombustiblesCredExpo;
    private BigDecimal otrasComprasBaseExpo;
    private BigDecimal otrasComprasCreditosExpo;
    private BigDecimal serviciosADquiridosBase;
    private BigDecimal serviciosAdquiridosCreditos;
    private BigDecimal importacionesCaBaseExpo;
    private BigDecimal importaciones;
    private BigDecimal importacionesMundoBaseExpo;
    private BigDecimal importacionesMundoCreditoExpo;
    private BigDecimal comprasActivosVincBaseExpo;
    private BigDecimal comprasActivosVincCrediosExpo;
    private BigDecimal importacionesFijosVincBaseExpo;
    private BigDecimal importacionesFijosVincCredExpo;
    private BigDecimal remanenteCreditoPerAntExpo;
    private BigDecimal creditoFacturasEmitidasExpo;
    private BigDecimal retencionPracticadasExpo;
    private BigDecimal sumatoriaColBaseCredBaseExpo;
    private BigDecimal sumatoriaColBaseCredCredExpo;
    private BigDecimal toralDeterminacionCredFiscal;
    private BigDecimal creditoFiscalSigPeriodo;
    private BigDecimal creditoFiscalOperacionesExpo;
    private BigDecimal impuestoTotalDeterminadoLocal;
    private BigDecimal impuestoTotalDeterminadoExpo;
    private BigDecimal creditoFiscalPerSigOpExentas;
    private BigDecimal remanenteRetencionIvaPerAnt;
    private BigDecimal numeroResolucion;
    private BigDecimal montoAcreditado;
    private BigDecimal remanenteRetencionIvaRecPer;
    private BigDecimal constanciasRetencionIvaRecPer;
    private BigDecimal saldoRetencionPeriodoSig;
    private BigDecimal subTotalImpuesto;
    private BigDecimal numeroResolucionCompensacion;
    private BigDecimal saldoNoCompensado;
    private BigDecimal valorCompensarPeriodoActual;
    private BigDecimal impuestoPagar;
    private BigDecimal indicadoresComerciales;
    private BigDecimal razonVenta;
    private BigDecimal cantidadFacturasEmitidas;
    private BigDecimal cantidadFacturasRecibidas;
    private BigDecimal cantidadConstanciasExencionEm;
    private BigDecimal cantidadConstanciasExencionRec;
    private BigDecimal cantConstanciasAdqInsumosEm;
    private BigDecimal cantConstanciasAdqInsumosRec;
    private BigDecimal cantConstanciasRetencionIvaEm;
    private BigDecimal cantConstanciasRetencionIvaRec;
    private BigDecimal cantFacturasEspecialesEm;
    private BigDecimal cantidadFacturasEspecialesRec;
    private BigDecimal numeroFormularioRectifica;
    private BigDecimal impuestoIngresadoFormularioRec;
    private BigDecimal impuestoPagarRect;
    private BigDecimal impuestoFavorContribuyente;
    private BigDecimal multaFormal;
    private BigDecimal multaOmision;
    private BigDecimal multaRectificacion;
    private BigDecimal intereses;
    private BigDecimal mora;
    private BigDecimal accesoriosPagar;
    private BigDecimal totalPagar;
    private BigDecimal saldoImpuesto;

    private BigDecimal remanente5;
    private BigDecimal remanente6;
    private BigDecimal remanente7;

    private BigDecimal credFiscalLocal;
    private BigDecimal crediFiscalExp;

    /**
     * @return the ventaExenta
     */
    public BigDecimal getVentaExenta() {
        return ventaExenta;
    }

    /**
     * @param pVentaExenta the ventaExenta to set
     */
    public void setVentaExenta(BigDecimal pVentaExenta) {
        this.ventaExenta = pVentaExenta;
    }

    /**
     * @return the ventaMedicamentos
     */
    public BigDecimal getVentaMedicamentos() {
        return ventaMedicamentos;
    }

    /**
     * @param pVentaMedicamentos the ventaMedicamentos to set
     */
    public void setVentaMedicamentos(BigDecimal pVentaMedicamentos) {
        this.ventaMedicamentos = pVentaMedicamentos;
    }

    /**
     * @return the ventasNoAfectas
     */
    public BigDecimal getVentasNoAfectas() {
        return ventasNoAfectas;
    }

    /**
     * @param pVentasNoAfectas the ventasNoAfectas to set
     */
    public void setVentasNoAfectas(BigDecimal pVentasNoAfectas) {
        this.ventasNoAfectas = pVentasNoAfectas;
    }

    /**
     * @return the ventasVehiculos
     */
    public BigDecimal getVentasVehiculos() {
        return ventasVehiculos;
    }

    /**
     * @param pVentasVehiculos the ventasVehiculos to set
     */
    public void setVentasVehiculos(BigDecimal pVentasVehiculos) {
        this.ventasVehiculos = pVentasVehiculos;
    }

    /**
     * @return the ventaVehiculosAct
     */
    public BigDecimal getVentaVehiculosAct() {
        return ventaVehiculosAct;
    }

    /**
     * @param pVentaVehiculosAct the ventaVehiculosAct to set
     */
    public void setVentaVehiculosAct(BigDecimal pVentaVehiculosAct) {
        this.ventaVehiculosAct = pVentaVehiculosAct;
    }

    /**
     * @return the ventaVehiculoActDeb
     */
    public BigDecimal getVentaVehiculoActDeb() {
        return ventaVehiculoActDeb;
    }

    /**
     * @param pVentaVehiculoActDeb the ventaVehiculoActDeb to set
     */
    public void setVentaVehiculoActDeb(BigDecimal pVentaVehiculoActDeb) {
        this.ventaVehiculoActDeb = pVentaVehiculoActDeb;
    }

    /**
     * @return the baseVentasGrabadas
     */
    public BigDecimal getBaseVentasGrabadas() {
        return baseVentasGrabadas;
    }

    /**
     * @param pBaseVentasGrabadas the baseVentasGrabadas to set
     */
    public void setBaseVentasGrabadas(BigDecimal pBaseVentasGrabadas) {
        this.baseVentasGrabadas = pBaseVentasGrabadas;
    }

    /**
     * @return the debitoVentasGrabadas
     */
    public BigDecimal getDebitoVentasGrabadas() {
        return debitoVentasGrabadas;
    }

    /**
     * @param pDebitoVentasGrabadas the debitoVentasGrabadas to set
     */
    public void setDebitoVentasGrabadas(BigDecimal pDebitoVentasGrabadas) {
        this.debitoVentasGrabadas = pDebitoVentasGrabadas;
    }

    /**
     * @return the baseServiciosPrestados
     */
    public BigDecimal getBaseServiciosPrestados() {
        return baseServiciosPrestados;
    }

    /**
     * @param pBaseServiciosPrestados the baseServiciosPrestados to set
     */
    public void setBaseServiciosPrestados(BigDecimal pBaseServiciosPrestados) {
        this.baseServiciosPrestados = pBaseServiciosPrestados;
    }

    /**
     * @return the debidoServiciosPrestados
     */
    public BigDecimal getDebidoServiciosPrestados() {
        return debidoServiciosPrestados;
    }

    /**
     * @param pDebidoServiciosPrestados the debidoServiciosPrestados to set
     */
    public void setDebidoServiciosPrestados(BigDecimal pDebidoServiciosPrestados) {
        this.debidoServiciosPrestados = pDebidoServiciosPrestados;
    }

    /**
     * @return the sumaBaseDebitos
     */
    public BigDecimal getSumaBaseDebitos() {
        return sumaBaseDebitos;
    }

    /**
     * @param pSumaBaseDebitos the sumaBaseDebitos to set
     */
    public void setSumaBaseDebitos(BigDecimal pSumaBaseDebitos) {
        this.sumaBaseDebitos = pSumaBaseDebitos;
    }

    /**
     * @return the sumatoriaDebLocales
     */
    public BigDecimal getSumatoriaDebLocales() {
        return sumatoriaDebLocales;
    }

    /**
     * @param pSumatoriaDebLocales the sumatoriaDebLocales to set
     */
    public void setSumatoriaDebLocales(BigDecimal pSumatoriaDebLocales) {
        this.sumatoriaDebLocales = pSumatoriaDebLocales;
    }

    /**
     * @return the exportacionCa
     */
    public BigDecimal getExportacionCa() {
        return exportacionCa;
    }

    /**
     * @param pExportacionCa the exportacionCa to set
     */
    public void setExportacionCa(BigDecimal pExportacionCa) {
        this.exportacionCa = pExportacionCa;
    }

    /**
     * @return the exportacionMundo
     */
    public BigDecimal getExportacionMundo() {
        return exportacionMundo;
    }

    /**
     * @param pExportacionMundo the exportacionMundo to set
     */
    public void setExportacionMundo(BigDecimal pExportacionMundo) {
        this.exportacionMundo = pExportacionMundo;
    }

    /**
     * @return the ventasMedicamentosExpo
     */
    public BigDecimal getVentasMedicamentosExpo() {
        return ventasMedicamentosExpo;
    }

    /**
     * @param pEentasMedicamentosExpo the ventasMedicamentosExpo to set
     */
    public void setVentasMedicamentosExpo(BigDecimal pEentasMedicamentosExpo) {
        this.ventasMedicamentosExpo = pEentasMedicamentosExpo;
    }

    /**
     * @return the ventasVehiculosExpo
     */
    public BigDecimal getVentasVehiculosExpo() {
        return ventasVehiculosExpo;
    }

    /**
     * @param pVentasVehiculosExpo the ventasVehiculosExpo to set
     */
    public void setVentasVehiculosExpo(BigDecimal pVentasVehiculosExpo) {
        this.ventasVehiculosExpo = pVentasVehiculosExpo;
    }

    /**
     * @return the ventasVehiculoActExpo
     */
    public BigDecimal getVentasVehiculoActExpo() {
        return ventasVehiculoActExpo;
    }

    /**
     * @param pVentasVehiculoActExpo the ventasVehiculoActExpo to set
     */
    public void setVentasVehiculoActExpo(BigDecimal pVentasVehiculoActExpo) {
        this.ventasVehiculoActExpo = pVentasVehiculoActExpo;
    }

    /**
     * @return the sumaBaseDebitosExpo
     */
    public BigDecimal getSumaBaseDebitosExpo() {
        return sumaBaseDebitosExpo;
    }

    /**
     * @param pSumaBaseDebitosExpo the sumaBaseDebitosExpo to set
     */
    public void setSumaBaseDebitosExpo(BigDecimal pSumaBaseDebitosExpo) {
        this.sumaBaseDebitosExpo = pSumaBaseDebitosExpo;
    }

    /**
     * @return the creditoRecibidoExportadores
     */
    public BigDecimal getCreditoRecibidoExportadores() {
        return creditoRecibidoExportadores;
    }

    /**
     * @param pCreditoRecibidoExportadores the creditoRecibidoExportadores to
     * set
     */
    public void setCreditoRecibidoExportadores(BigDecimal pCreditoRecibidoExportadores) {
        this.creditoRecibidoExportadores = pCreditoRecibidoExportadores;
    }

    /**
     * @return the debitoFactExportadores
     */
    public BigDecimal getDebitoFactExportadores() {
        return debitoFactExportadores;
    }

    /**
     * @param pDebitoFactExportadores the debitoFactExportadores to set
     */
    public void setDebitoFactExportadores(BigDecimal pDebitoFactExportadores) {
        this.debitoFactExportadores = pDebitoFactExportadores;
    }

    /**
     * @return the sumatoriaDebitosExp
     */
    public BigDecimal getSumatoriaDebitosExp() {
        return sumatoriaDebitosExp;
    }

    /**
     * @param pSumatoriaDebitosExp the sumatoriaDebitosExp to set
     */
    public void setSumatoriaDebitosExp(BigDecimal pSumatoriaDebitosExp) {
        this.sumatoriaDebitosExp = pSumatoriaDebitosExp;
    }

    /**
     * @return the compraMedicamentos
     */
    public BigDecimal getCompraMedicamentos() {
        return compraMedicamentos;
    }

    /**
     * @param pCompraMedicamentos the compraMedicamentos to set
     */
    public void setCompraMedicamentos(BigDecimal pCompraMedicamentos) {
        this.compraMedicamentos = pCompraMedicamentos;
    }

    /**
     * @return the comprasPeqContribuyentes
     */
    public BigDecimal getComprasPeqContribuyentes() {
        return comprasPeqContribuyentes;
    }

    /**
     * @param pComprasPeqContribuyentes the comprasPeqContribuyentes to set
     */
    public void setComprasPeqContribuyentes(BigDecimal pComprasPeqContribuyentes) {
        this.comprasPeqContribuyentes = pComprasPeqContribuyentes;
    }

    /**
     * @return the comprasNoAfectas
     */
    public BigDecimal getComprasNoAfectas() {
        return comprasNoAfectas;
    }

    /**
     * @param pComprasNoAfectas the comprasNoAfectas to set
     */
    public void setComprasNoAfectas(BigDecimal pComprasNoAfectas) {
        this.comprasNoAfectas = pComprasNoAfectas;
    }

    /**
     * @return the comprasVehiculos
     */
    public BigDecimal getComprasVehiculos() {
        return comprasVehiculos;
    }

    /**
     * @param pComprasVehiculos the comprasVehiculos to set
     */
    public void setComprasVehiculos(BigDecimal pComprasVehiculos) {
        this.comprasVehiculos = pComprasVehiculos;
    }

    /**
     * @return the comprasVehiculosAct
     */
    public BigDecimal getComprasVehiculosAct() {
        return comprasVehiculosAct;
    }

    /**
     * @param pComprasVehiculosAct the comprasVehiculosAct to set
     */
    public void setComprasVehiculosAct(BigDecimal pComprasVehiculosAct) {
        this.comprasVehiculosAct = pComprasVehiculosAct;
    }

    /**
     * @return the comprasVehiculosActCred
     */
    public BigDecimal getComprasVehiculosActCred() {
        return comprasVehiculosActCred;
    }

    /**
     * @param pComprasVehiculosActCred the comprasVehiculosActCred to set
     */
    public void setComprasVehiculosActCred(BigDecimal pComprasVehiculosActCred) {
        this.comprasVehiculosActCred = pComprasVehiculosActCred;
    }

    /**
     * @return the comprasCombustibles
     */
    public BigDecimal getComprasCombustibles() {
        return comprasCombustibles;
    }

    /**
     * @param pComprasCombustibles the comprasCombustibles to set
     */
    public void setComprasCombustibles(BigDecimal pComprasCombustibles) {
        this.comprasCombustibles = pComprasCombustibles;
    }

    /**
     * @return the creditosComprasCombustible
     */
    public BigDecimal getCreditosComprasCombustible() {
        return creditosComprasCombustible;
    }

    /**
     * @param pCreditosComprasCombustible the creditosComprasCombustible to set
     */
    public void setCreditosComprasCombustible(BigDecimal pCreditosComprasCombustible) {
        this.creditosComprasCombustible = pCreditosComprasCombustible;
    }

    /**
     * @return the otrasComprasBase
     */
    public BigDecimal getOtrasComprasBase() {
        return otrasComprasBase;
    }

    /**
     * @param pOtrasComprasBase the otrasComprasBase to set
     */
    public void setOtrasComprasBase(BigDecimal pOtrasComprasBase) {
        this.otrasComprasBase = pOtrasComprasBase;
    }

    /**
     * @return the otrasComprasCreditos
     */
    public BigDecimal getOtrasComprasCreditos() {
        return otrasComprasCreditos;
    }

    /**
     * @param pOtrasComprasCreditos the otrasComprasCreditos to set
     */
    public void setOtrasComprasCreditos(BigDecimal pOtrasComprasCreditos) {
        this.otrasComprasCreditos = pOtrasComprasCreditos;
    }

    /**
     * @return the baseServAdquiridos
     */
    public BigDecimal getBaseServAdquiridos() {
        return baseServAdquirido;
    }

    /**
     * @param pBaseServAdquiridos the baseServAdquiridos to set
     */
    public void setBaseServAdquiridos(BigDecimal pBaseServAdquiridos) {
        this.baseServAdquirido = pBaseServAdquiridos;
    }

    /**
     * @return the creditosServAdquiridos
     */
    public BigDecimal getCreditosServAdquiridos() {
        return creditosServAdquiridos;
    }

    /**
     * @param pCreditosServAdquiridos the creditosServAdquiridos to set
     */
    public void setCreditosServAdquiridos(BigDecimal pCreditosServAdquiridos) {
        this.creditosServAdquiridos = pCreditosServAdquiridos;
    }

    /**
     * @return the importacionesCaBase
     */
    public BigDecimal getImportacionesCaBase() {
        return importacionesCaBase;
    }

    /**
     * @param pImportacionesCaBase the importacionesCaBase to set
     */
    public void setImportacionesCaBase(BigDecimal pImportacionesCaBase) {
        this.importacionesCaBase = pImportacionesCaBase;
    }

    /**
     * @return the importacionesCaCreditos
     */
    public BigDecimal getImportacionesCaCreditos() {
        return importacionesCaCreditos;
    }

    /**
     * @param pImportacionesCaCreditos the importacionesCaCreditos to set
     */
    public void setImportacionesCaCreditos(BigDecimal pImportacionesCaCreditos) {
        this.importacionesCaCreditos = pImportacionesCaCreditos;
    }

    /**
     * @return the importacionesMundoBase
     */
    public BigDecimal getImportacionesMundoBase() {
        return importacionesMundoBase;
    }

    /**
     * @param pImportacionesMundoBase the importacionesMundoBase to set
     */
    public void setImportacionesMundoBase(BigDecimal pImportacionesMundoBase) {
        this.importacionesMundoBase = pImportacionesMundoBase;
    }

    /**
     * @return the importacionesMundoCreditos
     */
    public BigDecimal getImportacionesMundoCreditos() {
        return importacionesMundoCreditos;
    }

    /**
     * @param pImportacionesMundoCreditos the importacionesMundoCreditos to set
     */
    public void setImportacionesMundoCreditos(BigDecimal pImportacionesMundoCreditos) {
        this.importacionesMundoCreditos = pImportacionesMundoCreditos;
    }

    /**
     * @return the comprasActivosFijosBase
     */
    public BigDecimal getComprasActivosFijosBase() {
        return comprasActivosFijosBase;
    }

    /**
     * @param pComprasActivosFijosBase the comprasActivosFijosBase to set
     */
    public void setComprasActivosFijosBase(BigDecimal pComprasActivosFijosBase) {
        this.comprasActivosFijosBase = pComprasActivosFijosBase;
    }

    /**
     * @return the comprasActivosFijosCreditos
     */
    public BigDecimal getComprasActivosFijosCreditos() {
        return comprasActivosFijosCreditos;
    }

    /**
     * @param pComprasActivosFijosCreditos the comprasActivosFijosCreditos to
     * set
     */
    public void setComprasActivosFijosCreditos(BigDecimal pComprasActivosFijosCreditos) {
        this.comprasActivosFijosCreditos = pComprasActivosFijosCreditos;
    }

    /**
     * @return the importacionesActivosFijosBase
     */
    public BigDecimal getImportacionesActivosFijosBase() {
        return importacionesActivosFijosBase;
    }

    /**
     * @param pImportacionesActivosFijosBase the importacionesActivosFijosBase
     * to set
     */
    public void setImportacionesActivosFijosBase(BigDecimal pImportacionesActivosFijosBase) {
        this.importacionesActivosFijosBase = pImportacionesActivosFijosBase;
    }

    /**
     * @return the importacionesActivosFijosCred
     */
    public BigDecimal getImportacionesActivosFijosCred() {
        return importacionesActivosFijosCred;
    }

    /**
     * @param pImportacionesActivosFijosCred the importacionesActivosFijosCred
     * to set
     */
    public void setImportacionesActivosFijosCred(BigDecimal pImportacionesActivosFijosCred) {
        this.importacionesActivosFijosCred = pImportacionesActivosFijosCred;
    }

    /**
     * @return the ivaExencionRecibida
     */
    public BigDecimal getIvaExencionRecibida() {
        return ivaExencionRecibida;
    }

    /**
     * @param pIvaExencionRecibida the ivaExencionRecibida to set
     */
    public void setIvaExencionRecibida(BigDecimal pIvaExencionRecibida) {
        this.ivaExencionRecibida = pIvaExencionRecibida;
    }

    /**
     * @return the remanenteCreditoPeriodoAnt
     */
    public BigDecimal getRemanenteCreditoPeriodoAnt() {
        return remanenteCreditoPeriodoAnt;
    }

    /**
     * @param pRemanenteCreditoPeriodoAnt the remanenteCreditoPeriodoAnt to set
     */
    public void setRemanenteCreditoPeriodoAnt(BigDecimal pRemanenteCreditoPeriodoAnt) {
        this.remanenteCreditoPeriodoAnt = pRemanenteCreditoPeriodoAnt;
    }

    /**
     * @return the sumatoriaColBaseCreditosBase
     */
    public BigDecimal getSumatoriaColBaseCreditosBase() {
        return sumatoriaColBaseCreditosBase;
    }

    /**
     * @param pSumatoriaColBaseCreditosBase the sumatoriaColBaseCreditosBase to
     * set
     */
    public void setSumatoriaColBaseCreditosBase(BigDecimal pSumatoriaColBaseCreditosBase) {
        this.sumatoriaColBaseCreditosBase = pSumatoriaColBaseCreditosBase;
    }

    /**
     * @return the sumatoriaColBaseCreditosCred
     */
    public BigDecimal getSumatoriaColBaseCreditosCred() {
        return sumatoriaColBaseCreditosCred;
    }

    /**
     * @param pSumatoriaColBaseCreditosCred the sumatoriaColBaseCreditosCred to
     * set
     */
    public void setSumatoriaColBaseCreditosCred(BigDecimal pSumatoriaColBaseCreditosCred) {
        this.sumatoriaColBaseCreditosCred = pSumatoriaColBaseCreditosCred;
    }

    /**
     * @return the comprasServiciosAdquiridosPeq
     */
    public BigDecimal getComprasServiciosAdquiridosPeq() {
        return comprasServiciosAdquiridosPeq;
    }

    /**
     * @param pComprasServiciosAdquiridosPeq the comprasServiciosAdquiridosPeq
     * to set
     */
    public void setComprasServiciosAdquiridosPeq(BigDecimal pComprasServiciosAdquiridosPeq) {
        this.comprasServiciosAdquiridosPeq = pComprasServiciosAdquiridosPeq;
    }

    /**
     * @return the comprasCombustiblesBaseExpo
     */
    public BigDecimal getComprasCombustiblesBaseExpo() {
        return comprasCombustiblesBaseExpo;
    }

    /**
     * @param pComprasCombustiblesBaseExpo the comprasCombustiblesBaseExpo to
     * set
     */
    public void setComprasCombustiblesBaseExpo(BigDecimal pComprasCombustiblesBaseExpo) {
        this.comprasCombustiblesBaseExpo = pComprasCombustiblesBaseExpo;
    }

    /**
     * @return the comprasCombustiblesCredExpo
     */
    public BigDecimal getComprasCombustiblesCredExpo() {
        return comprasCombustiblesCredExpo;
    }

    /**
     * @param pComprasCombustiblesCredExpo the comprasCombustiblesCredExpo to
     * set
     */
    public void setComprasCombustiblesCredExpo(BigDecimal pComprasCombustiblesCredExpo) {
        this.comprasCombustiblesCredExpo = pComprasCombustiblesCredExpo;
    }

    /**
     * @return the otrasComprasBaseExpo
     */
    public BigDecimal getOtrasComprasBaseExpo() {
        return otrasComprasBaseExpo;
    }

    /**
     * @param pOtrasComprasBaseExpo the otrasComprasBaseExpo to set
     */
    public void setOtrasComprasBaseExpo(BigDecimal pOtrasComprasBaseExpo) {
        this.otrasComprasBaseExpo = pOtrasComprasBaseExpo;
    }

    /**
     * @return the otrasComprasCreditosExpo
     */
    public BigDecimal getOtrasComprasCreditosExpo() {
        return otrasComprasCreditosExpo;
    }

    /**
     * @param pOtrasComprasCreditosExpo the otrasComprasCreditosExpo to set
     */
    public void setOtrasComprasCreditosExpo(BigDecimal pOtrasComprasCreditosExpo) {
        this.otrasComprasCreditosExpo = pOtrasComprasCreditosExpo;
    }

    /**
     * @return the serviciosADquiridosBase
     */
    public BigDecimal getServiciosADquiridosBase() {
        return serviciosADquiridosBase;
    }

    /**
     * @param pServiciosADquiridosBase the serviciosADquiridosBase to set
     */
    public void setServiciosADquiridosBase(BigDecimal pServiciosADquiridosBase) {
        this.serviciosADquiridosBase = pServiciosADquiridosBase;
    }

    /**
     * @return the serviciosAdquiridosCreditos
     */
    public BigDecimal getServiciosAdquiridosCreditos() {
        return serviciosAdquiridosCreditos;
    }

    /**
     * @param pServiciosAdquiridosCreditos the serviciosAdquiridosCreditos to
     * set
     */
    public void setServiciosAdquiridosCreditos(BigDecimal pServiciosAdquiridosCreditos) {
        this.serviciosAdquiridosCreditos = pServiciosAdquiridosCreditos;
    }

    /**
     * @return the importacionesCaBaseExpo
     */
    public BigDecimal getImportacionesCaBaseExpo() {
        return importacionesCaBaseExpo;
    }

    /**
     * @param pImportacionesCaBaseExpo the importacionesCaBaseExpo to set
     */
    public void setImportacionesCaBaseExpo(BigDecimal pImportacionesCaBaseExpo) {
        this.importacionesCaBaseExpo = pImportacionesCaBaseExpo;
    }

    /**
     * @return the importaciones
     */
    public BigDecimal getImportaciones() {
        return importaciones;
    }

    /**
     * @param pImportaciones the importaciones to set
     */
    public void setImportaciones(BigDecimal pImportaciones) {
        this.importaciones = pImportaciones;
    }

    /**
     * @return the importacionesMundoBaseExpo
     */
    public BigDecimal getImportacionesMundoBaseExpo() {
        return importacionesMundoBaseExpo;
    }

    /**
     * @param pImportacionesMundoBaseExpo the importacionesMundoBaseExpo to set
     */
    public void setImportacionesMundoBaseExpo(BigDecimal pImportacionesMundoBaseExpo) {
        this.importacionesMundoBaseExpo = pImportacionesMundoBaseExpo;
    }

    /**
     * @return the importacionesMundoCreditoExpo
     */
    public BigDecimal getImportacionesMundoCreditoExpo() {
        return importacionesMundoCreditoExpo;
    }

    /**
     * @param pImportacionesMundoCreditoExpo the importacionesMundoCreditoExpo
     * to set
     */
    public void setImportacionesMundoCreditoExpo(BigDecimal pImportacionesMundoCreditoExpo) {
        this.importacionesMundoCreditoExpo = pImportacionesMundoCreditoExpo;
    }

    /**
     * @return the comprasActivosVincBaseExpo
     */
    public BigDecimal getComprasActivosVincBaseExpo() {
        return comprasActivosVincBaseExpo;
    }

    /**
     * @param pComprasActivosVincBaseExpo the comprasActivosVincBaseExpo to set
     */
    public void setComprasActivosVincBaseExpo(BigDecimal pComprasActivosVincBaseExpo) {
        this.comprasActivosVincBaseExpo = pComprasActivosVincBaseExpo;
    }

    /**
     * @return the comprasActivosVincCrediosExpo
     */
    public BigDecimal getComprasActivosVincCrediosExpo() {
        return comprasActivosVincCrediosExpo;
    }

    /**
     * @param pComprasActivosVincCrediosExpo the comprasActivosVincCrediosExpo
     * to set
     */
    public void setComprasActivosVincCrediosExpo(BigDecimal pComprasActivosVincCrediosExpo) {
        this.comprasActivosVincCrediosExpo = pComprasActivosVincCrediosExpo;
    }

    /**
     * @return the importacionesFijosVincBaseExpo
     */
    public BigDecimal getImportacionesFijosVincBaseExpo() {
        return importacionesFijosVincBaseExpo;
    }

    /**
     * @param pImportacionesFijosVincBaseExpo the importacionesFijosVincBaseExpo
     * to set
     */
    public void setImportacionesFijosVincBaseExpo(BigDecimal pImportacionesFijosVincBaseExpo) {
        this.importacionesFijosVincBaseExpo = pImportacionesFijosVincBaseExpo;
    }

    /**
     * @return the importacionesFijosVincCredExpo
     */
    public BigDecimal getImportacionesFijosVincCredExpo() {
        return importacionesFijosVincCredExpo;
    }

    /**
     * @param pImportacionesFijosVincCredExpo the importacionesFijosVincCredExpo
     * to set
     */
    public void setImportacionesFijosVincCredExpo(BigDecimal pImportacionesFijosVincCredExpo) {
        this.importacionesFijosVincCredExpo = pImportacionesFijosVincCredExpo;
    }

    /**
     * @return the remanenteCreditoPerAntExpo
     */
    public BigDecimal getRemanenteCreditoPerAntExpo() {
        return remanenteCreditoPerAntExpo;
    }

    /**
     * @param pRemanenteCreditoPerAntExpo the remanenteCreditoPerAntExpo to set
     */
    public void setRemanenteCreditoPerAntExpo(BigDecimal pRemanenteCreditoPerAntExpo) {
        this.remanenteCreditoPerAntExpo = pRemanenteCreditoPerAntExpo;
    }

    /**
     * @return the creditoFacturasEmitidasExpo
     */
    public BigDecimal getCreditoFacturasEmitidasExpo() {
        return creditoFacturasEmitidasExpo;
    }

    /**
     * @param pCreditoFacturasEmitidasExpo the creditoFacturasEmitidasExpo to
     * set
     */
    public void setCreditoFacturasEmitidasExpo(BigDecimal pCreditoFacturasEmitidasExpo) {
        this.creditoFacturasEmitidasExpo = pCreditoFacturasEmitidasExpo;
    }

    /**
     * @return the retencionPracticadasExpo
     */
    public BigDecimal getRetencionPracticadasExpo() {
        return retencionPracticadasExpo;
    }

    /**
     * @param pRetencionPracticadasExpo the retencionPracticadasExpo to set
     */
    public void setRetencionPracticadasExpo(BigDecimal pRetencionPracticadasExpo) {
        this.retencionPracticadasExpo = pRetencionPracticadasExpo;
    }

    /**
     * @return the sumatoriaColBaseCredBaseExpo
     */
    public BigDecimal getSumatoriaColBaseCredBaseExpo() {
        return sumatoriaColBaseCredBaseExpo;
    }

    /**
     * @param pSumatoriaColBaseCredBaseExpo the sumatoriaColBaseCredBaseExpo to
     * set
     */
    public void setSumatoriaColBaseCredBaseExpo(BigDecimal pSumatoriaColBaseCredBaseExpo) {
        this.sumatoriaColBaseCredBaseExpo = pSumatoriaColBaseCredBaseExpo;
    }

    /**
     * @return the sumatoriaColBaseCredCredExpo
     */
    public BigDecimal getSumatoriaColBaseCredCredExpo() {
        return sumatoriaColBaseCredCredExpo;
    }

    /**
     * @param pSumatoriaColBaseCredCredExpo the sumatoriaColBaseCredCredExpo to
     * set
     */
    public void setSumatoriaColBaseCredCredExpo(BigDecimal pSumatoriaColBaseCredCredExpo) {
        this.sumatoriaColBaseCredCredExpo = pSumatoriaColBaseCredCredExpo;
    }

    /**
     * @return the toralDeterminacionCredFiscal
     */
    public BigDecimal getToralDeterminacionCredFiscal() {
        return toralDeterminacionCredFiscal;
    }

    /**
     * @param pToralDeterminacionCredFiscal the toralDeterminacionCredFiscal to
     * set
     */
    public void setToralDeterminacionCredFiscal(BigDecimal pToralDeterminacionCredFiscal) {
        this.toralDeterminacionCredFiscal = pToralDeterminacionCredFiscal;
    }

    /**
     * @return the creditoFiscalSigPeriodo
     */
    public BigDecimal getCreditoFiscalSigPeriodo() {
        return creditoFiscalSigPeriodo;
    }

    /**
     * @param pCreditoFiscalSigPeriodo the creditoFiscalSigPeriodo to set
     */
    public void setCreditoFiscalSigPeriodo(BigDecimal pCreditoFiscalSigPeriodo) {
        this.creditoFiscalSigPeriodo = pCreditoFiscalSigPeriodo;
    }

    /**
     * @return the creditoFiscalOperacionesExpo
     */
    public BigDecimal getCreditoFiscalOperacionesExpo() {
        return creditoFiscalOperacionesExpo;
    }

    /**
     * @param pCreditoFiscalOperacionesExpo the creditoFiscalOperacionesExpo to
     * set
     */
    public void setCreditoFiscalOperacionesExpo(BigDecimal pCreditoFiscalOperacionesExpo) {
        this.creditoFiscalOperacionesExpo = pCreditoFiscalOperacionesExpo;
    }

    /**
     * @return the impuestoTotalDeterminadoLocal
     */
    public BigDecimal getImpuestoTotalDeterminadoLocal() {
        return impuestoTotalDeterminadoLocal;
    }

    /**
     * @param pImpuestoTotalDeterminadoLocal the impuestoTotalDeterminadoLocal
     * to set
     */
    public void setImpuestoTotalDeterminadoLocal(BigDecimal pImpuestoTotalDeterminadoLocal) {
        this.impuestoTotalDeterminadoLocal = pImpuestoTotalDeterminadoLocal;
    }

    /**
     * @return the impuestoTotalDeterminadoExpo
     */
    public BigDecimal getImpuestoTotalDeterminadoExpo() {
        return impuestoTotalDeterminadoExpo;
    }

    /**
     * @param pImpuestoTotalDeterminadoExpo the impuestoTotalDeterminadoExpo to
     * set
     */
    public void setImpuestoTotalDeterminadoExpo(BigDecimal pImpuestoTotalDeterminadoExpo) {
        this.impuestoTotalDeterminadoExpo = pImpuestoTotalDeterminadoExpo;
    }

    /**
     * @return the creditoFiscalPerSigOpExentas
     */
    public BigDecimal getCreditoFiscalPerSigOpExentas() {
        return creditoFiscalPerSigOpExentas;
    }

    /**
     * @param pCreditoFiscalPerSigOpExentas the creditoFiscalPerSigOpExentas to
     * set
     */
    public void setCreditoFiscalPerSigOpExentas(BigDecimal pCreditoFiscalPerSigOpExentas) {
        this.creditoFiscalPerSigOpExentas = pCreditoFiscalPerSigOpExentas;
    }

    /**
     * @return the remanenteRetencionIvaPerAnt
     */
    public BigDecimal getRemanenteRetencionIvaPerAnt() {
        return remanenteRetencionIvaPerAnt;
    }

    /**
     * @param pRemanenteRetencionIvaPerAnt the remanenteRetencionIvaPerAnt to
     * set
     */
    public void setRemanenteRetencionIvaPerAnt(BigDecimal pRemanenteRetencionIvaPerAnt) {
        this.remanenteRetencionIvaPerAnt = pRemanenteRetencionIvaPerAnt;
    }

    /**
     * @return the numeroResolucion
     */
    public BigDecimal getNumeroResolucion() {
        return numeroResolucion;
    }

    /**
     * @param pNumeroResolucion the numeroResolucion to set
     */
    public void setNumeroResolucion(BigDecimal pNumeroResolucion) {
        this.numeroResolucion = pNumeroResolucion;
    }

    /**
     * @return the montoAcreditado
     */
    public BigDecimal getMontoAcreditado() {
        return montoAcreditado;
    }

    /**
     * @param pMontoAcreditado the montoAcreditado to set
     */
    public void setMontoAcreditado(BigDecimal pMontoAcreditado) {
        this.montoAcreditado = pMontoAcreditado;
    }

    /**
     * @return the remanenteRetencionIvaRecPer
     */
    public BigDecimal getRemanenteRetencionIvaRecPer() {
        return remanenteRetencionIvaRecPer;
    }

    /**
     * @param pRemanenteRetencionIvaRecPer the remanenteRetencionIvaRecPer to
     * set
     */
    public void setRemanenteRetencionIvaRecPer(BigDecimal pRemanenteRetencionIvaRecPer) {
        this.remanenteRetencionIvaRecPer = pRemanenteRetencionIvaRecPer;
    }

    /**
     * @return the constanciasRetencionIvaRecPer
     */
    public BigDecimal getConstanciasRetencionIvaRecPer() {
        return constanciasRetencionIvaRecPer;
    }

    /**
     * @param pConstanciasRetencionIvaRecPer the constanciasRetencionIvaRecPer
     * to set
     */
    public void setConstanciasRetencionIvaRecPer(BigDecimal pConstanciasRetencionIvaRecPer) {
        this.constanciasRetencionIvaRecPer = pConstanciasRetencionIvaRecPer;
    }

    /**
     * @return the saldoRetencionPeriodoSig
     */
    public BigDecimal getSaldoRetencionPeriodoSig() {
        return saldoRetencionPeriodoSig;
    }

    /**
     * @param pSaldoRetencionPeriodoSig the saldoRetencionPeriodoSig to set
     */
    public void setSaldoRetencionPeriodoSig(BigDecimal pSaldoRetencionPeriodoSig) {
        this.saldoRetencionPeriodoSig = pSaldoRetencionPeriodoSig;
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
     * @return the numeroResolucionCompensacion
     */
    public BigDecimal getNumeroResolucionCompensacion() {
        return numeroResolucionCompensacion;
    }

    /**
     * @param pNumeroResolucionCompensacion the numeroResolucionCompensacion to
     * set
     */
    public void setNumeroResolucionCompensacion(BigDecimal pNumeroResolucionCompensacion) {
        this.numeroResolucionCompensacion = pNumeroResolucionCompensacion;
    }

    /**
     * @return the saldoNoCompensado
     */
    public BigDecimal getSaldoNoCompensado() {
        return saldoNoCompensado;
    }

    /**
     * @param pSaldoNoCompensado the saldoNoCompensado to set
     */
    public void setSaldoNoCompensado(BigDecimal pSaldoNoCompensado) {
        this.saldoNoCompensado = pSaldoNoCompensado;
    }

    /**
     * @return the valorCompensarPeriodoActual
     */
    public BigDecimal getValorCompensarPeriodoActual() {
        return valorCompensarPeriodoActual;
    }

    /**
     * @param pValorCompensarPeriodoActual the valorCompensarPeriodoActual to
     * set
     */
    public void setValorCompensarPeriodoActual(BigDecimal pValorCompensarPeriodoActual) {
        this.valorCompensarPeriodoActual = pValorCompensarPeriodoActual;
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
     * @return the indicadoresComerciales
     */
    public BigDecimal getIndicadoresComerciales() {
        return indicadoresComerciales;
    }

    /**
     * @param pIndicadoresComerciales the indicadoresComerciales to set
     */
    public void setIndicadoresComerciales(BigDecimal pIndicadoresComerciales) {
        this.indicadoresComerciales = pIndicadoresComerciales;
    }

    /**
     * @return the razonVenta
     */
    public BigDecimal getRazonVenta() {
        return razonVenta;
    }

    /**
     * @param pRazonVenta the razonVenta to set
     */
    public void setRazonVenta(BigDecimal pRazonVenta) {
        this.razonVenta = pRazonVenta;
    }

    /**
     * @return the cantidadFacturasEmitidas
     */
    public BigDecimal getCantidadFacturasEmitidas() {
        return cantidadFacturasEmitidas;
    }

    /**
     * @param pCantidadFacturasEmitidas the cantidadFacturasEmitidas to set
     */
    public void setCantidadFacturasEmitidas(BigDecimal pCantidadFacturasEmitidas) {
        this.cantidadFacturasEmitidas = pCantidadFacturasEmitidas;
    }

    /**
     * @return the cantidadFacturasRecibidas
     */
    public BigDecimal getCantidadFacturasRecibidas() {
        return cantidadFacturasRecibidas;
    }

    /**
     * @param pCantidadFacturasRecibidas the cantidadFacturasRecibidas to set
     */
    public void setCantidadFacturasRecibidas(BigDecimal pCantidadFacturasRecibidas) {
        this.cantidadFacturasRecibidas = pCantidadFacturasRecibidas;
    }

    /**
     * @return the cantidadConstanciasExencionEm
     */
    public BigDecimal getCantidadConstanciasExencionEm() {
        return cantidadConstanciasExencionEm;
    }

    /**
     * @param pCantidadConstanciasExencionEm the cantidadConstanciasExencionEm
     * to set
     */
    public void setCantidadConstanciasExencionEm(BigDecimal pCantidadConstanciasExencionEm) {
        this.cantidadConstanciasExencionEm = pCantidadConstanciasExencionEm;
    }

    /**
     * @return the cantidadConstanciasExencionRec
     */
    public BigDecimal getCantidadConstanciasExencionRec() {
        return cantidadConstanciasExencionRec;
    }

    /**
     * @param pCantidadConstanciasExencionRec the cantidadConstanciasExencionRec
     * to set
     */
    public void setCantidadConstanciasExencionRec(BigDecimal pCantidadConstanciasExencionRec) {
        this.cantidadConstanciasExencionRec = pCantidadConstanciasExencionRec;
    }

    /**
     * @return the cantConstanciasAdqInsumosEm
     */
    public BigDecimal getCantConstanciasAdqInsumosEm() {
        return cantConstanciasAdqInsumosEm;
    }

    /**
     * @param pCantConstanciasAdqInsumosEm the cantConstanciasAdqInsumosEm to
     * set
     */
    public void setCantConstanciasAdqInsumosEm(BigDecimal pCantConstanciasAdqInsumosEm) {
        this.cantConstanciasAdqInsumosEm = pCantConstanciasAdqInsumosEm;
    }

    /**
     * @return the cantConstanciasAdqInsumosRec
     */
    public BigDecimal getCantConstanciasAdqInsumosRec() {
        return cantConstanciasAdqInsumosRec;
    }

    /**
     * @param pCantConstanciasAdqInsumosRec the cantConstanciasAdqInsumosRec to
     * set
     */
    public void setCantConstanciasAdqInsumosRec(BigDecimal pCantConstanciasAdqInsumosRec) {
        this.cantConstanciasAdqInsumosRec = pCantConstanciasAdqInsumosRec;
    }

    /**
     * @return the cantConstanciasRetencionIvaEm
     */
    public BigDecimal getCantConstanciasRetencionIvaEm() {
        return cantConstanciasRetencionIvaEm;
    }

    /**
     * @param pCantConstanciasRetencionIvaEm the cantConstanciasRetencionIvaEm
     * to set
     */
    public void setCantConstanciasRetencionIvaEm(BigDecimal pCantConstanciasRetencionIvaEm) {
        this.cantConstanciasRetencionIvaEm = pCantConstanciasRetencionIvaEm;
    }

    /**
     * @return the cantConstanciasRetencionIvaRec
     */
    public BigDecimal getCantConstanciasRetencionIvaRec() {
        return cantConstanciasRetencionIvaRec;
    }

    /**
     * @param pCantConstanciasRetencionIvaRec the cantConstanciasRetencionIvaRec
     * to set
     */
    public void setCantConstanciasRetencionIvaRec(BigDecimal pCantConstanciasRetencionIvaRec) {
        this.cantConstanciasRetencionIvaRec = pCantConstanciasRetencionIvaRec;
    }

    /**
     * @return the cantidadFacturasEspecialesEm
     */
    public BigDecimal getCantidadFacturasEspecialesEm() {
        return cantFacturasEspecialesEm;
    }

    /**
     * @param pCantidadFacturasEspecialesEm the cantidadFacturasEspecialesEm to
     * set
     */
    public void setCantidadFacturasEspecialesEm(BigDecimal pCantidadFacturasEspecialesEm) {
        this.cantFacturasEspecialesEm = pCantidadFacturasEspecialesEm;
    }

    /**
     * @return the cantidadFacturasEspecialesRec
     */
    public BigDecimal getCantidadFacturasEspecialesRec() {
        return cantidadFacturasEspecialesRec;
    }

    /**
     * @param pCantidadFacturasEspecialesRec the cantidadFacturasEspecialesRec
     * to set
     */
    public void setCantidadFacturasEspecialesRec(BigDecimal pCantidadFacturasEspecialesRec) {
        this.cantidadFacturasEspecialesRec = pCantidadFacturasEspecialesRec;
    }

    /**
     * @return the numeroFormularioRectifica
     */
    public BigDecimal getNumeroFormularioRectifica() {
        return numeroFormularioRectifica;
    }

    /**
     * @param pNumeroFormularioRectifica the numeroFormularioRectifica to set
     */
    public void setNumeroFormularioRectifica(BigDecimal pNumeroFormularioRectifica) {
        this.numeroFormularioRectifica = pNumeroFormularioRectifica;
    }

    /**
     * @return the impuestoIngresadoFormularioRec
     */
    public BigDecimal getImpuestoIngresadoFormularioRec() {
        return impuestoIngresadoFormularioRec;
    }

    /**
     * @param pImpuestoIngresadoFormularioRec the impuestoIngresadoFormularioRec
     * to set
     */
    public void setImpuestoIngresadoFormularioRec(BigDecimal pImpuestoIngresadoFormularioRec) {
        this.impuestoIngresadoFormularioRec = pImpuestoIngresadoFormularioRec;
    }

    /**
     * @return the impuestoPagarRect
     */
    public BigDecimal getImpuestoPagarRect() {
        return impuestoPagarRect;
    }

    /**
     * @param pImpuestoPagarRect the impuestoPagarRect to set
     */
    public void setImpuestoPagarRect(BigDecimal pImpuestoPagarRect) {
        this.impuestoPagarRect = pImpuestoPagarRect;
    }

    /**
     * @return the impuestoFavorContribuyente
     */
    public BigDecimal getImpuestoFavorContribuyente() {
        return impuestoFavorContribuyente;
    }

    /**
     * @param pImpuestoFavorContribuyente the impuestoFavorContribuyente to set
     */
    public void setImpuestoFavorContribuyente(BigDecimal pImpuestoFavorContribuyente) {
        this.impuestoFavorContribuyente = pImpuestoFavorContribuyente;
    }

    /**
     * @return the multaFormal
     */
    public BigDecimal getMultaFormal() {
        return multaFormal;
    }

    /**
     * @param pMultaFormal the multaFormal to set
     */
    public void setMultaFormal(BigDecimal pMultaFormal) {
        this.multaFormal = pMultaFormal;
    }

    /**
     * @return the multaOmision
     */
    public BigDecimal getMultaOmision() {
        return multaOmision;
    }

    /**
     * @param pMultaOmision the multaOmision to set
     */
    public void setMultaOmision(BigDecimal pMultaOmision) {
        this.multaOmision = pMultaOmision;
    }

    /**
     * @return the multaRectificacion
     */
    public BigDecimal getMultaRectificacion() {
        return multaRectificacion;
    }

    /**
     * @param pMultaRectificacion the multaRectificacion to set
     */
    public void setMultaRectificacion(BigDecimal pMultaRectificacion) {
        this.multaRectificacion = pMultaRectificacion;
    }

    /**
     * @return the intereses
     */
    public BigDecimal getIntereses() {
        return intereses;
    }

    /**
     * @param pIntereses the intereses to set
     */
    public void setIntereses(BigDecimal pIntereses) {
        this.intereses = pIntereses;
    }

    /**
     * @return the mora
     */
    public BigDecimal getMora() {
        return mora;
    }

    /**
     * @param pMora the mora to set
     */
    public void setMora(BigDecimal pMora) {
        this.mora = pMora;
    }

    /**
     * @return the accesoriosPagar
     */
    public BigDecimal getAccesoriosPagar() {
        return accesoriosPagar;
    }

    /**
     * @param pAccesoriosPagar the accesoriosPagar to set
     */
    public void setAccesoriosPagar(BigDecimal pAccesoriosPagar) {
        this.accesoriosPagar = pAccesoriosPagar;
    }

    /**
     * @return the totalPagar
     */
    public BigDecimal getTotalPagar() {
        return totalPagar;
    }

    /**
     * @param pTotalPagar the totalPagar to set
     */
    public void setTotalPagar(BigDecimal pTotalPagar) {
        this.totalPagar = pTotalPagar;
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
     * @return the remanente5
     */
    public BigDecimal getRemanente5() {
        if (remanente5 == null) {
            remanente5 = BigDecimal.ZERO;
        }
        return remanente5;
    }

    /**
     * @param pRemanente5 the remanente5 to set
     */
    public void setRemanente5(BigDecimal pRemanente5) {
        this.remanente5 = pRemanente5;
    }

    /**
     * @return the remanente6
     */
    public BigDecimal getRemanente6() {
        if (remanente6 == null) {
            remanente6 = BigDecimal.ZERO;
        }
        return remanente6;
    }

    /**
     * @param pRemanente6 the remanente6 to set
     */
    public void setRemanente6(BigDecimal pRemanente6) {
        this.remanente6 = pRemanente6;
    }

    /**
     * @return the remanente7
     */
    public BigDecimal getRemanente7() {
        if (remanente7 == null) {
            remanente7 = BigDecimal.ZERO;
        }
        return remanente7;
    }

    /**
     * @param pRemanente7 the remanente7 to set
     */
    public void setRemanente7(BigDecimal pRemanente7) {
        this.remanente7 = pRemanente7;
    }

    /**
     * @return the credFiscalLocal
     */
    public BigDecimal getCredFiscalLocal() {
        if (credFiscalLocal == null) {
            credFiscalLocal = BigDecimal.ZERO;
        }
        return credFiscalLocal;
    }

    /**
     * @param pCredFiscalLocal the credFiscalLocal to set
     */
    public void setCredFiscalLocal(BigDecimal pCredFiscalLocal) {
        this.credFiscalLocal = pCredFiscalLocal;
    }

    /**
     * @return the crediFiscalExp
     */
    public BigDecimal getCrediFiscalExp() {
        if (crediFiscalExp == null) {
            credFiscalLocal = BigDecimal.ZERO;
        }
        return crediFiscalExp;
    }

    /**
     * @param pCrediFiscalExp the crediFiscalExp to set
     */
    public void setCrediFiscalExp(BigDecimal pCrediFiscalExp) {
        this.crediFiscalExp = pCrediFiscalExp;
    }

}
