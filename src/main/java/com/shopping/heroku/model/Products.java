package com.shopping.heroku.model;


import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Transient;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Products extends AuditModel {
	
	private static final long serialVersionUID = 1L;

//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Type(type="org.hibernate.type.UUIDCharType")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@NotNull(message = "Product name is required.")
    @Basic(optional = false)
    private String name;
 
    private Double price;
    
    private int availableQuantity;
 
    private String pictureUrl;
    private String addToCardUrl;
    
    private boolean isShow;
    
    @Lob
    private byte[] imageData1;
    
    @Lob
    private byte[] imageData2;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ShoppingCategory shoppingCategory;
    public Products() {}
    
    
    public Products(@NotNull(message = "Product name is required.") String name, Double price, String pictureUrl,
			String addToCardUrl, ShoppingCategory shoppingCategory,boolean isShow,int availableQuantity,byte[] imageData1,byte[] imageData2) {
		this.name = name;
		this.price = price;
		this.availableQuantity = availableQuantity;
		this.pictureUrl = pictureUrl;
		this.addToCardUrl = addToCardUrl;
		this.isShow = isShow;
		this.shoppingCategory = shoppingCategory;
		this.imageData1 = imageData1;
		this.imageData2 = imageData2;
	}

   public Products(@NotNull(message = "Product name is required.") String name, Double price, String pictureUrl,
			String addToCardUrl, ShoppingCategory shoppingCategory,boolean isShow,int availableQuantity) {
		this.name = name;
		this.price = price;
		this.availableQuantity = availableQuantity;
		this.pictureUrl = pictureUrl;
		this.addToCardUrl = addToCardUrl;
		this.isShow = isShow;
		this.shoppingCategory = shoppingCategory;
	}
   



   	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
	
    
	public UUID getId() {
		return id;
	}

	public boolean isShow() {
		return isShow;
	}


	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public byte[] getImageData1() {
		return imageData1;
	}


	public void setImageData1(byte[] imageData1) {
		this.imageData1 = imageData1;
	}


	public byte[] getImageData2() {
		return imageData2;
	}


	public void setImageData2(byte[] imageData2) {
		this.imageData2 = imageData2;
	}


	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public ShoppingCategory getShoppingCategory() {
		return shoppingCategory;
	}

	public void setShoppingCategory(ShoppingCategory shoppingCategory) {
		this.shoppingCategory = shoppingCategory;
	}

	public String getAddToCardUrl() {
		return addToCardUrl;
	}

	public void setAddToCardUrl(String addToCardUrl) {
		this.addToCardUrl = addToCardUrl;
	}
	@Transient
	public String base64EncodedImage1() {
		String base64EncodedImage = Base64.encodeBase64String(this.imageData1);
		return base64EncodedImage;
	}
	@Transient
	public String base64EncodedImage2() {
		String base64EncodedImage = Base64.encodeBase64String(this.imageData2);
		return base64EncodedImage;
	}
    

}
