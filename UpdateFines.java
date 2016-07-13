package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateFines")
public class UpdateFines extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateFines() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;

		try {
			int diff, i;
			float fine;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");

			Statement stmt1 = con.createStatement();
			Statement stmt2 = con.createStatement();

			PrintWriter write = response.getWriter();
			response.setContentType("text/html");

			String query = "select loan.loan_id, fines.paid, loan.date_in, loan.due_date from fines join (select * from book_loans where date_in > due_date or (date_in is null and curdate() > due_date)) as loan on fines.loan_id = loan.loan_id ;";

			ArrayList<LoanDtlsBean> lst = new ArrayList<LoanDtlsBean>();

			ResultSet rs1 = stmt1.executeQuery(query);
			while (rs1.next()) {
				LoanDtlsBean ld = new LoanDtlsBean();
				ld.setLoanid(rs1.getInt(1));
				ld.setPaid(rs1.getInt(2));
				ld.setIn(rs1.getDate(3));
				ld.setDue(rs1.getDate(4));

				lst.add(ld);
			}

			for (LoanDtlsBean list1 : lst) {
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
							+ "' where loan_id = '" + list1.getLoanid() + "' ;";
					i = stmt2.executeUpdate(query);
					System.out.println("" + i);
				}

			}
			rs1.close();

			query = "select loan_id, date_in, due_date from book_loans where (date_in > due_date or (date_in is null and curdate() > due_date)) and loan_id not in ( select loan_id from fines );";
			Statement stmt3 = con.createStatement();
			Statement stmt4 = con.createStatement();

			ResultSet rs2 = stmt3.executeQuery(query);
			ArrayList<LoanDtlsBean> list = new ArrayList<LoanDtlsBean>();
			while (rs2.next()) {

				LoanDtlsBean l = new LoanDtlsBean();
				l.setLoanid(rs2.getInt(1));
				l.setIn(rs2.getDate(2));
				l.setDue(rs2.getDate(3));

				list.add(l);
			}

			for (LoanDtlsBean list2 : list) {
				if (list2.getIn() == null) {
					diff = (int) (-(list2.getDue().getTime() - new Date()
							.getTime()) / (1000 * 60 * 60 * 24));

				} else {
					diff = (int) (-(list2.getDue().getTime() - list2.getIn()
							.getTime()) / (1000 * 60 * 60 * 24));
				}
				fine = (float) (diff * 0.25);
				query = "insert into fines values('" + list2.getLoanid()
						+ "','" + fine + "','" + 0 + "');";
				i = stmt4.executeUpdate(query);
				System.out.println("" + i);
			}

			write.println("<html>");
			write.println("<body> <h2>Fines Updated Successfully</h2>");

			write.println("</body>");
			write.println("</html>");

		} catch (Exception e) {
			System.out.println("Error:" + e.toString());
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
