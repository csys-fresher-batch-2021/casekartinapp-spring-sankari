/**
 * 
 */
function register(){
	event.preventDefault();
	let name=document.querySelector("#name").value;
	let email=document.querySelector("#email").value;
	let mobileNum=document.querySelector("#mobileNum").value;
	let address=document.querySelector("#address").value;
	let userName=document.querySelector("#userName").value;
	let password=document.querySelector("#password").value;
	console.log(name);
	let data ={ "name" : name,
	  			"email=" : email,
	  			"mobileNum" : mobileNum,
	  			"address" : address,
	  			"userName" : userName,
	  			"password" :password};
	let url = "RegistrationServlet";	
	axios.post(url,data).then(res=> {
		let data=res.data;
		console.log(data);
		alert(data.infoMessage);
		window.location.href="index.jsp";
	}
	).catch(err=>{
		let data = err.response.data;
		alert(data.errorMessage);
	});
}
