<%-- 
    Document   : login
    Created on : May 1, 2016, 10:36:51 AM
    Author     : Janakshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/title_logo.png"/>
        <link href="/VirtualClassroom/CSS/main_theme.css" rel="stylesheet" type="text/css"/>
        <link href="/VirtualClassroom/CSS/login.css" rel="stylesheet" type="text/css"/>
        <title>Login to Virtual Classroom</title>
    </head>
    <body>
        <c:import url="header.jsp"/>
        
        <div class="body">
            <h3>Welcome to VirtualClassroom.lk</h3><br/>
            <p id="welcome_txt">Already registered users can log in here. By logging in you can view your account and course details!</p>
            
            <div id="loginbox">
                <form action="LoginServlet" name="input" method="Post" id="loginform">
                    <img src="/VirtualClassroom/images/user.png" alt="username" width="70" height="70">
                    <input class="inputdetails" type="text" name="username" size="30" placeholder="Username"><br/>
                    <img src="/VirtualClassroom/images/lock.png" alt="password" width="70" height="70">
                    <input class="inputdetails" type="password" name="password" size="30" placeholder="Password"><br/>
                    <input type="submit" class="submit" value="Log in"/>
                    <a href="forgotpass.jsp"><p class="forgottext">Forgot your password? Let us help</p></a>
                </form>
                       
                         <c:choose>
                            <c:when test="${requestScope.retry ne null}">
                                <p id="error_message">Invalid login. Try again</p>
                            </c:when>
                        </c:choose>
           
            </div>
                
            <div id="infobox">
                <h4>Manage your courses, activities and assessments - all in one place!</h4>
                <img id="icon1" src="/VirtualClassroom/images/courses.png" alt="courses"/>
                <p id="text1">View course content and <br> Engage in course activities and assessments.</p>
                <img id="icon2" src="/VirtualClassroom/images/management.png" alt="management"/>
                <p id="text2">Course management for lecturers:<br> Add, Edit and Delete any course material.</p>  
                <img id="icon3" src="/VirtualClassroom/images/comments.png" alt="comments"/>
                <p id="text3">Give your comments on assessments.</p>
            </div>    
     
        </div>
        
        <%@include file="footer.jsp" %>
    </body>
</html>

