package it.prova.service;

import it.prova.dao.AutomobileDAOImpl;
import it.prova.dao.IAutomobileDAO;
import it.prova.dao.IUtenteDAO;
import it.prova.dao.UtenteDAOImpl;

public class MyServiceFactory {

	// implementiamo il singleton in modo da evitare
	// proliferazione di riferimenti
	private static IAutomobileService AUTOMOBILE_SERVICE_INSTANCE = null;
	private static IAutomobileDAO AUTOMOBILE_DAO_INSTANCE = null;

	private static IUtenteService UTENTE_SERVICE_INSTANCE = null;
	private static IUtenteDAO UTENTE_DAO_INSTANCE = null;

	public static IUtenteService getUtenteServiceInstance() {
		if (UTENTE_SERVICE_INSTANCE == null)
			UTENTE_SERVICE_INSTANCE = new UtenteServiceImpl();

		if (UTENTE_DAO_INSTANCE == null)
			UTENTE_DAO_INSTANCE = new UtenteDAOImpl();

		UTENTE_SERVICE_INSTANCE.setUtenteDAO(UTENTE_DAO_INSTANCE);

		return UTENTE_SERVICE_INSTANCE;
	}

	public static IAutomobileService getAutomobileServiceInstance() {
		if (AUTOMOBILE_SERVICE_INSTANCE == null)
			AUTOMOBILE_SERVICE_INSTANCE = new AutomobileServiceImpl();

		if (AUTOMOBILE_DAO_INSTANCE == null)
			AUTOMOBILE_DAO_INSTANCE = new AutomobileDAOImpl();

		AUTOMOBILE_SERVICE_INSTANCE.setAutomobileDAO(AUTOMOBILE_DAO_INSTANCE);

		return AUTOMOBILE_SERVICE_INSTANCE;
	}

}
