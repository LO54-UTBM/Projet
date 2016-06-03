/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.gestion_de_formations_en_ligne.repository;

import fr.utbm.gestion_de_formations_en_ligne.entity.Client;
import fr.utbm.gestion_de_formations_en_ligne.entity.Log;
import fr.utbm.gestion_de_formations_en_ligne.service.ClientService;
import fr.utbm.gestion_de_formations_en_ligne.util.HibernateUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transaction;
import org.hibernate.Session;

/**
 *
 * @author Ali
 */
public class JTAClientDAO {
    
        public void insertClientJta(Client client) throws Exception {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.persist(client);
//        session.getTransaction().commit();
            
          Session session = HibernateUtil.getSessionFactory().openSession();
          Transaction tx = null;
          
          try{
              System.out.println("test0 ");
              tx =  (Transaction) session.beginTransaction();
              System.out.println("test0.5 ");
              Log log = new Log();
              DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
              Date date = new Date();
              System.out.println("test1 ");
              log.setDates(date);
              log.setStudent(client.getFirstname()+" "+client.getLastname());
              System.out.println("test2 ");
              session.persist(log);
              System.out.println("test3 ");
              //Mettre l'exception
              session.persist(client);
              System.out.println("test4 ");
              tx.commit();
          }
          catch(Exception e){
              System.out.println("test5 ");
              Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, e);
//              tx.rollback();
              
          }
          finally{
              session.close();
          }
    }
}
