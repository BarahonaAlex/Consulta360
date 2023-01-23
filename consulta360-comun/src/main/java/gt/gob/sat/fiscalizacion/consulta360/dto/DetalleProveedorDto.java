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
public class DetalleProveedorDto {
    private String monto;
    private String nombramiento;
    private String expediente;

    /**
     * @return the monto
     */
    public String getMonto() {
        return monto;
    }

    /**
     * @param pMonto the monto to set
     */
    public void setMonto(String pMonto) {
        this.monto = pMonto;
    }

    /**
     * @return the nombramiento
     */
    public String getNombramiento() {
        return nombramiento;
    }

    /**
     * @param pNombramiento the nombramiento to set
     */
    public void setNombramiento(String pNombramiento) {
        this.nombramiento = pNombramiento;
    }

    /**
     * @return the expediente
     */
    public String getExpediente() {
        return expediente;
    }

    /**
     * @param pExpediente the expediente to set
     */
    public void setExpediente(String pExpediente) {
        this.expediente = pExpediente;
    }
}