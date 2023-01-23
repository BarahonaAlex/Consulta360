/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ContribuyenteDto;
import java.io.Serializable;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import gt.gob.sat.arquitectura.seguridad.encriptor.svc.impl.EncriptorSvcImpl;
import gt.gob.sat.properties.util.ConfigurationUtil;
import java.net.URLEncoder;

/**
 *
 * @author dealonzo
 */
@Controller
@Scope("view")
public class MainUI extends AbstractUI implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MainUI.class);

    private String nit;
    private ContribuyenteDto datos = new ContribuyenteDto();
    private String seccionActiva = "1.2";

    public void validarNit() {
        try {
            nit = nitValidador.nitDepurado(nit);
            if (nit.isEmpty()) {
                warnMsg("Ingrese el NIT a consultar");
            } else {
                if (nitValidador.validarNit(nit) == 1) {
                    if (nit.equalsIgnoreCase(datos.getNitContribuyente())) {
                        RequestContext.getCurrentInstance().execute("ocultarModal('dlgInicio');");
                    } else {
                        ContribuyenteDto contribuyente = generalSrvImpl.obtenerContribuyenteByNit(nit);
                        if (contribuyente.getNitContribuyente().isEmpty()) {
                            warnMsg("El NIT no existe en SAT");
                        } else {
                            generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(1, nit, sesion.getUsuarioDto().getNit(), sesion.getIpCliente()));
                            datos.setNitContribuyente(contribuyente.getNitContribuyente());
                            datos.setNombreContribuyente(contribuyente.getNombreContribuyente());
                            RequestContext.getCurrentInstance().execute("ocultarModal('dlgInicio');");
                            RequestContext.getCurrentInstance().execute("cargarFrame('perfilContribuyente.jsf?pNit=" + datos.getNitContribuyente() + "');");
                            RequestContext.getCurrentInstance().execute("mostrarModal('dlgStatus');");
                            setSeccionActiva("1.2");
                        }
                    }
                } else {
                    warnMsg("NIT inv\u00e1lido");
                }
                nit = null;
            }
        } catch (Exception e) {
            errorMsg("Error al consultar el NIT ingresado");
            LOG.error("Error al consultar el NIT ingresado", e);
        }
    }

    public ContribuyenteDto getDatos() {
        return datos;
    }

    public void setDatos(ContribuyenteDto datos) {
        this.datos = datos;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getSeccionActiva() {
        return seccionActiva;
    }

    public void setSeccionActiva(String seccionActiva) {
        this.seccionActiva = seccionActiva;
    }

    public String getRelacionamiento() throws Exception {

        /*
        * SE OBTIENE LA URL DESDE EL FILE PROPERTIES
         */
        ConfigurationUtil urlPage = new ConfigurationUtil("relacionamientos360");
        StringBuilder urlFile = new StringBuilder();
        urlFile.append(urlPage.getKey("url.cdn"));
        String urlPageNube = urlFile + "/consulta-360-relacionamientos-siii-temp/";
        /*---------------------------------------------------------------------*/

        urlPageNube += datos.getNitContribuyente();

        System.out.println("URL PAGE NUBE " + urlPageNube);

        return urlPageNube;

    }

    public String getVehiculos() throws Exception {

        /*
        * SE OBTIENE LA URL DESDE EL FILE PROPERTIES
         */
        ConfigurationUtil urlPage = new ConfigurationUtil("relacionamientos360");
        StringBuilder urlFile = new StringBuilder();
        urlFile.append(urlPage.getKey("vhs.getUrl"));
        /*---------------------------------------------------------------------*/

        EncriptorSvcImpl encriptorSvcImpl = new EncriptorSvcImpl();
        encriptorSvcImpl.setAlgorithm("PBEWithMD5AndDES");
        encriptorSvcImpl.setPhrase("SAT$GOB$GT.Reca");

        String nitaconsultar = datos.getNitContribuyente();
        String login = sesion.getLogin();
        String var = encriptorSvcImpl.encriptar("nit=" + nitaconsultar + "&login=" + login);

        var = URLEncoder.encode(var, "UTF-8");
        var = var.replace("&", "CAMBIARCARACTER").replace(";", "CAMBIARCARACTER1").replace("+", "CAMBIARCARACTER2");

        return urlFile.toString() + URLEncoder.encode(var, "UTF-8");

    }

    public String getOmisos() throws Exception {

        /*
        * SE OBTIENE LA URL DESDE EL FILE PROPERTIES
         */
        ConfigurationUtil urlPage = new ConfigurationUtil("relacionamientos360");
        StringBuilder urlFile = new StringBuilder();
        urlFile.append(urlPage.getKey("oms.getUrl"));
        /*---------------------------------------------------------------------*/

        EncriptorSvcImpl encriptorSvcImpl = new EncriptorSvcImpl();
        encriptorSvcImpl.setAlgorithm("PBEWithMD5AndDES");
        encriptorSvcImpl.setPhrase("SAT$GOB$GT.Reca");

        String nitaconsultar = datos.getNitContribuyente();
        String login = sesion.getLogin();
        String var = encriptorSvcImpl.encriptar("nit=" + nitaconsultar + "&login=" + login);

        var = URLEncoder.encode(var, "UTF-8");
        var = var.replace("&", "CAMBIARCARACTER").replace(";", "CAMBIARCARACTER1").replace("+", "CAMBIARCARACTER2");

        return urlFile.toString() + URLEncoder.encode(var, "UTF-8");

    }

    public String getConveniosPago() throws Exception {

        /*
        * SE OBTIENE LA URL DESDE EL FILE PROPERTIES
         */
        ConfigurationUtil urlPage = new ConfigurationUtil("relacionamientos360");
        StringBuilder urlFile = new StringBuilder();
        urlFile.append(urlPage.getKey("cdp.getUrl"));
        /*---------------------------------------------------------------------*/

        EncriptorSvcImpl encriptorSvcImpl = new EncriptorSvcImpl();
        encriptorSvcImpl.setAlgorithm("PBEWithMD5AndDES");
        encriptorSvcImpl.setPhrase("SAT$GOB$GT.Reca");

        String nitaconsultar = datos.getNitContribuyente();
        String login = sesion.getLogin();
        String var = encriptorSvcImpl.encriptar("nit=" + nitaconsultar + "&login=" + login);

        var = URLEncoder.encode(var, "UTF-8");
        var = var.replace("&", "CAMBIARCARACTER").replace(";", "CAMBIARCARACTER1").replace("+", "CAMBIARCARACTER2");

        return urlFile.toString() + URLEncoder.encode(var, "UTF-8");
    }

    public String getVisualizacion() throws Exception {

        /*
        * SE OBTIENE LA URL DESDE EL FILE PROPERTIES
         */
        ConfigurationUtil urlPage = new ConfigurationUtil("relacionamientos360");
        StringBuilder urlFile = new StringBuilder();
        urlFile.append(urlPage.getKey("vdr.getUrl"));
        /*---------------------------------------------------------------------*/

        EncriptorSvcImpl encriptorSvcImpl = new EncriptorSvcImpl();
        encriptorSvcImpl.setAlgorithm("PBEWithMD5AndDES");
        encriptorSvcImpl.setPhrase("SAT$GOB$GT.Reca");

        String nitaconsultar = datos.getNitContribuyente();
        String login = sesion.getLogin();
        String var = encriptorSvcImpl.encriptar("nit=" + nitaconsultar + "&login=" + login);

        var = URLEncoder.encode(var, "UTF-8");
        var = var.replace("&", "CAMBIARCARACTER").replace(";", "CAMBIARCARACTER1").replace("+", "CAMBIARCARACTER2");

        return urlFile.toString() + URLEncoder.encode(var, "UTF-8");
    }

}
