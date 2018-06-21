<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>

<head>
<title>Modern Library System - Author Management</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Modern Library System - Author Management</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">
		
		    <input type="button" value="Add Author"
		           onclick="window.location.href='add-author-form.jsp'; return false;"
		           class="add-author-button"
		    />

			<table>

				<tr>
					<th>Author Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>

				<c:forEach var="tempAuthor" items="${AUTHOR_LIST}">
                      <!--  set up a link for each author -->
					<c:url var="tempLink" value="AuthorControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="authorId" value="${tempAuthor.id}" />
					</c:url>
					
					  <!--  set up a link for each author -->
					<c:url var="deleteLink" value="AuthorControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="authorId" value="${tempAuthor.id}" />
					</c:url>
					
					<tr>			
						<td> ${tempAuthor.authorName} </td>
						<td> ${tempAuthor.email} </td>
						<td> 
							<a href="${tempLink}">Update</a>
							 |	
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this author?'))) return false">
							Delete</a>
							
							
					   </td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>

</html>