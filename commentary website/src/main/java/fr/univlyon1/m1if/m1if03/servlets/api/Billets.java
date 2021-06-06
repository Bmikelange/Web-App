package fr.univlyon1.m1if.m1if03.servlets.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import fr.univlyon1.m1if.m1if03.helper.HelperLink;
import fr.univlyon1.m1if.m1if03.helper.HelperParse;
import fr.univlyon1.m1if.m1if03.helper.HelperURL;
import fr.univlyon1.m1if.m1if03.classes.Groupe;
import fr.univlyon1.m1if.m1if03.classes.Billet;

import java.io.IOException;
import java.util.List;
import java.lang.StringBuilder;
import java.io.BufferedReader;

@WebServlet(name = "Billets", urlPatterns = "/Billets/*")
public class Billets extends HttpServlet {
    /**
     *
     */

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        int taille = HelperURL.getNbElement(url);
        String isForcommentaire = HelperURL.getNiemeElement(url, 4);

        if (taille > 4 && isForcommentaire.equals("commentaires")) {
            request.getServletContext().getNamedDispatcher("Commentaires").forward(request, response);
        } else if (taille == 3) {
            String groupeName = HelperURL.getNiemeElement(url, 1);
            Groupe groupe = HelperURL.getGroupe(request, groupeName);
            String proprietaire = (String) request.getAttribute("pseudo");
            this.createBillet(request, proprietaire, groupe);
            response.sendRedirect(HelperLink.groupe+"/"+groupeName+"/billets");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        int taille = HelperURL.getNbElement(url);
        int idBillet = this.transformStringToInt(HelperURL.getNiemeElement(url, 3));
        String isForcommentaire = HelperURL.getNiemeElement(url, 4);
        if (taille > 4 && isForcommentaire.equals("commentaires")) {
            request.getServletContext().getNamedDispatcher("Commentaires").forward(request, response);
        } else {
            String groupeName = HelperURL.getNiemeElement(url, 1);
            Groupe groupe = HelperURL.getGroupe(request, groupeName);
            if (taille == 4 && idBillet >= 0) {
                Billet billet = groupe.getGestion().getBillet(idBillet);
                String linkToView = "billet";
                HelperParse.parseInAnswer(request, response, billet, linkToView);
            } else if (taille == 3) {
                List<Billet> billets = groupe.getGestion().getListBillets();
                String linkToView = "billets";
                HelperParse.parseInAnswer(request, response, groupe, linkToView);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        int taille = HelperURL.getNbElement(url);
        int idBillet = this.transformStringToInt(HelperURL.getNiemeElement(url, 3));
        String isForcommentaire = HelperURL.getNiemeElement(url, 4);

        if (taille > 4 && isForcommentaire.equals("commentaires")) {
            request.getServletContext().getNamedDispatcher("Commentaires").forward(request, response);
        } else if (taille == 4 && idBillet >= 0) {
            String groupeName = HelperURL.getNiemeElement(url, 1);
            Groupe groupe = HelperURL.getGroupe(request, groupeName);
            String proprietaire = (String) request.getAttribute("pseudo");
            this.createOrSetBillet(request, proprietaire, idBillet, groupe);
            response.sendRedirect(HelperLink.groupe+"/"+groupeName+"/billets");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        int taille = HelperURL.getNbElement(url);
        int idBillet = this.transformStringToInt(HelperURL.getNiemeElement(url, 3));
        String isForcommentaire = HelperURL.getNiemeElement(url, 4);

        if (taille > 4 && isForcommentaire.equals("commentaires")) {
            request.getServletContext().getNamedDispatcher("Commentaires").forward(request, response);
        } else if (taille == 4 && idBillet > 0) {
            String groupeName = HelperURL.getNiemeElement(url, 1);
            Groupe groupe = HelperURL.getGroupe(request, groupeName);
            groupe.getGestion().getListBillets().remove(idBillet);
            response.sendRedirect(HelperLink.groupe+"/"+groupeName+"/billets");
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

    private void createBillet(HttpServletRequest request, String pseudo, Groupe groupe)
            throws ServletException, IOException {
        String contentType = request.getContentType();
        if (contentType.equals("application/xml")) {
            Billet billet;
            billet = new Billet();
            parseXml(request, billet);
            groupe.getGestion().add(billet);
        } else if (contentType.equals("application/json")) {
            Billet billet;
            billet = new Billet();
            parseJson(request, billet);
            groupe.getGestion().add(billet);
        } else {
            String titre = request.getParameter("titre");
            if (titre != null) {
                Billet billet = new Billet();
                billet.setContenu(request.getParameter("contenu"));
                billet.setTitre(titre);
                billet.setAuteur(pseudo);
                billet.setGroupe(groupe.getName());
                groupe.getGestion().add(billet);
            }
        }
    }

    private void createOrSetBillet(HttpServletRequest request, String pseudo, int Id, Groupe groupe)
            throws ServletException, IOException {
        String contentType = request.getContentType();
        if (contentType.equals("application/xml")) {
            Billet billet;
            if (groupe.getGestion().getListBillets().size() <= Id) {
                billet = new Billet();
                groupe.getGestion().add(billet);
            } else {
                billet = groupe.getGestion().getBillet(Id);
            }
            parseXml(request, billet);
        } else if (contentType.equals("application/json")) {
            Billet billet;
            if (groupe.getGestion().getListBillets().size() <= Id) {
                billet = new Billet();
                groupe.getGestion().add(billet);
            } else {
                billet = groupe.getGestion().getBillet(Id);
            }
            parseJson(request, billet);
        } else {
            String titre = request.getParameter("titre");
            if (titre != null) {
                if (groupe.getGestion().getListBillets().size() <= Id) {
                    Billet billet = new Billet();
                    billet.setContenu(request.getParameter("contenu"));
                    billet.setTitre(titre);
                    billet.setAuteur(pseudo);
                    billet.setGroupe(groupe.getName());
                    groupe.getGestion().add(billet);
                } else {
                    Billet billet = groupe.getGestion().getBillet(Id);
                    billet.setContenu(request.getParameter("contenu"));
                    billet.setTitre(titre);
                    billet.setAuteur(pseudo);
                    billet.setGroupe(groupe.getName());
                }
            }
        }
    }

    private void parseJson(HttpServletRequest request, Billet billet) throws ServletException, IOException {
        String data = getPayload(request);
        ObjectMapper mapper = new ObjectMapper();
        if (!data.isEmpty()) {
            billet = mapper.readValue(data, Billet.class);
        }
    }

    private void parseXml(HttpServletRequest request, Billet billet) throws ServletException, IOException {
        String data = getPayload(request);
        XmlMapper mapper = new XmlMapper();
        if (!data.isEmpty()) {
            billet = mapper.readValue(data, Billet.class);
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

    private void setCookie(HttpServletRequest request, HttpServletResponse response,Groupe groupe) throws ServletException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
             int i = 0;
             int j = 0;
             int k = 0;
             for (Cookie cookie : cookies) {
                 if (cookie.getName().equals("nbBillet")) {
                     i = 1;
                     if (cookie.getValue().equals(Integer.toString(groupe.getGestion().getListBillets().size()))) {
                         for (Billet billet : groupe.getGestion().getListBillets()) {
                             j = j + billet.getCommentaires().size();
                         }
                     } else {
                         cookie.setValue(Integer.toString(groupe.getGestion().getListBillets().size()));
                         response.addCookie(cookie);
                         k = 1;
                     }
                 }
                 if (cookie.getName().equals("nbCommentaires")) {
                     if (k == 0) {
                         if (cookie.getValue().equals(Integer.toString(j))) {
                             response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                             return;
                         } else {
                             cookie.setValue(Integer.toString(j));
                             response.addCookie(cookie);
                         }
                     }
                 }
             }
             if (i == 0) {
                 Cookie cooki = new Cookie("nbBillet", Integer.toString(groupe.getGestion().getListBillets().size()));
                 cooki.setMaxAge(60 * 60 * 24 * 30);
                 response.addCookie(cooki);
                 j = 0;
                 for (Billet billet : groupe.getGestion().getListBillets()) {
                     j = j + billet.getCommentaires().size();
                 }
                 Cookie cooki2 = new Cookie("nbCommentaires", Integer.toString(j));
                 cooki2.setMaxAge(60 * 60 * 24 * 30);
                 response.addCookie(cooki2);
             }
        }
    }
}