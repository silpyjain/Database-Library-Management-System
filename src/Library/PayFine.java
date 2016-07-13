package Library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class PayFine
 */
@WebServlet("/PayFine")
public class PayFine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PayFine() {
        super();
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
			PrintWriter write = response.getWriter();
			response.setContentType("text/html");

			int cardno = Integer.parseInt(request.getParameter("cardnum"));
			String query = "select count(*) from book_loans where card_no = '"
					+ cardno + "' and Datein is null";

			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(query);
			int no_Of_Books = 0;
			while (rs1.next()) {
				no_Of_Books = rs1.getInt(1);
			}
			if (no_Of_Books != 0) {
				write.println("<body>" +
						" <h2>You cannot pay fine. You have not checked in "
								+ no_Of_Books + " books.</h2>");

			} else if (no_Of_Books == 0) {
				query = "select loanid from book_loans where card_no = '"
						+ cardno + "' and Datein > Duedate;";
				Statement st2 = conn.createStatement();
				ResultSet rs2 = st2.executeQuery(query);

				while (rs2.next()) {
					query = "update fines set paid = '1' where loanid = '"
							+ rs2.getInt(1) + "';";
					Statement st3 = conn.createStatement();
					st3.executeUpdate(query);
					st3.close();
				}
				write.println("<html>");
				write.println("<body> <h2>Payment is Successful.</h2>");
			}
			

			write.println("</body>");
			write.println("</html>");
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
