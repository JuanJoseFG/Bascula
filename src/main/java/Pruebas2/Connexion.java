/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas2;

import Pruebas.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DESARROLLADOR
 */
public class Connexion {
    
    // Método para obtener una conexión a la base de datos
    public static Connection obtenerConexion() throws SQLException {
        // Datos de conexión
        
         
        String host = "oms.appeuro.mx";
        int puerto = 6033;
        String baseDeDatos = "gpodsw_pruebas";
        String usuario = "marco";
        String contraseña = "cv%j?y8=GI8h";
        String url = "jdbc:mysql://" + host + ":" + puerto + "/" + baseDeDatos;

        // Obtener conexión
        return DriverManager.getConnection(url, usuario, contraseña);
        
    }

    // Método para cerrar una conexión
    public static void cerrarConexion(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
