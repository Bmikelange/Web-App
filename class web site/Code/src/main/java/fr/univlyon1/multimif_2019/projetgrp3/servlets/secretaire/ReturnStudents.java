package fr.univlyon1.multimif_2019.projetgrp3.servlets.secretaire;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Student;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.StudentDAO;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.DAOFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

/**
 * Gets a set of students, from an URI : /secretaire/ReturnStudents/GroupName
 */
@WebServlet(name = "ReturnStudents", urlPatterns = "/secretaire/ReturnStudents/*")
public class ReturnStudents extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Extracts the group name and the set of students from this one.
     * Stores the group into a request attribute "master" and the students into a request attribute "students"
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger logger = Logger.getLogger("test");
        String t = (request.getRequestURI().split("/"))[4];
        StudentDAO studentDao = (StudentDAO) DAOFactory.getStudentDAO();
        List<Student> l= studentDao.getStudentbyGroupe(t);
        request.setAttribute("students",l);
        request.setAttribute("master",t);
        try {
            request.getRequestDispatcher("/secretaire/masterInfo.jsp").forward(request,response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occur", e);
        }
    }
}
