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
public class DetalleRiesgoDto {
    private String indice;
    private String significado;
    private String explicacionColoquial;

    
    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    public String getExplicacionColoquial() {
        return explicacionColoquial;
    }

    public void setExplicacionColoquial(String explicacionColoquial) {
        this.explicacionColoquial = explicacionColoquial;
    }
    
    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }
    
}