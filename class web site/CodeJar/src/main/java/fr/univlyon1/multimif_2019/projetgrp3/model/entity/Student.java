package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "student")
public class Student{

    @NotNull
    @Id
    @Pattern(regexp = "(p|1)[0-9]{7}") 
    @Column(name = "student_id")
    protected String id;

    @NotNull
    @Column(name = "password", nullable = false)
    protected String password;

    @Column(name = "name")
    protected String name;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "class")
    protected String graduation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    protected Collection<PresenceRecord> presencesRecords;

    public Student() {
        this.presencesRecords = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Student setId(String id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Student setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Student setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getGraduation() {
        return graduation;
    }

    public Student setGraduation(String graduation) {
        this.graduation = graduation;
        return this;
    }

    public Collection<PresenceRecord> getPresencesRecords() {
        return presencesRecords;
    }

    public Student addPresencesRecords(PresenceRecord presenceRecord) {
        this.presencesRecords.add(presenceRecord);
        return this;
    }

    public Student removeFromPresencesRecords(PresenceRecord presenceRecord) {
        this.presencesRecords.remove(presenceRecord);
        return this;
    }
}
