package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import fr.univlyon1.multimif_2019.projetgrp3.constante.ConstLogin;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represent a Student entity.
 */
@Entity
@Table(name = "student")
public class Student{

    /**
     * Id of the class.
     */
    @NotNull
    @Id
    @Pattern(regexp = "(p|1)[0-9]{7}")
    @Column(name = "student_id")
    protected String id;

    /**
     * Password of the student.
     */
    @NotNull
    @Column(name = "password", nullable = false)
    protected String password;

    /**
     * Name of the student.
     */
    @Column(name = "name")
    protected String name;

    /**
     * First name of the student.
     */
    @Column(name = "first_name")
    protected String firstName;

    /**
     * Graduation level of the student.
     */
    @Column(name = "class")
    protected String graduation;

    /**
     * Every presence records of the student.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    protected Collection<PresenceRecord> presencesRecords;

    /**
     * Constructor.
     */
    public Student() {
        this.presencesRecords = new ArrayList<>();
        this.id="0";
        this.password="";
    }

    /**
     * Id's getter.
     *
     * @return the current id of the class.
     */
    public String getId() {
        return id;
    }

    /**
     * Id's setter.
     *
     * @param id the new id we want to set.
     *
     * @return the entity after changes.
     */
    public Student setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Password's getter.
     *
     * @return the current password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password's setter.
     *
     * @param password the new encrypted password we want to set.
     *
     * @return the entity after changes.
     */
    public Student setPassword(String password) {
        Logger logger = Logger.getLogger("test");
        try {
            this.password = Encryptor.encryptSha(password);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, ConstLogin.ERROR, e);
        }
        return this;
    }

    /**
     * Names's getter.
     *
     * @return current name.
     */
    public String getName() {
        return name;
    }

    /**
     * Name's setter.
     *
     * @param name the new name we want to set.
     *
     * @return the entity after changes.
     */
    public Student setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * FirstName's getter.
     *
     * @return the current fistName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * FirstName's setter.
     *
     * @param firstName the new FirstName we want to set.
     *
     * @return the entity after changes.
     */
    public Student setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Graduation's getter.
     *
     * @return the current graduation.
     */
    public String getGraduation() {
        return graduation;
    }

    /**
     * Graduation's setter.
     *
     * @param graduation the new graduation we want to set.
     *
     * @return the entity after changes.
     */
    public Student setGraduation(String graduation) {
        this.graduation = graduation;
        return this;
    }

    /**
     * PresencesRecord' sgetter.
     *
     * @return the current collection of presencesRecords.
     */
    public Collection<PresenceRecord> getPresencesRecords() {
        return presencesRecords;
    }

    /**
     * Add apresenceRecord to presencesRecords
     *
     * @param presenceRecord the presenceRecord we want to add.
     *
     * @return the entity after changes.
     */
    public Student addPresencesRecords(PresenceRecord presenceRecord) {
        this.presencesRecords.add(presenceRecord);
        return this;
    }

    /**
     * Remove a presenceRecord for presencesRecords.
     *
     * @param presenceRecord the presenceRecord we want to remove.
     *
     * @return the entity after changes.
     */
    public Student removeFromPresencesRecords(PresenceRecord presenceRecord) {
        this.presencesRecords.remove(presenceRecord);
        return this;
    }
}
