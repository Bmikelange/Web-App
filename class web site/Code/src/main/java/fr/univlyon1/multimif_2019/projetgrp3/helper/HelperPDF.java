package fr.univlyon1.multimif_2019.projetgrp3.helper;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Student;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.PresenceRecord;
import fr.univlyon1.multimif_2019.projetgrp3.constante.ConstLogin;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.*;

/**
 * The goal of this class is to write a pdf that represent the entire presenceRecord of a student.
 */
public class HelperPDF {

  /**
   * Construtor.
   */
    private HelperPDF() {

    }

    public static void writePDF(Student student, String pdfName, HttpServletRequest request)
        throws IOException  {
  
      String contextPath = request.getServletContext().getRealPath(File.separator);
      Logger logger = Logger.getLogger("test");
      Document document = new Document();
      try {
        PdfWriter.getInstance(document, new FileOutputStream(contextPath + "pdf/"+ pdfName));
      } catch (FileNotFoundException|DocumentException e) {
        logger.log(Level.SEVERE, ConstLogin.ERROR, e);
      }
      document.open();
      createPdf(student, document);
      document.close();
    }

  private static void createPdf(Student student, Document document) {
      nameToPdf(student, document);
      tableToPdf(student,document);
  }

  private static void nameToPdf(Student student, Document document) {
    Font font = FontFactory.getFont(FontFactory.HELVETICA,20,BaseColor.BLACK);
    Paragraph text = new Paragraph(student.getName() + " "+ student.getFirstName(),font);
    Logger logger = Logger.getLogger("test");
    try {
      document.add(text);
      text = new Paragraph(student.getId(),font);
      document.add(text);
    } catch (DocumentException e) {
      logger.log(Level.SEVERE, ConstLogin.ERROR, e);
    }
  }

  private static void tableToPdf(Student student, Document document) {
    PdfPTable table = new PdfPTable(3);
    Logger logger = Logger.getLogger("test");
    for (PresenceRecord presenceRecord : student.getPresencesRecords())
    {
      PdfPCell cell = new PdfPCell(new Phrase(presenceRecord.getLesson().getName()));  
      cell.setColspan(1);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(presenceRecord.getLesson().getDate().toString()));  
      cell.setColspan(1);
      table.addCell(cell);
      cell = new PdfPCell(new Phrase(Boolean.toString(presenceRecord.isPresence())));  
      cell.setColspan(1);
      table.addCell(cell);
    }
    try {
      document.add(table); 
    } catch (DocumentException e) {
      logger.log(Level.SEVERE, ConstLogin.ERROR, e);
    }
  }


  public static void getPDF(HttpServletRequest request, HttpServletResponse response, String pdfName) 
      throws IOException {
  
    String contextPath = request.getServletContext().getRealPath(File.separator);
    File pdfFile = new File(contextPath + "pdf/" +  pdfName);
    Logger logger = Logger.getLogger("test");

    response.setContentType("application/pdf");
    response.addHeader("Content-Disposition", "attachment; filename=" + pdfName);
    response.setContentLength((int) pdfFile.length());
    try (FileInputStream fileInputStream = new FileInputStream(pdfFile)) {
      OutputStream responseOutputStream = response.getOutputStream();
      int bytes;
      while ((bytes = fileInputStream.read()) != -1) {
        responseOutputStream.write(bytes);
      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, ConstLogin.ERROR, e);
    }  
  }

  public static void getZIP(HttpServletRequest request, HttpServletResponse response, String zipName) 
      throws IOException {
  
    String contextPath = request.getServletContext().getRealPath(File.separator);
    File zipFile = new File(contextPath + "pdf/" +  zipName);
    Logger logger = Logger.getLogger("test");

    response.setContentType("application/zip");
    response.addHeader("Content-Disposition", "attachment; filename=" + zipName);
    response.setContentLength((int) zipFile.length());

    try (FileInputStream fileInputStream = new FileInputStream(zipFile)) {
      OutputStream responseOutputStream = response.getOutputStream();
      int bytes;
      while ((bytes = fileInputStream.read()) != -1) {
        responseOutputStream.write(bytes);
      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, ConstLogin.ERROR, e);
    }
  }
}