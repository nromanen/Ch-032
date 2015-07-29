package com.softserveinc.orphanagemenu.validator.warehouse;

public class WarehouseItemForm {
	private Long id;
	private String itemName;
	private Float quantity;
	private String dimension;
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WarehouseItemForm [id=" + id + ", itemName=" + itemName
				+ ", quantity=" + quantity + ", dimension=" + dimension + "]";
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	

}
