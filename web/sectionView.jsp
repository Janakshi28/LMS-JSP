<%-- 
    Document   : sectionView
    Created on : May 15, 2016, 3:44:57 PM
    Author     : Janakshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
     
        <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        li {
            display: inline;
        }
        </style>
        <title>Section</title>
    </head>
    <body>
        
        <c:set var="selection" value="${selection}" scope="request" />
        <c:choose>
            <c:when test="${((sessionScope.role eq 2)||(sessionScope.role eq 3)) && (selection eq '6')}">
                <h2> My Account </h2>
                
            </c:when>
            <c:otherwise>
                <ul>
                    <c:choose>
                        <c:when test="${(sessionScope.role eq 1) && (selection eq '1')}">
                            <li><a href="frameView.jsp?choice=1" target="targetframe">All Courses</a></li>
                            <li><a href="frameView.jsp?choice=2" target="targetframe">Add a Course</a></li>    
                        </c:when>
                            
                        <c:when test="${(sessionScope.role eq 1) && (selection eq '2')}">
                            <li><a href="frameView.jsp?choice=3" target="targetframe">Performance</a></li>
                            <li><a href="frameView.jsp?choice=4" target="targetframe">Course Enrollments</a></li> 
                            <li><a href="frameView.jsp?choice=5" target="targetframe">Session Tracking</a></li>  
                        </c:when>   
                            
                        <c:when test="${(sessionScope.role eq 1) && (selection eq '3')}">
                            <li><a href="frameView.jsp?choice=6" target="targetframe">Assignment Submissions</a></li>
                            <li><a href="frameView.jsp?choice=7" target="targetframe">Quiz Submissions</a></li>    
                        </c:when>
                            
                        <c:when test="${(sessionScope.role eq 2) && (selection eq '1')}">
                            <li><a href="frameView.jsp?choice=1" target="targetframe">All Courses</a></li>
                            <li><a href="frameView.jsp?choice=2" target="targetframe">My Courses</a></li>    
                        </c:when> 
                            
                        <c:when test="${(sessionScope.role eq 2) && (selection eq '4')}">
                            <li><a href="frameView.jsp?choice=3" target="targetframe">View Content</a></li>
                            <li><a href="frameView.jsp?choice=4" target="targetframe">Add New Material</a></li>
                            <li><a href="frameView.jsp?choice=5" target="targetframe">Delete Material</a></li> 
                            <li><a href="frameView.jsp?choice=6" target="targetframe">Edit Material</a></li>
                            <li><a href="frameView.jsp?choice=7" target="targetframe">Forums</a></li>
                        </c:when>  
                            
                        <c:when test="${(sessionScope.role eq 2) && (selection eq '5')}">
                            <li><a href="frameView.jsp?choice=8" target="targetframe">View Assessments</a></li>
                            <li><a href="frameView.jsp?choice=9" target="targetframe">Upload Assessments</a></li>
                            <li><a href="frameView.jsp?choice=10" target="targetframe">Delete Assessments</a></li> 
                            <li><a href="frameView.jsp?choice=11" target="targetframe">Mark Submissions</a></li>
                        </c:when>  
                            
                        <c:when test="${(sessionScope.role eq 3) && (selection eq '1')}">
                            <li><a href="frameView.jsp?choice=1" target="targetframe">All Courses</a></li>
                            <li><a href="frameView.jsp?choice=2" target="targetframe">My Courses</a></li>
                            <li><a href="frameView.jsp?choice=3" target="targetframe">Course Content</a></li> 
                            <li><a href="frameView.jsp?choice=4" target="targetframe">Forums</a></li>
                        </c:when>
                            
                        <c:when test="${(sessionScope.role eq 3) && (selection eq '4')}">
                            <li><a href="frameView.jsp?choice=5" target="targetframe">View Assignments</a></li>
                            <li><a href="frameView.jsp?choice=6" target="targetframe">Download Assignments</a></li>
                            <li><a href="frameView.jsp?choice=7" target="targetframe">Take Quiz</a></li> 
                            <li><a href="frameView.jsp?choice=8" target="targetframe">Peer Reviews</a></li>                    
                        </c:when>   
                            
                        <c:when test="${(sessionScope.role eq 3) && (selection eq '5')}">
                            <li><a href="frameView.jsp?choice=9" target="targetframe">Lecturer Feedbacks</a></li>
                            <li><a href="frameView.jsp?choice=10" target="targetframe">Peer Ratings</a></li>
                            <li><a href="frameView.jsp?choice=11" target="targetframe">Academic Report</a></li> 
                            <li><a href="frameView.jsp?choice=12" target="targetframe">Course Certificate</a></li>                    
                        </c:when>    

                    </c:choose>
                </ul>
        
            </c:otherwise>  
            
        </c:choose>   

      
    </body>
</html>
