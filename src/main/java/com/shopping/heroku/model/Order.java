package com.shopping.heroku.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders")
public class Order extends AuditModel {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Type(type="org.hibernate.type.UUIDCharType")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	private String status;
	private boolean isComplete;
	private String ipAddress;
	@JsonManagedReference
	@OneToMany(mappedBy = "pk.order",fetch = FetchType.EAGER)
	@Valid
	private List<OrderProduct> orderProducts = new ArrayList<>();
	@JsonIgnore 
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billing_id", referencedColumnName = "id")
    private BillingDetails billingDetails;
	
	@JsonIgnore 
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private PaymentDetails paymentDetails;
	
	private double deliveryCharge;
	private boolean isDeliveryOrPickupDone;
	
	

	
	public Order() {}
	
	public Order(String status, @Valid List<OrderProduct> orderProducts) {
		this.status = status;
		this.orderProducts = orderProducts;
	}

	public boolean isDeliveryOrPickupDone() {
		return isDeliveryOrPickupDone;
	}

	public void setDeliveryOrPickupDone(boolean isDeliveryOrPickupDone) {
		this.isDeliveryOrPickupDone = isDeliveryOrPickupDone;
	}

	public Order(String status) {
		this.status = status;
	}

	public Order(String ipAddress,boolean isComplete) {
		this.isComplete = isComplete;
		this.ipAddress = ipAddress;
	}

	public Order(String ipAddress,boolean isComplete,double deliveryCharge) {
		super();
		this.isComplete = isComplete;
		this.ipAddress = ipAddress;
		this.deliveryCharge = deliveryCharge;
	}

	@Transient
	public Double getTotalOrderPrice() {
		double sum = 0D;
		List<OrderProduct> orderProducts = getOrderProducts();
		for (OrderProduct op : orderProducts) {
			sum += op.getTotalPrice();
		}
		return sum;
	}
	
	@Transient
	public Double getTotalOrderPriceWithDeliveryCharge() {
		double sum = 0D;
		List<OrderProduct> orderProducts = getOrderProducts();
		for (OrderProduct op : orderProducts) {
			sum += op.getTotalPrice();
		}
		sum +=this.deliveryCharge;
		return sum;
	}

	@Transient
	public int getNumberOfProducts() {
		return this.orderProducts.size();
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

	public void setId(UUID id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	public BillingDetails getBillingDetails() {
		return billingDetails;
	}

	public void setBillingDetails(BillingDetails billingDetails) {
		this.billingDetails = billingDetails;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	
	public double getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}


	
}
