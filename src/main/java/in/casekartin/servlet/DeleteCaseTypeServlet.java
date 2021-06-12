package in.casekartin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.casekartin.service.CaseManagerService;

/**
 * Servlet implementation class DeleteCaseType
 */
@WebServlet("/DeleteCaseTypeServlet")
public class DeleteCaseTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		  //Get the case name to delete 
		String caseName=request.getParameter("caseName");
			if(CaseManagerService.deleteCaseType(caseName))
			  {
				  response.sendRedirect("listCases.jsp"); 
			  }
			  else
			  {
				  response.sendRedirect("/DeleteCaseTypeServlet"); 
			  }	 
	}
}
