
<!DOCTYPE html>
<html lang="en">
<head>
<title>Add Case Name</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Add Case Type</h3>
		<form action="AddCaseTypeServlet">
			<label for="caseName">Case Name</label> <input type="text"
				name="caseName" id="caseName" placeholder="Enter case Name" pattern="[a-zA-Z0-9\s]{3,}" required autofocus />
			<br />
			<br /> <label for="price">Price</label> <input type="number"
				name="cost" id="cost" placeholder="Enter cost of case" step=".01"  min=1
				required autofocus /> <br />
			<br />
			<button type="submit" class="btn btn-info">Submit</button>
		</form>
		<%
		String message = (String) session.getAttribute("MESSAGE");
		if (message != null) {
		%>
		<p class="text-danger"><%=message%></p>
		<%
		session.removeAttribute("MESSAGE");}
		%>
	</main>
</body>
</html>