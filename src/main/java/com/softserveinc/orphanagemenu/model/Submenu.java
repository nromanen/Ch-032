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
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
@Entity
@Table(name = "submenu")
public class Submenu {

	Long id;
	Integer childQuantity;
	DailyMenu dailyMenu;
	AgeCategory ageCategory;
	ConsumptionType consumptionType;
	Set<Dish> dishes = new LinkedHashSet<>();
	Set<FactProductQuantity> factProductQuantities = new HashSet<>();

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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


	@ManyToOne(cascade = {CascadeType.PERSIST,	CascadeType.MERGE })
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

	@OneToMany(mappedBy = "submenu", cascade = CascadeType.ALL)
	public Set<FactProductQuantity> getFactProductQuantities() {
		return factProductQuantities;
	}

	public void setFactProductQuantities(
			Set<FactProductQuantity> factProductQuantities) {
		this.factProductQuantities = factProductQuantities;
	}
}
