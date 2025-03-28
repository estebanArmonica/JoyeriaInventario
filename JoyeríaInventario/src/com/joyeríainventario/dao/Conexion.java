/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joyeríainventario.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author esteb
 */
public class Conexion {
    Connection con;
    
    public boolean acceder() throws ClassNotFoundException, IOException, Exception {
        Class.forName("org.postgresql.Driver");
        String user = "esteban";
        String password = "armonica";
        String url = "jdbc:postgresql://localhost:5432/inventario";
        con = DriverManager.getConnection(url, user, password);
        return true;
    }
    
    /*  
        creamos una conexion con getConnection de tipo Connection
        el cual retorna el con de Connection
    */
    public Connection get(){
        return con;
    }
    
    /*
     * creamos un metodo void para cerrar la conexion de la BD
    */
    public void cerrar() throws Exception{
        con.close();
    }
}
