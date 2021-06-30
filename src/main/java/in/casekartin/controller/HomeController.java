package in.casekartin.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import in.casekartin.exception.ServiceException;
import in.casekartin.service.CartManagerService;
import in.casekartin.service.CaseManagerService;
import in.casekartin.service.MobileManagerService;

@Controller
public class HomeController {
	final Logger logger = Logger.getLogger(this.getClass().getName());

	// http://localhost:9000/
	@GetMapping
	public String home() {
		return "index.jsp";
	}

	@GetMapping("/LogoutServlet")
	public String logout(HttpSession session) {
		session.removeAttribute("LOGGED_IN_USER");
		session.removeAttribute("ROLE");
		return "index.jsp";
	}

	@GetMapping("/AddCaseTypeServlet")
	public String addCase(HttpServletRequest request) {

		// get the form value
		String caseName = request.getParameter("caseName");
		String cost = request.getParameter("cost");
		HttpSession session = request.getSession();
		try {
			CaseManagerService.addCaseType(caseName, cost);
			return "listCases.jsp";
		} catch (ServiceException e) {
			String message = e.getMessage();
			session.setAttribute("MESSAGE", message);
			return "addCaseTypes.jsp";
		}
	}

	@GetMapping("/DeleteCaseTypeServlet")
	public String removeCases(HttpServletRequest request) {

		String caseName = request.getParameter("caseName");
		try {
			CaseManagerService.deleteCaseType(caseName);
			return "listCases.jsp";
		} catch (ServiceException e) {
			return "DeleteCaseTypeServlet";
		}

	}

	@GetMapping("/DeleteFromCartServlet")
	public String removeFromCart(HttpServletRequest request) {

		String orderId = request.getParameter("orderId");
		try {
			CartManagerService.cancelOrder(orderId);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "viewCart.jsp";
	}

	@GetMapping("/DecreaseNoOfCasesServlet")
	public String reduceCase(HttpServletRequest request) {
		int mobileId = 0;
		try {
			mobileId = Integer.parseInt(request.getParameter("mobileId"));
		} catch (NumberFormatException e) {
			logger.info(e.getMessage());
		}
		MobileManagerService.reduceCases(mobileId);
		return "listMobiles.jsp";

	}

	@GetMapping("/IncreaseNoOfCasesServlet")
	public String increaseCase(HttpServletRequest request) {
		int mobileId = 0;
		try {
			mobileId = Integer.parseInt(request.getParameter("mobileId"));
			MobileManagerService.increaseCases(mobileId);

		} catch (NumberFormatException e) {
			logger.info(e.getMessage());
		}

		return "listMobiles.jsp";

	}

}
