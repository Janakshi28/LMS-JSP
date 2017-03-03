<%-- 
    Document   : addNewCourse
    Created on : May 16, 2016, 10:09:00 AM
    Author     : Janakshi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Course</title>
        <link rel="stylesheet" href="CSS/manage_course.css" type="text/css"/>
    </head>
    <body>
        <h2>Add a New Course</h2><br/><br/>
        <div>
            <form action="CourseServlet" method="POST" id="formCrs">
                <div align="left">
                Course ID          : <br/><input type="text" name="crsID" value="" size="48" /><br/><br/>
                Course Title       : <br/><input type="text" name="crsTitle" value="" size="48" /><br/><br/>
                Degree Category    : <br/><select name="dCat">
                                        <option>Select Degree Category</option>
                                        <option value="School of Computing">School of Computing</option>
                                        <option value="Business School">Business School</option>
                                        <option value="Law School">Law School</option>
                                     </select><br/><br/>
                Degree             : <br/><select name="degree">
                                        <option>Select Degree</option>

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
                                     </select><br/><br/>
                Course Description :<br/><textarea name="crsDesc" rows="8" cols="50"></textarea><br/><br/>
                </div>
                <div class="button">
                   <center><input type="submit" value="Add Course" name="addCrs" /></center><br/><br/>
                </div>
            </form>
        
            </div>
            <c:choose>
                <c:when test= "${requestScope.addStatus eq '1'}">
                    New course was added successfully!! 
                </c:when>    
                <c:when test= "${requestScope.addStatus eq '2'}">
                    Sorry!!, You don't have permission to add courses to this degree category.
                </c:when>
            </c:choose>
        
        
    </body>
</html>
