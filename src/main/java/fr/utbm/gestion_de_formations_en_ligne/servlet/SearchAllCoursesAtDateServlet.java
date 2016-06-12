package fr.utbm.gestion_de_formations_en_ligne.servlet;

import fr.utbm.gestion_de_formations_en_ligne.entity.Course;
import fr.utbm.gestion_de_formations_en_ligne.service.CourseService;
import fr.utbm.gestion_de_formations_en_ligne.service.LocationService;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchAllCoursesAtDateServlet extends HttpServlet {

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
        CourseService cs = new CourseService();
        DateFormat formatter;
        Date d = null;

        /**
         * pass Date in parameter
         */
        try {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            d = formatter.parse(request.getParameter("date"));
        } catch (ParseException ex) {
            Logger.getLogger(SearchAllCoursesAtDateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

//        Get All courses with sessions after date
        try {
            List<Course> allCourses = cs.getAllCoursesAtDateService(d);
            request.setAttribute("allCourses", allCourses);
        } catch (Exception e) {
            request.setAttribute("dateError", "dateError");
        }

//        Set Servlet parameters
        LocationService ls = new LocationService();
        List<String> allLocations = ls.getAllLocationsService();
        request.setAttribute("allLocations", allLocations);
        request.getRequestDispatcher("jsp/Accueil.jsp").forward(request, response);
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
