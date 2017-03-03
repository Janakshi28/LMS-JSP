<%-- 
    Document   : myAccount
    Created on : May 16, 2016, 10:21:27 AM
    Author     : Janakshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        <h1>My Account</h1>
        <c:choose>
            
                    <c:when test="${sessionScope.role eq '2'}">
                        <sql:query dataSource="${vle}" var="result1">
                            SELECT * FROM LECTURERS WHERE USERID='${sessionScope.username}'
                        </sql:query>
                            
                            <c:forEach var="row" items="${result1.rows}"> 
                            User name : <c:out value="${sessionScope.username}"/> <br/><br/>    
                            Lecturer ID : <c:out value="${row.LECID}"/> <br/><br/>
                            Name : <c:out value="${row.LECNAME}"/> <br/><br/>
                            Section : <c:out value="${row.LECSECTION}"/> <br/><br/>
                            EMail : <c:out value="${row.LECMAIL}"/> <br/><br/>
                            Last accessed time : <c:out value="${sessionScope.lastAccessedTime}"/> <br/><br/>
                            </c:forEach> 
                             
                    </c:when> 
                    <c:when test="${sessionScope.role eq '3'}">
                        <sql:query dataSource="${vle}" var="result2">
                            SELECT * FROM STUDENTS WHERE USERID='${sessionScope.username}'
                        </sql:query>
                            <c:forEach var="row" items="${result2.rows}"> 
                            User name : <c:out value="${sessionScope.username}"/> <br/><br/>    
                            Student ID : <c:out value="${row.STDID}"/> <br/><br/>
                            Name : <c:out value="${row.STDNAME}"/> <br/><br/>
                            Degree : <c:out value="${row.STDDEGREE}"/> <br/><br/>
                            EMail : <c:out value="${row.STDMAIL}"/> <br/><br/>
                            Batch Code : <c:out value="${row.STDBATCH}"/> <br/><br/>
                            
                            <jsp:useBean id="timeValues" class="java.util.Date"/>
                             <c:set target="${timeValues}" value="${pageContext.session.lastAccessedTime}" property="time"/>

                            Last accessed time:  <fmt:formatDate value="${timeValues}" type="both" dateStyle="full"/><br/><br/>
                            </c:forEach> 
                            
                    </c:when>         
                            
        </c:choose>                    
        
    </body>
</html>
