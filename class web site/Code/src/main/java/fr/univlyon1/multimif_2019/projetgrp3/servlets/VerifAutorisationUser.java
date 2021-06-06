package fr.univlyon1.multimif_2019.projetgrp3.servlets;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter that check if the user can access to the page.
 */
@WebFilter("/VerifAutorisationUser")
public class VerifAutorisationUser implements Filter {

    /**
     * Function that check if the user has authorisation to access a page
     * @param chain FilterChain
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        Cookie[] cook = httpRequest.getCookies();
        for(Cookie c : cook) {
            if (c.getName().equals("UID")) {
                String token = c.getValue();
                try {
                    Algorithm algorithm = Algorithm.HMAC256("secret");
                    JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
                    DecodedJWT jwt = verifier.verify(token);
                    String verif =jwt.getSubject().split(";")[1];
                    if (verif.equals("etudiant")) {
                        chain.doFilter(request, response);
                    } else if (verif.equals("professeur")) {
                        httpResponse.sendRedirect("/value/professeur/professeur.jsp");
                    } else if (verif.equals("secretaire")) {
                        httpResponse.sendRedirect("/value/secretaire/secretaire.jsp");
                    }
                    return;
                } catch (JWTVerificationException exception){
                    httpResponse.sendRedirect("/value/index.html");
                }
            }
        }
    }

}