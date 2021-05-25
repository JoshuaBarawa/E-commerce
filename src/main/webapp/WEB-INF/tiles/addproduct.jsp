<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	<form:form action="/shopme/addedproduct" modelAttribute="product"
		method="post" enctype="multipart/form-data">

		<div class="form-group">
			<label for="image">Image</label>
			<form:input path="photo" type="file" class="form-control"
				name="photo" accept="image/jpeg,image/jpg,image/gif,/image/png" required="kdkd" />
		</div>
		<div class="form-group">
			<label for="name">Product Name:</label>
			<form:input path="name" type="text" class="form-control" name="name" required="kdkd"/>
		</div>


		<div class="form-group">
			<label for="desc">Description:</label>
			<form:textarea path="description" type="text" class="form-control"
				rows="5" name="description" required="kdkd"/>
		</div>

		<div class="form-group">
			<label for="category">Category</label>
			<form:select path="categoryid" class="form-control" id="categoryid"
				name="categoryid">
				<c:forEach var="category" items="${categories}">
					<option value="${category.id}"><c:out
							value="${category.name}"></c:out></option>
				</c:forEach>
			</form:select>
		</div>

		<div class="form-group">
			<label for="brand">Brand</label>
			<form:input path="brand" type="text" class="form-control"
				name="brand" required="kdkd" />
		</div>

		<div class="form-group">
			<label for="price">Price</label>
			<form:input path="price" type="text" class="form-control"
				name="price" required="kdkd" />
		</div>

		<input type="submit" value="Add">
	</form:form>
</div>
