/*
 * Superintendencia de Administracion Tributaria (SAT)
 * Gerencia de Informatica
 * Departamento de Desarrollo de Sistemas
 */
package gt.gob.sat.fiscalizacion.consulta360.srv.impl;

import gt.gob.sat.arquitectura.fwk.dao.DaoCall;
import gt.gob.sat.fiscalizacion.consulta360.dto.Consolidado223Dto;
import gt.gob.sat.arquitectura.fwk.dao.DaoCrud;
import gt.gob.sat.arquitectura.fwk.dao.DaoFinder;
import gt.gob.sat.arquitectura.fwk.dto.ParameterDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.UsuarioDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.AfiliacionDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ApoderadoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ArchivoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.CantidadVehiculosDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ConsultaCantidadDeclaracionesAduanerasConAnioDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ConsultaCantidadDeclaracionesAduanerasDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ConteoVehiculosActivosInactivosDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ContribuyenteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ConveniosActivosDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DatosContadorDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DatosRepresentantesDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DeclaracionAduaneraDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DeclaracionDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DeclaracionesPresentadasDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DetalleProveedorDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DocumentoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.EncabezadoNombramientoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.EstablecimientoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ExpedienteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HistorialRiesgoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ItemDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.LibroAutorizadoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.LineaAereaDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.MaquinaDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.NombramientoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ObligacionDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.PapelDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.PerfilContribuyenteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ProveedorDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.SupervisorAuditorDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.TotalDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.TrayectoriaNombramientoDto;
import gt.gob.sat.fiscalizacion.consulta360.modelo.Consulta360Bitacora;
import gt.gob.sat.fiscalizacion.consulta360.modelo.Consulta360BitacoraId;
import gt.gob.sat.fiscalizacion.consulta360.srv.GeneralSrv;
import gt.gob.sat.fiscalizacion.consulta360.dto.DetalleRiesgoSentenciaDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DetalleRiesgoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ExcelReadVerificacionesDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.GeneralVerificacionesDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HistorialCargaDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HistorialCargaFechaFormatDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HistorialCargaVerificacionesCampoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HistorialCargaVerificacionesFechaFormatDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ImpuestoIsoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IndicadoresCobProveedoresDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IndicadoresCreditoDebitoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IndicadoresEncabezadoCalculadosDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IndicadoresGastosGeneralesDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IndicadoresRentabilidadDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IngresosFelvsDeclaradoIvaPequeDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.PequenoContribuyenteEncabezadoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ImpuestoIsrDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ImpuestoIvaGeneralDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.TipologiasEvasionDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.TipologiasEvasionAnualDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.VehiculosDTO;
import gt.gob.sat.fiscalizacion.consulta360.dto.VerificacionesEnCampoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.VerificacionesEnCampoConteoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.obtenerConteoDto;
import gt.gob.sat.fiscalizacion.consulta360.modelo.CifCargaVerificacionesCampo;
import gt.gob.sat.fiscalizacion.consulta360.modelo.CifHistorialCargas;
import gt.gob.sat.fiscalizacion.consulta360.modelo.CifTipologiasDeEvasion;
import gt.gob.sat.fiscalizacion.consulta360.modelo.CifVerificacionesEnCampo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import static org.hibernate.jpa.internal.EntityManagerImpl.LOG;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Daniel Castillo (adcastic)
 * @since 08/12/2015
 * @version 1.0
 */
