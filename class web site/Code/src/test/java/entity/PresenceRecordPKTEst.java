package entity;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.PresenceRecordPK;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PresenceRecordPKTEst {

    private PresenceRecordPK presenceREcordPk;

    @Before
    public void initialize() {
        this.presenceREcordPk = new PresenceRecordPK();
        presenceREcordPk.setIdStudent("idStudent");
        presenceREcordPk.setIdLesson("idLesson");
    }

    @Test
    public void createPresenceRecordPKTest() {
        PresenceRecordPK presenceREcordPk1 = new PresenceRecordPK();
        presenceREcordPk1.setIdStudent("idStudent");
        presenceREcordPk1.setIdLesson("idLesson");
    }

    @Test
    public void getIdLesson() {
        assertEquals("idLesson", this.presenceREcordPk.getIdLesson());
    }

    @Test
    public void getIdStudent() {
        assertEquals("idStudent", this.presenceREcordPk.getIdStudent());
    }
}
