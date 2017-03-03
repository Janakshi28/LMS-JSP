<%-- 
    Document   : setLOutcomes
    Created on : May 17, 2016, 5:56:31 PM
    Author     : Janakshi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Learning Outcomes</title>
        <link rel="stylesheet" href="CSS/manage_course.css" type="text/css"/>
    </head>
    <body>
        <h2>Set Learning Outcomes </h2><br/><br/>
        <div>
            <form action="SetLOServlet" method="POST" id="formCrs">
                <div align="left">
                    Learning Outcomes: <br/><textarea name="loutcome" rows="4" cols="50"></textarea><br/><br/>
                    <input type="hidden" name="crsID" value="${requestScope.crsID}" />
                </div>
                <div class="button">
                   <center><input type="submit" value="Submit" name="setLOutcome" /></center><br/><br/>
                </div>    
            </form>   
        </div>
                
            
        
    </body>
</html>
