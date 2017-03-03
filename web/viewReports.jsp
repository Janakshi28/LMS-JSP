<%-- 
    Document   : viewReports
    Created on : May 16, 2016, 10:10:08 AM
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
                if (selectedValue !== '') {
                    var url = "viewReports.jsp?category=" + selectedValue;
                }
                else{
                    var url = "viewReports.jsp";
                }
                window.location.replace(url);
            }
            
            function crsRepFunc() {
                var crs_select = document.getElementById("crs_select");
                var selectedValue1 = crs_select.options[crs_select.selectedIndex].value;
                
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Performance Reports</title>
        <link rel="stylesheet" href="CSS/my_courses.css" type="text/css"/>
        
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        <h1>View Performance Reports</h1>
        <label>
                    <span>Select category</span>
                    <select id="cat_select" name="category" onchange="changeFunc();">
                        <option value="">Report type</option>
                        <option value="sessions"<c:if test="${'sessions' eq param.category}"><c:out value="selected"/></c:if>>Session Reports</option>
                        <option value="crsReports"<c:if test="${'crsReports' eq param.category}"><c:out value="selected"/></c:if>>Course Performance</option>
                    </select>    
        </label>
                    
        <c:choose>
                    <c:when test="${param.category != null && param.category eq 'sessions'}">
                        <sql:query dataSource="${vle}" var="result1">
                            SELECT * FROM SESSIONS ORDER BY DATE DESC
                        </sql:query>
                        
                         <table border="1" width="100%">
                            <tr>
                            <th>User ID</th>
                            <th>Date</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                            </tr>   
                            <c:forEach var="row" items="${result1.rows}">                                                       
                            <tr>
                            <td><c:out value="${row.USERID}"/></td>
                            <td><c:out value="${row.DATE}"/></td>
                            <td><c:out value="${row.SSTART}"/></td>
                            <td><c:out value="${row.SEND}"/></td>
                            </tr>                                 
                            </c:forEach>      
                        </table>       
                            
                            
                    </c:when> 
                            
                    <c:when test="${param.category != null && param.category eq 'crsReports'}">
                        <a href="viewCrsReport.jsp"> View Course Reports</a>
                       
                    </c:when>         
        </c:choose>
    </body>
</html>
