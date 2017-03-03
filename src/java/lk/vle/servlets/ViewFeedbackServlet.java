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
import lk.vle.beans.AssessmentBean;
import lk.vle.dao.DbConnector;

/**
 *
 * @author Janakshi
 */
public class ViewFeedbackServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        String id = (String)req.getSession().getAttribute("id");
         DbConnector dbcon = new DbConnector();
            dbcon.openConnection();        
            AssessmentBean adb = new AssessmentBean(dbcon.getCon());
             if (!dbcon.isConnectionValid()){ //Connection to the database was not successful
                resp.sendRedirect("sitedown.jsp");
            }else{
                if(adb.eligibleForViewFeedback(id)== true){
                    req.setAttribute("EFstatus","1");
                }
                else{
                    req.setAttribute("EFstatus","0");
                }
                rd= req.getRequestDispatcher("feedbacks.jsp");
                rd.forward(req, resp); 
            }
            
        dbcon.closeConnection();
    }



}
