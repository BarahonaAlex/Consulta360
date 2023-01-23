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
public class BitacoraConsulta360Dto {
    private int paginaConsultada;
    private String nitConsultado;
    private String nitConsulto;
    private String ipConsulto;

    public BitacoraConsulta360Dto(int paginaConsultada, String nitConsultado, String nitConsulto, String ipConsulto) {
        this.paginaConsultada = paginaConsultada;
        this.nitConsultado = nitConsultado;
        this.nitConsulto = nitConsulto;
        this.ipConsulto = ipConsulto;
    }
    
    public int getPaginaConsultada() {
        return paginaConsultada;
    }

    public void setPaginaConsultada(int paginaConsultada) {
        this.paginaConsultada = paginaConsultada;
    }

    public String getNitConsultado() {
        return nitConsultado;
    }

    public void setNitConsultado(String nitConsultado) {
        this.nitConsultado = nitConsultado;
    }

    public String getNitConsulto() {
        return nitConsulto;
    }

    public void setNitConsulto(String nitConsulto) {
        this.nitConsulto = nitConsulto;
    }

    public String getIpConsulto() {
        return ipConsulto;
    }

    public void setIpConsulto(String ipConsulto) {
        this.ipConsulto = ipConsulto;
    }
}