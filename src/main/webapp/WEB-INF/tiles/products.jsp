<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <link href="/css/products.css" rel="stylesheet">
   
 <div class="container">
        
            <c:forEach var="product" items="${products}">
            <a href="/shopme/viewitem/${product.id}">
          <div class="col-xl-3 col-lg-3 col-md-6 col-sm-6">
          
                    <div class="single-product">
                        <div class="product-thumb">
                         <img src="/shopme/display/${product.id}">
                        </div>
                        <div class="product-title">
                           <c:out value="${product.name}"/>
                        </div>
                        <div class="product-btns">
                            <div class="tag mr-2">$<c:out value="${product.price}"/></div>
                        
                        </div>
                    </div>
                     
                 </div>
                </a>
               </c:forEach>

        </div>


