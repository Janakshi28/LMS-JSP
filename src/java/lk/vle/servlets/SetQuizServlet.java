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
import lk.vle.beans.QuestionBean;
import lk.vle.beans.QuizBean;
import lk.vle.dao.DbConnector;

/**
 *
 * @author Janakshi
 */
public class SetQuizServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String qtitle=req.getParameter("qTitle");
        String qID=req.getParameter("qID");
        String deadline=req.getParameter("deadline");
        int period=Integer.parseInt(req.getParameter("period"));
        String cID=req.getParameter("cID");
    
        //String qID, String qtitle, String deadline, int period, String cID 
        RequestDispatcher rd;
        
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();
        QuizBean qzdb = new QuizBean(dbcon.getCon());
        
        if((qzdb.insertQuizTODB(qID, qtitle, deadline, period, cID))==true){
             req.setAttribute("qzStatus","1");    
        }
        else{
            req.setAttribute("qzStatus","0"); 
        }
             rd= req.getRequestDispatcher("setQuiz.jsp");
             rd.forward(req, resp);
        
        dbcon.closeConnection();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String qID=req.getParameter("qID");
        RequestDispatcher rd;
        
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();
        QuizBean qzdb = new QuizBean(dbcon.getCon());
        
        if((qzdb.makeQuizReady(qID))==true){
             req.setAttribute("rStatus","1");    
        }
        else{
            req.setAttribute("rStatus","0"); 
        }
             rd= req.getRequestDispatcher("setQuiz.jsp");
             rd.forward(req, resp);
        
        dbcon.closeConnection();     
    }

    
    

}
