package fr.univlyon1.multimif_2019.projetgrp3.model.hydratedb;

public enum DBCategories {
    STUDENTS("STUDENTS"),
    SECRETARIES("SECRETARIES"),
    PROFESSORS("PROFESSORS"),
    LESSONS("LESSONS"),
    PRESENCES_RECORDS("PRESENCES_RECORDS"),
    PROFESSORS_LESSONS("PROFESSORS_LESSONS");


    private DBCategories(String label){
        this.label = label;
    }

    public final String label;

    @Override
    public String toString() {
        return label;
    }
}
