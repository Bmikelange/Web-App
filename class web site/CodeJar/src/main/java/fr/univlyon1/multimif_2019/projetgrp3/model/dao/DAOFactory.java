package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DAOFactory {

    protected final static EntityManager entityManager =
            Persistence.createEntityManagerFactory("projet")
                                                                    .createEntityManager();

    public static DAO getStudentDAO() {
        return new StudentDAO(entityManager, Student.class);
    }

    public static LessonDAO getLessonDAO() {
        return new LessonDAO(entityManager, Lesson.class);
    }

    public static DAO getPresenceRecordDAO() {
        return new PresenceRecordDAO(entityManager, PresenceRecord.class);
    }

    public static DAO getProfessorDAO() {
        return new ProfessorDAO(entityManager, Professor.class);
    }

    public static DAO getSecretaryDAO() {
        return new SecretaryDAO(entityManager, Secretary.class);
    }

    public static DAO getProfessorLessonDAO() {
        return new ProfessorLessonDAO(entityManager, ProfessorLesson.class);
    }

}
