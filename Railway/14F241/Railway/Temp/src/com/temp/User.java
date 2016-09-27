package com.temp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.ResultSet;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			String source=request.getParameter("source");
			String dest=request.getParameter("destination");
			response.setContentType("text/html");
			try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Railway","Sudhakar","sudhakarms");
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from trainDetails where source=? and destination=?");
			ps.setString(1,source);
			ps.setString(2,dest);
			ResultSet rs = (ResultSet) ps.executeQuery();
			if(rs.next()){
				out.println("<style>th,td{ padding:15px;}</style>");
				out.println("<table>");
				out.println("<tr style=\"padding:15px;\"><th >TrainNo</th><th>TrainName</th><th>Source</th><th>Destination</th><th>SourceTime</th><th>DestinationTime</th><th>Via</th></tr>");
				out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td></tr>");
				out.println("</table>");
				out.println("<a href=users.html>click here</a> to search another train");
				out.println("</br>");
				out.println("<a href=index.html>Home</a>");
				
			}
			else{
				out.println("Trains not available");
			}
			}
			catch(Exception e){
				out.println("Error");
		}
		}
	}

}
