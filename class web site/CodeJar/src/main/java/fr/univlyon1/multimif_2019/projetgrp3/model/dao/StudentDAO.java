package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Student;

import javax.persistence.EntityManager;

public class StudentDAO extends DAO<Student,String> {

    public StudentDAO(EntityManager entityManager, Class<Student> entityClass) {
        super(entityManager, entityClass);
    }
}
