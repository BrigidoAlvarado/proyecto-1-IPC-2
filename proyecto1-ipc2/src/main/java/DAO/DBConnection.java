/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Brigido Alvarado
 */
public class DBConnection {
    
    private static DBConnection instance;
    
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String USER = "root";
    private static final String PASSWORD = "v1d1t0";
    private static final String DATABASE = "proyecto_1_ipc-2";
    private static final String URL = "jdbc:mysql://" +  HOST + ":" + PORT + "/" + DATABASE;
    
    private Connection connection;
    
    public static DBConnection getInstance (){
        
        if ( instance == null) {
            
            instance = new DBConnection();
        }
        
        return instance;
    }
    
    public Connection getConection(){
         
        try {
            if (connection == null) {
                
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("conexion creada");
            }
            return connection;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return connection;
    }
    
}
