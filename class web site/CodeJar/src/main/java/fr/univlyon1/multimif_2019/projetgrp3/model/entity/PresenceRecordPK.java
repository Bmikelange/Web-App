package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PresenceRecordPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "lesson_id")
    protected String idLesson;

    @Basic(optional = false)
    @Column(name = "student_id")
    protected String idStudent;

    public String getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(String idLesson) {
        this.idLesson = idLesson;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }
}
