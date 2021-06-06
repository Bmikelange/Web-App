package fr.univlyon1.m1if.m1if03.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.univlyon1.m1if.m1if03.helper.HelperToken;
import javax.servlet.http.Cookie;

@WebFilter("/VerifConnexion")
public class VerifConnexion implements Filter {

    List<String> excludeUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.excludeUrl = new ArrayList<>();
        this.excludeUrl.add("/v2");
        this.excludeUrl.add("/v2/users/login");
        this.excludeUrl.add("/v2/Init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();

        Cookie cooki= (Cookie)httpRequest.getServletContext().getAttribute("cookie");
        if (cooki != null)
        {
            ((HttpServletResponse)response).addCookie(cooki);
            request.getServletContext().removeAttribute("cookie");
        }

        Cookie[] cookies = httpRequest.getCookies();
        String pseudo = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sessionKey")) {
                    pseudo = HelperToken.returnPseudo(cookie.getValue());
                    request.setAttribute("pseudo",pseudo);
                }
            }
        }
        if (pseudo != null || excludeUrl.contains(path)) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/index.html").forward(request,response);
        }
    }

}
