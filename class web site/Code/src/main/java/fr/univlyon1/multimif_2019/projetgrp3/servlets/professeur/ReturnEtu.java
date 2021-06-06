package fr.univlyon1.multimif_2019.projetgrp3.servlets.professeur;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.DAOFactory;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Allows the interactions between the database and the Web application for the PresenceRecord table.
 * (particularly with the XmlHttpRequest, in the "majEtu.js" file).
 * @see HttpServlet
 */
@WebServlet(name = "ReturnEtu", urlPatterns = "/professeur/ReturnEtu")
public class ReturnEtu extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Called when a GET Request is called.
     * Makes a response which contains an object, from the "identifiant" request parameter.
     * @param response HttpServletResponse
     * @param request HttpServletRequest
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager entityManager = DAOFactory.getPresenceRecordDAO().getEntityManager();
        String s = "SELECT pr.presenceRecordPK.idLesson,pr.presence,s.name,s.firstName,s.id FROM PresenceRecord as pr JOIN pr.student as s WHERE pr.presenceRecordPK.idLesson = :LessonId ";
        TypedQuery<Object[]> presenceRecord = entityManager.createQuery(s,Object[].class);
        presenceRecord.setParameter("LessonId", request.getParameter("identifiant"));
        ObjectMapper om = new ObjectMapper();
        response.setContentType( "application/json" );
        PrintWriter out = response.getWriter();
        om.writeValue(out,presenceRecord.getResultList());
        out.close();
    }

    /**
     * Called when a POST Request is called.
     * Updates the PresentRecord table with "idStudent","idLesson","presence" request parameters.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
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
