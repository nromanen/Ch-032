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

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
@Entity
@Table(name = "daily_menu")
public class DailyMenu {

	private Long id;
	private Date date;
	private Boolean isAccepted;
	private Set<Submenu> submenus = new HashSet<>();
	
	public DailyMenu() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "date")	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "is_accepted")
	public Boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	@OneToMany(mappedBy = "dailyMenu", cascade = CascadeType.ALL)
	public Set<Submenu> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(Set<Submenu> submenus) {
		this.submenus = submenus;
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
