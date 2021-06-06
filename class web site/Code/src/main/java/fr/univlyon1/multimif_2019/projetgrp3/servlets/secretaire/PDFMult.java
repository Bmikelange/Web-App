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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Creates a zipped folder, which contains a set of PDF documents, with students' informations.
 * @see HttpServlet
 */
@WebServlet(name = "PDFMult", urlPatterns = "/secretaire/PDFMult")
public class PDFMult extends HttpServlet {

  private static final long serialVersionUID = 1L;

  /**
   * Makes a new zip folder with students' informations PDF.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String identifiant = request.getParameter("identifiant");
    Logger logger = Logger.getLogger("test");
    StudentDAO studentDao = (StudentDAO) DAOFactory.getStudentDAO();
    List<Student> students= studentDao.getStudentbyGroupe(identifiant);
    String contextPath = request.getServletContext().getRealPath(File.separator);
    try {
      File fle = new File(contextPath + "pdf/");
      fle.mkdir();
    } catch (Exception exp) {
      logger.log(Level.SEVERE, ConstLogin.ERROR, exp);
    }
    String zipName = LocalDate.now().toString() + ".zip";
    try (FileOutputStream fos = new FileOutputStream(contextPath + "pdf/" + zipName)) {
      try (ZipOutputStream zipOS = new ZipOutputStream(fos)) {
        for (Student student : students ) {
          String pdfName = student.getName() + student.getFirstName() + LocalDate.now().toString() + ".pdf";
          HelperPDF.writePDF(student,pdfName,request);
          this.writeToZipFile(contextPath + "pdf/" + pdfName, zipOS);
          File file = new File(contextPath + "pdf/" + pdfName); 
          Boolean bool = file.delete();
          if (!(Boolean.TRUE.equals(bool))) {
            bool=false;
          }
        }
      } catch (Exception ex) {
        logger.log(Level.SEVERE, ConstLogin.ERROR, ex);
      } 
    } catch (Exception e) {
      logger.log(Level.SEVERE, ConstLogin.ERROR, e);
    }
    try {
      HelperPDF.getZIP(request,response,zipName);
    } catch ( IOException fne) {
      logger.log(Level.SEVERE, ConstLogin.ERROR, fne);
    }
    File file = new File(contextPath + "pdf/" + zipName); 
    Boolean bool = file.delete();
    if (!(Boolean.TRUE.equals(bool))) {
      bool=false;
    }
  }

  /**
   * Exports a new PDF file into the zip folder.
   * @param path path where we can find the PDF file.
   * @param zipStream stream which will store the zip folder.
   * @throws IOException Error with a java.io element.
   * @see IOException
   */
  public void writeToZipFile(String path, ZipOutputStream zipStream)
            throws IOException {

    File file = new File(path);
    Logger logger = Logger.getLogger("test");

    try (FileInputStream fis = new FileInputStream(file)){
      ZipEntry zipEntry = new ZipEntry(path);
      zipStream.putNextEntry(zipEntry);

      byte[] bytes = new byte[1024];
      int length;
      while ((length = fis.read(bytes)) >= 0) {
          zipStream.write(bytes, 0, length);
      }

      zipStream.closeEntry();
    } catch (Exception ex) {
      logger.log(Level.SEVERE, ConstLogin.ERROR, ex);
    }
    
}


}