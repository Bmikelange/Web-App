package fr.univlyon1.multimif_2019.projetgrp3.model.hydratedb;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Interface to allow a database hydrator to parse a file.
 *
 * Usefull in strattegy pattern to parse many type of file (json, xml, ...).
 */
public interface FileToDBEntitiesParser {

    /**
     * Only parse a file to entities.
     *
     * @param file the file that serialise all database entities.
     *
     * @return a map. Each key correspond to a Database Categorie.
     *
     * @throws FileNotConformException If the file isn't conform to the parser.
     */
    public Map<String, Set> parse(File file) throws FileNotConformException;
}
