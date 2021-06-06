package fr.univlyon1.multimif_2019.projetgrp3.servlets.secretaire;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Student;
import fr.univlyon1.multimif_2019.projetgrp3.helper.HelperPDF;
import fr.univlyon1.multimif_2019.projetgrp3.constante.ConstLogin;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.StudentDAO;
import fr.univlyon1.multimif_2019.projetgrp3.model.dao.DAOFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

/**
 * Creates a PDF file which contains students' informations
 */
@WebServlet(name = "PDFOne", urlPatterns = "/secretaire/PDFOne")
public class PDFOne extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Makes the PDF file.
     * Called by a GET Request to the servlet.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String identifiant = request.getParameter("identifiant");
        Logger logger = Logger.getLogger("test");
        StudentDAO studentDao = (StudentDAO) DAOFactory.getStudentDAO();
        List<Student> l = studentDao.getStudentbyID(identifiant);
        Student s = l.get(0);
        
        String pdfName = s.getName() + s.getFirstName() + LocalDate.now().toString() + ".pdf";
        String contextPath = request.getServletContext().getRealPath(File.separator);
        try {
          File fle = new File(contextPath + "pdf/");
          fle.mkdir();
        } catch (Exception exp) {
          logger.log(Level.SEVERE, ConstLogin.ERROR, exp);
        }
        try {
          HelperPDF.writePDF(s,pdfName,request);
        } catch ( FileNotFoundException uhex) {
          logger.log(Level.SEVERE, ConstLogin.ERROR, uhex);
        } catch ( IOException fne) {
          logger.log(Level.SEVERE, ConstLogin.ERROR, fne);
        }

        try {
          HelperPDF.getPDF(request,response,pdfName);
        } catch ( FileNotFoundException uhex) {
          logger.log(Level.SEVERE, ConstLogin.ERROR, uhex);
        } catch ( IOException fne) {
          logger.log(Level.SEVERE, ConstLogin.ERROR, fne);
        }
        File file = new File(contextPath + "pdf/" + pdfName); 
        Boolean bool = file.delete();
        if (!bool) {

        }
    }
}