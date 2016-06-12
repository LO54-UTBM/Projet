/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.gestion_de_formations_en_ligne.repository;

import fr.utbm.gestion_de_formations_en_ligne.entity.Client;
import fr.utbm.gestion_de_formations_en_ligne.entity.Log;
import fr.utbm.gestion_de_formations_en_ligne.service.ClientService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

/**
 *
 * @author Ali
 */
public class JTALogDAO {
    public void insertLogJta(Client client,XAConnection xaCon) throws Exception {

        
        XAResource xaRes = null;
        Xid xid = null;
        Connection con;
        Statement stmt;
        int ret;

        try {
            xaRes = xaCon.getXAResource();
            con = xaCon.getConnection();
            stmt = con.createStatement();
            PreparedStatement persistLog = con.prepareStatement("insert into log values (?,?)");

            Log log = new Log();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            java.util.Date date = new java.util.Date();
            log.setDates(date);
            log.setStudent(client.getFirstname() + " " + client.getLastname());

            persistLog.setTimestamp(1, new Timestamp(date.getTime()));
            persistLog.setString(2, log.getStudent());
            persistLog.executeUpdate();

        } catch (Exception e) {
            System.out.println("Log Exception ");
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, e);   
            throw e ;

        }
    }
}
