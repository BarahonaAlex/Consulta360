/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;




import gt.gob.sat.fiscalizacion.consulta360.srv.GeneralSrv;
import gt.gob.sat.fiscalizacion.consulta360.util.Sesion;
import gt.gob.sat.fiscalizacion.consulta360.util.NitValidador;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author dealonzo
 */
public abstract class AbstractUI {
    @Resource
    protected GeneralSrv generalSrvImpl;
    
    @Resource
    protected NitValidador nitValidador;
    
    @Resource
    protected Sesion sesion;

    public void infoMsg(String pMessage) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, pMessage, null));
    }

    public void warnMsg(String pMessage) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, pMessage, null));
    }

    public void errorMsg(String pMessage) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, pMessage, null));
    }
}