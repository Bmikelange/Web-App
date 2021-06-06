package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.PresenceRecord;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.PresenceRecordPK;
import javax.persistence.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Manages interactions between database and PresenceRecord's entities.
 */
public class PresenceRecordDAO extends DAO<PresenceRecord, PresenceRecordPK> {

    /**
     * Constructor.
     *
     * @param entityManager the entity manager we need to build the PresenceRecord's DAO.
     *
     * @param entityClass the PresenceRecord's .class.
     */
    public PresenceRecordDAO(EntityManager entityManager, Class<PresenceRecord> entityClass) {
        super(entityManager, entityClass);
    }

    /**
     * Update a PresenceRecord.
     *
     * @param idStudent the id of the presenceRecord's student's attribute.
     *
     * @param idLesson the id of the presenceRecord's lesson's attribute.
     */
    public void updateStudentPresence(String idStudent, String idLesson) {
        String prQuery;
        prQuery = "UPDATE PresenceRecord pr SET pr.presence = true WHERE pr.presenceRecordPK.idLesson='" + idLesson + "' AND pr.presenceRecordPK.idStudent='" + idStudent + "'";
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.createQuery(prQuery).executeUpdate();
        transaction.commit();
        transaction.begin();
        entityManager.clear();
        transaction.commit();
    }

    /**
     * Get a list of presenceRecord related to a student and a lesson.
     *
     * @param value id of the student.
     *
     * @param idLesson id of the lesson.
     *
     * @return a list of the presenceRecord.
     */
    public List<PresenceRecord> getStudentPresence(String value, String idLesson) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.clear();
        transaction.commit();
        String s ="SELECT pl FROM PresenceRecord pl WHERE pl.presenceRecordPK.idStudent = :param1 and pl.presenceRecordPK.idLesson = :param2" ;
        TypedQuery<PresenceRecord> presenceRecord = entityManager.createQuery(s, PresenceRecord.class);
        presenceRecord.setParameter("param1",value);
        presenceRecord.setParameter("param2",idLesson);
        return presenceRecord.getResultList();
    }    
}
