/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.gestion_de_formations_en_ligne.servlet;

import fr.utbm.gestion_de_formations_en_ligne.entity.Client;
import fr.utbm.gestion_de_formations_en_ligne.entity.CourseSession;
import fr.utbm.gestion_de_formations_en_ligne.service.ClientService;
import fr.utbm.gestion_de_formations_en_ligne.service.CourseSessionService;
import fr.utbm.gestion_de_formations_en_ligne.service.JTAClientService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddClientServlet extends HttpServlet {

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
        CourseSession cs = new CourseSession();
        CourseSessionService css = new CourseSessionService();

        /**
         * pass Session ID in parameter
         */
        cs = css.getCourseSessionById(Integer.parseInt(request.getParameter("id")));

//        Create new client
        Client c = new Client();
        c.setAddress(request.getParameter("address"));
        c.setCourseSession(cs);
        c.setEmail(request.getParameter("email"));
        c.setFirstname(request.getParameter("firstName"));
        c.setLastname(request.getParameter("lastName"));
        c.setPhone(request.getParameter("phone"));

        /**
         * Create Client using hibernate
         */
//        ClientService cse = new ClientService();
//        try {
//            cse.insertClientService(c);
//            request.setAttribute("ok", "ok");
//        } catch (Exception ex) {
//            Logger.getLogger(AddClientServlet.class.getName()).log(Level.SEVERE, null, ex);
//            request.setAttribute("ok", "ko");
//        }
        
        /**
         * Create Client using JTA
         */
        JTAClientService jcs = new JTAClientService();
        try {
            jcs.insertClientService(c);
            request.setAttribute("ok", "ok");
        } catch (Exception ex) {
            Logger.getLogger(AddClientServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("ok", "ko");
        }

        request.getRequestDispatcher("Courses").forward(request, response);
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
