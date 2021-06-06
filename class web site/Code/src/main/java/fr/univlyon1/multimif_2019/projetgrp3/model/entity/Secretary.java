package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import fr.univlyon1.multimif_2019.projetgrp3.constante.ConstLogin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represent a Secretary entity.
 */
@Entity
@Table(name = "secretary")
public class Secretary {

    /**
     * Id of the class.
     */
    @NotNull
    @Id
    @Column(name = "secret_id")
    protected String id;

    /**
     * Password of the secretary.
     */
    @NotNull
    @Column(name = "password", nullable = false)
    protected String password;

    /**
     * Secretary's Name.
     */
    @Column(name = "name")
    protected String name;

    /**
     * Secretary's First name .
     */
    @Column(name = "first_name")
    protected String firstName;

    /**
     * Id's getter.
     *
     * @return the current's class id.
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
    public Secretary setId(String id) {
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
    public Secretary setPassword(String password) {
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
     *
     * @return the current name.
     */
    public String getName() {
        return name;
    }

    /**
     * Names's setter.
     *
     * @param name the new name we wanna set.
     *
     * @return the entity after changes.
     */
    public Secretary setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * FirstName's getter.
     *
     * @return the current firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * FirstName's setter.
     *
     * @param firstName the new first name we wanna set.
     *
     * @return the entity after changes.
     */
    public Secretary setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
}
