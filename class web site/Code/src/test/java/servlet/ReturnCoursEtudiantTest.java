package servlet;
import static org.junit.Assert.assertEquals;
import fr.univlyon1.multimif_2019.projetgrp3.servlets.etudiant.ReturnCoursEtudiant;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class ReturnCoursEtudiantTest {

   /* private ReturnCoursEtudiant servlet;
    private MockHttpServletRequest requete;
    private MockHttpServletResponse reponse;

    @Before
    public void set() {
        servlet = new ReturnCoursEtudiant();
        requete = new MockHttpServletRequest();
        reponse = new MockHttpServletResponse();
    }

    @Test
    public void deconnexionTest() throws ServletException, IOException {

	    requete.setAttribute("id","11508726");
        
        servlet.doGet(requete, reponse);
        

        assertEquals(null, reponse.getRedirectedUrl());

    }*/
}
