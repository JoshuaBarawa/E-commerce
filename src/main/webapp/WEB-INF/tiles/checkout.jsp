<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="/css/checkout.css" rel="stylesheet" />


<body>

	<div class="container">
		<form method="post" action="/pay">
			<div class="center">
				<h3>Payment</h3>
				
				
				<div class="form-group">
					<label for="price">Total</label> <input type="text" id="price"
						name="price" class="form-control" value="${total}">
				</div>
				
				<div class="form-group">
					<label for="currency">Currency</label> <input type="text"
						id="currency" name="currency" class="form-control" value="USD"> 
				</div>
				
				<div class="form-group">
					<label for="method">Payment Method</label> <input type="text"
						id="method" name="method" class="form-control" value="Paypal">
			     </div>
			
			<div class="form-group">
					<label for="intent">Intent</label> <input type="text" id="intent"
						name="intent" class="form-control" value="ORDER">
				 </div>
				 
				 <div class="form-group">
					<label for="description">Payment Description</label> <input
						type="text" id="description" name="description" class="form-control" value="Buy Goods">
				</div>
				
				<input type="submit" value="Continue to checkout" class="btn">
				
			</div>
			
		</form>
	</div>



</body>