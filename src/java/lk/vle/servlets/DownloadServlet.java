/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.servlets;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Janakshi
 */
@WebServlet(name = "DownloadServlet", urlPatterns = {"/DownloadServlet"})
public class DownloadServlet extends HttpServlet {
     static final long serialVersionUID = 1L;
        private static final int BUFSIZE = 4096;
        private String filePath;

        public void init() {
            // the file data.xls is under web application folder
            filePath = "C:\\Users\\Janakshi\\Documents\\NetBeansProjects\\VirtualClassroom\\web\\";
        }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        String fileName = req.getParameter("fileName");
        RequestDispatcher rd;
        req.setAttribute("path",path);
        req.setAttribute("fileName",fileName);
        
        rd= req.getRequestDispatcher("downloadFile.jsp");
            rd.forward(req, resp);
    }
        
        
        
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              System.out.println(filePath);
        //retrieving the parameter by its name
        String fileName = req.getParameter("fileName"); //this will return `data.xls`
        String folder= req.getParameter("path");
        System.out.println(fileName);
        //using the File(parent, child) constructor for File class
        String fullPath="xxxx";
        fullPath = ""+filePath+folder+"\\";
        File file = new File(fullPath, fileName);
        //verify if the file exists
        int length   = 0;
        ServletOutputStream outStream = resp.getOutputStream();
        ServletContext context  = getServletConfig().getServletContext();
        String mimetype = context.getMimeType(fullPath);

        // sets response content type
        if (mimetype == null) {
            mimetype = "application/octet-stream";
        }
        resp.setContentType(mimetype);
        resp.setContentLength((int)file.length());
        
        // sets HTTP header
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(file));

        // reads the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1))
        {
            outStream.write(byteBuffer,0,length);
        }

        in.close();
        outStream.close();
    }

}
