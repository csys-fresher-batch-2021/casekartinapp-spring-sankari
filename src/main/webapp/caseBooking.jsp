<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js">
</script>
<meta charset="ISO-8859-1">
<title>Booking</title>
</head>
<body >
	<%
	String userName = (String) session.getAttribute("LOGGED_IN_USER");
	if (userName == null) {
		response.sendRedirect("login.jsp");
	}
	String caseName = request.getParameter("caseType");
	String cost = request.getParameter("price");
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="containe-fluid">
		<form onsubmit="booking()" method="post" >
			<br />

			<label for="caseType">Case Type</label> <input type="text"
				name="caseType" id="caseType" value="<%=caseName%>" readonly /><br />
			<br /> <label for="price">Price</label> <input type="text"
				name="price" id="price" value="<%=cost%>" readonly /><br /> <br />
			<br /> <label for="mobileBrand">Select Mobile Brand *</label> <select
				name="mobileBrand" id="mobileBrand" onchange="mobileModels()"required>	
			</select><br /> <br /> <br /> <label for="mobileModel">Select
				Mobile Model *</label> <select name="mobileModel" id="mobileModel" required disabled>		
			</select><br /> <br /> <br>
			<%
			if (caseName != null &&  caseName.trim().equalsIgnoreCase("Friends Name Tag Custom Case")) {
			%>
			<label for="friendName">Friend's Name *</label> <input type="text"
				name="friendName" id="friendName" placeholder="Enter Friend's Name"
				required autofocus /> <br />
			<%
			}else{ %>
			<span id="friendName"></span>
			<% }%>
			<br /> <label for="noOfCases">No Of Cases *</label> <input
				type="number" name="noOfCases" id="noOfCases" min="1" required
				autofocus /><br /> <br /> <br />
			<button type="submit" class="btn btn-primary">Add to Cart</button>
		</form>
	</main>
	<script src="js/casekartin.js"></script>
</body>
</html>