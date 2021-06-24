<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">
<link rel="stylesheet" href="assets/css/style.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script>
	let url="ListCasesServlet";
	let content = "";
	fetch(url).then(res=> res.json()).then(res=>{
	let caseTypes=res;
	console.log(caseTypes);
})
</script>
<%@page import="java.util.Set"%>
<%@page import="in.casekartin.service.CaseManagerService"%>
<%@page import="in.casekartin.model.CaseManager"%>
<%@page import=" java.util.HashSet"%>
<%
	String role= (String) session.getAttribute("ROLE");
	String userName= (String) session.getAttribute("LOGGED_IN_USER");
%>
<header>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<a class="navbar-brand" href="index.jsp">CaseKartinApp</a>
		<button class="navbar-toggler d-lg-none" type="button"
			data-toggle="collapse" data-target="#collapsibleNavId"
			aria-controls="collapsibleNavId" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavId">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home <span class="sr-only">(current)</span></a></li>

				<%  if (role != null && role.equals("admin")) { %>

				<li class="nav-item"><a class="nav-link" href="listCases.jsp">Cases</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="listMobiles.jsp">Mobiles</a>
				</li>
				<% } else if(role!=null){%>
				<li class="nav-item dropdown active"><a
					class="nav-link dropdown-toggle" href="index.jsp" id="dropdownId"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Custom
						Case</a>
					<div class="dropdown-menu" aria-labelledby="dropdownId">

						<% Set<CaseManager> caseTypes = (Set<CaseManager>) session.getAttribute("LIST_CASES"); 
   if (caseTypes!=null){ 
   for(CaseManager cases : caseTypes){ 
%>
						<a class="dropdown-item"
							href="caseBooking.jsp?caseType=<%=cases.getCaseType() %> &price=<%= cases.getCost() %>"><%=cases.getCaseType() %></a>
						<% }} }%>
					</div></li>
				<% if(role!=null && !role.equalsIgnoreCase("admin")){ %>

				<li class="nav-item"><a class="nav-link" href="viewCart.jsp">Cart</a>
				</li>
				<% } %>
			</ul>
			<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
				<% if (role == null && userName==null) { %>
				<li class="nav-item active"><a class="nav-link"
					href="login.jsp">Login</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="registration.jsp">Register</a></li>
				<% } else{ %>
				<li class="nav-item"><a class="nav-link" href="#">Welcome <%=userName%></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a>
				</li>
				<% } %>
				<% if(role!=null && role.equalsIgnoreCase("admin")){ %>
				<li class="nav-item"><a class="nav-link" href="listAllUser.jsp">AllUserList</a>
				</li>
				<% } if(role!=null && !role.equalsIgnoreCase("admin")){%>
				<li class="nav-item"><a class="nav-link" href="userDetails.jsp">Your
						Account</a></li>
				<% } %>
			</ul>
		</div>
	</nav>
</header>