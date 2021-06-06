package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;
import java.util.List;

public class GestionBillets {
    private List<Billet> billets;

    public GestionBillets() {
        this.billets = new ArrayList<>();
    }

    public void add(Billet billet) {
        billet.setId(this.getNbBillet());
        this.billets.add(billet);
    }

    public Billet getBillet(int i) {
        return billets.get(i);
    }

    public Billet getLastBillet() {
        if (billets.size() > 0)
            return this.getBillet(billets.size() - 1);
        throw new IndexOutOfBoundsException("Erreur dans l'appel à la fonction getLastBillet");
    }

    public List<Billet> getListBillets() {
        return billets;
    }

    public List<Billet> getListBilletsByGroupe(String groupe) {
        List<Billet> result = new ArrayList<>();
        for (Billet billet : billets) {
            if (billet.getGroupe() == groupe) {
                result.add(billet);
            }
        }
        return result;
    }

    public void setListBillets(List<Billet> billets) {
        this.billets = billets;
    }

    public int getNbBillet() {
        return billets.size();
    }
}
