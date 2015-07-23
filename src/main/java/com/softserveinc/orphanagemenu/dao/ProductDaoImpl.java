package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;

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
    
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ProductWeight> getAllProductWeight() {
		return (ArrayList<ProductWeight>)em.createQuery("SELECT pW FROM ProductWeight pW").getResultList();
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

	public Product getProduct(String productName) {
		System.out.println(productName + "******"); 
		TypedQuery<Product> query = em.createQuery(
				"SELECT p FROM Product p WHERE p.name=?", Product.class).setParameter(1, productName);
		Product product = query.getSingleResult();
		
		return product;
		}
}
