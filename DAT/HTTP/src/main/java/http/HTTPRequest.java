package http;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns =
{
    "/HTTPRequest"
})
public class HTTPRequest extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter())
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HTTPRequest</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HTTPRequest at " + request.getContextPath() + "</h1>");
            
            out.println("<table>");
            out.println("<tr><th>Header</th><th>Value</th></tr>");
            out.println("<tr><td>Host</td><td>" + request.getHeader("Host") + "</td></tr>");
            out.println("<tr><td>Connection</td><td>" + request.getHeader("Connection") + "</td></tr>");
            out.println("<tr><td>CacheControl</td><td>" + request.getHeader("Cache-Control") + "</td></tr>");
            out.println("<tr><td>Accept</td><td>" + request.getHeader("Accept") + "</td></tr>");
            out.println("<tr><td>UserAgent</td><td>" + request.getHeader("User-Agent") + "</td></tr>");
            out.println("<tr><td>AcceptEncoding</td><td>" + request.getHeader("Accept-Encoding") + "</td></tr>");
            out.println("<tr><td>AcceptLanguage</td><td>" + request.getHeader("Accept-Language") + "</td></tr>");
            out.println("</table>");
            
            out.println("<p>PARAMETERMAP: " + request.getParameterMap() + "</p>");
            out.println("<p>PARAMETERNAMES: " + request.getParameterNames() + "</p>");
            out.println("<p>PARAMETERVALUES: " + request.getParameter("hidden") + "</p>");
            
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