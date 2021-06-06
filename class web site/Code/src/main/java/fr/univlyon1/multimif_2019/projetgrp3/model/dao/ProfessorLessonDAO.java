package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.ProfessorLesson;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.ProfessorLessonId;

import javax.persistence.EntityManager;

/**
 * Manages interactions between database and ProfessorLesson's entities.
 */
public class ProfessorLessonDAO extends DAO<ProfessorLesson, ProfessorLessonId> {

    /**
     * Constructor.
     *
     * @param entityManager the entity manager we need to build the ProfessorLesson's DAO.
     *
     * @param entityClass the ProfessorLesson's .class.
     */
    public ProfessorLessonDAO(EntityManager entityManager, Class<ProfessorLesson> entityClass) {
        super(entityManager, entityClass);
    }
}
