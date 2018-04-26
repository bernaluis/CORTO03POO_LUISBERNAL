/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.recursos;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author vergo_000
 */
public class Conexion {

    private Connection con;
    private String url,user,pass;

    public Connection getCon() {
        try{
            if(this.getDataConnection())
            {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                this.con=DriverManager.getConnection(this.url,this.user,this.pass);
            }
        }
        catch(Exception e)
        {
            System.err.println("Error al conectar "+e.getMessage());
        }
        return con;
    }
    private boolean getDataConnection()
    {
        boolean resp =true;
        try
        {
            Properties prop=new Properties();
            try(InputStream file=Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"))
                    {
                        prop.load(file);
                        this.url=prop.getProperty("url");
                        this.user=prop.getProperty("user");
                        this.pass=prop.getProperty("password");
                        
                    }
            catch(Exception e)
            {
                System.err.println("Error al leer el archivo de conexion "+e.getMessage());
            }
                    
        }
        catch(Exception e)
        {}
        return resp;
    }
}
