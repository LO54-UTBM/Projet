/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.gestion_de_formations_en_ligne.service;

import fr.utbm.gestion_de_formations_en_ligne.entity.Client;
import fr.utbm.gestion_de_formations_en_ligne.repository.HibernateClientDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eddie
 */
public class ClientService {

    public void insertClientService(Client client) throws Exception {
        HibernateClientDAO hcd = new HibernateClientDAO();
        hcd.insertClientHibernate(client);
        
        try {
            hcd.insertClientHibernate(client);
        } catch (Exception ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

}
