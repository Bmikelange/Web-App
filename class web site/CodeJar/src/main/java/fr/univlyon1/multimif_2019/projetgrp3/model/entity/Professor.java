package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "professor")
public class Professor {

    @NotNull
    @Id
    @Column(name = "prof_id")
    protected String id;

    @NotNull
    @Column(name = "password", nullable = false)
    protected String password;

    @Column(name = "name")
    protected  String name;

    @Column(name ="first_name")
    protected String firstName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    protected Collection<ProfessorLesson> teachedByProfessor;

    public Professor() {
        this.teachedByProfessor = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Professor setId(String id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public Professor setPassword() throws NoSuchAlgorithmException {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public Professor setName(String name) {
        this.name = name;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Professor setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Collection<ProfessorLesson> getTeachedByProfessor() {
        return teachedByProfessor;
    }

    public Professor addTeachedByProfessor(ProfessorLesson professorLesson) {
        this.teachedByProfessor.add(professorLesson);
        return this;
    }

    public Professor removeFromTeachedByProfessor(ProfessorLesson professorLesson) {
        this.teachedByProfessor.remove(professorLesson);
        return this;
    }
}
