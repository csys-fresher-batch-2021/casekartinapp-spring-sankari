<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js">
</script>
<title>Login</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>CaseKartin</h3>
		<form onsubmit="login()">
			<label for="userName">user name</label> <input type="text"
				id="userName" name="userName" placeholder="User Name" required
				autofocus /> <br /> <br /> <label for="password">Password</label>
			<input type="password" id="password" name="password"
				placeholder="Password" required autofocus /> <br /> <br /> <input
				type="radio" name="role" id="role" value="admin" required><label
				for="admin">Admin</label> <input type="radio" name="role" id="role"
				value="user" required><label for="user">User</label> <br />
			<br />
			<button class="btn btn-info" type="submit">Login</button>
			<button type="reset" class="btn btn-info">Reset</button>
		</form>
	</main>
	<script type="text/javascript">
		function login(){
	event.preventDefault();
	let userName=document.querySelector("#userName").value;
	let password=document.querySelector("#password").value;
	let roles=document.querySelectorAll("#role");
	let selectedRole ;
	roles.forEach(obj=> {
		if(obj.checked){
			selectedRole = obj.value;
		
		}
	});
	console.log(selectedRole);
	let url="LoginServlet";
	let data = {"userName":userName,
			      "password":password,
			      "role":selectedRole} //JSON Object => input
	console.log(data);
	axios.post(url,data).then(res=> {
		
		let data = res.data;
		alert(data.infoMessage);
		window.location.href="index.jsp";
		}
	).catch(err=>{
		let data = err.response.data;
		alert(data.errorMessage);
	});
}
	</script>
</body>
</html>