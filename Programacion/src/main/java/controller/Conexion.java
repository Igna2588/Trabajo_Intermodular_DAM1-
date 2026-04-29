package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


    public class Conexion {

        private static final String URL = "jdbc:mysql://localhost:3306/gastos_familiares";
        private static final String USUARIO = "root";
        private static final String PASSWORD = "";

        public static Connection getConexion() {
            Connection con = null;
            try {
                con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos");
            } catch (SQLException e) {
                System.out.println("Error al conectar: " + e.getMessage());
            }
            return con;
        }
    }
