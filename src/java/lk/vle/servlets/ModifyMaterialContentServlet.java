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
@WebServlet(name = "ModifyMaterialContentServlet", urlPatterns = {"/ModifyMaterialContentServlet"})
public class ModifyMaterialContentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String folderPath = "C:\\Users\\Janakshi\\Documents\\NetBeansProjects\\VirtualClassroom\\web\\CourseMaterials\\";
        RequestDispatcher rd;
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();        
        CourseMaterialBean crsMdb = new CourseMaterialBean(dbcon.getCon());
        String mID="xxx";
        String fileName="xxx.txt";
        //CourseMaterialBean crsMdb = (CourseMaterialBean)dbcon.getItem("COURSEMATERIALS", mID);
        //crsMdb.setCon(dbcon.getCon());
        
         //This code snippet was written reffering to www.java2s.com (2016)
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
                         System.out.println(name);
                          mID = fi.getString();
                         System.out.println(mID);
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
                            req.setAttribute("mUStatus","1");
                            req.setAttribute("fPath",folderPath);
                            req.setAttribute("fName",fileName);
                            //crsMdb.modifyMaterialFile(mID,fileName);
                            //System.out.println(fileName);
                           
                            //resp.sendRedirect("index.jsp?status=1&Path="+filePath+"&Name="+fileName);
                         //out.println("Uploaded Filename: " + filePath + fileName + "<br>");
                     }
                            //System.out.println(fileName);
                            String fName = crsMdb.getmFileName(mID);
                            System.out.println(fName);
                            try{

                                String tempFile = folderPath+fName;
                                //Delete if tempFile exists
                                File fileTemp = new File(tempFile);
                                  if (fileTemp.exists()){
                                     fileTemp.delete();

                                  }   
                             }catch(Exception ex) {
                                 System.out.println(ex);
                             }

                            crsMdb.modifyMaterialFile(mID,fileName);
                            rd= req.getRequestDispatcher("editMaterial.jsp");
                            rd.forward(req, resp);
                  }
                  //out.println("</body>");
                  //out.println("</html>");
               }catch(Exception ex) {
                  System.out.println(ex);
               }
            }else{
                
                req.setAttribute("mUStatus","0");
                rd= req.getRequestDispatcher("editMaterial.jsp");
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
