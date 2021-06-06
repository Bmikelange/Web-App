package fr.univlyon1.m1if.m1if03.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.http.Cookie;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.exceptions.*;

import fr.univlyon1.m1if.m1if03.helper.HelperLink;

@WebServlet(name = "Init", urlPatterns="/Init")
public class Init extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pseudo = request.getParameter("pseudo");
        @SuppressWarnings("unchecked")
        List<String> users = (List<String>) request.getServletContext()
        .getAttribute("lUsers");
        users.add(pseudo);
        if (havePseudo(pseudo)) {
            try {
                Algorithm algorithm = Algorithm.HMAC256("secret");
                String token = JWT.create()
                    .withIssuer("auth0")
                    .withSubject(pseudo)
                    .sign(algorithm);
                Cookie cooki = new Cookie("sessionKey", token);
                cooki.setMaxAge(60 * 60 * 24 * 30);
                request.getServletContext().setAttribute("cookie",cooki);
            } catch (JWTCreationException exception){
                //Invalid Signing configuration / Couldn't convert Claims.
            }
            response.sendRedirect(HelperLink.groupe);
        } else {
            request.getRequestDispatcher("/index.html").include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.getRequestDispatcher("/index.html").include(request, response);
    }

    private boolean havePseudo(String pseudo) {
        return pseudo != null && !pseudo.equals("");
    }
}
