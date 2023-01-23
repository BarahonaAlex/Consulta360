/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import com.opencsv.bean.CsvToBeanBuilder;
import gt.gob.sat.fiscalizacion.consulta360.dto.ExcelReadTipologiasDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HistorialCargaDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HistorialCargaFechaFormatDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.TipologiasEvasionDto;
import gt.gob.sat.fiscalizacion.consulta360.modelo.CifTipologiasDeEvasion;
import gt.gob.sat.fiscalizacion.consulta360.srv.GeneralSrv;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.util.Calendar.DATE;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialBlob;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.context.RequestContext;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author adlabrigo
 */
@Controller
@Scope("view")
public class HistorialCargaUI extends AbstractUI implements Serializable {

    private static final long serialVersionUID = 1L;
    @Resource
    private GeneralSrv generalSrv;
    /* @Resource
    private Detector detector;*/
    ///Variables para guardar la carga de datos///
    private BigDecimal idCarga;
    private String nombreArchivo;
    private Date fechaCargaRegistro;
    private BigDecimal cantidadRegistrosCargados;
    private String usuarioRegistro;
    private Blob documentoArchivo;
    private BigDecimal codigo;
    /*Variables para lista*/
    private List<HistorialCargaFechaFormatDto> historial;
    /*Variables para descargar archivo excel*/
    private Boolean archivo = false;
    private StreamedContent archivoUno;
    private String nombreDoc;

    private Integer cantidadRegistrosEcxel;
    private Boolean desactivarBoton;

    @PostConstruct
    public void inicializar() {
        this.historial = generalSrv.obtenerHistorialFormatoFecha();
        this.setDesactivarBoton(true);
    }

    public void guardarArchivo() {
        HistorialCargaDto cargaArchivo = new HistorialCargaDto();
        /*  cargaArchivo.setIdCarga(BigDecimal.ONE);*/
        cargaArchivo.setNombreArchivo(this.getNombreArchivo());
        cargaArchivo.setFechaCargaRegistro(new Date());
        cargaArchivo.setCantidadRegistrosCargados(new BigDecimal(cantidadRegistrosEcxel));
        cargaArchivo.setUsuarioRegistro(sesion.getUsuarioDto().getUsuario());
        cargaArchivo.setDocumentoArchivo(this.getDocumentoArchivo());
        setCodigo(this.generalSrv.guardarArchivo(cargaArchivo));

        this.infoMsg("Datos cargados exitosamente" + getCodigo());
        this.setDesactivarBoton(true);
        this.historial = generalSrv.obtenerHistorialFormatoFecha();
        RequestContext.getCurrentInstance().update("formContent:botonCargaHistorialArchivo");
        RequestContext.getCurrentInstance().update("formContent:detalle");
    }


