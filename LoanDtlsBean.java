package Servlet;

import java.util.Date;

public class LoanDtlsBean {
	int Loanid;
	int Paid;
	Date In;
	Date Due;

	public int getLoanid() {
		return Loanid;
	}

	public void setLoanid(int loanid) {
		Loanid = loanid;
	}

	public int getPaid() {
		return Paid;
	}

	public void setPaid(int paid) {
		Paid = paid;
	}

	public Date getIn() {
		return In;
	}

	public void setIn(Date in) {
		In = in;
	}

	public Date getDue() {
		return Due;
	}

	public void setDue(Date due) {
		Due = due;
	}

}
