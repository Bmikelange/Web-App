package fr.univlyon1.m1if.m1if03.servlets.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.Cookie;

import fr.univlyon1.m1if.m1if03.helper.HelperLink;
import fr.univlyon1.m1if.m1if03.helper.HelperParse;
import fr.univlyon1.m1if.m1if03.helper.HelperURL;
import fr.univlyon1.m1if.m1if03.classes.Groupe;

import java.util.HashMap;
import java.lang.StringBuilder;
import java.io.BufferedReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@WebServlet(name = "Groupes", urlPatterns = "/Groupes/*")
public class Groupes extends HttpServlet {
    /**
     *
     */

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        String isForBillet = HelperURL.getNiemeElement(url, 2);
        int taille = HelperURL.getNbElement(url);
        if (taille > 2 && isForBillet.equals("billets")) {
            request.getServletContext().getNamedDispatcher("Billets").forward(request, response);
        } else if (taille == 1) {
            String groupeName = request.getParameter("newGroupe");
            String description = request.getParameter("description");
            @SuppressWarnings("unchecked")
            HashMap<String, Groupe> groupes = (HashMap<String, Groupe>) request.getServletContext()
                    .getAttribute("groupes");
            String proprietaire = (String) request.getAttribute("pseudo");
            this.CreateGroupe(groupes, groupeName, proprietaire, description, request);
            response.sendRedirect(HelperLink.groupe+"/");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        String isForBillet = HelperURL.getNiemeElement(url, 2);
        Integer taille = HelperURL.getNbElement(url);
        if (taille > 2 && isForBillet.equals("billets")) {
            request.getServletContext().getNamedDispatcher("Billets").forward(request, response);
        } else if (taille == 2) {
            String numGroupe = HelperURL.getNiemeElement(url, 1);
            Groupe groupe = HelperURL.getGroupe(request, numGroupe);
            String linkToView = "groupe";
            deleteCookie(request,response);
            HelperParse.parseInAnswer(request, response, groupe, linkToView);
        } else if (taille == 1) {
            @SuppressWarnings("unchecked")
            HashMap<String, Groupe> groupes = (HashMap<String, Groupe>) request.getServletContext()
                    .getAttribute("groupes");
            String linkToView = "gestionGroupes";
            deleteCookie(request,response);
            HelperParse.parseInAnswer(request, response, groupes,linkToView);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        String isForBillet = HelperURL.getNiemeElement(url, 2);
        Integer taille = HelperURL.getNbElement(url);

        if (taille > 2 && isForBillet.equals("billets")) {
            request.getServletContext().getNamedDispatcher("Billets").forward(request, response);
        } else if (taille == 2) {
            String description = request.getParameter("description");
            @SuppressWarnings("unchecked")
            HashMap<String, Groupe> groupes = (HashMap<String, Groupe>) request.getServletContext()
                    .getAttribute("groupes");
            String proprietaire = (String) request.getAttribute("pseudo");
            String groupeName = HelperURL.getNiemeElement(url, 1);
            this.setOrCreateGroupe(groupes, groupeName, proprietaire, description, request);
            response.sendRedirect(HelperLink.groupe+"/");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        String isForBillet = HelperURL.getNiemeElement(url, 2);
        Integer taille = HelperURL.getNbElement(url);

        if (taille > 2 && isForBillet.equals("billets")) {
            request.getServletContext().getNamedDispatcher("Billets").forward(request, response);
        } else if (taille == 2) {
            @SuppressWarnings("unchecked")
            HashMap<String, Groupe> groupes = (HashMap<String, Groupe>) request.getServletContext()
                    .getAttribute("groupes");
            String groupeName = HelperURL.getNiemeElement(url, 1);
            groupes.remove(groupeName);
            response.sendRedirect(HelperLink.groupe+"/");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void CreateGroupe(HashMap<String, Groupe> groupes, String groupeName, String proprietaire,
            String description, HttpServletRequest request) throws ServletException, IOException {
        String contentType = request.getContentType();
        if (contentType.equals("application/xml")) {
            Groupe groupe;
            groupe = new Groupe();
            parseXml(request, groupe);
            groupes.put(groupe.getName(), groupe);
        } else if (contentType.equals("application/json")) {
            Groupe groupe;
            groupe = new Groupe();
            parseJson(request, groupe);
            groupes.put(groupe.getName(), groupe);
        } else {
            if (!(groupes.containsKey(groupeName))) {
                Groupe groupe = new Groupe();
                groupe.setName(groupeName);
                groupe.setProprietaire(proprietaire);
                groupe.setDescription(description);
                groupes.put(groupeName, groupe);
            }
        }
    }

    private void setOrCreateGroupe(HashMap<String, Groupe> groupes, String groupeName, String proprietaire,
        String description, HttpServletRequest request) throws ServletException, IOException {
        String contentType = request.getContentType();
        if (contentType.equals("application/xml")) {
            Groupe groupe;
            if (!(groupes.containsKey(groupeName))) {
                groupe = new Groupe();
                groupes.put(groupeName, groupe);
            } else {
                groupe = groupes.get(groupeName);
            }
            parseJson(request, groupe);
        } else if (contentType.equals("application/json")) {
            Groupe groupe;
            if (!(groupes.containsKey(groupeName))) {
                groupe = new Groupe();
                groupes.put(groupeName, groupe);
            } else {
                groupe = groupes.get(groupeName);
            }
            parseJson(request, groupe);
        } else {
            if (!(groupes.containsKey(groupeName))) {
                Groupe groupe = new Groupe();
                groupe.setName(groupeName);
                groupe.setProprietaire(proprietaire);
                groupe.setDescription(description);
                groupes.put(groupeName, groupe);
            } else {
                Groupe groupe = groupes.get(groupeName);
                groupe.setName(groupeName);
                groupe.setProprietaire(proprietaire);
                groupe.setDescription(description);
            }
        }
    }

    private void parseJson(HttpServletRequest request, Groupe groupe) throws ServletException, IOException {
        String data = getPayload(request);
        ObjectMapper mapper = new ObjectMapper();
        if (!data.isEmpty()) {
            groupe = mapper.readValue(data, Groupe.class);
        }
    }

    private void parseXml(HttpServletRequest request, Groupe groupe) throws ServletException, IOException {
        String data = getPayload(request);
        XmlMapper mapper = new XmlMapper();
        if (!data.isEmpty()) {
            groupe = mapper.readValue(data, Groupe.class);
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

    private void deleteCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException {
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
    }
}