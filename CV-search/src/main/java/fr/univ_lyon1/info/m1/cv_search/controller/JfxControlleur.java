package fr.univ_lyon1.info.m1.cv_search.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.cv_search.constantes.ConstantesSearch;
import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class JfxControlleur {
	
	public EventHandler<ActionEvent> getListApplicantRequired(
			ComboBox<String> search, HBox searchSkillsBox, VBox resultBox) {
		return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ApplicantList listApplicants = new ApplicantListBuilder(new File(".")).build();
                resultBox.getChildren().clear();
                String selected = search.getValue();
                ObservableList<Node> listSkillsSelected = searchSkillsBox.getChildren();

                List<Applicant> listResult = new ArrayList<Applicant>();
                if (selected == ConstantesSearch.SEARCH_50) {
                    listResult = listApplicants.getApplicantSkills(50, listSkillsSelected); 
                } else if (selected == ConstantesSearch.SEARCH_60) {
                    listResult = listApplicants.getApplicantSkills(60, listSkillsSelected); 
                } else if (selected == ConstantesSearch.SEARCH_MEAN) {
                    listResult = listApplicants.getApplicantMeansSkills(50, listSkillsSelected);
                }
                listResult = listApplicants.sortListe(listResult, listSkillsSelected);

                for (Applicant a : listResult) {
                    resultBox.getChildren().add(new Label(a.getName()));
                }
            }
        };
	}
}
