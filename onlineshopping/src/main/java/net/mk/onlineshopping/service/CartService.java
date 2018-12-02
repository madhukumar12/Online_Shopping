package net.mk.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mk.onlineshopping.model.UserModel;
import net.mk.shoppingbackend.dao.CartLineDAO;
import net.mk.shoppingbackend.dto.Cart;
import net.mk.shoppingbackend.dto.CartLine;

@Service("cartService")
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private HttpSession session;
	
	// cart of the user who has logged in
	private Cart getCart() {
		return ((UserModel)session.getAttribute("userModel")).getCart();
		
	}
	
	// returns the entire cart line
	public List<CartLine> getCartLines() {
		Cart cart = this.getCart();
		return cartLineDAO.list(cart.getId());
		
	}

}
