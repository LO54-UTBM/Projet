/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.gestion_de_formations_en_ligne.service;

import fr.utbm.gestion_de_formations_en_ligne.entity.Client;
import fr.utbm.gestion_de_formations_en_ligne.repository.JDBCConnectionDAO;
import fr.utbm.gestion_de_formations_en_ligne.repository.JTAClientDAO;
import fr.utbm.gestion_de_formations_en_ligne.repository.JTALogDAO;
import fr.utbm.gestion_de_formations_en_ligne.repository.MyXid;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

/**
 *
 * @author Ali
 */
public class JTAClientService {
    public void insertClientService(Client client) throws Exception {
        XAConnection xaCon;
        XAResource xaRes = null;
        Xid xid = null;
        Connection con;
        int ret;
        try {
            xaCon = JDBCConnectionDAO.getOracleConnection();
            xaRes = xaCon.getXAResource();
            con = xaCon.getConnection();
            xid = new MyXid(100, new byte[]{0x01}, new byte[]{0x02});
            /**
             * Begin Transaction
             */
            xaRes.start(xid, XAResource.TMNOFLAGS);
            /**
             * insert log
             */
            JTALogDAO jld = new JTALogDAO();
            jld.insertLogJta(client, xaCon);
            /**
             * insert client
             */
            JTAClientDAO jcd = new JTAClientDAO();
            jcd.insertClientJta(client, xaCon);
            /**
             * end Transaction
             */
            xaRes.end(xid, XAResource.TMSUCCESS);
            /**
             * Commit
             */
            ret = xaRes.prepare(xid);
            if (ret == XAResource.XA_OK) {
                xaRes.commit(xid, false);
            }

        } catch (Exception e) {
            System.out.println("test3 ");
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, e);
            /**
             * Rollback
             */
            xaRes.rollback(xid);
            throw e;
        }
    }
}
