<%-- 
    Document   : addMaterial
    Created on : May 23, 2016, 12:39:03 AM
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
        <title>New Course Material</title>
        <link rel="stylesheet" href="CSS/manage_material.css" type="text/css"/>
    </head>
    <body>
        <h1>Add a New Course Material</h1>
        <div>
            <form action="AddMaterialServlet" method="POST" enctype="multipart/form-data" id="formMat">
                <div align="left">
                Material ID          : <br/><input type="text" name="mID" value="" size="48" /><br/><br/>
                Material Title       : <br/><input type="text" name="mTitle" value="" size="48" /><br/><br/>
                Material Type    : <br/><select name="mCat">
                                        <option>Select Material Type</option>
                                        <option value="Slide">Slide</option>
                                        <option value="Tutorial">Tutorial</option>
                                        <option value="Resource">Resource</option>
                                     </select><br/><br/>
                Visibility      : <br/><label><input type="radio" name="visible" value="true">ON</label> 
                                  <label><input type="radio" name="visible" value="false">OFF</label><br/><br/>
                                  <input type="hidden" name="cID" value="${requestScope.cID}"><br/><br/>
                File to upload      :<br/><input type="file" name="file"/> <br/><br/>
                </div>
                <div class="button">
                   <center><input type="submit" value="Add Material" name="addM" /></center><br/><br/>
                </div>
            </form>
        
        </div>
                <c:choose>
                    <c:when test= "${requestScope.mAStatus eq '1'}">
                         New material added successfully!! <br/>
                         *Uploaded Filename: <br/>
                         Folder: <c:out value="${requestScope.fPath}"/> <br/>
                         File: <c:out value="${requestScope.fName}"/> 
                    </c:when>    
                    <c:when test= "${requestScope.mAStatus eq '0'}">
                        <font color= "red">
                        *No file uploaded!!
                        </font>
                    </c:when>
                </c:choose> 
    </body>
</html>
