/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gt.gob.sat.fiscalizacion.consulta360.util;

import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import gt.gob.sat.aduanas.utilidades.rmi.RmiProxyFactoryBeanSat;
import gt.gob.sat.aduanas.utilidades.rmi.ImpresionDeclaracionRmi;
import javax.sql.DataSource;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author dealonzo
 */
@Configuration
public class ImpresionDeclaracionRmiConfig {
    private static final long serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ImpresionDeclaracionRmiConfig.class);
    
    @Resource
    public DataSource dataSource;
    
    @Bean(name = "impresionDeclaracionRmi")
    public RmiProxyFactoryBeanSat impresionDeclaracionRmi() {
        RmiProxyFactoryBeanSat rmiBean = new RmiProxyFactoryBeanSat();
        rmiBean.setServiceInterface(ImpresionDeclaracionRmi.class);
        rmiBean.setRegistryPortProperty("impresionDeclaracionRmi.registryPort");
        rmiBean.setHostProperty("impresionDeclaracionRmi.url");
        rmiBean.setNameProperty("impresionDeclaracionRmi.name");
        rmiBean.setDataSource(dataSource);
        return rmiBean;
    }
}