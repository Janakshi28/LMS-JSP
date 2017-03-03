<%-- 
    Document   : forum
    Created on : Jun 1, 2016, 5:16:21 AM
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
        <title>Forums</title>
         <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>
    </head>
    <body>
        
        
          <c:choose>
                    <c:when test= "${requestScope.forumStatus eq '1'}">
                        <h1>Forum: <c:out value="${requestScope.cID}"/> </h1>
                        <sql:query dataSource="${vle}" var="result">
                                SELECT * FROM FORUMS WHERE FORUMID='${requestScope.fID}'
                        </sql:query>
                           <c:forEach var="row" items="${result.rows}"> 
                               <br/><br/>
                               Forum ID    : <c:out value="${row.FORUMID}"/><br/><br/>
                               Forum Title : <c:out value="${row.FTITLE}"/><br/><br/>
                               Description : <c:out value="${row.FDESC}"/><br/><br/>
                               
                                          
                           </c:forEach>    
                                
                        
                           <c:forEach var="msg" items="${requestScope.msgList}">                       
                                  <%-- This reads a string message object from the msgList--%>
                                  <br/><pre><c:out value="${msg}"/></pre>         
                           </c:forEach>
                    <center>
                        <form action="ForumServlet" method="POST">
                            <textarea name="msg" rows="6" cols="30"></textarea>
                            <input type="hidden" name="cID" value="${requestScope.cID}" />
                            <input type="hidden" name="fID" value="${requestScope.fID}" />
                            <input type="submit" value="Reply" />
                        </form>
                        
                    </center>
                    
                        <c:choose>
                             <c:when test= "${requestScope.msgStatus eq '1'}">
                             Your message was sent successfully!!..
                            </c:when>
                            <c:when test= "${requestScope.msgStatus eq '2'}">
                             Sorry!!, Message was not sent.<br/>
                             Something's wrong with the server. Please try again later..
                            </c:when>

                        </c:choose>
                                  
                                  
                   </c:when>  
                             
                   <c:when test= "${requestScope.forumStatus eq '2'}">
                         Sorry!!, Currently there's no forum for this course.<br/>
                         Forum Discussion will be opening soon.
                   </c:when>
                         
          </c:choose>
        
                         

        
    </body>
</html>
