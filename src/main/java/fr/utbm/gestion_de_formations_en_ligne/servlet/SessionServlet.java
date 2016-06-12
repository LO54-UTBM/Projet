package fr.utbm.gestion_de_formations_en_ligne.servlet;

import fr.utbm.gestion_de_formations_en_ligne.entity.Course;
import fr.utbm.gestion_de_formations_en_ligne.entity.CourseSession;
import fr.utbm.gestion_de_formations_en_ligne.service.CourseService;
import fr.utbm.gestion_de_formations_en_ligne.service.CourseSessionService;
import fr.utbm.gestion_de_formations_en_ligne.service.LocationService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionServlet extends HttpServlet {

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

        /**
         * pass Course Code and Session ID in parameter
         */
        String code = request.getParameter("code");
        int id = Integer.parseInt(request.getParameter("id"));

//        Get Course by Code
        CourseService cs = new CourseService();
        Course c = cs.getCourseByCode(code);
        request.setAttribute("course", c);

        //        Get Session by id
        CourseSessionService css = new CourseSessionService();
        CourseSession cse = css.getCourseSessionById(id);
        request.setAttribute("session", cse);

//        Set Servlet parameters
        LocationService ls = new LocationService();
        List<String> allLocations = ls.getAllLocationsService();
        request.setAttribute("allLocations", allLocations);
        request.getRequestDispatcher("jsp/Inscription.jsp").forward(request, response);

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
