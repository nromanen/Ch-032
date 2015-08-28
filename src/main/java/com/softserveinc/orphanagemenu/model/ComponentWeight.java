
package com.softserveinc.orphanagemenu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "component_weight")
public class ComponentWeight {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "standart_component_quantity")
	private Double standartWeight;

	@ManyToOne
	@JoinColumn(name = "component_id")
	private Component component;

	@ManyToOne
	@JoinColumn(name = "age_category_id")
	private AgeCategory ageCategory;

	public ComponentWeight() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getStandartWeight() {
		return standartWeight;
	}

	public void setStandartWeight(Double weight) {
		this.standartWeight = weight;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public AgeCategory getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(AgeCategory category) {
		this.ageCategory = category;
	}

	@Override
	public String toString() {
		return "ComponentWeight [id=" + id + ", standartCategoryWeight="
				+ standartWeight + ", component=" + component
				+ ", ageCategory=" + ageCategory + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((standartWeight == null) ? 0 : standartWeight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ComponentWeight other = (ComponentWeight) obj;
		if (ageCategory == null) {
			if (other.ageCategory != null) {
				return false;
			}
		} else if (!ageCategory.equals(other.ageCategory)) {
			return false;
		}
		if (component == null) {
			if (other.component != null) {
				return false;
			}
		} else if (!component.equals(other.component)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (standartWeight == null) {
			if (other.standartWeight != null) {
				return false;
			}
		} else if (!standartWeight.equals(other.standartWeight)) {
			return false;
		}
		return true;
	}

}

