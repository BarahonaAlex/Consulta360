/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.TokenObjectDto;
import gt.gob.sat.properties.util.ConfigurationUtil;
import java.io.IOException;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rfsartio
 */
@EnableScheduling
@Controller
@Scope("view")
public class ConsumoTokenInject {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ConsumoTokenInject.class);
    /*Leer properties*/
    Properties propiedades = new Properties();

    private String clientId;
    private String clientSecret;
    private String tokenAccessUri;
    private String obtenerToken;
    private TokenObjectDto tokenRespuesta = new TokenObjectDto();

    @PostConstruct
    public void inicio() {
        //Credenciales cliente ejemploClient
        //OAuth2 client id: ejemploClient
        //OAuth2 client secret: desa123$
        this.clientId = "ejemploClient";
        this.clientSecret = "desa123$";
        this.getAccessToken();
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void getAccessToken() {

        try {
            propiedades.load(getClass().getClassLoader().getResourceAsStream("consulta360MSConfig.properties"));
            clientId = propiedades.getProperty("oauth-config.usuario");
            clientSecret = propiedades.getProperty("oauth-config.passwd");
            tokenAccessUri = propiedades.getProperty("oauth-config.consumo-url");
        } catch (IOException e) {
            System.out.println("Error al obtener token -> " + e.getMessage());
            this.setObtenerToken("");
        }

        ResponseEntity<TokenObjectDto> response = null;
        RestTemplate restTemplate = new RestTemplate();
        String credentials = clientId + ":" + clientSecret;
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        try {

            System.out.println("::CLIENT ID " + clientId);
            System.out.println("::CLIENT SECRET " + clientSecret);
            System.out.println("TOKENACESSURL " + tokenAccessUri);

            response = restTemplate.exchange(tokenAccessUri, HttpMethod.POST, request, TokenObjectDto.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Obtuvo token - " + response.getBody().toString());
                this.tokenRespuesta = response.getBody();
                this.setObtenerToken(response.getBody().getAccess_token());
            } else {
                System.out.println("Error al obtener token - " + response.getBody() + " status - " + response.getStatusCode());
                this.setObtenerToken("");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Error al obtener token -> " + e.getMessage());
            this.setObtenerToken("");
            //return "";

        }
    }

    public boolean cerrarSesionNube(String pLogin, String entidad) throws IOException {

        try {
            propiedades.load(getClass().getClassLoader().getResourceAsStream("consulta360MSConfig.properties"));
            tokenAccessUri = propiedades.getProperty("url.microservicios.consumo");
        } catch (IOException e) {
            System.out.println("Error al obtener token -> " + e.getMessage());
            this.setObtenerToken("");
        }

        TokenObjectDto accesTokenSecCloud = tokenRespuesta;
        int hashCode = this.hashCode();
        HttpHeaders headers = new HttpHeaders();

        if (accesTokenSecCloud != null && !StringUtils.isEmpty(accesTokenSecCloud.getAccess_token())) {
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accesTokenSecCloud.getAccess_token());
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<Void> res = restTemplate.exchange(tokenAccessUri + "sat-seguridad/cerrarSesion/" + pLogin + "?pReqn=" + hashCode + "&pIssuer=" + entidad, HttpMethod.GET, request, Void.class);
        if (res.getStatusCode() != HttpStatus.OK) {

            System.out.println("Error cerrando sesion, respuesta {}" + res.getStatusCode());

            return false;

        }

        System.out.println("Cerrando sesion en servidor de autorizacion, respuesta {}" + res.getStatusCode());

        return true;

    }

    public String getObtenerToken() {
        return obtenerToken;
    }

    public void setObtenerToken(String obtenerToken) {
        this.obtenerToken = obtenerToken;
    }

}
