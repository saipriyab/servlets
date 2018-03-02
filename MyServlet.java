package com.wipro;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String name=request.getParameter("uname");
		String password=request.getParameter("upass");
		boolean flag=false;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			PreparedStatement pst=conn.prepareStatement("select * from users");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String s1=rs.getString(1);
				String s2=rs.getString(2);
				if(s1.equals(name) && s2.equals(password))
				{
					flag=true;
					break;
				}
			}
			conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		pw.println("<html><head><title>response page</title></head>");
		if(flag==true)
		{
			pw.println("success");
			pw.println("<a href='insert.html'>product links page for inserting</a>");
			pw.println("<a href='delete.html'>product links page for deleting</a>");
			pw.println("<a href='update.html'>product links page for updating</a>");
			pw.println("<a href='selection.html'>product links page for selection</a>");
		}
		else{
			pw.println("<body><h1>Login Fail<h1></body></html>");
		}
		pw.close();
	}

}
