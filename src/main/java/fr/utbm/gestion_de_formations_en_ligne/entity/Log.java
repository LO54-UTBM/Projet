/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.gestion_de_formations_en_ligne.entity;

import java.util.Date;

/**
 *
 * @author Eddie
 */
public class Log implements java.io.Serializable {
    
    private Date dates;
    private String student;

    public Log(){
    }
    
    public Log(Date date, String student) {
        this.dates = date;
        this.student = student;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Log{" + "date=" + dates + ", student=" + student + '}';
    }
    
}
