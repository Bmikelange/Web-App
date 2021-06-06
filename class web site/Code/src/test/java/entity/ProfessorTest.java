package entity;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.*;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ProfessorTest {
    
    private Professor professor;
    
    @Before
    public void initProfessor() {
        this.professor = new Professor();
        this.professor
                .setFirstName("firstName")
                .setId("id")
                .setName("name")
                .setPassword("password");
    }
    
   @Test
    public void createProfessor() {
        Professor professor = new Professor();
        professor.setId("p1234567");
        assertEquals("p1234567",professor.getId());
    }

    @Test
    public void encryptPassword() {
        Professor professor = new Professor();

        //Check if algorithm that encrypt the password isn't random
        professor.setPassword("toto");
        String encryptResultSalted = "";
        try {
            encryptResultSalted = Encryptor.encryptSha("toto");
        }
        catch(Exception e) {
            //TODO print the exception
        }
        assertEquals(professor.getPassword(), encryptResultSalted);
    }

    @Test
    public void validatorIsIdNotNull() {
        Professor professor = new Professor();
        professor.setId(null)
                .setFirstName("toto")
                .setName("toto")
                .setPassword("tatfdhdfhdo");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Professor>> constraintViolations = validator.validate( professor );

        assertEquals( 1, constraintViolations.size() );
    }

    @Test
    public void validatorIsPasswordNotNull() {
        Professor professor = new Professor();
        professor.setId("p1234567")
                .setFirstName("toto")
                .setName("toto")
                .setPassword(null);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Professor>> constraintViolations = validator.validate( professor );

        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void validatorRegexpId() {
        Professor professor = new Professor();
        professor.setId("p1234567dfgfdqbgdfqbdfbdqfb")
                .setFirstName("toto")
                .setName("toto")
                .setPassword("nullfsjfgj");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Professor>> constraintViolations = validator.validate( professor );

        assertEquals( 0, constraintViolations.size() );

        Professor professor2 = new Professor();
        professor2.setId("p1602520")
                .setFirstName("toto")
                .setName("toto")
                .setPassword("nullfhdfh");

        constraintViolations.clear();
        constraintViolations = validator.validate( professor2 );
        assertEquals( 0, constraintViolations.size() );

        Professor professor3 = new Professor();
        professor3.setId("11602520")
                .setFirstName("toto")
                .setName("toto")
                .setPassword("nulldfhdfh");

        constraintViolations.clear();
        constraintViolations = validator.validate( professor3 );
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void getIdTest() {
        Professor prof = this.professor;
        assertEquals("id",prof.getId());
    }

    @Test
    public void setIdTest() {
        Professor prof = this.professor;
        String newId = "newId";
        prof.setId(newId);
        assertEquals(prof.getId(), newId);
    }

    @Test
    public void getPasswordTest() {
        Professor prof = this.professor;
        String pwd = null;
        try {
            pwd = Encryptor.encryptSha("password");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assertEquals(prof.getPassword(), pwd);
    }

    @Test
    public void setPasswordTest() {
        Professor prof = this.professor;
        String pwd = null;
        try {
            pwd = Encryptor.encryptSha("newPassword");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        prof.setPassword("newPassword");
        assertEquals(prof.getPassword(), pwd);
    }

    @Test
    public void getNameTest() {
        assertEquals("name" , this.professor.getName());
    }

    @Test
    public void setNameTest() {
        Professor prof = this.professor;
        prof.setName("newName");
        assertEquals("newName" , prof.getName());
    }

    @Test
    public void getFirstNameTest() {
        assertEquals("firstName" , this.professor.getFirstName());
    }

    @Test
    public void setFirstNameTest() {
        Professor prof = this.professor;
        prof.setFirstName("newFirstName");
        assertEquals("newFirstName" , prof.getFirstName());
    }

    @Test
    public void getTeachedByProfessorTest() {
        Professor professor1 = this.professor;
        ProfessorLesson professorLesson = new ProfessorLesson();
        professorLesson.setProfessor(professor1);
        professorLesson.setLesson(new Lesson());
        int size = professor1.getTeachedByProfessor().size();
        assertEquals(0, size);
    }

    @Test
    public void addTeachedByProfessorTest() {
        Professor professor1 = this.professor;
        ProfessorLesson professorLesson = new ProfessorLesson();
        professorLesson.setProfessor(professor1);
        professorLesson.setLesson(new Lesson());
        professor1.addTeachedByProfessor(professorLesson);

        assertEquals(true, professor1.getTeachedByProfessor().contains(professorLesson));
    }

    @Test
    public void removeTeachedByProfessorTest() {
        Professor professor1 = this.professor;
        ProfessorLesson professorLesson = new ProfessorLesson();
        professorLesson.setProfessor(professor1);
        professorLesson.setLesson(new Lesson());
        professor1.addTeachedByProfessor(professorLesson);
        professor1.removeFromTeachedByProfessor(professorLesson);

        assertEquals(false, professor1.getTeachedByProfessor().contains(professorLesson));
    }
}
