package in.casekartin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import in.casekartin.exception.ServiceException;
import in.casekartin.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(this.getClass().getName());
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		// get form values
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		HttpSession session = request.getSession();
		String message = null;
		Gson gson = new Gson();
		try {
			LoginService.isloginSuccess(userName, password,role);
			session.setAttribute("LOGGED_IN_USER", userName);
			session.setAttribute("ROLE", role);
			message = "true";

		} catch (ServiceException e) {
			message = e.getMessage();
		}
		String json = gson.toJson(message);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (IOException e) {
			logger.info(e.getMessage());
		}

	}

}
