package com.menu.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HibernateCRUD {

	private static EntityManager em;

	public void initEntityManager() {
		em = Persistence.createEntityManagerFactory("persistence").createEntityManager();
	}

	public String addProduct(Product product) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(product);
		transaction.commit();
		return "Product has been saved";
	}
	
	public void updateProduct(Product product, String name) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		String query = "SELECT p FROM Product p WHERE p.title=:title";
		Product pr = em.createQuery(query, Product.class).
				setParameter("title", name).getSingleResult();
		product.setId(pr.getId());
		em.merge(product);
		transaction.commit();
	}

	public String getAllProducts() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		//This Product must be class name not table name
		String query = "SELECT p FROM Product p"; 
		Product.productList = em.createQuery(query, Product.class).getResultList();
		return Product.productList.toString();
	}
	
	public Product readProduct(String name) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		//This Product must be class name not table name
		String query = "SELECT p FROM Product p WHERE p.title=:title";
		Product pr = em.createQuery(query, Product.class).
				setParameter("title", name).getSingleResult();
		return pr;
	}

	public void deleteProductByName(String name) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		String query = "SELECT p FROM Product p WHERE p.title=:title";
		Product pr = em.createQuery(query, Product.class).
				setParameter("title", name).getSingleResult();
		em.remove(pr);
		transaction.commit();
	}
}
