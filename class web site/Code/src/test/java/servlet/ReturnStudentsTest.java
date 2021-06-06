package servlet;
import static org.junit.Assert.assertEquals;
import fr.univlyon1.multimif_2019.projetgrp3.servlets.secretaire.ReturnStudents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class ReturnStudentsTest {
    /*private ReturnStudents servlet;
    private MockHttpServletRequest requete;
    private MockHttpServletResponse reponse;

    @Before
    public void set() {
        servlet = new ReturnStudents();
        requete = new MockHttpServletRequest();
        reponse = new MockHttpServletResponse();
    }

    @Test
    public void getStudentsTest() throws ServletException, IOException {
        requete.setRequestURI("/value/secretaire/ReturnStudents/M1-info");

        servlet.doGet(requete, reponse);

        assertEquals(null, reponse.getRedirectedUrl());

    }*/
}
