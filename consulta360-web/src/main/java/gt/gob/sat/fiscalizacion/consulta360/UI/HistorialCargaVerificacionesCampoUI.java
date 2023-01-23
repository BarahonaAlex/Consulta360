/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import com.opencsv.bean.CsvToBeanBuilder;
import gt.gob.sat.fiscalizacion.consulta360.dto.ExcelReadTipologiasDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ExcelReadVerificacionesDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HistorialCargaDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HistorialCargaVerificacionesCampoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HistorialCargaVerificacionesFechaFormatDto;
import gt.gob.sat.fiscalizacion.consulta360.modelo.CifTipologiasDeEvasion;
import gt.gob.sat.fiscalizacion.consulta360.modelo.CifVerificacionesEnCampo;
import gt.gob.sat.fiscalizacion.consulta360.srv.GeneralSrv;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialBlob;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author adlabrigo
 */
@Controller
@Scope("view")
public class HistorialCargaVerificacionesCampoUI extends AbstractUI implements Serializable {

    private static final long serialVersionUID = 1L;
    @Resource
    private GeneralSrv generalSrv;

    /* @Resource
    private Detector detector;*/
    ///Variables para guardar la carga de datos///
    private BigDecimal idCargaCampo;
    private String nombreArchivo;
    private Date fechaCargaRegistro;
    private BigDecimal cantidadRegistrosCargados;
    private String usuarioRegistro;
    private Blob documentoArchivo;
    private BigDecimal codigo;
    /*Variables para lista*/
    private List<HistorialCargaVerificacionesFechaFormatDto> historial;

    /*Variables para descargar archivo excel*/
    private Boolean archivo = false;
    private StreamedContent archivoUno;
    private String nombreDoc;

    private Integer cantidadRegistrosEcxel;
    private Boolean desactivarBoton;

    @PostConstruct
    public void inicializar() {
        this.historial = generalSrv.obtenerHistorialVerificacionesFormatoFecha();
        this.setDesactivarBoton(true);

    }

    public void guardarArchivo() {
        HistorialCargaVerificacionesCampoDto cargaVerificacionesCampo = new HistorialCargaVerificacionesCampoDto();
        /*  cargaArchivo.setIdCarga(BigDecimal.ONE);*/
        cargaVerificacionesCampo.setNombreArchivo(this.getNombreArchivo());
        cargaVerificacionesCampo.setFechaCargaRegistro(new Date());
        cargaVerificacionesCampo.setCantidadRegistrosCargados(new BigDecimal(this.getCantidadRegistrosEcxel()));
        cargaVerificacionesCampo.setUsuarioRegistro(sesion.getUsuarioDto().getUsuario());
        cargaVerificacionesCampo.setDocumentoArchivo(this.getDocumentoArchivo());
        setCodigo(this.generalSrv.guardarCargaVerificacionesCampo(cargaVerificacionesCampo));

        this.infoMsg("Datos cargados exitosamente" + getCodigo());
        this.setDesactivarBoton(true);
        this.historial = generalSrv.obtenerHistorialVerificacionesFormatoFecha();
        RequestContext.getCurrentInstance().update("formContent:botonCargaHistorialArchivo");
        RequestContext.getCurrentInstance().update("formContent:botonCargaHistorialArchivo");
        RequestContext.getCurrentInstance().update("formContent:detalle");
    }