@Service("generalSrvImpl")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class GeneralSrvImpl implements GeneralSrv {

    private static final Log LOGGER = LogFactory.getLog(GeneralSrvImpl.class);
    /**
     * Bean de acceso a datos, permite realizar consultas a base de datos.
     */
    @Resource
    protected DaoFinder daoFinderImpl;

    /**
     * Bean de acceso a datos, permite realizar persistencias a base de datos.
     */
    @Resource
    protected DaoCrud daoCrudImpl;

    @Resource
    protected DaoFinder finder;

    /* Bean de acceso a datos, permite realizar persistencias a base de datos.*/
    @Resource
    protected DaoCrud crud;

    @Resource
    protected DaoCall calli;

    @Override
    public UsuarioDto obtenerDatosUsuario(String pLogin) {
        Map<String, Object> param = new HashMap<>();
        param.put("pLogin", pLogin);
        List<UsuarioDto> listUsuarios = this.daoFinderImpl.findByNamedQueryParam("obtenerDatosUsuarioInterno", param, UsuarioDto.class);

        listUsuarios = listUsuarios.isEmpty() ? this.daoFinderImpl.findByNamedQueryParam("obtenerDatosUsuarioExterno", param, UsuarioDto.class) : listUsuarios;

        return listUsuarios.isEmpty() ? new UsuarioDto() : listUsuarios.get(0);
    }

    @Override
    public ContribuyenteDto obtenerContribuyenteByNit(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        List<ContribuyenteDto> list = this.daoFinderImpl.findByNamedQueryParam("obtenerContribuyenteByNit", param, ContribuyenteDto.class);
        return list.isEmpty() ? new ContribuyenteDto() : list.get(0);
    }

    @Override
    public ContribuyenteDto obtenerInfoContribuyenteByNit(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        List<ContribuyenteDto> list = this.daoFinderImpl.findByNamedQueryParam("obtenerInfoContribuyenteByNit", param, ContribuyenteDto.class);
        return list.isEmpty() ? new ContribuyenteDto() : list.get(0);
    }

    @Override
    public List<DeclaracionDto> obtenerDeclaraciones(String pNit, Date pFechaDel, Date pFechaAl, String pCodFormulario) {
        List<DeclaracionDto> lista;

        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("pFechaDel", new SimpleDateFormat("dd/MM/yyyy").format(pFechaDel));
        param.put("pFechaAl", new SimpleDateFormat("dd/MM/yyyy").format(pFechaAl));

        String codigoFormulario = pCodFormulario.replace(" ", "");
        if (codigoFormulario.isEmpty()) {
            lista = this.daoFinderImpl.findByNamedQueryParam("obtenerDeclaracionesByNitAndFechas", param, DeclaracionDto.class);
        } else {
            param.put("pCodFormulario", codigoFormulario);
            lista = this.daoFinderImpl.findByNamedQueryParam("obtenerDeclaracionesByNitAndFechasAndFormulario", param, DeclaracionDto.class);
        }

        return lista;
    }

    @Override
    public List<LibroAutorizadoDto> obtenerLibrosAutorizadosByNit(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerLibrosAutorizadosByNit", param, LibroAutorizadoDto.class);
    }

    @Override
    public List<AfiliacionDto> obtenerAfiliacionesByNit(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerAfiliacionesByNit", param, AfiliacionDto.class);
    }

    @Override
    public List<TipologiasEvasionDto> obtenerTipologiasEvasionByNit(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerTipologiasEvasionByNit", param, TipologiasEvasionDto.class);
    }

    @Override
    public List<VerificacionesEnCampoDto> obtenerVerificacionesByNit(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerVerificacionesByNit", param, VerificacionesEnCampoDto.class);
    }

    @Override
    public List<VerificacionesEnCampoConteoDto> obtenerVerificacionesConteoByNit(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerVerificacionesConteoByNit", param, VerificacionesEnCampoConteoDto.class);
    }

    @Override
    public List<ObligacionDto> obtenerObligacionesByAfiliacionAndNit(String pNit, String pCodigoImpuesto, String pCodigoTipo) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("pCodigoImpuesto", pCodigoImpuesto);
        param.put("pCodigoTipo", pCodigoTipo);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerObligacionesByAfiliacionAndNit", param, ObligacionDto.class);
    }

    @Override
    public List<EstablecimientoDto> obtenerEstablecimientosByNit(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerEstablecimientosByNit", param, EstablecimientoDto.class);
    }

    @Override
    public List<DocumentoDto> obtenerDocumentosByNitAndEstablecimiento(String pNit, String pNoEstablecimiento) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("pNoEstablecimiento", pNoEstablecimiento);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerDocumentosByNitAndEstablecimiento", param, DocumentoDto.class);
    }

    @Override
    public List<MaquinaDto> obtenerMaquinasByNitAndEstablecimiento(String pNit, String pNoEstablecimiento) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("pNoEstablecimiento", pNoEstablecimiento);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerMaquinasByNitAndEstablecimiento", param, MaquinaDto.class);
    }

    @Override
    public List<LibroAutorizadoDto> obtenerLibrosByNitAndEstablecimiento(String pNit, String pNoEstablecimiento) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("pNoEstablecimiento", pNoEstablecimiento);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerLibrosByNitAndEstablecimiento", param, LibroAutorizadoDto.class);
    }

    @Override
    public List<LineaAereaDto> obtenerLineasAereasByNitAndEstablecimiento(String pNit, String pNoEstablecimiento) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("pNoEstablecimiento", pNoEstablecimiento);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerLineasAereasByNitAndEstablecimiento", param, LineaAereaDto.class);
    }

    @Override
    public List<NombramientoDto> obtenerNombramientosByNitAndDates(String pNit, Date pFechaInicio, Date pFechaFin) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("pFechaInicio", formato.format(pFechaInicio));
        param.put("pFechaFin", formato.format(pFechaFin));

        return this.daoFinderImpl.findByNamedQueryParam("obtenerNombramientosByNitAndDates", param, NombramientoDto.class);
    }

    @Override
    public boolean existeInformeBanguatActivo(String pNombramiento) {
        boolean bandera = false;

        Map<String, Object> params = new HashMap<>();
        params.put("pNombramiento", pNombramiento);

        if (!daoFinderImpl.uniqueResult(daoFinderImpl.findByNamedQueryParam("existeInformeBanguatActivo", params)).toString().equals("0")) {
            bandera = true;
        }

        return bandera;
    }

    @Override
    public boolean existeResolucionFiscalActiva(String pNombramiento) {
        boolean bandera = false;

        Map<String, Object> params = new HashMap<>();
        params.put("pNombramiento", pNombramiento);

        if (!daoFinderImpl.uniqueResult(daoFinderImpl.findByNamedQueryParam("existeResolucionFiscalActiva", params)).toString().equals("0")) {
            bandera = true;
        }

        return bandera;
    }

    @Override
    public boolean existenAudiencias(String pNombramiento) {
        boolean bandera = false;

        Map<String, Object> params = new HashMap<>();
        params.put("pNombramiento", pNombramiento);

        if (!daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("contarAudienciaByNombramiento", params)).toString().equals("0")) {
            bandera = true;
        }

        return bandera;
    }

    @Override
    public BigDecimal findNoInforme(String pNombramiento) {
        BigDecimal resultado = BigDecimal.ZERO;

        Map<String, Object> params = new HashMap<>();
        params.put("pNombramiento", pNombramiento);

        if (!daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("contarInformesByNombramiento", params)).toString().equals("0")) {
            resultado = this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("findNoInforme", params));
        }

        return resultado;
    }

    @Override
    public EncabezadoNombramientoDto obtenerEncabezadoNombramiento(String pNombramiento) {
        Map<String, Object> params = new HashMap<>();
        params.put("pNombramiento", pNombramiento);

        return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerEncabezadoNombramiento", params, EncabezadoNombramientoDto.class));
    }

    @Override
    public List<SupervisorAuditorDto> obtenerSupervisoresAuditoresByNombramiento(String pNombramiento) {
        Map<String, Object> params = new HashMap<>();
        params.put("pNombramiento", pNombramiento);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerSupervisoresAuditoresByNombramiento", params, SupervisorAuditorDto.class);
    }

    @Override
    public List<TrayectoriaNombramientoDto> obtenerTrayectoriaNombramiento(String pNombramiento) {
        Map<String, Object> params = new HashMap<>();
        params.put("pNombramiento", pNombramiento);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerTrayectoriaNombramiento", params, TrayectoriaNombramientoDto.class);
    }

    @Override
    public List<ExpedienteDto> obtenerExpedientesByNit(String pNit) {
        Map<String, Object> params = new HashMap<>();
        params.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerExpedientesByNit", params, ExpedienteDto.class);
    }

    @Override
    public List<DocumentoDto> obtenerDocumentosByExpediente(String pNumeroExpediente) {
        Map<String, Object> params = new HashMap<>();
        params.put("pNumeroExpediente", pNumeroExpediente);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerDocumentosByExpediente", params, DocumentoDto.class);
    }

    @Override
    public List<ExpedienteDto> obtenerExpedientesJuridicoByNit(String pNit, Date pFechaInicio, Date pFechaFin) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        Map<String, Object> params = new HashMap<>();
        params.put("pNit", pNit);
        params.put("pFechaInicio", formato.format(pFechaInicio));
        params.put("pFechaFin", formato.format(pFechaFin));

        return this.daoFinderImpl.findByNamedQueryParam("obtenerExpedientesJuridicoByNit", params, ExpedienteDto.class);
    }

    @Override
    public List<ProveedorDto> obtenerProveedoresByNitAndNombramiento(String pNit, String pNombramiento) {
        Map<String, Object> params = new HashMap<>();
        params.put("pNit", pNit);
        params.put("pNombramiento", pNombramiento);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerProveedoresByNitAndNombramiento", params, ProveedorDto.class);
    }

    @Override
    public List<DetalleProveedorDto> obtenerDetalleProveedor(String pNit, String pMes, String pAnio) {
        Map<String, Object> params = new HashMap<>();
        params.put("pNitProveedor", pNit);
        params.put("pMes", pMes);
        params.put("pAnio", pAnio);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerDetalleProveedor", params, DetalleProveedorDto.class);
    }

    @Override
    public String obtenerMontoDeclaradoByProveedor(String pNit, String pMes, String pAnio) {
        int casillaServicios = 0;
        int casillaVentas = 0;
        ProveedorDto informacion = this.findConsultaDocumento(pNit, pMes, pAnio);
        if (informacion != null) {
            int codFormulario = informacion.getCodigoFormulario().intValue();
            int version = Integer.parseInt(informacion.getNumeroVersion());

            if (codFormulario == 201) {
                switch (version) {
                    case 1:
                    case 2: {
                        casillaServicios = 28;
                        casillaVentas = 27;
                        break;
                    }
                    case 3: {
                        casillaServicios = 14;
                        casillaVentas = 10;
                        break;
                    }
                    case 4: {
                        casillaServicios = 14;
                        casillaVentas = 12;
                        break;
                    }
                    case 6: {
                        casillaServicios = 15;
                        casillaVentas = 11;
                        break;
                    }
                    case 7: {
                        casillaServicios = 13;
                        casillaVentas = 11;
                        break;
                    }
                    case 8:
                    case 9: {
                        casillaServicios = 28;
                        casillaVentas = 27;
                        break;
                    }
                }
            } else if (codFormulario == 215) {
                switch (version) {
                    case 1: {
                        casillaServicios = 19;
                        casillaVentas = 17;
                        break;
                    }
                    case 7:
                    case 9: {
                        casillaServicios = 20;
                        casillaVentas = 18;
                        break;
                    }
                }
            } else if (codFormulario == 223) {
                switch (version) {
                    case 1:
                    case 2: {
                        casillaServicios = 14;
                        casillaVentas = 12;
                        break;
                    }
                    case 8:
                    case 9: {
                        casillaServicios = 15;
                        casillaVentas = 13;
                        break;
                    }
                    case 7: {
                        casillaServicios = 16;
                        casillaVentas = 14;
                        break;
                    }
                }
            }
            Map<String, Object> params = new HashMap<>();
            params.put("pNumeroDocumento", informacion.getNumeroDocumento());
            params.put("pAnioFiscal", informacion.getAnioFiscal());
            params.put("casilla1", casillaServicios);
            params.put("casilla2", casillaVentas);

            return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerMontoDeclaradoByProveedor", params));
        } else {
            return "0";
        }
    }

    @Override
    public List<PapelDto> obtenerPapelesTrabajoByNombramiento(String pNombramiento) {
        Map<String, Object> params = new HashMap<>();
        params.put("pNombramiento", pNombramiento);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerPapelesTrabajoByNombramiento", params, PapelDto.class);
    }

    @Override
    public PerfilContribuyenteDto findPerfilContribuyenteByNit(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("findPerfilContribuyenteByNit", param, PerfilContribuyenteDto.class));
    }

    @Override
    public List<DatosRepresentantesDto> findDatosRepresentantesByNit(String pNit) {
        Map<String, Object> params = new HashMap<>();
        params.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("findDatosRepresentantesByNit", params, DatosRepresentantesDto.class);
    }

    @Override
    public List<DatosContadorDto> findDatosContadorByNit(String pNit) {
        Map<String, Object> params = new HashMap<>();
        params.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("findDatosContadorByNit", params, DatosContadorDto.class);
    }

    @Override
    public List<DeclaracionDto> findDeclaraciones223Or215(String pNit, String pFechaDel, String pFechaAl) {
        List<DeclaracionDto> declaraciones223 = null;
        try {
            Map<String, Object> param = new HashMap<>();

            param.put("pNit", pNit);
            param.put("pFechaDel", pFechaDel);
            param.put("pFechaAl", pFechaAl);
            declaraciones223 = this.daoFinderImpl.findByNamedQueryParam("obtenerFormularios223Or215", param, DeclaracionDto.class);
        } catch (Exception e) {
            LOGGER.error("Error!!!: consultando declaraciones 223 o 215 " + e);
        }
        return declaraciones223;
    }

    @Override
    public Consolidado223Dto findDatosConsolidado223(String pNit, String pFechaDel, String pFechaAl) {
        Consolidado223Dto consolidado = new Consolidado223Dto();
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("pNit", pNit);
            param.put("pFechaDel", pFechaDel);
            param.put("pFechaAl", pFechaAl);
            consolidado = this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("consolidado223", param, Consolidado223Dto.class));
        } catch (Exception e) {
            LOGGER.error("Error!!!: consultando datos de consolidado 223 o 215 " + e);
        }
        return consolidado;
    }

    @Override
    public Consolidado223Dto findDatosRemanenteConsolidado223(String pNit, String pFechaDel, String pFechaAl) {
        Consolidado223Dto consolidado = new Consolidado223Dto();
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("pNit", pNit);
            param.put("pFechaDel", pFechaDel);
            param.put("pFechaAl", pFechaAl);
            consolidado = this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("remanentesConsolidado223", param, Consolidado223Dto.class));
        } catch (Exception e) {
            LOGGER.error("Error!!!: consultando Remanentes 223 o 215 " + e);
        }
        return consolidado;
    }

    @Override
    public Consolidado223Dto findDatosCreditoFiscalConsolidado223(String pNit, String pFechaDel, String pFechaAl) {
        Consolidado223Dto consolidado = new Consolidado223Dto();
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("pNit", pNit);
            param.put("pFechaDel", pFechaDel);
            param.put("pFechaAl", pFechaAl);
            consolidado = this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("determinacionCreditoFiscalConsolidado223", param, Consolidado223Dto.class));
        } catch (Exception e) {
            LOGGER.error("Error!!!: consultando creditos fiscales 223 o 215 " + e);
        }
        return consolidado;
    }

    @Override
    public ProveedorDto findConsultaDocumento(String pNit, String pMes, String pAnio) {
        List<ProveedorDto> resultado;
        Map<String, Object> param = new HashMap<>();
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Integer.valueOf(pAnio), Integer.valueOf(pMes) - 1, 1);
        String pFechaDel = "01/" + pMes + "/" + pAnio;
        String pFechaAl = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) + "/" + pMes + "/" + pAnio;
        param.put("pNit", pNit);
        param.put("pFechaDel", pFechaDel);
        param.put("pFechaAl", pFechaAl);
        resultado = this.daoFinderImpl.findByNamedQueryParam("obtenerInformacionDocumento", param, ProveedorDto.class);

        return resultado.isEmpty() ? null : resultado.get(0);
    }

    @Override
    public List<ArchivoDto> obtenerInformesFinAuditoria(String pNombramiento) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNombramiento", pNombramiento);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerInformesFinAuditoria", param, ArchivoDto.class);
    }

    @Override
    public List<DocumentoDto> obtenerDocumentosVerificacion(String pNombramiento, ProveedorDto pProveedor) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNitProveedor", pProveedor.getNitProveedor());
        param.put("pNombramiento", pNombramiento);
        param.put("pMesPeriodo", pProveedor.getNumeroMesPeriodo());
        param.put("pAnioPeriodo", pProveedor.getAnioPeriodo());

        return this.daoFinderImpl.findByNamedQueryParam("obtenerDocumentosVerificacion", param, DocumentoDto.class);
    }

    @Override
    public List<ItemDto> obtenerTiposOperacionAduanas() {
        return daoFinderImpl.findByNamedQuery("obtenerTiposOperacionAduanas", ItemDto.class);
    }

    @Override
    public List<ItemDto> obtenerEstadosDua() {
        return daoFinderImpl.findByNamedQuery("obtenerEstadosDua", ItemDto.class);
    }

    @Override
    public List<ItemDto> obtenerAduanas() {
        return daoFinderImpl.findByNamedQuery("obtenerAduanas", ItemDto.class);
    }

    @Override
    public List<ItemDto> obtenerModalidadesPorRegimen(String pTipoOperacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("pTipoOperacion", pTipoOperacion);

        return daoFinderImpl.findByNamedQueryParam("obtenerModalidadesPorRegimen", param, ItemDto.class);
    }

    @Override
    public List<DeclaracionAduaneraDto> obtenerDeclaracionesAduaneras(String pNitContribuyente, String[] pModalidades, Date pFechaInicio, Date pFechaFin, String pEstadoDua, String pAduana) {
        String script = "SELECT  d.agente_orden                                                                                     as \"agenteOrden\","
                + "        d.numero_orden                                                                                     as \"declaracion\","
                + "        d.aduana_entrada                                                                                   as \"aduanaEntrada\","
                + "        d.puerto_entrada                                                                                   as \"puertoEntrada\","
                + "        to_char(d.secuencia)                                                                               as \"secuencia\","
                + "        d.anio                                                                                             as \"anio\","
                + "        to_char(d.version)                                                                                 as \"version\","
                + "        to_char(d.fecha_aceptacion,'dd/MM/yyyy')                                                           as \"fechaDeclaracion\","
                + "        to_char(nvl(nvl(d.total_fob_dolares,0)*nvl(d.valor_tipo_cambio,0),0),'99G999G999G999G990D00')      as \"montoFob\","
                + "        to_char(nvl(nvl(d.total_seguro_dolares,0)*nvl(d.valor_tipo_cambio,0),0),'99G999G999G999G990D00')   as \"montoSeguro\","
                + "        to_char(nvl(nvl(d.total_flete_dolares,0)*nvl(d.valor_tipo_cambio,0),0),'99G999G999G999G990D00')    as \"montoFlete\","
                + "        to_char(nvl(nvl(d.total_otros_dolares,0)*nvl(d.valor_tipo_cambio,0),0),'99G999G999G999G990D00')    as \"montoOtros\","
                + "        to_char("
                + "                   ("
                + "                       SELECT  nvl(nvl(da.valor_quetzales,0)+nvl(da.valor_seguros,0)+nvl(da.valor_fletes,0)+nvl(da.valor_otros_gastos,0),0)"
                + "                       FROM    ad_declaraciones_aduaneras da"
                + "                       WHERE   da.codigo_agente||da.numero_declaracion=d.numero_orden"
                + "                   ),"
                + "                   '99G999G999G999G990D00'"
                + "               )                                                                                           as \"montoCif\","
                + "        to_char("
                + "                   ("
                + "                       SELECT  nvl(sum(nvl(dtd.valor_tributo,0)),0)"
                + "                       FROM    dua_tributos_declaracion dtd"
                + "                       WHERE   dtd.aduana_entrada=d.aduana_entrada"
                + "                       AND     dtd.puerto_entrada=d.puerto_entrada"
                + "                       AND     dtd.secuencia=d.secuencia"
                + "                       AND     dtd.anio=d.anio"
                + "                       AND     dtd.version=d.version"
                + "                       AND     dtd.cod_tributo='DAI'"
                + "                   ),"
                + "                   '99G999G999G999G990D00'"
                + "               )                                                                                           as \"montoDai\","
                + "        to_char("
                + "                   ("
                + "                       SELECT  nvl(sum(nvl(dtd.valor_tributo,0)),0)"
                + "                       FROM    dua_tributos_declaracion dtd"
                + "                       WHERE   dtd.aduana_entrada=d.aduana_entrada"
                + "                       AND     dtd.puerto_entrada=d.puerto_entrada"
                + "                       AND     dtd.secuencia=d.secuencia"
                + "                       AND     dtd.anio=d.anio"
                + "                       AND     dtd.version=d.version"
                + "                       AND     dtd.cod_tributo='IVA'"
                + "                   ),"
                + "                   '99G999G999G999G990D00'"
                + "               )                                                                                           as \"montoIva\","
                + "       ("
                + "           SELECT  dp.numero_orden"
                + "           FROM    dua_poliza dp"
                + "           WHERE   dp.aduana_entrada=d.aduana_entrada"
                + "           AND     dp.puerto_entrada=d.puerto_entrada"
                + "           AND     dp.secuencia=d.secuencia"
                + "           AND     dp.anio=d.anio"
                + "           AND     dp.version=1"
                + "       )                                                                                                   as \"declaracionOriginal\" "
                + "FROM   ("
                + "           SELECT  *"
                + "           FROM    dua_poliza p"
                + "           WHERE   NOT EXISTS  ("
                + "                                   SELECT  1"
                + "                                   FROM    ad_rectificaciones ar"
                + "                                   WHERE   ar.codigo_agente_original=p.agente_orden"
                + "                                   AND     ar.numero_declaracion_original=substr(p.numero_orden,4)"
                + "                               )"
                + "       ) d "
                + "WHERE  d.documento_beneficiario='" + pNitContribuyente + "' ";

        if (!pAduana.equals("-")) {
            script += "AND    d.aduana_entrada='" + pAduana.split("~")[0] + "' ";
        }
        if (pModalidades != null && pModalidades.length > 0) {
            String regimenes = "";
            String modalidades = "";
            for (String modalidad : pModalidades) {
                if (!modalidades.isEmpty()) {
                    regimenes += ",'" + modalidad.split("~")[0] + "'";
                    modalidades += ",'" + modalidad.split("~")[1] + "'";
                } else {
                    regimenes = "'" + modalidad.split("~")[0] + "'";
                    modalidades = "'" + modalidad.split("~")[1] + "'";
                }
            }

            script += "AND    d.cod_regimen in (" + regimenes + ") ";
            script += "AND    d.modalidad_regimen in (" + modalidades + ") ";
        }
        if (pFechaInicio != null && pFechaFin != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            script += "AND    d.fecha_aceptacion BETWEEN to_date('" + formato.format(pFechaInicio) + "','dd/MM/yyyy') AND to_date('" + formato.format(pFechaFin) + "','dd/MM/yyyy') ";
        }
        if (!pEstadoDua.equals("-")) {
            script += "AND    ("
                    + "           SELECT  max(cod_estado)"
                    + "           FROM    dua_estados_declaracion ded"
                    + "           WHERE   ded.aduana_entrada=d.aduana_entrada"
                    + "           AND     ded.puerto_entrada=d.puerto_entrada"
                    + "           AND     ded.secuencia=d.secuencia"
                    + "           AND     ded.anio=d.anio"
                    + "           AND     ded.version=d.version"
                    + "       )=" + pEstadoDua + " ";
        }
        script += "       ORDER BY    d.fecha_aceptacion DESC";

        return daoFinderImpl.executeSQLQuery(script, DeclaracionAduaneraDto.class);
    }

    @Override
    public List<AfiliacionDto> findAfiliacionesByNit(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("findAfiliacionesByNit", param, AfiliacionDto.class);
    }

    @Override
    public List<TotalDto> findSolicitudesDevolucionCF(String pNit) {
        StringBuilder script = new StringBuilder();
        int anioActual = LocalDate.now().getYear();

        script.append(" SELECT      descripcion                                                                     as \"descripcion\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM        (");
        script.append("                 SELECT      EXTRACT(YEAR FROM sd.fecha_adicion) anio,");
        script.append("                             s.descripcion");
        script.append("                 FROM        dc_solicitudes_devolucion sd");
        script.append("                 INNER JOIN  dc_subtipos_solicitudes sts ON sts.subtipo=sd.subtipo");
        script.append("                 INNER JOIN  dc_solicitudes s ON s.numero_solicitud=sts.numero_solicitud");
        script.append("                 WHERE       sd.nit='").append(pNit).append("'");
        script.append("             )");
        script.append(" PIVOT       (");
        script.append("                 COUNT(1)");
        script.append("                 FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("             )");
        script.append(" ORDER BY    1");

        return daoFinderImpl.executeSQLQuery(script.toString(), TotalDto.class);
    }

    @Override
    public List<TotalDto> findOmisos(String pNit) {
        StringBuilder script = new StringBuilder();
        int anioActual = LocalDate.now().getYear();

        script.append(" SELECT  descripcion                                                                     as \"descripcion\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT      to_number(substr(omi_omisiones.periodo,1,4)) anio,");
        script.append("                         cci_conceptos.nombre_corto descripcion");
        script.append("             FROM        sat_cci.omi_omisiones,");
        script.append("                         sat_cci.cci_conceptos,");
        script.append("                         sat_cci.cci_formularios");
        script.append("             WHERE       omi_omisiones.id_formulario = cci_formularios.id_formulario");
        script.append("             AND         omi_omisiones.id_concepto = cci_conceptos.id_concepto");
        script.append("             AND         estado IN   (");
        script.append("                                         'IDENTIFICADA','EN PROGRAMA','ASIGNADA','ADICIONADA','SUSPENDIDA POR SOLICITUD DE VERIFICACION DE MARCA FALSA','SUSPENDIDA POR SOLICITUD DE CORRECCION',");
        script.append("                                         'SUSPENDIDA POR SOLICITUD DE CORRECCION','SUSPENDIDA POR SOLICITUD DE ELIMINACION','SUSPENDIDA POR SOLICITUD DE ELIMINACION','SUSPENDIDA NO LOCALIZABLE',");
        script.append("                                         'CERRADA PARA TRASLADO A FISCALIZACION','EN PROCESO DE ASIGNACION','GESTION CRUCES DE FISCALIZACION','INACTIVO CIERRE OPERACIONES','INACTIVO NO CONTACTADO',");
        script.append("                                         'INACTIVO NO LOCALIZADO','INACTIVO RENUENTE','REPORTADA A FISCALIZACION','REVERSADO TEMPORALMENTE','SUSPENDIDA POR SOLICITUD DE INACTIVACION'");
        script.append("                                     )");
        script.append("             AND         codigo_formulario != 404");
        script.append("             AND         omi_omisiones.nit='").append(pNit).append("'");
        script.append("         )");
        script.append(" PIVOT   (");
        script.append("             COUNT(1)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");
        script.append(" UNION");
        script.append(" SELECT  descripcion                                                                     as \"descripcion\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT  EXTRACT(YEAR FROM p.fecha_aceptacion) anio,");
        script.append("                     'IPRIMA' descripcion");
        script.append("             FROM    sat_cci.omi_omisiones_iprima i, sat_dua.dua_poliza p");
        script.append("             WHERE   i.id_omision IN (");
        script.append("                                         SELECT  a.id_omision");
        script.append("                                         FROM    sat_cci.omi_omisiones a");
        script.append("                                         WHERE   a.nit='").append(pNit).append("'");
        script.append("                                         AND     (");
        script.append("                                                     a.ESTADO IN (");
        script.append("                                                                     'ASIGNADA','EN PROGRAMA','IDENTIFICADA','ADICIONADA','MARCA FALSA','EN PROCESO DE ASIGNACION','SUSPENDIDA POR SOLICITUD DE CORRECCION',");
        script.append("                                                                     'SUSPENDIDA POR SOLICITUD DE VERIFICACION DE MARCA FALSA','SUSPENDIDA POR NO LOCALIZABLE','SUSPENDIDA POR SOLICITUD DE ELIMINACION','INACTIVO NO CONTACTADO',");
        script.append("                                                                     'INACTIVO NO LOCALIZADO','SUSPENDIDA POR SOLICITUD DE INACTIVACION','INACTIVO RENUENTE'");
        script.append("                                                                 )");
        script.append("                                                     OR (a.ESTADO = 'CERRADA' AND a.MOTIVO_CIERRE IN ('No Contactado', 'No Localizado'))");
        script.append("                                                 )");
        script.append("                                     )");
        script.append("             AND     i.numero_orden = p.numero_orden");
        script.append("         )");
        script.append(" PIVOT   (");
        script.append("             COUNT(1)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");
        script.append(" UNION");
        script.append(" SELECT  descripcion                                                                     as \"descripcion\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT  EXTRACT(YEAR FROM fecha_morosidad) anio,");
        script.append("                     'Vehículos' descripcion");
        script.append("             FROM    veh_pagos_nuevas_placas");
        script.append("             WHERE   moroso = 'S'");
        script.append("             AND     nit='").append(pNit).append("'");
        script.append("         )");
        script.append(" PIVOT   (");
        script.append("             COUNT(1)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");

        return daoFinderImpl.executeSQLQuery(script.toString(), TotalDto.class);
    }

    @Override
    public List<TotalDto> findOmisosinactivos(String pNit) {
        StringBuilder script = new StringBuilder();
        int anioActual = LocalDate.now().getYear();

        script.append(" SELECT  descripcion                                                                     as \"descripcion\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT      to_number(substr(omi_omisiones.periodo,1,4)) anio,");
        script.append("                         cci_conceptos.nombre_corto descripcion");
        script.append("             FROM        sat_cci.omi_omisiones,");
        script.append("                         sat_cci.cci_conceptos,");
        script.append("                         sat_cci.cci_formularios");
        script.append("             WHERE       omi_omisiones.id_formulario = cci_formularios.id_formulario");
        script.append("             AND         omi_omisiones.id_concepto = cci_conceptos.id_concepto");
        script.append("             AND         estado IN   (");
        script.append("                                 'SUSPENDIDA POR SOLICITUD DE VERIFICACION DE MARCA FALSA','SUSPENDIDA POR SOLICITUD DE CORRECCION',");
        script.append("                                 'SUSPENDIDA POR SOLICITUD DE CORRECCION','SUSPENDIDA POR SOLICITUD DE ELIMINACION','SUSPENDIDA POR SOLICITUD DE ELIMINACION',");
        script.append("                                 'CERRADA PARA TRASLADO A FISCALIZACION','EN PROCESO DE ASIGNACION','GESTION CRUCES DE FISCALIZACION','INACTIVO CIERRE OPERACIONES',");
        script.append("                                 'PRESCRITA','ELIMINADA''CERRADA',");
        script.append("                                     )");
        script.append("             AND         codigo_formulario != 404");
        script.append("             AND         omi_omisiones.nit='").append(pNit).append("'");
        script.append("         )");
        script.append(" PIVOT   (");
        script.append("             COUNT(1)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");
        script.append(" UNION");
        script.append(" SELECT  descripcion                                                                     as \"descripcion\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT  EXTRACT(YEAR FROM p.fecha_aceptacion) anio,");
        script.append("                     'IPRIMA' descripcion");
        script.append("             FROM    sat_cci.omi_omisiones_iprima i, sat_dua.dua_poliza p");
        script.append("             WHERE   i.id_omision IN (");
        script.append("                                         SELECT  a.id_omision");
        script.append("                                         FROM    sat_cci.omi_omisiones a");
        script.append("                                         WHERE   a.nit='").append(pNit).append("'");
        script.append("                                         AND     (");
        script.append("                                                     a.ESTADO IN (");
        script.append("                                 'SUSPENDIDA POR SOLICITUD DE VERIFICACION DE MARCA FALSA','SUSPENDIDA POR SOLICITUD DE CORRECCION',");
        script.append("                                 'SUSPENDIDA POR SOLICITUD DE CORRECCION','SUSPENDIDA POR SOLICITUD DE ELIMINACION','SUSPENDIDA POR SOLICITUD DE ELIMINACION',");
        script.append("                                 'CERRADA PARA TRASLADO A FISCALIZACION','EN PROCESO DE ASIGNACION','GESTION CRUCES DE FISCALIZACION','INACTIVO CIERRE OPERACIONES',");
        script.append("                                 'PRESCRITA','ELIMINADA''CERRADA',");
        script.append("                                                                 )");
        script.append("                                                     OR (a.ESTADO = 'CERRADA' AND a.MOTIVO_CIERRE IN ('No Contactado', 'No Localizado'))");
        script.append("                                                 )");
        script.append("                                     )");
        script.append("             AND     i.numero_orden = p.numero_orden");
        script.append("         )");
        script.append(" PIVOT   (");
        script.append("             COUNT(1)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");
        script.append(" UNION");
        script.append(" SELECT  descripcion                                                                     as \"descripcion\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT  EXTRACT(YEAR FROM fecha_morosidad) anio,");
        script.append("                     'Vehículos' descripcion");
        script.append("             FROM    veh_pagos_nuevas_placas");
        script.append("             WHERE   moroso = 'S'");
        script.append("             AND     nit='").append(pNit).append("'");
        script.append("         )");
        script.append(" PIVOT   (");
        script.append("             COUNT(1)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");

        return daoFinderImpl.executeSQLQuery(script.toString(), TotalDto.class);
    }

    @Override
    public List<TotalDto> findLibrosAutorizados(String pNit) {
        StringBuilder script = new StringBuilder();
        int anioActual = LocalDate.now().getYear();

        script.append(" SELECT      descripcion                                                                     as \"descripcion\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM        (");
        script.append("                 SELECT      to_number(l.ano_autorizacion) anio,");
        script.append("                             ta.descripcion,");
        script.append("                             l.numero_hojas");
        script.append("                 FROM        aut_libros l");
        script.append("                 INNER JOIN  aut_tipos_autorizacion ta ON ta.codigo_catalogo=l.codigo_catalogo AND ta.codigo_tipo=l.codigo_tipo");
        script.append("                 WHERE       l.nit='").append(pNit).append("'");
        script.append("                 AND         nvl(l.numero_establecimiento,0)=0");
        script.append("                 AND         l.status='A'");
        script.append("             )");
        script.append(" PIVOT       (");
        script.append("                 SUM(numero_hojas)");
        script.append("                 FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("             )");
        script.append(" ORDER BY    1");

        return daoFinderImpl.executeSQLQuery(script.toString(), TotalDto.class);
    }

    @Override
    public List<TotalDto> findOtrosLibrosAutorizados(String pNit) {
        StringBuilder script = new StringBuilder();
        int anioActual = LocalDate.now().getYear();

        script.append(" SELECT      descripcion                                                                     as \"descripcion\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM        (");
        script.append("                 SELECT      to_number(l.ano_autorizacion) anio,");
        script.append("                             ta.descripcion,");
        script.append("                             l.numero_hojas");
        script.append("                 FROM        aut_libros l");
        script.append("                 INNER JOIN  aut_tipos_autorizacion ta ON ta.codigo_catalogo=l.codigo_catalogo AND ta.codigo_tipo=l.codigo_tipo");
        script.append("                 WHERE       l.nit='").append(pNit).append("'");
        script.append("                 AND         nvl(l.numero_establecimiento,0)<>0");
        script.append("                 AND         l.status='A'");
        script.append("             )");
        script.append(" PIVOT       (");
        script.append("                 SUM(numero_hojas)");
        script.append("                 FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("             )");
        script.append(" ORDER BY    1");

        return daoFinderImpl.executeSQLQuery(script.toString(), TotalDto.class);
    }

    @Override
    public List<TotalDto> findFacturasAutorizadas(String pNit) {
        StringBuilder script = new StringBuilder();
        int anioActual = LocalDate.now().getYear();

        script.append(" SELECT      trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("             trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM        (");
        script.append("                 SELECT  to_number(ano_autorizacion) anio,");
        script.append("                         total_documentos");
        script.append("                 FROM    aut_documentos");
        script.append("                 WHERE   nit='").append(pNit).append("'");
        script.append("                 AND     fecha_autorizacion IS NOT NULL");
        script.append("             )");
        script.append(" PIVOT       (");
        script.append("                 SUM(total_documentos)");
        script.append("                 FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("             )");

        return daoFinderImpl.executeSQLQuery(script.toString(), TotalDto.class);
    }

    @Override
    public TotalDto findVehiculosActivos(String pNit) {
        StringBuilder script = new StringBuilder();
        int anioActual = LocalDate.now().getYear();

        script.append(" SELECT  descripcion                                                                     as \"descripcion\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT      EXTRACT(YEAR FROM p.fecha_traspado) anio,");
        script.append("                         'Activos' descripcion");
        script.append("             FROM        veh_vehiculos v");
        script.append("             INNER JOIN  veh_propiedades p ON p.anio_alza=v.anio_alza AND p.identificador=v.identificador AND p.digito_verificador=v.digito_verificador");
        script.append("             WHERE       v.estatus='A'");
        script.append("             AND         p.nit='").append(pNit).append("'");
        script.append("         )");
        script.append(" PIVOT   (");

        script.append("             COUNT(1)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");

        List<TotalDto> lista = daoFinderImpl.executeSQLQuery(script.toString(), TotalDto.class);
        return lista.isEmpty() ? new TotalDto() : lista.get(0);
    }

    @Override
    public TotalDto findCargaTributaria(String pNit) {
        StringBuilder script = new StringBuilder();
        int anioActual = LocalDate.now().getYear();

        script.append(" SELECT  trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990D00')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990D00')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990D00')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990D00')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990D00'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT      EXTRACT(YEAR FROM fecha_adicion) anio,");
        script.append("                         valor_pago");
        script.append("             FROM        ba_declaraciones");
        script.append("             WHERE       nit='").append(pNit).append("'");
        script.append("             AND         status='C'");
        script.append("         )");
        script.append(" PIVOT   (");
        script.append("             SUM(valor_pago)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");

        return (TotalDto) daoFinderImpl.executeSQLQuery(script.toString(), TotalDto.class).get(0);
    }

    @Override
    public List<TotalDto> findAuditorias(String pNit) {
        StringBuilder script = new StringBuilder();
        int anioActual = LocalDate.now().getYear();

        script.append(" SELECT  descripcion                                                                     as \"descripcion\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT      EXTRACT(YEAR FROM n.fecha_emision) anio,");
        script.append("                         'En proceso' descripcion");
        script.append("             FROM        fg_nombramientos n");
        script.append("             INNER JOIN  fg_bitacoras b ON n.nombramiento=b.nombramiento");
        script.append("             INNER JOIN  (");
        script.append("                             SELECT      bitacora.nombramiento,");
        script.append("                                         MAX(bitacora.fecha) ultima_fecha");
        script.append("                             FROM        fg_bitacoras bitacora");
        script.append("                             GROUP BY    bitacora.nombramiento");
        script.append("                         ) etapa ON b.nombramiento=etapa.nombramiento AND b.fecha=etapa.ultima_fecha");
        script.append("             WHERE       n.nit='").append(pNit).append("'");
        script.append("             AND         n.codigo_estado<30");
        script.append("             AND         (n.generado='APA' OR n.generado IS NULL)");
        script.append("         )");
        script.append(" PIVOT   (");
        script.append("             COUNT(1)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");
        script.append(" UNION");
        script.append(" SELECT  descripcion                                                                     as \"descripcion\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT      EXTRACT(YEAR FROM i.fecha_emision) anio,");
        script.append("                         'Terminadas con resultados' descripcion");
        script.append("             FROM        (");
        script.append("                             SELECT      n.nombramiento");
        script.append("                             FROM        fg_nombramientos n");
        script.append("                             INNER JOIN  fg_bitacoras b ON n.nombramiento=b.nombramiento");
        script.append("                             INNER JOIN  (");
        script.append("                                             SELECT      bitacora.nombramiento,");
        script.append("                                                         MAX(bitacora.fecha) ultima_fecha");
        script.append("                                             FROM        fg_bitacoras bitacora");
        script.append("                                             GROUP BY    bitacora.nombramiento");
        script.append("                                         ) etapa ON b.nombramiento=etapa.nombramiento AND b.fecha=etapa.ultima_fecha");
        script.append("                             WHERE       n.nit='").append(pNit).append("'");
        script.append("                             AND         n.codigo_estado=30");
        script.append("                             AND         (n.generado='APA' OR n.generado IS NULL)");
        script.append("                         ) a");
        script.append("             INNER JOIN  fg_informe i ON i.nombramiento=a.nombramiento");
        script.append("             INNER JOIN  fg_tipo_informe ti ON ti.tipo_informe=i.tipo_informe");
        script.append("             WHERE       ti.tipo_informe IN (3,4,5,8,9,11,12,13)");
        script.append("         )");
        script.append(" PIVOT   (");
        script.append("             COUNT(1)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");
        script.append(" UNION");
        script.append(" SELECT  descripcion                                                                     as \"descripcion\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT      EXTRACT(YEAR FROM i.fecha_emision) anio,");
        script.append("                         'Terminadas sin resultados' descripcion");
        script.append("             FROM        (");
        script.append("                             SELECT      n.nombramiento");
        script.append("                             FROM        fg_nombramientos n");
        script.append("                             INNER JOIN  fg_bitacoras b ON n.nombramiento=b.nombramiento");
        script.append("                             INNER JOIN  (");
        script.append("                                             SELECT      bitacora.nombramiento,");
        script.append("                                                         MAX(bitacora.fecha) ultima_fecha");
        script.append("                                             FROM        fg_bitacoras bitacora");
        script.append("                                             GROUP BY    bitacora.nombramiento");
        script.append("                                         ) etapa ON b.nombramiento=etapa.nombramiento AND b.fecha=etapa.ultima_fecha");
        script.append("                             WHERE       n.nit='").append(pNit).append("'");
        script.append("                             AND         n.codigo_estado=30");
        script.append("                             AND         (n.generado='APA' OR n.generado IS NULL)");
        script.append("                         ) a");
        script.append("             INNER JOIN  fg_informe i ON i.nombramiento=a.nombramiento");
        script.append("             INNER JOIN  fg_tipo_informe ti ON ti.tipo_informe=i.tipo_informe");
        script.append("             WHERE       ti.tipo_informe IN (1,2,6,7,10)");
        script.append("         )");
        script.append(" PIVOT   (");
        script.append("             COUNT(1)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");

        return daoFinderImpl.executeSQLQuery(script.toString(), TotalDto.class);
    }

    @Override
    public List<TotalDto> findExpedientesJuridico(String pNit) {
        StringBuilder script = new StringBuilder();
        int anioActual = LocalDate.now().getYear();

        script.append(" SELECT  descripcion                                                                     as \"descripcion\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT      EXTRACT(YEAR FROM e.fecha_ingreso) anio,");
        script.append("                         u.nombre_unidad descripcion");
        script.append("             FROM        sat_sgel.sgel_expediente e");
        script.append("             INNER JOIN  sat_sgel.sgel_unidad u ON u.id_unidad=e.id_unidad_destino");
        script.append("             INNER JOIN  ex_expediente ee ON ee.numero_expediente=e.id_sgce");
        script.append("             WHERE       e.id_tipo_expediente=2");
        script.append("             AND         e.id_estado=7");
        script.append("             AND         ee.nit='").append(pNit).append("'");
        script.append("         )");
        script.append(" PIVOT   (");
        script.append("             COUNT(1)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");

        return daoFinderImpl.executeSQLQuery(script.toString(), TotalDto.class);
    }

    @Override
    public List<TotalDto> findExpedientesTributa(String pNit) {
        StringBuilder script = new StringBuilder();
        int anioActual = LocalDate.now().getYear();

        script.append(" SELECT  trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT  EXTRACT(YEAR FROM fecha_creacion) anio");
        script.append("             FROM    ex_expediente");
        script.append("             WHERE   codigo_dependencia_actual=29");
        script.append("             AND     nit='").append(pNit).append("'");
        script.append("         )");
        script.append(" PIVOT   (");
        script.append("             COUNT(1)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");

        return daoFinderImpl.executeSQLQuery(script.toString(), TotalDto.class);
    }

    @Override
    public List<TotalDto> findExpedientesAdm(String pNit) {
        StringBuilder script = new StringBuilder();
        int anioActual = LocalDate.now().getYear();

        script.append(" SELECT  trim(to_char(nvl(\"").append(anioActual - 4).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos4\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 3).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos3\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 2).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos2\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual - 1).append("\",0),'99G999G999G999G990')) as \"cantidadAnioActualMenos1\",");
        script.append("         trim(to_char(nvl(\"").append(anioActual).append("\",0),'99G999G999G999G990'))   as \"cantidadAnioActual\"");
        script.append(" FROM    (");
        script.append("             SELECT      EXTRACT(YEAR FROM e.fecha_ingreso) anio");
        script.append("             FROM        sat_sgel.sgel_expediente e");
        script.append("             INNER JOIN  ex_expediente ee ON ee.numero_expediente=e.id_sgce");
        script.append("             WHERE       e.id_tipo_expediente=1");
        script.append("             AND         e.id_estado=7");
        script.append("             AND         ee.nit='").append(pNit).append("'");
        script.append("         )");
        script.append(" PIVOT   (");
        script.append("             COUNT(1)");
        script.append("             FOR anio IN (").append(anioActual - 4).append(",").append(anioActual - 3).append(",").append(anioActual - 2).append(",").append(anioActual - 1).append(",").append(anioActual).append(")");
        script.append("         )");

        return daoFinderImpl.executeSQLQuery(script.toString(), TotalDto.class);
    }

    @Override
    public List<ApoderadoDto> findApoderadosByRepresentante(String pNit) {
        Map<String, Object> params = new HashMap<>();
        params.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("findApoderadosByRepresentante", params, ApoderadoDto.class);
    }

    @Override
    public List<String> findAcreditamientosByNit(String pNit) {
        Map<String, Object> params = new HashMap<>();
        params.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("findAcreditamientosByNit", params);
    }

    @Transactional
    @Override
    public void registrarAcceso(BitacoraConsulta360Dto pBitacora) {
        Consulta360BitacoraId idBitacora = new Consulta360BitacoraId();
        idBitacora.setPaginaConsultada(new BigDecimal(pBitacora.getPaginaConsultada()));
        idBitacora.setNitConsultado(pBitacora.getNitConsultado().toUpperCase());
        idBitacora.setFechaConsulto(new Date());
        idBitacora.setNitConsulto(pBitacora.getNitConsulto().toUpperCase());
        idBitacora.setIpConsulto(pBitacora.getIpConsulto());

        daoCrudImpl.save(new Consulta360Bitacora(idBitacora));
    }

    @Override
    public List<HistorialRiesgoDto> findHistorialRiesgoInstitucional(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("findHistorialRiesgoInstitucional", param, HistorialRiesgoDto.class);
    }

    @Override
    public List<HistorialRiesgoDto> findHistorialDCFRiesgoInstitucional(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("findHistorialDCFRiesgoInstitucional", param, HistorialRiesgoDto.class);
    }

    @Override
    public BigDecimal obtenerCodigoRegimenRiesgo(String pNit, String pAnio) {
        BigDecimal resultado = BigDecimal.ZERO;

        Map<String, Object> params = new HashMap<>();
        params.put("pNit", pNit);
        params.put("pAnio", pAnio);

        resultado = this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerCodigoRegimenRiesgo", params));

        return resultado;

    }

    @Override
    public List<DetalleRiesgoSentenciaDto> obtenerSentenciaMapeoPorRegimen(String pNit, String pAnio, String pRegimen) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("pAnio", pAnio);
        param.put("pRegimen", pRegimen);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerSentenciaMapeoPorRegimen", param, DetalleRiesgoSentenciaDto.class);
    }

    @Override
    public List<DetalleRiesgoDto> obtenerVariablesDeRiesgo(List<DetalleRiesgoSentenciaDto> pListaSentencias) {
        StringBuilder script = new StringBuilder();
        String SentenciaTemp;

        //int anioActual=LocalDate.now().getYear();
        script.append("SELECT ");
        script.append(" ' '||ROWNUM AS \"indice\", ");
        script.append(" INFO.SIGNIFICADO AS \"significado\", ");
        script.append(" INFO.EXPLICACION_COLOQUIAL AS \"explicacionColoquial\" ");
        script.append(" FROM ");
        script.append("( ");

        for (int indice = 0; indice < pListaSentencias.size(); indice++) {
            SentenciaTemp = pListaSentencias.get(indice).getSentenciaDetalleRiesgo();
            LOGGER.error(SentenciaTemp);
            script.append(SentenciaTemp).append(" ");

        }

        script.append(" SELECT 'X' SIGNIFICADO,'X' EXPLICACION_COLOQUIAL, 0 VALOR FROM DUAL ");
        script.append(") INFO ");
        script.append("WHERE INFO.VALOR > 0 ");

        LOGGER.error("empieza la ejecucion final");
        LOGGER.error(script.toString());

        return daoFinderImpl.executeSQLQuery(script.toString(), DetalleRiesgoDto.class);

    }

    @Override
    public List<DeclaracionesPresentadasDto> obtenerDescripcionesFormulario(BigDecimal id_reporte) {
        Map<String, Object> param = new HashMap<>();
        param.put("id_reporte", id_reporte);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerDescripcionesFormulario", param, DeclaracionesPresentadasDto.class);
    }

    @Override
    public ArrayList<PequenoContribuyenteEncabezadoDto> obtenerEncabezadoPeqContribuyente(String pNit) {
        String nombreCampo = " AND fecha_cambio_regimen=(SELECT MAX(fecha_cambio_regimen) FROM sat_general.o_afiliacion_contribuyentes_dm WHERE nit ='" + pNit + "' )";
        ArrayList<PequenoContribuyenteEncabezadoDto> listaEncabezado = new ArrayList();

        String SQL = "SELECT TOP 1\n"
                + "nit                      AS  'nit',\n"
                + "telefono                 AS  'telefono',\n"
                + "nombre_ordenado          AS  'nombreOrdenado',\n"
                + "direccion                AS  'direccion' ,\n"
                + "clasificacion            AS  'clasificacion',\n"
                + "gerencia                 AS  'gerencia',\n"
                + "region                   AS  'region' ,\n"
                + "actividad_economica      AS  'actividadeEconomica',\n"
                + "nombre_regimen           AS  'nombreRegimen', \n"
                + "status                   AS  'status',\n"
                + "convert(nvarchar,fecha_cambio_regimen, 1)    AS  'fechaCambioRegimen', \n"
                + "personeria               AS  'personeria', \n"
                + "tipo_persona             AS  'tipoPersona', \n"
                + "marca_no_localizado      AS  'marcaNoLocalizado', \n"
                + "nombre_color             AS  'nombreColor', \n"
                + "marca_establecimiento    AS  'marcaEstablecimiento', \n"
                + "fecha_fallecimiento      AS  'fechafallecimiento', \n"
                + "nit_contador             AS  'nitContador' , \n"
                + "correo_agencia_virtual   AS  'correoAgenciaVirtual' , \n"
                + "correo_rtu               AS  'correoRtu' , \n"
                + "sector_economico         AS  'sectorEconomico' , \n"
                + "omiso                    AS  'omiso' , \n"
                + "telefono_contador        AS  'telefonoContador' , \n"
                + "ISNULL(direccion_invalida ,'N/A')       AS  'direccionInvalida' , \n"
                + "nombre_municipio         AS  'nombreMunicipio' , \n"
                + "ISNULL(plan_operativo,'N/A')   AS  'planOperativo' , \n"
                + "nombre_departamento      AS  'nombreDepartamento' \n"
                + "FROM sat_general.o_afiliacion_contribuyentes_dm\n "
                + "where nit ='" + pNit + "'\n";

        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            System.out.print(SQL);
            rs = statement.executeQuery(SQL + nombreCampo);

            while (rs.next()) {
                PequenoContribuyenteEncabezadoDto datos = new PequenoContribuyenteEncabezadoDto();
                datos.setNit(rs.getString("nit"));
                datos.setTelefono(rs.getString("telefono"));
                datos.setNombreOrdenado(rs.getString("nombreOrdenado"));
                datos.setDireccion(rs.getString("direccion"));
                datos.setClasificacion(rs.getString("clasificacion"));
                datos.setGerencia(rs.getString("gerencia"));
                datos.setRegion(rs.getString("region"));
                datos.setActividadEconomica(rs.getString("actividadeEconomica"));
                datos.setNombreRegimen(rs.getString("nombreRegimen"));
                datos.setStatus(rs.getString("status"));
                datos.setFechaCambioRegimen(rs.getString("fechaCambioRegimen"));
                datos.setPersoneria(rs.getString("personeria"));
                datos.setTipoPersona(rs.getString("tipoPersona"));
                datos.setMarcaNoLocalizado(rs.getString("marcaNoLocalizado"));
                datos.setNombreColor(rs.getString("nombreColor"));
                datos.setMarcaEstablecimiento(rs.getString("marcaEstablecimiento"));
                datos.setFechafallecimiento(rs.getString("fechafallecimiento"));
                datos.setNitContador(rs.getString("nitContador"));
                datos.setCorreoAgenciaVirtual(rs.getString("correoAgenciaVirtual"));
                datos.setCorreoRtu(rs.getString("correoRtu"));
                datos.setSectorEconomico(rs.getString("sectorEconomico"));
                datos.setOmiso(rs.getString("omiso"));
                datos.setTelefonoContador(rs.getString("telefonoContador"));
                datos.setDireccionInvalida(rs.getString("direccionInvalida"));
                datos.setNombreMunicipio(rs.getString("nombreMunicipio"));
                datos.setPlanOperativo(rs.getString("planOperativo"));
                datos.setNombreDepartamento(rs.getString("nombreDepartamento"));

                listaEncabezado.add(datos);

            }
            conn.close();
            statement.close();

        } catch (Exception ex) {
            LOGGER.error("Error: ");
            LOGGER.error(ex.getMessage());
        }

        if (listaEncabezado.isEmpty()) {

            nombreCampo = " AND fecha_adicion_afiliacion=(SELECT MAX(fecha_adicion_afiliacion) FROM sat_general.o_afiliacion_contribuyentes_dm WHERE nit ='" + pNit + "' )";

            Statement statement2;
            ResultSet rs2;

            try (Connection conn2 = getConnection()) {
                statement2 = conn2.createStatement();
                rs2 = statement2.executeQuery(SQL + nombreCampo);

                while (rs2.next()) {
                    PequenoContribuyenteEncabezadoDto datos = new PequenoContribuyenteEncabezadoDto();
                    datos.setNit(rs2.getString("nit"));
                    datos.setTelefono(rs2.getString("telefono"));
                    datos.setNombreOrdenado(rs2.getString("nombreOrdenado"));
                    datos.setDireccion(rs2.getString("direccion"));
                    datos.setClasificacion(rs2.getString("clasificacion"));
                    datos.setGerencia(rs2.getString("gerencia"));
                    datos.setRegion(rs2.getString("region"));
                    datos.setActividadEconomica(rs2.getString("actividadeEconomica"));
                    datos.setNombreRegimen(rs2.getString("nombreRegimen"));
                    datos.setStatus(rs2.getString("status"));
                    datos.setFechaCambioRegimen(rs2.getString("fechaCambioRegimen"));
                    datos.setPersoneria(rs2.getString("personeria"));
                    datos.setTipoPersona(rs2.getString("tipoPersona"));
                    datos.setMarcaNoLocalizado(rs2.getString("marcaNoLocalizado"));
                    datos.setNombreColor(rs2.getString("nombreColor"));
                    datos.setMarcaEstablecimiento(rs2.getString("marcaEstablecimiento"));
                    datos.setFechafallecimiento(rs2.getString("fechafallecimiento"));
                    datos.setNitContador(rs2.getString("nitContador"));
                    datos.setCorreoAgenciaVirtual(rs2.getString("correoAgenciaVirtual"));
                    datos.setCorreoRtu(rs2.getString("correoRtu"));
                    datos.setSectorEconomico(rs2.getString("sectorEconomico"));
                    datos.setOmiso(rs2.getString("omiso"));
                    datos.setTelefonoContador(rs2.getString("telefonoContador"));
                    datos.setDireccionInvalida(rs2.getString("direccionInvalida"));
                    datos.setNombreMunicipio(rs2.getString("nombreMunicipio"));
                    datos.setPlanOperativo(rs2.getString("planOperativo"));
                    datos.setNombreDepartamento(rs2.getString("nombreDepartamento"));

                    listaEncabezado.add(datos);

                }
                conn2.close();
                statement2.close();

            } catch (Exception ex) {
                LOGGER.error("Error: ");
                LOGGER.error(ex.getMessage());
            }

        }//end del if

        return listaEncabezado;
    }

    public Connection getConnection() throws Exception {
        InitialContext inc = new InitialContext();
        DataSource ds = (DataSource) inc.lookup("java:comp/env/jdbc/consulta360azure");
        return ds.getConnection();
    }

    public IngresosFelvsDeclaradoIvaPequeDto obtenerPequeñoContribuyentes(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        List<IngresosFelvsDeclaradoIvaPequeDto> list = this.daoFinderImpl.findByNamedQueryParam("obtenerContribuyenteByNit", param, IngresosFelvsDeclaradoIvaPequeDto.class);
        return list.isEmpty() ? new IngresosFelvsDeclaradoIvaPequeDto() : list.get(0);
    }

    @Transactional
    @Override
    public BigDecimal guardarArchivo(HistorialCargaDto cargaArchivo) {
        CifHistorialCargas historial = new CifHistorialCargas();
        /*  historial.setIdCarga(cargaArchivo.getIdCarga());*/
        historial.setNombreArchivo(cargaArchivo.getNombreArchivo());
        historial.setFechaCargaRegistro(cargaArchivo.getFechaCargaRegistro());
        historial.setCantidadRegistrosCargados(cargaArchivo.getCantidadRegistrosCargados());
        historial.setUsuarioRegistro(cargaArchivo.getUsuarioRegistro());
        historial.setDocumentoArchivo(cargaArchivo.getDocumentoArchivo());
        daoCrudImpl.save(historial);
        return historial.getIdCarga();
    }

    @Override
    public List<HistorialCargaDto> listaHistorial() {
        List<HistorialCargaDto> listaTipologia = this.daoFinderImpl.findByNamedQuery("obtenerHistorialCargaTipologia", HistorialCargaDto.class);
        return listaTipologia;
    }

    @Override
    public HistorialCargaDto obtenerArchivoRegistros(BigDecimal idCarga) {
        Map<String, Object> params = new HashMap<>();
        params.put("idCarga", idCarga);
        return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerArchivoEspecifico", params, HistorialCargaDto.class));
    }

    @Override
    @Transactional
    public BigDecimal guardarDetalleArchivoV2(CifTipologiasDeEvasion detalleArchivo) {
        daoCrudImpl.save(detalleArchivo);
        return detalleArchivo.getId();
    }

    @Override
    public obtenerConteoDto obtenerRegistroContadores(String pNit, int idTipologia, int anio, int tipoConsulta) {
        Map<String, Object> params = new HashMap<>();
        switch (tipoConsulta) {
            case 1:
                params.put("pNit", pNit);
                params.put("idTipologia", idTipologia);
                params.put("anioInformacion", anio);

                return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerRegistro", params, obtenerConteoDto.class));

            case 2:
                params.put("pNit", pNit);

                return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerCampoRepetidoNit", params, obtenerConteoDto.class));

            case 3:
                params.put("idTipologia", idTipologia);

                return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerCampoRepetidoIdTipologia", params, obtenerConteoDto.class));
            case 4:
                params.put("anioInformacion", anio);

                return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerCampoRepetidoAnioInfo", params, obtenerConteoDto.class));

        }
        return null;

    }

    @Override
    public obtenerConteoDto obteneridCatalogoTipologia(int idTipologia) {
        Map<String, Object> params = new HashMap<>();

        params.put("idTipologia", idTipologia);

        return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerCampoRepetidoidCatalogoTipologias", params, obtenerConteoDto.class));

    }

    @Transactional
    @Override
    public BigDecimal guardarCargaVerificacionesCampo(HistorialCargaVerificacionesCampoDto cargaVerificacionesCampo) {
        CifCargaVerificacionesCampo historial = new CifCargaVerificacionesCampo();
        /*  historial.setIdCarga(cargaArchivo.getIdCarga());*/
        historial.setNombreArchivo(cargaVerificacionesCampo.getNombreArchivo());
        historial.setFechaCargaRegistro(cargaVerificacionesCampo.getFechaCargaRegistro());
        historial.setCantidadRegistrosCargados(cargaVerificacionesCampo.getCantidadRegistrosCargados());
        historial.setUsuarioRegistro(cargaVerificacionesCampo.getUsuarioRegistro());
        historial.setDocumentoArchivo(cargaVerificacionesCampo.getDocumentoArchivo());
        daoCrudImpl.save(historial);
        return historial.getIdCargaCampo();
    }

    @Override
    public List<HistorialCargaVerificacionesCampoDto> listaVerificacionesCampo() {
        List<HistorialCargaVerificacionesCampoDto> listaVerificaciones = this.daoFinderImpl.findByNamedQuery("obtenerHistorialCargaVerificaciones", HistorialCargaVerificacionesCampoDto.class
        );
        return listaVerificaciones;
    }

    @Override
    @Transactional
    public BigDecimal guardarDetalleArchivoVerificaciones(CifVerificacionesEnCampo detalleArchivoVerificaciones) {
        daoCrudImpl.save(detalleArchivoVerificaciones);
        return detalleArchivoVerificaciones.getId();
    }

    @Override
    public obtenerConteoDto obtenerRegistroContadoresVerificaciones(String pNit, String direccionVerificada, String departamento, String municipio, String tipoDireccion, int tipoConsulta) {
        Map<String, Object> params = new HashMap<>();
        switch (tipoConsulta) {
            case 1:
                params.put("pNit", pNit);
                params.put("direccionVerificada", direccionVerificada);
                params.put("departamento", departamento);
                params.put("municipio", municipio);
                params.put("tipoDireccion", tipoDireccion);

                return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerRegistroVerificaciones", params, obtenerConteoDto.class
                ));

            case 2:
                params.put("pNit", pNit);

                return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerCampoRepetidoNit", params, obtenerConteoDto.class
                ));

            case 3:
                params.put("direccionVerificada", direccionVerificada);

                return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerCampoRepetidoDireccion", params, obtenerConteoDto.class
                ));
            case 4:
                params.put("departamento", departamento);

                return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerCampoRepetidoDepartamentos", params, obtenerConteoDto.class
                ));

            case 5:
                params.put("municipio", municipio);

                return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerCampoRepetidoMunicipio", params, obtenerConteoDto.class
                ));

            case 6:
                params.put("tipoDireccion", tipoDireccion);

                return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerCampoRepetidoTipoDireccion", params, obtenerConteoDto.class
                ));

        }
        return null;

    }

    @Override
    public HistorialCargaVerificacionesCampoDto obtenerArchivoRegistrosVerificaciones(BigDecimal idCargaCampo) {
        Map<String, Object> params = new HashMap<>();
        params.put("idCargaCampo", idCargaCampo);
        return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerArchivoEspecificoVerificaciones", params, HistorialCargaVerificacionesCampoDto.class));
    }

    @Override
    public List<HistorialCargaFechaFormatDto> obtenerHistorialFormatoFecha() {
        return this.daoFinderImpl.findByNamedQuery("obtenerHistorialCargaTipologiaFechaHora", HistorialCargaFechaFormatDto.class);
    }

    @Override
    public List<HistorialCargaVerificacionesFechaFormatDto> obtenerHistorialVerificacionesFormatoFecha() {
        return this.daoFinderImpl.findByNamedQuery("obtenerHistorialCargaVerificacionesFechaHora", HistorialCargaVerificacionesFechaFormatDto.class);
    }

    @Override
    public List<GeneralVerificacionesDto> VerificacionesEnCampo(String pNit) {
        String Query = "SELECT \n"
                + "DIRECCION_VERIFICADA    AS \"direccionVerificada\",\n"
                + "LONGITUD                AS  \"longitud\",\n"
                + "LATITUD                 AS  \"latitud\",\n"
                + "TO_CHAR( FECHA_CREACION, 'DD/MM/YYYY' )           AS  \"fechaCreacion\",\n"
                + "ESTADO_DIRECCION        AS  \"estadoDireccion\"\n"
                + "FROM CIF_VERIFICACIONES_EN_CAMPO\n"
                + "WHERE NIT= '" + pNit + "'";

        return daoFinderImpl.executeSQLQuery(Query.toString(), GeneralVerificacionesDto.class);
    }

    @Override
    public VehiculosDTO obtenerImpuestoISCV(String pNit) {
        Map<String, Object> params = new HashMap<>();
        params.put("pNit", pNit);
        return this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerImpuestoISCV", params, VehiculosDTO.class));

    }

    @Override
    public List<TipologiasEvasionAnualDto> obtenerTipologiasEvasionAnualByNit(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("obtenerTipologiasEvasionAnualByNit", param, TipologiasEvasionAnualDto.class);
    }

    @Override
    public List<TipologiasEvasionAnualDto> obtenerTipologiasEvasionAnualByNitFiveYears(String pNit, List<Integer> anioEvaluar) {
        String Query = " SELECT    rn.name as \"nombreAnio\", NVL(cnt, 0) as \"cantidadAnios\" \n"
                + "FROM      (SELECT " + anioEvaluar.get(0) + "  AS name FROM dual \n"
                + "           UNION ALL \n"
                + "           SELECT " + anioEvaluar.get(1) + " FROM dual \n"
                + "           UNION ALL \n"
                + "           SELECT " + anioEvaluar.get(2) + " FROM dual \n"
                + "           UNION ALL \n"
                + "           SELECT " + anioEvaluar.get(3) + " FROM dual \n"
                + "           UNION ALL \n"
                + "           SELECT " + anioEvaluar.get(4) + " FROM dual) rn\n"
                + "LEFT JOIN (SELECT   EXTRACT(YEAR FROM FECHA_REGISTRO) name, COUNT(*) AS cnt\n"
                + "           FROM     CIF_TIPOLOGIAS_DE_EVASION\n"
                + "           WHERE  NIT = '" + pNit + "' and \n"
                + "            extract(YEAR from FECHA_REGISTRO) in (" + anioEvaluar.get(0) + "," + anioEvaluar.get(1) + "," + anioEvaluar.get(2) + "," + anioEvaluar.get(3) + "," + anioEvaluar.get(4) + ") \n"
                + "           GROUP BY EXTRACT(YEAR FROM FECHA_REGISTRO) ) n ON n.name = rn.name\n"
                + "           ORDER BY 1 desc ";

        return daoFinderImpl.executeSQLQuery(Query.toString(), TipologiasEvasionAnualDto.class);

    }

    @Override
    @Transactional()
    public ArrayList<ParameterDto> procedimientoInfoVehiculos(String pNit) {

        ArrayList<ParameterDto> lista = new ArrayList<>();
        try {
            lista.add(new ParameterDto(Types.VARCHAR, 1, false, pNit));
            this.calli.callProcedureOrFunction("procedimientoInfoVehiculos", lista);
            ParameterDto parameterDto = lista.get(0);
            String result = (String) parameterDto.getValue();
            System.out.println("result obtenerSanciones en implement=" + result);

        } catch (Exception ex) {
            System.out.println("Error en la ejecucion del procedimiento: " + ex.getMessage());
        }
        return lista;

    }

    @Override
    public List<ConveniosActivosDto> conveniosActivos(String pNit) {
        String Query = "SELECT\n"
                + "ROW_NUMBER() OVER (ORDER BY A.FECHA_PRESENTACION) as \"correlativo\",\n"
                + "    ID_EXPEDIENTE as \"idExpediente\", TO_CHAR(FECHA_RUAT,'dd/MM/yyyy') as \"fechaRuat\", MONTO_AUTORIZADO as \"montoAutorizado\", VALOR_PAGADO as \"valorPago\",\n"
                + "    VALOR_INCUMPLIDO as \"valorIncumplido\", (MONTO_AUTORIZADO - VALOR_PAGADO - VALOR_INCUMPLIDO) as \"valorRestante\"\n"
                + "    FROM ( SELECT S.FECHA_PRESENTACION,\n"
                + "    (SELECT ID_EXPEDIENTE\n"
                + "        FROM Sat_Gft.Gft_Expediente_Documentos Ed\n"
                + "        WHERE ED.CODIGO_FORMULARIO = 821\n"
                + "        AND Ed.Id_Documento = S.ID_DOCUMENTO_SAD\n"
                + "        AND FECHA_INCORPORACION = (SELECT MAX(FECHA_INCORPORACION)\n"
                + "        FROM Sat_Gft.Gft_Expediente_Documentos\n"
                + "        WHERE ID_DOCUMENTO = ED.ID_DOCUMENTO)) ID_EXPEDIENTE,\n"
                + "    (SELECT FECHA_RECAUDO FROM SAT_SAD.SAD_DOCUMENTOS WHERE NUMERO_FORMULARIO = S.RUAT_APROBACION) FECHA_RUAT,\n"
                + "    S.MONTO_CONVENIO AS MONTO_AUTORIZADO,\n"
                + "    (SELECT NVL(SUM(SUM(IMPUESTO) +\n"
                + "       SUM(INTERES) +\n"
                + "       SUM(MULTA_FORMAL) +\n"
                + "       SUM(MULTA_OMISION) +\n"
                + "       SUM(MORA) +\n"
                + "       SUM(MULTA_RECTIFICATIVA) +\n"
                + "       NVL(SUM(COMPLEMENTO_INTERES),0) +\n"
                + "       NVL(SUM(COMPLEMENTO_MORA),0)),0) AS VALOR_PAGADO\n"
                + "        FROM SAT_CCI.CON_CUOTAS C\n"
                + "       WHERE NIT = S.NIT AND NUMERO_FORMULARIO = S.NUMERO_FORMULARIO\n"
                + "         AND CUOTA IN (SELECT CUOTA\n"
                + "            FROM SAT_CCI.CON_PAGOS P\n"
                + "           WHERE P.NIT = S.NIT\n"
                + "             AND NUMERO_FORMULARIO = S.NUMERO_FORMULARIO AND ESTADO = 'C' AND ANIO_FISCAL IS NOT NULL\n"
                + "             AND NUMERO_DOCUMENTO IS NOT NULL)\n"
                + "        GROUP BY IMPUESTO) VALOR_PAGADO,\n"
                + "    (SELECT NVL(SUM(SUM(IMPUESTO) +\n"
                + "       SUM(INTERES) +\n"
                + "       SUM(MULTA_FORMAL) +\n"
                + "       SUM(MULTA_OMISION) +\n"
                + "       SUM(MORA) +\n"
                + "       SUM(MULTA_RECTIFICATIVA) +\n"
                + "       NVL(SUM(COMPLEMENTO_INTERES),0) +\n"
                + "       NVL(SUM(COMPLEMENTO_MORA),0)),0) AS VALOR_INCUMPLIDO\n"
                + "    FROM SAT_CCI.CON_CUOTAS C\n"
                + "    WHERE NIT = S.NIT AND NUMERO_FORMULARIO = S.NUMERO_FORMULARIO\n"
                + "    AND CUOTA IN (SELECT CUOTA\n"
                + "    FROM SAT_CCI.CON_PAGOS P\n"
                + "    WHERE P.NIT = S.NIT\n"
                + "    AND NUMERO_FORMULARIO = S.NUMERO_FORMULARIO\n"
                + "    AND ESTADO = 'P'\n"
                + "    AND FECHA_PAGO < SYSDATE)\n"
                + "    GROUP BY IMPUESTO) VALOR_INCUMPLIDO\n"
                + "FROM SAT_CCI.CON_SOLICITUD S\n"
                + "WHERE S.NIT = '" + pNit + "'\n"
                + "AND ESTADO_CONVENIO IN ('LEGALIZADO', 'TRASLADADO INCUMPLIDOS')) A\n"
                + "WHERE A.ID_EXPEDIENTE IS NOT NULL\n"
                + "AND A.FECHA_RUAT IS NOT NULL\n";

        return daoFinderImpl.executeSQLQuery(Query.toString(), ConveniosActivosDto.class);
    }

    @Override
    public ArrayList<IndicadoresCreditoDebitoDto> obtenerIndicadoresDebitoCredito(String pNit) {
        ArrayList<IndicadoresCreditoDebitoDto> listaIndicadoresDebitoCredito = new ArrayList();

        String SQL = " DECLARE @nit varchar(18) ='" + pNit + "' \n"
                + " SELECT \n"
                + "year(getdate())                               AS    'anio',\n"
                + "isnull(count(sector_economico),0)             AS    'sectorEconomico',\n"
                + "isnull(count(anio),0)                         AS    'cantidadRegistros'\n"
                + "FROM sat_indicadores.o_detalle_debito_credito_ag\n"
                + "WHERE nit_del_contribuyente = @nit \n"
                + "and YEAR(anio) = year(getdate())\n "
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-1,getdate()))              AS 'anio',\n"
                + "isnull(count(sector_economico),0)             AS 'sectorEconomico',\n"
                + "isnull(count(anio),0)                         AS 'cantidadRegistros' \n"
                + "From sat_indicadores.o_detalle_debito_credito_ag\n "
                + "where nit_del_contribuyente = @nit\n"
                + "and YEAR(anio) = year(dateadd(yyyy,-1,getdate()))\n"
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-2,getdate()))              AS 'anio',\n"
                + "isnull(count(sector_economico),0)             AS 'sectorEconomico',\n"
                + "isnull(count(anio),0)                         AS 'cantidadRegistros' \n"
                + "From sat_indicadores.o_detalle_debito_credito_ag\n"
                + "where nit_del_contribuyente =  @nit \n"
                + "and YEAR(anio) = year(dateadd(yyyy,-2,getdate()))\n"
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-3,getdate()))              AS  'anio',\n"
                + "isnull(count(sector_economico),0)             AS  'sectorEconomico',\n"
                + "isnull(count(anio),0)                         AS  'cantidadRegistros' \n"
                + "From sat_indicadores.o_detalle_debito_credito_ag\n"
                + "where nit_del_contribuyente =  @nit "
                + "and YEAR(anio) = year(dateadd(yyyy,-3,getdate()))\n"
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-4,getdate()))              AS 'anio',\n"
                + "isnull(count(sector_economico),0)             AS 'sectorEconomico',\n"
                + "isnull(count(anio),0)                         AS 'cantidadRegistros'\n"
                + "From sat_indicadores.o_detalle_debito_credito_ag\n"
                + "where nit_del_contribuyente =  @nit\n"
                + "and YEAR(anio) = year(dateadd(yyyy,-4,getdate()))";

        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            System.out.print(SQL);
            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                IndicadoresCreditoDebitoDto datos = new IndicadoresCreditoDebitoDto();
                datos.setSectorEconomico(rs.getString("sectorEconomico"));
                datos.setAnio(rs.getString("anio"));
                datos.setCantidadRegistros(rs.getString("cantidadRegistros"));

                listaIndicadoresDebitoCredito.add(datos);
            }
            conn.close();
            statement.close();
        } catch (Exception ex) {
            LOGGER.error("Error: ");
            LOGGER.error(ex.getMessage());
        }
        return listaIndicadoresDebitoCredito;
    }

    @Override
    public ArrayList<IndicadoresGastosGeneralesDto> obtenerIndicadoresGatosGenerales(String pNit) {
        ArrayList<IndicadoresGastosGeneralesDto> listaIndicadoresGastosGenerales = new ArrayList();

        String SQL = " DECLARE @nit varchar(18) ='" + pNit + "' \n"
                + " SELECT \n"
                + "year(getdate())                                         AS    'anio',\n"
                + "isnull(count(sector_economico),0)                       AS    'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)            AS    'cantidadRegistros'\n"
                + "FROM sat_indicadores.o_gastos_generales_ge\n"
                + "WHERE nit = @nit \n"
                + "and YEAR(fecha_adicion_contribuyente) = year(getdate())\n "
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-1,getdate()))              AS 'anio',\n"
                + "isnull(count(sector_economico),0)             AS 'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)  AS 'cantidadRegistros' \n"
                + "From sat_indicadores.o_gastos_generales_ge\n "
                + "where nit = @nit\n"
                + "and YEAR(fecha_adicion_contribuyente) = year(dateadd(yyyy,-1,getdate()))\n"
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-2,getdate()))              AS 'anio',\n"
                + "isnull(count(sector_economico),0)             AS 'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)  AS 'cantidadRegistros' \n"
                + "From sat_indicadores.o_gastos_generales_ge\n"
                + "where nit =  @nit \n"
                + "and YEAR(fecha_adicion_contribuyente) = year(dateadd(yyyy,-2,getdate()))\n"
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-3,getdate()))              AS  'anio',\n"
                + "isnull(count(sector_economico),0)             AS  'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)  AS  'cantidadRegistros' \n"
                + "From sat_indicadores.o_gastos_generales_ge\n"
                + "where nit =  @nit "
                + "and YEAR(fecha_adicion_contribuyente) = year(dateadd(yyyy,-3,getdate()))\n"
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-4,getdate()))              AS 'anio',\n"
                + "isnull(count(sector_economico),0)             AS 'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)   AS 'cantidadRegistros'\n"
                + "From sat_indicadores.o_gastos_generales_ge\n"
                + "where nit =  @nit\n"
                + "and YEAR(fecha_adicion_contribuyente) = year(dateadd(yyyy,-4,getdate()))";

        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();

            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                IndicadoresGastosGeneralesDto datos = new IndicadoresGastosGeneralesDto();
                datos.setSectorEconomico(rs.getString("sectorEconomico"));
                datos.setAnio(rs.getString("anio"));
                datos.setCantidadRegistros(rs.getString("cantidadRegistros"));

                listaIndicadoresGastosGenerales.add(datos);
            }
            conn.close();
            statement.close();
        } catch (Exception ex) {
            LOGGER.error("Error: ");
            LOGGER.error(ex.getMessage());
        }
        return listaIndicadoresGastosGenerales;
    }

    @Override
    public ArrayList<IndicadoresRentabilidadDto> obtenerIndicadoresRentabilidad(String pNit) {
        ArrayList<IndicadoresRentabilidadDto> listaIndicadoresRentabilidad = new ArrayList();

        String SQL = " DECLARE @nit varchar(18) ='" + pNit + "' \n"
                + " SELECT \n"
                + "year(getdate())                                         AS    'anio',\n"
                + "isnull(count(sector_economico),0)                       AS    'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)            AS    'cantidadRegistros'\n"
                + "FROM sat_indicadores.o_rentabilidad_ge\n"
                + "WHERE nit = @nit \n"
                + "and YEAR(fecha_adicion_contribuyente) = year(getdate())\n "
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-1,getdate()))              AS 'anio',\n"
                + "isnull(count(sector_economico),0)             AS 'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)  AS 'cantidadRegistros' \n"
                + "From sat_indicadores.o_rentabilidad_ge\n "
                + "where nit = @nit\n"
                + "and YEAR(fecha_adicion_contribuyente) = year(dateadd(yyyy,-1,getdate()))\n"
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-2,getdate()))              AS 'anio',\n"
                + "isnull(count(sector_economico),0)             AS 'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)  AS 'cantidadRegistros' \n"
                + "From sat_indicadores.o_rentabilidad_ge\n"
                + "where nit =  @nit \n"
                + "and YEAR(fecha_adicion_contribuyente) = year(dateadd(yyyy,-2,getdate()))\n"
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-3,getdate()))              AS  'anio',\n"
                + "isnull(count(sector_economico),0)             AS  'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)  AS  'cantidadRegistros' \n"
                + "From sat_indicadores.o_rentabilidad_ge\n"
                + "where nit =  @nit "
                + "and YEAR(fecha_adicion_contribuyente) = year(dateadd(yyyy,-3,getdate()))\n"
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-4,getdate()))              AS 'anio',\n"
                + "isnull(count(sector_economico),0)             AS 'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)   AS 'cantidadRegistros'\n"
                + "From sat_indicadores.o_rentabilidad_ge\n"
                + "where nit =  @nit\n"
                + "and YEAR(fecha_adicion_contribuyente) = year(dateadd(yyyy,-4,getdate()))";

        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();

            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                IndicadoresRentabilidadDto datos = new IndicadoresRentabilidadDto();
                datos.setSectorEconomico(rs.getString("sectorEconomico"));
                datos.setAnio(rs.getString("anio"));
                datos.setCantidadRegistros(rs.getString("cantidadRegistros"));

                listaIndicadoresRentabilidad.add(datos);
            }
            conn.close();
            statement.close();
        } catch (Exception ex) {
            LOGGER.error("Error: ");
            LOGGER.error(ex.getMessage());
        }
        return listaIndicadoresRentabilidad;
    }

    @Override
    public ArrayList<IndicadoresCobProveedoresDto> obtenerIndicadoresProveedores(String pNit) {
        ArrayList<IndicadoresCobProveedoresDto> listaIndicadoresProveedores = new ArrayList();

        String SQL = " DECLARE @nit varchar(18) ='" + pNit + "' \n"
                + " SELECT \n"
                + "year(getdate())                                         AS    'anio',\n"
                + "isnull(count(sector_economico),0)                       AS    'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)            AS    'cantidadRegistros'\n"
                + "FROM sat_indicadores.o_cobertura_proveedores_ge\n"
                + "WHERE nit = @nit \n"
                + "and YEAR(fecha_adicion_contribuyente) = year(getdate())\n "
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-1,getdate()))              AS 'anio',\n"
                + "isnull(count(sector_economico),0)             AS 'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)  AS 'cantidadRegistros' \n"
                + "From sat_indicadores.o_cobertura_proveedores_ge\n "
                + "where nit = @nit\n"
                + "and YEAR(fecha_adicion_contribuyente) = year(dateadd(yyyy,-1,getdate()))\n"
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-2,getdate()))              AS 'anio',\n"
                + "isnull(count(sector_economico),0)             AS 'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)  AS 'cantidadRegistros' \n"
                + "From sat_indicadores.o_cobertura_proveedores_ge\n"
                + "where nit =  @nit \n"
                + "and YEAR(fecha_adicion_contribuyente) = year(dateadd(yyyy,-2,getdate()))\n"
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-3,getdate()))              AS  'anio',\n"
                + "isnull(count(sector_economico),0)             AS  'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)  AS  'cantidadRegistros' \n"
                + "From sat_indicadores.o_cobertura_proveedores_ge\n"
                + "where nit =  @nit "
                + "and YEAR(fecha_adicion_contribuyente) = year(dateadd(yyyy,-3,getdate()))\n"
                + "UNION ALL\n"
                + "Select\n"
                + "year(dateadd(yyyy,-4,getdate()))              AS 'anio',\n"
                + "isnull(count(sector_economico),0)             AS 'sectorEconomico',\n"
                + "isnull(count(fecha_adicion_contribuyente),0)   AS 'cantidadRegistros'\n"
                + "From sat_indicadores.o_cobertura_proveedores_ge\n"
                + "where nit =  @nit\n"
                + "and YEAR(fecha_adicion_contribuyente) = year(dateadd(yyyy,-4,getdate()))";

        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();

            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                IndicadoresCobProveedoresDto datos = new IndicadoresCobProveedoresDto();
                datos.setSectorEconomico(rs.getString("sectorEconomico"));
                datos.setAnio(rs.getString("anio"));
                datos.setCantidadRegistros(rs.getString("cantidadRegistros"));

                listaIndicadoresProveedores.add(datos);
            }
            conn.close();
            statement.close();
        } catch (Exception ex) {
            LOGGER.error("Error: ");
            LOGGER.error(ex.getMessage());
        }
        return listaIndicadoresProveedores;
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadores(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaDeIndicadoresDetalle", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresPorTipoIndicador(String tipoIndicador) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorTipoIndicador", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresPorColor(String color) {
        Map<String, Object> param = new HashMap<>();
        param.put("color", color);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorColor", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresPorAnio(String anio) {
        Map<String, Object> param = new HashMap<>();
        param.put("anio", anio);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorAnio", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresPorSectorEconomico(String sectorEconomico) {
        Map<String, Object> param = new HashMap<>();
        param.put("sectorEconomico", sectorEconomico);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorSectorEconomico", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresPorRegion(String region) {
        Map<String, Object> param = new HashMap<>();
        param.put("region", region);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorRegion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresPorClasificacion(String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public String obtenerNombre(String pNit) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);

        String nombre = this.finder.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("mostrarNombreContribuyente", param));

        return nombre;
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitColorAnio(String pNit,
            String color,
            String anio) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("color", color);
        param.put("anio", anio);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitColorAnio", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitColor(String pNit,
            String color) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("color", color);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitColor", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitAnio(String pNit,
            String anio) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("anio", anio);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitAnio", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaVaciaIndicadores(String pNit,
            String tipoIndicador,
            String sectorEconomico,
            String color,
            String region,
            String anio,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("tipoIndicador", tipoIndicador);
        param.put("sectorEconomico", sectorEconomico);
        param.put("color", color);
        param.put("region", region);
        param.put("anio", anio);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleVacia", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorSectorColorRegionAnioClasificacion(String pNit,
            String tipoIndicador,
            String sectorEconomico,
            String color,
            String region,
            String anio,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("tipoIndicador", tipoIndicador);
        param.put("sectorEconomico", sectorEconomico);
        param.put("color", color);
        param.put("region", region);
        param.put("anio", anio);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitIndicadorSectorColorRegionAnioClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorSectorColorRegionAnio(String pNit,
            String tipoIndicador,
            String sectorEconomico,
            String color,
            String region,
            String anio) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("tipoIndicador", tipoIndicador);
        param.put("sectorEconomico", sectorEconomico);
        param.put("color", color);
        param.put("region", region);
        param.put("anio", anio);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitIndicadorSectorColorRegionAnio", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorSectorColorRegion(String pNit,
            String tipoIndicador,
            String sectorEconomico,
            String color,
            String region) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("tipoIndicador", tipoIndicador);
        param.put("sectorEconomico", sectorEconomico);
        param.put("color", color);
        param.put("region", region);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitIndicadorSectorColorRegion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorSectorColor(String pNit,
            String tipoIndicador,
            String sectorEconomico,
            String color) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("tipoIndicador", tipoIndicador);
        param.put("sectorEconomico", sectorEconomico);
        param.put("color", color);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitIndicadorSectorColor", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorSector(String pNit,
            String tipoIndicador,
            String sectorEconomico) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("tipoIndicador", tipoIndicador);
        param.put("sectorEconomico", sectorEconomico);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitIndicadorSector", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitTipoIndicador(String pNit,
            String tipoIndicador) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("tipoIndicador", tipoIndicador);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitTipoIndicador", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitSector(String pNit,
            String sectorEconomico) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("sectorEconomico", sectorEconomico);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitSectorEconomico", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitRegion(String pNit,
            String region) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("region", region);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitRegion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitClasificacion(String pNit,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorColor(String pNit,
            String tipoIndicador,
            String color) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitIndicadorColor", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorColorAnio(String pNit,
            String tipoIndicador,
            String color,
            String anio) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);
        param.put("anio", anio);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitIndicadorColorAnio", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorColorAnioSector(String pNit,
            String tipoIndicador,
            String color,
            String anio,
            String sectorEconomico) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);
        param.put("anio", anio);
        param.put("sectorEconomico", sectorEconomico);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitIndicadorColorAnioSector", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorColorAnioSectorRegion(String pNit,
            String tipoIndicador,
            String color,
            String anio,
            String sectorEconomico,
            String region) {
        Map<String, Object> param = new HashMap<>();
        param.put("pNit", pNit);
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);
        param.put("anio", anio);
        param.put("sectorEconomico", sectorEconomico);
        param.put("region", region);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleNitIndicadorColorAnioSectorRegion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndicadorColor(
            String tipoIndicador,
            String color) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorTipoIndicadorColor", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndicadorAnio(
            String tipoIndicador,
            String anio) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("anio", anio);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorTipoIndicadorAnio", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndicadorSector(
            String tipoIndicador,
            String sectorEconomico) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("sectorEconomico", sectorEconomico);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorTipoIndicadorSector", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndicadorRegion(
            String tipoIndicador,
            String region) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("region", region);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorTipoIndicadorRegion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndicadorClasificacion(
            String tipoIndicador,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorTipoIndicadorClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorAnio(
            String color,
            String anio) {
        Map<String, Object> param = new HashMap<>();
        param.put("color", color);
        param.put("anio", anio);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorColorAnio", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorSector(
            String color,
            String sectorEconomico) {
        Map<String, Object> param = new HashMap<>();
        param.put("color", color);
        param.put("sectorEconomico", sectorEconomico);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorColorSector", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorRegion(
            String color,
            String region) {
        Map<String, Object> param = new HashMap<>();
        param.put("color", color);
        param.put("region", region);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorColorRegion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorClasificacion(
            String color,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("color", color);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorColorClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresAnioSector(
            String anio,
            String sectorEconomico) {
        Map<String, Object> param = new HashMap<>();
        param.put("anio", anio);
        param.put("sectorEconomico", sectorEconomico);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorAnioSector", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresAnioRegion(
            String anio,
            String region) {
        Map<String, Object> param = new HashMap<>();
        param.put("anio", anio);
        param.put("region", region);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorAnioRegion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresAnioClasificacion(
            String anio,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("anio", anio);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorAnioClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresSectorRegion(
            String sectorEconomico,
            String region) {
        Map<String, Object> param = new HashMap<>();
        param.put("sectorEconomico", sectorEconomico);
        param.put("region", region);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorSectorRegion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresSectorClasificacion(
            String sectorEconomico,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("sectorEconomico", sectorEconomico);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorSectorClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresRegionClasificacion(
            String region,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("region", region);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetallePorRegionClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresIndicadorSectorColorRegionAnioClasificacion(
            String tipoIndicador,
            String sectorEconomico,
            String color,
            String region,
            String anio,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("sectorEconomico", sectorEconomico);
        param.put("color", color);
        param.put("region", region);
        param.put("anio", anio);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleIndicadorSectorColorRegionAnioClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndColorAnio(
            String tipoIndicador,
            String color,
            String anio) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);
        param.put("anio", anio);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleIndicadorColorAnio", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndColorSector(
            String tipoIndicador,
            String color,
            String sectorEconomico) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);
        param.put("sectorEconomico", sectorEconomico);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleIndicadorColorAnio", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndColorRegion(
            String tipoIndicador,
            String color,
            String region) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);
        param.put("region", region);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleIndicadorColorRegion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndColorClasificacion(
            String tipoIndicador,
            String color,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleIndicadorColorClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorAnioSector(
            String color,
            String anio,
            String sectorEconomico) {
        Map<String, Object> param = new HashMap<>();
        param.put("color", color);
        param.put("anio", anio);
        param.put("sectorEconomico", sectorEconomico);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleColorAnioSector", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorAnioRegion(
            String color,
            String anio,
            String region) {
        Map<String, Object> param = new HashMap<>();
        param.put("color", color);
        param.put("anio", anio);
        param.put("region", region);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleColorAnioRegion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorAnioClasificacion(
            String color,
            String anio,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("color", color);
        param.put("anio", anio);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleColorAnioClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresAnioSectorRegion(
            String anio,
            String sectorEconomico,
            String region) {
        Map<String, Object> param = new HashMap<>();
        param.put("anio", anio);
        param.put("sectorEconomico", sectorEconomico);
        param.put("region", region);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleAnioSectorRegion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresAnioSectorClasificacion(
            String anio,
            String sectorEconomico,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("anio", anio);
        param.put("sectorEconomico", sectorEconomico);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleAnioSectorClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresSectorRegionClasificacion(
            String sectorEconomico,
            String region,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("sectorEconomico", sectorEconomico);
        param.put("region", region);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleSectorRegionClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresIndicadorColorAnioSect(
            String tipoIndicador,
            String color,
            String anio,
            String sectorEconomico) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);
        param.put("anio", anio);
        param.put("sectorEconomico", sectorEconomico);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleTipoIndicadorColorAnioSect", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresIndicadorColorAnioRegion(
            String tipoIndicador,
            String color,
            String anio,
            String region) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);
        param.put("anio", anio);
        param.put("region", region);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleTipoIndicadorColorAnioRegion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresIndicadorColorAnioClasificacion(
            String tipoIndicador,
            String color,
            String anio,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);
        param.put("anio", anio);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleTipoIndicadorColorAnioClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresIndicadorColorAnioSecRegion(
            String tipoIndicador,
            String color,
            String anio,
            String sectorEconomico,
            String region) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);
        param.put("anio", anio);
        param.put("sectorEconomico", sectorEconomico);
        param.put("region", region);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleTipoIndicadorColorAnioSecRegion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresIndicadorColorAnioSecClasificacion(
            String tipoIndicador,
            String color,
            String anio,
            String sectorEconomico,
            String clasificacion) {
        Map<String, Object> param = new HashMap<>();
        param.put("tipoIndicador", tipoIndicador);
        param.put("color", color);
        param.put("anio", anio);
        param.put("sectorEconomico", sectorEconomico);
        param.put("clasificacion", clasificacion);

        return this.daoFinderImpl.findByNamedQueryParam("mostrarConsultaIndDetalleTipoIndicadorColorAnioSecClasificacion", param, IndicadoresEncabezadoCalculadosDto.class
        );
    }

    @Override
    public List<ConteoVehiculosActivosInactivosDto> obtenerConteoVehiculosActivInactiv(List<Integer> anioEvaluar, String pNit) {
        String Query = " SELECT    rn.name as \"nombreAnio\", NVL(cnt, 0) as \"cantidadVehiculos\" "
                + "FROM      (SELECT " + anioEvaluar.get(0) + "  AS name FROM dual \n"
                + "           UNION ALL \n"
                + "           SELECT " + anioEvaluar.get(1) + " FROM dual \n"
                + "           UNION ALL \n"
                + "           SELECT " + anioEvaluar.get(2) + " FROM dual \n"
                + "           UNION ALL \n"
                + "           SELECT " + anioEvaluar.get(3) + " FROM dual \n"
                + "           UNION ALL \n"
                + "           SELECT " + anioEvaluar.get(4) + " FROM dual) rn\n"
                + "LEFT JOIN (SELECT   EXTRACT(YEAR FROM V.FECHA_ALZA) name, COUNT(*) AS cnt \n"
                + "           FROM SAT_VEHICULOS.VEH_VEHICULOS V , VEH_PROPIEDADES P \n"
                + "           WHERE V.ANIO_ALZA=P.ANIO_ALZA AND V.IDENTIFICADOR=P.IDENTIFICADOR AND V.DIGITO_VERIFICADOR=P.DIGITO_VERIFICADOR\n"
                + "			AND P.ULTIMO_PROPIETARIO='PA' AND P.NIT = '" + pNit + "' and \n"
                + "            EXTRACT(YEAR FROM V.FECHA_ALZA) IN ( " + anioEvaluar.get(0) + "," + anioEvaluar.get(1) + "," + anioEvaluar.get(2) + "," + anioEvaluar.get(3) + "," + anioEvaluar.get(4) + ") \n"
                + "           GROUP BY EXTRACT(YEAR FROM V.FECHA_ALZA) ) n ON n.name = rn.name\n"
                + "           ORDER BY 1 desc ";

        return daoFinderImpl.executeSQLQuery(Query.toString(), ConteoVehiculosActivosInactivosDto.class);
    }

    @Override
    public List<ConsultaCantidadDeclaracionesAduanerasConAnioDto> obtenerConteoDeclaracionesAduanas(List<Integer> anios, String pNit) {
        List<ConsultaCantidadDeclaracionesAduanerasConAnioDto> dataGeneral = new ArrayList<>();
        for (Integer anio : anios) {
            ConsultaCantidadDeclaracionesAduanerasConAnioDto dataGeneralSub = new ConsultaCantidadDeclaracionesAduanerasConAnioDto();

            Map<String, Object> param = new HashMap<>();
            param.put("pNit", pNit);
            param.put("pAnio", anio);

            ConsultaCantidadDeclaracionesAduanerasDto data = this.daoFinderImpl.uniqueResult(this.daoFinderImpl.findByNamedQueryParam("obtenerConteoDeclaracionesAduanerasByAnioNit", param, ConsultaCantidadDeclaracionesAduanerasDto.class));

            dataGeneralSub.setAnio(anio);
            dataGeneralSub.setCantidad_declaraciones(data.getCantidad_declaraciones());
            dataGeneralSub.setCif_suma(data.getCif_suma());
            dataGeneralSub.setFob_suma(data.getFob_suma());
            dataGeneral.add(dataGeneralSub);
        }
        return dataGeneral;
    }

    @Override
    public PequenoContribuyenteEncabezadoDto datosPerfilContribuyente(String pNit) {
        PequenoContribuyenteEncabezadoDto datosPerfil = new PequenoContribuyenteEncabezadoDto();

        String SQL = "SELECT TOP 1\n"
                + "correo_agencia_virtual   AS  'correoAgenciaVirtual' , \n"
                + "correo_rtu               AS  'correoRtu' , \n"
                + "ISNULL(plan_operativo,'N/A')   AS  'planOperativo'  \n"
                + "FROM sat_general.o_afiliacion_contribuyentes_dm\n "
                + "WHERE nit ='" + pNit + "'\n";

        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            /* System.out.print(SQL);*/
            rs = statement.executeQuery(SQL);

            while (rs.next()) {
                datosPerfil.setCorreoAgenciaVirtual(rs.getString("correoAgenciaVirtual"));
                datosPerfil.setCorreoRtu(rs.getString("correoRtu"));
                datosPerfil.setPlanOperativo(rs.getString("planOperativo"));

            }
            conn.close();
            statement.close();

        } catch (Exception ex) {
            LOGGER.error("Error: ");
            LOGGER.error(ex.getMessage());
        }

        return datosPerfil;

    }

    @Override
    public ImpuestoIvaGeneralDto DetalleIvaGeneral(String pNit) {
        ImpuestoIvaGeneralDto datosRegimenIva = new ImpuestoIvaGeneralDto();

        

        String SQL = "SELECT \n"
                + "nombre_regimen                                    AS nombreRegimenIVA,\n"
                + "convert (nvarchar, fecha_cambio_regimen, 1 )      AS fechaCambioRegimenIVA,\n"
                + "convert (nvarchar, fecha_adicion_afiliacion,1 )   AS fechaAdicionAfiliacionIVA\n"
                + "FROM [sat_general].[o_afiliacion_contribuyentes_dm]\n"
                + "WHERE nombre_regimen IN  ('PEQUEÑO_CONTRIBUYENTE','GENERAL', 'IMPUESTO AL VALOR AGREGADO','EXENTO')\n"
                + "and nit='" + pNit + "'\n"
                + "ORDER BY fecha_adicion_afiliacion, fecha_cambio_regimen ASC ";

        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            /* System.out.print(SQL);*/
            rs = statement.executeQuery(SQL);

            while (rs.next()) {
                datosRegimenIva.setNombreRegimen(rs.getString("nombreRegimenIVA"));
                datosRegimenIva.setFechaCambioRegimen(rs.getString("fechaCambioRegimenIVA"));
                datosRegimenIva.setFechaAdicionAfiliacionIVA(rs.getString("fechaAdicionAfiliacionIVA"));

                
            }
            conn.close();
            statement.close();

        } catch (Exception ex) {
            LOGGER.error("Error: ");
            LOGGER.error(ex.getMessage());
        }

        return datosRegimenIva;

    }

    @Override
    public ImpuestoIsrDto DetalleISR(String pNit) {
        ImpuestoIsrDto datosRegimenIsr = new ImpuestoIsrDto();

        

        String SQL = "SELECT \n"
                + "nombre_regimen                                    AS nombreRegimenISR,\n"
                + "convert (nvarchar, fecha_cambio_regimen, 1 )      AS fechaCambioRegimenISR,\n"
                + "convert (nvarchar, fecha_adicion_afiliacion,1 )   AS fechaAdicionAfiliacionISR\n"
                + "FROM [sat_general].[o_afiliacion_contribuyentes_dm]\n"
                + "WHERE nombre_regimen IN ('ASALARIADO','OPCIONAL SIMPLIFICADO S/INGRESOS','EXENTO','IMPUESTO DE SOLIDARIDAD ACREDITABLE A ISR', 'SOBRE_UTILIDADES')\n"
                + "and nit='" + pNit + "'\n"
                + "ORDER BY fecha_adicion_afiliacion, fecha_cambio_regimen ASC";

        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            /*System.out.print(SQL);*/
            rs = statement.executeQuery(SQL);

            while (rs.next()) {
                datosRegimenIsr.setNombreRegimenISR(rs.getString("nombreRegimenISR"));
                datosRegimenIsr.setFechaCambioRegimenISR(rs.getString("fechaCambioRegimenISR"));
                datosRegimenIsr.setFechaAdicionAfiliacionISR(rs.getString("fechaAdicionAfiliacionISR"));

              
            }
            conn.close();
            statement.close();

        } catch (Exception ex) {
            LOGGER.error("Error: ");
            LOGGER.error(ex.getMessage());
        }

        return datosRegimenIsr;

    }

    @Override
    public ImpuestoIsoDto DetalleIso(String pNit) {
        ImpuestoIsoDto datosRegimenIso = new ImpuestoIsoDto();

        

        String SQL = "SELECT \n"
                + "nombre_regimen                                    AS nombreRegimenISO,\n"
                + "convert (nvarchar, fecha_cambio_regimen, 1 )      AS fechaCambioRegimenISO,\n"
                + "convert (nvarchar, fecha_adicion_afiliacion,1 )   AS fechaAdicionAfiliacionISO\n"
                + "FROM [sat_general].[o_afiliacion_contribuyentes_dm]\n"
                + "WHERE nombre_regimen IN ('ISR ACREDITABLE A IMPUESTO DE SOLIDARIDAD','IMPUESTO DE SOLIDARIDAD ACREDITABLE A ISR' )\n"
                + "and nit='" + pNit + "'\n"
                + "ORDER BY fecha_adicion_afiliacion, fecha_cambio_regimen ASC";
        
        Statement statement;
        ResultSet rs;

        try (Connection conn = getConnection()) {
            statement = conn.createStatement();
            /* System.out.print(SQL);*/
            rs = statement.executeQuery(SQL);

            while (rs.next()) {
                datosRegimenIso.setNombreRegimenISO(rs.getString("nombreRegimenISO"));
                datosRegimenIso.setFechaCambioRegimenISO(rs.getString("fechaCambioRegimenISO"));
                datosRegimenIso.setFechaAdicionAfiliacionISO(rs.getString("fechaAdicionAfiliacionISO"));

                
            }
            conn.close();
            statement.close();

        } catch (Exception ex) {
            LOGGER.error("Error: ");
            LOGGER.error(ex.getMessage());
        }

        return datosRegimenIso;

    }

}
