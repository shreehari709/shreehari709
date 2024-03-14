// Delete.java
package in.sp.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteForm")
public class Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String myemail = req.getParameter("email1");
        String mypass = req.getParameter("pass1");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yt_demo", "root", "root");

            PreparedStatement ps = con.prepareStatement("delete from register where email=? and password=?");

            ps.setString(1, myemail);
            ps.setString(2, mypass);

            int count = ps.executeUpdate();
            if (count > 0) {
                resp.setContentType("text/html");
                out.print("<h3 style='color:green'> User deleted Successfully</h3>");

                RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
                rd.include(req, resp);
            } else {
                resp.setContentType("text/html");
                out.print("<h3 style='color:red'> User not found or delete failed</h3>");

                RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
                rd.include(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();

            resp.setContentType("text/html");
            out.print("<h3 style='color:red'> " + e.getMessage() + " </h3>");

            RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
            rd.include(req, resp);
        }
    }
}