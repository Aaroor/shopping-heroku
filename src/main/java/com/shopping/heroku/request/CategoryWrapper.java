package com.shopping.heroku.request;

import java.util.UUID;

public class CategoryWrapper {
	
	private String categoryName;
	private UUID categoryId;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public UUID getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(UUID categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "CategoryWrapper [categoryName=" + categoryName + ", categoryId=" + categoryId + "]";
	}
	

}
