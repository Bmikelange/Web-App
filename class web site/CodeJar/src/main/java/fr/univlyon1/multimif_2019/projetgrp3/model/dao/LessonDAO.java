package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Lesson;

import javax.persistence.EntityManager;

public class LessonDAO extends DAO<Lesson, String> {
    public LessonDAO(EntityManager entityManager, Class<Lesson> entityClass) {
        super(entityManager, entityClass);
    }
}
