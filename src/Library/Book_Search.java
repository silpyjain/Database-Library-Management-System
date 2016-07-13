package Library;

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

import java.sql.*;

/**
 * Servlet implementation class Book_Search
 */
@WebServlet("/Book_Search")
public class Book_Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Book_Search() {
        super();
     
    }

    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> title = new ArrayList<String>();
		ArrayList<String> author = new ArrayList<String>();
		ArrayList<String> book = new ArrayList<String>();
		ArrayList<String> branch = new ArrayList<String>();
		ArrayList<Integer> copies = new ArrayList<Integer>();
		ArrayList<Integer> available = new ArrayList<Integer>();

		java.sql.Connection conn;
		PrintWriter write = response.getWriter();
		response.setContentType("text/html");
		Boolean flag = true; 
		try {
			String bookID = request.getParameter("bookId");
			String booktitle = request.getParameter("btitle");
			String aname = request.getParameter("aname");
			String query1 = null;
			query1 = "SELECT b1.bookid, b1.title, b1.author_name, b1.branchid, b1.noofcopies,"
				+ "IFNULL(b1.noofcopies - bl.checkin , b1.noofcopies)"
				+ "FROM (SELECT b.title, b.bookid, GROUP_CONCAT(ba.authorname) as author_name, bc.branchid, bc.noofcopies "
				+ "FROM book b, book_authors ba, book_copies bc where b.bookid = ba.bookid "
				+ "and bc.bookid = b.bookid GROUP BY b.bookid , bc.branchid) AS b1 LEFT JOIN "
				+ "(SELECT bookid, branchid, COUNT(*) as checkin FROM book_loans "
				+ "WHERE (book_loans.datein IS NULL OR book_loans.datein='0000-00-00') "
				+ "GROUP BY book_loans.bookid , book_loans.branchid)bl ON (bl.bookid = b1.bookid AND bl.branchid = b1.branchid) where b1.bookid LIKE \'%"
				+ bookID
				+ "%\' and b1.title LIKE \'%"
				+ booktitle
				+ "%\' and b1.author_name LIKE \'%" + aname + "%\' ;  ";
			
			System.out.println(query1);
			try {
				if (flag) {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/library", "root",
							"root");

					Statement stmt = (Statement) conn.createStatement();
					ResultSet rs = stmt.executeQuery(query1);

					while (rs.next()) {
						book.add(rs.getString(1));
						title.add(rs.getString(2));
						author.add(rs.getString(3));
						branch.add(rs.getString(4));
						copies.add(rs.getInt(5));
						available.add(rs.getInt(6));
					}

					conn.close();
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
				write.println("<td><form action=\"CheckOutBooks\"><input type=\"text\" name=\"bookid\" value =\""
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
