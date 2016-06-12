/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.gestion_de_formations_en_ligne.repository;

import fr.utbm.gestion_de_formations_en_ligne.entity.Location;
import fr.utbm.gestion_de_formations_en_ligne.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ali
 */
public class HibernateLocationDAO {

    public List<String> getAllLocationsHibernate() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<String> listCities = new ArrayList<>();
        try {

            Query query = session.createQuery("from Location");
            List<Location> listLocations = query.list();

            Iterator it = listLocations.iterator();
            while (it.hasNext()) {

                Location c = (Location) it.next();
                listCities.add(c.getCity());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return listCities;
    }
}
