package it.prova.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.dao.IAutomobileDAO;
import it.prova.model.Automobile;
import it.prova.web.listener.LocalEntityManagerFactoryListener;

public class AutomobileServiceImpl implements IAutomobileService {

	private IAutomobileDAO automobiledao;

	@Override
	public void setAutomobileDAO(IAutomobileDAO autoDao) {
		this.automobiledao = autoDao;
	}

	@Override
	public List<Automobile> listAll() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			automobiledao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return automobiledao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Automobile caricaSingoloElemento(Long idInput) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			automobiledao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return automobiledao.findOne(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Automobile input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			entityManager.getTransaction().begin();
			automobiledao.setEntityManager(entityManager);
			automobiledao.update(input);
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {

			entityManager.close();
		}

	}

	@Override
	public void inserisciNuovo(Automobile input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			automobiledao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			automobiledao.insert(input);

			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {

			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Automobile input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			automobiledao.setEntityManager(entityManager);
			input = entityManager.merge(input);
			automobiledao.delete(input);

			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {

			entityManager.close();
		}

	}

	@Override
	public List<Automobile> findByExample(Automobile input) throws Exception {

		return null;
	}

}
