package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Represent a PresecneRecord's primaryKey's entity.
 */
@Embeddable
public class PresenceRecordPK implements Serializable {

    /**
     * The id of the lesson linked to a presenceRecord.
     */
    @Basic(optional = false)
    @Column(name = "lesson_id")
    protected String idLesson;

    /**
     * The id of the student linked to this presenceRecord.
     */
    @Basic(optional = false)
    @Column(name = "student_id")
    protected String idStudent;

    /**
     * IdLesson's getter.
     *
     * @return Lesson's id.
     */
    public String getIdLesson() {
        return idLesson;
    }

    /**
     * LessonId's setter.
     *
     * @param idLesson the id of the lesson we wanna set.
     */
    public void setIdLesson(String idLesson) {
        this.idLesson = idLesson;
    }

    /**
     * StudentId's getter.
     *
     * @return the id of the student.
     */
    public String getIdStudent() {
        return idStudent;
    }

    /**
     * StudentId's setter.
     *
     * @param idStudent the new id of the student.
     */
    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }
}
