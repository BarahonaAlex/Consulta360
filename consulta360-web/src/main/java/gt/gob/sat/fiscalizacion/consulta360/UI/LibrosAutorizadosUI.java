/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.BitacoraConsulta360Dto;
import gt.gob.sat.fiscalizacion.consulta360.dto.LibroAutorizadoDto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author dealonzo
 */
@Controller
@Scope("view")
public class LibrosAutorizadosUI extends AbstractUI implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LibrosAutorizadosUI.class);
    
    private String nit=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private List<LibroAutorizadoDto> listLibrosAutorizados;
    private List<LibroAutorizadoDto> listLibrosAutorizadosTemp;
    
    @PostConstruct
    public void inicializar(){
        try {
            if(nit==null || nit.isEmpty()){
                this.errorMsg("No se ha recibido el par\u00e1metro esperado");
                LOG.error("No se ha recibido el parametro esperado");
            }else{
                setListLibrosAutorizados(this.generalSrvImpl.obtenerLibrosAutorizadosByNit(nit));
                generalSrvImpl.registrarAcceso(new BitacoraConsulta360Dto(12,nit,sesion.getUsuarioDto().getNit(),sesion.getIpCliente()));
                if(listLibrosAutorizados==null || listLibrosAutorizados.isEmpty()){
                    warnMsg("No hay registros relacionados a la consulta");
                }
            }
        } catch (Exception e) {
            this.errorMsg("Error al cargar la p\u00e1gina de otros libros autorizados");
            LOG.error("Error al cargar la pagina de otros libros autorizados", e);
        }
    }
    
    /**
     * @return the listLibrosAutorizados
     */
    public List<LibroAutorizadoDto> getListLibrosAutorizados() {
        return this.listLibrosAutorizados;
    }

    /**
     * @param pListLibrosAutorizados the listLibrosAutorizados to set
     */
    public void setListLibrosAutorizados(List<LibroAutorizadoDto> pListLibrosAutorizados) {
        this.listLibrosAutorizados = pListLibrosAutorizados;
    }

    /**
     * @return the listLibrosAutorizadosTemp
     */
    public List<LibroAutorizadoDto> getListLibrosAutorizadosTemp() {
        return this.listLibrosAutorizadosTemp;
    }

    /**
     * @param pListLibrosAutorizadosTemp the listLibrosAutorizadosTemp to set
     */
    public void setListLibrosAutorizadosTemp(List<LibroAutorizadoDto> pListLibrosAutorizadosTemp) {
        this.listLibrosAutorizadosTemp = pListLibrosAutorizadosTemp;
    }
}