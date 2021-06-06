package fr.univlyon1.m1if.m1if03.helper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import org.json.XML;

public class HelperParse {

    public static void parseInAnswer(HttpServletRequest request, HttpServletResponse response, Object data,
            String linkToView) throws ServletException, IOException {

        String accept = request.getHeader("accept");
            if (accept.startsWith("application/json")) {
                parseJsonInAnswer(response, data);
            } else if (accept.startsWith("application/xml")) {
                parseXmlInAnswer(response, data);
            } else if (!linkToView.isEmpty()) {
            redirectToView(request, response, data, linkToView);
        }
    }

    public static void parseInAnswer(HttpServletRequest request, HttpServletResponse response, Object data)
            throws ServletException, IOException {

        parseInAnswer(request, response, data, "");
    }

    public static void redirectToView(HttpServletRequest request, HttpServletResponse response, Object data,
            String linkToView) throws ServletException, IOException {

        //response.setContentType("text/html");
        // response.setCharacterEncoding("UTF-8");
        request.setAttribute("result", data);
        request.getServletContext().getNamedDispatcher(linkToView).forward(request, response);
        
    }

    private static void parseJsonInAnswer(HttpServletResponse response, Object data)
            throws ServletException, IOException {

        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private static void parseXmlInAnswer(HttpServletResponse response, Object data)
            throws ServletException, IOException {

        String json = new Gson().toJson(data);
        String xml = XML.toString(json);
        response.setContentType("application/xml");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(xml);
    }

    // private static String getPayload(HttpServletRequest request) throws
    // ServletException, IOException {
    // StringBuilder buffer = new StringBuilder();
    // BufferedReader reader = request.getReader();
    // String line;
    // while ((line = reader.readLine()) != null) {
    // buffer.append(line);
    // }
    // String data = buffer.toString();
    // return data;
    // }
}