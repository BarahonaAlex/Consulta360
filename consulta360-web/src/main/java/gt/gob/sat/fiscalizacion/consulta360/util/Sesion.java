package gt.gob.sat.fiscalizacion.consulta360.util;

import gt.gob.sat.fiscalizacion.consulta360.dto.UsuarioDto;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.uadetector.ReadableUserAgent;
import org.springframework.stereotype.Service;

/**
 * @author dealonzo
 */
@Service("sesion")
public class Sesion implements Serializable{
    public String getLogin() {
        String login;
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session.getAttribute("SAT_SEGURIDAD_login") != null) {
            login = session.getAttribute("SAT_SEGURIDAD_login").toString();

            login= (login != null && !login.equals("")) ? login.toUpperCase() : "Invitado";
        } else {
            login="Invitado";
        }
        
        return login;
    }
    
    public UsuarioDto getUsuarioDto() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        return (UsuarioDto) session.getAttribute("SAT_USUARIO_COMPLETO");
    }
        
    public boolean isExploradorIncompatible() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        ReadableUserAgent userAgentInfo = (ReadableUserAgent) session.getAttribute(Constantes.ATTR_BEAN_USER_AGENT);
        
        return userAgentInfo.getName().contains("IE");
    }

    public String getOpcion() {
        String opcion;
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session.getAttribute("SAT_SEGURIDAD_opcion") != null) {
            opcion = session.getAttribute("SAT_SEGURIDAD_opcion").toString();

            opcion=(opcion != null && !opcion.equals("")) ? "Opcion: " + opcion : "Invitado";
        } else {
            opcion="Invitado";
        }
        
        return opcion;
    }

    public String getSesion() {
        String sesion;
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session.getAttribute("SAT_SEGURIDAD_sessionGlobal") != null) {
            sesion = session.getAttribute("SAT_SEGURIDAD_sessionGlobal").toString();
            
            sesion=(sesion != null && !sesion.equals("")) ? "Sesion: " + sesion : "Invitado";
        } else {
            sesion="Invitado";
        }

        return sesion;
    }

    public String getTicket() {
        String ticket;
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session.getAttribute("SAT_SEGURIDAD_ticket") != null) {
            ticket = session.getAttribute("SAT_SEGURIDAD_ticket").toString();

            ticket = (ticket != null && !ticket.equals("")) ? ticket : "Invitado";
        } else {
            ticket = "Invitado";
        }
        
        return ticket;
    }
    
    public String getIpCliente(){
        HttpServletRequest request=(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getHeader("rlnclientipaddr")==null ? request.getRemoteAddr() : request.getHeader("rlnclientipaddr");
    }
    
    
    public String geRoles() {
        String rol;
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session.getAttribute("SAT_SEGURIDAD_roles") != null) {
           rol = session.getAttribute("SAT_SEGURIDAD_roles").toString();

           rol = (rol != null && ! rol.equals("")) ? rol : "Invitado";
        } else {
            rol = "Invitado";
        }
        
        return rol;
    }
}