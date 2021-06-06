package entity;

import fr.univlyon1.multimif_2019.projetgrp3.model.dao.DAOFactory;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.PresenceRecord;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.PresenceRecordPK;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PresenceRecorderTest {
    private PresenceRecord pr;

    /*@Before
    public void InitilisationPresenceRecord() {
        PresenceRecordPK prk = new PresenceRecordPK();
        prk.setIdLesson("2609");
        prk.setIdStudent("11508966");
        pr = (PresenceRecord) DAOFactory.getPresenceRecordDAO().findById(prk);
    }

    @Test
    public void VerificationRequeteDAOPresenceRecord() {
        assertFalse( pr.isPresence());
        assertEquals(0,pr.getStudent().getId().compareTo("11508966"));
        assertEquals(0,pr.getLesson().getId().compareTo("2609"));
        pr.setPresence(true);
        assertTrue(pr.isPresence());
        pr.setPresence(false);
    } */
}
