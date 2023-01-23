/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.ContribuyenteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.VerificacionesEnCampoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.VerificacionesEnCampoConteoDto;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author cgsamayo
 */
@Controller
@Scope("view")
public class VerificacionesEnCampoUI extends AbstractUI implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(VerificacionesEnCampoUI.class);
    private String nit = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private List<VerificacionesEnCampoDto> listVerificaciones;
    private List<VerificacionesEnCampoConteoDto> listVerificacionesConteo;
    private VerificacionesEnCampoDto dtVer;
    private String center_map;
    private double Lon;
    private double Lat;

    @PostConstruct
    public void inicializar() {
        dtVer = new VerificacionesEnCampoDto();
        try {
            if (nit == null || nit.isEmpty()) {

                this.errorMsg("No se ha recibido el par\u00e1metro esperado");
                LOG.error("No se ha recibido el parametro esperado");

            } else {
                listVerificaciones = this.generalSrvImpl.obtenerVerificacionesByNit(nit);

                if (listVerificaciones.isEmpty() || listVerificaciones == null) {
                    warnMsg("No existen registros para el NIT consultado...");
                } else {
                    listVerificacionesConteo = this.generalSrvImpl.obtenerVerificacionesConteoByNit(nit);

                }
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la p\u00e1gina de Verificaciones en Campo");
            LOG.error("Error al cargar la pagina de Verificaciones en Campo", e);
        }

    }

    public String getCenter_map() {
        return center_map;
    }

    public void setCenter_map(String center_map) {
        this.center_map = center_map;
    }

    public MapModel getSimpleModel() {
        DefaultMapModel simpleModel = new DefaultMapModel();

        //coordenada
        LatLng coord1 = new LatLng(this.Lon, this.Lat);

        //marcador
        simpleModel.addOverlay(new Marker(coord1, ""));

        return simpleModel;
    }

    public List<VerificacionesEnCampoDto> getListVerificaciones() {
        return listVerificaciones;
    }

    public void setListVerificaciones(List<VerificacionesEnCampoDto> listVerificaciones) {
        this.listVerificaciones = listVerificaciones;
    }

    public List<VerificacionesEnCampoConteoDto> getListVerificacionesConteo() {
        return listVerificacionesConteo;
    }

    public void setListVerificacionesConteo(List<VerificacionesEnCampoConteoDto> listVerificacionesConteo) {
        this.listVerificacionesConteo = listVerificacionesConteo;
    }

    public double getLon() {
        return Lon;
    }

    public void setLon(double Lon) {
        this.Lon = Lon;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double Lat) {
        this.Lat = Lat;
    }

    public VerificacionesEnCampoDto getDtVer() {
        return dtVer;
    }

    public void setDtVer(VerificacionesEnCampoDto dtVer) {
        this.dtVer = dtVer;
    }

    public void exportare() throws IOException {

        ContribuyenteDto contribuyente = generalSrvImpl.obtenerContribuyenteByNit(nit);

        HSSFWorkbook libro = new HSSFWorkbook();
        HSSFSheet hoja = libro.createSheet("HOJA1");

        /*FONT STYLE*/
        HSSFFont headerFont = libro.createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        /*COLOR FONT*/
        HSSFPalette palette = libro.getCustomPalette();
        HSSFColor myColor = palette.findSimilarColor(50, 135, 155);
        short palIndex = myColor.getIndex();

        /*ROW STYLE*/
        HSSFCellStyle rowStyle = hoja.getWorkbook().createCellStyle();
        rowStyle.setFillForegroundColor(palIndex);
        rowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        rowStyle.setFont(headerFont);

        /*CELL RED*/
        HSSFCellStyle cellRed = hoja.getWorkbook().createCellStyle();
        cellRed.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellRed.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        /*CELL GREEN*/
        HSSFCellStyle cellGreen = hoja.getWorkbook().createCellStyle();
        cellGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        /*FORMATO DE FECHA PARA CELDA*/
        HSSFCellStyle styleFecha = hoja.getWorkbook().createCellStyle();
        styleFecha.setDataFormat((short) 15);


        /*ENCABEZADOS DE LA HOJA DE EXCEL*/
        HSSFRow filaH = hoja.createRow(0);

        HSSFCell celdaH0 = filaH.createCell(0);
        celdaH0.setCellStyle(rowStyle);
        celdaH0.setCellValue("NIT");

        HSSFCell celdaH1 = filaH.createCell(1);
        celdaH1.setCellStyle(rowStyle);
        celdaH1.setCellValue("CONTRIBUYENTE");

        HSSFCell celdaH2 = filaH.createCell(2);
        celdaH2.setCellStyle(rowStyle);
        celdaH2.setCellValue("DIRECCION VERIFICADA");

        HSSFCell celdaH3 = filaH.createCell(3);
        celdaH3.setCellStyle(rowStyle);
        celdaH3.setCellValue("DEPARTAMENTO");

        HSSFCell celdaH4 = filaH.createCell(4);
        celdaH4.setCellStyle(rowStyle);
        celdaH4.setCellValue("MUNICIPIO");

        HSSFCell celdaH5 = filaH.createCell(5);
        celdaH5.setCellStyle(rowStyle);
        celdaH5.setCellValue("LATITUD");

        HSSFCell celdaH6 = filaH.createCell(6);
        celdaH6.setCellStyle(rowStyle);
        celdaH6.setCellValue("LONGITUD");

        HSSFCell celdaH7 = filaH.createCell(7);
        celdaH7.setCellStyle(rowStyle);
        celdaH7.setCellValue("TIPO DE DIRECCION");

        HSSFCell celdaH8 = filaH.createCell(8);
        celdaH8.setCellStyle(rowStyle);
        celdaH8.setCellValue("ESTADO DIRECCION");

        HSSFCell celdaH9 = filaH.createCell(9);
        celdaH9.setCellStyle(rowStyle);
        celdaH9.setCellValue("FUENTE ORIGEN INFORMACION");

        HSSFCell celdaH10 = filaH.createCell(10);
        celdaH10.setCellStyle(rowStyle);
        celdaH10.setCellValue("ESTIMACION DE CAPACIDAD INSTALADA");

        HSSFCell celdaH11 = filaH.createCell(11);
        celdaH11.setCellStyle(rowStyle);
        celdaH11.setCellValue("NUMERO DE CONTADOR ENERGIA ELECTRICA");

        HSSFCell celdaH12 = filaH.createCell(12);
        celdaH12.setCellStyle(rowStyle);
        celdaH12.setCellValue("REFERENCIA VERIFICACION");

        HSSFCell celdaH13 = filaH.createCell(13);
        celdaH13.setCellStyle(rowStyle);
        celdaH13.setCellValue("FECHA VERIFICACION");

        HSSFCell celdaH14 = filaH.createCell(14);
        celdaH14.setCellStyle(rowStyle);
        celdaH14.setCellValue("RESPONSABLE DE LA VERIFICACION");

        HSSFCell celdaH15 = filaH.createCell(15);
        celdaH15.setCellStyle(rowStyle);
        celdaH15.setCellValue("ESTADO REGISTRO");

        for (int x = 0; x < listVerificaciones.size(); x++) {

            HSSFRow fila = hoja.createRow(x + 1);

            HSSFCell celda0 = fila.createCell(0);
            celda0.setCellValue(nit);

            HSSFCell celda1 = fila.createCell(1);
            celda1.setCellValue(contribuyente.getNombreContribuyente());

            HSSFCell celda2 = fila.createCell(2);

            celda2.setCellValue(listVerificaciones.get(x).getDireccionVerificada());

            HSSFCell celda3 = fila.createCell(3);
            celda3.setCellValue(listVerificaciones.get(x).getDepartamento());

            HSSFCell celda4 = fila.createCell(4);
            celda4.setCellValue(listVerificaciones.get(x).getMunicipio());

            HSSFCell celda5 = fila.createCell(5);
            celda5.setCellValue(listVerificaciones.get(x).getLatitud().toString());

            HSSFCell celda6 = fila.createCell(6);
            celda6.setCellValue(listVerificaciones.get(x).getLongitud().toString());

            HSSFCell celda7 = fila.createCell(7);
            celda7.setCellValue(listVerificaciones.get(x).getTipoDireccion());

            HSSFCell celda8 = fila.createCell(8);
            if ("LOCALIZADO".equals(listVerificaciones.get(x).getEstadoDireccion().toUpperCase())) {
                celda8.setCellStyle(cellGreen);
            } else {
                celda8.setCellStyle(cellRed);
            }
            celda8.setCellValue(listVerificaciones.get(x).getEstadoDireccion());

            HSSFCell celda9 = fila.createCell(9);
            celda9.setCellValue(listVerificaciones.get(x).getFuenteDeInformacion());

            HSSFCell celda10 = fila.createCell(10);
            celda10.setCellValue(listVerificaciones.get(x).getEstimacionCapacidadInstalada());

            HSSFCell celda11 = fila.createCell(11);
            celda11.setCellValue(listVerificaciones.get(x).getContadorEnergiaElectrica());

            HSSFCell celda12 = fila.createCell(12);
            celda12.setCellValue(listVerificaciones.get(x).getReferenciaVerificacion());

            HSSFCell celda13 = fila.createCell(13);
            celda13.setCellStyle(styleFecha);
            celda13.setCellValue(listVerificaciones.get(x).getFechaCreacion());

            HSSFCell celda14 = fila.createCell(14);
            celda14.setCellValue(listVerificaciones.get(x).getResponsableDeLaVerificacion());

            HSSFCell celda15 = fila.createCell(15);
            celda15.setCellValue(listVerificaciones.get(x).getEstadoRegistro());

        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        externalContext.setResponseContentType("application/vnd.ms-excel; charset=utf-8");

        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"verificaciones_en_campo.xls\"");

        libro.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();

    }
}
