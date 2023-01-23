/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.ContribuyenteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.TipologiasEvasionDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.TipologiasEvasionAnualDto;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
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

/**
 *
 * @author cgsamayo
 */
@Controller
@Scope("view")
public class TipologiasEvasionUI extends AbstractUI implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(TipologiasEvasionUI.class);

    private String nit = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private List<TipologiasEvasionDto> listTipologias;

    private List<TipologiasEvasionAnualDto> listTipologiasAnual;

    @PostConstruct
    public void inicializar() {

        try {
            if (nit == null || nit.isEmpty()) {

                this.errorMsg("No se ha recibido el par\u00e1metro esperado");
                LOG.error("No se ha recibido el parametro esperado");

            } else {
                listTipologias = this.generalSrvImpl.obtenerTipologiasEvasionByNit(nit);
                
                if (listTipologias.isEmpty() || listTipologias == null) {
                    warnMsg("No existen registros para el NIT consultado");
                } else {
                    listTipologiasAnual = this.generalSrvImpl.obtenerTipologiasEvasionAnualByNit(nit);
                }
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la p\u00e1gina de Tipologias de Evasi&ocute;n");
            LOG.error("Error al cargar la pagina de Tipologias de Evasi&ocute;n", e);
        }
    }

    public List<TipologiasEvasionDto> getListTipologias() {
        return listTipologias;
    }

    public void setListTipologias(List<TipologiasEvasionDto> listTipologias) {
        this.listTipologias = listTipologias;
    }

    public List<TipologiasEvasionAnualDto> getListTipologiasAnual() {
        return listTipologiasAnual;
    }

    public void setListTipologiasAnual(List<TipologiasEvasionAnualDto> listTipologiasAnual) {
        this.listTipologiasAnual = listTipologiasAnual;
    }

    public void exportare() throws IOException  {
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
        
        /*FORMATO DE FECHA PARA CELDA*/
        HSSFCellStyle styleFecha = hoja.getWorkbook().createCellStyle();
        styleFecha.setDataFormat((short)15);
        
        /*FORMATO MONEDA PARA CELDA*/
        DataFormat format = libro.createDataFormat();
        HSSFCellStyle styleMoneda = libro.createCellStyle();
        styleMoneda.setDataFormat(format.getFormat("Q #,##0.00; Q (#,##0.00)"));
        
        /*ENCABEZADO PRINCIPAL DE LA HOJA DE EXCEL*/
        HSSFRow filaEnc1 = hoja.createRow(1);

        HSSFCell celdaEnc1 = filaEnc1.createCell(0);
        celdaEnc1.setCellValue("NIT:");

        HSSFCell celdaEnc2 = filaEnc1.createCell(1);
        celdaEnc2.setCellValue(nit);

        HSSFRow filaEnc2 = hoja.createRow(2);
        
        HSSFCell celdaEnc3 = filaEnc2.createCell(0);
        celdaEnc3.setCellValue("NOMBRE");
        
        HSSFCell celdaEnc4 = filaEnc2.createCell(1);
        celdaEnc4.setCellValue(contribuyente.getNombreContribuyente());
                
        
        /*ENCABEZADOS DETALLE DE LA HOJA DE EXCEL*/
        HSSFRow filaH = hoja.createRow(5);

        HSSFCell celdaH0 = filaH.createCell(0);
        celdaH0.setCellStyle(rowStyle);
        celdaH0.setCellValue("TIPOLOGIA");

        HSSFCell celdaH1 = filaH.createCell(1);
        celdaH1.setCellStyle(rowStyle);
        celdaH1.setCellValue("ID TIPOLOGIA");

        HSSFCell celdaH2 = filaH.createCell(2);
        celdaH2.setCellStyle(rowStyle);
        celdaH2.setCellValue("REFERENCIA");

        HSSFCell celdaH3 = filaH.createCell(3);
        celdaH3.setCellStyle(rowStyle);
        celdaH3.setCellValue("FECHA DE REGISTRO");

        HSSFCell celdaH4 = filaH.createCell(4);
        celdaH4.setCellStyle(rowStyle);
        celdaH4.setCellValue("DESCRIPCION TIPOLOGIA");

        HSSFCell celdaH5 = filaH.createCell(5);
        celdaH5.setCellStyle(rowStyle);
        celdaH5.setCellValue("AÑO INFORMACION BASE");

        HSSFCell celdaH6 = filaH.createCell(6);
        celdaH6.setCellStyle(rowStyle);
        celdaH6.setCellValue("INDICADOR");

        HSSFCell celdaH7 = filaH.createCell(7);
        celdaH7.setCellStyle(rowStyle);
        celdaH7.setCellValue("COLOR INDICADOR");

        HSSFCell celdaH8 = filaH.createCell(8);
        celdaH8.setCellStyle(rowStyle);
        celdaH8.setCellValue("MONTO DISCREPANCIA");

        HSSFCell celdaH9 = filaH.createCell(9);
        celdaH9.setCellStyle(rowStyle);
        celdaH9.setCellValue("DEPENDENCIA");

        HSSFCell celdaH10 = filaH.createCell(10);
        celdaH10.setCellStyle(rowStyle);
        celdaH10.setCellValue("ESTADO");

        HSSFCell celdaH11 = filaH.createCell(11);
        celdaH11.setCellStyle(rowStyle);
        celdaH11.setCellValue("FECHA ESTADO");

        HSSFCell celdaH12 = filaH.createCell(12);
        celdaH12.setCellStyle(rowStyle);
        celdaH12.setCellValue("OBSERVACION ESTADO");

        for (int x = 0; x < listTipologias.size(); x++) {

            HSSFRow fila = hoja.createRow(x + 6);

            HSSFCell celda0 = fila.createCell(0);
            celda0.setCellValue(listTipologias.get(x).getTipologia());

            HSSFCell celda1 = fila.createCell(1);
            if(listTipologias.get(x).getIdTipologia()!=null){
                celda1.setCellValue(listTipologias.get(x).getIdTipologia().toString());
            }
            else
            {
                celda1.setCellValue("");
            }

            HSSFCell celda2 = fila.createCell(2);
            celda2.setCellValue(listTipologias.get(x).getReferencia());

            HSSFCell celda3 = fila.createCell(3);
            celda3.setCellStyle(styleFecha);
            celda3.setCellValue(listTipologias.get(x).getFechaRegistro());

            HSSFCell celda4 = fila.createCell(4);
            celda4.setCellValue(listTipologias.get(x).getDescripcionTipologia());

            HSSFCell celda5 = fila.createCell(5);
            celda5.setCellValue(listTipologias.get(x).getAnioInformacion().toString());

            HSSFCell celda6 = fila.createCell(6);
            if(listTipologias.get(x).getIndicador()!=null){
                celda6.setCellValue(listTipologias.get(x).getIndicador().toString());
            }
            else
            {
                celda6.setCellValue("");
            }
            
            HSSFCell celda7 = fila.createCell(7);
            celda7.setCellValue(listTipologias.get(x).getColorIndicador());

            HSSFCell celda8 = fila.createCell(8);
            celda8.setCellValue(listTipologias.get(x).getMontoDiscrepancia().doubleValue());
            celda8.setCellStyle(styleMoneda);

            HSSFCell celda9 = fila.createCell(9);
            celda9.setCellValue(listTipologias.get(x).getDependencia());

            HSSFCell celda10 = fila.createCell(10);
            celda10.setCellValue(listTipologias.get(x).getEstado());

            HSSFCell celda11 = fila.createCell(11);
            celda11.setCellStyle(styleFecha);
            celda11.setCellValue(listTipologias.get(x).getFechaEstado());

            HSSFCell celda12 = fila.createCell(12);
            celda12.setCellValue(listTipologias.get(x).getObservacionesEstado());

        }
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        externalContext.setResponseContentType("application/vnd.ms-excel; charset=utf-8");

        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"tipologias_evasion.xls\"");

        libro.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();

    }
}
