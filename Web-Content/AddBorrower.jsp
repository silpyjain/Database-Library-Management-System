<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Borrower</title>
<script>
	function checkValues() {
		if (document.getElementById("fname").value == ""
				&& document.getElementById("lname").value == ""
				&& document.getElementById("address").value == "") {
			alert("Please enter values for all mandatory fields");
			return false;
		} else
			document.getElementById("form2").setAttribute("action",
					"AddBorrower");
	}
</script>
</head>
<body>

	<center>
		<h2>ADD Borrower</h2>
	</center>
	<form id="form2" method="get" action="">
		<table align="center">
			<tr>
				<td>First Name: <span style="color: red;">*</span>
				<td />
				<td><input type="text" name="fname" id="fname"></td>
			</tr>
			<tr>
				<td>Last Name: <span style="color: red;">*</span>
				<td />
				<td><input type="text" name="lname" id="lname"></td>
			</tr>
			<tr>
				<td>Address: <span style="color: red;">*</span>
				<td />
				<td><input type="text" name="address" id="address"></td>
			</tr>
			<tr>
				<td>Phone:
				<td />
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td colspan="2"><input class="submit" type="submit"
					onclick="checkValues()" value="ADD Borrower"></td>
			</tr>
			<tr>
				<td colspan="2">Fields marked with <span style="color: red">*</span>
					are mandatory
				</td>
			</tr>
		</table>
	</form>



</body>
</html>