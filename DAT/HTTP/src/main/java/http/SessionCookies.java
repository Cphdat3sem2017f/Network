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
    "/SessionCookies"
})
public class SessionCookies extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name = request.getParameter("name");
        
        if (name != null)
        {
            request.getSession().setAttribute("name", name);
        }
        else
        {
            name = (String) request.getSession().getAttribute("name");
        }

        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter())
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SessionCookies</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>SessionCookies</h1>");

            if (name != null)
            {
                name = (String) request.getSession().getAttribute("name");
                out.println("<p> Welcome " + name + " !</p>");
            }
            else
            {
                out.println("<h2>Please enter your name, and submit</h2>");
                out.println("<form action=\"SessionCookies\">");
                out.println("<input type='input' name='name'>");
                out.println("<input type='submit'></form>");
            }

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