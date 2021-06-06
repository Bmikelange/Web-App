package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Secretary;

import javax.persistence.EntityManager;

public class SecretaryDAO extends DAO<Secretary, String> {
    public SecretaryDAO(EntityManager entityManager, Class<Secretary> entityClass) {
        super(entityManager, entityClass);
    }
}
