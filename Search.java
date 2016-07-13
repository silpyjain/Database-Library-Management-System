package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.Statement;

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> title = new ArrayList<String>();
		ArrayList<String> author = new ArrayList<String>();
		ArrayList<String> book = new ArrayList<String>();
		ArrayList<String> branch = new ArrayList<String>();
		ArrayList<Integer> copies = new ArrayList<Integer>();
		ArrayList<Integer> available = new ArrayList<Integer>();

		java.sql.Connection con;
		PrintWriter write = response.getWriter();
		response.setContentType("text/html");
		Boolean flag = true;

		try {
			String bookID = request.getParameter("bookId");
			String btitle = request.getParameter("btitle");
			String aname = request.getParameter("aname");
			String query1 = null;

			query1 = "SELECT b1.book_id, b1.title, b1.author_name, b1.branch_id, b1.no_of_copies as No_Of_Copies,"
					+ "IFNULL(b1.no_of_copies - bl.checkin , b1.no_of_copies) as No_Of_Available_Copies "
					+ "FROM (SELECT b.title, b.book_id, GROUP_CONCAT(ba.author_name) as author_name, bc.branch_id, bc.no_of_copies "
					+ "FROM book b, book_authors ba, book_copies bc where b.book_id = ba.book_id "
					+ "and bc.book_id = b.book_id GROUP BY b.book_id , bc.branch_id) AS b1 LEFT JOIN "
					+ "(SELECT book_id, branch_id, COUNT(*) as checkin FROM book_loans "
					+ "WHERE (book_loans.date_in IS NULL OR book_loans.date_in='0000-00-00') "
					+ "GROUP BY book_loans.book_id , book_loans.branch_id) as bl ON (bl.book_id = b1.book_id AND bl.branch_id = b1.branch_id) where b1.book_id LIKE \'%"
					+ bookID
					+ "%\' and b1.title LIKE \'%"
					+ btitle
					+ "%\' and b1.author_name LIKE \'%" + aname + "%\';";

			try {
				if (flag) {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/library", "root",
							"root");

					Statement stmt = (Statement) con.createStatement();
					ResultSet rs = stmt.executeQuery(query1);

					while (rs.next()) {
						book.add(rs.getString(1));
						title.add(rs.getString(2));
						author.add(rs.getString(3));
						branch.add(rs.getString(4));
						copies.add(rs.getInt(5));
						available.add(rs.getInt(6));
					}

					con.close();
				}
			} catch (Exception e) {

			}
			write.println("<html>");
			write.println("<body>");
			write.println("<table border = 1 align = center cellpadding = 3px>");
			write.println("<style>");
			write.println("<finish>polished</finish>");
			write.println("<material>oak</material>");
			write.println("</style>");
			write.println("<tr>");
			write.println("<th> TITLE </th>");
			write.println("<th> AUTHOR </th>");
			write.println("<th> BOOK ID </th>");
			write.println("<th> BRANCH ID </th>");
			write.println("<th> COPIES </th>");
			write.println("<th> AVAILABLE </th>");
			write.println("<th> Check Out </th>");
			write.println("</tr>");
			for (int i = 0; i < book.size(); i++) {
				write.println("<tr> ");
				write.println("<td>" + title.get(i) + "</td>");
				write.println("<td>" + author.get(i) + "</td>");
				write.println("<td>" + book.get(i) + "</td>");
				write.println("<td>" + branch.get(i) + "</td>");
				write.println("<td>" + copies.get(i) + "</td>");
				write.println("<td>" + available.get(i) + "</td>");
				write.println("<td><form action=\"CheckOut\"><input type=\"text\" name=\"bookid\" value =\""
						+ book.get(i)
						+ "\" ><input type=\"text\" name=\"branchid\" value =\""
						+ branch.get(i)
						+ "\" >CARD NO <input type=\"text\" name=\"cardno\"  ><input type=\"submit\" value=\"Check Out\" /></form><Check Out</button> </td>");
				write.println("</tr> ");
			}

			write.println("</table>");
			write.println("</body>");
			write.println("</html>");
		} finally {

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
