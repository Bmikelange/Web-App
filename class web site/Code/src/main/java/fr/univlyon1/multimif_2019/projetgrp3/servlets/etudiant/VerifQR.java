package fr.univlyon1.multimif_2019.projetgrp3.servlets.etudiant;

import fr.univlyon1.multimif_2019.projetgrp3.model.dao.PresenceRecordDAO;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.DAOFactory;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.PresenceRecord;
import fr.univlyon1.multimif_2019.projetgrp3.constante.ConstLogin;

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
 * Allows the interactions between the database and the Web application for the Student's PresenceRecords table.
 * @see HttpServlet
 */
@WebServlet(name = "VerifQR", urlPatterns = "/etudiant/VerifQR")
public class VerifQR extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static PresenceRecordDAO presenceRecordDAO =(PresenceRecordDAO) DAOFactory.getPresenceRecordDAO();
    /**
     * Set the presence of a student in a lesson to true in the database.
     *
     * Note : if the student present is not modified in the database send the user to an error page.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contenu = request.getParameter("contenu");
        String id = (String) request.getAttribute("id");
        Logger logger = Logger.getLogger("test");
        presenceRecordDAO.updateStudentPresence(id,contenu);
        List<PresenceRecord> pr = presenceRecordDAO.getStudentPresence(id,contenu);
        if (!(pr.isEmpty()) && pr.get(0).isPresence()) {
            try {
                response.sendRedirect("/value/etudiant/valide.html");
            } catch (Exception e) {
                logger.log(Level.SEVERE, ConstLogin.ERROR, e);
            }
        } else {
            try {
                response.sendRedirect("/value/etudiant/invalide.html");
            } catch (Exception e) {
                logger.log(Level.SEVERE, ConstLogin.ERROR, e);
            }
        }
    }
}
