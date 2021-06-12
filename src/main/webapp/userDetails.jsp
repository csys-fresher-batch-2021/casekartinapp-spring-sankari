<%@page import="in.casekartin.model.RegisterManager"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>User Details</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Your Account</h3>
		<%		
		String userName = (String) session.getAttribute("LOGGED_IN_USER");
		%>
		<form >			
				<label for="name"> Name </label><input type="text" name="name" id="name" value=""
				readonly required /><br /><br/>
				<label for="email"> E-mail id </label><input type="text" name="email" id="email" value=""
				readonly required /><br /><br/>
				<label for="mobileNum"> Mobile Number </label><input type="text" name="mobileNum" id="mobileNum" value=""
				readonly required /><br /><br/>
				<label for="address"> Address </label><input type="text" name="address" id="address" value=""
				readonly required /><br /><br/>
				<label for="userName"> User Name </label><input type="text" name="userName" id="userName" value=""
				readonly required /><br /><br/>
				
		</form>
	</main>
	<script type="text/javascript">
	
	function getUserdetails(){
		let url = "ListUserServlet?userName=<%=userName%>";
		fetch(url).then(res=>res.json()).then(res=>{
			let userDetails = res;
			document.querySelector("#name").value = userDetails.name;
			document.querySelector("#email").value = userDetails.email;
			document.querySelector("#mobileNum").value = userDetails.mobileNum;
			document.querySelector("#address").value = userDetails.address;
			document.querySelector("#userName").value = userDetails.userName;
			
		});
	}
	getUserdetails();
	
	</script>
	
</body>
</html>