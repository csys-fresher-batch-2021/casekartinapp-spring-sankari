package in.casekartin.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.casekartin.dto.Message;
import in.casekartin.exception.DBException;
import in.casekartin.exception.ServiceException;
import in.casekartin.model.CartManager;
import in.casekartin.model.CaseManager;
import in.casekartin.service.CaseManagerService;
import in.casekartin.service.MobileManagerService;

@RestController

public class CaseController {

	@GetMapping("/ListCasesServlet")
	public Set<CaseManager> getAllCases(HttpSession session) {
		Set<CaseManager> caseTypes = null;
		try {
			caseTypes = CaseManagerService.getActiveCaseTypes();
			session.setAttribute("LIST_CASES", caseTypes);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return caseTypes;

	}

	@PostMapping("/addMobileServlet")
	public ResponseEntity<?> addMobiles(@RequestBody CartManager params) {
		try {
			MobileManagerService.addMobiles(params);
			Message message = new Message();
			message.setInfoMessage("Successfully Added mobiles");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (ServiceException | DBException e) {
			Message message = new Message();
			message.setErrorMessage(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/ListMobilesServlet")
	public List<CartManager> getAllMobiles() {
		List<CartManager> listMobileDetails = null;
		try {

			listMobileDetails = MobileManagerService.getAllMobiles();

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return listMobileDetails;

	}

}
