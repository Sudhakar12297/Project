package com.temp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter();){
		int no = Integer.parseInt(request.getParameter("trainNo"));
		response.setContentType("text/html");
		out.print("Welcome");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			out.print("Welcome1");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Railway","Sudhakar","sudhakarms");
			out.print("Welcome2");
			Statement ps = con.createStatement();
			out.print("Welcome3");
			ResultSet rs = ps.executeQuery("select * from trainDetails where trainNo = "+no);
			out.print("Welcome4");
			if(rs.next()) {
				out.print("Welcome5");
				out.println("<form action=\"UpdateInsert\" method=\"post\"><table>");
				out.println("<tr><td>Train No:</td><td><input type=\"text\" name=\"trainNo\" value=\""+rs.getString(1)+"\"></td></tr><br>");
				out.println("<tr>Train Name:<td></td><td><input type=\"text\" name=\"trainName\" value=\""+rs.getString(2)+"\"></td></tr><br>");
				out.println("<tr><td>Source:</td><td><input type=\"text\" name=\"source\" value=\""+rs.getString(3)+"\"></td></tr><br>");
				out.println("<tr><td>Destination:</td><td><input type=\"text\" name=\"destination\" value=\""+rs.getString(4)+"\"></td></tr><br>");
				out.println("<tr><td>Source Time:</td><td><input type=\"text\" name=\"sourceTime\" value=\""+rs.getString(5)+"\"></td></tr><br>");
				out.println("<tr><td>Destination Time:</td><td><input type=\"text\" name=\"destinationTime\" value=\""+rs.getString(6)+"\"></td></tr><br>");
				out.println("<tr><td>Route:</td><td><input type=\"text\" name=\"via\" value=\""+rs.getString(7)+"\"></td></tr><br>");
				out.println("<tr><td colspan=2><input type=\"submit\"></td></tr>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e);
		}
		}
	}
}

