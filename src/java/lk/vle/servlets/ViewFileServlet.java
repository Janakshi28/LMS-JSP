/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Janakshi
 */
@WebServlet(name = "ViewFileServlet", urlPatterns = {"/ViewFileServlet"})
public class ViewFileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
         String jspPath = "C:\\Users\\Janakshi\\Documents\\NetBeansProjects\\VirtualClassroom\\web\\";
         RequestDispatcher rd;   
            String txtFilePath = jspPath+req.getParameter("path")+"\\"+req.getParameter("file");
            BufferedReader reader = new BufferedReader(new FileReader(txtFilePath));
            StringBuilder sb = new StringBuilder();
            String line;

            while((line = reader.readLine())!= null){
                sb.append(line).append("\n");
                //out.println(sb.toString());
            }
            String view= sb.toString();
            req.setAttribute("view",view);
                rd= req.getRequestDispatcher("viewFile.jsp");
                rd.forward(req, resp);
        
    }

    

}
