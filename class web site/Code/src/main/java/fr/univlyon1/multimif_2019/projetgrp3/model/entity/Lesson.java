package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represent a Lesson's entity.
 */
@Entity
@Table(name = "lesson")
public class Lesson {

    /**
     * Id of the Lesson.
     */
    @NotNull
    @Id
    @Column(name = "lesson_id")
    protected String id;

    /**
     * Date of the lesson.
     */
    @Column(name = "date")
    protected LocalDate date;

    /**
     * Duration of the lesson.
     */
    @Max(value = 24)
    @Min(value = 0)
    @Column(name = "duration")
    protected int duration;

    /**
     * Name of the lesson.
     */
    @Column(name = "name")
    protected String name;

    /**
     * Bidirectional link to PresenceRecord associate to this lesson.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    protected Collection<PresenceRecord> studentsPresent;

    /**
     * Bidirectional link to ProfessorLesson associate to this lesson
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    protected Collection<ProfessorLesson> teachedByProfessor;

    /**
     * Constructor.
     */
    public Lesson() {
        this.studentsPresent = new ArrayList<>();
        this.teachedByProfessor = new ArrayList<>();
        this.id="0";
    }

    /**
     * Id's getter.
     *
     * @return the current id.
     */
    public String getId() {
        return id;
    }

    /**
     * Id's setter.
     *
     * @param id the new id we want to set.
     *
     * @return the entity after changes.
     */
    public Lesson setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Date's getter.
     *
     * @return the current date of the lesson.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Date's setter.
     *
     * @param date the new date we want to set.
     *
     * @return the entity after changes.
     */
    public Lesson setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * Duration's getter.
     *
     * @return the current duration of the lesson.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Duration's setter.
     *
     * @param duration the new duration we want to set.
     *
     * @return the entity after changed
     */
    public Lesson setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Name's getter.
     *
     * @return the current name of the entity.
     */
    public String getName() {
        return name;
    }

    /**
     * Name's setter.
     *
     * @param name the new name we want to set.
     *
     * @return the entity after changes.
     */
    public Lesson setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * StudentsPresent's getter.
     *
     * @return every presenceRecord related to this lesson.
     */
    public Collection<PresenceRecord> getStudentsPresent() {
        return studentsPresent;
    }

    /**
     * add a PresenceRecord to the studentPresent attribute.
     *
     * @param presenceRecord the presencerecord we want to add.
     *
     * @return entity after changes.
     */
    public Lesson addStudentsPresent(PresenceRecord presenceRecord) {
        this.studentsPresent.add(presenceRecord);
        return this;
    }

    /**
     * Remove a presenceRecord from studentPresent.
     *
     * @param presenceRecord the presenceRecord we want to remove.
     *
     * @return entity after changes.
     */
    public Lesson removeFromStudentsPresent(PresenceRecord presenceRecord) {
        this.studentsPresent.remove(presenceRecord);
        return this;
    }

    /**
     * TeachedByProfessor 's getter.
     *
     * @return the professors in charge of this lesson.
     */
    public Collection<ProfessorLesson> getTeachedByProfessor() {
        return teachedByProfessor;
    }

    /**
     * Add a professorLesson to teachedByProfessor.
     *
     * @param professorLesson the professorLesson we want to add.
     *
     * @return the entity after changes.
     */
    public Lesson addTeachedByProfessor(ProfessorLesson professorLesson) {
        this.teachedByProfessor.add(professorLesson);
        return this;
    }

    /**
     * Remove a professor lesson from teachedByProfessor.
     *
     * @param professorLesson the professorLesson we  want to remove.
     *
     * @return the entity after changes.
     */
    public Lesson removeFromTeachedByProfessor(ProfessorLesson professorLesson) {
        this.teachedByProfessor.remove(professorLesson);
        return this;
    }
}
