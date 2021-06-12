<!DOCTYPE html>
<html lang="en">
<head>
<title>Display Users</title>
<style>
.center {
  padding: 250px 0;
  text-align: center;
}
</style>
</head>
<%@page import="in.casekartin.model.CartManager" %>
<%@page import="java.util.List" %>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid" id="table">
					
					
	</main>
	<script type="text/javascript">		

		function getCartdetails(){
			let url = "ViewCartServlet";
			
			fetch(url).then(res=> res.json()).then(res=>{
				let cartDetails = res;
				console.log(cartDetails);
				let content = "";
				let serial=1;
				
				if(cartDetails.length==0){
					
					content="<p class=\"center\" style=\"text-align:center\">Your Cart is Empty</p>";
				}else{
					
					content+="<h3>List Of item</h3><table class=\"table table-bordered\" ><caption>List of item</caption><thead><tr><th >S.No</th>"+
					"<th >Order Id</th>"+
					"<th >Case Name</th>"+
					"<th >Mobile Brand</th>"+
					"<th >Mobile Model</th>"+
					"<th >Price</th>"+
					"<th >Number Of Cases</th>"+
					"</tr></thead><tbody>";
			for(let cartList of cartDetails){ 
					
					content += "<tr><td>"+serial+
					"</td><td>"+cartList.orderId+
					"</td><td>"+cartList.caseName+
					"</td><td>"+cartList.mobileBrand+
					"</td><td>"+cartList.mobileModel+
					"</td><td>Rs."+cartList.price+
					"</td><td>"+cartList.noOfCases+
					"</td><td><a class=\"btn btn-danger\"href=\"DeleteFromCartServlet?orderId="+
					cartList.orderId+"\">Cancel</a></td></tr>";
					serial++;
					
				}
				content+="</tbody></table>";
				}
				console.log(content);
				document.querySelector("#table").innerHTML= content;
			});	
		}
		getCartdetails();
	
	</script>
	
</body>
</html>