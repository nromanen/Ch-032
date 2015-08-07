package com.softserveinc.orphanagemenu.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.softserveinc.orphanagemenu.model.Submenu;

@Entity
@Table(name = "daily_menu")
public class DailyMenu {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")	
	Long id;

	@Column(name = "date")	
	Date date;
	
	@Column(name = "is_accepted")
	Boolean isAccepted;
	
	@OneToMany(mappedBy = "dailyMenu", cascade = CascadeType.ALL)
	Set<Submenu> submenus = new HashSet<>();
	

	public Set<Submenu> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(Set<Submenu> submenus) {
		this.submenus = submenus;
	}

	public DailyMenu() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		DailyMenu other = (DailyMenu) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DailyManu [id=" + id + ", date=" + date + ", isAccepted="
				+ isAccepted + "]";
	}
	
	
}
