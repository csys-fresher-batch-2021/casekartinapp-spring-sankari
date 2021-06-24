package in.casekartin.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.casekartin.dto.Message;
import in.casekartin.exception.ServiceException;
import in.casekartin.model.CartManager;
import in.casekartin.service.CartManagerService;

@RestController
public class CartController {
	final Logger logger = Logger.getLogger(this.getClass().getName());
	@GetMapping("/BookingServlet")
	public ResponseEntity<?> saveToCart(HttpServletRequest request) {
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
		try {
			CartManagerService.addCartToBookedDetails(cart,userName);
			Message message = new Message();
			message.setInfoMessage("Successfully Added to Cart");
			
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (ServiceException e) {
			Message message = new Message();
			message.setErrorMessage(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/ViewCartServlet")
	public List<CartManager> getCartDetails(HttpSession session) {
		String userName = (String) session.getAttribute("LOGGED_IN_USER");
		List<CartManager> cart = null;
		try {
			cart=CartManagerService.listByUserName(userName);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return cart;
	}

}
