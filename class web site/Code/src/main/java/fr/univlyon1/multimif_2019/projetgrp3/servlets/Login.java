package fr.univlyon1.multimif_2019.projetgrp3.servlets;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.DAOFactory;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.ProfessorDAO;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.SecretaryDAO;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.StudentDAO;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Encryptor;
import fr.univlyon1.multimif_2019.projetgrp3.constante.ConstLogin;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Professor;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Secretary;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Student;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.swing.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Authentification servlet, with a JWT Token
 */
@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet implements Encryptor{
    ServletContext contexte;

    /**
     * Context initialisation
     * @param config The Servlet Configuration
     */
    @Override
    public void init(ServletConfig config){
        contexte = config.getServletContext();
    }

    /**
     * Authenticates a user, and redirect him to his page,
     * based on his username and password, gotten through the request parameters "password" and "username"
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Logger logger = Logger.getLogger("test");
        if(username != null && !username.equals("") && password != null && !password.equals("")) {
            Professor professor;
            Student student;
            Secretary secretary;
            StudentDAO studentDAO = (StudentDAO) DAOFactory.getStudentDAO();
            ProfessorDAO professorDAO = (ProfessorDAO) DAOFactory.getProfessorDAO();
            SecretaryDAO secretaryDAO = (SecretaryDAO) DAOFactory.getSecretaryDAO();

            if ((professor = professorDAO.findById(username)) != null && professor.getPassword().equals(password)) {
                String p = "professeur";
                try {
                    creatingToken(username, p, response);
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, ConstLogin.ERROR, ex);
                }
                try {
                    response.sendRedirect("professeur/professeur.jsp");
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, ConstLogin.ERROR, ex);
                }
            } else if ((student = studentDAO.findById(username)) != null && student.getPassword().equals(password)) {
                    String e = "etudiant";
                    try {
                        creatingToken(username, e, response);
                    } catch (Exception ex) {
                        logger.log(Level.SEVERE, ConstLogin.ERROR, ex);
                    }
                    try {
                        response.sendRedirect("etudiant/etudiant.jsp");
                    } catch (Exception ex) {
                        logger.log(Level.SEVERE, ConstLogin.ERROR, ex);
                    }
            }  else if ((secretary = secretaryDAO.findById(username)) != null && secretary.getPassword().equals(password)) {
                    String s = "secretaire";
                    try {
                        creatingToken(username, s, response);
                    } catch (Exception ex) {
                        logger.log(Level.SEVERE, ConstLogin.ERROR, ex);
                    }
                    try {
                        response.sendRedirect("secretaire/secretaire.jsp");
                    } catch (Exception ex) {
                        logger.log(Level.SEVERE, ConstLogin.ERROR, ex);
                    }
            } else {
                try {
                    response.sendRedirect("index.html");
                } catch (Exception e) {
                    logger.log(Level.SEVERE, ConstLogin.ERROR, e);
                }
            }

        } else {
            try {
                response.sendRedirect("index.html");
            } catch (Exception exe) {
                logger.log(Level.SEVERE, ConstLogin.ERROR, exe);
            }
        }

    }

    /**
     * Generates a JWT token which contains a hash of the string : username;group.
     * Stores it into a cookie.
     * @param username user's username
     * @param groupe user's group (professor/student/secretary)
     */
    private void creatingToken(String username, String groupe,  HttpServletResponse response) {
        Logger logger = Logger.getLogger("test");
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create().withIssuer("auth0").withSubject(username + ";" + groupe).sign(algorithm);
            Cookie userCookie = new Cookie ("UID", token);
            userCookie.setMaxAge(30*60);
            userCookie.setHttpOnly(true);
            response.addCookie(userCookie);

        } catch (JWTCreationException exception){
            logger.log(Level.SEVERE, ConstLogin.ERROR, exception);
        }
    }
}
