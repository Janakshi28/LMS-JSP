<%-- 
    Document   : accessQuiz
    Created on : May 24, 2016, 3:41:21 AM
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
        <title>Quiz</title>
        <meta charset="UTF-8"/>
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        
        <h2>QUIZ - <c:out value="${requestScope.qID}"/> </h2>
        
        <c:choose>
                    <c:when test= "${sessionScope.role eq '2'}">
                        <sql:query dataSource="${vle}" var="result1">
                            SELECT * FROM QUESTIONS WHERE QUIZID='${requestScope.qID}'                                                
                        </sql:query>
                            
                        <c:set var="counter" scope="session" value="${0}"/>
                            <c:forEach var="row" items="${result1.rows}">
                                <c:set var="counter" scope="session" value="${counter+1}"/>
                                <c:out value="Q${counter}"/>. <c:out value="${row.QDESC}"/> <br/>
                                A) <c:out value="${row.CHOICE1}"/> <br/>
                                B) <c:out value="${row.CHOICE2}"/> <br/>
                                C) <c:out value="${row.CHOICE3}"/> <br/>
                                D) <c:out value="${row.CHOICE4}"/> <br/> 
                                <font color="red">  Correct Choice: <c:out value="${row.CORRECTCHOICE}"/> </font> <br/><br/>
                            </c:forEach>
                            
                    </c:when> 
                                
                    <c:when test= "${sessionScope.role eq '3'}">
                        <sql:query dataSource="${vle}" var="result">
                            SELECT QZTIMEPERIOD FROM QUIZES WHERE QUIZID='${requestScope.qID}'                                                
                        </sql:query>
                        <sql:query dataSource="${vle}" var="result1">
                            SELECT * FROM QUESTIONS WHERE QUIZID='${requestScope.qID}'                                                
                        </sql:query>
                            
                            
                            
                            <c:forEach var="row" items="${result.rows}">
                            <h4>Time Period: <c:out value="${row.QZTIMEPERIOD}"/> minutes </h4> 
                            </c:forEach>
                            
                            <form action="QuizServlet" method="POST">
                             
                            <c:set var="counter" scope="session" value="${0}"/>
                            <c:forEach var="row" items="${result1.rows}">
                                <c:set var="counter" scope="session" value="${counter+1}"/>
                                <c:out value="Q${counter}"/>. <c:out value="${row.QDESC}"/> <br/>
                                A) <c:out value="${row.CHOICE1}"/> <br/>
                                B) <c:out value="${row.CHOICE2}"/> <br/>
                                C) <c:out value="${row.CHOICE3}"/> <br/>
                                D) <c:out value="${row.CHOICE4}"/> <br/>
                                <center>
                                <input type="radio" name="CH${row.QNO}" value="1" /> A 
                                <input type="radio" name="CH${row.QNO}" value="2" /> B  
                                <input type="radio" name="CH${row.QNO}" value="3" /> C 
                                <input type="radio" name="CH${row.QNO}" value="4" /> D <br/> <br/> <br/> 
                                </center>
                            </c:forEach>  
                                <input type="hidden" name="qID" value="${requestScope.qID}"/>
                                <center>
                                <input type="submit" value="Submit" /></center> <br/><br/>
                            </form> 
                                                               
                    </c:when>
        </c:choose> 
    </body>
</html>
