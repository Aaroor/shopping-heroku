package com.shopping.heroku.request;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ProductFormWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productName;
	private int quantity;
	private String productCategory;
	private boolean isShow;
	private double unitPrice;
	private String prodShow;
	private UUID productId;
	
	
	
	
	
	public UUID getProductId() {
		return productId;
	}
	public void setProductId(UUID productId) {
		this.productId = productId;
	}
	public String getProdShow() {
		return prodShow;
	}
	public void setProdShow(String prodShow) {
		this.prodShow = prodShow;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public boolean isShow() {
		return isShow;
	}
	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}
	@Override
	public String toString() {
		return "ProductFormWrapper [productName=" + productName + ", quantity=" + quantity + ", productCategory="
				+ productCategory + ", isShow=" + isShow + ", unitPrice=" + unitPrice + ", prodShow=" + prodShow + "]";
	}
	
	

}
