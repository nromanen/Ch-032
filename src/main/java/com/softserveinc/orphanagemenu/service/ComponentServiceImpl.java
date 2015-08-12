
package com.softserveinc.orphanagemenu.service;

import java.util.List;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.AgeCategoryDao;
import com.softserveinc.orphanagemenu.dao.ComponentDao;
import com.softserveinc.orphanagemenu.dao.DishDao;
import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.json.DishForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;


@Service
@Transactional
public class ComponentServiceImpl implements ComponentService {

	@Autowired
	private ComponentDao componentDao;
	
	@Autowired
	private DishDao dishDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private AgeCategoryDao ageCategoryDao;
	
	@Override
	@Transactional
	public List<Component> getAllComponent(){
    	return this.componentDao.getAllComponent();
	}
	
	@Override
	@Transactional
	public void saveComponent(Component comp){
		this.componentDao.saveComponent(comp);
	}
	
	@Override
	@Transactional
	public Long getProductFromComponent(Product product){
		return this.componentDao.getProductFromComponent(product);
	}

	@Override
	@Transactional
	public void updateComponent(Component component) {
		this.componentDao.updateComponent(component);
	}
	
	@Override
	@Transactional
	public Component getComponentById(Long id) {
		return this.componentDao.getComponentById(id);
	}
	
	@Override
	@Transactional
	public Component getNewComponentByDishForm(DishForm dishForm) {
		
		Component component = new Component();
		if(!(dishForm.getId()==null)) {
			component = componentDao.getComponentById(dishForm.getId());
			return component;
		}
		
		component.setDish(dishDao.getDishByName(dishForm.getDishName()));
		component.setProduct(productDao.getProduct(dishForm.getProduct().getName()));
		List<AgeCategory> ageCategoryList = ageCategoryDao.getAllAgeCategory();
		
		Set<ComponentWeight> componentsWeightList = new HashSet<ComponentWeight>();
		int i = 0;
		for(Map.Entry<Long, Double> formWeight : dishForm.getWeight().entrySet()){
			ComponentWeight cWeight = new ComponentWeight();
			cWeight.setStandartWeight(formWeight.getValue());
			cWeight.setAgeCategory(ageCategoryList.get(i));
			cWeight.setComponent(component);
			componentsWeightList.add(cWeight);
			i++;
		}
		component.setComponents(componentsWeightList);
		
		return component;
	}
	
	@Override
	@Transactional
	public Component updateNewComponentByDishForm(DishForm dishForm){
		Component component = componentDao.getComponentById(dishForm.getComponent().getId());
		component.setDish(dishDao.getDishByName(dishForm.getDishName()));
		component.setProduct(productDao.getProduct(dishForm.getProduct().getName()));
		for(Map.Entry<Long, Double> formWeight : dishForm.getWeight().entrySet()) {
			for(ComponentWeight cWeight : component.getComponents()) {
				if(formWeight.getKey().equals(cWeight.getAgeCategory().getId())){
					cWeight.setStandartWeight(formWeight.getValue());
				}
			}
		}
		return component;
	}
	
	@Override
	@Transactional
	public List<Component> getAllComponentByDishId(Dish dish){
		return this.componentDao.getAllComponentByDishId(dish);
	}

	@Override
	public Component getComponentById(Component component_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
