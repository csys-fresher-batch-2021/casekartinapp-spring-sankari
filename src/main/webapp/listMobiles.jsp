
<!DOCTYPE html>
<html lang="en">
<head>
<title>Display Case Name</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>List Of Mobiles</h3>
		<table class="table table-bordered">
			<caption>List of Mobiles</caption>
			<thead>
				<tr>
					<th id="serialnumber">S.No</th>
					<th id="caseName">Case Name</th>
					<th id="mobileBrand">Mobile Brand</th>
					<th id="mobileModel">Mobile Model</th>
					<th></th>
					<th id="noOfCases">No Of Cases</th>
				</tr>
			</thead>
			<tbody id="mobile-table">
			</tbody>
		</table>

		<a href="addMobileModel.jsp">Add Mobile</a>
	</main>
	<script type="text/javascript">

function getAllMobiles(){

	let url = "ListMobilesServlet";
	fetch(url).then(res=> res.json()).then(res=>{
		let mobiles = res;
		console.log("Got response from servlet");
		console.log(mobiles);
		let content = "";
		let serial=1;
		for(let mobileList of mobiles){ 
			content += "<tr><td>"+serial+
			"</td><td>"+mobileList.caseName+
			"</td><td>"+mobileList.mobileBrand+
			"</td><td>"+mobileList.mobileModel+
			"</td><td><a class=\"btn btn-primary\"href=\"DecreaseNoOfCasesServlet?mobileId="+
			mobileList.mobileId+"\">-</a>"+
			"</td><td>"+mobileList.noOfCases+
			"</td><td><a class=\"btn btn-primary\"href=\"IncreaseNoOfCasesServlet?mobileId="+
			mobileList.mobileId+"\">+</a></td></tr>";
			serial++;
		}
		console.log(content);
		document.querySelector("#mobile-table").innerHTML= content;
	});
}
getAllMobiles();
</script>
</body>
</html>