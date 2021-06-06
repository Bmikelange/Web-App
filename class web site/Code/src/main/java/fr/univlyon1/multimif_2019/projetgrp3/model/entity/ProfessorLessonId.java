package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Representthe id of the ProfessorLesson entity.
 */
@Embeddable
public class ProfessorLessonId implements Serializable {

    /**
     * Id of the professor linked to ProfessorLesson.
     */
    @Basic(optional = false)
    @Column(name = "prof_id")
    public String idProfessor;

    /**
     * Id of the lesson linked to ProfessorLesson.
     */
    @Basic(optional = false)
    @Column(name = "lesson_id")
    public String idLesson;
}
