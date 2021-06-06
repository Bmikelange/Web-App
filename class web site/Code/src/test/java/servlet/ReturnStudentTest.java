package servlet;
import static org.junit.Assert.assertEquals;
import fr.univlyon1.multimif_2019.projetgrp3.servlets.secretaire.ReturnStudent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class ReturnStudentTest {
    /*private ReturnStudent servlet;
    private MockHttpServletRequest requete;
    private MockHttpServletResponse reponse;

    @Before
    public void set() {
        servlet = new ReturnStudent();
        requete = new MockHttpServletRequest();
        reponse = new MockHttpServletResponse();
    }

    @Test
    public void correctContentInResponse() throws ServletException, IOException {

        requete.addParameter("identifiant","11508726");
        servlet.doGet(requete, reponse);

        assertEquals(null, reponse.getContentType());

    }
    @Test
    public void setEtuFalse() throws ServletException, IOException {
        requete.addParameter("presence","false");
        requete.addParameter("idLesson","2618");
        requete.addParameter("idStudent","11508726");

        servlet.doPost(requete, reponse);

        assertEquals(null, reponse.getRedirectedUrl());

    }

    @Test
    public void setEtutrue() throws ServletException, IOException {
        requete.addParameter("presence","true");
        requete.addParameter("idLesson","2618");
        requete.addParameter("idStudent","11508726");

        servlet.doPost(requete, reponse);

        assertEquals(null, reponse.getRedirectedUrl());

    }*/
}
