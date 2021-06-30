package in.casekartin.controller;

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

@RestController
public class AuthController {

//	@Autowired
//	UserService userService;

	@PostMapping("LoginServlet")
	public ResponseEntity<?> login(@RequestBody RegisterManager data, HttpSession session) {

		try {
			System.out.println(data);
			LoginService.isloginSuccess(data.getUserName(), data.getPassword(), data.getRole());
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

	/*
	 * @PostMapping("/RegistrationServlet") public ResponseEntity<?>
	 * saveRegisterDetails(@RequestBody UserDTO data) {
	 * 
	 * RegisterManager userDetails = new RegisterManager(data.getName(),
	 * data.getUserName(), data.getPassword(), data.getMobileNum(), data.getEmail(),
	 * data.getAddress()); try {
	 * 
	 * userService.addRegDetails(userDetails); Message message = new Message();
	 * message.setInfoMessage("Successfully Registered"); return new
	 * ResponseEntity<>(message, HttpStatus.OK); } catch (Exception e) { Message
	 * message = new Message(); message.setErrorMessage(e.getMessage()); return new
	 * ResponseEntity<>(message, HttpStatus.BAD_REQUEST); }
	 * 
	 * }
	 */

}
