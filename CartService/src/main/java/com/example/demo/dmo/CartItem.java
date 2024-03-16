package com.example.demo.dmo;

import java.util.Date;

import lombok.Data;


@Data
public class CartItem {
	private Integer productId;
	private Integer quantity;
	private String deliveryDate;


}
