<%-- 
    Document   : manageContent
    Created on : May 16, 2016, 10:15:39 AM
    Author     : Janakshi
--%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <script type="text/javascript">
            function changeFunc() {
                var cat_select = document.getElementById("cat_select");
                var selectedValue = cat_select.options[cat_select.selectedIndex].value;
                if (selectedValue !== 'all') {
                    var url = "manageContent.jsp?category=" + selectedValue;
                }
                else{
                    var url = "manageContent.jsp";
                }
                window.location.replace(url);
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="icon" type="image/png" href="images/title_logo.png"/>
        <title>Course Content</title>
        <link rel="stylesheet" href="CSS/my_courses.css" type="text/css"/>
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        <h1>Course Content</h1>
        <div>
            
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
                            <center>
                                <a href="AddMaterialServlet?cID=${param.category}"><img src="/VirtualClassroom/images/add.png" width="40" height="40" alt="edit"/></a> 
                                <b>Add a Course Material</b> <br/><br/>
                                <a href="ForumServlet?cID=${param.category}"><img src="/VirtualClassroom/images/forum.png" width="40" height="40" alt="edit"/></a> 
                                <b>View Forums</b> <br/><br/>
                            </center>
                            <sql:query dataSource="${vle}" var="result">
                                SELECT * FROM COURSEMATERIALS WHERE COURSEID='${param.category}' ORDER BY MATERIALID DESC
                            </sql:query>
                        </c:when>                              
                    </c:choose>  
         
                                
                    <table border="1" width="100%">
                    <tr>
                    <th>Material Type</th>
                    <th>Material Title</th>
                    <th>Edit</th>
                    <th>Delete</th>
                    </tr>   
                    <c:forEach var="row" items="${result.rows}">                                                       
                    <tr>
                    <td><c:out value="${row.MTYPE}"/></td>
                    <td><a href="ViewFileServlet?mID=${row.MATERIALID}&amp;path=CourseMaterials&amp;file=${row.MFILENAME}">${row.MTITLE}</a></td>
                    <td><a href="EditMaterialServlet?mID=${row.MATERIALID}"><img src="/VirtualClassroom/images/edit-notes.png" width="50" height="50" alt="edit"/></a></td>
                    <td><a href="DeleteMaterialServlet?mID=${row.MATERIALID}"><img src="/VirtualClassroom/images/delete.png" width="50" height="50" alt="delete"/></a></td>        
                    </tr>                                 
                    </c:forEach>      
                    </table>   
                         
                                <c:choose>
                                            <c:when test= "${requestScope.mDStatus eq '1'}">
                                                <c:out value="${requestScope.mID}"/> was deleted successfully!! 
                                            </c:when>    
                                            <c:when test= "${requestScope.mDStatus eq '2'}">
                                                Sorry!!, Something's wrong with the database.<br/>
                                                Material was not deleted.
                                            </c:when>
                                </c:choose>
                                
                                
                </c:when>     
                <c:when test="${sessionScope.role eq 3}">
                    
                    <sql:query dataSource="${vle}" var="result1">
                            SELECT * FROM COURSES WHERE COURSEID IN(SELECT COURSEID FROM ENROLLMENTS WHERE STDID='${sessionScope.id}')                         
                    </sql:query> 
                    <label>  
                        <br/><br/>
                        <span>Select category</span>
                        <select id="cat_select" name="category" onchange="changeFunc();"> 
                            <option value="">Select the course</option>
                            <c:forEach var="row" items="${result1.rows}">                                                       
                            <option value="${row.courseID}"<c:if test="${row.courseID eq param.category}"><c:out value="selected"/></c:if>>${row.courseID}</option>                        
                            </c:forEach>      
                        </select>
                    </label> 
                            
                    <c:choose>
                        <c:when test="${param.category != null}">
                            
                            <sql:query dataSource="${vle}" var="result">
                                SELECT * FROM COURSEMATERIALS WHERE COURSEID='${param.category}' ORDER BY MATERIALID DESC
                            </sql:query>
                        <center>    
                            <a href="ForumServlet?cID=${param.category}"><img src="/VirtualClassroom/images/forum.png" width="40" height="40" alt="edit"/></a> 
                            <b>View Forums</b> <br/><br/>
                        </center>    
                        </c:when>                              
                    </c:choose>  
                    <br/><br/>                                        
                    <table border="1" width="100%">
                    <tr>
                    <th>Material Type</th>
                    <th>Material Title</th>
                    <th>Download</th>
                    </tr>   
                    <c:forEach var="row" items="${result.rows}"> 
                        <c:choose>
                            <c:when test="${row.VISIBILITY eq true}">                        
                            <tr>
                            <td><c:out value="${row.MTYPE}"/></td>
                            <td><a href="ViewFileServlet?mID=${row.MATERIALID}&amp;path=CourseMaterials&amp;file=${row.MFILENAME}">${row.MTITLE}</a></td> 
                            <td><a href="DownloadServlet?path=CourseMaterials&amp;fileName=${row.MFILENAME}"><img src="/VirtualClassroom/images/download.png" width="40" height="40" alt="download"/></a></td>
                            </tr> 
                            </c:when> 
                        </c:choose>
                    </c:forEach>      
                    </table>          
                </c:when>     
                               
                            
            </c:choose>                
            
            
     
            
            
            
            
            
        </div>
        
    </body>
</html>
