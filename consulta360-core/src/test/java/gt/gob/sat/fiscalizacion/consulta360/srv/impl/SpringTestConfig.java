/**
 * Superintendencia de Administracion Tributaria (SAT)
 * Gerencia de Informatica
 * Desarrollo de Sistemas
 */
package gt.gob.sat.fiscalizacion.consulta360.srv.impl;

import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Daniel Castillo (adcastic)
 * @since 27/11/2015
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"gt.gob.sat.arquitectura.fwk.dao, gt.gob.sat.fiscalizacion.consulta360.srv.impl"})
public class SpringTestConfig {

    /**
     * Metodo para cargar configuraciones iniciales.
     */
    @PostConstruct
    public void init() {
        // creacion de appenders
        Appender consola = crearConsoleAppender();

        // se configura la raiz de LOG4J
        LogManager.resetConfiguration();
        Logger root = LogManager.getRootLogger();
        root.setAdditivity(false);
        root.setLevel(Level.ERROR);
        root.addAppender(consola);

        // logger para mostrar las consultas SQL que realiza hibernate
        Logger logger2 = LogManager.getLogger("org.hibernate.SQL");
        logger2.setAdditivity(false);
        logger2.setLevel(Level.DEBUG);
        logger2.addAppender(consola);

        // logger para mostrar los parametros que reciben las consultas que ejecuta hibernate
        Logger logger3 = LogManager.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
        logger3.setAdditivity(false);
        logger3.setLevel(Level.TRACE);
        logger3.addAppender(consola);

        // logger especifico de la aplicacion
        Logger logger4 = LogManager.getLogger("gt.gob.sat");
        logger4.setAdditivity(false);
        logger4.setLevel(Level.DEBUG);
        logger4.addAppender(consola);
    }
//______________________________________________________________________________

    /**
     * Bean que crea la conexion con la base de datos por medio de JDBC este
     * bean se utiliza cuando el Data Source se encuentra controlado por la
     * misma aplicacion.
     *
     * @return DataSource Conexion de base de datos
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@//10.112.0.6:1521/DBDESA");
        dataSource.setUsername("AP_CONSULTA_USR");
        dataSource.setPassword("desa123$");
        return dataSource;
    }
//______________________________________________________________________________

    /**
     * Bean de sesion para la base de datos, realiza la integracion con
     * Hibernate.
     *
     * @return LocalSessionFactoryBean Fabrica de sesiones
     */
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        // se crea la configuracion para hibernate
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        hibernateProperties.put("hibernate.show_sql", false);
        hibernateProperties.put("hibernate.format_sql", true);
        hibernateProperties.put("hibernate.bytecode.use_reflection_optimizer", true);

        // se crea el session factory
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("gt.gob.sat.fiscalizacion.consulta360.modelo");
        sessionFactory.setHibernateProperties(hibernateProperties);
        sessionFactory.setConfigLocation(new ClassPathResource("META-INF/hibernate.cfg.xml"));

        return sessionFactory;
    }
//______________________________________________________________________________

    /**
     * Bean que controlara las transacciones en Hibernate.
     *
     * @return HibernateTransactionManager Controlador de transacciones
     */
    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }
//______________________________________________________________________________

    /**
     * Bean para manejar las excepciones a nivel de Hibernate.
     *
     * @return PersistenceExceptionTranslationPostProcessor Controlador de
     * excepciones
     */
    @Bean(name = "persistenceExceptionTranslationPostProcessor")
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
//______________________________________________________________________________

    /**
     * Este metodo permite crear un appender para escribir log en consola.
     *
     * @param pContexto Nombre para el appender
     * @return ConsoleAppender Appender creado
     */
    private ConsoleAppender crearConsoleAppender() {
        ConsoleAppender appender = new ConsoleAppender();
        appender.setName("consola");
        appender.setLayout(new PatternLayout("%d{HH:mm:ss.SSS} %-5p [%t][%F:%L] %m%n"));
        appender.activateOptions();
        return appender;
    }
}
