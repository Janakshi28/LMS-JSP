/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.servlets;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.vle.beans.AssessmentBean;
import lk.vle.beans.CourseBean;
import lk.vle.beans.StudentBean;
import lk.vle.dao.DbConnector;

/**
 *
 * @author Janakshi
 */
@WebServlet(name = "PrintCertificateServlet", urlPatterns = {"/PrintCertificateServlet"})
public class PrintCertificateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cID = req.getParameter("cID");
        String id = (String)req.getSession().getAttribute("id");
        RequestDispatcher rd; 
         DbConnector dbcon = new DbConnector();
         dbcon.openConnection();  
         CourseBean crsdb = new CourseBean(dbcon.getCon());
         StudentBean stddb = new StudentBean(dbcon.getCon());
         AssessmentBean adb = new AssessmentBean(dbcon.getCon());
            
            if (!dbcon.isConnectionValid()){ //Connection to the database was not successful
                resp.sendRedirect("sitedown.jsp");
            }else{
                if(crsdb.isCourseComplete(cID, id)){
                    req.setAttribute("CStatus","1");
                    resp.setContentType("application/pdf");

                        //This code snippet was coded referring to (what-when-how.com)
                       try {        
                           Document document = new Document();
                           PdfWriter.getInstance(document, resp.getOutputStream());
                           document.open();
                           document.add(new Paragraph(crsdb.getCourseDetail(cID)));
                           document.add(new Paragraph(stddb.getStudentDetail(id)));
                           document.add(new Paragraph(adb.getFinalSubmissionDetail(id, cID)));
                           document.add(new Paragraph(new Date().toString()));
                           document.close();

                       } catch (DocumentException ex) {
                           throw new IOException(ex.getMessage());
                       }

                    
                }
                else{
                    req.setAttribute("CStatus","0");
                }
                rd= req.getRequestDispatcher("printCertificate.jsp");
                rd.forward(req, resp);
            }
        
        dbcon.closeConnection();
    }

    
    

}
