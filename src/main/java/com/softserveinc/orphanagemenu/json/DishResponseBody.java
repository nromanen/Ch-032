package com.softserveinc.orphanagemenu.json;

public class DishResponseBody {
	
	private Long productId;
	
	private String dishName;
	
	private Double category0;
	
	private Double category1;
	
	private Double category2;
	
	private Double category3;
	
	
	public DishResponseBody() {}
	
	public Long getProductId(){
		return productId;
	}
	
	public void setProductId(Long id){
		 this.productId = id;
	}
	
	public Double getCategory0(){
		return category0;
	}
	
	public void setCategory0(Double cat){
		this.category0  = cat;
	}
	
	public Double getCategory1(){
		return category1;
	}
	
	public void setCategory1(Double cat){
		this.category1 = cat;
	}
	
	public Double getCategory2(){
		return category2;
	}
	
	public void setCategory2(Double cat){
		this.category2 = cat;
	}
	
	public Double getCategory3(){
		return category3;
	}
	
	public void setCategory3(Double cat){
		this.category3 = cat;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	
	
	
}
