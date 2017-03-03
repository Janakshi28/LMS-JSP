<%-- 
    Document   : header
    Created on : May 1, 2016, 10:37:08 AM
    Author     : Janakshi
--%>

<% session = request.getSession();%>
<jsp:useBean id="user" class="lk.vle.beans.UserBean" scope="session">
    <jsp:setProperty name="user" property="userID" value="Guest"/>
</jsp:useBean>

        <div class="header">
            <a href="/VirtualClassroom/index.jsp">
                <img src="/VirtualClassroom/images/site_logo_small.jpg" alt="logo" id="logo"/>
            </a>
            <div id="welcome">
                
                <%
                    if (!user.getUserID().equals("Guest")){
                        out.print("<span id=\"topic\">Welcome</span>");
                        out.print("<a href=\"/VirtualClassroom/myHome.jsp\"><span id=\"name\">" + user.getUserID() + "</span></a>");
                        out.print("<a href=\"http://webspace.apiit.lk/\"><img id =\"webspace\" src=\"/VirtualClassroom/images/star_logo.png\" alt=\"Star Academy Webspace\"/></a>");
                        out.print("<a href=\"http://webspace1.apiit.lk/gims/library/\"><img id =\"opac\" src=\"/VirtualClassroom/images/library.jpg\" alt=\"Library OPAC\"/></a>");
                    }
                    
                    
                %>
            </div>
            <ul id="navibar">
				
		<li>
                    <a class="navilink" href="#news">About</a>
		</li>
		<li>
                    <% 
                        if (!user.getUserID().equals("Guest")) {
                            out.print("<a class=\"navilink\" href=\"/VirtualClassroom/myHome.jsp\">My Home</a>");
                            //out.print("<a class=\"navilink\" href=\"/VirtualClassroom/allCourses.jsp\">Courses</a>");
                        } 
                    %>
		</li>
		<li>
                    <%
                        if (user.getUserID().equals("Guest")){
                            out.print("<a class=\"navilink\" href=\"/VirtualClassroom/login.jsp\">Log in</a>");
                        } else {
                            out.print("<a class=\"navilink\" href=\"LogoutServlet?logout=1\">Log out</a>");
                        }
                        
                    %>	
		</li>
            </ul>
	</div>
<!-- header -->
