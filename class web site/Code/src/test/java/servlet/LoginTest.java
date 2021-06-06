package servlet;
import static org.junit.Assert.assertEquals;
import fr.univlyon1.multimif_2019.projetgrp3.servlets.Login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class LoginTest {

    /*private Login servlet;
    private MockHttpServletRequest requete;
    private MockHttpServletResponse reponse;

    @Before
    public void set() {
        servlet = new Login();
        requete = new MockHttpServletRequest();
        reponse = new MockHttpServletResponse();
    }

    @Test
    public void testEtu() throws ServletException, IOException {
        requete.addParameter("username", "11508726");
        requete.addParameter("password", "mikado");
        
        servlet.doPost(requete, reponse);
        

        assertEquals("etudiant/etudiant.jsp", reponse.getRedirectedUrl());

    }

    @Test
    public void testProf() throws ServletException, IOException {
        requete.addParameter("username", "5050");
        requete.addParameter("password", "please");
        
        servlet.doPost(requete, reponse);

        assertEquals("professeur/professeur.jsp", reponse.getRedirectedUrl());

    }

    @Test
    public void testSecretaire() throws ServletException, IOException {
        requete.addParameter("username", "512");
        requete.addParameter("password", "Secret");
        
        servlet.doPost(requete, reponse);

        assertEquals("secretaire/secretaire.jsp", reponse.getRedirectedUrl());

    }

    @Test
    public void testnull() throws ServletException, IOException {
        requete.addParameter("username", "512");
        requete.addParameter("password", "Sec");
        
        servlet.doPost(requete, reponse);

        assertEquals("index.html", reponse.getRedirectedUrl());

    }*/
}
