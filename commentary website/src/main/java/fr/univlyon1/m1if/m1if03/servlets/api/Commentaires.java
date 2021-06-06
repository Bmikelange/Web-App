package fr.univlyon1.m1if.m1if03.servlets.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.univlyon1.m1if.m1if03.helper.HelperLink;
import fr.univlyon1.m1if.m1if03.helper.HelperParse;
import fr.univlyon1.m1if.m1if03.helper.HelperURL;
import fr.univlyon1.m1if.m1if03.classes.Groupe;
import fr.univlyon1.m1if.m1if03.classes.Commentaire;
import java.util.List;
import java.lang.StringBuilder;
import java.io.BufferedReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@WebServlet(name = "Commentaires", urlPatterns = "/Commentaires/*")
public class Commentaires extends HttpServlet {
    /**
     *
     */

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getRequestURI();
        int taille = HelperURL.getNbElement(url);

        int idBillet = this.transformStringToInt(HelperURL.getNiemeElement(url, 3));

        if (taille == 5 && idBillet >= 0) {
            String groupeName = HelperURL.getNiemeElement(url, 1);
            Groupe groupe = HelperURL.getGroupe(request, groupeName);
            String proprietaire = (String) request.getAttribute("pseudo");
            this.createCommentaire(request, proprietaire, idBillet, groupe);
            response.sendRedirect(HelperLink.groupe+"/"+groupeName+"/billets/");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        int taille = HelperURL.getNbElement(url);

        int idBillet = this.transformStringToInt(HelperURL.getNiemeElement(url, 3));
        int idCom = this.transformStringToInt(HelperURL.getNiemeElement(url, 5));

        if (idBillet >= 0) {
            String groupeName = HelperURL.getNiemeElement(url, 1);
            Groupe groupe = HelperURL.getGroupe(request, groupeName);
            if (taille == 6 && idCom >= 0) {
                Commentaire com = groupe.getGestion().getBillet(idBillet).getCommentaire(idCom);
                String linkToView = "commentaire";
                HelperParse.parseInAnswer(request, response, com, linkToView);
            } else if (taille == 5) {
                List<Commentaire> com = groupe.getGestion().getBillet(idBillet).getCommentaires();
                String linkToView = "commentaires";
                HelperParse.parseInAnswer(request, response, com, linkToView);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        int taille = HelperURL.getNbElement(url);

        int idBillet = this.transformStringToInt(HelperURL.getNiemeElement(url, 3));
        int idCom = this.transformStringToInt(HelperURL.getNiemeElement(url, 5));

        if (taille == 8 && idBillet >= 0 && idCom >= 0) {
            String groupeName = HelperURL.getNiemeElement(url, 1);
            Groupe groupe = HelperURL.getGroupe(request, groupeName);
            String proprietaire = (String) request.getAttribute("pseudo");
            this.setOrCreateCommentaire(request, proprietaire, idBillet, idCom, groupe);
            response.sendRedirect(HelperLink.groupe+"/"+groupeName+"/billets/"+idBillet+"/commentaires");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        int taille = HelperURL.getNbElement(url);

        int idBillet = this.transformStringToInt(HelperURL.getNiemeElement(url, 3));
        int idCom = this.transformStringToInt(HelperURL.getNiemeElement(url, 5));

        if (taille == 6 && idBillet >= 0 && idCom >= 0) {
            String groupeName = HelperURL.getNiemeElement(url, 1);
            Groupe groupe = HelperURL.getGroupe(request, groupeName);
            groupe.getGestion().getBillet(idBillet).getCommentaires().remove(idCom);
            response.sendRedirect(HelperLink.groupe+"/"+groupeName+"/billets/"+idBillet+"/commentaires");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private int transformStringToInt(String stringValue) {
        int intValue;
        try {
            intValue = Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            intValue = -1;
        }
        return intValue;
    }

    private void createCommentaire(HttpServletRequest request, String pseudo, int indice, Groupe groupe)
            throws ServletException, IOException {
        String contentType = request.getContentType();
        if (contentType.equals("application/xml")) {
            Commentaire commentaire;
            commentaire = new Commentaire();
            parseXml(request, commentaire);
            groupe.getGestion().getBillet(indice).addCommentaire(commentaire);
        } else if (contentType.equals("application/json")) {
            Commentaire commentaire;
            commentaire = new Commentaire();
            parseJson(request, commentaire);
            groupe.getGestion().getBillet(indice).addCommentaire(commentaire);
        } else {
            Commentaire commentaire = new Commentaire();
            commentaire.setContenu(request.getParameter("commentaire"));
            commentaire.setAuteur(pseudo);
            groupe.getGestion().getBillet(indice).addCommentaire(commentaire);
        }
    }

    private void setOrCreateCommentaire(HttpServletRequest request, String pseudo, int idBillet, int idCom,
            Groupe groupe) throws ServletException, IOException {
        String contentType = request.getContentType();
        if (contentType.equals("application/xml")) {
            Commentaire commentaire;
            if (groupe.getGestion().getBillet(idBillet).getCommentaires().size() <= idCom) {
                commentaire = new Commentaire();
                groupe.getGestion().getBillet(idBillet).addCommentaire(commentaire);
            } else {
                commentaire = groupe.getGestion().getBillet(idBillet).getCommentaire(idCom);
            }
            parseXml(request, commentaire);
        } else if (contentType.equals("application/json")) {
            Commentaire commentaire;
            if (groupe.getGestion().getBillet(idBillet).getCommentaires().size() <= idCom) {
                commentaire = new Commentaire();
                groupe.getGestion().getBillet(idBillet).addCommentaire(commentaire);
            } else {
                commentaire = groupe.getGestion().getBillet(idBillet).getCommentaire(idCom);
            }
            parseJson(request, commentaire);
        } else {
            if (request.getParameter("soumettreCommentaire") != null) {
                if (groupe.getGestion().getBillet(idBillet).getCommentaires().size() <= idCom) {
                    Commentaire commentaire = new Commentaire();
                    commentaire.setContenu(request.getParameter("commentaire"));
                    commentaire.setAuteur(pseudo);
                    groupe.getGestion().getBillet(idBillet).addCommentaire(commentaire);
                } else {
                    Commentaire commentaire = groupe.getGestion().getBillet(idBillet).getCommentaire(idCom);
                    commentaire.setContenu(request.getParameter("commentaire"));
                    commentaire.setAuteur(pseudo);
                }
            }
        }
    }

    private void parseJson(HttpServletRequest request, Commentaire commentaire) throws ServletException, IOException {
        String data = getPayload(request);
        ObjectMapper mapper = new ObjectMapper();
        if (!data.isEmpty()) {
            commentaire = mapper.readValue(data, Commentaire.class);
        }
    }

    private void parseXml(HttpServletRequest request, Commentaire commentaire) throws ServletException, IOException {
        String data = getPayload(request);
        XmlMapper mapper = new XmlMapper();
        if (!data.isEmpty()) {
            commentaire = mapper.readValue(data, Commentaire.class);
        }
    }

    private String getPayload(HttpServletRequest request) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        return data;
    }
}