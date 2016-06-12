/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.gestion_de_formations_en_ligne.service;

import fr.utbm.gestion_de_formations_en_ligne.repository.HibernateLocationDAO;
import java.util.List;

/**
 *
 * @author Eddie
 */
public class LocationService {

    public List<String> getAllLocationsService() {
        HibernateLocationDAO hld = new HibernateLocationDAO();
        return hld.getAllLocationsHibernate();
    }
}
