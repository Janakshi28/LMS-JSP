<%-- 
    Document   : myCourses
    Created on : May 16, 2016, 10:14:20 AM
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
        <link rel="icon" type="image/png" href="images/title_logo.png"/>
        <title>My Courses</title>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="CSS/my_courses.css" type="text/css"/>
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        <h2> My Courses</h2>
        <div id="tBody">
            <c:choose>
                <c:when test="${sessionScope.role eq 2}">
                    <sql:query dataSource="${vle}" var="result1">
                            SELECT * FROM COURSES WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE LECID='${sessionScope.id}')                         
                    </sql:query>                           
                    <table border="1" width="100%">
                    <tr>
                    <th>Course</th>
                    <th>Status</th>
                        <c:choose>
                        <c:when test="${sessionScope.lecturer.isCC eq true}">
                    <th>Learning Outcomes</th>
                        </c:when>
                        </c:choose>
                    </tr>   
                    <c:forEach var="row" items="${result1.rows}">                                                       
                    <tr>
                    <td><c:out value="${row.CRSTITLE}"/></td>
                    <td>
                         <c:choose>
                             <c:when test="${row.ISREADY eq true}"><img src="/VirtualClassroom/images/rdy.jpg" alt="rdy"/></c:when>
                             <c:otherwise>
                                 <img src="/VirtualClassroom/images/not-rdy.jpg" alt="rdy"/><br/>
                                 <a href="ReadyCrsServlet?crsID=${row.COURSEID}">Make it ready</a>
                             </c:otherwise>
                         </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${row.LOUTCOME eq null}">
                                <a href="SetLOServlet?crsID=${row.COURSEID}">Add Learning Outcomes</a>
                            </c:when>
                            <c:otherwise><pre><c:out value="${row.LOUTCOME}"/></pre></c:otherwise>
                        </c:choose>
                    </td>
                    </tr>                                 
                    </c:forEach>      
                    </table>
                            
                </c:when>                            

                <c:when test="${sessionScope.role eq 3}">

                    <sql:query dataSource="${vle}" var="result1">
                            SELECT * FROM COURSES WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE STDID='${sessionScope.id}')                         
                    </sql:query>                           
                    <table border="1" width="100%">
                    <tr>
                    <th>Course</th>
                    <th>Course Description</th>
                    <th>Learning Outcomes</th>  
                    </tr>   
                    <c:forEach var="row" items="${result1.rows}">                                                       
                    <tr>
                    <td><c:out value="${row.CRSTITLE}"/></td>
                    <td><pre><c:out value="${row.CRSDESC}"/></pre></td>
                    <td><pre><c:out value="${row.LOUTCOME}"/></pre></td>
                    </tr>                                 
                    </c:forEach>      
                    </table>

                </c:when>  

            </c:choose>
        
        </div> 
        
         <c:choose>
                <c:when test= "${requestScope.rStatus eq '1'}">
                    <c:out value="${requestScope.CRS}"/> was set as 'Ready' successfully!! 
                </c:when>    
                <c:when test= "${requestScope.rStatus eq '2'}">
                    Sorry!!, Something's wrong with the database.<br/>
                    Data was not added.
                </c:when>
        </c:choose>
                    
        <c:choose>
                <c:when test= "${requestScope.lStatus eq '1'}">
                    Learning outcomes were set for <c:out value="${requestScope.CRS}"/> successfully!! 
                </c:when>    
                <c:when test= "${requestScope.lStatus eq '2'}">
                    Sorry!!, Something's wrong with the database.<br/>
                    Data was not added.
                </c:when>
        </c:choose>            
        
    </body>
</html>
