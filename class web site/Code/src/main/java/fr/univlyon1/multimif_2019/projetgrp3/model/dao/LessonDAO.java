package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Lesson;
import javax.persistence.*;

import java.util.List;
import java.time.LocalDate;

/**
 * Manages interactions between database and Lesson's entities.
 */
public class LessonDAO extends DAO<Lesson, String> {

    /**
     * Constructor.
     *
     * @param entityManager the entity manager we need to build the Lesson's DAO.
     *
     * @param entityClass the Lesson's .class.
     */
    public LessonDAO(EntityManager entityManager, Class<Lesson> entityClass) {
        super(entityManager, entityClass);
    }

    /**
     * Get the list of Lessons taught by a professor.
     *
     * @param value id of the professor who teaches the lessons.
     *
     * @return an array of the lessons taught by the professor.
     */
    public List<Lesson> getLessonDATE(String value) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.clear();
        transaction.commit();
        String s ="SELECT l FROM Lesson l JOIN l.teachedByProfessor pl WHERE pl.professorLessonId.idProfessor = :param1" /*and l.date = \'" + LocalDate.now().toString()*/;
        TypedQuery<Lesson> lesson = entityManager.createQuery(s, Lesson.class);
        lesson.setParameter("param1",value);
        return lesson.getResultList();
    }
}
