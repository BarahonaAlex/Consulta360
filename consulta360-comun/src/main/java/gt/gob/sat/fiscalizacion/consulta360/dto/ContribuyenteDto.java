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
public class ContribuyenteDto {

    private String nitContribuyente;
    private String nombreContribuyente;
    private String clasificacionContribuyente;
    private String regionContribuyente;

    /**
     * @return the nitContribuyente
     */
    public String getNitContribuyente() {
        return nitContribuyente;
    }

    /**
     * @param pNitContribuyente the nitContribuyente to set
     */
    public void setNitContribuyente(String pNitContribuyente) {
        this.nitContribuyente = pNitContribuyente;
    }

    /**
     * @return the nombreContribuyente
     */
    public String getNombreContribuyente() {
        return nombreContribuyente;
    }

    /**
     * @param pNombreContribuyente the nombreContribuyente to set
     */
    public void setNombreContribuyente(String pNombreContribuyente) {
        this.nombreContribuyente = pNombreContribuyente;
    }

    public String getClasificacionContribuyente() {
        return clasificacionContribuyente;
    }

    public void setClasificacionContribuyente(String clasificacionContribuyente) {
        this.clasificacionContribuyente = clasificacionContribuyente;
    }

    public String getRegionContribuyente() {
        return regionContribuyente;
    }

    public void setRegionContribuyente(String regionContribuyente) {
        this.regionContribuyente = regionContribuyente;
    }

}
