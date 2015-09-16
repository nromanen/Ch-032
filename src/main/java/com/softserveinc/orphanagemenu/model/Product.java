package com.softserveinc.orphanagemenu.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Product {

	private Long id;
	private String name;
	private Dimension dimension;
	private Set<ProductWeight> productWeight;

	public Product() {
    }

	@ManyToOne
	@JoinColumn(name = "dimension_id")
	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	public Set<ProductWeight> getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(Set<ProductWeight> productWeight) {
		this.productWeight = productWeight;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Product other = (Product) obj;
		if (!Objects.equals(this.name, other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 73 * hash + Objects.hashCode(this.name);
		return hash;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", dimension="
				+ dimension + "]";
	}

    
}