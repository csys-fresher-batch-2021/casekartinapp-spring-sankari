<!DOCTYPE html>
<html lang="en">
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<title>Registration</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Registration</h3>
		<form onsubmit="register()">
			<label for="name">Name</label> <input type="text" name="name"
				id="name" placeholder="Enter Name" pattern="[a-zA-Z.\s]{1,50}" required
				autofocus /> <br /> <br /> <label for="email">E-mail id</label> <input
				type="text" name="email" id="email" placeholder="Enter mail Id"
				pattern="[a-z0-9@.]{1,50}" required autofocus /> <br /> <br /> <label
				for="mobileNum">Mobile Number</label> <input type="text"
				name="mobileNum" id="mobileNum" placeholder="Enter mobile number" maxlength="10"
				size="10" required autofocus /> <br /> <br /> <label
				for="address">Address</label> <input type="text" name="address"
				id="address" placeholder="Enter address" pattern="[a-zA-Z0-9/-,\s]{1,}"required autofocus /> <br />
			<br /> <label for="userName">User Name</label> <input type="text"
				id="userName" name="userName" placeholder="User Name"
				pattern="[a-zA-Z0-9]{5,10}" required autofocus /> <br /> <br /> <label
				for="password"></label>password<input type="password" id="password"
				name="password" placeholder="Password"
				pattern="[a-zA-Z0-9*&$#@!]{5,10}" required autofocus /> <br /><br />
			<button type="submit" class="btn btn-info">Submit</button>
		</form>
<script type="text/javascript">
function register(){
	event.preventDefault();
	let name=document.querySelector("#name").value;
	let email=document.querySelector("#email").value;
	let mobileNum=document.querySelector("#mobileNum").value;
	let address=document.querySelector("#address").value;
	let userName=document.querySelector("#userName").value;
	let password=document.querySelector("#password").value;
	const queryParameter = "?name=" + name + "&email=" + email +"&mobileNum=" + mobileNum +"&address=" + address +"&userName=" + userName +"&password="+password;
	let url = "RegistrationServlet"+queryParameter;	
	const data={};
	axios.post(url,data).then(res=> {
		let message=res.data;
		if(message=="true")
		{
		alert("Registration Success");
		window.location.href="index.jsp";
		}
	else
		{
		alert(message);
		}
		
		}
		);
}
</script>
	</main>
</body>
</html>