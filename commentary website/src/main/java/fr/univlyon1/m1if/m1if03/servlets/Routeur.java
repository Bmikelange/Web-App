package fr.univlyon1.m1if.m1if03.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequestWrapper;
import fr.univlyon1.m1if.m1if03.helper.HelperURL;

import java.util.ArrayList;
import java.util.HashMap;

import fr.univlyon1.m1if.m1if03.classes.Groupe;

@WebServlet(name = "Routeur", urlPatterns = "/*")
public class Routeur extends HttpServlet {

    ServletContext ctx;
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute("groupes", new HashMap<String, Groupe>());
        config.getServletContext().setAttribute("lUsers", new ArrayList<String>());
        super.init(config);
        ctx = getServletContext();
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.dispatch(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.dispatch(request,response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.dispatch(request,response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.dispatch(request,response);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        
        String url = request.getRequestURI();
        String way = HelperURL.getNiemeElement(url, 0);
        Integer taille = HelperURL.getNbElement(url);

        if(taille > 0) { // l'URL est complète

            switch (way)
            {
                case "users" : 
                    dispatcher = ctx.getNamedDispatcher("Users");
                    if ( dispatcher != null ) {
                        dispatcher.forward(request,response);
                    } else {
                        dispatcher = getServletContext().getNamedDispatcher("default");
        
                        HttpServletRequest wrapped = new HttpServletRequestWrapper(request) {
                            public String getServletPath() { return ""; }
                        };
        
                        dispatcher.forward(wrapped, response);
                    }
                    break;
                case "groupes" : 
                    dispatcher = ctx.getNamedDispatcher("Groupes");
                    if ( dispatcher != null ) {
                        dispatcher.forward(request,response);
                     } else {
                        dispatcher = getServletContext().getNamedDispatcher("default");
        
                        HttpServletRequest wrapped = new HttpServletRequestWrapper(request) {
                            public String getServletPath() { return ""; }
                        };
        
                        dispatcher.forward(wrapped, response);
                    }
                    break;
                default :
                    dispatcher = getServletContext().getNamedDispatcher("default");
            
                    HttpServletRequest wrapped = new HttpServletRequestWrapper(request) {
                        public String getServletPath() { return ""; }
                    };

                    dispatcher.forward(wrapped, response);
                    break;
            }
        } else {
            dispatcher = request.getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }
    }
}