<!DOCTYPE html>
<html>

<head>
	<title>Add Author</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-author-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Modern Library System</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add Author</h3>
		
		<form action="AuthorControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Author Name:</label></td>
						<td><input type="text" name="authorName" /></td>		
					</tr>
					<tr>
					    <td><label>Email:</label></td>
					    <td><input type="email" name="email" /></td>
					</tr>
					<tr>
					    <td><label></label></td>
					    <td><input type="submit" value="Save" class="save"></td>
					</tr>	
				</tbody>
			</table>
		</form>
		<div style="clear: both;"></div>
		
		<p>
			<a href="AuthorControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>