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
import javax.servlet.http.HttpSession;
import lk.vle.beans.AdminBean;
import lk.vle.beans.LecturerBean;
import lk.vle.beans.StudentBean;
import lk.vle.beans.UserBean;
import lk.vle.dao.DbConnector;
import lk.vle.dao.LoginDAO;

/**
 *
 * @author Janakshi
 */
public class LoginServlet extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");       
        int accessLevel;
        String sessionStart;
        String sessionDate;
          
        HttpSession session = request.getSession(); // retriving session 
        session.setAttribute("username", username); // to get the session details use session.getAttribute()
     
        RequestDispatcher rd;
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();
        
        UserBean user = (UserBean) dbcon.getItem("USERLOGIN", username);
        
        
        LoginDAO logindb = new LoginDAO(dbcon.getCon());
        
        
        if (!dbcon.isConnectionValid()){ //Connection to the database was not successful
            response.sendRedirect("sitedown.jsp");
        } 
        else {
                //if ("janakshi".equals(username) && "pass".equals(password)){
                if(logindb.isUserExist(username,password)){
                    //Using (www.tutorialspoint.com,2016)
                    //To get the date and time of the user's current session
                    Date dNow = new Date( );
                    SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss");
                    SimpleDateFormat ft1 = new SimpleDateFormat ("yyyy-MM-dd");
                    sessionStart = ft.format(dNow);
                    sessionDate= ft1.format(dNow);
                    accessLevel = logindb.readUserRole(username);
                    String id = logindb.readID(username, accessLevel);
                    System.out.println(id);
                    switch (accessLevel) {
                        case 1:
                            AdminBean admin = (AdminBean)dbcon.getItem("ADMINS",username);
                            String sec = admin.getAdSection();
                            System.out.println(sec);
                            session.setAttribute("adsec",sec);
                            session.setAttribute("admin",admin);
                            break;
                        case 2:
                            LecturerBean lecturer = (LecturerBean)dbcon.getItem("LECTURERS",username);
                            session.setAttribute("lecturer",lecturer);
                            break;
                        case 3:
                            StudentBean student = (StudentBean)dbcon.getItem("STUDENTS",username);
                            session.setAttribute("student",student);
                            break;
                        default:
                            break;
                    }
                    session.setAttribute("user",user);
                    session.setAttribute("role", accessLevel); // can access when current session is happening
                    session.setAttribute("start", sessionStart);
                    session.setAttribute("sessionDate", sessionDate);
                    session.setAttribute("id",id);
                    //request.setAttribute("role",accessLevel);
                    rd= request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                }
                else {
                    //response.sendRedirect("login.jsp?retry=1");
                    request.setAttribute("retry","1");
                    rd= request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                }
                
        }
        
        dbcon.closeConnection(); 
   
    }    
    
}
