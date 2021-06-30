<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<title>Registration</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Registration</h3>
		<form onsubmit="register()">
			<label for="name">Name</label> <input type="text" name="name"
				id="name" placeholder="Enter Name" pattern="[a-zA-Z.\s]{1,50}"
				required autofocus /> <br /> <br /> <label for="email">E-mail
				id</label> <input type="text" name="email" id="email"
				placeholder="Enter mail Id" pattern="[a-z0-9@.]{1,50}" required
				autofocus /> <br /> <br /> <label for="mobileNum">Mobile
				Number</label> <input type="text" name="mobileNum" id="mobileNum"
				placeholder="Enter mobile number" maxlength="10" size="10" required
				autofocus /> <br /> <br /> <label for="address">Address</label> <input
				type="text" name="address" id="address" placeholder="Enter address"
				pattern="[a-zA-Z0-9/-,\s]{1,}" required autofocus /> <br /> <br />
			<label for="userName">User Name</label> <input type="text"
				id="userName" name="userName" placeholder="User Name"
				pattern="[a-zA-Z0-9]{5,10}" required autofocus /> <br /> <br /> <label
				for="password"></label>password<input type="password" id="password"
				name="password" placeholder="Password"
				pattern="[a-zA-Z0-9*&$#@!]{5,10}" required autofocus /> <br />
			<br />
			<button type="submit" class="btn btn-info">Submit</button>
		</form>
		<script src="js/registration.js"> </script>
	</main>
</body>
</html>