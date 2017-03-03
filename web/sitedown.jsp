<%-- 
    Document   : sitedown
    Created on : May 2, 2016, 7:41:07 PM
    Author     : Janakshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    	<link href="/AdsLanka/CSS/main_theme.css" rel="stylesheet" type="text/css">
        
    	<style type="text/css">
            .error {
                background: #CCC;
                margin: 100px;
                padding: 20px;
                border: 2px solid;
                border-radius: 25px;
                box-shadow: 10px 10px 5px #888888;
                text-align: center;
            }

                .error h2 {
                    text-transform: uppercase;
                    color: #F00;
                }

        </style>
    </head>
    <body>        
    <div class="error">
          	<h2>Database Error</h2>
      		<img src="/VirtualClassroom/images/error.png" width="128" height="128" alt="error"><br/><br/>
            <p>Sorry, you cannot continue using this web site. There was an error in connecting to the Database. <br/>Please try again.</p>
            </div>
    </body>
</html>

