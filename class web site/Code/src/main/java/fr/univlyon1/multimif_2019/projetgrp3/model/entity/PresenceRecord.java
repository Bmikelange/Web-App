package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import javax.persistence.*;

/**
 * Represent a PresenceRecord's entity.
 */
@Entity
@Table(name = "presence_record")
public class PresenceRecord {

    /**
     * Id of the presenceRecord.
     */
    @EmbeddedId
    protected PresenceRecordPK presenceRecordPK;

    /**
     * Lesson of the pressenceRecord.
     */
    @JoinColumn(name = "lesson_id", referencedColumnName = "lesson_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    protected Lesson lesson;

    /**
     * Presence of the student during the lesson.
     */
    @Column(name = "presence")
    protected boolean presence;

    /**
     * Student of this presenceRecord.
     */
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    protected Student student;

    /**
     * Constructor.
     */
    public PresenceRecord() {
        this.presence = false;
    }

    /**
     * Id's getter.
     * @return the id of this class.
     */
    public PresenceRecordPK getPresenceRecordPK() {
        return presenceRecordPK;
    }

    /**
     * Id's setter.
     *
     * @param presenceRecordPK the id we want to set.
     *
     * @return the entity after changes.
     */
    public PresenceRecord setPresenceRecordPK(PresenceRecordPK presenceRecordPK) {
        this.presenceRecordPK = presenceRecordPK;
        return this;
    }

    /**
     * Lesson's getter.
     *
     * @return the lesson linked to this entity.
     */
    public Lesson getLesson() {
        return lesson;
    }

    /**
     * Lesson's setter.
     *
     * @param lesson the new lesson we want to set.
     *
     * @return the entity after changes.
     */
    public PresenceRecord setLesson(Lesson lesson) {
        this.lesson = lesson;
        return this;
    }

    /**
     * Verifies student's presence.
     *
     * @return true if the student was present to the lesson | false otherwise.
     */
    public boolean isPresence() {
        return presence;
    }

    /**
     * Presence's setter.
     *
     * @param presence the presence we want to set.
     *
     * @return the entity after changes.
     */
    public PresenceRecord setPresence(boolean presence) {
        this.presence = presence;
        return this;
    }

    /**
     * Student's getter.
     *
     * @return the student linked to this class.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Student 's setter.
     *
     * @param student the student we wanna set.
     *
     * @return the entity after changes.
     */
    public PresenceRecord setStudent(Student student) {
        this.student = student;
        return this;
    }
}
