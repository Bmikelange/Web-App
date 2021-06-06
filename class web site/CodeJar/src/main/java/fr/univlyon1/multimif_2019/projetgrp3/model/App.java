package fr.univlyon1.multimif_2019.projetgrp3.model;

import fr.univlyon1.multimif_2019.projetgrp3.model.hydratedb.FileNotConformException;
import fr.univlyon1.multimif_2019.projetgrp3.model.hydratedb.HydrateDatabase;

class App {

    public static void main(String[] args) {
        HydrateDatabase hydrator = new HydrateDatabase();
        try {
            hydrator.getParser();
            hydrator.hydrateDatabase();
        } catch (FileNotConformException e) {

        }
        System.err.println("Success !!");
    }

}
