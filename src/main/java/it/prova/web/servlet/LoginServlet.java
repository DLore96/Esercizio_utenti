package it.prova.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.model.Utente;
import it.prova.service.MyServiceFactory;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usernameParameter = request.getParameter("inputUsername");
		String passwordParameter = request.getParameter("inputPassword");
		System.out.println(usernameParameter);

		if (usernameParameter == null || usernameParameter.isEmpty() || passwordParameter == null
				|| passwordParameter.isEmpty()) {

			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		try {

			Utente nuovoUtenteDaDB = MyServiceFactory.getUtenteServiceInstance()
					.trovaUtenteByUserPass(usernameParameter, passwordParameter);
			request.getSession().setAttribute("info_utente", nuovoUtenteDaDB);

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

}
