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

@WebServlet("/PrepareUpdateAutomobileServlet")
public class PrepareUpdateAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareUpdateAutomobileServlet() {
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

		String parametroIdDellAutoCheVoglioModificare = request.getParameter("idauto");

		IAutomobileService autoServiceInstance = MyServiceFactory.getAutomobileServiceInstance();
		Automobile result = null;

		if (!NumberUtils.isCreatable(parametroIdDellAutoCheVoglioModificare)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;

		}

		try {

			result = autoServiceInstance.caricaSingoloElemento(Long.parseLong(parametroIdDellAutoCheVoglioModificare));

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		request.setAttribute("Auto_Modifica", result);
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/automobile/edit.jsp");
		rd.forward(request, response);
	}

}
