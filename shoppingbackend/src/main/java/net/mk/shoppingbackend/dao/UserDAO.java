package net.mk.shoppingbackend.dao;


import java.util.List;

import net.mk.shoppingbackend.dto.Address;
import net.mk.shoppingbackend.dto.Cart;
import net.mk.shoppingbackend.dto.User;

public interface UserDAO {

	boolean addUser(User user);
	
	User getByEmail(String email);

	boolean addAddress(Address address);
	
	Address getBillingAddress(User user);
	
	List<Address> listShippingAddress(User user);

	boolean updateCart(Cart cart);
}
