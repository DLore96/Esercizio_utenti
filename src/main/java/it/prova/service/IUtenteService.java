package it.prova.service;

import java.util.List;

import it.prova.dao.IUtenteDAO;
import it.prova.model.Utente;

public interface IUtenteService {

	public List<Utente> listAll() throws Exception;

	public Utente caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Utente utenteInstance) throws Exception;

	public void inserisciNuovo(Utente utenteInstance) throws Exception;

	public void rimuovi(Utente utenteInstance) throws Exception;

	public void setUtenteDAO(IUtenteDAO utenteDAO);

	public Utente trovaUtenteByUserPass(String username, String password) throws Exception;

}
