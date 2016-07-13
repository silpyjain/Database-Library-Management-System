<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Checked Out Book</title>
</head>
<body>
	<center>
		<h2>Check In : Search by Book ID , Card No or Name</h2>
	</center>
	<form id="form2" method="get" action="SearchCheckIn">


		<table align="center">
			<tr>
				<td>Book ID:</td>
				<td><input type="text" name="bookid"></td>
			</tr>

			<tr>
				<td>Card No:</td>
				<td><input type="text" name="cardno"></td>
			</tr>

			<tr>
				<td>Borrower Name:</td>
				<td><input type="text" name="bname"></td>
			</tr>
			<tr>
				<td colspan="2"><input class="submit" type="submit"
					value="Search"></td>
			</tr>
		</table>

	</form>

</body>
</html>