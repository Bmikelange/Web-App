package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;
import java.util.List;

public class Billet {
    private String titre, contenu, auteur, groupe;
    private int id;
    private List<Commentaire> commentaires;

    public Billet() {
        this.titre = "Rien";
        this.contenu = "Vide";
        this.auteur = "Personne";
        this.setCommentaires();
        this.groupe = "Aucun groupe";   
    }

    public Billet(String titre, String contenu, String auteur, String commentaire, String groupe) {
        this.titre = titre;
        this.contenu = contenu;
        this.auteur = auteur;
        this.setCommentaires();
        this.groupe = groupe;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public void addCommentaire(Commentaire commentaire) {
        commentaire.setId(commentaires.size());
        commentaires.add(commentaire);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commentaire getCommentaire(int i) {
        return commentaires.get(i);
    }
    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires() {
        this.commentaires = new ArrayList<>();
    }

    public void setCommentaire(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}
