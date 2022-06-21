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
    <title>Book Lender Dashboard</title>
    <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <!-- For any Bootstrap that uses JS or jQuery-->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<header class="row">
			<div class="col-8">
				<p>Hello, <c:out value="${user.getName()}"/>. Welcome to...</p>
				<h1>The Book Broker!</h1>
			</div>
			<div class="col d-flex flex-column justify-content-around">
				<a class="text-end" href="/dashboard">Back to the Shelves</a>
			</div>
		</header>
		<div class="row">
			<p>Available Books to Borrow</p>
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Title</th>
						<th scope="col">Author Name</th>
						<th scope="col">Owner</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${books}">
						<c:if test="${book.getBorrower() == null}">
							<tr>
								<th scope="row"><c:out value="${book.getId()}"/></th>
								<td><a href=""><c:out value="${book.getTitle()}"/></a></td>
								<td><c:out value="${book.getAuthor()}"/></td>
								<td><c:out value="${book.getUser().getName()}"/></td>
								<td>
									<c:choose>
										<c:when test="${book.getUser() != user}">
											<form:form action="/borrow/${book.getId()}" method="post">
												<input type="hidden" name="_method" value="put"/>
												<input type="submit" value="borrow" class="a-btn"/>
											</form:form>
										</c:when>
										<c:otherwise>
											<div class="row d-flex">
												<a class="col-3" href="/books/${book.getId()}/edit">edit</a>
												<form class="col" action="/books/${book.getId()}" method="post">
													<input type="hidden" name="_method" value="delete"/>
													<input class="a-btn" type="submit" value="delete"/>
												</form>
											</div>
										</c:otherwise>
									</c:choose>
								</td>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<p>Books I'm Borrowing...</p>
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Title</th>
						<th scope="col">Author Name</th>
						<th scope="col">Owner</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${books}">
						<c:if test="${book.getBorrower() == user}">
							<tr>
								<th scope="row"><c:out value="${book.getId()}"/></th>
								<td><a href=""><c:out value="${book.getTitle()}"/></a></td>
								<td><c:out value="${book.getAuthor()}"/></td>
								<td><c:out value="${book.getUser().getName()}"/></td>
								<td>
									<form:form action="/borrow/${book.getId()}" method="post">
										<input type="hidden" name="_method" value="put"/>
										<input type="submit" value="return" class="a-btn"/>
									</form:form>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>