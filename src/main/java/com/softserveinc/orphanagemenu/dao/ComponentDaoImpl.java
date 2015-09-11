package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;

@Repository("componentImpl")
@Transactional
public class ComponentDaoImpl implements ComponentDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Component> getAllComponents() {
		return (List<Component>)em.createQuery("SELECT c FROM Component c").getResultList();
	}
	
	@Override
	public void saveComponent(Component comp){
		em.persist(comp);
		}

	@Override
	public Long getProductFromComponent(Product product) {
		return  (Long)em.createQuery("SELECT p FROM Component p WHERE p.product_id="+product.getId()).getSingleResult();
	}
	
	@Override
	public void updateComponent(Component component) {
		em.merge(component);
	}
	
	
	@Override
	public void deleteComponent(Component component) {
	//Component comp = em.find(Component.class,component.getId());
//		System.out.println(comp.getProduct().getName());
		
		em.remove(component);
		//em.flush();
		//em.createQuery("DELETE FROM Component c where c.id = :compId").setParameter("compId",  component.getId()).executeUpdate();
		//em.remove(em.contains(component) ? component : em.merge(component));
	}
	
	@Override
	public Component getComponentById(Long id) {
		return (Component) em.createQuery("SELECT c FROM Component c WHERE c.id="+id).getSingleResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Component> getAllComponentsByDishId(Dish dish) {
		return (List<Component>) em.createQuery("SELECT c FROM Component c WHERE c.dish="+dish.getId()).getResultList();
	}
	
	@Override
	public Long getCount() {
		return em.createQuery("SELECT Count(c) FROM Component c", Long.class)
				.getSingleResult();
	}
	
}