package model.database.hydrate;

import fr.univlyon1.multimif_2019.projetgrp3.model.hydratedb.FileNotConformException;
import fr.univlyon1.multimif_2019.projetgrp3.model.hydratedb.IcsToEntityParser;
import org.junit.Test;

import java.io.File;

public class IcsToEntitiesParserTest {
    @Test
    public void parseFileTest(){
        IcsToEntityParser icsParser = new IcsToEntityParser();
        String url = System.getProperty("user.dir") + "/src/test/java/model/database/hydrate/ADECal.ics";
        File file = new File(url);

        try {
            icsParser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
