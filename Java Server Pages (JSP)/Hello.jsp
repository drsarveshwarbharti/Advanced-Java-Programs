<%@ page language="java" contentType="text/html; charset=UTF-8" %> <html>
<head>
<title>JSP Example</title>
</head>
<body>
<h2>Welcome to JSP!</h2>
<%
// Scriptlet: Java code
String name = request.getParameter("name"); if (name == null) {
        name = "Guest";
    }
%>    
<p>Hello, <%= name %>! Nice to meet you.</p>
<form action="hello.jsp" method="get">
Enter your name: <input type="text" name="name"> <input type="submit" value="Greet">
</form>
</body>
</html>
