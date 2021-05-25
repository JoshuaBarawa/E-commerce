<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <link href="/css/products.css" rel="stylesheet">
   
 <div class="container">
    <h3>Choose Product Category</h3>   
    
 <div class="error" style="color: green">
<c:out value="${mssg}"></c:out>
</div>    
       
             <c:forEach var="category" items="${categories}">
            <a href="/shopme/adminproducts/${category.id}">
          <div class="col-xl-3 col-lg-3 col-md-6 col-sm-6">
          
                    <div class="single-product">
                        <div class="product-thumb">
                         <img src="/shopme/displayimage/${category.id}">
                        </div>
                        <div class="product-title">
                           <c:out value="${category.name}"/>
                        </div>
                    </div>
                     
                 </div>
                </a>
               </c:forEach>

        </div>


