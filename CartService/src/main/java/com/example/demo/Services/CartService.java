package com.example.demo.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.CartRepo;
import com.example.demo.dmo.CartItem;
import com.example.demo.dto.CartRequest;
import com.example.demo.dto.CartResponse;
import com.example.demo.models.Cart;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartService {
	
	@Autowired
	private CartRequest cartrequest;
	@Autowired
	private CartResponse cartresponse;
	@Autowired
	private CartRepo cartrepository;
	
	public List<CartResponse> getCartByUserId(Integer userId)
	{
		return cartrepository.findByUserId(userId)
				             .stream()
				             .map(cart->{
				             return CartResponse.builder()
				            		 .cts(cart.getCts())
				            		 .build();
				             }).toList();
				
	}
	
	public void createCart(CartRequest cartrequest)
	{
		Cart cart=Cart.builder()
				.userId(cartrequest.getUserId())
				.cts(new ArrayList<CartItem> ())
				.build();
		
		cart.addItem(cartrequest.getItem());
		
		cartrepository.save(cart);
				
	}
	
	public void updateCartByUserId(Integer userId,CartItem item)
	{
		Cart cart=(cartrepository.findByUserId(userId)).get(0);
		cart.addItem(item);
		
		cartrepository.save(cart);
		
	}

}
