/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.gestion_de_formations_en_ligne.service;

import fr.utbm.gestion_de_formations_en_ligne.entity.Course;
import fr.utbm.gestion_de_formations_en_ligne.entity.CourseSession;
import fr.utbm.gestion_de_formations_en_ligne.entity.Location;
import fr.utbm.gestion_de_formations_en_ligne.repository.HibernateCourseDAO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ali
 */
public class CourseService {

    public List<Course> getAllCoursesService() {
        HibernateCourseDAO hcd = new HibernateCourseDAO();
        return hcd.getAllCoursesHibernate();
    }

    public List<Course> getAllCoursesService(String motCle) {
        HibernateCourseDAO hcd = new HibernateCourseDAO();
        return hcd.getAllCoursesHibernate(motCle);
    }

    public List<Course> getAllCoursesAtLocationService(Location location) {
        HibernateCourseDAO hcd = new HibernateCourseDAO();
        return hcd.getAllCoursesAtLocationHibernate(location);
    }

    public List<Course> getAllCoursesAtDateService(Date date) throws Exception {
        HibernateCourseDAO hcd = new HibernateCourseDAO();
        return hcd.getAllCoursesAtDateHibernate(date);
    }
    public List<CourseSession> getAllCourseSessions(String code) {
        HibernateCourseDAO hcd = new HibernateCourseDAO();
        return hcd.getAllCourseSessions(code);
    }
    public Course getCourseByCode(String code) {
        HibernateCourseDAO hcd = new HibernateCourseDAO();
        return hcd.getCourseByCode(code);
    }
}
