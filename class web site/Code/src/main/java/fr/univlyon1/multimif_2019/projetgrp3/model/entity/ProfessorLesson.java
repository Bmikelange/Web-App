package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import javax.persistence.*;

/**
 * Represent a Link between a Professor's entity and a Lesson's entity.
 */
@Entity
@Table(name = "professor_lesson")
public class ProfessorLesson {
    /**
     * Id of the class.
     */
    @EmbeddedId
    protected ProfessorLessonId professorLessonId;

    /**
     * Lesson that is linked to a professor.
     */
    @JoinColumn(name = "lesson_id", referencedColumnName = "lesson_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    protected Lesson lesson;

    /**
     * Professor in charge of the lesson.
     */
    @JoinColumn(name = "prof_id", referencedColumnName = "prof_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    protected Professor professor;

    /**
     * Id's getter.
     *
     * @return the id of the class.
     */
    public ProfessorLessonId getProfessorLessonId() {
        return professorLessonId;
    }

    /**
     * Id's setter.
     *
     * @param professorLessonId the new id we want to set.
     */
    public void setProfessorLessonId(ProfessorLessonId professorLessonId) {
        this.professorLessonId = professorLessonId;
    }

    /**
     * Lesson's getter.
     *
     * @return the current Lesson.
     */
    public Lesson getLesson() {
        return lesson;
    }

    /**
     * Lesson's setter.
     *
     * @param lesson the new lesson we want to set.
     */
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    /**
     * Professor's getter.
     *
     * @return the current professor.
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * Professor's setter.
     *
     * @param professor the new professor we want to set.
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
