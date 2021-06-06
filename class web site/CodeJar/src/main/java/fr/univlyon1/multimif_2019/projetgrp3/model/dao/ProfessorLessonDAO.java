package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.ProfessorLesson;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.ProfessorLessonId;

import javax.persistence.EntityManager;

public class ProfessorLessonDAO extends DAO<ProfessorLesson, ProfessorLessonId> {
    public ProfessorLessonDAO(EntityManager entityManager, Class<ProfessorLesson> entityClass) {
        super(entityManager, entityClass);
    }
}
