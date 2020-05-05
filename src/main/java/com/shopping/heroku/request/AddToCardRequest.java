package com.shopping.heroku.request;

import java.util.UUID;

public class AddToCardRequest {
	private int quantity;
	private UUID productId;
	private UUID orderId;
	private Object deliveryType;
	private String isShow;
	private UUID categoryId;
	private UUID userId;
	
	public Object getDeliveryType() {
		return deliveryType;
	}
	


	@Override
	public String toString() {
		return "AddToCardRequest [quantity=" + quantity + ", productId=" + productId + ", orderId=" + orderId
				+ ", deliveryType=" + deliveryType + ", isShow=" + isShow + ", categoryId=" + categoryId + ", userId="
				+ userId + "]";
	}



	public UUID getCategoryId() {
		return categoryId;
	}
	

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public void setCategoryId(UUID categoryId) {
		this.categoryId = categoryId;
	}


	public void setDeliveryType(Object deliveryType) {
		this.deliveryType = deliveryType;
	}
	public UUID getOrderId() {
		return orderId;
	}
	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
//	public long getProductId() {
//		return productId;
//	}
//	public void setProductId(long productId) {
//		this.productId = productId;
//	}
	public UUID getProductId() {
		return productId;
	}
	public void setProductId(UUID productId) {
		this.productId = productId;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	
}
