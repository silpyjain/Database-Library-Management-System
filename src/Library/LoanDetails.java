package Library;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class LoanDetails {
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

        
        
        
        
        
        
        
        
        
        
        
        
       
        
        
        
        
    
       
    



