/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.gestion_de_formations_en_ligne.repository;

import fr.utbm.gestion_de_formations_en_ligne.entity.Client;
import fr.utbm.gestion_de_formations_en_ligne.entity.Log;
import fr.utbm.gestion_de_formations_en_ligne.service.ClientService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.hibernate.Transaction;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.XAConnection;
import javax.transaction.xa.*;

public class JTAClientDAO {

    public void insertClientJta(Client client,XAConnection xaCon) throws Exception {

        
        
        XAResource xaRes = null;
        Xid xid = null;
        Connection con;
        int ret;

        try {
            
            xaRes = xaCon.getXAResource();
            con = xaCon.getConnection();
            
            PreparedStatement persistClient = con.prepareStatement("insert into client values (null,?,?,?,?,?,?)");


            /** Exception */
//            int x=5/0;

            persistClient.setShort(1, client.getCourseSession().getId());
            persistClient.setString(2, client.getLastname());
             persistClient.setString(2, client.getLastname()+"fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
            persistClient.setString(3, client.getFirstname());
            persistClient.setString(4, client.getAddress());
            persistClient.setString(5, client.getPhone());
            persistClient.setString(6, client.getEmail());

            persistClient.executeUpdate();


        } catch (Exception e) {
            System.out.println("test2 ");
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, e);
            throw e ;

        }
    }
}
