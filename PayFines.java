package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PayFines")
public class PayFines extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PayFines() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
			PrintWriter write = response.getWriter();
			response.setContentType("text/html");

			int cardno = Integer.parseInt(request.getParameter("cardnum"));
			String query = "select count(*) from book_loans where card_no = '"
					+ cardno + "' and date_in is null";

			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(query);
			int no_Of_Books = 0;
			while (rs1.next()) {
				no_Of_Books = rs1.getInt(1);
			}
			if (no_Of_Books != 0) {
				request.setAttribute("msg",
						"You can not pay fine. You have not checked in "
								+ no_Of_Books + " books.");

			} else if (no_Of_Books == 0) {
				query = "select loan_id from book_loans where card_no = '"
						+ cardno + "' and date_in > due_date;";
				Statement st2 = con.createStatement();
				ResultSet rs2 = st2.executeQuery(query);

				while (rs2.next()) {
					query = "update fines set paid = '1' where loan_id = '"
							+ rs2.getInt(1) + "';";
					Statement st3 = con.createStatement();
					st3.executeUpdate(query);
					st3.close();
				}
			}
			write.println("<html>");
			write.println("<body> <h2>Payment Successful.</h2>");

			write.println("</body>");
			write.println("</html>");
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
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
