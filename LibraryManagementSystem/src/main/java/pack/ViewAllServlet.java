package pack;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.google.gson.Gson;

@WebServlet("/ViewAllServlet")
public class ViewAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			  
			Connection con = DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/sonoo","root","root");  
			  
			//here sonoo is database name, root is username and password  
			  
			Statement stmt=con.createStatement();  
			  
			ResultSet rs=stmt.executeQuery("select * from books");  
			  
			List<Books> list = new ArrayList<Books>();

			while (rs.next()) {
				 
				Books b = new Books(rs.getInt(1), rs.getString(2),rs.getString(4),rs.getString(5),rs.getInt(3));
				list.add(b);
				
			}
			String json = new Gson().toJson(list);
			
	}
		
	catch(Exception e)
			{
			System.out.println(e);}   
			}  
		
	}


