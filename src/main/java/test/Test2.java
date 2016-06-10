/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import fr.utbm.gestion_de_formations_en_ligne.repository.MyXid;
import fr.utbm.gestion_de_formations_en_ligne.repository.JDBCConnectionDAO;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.XAConnection;
import javax.transaction.xa.*;

public class Test2 {

    public static void main(String[] args) {

        XAConnection xaCon;
        XAResource xaRes;
        Xid xid;
        Connection con;
        Statement stmt;
        int ret;
        try {

            xaCon = JDBCConnectionDAO.getOracleConnection();
            xaRes = xaCon.getXAResource();
            con = xaCon.getConnection();
            stmt = con.createStatement();
            xid = new MyXid(100, new byte[]{0x01}, new byte[]{0x02});

            xaRes.start(xid, XAResource.TMNOFLAGS);
            stmt.executeUpdate("insert into client values (null,1,'7','oo','789','25','aa@a')");
            xaRes.end(xid, XAResource.TMSUCCESS);
            ret = xaRes.prepare(xid);
            if (ret == XAResource.XA_OK) {
                xaRes.commit(xid, false);
            }
        } catch (XAException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
