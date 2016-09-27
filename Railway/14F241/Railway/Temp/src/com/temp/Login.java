package com.temp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			String user = request.getParameter("userName");
			String pass = request.getParameter("password");
			String admin="yes";
				response.setContentType("text/html");
				Map<String, String> mp = new TreeMap<String,String>();
				if(user == null || pass == null || user.equals("") || pass.equals("")) {
				mp.put("message", "Query Error");
				} else {
				try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Railway","Sudhakar","sudhakarms");
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select * from userDetails where username=? and password=?");
				ps.setString(1, user);
				ps.setString(2, pass);
				ResultSet rs = (ResultSet) ps.executeQuery();
				if(rs.next()) {
					PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement("select * from userDetails where isAdmin=? and userName=?");
					ps1.setString(1, admin);
					ps1.setString(2, user);
					ResultSet rs1 = (ResultSet) ps1.executeQuery();
					if(rs1.next()){
						mp.put("message", "<a href=insert.html><button >Add new</button></a><a href=update.html><button >Update</button></a><a href=delete.html><button >Delete</button></a>");
					}
					else{
						mp.put("message", "<a href=users.html><button >Go ahead</button></a>");
						//response.sendRedirect("users.html");
					}
				} else {
				mp.put("message", "Login Error");
			                                 	}
				} catch(Exception e) {
				mp.put("message", "Database Error");
				}
				}
				out.println(new Gson().toJson(mp));
				out.close();
				}
				}
				}