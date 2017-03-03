/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.vle.dao.DbConnector;
import lk.vle.dao.SessionDAO;

/**
 *
 * @author Janakshi
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logout = req.getParameter("logout");
        String sessionEnd;
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();
        SessionDAO sdb = new SessionDAO(dbcon.getCon());
        String id = (String)(req.getSession().getAttribute("username")); 
        String start = (String)(req.getSession().getAttribute("start")); 
        String date = (String)(req.getSession().getAttribute("sessionDate")); 
        
        if (logout != null){
            
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss");
            sessionEnd=ft.format(dNow);
            sdb.insertSessionDetail(id, date, start, sessionEnd);
            
            req.getSession().invalidate();
        }
        
        resp.sendRedirect("index.jsp");
        
        dbcon.closeConnection();
    }
    
}
