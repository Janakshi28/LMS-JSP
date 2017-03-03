<%-- 
    Document   : addAssignment
    Created on : May 24, 2016, 7:59:51 AM
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
        <title>New Assignment</title>
        <link rel="stylesheet" href="CSS/manage_material.css" type="text/css"/>
        <meta charset="UTF-8"/>
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        <h1>Add a New Assignment</h1>
        <div>
            <sql:query dataSource="${vle}" var="result1">
                            SELECT * FROM COURSES WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE LECID='${sessionScope.id}')                         
            </sql:query>
            <form action="AddAssignmentServlet" method="POST" enctype="multipart/form-data" id="formMat">
                <div align="left">
                Assignment ID          : <br/><input type="text" name="aID" value="" size="48" /><br/><br/>                
                Assignment Type    : <br/><select name="aCat">
                                        <option>Select Assignment Type</option>
                                        <option value="Individual">Individual</option>
                                        <option value="Group">Group</option>
                                     
                                     </select><br/><br/>
                Hand Out Date       : <br/><input type="text" name="handout" value="" size="48" /><br/><br/> 
                Hand In Date       : <br/><input type="text" name="handin" value="" size="48" /><br/><br/>
                Course ID     : <br/><select name="cID">
                                        <option>Select Assignment Type</option>
                                        <c:forEach var="row" items="${result1.rows}">   
                                        <option value="${row.COURSEID}">${row.COURSEID}</option>
                                        </c:forEach>
                                     </select><br/><br/>
                File to upload      :<br/><input type="file" name="file"/> <br/><br/>
                </div>
                <div class="button">
                   <center><input type="submit" value="Add Assignment" name="addA" /></center><br/><br/>
                </div>
            </form>
        
        </div>
                <c:choose>
                    <c:when test= "${requestScope.aStatus eq '1'}">
                         New assignment added successfully!! <br/>
                         *Uploaded Filename: <br/>
                         Folder: <c:out value="${requestScope.fPath}"/> <br/>
                         File: <c:out value="${requestScope.fName}"/> 
                    </c:when>    
                    <c:when test= "${requestScope.aStatus eq '0'}">
                        <font color= "red">
                        *No file uploaded!!
                        </font>
                    </c:when>
                </c:choose> 
    </body>
</html>
