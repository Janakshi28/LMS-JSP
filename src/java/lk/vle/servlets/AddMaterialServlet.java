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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.vle.beans.CourseMaterialBean;
import lk.vle.dao.DbConnector;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Janakshi
 */
@WebServlet(name = "AddMaterialServlet", urlPatterns = {"/AddMaterialServlet"})
public class AddMaterialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         RequestDispatcher rd;
            String cID= req.getParameter("cID");
            //System.out.println(mID);
            req.setAttribute("cID",cID);            
            rd= req.getRequestDispatcher("addMaterial.jsp");
            rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String folderPath = "C:\\Users\\Janakshi\\Documents\\NetBeansProjects\\VirtualClassroom\\web\\CourseMaterials\\";
        RequestDispatcher rd;
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();        
        CourseMaterialBean crsMdb = new CourseMaterialBean(dbcon.getCon());
        String mID = "XXX";
        String mTitle = "XXX";
        String mType = "xxx";
        String fileName= "xxx.txt";
        String cID= "xXx";
        boolean visibility = false;
        
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
                             case "mID": mID= fi.getString();
                                        System.out.println(mID);
                                break;
                             case "mTitle": mTitle = fi.getString();
                                        System.out.println(mTitle);
                                break;
                             case "mCat":mType= fi.getString();
                                        System.out.println(mType);
                                break;
                             case "visible": visibility= Boolean.parseBoolean(fi.getString());
                                        System.out.println(visibility);
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
                            req.setAttribute("mAStatus","1");
                            req.setAttribute("fPath",folderPath);
                            req.setAttribute("fName",fileName);

                     }
                            
                            
                  }
 
                            if(crsMdb.insertMaterialTODB(mID, mTitle, mType,fileName,visibility,cID))
                                    System.out.println("New material stored successfully");
                            else System.out.println("Not added to db");
                            rd= req.getRequestDispatcher("addMaterial.jsp");
                            rd.forward(req, resp);
                  //out.println("</body>");
                  //out.println("</html>");
               }catch(Exception ex) {
                  System.out.println(ex);
               }
            }else{
                
                req.setAttribute("mAStatus","0");
                rd= req.getRequestDispatcher("addMaterial.jsp");
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
