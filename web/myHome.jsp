<%-- 
    Document   : myAccount
    Created on : May 1, 2016, 4:28:03 PM
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
        <title>My Home</title>
        <meta charset="UTF-8"/>
        <link rel="icon" type="image/png" href="images/title_logo.png"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="stylesheet" href="CSS/main_theme.css" type="text/css"/>
        <link rel="stylesheet" href="CSS/my_home.css" type="text/css"/>
        
        
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        <c:import url="header.jsp"/>
        <div class="body">
            
            <div id="main">
                    
                
                    <div>
                        <iframe src="sectionView.jsp" name="targetframe" id="myFrame" width="820" height="900" >
                        </iframe>
                    </div>

                    <div id="selectionbar2">

                        <h2>Navigation Bar</h2>
                        
                        <ul id="main_navibar">
                            
                        <c:choose>
                        <c:when test="${sessionScope.role eq 1}">
                                <li><a href="allCourses.jsp">All Courses</a></li>
                                <li><a href="addNewCourse.jsp" target="targetframe">Add New Course</a></li> 
                                <li><a href="viewReports.jsp" target="targetframe">Reports</a></li> 
                                
                        </c:when>
                        <c:when test="${sessionScope.role eq 2}">
                                <li><a href="allCourses.jsp">All Courses</a></li> 
                                <li><a href="myCourses.jsp" target="targetframe">My Courses</a></li> 
                                <li><a href="manageContent.jsp" target="targetframe">Course Content</a></li>
                                <li><a href="viewAssessments.jsp" target="targetframe">Assessments</a></li>
                                <li><a href="feedbacks.jsp" target="targetframe">Mark Submissions</a></li>
                                <li><a href="myAccount.jsp" target="targetframe">My account</a></li>
                        </c:when>                           
                         <c:when test="${sessionScope.role eq 3}">
                                <li><a href="allCourses.jsp">All Courses</a></li>
                                <li><a href="myCourses.jsp" target="targetframe">My Courses</a></li>
                                <li><a href="manageContent.jsp" target="targetframe">Course Content</a></li>
                                <li><a href="viewAssessments.jsp" target="targetframe">Assessments</a></li>
                                <li><a href="submitAssignment.jsp" target="targetframe">Submissions</a></li>
                                <li><a href="feedbacks.jsp" target="targetframe">Feedbacks</a></li>
                                <li><a href="printCertificate.jsp" target="targetframe">Print Certificate</a></li>
                                <li><a href="myAccount.jsp" target="targetframe">My account</a></li>
                        </c:when>  
                          
                        </c:choose> 
                          
                        </ul>

                    </div>
                 
                                
                
            </div> 
        
        </div> 
        <%@include file="footer.jsp" %>  
    </body>
</html>
