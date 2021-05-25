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
<div class="container">

<div class="error" style="color: red">

<c:out value="${mssg}"></c:out>

</div>

	<table id="cart" class="table table-hover table-condensed">
    				<thead>
						<tr>
							<th style="width:40%">Product</th>
							<th style="width:10%">Actions</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${products}" var="item">
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs"><img src="/shopme/display/${item.id}" alt="..." class="img-responsive"/></div>
									<div class="col-sm-10">
										<h4 class="nomargin">${item.name}</h4>
									</div>
								</div>
							</td>
			
							<td class="actions" data-th="Actions">
							    <a href="/shopme/updateproduct/${item.id}" >Edit</a>			
								<a href="/shopme/removeproduct/${item.id}" >Remove</a>	
										
							</td>
						</tr>
						</c:forEach>
						
						
					</tbody>
				
				</table>
</div>
