/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.vle.beans.AdminBean;
import lk.vle.beans.CourseBean;
import lk.vle.beans.UserBean;
import lk.vle.dao.DbConnector;
import lk.vle.dao.LoginDAO;

/**
 *
 * @author Janakshi
 */
@WebServlet(name = "CourseServlet", urlPatterns = {"/CourseServlet"})
public class CourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        
        
        RequestDispatcher rd;
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();        
        CourseBean crsdb = new CourseBean(dbcon.getCon());              
        UserBean u = (UserBean)req.getSession().getAttribute("user");     
        
           String crsID = req.getParameter("crsID");
           String crs = req.getParameter("crs"); 
            if (!dbcon.isConnectionValid()){ //Connection to the database was not successful
                resp.sendRedirect("sitedown.jsp");
            } 
            else {

                if(u.getUserID().equals("Guest")){   
                    req.setAttribute("cStatus", "1");
                }
                else if(!u.getUserID().equals("Guest")){
                    int role = (int)req.getSession().getAttribute("role");
                    String id = (String)req.getSession().getAttribute("id");
                    //System.out.println(id);
                    if(role!= 1){
                        if(crsdb.isEnrolled(id, crsID, role)==true){
                           req.setAttribute("cStatus", "2"); 
                        }
                        else{
                           req.setAttribute("cStatus", "3");  
                        }
                    }
                    else{
                        req.setAttribute("cStatus", "4"); 
                    }
                }
                String crsDesc = crsdb.readCrsDesc(crsID);        
                req.setAttribute("crsDesc",crsDesc);
                req.setAttribute("crs",crs);
                req.setAttribute("crsID",crsID);
                rd= req.getRequestDispatcher("allCourses.jsp");
                rd.forward(req, resp);

            }
        dbcon.closeConnection();
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        RequestDispatcher rd;
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();
        
        CourseBean crsdb = new CourseBean(dbcon.getCon());
        
        if(req.getParameter("loutcome")== null){
                String crsID= req.getParameter("crsID"); 
                String crsTitle= req.getParameter("crsTitle");
                String dCategory= req.getParameter("dCat");
                String degree= req.getParameter("degree");
                String crsDesc= req.getParameter("crsDesc");
        
            AdminBean admin = (AdminBean)req.getSession().getAttribute("admin");
            if (!dbcon.isConnectionValid()){ //Connection to the database was not successful
                resp.sendRedirect("sitedown.jsp");
            } else{
                if(admin.getAdSection().equals(dCategory)){
                    
                    crsdb.addCourse(crsID, crsTitle, dCategory, degree, crsDesc);
                    req.setAttribute("addStatus", "1"); 
                }
                else{
                    req.setAttribute("addStatus", "2");  
                }

                rd= req.getRequestDispatcher("addNewCourse.jsp");
                rd.forward(req, resp);
            }
        }
        
      dbcon.closeConnection();  
        
    }
    
    



}
