/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.fiscalizacion.consulta360.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author cgsamayo
 */
public class Conexion {
    private static String servidor = "jdbc:sqlserver://pro-analitica-ondemand.sql.azuresynapse.net:1433;databaseName=dwi_cruces";
    private static String user = "dwuser";
    private static String pass = "Datawarehouse01.";
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection conexion;

    public Conexion() throws SQLException {
        try{
            Class.forName(this.driver);
            this.conexion = DriverManager.getConnection(this.servidor,this.user,this.pass);
            System.out.println("Conexión iniciada con éxito");
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("ERROR CONECTANDO A LA BASE DE DATOS");
        }
    }

    public Connection getConexion(){
        return this.conexion;
    }
}   

