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
import lk.vle.beans.AssessmentBean;
import lk.vle.dao.DbConnector;

/**
 *
 * @author Janakshi
 */
@WebServlet(name = "FeedbackServlet", urlPatterns = {"/FeedbackServlet"})
public class FeedbackServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
            String id= req.getParameter("id");
            String stdID = req.getParameter("stdID");
            String subNo = req.getParameter("subNo");
            
            
            //System.out.println(mID);
            req.setAttribute("id",id);
            req.setAttribute("stdID",stdID);
            req.setAttribute("subNo",subNo); 
            req.setAttribute("fStatus","1");
            rd= req.getRequestDispatcher("feedbacks.jsp");
            rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String com = req.getParameter("comment");
        char grade = req.getParameter("grade").charAt(0);
        int subNo = Integer.parseInt(req.getParameter("subNo"));
        String id = (String)req.getSession().getAttribute("id");
        int fNo = 0;
         RequestDispatcher rd; 
         DbConnector dbcon = new DbConnector();
            dbcon.openConnection();        
            AssessmentBean adb = new AssessmentBean(dbcon.getCon());
            
            if (!dbcon.isConnectionValid()){ //Connection to the database was not successful
                resp.sendRedirect("sitedown.jsp");
            }
            else{
                fNo=adb.getFeedbackSum();
                if(adb.insertLFeedbackTODB(fNo, com, grade, subNo, id)== true){
                    req.setAttribute("SFstatus","1");
                }
                else{
                    req.setAttribute("SFstatus","0");
                }
                rd= req.getRequestDispatcher("feedbacks.jsp");
                rd.forward(req, resp);
            }
            
         dbcon.closeConnection();
            
    }
    
    

    

}
