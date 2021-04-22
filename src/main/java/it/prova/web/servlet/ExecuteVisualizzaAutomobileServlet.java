package it.prova.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.service.MyServiceFactory;

@WebServlet("/ExecuteVisualizzaAutomobileServlet")
public class ExecuteVisualizzaAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteVisualizzaAutomobileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// CONTROLLO SE LA SESSIONE SIA ANCORA "ATTIVA"
		if (request.getSession().getAttribute("info_utente") == null) {
			response.sendRedirect("/login.jsp");
			return;
		}

		String idAutoParameter = request.getParameter("idauto");

		if (!NumberUtils.isCreatable(idAutoParameter)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try {

			request.setAttribute("visualizza_auto_attr", MyServiceFactory.getAutomobileServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idAutoParameter)));

		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/automobile/show.jsp").forward(request, response);
	}

}
