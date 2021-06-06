package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Secretary;

import javax.persistence.EntityManager;

/**
 * Manages interactions between database and Secretary's entities.
 */
public class SecretaryDAO extends DAO<Secretary, String> {

    /**
     * Constructor.
     *
     * @param entityManager the entity manager we need to build the Secretary's DAO.
     *
     * @param entityClass the Secretary's .class.
     */
    public SecretaryDAO(EntityManager entityManager, Class<Secretary> entityClass) {
        super(entityManager, entityClass);
    }
}
