package com.temp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			String first = request.getParameter("firstName");
			String last = request.getParameter("lastName");
			String user = request.getParameter("userName");
			String pass = request.getParameter("password");	
			String admin = "no";
			try{
				response.setContentType("text/html");
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Railway","Sudhakar","sudhakarms");
				Statement stmt = conn.createStatement();
				int c = stmt.executeUpdate("insert into userDetails values('"+first+"','"+last+"','"+user+"','"+pass+"','"+admin+"')");
				if(c>0)
				{
					out.println("Signed up Successfully");
					out.println("<br>");
					out.println("<a href=signuptemp.html>Click here</a>");
					out.println(" to Sign Up another account...");
					out.println("<br>");
					out.println("<a href=index.html>Home</a>");
				}
			}  
			catch(Exception e)
			{
				out.println(e);
			}
		}
	}
}