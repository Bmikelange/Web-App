package fr.univlyon1.m1if.m1if03.classes;

public class Commentaire {
    private String contenu, auteur;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int i) {
        id =i;
    }

    public String getContenu() {
        return contenu;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setContenu(String commentaire) {
        this.contenu = commentaire;
    }
}