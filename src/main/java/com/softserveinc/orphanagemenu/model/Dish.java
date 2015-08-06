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
import javax.persistence.Table;


@Entity
@Table(name="meal")
public class Dish {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="name")
	private String dishName;
	
	@Column(name="is_available")
	private Boolean available;
	
	@OneToMany(mappedBy="dish")
	private Set<Component> components = new HashSet<Component>();

	
	public Dish() {}
	
	public Set<Component> getComponents(){
		return components;
	}
	
	public void setComponents(Set<Component> list){
		this.components = list;
	}
	
	public Dish(String name, Boolean avail){
		this.dishName = name;
		this.available = avail;
	}
	
	public Dish(String name, Boolean available, Product prod) {
		this.dishName = name;
		this.available = available;
	}
	
	public Long getDishId(){
		return id;
	}
	
	public void setDishId(Long id){
		this.id = id;
	}
	
	public String getDishName(){
		return dishName;
	}
	
	public void setDishName(String name){
		this.dishName = name;
	}
	
	public Boolean getAvailable(){
		return available;
	}
	
	
	public void setAvailable(Boolean available){
		this.available = available;
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
        if (!Objects.equals(this.dishName, other.dishName)) {
            return false;
        }
        return true;
    }
	
	@Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.dishName);
        return hash;
    }
	
	@Override
    public String toString() {
        return "Dish{" + "id=" + id + ", name=" + dishName + ", availability=" + available + "}";
    }
	
}
