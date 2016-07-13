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

@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckOut() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String cardno = request.getParameter("cardno");
		String bookid = request.getParameter("bookid");
		String branchid = request.getParameter("branchid");
		Boolean CheckOutMaxLimit = true;
		Boolean BookAvailable = true;
		Boolean Done = false;
		Boolean SameBook = true, finesPaid = true;
		java.sql.Connection con;
		PrintWriter write = response.getWriter();
		response.setContentType("text/html");
		try {

			int rs3 = 0;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
			String query1 = null;
			String query2 = null;
			String query3 = null;
			String query4 = null, query5 = null;
			query1 = "SELECT count(*) from book_loans where card_no = \'"
					+ cardno + "\' and date_in is null ;";
			query2 = "SELECT no_of_copies - (SELECT count(*) from book_loans AS bl WHERE bl.book_id =\'"
					+ bookid
					+ "\' AND bl.branch_id =\'"
					+ branchid
					+ "\' and date_in is null) from book_copies AS bc WHERE bc.book_id =\'"
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
					+ "\' AND card_no = \'" + cardno
					+ "\' and date_in is null;";
			query5 = "SELECT * FROM fines a , book_loans b WHERE a.loan_id=b.loan_id AND a.paid='0' AND b.card_no=\'"
					+ cardno + "\';";
			Statement stmt, stmt2, stmt3, stmt4, stmt5;
			stmt = (Statement) con.createStatement();
			stmt2 = (Statement) con.createStatement();
			stmt3 = (Statement) con.createStatement();
			stmt4 = (Statement) con.createStatement();
			stmt5 = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(query1);
			ResultSet rs2 = stmt2.executeQuery(query2);
			ResultSet r = stmt4.executeQuery(query4);
			ResultSet rs5 = stmt5.executeQuery(query5);
			while (rs.next()) {
				if (rs.getInt(1) < 3) {
					CheckOutMaxLimit = true;
				} else {
					CheckOutMaxLimit = false;
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
			while (rs5.next()) {
				finesPaid = false;
			}

			if (CheckOutMaxLimit && BookAvailable && SameBook && finesPaid) {
				rs3 = stmt3.executeUpdate(query3);
				Done = true;
			} else if (!finesPaid) {
				write.println("<html>");
				write.println("<body> <h2>Can't Check out Book As Borrower has not paid fines!</h2>");

				write.println("</body>");
				write.println("</html>");
			} else if (!SameBook) {
				write.println("<html>");
				write.println("<body> <h2>Can't Check out Same Book More Than Once!</h2>");

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
		} else if (!CheckOutMaxLimit) {
			write.println("<html>");
			write.println("<body> <h2>Maximum Check Out Limit Reached !!</h2><h3> Cannot checkout more than 3 books</h3>");

			write.println("</body>");
			write.println("</html>");
		} else if (!BookAvailable) {
			write.println("<html>");
			write.println("<body> <h2>Book not available</h2>");

			write.println("</body>");
			write.println("</html>");

		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
