<%-- 
    Document   : submitAssignment
    Created on : May 16, 2016, 10:22:29 AM
    Author     : Janakshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Course Material</title>
    </head>
    <body>
        <h2>Edit Course Material: <c:out value="${requestScope.mID}"/> </h2> <br/> <br/>
        <center>
        <h4>Change the material visibility: </h4> <br/>
    
        <form action="EditMaterialServlet" method="POST">
            
            <label><input type="radio" name="visible" value="true">ON</label> 
            <label><input type="radio" name="visible" value="false">OFF</label><br/><br/>
            <input type="hidden" name="mID" value="${requestScope.mID}">
            <input type="submit" value="Set"/>
              
        </form> <br/> <br/>
        
        <h4>Upload modified material file: </h4> <br />
        
        <form action="ModifyMaterialContentServlet" method="POST" enctype="multipart/form-data" id="uploadbox">
            File to upload:  <input type="file" name="file"/> <br/><br/>
            <input type="hidden" name="MID" value="${requestScope.mID}">
            <input type="submit" value="Upload File" />
            <br/><br/>
            
        </form>
    </center>    
    
        <c:choose>
                <c:when test= "${requestScope.vStatus eq '1'}">
                    Visibility of <c:out value="${requestScope.mID}"/> was set as <c:out value="${requestScope.v}"/> successfully!! 
                </c:when>    
                <c:when test= "${requestScope.vStatus eq '2'}">
                    Sorry!!, Something's wrong with the database.<br/>
                    Data was not set.
                </c:when>
        </c:choose>
                    
        <c:choose>
                <c:when test= "${requestScope.mUStatus eq '1'}">
                     Modified material uploaded successfully!! <br/>
                     *Uploaded Filename: <br/>
                     Folder: <c:out value="${requestScope.fPath}"/> <br/>
                     File: <c:out value="${requestScope.fName}"/> 
                </c:when>    
                <c:when test= "${requestScope.mUStatus eq '0'}">
                    <font color= "red">
                    *No file uploaded!!
                    </font>
                </c:when>
        </c:choose>            
        
    </body>
</html>
