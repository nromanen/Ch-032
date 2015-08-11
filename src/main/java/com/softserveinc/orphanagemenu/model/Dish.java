package com.softserveinc.orphanagemenu.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="dish")
public class Dish {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="dish_id_seq")
    @SequenceGenerator(name="dish_id_seq", sequenceName="dish_id_seq", allocationSize=10)
	@Column(name = "id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="is_available")
	private Boolean is_available;
	
	@OneToMany(mappedBy="dish")
	private Set<Component> components = new HashSet<Component>();

	
	public Dish() {}
	
	public Dish(String name, boolean avail){
		this.name = name;
		this.is_available = avail;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIs_available() {
		return is_available;
	}

	public void setIs_available(Boolean is_available) {
		this.is_available = is_available;
	}

	public Set<Component> getComponents() {
		return components;
	}

	public void setComponents(Set<Component> components) {
		this.components = components;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dish other = (Dish) obj;
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
        return "Dish{" + "id=" + id + ", name=" + name + ", availability=" + is_available + "}";
    }
	
}
