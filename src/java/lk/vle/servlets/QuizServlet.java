/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.servlets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.vle.beans.QuestionBean;
import lk.vle.beans.QuizBean;
import lk.vle.dao.DbConnector;
import lk.vle.dao.LoginDAO;

/**
 *
 * @author Janakshi
 */
public class QuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String qID= req.getParameter("qID");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String deadline = req.getParameter("deadline");
        Date dNow = new Date();
        String dToday = sdf.format(dNow); 
        boolean access = false;
        RequestDispatcher rd;
        
        DbConnector dbcon = new DbConnector();
            dbcon.openConnection();
        QuizBean qzdb = new QuizBean(dbcon.getCon());
        
        int role= (int)(req.getSession().getAttribute("role")); 
        
        try {
            Date date1 = sdf.parse(dToday);
            Date date2 = sdf.parse(deadline);
            //Date date2 = sdf.parse("2016-05-23"); //To test when the access date has passed the deadline
            
                if(date1.compareTo(date2)>0){
        		access=false;
        	}else if(date1.compareTo(date2)<0 ||date1.compareTo(date2)==0){
        		access= true;
        	}
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        
        if(role==2){
                    req.setAttribute("qID",qID);
                    rd= req.getRequestDispatcher("accessQuiz.jsp");
                    rd.forward(req, resp);
        }
        else if(role==3){
            System.out.println(qzdb.isQuizReady(qID));
            if(access == true && qzdb.isQuizReady(qID)==true){
                    req.setAttribute("qID",qID);
                    rd= req.getRequestDispatcher("accessQuiz.jsp");
                    rd.forward(req, resp);
            }
            else{
                    req.setAttribute("qID",qID);
                    req.setAttribute("qStatus","0");
                    rd= req.getRequestDispatcher("viewAssessments.jsp");
                    rd.forward(req, resp);
            }
            
        }
        
        dbcon.closeConnection();
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int count= 0;
        String stdID = (String)(req.getSession().getAttribute("id"));        
        String qID = req.getParameter("qID");
        String answers = "\n\n  Quiz: "+qID+"\n  Student ID: "+stdID+"\n\n  Answers: \n";
         RequestDispatcher rd;
            DbConnector dbcon = new DbConnector();
            dbcon.openConnection();
            
            QuestionBean qdb = new QuestionBean(dbcon.getCon());
            QuizBean qzdb = new QuizBean(dbcon.getCon());
            
            for(int i= 1; i<=10;i++){
                answers= answers+"    Q"+i+") "+req.getParameter("CH"+i)+" \n";
                if(Integer.parseInt(req.getParameter("CH"+i))== qdb.retrieveCorrectChoice(qID, i)){
                    count++;
                }
            }
            
            answers = answers+"\n           No of correct answers(Out of 10): "+count
                             +"\n           Marks= "+(count/10.0)*100.0;
            
            String fileName="QA-"+qID+"-"+stdID+".txt";
            String path = "C:"+File.separator+"Users"+File.separator+"Janakshi"+File.separator+"Documents"+File.separator+"NetBeansProjects"+File.separator+"VirtualClassroom"+File.separator+"web"+File.separator+"QuizSubmissions"+File.separator+fileName ;
            File f = new File(path);
            f.getParentFile().mkdirs(); 
            f.createNewFile();
            FileWriter filewriter = new FileWriter(path, true);
            filewriter.write(answers);
            filewriter.close();
            
            if(qzdb.addQuizSubmission(stdID, qID, fileName)){
                System.out.println("Qz added to db");
                req.setAttribute("QsStatus","1");
            }
            else{
                System.out.println("Qz Not added to db");
                req.setAttribute("QsStatus","2");
            }
            req.setAttribute("qID",qID);
            rd= req.getRequestDispatcher("viewAssessments.jsp");
            rd.forward(req, resp);
            //addQuizSubmission(String type,String stdID, String qID, String qFile)
            
      dbcon.closeConnection();
            
    }
    
    



}
