package fr.univlyon1.multimif_2019.projetgrp3.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The log out servlet
 */
@WebServlet(name = "Deconnexion", urlPatterns = "/Deconnexion")
public class Deconnexion extends HttpServlet {

    /**
     * Erases the current session by removing the corresponding cookie
     * @param response HttpServletResponse
     * @param request HttpServletRequest
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Logger logger = Logger.getLogger("test");
        if (cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("UID")){
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    try {
                        response.sendRedirect("/value/index.html");
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "Exception occur", e);
                    }
                    
                }
            }
        }
        try {
            response.sendRedirect("/value/index.html");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occur", e);
        }
    }
}
