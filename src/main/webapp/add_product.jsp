<jsp:useBean id="defaultProduct" class="com.javagroup.restaurantmenu.jspbeans.DefaultProduct" scope="request">
</jsp:useBean>

<HTML>
<HEAD>
<TITLE>Products</TITLE>
<BODY>
<CENTER>
<h3>Please, fill the fields on new product:</h3>
<FORM action=menu name=form1>
<TABLE border=1 cellspacing=0 cellpadding=2>
<TR>
	<TD><b>name:</b></TD>
	<TD><input type=text name=product_name value='<jsp:getProperty name="defaultProduct" property="name" />'></TD>
</TR>
<TR>
	<TD><b>price:</b></TD>
	<TD><input type=text name=product_price value='<jsp:getProperty name="defaultProduct" property="price" />'></TD>
</TR>
<TR>
	<TD><b>available:</b></TD>
	<TD><input type=checkbox name=product_available value=true checked="checked"></TD>
</TR>
</TABLE>
<input type=submit name=action value=add>
</CENTER>
</BODY>
</HTML>

 