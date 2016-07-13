package Library;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Library.LoanDetails;
import java.sql.*;


@WebServlet("/UpdateFine")
public class UpdateFine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateFine() {
        super();
        
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			try {
				int diff, i;
				float fine;
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = (Connection) DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/library", "root", "root");

				Statement stmt1 = conn.createStatement();
				Statement stmt2 = conn.createStatement();

				PrintWriter write = response.getWriter();
				response.setContentType("text/html");

				String query = "select loan.loanid, fines.Paid, loan.Datein, loan.Duedate from fines join (select * from book_loans where Datein > Duedate or (Datein is null and curdate() > Duedate)) as loan on fines.Loanid = loan.loanid ;";

				ArrayList<LoanDetails> lst = new ArrayList<LoanDetails>();

				ResultSet rs1 = stmt1.executeQuery(query);
				while (rs1.next()) {
					LoanDetails ld = new LoanDetails();
					ld.setLoanid(rs1.getInt(1));
					ld.setPaid(rs1.getInt(2));
					ld.setIn(rs1.getDate(3));
					ld.setDue(rs1.getDate(4));

					lst.add(ld);
				}

				for (LoanDetails list1 : lst) {
					if (list1.getPaid() == 0) {

						if (list1.getIn() == null) {
							diff = (int) (-(list1.getDue().getTime() - new Date()
									.getTime()) / (1000 * 60 * 60 * 24));

						} else {

							diff = (int) (-(list1.getDue().getTime() - list1
									.getIn().getTime()) / (1000 * 60 * 60 * 24));

						}
						fine = (float) (diff * 0.25);
						query = "update fines set fine_amount = '" + fine
								+ "' where loanid = '" + list1.getLoanid() + "' ;";
						i = stmt2.executeUpdate(query);
						System.out.println("" + i);
					}

				}
				rs1.close();

				query = "select loanid, Datein, Duedate from book_loans where (Datein > Duedate or (Datein is null and curdate() > Duedate)) and loanid not in ( select Loanid from fines );";
				Statement stmt3 = conn.createStatement();
				Statement stmt4 = conn.createStatement();

				ResultSet rs2 = stmt3.executeQuery(query);
				ArrayList<LoanDetails> list = new ArrayList<LoanDetails>();
				while (rs2.next()) {

					LoanDetails ld1 = new LoanDetails();
					ld1.setLoanid(rs2.getInt(1));
					ld1.setIn(rs2.getDate(2));
					ld1.setDue(rs2.getDate(3));

					list.add(ld1);
				}

				for (LoanDetails list2 : list) {
					if (list2.getIn() == null) {
						diff = (int) (-(list2.getDue().getTime() - new Date()
								.getTime()) / (1000 * 60 * 60 * 24));

					} else {
						diff = (int) (-(list2.getDue().getTime() - list2.getIn()
								.getTime()) / (1000 * 60 * 60 * 24));
					}
					fine = (float) (diff * 0.25);
					query = "insert into fines ( Loanid, Fine_amount, Paid )values('" + list2.getLoanid()
							+ "','" + fine + "','" + 0 + "');";
					i = stmt4.executeUpdate(query);
					System.out.println("" + i);
				}

				write.println("<html>");
				
				write.println("<body> <center><h2>Fines Updated Successfully</h2></center>");
				

				write.println("</body>");
				write.println("</html>");

			} catch (Exception e) {
				System.out.println("Error:" + e.toString());
			}
		
		} finally {}
		
		
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
