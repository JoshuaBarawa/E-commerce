 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" />
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link href="/css/cart.css" rel="stylesheet">
<div class="container">
	<table id="cart" class="table table-hover table-condensed">
    				<thead>
						<tr>
							<th style="width:50%">Product</th>
							<th style="width:10%">Price</th>
							<th style="width:8%">Quantity</th>
							<th style="width:22%" class="text-center">SubTotal</th>
							<th style="width:10%">Actions</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${cartitems}" var="item">
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs"><img src="/shopme/display/${item.product.id}" alt="..." class="img-responsive"/></div>
									<div class="col-sm-10">
										<h5 class="nomargin">${item.product.name}</h5>
									</div>
								</div>
							</td>
							<td data-th="Price">${item.product.price}</td>
							<td data-th="Quantity"><c:out value="${item.quantity}"/></td>
							
							<td data-th="Subtotal" name="" class="text-center"><c:out value="${item.product.price * item.quantity}"/></td>
							<td class="actions" data-th="Actions">
								<a href="/shopme/remove/${item.id}" >Remove</a>					
							</td>
						</tr>
						</c:forEach>
						
						
					</tbody>
				
					
					<tfoot>
						<tr>
		
						<td><a href="/shopme/products/${id}" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
								<td colspan="2" class="hidden-xs"></td>
	                  <td class="hidden-xs text-center"><strong><span>Total Cost:</span><c:out value="${total}" /></strong></td>
                         
					<td><a href="/checkout" class="btn btn-success btn-block"> Checkout <i class="fa fa-angle-right"></i></a></td>
							
						</tr>
						
					</tfoot>
				</table>
</div>