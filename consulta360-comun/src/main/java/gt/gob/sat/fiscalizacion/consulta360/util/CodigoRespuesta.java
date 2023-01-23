/*
 * Superintendencia de Administracion Tributaria (SAT)
 * Gerencia de Informatica
 * Departamento de Desarrollo de Sistemas
 */
package gt.gob.sat.fiscalizacion.consulta360.util;

import gt.gob.sat.arquitectura.fwk.util.CodigoRespuestaFwk;
import gt.gob.sat.arquitectura.fwk.util.ConstantesFwk;

/**
 *
 * @author Daniel Castillo (adcastic)
 * @since 08/12/2015
 * @version 1.0
 */
public enum CodigoRespuesta implements CodigoRespuestaFwk {
    
    OPERACION_EXITOSA ("La operación fue realizada exitosamente", ConstantesFwk.EXITO, 0),
    ERROR_DESCONOCIDO ("Error desconocido.", ConstantesFwk.ERROR, 1000),
    ERROR_ACCESO_BASE_DE_DATOS ("Error inesperado al acceder a la base de datos.", ConstantesFwk.ERROR, 1001),
    ERROR_SIN_CAMPOS_BUSQUEDA ("Se debe de ingresar por lo menos uno de los parámetros de búsqueda.", ConstantesFwk.ERROR, 1002),
    ERROR_SIN_RESULTADOS ("No se encontró información para los filtros de búsqueda ingresados.", ConstantesFwk.ERROR, 1003);

    private final String descripcion;
    private final int codigo;
    private final String tipo;

    private CodigoRespuesta(String pDescripcion, String pTipo, int pCodigo) {
        this.descripcion = pDescripcion;
        this.codigo = pCodigo;
        this.tipo = pTipo;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return this.tipo.concat(": ").concat(String.valueOf(this.codigo)).concat("-").concat(this.descripcion);
    }
}
