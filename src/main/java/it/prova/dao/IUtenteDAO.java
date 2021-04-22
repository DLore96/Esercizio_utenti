package it.prova.dao;

import it.prova.model.Utente;

public interface IUtenteDAO extends IBaseDAO<Utente> {

	public Utente findByUserPAssword(String user, String password) throws Exception;

}
