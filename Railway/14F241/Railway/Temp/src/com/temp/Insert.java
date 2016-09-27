package com.temp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try(PrintWriter out = response.getWriter()){
			int no = Integer.parseInt(request.getParameter("trainNo"));
			String name = request.getParameter("trainName");
			String source = request.getParameter("source");
			String dest = request.getParameter("destination");
			String stime = request.getParameter("sourceTime");
			String dtime = request.getParameter("destinationTime");
			String via = request.getParameter("via");
			try{
			response.setContentType("text/html");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Railway","Sudhakar","sudhakarms");
			Statement st=con.createStatement();
			st.executeUpdate("insert into trainDetails values('"+no+"','"+name+"','"+source+"','"+dest+"','"+stime+"','"+dtime+"','"+via+"')");
			out.println("Inserted Successfully</br>");
			out.println("<a href=insert.html>click here</a> for another response</br>");
			out.println("<a href=index.html>Home</a>");
			}
		catch (Exception e){
			out.println(e);
		}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
