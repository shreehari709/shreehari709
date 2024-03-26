import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logForm")

public class LoginPage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("email");
        String password = request.getParameter("pwd");
    
        try {
        	Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cat","root","root");
			
			
			PreparedStatement ps	=  connection.prepareStatement("select *from Register where email=?  and pwd=?;");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			     HttpSession session = request.getSession();
		            session.setAttribute("username", rs.getString("name"));
				 response.sendRedirect("Home.jsp");
			}else {

				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.include(request, response);
			}
        } catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
