/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vista;

import com.sv.udb.recursos.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
/**
 *
 * @author vergo_000
 */
@WebServlet(name = "ReporteSrv", urlPatterns = {"/ReporteSrv"})
public class ReporteSrv extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        try{
            Connection cn = new Conexion().getCon();
            String url1 = getServletContext().getRealPath("reportes/ReporteLibros.jrxml");
            String url2="";
            String url3 = getServletContext().getRealPath("");
             String CRUD = request.getParameter("btonReporte");
            if(CRUD.equals("Libros")){
                url2 = getServletContext().getRealPath("reportes/ReporteLibros.jasper");
            }else if(CRUD.equals("Prestamos")){
                url2 = getServletContext().getRealPath("reportes/ReportePrestamos.jasper");
            }           
            
            byte[] bytes = null;
            try{
                bytes = JasperRunManager.runReportToPdf(url2,null,cn);
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=reporte.pdf");
                response.setContentLength(bytes.length);
                out.write(bytes,0,bytes.length);
                out.flush();
                out.close();
            }catch(IOException | JRException ex){
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                ex.printStackTrace(printWriter);
                response.setContentType("text/plain");
                response.getOutputStream().print(stringWriter.toString());
            }
            
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }           
            
           
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
