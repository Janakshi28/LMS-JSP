/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.vle.beans.AssignmentBean;
import lk.vle.beans.QuizBean;
import lk.vle.dao.DbConnector;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Janakshi
 */
public class SubmitAssignmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String aID= req.getParameter("aID");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String deadline = req.getParameter("deadline");
        Date dNow = new Date( );
        String dToday = sdf.format(dNow); 
        boolean access = false;
        RequestDispatcher rd;
        
        DbConnector dbcon = new DbConnector();
            dbcon.openConnection();
        QuizBean qzdb = new QuizBean(dbcon.getCon());
        
        int role= (int)(req.getSession().getAttribute("role")); 
        try {
            Date date1 = sdf.parse(dToday);
            Date date2 = sdf.parse(deadline);
            //Date date2 = sdf.parse("2016-05-23"); //To test when the access date has passed the deadline
            
                if(date1.compareTo(date2)>0){
        		access=false;
        	}else if(date1.compareTo(date2)<0 ||date1.compareTo(date2)==0){
        		access= true;
        	}
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        
            if(access == true){
                    
                    req.setAttribute("eligible","1");
            }
            else{
                    
                    req.setAttribute("eligible","0");        
            }
                    req.setAttribute("aID",aID);
                    rd= req.getRequestDispatcher("submitAssignment.jsp");
                    rd.forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String folderPath = "C:\\Users\\Janakshi\\Documents\\NetBeansProjects\\VirtualClassroom\\web\\AssignmentSubmissions\\";
        String stdID = (String)(req.getSession().getAttribute("id"));        
        String aID = "xxx";
        String fileName="xxx.txt";
        RequestDispatcher rd;
            DbConnector dbcon = new DbConnector();
            dbcon.openConnection();
        AssignmentBean adb = new AssignmentBean(dbcon.getCon()); 
        
         File file ;
            int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;
        
        String contentType = req.getContentType();
            if ((contentType.indexOf("multipart/form-data") >= 0)) {

               DiskFileItemFactory factory = new DiskFileItemFactory();
               factory.setSizeThreshold(maxMemSize);
               factory.setRepository(new File("c:\\temp"));
               ServletFileUpload upload = new ServletFileUpload(factory);
               upload.setSizeMax( maxFileSize );
               
               try{ 
                  List fileItems = upload.parseRequest(req);
                  Iterator i = fileItems.iterator();
                  //out.println("<html>");
                  //out.println("<body>");
                  while ( i.hasNext () ) 
                  {
                     FileItem fi = (FileItem)i.next();
                     if(fi.isFormField ()){
                         String name = fi.getFieldName();//text1
                         if(name.equals("aID")){
                             aID= fi.getString();
                             System.out.println(aID);
                         }

                     }
                     else if ( !fi.isFormField () )  {
                         String fieldName = fi.getFieldName();
                         fileName = fi.getName();
                         boolean isInMemory = fi.isInMemory();
                         long sizeInBytes = fi.getSize();
                         
                         // Write the file
                         if( fileName.lastIndexOf("\\") >= 0 ){
                            file = new File( folderPath + 
                            fileName.substring( fileName.lastIndexOf("\\"))) ;
                         }else{
                            file = new File( folderPath + 
                            fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                         }
                            fi.write( file );
                            req.setAttribute("fPath",folderPath);
                            req.setAttribute("fName",fileName);

                     }
                            
                            
                  }
 
                            if(adb.addAssignmentSubmission(stdID, aID, fileName)){
                                System.out.println("submission added to db");
                                req.setAttribute("aID", aID);
                                req.setAttribute("AsStatus","1");
                                rd= req.getRequestDispatcher("submitAssignment.jsp");
                                rd.forward(req, resp);
                            }else{
                                System.out.println("submission wasn't added to db");
                            }
                            
                  //out.println("</body>");
                  //out.println("</html>");
               }catch(Exception ex) {
                  System.out.println(ex);
               }
            }else{
                
                req.setAttribute("AsStatus","2");
                req.setAttribute("aID", aID);
                rd= req.getRequestDispatcher("submitAssignment.jsp");
                rd.forward(req, resp);
                //resp.sendRedirect("index.jsp?status=0");
              // out.println("<html>");
              // out.println("<body>");
              // out.println("<p>No file uploaded</p>"); 
              // out.println("</body>");
              // out.println("</html>");
            }    
            
       dbcon.closeConnection();
        
    }
    
    

    

}
