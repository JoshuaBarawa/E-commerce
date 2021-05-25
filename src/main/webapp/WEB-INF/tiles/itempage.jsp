<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <link href="/css/item.css" rel="stylesheet">

<center><div class="msg" ><c:out value="${msg}"/></div></center>
<div class="container bootdey">

<c:forEach var="product" items="${items}">
  <form action="/shopme/addtocart/${product.id}"  method="post">
<div class="col-md-12">
<section class="panel">
      <div class="panel-body">
          <div class="col-md-6">
              <div class="pro-img-details">
                  <img src="/shopme/display/${product.id}" alt="">
              </div>
          </div>
          <div class="col-md-6">
              <h4 class="pro-d-title"><c:out value="${product.name}"/></h4>
               
              <p><c:out value="${product.description}"/></p>
                  
              <div class="product_meta">
                  <span class="posted_in"> <strong>Category:</strong><c:out value="${product.categoryid.name}"/></span>
                 <span class="posted_in"> <strong>Brand:</strong><c:out value="${product.brand}"/></span>

              </div>
              <div class="m-bot15"> <strong>Price:</strong><span class="pro-price"><c:out value="${product.price}"/></span></div>
            
              <div class="form-group">
                  <label>Quantity</label>
                  <input type="number" name="quantity" value="1" class="form-control quantity"/>
              </div>
              <p>
                  <input class="btn btn-round btn-danger" type="submit" Value="Add to Cart"><i class="fa fa-shopping-cart"></i></input>
              </p>
              </form>
          </div>
      </div>
  </section>
  </div>
  </c:forEach>
  </div>
	
	