package model.database.hydrate;

import fr.univlyon1.multimif_2019.projetgrp3.model.hydratedb.JsonFileToDBEntityParser;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class JsonToDBEntitiesParserTest {

    @Test
    public void parseTest() {
        JsonFileToDBEntityParser parser = new JsonFileToDBEntityParser();
        File file = new File(System.getProperty("user.dir")+ "/src/" +
                "test/java/model/database/hydrate/database.json");
        try {
            Map<String, Set> enititiesFound = parser.parse(file);
            for(Map.Entry<String, Set> entry : enititiesFound.entrySet()) {
                Set valeur = entry.getValue();
                for(Object s : valeur)
                    System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
