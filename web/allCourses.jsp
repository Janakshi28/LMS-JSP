<%-- 
    Document   : allCourses
    Created on : May 2, 2016, 12:53:42 PM
    Author     : Janakshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
         
        
        <title>All Courses</title>
        <meta charset="UTF-8"/>
        <link rel="icon" type="image/png" href="images/title_logo.png"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="stylesheet" href="CSS/main_theme.css" type="text/css"/>
        <link rel="stylesheet" href="CSS/all_courses.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>

        <!-- Use bean tags for the user are included in the header.jsp -->
    </head>
    <body>
         <c:import url="header.jsp"/>
         
            <div class="body">
                
                <div id="main">
                    
                    <h4>All Courses</h4>
                <label>
                    <span>Select category</span>
                    <select id="cat_select" name="category">
                        <option value="">Select the degree</option>
                        <option value="all">All</option>
                        
                        <optgroup label="School of Computing">
                        <option value="Software Engineering">Software Engineering</option>
                        <option value="Computer Networks and Security">Computer Networks and Security</option>
                        <option value="Information Systems">Information Systems</option>
                        </optgroup>
                        
                        <optgroup label="Business School">
                        <option value="International Business Management">International Business Management</option>
                        <option value="Accounting and Finance">Accounting and Finance</option>
                        <option value="Events Management">Events Management</option>
                        <option value="Tourism Management">Tourism Management</option>
                        </optgroup>
                        
                        <optgroup label="Law School">
                        <option value="LLB Law">LLB Law</option>
                        <option value="LLM International Business Law">LLM International Business Law</option>
                        </optgroup>
                    
                    </select>
                </label>
                          
                            
                <%-- Design the page according to the values returned --%>
                <div id="crs_box">
                    <c:forEach var="row" items="${result.rows}">
                                <a href="CourseServlet?crsID=${row.courseID}&crs=${row.crsTitle}"><c:out value="${row.crsTitle}"/></a> <br/>
                    </c:forEach>        
                </div>  
                
                <div>
                    
                    <c:choose>
                        <c:when test="${requestScope.cStatus eq '1'}">
                            <b>Course:</b> <c:out value="${requestScope.crs}"/> <br/><br/>
                            <b>Course Description:</b> <pre><c:out value="${requestScope.crsDesc}"/></pre><br/><br/>   
                                <div class="warning_box">
                                    <p class="warning_message">
                                                You are not logged in yet. You need to log in to your account to enroll for this course.
                                                If you are already registered with the VLE you can <a href="login.jsp">log in</a> here.
                                    </p> 
                                    
                                </div>    
                        </c:when>
                        <c:when test="${requestScope.cStatus eq '2'}">
                            <b>Course:</b> <c:out value="${requestScope.crs}"/> <br/><br/>
                            <b>Course Description:</b> <pre><c:out value="${requestScope.crsDesc}"/></pre><br/><br/>    
                                <div class="success_box">
                                   You are already enrolled for this course. <br/>
                                   <a href="myHome.jsp">Go to your home page to view its course content.</a>
                                    
                                </div>
                            
                        </c:when>
                        <c:when test="${requestScope.cStatus eq '3'}">
                            <b>Course:</b> <c:out value="${requestScope.crs}"/> <br/><br/>
                            <b>Course Description:</b> <pre><c:out value="${requestScope.crsDesc}"/></pre><br/><br/>   
                            <div id="enroll_box">
                                <b>Enroll for this course: </b>
                                <form action="EnrollServlet" method="POST" name="input" id="enroll_form">
                                    <img src="/VirtualClassroom/images/enrollment_icon.png" alt="enKey">
                                    <input class="inputdetails" type="text" name="enKey" size="30" placeholder="Enroll Key"><br/>
                                    <input type="hidden" value="${requestScope.crsID}" name="crsID" /><br/>
                                    <input type="submit" class="submit" value="Enroll"/>
                                </form>
                                
                            </div>
                        </c:when>
                        <c:when test="${requestScope.cStatus eq '4'}">
                            <b>Course:</b> <c:out value="${requestScope.crs}"/> <br/><br/>
                            <b>Course Description:</b> <pre><c:out value="${requestScope.crsDesc}"/></pre><br/><br/>    
                        </c:when>    
                        
                    </c:choose>
                </div>
                
                 <div>
                    
                    <c:choose>
                        <c:when test="${requestScope.eStatus eq '1'}">  
                                <div class="success_box">
                                    <p class="warning_message">
                                                You have enrolled for this course successfully!! <br/>
                                                <a href="myHome.jsp">Go to your home page to view its course content.</a>
                                    </p> 
                                    
                                </div>    
                        </c:when>
                        <c:when test="${requestScope.eStatus eq '2'}">   
                                <div class="warning_box">
                                   Incorrect enroll key!!!
                                   Please check your enroll key and try again.
                                    
                                </div>
                            
                        </c:when>
                        <c:when test="${requestScope.eStatus eq '3'}">   
                                <div class="warning_box">
                                   Sorry!!
                                   This Course is still not available for you..            
                                </div>
                            
                        </c:when>
                    </c:choose>
                 </div>
                                     
                
                            
                </div>
            </div>  
        
       <%@include file="footer.jsp" %>    
       
    </body>
</html>
