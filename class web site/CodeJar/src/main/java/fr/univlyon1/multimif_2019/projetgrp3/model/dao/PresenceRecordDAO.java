package fr.univlyon1.multimif_2019.projetgrp3.model.dao;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.PresenceRecord;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.PresenceRecordPK;

import javax.persistence.EntityManager;

public class PresenceRecordDAO extends DAO<PresenceRecord, PresenceRecordPK> {
    public PresenceRecordDAO(EntityManager entityManager, Class<PresenceRecord> entityClass) {
        super(entityManager, entityClass);
    }
}
