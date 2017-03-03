/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.vle.beans.AssignmentBean;
import lk.vle.dao.DbConnector;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Janakshi
 */
public class AddAssignmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String folderPath = "C:\\Users\\Janakshi\\Documents\\NetBeansProjects\\VirtualClassroom\\web\\Assessments\\";
        RequestDispatcher rd;
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();
        AssignmentBean adb = new AssignmentBean(dbcon.getCon());
        String aID="xxx";
        String aType="xxx";
        String handout="xxx";
        String handin="xxx";
        String cID="xxx";
        String fileName="xxx.txt";
        
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
                         switch(name){
                             case "aID": aID= fi.getString();
                                        System.out.println(aID);
                                break;
                             case "aCat": aType = fi.getString();
                                        System.out.println(aType);
                                break;
                             case "handout":handout= fi.getString();
                                        System.out.println(handout);
                                break;
                             case "handin": handin= fi.getString();
                                        System.out.println(handin);
                                break;
                             case "cID":cID= fi.getString();
                                        System.out.println(cID);
                                break;   
                             default: System.out.println("No match found");
                                break;
                             
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
                            req.setAttribute("aStatus","1");
                            req.setAttribute("fPath",folderPath);
                            req.setAttribute("fName",fileName);

                     }
                            
                            
                  }
 
                            if(adb.insertAssignmentTODB(aID, aType, handout, handin, cID, fileName))
                                    System.out.println("New assignment stored successfully");
                            else System.out.println("Not added to db");
                            rd= req.getRequestDispatcher("addAssignment.jsp");
                            rd.forward(req, resp);
                  //out.println("</body>");
                  //out.println("</html>");
               }catch(Exception ex) {
                  System.out.println(ex);
               }
            }else{
                
                req.setAttribute("aStatus","0");
                rd= req.getRequestDispatcher("addAssignment.jsp");
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
