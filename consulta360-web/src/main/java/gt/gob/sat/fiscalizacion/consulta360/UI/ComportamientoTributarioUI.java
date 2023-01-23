package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.properties.util.ConfigurationUtil;
import java.io.Serializable;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class ComportamientoTributarioUI extends AbstractUI implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ComportamientoTributarioUI.class);
    
    private String nit=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private int anioInicio,anioFin;

    @PostConstruct
    private void init(){
        if(nit==null || nit.isEmpty()){
            errorMsg("No se ha recibido el par\u00e1metro esperado");
            LOG.error("No se ha recibido el parametro esperado");
        }else{
            infoMsg("Defina el periodo de consulta");
        }
    }

    public void consultar(){
        if (anioInicio==0 || anioFin==0 || anioInicio>anioFin) {
            warnMsg("Verifique el periodo de consulta");
        }else{
            if (anioFin>LocalDate.now().getYear()) {
                warnMsg("El periodo de consulta no puede exceder del a\u00f1o actual");
            } else {
                try {
                    ConfigurationUtil urlArchivo = new ConfigurationUtil("reportesBIRT");
                    StringBuilder urlReporte = new StringBuilder();

                    urlReporte.append(urlArchivo.getKey("urlBirt"));
                    urlReporte.append(urlArchivo.getKey("urlReporte")).append("comportamientoTributario.rptdesign");
                    urlReporte.append("&__format=xls");
                    urlReporte.append("&pNit=").append(nit);
                    urlReporte.append("&pAnioInicio=").append(anioInicio);
                    urlReporte.append("&pAnioFin=").append(anioFin);
                    urlReporte.append("&").append(sesion.getTicket());
                    generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(15,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
                    RequestContext.getCurrentInstance().execute("window.open('"+urlReporte.toString()+"')");

                    anioInicio=anioFin=0;
                    infoMsg("Defina el periodo de consulta");
                } catch (Exception e) {
                    errorMsg("Ocurri\u00f3 un error al generar su reporte");
                    LOG.error("Ocurrio un error al generar el reporte de comportamiento tributario", e);
                }
            }
        }
    }

    public int getAnioInicio() {
        return anioInicio;
    }

    public void setAnioInicio(int anioInicio) {
        this.anioInicio = anioInicio;
    }

    public int getAnioFin() {
        return anioFin;
    }

    public void setAnioFin(int anioFin) {
        this.anioFin = anioFin;
    }
}