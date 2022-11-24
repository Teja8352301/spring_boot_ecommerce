package com.teja.springapplication.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name="cart")
@Data
@JsonIgnoreProperties(ignoreUnknown = true, 
value = {"hibernateLazyInitializer", "handler", "created"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class Cart {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		        name = "UUID",
		        strategy = "org.hibernate.id.UUIDGenerator"
		    )
	@Column(name="cart_id")
	private String cartId;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="cartId")
	private User userId;
	
	@OneToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH},mappedBy="cartId")
	private List<CartItem> cartItems;
	
	public void setCartItem(CartItem cartItem) {
		if(cartItems == null) {
			cartItems = new ArrayList<CartItem>();
		}
		cartItems.add(cartItem);
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId.getUserId() + "]";
	}
}
