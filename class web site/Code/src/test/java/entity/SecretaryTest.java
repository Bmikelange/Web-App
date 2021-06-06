package entity;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Encryptor;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Secretary;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.security.NoSuchAlgorithmException;
import java.util.Set;


import static org.junit.Assert.assertEquals;


public class SecretaryTest {

    private Secretary secretary;

    @Before
    public void initSecretary() {
        this.secretary = new Secretary();
        this.secretary
                .setFirstName("foo")
                .setId("imasecretary")
                .setName("toto")
                .setPassword("secretary");
    }

    @Test
    public void createSecretary() {
        Secretary secretary = new Secretary();
        secretary.setId("p1234567");
        assertEquals("p1234567", secretary.getId());
    }
    
    @Test
    public void encryptPassword() {
        Secretary secretary = new Secretary();

        //Check if algorithm that encrypt the password isn't random
        secretary.setPassword("toto");
        String encryptResultSalted = "";
        try {
            encryptResultSalted = Encryptor.encryptSha("toto");
        }
        catch(Exception e) {
            
        }
        assertEquals(secretary.getPassword(), encryptResultSalted);
    }

    @Test
    public void validatorIsIdNotNull() {
        Secretary secretary = new Secretary();
        secretary.setId(null)
                .setFirstName("toto")
                .setName("toto")
                .setPassword("tatfdhdfhdo");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Secretary>> constraintViolations = validator.validate( secretary );

        assertEquals( 1, constraintViolations.size() );
    }
    @Test
    public void validatorIsPasswordNotNull() {
        Secretary secretary = new Secretary();
        secretary.setId("p1234567")
                .setFirstName("toto")
                .setName("toto")
                .setPassword(null);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Secretary>> constraintViolations = validator.validate( secretary );

        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void validatorRegexpId() {
        Secretary secretary = new Secretary();
        secretary.setId("p1234567dfgfdqbgdfqbdfbdqfb")
                .setFirstName("toto")
                .setName("toto")
                .setPassword("nullfsjfgj");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Secretary>> constraintViolations = validator.validate( secretary );

        assertEquals( 0, constraintViolations.size() );

        Secretary secretary2 = new Secretary();
        secretary2.setId("p1602520")
                .setFirstName("toto")
                .setName("toto")
                .setPassword("nullfhdfh");

        constraintViolations.clear();
        constraintViolations = validator.validate( secretary2 );
        assertEquals( 0, constraintViolations.size() );

        Secretary secretary3 = new Secretary();
        secretary3.setId("11602520")
                .setFirstName("toto")
                .setName("toto")
                .setPassword("nulldfhdfh");

        constraintViolations.clear();
        constraintViolations = validator.validate( secretary3 );
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void getIdTest() {
        Secretary secret = this.secretary;
        assertEquals("imasecretary", secret.getId());
    }

    @Test
    public void setIdTest() {
        Secretary secret = this.secretary;
        String newId = "id";
        secret.setId(newId);
        assertEquals(secret.getId(), newId);
    }

    @Test
    public void getPasswordTest() {
        Secretary secret = this.secretary;
        String pwd = null;
        try {
            pwd = Encryptor.encryptSha("secretary");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assertEquals(secret.getPassword(), pwd);
    }

    @Test
    public void setPasswordTest() {
        Secretary secret = this.secretary;
        String pwd = null;
        try {
            pwd = Encryptor.encryptSha("password");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        secret.setPassword("password");
        assertEquals(secret.getPassword(), pwd);
    }

    @Test
    public void getNameTest() {
        assertEquals("toto" , this.secretary.getName());
    }

    @Test
    public void setNameTest() {
        Secretary secret = this.secretary;
        secret.setName("name");
        assertEquals("name", secret.getName());
    }

    @Test
    public void getFirstNameTest() {
        assertEquals("foo", this.secretary.getFirstName());
    }

    @Test
    public void setFirstNameTest() {
        Secretary secret = this.secretary;
        secret.setFirstName("name");
        assertEquals("name", secret.getFirstName());
    }

}
