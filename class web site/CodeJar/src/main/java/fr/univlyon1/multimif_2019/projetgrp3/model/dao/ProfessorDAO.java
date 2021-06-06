package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Professor;

import javax.persistence.EntityManager;

public class ProfessorDAO extends DAO<Professor,String>  {
    public ProfessorDAO(EntityManager entityManager, Class<Professor> entityClass) {
        super(entityManager, entityClass);
    }
}
