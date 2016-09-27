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
 * Servlet implementation class UpdateInsert
 */
@WebServlet("/UpdateInsert")
public class UpdateInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInsert() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			st.executeUpdate("delete from trainDetails where trainNo = "+no);
			st.executeUpdate("insert into trainDetails values('"+no+"','"+name+"','"+source+"','"+dest+"','"+stime+"','"+dtime+"','"+via+"')");
			out.println("Updated Successfully</br>");
			out.println("<a href=update.html>click here</a> for another response</br>");
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
