package in.casekartin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import in.casekartin.exception.ServiceException;
import in.casekartin.model.RegisterManager;
import in.casekartin.service.RegisterManagerService;
import in.casekartin.util.LocalDateAdapter;
import in.casekartin.util.LocalDateTimeAdapter;

/**
 * Servlet implementation class ListAllUserServlet
 */
@WebServlet("/ListAllUserServlet")
public class ListAllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<RegisterManager> userDetails = RegisterManagerService.listAllDetails();
			Gson gson = new GsonBuilder().setPrettyPrinting()
					.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
					.registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
			String json = gson.toJson(userDetails);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
