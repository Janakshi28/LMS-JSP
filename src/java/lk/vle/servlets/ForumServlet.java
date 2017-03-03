/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.vle.beans.CourseBean;
import lk.vle.beans.ForumBean;
import lk.vle.dao.DbConnector;

/**
 *
 * @author Janakshi
 */
public class ForumServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cID = req.getParameter("cID");
        
        RequestDispatcher rd;
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();
        
        ForumBean forumdb = new ForumBean(dbcon.getCon());
        
            if (!dbcon.isConnectionValid()){ //Connection to the database was not successful
                resp.sendRedirect("sitedown.jsp");
            } else{
                if(forumdb.readForumID(cID)!= null){
                    String fID = forumdb.readForumID(cID);
                    ArrayList<String> fmsgs=new ArrayList<String>(forumdb.readForumMSGs(fID));
                    req.setAttribute("forumStatus", "1");
                    req.setAttribute("cID", cID);
                    req.setAttribute("fID", fID);
                    req.setAttribute("msgList", fmsgs);
                }
                else{
                    req.setAttribute("forumStatus", "2");  
                }

                rd= req.getRequestDispatcher("forum.jsp");
                rd.forward(req, resp);
            }
        
        
      dbcon.closeConnection();  
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cID = req.getParameter("cID");
        String fID = req.getParameter("fID");
        String msg = req.getParameter("msg");
        String sender = (String)req.getSession().getAttribute("id");
        Date dNow = new Date( );
        SimpleDateFormat ft1 = new SimpleDateFormat ("yyyy-MM-dd");
        SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss");
        String time = ft.format(dNow);
        String date = ft1.format(dNow);
        
        RequestDispatcher rd;
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();
        
        ForumBean forumdb = new ForumBean(dbcon.getCon());
        
        if (!dbcon.isConnectionValid()){ //Connection to the database was not successful
                resp.sendRedirect("sitedown.jsp");
        } else{
            if(forumdb.addForumMsgToDB(fID, date, time, msg, sender)==true){
                req.setAttribute("msgStatus", "1");  
            } else{
                req.setAttribute("msgStatus", "2");  
            }

                rd= req.getRequestDispatcher("forum.jsp");
                rd.forward(req, resp);
            
        }
        
        dbcon.closeConnection();  
        
    }
    
    



}
