package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * The goal of this class is to instanciate DAO of entities.
 */
public class DAOFactory {

    /**
     * Default constructor.
     */
    private DAOFactory(){

    }

    /**
     * The entityManager needed to build DAOs of entities.
     */
    protected static final EntityManager entityManager =
            Persistence.createEntityManagerFactory("projet")
                                                                    .createEntityManager();

    /**
     * Build the Student's entity's DAO.
     *
     * @return a Student's entity's DAO.
     */
    public static DAO getStudentDAO() {
        return new StudentDAO(entityManager, Student.class);
    }

    /**
     * Build the Lesson's entity's DAO.
     *
     * @return a Lesson's entity's DAO.
     */
    public static LessonDAO getLessonDAO() {
        return new LessonDAO(entityManager, Lesson.class);
    }

    /**
     * Build the PresenceRecord's entity's DAO.
     *
     * @return a PresenceRecord's entity's DAO.
     */
    public static DAO getPresenceRecordDAO() {
        return new PresenceRecordDAO(entityManager, PresenceRecord.class);
    }

    /**
     * Build the Professor's entity's DAO.
     *
     * @return a Professor's entity's DAO.
     */
    public static DAO getProfessorDAO() {
        return new ProfessorDAO(entityManager, Professor.class);
    }

    /**
     * Build the Secretary's entity's DAO.
     *
     * @return a Secretary's entity's DAO.
     */
    public static DAO getSecretaryDAO() {
        return new SecretaryDAO(entityManager, Secretary.class);
    }

    /**
     * Build the ProfessorLesson's entity's DAO.
     *
     * @return a ProfessorLesson's entity's DAO.
     */
    public static DAO getProfessorLessonDAO() {
        return new ProfessorLessonDAO(entityManager, ProfessorLesson.class);
    }

}
