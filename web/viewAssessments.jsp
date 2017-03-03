<%-- 
    Document   : viewAssessments
    Created on : May 23, 2016, 9:26:12 PM
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
        <title>Assessments</title>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="CSS/my_courses.css" type="text/css"/>
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.role eq 2}">
                <sql:query dataSource="${vle}" var="result1">
                    SELECT * FROM ASSIGNMENTS WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE LECID='${sessionScope.id}')                                                  
                </sql:query>
                <sql:query dataSource="${vle}" var="result2">
                    SELECT * FROM QUIZES WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE LECID='${sessionScope.id}')                                                  
                </sql:query>   
                 
                    <h2>Assignments</h2>
                        <center>
                            <a href="addAssignment.jsp"><img src="/VirtualClassroom/images/add.png" width="40" height="40" alt="edit"/></a> <b>Add an Assignment</b><br/> <br/>
                        </center>
            
                    <table border="1" width="100%">
                    <tr>
                    <th>Course</th>    
                    <th>Assignment</th>
                    <th>Type</th>
                    <th>Hand Out Date</th>
                    <th>Hand In Date</th>
                    <th>View</th>
                    </tr> 
                    <c:forEach var="row" items="${result1.rows}">                                                       
                    <tr>
                    <td><c:out value="${row.COURSEID}"/></td>    
                    <td><c:out value="${row.ASSIGNMENTID}"/></td>
                    <td><c:out value="${row.ATYPE}"/></td>
                    <td><c:out value="${row.HANDOUT}"/></td>
                    <td><c:out value="${row.HANDIN}"/></td>
                    <td><a href="ViewFileServlet?path=Assessments&amp;file=${row.AFILENAME}"><img src="/VirtualClassroom/images/view.png" width="40" height="40" alt="edit"/></a></td>
                    </tr>
                    </c:forEach>
                    </table> <br/> <br/>
                    
                    <h2>Quizzes</h2>
                        <center>
                            <a href="setQuiz.jsp"><img src="/VirtualClassroom/images/add.png" width="40" height="40" alt="edit"/></a> <b>Set a Quiz</b><br/> <br/>
                        </center>
            
                    <table border="1" width="100%">
                    <tr>
                    <th>Course</th>     
                    <th>Quiz </th>
                    <th>Deadline</th>
                    <th>Time Period</th>
                    <th>View</th>
                    </tr> 
                    <c:forEach var="row" items="${result2.rows}">                                                       
                    <tr>
                    <td><c:out value="${row.COURSEID}"/></td>
                    <td><c:out value="${row.QTITLE}"/></td>
                    <td><c:out value="${row.QZDEADLINE}"/></td>
                    <td><c:out value="${row.QZTIMEPERIOD} minutes"/></td>
                    <td><a href="QuizServlet?qID=${row.QUIZID}&deadline=${row.QZDEADLINE}"><img src="/VirtualClassroom/images/view.png" width="40" height="40" alt="edit"/></a></td>
                    </tr>
                    </c:forEach>
                    </table> <br/> <br/>
                    
                    
                    
                    
            </c:when>
                    
            <c:when test="${sessionScope.role eq 3}">
                
                
                <sql:query dataSource="${vle}" var="result1">
                    SELECT * FROM ASSIGNMENTS WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE STDID='${sessionScope.id}')                                                  
                </sql:query>
                <sql:query dataSource="${vle}" var="result2">
                    SELECT * FROM QUIZES WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE STDID='${sessionScope.id}')                                                  
                </sql:query> 
                    
                <h2>Assignments</h2>
                       
                    <table border="1" width="100%">
                    <tr>
                    <th>Course</th>    
                    <th>Assignment</th>
                    <th>Type</th>
                    <th>Hand Out Date</th>
                    <th>Hand In Date</th>
                    <th>View</th>
                    <th>Download</th>
                    </tr> 
                    <c:forEach var="row" items="${result1.rows}">                                                       
                    <tr>
                    <td><c:out value="${row.COURSEID}"/></td>    
                    <td><c:out value="${row.ASSIGNMENTID}"/></td>
                    <td><c:out value="${row.ATYPE}"/></td>
                    <td><c:out value="${row.HANDOUT}"/></td>
                    <td><c:out value="${row.HANDIN}"/></td>
                    <td><a href="ViewFileServlet?path=Assessments&amp;file=${row.AFILENAME}"><img src="/VirtualClassroom/images/view.png" width="40" height="40" alt="edit"/></a></td>
                    <td><a href="DownloadServlet?path=Assessments&amp;fileName=${row.AFILENAME}"><img src="/VirtualClassroom/images/download.png" width="40" height="40" alt="download"/></a></td>
                    </tr>
                    </c:forEach>
                    </table> <br/> <br/>
                    
                <h2>Quizzes</h2>
            
                    <table border="1" width="100%">
                    <tr>
                    <th>Course</th>     
                    <th>Quiz </th>
                    <th>Deadline</th>
                    <th>Time Period</th>
                    <th>Access</th>
                    </tr> 
                    <c:forEach var="row" items="${result2.rows}">                                                       
                    <tr>
                    <td><c:out value="${row.COURSEID}"/></td>
                    <td><c:out value="${row.QTITLE}"/></td>
                    <td><c:out value="${row.QZDEADLINE}"/></td>
                    <td><c:out value="${row.QZTIMEPERIOD} minutes"/></td>
                    <td><a href="QuizServlet?qID=${row.QUIZID}&deadline=${row.QZDEADLINE}"><img src="/VirtualClassroom/images/access.png" width="40" height="40" alt="edit"/></a></td>
                    </tr>
                    </c:forEach>
                    </table> <br/> <br/>
                        
                     <c:choose>
                            <c:when test= "${requestScope.qStatus eq '0'}">
                            Deadline for <c:out value="${requestScope.qID}"/> has been passed!! <br/>
                            You cannot access this quiz..
                            </c:when>    
                                            
                    </c:choose>
                    
                     <c:choose>
                                <c:when test= "${requestScope.QsStatus eq '1'}">
                                    Your answers for the quiz <c:out value="${requestScope.qID}"/> were submitted successfully!! 
                                </c:when>    
                                <c:when test= "${requestScope.QsStatus eq '2'}">
                                    Sorry!!, Something's wrong with the database.<br/>
                                    Your answers were not submitted. Check and Submit new answers.
                                </c:when>
                    </c:choose>
                    
            </c:when>        
                    
        </c:choose>
        
    </body>
</html>
