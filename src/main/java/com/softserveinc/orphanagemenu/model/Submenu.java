package com.softserveinc.orphanagemenu.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
@Entity
@Table(name = "submenu")
@NamedQueries({
		@NamedQuery(name = "submenuListByDailyMenuAndConsumptionTypeId", query = "SELECT s FROM Submenu s "
				+ "WHERE s.dailyMenu.id = :dailyMenuId "
				+ "AND s.consumptionType.id = :consumptionTypeId"),
		@NamedQuery(name = "submenuByDailyMenuAndConsumptionTypeAndAgeCategory", query = "SELECT s FROM Submenu s "
				+ "WHERE s.dailyMenu.id = :dailyMenuId "
				+ "AND s.consumptionType.id = :consumptionTypeId "
				+ "AND s.ageCategory.id = :ageCategoryId"),
		@NamedQuery(name = "submenuListByDailyMenuId", query = "SELECT s FROM Submenu s WHERE s.dailyMenu.id = :dailyMenuId") })
public class Submenu {

	private Long id;
	private Integer childQuantity;
	private DailyMenu dailyMenu;
	private AgeCategory ageCategory;
	private ConsumptionType consumptionType;
	private Set<Dish> dishes = new LinkedHashSet<>();
	private Set<FactProductQuantity> factProductQuantities = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "child_quantity")
	public Integer getChildQuantity() {
		return childQuantity;
	}

	public void setChildQuantity(Integer childQuantity) {
		this.childQuantity = childQuantity;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "daily_menu_id")
	public DailyMenu getDailyMenu() {
		return dailyMenu;
	}

	public void setDailyMenu(DailyMenu dailyMenu) {
		this.dailyMenu = dailyMenu;
	}

	@ManyToOne
	@JoinColumn(name = "age_category_id")
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}

	@ManyToOne
	@JoinColumn(name = "consumption_type_id")
	public ConsumptionType getConsumptionType() {
		return consumptionType;
	}

	public void setConsumptionType(ConsumptionType consumptionType) {
		this.consumptionType = consumptionType;
	}

	@ManyToMany
	@JoinTable(name = "submenu_has_dish", joinColumns = { @JoinColumn(name = "submenu_id") }, inverseJoinColumns = { @JoinColumn(name = "dish_id") })
	public Set<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(Set<Dish> dishes) {
		this.dishes = dishes;
	}

	@OneToMany(mappedBy = "submenu", cascade = CascadeType.ALL, orphanRemoval=true)
	public Set<FactProductQuantity> getFactProductQuantities() {
		return factProductQuantities;
	}

	public void setFactProductQuantities(
			Set<FactProductQuantity> factProductQuantities) {
		this.factProductQuantities = factProductQuantities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((childQuantity == null) ? 0 : childQuantity.hashCode());
		result = prime * result
				+ ((consumptionType == null) ? 0 : consumptionType.hashCode());
		result = prime * result
				+ ((dailyMenu == null) ? 0 : dailyMenu.hashCode());
		result = prime * result
				+ ((ageCategory == null) ? 0 : ageCategory.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Submenu other = (Submenu) obj;

		if ((childQuantity == null) && (other.childQuantity != null)) {
			return false;
		} else if (!childQuantity.equals(other.childQuantity))
			return false;

		if ((consumptionType == null) && (other.consumptionType != null)) {
			return false;
		} else if (!consumptionType.equals(other.consumptionType))
			return false;

		if ((ageCategory == null) && (other.ageCategory != null)) {
			return false;
		} else if (!ageCategory.equals(other.ageCategory))
			return false;

		if (dailyMenu == null) {
			if (other.dailyMenu != null)
				return false;
		} else if (!dailyMenu.equals(other.dailyMenu))
			return false;
		return true;
	}

}
