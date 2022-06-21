<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit a Book</title>
    <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <!-- For any Bootstrap that uses JS or jQuery-->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
   <div class="container">
   	<header class="row d-flex align-items-center">
   		<h1 class="col-8">Change your Entry</h1>
   		<div class="col text-end">
   			<a href="/dashboard">Back to the Shelves</a>
   		</div>
   	</header>
   	<div class="row">
   		<form:form id="edit-form" action="/books/${editBook.getId()}" method="post" modelAttribute="editBook">
   			<input type="hidden" name="_method" value="put"/>
   			<div class="mb-3">
   				<form:label class="form-label" path="title">Title</form:label>
   				<form:input class="form-control" path="title"/>
   				<form:errors class="text-danger" path="title"/>
   			</div>
   			<div class="mb-3">
   				<form:label class="form-label" path="author">Author</form:label>
   				<form:input class="form-control" path="author"/>
   				<form:errors class="text-danger" path="author"/>
   			</div>
   			<div class="mb-3">
   				<form:label class="form-label" path="thought">My thoughts</form:label>
   				<form:textarea class="form-control" path="thought"></form:textarea>
   				<form:errors class="text-danger" path="thought"/>
   			</div>
   		</form:form>
  		<div class="d-flex">
   			<button form="edit-form" type="submit" class="col w-50 btn btn-outline-primary">Submit</button>
   			<form:form id="delete-form" class="col" action="/books/${editBook.getId()}" method="post">
				<input type="hidden" name="_method" value="delete"/>
				<button form="delete-form" type="submit" class="w-100 btn btn-outline-danger">Delete</button>
			</form:form>
  		</div>
   	</div>
   </div>
</body>
</html>