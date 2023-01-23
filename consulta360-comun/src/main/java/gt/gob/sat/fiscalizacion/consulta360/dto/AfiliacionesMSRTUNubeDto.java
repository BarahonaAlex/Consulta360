/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

/**
 *
 * @author rfsartio
 */
public class AfiliacionesMSRTUNubeDto {

    private String nombreImpuesto;
    private String codigoRegimenDesc;
    private String fechaCreacion;
    private String estadoDesc;
    private String fechaCambio;

    public String getNombreImpuesto() {
        return nombreImpuesto;
    }

    public void setNombreImpuesto(String nombreImpuesto) {
        this.nombreImpuesto = nombreImpuesto;
    }

    public String getCodigoRegimenDesc() {
        return codigoRegimenDesc;
    }

    public void setCodigoRegimenDesc(String codigoRegimenDesc) {
        this.codigoRegimenDesc = codigoRegimenDesc;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstadoDesc() {
        return estadoDesc;
    }

    public void setEstadoDesc(String estadoDesc) {
        this.estadoDesc = estadoDesc;
    }

    public String getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(String fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

}
