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
import in.casekartin.model.CartManager;
import in.casekartin.service.CartManagerService;

/**
 * Servlet implementation class BookingServlet
 */
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(this.getClass().getName());
	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String caseType=request.getParameter("caseType");
		String mobileBrand=request.getParameter("mobileBrand");	
		String mobileModel=request.getParameter("mobileModel");	
		String noOfCases=request.getParameter("noOfCases");	
		String price=request.getParameter("price");	
		String friendName=request.getParameter("friendName");
		HttpSession session=request.getSession(); 
		String userName= (String) session.getAttribute("LOGGED_IN_USER");
		CartManager cart=new CartManager();
		cart.setCaseName(caseType);
		cart.setMobileBrand(mobileBrand);
		cart.setMobileModel(mobileModel);
		cart.setFriendsName(friendName);
		try {
			cart.setPrice(Float.parseFloat(price));
			cart.setNoOfCases(Integer.parseInt(noOfCases));
		} catch (NumberFormatException e1) {
			logger.info(e1.getMessage());
			
		}
		
		Gson gson = new Gson();
		String message=null;
		String json=null;
		try {
			CartManagerService.addCartToBookedDetails(cart,userName);
			message="true";		
		} catch (ServiceException e) {
			message=e.getMessage();
		}
		json=gson.toJson(message);
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
