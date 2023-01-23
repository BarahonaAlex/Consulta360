/*
 * Superintendencia de Administracion Tributaria (SAT)
 * Gerencia de Informatica
 * Departamento de Desarrollo de Sistemas
 */
package gt.gob.sat.fiscalizacion.consulta360.srv;

import gt.gob.sat.arquitectura.fwk.dto.ParameterDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.UsuarioDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.AfiliacionDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ApoderadoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ArchivoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.CantidadVehiculosDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.Consolidado223Dto;
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
import gt.gob.sat.fiscalizacion.consulta360.dto.IngresosFelvsDeclaradoIvaGeneralDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IngresosFelvsDeclaradoIvaPequeDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ImpuestoIvaGeneralDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.PequenoContribuyenteEncabezadoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ImpuestoIsrDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.TipologiasEvasionDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.TipologiasEvasionAnualDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.VehiculosDTO;
import gt.gob.sat.fiscalizacion.consulta360.dto.VerificacionesEnCampoConteoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.VerificacionesEnCampoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.obtenerConteoDto;
import gt.gob.sat.fiscalizacion.consulta360.modelo.CifTipologiasDeEvasion;
import gt.gob.sat.fiscalizacion.consulta360.modelo.CifVerificacionesEnCampo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Daniel Castillo (adcastic)
 * @since 08/12/2015
 * @version 1.0
 */
public interface GeneralSrv {

    public UsuarioDto obtenerDatosUsuario(String pLogin);

    public ContribuyenteDto obtenerContribuyenteByNit(String pNit);

    public ContribuyenteDto obtenerInfoContribuyenteByNit(String pNit);

    public ArrayList<PequenoContribuyenteEncabezadoDto> obtenerEncabezadoPeqContribuyente(String pNit);

    public List<DeclaracionDto> obtenerDeclaraciones(String pNit, Date pFechaDel, Date pFechaAl, String pCodFormulario);

    public List<LibroAutorizadoDto> obtenerLibrosAutorizadosByNit(String pNit);

    public List<AfiliacionDto> obtenerAfiliacionesByNit(String pNit);

    public List<TipologiasEvasionDto> obtenerTipologiasEvasionByNit(String pNit);

    public List<TipologiasEvasionAnualDto> obtenerTipologiasEvasionAnualByNit(String pNit);

    public List<VerificacionesEnCampoDto> obtenerVerificacionesByNit(String pNit);

    public List<VerificacionesEnCampoConteoDto> obtenerVerificacionesConteoByNit(String pNit);

    public List<ObligacionDto> obtenerObligacionesByAfiliacionAndNit(String pNit, String pCodigoImpuesto, String pCodigoTipo);

    public List<EstablecimientoDto> obtenerEstablecimientosByNit(String pNit);

    public List<DocumentoDto> obtenerDocumentosByNitAndEstablecimiento(String pNit, String pNoEstablecimiento);

    public List<MaquinaDto> obtenerMaquinasByNitAndEstablecimiento(String pNit, String pNoEstablecimiento);

    public List<LibroAutorizadoDto> obtenerLibrosByNitAndEstablecimiento(String pNit, String pNoEstablecimiento);

    public List<LineaAereaDto> obtenerLineasAereasByNitAndEstablecimiento(String pNit, String pNoEstablecimiento);

    public List<NombramientoDto> obtenerNombramientosByNitAndDates(String pNit, Date pFechaInicio, Date pFechaFin);

    public boolean existeInformeBanguatActivo(String pNombramiento);

    public boolean existeResolucionFiscalActiva(String pNombramiento);

    public boolean existenAudiencias(String pNombramiento);

    public BigDecimal findNoInforme(String pNombramiento);

    public EncabezadoNombramientoDto obtenerEncabezadoNombramiento(String pNombramiento);

    public List<SupervisorAuditorDto> obtenerSupervisoresAuditoresByNombramiento(String pNombramiento);

    public List<TrayectoriaNombramientoDto> obtenerTrayectoriaNombramiento(String pNombramiento);

    public List<ExpedienteDto> obtenerExpedientesByNit(String pNit);

