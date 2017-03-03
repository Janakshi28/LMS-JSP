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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.vle.beans.CourseBean;
import lk.vle.dao.DbConnector;

/**
 *
 * @author Janakshi
 */
public class SetLOServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            RequestDispatcher rd;
            String crsID = req.getParameter("crsID");
            
                req.setAttribute("crsID",crsID);
                rd= req.getRequestDispatcher("setLOutcomes.jsp");
                rd.forward(req, resp);     
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            RequestDispatcher rd;
            DbConnector dbcon = new DbConnector();
            dbcon.openConnection();
        
        CourseBean crsdb = new CourseBean(dbcon.getCon());
        
            String crsID=req.getParameter("crsID");
            String loutcome= req.getParameter("loutcome");
            System.out.println(loutcome);
            if (!dbcon.isConnectionValid()){ //Connection to the database was not successful
                resp.sendRedirect("sitedown.jsp");
            }else{
                if(crsdb.setLearningOutcomes(crsID,loutcome)){
                    System.out.println(crsID);
                    req.setAttribute("CRS",crsID);
                    req.setAttribute("lStatus","1");

                }
                else{
                    req.setAttribute("lStatus","2");     
                }
                rd= req.getRequestDispatcher("myCourses.jsp");
                rd.forward(req, resp);
            }
        
    }
    
    
    
    



}
