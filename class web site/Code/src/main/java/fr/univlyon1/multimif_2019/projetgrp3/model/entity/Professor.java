package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import fr.univlyon1.multimif_2019.projetgrp3.constante.ConstLogin;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represent a Professor's entity.
 */
@Entity
@Table(name = "professor")
public class Professor {

    /**
     * Id of the professor
     */
    @NotNull
    @Id
    @Column(name = "prof_id")
    protected String id;

    /**
     * Professor's password
     */
    @NotNull
    @Column(name = "password", nullable = false)
    protected String password;

    /**
     * Professor's name
     */
    @Column(name = "name")
    protected  String name;

    /**
     * Professor's first name
     */
    @Column(name ="first_name")
    protected String firstName;

    /**
     * Bidirectional link to ProfessorLesson associated to this professor.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    protected Collection<ProfessorLesson> teachedByProfessor;

    /**
     * Default constructor.
     */
    public Professor() {
        this.teachedByProfessor = new ArrayList<>();
        this.id="0";
        this.password="";
    }

    /**
     * Id's getter .
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Id's setter.
     * @param id the new id
     * @return updated entity
     */
    public Professor setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Password's getter.
     * @return current password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Password's setter.
     * @param password the new already encrypted password
     * @return updated entity
     */
    public Professor setPassword(String password){
        Logger logger = Logger.getLogger("test");
        try {
            this.password = Encryptor.encryptSha(password);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, ConstLogin.ERROR, e);
        }
        return this;
    }

    /**
     * Name's getter.
     * @return the name of the professor
     */
    public String getName() {
        return name;
    }

    /**
     * Name's setter.
     * @param name the new name
     * @return updated entity
     */
    public Professor setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * FirstName's getter.
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * FirstName's setter.
     * @param firstName the new first name
     * @return updated entity
     */
    public Professor setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * TeachedByProfessor's getter
     * @return every ProfessorLesson linked to lessons that are taught by the current professor
     */
    public Collection<ProfessorLesson> getTeachedByProfessor() {
        return teachedByProfessor;
    }

    /**
     * Add a link to a Lesson that is taught by the current professor.
     * @param professorLesson the new professorLesson
     * @return updated entity
     */
    public Professor addTeachedByProfessor(ProfessorLesson professorLesson) {
        this.teachedByProfessor.add(professorLesson);
        return this;
    }

    /**
     * Remove the link between a professor and a lesson.
     * @param professorLesson the old professorLesson
     * @return updated entity
     */
    public Professor removeFromTeachedByProfessor(ProfessorLesson professorLesson) {
        this.teachedByProfessor.remove(professorLesson);
        return this;
    }
}
