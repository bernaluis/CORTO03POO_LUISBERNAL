/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vista;

import com.sv.udb.controlador.Prestamos_Ctrl;
import com.sv.udb.modelo.Prestamos;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vergo_000
 */
@WebServlet(name = "PrestamosSrv", urlPatterns = {"/PrestamosSrv"})
public class PrestamosSrv extends HttpServlet {

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
        boolean esValido = request.getMethod().equals("POST");
        String mens = "";
        if (!esValido) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            String CRUD = request.getParameter("btonPrestamo");
            if (CRUD.equals("Agregar")) {
                try {
                    if (new Prestamos_Ctrl().guar(Integer.parseInt(request.getParameter("selLibros")), Integer.parseInt(request.getParameter("selUsuarios")), new SimpleDateFormat("yyyy-MM-dd").parse( request.getParameter("fechaP"))  )) {
                        mens = "Datos guardados";
                    } else {
                        mens = "Error al guardar";
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(PrestamosSrv.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("mensAler", mens);
            } else if (CRUD.equals("Modificar")) {
                try {
                    if (new Prestamos_Ctrl().modi(Integer.parseInt(request.getParameter("selLibrosPrestados")), new SimpleDateFormat("yyyy-MM-dd").parse( request.getParameter("fechaD")),Integer.parseInt(request.getParameter("codigoP")))) {
                        mens = "Datos Modificados";
                    } else {
                        mens = "Error al modificar";
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(PrestamosSrv.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("mensAlerta", mens);
            } else if (CRUD.equals("Consultar")) {
                int codi = Integer.parseInt(request.getParameter("codiBRadi") == null ? "-1"
                        : request.getParameter("codiBRadi"));
                Prestamos obje = new Prestamos_Ctrl().consTodoE(codi);
                if (obje != null) {
                    request.setAttribute("codigoP", obje.getCodigo());
                    request.setAttribute("selLibros", obje.getCodigoL().getCodi_libr());
                    request.setAttribute("selLibros", obje.getCodigoL().getNomb_libr());
                    request.setAttribute("selUsuarios", obje.getCodigoU().getCodi_usua());
                    request.setAttribute("selUsuarios", obje.getCodigoU().getNomb_usua());
                    request.setAttribute("fechaD", obje.getFechaD());
                    mens = "Información consultada";
                 
                } else {
                    mens = "Error al consultar";
                }
                request.setAttribute("mensAlerta", mens);

            }
            
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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
