package fr.utbm.gestion_de_formations_en_ligne.entity;
// Generated 4 avr. 2016 22:24:00 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Course generated by hbm2java
 */
public class Course implements java.io.Serializable {

    private String code;
    private String title;
    private String description;
    private String picture;
    private Set courseSessions = new HashSet(0);

    public Course() {
    }

    public Course(String code, String title, String description, String picture) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.picture = picture;
    }

    public Course(String code, String title, String description, String picture, Set courseSessions) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.courseSessions = courseSessions;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Set getCourseSessions() {
        return this.courseSessions;
    }

    public void setCourseSessions(Set courseSessions) {
        this.courseSessions = courseSessions;
    }

    @Override
    public String toString() {
        return "Course{" + "code=" + code + ", title=" + title + ", description=" + description + ", picture=" + picture + ", courseSessions=" + courseSessions + '}';
    }

}
