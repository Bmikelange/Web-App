package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "professor_lesson")
public class ProfessorLesson {
    @EmbeddedId
    protected ProfessorLessonId professorLessonId;

    @JoinColumn(name = "lesson_id", referencedColumnName = "lesson_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    protected Lesson lesson;

    @JoinColumn(name = "prof_id", referencedColumnName = "prof_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    protected Professor professor;

    public ProfessorLessonId getProfessorLessonId() {
        return professorLessonId;
    }

    public void setProfessorLessonId(ProfessorLessonId professorLessonId) {
        this.professorLessonId = professorLessonId;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
