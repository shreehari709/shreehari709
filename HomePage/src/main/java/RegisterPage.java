

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


@WebServlet("/regForm")

public class RegisterPage extends HttpServlet {


	private static final long serialVersionUID = 5339244778025620096L;

@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String myname = request.getParameter("Name");
		String myemail = request.getParameter("email");
		String mypwd =  request.getParameter("pwd");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cat","root","root");
			
			PreparedStatement ps	= connection.prepareStatement("insert into Register values(?,?,?)");
			ps.setString(1, myname);
			ps.setString(2, myemail);
			ps.setString(3, mypwd);
			
			int count = ps.executeUpdate();
			if(count>0) {
				 response.sendRedirect("welcome.jsp");
				//response.setContentType("text/html");
				//out.print(" <h3 style='color: greenyellow;'>User Sucessfully Register</h3>");
			}else {
				out.print("<h3 style='red;'>User Not Register</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("/Register.jsp");
				rd.include(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			out.print(e);
			
		}
	}
}
