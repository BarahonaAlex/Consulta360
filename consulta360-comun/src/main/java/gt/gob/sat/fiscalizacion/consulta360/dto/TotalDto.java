/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author dealonzo
 */
public class TotalDto {
    private String descripcion;
    private String cantidadAnioActualMenos4="0";
    private String cantidadAnioActualMenos3="0";
    private String cantidadAnioActualMenos2="0";
    private String cantidadAnioActualMenos1="0";
    private String cantidadAnioActual="0";

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidadAnioActualMenos4() {
        return cantidadAnioActualMenos4;
    }

    public void setCantidadAnioActualMenos4(String cantidadAnioActualMenos4) {
        this.cantidadAnioActualMenos4 = cantidadAnioActualMenos4;
    }

    public String getCantidadAnioActualMenos3() {
        return cantidadAnioActualMenos3;
    }

    public void setCantidadAnioActualMenos3(String cantidadAnioActualMenos3) {
        this.cantidadAnioActualMenos3 = cantidadAnioActualMenos3;
    }

    public String getCantidadAnioActualMenos2() {
        return cantidadAnioActualMenos2;
    }

    public void setCantidadAnioActualMenos2(String cantidadAnioActualMenos2) {
        this.cantidadAnioActualMenos2 = cantidadAnioActualMenos2;
    }

    public String getCantidadAnioActualMenos1() {
        return cantidadAnioActualMenos1;
    }

    public void setCantidadAnioActualMenos1(String cantidadAnioActualMenos1) {
        this.cantidadAnioActualMenos1 = cantidadAnioActualMenos1;
    }

    public String getCantidadAnioActual() {
        return cantidadAnioActual;
    }

    public void setCantidadAnioActual(String cantidadAnioActual) {
        this.cantidadAnioActual = cantidadAnioActual;
    }
    
    public String getTotal(){
        BigDecimal total=new BigDecimal(BigInteger.ZERO);
        
        total=total.add(new BigDecimal(cantidadAnioActualMenos4.replace(",", "")));
        total=total.add(new BigDecimal(cantidadAnioActualMenos3.replace(",", "")));
        total=total.add(new BigDecimal(cantidadAnioActualMenos2.replace(",", "")));
        total=total.add(new BigDecimal(cantidadAnioActualMenos1.replace(",", "")));
        total=total.add(new BigDecimal(cantidadAnioActual.replace(",", "")));
        
        return new DecimalFormat("###,##0").format(total.round(MathContext.UNLIMITED));
    }
    
    public String getPromedio(){
        BigDecimal total=new BigDecimal(BigInteger.ZERO);
        BigDecimal valoresSumados=new BigDecimal(BigInteger.ZERO);
        
        if(!cantidadAnioActualMenos4.equals("0.00")){
            total=total.add(new BigDecimal(cantidadAnioActualMenos4.replace(",", "")));
            valoresSumados=valoresSumados.add(new BigDecimal(BigInteger.ONE));
        }
        
        if(!cantidadAnioActualMenos3.equals("0.00")){
            total=total.add(new BigDecimal(cantidadAnioActualMenos3.replace(",", "")));
            valoresSumados=valoresSumados.add(new BigDecimal(BigInteger.ONE));
        }
        
        if(!cantidadAnioActualMenos2.equals("0.00")){
            total=total.add(new BigDecimal(cantidadAnioActualMenos2.replace(",", "")));
            valoresSumados=valoresSumados.add(new BigDecimal(BigInteger.ONE));
        }
        
        if(!cantidadAnioActualMenos1.equals("0.00")){
            total=total.add(new BigDecimal(cantidadAnioActualMenos1.replace(",", "")));
            valoresSumados=valoresSumados.add(new BigDecimal(BigInteger.ONE));
        }
        
        if(!cantidadAnioActual.equals("0.00")){
            total=total.add(new BigDecimal(cantidadAnioActual.replace(",", "")));
            valoresSumados=valoresSumados.add(new BigDecimal(BigInteger.ONE));
        }
        
        return new DecimalFormat("###,##0.00").format(valoresSumados.compareTo(BigDecimal.ZERO)==0 ? BigDecimal.ZERO : total.divide(valoresSumados, 2, RoundingMode.HALF_UP));
    }

    @Override
    public String toString() {
        return "TotalDto{" + "descripcion=" + descripcion + ", cantidadAnioActualMenos4=" + cantidadAnioActualMenos4 + ", cantidadAnioActualMenos3=" + cantidadAnioActualMenos3 + ", cantidadAnioActualMenos2=" + cantidadAnioActualMenos2 + ", cantidadAnioActualMenos1=" + cantidadAnioActualMenos1 + ", cantidadAnioActual=" + cantidadAnioActual + '}';
    }
    
    
}