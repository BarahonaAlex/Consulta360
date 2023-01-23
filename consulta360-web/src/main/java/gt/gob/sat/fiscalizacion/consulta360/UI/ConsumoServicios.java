/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.AfiliacionesMSRTUNubeDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.CamEspecialesContriDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.HandledException;
import gt.gob.sat.fiscalizacion.consulta360.dto.ImpuestosDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.InfoConteoEstablecimientosEstadoDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.InfoEstablecimientosDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.PartesRelacionadasDto;
import java.io.IOException;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

/**
 *
 * @author rfsartio
 */
@Controller
@Scope("view")
public class ConsumoServicios extends RestTemplateService {

    final static Logger logger = LoggerFactory.getLogger(RestTemplateService.class);
    private HttpHeaders headers = new HttpHeaders();
    private String baseUrl;

    /*Leer properties*/
    Properties propiedades = new Properties();

    @Autowired
    ConsumoTokenInject consumoTokenInject;

    @PostConstruct
    public void inicializar() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (consumoTokenInject.getObtenerToken() != null && !StringUtils.isEmpty(consumoTokenInject.getObtenerToken())) {
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + consumoTokenInject.getObtenerToken());
        }
        this.configUrls();
         System.out.println("tocken++++++++++++" + consumoTokenInject.getObtenerToken());
    }

    public void configUrls() {
        try {
            propiedades.load(getClass().getClassLoader().getResourceAsStream("consulta360MSConfig.properties"));
            baseUrl = propiedades.getProperty("url.microservicios.consumo");
        } catch (IOException ex) {
            System.out.println("Algo malo paso " + ex.toString());
        }
    }

    public ImpuestosDto[] obtenerAfiliacionesRtu(String nit) throws HandledException {
        String ruta = baseUrl + "sat-mp/contribuyente-afiliaciones-rtu/" + nit;
        System.out.println("/*******RUTA ********/" + ruta);
        HttpEntity<String> tokenInject = new HttpEntity<>(headers);
        return (ImpuestosDto[]) this.restT(ruta, HttpMethod.GET, tokenInject, ImpuestosDto[].class).getBody();
    }

    public PartesRelacionadasDto[] obtenerPartesRelacionadas(String nit, int tipoConsulta) throws HandledException {
        String ruta = baseUrl + "sat-mp/relacionamientos-contribuyente-360/" + nit + "/" + tipoConsulta;
        System.out.println("/*******RUTA ********/" + ruta);
        HttpEntity<String> tokenInject = new HttpEntity<>(headers);
        return (PartesRelacionadasDto[]) this.restT(ruta, HttpMethod.GET, tokenInject, PartesRelacionadasDto[].class).getBody();
    }

    public InfoEstablecimientosDto[] obtenerEstablecimientos(String nit) throws HandledException {
        String ruta = baseUrl + "sat-mp/contribEstablecimientos/" + nit;
        System.out.println("/*******RUTA ********/" + ruta);
        HttpEntity<String> tokenInject = new HttpEntity<>(headers);
        System.out.println("consumir Micro***********" + tokenInject.getHeaders().toString());
        return (InfoEstablecimientosDto[]) this.restT(ruta, HttpMethod.GET, tokenInject, InfoEstablecimientosDto[].class).getBody();
    }
    

    public InfoConteoEstablecimientosEstadoDto[] obtenerEstablecimientoActivoInactivo(String nit, Integer estado) throws HandledException {
        String ruta = baseUrl + "sat-mp/cantidad-by-estado-establecimientos/" + nit + "/" + estado;
        System.out.println("/*******RUTA ********/" + ruta);
        HttpEntity<String> tokenInject = new HttpEntity<>(headers);
        return (InfoConteoEstablecimientosEstadoDto[]) this.restT(ruta, HttpMethod.GET, tokenInject, InfoConteoEstablecimientosEstadoDto[].class).getBody();
    }
    
    public CamEspecialesContriDto[] obtenerCaracteristicasEspeciales(String nit) throws HandledException {
        String ruta = baseUrl + "sat-mp/consulta360-caracteristicas/" + nit;
        System.out.println("/*******RUTA ********/" + ruta);
        HttpEntity<String> tokenInject = new HttpEntity<>(headers);
        return (CamEspecialesContriDto[]) this.restT(ruta, HttpMethod.GET, tokenInject, CamEspecialesContriDto[].class).getBody();
    }
    

}
