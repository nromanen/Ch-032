<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Please enter your product parameters</h2>
	<form action="AddProductServlet" method="get">
		Product title : <input type="text" name="productTitle"><br>
		Price for 1 gram or 1 mililitr: <input type="text" name="productPrice"><br>
		Choose dimension of your product:<br> 
		<input type="radio" name="productDimension" value="GR"> Gram<br>
		<input type="radio" name="productDimension" value="ML"> Mililitr<br>
		Choose availibility of your product:<br> 
		<input type="radio" name="productAvailibility" value="TRUE"> True<br>
		<input type="radio" name="productAvailibility" value="FALSE"> False<br><br>
		<input type="submit" value="Save">
	</form><br>
	<form action="ProductList" method="get">
	<input type="submit" value="Back to list">
	</form>
</body>
</html>