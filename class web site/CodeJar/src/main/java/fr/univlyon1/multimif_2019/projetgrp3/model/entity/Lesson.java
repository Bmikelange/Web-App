package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "lesson")
public class Lesson {

    @NotNull
    @Id
    @Column(name = "lesson_id")
    protected String id;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    protected LocalDate date;

    @Max(value = 24)
    @Min(value = 0)
    @Column(name = "duration")
    protected int duration;

    @Column(name = "name")
    protected String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    protected Collection<PresenceRecord> studentsPresent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    protected Collection<ProfessorLesson> teachedByProfessor;

    public Lesson() {
        this.studentsPresent = new ArrayList<>();
        this.teachedByProfessor = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Lesson setId(String id) {
        this.id = id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    //TODO peut etre formater la date sous un format que POSTGRESQL peut lire et qui sera commun à tout enregistrement
    public Lesson setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public Lesson setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public String getName() {
        return name;
    }

    public Lesson setName(String name) {
        this.name = name;
        return this;
    }

    public Collection<PresenceRecord> getStudentsPresent() {
        return studentsPresent;
    }

    public Lesson addStudentsPresent(PresenceRecord presenceRecord) {
        this.studentsPresent.add(presenceRecord);
        return this;
    }

    public Lesson removeFromStudentsPresent(PresenceRecord presenceRecord) {
        this.studentsPresent.remove(presenceRecord);
        return this;
    }

    public Collection<ProfessorLesson> getTeachedByProfessor() {
        return teachedByProfessor;
    }

    public Lesson addTeachedByProfessor(ProfessorLesson professorLesson) {
        this.teachedByProfessor.add(professorLesson);
        return this;
    }

    public Lesson removeFromTeachedByProfessor(ProfessorLesson professorLesson) {
        this.teachedByProfessor.remove(professorLesson);
        return this;
    }
}
