<%-- 
    Document   : feedbacks
    Created on : May 16, 2016, 10:21:05 AM
    Author     : Janakshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
    <head>
        <script type="text/javascript">
            function changeFunc() {
                var cat_select = document.getElementById("cat_select");
                var selectedValue = cat_select.options[cat_select.selectedIndex].value;
                if (selectedValue !== 'all') {
                    var url = "feedbacks.jsp?category=" + selectedValue;
                }
                else{
                    var url = "feedbacks.jsp";
                }
                window.location.replace(url);
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedbacks</title>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="CSS/my_courses.css" type="text/css"/>
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        <h2>Assessment Submissions</h2>
        <c:choose>
                <c:when test="${sessionScope.role eq 2}">
                    <sql:query dataSource="${vle}" var="result1">
                            SELECT * FROM COURSES WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE LECID='${sessionScope.id}')                         
                    </sql:query> 
                    
                    <label>       
                        <span>Select category</span>
                        <select id="cat_select" name="category" onchange="changeFunc();"> 
                            <option value="">Select the course</option>
                            <c:forEach var="row" items="${result1.rows}">                                                       
                            <option value="${row.courseID}"<c:if test="${row.courseID eq param.category}"><c:out value="selected"/></c:if>>${row.courseID}</option>                        
                            </c:forEach>      
                        </select>
                    </label> <br/><br/>
                    
                    <c:choose>
                        <c:when test="${param.category != null}">
                            <sql:query dataSource="${vle}" var="result2">
                                SELECT * FROM SUBMISSIONS WHERE SUBMISSIONNO NOT IN(SELECT SUBMISSIONNO FROM LECTURERFEEDBACKS WHERE LECID='${sessionScope.id}')AND ASSIGNMENTID IS NOT NULL AND ASSIGNMENTID IN(SELECT ASSIGNMENTID FROM ASSIGNMENTS WHERE COURSEID='${param.category}')
                            </sql:query>
                            <sql:query dataSource="${vle}" var="result3">
                                SELECT * FROM SUBMISSIONS WHERE SUBMISSIONNO NOT IN(SELECT SUBMISSIONNO FROM LECTURERFEEDBACKS WHERE LECID='${sessionScope.id}')AND QUIZID IS NOT NULL AND QUIZID IN(SELECT QUIZID FROM QUIZES WHERE COURSEID='${param.category}')
                            </sql:query>                      
                        </c:when>                              
                    </c:choose> 
                                
                   <h3>Assignment Submissions: </h3>
                   <table border="1" width="100%">
                    <tr>
                    <th>Student</th>
                    <th>Assignment</th>
                    <th>Submission</th>
                    <th>Feedback</th>
                    </tr>   
                    <c:forEach var="row" items="${result2.rows}">                                                       
                    <tr>
                    <td><c:out value="${row.STDID}"/></td>
                    <td><c:out value="${row.ASSIGNMENTID}"/></td>
                    <td><a href="ViewFileServlet?file=${row.SFILENAME}&amp;path=AssignmentSubmissions" target="_blank"><img src="/VirtualClassroom/images/view.png" width="50" height="50" alt="view"/></a></td>
                    <td><a href="FeedbackServlet?id=${row.ASSIGNMENTID}&amp;stdID=${row.STDID}&amp;subNo=${row.SUBMISSIONNO}"><img src="/VirtualClassroom/images/feedback.png" width="70" height="45" alt="feedback"/></a></td>        
                    </tr>                                 
                    </c:forEach>      
                    </table> <br/> <br/>
                   
                   <h3>Quiz Submissions: </h3>
                   <table border="1" width="100%">
                    <tr>
                    <th>Student</th>
                    <th>Quiz</th>
                    <th>Submission</th>
                    <th>Feedback</th>
                    </tr>   
                    <c:forEach var="row" items="${result3.rows}">                                                       
                    <tr>
                    <td><c:out value="${row.STDID}"/></td>
                    <td><c:out value="${row.QUIZID}"/></td>
                    <td><a href="ViewFileServlet?file=${row.SFILENAME}&amp;path=QuizSubmissions" target="_blank"><img src="/VirtualClassroom/images/view.png" width="50" height="50" alt="view"/></a></td>
                    <td><a href="FeedbackServlet?id=${row.QUIZID}&amp;stdID=${row.STDID}&amp;subNo=${row.SUBMISSIONNO}"><img src="/VirtualClassroom/images/feedback.png" width="70" height="45" alt="feedback"/></a></td>        
                    </tr>                                 
                    </c:forEach>      
                    </table> 
                    
                            <c:choose>
                                <c:when test= "${requestScope.fStatus eq '1'}">
                                    <br/><br/>
                                    
                                    <form action="FeedbackServlet" method="POST">
                                       Comment : <textarea name="comment" rows="4" cols="35"></textarea><br/><br/>
                                       Grade   :<input type="text" name="grade" value="" /><br/><br/>
                                        <input type="hidden" name="subNo" value="${requestScope.subNo}" />
                                        <input type="submit" value="Submit" />
                                    </form>
                                </c:when>    
                            </c:choose>
                   
                    <c:choose>            
                            <c:when test= "${requestScope.SFstatus eq '1'}">
                                 <font color= "green">
                                  Feedback saved successfully!! <br/>
                                 </font>
                             </c:when>    
                             <c:when test= "${requestScope.SFstatus eq '0'}">
                                 <font color= "red">
                                 *Feedback wasn't saved!!! Something's wrong with the DB.<br/><br/>
                                 </font>
                             </c:when>
                  </c:choose>          
                </c:when>
                                 
                <c:when test="${sessionScope.role eq 3}">
                    
                    <sql:query dataSource="${vle}" var="result1">
                            SELECT * FROM COURSES WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE STDID='${sessionScope.id}')                         
                    </sql:query> 
                    
                    <label>       
                        <span>Select category</span>
                        <select id="cat_select" name="category" onchange="changeFunc();"> 
                            <option value="">Select the course</option>
                            <c:forEach var="row" items="${result1.rows}">                                                       
                            <option value="${row.courseID}"<c:if test="${row.courseID eq param.category}"><c:out value="selected"/></c:if>>${row.courseID}</option>                        
                            </c:forEach>      
                        </select>
                    </label> <br/><br/>
                    
                        <c:choose>
                        <c:when test="${param.category != null}">
                            <sql:query dataSource="${vle}" var="result2">
                                SELECT * FROM SUBMISSIONS WHERE STDID<>'${sessionScope.id}' AND SUBMISSIONNO NOT IN(SELECT SUBMISSIONNO FROM PEERREVIEWS WHERE STDID='${sessionScope.id}') AND ASSIGNMENTID IS NOT NULL AND ASSIGNMENTID IN(SELECT ASSIGNMENTID FROM ASSIGNMENTS WHERE COURSEID='${param.category}')
                            </sql:query>
                            <sql:query dataSource="${vle}" var="result3">
                                SELECT * FROM SUBMISSIONS WHERE STDID<>'${sessionScope.id}' AND SUBMISSIONNO NOT IN(SELECT SUBMISSIONNO FROM PEERREVIEWS WHERE STDID='${sessionScope.id}') AND QUIZID IS NOT NULL AND QUIZID IN(SELECT QUIZID FROM QUIZES WHERE COURSEID='${param.category}')
                            </sql:query> 
                             
                               
                        </c:when>                              
                        </c:choose> 
                                
                               
                <center>
                    <a href="ViewFeedbackServlet"><img src="/VirtualClassroom/images/comments.png" width="50" height="50" alt="comments"/></a>
                    <h2>View My Feedbacks</h2>   
                </center>    
                    
                    
                         <c:choose>   
                            
                            <c:when test= "${requestScope.EFstatus eq '1'}">
                                   <sql:query dataSource="${vle}" var="result4">
                                        Select l.COMMENT,l.GRADE,s.ASSIGNMENTID,s.QUIZID FROM LECTURERFEEDBACKS l, SUBMISSIONS s WHERE l.SUBMISSIONNO=s.SUBMISSIONNO AND s.STDID='${sessionScope.id}'         
                                    </sql:query>
                                    <sql:query dataSource="${vle}" var="result5">
                                             Select r.RDESC,r.RATE,s.ASSIGNMENTID,s.QUIZID FROM PEERREVIEWS r, SUBMISSIONS s WHERE r.SUBMISSIONNO=s.SUBMISSIONNO AND s.STDID='${sessionScope.id}'   
                                    </sql:query> 
                                             
                                 <font color= "green">
                                  You can view feedbacks for your submissions!! <br/>
                                 </font>
                                 
                                 <br/><h3>My Feedbacks</h3> <br/>
                                 
                                 <h2>Lecturer Feedbacks: </h2>
                                    <table border="1" width="100%">
                                     <tr>
                                     <th>Grade</th>
                                     <th>Assignment</th>
                                     <th>Quiz</th>
                                     <th>Comment</th>
                                     </tr>   
                                     <c:forEach var="row" items="${result4.rows}">                                                       
                                     <tr>
                                     <td><c:out value="${row.GRADE}"/></td>
                                     <td><c:out value="${row.ASSIGNMENTID}"/></td>
                                     <td><c:out value="${row.QUIZID}"/></td>
                                     <td><c:out value="${row.COMMENT}"/></td>        
                                     </tr>                                 
                                     </c:forEach>      
                                     </table> <br/> <br/>
                                 
                                  <h2>Peer Reviews: </h2>
                                    <table border="1" width="100%">
                                     <tr>
                                     <th>Rate</th>
                                     <th>Assignment</th>
                                     <th>Quiz</th>
                                     <th>Review</th>
                                     </tr>   
                                     <c:forEach var="row" items="${result5.rows}">                                                       
                                     <tr>
                                     <td><c:out value="${row.RATE}"/></td>
                                     <td><c:out value="${row.ASSIGNMENTID}"/></td>
                                     <td><c:out value="${row.QUIZID}"/></td>
                                     <td><c:out value="${row.RDESC}"/></td>        
                                     </tr>                                 
                                     </c:forEach>      
                                     </table> <br/> <br/>   
                                     
                                 
                                 
                             </c:when>    
                             <c:when test= "${requestScope.EFstatus eq '0'}">
                                 <font color= "red">
                                 *Sorry!! You don't have permission to view feedbacks.<br/>
                                    Try giving more Peer reviews...<br/><br/>
                                 </font>
                             </c:when>
                        </c:choose>
                    
                    
                    <br/><br/><h2>Give Peer Reviews</h2> <br/>
                                 
                   <h3>Assignment Submissions: </h3>
                   <table border="1" width="100%">
                    <tr>
                    <th>Student</th>
                    <th>Assignment</th>
                    <th>Submission</th>
                    <th>Review</th>
                    </tr>   
                    <c:forEach var="row" items="${result2.rows}">                                                       
                    <tr>
                    <td><c:out value="${row.STDID}"/></td>
                    <td><c:out value="${row.ASSIGNMENTID}"/></td>
                    <td><a href="ViewFileServlet?file=${row.SFILENAME}&amp;path=AssignmentSubmissions" target="_blank"><img src="/VirtualClassroom/images/view.png" width="50" height="50" alt="view"/></a></td>
                    <td><a href="PeerReviewServlet?id=${row.ASSIGNMENTID}&amp;stdID=${row.STDID}&amp;subNo=${row.SUBMISSIONNO}"><img src="/VirtualClassroom/images/review.png" width="50" height="50" alt="review"/></a></td>        
                    </tr>                                 
                    </c:forEach>      
                    </table> <br/> <br/>
                   
                   <h3>Quiz Submissions: </h3>
                   <table border="1" width="100%">
                    <tr>
                    <th>Student</th>
                    <th>Quiz</th>
                    <th>Submission</th>
                    <th>Review</th>
                    </tr>   
                    <c:forEach var="row" items="${result3.rows}">                                                       
                    <tr>
                    <td><c:out value="${row.STDID}"/></td>
                    <td><c:out value="${row.QUIZID}"/></td>
                    <td><a href="ViewFileServlet?file=${row.SFILENAME}&amp;path=QuizSubmissions" target="_blank"><img src="/VirtualClassroom/images/view.png" width="50" height="50" alt="view"/></a></td>
                    <td><a href="PeerReviewServlet?id=${row.QUIZID}&amp;stdID=${row.STDID}&amp;subNo=${row.SUBMISSIONNO}"><img src="/VirtualClassroom/images/review.png" width="50" height="50" alt="review"/></a></td>        
                    </tr>                                 
                    </c:forEach>      
                    </table> 
                    
                            <c:choose>
                                <c:when test= "${requestScope.rStatus eq '1'}">
                                    <br/> <br/> <br/>Give peer review for <c:out value="${requestScope.stdID}"/>
                                    <br/><br/>
                                    <form action="PeerReviewServlet" method="POST">
                                      Review  : <textarea name="rdesc" rows="4" cols="35"></textarea><br/><br/>
                                      Rate    : <input type="text" name="rate" value="" /><br/><br/>
                                        <input type="hidden" name="subNo" value="${requestScope.subNo}" />
                                        <input type="submit" value="Submit" />
                                    </form>
                                </c:when>    
                            </c:choose>
                   
                    <c:choose>            
                            <c:when test= "${requestScope.SRstatus eq '1'}">
                                 <font color= "green">
                                  Review saved successfully!! <br/>
                                 </font>
                             </c:when>    
                             <c:when test= "${requestScope.SRstatus eq '0'}">
                                 <font color= "red">
                                 *Review wasn't saved!!! Something's wrong with the DB.<br/><br/>
                                 </font>
                             </c:when>
                  </c:choose>    
                    
                </c:when>
        </c:choose>        
        
    </body>
</html>
