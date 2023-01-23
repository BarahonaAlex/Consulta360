package gt.gob.sat.fiscalizacion.consulta360.modelo;
// Generated 20/06/2022 12:18:29 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CifClasesTipologias generated by hbm2java
 */
@Entity
@Table(name="CIF_CLASES_TIPOLOGIAS"
    ,schema="SAT_FISCALIZACION"
)
public class CifClasesTipologias  implements java.io.Serializable {


     private BigDecimal idTipologia;
     private String tipologia;
     private String descripcion;
     private Date fechaCreacion;

    public CifClasesTipologias() {
    }

	
    public CifClasesTipologias(BigDecimal idTipologia, String tipologia) {
        this.idTipologia = idTipologia;
        this.tipologia = tipologia;
    }
    public CifClasesTipologias(BigDecimal idTipologia, String tipologia, String descripcion, Date fechaCreacion) {
       this.idTipologia = idTipologia;
       this.tipologia = tipologia;
       this.descripcion = descripcion;
       this.fechaCreacion = fechaCreacion;
    }
   
     @Id 

    
    @Column(name="ID_TIPOLOGIA", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getIdTipologia() {
        return this.idTipologia;
    }
    
    public void setIdTipologia(BigDecimal idTipologia) {
        this.idTipologia = idTipologia;
    }

    
    @Column(name="TIPOLOGIA", nullable=false, length=200)
    public String getTipologia() {
        return this.tipologia;
    }
    
    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    
    @Column(name="DESCRIPCION", length=500)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_CREACION", length=7)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }




}

