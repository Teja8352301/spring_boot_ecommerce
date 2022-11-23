package com.teja.springapplication.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="product")
@JsonIgnoreProperties(ignoreUnknown = true, 
value = {"hibernateLazyInitializer", "handler", "created"})
public class Product {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		        name = "UUID",
		        strategy = "org.hibernate.id.UUIDGenerator"
		    )
	@Column(name="product_id")
	private String productId;
	
	@NotNull(message="The field value should not be null")
	@Length(min = 3, max = 15,message="The field value should be greater than 3 and less than 15")
	@Column(name="product_name")
	private String productName;
	
	@Column(name="list_price")
	private double listPrice;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="description")
	private String description;
	
	@Column(name="sale_price")
	private double salePrice;
	
	@Column(name="enable_product")
	private boolean enableProduct;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.ALL})
	@JoinTable(
			name="cart_items",
			joinColumns=@JoinColumn(name="product_id"),
			inverseJoinColumns=@JoinColumn(name="cart_id")
			)
	List<Cart> carts;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.ALL})
	@JoinTable(
			name="order_items",
			joinColumns=@JoinColumn(name="product_id"),
			inverseJoinColumns=@JoinColumn(name="order_id")
			)
	List<Order> orders;

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", listPrice=" + listPrice
				+ ", imageUrl=" + imageUrl + ", description=" + description + ", salePrice=" + salePrice
				+ ", enableProduct=" + enableProduct + "]";
	}
	
	

}
