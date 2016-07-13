<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check Out Books Here!</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script>
	function checkData() {
		if (document.getElementById("bookid").value == "") {
			alert("Please enter values for all mandatory fields");
			return false;
		} 
		else if(document.getElementById("branchid").value == ""){
			alert("Please enter values for all mandatory fields");
			return false;
		}
		else if(document.getElementById("cardno").value == ""){
			alert("Please enter values for all mandatory fields");
			return false;
		}
		else
			document.getElementById("checkout").setAttribute("action",
					"CheckOutBooks");
	}
</script>
</head>
<body>
<center>
		<h2>Check Out</h2>
	</center>
	<form id="checkout" method="get" action="">

		<table align="center">
			<tr>
				<td>Book Id:<span style="color: red;">*</span></td>
				<td><input type="text" name="bookid" id="bookid"></td>
			</tr>

			<tr>
				<td>Branch Id:<span style="color: red;">*</span></td>
				<td><input type="text" name="branchid" id="branchid"></td>
			</tr>

			<tr>
				<td>Card No:<span style="color: red;">*</span></td>
				<td><input type="text" name="cardno" id="cardno"></td>
			</tr>

			<tr>
			    <td></td>
				<td><input type="submit" name="submit" value="Check Out" onclick="return checkData()"/></td>
			</tr>
		</table>      
	</form>
	
</body>
</html>