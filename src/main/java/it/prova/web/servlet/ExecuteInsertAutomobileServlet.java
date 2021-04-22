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

@WebServlet("/ExecuteInsertAutomobileServlet")
public class ExecuteInsertAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteInsertAutomobileServlet() {
		super();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// CONTROLLO SE LA SESSIONE SIA ANCORA "ATTIVA"
		if (request.getSession().getAttribute("info_utente") == null) {
			response.sendRedirect("/login.jsp");
			return;
		}

		// estraggo input
		String marcaInputParam = request.getParameter("marca");
		String modelloInputParam = request.getParameter("modello");
		String cilindrataInputParam = request.getParameter("cilindrata");
		String dataImmatricolazioneParam = request.getParameter("dataImmatricolazione");

		// questa variabile mi serve in quanto sfrutto in un colpo la validazione
		// della data ed il suo parsing che non posso fare senza un try catch
		// a questo punto lo incapsulo in un metodo apposito
		Date dataPubblicazioneParsed = UtilityAutomobileForm
				.parseDateImmatricolazioneFromString(dataImmatricolazioneParam);

		// valido input tramite apposito metodo e se la validazione fallisce torno in
		// pagina
		if (!UtilityAutomobileForm.validateInput(marcaInputParam, modelloInputParam, cilindrataInputParam,
				dataImmatricolazioneParam) || dataImmatricolazioneParam == null) {

			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/automobile/insert.jsp").forward(request, response);
			return;

		}

		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		Automobile autoInstance = new Automobile(marcaInputParam, modelloInputParam,
				Integer.parseInt(cilindrataInputParam), dataPubblicazioneParsed);
		// occupiamoci delle operazioni di business
		try {

			MyServiceFactory.getAutomobileServiceInstance().inserisciNuovo(autoInstance);
			request.setAttribute("listautomobileAttribute", MyServiceFactory.getAutomobileServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/automobile/insert.jsp").forward(request, response);
			return;

		}

		// andiamo ai risultati
		request.getRequestDispatcher("/automobile/results.jsp").forward(request, response);
	}

}
