package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Professor;

import javax.persistence.EntityManager;

/**
 * Manages interactions between database and Professor's entities.
 */
public class ProfessorDAO extends DAO<Professor,String>  {

    /**
     * Constructor.
     *
     * @param entityManager the entity manager we need to build the Professor's DAO.
     *
     * @param entityClass the Professor's .class.
     */
    public ProfessorDAO(EntityManager entityManager, Class<Professor> entityClass) {
        super(entityManager, entityClass);
    }
}
