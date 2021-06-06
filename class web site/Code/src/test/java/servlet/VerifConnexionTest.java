package servlet;
import static org.junit.Assert.assertEquals;
import fr.univlyon1.multimif_2019.projetgrp3.servlets.VerifConnexion;
import fr.univlyon1.multimif_2019.projetgrp3.constante.ConstLogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import javax.servlet.http.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import javax.servlet.FilterChain;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterConfig;

public class VerifConnexionTest {

    /*private VerifConnexion servlet;
    private MockHttpServletRequest requete;
    private MockHttpServletResponse reponse;

    @Before
    public void set() {
        servlet = new VerifConnexion();
        requete = new MockHttpServletRequest();
        reponse = new MockHttpServletResponse();
    }

    @Test
    public void noCookie() throws ServletException, IOException {

        
        FilterChain chain = null;
        FilterConfig fg =null;
        requete.setRequestURI("/value/test.html");

        servlet.init(fg);
        
        servlet.doFilter(requete, reponse, chain);
        

        assertEquals("/value/index.html", reponse.getRedirectedUrl());

    }*/

    /*@Test
    public void cookieExist() throws ServletException, IOException {

        FilterChain chain = new HttpFilter();

        Logger logger = Logger.getLogger("test");
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create().withIssuer("auth0").withSubject("11508726;etudiant").sign(algorithm);
            Cookie cookie = new Cookie ("UID", token);
            Cookie[] cookies = {cookie};
            cookie.setMaxAge(30*60);
            cookie.setHttpOnly(true);
            requete.setCookies(cookies);

        } catch (JWTCreationException exception){
            logger.log(Level.SEVERE, ConstLogin.ERROR, exception);
        }

        requete.setRequestURI("/value/etudiant/etudiant.jsp");
        
        servlet.doFilter(requete, reponse, chain);
        

        assertEquals("/value/etudiant/etudiant.jsp", reponse.getRedirectedUrl());

    }*/

}
