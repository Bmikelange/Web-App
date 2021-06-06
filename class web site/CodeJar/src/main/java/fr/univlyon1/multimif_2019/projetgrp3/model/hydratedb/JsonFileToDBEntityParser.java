package fr.univlyon1.multimif_2019.projetgrp3.model.hydratedb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * This class is used to deserialise and extract entities from a json.
 *
 * Note : the json must be like {"Categorie1":[{entity1FromCategorie1},...],"Categorie2":...}
 *
 * There are 6 different possible categories : "STUDENTS", "PROFESSORS", "SECRETARIES", "PRESENCES_RECORDS"
 * and "PROFESSORS_LESSONS".
 *
 * Each categorie must be unique.
 *
 * Each entity from a categorie must be unique
 */
public class JsonFileToDBEntityParser implements FileToDBEntitiesParser {

    @Override
    public Map<String, Set> parse(File file) throws FileNotConformException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(file);
        } catch (IOException e) {
        }

        Set<Student> studentsToReturn =
                getEntitiesFromJsonNode(rootNode, DBCategories.STUDENTS.toString(), mapper, Student.class);
        Set<Lesson> lessonsToReturn =
                getEntitiesFromJsonNode(rootNode, DBCategories.LESSONS.toString(), mapper, Lesson.class);
        Set<Professor> professorsToReturn =
                getEntitiesFromJsonNode(rootNode, DBCategories.PROFESSORS.toString(), mapper, Professor.class);
        Set<Secretary> secretariesToReturn =
                getEntitiesFromJsonNode(rootNode, DBCategories.SECRETARIES.toString(), mapper, Secretary.class);
        Set<ProfessorLesson> professorLessons =
                getEntitiesFromJsonNode(rootNode, DBCategories.PROFESSORS_LESSONS.toString(), mapper, ProfessorLesson.class);
        Set<PresenceRecord> presenceRecords =
                getEntitiesFromJsonNode(rootNode, DBCategories.PRESENCES_RECORDS.toString(), mapper, PresenceRecord.class);

        //stocker les donnes récupérées dans la structure à renvoyer
        Map<String, Set> entitiesFoundInFile = new HashMap<>();
        entitiesFoundInFile.put(DBCategories.STUDENTS.toString(), studentsToReturn);
        entitiesFoundInFile.put(DBCategories.LESSONS.toString(), lessonsToReturn);
        entitiesFoundInFile.put(DBCategories.PROFESSORS.toString(), professorsToReturn);
        entitiesFoundInFile.put(DBCategories.SECRETARIES.toString(), secretariesToReturn);
        entitiesFoundInFile.put(DBCategories.PRESENCES_RECORDS.toString(), presenceRecords);
        entitiesFoundInFile.put( DBCategories.PROFESSORS_LESSONS.toString(), professorLessons);

        return entitiesFoundInFile;
    }


    /**
     * Find a Json's sub-part from the Json file and convert it to a set of entities.
     *
     * Note : the Json sub-part should be a section of the Json wich contains a specific type of entity
     *        for example the section "STUDENTS" from th json corresponds to Student.class entities.
     *
     * @param root the full json file
     * @param nameOfNode the name of the entities' section we are looking for
     * @param mapper the objectMapper in param to not instanciate a new one each time we call the function
     * @param eClass the Entity.class we are looking for
     * @param <T> the type of the entity we are looking for
     * @return a set of entities filled with entities or empty
     * @throws FileNotConformException if there is something abnormal (not conform to what we want)
     */
    private <T> Set<T> getEntitiesFromJsonNode(JsonNode root, String nameOfNode, ObjectMapper mapper, Class<T> eClass) throws FileNotConformException {
        Set<T> entitiesToReturn = new HashSet<>();

        CollectionType entityCollectionType = mapper.getTypeFactory().constructCollectionType(List.class, eClass);

        if(root.isNull()) {
            throw new FileNotConformException();
        }
        else {
            if (root.get(nameOfNode) != null) {
                String jsonEntityArray = root.get(nameOfNode).toString();
                try {
                    Collection<T> entityArray = mapper.readValue(jsonEntityArray, entityCollectionType);
                    for (T entity : entityArray) {
                        if(entitiesToReturn.contains(entity))
                            //this means that an entity is presenct twice in the json
                            throw new FileNotConformException();
                        entitiesToReturn.add(entity);
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    throw new FileNotConformException();
                }
            }
        }
        return entitiesToReturn;
    }
}
