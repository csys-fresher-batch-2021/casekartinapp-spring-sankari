
<!DOCTYPE html>
<html lang="en">
<head>
<title>Display Case Name</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>List Of Cases</h3>
		<table class="table table-bordered">
			<caption>List of Cases</caption>
			<thead>
				<tr>
					<th id="serialnumber">S.No</th>
					<th id="casename">Case Name</th>
					<th id="cost">Cost</th>
				</tr>
			</thead>
			<tbody id="case-table">
			</tbody>
		</table>

		<a href="addCaseTypes.jsp">Add Case Type</a>
	</main>
	<script type="text/javascript">

function getAllCases(){
	
	console.log("Fetching all Cases ");
	let url = "ListCasesServlet";
	fetch(url).then(res=> res.json()).then(res=>{
		let cases = res;
		console.log("Got response from servlet");
		console.log(cases);
		let content = "";
		let serial=1;
		for(let caselist of cases){ 
			content += "<tr><td>"+serial+
			"</td><td>"+caselist.caseType+
			"</td><td>Rs."+caselist.cost+
			"</td ><td><a class=\"btn btn-danger\"href=\"DeleteCaseTypeServlet?caseName="+
					caselist.caseType+"\">Delete</a></td></tr>";
			serial++;
		}
		console.log(content);
		document.querySelector("#case-table").innerHTML= content;
	});
}
getAllCases();
</script>
</body>
</html>