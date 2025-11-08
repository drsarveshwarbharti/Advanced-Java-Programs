//Example: Request and Response Handling in Java Servlets (GET Requests)
import java.io.*;
import javax.servlet.*; 
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    // 1. Retrieve data from request
    String user = request.getParameter("user");
    
    // 2. Set response type 
    response.setContentType("text/html");
    
    // 3. Prepare writer to send output 
    PrintWriter out = response.getWriter();
    
    // 4. Generate response 
    out.println("<html><body>"); 
    out.println("<h2>Hello, " + user + "!</h2>"); 
    out.println("</body></html>");
    } 
}
