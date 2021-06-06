package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;


import javafx.scene.control.Button;

public class ApplicantList implements Iterable<Applicant> {
    private List<Applicant> list = new ArrayList<Applicant>();

    void add(Applicant a) {
        list.add(a);
    }

    public Object size() {
        return list.size();
    }

    @Override
    public Iterator<Applicant> iterator() {
        return list.iterator();
    }

    /** Clear the list of applicants. */
    public void clear() {
        list.clear();
    }

    /**
     * renvoie le niveau de chaque applicant pour chaque skill.
    */
    public List<Applicant> getApplicantSkills(int value,ObservableList<Node> skillList) {
        List<Applicant> result = new ArrayList<>();
        for (Applicant a : list) {
            boolean selected = true;
            for (Node skill : skillList) {
                String skillName = ((Button)skill).getText();
                if (a.getSkill(skillName) < value) {
                    selected = false;
                    break;
                }
            }
            if (selected) {
                result.add(a);
            }
        }
        return result;
    }

    /**
     * renvoie la moyenne de niveau de chaque applicant.
    */
    public List<Applicant> getApplicantMeansSkills(float value, ObservableList<Node> skillList) {
        List<Applicant> result = new ArrayList<>();
        for (Applicant a : list) {
            int sumSkill = 0;
            for (Node skill : skillList) {
                String skillName = ((Button)skill).getText();
                sumSkill += a.getSkill(skillName);
            }
            float mean = sumSkill / (float)skillList.size();
            if (mean > value) {
                result.add(a);
            }
        }
        return result;
    }

    public List<Applicant> sortListe(List<Applicant> listApp, ObservableList<Node> skillsList) {
        listApp.sort((x,y) -> y.getSumSkills(skillsList) - x.getSumSkills((skillsList)));
        return listApp;
    }

    /** Sets the content of the applicant list. */
    public void setList(ApplicantList list) {
        this.list = list.list;
    }
}
