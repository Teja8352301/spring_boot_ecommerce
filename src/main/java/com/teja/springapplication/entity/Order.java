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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name="`order`")
@Data
@JsonIgnoreProperties(ignoreUnknown = true, 
value = {"hibernateLazyInitializer", "handler", "created"})
public class Order {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		        name = "UUID",
		        strategy = "org.hibernate.id.UUIDGenerator"
		    )
	@Column(name="order_id")
	private String orderId;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name="user_id")
	private User userId;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="order_items",
			joinColumns=@JoinColumn(name="order_id"),
			inverseJoinColumns=@JoinColumn(name="product_id")
			)
	private List<Product> products; 
	
	@Column(name="order_price")
	private double orderPrice;

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderPrice=" + orderPrice + "]";
	}


}
