package it.prova.dao;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.model.Automobile;

public class AutomobileDAOImpl implements IAutomobileDAO {

	private EntityManager entityManager;

	@Override
	public List<Automobile> list() throws Exception {
		return entityManager.createQuery("from Automobile", Automobile.class).getResultList();
	}

	@Override
	public Automobile findOne(Long id) throws Exception {
		return entityManager.find(Automobile.class, id);
	}

	@Override
	public void update(Automobile input) throws Exception {

		if (input == null) {
			throw new Exception("Problema valore in input");
		}

		input = entityManager.merge(input);

	}

	@Override
	public void insert(Automobile input) throws Exception {

		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);

	}

	@Override
	public void delete(Automobile input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.remove(entityManager.merge(input));

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;

	}

}
