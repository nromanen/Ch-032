package com.softserveinc.orphanagemenu.json;


public class DishResponseBody {
	
	private Long productId;
	
	private String dishName;
	
	private String ageCategoryId ;
	
	private String ageCategoryQuantity;
	
	public DishResponseBody() {
		
	}
	
	public String getAgeCategoryId() {
		return ageCategoryId;
	}

	public void setAgeCategoryId(String ageCategoryId) {
		this.ageCategoryId = ageCategoryId;
	}

	public String getAgeCategoryQuantity() {
		return ageCategoryQuantity;
	}

	public void setAgeCategoryQuantity(String ageCategoryQuantity) {
		this.ageCategoryQuantity = ageCategoryQuantity;
	}

	public Long getProductId(){
		return productId;
	}
	
	public void setProductId(Long id){
		 this.productId = id;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	
}