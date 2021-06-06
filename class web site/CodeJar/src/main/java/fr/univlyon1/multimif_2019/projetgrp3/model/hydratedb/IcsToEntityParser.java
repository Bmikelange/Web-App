package fr.univlyon1.multimif_2019.projetgrp3.model.hydratedb;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Lesson;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Extract every Lesson's entity that we found in an ics file.
 */
public class IcsToEntityParser implements FileToDBEntitiesParser{
    @Override
    public Map<String, Set> parse(File file) throws FileNotConformException {
        Set<Lesson> lessons = new HashSet<>();
        Map<String,Set> lessonsToReturn = new HashMap<String, Set>();

        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotConformException();
        }

        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = null;
        try {
            calendar = builder.build(fin);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileNotConformException();
        } catch (ParserException e) {
            e.printStackTrace();
            throw new FileNotConformException();
        }


        Map<String, String> calendarEntry = null;
        for (Iterator it = calendar.getComponents().iterator(); it.hasNext(); ) {
            Component component = (Component) it.next();
            if (component.getName().equalsIgnoreCase("VEVENT")) {
                calendarEntry = new HashMap<>();
                for (Iterator iterator = component.getProperties().iterator(); iterator.hasNext(); ) {
                    Property property = (Property) iterator.next();
                    calendarEntry.put(property.getName(), property.getValue());
                }
                lessons.add(parseCalendarEntryToLesson(calendarEntry));
            }
        }
        lessonsToReturn.put(DBCategories.LESSONS.toString(), lessons);
        return lessonsToReturn;
    }

    /**
     * Parse a entry of the calendar into a Lesson's entity.
     *
     * @param calendarEntry is a map that correspond to every Lesson's property found in the file.
     *
     * @return a Lesson entity. this entity can be not valid.
     */
    private Lesson parseCalendarEntryToLesson(Map<String, String> calendarEntry) {
        Lesson lessonToReturn = new Lesson();

        DateTime dateTimeStart = null;
        DateTime dateTimeEnd = null;

        for (String key : calendarEntry.keySet()) {
            switch (key.toUpperCase()) {
                case "UID":
                    lessonToReturn.setId(calendarEntry.get(key));
                    break;
                case "SUMMARY":
                    lessonToReturn.setName(calendarEntry.get(key));
                    break;
                case "DTSTART":
                    try {
                        dateTimeStart = new DateTime(calendarEntry.get(key));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "DTEND":
                    try {
                        dateTimeEnd = new DateTime(calendarEntry.get(key));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "DTSTAMP":
                    try {
                        DateTime lessonDate = new DateTime(calendarEntry.get(key));
                        LocalDate localDate = this.castDateTimeToLocalDate(lessonDate);
                        lessonToReturn.setDate(localDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        if(dateTimeEnd != null && dateTimeStart!=null)
            lessonToReturn.setDuration(getDurationFromDates(dateTimeStart, dateTimeEnd));

        return lessonToReturn;
    }

    /**
     * Cast a DateTime instance into a LocalDate instance.
     *
     * @param dateTime a datetime instance.
     *
     * @return a LocalDate instance
     */
    private LocalDate castDateTimeToLocalDate (DateTime dateTime) {
        Date date = new Date(dateTime.getTime());
        return date
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * Get the Lesson's duration from the lesson's hours
     * @param dateStart the lesson's start time
     * @param dateEnd the lesson's end time
     * @return the duration of the lesson
     * todo les durees son en int elles devraient etre en float en parler aux autres
     */
    private int getDurationFromDates(DateTime dateStart, DateTime dateEnd) {
        Date start = new Date(dateStart.getTime());
        Date end = new Date(dateEnd.getTime());

        return ((end.getHours()+end.getMinutes()*60) - (start.getHours()+start.getMinutes()*60));
    }
}
