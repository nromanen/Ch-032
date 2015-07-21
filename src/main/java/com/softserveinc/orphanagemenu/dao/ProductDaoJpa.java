package com.softserveinc.orphanagemenu.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Product;

@Repository("productDao")
@Transactional
public class ProductDaoJpa implements ProductDao {
    
	@PersistenceContext
    private EntityManager em;

    public void saveProduct(Product product) {
		em.persist(product);
    }
}