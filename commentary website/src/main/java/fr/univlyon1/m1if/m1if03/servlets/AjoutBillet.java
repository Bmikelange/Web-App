package fr.univlyon1.m1if.m1if03.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.univlyon1.m1if.m1if03.classes.Billet;
import fr.univlyon1.m1if.m1if03.classes.Commentaire;
import fr.univlyon1.m1if.m1if03.classes.Groupe;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "AjoutBillet", urlPatterns = "/AjoutBillet")
public class AjoutBillet extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Groupe groupe = this.getCurrentGroupe(request, session);
        this.createBillet(request, session, groupe);
        this.createCommentaire(request, session, groupe);
        request.setAttribute("result", groupe);

        request.getRequestDispatcher("WEB-INF/jsp/billets.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // HttpSession session = request.getSession(true);

        // Groupe groupe = this.getCurrentGroupe(request, session);
        // request.setAttribute("result", groupe);
        // Cookie[] cookies = request.getCookies();
        // if (cookies != null) {
        //     int i = 0;
        //     int j = 0;
        //     int k = 0;
        //     for (Cookie cookie : cookies) {
        //         if (cookie.getName().equals("nbBillet")) {
        //             i = 1;
        //             if (cookie.getValue().equals(Integer.toString(groupe.getGestion().getListBillets().size()))) {
        //                 for (Billet billet : groupe.getGestion().getListBillets()) {
        //                     j = j + billet.getCommentaires().size();
        //                 }
        //             } else {
        //                 cookie.setValue(Integer.toString(groupe.getGestion().getListBillets().size()));
        //                 response.addCookie(cookie);
        //                 k = 1;
        //             }
        //         }
        //         if (cookie.getName().equals("nbCommentaires")) {
        //             if (k == 0) {
        //                 if (cookie.getValue().equals(Integer.toString(j))) {
        //                     response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        //                     return;
        //                 } else {
        //                     cookie.setValue(Integer.toString(j));
        //                     response.addCookie(cookie);
        //                 }
        //             }
        //         }
        //     }
        //     if (i == 0) {
        //         Cookie cooki = new Cookie("nbBillet", Integer.toString(groupe.getGestion().getListBillets().size()));
        //         cooki.setMaxAge(60 * 60 * 24 * 30);
        //         response.addCookie(cooki);
        //         j = 0;
        //         for (Billet billet : groupe.getGestion().getListBillets()) {
        //             j = j + billet.getCommentaires().size();
        //         }
        //         Cookie cooki2 = new Cookie("nbCommentaires", Integer.toString(j));
        //         cooki2.setMaxAge(60 * 60 * 24 * 30);
        //         response.addCookie(cooki2);
        //     }
        // }
        // request.getRequestDispatcher("WEB-INF/jsp/billets.jsp").forward(request, response);
        request.getServletContext().getRequestDispatcher("/Groupes/test").forward(request, response);
    }

    private void createCommentaire(HttpServletRequest request, HttpSession session, Groupe groupe) {
        if (request.getParameter("soumettreCommentaire") != null) {
            Commentaire commentaire = new Commentaire();
            commentaire.setContenu(request.getParameter("commentaire"));
            commentaire.setAuteur((String) session.getAttribute("pseudo"));

            int indice = Integer.parseInt(request.getParameter("idCommentaire"));
            groupe.getGestion().getBillet(indice).addCommentaire(commentaire);
        }
    }

    private void createBillet(HttpServletRequest request, HttpSession session, Groupe groupe) {
        String titre = (String) request.getParameter("titre");
        String groupeName = (String) session.getAttribute("groupeName");

        if (titre != null) {
            Billet billet = new Billet();
            billet.setContenu(request.getParameter("contenu"));
            billet.setTitre(titre);
            billet.setAuteur((String) session.getAttribute("pseudo"));
            billet.setGroupe(groupeName);
            groupe.getGestion().add(billet);
        }
    }

    private Groupe getCurrentGroupe(HttpServletRequest request, HttpSession session) {
        String groupeName = (String) session.getAttribute("groupeName");
        @SuppressWarnings("unchecked")
        HashMap<String, Groupe> groupes = (HashMap<String, Groupe>) request.getServletContext().getAttribute("groupes");
        Groupe groupe = groupes.get(groupeName);
        return groupe;
    }

}
