package in.casekartin.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.casekartin.model.CaseManager;
import in.casekartin.service.CaseManagerService;

@RestController

public class CaseController {

	@GetMapping("/ListCasesServlet")
	public Set<CaseManager> getAllCases(HttpSession session) {
		System.out.println("Case Controller - get All Cases");
		Set<CaseManager> caseTypes = CaseManagerService.getActiveCaseTypes();
		session.setAttribute("LIST_CASES", caseTypes);
		return caseTypes;
	}
}
