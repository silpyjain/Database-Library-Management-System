package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class CheckIn
 */
@WebServlet("/CheckIn")
public class CheckIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String cardno = request.getParameter("cardno");
		String bookid = request.getParameter("bookid");
		String branchid = request.getParameter("branchid");

		java.sql.Connection con;
		PrintWriter write = response.getWriter();
		response.setContentType("text/html");

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
			String query = null;

			query = "update book_loans set date_in=CURDATE() WHERE book_id =\'"
					+ bookid + "\' AND branch_id =\'" + branchid
					+ "\' AND card_no=\'" + cardno + "\';";
			Statement stmt;
			stmt = (Statement) con.createStatement();
			int rs = stmt.executeUpdate(query);

			if (rs == 1) {
				write.println("<html>");
				write.println("<body> <h2>Check In Successful !!</h2>");

				write.println("</body>");
				write.println("</html>");
			} else {
				write.println("<html>");
				write.println("<body> <h2>Check In Unsuccessful !!</h2>");

				write.println("</body>");
				write.println("</html>");
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
