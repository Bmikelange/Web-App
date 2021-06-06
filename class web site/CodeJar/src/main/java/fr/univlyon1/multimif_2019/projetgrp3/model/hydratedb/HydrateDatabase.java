package fr.univlyon1.multimif_2019.projetgrp3.model.hydratedb;

import fr.univlyon1.multimif_2019.projetgrp3.model.dao.DAOFactory;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.LessonDAO;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.PresenceRecordDAO;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.*;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * The class's goal is to fill the database with entities found in a file since
 * to a parser.
 */
public class HydrateDatabase {

    String directoryUrl;

    private String icsFileName = "ADECal.ics";

    private String jsonfileName = "database.json";

    /**
     * The parser's goal is to read à file(Json, XML, ...), parse it to Entities
     * and fill the database with entities. Note: implement Strattegy patern to
     * easly change the parser if the file structure change
     */
    protected FileToDBEntitiesParser parser;

    public HydrateDatabase() {
        this.directoryUrl = "./";
    }
    
    public void setDirectoryUrl(String url) {
        this.directoryUrl = url;
    }

    /**
     * FileToDBEntitiesParser's getter.
     *
     * @return the current parser.
     */
    public FileToDBEntitiesParser getParser() {
        return parser;
    }

    /**
     * FileToDBEntitiesParser' setter.
     *
     * @param parser the new parser we want to have.
     */
    public void setParser(FileToDBEntitiesParser parser) {
        this.parser = parser;
    }

    /**
     * Fill the database with every entities found in the file by the parser.
     *
     * @throws
     * fr.univlyon1.multimif_2019.projetgrp3.model.hydratedb.FileNotConformException
     */
    public void hydrateDatabase() throws FileNotConformException {
        Map<String, Set> lessonsFound;
        System.out.println(directoryUrl + " - " + icsFileName);
        this.parser = new IcsToEntityParser();
        lessonsFound = parser.parse(new File(directoryUrl + icsFileName));

        Set<Lesson> lessons = lessonsFound.get(DBCategories.LESSONS.toString());
        lessons.forEach((lesson) -> {
            //todo comparateur de date de 2 cours poour mise a jour ou rien
            LessonDAO lessonDAO = DAOFactory.getLessonDAO();
            lessonDAO.mergeOrPersist(lesson, lesson.getId());
        });

        File jFile = new File(directoryUrl + jsonfileName);
        if (jFile.exists()) {

            Map<String, Set> entitiesFound = null;
            try {
                this.parser = new JsonFileToDBEntityParser();
                entitiesFound = parser.parse(jFile);
            } catch (FileNotConformException e) {
            }

            Set<Student> students = entitiesFound.get(DBCategories.STUDENTS.toString());
            students.forEach((student) -> {
                DAOFactory.getStudentDAO().mergeOrPersist(student, student.getId());
            });

            Set<Professor> professors = entitiesFound.get(DBCategories.PROFESSORS.toString());
            professors.forEach((professor) -> {
                DAOFactory.getProfessorDAO().mergeOrPersist(professor, professor.getId());
            });

            Set<Secretary> secretaries = entitiesFound.get(DBCategories.SECRETARIES.toString());
            secretaries.forEach((secretary) -> {
                DAOFactory.getSecretaryDAO().mergeOrPersist(secretary, secretary.getId());
            });

            Set<PresenceRecord> presencesRecords = entitiesFound.get(DBCategories.PRESENCES_RECORDS.toString());
            presencesRecords.forEach((presenceRecord) -> {
                PresenceRecordDAO presenceRecordDAO = (PresenceRecordDAO) DAOFactory.getPresenceRecordDAO();
                PresenceRecord presenceRecordInDB = presenceRecordDAO.findById(presenceRecord.getPresenceRecordPK());
                if (presenceRecordInDB == null) {
                    presenceRecordDAO.persist(presenceRecord);
                }
            });

            Set<ProfessorLesson> professorLessons = entitiesFound.get(DBCategories.PROFESSORS_LESSONS.toString());
            professorLessons.forEach((lesson) -> {
                DAOFactory.getProfessorLessonDAO().mergeOrPersist(lesson, lesson.getProfessorLessonId());
            });
        }

    }

}
