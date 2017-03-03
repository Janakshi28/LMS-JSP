<%-- 
    Document   : setQuiz
    Created on : May 24, 2016, 10:47:48 AM
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
        <title>Set Quiz</title>
        <link rel="stylesheet" href="CSS/manage_material.css" type="text/css"/>
        <meta charset="UTF-8"/>
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        <sql:query dataSource="${vle}" var="result1">
                            SELECT * FROM COURSES WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE LECID='${sessionScope.id}')                         
        </sql:query>
        <h2>New Quiz</h2>
        <form action="SetQuizServlet" method="POST" id="formMat">
            <div align="left">
            Quiz ID          : <br/><input type="text" name="qID" value="" /><br/><br/>
            Quiz Title         : <br/><input type="text" name="qTitle" value="" /><br/><br/>
            Deadline         : <br/><input type="text" name="deadline" value="" /><br/><br/>
            Time Period (minutes)         : <br/><input type="text" name="period" value="" /><br/><br/>
            Course ID     : <br/><select name="cID">
                                        <option>Select Course</option>
                                        <c:forEach var="row" items="${result1.rows}">   
                                        <option value="${row.COURSEID}">${row.COURSEID}</option>
                                        </c:forEach>
                                     </select><br/><br/>
            </div>
            <div class="button">
                   <center><input type="submit" value="Add Quiz" name="setQ" /></center><br/><br/>
            </div>
        </form>
        <c:choose>
                    <c:when test= "${requestScope.qzStatus eq '1'}">
                        <font color= "green">
                         Quiz added to the DB successfully!! <br/>
                        </font>
                    </c:when>    
                    <c:when test= "${requestScope.qzStatus eq '0'}">
                        <font color= "red">
                        *Quiz wasn't added to the DB!! Something's wrong with data.<br/><br/>
                        </font>
                    </c:when>
        </c:choose> 
        
        <sql:query dataSource="${vle}" var="result2">
                    SELECT * FROM QUIZES WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE LECID='${sessionScope.id}')                                                  
        </sql:query>   
        <h2>Make Quiz ready</h2>
        <form action="SetQuizServlet" method="GET" >
            <div align="left">
            Quiz ID     : <br/><select name="qID">
                                        <option>Select Quiz</option>
                                        <c:forEach var="row" items="${result2.rows}">   
                                        <option value="${row.QUIZID}">${row.QUIZID}</option>
                                        </c:forEach>
                                     </select><br/><br/>
                                     
            </div>
            <center><input type="submit" value="Make ready" name="rdyQ" /></center><br/>
        </form>
        <c:choose>
                    <c:when test= "${requestScope.rStatus eq '1'}">
                        <font color= "green">
                         Quiz was set as 'Ready' successfully!! <br/>
                        </font>
                    </c:when>    
                    <c:when test= "${requestScope.rStatus eq '0'}">
                        <font color= "red">
                        *Quiz wasn't set as 'Ready'!! Something's wrong with data.<br/><br/>
                        </font>
                    </c:when>
        </c:choose> 
        
        <h2>Add Questions to Quizzes</h2>
        <form action="SetQuestionServlet" method="POST">
            <div align="left">
            Question No     : <br/><input type="text" name="qNo" value="" /><br/><br/>
            Question      : <br/><textarea name="qDesc" rows="4" cols="20"></textarea><br/><br/>
            Quiz ID     : <br/><select name="qID">
                                        <option>Select Quiz</option>
                                        <c:forEach var="row" items="${result2.rows}">   
                                        <option value="${row.QUIZID}">${row.QUIZID}</option>
                                        </c:forEach>
                                     </select><br/><br/>
            Choice 1     : <br/><input type="text" name="ch1" value="" /><br/><br/> 
            Choice 2     : <br/><input type="text" name="ch2" value="" /><br/><br/> 
            Choice 3     : <br/><input type="text" name="ch3" value="" /><br/><br/> 
            Choice 4     : <br/><input type="text" name="ch4" value="" /><br/><br/> 
            Correct Choice     : <br/><input type="text" name="correct" value="" /><br/><br/> 
            </div> 
            <center><input type="submit" value="Add Question" name="addQ" /></center><br/>
        </form>
            <c:choose>
                    <c:when test= "${requestScope.qStatus eq '1'}">
                        <font color= "green">
                         Question added to the question DB successfully!! <br/>
                         </font>
                    </c:when>    
                    <c:when test= "${requestScope.qStatus eq '0'}">
                        <font color= "red">
                        *Question wasn't added to the DB!! Something's wrong with data.
                        </font>
                    </c:when>
            </c:choose> 
        
    </body>
</html>
