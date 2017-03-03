<%-- 
    Document   : downloadFile
    Created on : May 23, 2016, 3:52:46 PM
    Author     : Janakshi
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Download Servlet Test</title>
</head>
<body>
    Click on the link to download: <br/> <br/>
    <form action="DownloadServlet" method="POST">
        <input type="hidden" name="path" value="${requestScope.path}">
        <input type="hidden" name="fileName" value="${requestScope.fileName}">
        <input type="submit" value="Download">
    </form>
    
</body>
</html>