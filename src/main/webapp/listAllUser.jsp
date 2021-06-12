<!DOCTYPE html>
<html lang="en">
<head>
<title>Display Users</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>List Of users</h3>
		<table class="table table-bordered">
			<caption>List of Users</caption>
			<thead>
				<tr>
					<th id="">S.No</th>
					<th id="name">Name</th>
					<th id="mobileNum">Mobile Number</th>
					<th id="email">E-mail id</th>
					<th id="address">Address</th>
					<th id="createdDate">Created Date</th>
					<th id="modifiedDate">Modified Date</th>
					<th id="username">User Name</th>
				</tr>
			</thead>
			<tbody id="user-table">
			</tbody>
		</table>
	</main>
	<script type="text/javascript">

function getAllUsers(){
	
	console.log("Fetching all Cases ");
	let url = "ListAllUserServlet";
	fetch(url).then(res=> res.json()).then(res=>{
		let users = res;
		console.log("Got response from servlet");
		console.log(users);
		let content = "";
		let serial=1;
		for(let userList of users){ 
			content += "<tr><td>"+serial+
			"</td><td>"+userList.name+
			"</td><td>"+userList.mobileNum+
			"</td><td>"+userList.email+
			"</td><td>"+userList.address+
			"</td><td>"+userList.createdDate+
			"</td><td>"+userList.modifiedDate+
			"</td><td>"+userList.userName+
			"</td></tr>";
			serial++;
		}
		console.log(content);
		document.querySelector("#user-table").innerHTML= content;
	});	
}
getAllUsers();
</script>
</body>
</html>