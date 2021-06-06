package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.function.Consumer;

/**
 * The goal of this class is to persist the Entities into the database.
 * @param <E> the entity type we want to manipulate
 * @param <K> the id type of the entity
 */
public abstract class DAO<E, K> {

    /**
     * Instance of entityManager that allow us to do transaction with the database.
     */
    EntityManager entityManager;

    /**
     * The class of the entity.
     */
    Class<E> entityClass;

    /**
     * Constructor.
     *
     * @param entityManager an entityManager that allows us to manipulate entities within the database
     *
     * @param entityClass the class of the entity.
     */
    public DAO(EntityManager entityManager, Class<E> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    /**
     * EntityManager's getter.
     *
     * @return the current state of the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Persist an entity into the database via the entityManager.
     * Note : the entity must be validated by a validator to be persist.
     *
     * @param entity the entity we want to persist.
     *
     * @return false if the entity isn't valid | true if entity is successfully saved in database.
     */
    public boolean persist(E entity) {
        if (!this.validateEntity(entity))
            return false;
        executeInsideTransaction(em -> entityManager.persist(entity));
        return true;
    }

    /**
     * Remove an entity form the database.
     *
     * @param entity the entity we want to remove.
     *
     * @return true.
     */
    public boolean remove(E entity) {
        executeInsideTransaction(em -> entityManager.remove(entity));
        return true;
    }

    /**
     * Update an entity in the database.
     *
     * @param entity the entity we want to update.
     *
     * @return true.
     */
    public boolean update(E entity) {
        executeInsideTransaction(em -> entityManager.merge(entity));
        return true;
    }

    /**
     * Find an entity using it's id in the database.
     *
     * @param id the id of the entity we want to find.
     *
     * @return the entity that we found | null if entity is not present in the database.
     */
    public E findById(K id) {
        return entityManager.find(entityClass, id);
    }

    /**
     * Executes an action using the entityManager inside a transaction.
     *
     * @param action a function of an entityManager.
     */
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

    /**
     * Validate an entity if it's parameters are correct.
     *
     * @param entity the entity we want to validate.
     *
     * @return true if the entity's parameters correspond to the requirement we've set | false otherwise.
     */
    protected boolean validateEntity(E entity) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<E>> constraintViolations = validator.validate( entity );

        return constraintViolations.isEmpty();
    }
}
