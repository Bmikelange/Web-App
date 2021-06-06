package fr.univlyon1.multimif_2019.projetgrp3.servlets.professeur;

import java.io.IOException;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Lesson;
import fr.univlyon1.multimif_2019.projetgrp3.constante.ConstLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.LessonDAO;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.DAOFactory;

/**
 * Gets a set of lessons, from an attribute "id".
 */
@WebServlet(name = "ReturnCours", urlPatterns = "/professeur/ReturnCours")
public class ReturnCours extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Get the id of a lesson through the request parameter and find the lesson related to this id.
     * Save the lesson found in the request parameter and then redirect.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger logger = Logger.getLogger("test");
        LessonDAO lessonDao = DAOFactory.getLessonDAO();
        List<Lesson> l= lessonDao.getLessonDATE((String) request.getAttribute("id"));
        request.setAttribute("lessons",l);
        try {
            request.getRequestDispatcher("liste_des_cours.jsp").forward(request,response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, ConstLogin.ERROR, e);
        }
    }
}
