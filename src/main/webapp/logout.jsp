<%--
  Created by IntelliJ IDEA.
  User: icondor
  Date: 27/03/2022
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%

    HttpSession s = request.getSession();
    s.invalidate();
    response.sendRedirect("login.html");

%>
</body>
</html>
