<%@page import = "com.Payment" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/pay.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> <br><br>
<br>
<h1>Payment Management</h1>
	<form id="formPayment" name="formPayment" method="post" action="payments.jsp" >
	   Bill Account Number: 
	 <input id="paymentCode" name="paymentCode" type="text" 
	 class="form-control form-control-sm">
	 <br>Customer name: 
	 <input id="paymentName" name="paymentName" type="text" 
	 class="form-control form-control-sm">
	 <br> Pay Value(Rs): 
	 <input id="paymentPrice" name="paymentPrice" type="text" 
	 class="form-control form-control-sm">
	 <br> Contact Number : 
	 <input id="paymentContact" name="paymentContact" Placeholder="format (xx xxx xxxx)"  
	 class="form-control form-control-sm">
	 <br>
	 <input id="btnSave" name="btnSave" type="button" value="Save" 
	 class="btn btn-primary">
	 <input type="hidden" id="hidPaymentIDSave" 
	 name="hidPaymentIDSave" value="">
	</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divPaymentGrid">
 <%
 Payment paymentObj = new Payment(); 
 out.print(paymentObj.readPayments()); 
 %>
</div>
</div> </div></div>
</body>
</html>
