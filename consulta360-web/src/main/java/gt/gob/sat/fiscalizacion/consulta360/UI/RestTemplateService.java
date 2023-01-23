/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import static gt.gob.sat.fiscalizacion.consulta360.UI.ConsumoServicios.logger;
import gt.gob.sat.fiscalizacion.consulta360.dto.HandledException;
import gt.gob.sat.fiscalizacion.consulta360.dto.restTemplateDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rfsartio
 */
@Service
@Component
public class RestTemplateService implements RestTemplateInterface {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public restTemplateDto restT(String url, HttpMethod metodo, HttpEntity<?> requestBody, Class tipo) throws HandledException {
        try {
            ResponseEntity<Object> respuesta;

            if (metodo.equals(HttpMethod.GET)) {
                 respuesta = restTemplate.exchange(url, metodo, requestBody, tipo);
            } else {
                respuesta = restTemplate.exchange(url, metodo, requestBody, tipo);
            }

            switch (respuesta.getStatusCode()) {
                case OK:
                    return new restTemplateDto(respuesta.getBody(), respuesta.getStatusCode());
                case NOT_FOUND:
                    logger.info("RUTA:::" + url + " ----- " + "Response:::" + respuesta.getBody() + "------ NOT FOUND SERVICE");
                    return new restTemplateDto(respuesta.getBody(), respuesta.getStatusCode());
                default:
                    logger.info("RUTA:::" + url + " ----- " + "Response:::" + respuesta.getBody());
                    throw new HandledException(String.valueOf(respuesta.getStatusCode()),
                            "Error en el servicio REST :: " + url
                            + "--->code:: " + respuesta.getStatusCode());
            }
        } catch (RestClientException r) {
            throw new HandledException("500", "Error cuando se intentaba consultar el servicio:: " + url + "--->" + r);
        }
    }

}