    /*Método para almacenar el archivo csv*/
    public void subirArchivo(FileUploadEvent event) throws IOException {

          String mensaje = "Error en carga de datos. Se detecto que los campos NIT, Dirección Verificada, Departamento, Municipio y Tipo de dirección se encuentran registrados en el sistema";
        UploadedFile file = event.getFile();

        //validando archivo que si sea excel
        this.setNombreArchivo(file.getFileName());
        try {
            this.setDocumentoArchivo(new SerialBlob(file.getContents()));
        } catch (SQLException ex) {
            Logger.getLogger(HistorialCargaVerificacionesCampoUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        Integer inicioFilaRealExcel = 0;

        InputStream fileE = file.getInputstream();
        ArrayList<Integer> filasRepetidas = new ArrayList<>();

        try {
            List<ExcelReadVerificacionesDto> informacionCSV = new CsvToBeanBuilder(new InputStreamReader(fileE))
                    .withType(ExcelReadVerificacionesDto.class)
                    .build()
                    .parse();
            
        for (int i = 0; i < informacionCSV.size(); i++) {

            //(lista.get(i).getNit(),lista.get(i).getDireccionVerificada(),lista.get(i).getDepartamento(),lista.get(i).getMunicipio(),lista.get(i).getTipoDireccion()).getCantidadRegistros()
            if (i > 0) {
                Integer contadorNit = this.generalSrvImpl.obtenerRegistroContadoresVerificaciones(informacionCSV.get(i).getNit(), informacionCSV.get(i).getDireccionVerificada(),informacionCSV.get(i).getDepartamento(), informacionCSV.get(i).getMunicipio(), informacionCSV.get(i).getTipoDireccion(), 1).getCantidadRegistros().intValueExact();

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

        for (int i = 0; i < informacionCSV.size(); i++) {
            if (i > 0) {
                try {
                    Date fechaCreacionExcel = new SimpleDateFormat("dd/MM/yyyy").parse(informacionCSV.get(i).getFechaCreacion());
                    CifVerificacionesEnCampo modeloBDD = new CifVerificacionesEnCampo();

                    modeloBDD.setNit(informacionCSV.get(i).getNit());
                    modeloBDD.setContribuyente(informacionCSV.get(i).getContribuyente());
                    modeloBDD.setDireccionVerificada(informacionCSV.get(i).getDireccionVerificada());
                    modeloBDD.setDepartamento(informacionCSV.get(i).getDepartamento());
                    modeloBDD.setMunicipio(informacionCSV.get(i).getMunicipio());
                    modeloBDD.setLongitud(new BigDecimal(informacionCSV.get(i).getLongitud()));
                    modeloBDD.setLatitud(new BigDecimal(informacionCSV.get(i).getLatitud()));
                    modeloBDD.setTipoDireccion(informacionCSV.get(i).getTipoDireccion());
                    modeloBDD.setEstadoDireccion(informacionCSV.get(i).getEstadoDireccion());
                    modeloBDD.setFuenteDeInformacion(informacionCSV.get(i).getFuenteDeInformacion());
                    modeloBDD.setEstimacionCapacidadInstalada(informacionCSV.get(i).getEstimacionCapacidadInstalada());
                    modeloBDD.setContadorEnergiaElectrica(informacionCSV.get(i).getContadorEnergiaElectrica());
                    modeloBDD.setReferenciaVerificacion(informacionCSV.get(i).getReferenciaVerificacion());
                    modeloBDD.setResponsableDeLaVerificacion(informacionCSV.get(i).getResponsableDeLaVerificacion());
                    modeloBDD.setEstadoRegistro(informacionCSV.get(i).getEstadoRegistro());
                    modeloBDD.setFechaCreacion(fechaCreacionExcel);

                    System.out.println(":::: " + informacionCSV.get(i).toString());
                    this.generalSrvImpl.guardarDetalleArchivoVerificaciones(modeloBDD);
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

public void descargarArchivos(BigDecimal idCargaCampo, String docExcel) {
       try {

            HistorialCargaVerificacionesCampoDto info = this.generalSrv.obtenerArchivoRegistrosVerificaciones(idCargaCampo);

            String archivoD = docExcel + idCargaCampo + ".csv";

            if (info.getDocumentoArchivo() == null) {
                archivo = true;
            }

            if (info.getDocumentoArchivo() != null) {
                long tamanio = info.getDocumentoArchivo() == null ? 0 : info.getDocumentoArchivo().length();
                byte[] documento = info.getDocumentoArchivo().getBytes(tamanio, 1);
                setArchivoUno(new DefaultStreamedContent(info.getDocumentoArchivo().getBinaryStream(), "text/csv", archivoD));
            }

        } catch (SQLException ex) {
            Logger.getLogger(HistorialCargaVerificacionesCampoDto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public BigDecimal getIdCargaCampo() {
        return idCargaCampo;
    }

    public void setIdCargaCampo(BigDecimal idCargaCampo) {
        this.idCargaCampo = idCargaCampo;
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

    public List<HistorialCargaVerificacionesFechaFormatDto> getHistorial() {
        return historial;
    }

    public void setHistorial(List<HistorialCargaVerificacionesFechaFormatDto> historial) {
        this.historial = historial;
    }

    

    public Boolean getDesactivarBoton() {
        return desactivarBoton;
    }

    public void setDesactivarBoton(Boolean desactivarBoton) {
        this.desactivarBoton = desactivarBoton;
    }

    public Integer getCantidadRegistrosEcxel() {
        return cantidadRegistrosEcxel;
    }

    public void setCantidadRegistrosEcxel(Integer cantidadRegistrosEcxel) {
        this.cantidadRegistrosEcxel = cantidadRegistrosEcxel;
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
    

}
