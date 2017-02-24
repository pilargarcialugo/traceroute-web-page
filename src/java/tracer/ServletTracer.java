package tracer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServeltTraza", urlPatterns = {"/ServeltTraza"})
public class ServletTracer extends HttpServlet {

    private Tracer tracer;
    PrintWriter out;
    ArrayList<String> aux = new ArrayList<String>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        int a = 1;
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head></head>");
        out.println("<title></title>");
        out.println("<body>");
        out.println("<t1>" + aux + "</t1>");
        out.println("<t1>" + a + "</t1>");
        out.println("</body>");
        out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        int a = 1;
        out.println("<html>");
        out.println("<body>");
        out.println("<t1>" + aux + "</t1>");
        out.println("<t1>" + a + "</t1>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        aux = tracer.getArray();
    }
}