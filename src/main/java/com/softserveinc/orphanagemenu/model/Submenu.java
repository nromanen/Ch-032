package com.softserveinc.orphanagemenu.model;

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
import javax.persistence.Table;


@Entity
@Table(name = "submenu")
public class Submenu {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;

	@Column(name = "child_quantity")
	Integer childQuantity;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "daily_menu_id")	
	DailyMenu dailyMenu;

	@ManyToOne
    @JoinColumn(name = "age_category_id")
	AgeCategory ageCategory;
	
	@ManyToMany
	@JoinTable(
		name="submenu_has_dish",
		joinColumns = {@JoinColumn(name = "submenu_id")},
		inverseJoinColumns = {@JoinColumn(name = "dish_id")}
	)
	Set<Dish> dishes;

	public Set<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(Set<Dish> dishes) {
		this.dishes = dishes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getChildQuantity() {
		return childQuantity;
	}

	public void setChildQuantity(Integer childQuantity) {
		this.childQuantity = childQuantity;
	}

	public DailyMenu getDailyMenu() {
		return dailyMenu;
	}

	public void setDailyMenu(DailyMenu dailyMenu) {
		this.dailyMenu = dailyMenu;
	}

	public AgeCategory getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}


	
	
	
	
}
