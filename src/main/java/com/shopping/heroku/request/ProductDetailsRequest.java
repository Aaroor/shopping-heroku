package com.shopping.heroku.request;

import org.springframework.web.multipart.MultipartFile;

public class ProductDetailsRequest {
	private String productName;
	private String quantity;
	private String productCategory;
	private String isShow;
	private MultipartFile productFile;
	
	
	public MultipartFile getProductFile() {
		return productFile;
	}
	public void setProductFile(MultipartFile productFile) {
		this.productFile = productFile;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	@Override
	public String toString() {
		return "ProductDetailsRequest [productName=" + productName + ", quantity=" + quantity + ", productCategory="
				+ productCategory + ", isShow=" + isShow + "]";
	}
	
	
	
	
}
