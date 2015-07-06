<%@ page import="com.menu.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		HibernateCRUD hbr = new HibernateCRUD();
		hbr.initEntityManager();
		hbr.getAllProducts();
	%>
	<table cellpadding="5" border=1>

		<tr valign="bottom">
			<td align="left"><b>Id</b></td>
			<td align="left"><b>Description</b></td>
			<td align="left"><b>Price</b></td>
			<td align="left"><b>Product dimension</b></td>
			<td align="left"><b>Availability</b></td>
		</tr>

		<%
			for (Product pr : Product.productList) {
		%>
		<tr valign="top">
			<td>
				<%=pr.getId() %>
			</td>
			<td>
				<%
					out.println(pr.getTitle());
				%>
			</td>
			<td>
				<%
					out.println(pr.getPrice());
				%>
			</td>
			<td>
				<%
					out.println(pr.getProductDimension());
				%>
			</td>
			<td>
				<%
					out.println(pr.isAvailable());
				%>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<br />

	<form action="AddProduct" method="get">
		<input type="submit" value='Add new product!'>
	</form>
	<br />
	<form action="DeleteProduct" method="get">
		<input type="submit" value='Delete product by name!'>
	</form>
	<br />
	<form action="UpdateProduct" method="get">
		<input type="submit" value='Update product!'>
	</form>
	<br />
</body>
</html>