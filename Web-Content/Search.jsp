<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
</head>

<style type="text/css">
body {
	text-align: center;
}
</style>



<body>

	<center>
		<h2>Search Books by Book ID and/or Title and/or Author</h2>
	</center>
	<form id="form2" method="get" action="Search">
		<table align="center">
			<tr>
				<td>Book ID:</td>
				<td><input type="text" name="bookId"></td>
			</tr>

			<tr>
				<td>Book Title:</td>
				<td><input type="text" name="btitle"></td>
			</tr>

			<tr>
				<td>Author:</td>
				<td><input type="text" name="aname"></td>
			</tr>

			<tr>
				<td colspan="2"><input class="submit" type="submit"
					value="Search"></td>
			</tr>
		</table>
	</form>




</body>
</html>