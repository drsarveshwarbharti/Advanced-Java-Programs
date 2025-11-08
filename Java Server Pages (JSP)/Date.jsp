<html>
<body>
<%-- JSP comments --%>
<%@page import="java.util.Date"%>
<%!
    Date date; 
%>
<% 
    date = new Date();
%>
<b>System date and time: </b> <%= date %>
</body>
</html>
