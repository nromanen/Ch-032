package com.softserveinc.orphanagemenu.model;

import java.util.HashSet;
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
	
	@ManyToOne
    @JoinColumn(name = "consumption_type_id")
	ConsumptionType consumptionType;
	
	@ManyToMany
	@JoinTable(
		name="submenu_has_dish",
		joinColumns = {@JoinColumn(name = "submenu_id")},
		inverseJoinColumns = {@JoinColumn(name = "dish_id")}
	)
	Set<Dish> dishes = new HashSet<>();
	
	@OneToMany(mappedBy = "submenu", cascade = CascadeType.ALL)
	Set<FactProductQuantity> factProductQuantities = new HashSet<>();
	
	
	public ConsumptionType getConsumptionType() {
		return consumptionType;
	}

	public void setConsumptionType(ConsumptionType consumptionType) {
		this.consumptionType = consumptionType;
	}

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

	public Set<FactProductQuantity> getFactProductQuantities() {
		return factProductQuantities;
	}

	public void setFactProductQuantities(Set<FactProductQuantity> factProductQuantities) {
		this.factProductQuantities = factProductQuantities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ageCategory == null) ? 0 : ageCategory.hashCode());
		result = prime * result + ((childQuantity == null) ? 0 : childQuantity.hashCode());
		result = prime * result + ((consumptionType == null) ? 0 : consumptionType.hashCode());
		result = prime * result + ((dailyMenu == null) ? 0 : dailyMenu.hashCode());
		result = prime * result + ((dishes == null) ? 0 : dishes.hashCode());
		result = prime * result + ((factProductQuantities == null) ? 0 : factProductQuantities.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Submenu other = (Submenu) obj;
		if (ageCategory == null) {
			if (other.ageCategory != null)
				return false;
		} else if (!ageCategory.equals(other.ageCategory))
			return false;
		if (childQuantity == null) {
			if (other.childQuantity != null)
				return false;
		} else if (!childQuantity.equals(other.childQuantity))
			return false;
		if (consumptionType == null) {
			if (other.consumptionType != null)
				return false;
		} else if (!consumptionType.equals(other.consumptionType))
			return false;
		if (dailyMenu == null) {
			if (other.dailyMenu != null)
				return false;
		} else if (!dailyMenu.equals(other.dailyMenu))
			return false;
		if (dishes == null) {
			if (other.dishes != null)
				return false;
		} else if (!dishes.equals(other.dishes))
			return false;
		if (factProductQuantities == null) {
			if (other.factProductQuantities != null)
				return false;
		} else if (!factProductQuantities.equals(other.factProductQuantities))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
