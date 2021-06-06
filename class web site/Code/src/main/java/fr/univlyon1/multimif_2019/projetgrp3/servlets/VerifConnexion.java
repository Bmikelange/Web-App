package fr.univlyon1.multimif_2019.projetgrp3.servlets;

import fr.univlyon1.multimif_2019.projetgrp3.constante.ConstLogin;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.JWT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@WebFilter(urlPatterns = "/VerifConnexion")
public class VerifConnexion implements Filter {

    /**
     * Every url that an unauthenticated user can access.
     */
    List<String> excludeUrl;

    /**
     * Init the filter.
     * Set every url that can be accessed by everyone.
     * @param filterConfig Filter Configuration
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.excludeUrl = new ArrayList<>();
        this.excludeUrl.add("/value");
        this.excludeUrl.add("/value/index.html");
        this.excludeUrl.add("/value/authentification.html");
        this.excludeUrl.add("/value/Login");
        this.excludeUrl.add("/value/css/identification.css");
        this.excludeUrl.add("/value/img/back.png");
        this.excludeUrl.add("/value/img/Header.png");
        this.excludeUrl.add("/value/img/Header120.png");
        Filter.super.init(filterConfig);
    }

    /**
     * Decode a json web token if it is valid and allow access to the page. Or redirects to the connection page.
     * @param response ServletResponse
     * @param request ServletRequest
     * @param chain FilterChain
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Logger logger = Logger.getLogger("test");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();
        Cookie[] allCookies = ((HttpServletRequest) request).getCookies();
        String token = "";
        if (allCookies != null) {
            for (Cookie cookie : allCookies) {
                if(cookie.getName().equals("UID")){
                    token = cookie.getValue();
                }
            }
        } else if (!(excludeUrl.contains(path))) {
            ((HttpServletResponse) response).sendRedirect("/value/index.html");
            return;
        }

        if (!token.equals("") || verifyToken(token) || excludeUrl.contains(path)) {
            try{
                DecodedJWT jwt =JWT.decode(token);
                String temp =jwt.getSubject().split(";")[0];
                request.setAttribute("id",temp);
            } catch (JWTDecodeException ex ) {
                logger.log(Level.SEVERE, ConstLogin.ERROR, ex);
            }
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/value/index.html");
        }
    }

    /**
     * Verifies the validity of a json web token.
     *
     * @param token a json web token.
     *
     * @return true if the jwt is valid | false otherwise.
     */
    private boolean verifyToken(String token) {
        Logger logger = Logger.getLogger("test");
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            logger.log(Level.SEVERE, ConstLogin.ERROR, exception);
        }

        return false;
    }

}
