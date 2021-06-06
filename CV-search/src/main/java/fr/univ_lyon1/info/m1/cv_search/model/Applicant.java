package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Applicant {
    Map<String, Integer> skills = new HashMap<String, Integer>();
    String name;

    public int getSkill(String string) {
        return skills.getOrDefault(string, 0);
    }

    public void setSkill(String string, int value) {
        skills.put(string, value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** . */
    public float getMeanSkills(ObservableList<Node> skillsList) {
        float sum = 0;
        for (Node skill : skillsList) {
            sum += getSkill(skill.toString());
        }
        return sum / (float)skillsList.size();
    }

    /** . */
    public int getSumSkills(ObservableList<Node> skillsList) {
        int sum = 0;
        for (Node skill : skillsList) {
            sum += getSkill(skill.toString());
        }
        return sum;
    }
}
