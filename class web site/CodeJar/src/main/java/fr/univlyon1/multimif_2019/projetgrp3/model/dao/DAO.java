package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.function.Consumer;

public abstract class DAO<E, K> {

    EntityManager entityManager;

    Class<E> entityClass;

    public DAO(EntityManager entityManager, Class<E> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public boolean persist(E entity) {
        if (!this.validateEntity(entity))
            return false;
        executeInsideTransaction(toto -> entityManager.persist(entity));
        return true;
    }

    public boolean remove(E entity) {
        executeInsideTransaction(entityManager -> entityManager.remove(entity));
        return true;
    }

    public boolean update(E entity) {
        executeInsideTransaction(entityManager -> entityManager.merge(entity));
        return true;
    }

    public E findById(K id) {
        return entityManager.find(entityClass, id);
    }

    protected void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    public void mergeOrPersist (E entity, K id) {
        E entityFound = this.findById(id);
        if(entityFound==null)
            this.persist(entity);
        else
            this.update(entity);
    }

    //TODO refractor : voir si on peut utiliser ca en tant que service comme ne PHP plutot
    protected boolean validateEntity(E entity) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<E>> constraintViolations = validator.validate( entity );

        if (!constraintViolations.isEmpty())
            return false;
        else
            return true;
    }
}
