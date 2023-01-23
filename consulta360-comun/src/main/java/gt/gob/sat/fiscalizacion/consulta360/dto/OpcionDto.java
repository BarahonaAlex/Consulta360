/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gt.gob.sat.fiscalizacion.consulta360.dto;

/**
 *
 * @author dealonzo
 */
public class OpcionDto {
    private String icono;
    private String nombre;
    private String descripcion;
    private String pagina;
    
    public OpcionDto(){
        //Constructor default
    }

    public OpcionDto(String pIcono, String pNombre, String pDescripcion, String pPagina){
        this.icono=pIcono;
        this.nombre=pNombre;
        this.descripcion=pDescripcion;
        this.pagina=pPagina;
    }
    
    /**
     * @return the icono
     */
    public String getIcono() {
        return icono;
    }

    /**
     * @param pIcono the icono to set
     */
    public void setIcono(String pIcono) {
        this.icono = pIcono;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param pNombre the nombre to set
     */
    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param pDescripcion the descripcion to set
     */
    public void setDescripcion(String pDescripcion) {
        this.descripcion = pDescripcion;
    }

    /**
     * @return the pagina
     */
    public String getPagina() {
        return pagina;
    }

    /**
     * @param pPagina the pagina to set
     */
    public void setPagina(String pPagina) {
        this.pagina = pPagina;
    }
}