package Servlet;

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

import com.mysql.jdbc.Statement;

@WebServlet("/QuickCheckOut")
public class QuickCheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuickCheckOut() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookid = request.getParameter("bookid");
		String branchid = request.getParameter("branchid");
		String cardno = request.getParameter("cardno");
		Boolean CheckOutLimit = true;
		Boolean BookAvailable = true;
		Boolean Done = false;
		Boolean SameBook = true;
		java.sql.Connection con;
		PrintWriter write = response.getWriter();
		response.setContentType("text/html");
		try {

			int rs3 = 0;

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
			String query = null;
			String query2 = null;
			String query3 = null;
			String query4 = null;
			query = "SELECT count(*) from book_loans where card_no = \'"
					+ cardno + "\' and date_in is null;";
			query2 = "SELECT no_of_copies - (SELECT count(*) from book_loans AS bl WHERE bl.book_id =\'"
					+ bookid
					+ "\' AND bl.branch_id =\'"
					+ branchid
					+ "\') from book_copies AS bc WHERE bc.book_id =\'"
					+ bookid + "\' AND bc.branch_id = \'" + branchid + "\';";
			query3 = "INSERT INTO book_loans(book_id,branch_id,card_no,date_out,due_date) VALUES(\'"
					+ bookid
					+ "\' ,\'"
					+ branchid
					+ "\', \'"
					+ cardno
					+ "\' , CURDATE(), date_add(CURDATE(), INTERVAL 14 DAY)) ;";
			query4 = "SELECT * from book_loans WHERE book_id = \'" + bookid
					+ "\' AND branch_id = \'" + branchid
					+ "\' AND card_no = \'" + cardno + "\';";
			Statement stmt, stmt2, stmt3, stmt4;
			stmt = (Statement) con.createStatement();
			stmt2 = (Statement) con.createStatement();
			stmt3 = (Statement) con.createStatement();
			stmt4 = (Statement) con.createStatement();
			// System.out.println(query);
			// System.out.println(query2);
			// System.out.println(query3);
			ResultSet rs = stmt.executeQuery(query);
			ResultSet rs2 = stmt2.executeQuery(query2);
			ResultSet r = stmt4.executeQuery(query4);
			while (rs.next()) {
				if (rs.getInt(1) < 3) {
					CheckOutLimit = true;
				} else {
					CheckOutLimit = false;
				}
			}

			while (rs2.next()) {
				if (rs2.getInt(1) > 0) {
					BookAvailable = true;
				} else {
					BookAvailable = false;
				}
			}

			while (r.next()) {
				SameBook = false;
			}

			if (CheckOutLimit && BookAvailable && SameBook) {
				rs3 = stmt3.executeUpdate(query3);
				Done = true;
			} else if (!SameBook) {
				write.println("<html>");
				write.println("<body> <h2>Can't Check out Same Book More Than Once! :(</h2><h3> Cannot checkout more than 3 books</h3>");

				write.println("</body>");
				write.println("</html>");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (Done) {

			write.println("<html>");
			write.println("<body> <h2>Check Out Successful !!</h2>");

			write.println("</body>");
			write.println("</html>");
		} else if (!CheckOutLimit) {
			write.println("<html>");
			write.println("<body> <h2>Maximum Check Out Limit Reached !!</h2><h3> Cannot checkout more than 3 books</h3>");

			write.println("</body>");
			write.println("</html>");
		} else if (!BookAvailable) {
			write.println("<html>");
			write.println("<body> <h2>Book Unavailable</h2>");

			write.println("</body>");
			write.println("</html>");

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
