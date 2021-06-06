package fr.univlyon1.m1if.m1if03.servlets;

import fr.univlyon1.m1if.m1if03.classes.Groupe;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "User", urlPatterns = "/User")
public class User extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String groupeName = request.getParameter("newGroupe");

        if (!this.haveGroupeName(groupeName)) {
            groupeName = request.getParameter("existingGroup");
        }

        @SuppressWarnings("unchecked")
        HashMap<String, Groupe> groupes = (HashMap<String, Groupe>) request.getServletContext().getAttribute("groupes");

        String pseudo = request.getParameter("pseudo");
        String description = request.getParameter("description");
        Groupe groupe = this.getOrCreateGroupe(groupes, groupeName, pseudo, description);
        groupe.addParticipant(pseudo);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("nbBillet")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
                if (cookie.getName().equals("nbCommentaires")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        request.getServletContext().getNamedDispatcher("Init").include(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/gestionGroupes.jsp").forward(request, response);
    }

    private boolean haveGroupeName(String groupeName) {
        return groupeName != null && !groupeName.equals("");
    }

    private Groupe getOrCreateGroupe(HashMap<String, Groupe> groupes, String groupeName, String proprietaire,
            String description) {
        if (!groupes.containsKey(groupeName)) {
            Groupe groupe = new Groupe();
            groupe.setName(groupeName);
            groupe.setProprietaire(proprietaire);
            groupe.setDescription(description);
            groupes.put(groupeName, groupe);
            return groupe;
        }
        return groupes.get(groupeName);
    }
}
