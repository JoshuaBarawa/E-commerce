<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
 <link href="/css/login.css" rel="stylesheet">

 <div class="center">
 
  <form action="/login" method="post">
 
   <div class="msg"> <c:out value="${msg}"></c:out>    </div> 

   <c:if test="${param.error != null }">
<div class="login-error">Invalid username or password</div>
</c:if>
    <h1>Login</h1>
   
    <div class="form-group">
   
    <input type="text" placeholder="Enter Email" name="username" class="inp" id="username" required>
</div>

  <div class="form-group">
   
    <input type="password" placeholder="Enter Password" name="password" class="inp" id="password" required >
  
    </div>
    
    <div class="link" >
    <input type="submit" value="Login" class="registerbtn"/>
    <a href="/user/resetpassword" class="fp">Forgot Password?</a>
     </div>
    
 
 
</form>

<div class="container signin">
    <p>Don't have an account? <a href="/user/signup">Sign Up</a>.</p>
  </div> 
 </div>
