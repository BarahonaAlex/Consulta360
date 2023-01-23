/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;

import gt.gob.sat.fiscalizacion.consulta360.dto.ContribuyenteDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.IndicadoresEncabezadoCalculadosDto;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author adlabrigo
 */
@Controller
@Scope("view")
public class ConsultaDetalleIndicadoressUI extends AbstractUI implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ConsultaDetalleIndicadoressUI.class);
    
     //Variables búsqueda
    private String nit = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pNit");
    private String nitPrueba;
    private String nombreContribuyente;
    private String tipoIndicador;
    private String sectorEconomico;
    private String color;
    private String region;
    private String anio;
    private String clasificacion;
    
    //Listas
    private List<IndicadoresEncabezadoCalculadosDto> listaContribuyentes;
    private List<IndicadoresEncabezadoCalculadosDto> indicadoresDetalle;
    
    
    
    
    @PostConstruct
    public void inicializar() {
        
    
}
     public void buscar() {
         
        if (nit == "" && tipoIndicador == "" && sectorEconomico == "" && color == "" && region == "" && anio == "" && clasificacion == "") {

            this.listaContribuyentes = this.generalSrvImpl.obtenerListaVaciaIndicadores(nit, tipoIndicador, sectorEconomico, color, region, anio, clasificacion);
            if (listaContribuyentes.isEmpty()) {
                this.warnMsg("Debes ingresar un filtro como mínimo para realizar la búsqueda. Favor verifique.");

            }
        } else {

            if (nit != "" && tipoIndicador != "" && sectorEconomico != "" && color != "" && region != "" && anio != "" && clasificacion != "") {

                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitIndicadorSectorColorRegionAnioClasificacion(nit, tipoIndicador, sectorEconomico, color, region, anio, clasificacion);
          
       
                if (listaContribuyentes.isEmpty()) {
                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                }

            } else {

                if (nit != "" && tipoIndicador != "" && sectorEconomico != "" && color != "" && region != "" && anio != "") {

                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitIndicadorSectorColorRegionAnio(nit, tipoIndicador, sectorEconomico, color, region, anio);
                    
            
                    if (listaContribuyentes.isEmpty()) {
                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                    }

                } else {

                    if (tipoIndicador != "" && sectorEconomico != "" && color != "" && region != "" && anio != "" && clasificacion != "") {

                        this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresIndicadorSectorColorRegionAnioClasificacion(tipoIndicador, sectorEconomico, color, region, anio, clasificacion);

    
                        if (listaContribuyentes.isEmpty()) {
                            this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                        }

                    } else {

                        if (nit != "" && tipoIndicador != "" && color != "" && anio != "" && sectorEconomico != "" && region != "") {

                            this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitIndicadorColorAnioSectorRegion(nit, tipoIndicador, color, anio, sectorEconomico, region);
                            this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                    
                            if (listaContribuyentes.isEmpty()) {
                                this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                            }

                        } else {

                            if (nit != "" && tipoIndicador != "" && sectorEconomico != "" && color != "" && region != "") {

                                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitIndicadorSectorColorRegion(nit, tipoIndicador, sectorEconomico, color, region);
                                this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                               
                                if (listaContribuyentes.isEmpty()) {
                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                }
                            } else {

                                if (nit != "" && tipoIndicador != "" && color != "" && anio != "" && sectorEconomico != "") {

                                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitIndicadorColorAnioSector(nit, tipoIndicador, color, anio, sectorEconomico);
                                    this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                                    
                                    if (listaContribuyentes.isEmpty()) {
                                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                    }
                                } else {

                                    if (tipoIndicador != "" && color != "" && anio != "" && sectorEconomico != "" && region != "") {

                                        this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresIndicadorColorAnioSecRegion(tipoIndicador, color, anio, sectorEconomico, region);

                                      

                                        if (listaContribuyentes.isEmpty()) {
                                            this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                        }
                                    } else {

                                        if (tipoIndicador != "" && color != "" && anio != "" && sectorEconomico != "" && clasificacion != "") {

                                            this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresIndicadorColorAnioSecClasificacion(tipoIndicador, color, anio, sectorEconomico, clasificacion);

                                            

                                            if (listaContribuyentes.isEmpty()) {
                                                this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                            }
                                        } else {

                                            if (nit != "" && tipoIndicador != "" && sectorEconomico != "" && color != "") {

                                                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitIndicadorSectorColor(nit, tipoIndicador, sectorEconomico, color);
                                                this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                                                

                                                if (listaContribuyentes.isEmpty()) {
                                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                }
                                            } else {

                                                if (nit != "" && tipoIndicador != "" && color != "" && anio != "") {

                                                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitIndicadorColorAnio(nit, tipoIndicador, color, anio);
                                                    this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                                                    

                                                    if (listaContribuyentes.isEmpty()) {
                                                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                    }
                                                } else {

                                                    if (tipoIndicador != "" && color != "" && anio != "" && sectorEconomico != "") {

                                                        this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresIndicadorColorAnioSect(tipoIndicador, color, anio, sectorEconomico);

                                                        

                                                        if (listaContribuyentes.isEmpty()) {
                                                            this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                        }
                                                    } else {

                                                        if (tipoIndicador != "" && color != "" && anio != "" && region != "") {

                                                            this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresIndicadorColorAnioRegion(tipoIndicador, color, anio, region);

                                                            

                                                            if (listaContribuyentes.isEmpty()) {
                                                                this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                            }
                                                        } else {

                                                            if (tipoIndicador != "" && color != "" && anio != "" && clasificacion != "") {

                                                                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresIndicadorColorAnioClasificacion(tipoIndicador, color, anio, clasificacion);

                                                                

                                                                if (listaContribuyentes.isEmpty()) {
                                                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                }
                                                            } else {

                                                                if (nit != "" && tipoIndicador != "" && sectorEconomico != "") {

                                                                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitIndicadorSector(nit, tipoIndicador, sectorEconomico);
                                                                    this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                                                                   
                                                                    if (listaContribuyentes.isEmpty()) {
                                                                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                    }
                                                                } else {

                                                                    if (nit != "" && tipoIndicador != "" && color != "") {

                                                                        this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitIndicadorColor(nit, tipoIndicador, color);
                                                                        this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                                                                       
                                                                        if (listaContribuyentes.isEmpty()) {
                                                                            this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                        }

                                                                    } else {

                                                                        if (nit != "" && color != "" && anio != "") {

                                                                            this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitColorAnio(nit, color, anio);
                                                                            this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                                                                            
                                                                            if (listaContribuyentes.isEmpty()) {
                                                                                this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                            }

                                                                        } else {

                                                                            if (tipoIndicador != "" && color != "" && anio != "") {

                                                                                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresTipoIndColorAnio(tipoIndicador, color, anio);

                                                                               
                                                                                if (listaContribuyentes.isEmpty()) {
                                                                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                }

                                                                            } else {

                                                                                if (tipoIndicador != "" && color != "" && sectorEconomico != "") {

                                                                                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresTipoIndColorSector(tipoIndicador, color, sectorEconomico);

                                                                                    
                                                                                    if (listaContribuyentes.isEmpty()) {
                                                                                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                    }

                                                                                } else {

                                                                                    if (tipoIndicador != "" && color != "" && region != "") {

                                                                                        this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresTipoIndColorRegion(tipoIndicador, color, region);

                                                                                     
                                                                                        if (listaContribuyentes.isEmpty()) {
                                                                                            this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                        }

                                                                                    } else {

                                                                                        if (tipoIndicador != "" && color != "" && clasificacion != "") {

                                                                                            this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresTipoIndColorClasificacion(tipoIndicador, color, clasificacion);

                                                                                            
                                                                                            if (listaContribuyentes.isEmpty()) {
                                                                                                this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                            }

                                                                                        } else {

                                                                                            if (color != "" && anio != "" && sectorEconomico != "") {

                                                                                                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresColorAnioSector(color, anio, sectorEconomico);

                                                                                               
                                                                                                if (listaContribuyentes.isEmpty()) {
                                                                                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                }

                                                                                            } else {

                                                                                                if (color != "" && anio != "" && region != "") {

                                                                                                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresColorAnioRegion(color, anio, region);

                                                                                                   
                                                                                                    if (listaContribuyentes.isEmpty()) {
                                                                                                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                    }

                                                                                                } else {

                                                                                                    if (color != "" && anio != "" && clasificacion != "") {

                                                                                                        this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresColorAnioClasificacion(color, anio, clasificacion);

                                                                                                       
                                                                                                        if (listaContribuyentes.isEmpty()) {
                                                                                                            this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                        }

                                                                                                    } else {

                                                                                                        if (anio != "" && sectorEconomico != "" && region != "") {

                                                                                                            this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresAnioSectorRegion(anio, sectorEconomico, region);

                                                                                                           
                                                                                                            if (listaContribuyentes.isEmpty()) {
                                                                                                                this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                            }

                                                                                                        } else {

                                                                                                            if (anio != "" && sectorEconomico != "" && clasificacion != "") {

                                                                                                                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresAnioSectorClasificacion(anio, sectorEconomico, clasificacion);

                                                                                                               
                                                                                                                if (listaContribuyentes.isEmpty()) {
                                                                                                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                }

                                                                                                            } else {

                                                                                                                if (sectorEconomico != "" && region != "" && clasificacion != "") {

                                                                                                                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresSectorRegionClasificacion(sectorEconomico, region, clasificacion);

                                                                                                                    
                                                                                                                    if (listaContribuyentes.isEmpty()) {
                                                                                                                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                    }

                                                                                                                } else {

                                                                                                                    if (nit != "" && tipoIndicador != "") {

                                                                                                                        this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitTipoIndicador(nit, tipoIndicador);
                                                                                                                        this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                                                                                                                        
                                                                                                                        if (listaContribuyentes.isEmpty()) {
                                                                                                                            this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                        }

                                                                                                                    } else {

                                                                                                                        if (nit != "" && color != "") {

                                                                                                                            this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitColor(nit, color);
                                                                                                                            this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                                                                                                                            
                                                                                                                            if (listaContribuyentes.isEmpty()) {
                                                                                                                                this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                            }

                                                                                                                        } else {

                                                                                                                            if (nit != "" && anio != "") {

                                                                                                                                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitAnio(nit, anio);
                                                                                                                                this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                                                                                                                                
                                                                                                                                if (listaContribuyentes.isEmpty()) {
                                                                                                                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                }

                                                                                                                            } else {

                                                                                                                                if (nit != "" && sectorEconomico != "") {

                                                                                                                                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitSector(nit, sectorEconomico);
                                                                                                                                    this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                                                                                                                                  
                                                                                                                                    if (listaContribuyentes.isEmpty()) {
                                                                                                                                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                    }

                                                                                                                                } else {

                                                                                                                                    if (nit != "" && region != "") {

                                                                                                                                        this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitRegion(nit, region);
                                                                                                                                        this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                                                                                                                                        
                                                                                                                                        if (listaContribuyentes.isEmpty()) {
                                                                                                                                            this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                        }

                                                                                                                                    } else {

                                                                                                                                        if (nit != "" && clasificacion != "") {

                                                                                                                                            this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresNitClasificacion(nit, clasificacion);
                                                                                                                                            this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                                                                                                                                            
                                                                                                                                            if (listaContribuyentes.isEmpty()) {
                                                                                                                                                this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                            }

                                                                                                                                        } else {

                                                                                                                                            if (tipoIndicador != "" && color != "") {

                                                                                                                                                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresTipoIndicadorColor(tipoIndicador, color);

                                                                                                                                              
                                                                                                                                                if (listaContribuyentes.isEmpty()) {
                                                                                                                                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                }

                                                                                                                                            } else {
                                                                                                                                                if (tipoIndicador != "" && anio != "") {

                                                                                                                                                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresTipoIndicadorAnio(tipoIndicador, anio);

                                                                                                                                                    
                                                                                                                                                    if (listaContribuyentes.isEmpty()) {
                                                                                                                                                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                    }

                                                                                                                                                } else {

                                                                                                                                                    if (tipoIndicador != "" && sectorEconomico != "") {

                                                                                                                                                        this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresTipoIndicadorSector(tipoIndicador, sectorEconomico);

                                                                                                                                                        
                                                                                                                                                        if (listaContribuyentes.isEmpty()) {
                                                                                                                                                            this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                        }

                                                                                                                                                    } else {

                                                                                                                                                        if (tipoIndicador != "" && region != "") {

                                                                                                                                                            this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresTipoIndicadorRegion(tipoIndicador, region);

                                                                                                                                                            
                                                                                                                                                            if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                            }

                                                                                                                                                        } else {

                                                                                                                                                            if (tipoIndicador != "" && clasificacion != "") {

                                                                                                                                                                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresTipoIndicadorClasificacion(tipoIndicador, clasificacion);

                                                                                                                                                                
                                                                                                                                                                if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                }

                                                                                                                                                            } else {

                                                                                                                                                                if (color != "" && anio != "") {

                                                                                                                                                                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresColorAnio(color, anio);

                                                                                                                                                                   
                                                                                                                                                                    if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                    }

                                                                                                                                                                } else {

                                                                                                                                                                    if (color != "" && sectorEconomico != "") {

                                                                                                                                                                        this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresColorSector(color, sectorEconomico);

                                                                                                                                                                        
                                                                                                                                                                        if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                            this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                        }

                                                                                                                                                                    } else {

                                                                                                                                                                        if (color != "" && region != "") {

                                                                                                                                                                            this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresColorRegion(color, region);

                                                                                                                                                                            
                                                                                                                                                                            if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                            }

                                                                                                                                                                        } else {

                                                                                                                                                                            if (color != "" && clasificacion != "") {

                                                                                                                                                                                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresColorClasificacion(color, clasificacion);

                                                                                                                                                                                
                                                                                                                                                                                if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                }

                                                                                                                                                                            } else {

                                                                                                                                                                                if (anio != "" && sectorEconomico != "") {

                                                                                                                                                                                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresAnioSector(anio, sectorEconomico);

                                                                                                                                                                                    
                                                                                                                                                                                    if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                    }

                                                                                                                                                                                } else {

                                                                                                                                                                                    if (anio != "" && region != "") {

                                                                                                                                                                                        this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresAnioRegion(anio, region);

                                                                                                                                                                                        
                                                                                                                                                                                        if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                            this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                        }

                                                                                                                                                                                    } else {

                                                                                                                                                                                        if (anio != "" && clasificacion != "") {

                                                                                                                                                                                            this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresAnioClasificacion(anio, clasificacion);

                                                                                                                                                                                            
                                                                                                                                                                                            if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                                this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                            }

                                                                                                                                                                                        } else {

                                                                                                                                                                                            if (sectorEconomico != "" && region != "") {

                                                                                                                                                                                                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresSectorRegion(sectorEconomico, region);

                                                                                                                                                                                                
                                                                                                                                                                                                if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                                }

                                                                                                                                                                                            } else {

                                                                                                                                                                                                if (sectorEconomico != "" && clasificacion != "") {

                                                                                                                                                                                                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresSectorClasificacion(sectorEconomico, clasificacion);

                                                                                                                                                                                                    
                                                                                                                                                                                                    if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                                    }

                                                                                                                                                                                                } else {

                                                                                                                                                                                                    if (region != "" && clasificacion != "") {

                                                                                                                                                                                                        this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresRegionClasificacion(region, clasificacion);

                                                                                                                                                                                                      
                                                                                                                                                                                                        if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                                            this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                                        }

                                                                                                                                                                                                    } else {

                                                                                                                                                                                                        if (!"".equals(tipoIndicador)) {

                                                                                                                                                                                                            this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresPorTipoIndicador(tipoIndicador);

                                                                                                                                                                                                            

                                                                                                                                                                                                            if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                                                this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                                            }
                                                                                                                                                                                                        } else {

                                                                                                                                                                                                            if (!"".equals(nit)) {

                                                                                                                                                                                                                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadores(nit);
                                                                                                                                                                                                                this.setNombreContribuyente(this.generalSrvImpl.obtenerNombre(nit));
                                                                                                                                                                                                               
                                                                                                                                                                                                                if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                                                }
                                                                                                                                                                                                            } else {

                                                                                                                                                                                                                if (!"".equals(color)) {

                                                                                                                                                                                                                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresPorColor(color);

                                                                                                                                                                                                            

                                                                                                                                                                                                                    if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                                                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                                                    }
                                                                                                                                                                                                                } else {

                                                                                                                                                                                                                    if (!"".equals(anio)) {

                                                                                                                                                                                                                        this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresPorAnio(anio);

                                                                                                                                                                                                                       

                                                                                                                                                                                                                        if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                                                            this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    } else {

                                                                                                                                                                                                                        if (!"".equals(sectorEconomico)) {

                                                                                                                                                                                                                            this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresPorSectorEconomico(sectorEconomico);

                                                                                                                                                                                                                            

                                                                                                                                                                                                                            if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                                                                this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        } else {

                                                                                                                                                                                                                            if (!"".equals(region)) {

                                                                                                                                                                                                                                this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresPorRegion(region);

                                                                                                                                                                                                                                

                                                                                                                                                                                                                                if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                                                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                                                                }
                                                                                                                                                                                                                            } else {

                                                                                                                                                                                                                                if (!"".equals(clasificacion)) {

                                                                                                                                                                                                                                    this.listaContribuyentes = this.generalSrvImpl.obtenerListaIndicadoresPorClasificacion(clasificacion);
                                                                                                                                                                                                                                 

                                                                                                                                                                                                                                    if (listaContribuyentes.isEmpty()) {
                                                                                                                                                                                                                                        this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");

                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                    this.warnMsg("Los filtros de busqueda ingresados no generaron datos que mostrar");
                                                                                                                                                                                                                                }

                                                                                                                                                                                                                            }

                                                                                                                                                                                                                        }

                                                                                                                                                                                                                    }

                                                                                                                                                                                                                }
                                                                                                                                                                                                            }

                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }

                                                                                                                                                            }
                                                                                                                                                        }

                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
 

    }
     
     
     public void limpiar() {

        this.setNit("");
        this.setNombreContribuyente("");
        this.setColor("");
        this.setSectorEconomico("");
        this.setAnio("");
        this.setClasificacion("");
        this.setTipoIndicador("");
        this.setRegion("");

    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNitPrueba() {
        return nitPrueba;
    }

    public void setNitPrueba(String nitPrueba) {
        this.nitPrueba = nitPrueba;
    }

    public String getNombreContribuyente() {
        return nombreContribuyente;
    }

    public void setNombreContribuyente(String nombreContribuyente) {
        this.nombreContribuyente = nombreContribuyente;
    }

    public String getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(String tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }

    public String getSectorEconomico() {
        return sectorEconomico;
    }

    public void setSectorEconomico(String sectorEconomico) {
        this.sectorEconomico = sectorEconomico;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public List<IndicadoresEncabezadoCalculadosDto> getListaContribuyentes() {
        return listaContribuyentes;
    }

    public void setListaContribuyentes(List<IndicadoresEncabezadoCalculadosDto> listaContribuyentes) {
        this.listaContribuyentes = listaContribuyentes;
    }

    public List<IndicadoresEncabezadoCalculadosDto> getIndicadoresDetalle() {
        return indicadoresDetalle;
    }

    public void setIndicadoresDetalle(List<IndicadoresEncabezadoCalculadosDto> indicadoresDetalle) {
        this.indicadoresDetalle = indicadoresDetalle;
    }
    
    public void exportare() throws IOException {
       

        HSSFWorkbook libro = new HSSFWorkbook();
        HSSFSheet hoja = libro.createSheet("HOJA1");

        /*FONT STYLE*/
        HSSFFont headerFont = libro.createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        /*COLOR FONT*/
        HSSFPalette palette = libro.getCustomPalette();
        HSSFColor myColor = palette.findSimilarColor(50, 135, 155);
        short palIndex = myColor.getIndex();

        /*ROW STYLE*/
        HSSFCellStyle rowStyle = hoja.getWorkbook().createCellStyle();
        rowStyle.setFillForegroundColor(palIndex);
        rowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        rowStyle.setFont(headerFont);

        /*CELL STYLE*/
        HSSFCellStyle cellStyle = hoja.getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        
        /*FORMATO MONEDA PARA CELDA*/
        DataFormat format = libro.createDataFormat();
        HSSFCellStyle styleMoneda = libro.createCellStyle();
        styleMoneda.setDataFormat(format.getFormat("Q #,##0.00; Q (#,##0.00)"));

        hoja.setColumnWidth(0, 10000);
        
        /*ENCABEZADO PRINCIPAL DE LA HOJA DE EXCEL*/
        HSSFRow filaEnc1 = hoja.createRow(1);

        HSSFCell celdaEnc1 = filaEnc1.createCell(0);
        celdaEnc1.setCellValue("REPORTE INDICADORES DETALLE");

     
        /*---------------------------------------------------*/
       

        /*ENCABEZADOS DE LAS COLUMNAS EN LA HOJA DE EXCEL*/
        HSSFRow filaH = hoja.createRow(7);

        HSSFCell celdaH0 = filaH.createCell(0);
        celdaH0.setCellStyle(rowStyle);
        celdaH0.setCellValue("NIT");

        HSSFCell celdaH1 = filaH.createCell(1);
        celdaH1.setCellStyle(rowStyle);
        celdaH1.setCellValue("NOMBRE CONTRIBUYENTE");

        HSSFCell celdaH2 = filaH.createCell(2);
        celdaH2.setCellStyle(rowStyle);
        celdaH2.setCellValue("STATUS");

        HSSFCell celdaH3 = filaH.createCell(3);
        celdaH3.setCellStyle(rowStyle);
        celdaH3.setCellValue("SECTOR ECONÓMICO");

        HSSFCell celdaH4 = filaH.createCell(4);
        celdaH4.setCellStyle(rowStyle);
        celdaH4.setCellValue("REGIÓN");

        HSSFCell celdaH5 = filaH.createCell(5);
        celdaH5.setCellStyle(rowStyle);
        celdaH5.setCellValue("CLASIFICACIÓN");

        HSSFCell celdaH6 = filaH.createCell(6);
        celdaH6.setCellStyle(rowStyle);
        celdaH6.setCellValue("PERSONERIA");

        HSSFCell celdaH7 = filaH.createCell(7);
        celdaH7.setCellStyle(rowStyle);
        celdaH7.setCellValue("AFILIACIÓN IVA");

        HSSFCell celdaH8 = filaH.createCell(8);
        celdaH8.setCellStyle(rowStyle);
        celdaH8.setCellValue("FECHA IVA");

        HSSFCell celdaH9 = filaH.createCell(9);
        celdaH9.setCellStyle(rowStyle);
        celdaH9.setCellValue("AFILIACIÓN ISR");

        HSSFCell celdaH10 = filaH.createCell(10);
        celdaH10.setCellStyle(rowStyle);
        celdaH10.setCellValue("FECHA ISR");

        HSSFCell celdaH11 = filaH.createCell(11);
        celdaH11.setCellStyle(rowStyle);
        celdaH11.setCellValue("ESTABLECIMIENTOS ACTIVOS");

        HSSFCell celdaH12 = filaH.createCell(12);
        celdaH12.setCellStyle(rowStyle);
        celdaH12.setCellValue("ESTABLECIMIENTOS INACTIVOS ");
        
        HSSFCell celdaH13 = filaH.createCell(13);
        celdaH13.setCellStyle(rowStyle);
        celdaH13.setCellValue("PLAN OPERATIVO ACTUAL");
        
        HSSFCell celdaH14 = filaH.createCell(14);
        celdaH14.setCellStyle(rowStyle);
        celdaH14.setCellValue("AÑO");
        
        HSSFCell celdaH15 = filaH.createCell(15);
        celdaH15.setCellStyle(rowStyle);
        celdaH15.setCellValue("ID INDICADOR");
        
        HSSFCell celdaH16 = filaH.createCell(16);
        celdaH16.setCellStyle(rowStyle);
        celdaH16.setCellValue("INDICADOR");  
        
        HSSFCell celdaH17 = filaH.createCell(17);
        celdaH17.setCellStyle(rowStyle);
        celdaH17.setCellValue("TIPO INDICADOR");  
        
        HSSFCell celdaH18 = filaH.createCell(18);
        celdaH18.setCellStyle(rowStyle);
        celdaH18.setCellValue("NUMERADOR");
        
        HSSFCell celdaH19 = filaH.createCell(19);
        celdaH19.setCellStyle(rowStyle);
        celdaH19.setCellValue("DENOMINADOR");
        
        HSSFCell celdaH20 = filaH.createCell(20);
        celdaH20.setCellStyle(rowStyle);
        celdaH20.setCellValue("COEFICIENTE SECTOR ECONÓMICO");
        
        HSSFCell celdaH21 = filaH.createCell(21);
        celdaH21.setCellStyle(rowStyle);
        celdaH21.setCellValue("COOFICIENTE");
        
        HSSFCell celdaH22 = filaH.createCell(22);
        celdaH22.setCellStyle(rowStyle);
        celdaH22.setCellValue("COLOR");
        

        int i = 7;
        for (IndicadoresEncabezadoCalculadosDto dato : listaContribuyentes) {
            i = i + 1;
            HSSFRow fila = hoja.createRow(i);

            HSSFCell celda0 = fila.createCell(0);
            celda0.setCellStyle(cellStyle);
            celda0.setCellValue(dato.getNit().toString());

            HSSFCell celda1 = fila.createCell(1);
            celda1.setCellStyle(cellStyle);
            celda1.setCellValue(dato.getNombreContrIns());

            HSSFCell celda2 = fila.createCell(2);
            celda2.setCellStyle(cellStyle);
            celda2.setCellValue(dato.getStatus().toString());

            
            HSSFCell celda3 = fila.createCell(3);
            celda3.setCellStyle(styleMoneda);
            celda3.setCellValue(dato.getSectorEconomico().toString());
            

            HSSFCell celda4 = fila.createCell(4);
            celda4.setCellStyle(styleMoneda);
            celda4.setCellValue(dato.getRegion().toString());
            

            HSSFCell celda5 = fila.createCell(5);
            celda5.setCellStyle(styleMoneda);
            celda5.setCellValue(dato.getClasificacion().toString());
            

            HSSFCell celda6 = fila.createCell(6);
            celda6.setCellStyle(styleMoneda);
            celda6.setCellValue(dato.getPersoneria().toString());
            

            HSSFCell celda7 = fila.createCell(7);
            celda7.setCellStyle(cellStyle);
            celda7.setCellValue(dato.getAfiliacionIva().toString());

            HSSFCell celda8 = fila.createCell(8);
            celda8.setCellStyle(cellStyle);
            celda8.setCellValue(dato.getFechaIva().toString());

            HSSFCell celda9 = fila.createCell(9);
            celda9.setCellStyle(cellStyle);
            celda9.setCellValue(dato.getAfiliacionIsr().toString());

            HSSFCell celda10 = fila.createCell(10);
            celda10.setCellStyle(cellStyle);
            celda10.setCellValue(dato.getFechaIsr());

            HSSFCell celda11 = fila.createCell(11);
            celda11.setCellStyle(cellStyle);
            celda11.setCellValue(dato.getEstablecimientosActivos().toString());
            
            HSSFCell celda12 = fila.createCell(12);
            celda12.setCellStyle(cellStyle);
            celda12.setCellValue(dato.getEstablecimientosInactivos().toString());
            
            HSSFCell celda13 = fila.createCell(13);
            celda13.setCellStyle(cellStyle);
            celda13.setCellValue(dato.getPlOpActual().toString());
            
            HSSFCell celda14 = fila.createCell(14);
            celda14.setCellStyle(cellStyle);
            celda14.setCellValue(dato.getAnio().toString());
            
            HSSFCell celda15 = fila.createCell(15);
            celda15.setCellStyle(cellStyle);
            celda15.setCellValue(dato.getIdIndicador().toString());
            
            HSSFCell celda16 = fila.createCell(16);
            celda16.setCellStyle(cellStyle);
            celda16.setCellValue(dato.getNombreIndicador());
            
            HSSFCell celda17 = fila.createCell(17);
            celda17.setCellStyle(cellStyle);
            celda17.setCellValue(dato.getTipoIndicador().toString());
            
            HSSFCell celda18 = fila.createCell(18);
            celda18.setCellStyle(cellStyle);
            celda18.setCellValue(dato.getNumerador().toString());
            
            HSSFCell celda19 = fila.createCell(19);
            celda19.setCellStyle(cellStyle);
            celda19.setCellValue(dato.getDenominador().toString());
            
            HSSFCell celda20 = fila.createCell(20);
            celda20.setCellStyle(cellStyle);
            celda20.setCellValue(dato.getCoeficienteSectorEco().toString());
            
            HSSFCell celda21 = fila.createCell(21);
            celda21.setCellStyle(cellStyle);
            celda21.setCellValue(dato.getCoeficiente().toString());
            
            HSSFCell celda22 = fila.createCell(22);
            celda22.setCellStyle(cellStyle);
            celda22.setCellValue(dato.getColor().toString());
            
            

        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        externalContext.setResponseContentType("application/vnd.ms-excel; charset=utf-8");

        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"Indicadores_Detalle.xls\"");

        libro.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();

    }

    
    
    
    
    
 }

