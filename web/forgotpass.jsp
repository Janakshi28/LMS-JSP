<%-- 
    Document   : forgotpass
    Created on : May 2, 2016, 2:15:24 PM
    Author     : Janakshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/title_logo.png"/>
        <link href="/VirtualClassroom/CSS/main_theme.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="CSS/forgotpass.css" type="text/css"/>
        <link rel="stylesheet" href="CSS/form.css" type="text/css"/>

        <title>Password VirtualClassroom</title>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="body">
            You can reset your password here. You will receive an email for resetting your password.
            <br>
            
            <form class="wrapper" name="enter" action="login.jsp?fgot=1">
                <p>Enter your registered email here : </p>
                <input type="email" placeholder="Your registered email"/>
                <input type="submit" class="submit" value="Reset"/>
            </form>


        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
