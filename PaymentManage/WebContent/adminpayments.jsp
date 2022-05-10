<%@page import="com.epayment.Model.AdminPayment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/adminpayments.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">
	
	<h1>Online Payment Details</h1>
	
	<form id="formAdminPayment" name="formAdminPayment">
		 Pay CardType:
		 <input id="payCardType" name="payCardType" type="text" class="form-control form-control-sm">
		 <br> 
		 Pay CardNO:
		 <input id="payCardNO" name="payCardNO" type="text" class="form-control form-control-sm">
		 <br> 
		 Pay ExpiryDate:
		 <input id="payExpiryDate" name="payExpiryDate" type="text" class="form-control form-control-sm">
		 <br> 
		 Pay CVV:
		 <input id="payCVV" name="payCVV" type="text" class="form-control form-control-sm">
		 <br> 
		 Pay Date:
		 <input id="payDate" name="payDate" type="text" class="form-control form-control-sm">
		 <br> 
		 Pay TotalAmount :
		 <input id="payTotalAmount" name="payTotalAmount" type="text" class="form-control form-control-sm">
		  <br> 
		  Pay Amount :
		 <input id="payAmount" name="payAmount" type="text" class="form-control form-control-sm">
		 <br>
		 
		 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		 <input type="hidden" id="hidPayIDSave" name="hidPayIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	
	<div id="divAdminPaymentsGrid">
		 <%
		 AdminPayment payObj = new AdminPayment();
		 out.print(payObj.readItems());
		 %>
	</div>
</div> </div> </div>
</body>
</html>