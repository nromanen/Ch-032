package com.softserveinc.orphanagemenu.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "fact_product_quantity")
public class FactProductQuantity {

	private Long id;
	private Submenu submenu;
	private Double factProductQuantity;
	private ComponentWeight componentWeight;

	public FactProductQuantity() {
	}

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fact_product_quantity_id_seq")
    @SequenceGenerator(name="fact_product_quantity_id_seq", sequenceName="fact_product_quantity_id_seq", allocationSize=10)
	@Column(columnDefinition = "BIGSERIAL")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "submenu_id")	
	public Submenu getSubmenu() {
		return submenu;
	}

	public void setSubmenu(Submenu submenu) {
		this.submenu = submenu;
	}

	@Column(name = "fact_product_quantity")
	public Double getFactProductQuantity() {
		return factProductQuantity;
	}

	public void setFactProductQuantity(Double factProductQuantity) {
		this.factProductQuantity = factProductQuantity;
	}

	@OneToOne
	@JoinColumn(name = "component_weight_id")
	public ComponentWeight getComponentWeight() {
		return componentWeight;
	}

	public void setComponentWeight(ComponentWeight componentWeight) {
		this.componentWeight = componentWeight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((factProductQuantity == null) ? 0 : factProductQuantity.hashCode());
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
		FactProductQuantity other = (FactProductQuantity) obj;
		if (factProductQuantity == null) {
			if (other.factProductQuantity != null)
				return false;
		} else if (!factProductQuantity.equals(other.factProductQuantity))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FactProductQuantity [id=" + id + ", submenu=" + submenu + ", factProductQuantity="
				+ factProductQuantity + "]";
	}

}
