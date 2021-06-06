package entity;

import fr.univlyon1.multimif_2019.projetgrp3.model.dao.DAOFactory;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Lesson;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Professor;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.ProfessorLesson;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.ProfessorLessonId;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProfessorLessonTest {
    /*private ProfessorLesson professorLesson;

    @Before
    public void initProfessorLesson() {
        this.professorLesson = new ProfessorLesson();
        this.professorLesson.setLesson(new Lesson());
        this.professorLesson.setProfessor(new Professor());
        //this.professorLesson.setProfessorLessonId();

    }


    @Before
    public void InitilisationPresenceRecord() {
        ProfessorLessonId pli = new ProfessorLessonId();
        pli.idProfessor = "5050";
        pli.idLesson = "2609";
        professorLesson = (ProfessorLesson) DAOFactory.getProfessorLessonDAO().findById(pli);
    }

    @Test
    public void VerificationRequeteDAOProfessorLesson() {
        assertEquals(0,professorLesson.getProfessor().getId().compareTo("5050"));
        assertEquals(0,professorLesson.getLesson().getId().compareTo("2609"));
    }*/
}
