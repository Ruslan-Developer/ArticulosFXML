package com.ruslan.articulosfxml.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBBDD {
   // public static Connection conexion;

    private static Connection conexion;

   // public static Connection getConexion() throws ClassNotFoundException, SQLException, IOException {

    public static Connection conectar() throws  ClassNotFoundException, SQLException,IOException {

        //if(conexion == null) {
            Properties configuration = new Properties();
            try (FileInputStream fis = new FileInputStream("src/main/resources/configuration/application.properties")) {
                configuration.load(fis);
            }
            String host = configuration.getProperty("host");


            String port = configuration.getProperty("port");
            String name = configuration.getProperty("name");
            String username = configuration.getProperty("username");
            String password = configuration.getProperty("password");

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC", username, password);

            return conexion;

    }

       // return conexion;

    //}

}