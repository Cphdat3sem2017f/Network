package http;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HTTP", urlPatterns =
{
    "/HTTP"
})
public class HTTP extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        
        
        try (PrintWriter out = response.getWriter())
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HTTP</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HTTP at " + request.getContextPath() + "</h1>");
            
            out.println("<p>HTTP</p>");
            
            out.println("<p>Header: " + request.getHeader("User-Agent") + "</p>");
            
            //response.setHeader("Cache-Control","max-age=300");
            //https://coderanch.com/t/352345/java/HTTP-basic-authentication-Web-Applications
            String encode = "test1:test1";
            byte[] encodeBytes = encode.getBytes(StandardCharsets.UTF_8);
            String encoded = Base64.getEncoder().encodeToString(encodeBytes);
            response.setHeader("WWW-Authenticate", "Basic realm=\"User Visible Realm\"");
            
            //out.println("<p>Parameter: " + request.getParameter("name") + "</p>");
            
            //request.getSession().setAttribute("name", "nonameSessionCookie");
            //out.println("<p>CookieSession: " + request.getSession().getAttribute("name") + "</p>");
                    
            //response.addCookie(new Cookie("name", "nonamePersistentCookie"));
            //out.println("<p>CookiePersistent: " + request.getCookies()[0].getValue() + "</p>");
            
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}