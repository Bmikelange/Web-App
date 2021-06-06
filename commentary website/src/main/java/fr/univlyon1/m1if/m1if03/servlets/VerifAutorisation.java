package fr.univlyon1.m1if.m1if03.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.univlyon1.m1if.m1if03.classes.Groupe;

@WebFilter("/VerifAutorisation")
public class VerifAutorisation implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(true);
        String pseudo = (String) session.getAttribute("pseudo");

        String groupeName = (String) session.getAttribute("groupeName");
        @SuppressWarnings("unchecked")
        HashMap<String, Groupe> groupes = (HashMap<String, Groupe>) request.getServletContext().getAttribute("groupes");
        Groupe groupe = groupes.get(groupeName);

        if (groupe.containsParticipant(pseudo)) {
            chain.doFilter(request, response);
        } else {
            request.getServletContext().getNamedDispatcher("Init").forward(request,response);
        }

    }

}