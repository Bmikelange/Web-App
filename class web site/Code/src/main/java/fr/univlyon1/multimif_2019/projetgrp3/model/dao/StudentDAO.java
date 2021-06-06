package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Student;

import java.util.List;
import javax.persistence.*;

/**
 * Manages interactions between database and Student's entities.
 */
public class StudentDAO extends DAO<Student,String> {

    /**
     * Constructor.
     *
     * @param entityManager the entity manager we need to build the Student's DAO.
     *
     * @param entityClass the Student's .class.
     */
    public StudentDAO(EntityManager entityManager, Class<Student> entityClass) {
        super(entityManager, entityClass);
    }

    /**
     * Get one student's presenceRecords.
     *
     * @param value id of the student.
     *
     * @return the student's presenceRecords.
     */
    public List<Student> getStudentContent(String value) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.clear();
        transaction.commit();
        String s ="SELECT DISTINCT s FROM Student s JOIN s.presencesRecords pl WHERE pl.presenceRecordPK.idStudent = :param1";
        TypedQuery<Student> student = entityManager.createQuery(s, Student.class);
        student.setParameter("param1",value);
        return student.getResultList();
    }

    /**
     * Get the list of a student's presenceRecord.
     *
     * @param value id of the student.
     *
     * @return the list of the student's presenceRecords ordered by PresenceRecord.presence.
     */
    public List<Student> getStudentbyID(String value) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.clear();
        transaction.commit();
        String s ="SELECT s FROM Student s JOIN s.presencesRecords pr WHERE s.id = :param1 ORDER BY pr.presence";
        TypedQuery<Student> student = entityManager.createQuery(s, Student.class);
        student.setParameter("param1",value);
        return student.getResultList();
    }

    /**
     * Get students from a given graduation.
     *
     * @param value graduation name.
     *
     * @return a list of student who are in the same grade.
     */
    public List<Student> getStudentbyGroupe(String value) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.clear();
        transaction.commit();
        String s ="SELECT DISTINCT s FROM Student s WHERE s.graduation = :param1";
        TypedQuery<Student> student = entityManager.createQuery(s, Student.class);
        student.setParameter("param1",value);
        return student.getResultList();
    }
}
