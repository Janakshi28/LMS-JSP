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
import lk.vle.dao.DbConnector;

/**
 *
 * @author Janakshi
 */
public class SetQuestionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int qNo = Integer.parseInt(req.getParameter("qNo"));
        String qDesc=req.getParameter("qDesc");
        int correct= Integer.parseInt(req.getParameter("correct"));
        String qID=req.getParameter("qID");
        String ch1=req.getParameter("ch1");
        String ch2=req.getParameter("ch2");
        String ch3=req.getParameter("ch3");
        String ch4=req.getParameter("ch4");
        
        RequestDispatcher rd;
        
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();
        QuestionBean qdb = new QuestionBean(dbcon.getCon());
        
        if(qdb.insertQuestionTODB(qNo, qDesc, correct, qID, ch1, ch2, ch3, ch4)==true){
             req.setAttribute("qStatus","1");    
        }
        else{
            req.setAttribute("qStatus","0"); 
        }
             rd= req.getRequestDispatcher("setQuiz.jsp");
             rd.forward(req, resp);
        
        dbcon.closeConnection();
    }



}
