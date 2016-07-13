<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
<script type="text/javascript" language="JavaScript">

		window.history.forward();
	    function logout()
	    {
	    	window.open("login.jsp", '_parent');
	    }
</script>
<style type="text/css">
#menu {
	padding: 10px;
	margin: 0;
	text-align: inherit;
}
</style>
</head>
<body bgcolor="2B5047" align="center"><b>Library Management System</b>
	<div id="menu">
		<ul style="list-style: none;">

			<li><a href="Search.jsp" target="frame3"
				style="color: white; font-size: 20px;">Search </a></li>

					<li><a href="CheckOut.jsp" target="frame3"
						style="color: white; font-size: 20px;">Check Out</a></li>


							<li><a href="SearchCheckIn.jsp" target="frame3"
								style="color: white; font-size: 20px;">Check In</a></li>
									<li><a href="AddBorrower.jsp" target="frame3"
										style="color: white; font-size: 20px;">Add User</a></li>
										
									<li><a href="FinesUpdate.jsp" target="frame3"
										style="color: white; font-size: 20px;">Fines Update</a></li>
										
									<li><a href="GetFines.jsp" target="frame3"
										style="color: white; font-size: 20px;">Retrieve Fines</a></li>
		</ul>
	</div>
	Developed By : Milavkumar Shah
</body>
</html>