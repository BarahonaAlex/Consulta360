/*
 * Superintendencia de Administracion Tributaria (SAT)
 * Gerencia de Informatica
 * Departamento de Desarrollo de Sistemas
 */
package gt.gob.sat.fiscalizacion.consulta360.util;

import java.util.Locale;

/**
 *
 * @author Daniel Castillo (adcastic)
 * @since 08/12/2015
 * @version 1.0
 */
public class Constantes {

    /**
     * Mensaje de acceso de los metodos rest.
     */
    public static final String LOG_ACCESO = "Se ejecuto la peticion REST [{0}]";
//______________________________________________________________________________
    /**
     * Mensaje de error de los metodos rest.
     */
    public static final String LOG_RESPUESTA_ERROR = "La peticion REST [{0}] retorno la siguientes respuesta de error: [{1}]";
//______________________________________________________________________________
    /**
     * Nombre de la operacion para la bitacora de acceso.
     */
    public static final String BITACORA_OPERACION = "Consulta de Retenciones y Liberaciones";
//______________________________________________________________________________
    /**
     * Comentario para la bitacora de acceso.
     */
    public static final String BITACORA_COMENTARIO = "Acceso por medio del WS RESTful: retencion-liberacion-ws";
//______________________________________________________________________________
    /**
     * Filtro de hibernate duaAfpAuxiliarNit.
     */
    public static final String FILTRO_DUA_AFP_AUXILIAR_NIT = "duaAfpAuxiliarNit";
//______________________________________________________________________________
    /**
     * Filtro de hibernate duaAfpAuxiliarCodigoAntiguo.
     */
    public static final String FILTRO_DUA_AFP_AUXILIAR_CODIGO_ANTIGUO = "duaAfpAuxiliarCodigoAntiguo";
//______________________________________________________________________________
    /**
     * Filtro de hibernate adAgentesAsistentesNit.
     */
    public static final String FILTRO_AD_AGENTES_ASISTENTES_NIT = "adAgentesAsistentesNit";
//______________________________________________________________________________

    public static final String FORMATO_FECHA_CORTA = "dd/MM/yyyy";

    public static final String FORMATO_FECHA_TO_STRING = "dd/MM/yyyy HH:mm:ss";

    public static final Locale LOCALE = new Locale("es", "GT");

    /**
     * "A". Constante que identifica a los registros que estan
     * asociados/pertenecen o fueron creados en el modulo Audit.
     */
    public static final String M_AUDIT = "A";

    /**
     * "B". Constante que identifica a los registros que estan
     * asociados/pertenecen o fueron creados en el modulo Audien.
     */
    public static final String M_AUDIEN = "B";

    /**
     * "P". Constante que identifica a los registros que estan
     * asociados/pertenecen o fueron creados en liquidaciones previas.
     */
    public static final String M_LIQUIDACIONES_PREVIAS = "P";

    public static final String O_CREAR_LIQUIDACION = "crearLiquidacion";
    public static final String O_ELIMINAR_LIQUIDACION = "eliminarLiquidacion";
    /**
     * <p>
     * Nombre del atributo de sesion que contiene el bean con la informacion del
     * usuario.</p>
     */
    public static final String ATTR_BEAN_USUARIO = "SAT_USUARIO_COMPLETO";
//______________________________________________________________________________

    /**
     * <p>
     * Nombre del atributo de sesion que contiene el bean con la informacion del
     * user-agent.</p>
     */
    public static final String ATTR_BEAN_USER_AGENT = "SAT_USER_AGENT";
//______________________________________________________________________________

    /**
     * <p>
     * Nombre del atributo de sesion que contiene el login del usuario
     * conectado.</p>
     */
    public static final String ATTR_LOGIN = "SAT_SEGURIDAD_login";
//______________________________________________________________________________

    /**
     * <p>
     * Nombre del atributo de sesion que contiene el login del usuario conectado
     * en funcion de delegado.</p>
     */
    public static final String ATTR_LOGIN_DELEGADO = "SAT_SEGURIDAD_loginColaborador";

    /**
     * Constructor privado, evita que la clase pueda ser instanciada.
     */
    private Constantes() {
    }
}
