<%@page import="com.item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    <%
    if (request.getParameter("Supplier_code") != null) 
    { 
     item itemObj = new item(); 
     String stsMsg = ""; 
    //Insert--------------------------
    if (request.getParameter("hidsupplierIDSave") == "") 
     { 
     stsMsg = itemObj.insertsupplier(request.getParameter("Supplier_code"), 
     request.getParameter("Name"), 
     request.getParameter("Phone")); 
     
     } 
    else//Update----------------------
     { 
     stsMsg = itemObj.updatesupplier(request.getParameter("hidsupplierIDSave"), 
     request.getParameter("Supplier_code"), 
     request.getParameter("Name"), 
     
     request.getParameter("Phone")); 
     } 
     session.setAttribute("statusMsg", stsMsg); 
    } 
  //Delete item----------------------------------
    if (request.getParameter("supplierID") != null)
     {
     item itemObj = new item();
     String stsMsg = itemObj.deletesupplier(request.getParameter("supplierID"));
     session.setAttribute("statusMsg", stsMsg);
     }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Supplier Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
				<div class="col-8">
<h1>Supplier Management</h1>





		<form id="formItem" name="formItem" method="post" action="items.jsp">
 Supplier code: 
<input id="Supplier_code" name="Supplier_code" type="text" 
 class="form-control form-control-sm">
<br> Supplier name: 
<input id="Name" name="Name" type="text" 
 class="form-control form-control-sm">
<br> Phone: 
<input id="Phone" name="Phone" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidsupplierIDSave" name="hidsupplierIDSave" value="">
</form>

	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemsGrid">
	<%
			item itemObj = new item();
			out.print(itemObj.readsupplier());
	%>
	
	</div>
</div>

</div>

</div>

</body>
</html>



