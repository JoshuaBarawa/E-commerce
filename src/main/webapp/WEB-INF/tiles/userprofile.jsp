<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
 <link href="/css/userprofile.css" rel="stylesheet">
<div class="center">
  <form:form action="/shopme/updateaccount" modelAttribute="customer" method="post" >
 
 
   <h3>Update Your User Account Information</h3> 
  
  <div class="form-group">
    <label for="userName"><b>UserName</b></label>
    <form:input path="userName" type="text" class="form-control" name="name" required="kdkd" />  
   </div>
   
    <div class="form-group">
    <label for="email"><b>Email</b></label>
    <form:input path="email" type="text" class="form-control" name="email" required="kdkd" />
 </div>
 
 <div class="form-group">
    <label for="password"><b> Change Password</b></label>
    <form:input path="password" type="password" class="form-control" name="password" required="kdkd" />
    </div>
   

    <input type="submit" value="Update" class="registerbtn" />
  
</form:form> 
</div>
