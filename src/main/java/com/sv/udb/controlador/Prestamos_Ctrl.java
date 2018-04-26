/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Libros;
import com.sv.udb.modelo.Prestamos;
import com.sv.udb.modelo.Usuarios;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vergo_000
 */
public class Prestamos_Ctrl {
     private final Connection conn;

    public Prestamos_Ctrl() {
        this.conn = new Conexion().getCon();
    }
     public List<Prestamos> consTodo()
    {
       List<Prestamos> resp = new ArrayList<>();
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("select p.codi_pres,l.*,u.*,p.fech_pres,p.fech_devo from ((prestamos p inner join libros l on p.codi_libr=l.codi_libr) inner join usuarios u on u.codi_usua=p.codi_usua) where l.esta_libr=1 and p.fech_devo is null");
           ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Prestamos(rs.getInt(1),(new Libros(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7))),(new Usuarios(rs.getInt(8),rs.getString(9),rs.getString(10),rs.getString(11))),rs.getDate(12),rs.getDate(13))); // <----- Hay que llenar con los objetos
            }
        }
        catch(SQLException ex)
        {
            System.err.println("Error al consultar los prestamos: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
       public Prestamos consTodoE(int codi)
    {
       Prestamos resp=null;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("select p.codi_pres,l.*,u.*,p.fech_pres,p.fech_devo from ((prestamos p inner join libros l on p.codi_libr=l.codi_libr) inner join usuarios u on u.codi_usua=p.codi_usua) where l.esta_libr=1 and p.codi_pres=?");
            cmd.setInt(1, codi);
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp=new Prestamos(rs.getInt(1),(new Libros(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7))),(new Usuarios(rs.getInt(8),rs.getString(9),rs.getString(10),rs.getString(11))),rs.getDate(12),rs.getDate(13)); // <----- Hay que llenar con los objetos
            }
        }
        catch(SQLException ex)
        {
            System.err.println("Error al consultar los prestamos: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
    public boolean guar(int codiL, int codiU, Date fechaP)
    {
        boolean resp = false;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO prestamos (codi_libr,codi_usua,fech_pres,fech_devo) VALUES(?,?,?,NULL)");
            cmd.setInt(1, codiL);
            cmd.setInt(2, codiU);
            cmd.setString(3,  new SimpleDateFormat("yyyy-MM-dd").format(fechaP));
            cmd.executeUpdate();
            Libros_Ctrl lb=new Libros_Ctrl();
            lb.modiCL(codiL);
            resp=true;
        }
        catch (SQLException ex)
        {
            System.err.println("Error al guardar el prestamo: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
    
    public boolean modi(int codiL,Date fechaP,int codigo)
    {
        boolean resp = false;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("update prestamos set prestamos.fech_devo=? WHERE prestamos.codi_pres=?");
            cmd.setString(1,  new SimpleDateFormat("yyyy-MM-dd").format(fechaP));
            cmd.setInt(2,codigo);
            Libros_Ctrl lb2=new Libros_Ctrl();
            lb2.modiCLBack(codiL);
            cmd.executeUpdate();
            resp=true;
        }
        catch (SQLException ex)
        {
            System.err.println("Error al modificar el prestamo: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
}
