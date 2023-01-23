/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

/**
 *
 * @author adlabrigo
 */
public class PartesRelacionadasDto {
    private String nit;
    private String nombreContribuyente;
    private Integer cantidadConribAbogados;
    private Integer cantidadContribRepLeg;
    private Integer cantidadContribContador;

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombreContribuyente() {
        return nombreContribuyente;
    }

    public void setNombreContribuyente(String nombreContribuyente) {
        this.nombreContribuyente = nombreContribuyente;
    }

    public Integer getCantidadConribAbogados() {
        return cantidadConribAbogados;
    }

    public void setCantidadConribAbogados(Integer cantidadConribAbogados) {
        this.cantidadConribAbogados = cantidadConribAbogados;
    }

    public Integer getCantidadContribRepLeg() {
        return cantidadContribRepLeg;
    }

    public void setCantidadContribRepLeg(Integer cantidadContribRepLeg) {
        this.cantidadContribRepLeg = cantidadContribRepLeg;
    }

    public Integer getCantidadContribContador() {
        return cantidadContribContador;
    }

    public void setCantidadContribContador(Integer cantidadContribContador) {
        this.cantidadContribContador = cantidadContribContador;
    }
    
    
    
}
