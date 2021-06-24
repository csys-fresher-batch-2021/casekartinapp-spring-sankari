package in.casekartin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.casekartin.exception.ServiceException;
import in.casekartin.model.RegisterManager;
import in.casekartin.service.RegisterManagerService;

@RestController
public class UserController {
	@GetMapping("/ListAllUserServlet")
	public List<RegisterManager> getAllUsers() {
		List<RegisterManager> userDetails = null;
		try {
			userDetails = RegisterManagerService.listAllDetails();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return userDetails;		
	}
	
	@GetMapping("/ListUserServlet")
	public RegisterManager getUserByUserName(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		RegisterManager user = null;
		try {
			user=RegisterManagerService.listByUserName(userName);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return user;
	}

}
