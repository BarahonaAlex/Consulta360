/*
 * Superintendencia de Administracion Tributaria (SAT)
 * Gerencia de Informatica
 * Departamento de Desarrollo de Sistemas
 */
package gt.gob.sat.fiscalizacion.consulta360.srv.impl;

import gt.gob.sat.arquitectura.fwk.dto.ParameterDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ConsultaCantidadDeclaracionesAduanerasConAnioDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.ConteoVehiculosActivosInactivosDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.DeclaracionAduaneraDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.TotalDto;
import gt.gob.sat.fiscalizacion.consulta360.dto.obtenerConteoDto;
import gt.gob.sat.fiscalizacion.consulta360.srv.GeneralSrv;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Daniel Castillo (adcastic)
 * @since 08/12/2015
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringTestConfig.class})
public class GeneralSrvImplTest {

    /**
     * Numero de version de la clase.
     */
    private static final long serialVersionUID = 1L;
//______________________________________________________________________________
    /**
     * Genera mensajes de log para depuracion.
     */
    private static final Log LOGGER = LogFactory.getLog(GeneralSrvImplTest.class);
//______________________________________________________________________________
    /**
     * Bean gestionado por Spring, contiene logica de negocio
     */
    @Autowired
    private GeneralSrv generalSrvImpl;
//______________________________________________________________________________   

    /**
     * Constructor predeterminado.
     */
    public GeneralSrvImplTest() {
    }
//______________________________________________________________________________

    /**
     * Este metodo se ejecuta antes de iniciar con las pruebas unitarias. Este
     * metodo es util para inicializar atributos comunes a varias pruebas o para
     * ejecutar procesos que tardaran bastante tiempo en ejecutarse. Esta
     * capacidad es otorgada por la anotacion {@link BeforeClass}, tomar en
     * cuenta que solo puede existir un metodo con esta anotacion.
     */
    @BeforeClass
    public static void setUpClass() {
    }
//______________________________________________________________________________

    /**
     * Este método sera invocado un sola vez cuando finalizen todas las pruebas
     * unitarias. Esta capacidad es otorgada por la anotacion
     * {@link AfterClass}, tomar en cuenta que solo puede existir un metodo con
     * esta anotacion.
     */
    @AfterClass
    public static void tearDownClass() {
    }
//______________________________________________________________________________

    /**
     * Este metodo se ejecuta antes de iniciar cada prueba. Esta capacidad es
     * otorgada por la anotacion {@link Before}, pueden existir varios metodos
     * con esta anotacion.
     */
    @Before
    public void setUp() {
    }
//______________________________________________________________________________

    /**
     * Este metodo se ejecuta despues de finalizar cada prueba. Esta capacidad
     * es otorgada por la anotacion {@link After}, pueden existir varios metodos
     * con esta anotacion.
     */
    @After
    public void tearDown() {
    }

    @Test
    @Ignore
    public void test() {

    }

    @Test
    @Ignore
    public void probandoConsultaContador() {
        obtenerConteoDto data = this.generalSrvImpl.obtenerRegistroContadores("335703", 0, 0, 1);
        System.out.println("CONTADORRR " + data.getCantidadRegistros());
    }

    @Test
    @Ignore
    public void probarProcedimientoVehiulos() {
        List<Integer> anio = new ArrayList();

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int restaAnios = year;

        for (int i = year - 5; i < year; i++) {
            anio.add(restaAnios--);
        }

        List<ConteoVehiculosActivosInactivosDto> data = this.generalSrvImpl.obtenerConteoVehiculosActivInactiv(anio, "19");

        for (ConteoVehiculosActivosInactivosDto conteoVehiculosActivosInactivosDto : data) {
            System.out.println("DATA " + conteoVehiculosActivosInactivosDto.getCantidadVehiculos());
            System.out.println("DATA " + conteoVehiculosActivosInactivosDto.getNombreAnio());
        }

    }

    @Test
    @Ignore
    public void probarDeclaracionesAduaneras() throws ParseException {
        String sDate1 = "01/01/2022";
        String sDate2 = "01/12/2022";
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
        List<DeclaracionAduaneraDto> obtenerDeclaracionesAduaneras = this.generalSrvImpl.obtenerDeclaracionesAduaneras("2291", null, date1, date2, "-", "-");
        for (DeclaracionAduaneraDto obtenerDeclaracionesAduanera : obtenerDeclaracionesAduaneras) {
            System.out.println("DATA " + obtenerDeclaracionesAduanera.getAduanaEntrada());
        }
    }

    @Test
    @Ignore
    public void probarOmisos() {
        List<TotalDto> data = generalSrvImpl.findOmisos("1350552");
        for (TotalDto totalDto : data) {
            System.out.println("" + totalDto.getCantidadAnioActual());
            System.out.println("" + totalDto.getCantidadAnioActualMenos1());
            System.out.println("" + totalDto.getCantidadAnioActualMenos3());
        }
    }

    @Test
    @Ignore
    public void contarDeclaracionesAduaneras() {
        List<Integer> anios = new ArrayList<>();
        int year = Year.now().getValue();
        for (int i = 0; i < 5; i++) {
            anios.add(year - i);
        }

        List<ConsultaCantidadDeclaracionesAduanerasConAnioDto> data = this.generalSrvImpl.obtenerConteoDeclaracionesAduanas(anios, "2291");

        for (ConsultaCantidadDeclaracionesAduanerasConAnioDto dataShow : data) {
            System.out.println(":::: " + dataShow.getAnio());
            System.out.println(":::: " + dataShow.getCantidad_declaraciones());
            System.out.println(":::: " + dataShow.getCif_suma());
            System.out.println(":::: " + dataShow.getFob_suma());
        }
    }
}
