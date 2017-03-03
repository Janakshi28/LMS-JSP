<%-- 
    Document   : index
    Created on : May 1, 2016, 10:35:42 AM
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
                    var url = "index.jsp?category=" + selectedValue;
                } else {
                    var url = "index.jsp";
                }
                window.location.replace(url);
            }
            
            el = document.getElementById("simple-ss");
            el.onclick = links;

            function links() {
              left = parseInt(getComputedStyle(el).getPropertyValue("background-position").split(" ", 1));

              /* DEFINE POSITIONS FOR CLICK EVENTS */
              if (left >= -400) {

                // First until about half way scrolled over
                alert("first");
                //window.open("http://www.google.com");

              } else if (left >= -1200) {

                // Second when half way scrolled either side
                alert("second");
                //window.open("http://www.google.com");

              } else if (left >= -1600) {

                // Third when over half way into banner
                alert("third");
                //window.open("http://www.google.com");

              }else if (left >= -1800) {

                // Third when over half way into banner
                alert("fourth");
                //window.open("http://www.google.com");

              }else if (left >= -1900) {

                // Third when over half way into banner
                alert("fifth");
                //window.open("http://www.google.com");

              }else if (left >= -1950) {

                // Third when over half way into banner
                alert("sixth");
                //window.open("http://www.google.com");

              }           

            }
        </script>
        
        <title>Virtual Classroom</title>
        
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="icon" type="image/png" href="images/title_logo.png"/>
        <link rel="stylesheet" href="CSS/main_theme.css" type="text/css"/>
        <link rel="stylesheet" href="CSS/index.css" type="text/css"/>
        
        <sql:setDataSource var="vle" driver="org.apache.derby.jdbc.ClientDriver"
                           url="jdbc:derby://localhost:1527/dbvle"
                           user="janakshi" password="janakshi"/>

        <!-- Use bean tags for the user are included in the header.jsp -->
    </head>
        <body>
          
          <c:import url="header.jsp"/>
          <div class="body">
              
                <div id="main">                    
                    <img src="/VirtualClassroom/images/cover_logo.jpg" alt="logo" id="logo2"/>

                    <div id="selectionbar">

                        <h2>Navigation Bar</h2>

                        <ul id="main_navibar">
                          <li><a href="allCourses.jsp">All Courses</a></li>
                          <li><a href="http://webspace.apiit.lk/TT-Version17.1/">Class Timetable</a></li>

                        </ul>


                    </div>
                    
                    <!-- All you need is this one div!-->
                    <div class="simple-ss" id="simple-ss"></div>

                    
                    <div id="infobox">
                        <h4>Manage your courses, activities and assessments - all in one place!</h4>
                        <img id="icon1" src="/VirtualClassroom/images/courses.png" alt="courses"/>
                        <p id="text1">View course content and <br> Engage in course activities and assessments.</p>
                        <img id="icon2" src="/VirtualClassroom/images/management.png" alt="management"/>
                        <p id="text2">Course management for lecturers:<br> Add, Edit and Delete any course material.</p>  
                        <img id="icon3" src="/VirtualClassroom/images/comments.png" alt="comments"/>
                        <p id="text3">Give your comments on assessments.</p>
                    </div>  
                    
               </div> 
              
           </div> 
          
          
          
           <%@include file="footer.jsp" %>    
        </body>
        
</html>