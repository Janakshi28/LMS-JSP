<%-- 
    Document   : printCertificate
    Created on : May 24, 2016, 9:03:57 PM
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
            function changeFunc() {
                var cat_select = document.getElementById("cat_select");
                var selectedValue = cat_select.options[cat_select.selectedIndex].value;
                if (selectedValue !== 'all') {
                    var url = "printCertificate.jsp?category=" + selectedValue;
                }
                else{
                    var url = "printCertificate.jsp";
                }
                window.location.replace(url);
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Print Course Certificate</title>
        <meta charset="UTF-8"/>
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        <sql:query dataSource="${vle}" var="result1">
                            SELECT * FROM COURSES WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE STDID='${sessionScope.id}')                         
        </sql:query>
                            
                    <label>       
                        <span>Select Course</span>
                        <select id="cat_select" name="category" onchange="changeFunc();"> 
                            <option value="">Select the course</option>
                            <c:forEach var="row" items="${result1.rows}">                                                       
                            <option value="${row.courseID}"<c:if test="${row.courseID eq param.category}"><c:out value="selected"/></c:if>>${row.courseID}</option>                        
                            </c:forEach>      
                        </select>
                    </label> <br/><br/> 
                    
                    <c:choose>
                        <c:when test="${param.category != null}">
                        <center>
                            <a href="PrintCertificateServlet?cID=${param.category}"><img src="/VirtualClassroom/images/download.png" width="50" height="50" alt="download"/></a> <h3>Print Your Course Certificate</h3>
                        </center>
                        </c:when>                              
                    </c:choose> 
                            
    </body>
</html>