    public List<DocumentoDto> obtenerDocumentosByExpediente(String pNumeroExpediente);

    public List<ExpedienteDto> obtenerExpedientesJuridicoByNit(String pNit, Date pFechaInicio, Date pFechaFin);

    public List<ProveedorDto> obtenerProveedoresByNitAndNombramiento(String pNit, String pNombramiento);

    public List<DetalleProveedorDto> obtenerDetalleProveedor(String pNit, String pMes, String pAnio);

    public String obtenerMontoDeclaradoByProveedor(String pNit, String pMes, String pAnio);

    public List<PapelDto> obtenerPapelesTrabajoByNombramiento(String pNombramiento);

    public PerfilContribuyenteDto findPerfilContribuyenteByNit(String pNit);

    public List<DatosRepresentantesDto> findDatosRepresentantesByNit(String pNit);

    public List<DatosContadorDto> findDatosContadorByNit(String pNit);

    public List<DeclaracionDto> findDeclaraciones223Or215(String pNit, String pFechaDel, String pFechaAl);

    public Consolidado223Dto findDatosConsolidado223(String pNit, String pFechaDel, String pFechaAl);

    public Consolidado223Dto findDatosRemanenteConsolidado223(String pNit, String pFechaDel, String pFechaAl);

    public Consolidado223Dto findDatosCreditoFiscalConsolidado223(String pNit, String pFechaDel, String pFechaAl);

    public ProveedorDto findConsultaDocumento(String pNit, String pMes, String pAnio);

    public List<ArchivoDto> obtenerInformesFinAuditoria(String pNombramiento);

    public List<DocumentoDto> obtenerDocumentosVerificacion(String pNombramiento, ProveedorDto pProveedor);

    public List<ItemDto> obtenerTiposOperacionAduanas();

    public List<ItemDto> obtenerEstadosDua();

    public List<ItemDto> obtenerAduanas();

    public List<ItemDto> obtenerModalidadesPorRegimen(String pTipoOperacion);

    public List<DeclaracionAduaneraDto> obtenerDeclaracionesAduaneras(String pNitContribuyente, String[] pModalidades, Date pFechaInicio, Date pFechaFin, String pEstadoDua, String pAduana);

    public List<AfiliacionDto> findAfiliacionesByNit(String pNit);

    public List<TotalDto> findSolicitudesDevolucionCF(String pNit);

    public List<TotalDto> findOmisos(String pNit);

    public List<TotalDto> findOmisosinactivos(String pNit);

    public List<TotalDto> findLibrosAutorizados(String pNit);

    public List<TotalDto> findOtrosLibrosAutorizados(String pNit);

    public List<TotalDto> findFacturasAutorizadas(String pNit);

    public TotalDto findVehiculosActivos(String pNit);

    public TotalDto findCargaTributaria(String pNit);

    public List<TotalDto> findAuditorias(String pNit);

    public List<TotalDto> findExpedientesJuridico(String pNit);

    public List<TotalDto> findExpedientesTributa(String pNit);

    public List<TotalDto> findExpedientesAdm(String pNit);

    public List<ApoderadoDto> findApoderadosByRepresentante(String pNit);

    public List<String> findAcreditamientosByNit(String pNit);

    public void registrarAcceso(BitacoraConsulta360Dto pBitacora);

    public List<HistorialRiesgoDto> findHistorialRiesgoInstitucional(String pNit);

    public List<HistorialRiesgoDto> findHistorialDCFRiesgoInstitucional(String pNit);

    public List<DeclaracionesPresentadasDto> obtenerDescripcionesFormulario(BigDecimal id_reporte);

    public IngresosFelvsDeclaradoIvaPequeDto obtenerPequeñoContribuyentes(String pNit);

    public BigDecimal obtenerCodigoRegimenRiesgo(String pNit, String pAnio);

    public List<DetalleRiesgoSentenciaDto> obtenerSentenciaMapeoPorRegimen(String pNit, String pAnio, String pRegimen);

    public List<DetalleRiesgoDto> obtenerVariablesDeRiesgo(List<DetalleRiesgoSentenciaDto> Sentencias);

