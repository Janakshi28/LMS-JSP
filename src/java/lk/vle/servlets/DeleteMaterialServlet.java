/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.vle.beans.CourseMaterialBean;
import lk.vle.dao.DbConnector;

/**
 *
 * @author Janakshi
 */
public class DeleteMaterialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mID = req.getParameter("mID");
        String folderPath = "C:\\Users\\Janakshi\\Documents\\NetBeansProjects\\VirtualClassroom\\web\\CourseMaterials\\";
        RequestDispatcher rd;
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();        
        CourseMaterialBean crsMdb = new CourseMaterialBean(dbcon.getCon());
        String fileName="xxx.txt";
        
                            
                    if (!dbcon.isConnectionValid()){ //Connection to the database was not successful
                        resp.sendRedirect("sitedown.jsp");
                    } 
                    else{
                            
                            fileName = crsMdb.getmFileName(mID);
                            System.out.println(fileName);
                            try{

                                String tempFile = folderPath+fileName;
                                //Delete if tempFile exists
                                File fileTemp = new File(tempFile);
                                  if (fileTemp.exists()){
                                     fileTemp.delete();

                                  }   
                            }catch(Exception ex) {
                                 System.out.println(ex);
                            }
                            
                            if(crsMdb.deleteMaterial(mID)==true){
                                req.setAttribute("mDStatus", "1"); 
                            }
                            else{
                                req.setAttribute("mDStatus", "2");  
                            }
                            
                            req.setAttribute("mID",mID);
                            rd= req.getRequestDispatcher("manageContent.jsp");
                            rd.forward(req, resp);
                            
                            
                    }  
                    
        dbcon.closeConnection();
                            
    }

        
}
