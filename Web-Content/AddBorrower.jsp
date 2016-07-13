<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function checkValues() {
		if (document.getElementById("fname").value == ""
				&& document.getElementById("lname").value == ""
				&& document.getElementById("address").value == "") {
			alert("Please enter values for all mandatory fields");
			return false;
		} else
			document.getElementById("Borrower").setAttribute("action",
					"AddingBorrower");
	}
</script>
<link rel="stylesheet" type ="text/css" href="style.css">
</head>
<body>
<center>
		<h2>Add A New Borrower</h2>
	</center>
	<form id="Borrower" method="get" action="">
		<table align="center">
			<tr>
				<td>First Name: <span style="color: red;">*</span>
				<td />
				<td><input type="text" name="Fname" id="fname"></td>
			</tr>
			<tr>
				<td>Last Name: <span style="color: red;">*</span>
				<td />
				<td><input type="text" name="Lname" id="lname"></td>
			</tr>
			<tr>
				<td>Email Id:
				<td />
				<td><input type="text" name="Email" id="emailid"></td>
			</tr>
			<tr>
				<td>Address: <span style="color: red;">*</span>
				<td />
				<td><input type="text" name="Address" id="address"></td>
			</tr>
			<tr>
				<td>City: 
				<td />
				<td><input type="text" name="City" id="city"></td>
			</tr>
			<tr>
				<td>State: 
				<td />
				<td><input type="text" name="State" id="state"></td>
			</tr>
			<tr>
				<td>Phone:
				<td />
				<td><input type="text" name="Phone"></td>
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