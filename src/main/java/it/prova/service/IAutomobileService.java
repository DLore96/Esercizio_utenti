package it.prova.service;

import java.util.List;

import it.prova.dao.IAutomobileDAO;
import it.prova.model.Automobile;

public interface IAutomobileService {

	public void setAutomobileDAO(IAutomobileDAO autoDao);

	public List<Automobile> listAll() throws Exception;

	public Automobile caricaSingoloElemento(Long idInput) throws Exception;

	public void aggiorna(Automobile input) throws Exception;

	public void inserisciNuovo(Automobile input) throws Exception;

	public void rimuovi(Automobile input) throws Exception;

	public List<Automobile> findByExample(Automobile input) throws Exception;

}
