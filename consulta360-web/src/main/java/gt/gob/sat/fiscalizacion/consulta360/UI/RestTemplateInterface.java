/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.HandledException;
import gt.gob.sat.fiscalizacion.consulta360.dto.restTemplateDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

/**
 *
 * @author rfsartio
 */
public interface RestTemplateInterface {

    public restTemplateDto restT(String url, HttpMethod metodo, HttpEntity<?> requestBody, Class tipo) throws HandledException;

}