    /*Metodo para guardar el historial de la carga de datos de Tipologia de Evasión*/
    public BigDecimal guardarArchivo(HistorialCargaDto cargaArchivo);

    /* Metodo para descargar archivo excel modulo Tipologias de evasión*/
    public HistorialCargaDto obtenerArchivoRegistros(BigDecimal idCarga);

    public List<HistorialCargaDto> listaHistorial();

    public BigDecimal guardarDetalleArchivoV2(CifTipologiasDeEvasion detalleArchivo);
    //public List <DetalleArchivoTipologiaDto> informacionArchivo();

    /*Metodo para el conteo del registro */
    public obtenerConteoDto obtenerRegistroContadores(String pNit, int idTipologia, int anio, int tipoConsulta);

    /*Metodo para la validación idTipologia exista en base de datos*/
    public obtenerConteoDto obteneridCatalogoTipologia(int idTipologia);

    /*Metodo para guardar el historial de la carga de datos de Verificaciones en campo*/
    public BigDecimal guardarCargaVerificacionesCampo(HistorialCargaVerificacionesCampoDto cargaVerificacionesCampo);

    public List<HistorialCargaVerificacionesCampoDto> listaVerificacionesCampo();

    public BigDecimal guardarDetalleArchivoVerificaciones(CifVerificacionesEnCampo detalleArchivoVerificaciones);
    //public List <DetalleArchivoTipologiaDto> informacionArchivo();

    public obtenerConteoDto obtenerRegistroContadoresVerificaciones(String pNit, String direccionVerificada, String departamento, String municipio, String tipoDireccion, int tipoConsulta);

    /* Metodo para descargar archivo excel modulo Tipologias de evasión*/
    public HistorialCargaVerificacionesCampoDto obtenerArchivoRegistrosVerificaciones(BigDecimal idCargaCampo);

    /*Fecha formateado con hora historial SAT_FISCALIZACION.CIF_HISTORIAL_CARGAS*/
    public List<HistorialCargaFechaFormatDto> obtenerHistorialFormatoFecha();

    public List<HistorialCargaVerificacionesFechaFormatDto> obtenerHistorialVerificacionesFormatoFecha();

    /*General Verificaciones en campo*/
    public List<GeneralVerificacionesDto> VerificacionesEnCampo(String pNit);

    public VehiculosDTO obtenerImpuestoISCV(String pNit);

    public List<TipologiasEvasionAnualDto> obtenerTipologiasEvasionAnualByNitFiveYears(String pNit, List<Integer> anioEvaluar);

    public ArrayList<ParameterDto> procedimientoInfoVehiculos(String pNit);

    public ArrayList<IndicadoresCreditoDebitoDto> obtenerIndicadoresDebitoCredito(String pNit);

    //Metodo buscar indicador esto en el perfil del contribuyente//
    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadores(String nit);

    public ArrayList<IndicadoresGastosGeneralesDto> obtenerIndicadoresGatosGenerales(String pNit);

    public ArrayList<IndicadoresRentabilidadDto> obtenerIndicadoresRentabilidad(String pNit);

    public ArrayList<IndicadoresCobProveedoresDto> obtenerIndicadoresProveedores(String pNit);
    
    public List<ConveniosActivosDto> conveniosActivos(String pNit);

