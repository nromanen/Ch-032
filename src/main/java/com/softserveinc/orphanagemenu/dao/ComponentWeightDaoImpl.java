package com.softserveinc.orphanagemenu.dao;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.softserveinc.orphanagemenu.model.ComponentWeight;


@Repository("componentWeightDaoImpl")
@Transactional
public class ComponentWeightDaoImpl implements ComponentWeightDao {
	
	@PersistenceContext
    private EntityManager em;

	public void addComponents(ComponentWeight comp) {
		em.persist(comp);
	}

	
	@SuppressWarnings("unchecked")
	public List<ComponentWeight> addAllComponentWeight() {
		return (List<ComponentWeight>)em.createQuery("SELECT w FROM ComponentWeight w").getResultList();
	}
	
}
