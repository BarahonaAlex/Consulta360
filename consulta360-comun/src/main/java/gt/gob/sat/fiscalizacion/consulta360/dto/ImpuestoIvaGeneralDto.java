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
public class ImpuestoIvaGeneralDto {
    
    private String nombreRegimen;
    private String fechaCambioRegimen;
    private String fechaAdicionAfiliacionIVA;

    public String getNombreRegimen() {
        return nombreRegimen;
    }

    public void setNombreRegimen(String nombreRegimen) {
        this.nombreRegimen = nombreRegimen;
    }

    public String getFechaCambioRegimen() {
        return fechaCambioRegimen;
    }

    public void setFechaCambioRegimen(String fechaCambioRegimen) {
        this.fechaCambioRegimen = fechaCambioRegimen;
    }

    public String getFechaAdicionAfiliacionIVA() {
        return fechaAdicionAfiliacionIVA;
    }

    public void setFechaAdicionAfiliacionIVA(String fechaAdicionAfiliacionIVA) {
        this.fechaAdicionAfiliacionIVA = fechaAdicionAfiliacionIVA;
    }
    
    
    
    
    
}
