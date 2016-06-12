/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.gestion_de_formations_en_ligne.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import oracle.jdbc.xa.client.*;

/**
 *
 * @author Ali
 */
public class JDBCConnectionDAO {

    public static XADataSource getOracleDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        OracleXADataSource oracleDS = null;
        try {
            fis = new FileInputStream("D:\\Netbeans1\\Utbm\\LO54\\Data\\Projects\\site_de_gestion_de_formations3\\Projet\\src\\main\\webapp\\ressources\\db.properties");
            props.load(fis);
            oracleDS = new OracleXADataSource();
            oracleDS.setURL(props.getProperty("ORACLE_DB_URL"));
            oracleDS.setUser(props.getProperty("ORACLE_DB_USERNAME"));
            oracleDS.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oracleDS;
    }

    public static XAConnection getOracleConnection() {
        try {
            return getOracleDataSource().getXAConnection();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnectionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
