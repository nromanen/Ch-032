package com.softserveinc.orphanagemenu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "age_category")
public class AgeCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGSERIAL")
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "is_active")
	private Boolean isActive;

	public AgeCategory() {
	}
}
