<%-- 
    Document   : submitAssignment
    Created on : May 24, 2016, 12:47:46 PM
    Author     : Janakshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assignment Submissions</title>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="CSS/my_courses.css" type="text/css"/>
        <link rel="stylesheet" href="CSS/manage_material.css" type="text/css"/>
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        <sql:query dataSource="${vle}" var="result1">
                    SELECT * FROM ASSIGNMENTS WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE STDID='${sessionScope.id}')                                                  
        </sql:query>
        <h1>Assignment submissions</h1>
        <table border="1" width="100%">
                    <tr>
                    <th>Course</th>    
                    <th>Assignment</th>
                    <th>Type</th>
                    <th>Hand In Date</th>
                    <th>Submit</th>
                    </tr> 
                    <c:forEach var="row" items="${result1.rows}">                                                       
                    <tr>
                    <td><c:out value="${row.COURSEID}"/></td>    
                    <td><c:out value="${row.ASSIGNMENTID}"/></td>
                    <td><c:out value="${row.ATYPE}"/></td>
                    <td><c:out value="${row.HANDIN}"/></td>
                    <td><a href="SubmitAssignmentServlet?aID=${row.ASSIGNMENTID}&amp;deadline=${row.HANDIN}"><img src="/VirtualClassroom/images/submit.png" width="40" height="40" alt="edit"/></a></td>                    
                    </tr>
                    </c:forEach>
        </table>
        
                    <c:choose>
                        <c:when test= "${requestScope.eligible eq '1'}">
                           <font color='green'>
                            You are eligible to submit your assignment!! 
                           </font>
                                    
                           <form action="SubmitAssignmentServlet" method="POST" enctype="multipart/form-data" id="formMat">
                                <div align="left">
                                    <input type="hidden" name="aID" value="${requestScope.aID}" />   
                                File to upload      :<br/><input type="file" name="file"/> <br/><br/>
                                </div>
                                <div class="button">
                                   <center><input type="submit" value="Submit Assignment" name="submitA" /></center><br/><br/>
                                </div>
                           </form>         
                                    
                        </c:when>    
                        <c:when test= "${requestScope.eligible eq '0'}">
                           <font color='red'>
                            Sorry!!, Deadline for this assignment has been passed.<br/>
                            You cannot submit your assignment now.<br/>
                           </font>
                        </c:when>
                    </c:choose>
                           
                     <c:choose>
                                <c:when test= "${requestScope.AsStatus eq '1'}">
                                    <font color='green'>
                                    Your assignment was submitted successfully!! 
                                    </font>
                                </c:when>    
                                <c:when test= "${requestScope.AsStatus eq '2'}">
                                    <font color='red'>
                                    Sorry!!, Something's wrong with the database.<br/>
                                    Your assignment was not submitted. Check and Submit assignment again..
                                    </font>
                                </c:when>
                    </c:choose>       
        
    </body>
</html>
