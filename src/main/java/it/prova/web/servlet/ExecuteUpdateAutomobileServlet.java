package it.prova.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.model.Automobile;
import it.prova.service.MyServiceFactory;
import it.prova.utility.UtilityAutomobileForm;

@WebServlet("/ExecuteUpdateAutomobileServlet")
public class ExecuteUpdateAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteUpdateAutomobileServlet() {
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

		String idParameter = request.getParameter("idauto");
		String marcaParameter = request.getParameter("marca");
		String modelloParameter = request.getParameter("modello");
		String cilindrataParameter = request.getParameter("cilindrata");
		String dataParameter = request.getParameter("dataImmatricolazione");

		Date dataImmatricolazioneParsed = UtilityAutomobileForm.parseDateImmatricolazioneFromString(dataParameter);

		if (cilindrataParameter == null || cilindrataParameter.equals("")) {

			cilindrataParameter = "0";
		}

		Automobile autoInstance = new Automobile();
		autoInstance.setMarca(marcaParameter);
		autoInstance.setModello(modelloParameter);
		autoInstance.setCilindrata(Integer.parseInt(cilindrataParameter));
		autoInstance.setId(Long.parseLong(idParameter));
		autoInstance.setDataImmatricolazione(dataImmatricolazioneParsed);

		if (!UtilityAutomobileForm.validateInput(marcaParameter, modelloParameter, cilindrataParameter, dataParameter)
				|| dataImmatricolazioneParsed == null) {

			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("Auto_Modifica", autoInstance);
			request.getRequestDispatcher("/automobile/edit.jsp").forward(request, response);
			return;
		}

		try {

			MyServiceFactory.getAutomobileServiceInstance().aggiorna(autoInstance);
			request.setAttribute("listautomobileAttribute", MyServiceFactory.getAutomobileServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/automobile/edit.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/automobile/results.jsp").forward(request, response);
	}

}