    /*Método para almacenar el archivo excek*/
    public void subirArchivo(FileUploadEvent event) throws IOException {

        String mensaje = "Error en carga de datos. Se detecto que los campos NIT, ID TIPOLOGIA y AÑO TIPOLOGIA estan registrados en el sistema ";
        UploadedFile file = event.getFile();

        //validando archivo que si sea excel
        this.setNombreArchivo(file.getFileName());
        try {
            this.setDocumentoArchivo(new SerialBlob(file.getContents()));
        } catch (SQLException ex) {
            Logger.getLogger(HistorialCargaUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        Integer inicioFilaRealExcel = 0;

        InputStream fileE = file.getInputstream();
        ArrayList<Integer> filasRepetidas = new ArrayList<>();

        try {
            List<ExcelReadTipologiasDto> informacionCSV = new CsvToBeanBuilder(new InputStreamReader(fileE))
                    .withType(ExcelReadTipologiasDto.class)
                    .build()
                    .parse();

            for (int i = 0; i < informacionCSV.size(); i++) {
                if (i > 0) {
                    Integer existeIdTipologia = this.generalSrvImpl.obteneridCatalogoTipologia(Integer.valueOf(informacionCSV.get(i).getId_tipologia())).getCantidadRegistros().intValueExact();
                    if (existeIdTipologia == 0) {
                        this.warnMsg("El ID de la tipologia " + informacionCSV.get(i).getId_tipologia() + " No existe en bases de datos, por favor verifique la información");
                        return;
                    }

                }
            }

            for (int i = 0; i < informacionCSV.size(); i++) {
                if (i > 0) {
                    Integer contadorNit = this.generalSrvImpl.obtenerRegistroContadores(informacionCSV.get(i).getNit(), Integer.valueOf(informacionCSV.get(i).getId_tipologia()), Integer.valueOf(informacionCSV.get(i).getAnio_informacion()), 1).getCantidadRegistros().intValueExact();

                    if (contadorNit > 0) {
                        System.out.println("Fila repetida " + inicioFilaRealExcel);
                        //se agrega filas repetidas
                        Integer guardarFila = inicioFilaRealExcel++;
                        filasRepetidas.add(guardarFila);
                    }

                }
            }

            if (!filasRepetidas.isEmpty()) {
                if (filasRepetidas.size() > 0) {
                    for (int i = 0; i < filasRepetidas.size(); i++) {
                        mensaje += filasRepetidas.get(i);
                        if (filasRepetidas.size() > 1 && i != (filasRepetidas.size() - 1)) {
                            mensaje += ", ";
                        }
                    }

                    this.warnMsg(mensaje);
                    return;
                }
            }

            if (evaluarListaEstructuraCorrecta(informacionCSV)) {
                return;
            }

            for (int i = 0; i < informacionCSV.size(); i++) {
                if (i > 0) {
                    try {
                        Date estadoFechaExcel = new SimpleDateFormat("dd/MM/yyyy").parse(informacionCSV.get(i).getFecha_estado());
                        Date estadoRegistroExcel = new SimpleDateFormat("dd/MM/yyyy").parse(informacionCSV.get(i).getFecha_registro());
                        CifTipologiasDeEvasion modeloBDD = new CifTipologiasDeEvasion();
                        modeloBDD.setIdTipologia(new BigDecimal(informacionCSV.get(i).getId_tipologia()));
                        modeloBDD.setNit(informacionCSV.get(i).getNit());
                        modeloBDD.setReferencia(informacionCSV.get(i).getReferencias());
                        modeloBDD.setFechaRegistro(estadoRegistroExcel);
                        modeloBDD.setDescripcionTipologia(informacionCSV.get(i).getDescripción_tipologia());
                        modeloBDD.setAnioInformacion(new BigDecimal(informacionCSV.get(i).getAnio_informacion()));
                        modeloBDD.setIndicador(new BigDecimal(informacionCSV.get(i).getIndicador()));
                        modeloBDD.setColorIndicador(informacionCSV.get(i).getColor_indicador());
                        modeloBDD.setMontoDiscrepancia(new BigDecimal(informacionCSV.get(i).getMonto_discrepancia()));
                        modeloBDD.setDependencia(informacionCSV.get(i).getDependencia());
                        modeloBDD.setEstado(informacionCSV.get(i).getEstado());
                        modeloBDD.setFechaEstado(estadoFechaExcel);
                        modeloBDD.setObservacionesEstado(informacionCSV.get(i).getObservaciones_estado());

                        System.out.println(":::: " + informacionCSV.get(i).toString());
                        this.generalSrvImpl.guardarDetalleArchivoV2(modeloBDD);
                    } catch (ParseException ex) {
                        this.warnMsg("Se han ingresado fechas invalidas por favor validar nuevamente.");
                        System.out.println("Error controlado fechas " + ex.toString());
                    }
                }
            }

            cantidadRegistrosEcxel = informacionCSV.size() - 1;
            fileE.close();
            this.setDesactivarBoton(false);
            RequestContext.getCurrentInstance().update("formContent:botonCargaHistorialArchivo");
            this.infoMsg("Datos en archivo CSV cargados exitosamente.");
        } catch (IllegalStateException | NumberFormatException e) {
            System.out.println("  " + e.getMessage());
        }
    }

    private boolean evaluarListaEstructuraCorrecta(List<ExcelReadTipologiasDto> informacionCSV) {
        for (int i = 0; i < informacionCSV.size(); i++) {
            if (i > 0) {
                if (this.evaluarEspaciosBlancosNulos(informacionCSV.get(i).getFecha_registro())) {
                    System.out.println("ERROR BLANK NULL");
                    return true;
                }
                if (this.evaluarEspaciosBlancosNulos(informacionCSV.get(i).getAnio_informacion())) {
                    System.out.println("ERROR BLANK NULL");
                    return true;
                }
                if (this.evaluarEspaciosBlancosNulos(informacionCSV.get(i).getColor_indicador())) {
                    System.out.println("ERROR BLANK NULL");
                    return true;
                }
                if (this.evaluarEspaciosBlancosNulos(informacionCSV.get(i).getDependencia())) {
                    System.out.println("ERROR BLANK NULL");
                    return true;
                }
                if (this.evaluarEspaciosBlancosNulos(informacionCSV.get(i).getDescripción_tipologia())) {
                    System.out.println("ERROR BLANK NULL");
                    return true;
                }
                if (this.evaluarEspaciosBlancosNulos(informacionCSV.get(i).getEstado())) {
                    System.out.println("ERROR BLANK NULL");
                    return true;
                }
                if (this.evaluarEspaciosBlancosNulos(informacionCSV.get(i).getFecha_estado())) {
                    System.out.println("ERROR BLANK NULL");
                    return true;
                }
                if (this.evaluarEspaciosBlancosNulos(informacionCSV.get(i).getId_tipologia())) {
                    System.out.println("ERROR BLANK NULL");
                    return true;
                }
                if (this.evaluarEspaciosBlancosNulos(informacionCSV.get(i).getIndicador())) {
                    System.out.println("ERROR BLANK NULL");
                    return true;
                }
                if (this.evaluarEspaciosBlancosNulos(informacionCSV.get(i).getMonto_discrepancia())) {
                    System.out.println("ERROR BLANK NULL");
                    return true;
                }
                if (this.evaluarEspaciosBlancosNulos(informacionCSV.get(i).getNit())) {
                    System.out.println("ERROR BLANK NULL");
                    return true;
                }
                if (this.evaluarEspaciosBlancosNulos(informacionCSV.get(i).getObservaciones_estado())) {
                    System.out.println("ERROR BLANK NULL");
                    return true;
                }
                if (this.evaluarEspaciosBlancosNulos(informacionCSV.get(i).getReferencias())) {
                    System.out.println("ERROR BLANK NULL");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean evaluarEspaciosBlancosNulos(String string) {
        return string == null || string.trim().isEmpty();
    }

    public void descargarArchivos(BigDecimal idCarga, String docExcel) {
        try {

            HistorialCargaDto info = this.generalSrv.obtenerArchivoRegistros(idCarga);

            String archivoD = idCarga + "_" + docExcel;

            if (info.getDocumentoArchivo() == null) {
                archivo = true;
            }

            if (info.getDocumentoArchivo() != null) {
                long tamanio = info.getDocumentoArchivo() == null ? 0 : info.getDocumentoArchivo().length();
                byte[] documento = info.getDocumentoArchivo().getBytes(tamanio, 1);
                setArchivoUno(new DefaultStreamedContent(info.getDocumentoArchivo().getBinaryStream(), "text/csv", archivoD));
            }

        } catch (SQLException ex) {
            Logger.getLogger(HistorialCargaUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public BigDecimal getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(BigDecimal idCarga) {
        this.idCarga = idCarga;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Date getFechaCargaRegistro() {
        return fechaCargaRegistro;
    }

    public void setFechaCargaRegistro(Date fechaCargaRegistro) {
        this.fechaCargaRegistro = fechaCargaRegistro;
    }

    public BigDecimal getCantidadRegistrosCargados() {
        return cantidadRegistrosCargados;
    }

    public void setCantidadRegistrosCargados(BigDecimal cantidadRegistrosCargados) {
        this.cantidadRegistrosCargados = cantidadRegistrosCargados;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Blob getDocumentoArchivo() {
        return documentoArchivo;
    }

    public void setDocumentoArchivo(Blob documentoArchivo) {
        this.documentoArchivo = documentoArchivo;
    }

    public BigDecimal getCodigo() {
        return codigo;
    }

    public void setCodigo(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public List<HistorialCargaFechaFormatDto> getHistorial() {
        return historial;
    }

    public void setHistorial(List<HistorialCargaFechaFormatDto> historial) {
        this.historial = historial;
    }

    public Boolean getArchivo() {
        return archivo;
    }

    public void setArchivo(Boolean archivo) {
        this.archivo = archivo;
    }

    public StreamedContent getArchivoUno() {
        return archivoUno;
    }

    public void setArchivoUno(StreamedContent archivoUno) {
        this.archivoUno = archivoUno;
    }

    public String getNombreDoc() {
        return nombreDoc;
    }

    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }

    public Boolean getDesactivarBoton() {
        return desactivarBoton;
    }

    public void setDesactivarBoton(Boolean desactivarBoton) {
        this.desactivarBoton = desactivarBoton;
    }

}
