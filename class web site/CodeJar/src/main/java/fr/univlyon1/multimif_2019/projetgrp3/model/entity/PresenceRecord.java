package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "presence_record")
public class PresenceRecord {

    @EmbeddedId
    protected PresenceRecordPK presenceRecordPK;

    @JoinColumn(name = "lesson_id", referencedColumnName = "lesson_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    protected Lesson lesson;

    @Column(name = "presence")
    protected boolean presence;

    @JoinColumn(name = "student_id", referencedColumnName = "student_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    protected Student student;

    public PresenceRecord() {
        this.presence = false;
    }

    public PresenceRecordPK getPresenceRecordPK() {
        return presenceRecordPK;
    }

    public PresenceRecord setPresenceRecordPK(PresenceRecordPK presenceRecordPK) {
        this.presenceRecordPK = presenceRecordPK;
        return this;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public PresenceRecord setLesson(Lesson lesson) {
        lesson = lesson;
        return this;
    }

    public boolean isPresence() {
        return presence;
    }

    public PresenceRecord setPresence(boolean presence) {
        this.presence = presence;
        return this;
    }

    public Student getStudent() {
        return student;
    }

    public PresenceRecord setStudent(Student student) {
        this.student = student;
        return this;
    }
}
