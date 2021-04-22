package it.prova.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.model.Automobile;
import it.prova.service.IAutomobileService;
import it.prova.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteAutomobileServlet")
public class ExecuteDeleteAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteDeleteAutomobileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// CONTROLLO SE LA SESSIONE SIA ANCORA "ATTIVA"
		if (request.getSession().getAttribute("info_utente") == null) {
			response.sendRedirect("/login.jsp");
			return;
		}

		String idAutoParameter = request.getParameter("inputid");

		IAutomobileService autoservice = MyServiceFactory.getAutomobileServiceInstance();

		Automobile autoInstance;
		if (!NumberUtils.isCreatable(idAutoParameter)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try {

			autoInstance = autoservice.caricaSingoloElemento(Long.parseLong(idAutoParameter));
			autoservice.rimuovi(autoInstance);
			request.setAttribute("listautomobileAttribute", MyServiceFactory.getAutomobileServiceInstance().listAll());

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;

		}

		RequestDispatcher rd = request.getRequestDispatcher("/automobile/results.jsp");
		rd.forward(request, response);
	}

}
