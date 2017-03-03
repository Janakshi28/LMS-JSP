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
public class EnrollServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String cID = req.getParameter("crsID");
            String enKey = req.getParameter("enKey");
            int role = (int)req.getSession().getAttribute("role");
            String id = (String)req.getSession().getAttribute("id");
            
            RequestDispatcher rd;
            DbConnector dbcon = new DbConnector();
            dbcon.openConnection();
            
            CourseBean crsdb = new CourseBean(dbcon.getCon());
                    
            
        switch (crsdb.enrollForCourse(id, role, cID, enKey)) {
            case 1:
                req.setAttribute("eStatus","1");
                break;
            case 3:
                req.setAttribute("eStatus","2");
                break;
            case 2:
                req.setAttribute("eStatus","3");
                break;
            default:
                break;
        }
            
            rd= req.getRequestDispatcher("allCourses.jsp");
            rd.forward(req, resp);
            
            
        dbcon.closeConnection();    
            
    }

 
}
