/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.util;


import gt.gob.sat.fiscalizacion.consulta360.dto.UsuarioDto;
import gt.gob.sat.fiscalizacion.consulta360.srv.GeneralSrv;
import java.io.Serializable;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author dealonzo
 */
public class RequestFilter implements Filter{
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(RequestFilter.class);

    private GeneralSrv generalSrvImpl;
    
    /**
     * <p>Metodo de configuracion del filtro, se ejecuta al momento de asignarse memoria al objeto.</p>
     * 
     * @param pFilterConfig Objeto con los parametros de configuracion del filtro
     */
    @Override
    public void init(FilterConfig pFilterConfig){
        try {
            if (this.generalSrvImpl == null) {
                ApplicationContext appCtx = WebApplicationContextUtils.getWebApplicationContext(pFilterConfig.getServletContext());
                this.generalSrvImpl = appCtx.getBean(GeneralSrv.class);
            }
        } catch (Exception e) {
            LOG.error("Error al cargar la clase de servicios", e);
        }
    }

    /**
     * <p>Metodo principal del filtro. Se ejecuta cada vez que se realiza una peticion a cualquiera de los recursos que el filtro monitorea.</p>
     *
     * @param pRequest Peticion hecha por el usuario
     * @param pResponse Respuesta para el usuario
     * @param pChain Cadena que contiene todos los filtros que aplican a la peticion
     */
    @Override
    public void doFilter(ServletRequest pRequest, ServletResponse pResponse, FilterChain pChain) {
        try {
            this.leerUserAgent(pRequest);
            this.verificarUsuarioSesion(pRequest);
            pChain.doFilter(pRequest, pResponse);
        } catch (Exception e) {
            LOG.error("Error al procesar la peticion", e);
        }    
    }
    
    /**
     * <p>
     * Metodo de liberacion de recursos, se invoca al momento de eliminar el
     * objeto de memoria.
     * </p>
     */
    @Override
    public void destroy() {
        
    }
    
    /**
     * <p>
     * Este metodo se encarga de interpretar la cabecera de USER-AGENT para
     * crear un bean con la informacion del cliente utilizado para acceder a la
     * aplicacion.
     * </p>
     * @param pRequest Peticion hecha por el cliente
     */
    private void leerUserAgent(ServletRequest pRequest) {
        HttpSession session = ((HttpServletRequest) pRequest).getSession();
        ReadableUserAgent agent = (ReadableUserAgent) session.getAttribute(Constantes.ATTR_BEAN_USER_AGENT);
        
        if (agent == null) {    
            UserAgentStringParser uasParser=UADetectorServiceFactory.getResourceModuleParser();
            agent = uasParser.parse(((HttpServletRequest) pRequest).getHeader("user-agent"));
            session.setAttribute(Constantes.ATTR_BEAN_USER_AGENT, (Serializable) agent);
        }
    }
    
    /**
     * <p>
     * Este metodo se encarga de obtener el login del usuario que esta haciendo
     * las peticiones. En base a este login, el metodo completa toda la
     * informacion del perfil del usuario y crea un bean el cual es insertado en
     * la sesion.
     * </p>
     * @param pRequest Peticion hecha por el cliente
     */
    private void verificarUsuarioSesion(ServletRequest pRequest)  {    
        HttpSession session = ((HttpServletRequest) pRequest).getSession();
        
        UsuarioDto usuarioDto = (UsuarioDto) session.getAttribute("SAT_USUARIO_COMPLETO");
        
        String loginTitular = (String) session.getAttribute("SAT_SEGURIDAD_login");
        String loginDelegado = (String) session.getAttribute("SAT_SEGURIDAD_loginColaborador");

        String login = (loginDelegado!=null  && !loginDelegado.isEmpty()) ? loginDelegado : loginTitular;
        
        if (usuarioDto==null || !usuarioDto.getUsuario().equalsIgnoreCase(login)) {              
            session.setAttribute("SAT_USUARIO_COMPLETO", this.generalSrvImpl.obtenerDatosUsuario(login));
        }
    }
}