/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gt.gob.sat.fiscalizacion.consulta360.dto;

import java.sql.Blob;

/**
 *
 * @author dealonzo
 */
public class PapelDto {
    private String categoriaFormato;
    private String codigoFormato;
    private String nombreFormato;
    private String fechaIngreso;
    private Blob binaryFile;
    private String nameFile;

    /**
     * @return the categoriaFormato
     */
    public String getCategoriaFormato() {
        return categoriaFormato;
    }

    /**
     * @param pCategoriaFormato the categoriaFormato to set
     */
    public void setCategoriaFormato(String pCategoriaFormato) {
        this.categoriaFormato = pCategoriaFormato;
    }

    /**
     * @return the codigoFormato
     */
    public String getCodigoFormato() {
        return codigoFormato;
    }

    /**
     * @param pCodigoFormato the codigoFormato to set
     */
    public void setCodigoFormato(String pCodigoFormato) {
        this.codigoFormato = pCodigoFormato;
    }

    /**
     * @return the nombreFormato
     */
    public String getNombreFormato() {
        return nombreFormato;
    }

    /**
     * @param pNombreFormato the nombreFormato to set
     */
    public void setNombreFormato(String pNombreFormato) {
        this.nombreFormato = pNombreFormato;
    }

    /**
     * @return the fechaIngreso
     */
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param pFechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(String pFechaIngreso) {
        this.fechaIngreso = pFechaIngreso;
    }

    /**
     * @return the binaryFile
     */
    public Blob getBinaryFile() {
        return binaryFile;
    }

    /**
     * @param pBinaryFile the binaryFile to set
     */
    public void setBinaryFile(Blob pBinaryFile) {
        this.binaryFile = pBinaryFile;
    }

    /**
     * @return the nameFile
     */
    public String getNameFile() {
        return nameFile;
    }

    /**
     * @param pNameFile the nameFile to set
     */
    public void setNameFile(String pNameFile) {
        this.nameFile = pNameFile;
    }
}