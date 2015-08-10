package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;

@Repository("componentImpl")
@Transactional
public class ComponentDaoImpl implements ComponentDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public ArrayList<Component> getAllComponent() {
		return (ArrayList<Component>)em.createQuery("SELECT c FROM Component c").getResultList();
	}
	
	public void saveComponent(Component comp){
		em.persist(comp);
	}

	
	public Long getProductFromComponent(Product product) {
		Long id =  (Long)em.createQuery("SELECT p FROM Component p WHERE p.product_id="+product.getId()).getSingleResult();
		return id;
	}

	public void updateComponent(Component component) {
		
		em.merge(component);
	}

	public Component getComponentById(Long id) {
		Component component = (Component) em.createQuery("SELECT c FROM Component c WHERE c.id="+id).getSingleResult();
		return component;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Component> getAllComponentByDishId(Dish dish) {
		return (ArrayList<Component>) em.createQuery("SELECT c FROM Component c WHERE c.dish="+dish.getId()).getResultList();
	}
	
	public Component getComponentById(Component component_id) {
			Component component  = (Component)em.createQuery("SELECT c FROM Component c WHERE component.id="+component_id.getId());
			return component;
	}
	
	
}
