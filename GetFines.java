package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetFines")
public class GetFines extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetFines() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			int cardno = (Integer.parseInt(request.getParameter("cardno")));
			PrintWriter write = response.getWriter();
			response.setContentType("text/html");

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");

			String query = "select card_no, sum(fine_amount) from book_loans as b, fines as f where b.loan_id = f.loan_id and f.paid = 0 and b.card_no = '"
					+ cardno + "' group by card_no;";
			Statement st1 = con.createStatement();
			write.println("<html>");
			write.println("<head>");
			write.println("<script>");
			write.println("function showDiv(){document.getElementById(\"history\").style.display=\"block\";}");
			write.println("</script>");
			write.println("<head>");
			write.println("<body>");
			write.println("<table border = 1 align = center cellpadding = 3px>");
			write.println("<style>");
			write.println("<finish>polished</finish>");
			write.println("<material>oak</material>");
			write.println("</style>");
			write.println("<tr>");
			write.println("<th> Card Number </th>");
			write.println("<th> Total Fines </th>");
			write.println("<th> Pay Fines </th>");
			write.println("</tr>");

			try (ResultSet rs1 = st1.executeQuery(query)) {
				if (rs1.next()) {
					write.println("<tr> ");
					write.println("<td>" + cardno + "</td>");
					write.println("<td>" + rs1.getFloat(2) + "</td>");
					write.println("<td><form action=\"PayFines\"><input type=\"hidden\" name=\"cardnum\" value =\""
							+ cardno
							+ "\" ><input type=\"submit\" value=\"Pay Fines\" /></form></td>");
					write.println("</tr> ");

				}
			}
			write.println("<tr>");
			write.println("<td><input type=\"button\" value=\"Check History\" onclick=\"showDiv();\"/></td>");
			write.println("</tr>");
			write.println("</table>");

			query = "select b.title, bl.branch_id , bl.date_out, bl.date_in, bl.due_date, f.fine_amount from book as b, book_loans as bl, fines as f where bl.card_no ='"
					+ cardno
					+ "' and b.book_id = bl.book_id and bl.loan_id = f.loan_id and f.paid = '1';";
			Statement st2 = con.createStatement();
			ResultSet rs2 = st2.executeQuery(query);

			write.println("<div id=\"history\" style=\"display:none\">");
			write.println("<table border = 1 align = center cellpadding = 3px>");
			write.println("<style>");
			write.println("<finish>polished</finish>");
			write.println("<material>oak</material>");
			write.println("</style>");
			write.println("<tr>");
			write.println("<th> TITLE </th>");
			write.println("<th> BRANCH </th>");
			write.println("<th> DATE OUT </th>");
			write.println("<th> DATE IN </th>");
			write.println("<th> DATE DUE </th>");
			write.println("<th> FINE </th>");
			write.println("</tr>");
			while (rs2.next()) {
				write.println("<tr> ");
				write.println("<td>" + rs2.getString(1) + "</td>");
				write.println("<td>" + rs2.getInt(2) + "</td>");
				write.println("<td>" + rs2.getString(3) + "</td>");
				write.println("<td>" + rs2.getString(4) + "</td>");
				write.println("<td>" + rs2.getString(5) + "</td>");
				write.println("<td>" + rs2.getString(6) + "</td>");
				write.println("</tr> ");
			}

			write.println("</table>");
			write.println("</div>");
			write.println("</body>");
			write.println("</html>");
			rs2.close();

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
