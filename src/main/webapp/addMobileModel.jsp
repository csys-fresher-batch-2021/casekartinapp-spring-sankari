<!DOCTYPE html>
<html lang="en">
<head>
<title>Add Mobile Model</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Add Mobile Model</h3>
		<form onsubmit="addMobile()">
			<% String caseName=request.getParameter("caseType"); %>
			<label for="caseName">Case Name </label><input type="text"
				name="caseName" id="caseName" placeholder="Enter Case Name"
				pattern="[a-zA-Z0-9\s]{3,}" required /><br />
			<br /> <label for="mobileBrand">Mobile Brand *</label><input
				type="text" name="mobileBrand" id="mobileBrand"
				placeholder="Enter Mobile Brand" pattern="[a-zA-Z\s]{3,}" required /><br />
			<br /> <label for="mobileModel">Mobile Model *</label><input
				type="text" name="mobileModel" id="mobileModel"
				placeholder="Enter Mobile Model" pattern="[a-zA-Z0-9\s]{3,}"
				required /><br />
			<br /> <label for="noOfCases">No Of Cases *</label> <input
				type="number" name="noOfCases" id="noOfCases" min="1" required
				autofocus /><br />
			<br />
			<button type="submit" class="btn btn-primary">Submit</button>

		</form>
	</main>
	<script type="text/javascript">
function addMobile(){
	event.preventDefault();
	let mobileBrand=document.querySelector("#mobileBrand").value;
	let mobileModel=document.querySelector("#mobileModel").value;
	let noOfCases=document.querySelector("#noOfCases").value;
	let url="addMobileServlet";
	let data={"mobileBrand": mobileBrand,
			"mobileModel":mobileModel,
			"noOfCases":noOfCases};
	fetch(url,data).then(res=> res.json()).then(res=>{
		let message=res;
		window.location.href="listMobiles.jsp";
		
	}).catch(err=>{
		let data = err.response.data;
		alert(data.errorMessage);
	});
	
	
}
</script>
</body>
</html>