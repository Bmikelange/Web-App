package servlet;
import static org.junit.Assert.assertEquals;
import fr.univlyon1.multimif_2019.projetgrp3.servlets.etudiant.VerifQR;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class VerifQRTest {

    /*private VerifQR servlet;
    private MockHttpServletRequest requete;
    private MockHttpServletResponse reponse;

    @Before
    public void set() {
        servlet = new VerifQR();
        requete = new MockHttpServletRequest();
        reponse = new MockHttpServletResponse();
    }

    @Test
    public void correctValueInRequest() throws ServletException, IOException {
        requete.addParameter("contenu", "2618");
        requete.setAttribute("id", "11508556");
        
        servlet.doPost(requete, reponse);
        

        assertEquals("/value/etudiant/valide.html", reponse.getRedirectedUrl());

    }

    @Test
    public void nullValueInRequest() throws ServletException, IOException {
        requete.addParameter("contenu", "2618");
        requete.addParameter("id", "11508726");
        
        servlet.doPost(requete, reponse);

        assertEquals("/value/etudiant/invalide.html", reponse.getRedirectedUrl());

    }*/
}
