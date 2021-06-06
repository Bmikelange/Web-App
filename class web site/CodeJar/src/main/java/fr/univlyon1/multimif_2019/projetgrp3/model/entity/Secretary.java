package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "secretary")
public class Secretary {

    @NotNull
    @Id
    @Column(name = "secret_id")
    protected String id;

    @NotNull
    @Column(name = "password", nullable = false)
    protected String password;

    @Column(name = "name")
    protected String name;

    @Column(name = "first_name")
    protected String firstName;

    //TODO lier le secretaire aux etudiants
    //TODO le faire aussi en BD

    public String getId() {
        return id;
    }

    public Secretary setId(String id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Secretary setPassword(String password) throws NoSuchAlgorithmException {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public Secretary setName(String name) {
        this.name = name;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Secretary setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
}
