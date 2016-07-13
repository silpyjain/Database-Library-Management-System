<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check In Books Here!</title>
</head>
<body>
<center>
		<h2>Check In</h2>
	</center>
	<form id="checkin" method="get" action="CheckInSearch">
		<table align="center">
			
				<tr>
				<td>Card No:</td>
				<td><input type="text" name="cardno" id="cardno"></td>
			</tr>
			<tr>
				<td>Book Id:</td>
				<td><input type="text" name="bookid" id="bookid"></td>
			</tr>

			<tr>
				<td>Borrower Name:</td>
				<td><input type="text" name="bname" id="bname"></td>
			</tr>

			<tr>
			    <td></td>
				<td><input type="submit" name="CheckInBooks" value="CheckInBooks">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>