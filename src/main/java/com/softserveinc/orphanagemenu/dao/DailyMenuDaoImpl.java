package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dto.AgeCategoryNormsAndFactDto;
import com.softserveinc.orphanagemenu.dto.ProductNormComplianceDto;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.DailyMenu;

import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.ProductWeight;

import com.softserveinc.orphanagemenu.model.Submenu;

@Repository("dailyMenuDao")
@Transactional
public class DailyMenuDaoImpl implements DailyMenuDao {

	private static final String DAILY_MENU_BY_DATE = "SELECT dm FROM DailyMenu dm WHERE dm.date = :date";
	private static final String DAILY_MENU_CURRENT_DATE_TO_FUTURE_DATE = "SELECT dm FROM DailyMenu dm WHERE dm.date >= :currentDate and dm.date < :futureDate";

	@PersistenceContext
	private EntityManager em;

	@Override
	public DailyMenu save(DailyMenu dailyMenu) {
		em.persist(dailyMenu);
		return null;
	}
	
	@Override
	public void updateDailyMenu(DailyMenu dailyMenu) {
		em.merge(dailyMenu);
	}

	@Override
	public void delete(DailyMenu dailyMenu) {
		em.remove(em.merge(dailyMenu));
	}

	@Override
	public DailyMenu getById(Long id) {
		return em.find(DailyMenu.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyMenu> getAll() {
		return (ArrayList<DailyMenu>) em.createQuery(
				"SELECT dm FROM DailyMenu dm").getResultList();
	}

	@Override
	public void print() {
		DailyMenu dailyMenu = getById(1L);
		System.out.println(dailyMenu);
		System.out.println(dailyMenu.getSubmenus());
		Submenu submenu = (Submenu) dailyMenu.getSubmenus().toArray()[0];
		System.out.println(submenu);
		System.out.println(submenu.getFactProductQuantities());

	}

	@Override
	@SuppressWarnings("unchecked")
	public DailyMenu getByDate(Date date) {
		List<DailyMenu> dailyMenus = (List<DailyMenu>) em
				.createQuery(DAILY_MENU_BY_DATE).setParameter("date", date)
				.getResultList();
		DailyMenu dailyMenu = null;
		if (dailyMenus.size() != 0) {
			dailyMenu = dailyMenus.get(0);
		}
		return dailyMenu;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DailyMenu> getFromCurrentDateToFutureDate(Date futureDate) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date currentDate = calendar.getTime();
		return (List<DailyMenu>) em
				.createQuery(DAILY_MENU_CURRENT_DATE_TO_FUTURE_DATE)
				.setParameter("currentDate", currentDate)
				.setParameter("futureDate", futureDate).getResultList();
	}
// norms compliance
	public List<ProductNormComplianceDto> getProductWithStandartAndFactQuantityList(
			Long id) {
		ArrayList<ProductNormComplianceDto> prodNormList = new ArrayList<ProductNormComplianceDto>();

		for (Submenu subMenu : getById(id).getSubmenus()) {

			for (Dish dish : subMenu.getDishes()) {

				for (Component component : dish.getComponents()) {

					for (ComponentWeight componentWeight : component
							.getComponents()) {

						AgeCategoryNormsAndFactDto ageCategoryNormsAndFact = new AgeCategoryNormsAndFactDto();
						ageCategoryNormsAndFact.setAgeCategory(componentWeight
								.getAgeCategory());
					
						for(ProductWeight productWeight:component.getProduct().getProductWeight()){
							if(productWeight.getAgeCategory().getName().equals(componentWeight.getAgeCategory().getName())){
								
								ageCategoryNormsAndFact.setNorma(productWeight.getStandartProductQuantity());
								
								break;
							}
						}
												

						ageCategoryNormsAndFact.setFactQuantity(componentWeight
								.getStandartWeight());

						ProductNormComplianceDto productNormCompliance = new ProductNormComplianceDto();
						productNormCompliance.setName(component.getProduct()
								.getName());

						productNormCompliance
								.setCategoryWithNormsAndFact(ageCategoryNormsAndFact);

						if (prodNormList.contains(productNormCompliance)) {
											
																			
																			
							int indexID = prodNormList
									.indexOf(productNormCompliance);
							ProductNormComplianceDto itemProductNormCompliance = prodNormList
									.get(indexID);

							itemProductNormCompliance
									.setCategoryWithNormsAndFact(productNormCompliance
											.getCategoryWithNormsAndFact().get(
													0));

						} else {

							prodNormList.add(productNormCompliance);

						}

					}

				}

			}

		}

		return prodNormList;
	}

}
