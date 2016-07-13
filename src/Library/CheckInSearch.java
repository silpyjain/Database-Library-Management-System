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
 * Servlet implementation class CheckInSearch
 */
@WebServlet("/CheckInSearch")
public class CheckInSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckInSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Bookid = request.getParameter("bookid");
		String cardno = request.getParameter("cardno");
		String bookname = request.getParameter("bname");
		String query = null;
		java.sql.Connection conn;
		PrintWriter write = response.getWriter();
		response.setContentType("text/html");
		
		query = "SELECT b.Fname,b.Lname,bl.card_no, bl.Bookid,bl.Branchid,bl.Dateout,bl.Duedate FROM borrower AS b, book_loans AS bl WHERE CONCAT(b.Fname,\' \',b.Lname) LIKE \'%"
			+ bookname
			+ "%\' AND b.card_no = bl.card_no AND bl.Datein is null AND bl.Bookid LIKE \'%"
			+ Bookid + "%\' AND bl.card_no LIKE \'%" + cardno + "%\';";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");

			Statement stmt;
			stmt = (Statement) conn.createStatement();

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
				write.println("<td><form action=\"CheckInBooks\"><input type=\"text\" name=\"bookid\" value =\""
						+ rs.getString(4)
						+ "\" /><input type=\"text\" name=\"branchid\" value =\""
						+ rs.getString(5)
						+ "\" /><input type=\"text\" name=\"cardno\" value =\""
						+ rs.getString(3)
						+ "\" /><input type=\"submit\" value=\"Check In\" /></form><Check In</td>");
				write.println("</tr> ");
			}
			write.println("</table>");
			write.println("</body>");
			write.println("</html>");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
catch (SQLException e){
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
