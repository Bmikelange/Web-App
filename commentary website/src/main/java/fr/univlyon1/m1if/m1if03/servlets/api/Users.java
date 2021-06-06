package fr.univlyon1.m1if.m1if03.servlets.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.univlyon1.m1if.m1if03.helper.HelperParse;
import fr.univlyon1.m1if.m1if03.helper.HelperURL;
import java.util.List;

@WebServlet(name = "Users", urlPatterns="/Users/*")
public class Users extends HttpServlet {
    /**
     *
     */

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        int taille = HelperURL.getNbElement(url);
        String islog = HelperURL.getNiemeElement(url, 1);
        if(taille==2)
        {
            switch (islog){
                case "login":
                    request.getServletContext().getNamedDispatcher("Init").forward(request,response);
                    break;
                case "logout":
                    request.getServletContext().getNamedDispatcher("Deco").forward(request,response);
                    break;
                default :
                    break;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        int taille = HelperURL.getNbElement(url);
        if(taille==1){
            @SuppressWarnings("unchecked")
            List<String> users = (List<String>) request.getServletContext().getAttribute("users");
            HelperParse.parseInAnswer(request, response, users);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}