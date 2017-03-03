<%-- 
    Document   : viewCrsReport
    Created on : Jun 1, 2016, 3:14:24 AM
    Author     : Janakshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript">
            
            
            function crsRepFunc() {
                var crs_select = document.getElementById("crs_select");
                var selectedValue1 = crs_select.options[crs_select.selectedIndex].value;
                
                if (selectedValue1 !== '') {
                    var url = "viewCrsReport.jsp?crs=" + selectedValue1;
                }
                else{
                    var url = "viewCrsReport.jsp";
                }
                window.location.replace(url);
                
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Performance</title>
        <link rel="stylesheet" href="CSS/my_courses.css" type="text/css"/>
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        <h1>View Course Performance Reports:</h1>
        
                        <sql:query dataSource="${vle}" var="result2">
                            SELECT * FROM COURSES WHERE DCATEGORY='${sessionScope.adsec}' 
                        </sql:query>
                        <label>
                            <span>Select category</span>
                            <select id="crs_select" name="crs" onchange="crsRepFunc();">
                                <option value="">Course</option>
                                <c:forEach var="row" items="${result2.rows}">                                                       
                                    <option value="${row.courseID}"<c:if test="${row.courseID eq param.crs}"><c:out value="selected"/></c:if>>${row.courseID}</option>                        
                                </c:forEach>
                               
                            </select>    
                        </label><br/>
                        
                         
                         <c:choose>
                                <c:when test="${param.crs != null}">
                                    <sql:query dataSource="${vle}" var="result3">
                                        SELECT l.GRADE,s.STDID, l.LECID FROM LECTURERFEEDBACKS l,SUBMISSIONS s WHERE l.SUBMISSIONNO=s.SUBMISSIONNO AND s.SUBMISSIONNO IN(Select SUBMISSIONNO FROM SUBMISSIONS WHERE ASSIGNMENTID IS NOT NULL AND ASSIGNMENTID IN (SELECT ASSIGNMENTID FROM ASSIGNMENTS WHERE COURSEID='${param.crs}')) 
                                    </sql:query>
                                      <h3>Course Report</h3>     
                                     <table border="1" width="100%">
                                        <tr>
                                        <th>Student ID</th>
                                        <th>Grade</th>
                                        <th>Instructor</th>
                                        </tr>   
                                        <c:forEach var="row" items="${result3.rows}">                                                       
                                        <tr>
                                        <td><c:out value="${row.STDID}"/></td>
                                        <td><c:out value="${row.GRADE}"/></td>
                                        <td><c:out value="${row.LECID}"/></td>
                                        </tr>                                 
                                        </c:forEach>      
                                     </table>       
                                </c:when>   
                                                           
                        </c:choose>     
                        
                                        
                       
                        
    </body>
</html>
