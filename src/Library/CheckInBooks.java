package Library;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class CheckInBooks
 */
@WebServlet("/CheckInBooks")
public class CheckInBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckInBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cardno = request.getParameter("cardno");
		String bookid = request.getParameter("bookid");
		String branchid = request.getParameter("branchid");
		
		java.sql.Connection conn;
		PrintWriter write = response.getWriter();
		response.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
			String query = null;

			query = "update book_loans set Datein=CURDATE() WHERE Bookid =\'"
					+ bookid + "\' AND Branchid =\'" + branchid
					+ "\' AND card_no=\'" + cardno + "\';";
			Statement stmt;
			stmt = (Statement) conn.createStatement();
			int rs = stmt.executeUpdate(query);

			if (rs == 1) {
				write.println("<html>");
				write.println("<body> <h2>Check In is Successful !!</h2>");

				write.println("</body>");
				write.println("</html>");
			} else {
				write.println("<html>");
				write.println("<body> <h2>Check In is not Successful !!</h2>");

				write.println("</body>");
				write.println("</html>");
			}
		} catch (ClassNotFoundException e){
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
