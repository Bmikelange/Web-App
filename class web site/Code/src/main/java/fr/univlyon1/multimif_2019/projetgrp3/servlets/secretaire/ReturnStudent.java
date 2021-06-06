package fr.univlyon1.multimif_2019.projetgrp3.servlets.secretaire;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Student;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.StudentDAO;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.DAOFactory;

import java.io.IOException;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Lesson;

import javax.persistence.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

/**
 * Allows the interactions between the database and the Web application for the PresenceRecord table for the secretary part.
 */
@WebServlet(name = "ReturnStudent", urlPatterns = "/secretaire/ReturnStudent")
public class ReturnStudent extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Seeks a student with the request parameter "identifiant" and put him into a request Attribute "student"
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String identifiant = request.getParameter("identifiant");
        Logger logger = Logger.getLogger("test");
        StudentDAO studentDao = (StudentDAO) DAOFactory.getStudentDAO();
        List<Student> l = studentDao.getStudentbyID(identifiant);
        request.setAttribute("student",l.get(0));
        request.setAttribute("identifiant",identifiant);
        try {
            request.getRequestDispatcher("etudiant.jsp").forward(request,response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occur", e);
        }
    }

    /**
     * Updates the PresentRecord table with "idStudent","idLesson","presence" request parameters.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager entityManager = DAOFactory.getPresenceRecordDAO().getEntityManager();
        String idStudent = request.getParameter("idStudent");
        String idLesson = request.getParameter("idLesson");
        String presence = request.getParameter("presence");
        String prQuery;
        if (presence.compareTo("true")==0){
            prQuery = "UPDATE PresenceRecord pr SET pr.presence = true WHERE pr.presenceRecordPK.idLesson='" + idLesson + "' AND pr.presenceRecordPK.idStudent='" + idStudent + "'";
        } else {
            prQuery = "UPDATE PresenceRecord pr SET pr.presence = false WHERE pr.presenceRecordPK.idLesson='"+ idLesson + "' AND pr.presenceRecordPK.idStudent='" + idStudent + "'";
        }
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.createQuery(prQuery).executeUpdate();
        transaction.commit();
        transaction.begin();
        entityManager.clear();
        transaction.commit();

    }
}
