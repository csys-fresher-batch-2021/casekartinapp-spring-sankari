package in.casekartin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.casekartin.exception.ServiceException;
import in.casekartin.model.RegisterManager;
import in.casekartin.service.RegisterManagerService;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		String message=null;
		Gson gson = new Gson();
		try {
			RegisterManagerService.addRegDetails(regDetails);
			message="true";
		} catch (ServiceException e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		String json = gson.toJson(message);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

}
