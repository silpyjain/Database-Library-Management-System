package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class SearchCheckIn
 */
@WebServlet("/SearchCheckIn")
public class SearchCheckIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchCheckIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String bookid = request.getParameter("bookid");
		String cardno = request.getParameter("cardno");
		String bname = request.getParameter("bname");
		String query = null;
		java.sql.Connection con;
		PrintWriter write = response.getWriter();
		response.setContentType("text/html");

		query = "SELECT b.fname,b.lname,bl.card_no, bl.book_id,bl.branch_id,bl.date_out,bl.due_date FROM borrower AS b, book_loans AS bl WHERE CONCAT(b.fname,\' \',b.lname) LIKE \'%"
				+ bname
				+ "%\' AND b.card_no = bl.card_no AND bl.date_in is null AND bl.book_id LIKE \'%"
				+ bookid + "%\' AND bl.card_no LIKE \'%" + cardno + "%\';";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");

			Statement stmt;
			stmt = (Statement) con.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			write.println("<html>");
			write.println("<body>");
			write.println("<table border = 1 align = center cellpadding = 3px>");
			write.println("<style>");
			write.println("<finish>polished</finish>");
			write.println("<material>oak</material>");
			write.println("</style>");
			write.println("<tr>");
			write.println("<th> Name </th>");
			write.println("<th> Card Number </th>");
			write.println("<th> Book ID </th>");
			write.println("<th> Branch ID </th>");
			write.println("<th> Date Out </th>");
			write.println("<th> Due Date </th>");
			write.println("<th> Check In </th>");
			write.println("</tr>");

			while (rs.next()) {
				write.println("<tr> ");
				write.println("<td>" + rs.getString(1) + rs.getString(2)
						+ "</td>");
				write.println("<td>" + rs.getString(3) + "</td>");
				write.println("<td>" + rs.getString(4) + "</td>");
				write.println("<td>" + rs.getString(5) + "</td>");
				write.println("<td>" + rs.getString(6) + "</td>");
				write.println("<td>" + rs.getString(7) + "</td>");
				write.println("<td><form action=\"CheckIn\"><input type=\"text\" name=\"bookid\" value =\""
						+ rs.getString(4)
						+ "\" ><input type=\"text\" name=\"branchid\" value =\""
						+ rs.getString(5)
						+ "\" ><input type=\"text\" name=\"cardno\" value =\""
						+ rs.getString(3)
						+ "\" ><input type=\"submit\" value=\"Check In\" /></form><Check In</button> </td>");
				write.println("</tr> ");
			}
			write.println("</table>");
			write.println("</body>");
			write.println("</html>");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