    //Metodo para obtener el nombre del contrribuyente
    public String obtenerNombre(String nit);

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresPorTipoIndicador(String tipoIndicador);

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresPorColor(String color);

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresPorAnio(String anio);

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresPorSectorEconomico(String sectorEconomico);

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresPorRegion(String region);

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresPorClasificacion(String clasificacion);

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaVaciaIndicadores(
            String nit,
            String tipoIndicador,
            String sectorEconomico,
            String color,
            String region,
            String anio,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorSectorColorRegionAnioClasificacion(
            String nit,
            String tipoIndicador,
            String sectorEconomico,
            String color,
            String region,
            String anio,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorSectorColorRegionAnio(
            String pNit,
            String tipoIndicador,
            String sectorEconomico,
            String color,
            String region,
            String anio
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorColorAnioSectorRegion(
            String pNit,
            String tipoIndicador,
            String color,
            String anio,
            String sectorEconomico,
            String region
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorSectorColorRegion(
            String pNit,
            String tipoIndicador,
            String sectorEconomico,
            String color,
            String region
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorColorAnioSector(
            String pNit,
            String tipoIndicador,
            String color,
            String anio,
            String sectorEconomico
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorSectorColor(
            String pNit,
            String tipoIndicador,
            String sectorEconomico,
            String color
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorColorAnio(
            String pNit,
            String tipoIndicador,
            String color,
            String anio
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorSector(
            String pNit,
            String tipoIndicador,
            String sectorEconomico
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitIndicadorColor(
            String pNit,
            String tipoIndicador,
            String color
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitColorAnio(
            String pNit,
            String color,
            String anio
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitColor(
            String pNit,
            String color
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitTipoIndicador(
            String pNit,
            String tipoIndicador
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitAnio(
            String pNit,
            String anio
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitSector(
            String pNit,
            String sectorEconomico
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitRegion(
            String pNit,
            String region
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresNitClasificacion(
            String pNit,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndicadorColor(
            String tipoIndicador,
            String color
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndicadorAnio(
            String tipoIndicador,
            String anio
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndicadorSector(
            String tipoIndicador,
            String sectorEconomico
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndicadorRegion(
            String tipoIndicador,
            String region
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndicadorClasificacion(
            String tipoIndicador,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorAnio(
            String color,
            String anio
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorSector(
            String color,
            String sectorEconomico
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorRegion(
            String color,
            String region
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorClasificacion(
            String color,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresAnioSector(
            String anio,
            String sectorEconomico
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresAnioRegion(
            String anio,
            String region
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresAnioClasificacion(
            String anio,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresSectorRegion(
            String sectorEconomico,
            String region
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresSectorClasificacion(
            String sectorEconomico,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresRegionClasificacion(
            String region,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresIndicadorSectorColorRegionAnioClasificacion(
            String tipoIndicador,
            String sectorEconomico,
            String color,
            String region,
            String anio,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndColorAnio(
            String tipoIndicador,
            String color,
            String anio
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndColorSector(
            String tipoIndicador,
            String color,
            String sectorEconomico
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndColorRegion(
            String tipoIndicador,
            String color,
            String region
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresTipoIndColorClasificacion(
            String tipoIndicador,
            String color,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorAnioSector(
            String color,
            String anio,
            String sectorEconomico
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorAnioRegion(
            String color,
            String anio,
            String region
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresColorAnioClasificacion(
            String color,
            String anio,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresAnioSectorRegion(
            String anio,
            String sectorEconomico,
            String region
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresAnioSectorClasificacion(
            String anio,
            String sectorEconomico,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresSectorRegionClasificacion(
            String sectorEconomico,
            String region,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresIndicadorColorAnioSect(
            String tipoIndicador,
            String color,
            String anio,
            String sectorEconomico
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresIndicadorColorAnioRegion(
            String tipoIndicador,
            String color,
            String anio,
            String region
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresIndicadorColorAnioClasificacion(
            String tipoIndicador,
            String color,
            String anio,
            String clasificacion
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresIndicadorColorAnioSecRegion(
            String tipoIndicador,
            String color,
            String anio,
            String sectorEconomico,
            String region
    );

    public List<IndicadoresEncabezadoCalculadosDto> obtenerListaIndicadoresIndicadorColorAnioSecClasificacion(
            String tipoIndicador,
            String color,
            String anio,
            String sectorEconomico,
            String clasificacion
    );

    public List<ConteoVehiculosActivosInactivosDto> obtenerConteoVehiculosActivInactiv(List<Integer> anios, String pNit);

    public List<ConsultaCantidadDeclaracionesAduanerasConAnioDto> obtenerConteoDeclaracionesAduanas(List<Integer> anios, String pNit);

    public PequenoContribuyenteEncabezadoDto datosPerfilContribuyente(String pNit);
    
    public ImpuestoIvaGeneralDto DetalleIvaGeneral (String pNit);
    
    public ImpuestoIsrDto DetalleISR(String pNit);
     
    public ImpuestoIsoDto DetalleIso(String pNit);

}
