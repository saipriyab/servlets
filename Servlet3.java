package com.wipro;

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

/**
 * Servlet implementation class Servlet3
 */
@WebServlet("/Servlet3")
public class Servlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		Float price=Float.parseFloat(request.getParameter("price"));
		
		 int k=0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			PreparedStatement pst=conn.prepareStatement("insert into products values(?,?,?,?)");
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setInt(3, quantity);
			pst.setFloat(4, price);
			k=pst.executeUpdate();
			conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		if(k==1)
		{
			pw.println("Successfully inserted");
		
			
		}
		else
			pw.println("Registration fail");
	}

	}

