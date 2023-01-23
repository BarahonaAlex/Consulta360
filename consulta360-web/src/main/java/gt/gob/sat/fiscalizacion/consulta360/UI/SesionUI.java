/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import java.io.Serializable;
import java.util.Date;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author dealonzo
 */
@Controller
@Scope("session")
public class SesionUI implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Date fechaInicioConsultaNombramientos;
    private Date fechaFinConsultaNombramientos;
    
    public Date getFechaInicioConsultaNombramientos() {
        return fechaInicioConsultaNombramientos;
    }

    public void setFechaInicioConsultaNombramientos(Date fechaInicioConsultaNombramientos) {
        this.fechaInicioConsultaNombramientos = fechaInicioConsultaNombramientos;
    }

    public Date getFechaFinConsultaNombramientos() {
        return fechaFinConsultaNombramientos;
    }

    public void setFechaFinConsultaNombramientos(Date fechaFinConsultaNombramientos) {
        this.fechaFinConsultaNombramientos = fechaFinConsultaNombramientos;
    }
}