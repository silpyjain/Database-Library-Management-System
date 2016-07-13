package Library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class AddingBorrower
 */
@WebServlet("/AddingBorrower")
public class AddingBorrower extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddingBorrower() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Fname = request.getParameter("Fname");
		String Lname = request.getParameter("Lname");
		String Email = request.getParameter("Email");
		String Address = request.getParameter("Address");
		String City = request.getParameter("City");
		String State = request.getParameter("State");
		String Phone = request.getParameter("Phone");
		Boolean user = true;
		PrintWriter write = response.getWriter();
		response.setContentType("text/html");
		java.sql.Connection conn = null;
		
		try {
			if ("".equalsIgnoreCase(Fname)
					&& "".equalsIgnoreCase(Lname)
					&& "".equalsIgnoreCase(Address)) {
				write.println("<html>");
				write.println("<body>");
				write.println("<h2> Please Enter Valid Values");
				write.println("</h2>");
				write.println("<body>");
				write.println("</html>");

			} else {
				Class.forName("com.mysql.jdbc.Driver");

				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/library", "root", "root");

				String query1 = null;
				String query2 = null;
				query1 = "SELECT * from borrower where Fname = \'" + Fname
						+ "\' && Lname = \'" + Lname + "\' && Address = \'"
						+ Address + "\' ;";

				query2 = "INSERT INTO borrower(Fname,Lname,Email,Address,City,State,Phone) VALUES(\'"
					+ Fname + "\',\'"					
					+ Lname + "\',\'"
					+ Email + "\',\'" 
					+ Address + "\',\'"
					+ City + "\',\'"
					+ State + "\',\'"
					+ Phone + "\')";
				ResultSet rs = null;
//				ResultSet rs2 = null;
				int rs3;

				Statement stmt, stmt2;

				stmt = (Statement) conn.createStatement();
				rs = stmt.executeQuery(query1);
				stmt2 = (Statement) conn.createStatement();

				if (rs.next()) {

					user = false;
				}

//				if (rs2.next()) {
//					card = false;
//				}
				if (user) {
					rs3 = stmt2.executeUpdate(query2);

					write.println("<html>");
					write.println("<body>");
					write.println("<center><h2> User Added Successfully </h2></center>");
					
					write.println("<body>");
					write.println("</html>");
				}
				else {

					write.println("<html>");
					write.println("<body>");
					write.println("<center><h2> User Already Exists</h2></center>");
				
					write.println("<body>");
					write.println("</html>");
				}
				
			}

		} catch (ClassNotFoundException  e) {
			e.printStackTrace();
		}
		catch (SQLException  e) {
			e.printStackTrace();
		}
	}
		

		
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
