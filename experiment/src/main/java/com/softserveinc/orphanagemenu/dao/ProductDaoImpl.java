package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;

@Repository("productDaoImpl")
@Transactional
public class ProductDaoImpl implements ProductDao {
    
	@PersistenceContext
    private EntityManager em;

    public void saveProduct(Product product) {
		em.persist(product);
    }
    
    @SuppressWarnings("unchecked")
	public ArrayList<Product> getAllProduct(){
    	return (ArrayList<Product>)em.createQuery("SELECT p FROM Product p ORDER BY p.name asc").getResultList();
    }

	public Product getProductById(Long id) {
		Product product = (Product) em.createQuery("SELECT p FROM Product p WHERE p.id="+id).getSingleResult();
		return product;
	}

	public Dimension getDimensionById(Long id) {
		Dimension dimension = (Dimension) em.createQuery("SELECT d FROM Dimension d WHERE d.id="+id).getSingleResult();
		return dimension;
	}
	
    @SuppressWarnings("unchecked")
	public ArrayList<Dimension> getAllDimension(){
    	return (ArrayList<Dimension>)em.createQuery("SELECT d FROM Dimension d").getResultList();
    }

	@Override
	public void updateProduct(Product product) {
		em.merge(product);
	}
    
}