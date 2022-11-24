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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name="product")
@Data
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
	@Column(name="product_name",nullable = false)
	private String productName;
	
	@Max(value=1000,message="The Value Should be Less than 1000")
	@Min(value=100,message="The Value Should be greater than 100")
	@Column(name="list_price",nullable = false)
	private double listPrice;
	
	@Column(name="image_url",nullable = false)
	@NotNull(message="The field value should not be null")
	private String imageUrl;
	
	@Column(name="description",nullable = false)
	private String description;
	
	@Column(name="sale_price",nullable = false)
	@Max(value=1000,message="The Value Should be Less than 1000")
	@Min(value=100,message="The Value Should be greater than 100")
	private double salePrice;
	
	@Column(name="enable_product",nullable = false)
	private boolean enableProduct;

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", listPrice=" + listPrice
				+ ", imageUrl=" + imageUrl + ", description=" + description + ", salePrice=" + salePrice
				+ ", enableProduct=" + enableProduct + "]";
	}
	
	

}
