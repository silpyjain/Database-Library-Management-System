<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
#menu li {
display: inline;
padding: 20px;
}
</style>
</head>
<body style="background-color:orange">
<div id="menu">
<ul style="list-style:none ">
<li><a href="Book_Search.jsp" target="List"
				style="color: black; font-size: 20px; display:inline;">Search </a></li>

					<li><a href="CheckOutBooks.jsp" target="List"
						style="color: black; font-size: 20px; display:inline;">Check Out</a></li>

								<li><a href="CheckInBooks.jsp" target="List"
										style="color: black; font-size: 20px;">Check In</a></li>
										
							
									<li><a href="AddBorrower.jsp" target="List"
										style="color: black; font-size: 20px;">Add User</a></li>
										
										<li><a href="ReceiveFine.jsp" target="List"
										style="color: black; font-size: 20px;">Receive Fine</a></li>
										
									<li><a href="UpdateFine.jsp" target="List"
										style="color: black; font-size: 20px;">Update Fine</a></li>
										
									
										
		</ul>



</div>

</body>
</html>