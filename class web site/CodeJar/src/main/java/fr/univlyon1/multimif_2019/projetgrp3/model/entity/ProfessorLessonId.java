package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProfessorLessonId implements Serializable {

    @Basic(optional = false)
    @Column(name = "prof_id")
    protected String idProfessor;

    @Basic(optional = false)
    @Column(name = "lesson_id")
    protected String idLesson;
}
