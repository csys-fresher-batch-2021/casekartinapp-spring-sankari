package in.casekartin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import in.casekartin.dto.Message;
import in.casekartin.exception.ServiceException;
import in.casekartin.service.LoginService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthController {

	
	@PostMapping("/LoginServlet")
	public ResponseEntity<?> login(HttpServletRequest request) {

		// get form values
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		HttpSession session = request.getSession();
		
		
		try {
			LoginService.isloginSuccess(userName, password,role);
			session.setAttribute("LOGGED_IN_USER", userName);
			session.setAttribute("ROLE", role);
			
			Message message = new Message();
			message.setInfoMessage("Successfully LoggedIn");
			
			return new ResponseEntity<>(message, HttpStatus.OK);

		} catch (ServiceException e) {
			
			Message message = new Message();
			message.setErrorMessage(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
			
	}
}
