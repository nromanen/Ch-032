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
import javax.persistence.Table;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGSERIAL")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = {CascadeType.PERSIST,	CascadeType.MERGE })
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

	@ManyToOne
	@JoinColumn(name = "component_weight_id")
	public ComponentWeight getComponentWeight() {
		return componentWeight;
	}

	public void setComponentWeight(ComponentWeight componentWeight) {
		this.componentWeight = componentWeight;
	}

	@Override
	public String toString() {
		return "FactProductQuantity [id=" + id + ", submenu=" + submenu + ", factProductQuantity="
				+ factProductQuantity + "]";
	}

}
