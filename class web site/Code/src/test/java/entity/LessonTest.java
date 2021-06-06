package entity;

import fr.univlyon1.multimif_2019.projetgrp3.model.dao.DAOFactory;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.LessonDAO;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Lesson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LessonTest {

    private Lesson lesson;

    private LocalDate localDate;
    private List<Lesson> l;

    @Before
    public void initAttribute(){
        this.localDate = LocalDate.now();

        this.lesson = new Lesson();
        lesson
                .setId("id")
                .setDate(this.localDate)
                .setName("name")
                .setDuration(4);
    }
    
    /*
    @Before
    public void InitilisationLesson() {
        LessonDAO lessonDao = DAOFactory.getLessonDAO();
        l = lessonDao.getLessonDATE("5050");
    }

    @Test
    public void VerificationRequeteDAOLesson() {
        assertEquals(0 , "2609".compareTo((String)l.get(0).getId()));
        assertEquals(6 , l.get(0).getDuration());
        assertEquals(0,"Enfin".compareTo(l.get(0).getName()));
    }

    @Test
    public void VerificationRequeteDAOLesson2() {
        assertEquals(0 , "2615".compareTo((String)l.get(1).getId()));
        assertEquals(4 , l.get(1).getDuration());
        assertEquals(0,"Mif03".compareTo(l.get(1).getName()));
    }*/


    @Test
    public void createLesson() {
        Lesson lessons = new Lesson();
        lessons
                .setId("id")
                .setDate(LocalDate.now())
                .setName("name")
                .setDuration(4);
    }

    @Test
    public void getIdTest() {
        assertEquals("id", this.lesson.getId());
    }

    @Test
    public void setIdtest() {
        Lesson lesson1 = this.lesson;
        lesson1.setId("newId");

        assertEquals("newId", lesson1.getId());
    }

    @Test
    public void getDateTest() {
        assertEquals(this.localDate, lesson.getDate());
    }

    @Test
    public void setDateTest() {
        Lesson lesson1 = this.lesson;
        LocalDate newLocalDate = LocalDate.now();

        lesson1.setDate(newLocalDate);

        assertEquals(newLocalDate, lesson1.getDate());
    }

    @Test
    public void getDurationTest() {
        assertEquals(4, this.lesson.getDuration());
    }

    @Test
    public void setDurationTest() {
        Lesson lesson1 = this.lesson;

        lesson1.setDuration(3);

        assertEquals(3, lesson1.getDuration());
    }

    @Test
    public void getNameTest() {
        assertEquals("name", this.lesson.getName());
    }

}
