<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container">
	<form:form action="/shopme/addedcategory" modelAttribute="category"
		method="post" enctype="multipart/form-data">

		<div class="form-group">
			<label for="name">Name:</label>
			<form:input path="name" type="text" class="form-control" id="usr" required="kdkd" />
		</div>

		<div class="form-group">
			<label for="name">Upload Image:</label>
			<form:input path="photo" class="form-control" type="file" name="photo"
				accept="image/jpeg,image/jpg,image/gif" required="kdkd"/>
		</div>

		<input type="submit" value="Add">
	</form:form>
</div>
