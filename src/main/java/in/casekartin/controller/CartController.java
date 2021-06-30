package in.casekartin.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.casekartin.dto.Message;
import in.casekartin.exception.ServiceException;
import in.casekartin.model.CartManager;
import in.casekartin.service.CartManagerService;
import in.casekartin.service.MobileManagerService;

@RestController
public class CartController {
	final Logger logger = Logger.getLogger(this.getClass().getName());

	@GetMapping("/ListMobileBrands")
	public List<CartManager> getAllMobileBrands(@RequestParam("caseName") String caseName) {
		List<CartManager> listMobileBrands = null;
		try {
			listMobileBrands = MobileManagerService.getAllMobileBrands(caseName);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return listMobileBrands;

	}

	@GetMapping("/ListMobileModels")
	public List<CartManager> getAllMobileModels(@RequestParam("caseName") String caseName,
			@RequestParam("mobileBrand") String mobileBrand) {
		List<CartManager> listMobileModels = null;
		try {
			listMobileModels = MobileManagerService.getAllMobileModels(caseName, mobileBrand);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return listMobileModels;
	}

	@PostMapping("/BookingServlet")
	public ResponseEntity<?> saveToCart(@RequestBody CartManager data, HttpSession session) {
		String userName = (String) session.getAttribute("LOGGED_IN_USER");
		try {
			CartManagerService.addCartToBookedDetails(data, userName);
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
			cart = CartManagerService.listByUserName(userName);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return cart;
	}

}
