package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;
import java.util.List;

public class Groupe {
    private String name;
    private String description;
    private String proprietaire;
    private List<String> participantList;
    private GestionBillets gestion;

    public Groupe() {
        this.name = "name";
        this.description = "description";
        this.proprietaire = "proprietaire";
        this.participantList = new ArrayList<>();
        this.gestion = new GestionBillets();
    }

    /**
     * add participant in participant list if he's not already in this groupe
     * 
     * @param participant
     */
    public void addParticipant(String participant) {
        if (!participantList.contains(participant)) {
            participantList.add(participant);
        }
    }

    public boolean containsParticipant(String participant) {
        return this.participantList.contains(participant);
    }

    public String getName() {
        return name;
    }

    public GestionBillets getGestion() {
        return gestion;
    }

    public void setGestion(GestionBillets gestion) {
        this.gestion = gestion;
    }

    public List<String> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<String> participantList) {
        this.participantList = participantList;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}