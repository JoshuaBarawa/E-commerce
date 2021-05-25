<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
  <link href="/css/signup.css" rel="stylesheet">
 
 
  <div class="center">
  <form:form action="/user/signedup" modelAttribute="customer" method="post" >
  
    <h2>Register</h2>
     <h5>An activation link will be sent to your email</h5>
   
  <div class="error"> <c:out value="${error}"></c:out></div> 
  
  <div class="form-group">
    <form:input path="userName" type="text" class="inp" placeholder="Enter UserName" name="name" required="kdkd"/>  
   </div>
   
    <div class="form-group">
    <form:input path="email" type="text" class="inp" placeholder="Enter Email" name="email" required="kdkd"/>
 </div>
 
 <div class="form-group">
    <form:input path="password" type="password" class="inp" placeholder="Enter Password" name="password" required="kdkd"/>
    </div>
  
    <input type="submit" value="Sign Up" class="registerbtn" />

  <div class="container signin">
    <p>Already have an account? <a href="/login">Sign in</a>.</p>
  </div>
</form:form> 

</div>