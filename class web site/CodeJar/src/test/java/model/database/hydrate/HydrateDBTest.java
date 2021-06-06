package model.database.hydrate;

import fr.univlyon1.multimif_2019.projetgrp3.model.hydratedb.FileNotConformException;
import fr.univlyon1.multimif_2019.projetgrp3.model.hydratedb.HydrateDatabase;
import org.junit.Test;


public class HydrateDBTest {

    @Test
    public void HydrateDBWithJson() {
        HydrateDatabase hydrator = new HydrateDatabase();
        try {hydrator.setDirectoryUrl(System.getProperty("user.dir") + "/src/test/java/model/database/hydrate");
            hydrator.getParser();
            hydrator.hydrateDatabase();
        } catch (FileNotConformException e) {

        }
    }
}
