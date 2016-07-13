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



@WebServlet("/CheckOutBooks")
public class CheckOutBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckOutBooks() {
        super();
    }
    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

        String cardno = request.getParameter("cardno");
		String bookid = request.getParameter("bookid");
		String branchid = request.getParameter("branchid");
		Boolean CheckOutMaxLimit = false;
		Boolean BookAvailable = true;
		Boolean Done = false;
		Boolean SameBook = true, finesPaid = true;
		java.sql.Connection conn;
		PrintWriter write = response.getWriter();
		response.setContentType("text/html");
		
		try {
			int rs3 = 0;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
			String query1 = null;
			String query2 = null;
			String query3 = null;
			String query4 = null, query5 = null;
			
			query1 = "SELECT count(*) from book_loans where card_no = \'"
					+ cardno + "\' and Datein is null ;";
			
			query2 = "SELECT noofcopies - (SELECT count(*) from book_loans AS bl WHERE bl.Bookid =\'"
					+ bookid
					+ "\' AND bl.branchid =\'"
					+ branchid
					+ "\' and datein is null) from book_copies AS bc WHERE bc.bookid =\'"
					+ bookid + "\' AND bc.Branchid = \'" + branchid + "\';";
			query3 = "INSERT INTO book_loans(Bookid,Branchid,card_no,Dateout,Duedate) VALUES(\'"
					+ bookid
					+ "\' ,\'"
					+ branchid
					+ "\', \'"
					+ cardno
					+ "\' , CURDATE(), date_add(CURDATE(), INTERVAL 14 DAY)) ;";
			
			query4 = "SELECT * from book_loans WHERE Bookid = \'" + bookid
					+ "\' AND Branchid = \'" + branchid
					+ "\' AND card_no = \'" + cardno
					+ "\' and Datein is null;";
			//fine
			query5 = "SELECT * FROM fines a , book_loans b WHERE a.loanid=b.loanid AND a.paid='0' AND b.card_no=\'"
					+ cardno + "\';";
			Statement stmnt, stmnt2, stmnt3, stmnt4, stmnt5;
			stmnt = (Statement)conn.createStatement();
			stmnt2 = (Statement)conn.createStatement();
			stmnt3 = (Statement)conn.createStatement();
			stmnt4 = (Statement)conn.createStatement();
			stmnt5 = (Statement)conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query1);
			ResultSet rs2 = stmnt2.executeQuery(query2);
			ResultSet r = stmnt4.executeQuery(query4);
			ResultSet rs5 = stmnt5.executeQuery(query5);
			while (rs.next()){
				if (rs.getInt(1)< 3){
				CheckOutMaxLimit = true;
				} else {
					CheckOutMaxLimit = false;
				}
		}
		while (rs2.next()){
			if (rs2.getInt(1)>0){
				BookAvailable = true;
			}else {
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
			rs3 = stmnt3.executeUpdate(query3);
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
    
	
	
	if (Done){

		write.println("<html>");
		write.println("<body> <h2> Check Out Successful !!</h2>");

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
	catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
