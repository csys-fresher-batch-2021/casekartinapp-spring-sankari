/**
 * 
 */
 
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
	
function booking(){
		event.preventDefault();
		let caseType=document.querySelector("#caseType").value;
		let cost=document.querySelector("#price").value;
		let friendName=document.querySelector("#friendName").value;
		let mobileBrand=document.querySelector("#mobileBrand").value;
		let mobileModel=document.querySelector("#mobileModel").value;
		let noOfCases=document.querySelector("#noOfCases").value;
		let url = "BookingServlet";
		let data={"caseName" : caseType,
				"price" : cost,
				"mobileBrand": mobileBrand, 
				"mobileModel" : mobileModel, 
				"noOfCases" : noOfCases,
				"friendName" : friendName}
		axios.post(url,data).then(res=>{  
			let data=res.data;
			alert(data.infoMessage);
			window.location.href="viewCart.jsp";
		}).catch(err=>{
			let data = err.response.data;
			alert(data.errorMessage);
			
		});
	}
	function mobileBrands(){
		let caseName=document.querySelector("#caseType").value;
		let url="ListMobileBrands?caseName="+caseName;
		fetch(url).then(res=> res.json()).then(res=>{  
			let data=res;
			let content= "<option value='' disabled selected hidden>Choose a Mobile Brand</option>";
			for(let obj of data){
			 content+= "<option value='"+ obj.mobileBrand + "'>" + obj.mobileBrand + "</option>";
			}
			document.querySelector("#mobileBrand").innerHTML= content;
			
			
		});
	}
	mobileBrands();
	function mobileModels(){	
	event.preventDefault();
	document.querySelector("#mobileModel").disabled=false;
	
		let caseName=document.querySelector("#caseType").value;
		let mobileBrand=document.querySelector("#mobileBrand").value;
		let url="ListMobileModels?caseName="+caseName+"&mobileBrand="+mobileBrand;
		fetch(url).then(res=> res.json()).then(res=>{  
			let data=res;
			let content= "<option value='' disabled selected hidden>Choose a MobileModel</option>";
			for(let obj of data){
			 content+= "<option value='"+ obj.mobileModel + "'>" + obj.mobileModel + "</option>";
			}
			document.querySelector("#mobileModel").innerHTML= content;
			
		});
	}
 

