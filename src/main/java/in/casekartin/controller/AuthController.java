package in.casekartin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.casekartin.dto.Message;
import in.casekartin.exception.ServiceException;
import in.casekartin.model.RegisterManager;
import in.casekartin.service.LoginService;
import in.casekartin.service.RegisterManagerService;

@RestController
public class AuthController {

	
	@PostMapping("/LoginServlet")
	public ResponseEntity<?> login(@RequestBody RegisterManager data,HttpSession session) {
		
		
		try {
			System.out.println("controller"+data.getUserName());
			LoginService.isloginSuccess(data.getUserName(), data.getPassword(),data.getRole());
			session.setAttribute("LOGGED_IN_USER", data.getUserName());
			session.setAttribute("ROLE", data.getRole());
			
			Message message = new Message();
			message.setInfoMessage("Successfully LoggedIn");
			
			return new ResponseEntity<>(message, HttpStatus.OK);

		} catch (ServiceException e) {
			
			Message message = new Message();
			message.setErrorMessage(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
			
	}
	@PostMapping("/RegistrationServlet")
	public ResponseEntity<?> saveRegisterDetails(HttpServletRequest request) {
		// get form values
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String mobileNum =request.getParameter("mobileNum");
				String address = request.getParameter("address");		
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				//create array list to carry registration info 
				RegisterManager regDetails=new RegisterManager();
				regDetails.setName(name);
				regDetails.setUserName(userName);
				regDetails.setPassword(password);
				regDetails.setMobileNum(mobileNum);
				regDetails.setEmail(email);
				regDetails.setAddress(address);
			try {
				RegisterManagerService.addRegDetails(regDetails);
				Message message = new Message();
				message.setInfoMessage("Successfully Registered");
					
				return new ResponseEntity<>(message, HttpStatus.OK);
			} catch (ServiceException e) {
				Message message = new Message();
				message.setErrorMessage(e.getMessage());
				return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);				}

	}
	

	
}
