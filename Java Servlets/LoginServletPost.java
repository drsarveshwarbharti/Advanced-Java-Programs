//Example: Request and Response Handling in Java Servlets (POST Requests)
/*
Typical Workflow
1. Client submits an HTML form.(Sample HTML form is given below)
2. Servlet retrieves parameters using request.getParameter().
3. Servlet performs business logic (e.g., validation, DB access).
4. Servlet generates a response using response.getWriter(), or forwards to a JSP.

<html>
  <body>
    <form action="LoginServletPost" method="post">
    Username: <input type="text" name="username"><br> Password: <input type="password" name="password"><br> <input type="submit" value="Login">
    </form>
  </body>
</html

*/

import java.io.*;
import javax.servlet.*; 
import javax.servlet.http.*;

public class LoginServletPost extends HttpServlet {

  protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    // 1. Retrieve data from request (POST Request)
    String username = request.getParameter("username"); 
    String password = request.getParameter("password");
  

    // 2. Set response type 
    response.setContentType("text/html");
    
    // 3. Prepare writer to send output 
    PrintWriter out = response.getWriter();
    
    // 4. Generate response 
    if ("admin".equals(username) && "1234".equals(password)) { 
      out.println("<h3>Login Successful</h3>");
    } else {
    out.println("<h3>Invalid credentials</h3>"); 
    }
    
    } 
}
