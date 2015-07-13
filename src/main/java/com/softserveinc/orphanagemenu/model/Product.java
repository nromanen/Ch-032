package com.softserveinc.orphanagemenu.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "dimension")
	private String dimension;

	public Product() {
    }
    
    public Product(String name, String dimension) {
		super();
		this.name = name;
		this.dimension = dimension;
	}

	public Product(long id, String name, double price, boolean available) {
    	this.id = id;
    	this.name = name;
    }

    public long getId() {
		return id;
	}

    public void setId(long id) {
		this.id = id;
	}
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	@Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", dimension=" + dimension + "}";
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
    
}
