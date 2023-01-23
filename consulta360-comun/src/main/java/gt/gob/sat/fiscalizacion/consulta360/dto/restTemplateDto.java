/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

import org.springframework.http.HttpStatus;

/**
 *
 * @author rfsartio
 */
public class restTemplateDto {

    private Object body;
    private HttpStatus codeResponse;

    public restTemplateDto() {
    }

    public restTemplateDto(Object body, HttpStatus codeResponse) {
        this.body = body;
        this.codeResponse = codeResponse;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public HttpStatus getCodeResponse() {
        return codeResponse;
    }

    public void setCodeResponse(HttpStatus codeResponse) {
        this.codeResponse = codeResponse;
    }
}
