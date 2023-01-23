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
public class ImpuestoIsrDto {
    
    private String nombreRegimenISR;
    private String fechaCambioRegimenISR;
    private String fechaAdicionAfiliacionISR;

    public String getNombreRegimenISR() {
        return nombreRegimenISR;
    }

    public void setNombreRegimenISR(String nombreRegimenISR) {
        this.nombreRegimenISR = nombreRegimenISR;
    }

    public String getFechaCambioRegimenISR() {
        return fechaCambioRegimenISR;
    }

    public void setFechaCambioRegimenISR(String fechaCambioRegimenISR) {
        this.fechaCambioRegimenISR = fechaCambioRegimenISR;
    }

    public String getFechaAdicionAfiliacionISR() {
        return fechaAdicionAfiliacionISR;
    }

    public void setFechaAdicionAfiliacionISR(String fechaAdicionAfiliacionISR) {
        this.fechaAdicionAfiliacionISR = fechaAdicionAfiliacionISR;
    }

    

    
    
    
}
