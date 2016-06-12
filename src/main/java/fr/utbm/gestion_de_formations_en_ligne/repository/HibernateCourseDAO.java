/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.gestion_de_formations_en_ligne.repository;

import fr.utbm.gestion_de_formations_en_ligne.entity.Course;
import fr.utbm.gestion_de_formations_en_ligne.entity.CourseSession;
import fr.utbm.gestion_de_formations_en_ligne.entity.Location;
import fr.utbm.gestion_de_formations_en_ligne.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Eddie
 */
public class HibernateCourseDAO {

    public List<Course> getAllCoursesHibernate() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Course");
        List<Course> listCourse = query.list();
        return listCourse;
    }

    public List<Course> getAllCoursesHibernate(String motCle) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Course as course where upper(course.title) like  ?");
        query.setString(0, "%" + motCle.toUpperCase() + "%");
        List<Course> listCourse = query.list();
        return listCourse;
    }

    public List<Course> getAllCoursesAtDateHibernate(Date date) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Course");
        List<Course> listCourse = query.list();

        List<Course> listCourseOK = new ArrayList<>();

        for (Course c : listCourse) {
            for (Iterator it = c.getCourseSessions().iterator(); it.hasNext();) {
                CourseSession cs = (CourseSession) it.next();
                if (date.before(cs.getStartDate())) {
                    int count = 0;
                    for (Course c2 : listCourseOK) {
                        if (c2.equals(c)) {
                            count++;
                        }
                    }

                    if (count == 0) {
                        listCourseOK.add(c);
                    }
                }
            }
        }
        return listCourseOK;
    }

    public List<Course> getAllCoursesAtLocationHibernate(Location location) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Course");
        List<Course> listCourse = query.list();

        List<Course> listCourseOK = new ArrayList<>();

        for (Course c : listCourse) {
            for (Iterator it = c.getCourseSessions().iterator(); it.hasNext();) {
                CourseSession cs = (CourseSession) it.next();

                if (cs.getLocation().getCity().equalsIgnoreCase(location.getCity())) {
                    int count = 0;
                    for (Course c2 : listCourseOK) {
                        if (c2.equals(c)) {
                            count++;
                        }
                    }

                    if (count == 0) {
                        listCourseOK.add(c);
                    }

                }
            }
        }
        return listCourseOK;
    }

    public List<CourseSession> getAllCourseSessions(String code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Course as course where course.code=?");
        query.setString(0, code);
        Course c = (Course) query.uniqueResult();
        List<CourseSession> list = new ArrayList<>();
        for (Iterator it = c.getCourseSessions().iterator(); it.hasNext();) {
            CourseSession cs = (CourseSession) it.next();
            list.add(cs);
        }

        int i, j;
        List<CourseSession> tab = list;

        CourseSession temp = new CourseSession();

        i = 0;
        while (i < tab.size()) {
            for (j = tab.size() - 1; j >= i + 1; j--) {

                if (tab.get(j).getStartDate().compareTo(tab.get(j - 1).getStartDate()) > 0) {
                    temp = tab.get(j);
                    tab.set(j, tab.get(j - 1));
                    tab.set(j - 1, temp);
                }
            }
            i++;
        }

        return tab;

    }

    public Course getCourseByCode(String code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Course as course where course.code=?");
        query.setString(0, code);
        Course c = (Course) query.uniqueResult();
        return c;
    }

}
