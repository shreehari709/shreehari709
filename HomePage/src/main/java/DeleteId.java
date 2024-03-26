import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/removeRegistration")

public class DeleteId extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
        String username = request.getParameter("email");
        String password = request.getParameter("pwd");
    
        try {
        	Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cat","root","root");
			
			
			PreparedStatement ps	=  connection.prepareStatement("delete from Register where email=? and pwd=?;");
			ps.setString(1, username);
			ps.setString(2, password);
			int count = ps.executeUpdate();
			if(count>0) {
				response.setContentType("text/html");
				out.print(" <h3 style='color: greenyellow;'>User Sucessfully Register</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
				rd.include(request, response);
			}else {
				out.print("<h3 style='red;'>Your Id Not Found</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
				rd.include(request, response);
			}
        } catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
