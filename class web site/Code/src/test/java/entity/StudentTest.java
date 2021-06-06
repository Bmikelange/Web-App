package entity;


import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Encryptor;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Student;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.security.NoSuchAlgorithmException;
import java.util.Set;


import static org.junit.Assert.assertEquals;


public class StudentTest {

    private Student student;


    @Before
    public void initStudent() {
        this.student = new Student();
        this.student
                .setFirstName("titi")
                .setId("2222")
                .setName("toto")
                .setPassword("student");
    }

   @Test
    public void createStudent() {
        Student student = new Student();
        student.setId("p1234567");
        assertEquals("p1234567", student.getId());
    }

    @Test
    public void encryptPassword() {
        Student student = new Student();

        //Check if algorithm that encrypt the password isn't random
        student.setPassword("toto");
        String encryptResultSalted = "";
        try {
            encryptResultSalted = Encryptor.encryptSha("toto");
        }
        catch(Exception e) {
            //TODO print the exception
        }
        assertEquals(student.getPassword(), encryptResultSalted);
    }

    @Test
    public void validatorIsIdNotNull() {
    Student student = new Student();
    student.setId(null)
            .setFirstName("toto")
            .setGraduation("m1")
            .setName("toto")
            .setPassword("tatfdhdfhdo");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate( student );

        assertEquals( 1, constraintViolations.size() );
    }

    @Test
    public void validatorIsPasswordNotNull() {
        Student student = new Student();
        student.setId("p1234567")
                .setFirstName("toto")
                .setGraduation("m1")
                .setName("toto")
                .setPassword(null);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate( student );

        assertEquals( 0, constraintViolations.size() );
    }


    @Test
    public void getIdTest() {
        Student student = this.student;
        assertEquals("2222", student.getId());
    }

    @Test
    public void setIdTest() {
        Student student = this.student;
        String newId = "id";
        student.setId(newId);
        assertEquals(student.getId(), newId);
    }

    @Test
    public void getPasswordTest() {
        Student student = this.student;
        String pwd = null;
        try {
            pwd = Encryptor.encryptSha("student");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assertEquals(student.getPassword(), pwd);
    }

    @Test
    public void setPasswordTest() {
        Student student = this.student;
        String pwd = null;
        try {
            pwd = Encryptor.encryptSha("password");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        student.setPassword("password");
        assertEquals(student.getPassword(), pwd);
    }

    @Test
    public void getNameTest() {
        assertEquals("toto" , this.student.getName());
    }

    @Test
    public void setNameTest() {
        Student student = this.student;
        student.setName("name");
        assertEquals("name", student.getName());
    }

    @Test
    public void getFirstNameTest() {
        assertEquals("titi", this.student.getFirstName());
    }

    @Test
    public void setFirstNameTest() {
        Student student = this.student;
        student.setFirstName("name");
        assertEquals("name", student.getFirstName());
    }


    @Test
    public void validatorRegexpId() {
        Student student = new Student();
        student.setId("p1234567dfgfdqbgdfqbdfbdqfb")
                .setFirstName("toto")
                .setGraduation("m1")
                .setName("toto")
                .setPassword("nullfsjfgj");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate( student );

        assertEquals( 1, constraintViolations.size() );

        Student student2 = new Student();
        student2.setId("p1602520")
                .setFirstName("toto")
                .setGraduation("m1")
                .setName("toto")
                .setPassword("nullfhdfh");

        constraintViolations.clear();
        constraintViolations = validator.validate( student2 );
        assertEquals( 0, constraintViolations.size() );

        Student student3 = new Student();
        student3.setId("11602520")
                .setFirstName("toto")
                .setGraduation("m1")
                .setName("toto")
                .setPassword("nulldfhdfh");

        constraintViolations.clear();
        constraintViolations = validator.validate( student3 );
        assertEquals( 0, constraintViolations.size() );
    }



    @Test
    public void testIdGetter () {
        Student student = new Student();
        student.setId("p1602520")
                .setFirstName("toto")
                .setGraduation("m1")
                .setName("toto")
                .setPassword("tatfdhdfhdo");

        assertEquals("p1602520" , student.getId());
    }

    @Test
    public void testIdSetter() {
        Student student = new Student();
        student.setId("p1602520")
                .setFirstName("toto")
                .setGraduation("m1")
                .setName("toto")
                .setPassword("tatfdhdfhdo");
        String newId = "11602520";
        student.setId(newId);
        assertEquals(student.getId(), newId);
    }

    @Test
    public void testPaswordGetter () {
        Student student = new Student();
        student.setId("p1602520")
                .setFirstName("toto")
                .setGraduation("m1")
                .setName("toto")
                .setPassword("tatfdhdfhdo");

        String password = null;
        try {
            password = Encryptor.encryptSha("tatfdhdfhdo");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assertEquals(student.getPassword(), password);
    }
    @Test
    public void testPasswordSetter() {
        Student student = new Student();
        student.setId("p1602520")
                .setFirstName("toto")
                .setGraduation("m1")
                .setName("toto")
                .setPassword("tatfdhdfhdo");

        String password = null;
        try {
            password = Encryptor.encryptSha("password");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        student.setPassword("password");
        assertEquals(student.getPassword(), password);
    }

    @Test
    public void testNameGetter () {
        Student student = new Student();
        student.setId("p1602520")
                .setFirstName("toto")
                .setGraduation("m1")
                .setName("toto")
                .setPassword("tatfdhdfhdo");

        assertEquals("toto" , student.getName());
    }

    @Test
    public void testNameSetter() {
        Student student = new Student();
        student.setId("p1602520")
                .setFirstName("tutu")
                .setGraduation("m1")
                .setName("toto")
                .setPassword("tatfdhdfhdo");

        student.setName("tata");
        assertEquals("tata" , student.getName());
    }

    @Test
    public void testFirstNameGetter() {
        Student student = new Student();
        student.setId("p1602520")
                .setFirstName("tutu")
                .setGraduation("m1")
                .setName("toto")
                .setPassword("tatfdhdfhdo");

        assertEquals("tutu" , student.getFirstName());
    }

    @Test
    public void testFirstNameSetter() {
        Student student = new Student();
        student.setId("p1602520")
                .setFirstName("tutu")
                .setGraduation("m1")
                .setName("toto")
                .setPassword("tatfdhdfhdo");

        student.setFirstName("tata");
        assertEquals("tata" , student.getFirstName());
    }

    @Test
    public void testGraduationGetter() {
        Student student = new Student();
        student.setId("p1602520")
                .setFirstName("tutu")
                .setGraduation("m1")
                .setName("toto")
                .setPassword("tatfdhdfhdo");

        assertEquals("m1",student.getGraduation());
    }

    @Test
    public void testGraduationSetter() {
        Student student = new Student();
        student.setId("p1602520")
                .setFirstName("tutu")
                .setGraduation("m1")
                .setName("toto")
                .setPassword("tatfdhdfhdo");

        student.setGraduation("l3");
        assertEquals("l3",student.getGraduation());
    }

}
