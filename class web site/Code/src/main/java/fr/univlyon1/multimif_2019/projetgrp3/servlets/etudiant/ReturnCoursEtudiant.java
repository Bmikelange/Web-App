package fr.univlyon1.multimif_2019.projetgrp3.servlets.etudiant;

import java.io.IOException;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Student;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.StudentDAO;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.DAOFactory;
import fr.univlyon1.multimif_2019.projetgrp3.constante.ConstLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

/**
 * Allows the interactions between the database and the Web application for the Student table.
 * @see HttpServlet
 */
@WebServlet(name = "ReturnCoursEtudiant", urlPatterns = "/etudiant/ReturnCoursEtudiant")
public class ReturnCoursEtudiant extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Get the id of a student through the request parameter and find the student related to this id.
     * Save the student found in the request parameter.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger logger = Logger.getLogger("test");
        StudentDAO studentDao = (StudentDAO) DAOFactory.getStudentDAO();
        List<Student> l= studentDao.getStudentContent((String) request.getAttribute("id"));
        request.setAttribute("student",l.get(0));
        try {
            request.getRequestDispatcher("mon_historique.jsp").forward(request,response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, ConstLogin.ERROR, e);
        }
    }
}
