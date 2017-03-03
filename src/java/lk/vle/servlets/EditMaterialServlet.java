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
import lk.vle.beans.CourseMaterialBean;
import lk.vle.dao.DbConnector;

/**
 *
 * @author Janakshi
 */
@WebServlet(name = "EditMaterialServlet", urlPatterns = {"/EditMaterialServlet"})
public class EditMaterialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            RequestDispatcher rd;
            String mID= req.getParameter("mID");
            //System.out.println(mID);
            req.setAttribute("mID",mID);            
            rd= req.getRequestDispatcher("editMaterial.jsp");
            rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        DbConnector dbcon = new DbConnector();
        dbcon.openConnection();        
        CourseMaterialBean crsMdb = new CourseMaterialBean(dbcon.getCon());
        
            String mID = req.getParameter("mID");
            boolean visibility = Boolean.parseBoolean(req.getParameter("visible"));
            crsMdb.setVisibility(visibility);
            
            if (!dbcon.isConnectionValid()){ //Connection to the database was not successful
                resp.sendRedirect("sitedown.jsp");
            } 
            else{
                if(crsMdb.modifyVisibility(mID)==true){
                    req.setAttribute("v",visibility);
                    req.setAttribute("vStatus", "1"); 
                }
                else{
                    req.setAttribute("vStatus", "2");  
                }
                req.setAttribute("mID",mID);
                rd= req.getRequestDispatcher("editMaterial.jsp");
                rd.forward(req, resp);
            }
      
        dbcon.closeConnection();
        
        
    }
    
    
    


}
