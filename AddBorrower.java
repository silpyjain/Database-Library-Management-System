package Servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

@WebServlet("/AddBorrower")
public class AddBorrower extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddBorrower() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String lname = request.getParameter("lname");
		String address = request.getParameter("address");
		String fname = request.getParameter("fname");
		String phone = request.getParameter("phone");
		Boolean user = true;
//		Boolean card = true;
		PrintWriter write = response.getWriter();
		response.setContentType("text/html");
		java.sql.Connection con = null;

		try {
			if ("".equalsIgnoreCase(fname)
					&& "".equalsIgnoreCase(lname)
					&& "".equalsIgnoreCase(address)
					&& "".equalsIgnoreCase(phone)) {
				write.println("<html>");
				write.println("<body>");
				write.println("<h2> Enter Valid Input Values");
				write.println("</h2>");
				write.println("<body>");
				write.println("</html>");

			} else {
				Class.forName("com.mysql.jdbc.Driver");

				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/library", "root", "root");

				String query1 = null;
//				String query2 = null;
				String query3 = null;
				query1 = "SELECT * from borrower where fname = \'" + fname
						+ "\' && lname = \'" + lname + "\' && address = \'"
						+ address + "\' ;";
//				query2 = "SELECT * from borrower where card_no = \'" + cardno
//						+ "\' ;";
				query3 = "INSERT INTO borrower(fname,lname,address,phone) VALUES(\'"
						+ fname
						+ "\',\'"
						+ lname
						+ "\',\'"
						+ address + "\',\'" + phone + "\');";
				ResultSet rs = null;
//				ResultSet rs2 = null;
				int rs3;

				Statement stmt, stmt3;

				stmt = (Statement) con.createStatement();
				rs = stmt.executeQuery(query1);
				stmt3 = (Statement) con.createStatement();
//				stmt2 = (Statement) con.createStatement();
//				rs2 = stmt2.executeQuery(query2);

				if (rs.next()) {

					user = false;
				}

//				if (rs2.next()) {
//					card = false;
//				}

				if (!user) {

					write.println("<html>");
					write.println("<body>");
					write.println("<h2> User Already Exists");
					write.println("</h2>");
					write.println("<body>");
					write.println("</html>");
				}
				if (user) {
					rs3 = stmt3.executeUpdate(query3);

					write.println("<html>");
					write.println("<body>");
					write.println("<h2> User Added Successfully");
					write.println("</h2>");
					write.println("<body>");
					write.println("</html>");
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
