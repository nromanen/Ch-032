package com.softserveinc.orphanagemenu.forms;

public class WarehouseItemForm {
	private String id;
	private String itemName;
	private String quantity;
	private String dimension;
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getQuantity() {
		return quantity;
	}



	public void setQuantity(String quantity) {
		
		
		this.quantity = quantity;
	}


	public String getDimension() {
		return dimension;
	}



	public void setDimension(String dimension) {
		this.dimension = dimension;
	}



	@Override
	public String toString() {
		return "WarehouseItemForm [id=" + id + ", itemName=" + itemName
				+ ", quantity=" + quantity + ", dimension=" + dimension + "]";
	}
	
	

}
