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


/**
 * Servlet implementation class ReceiveFine
 */
@WebServlet("/ReceiveFine")
public class ReceiveFine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReceiveFine() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	

	try {
		int cardno = (Integer.parseInt(request.getParameter("cardno")));
		PrintWriter write = response.getWriter();
		response.setContentType("text/html");

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = (Connection) DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library", "root", "root");

		String query = "select card_no, sum(Fine_amount) from book_loans as b, fines as f where b.loanid = f.Loanid and f.Paid = 0 and b.card_no = '"
				+ cardno + "' group by card_no;";
		Statement st1 = conn.createStatement();
		write.println("<html>");
		write.println("<head>");
		
		write.println("</head>");
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
		ResultSet rs1 = st1.executeQuery(query);
		try  {
			if (rs1.next()) {
				write.println("<tr> ");
				write.println("<td>" + cardno + "</td>");
				write.println("<td>" + rs1.getFloat(2) + "</td>");
				write.println("<td><form action=\"PayFine\"><input type=\"hidden\" name=\"cardnum\" value =\""
						+ cardno
						+ "\" ><input type=\"submit\" value=\"Pay Fines\" /></form></td>");
				write.println("</tr> ");

			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		write.println("</table>");
		write.println("</body>");
		write.println("</html>");
		

	} catch (Exception e) {
		System.out.println("Error: " + e.toString());
	}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
