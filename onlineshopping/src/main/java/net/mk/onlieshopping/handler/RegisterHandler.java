package net.mk.onlieshopping.handler;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import net.mk.onlieshopping.model.RegisterModel;
import net.mk.shoppingbackend.dao.UserDAO;
import net.mk.shoppingbackend.dto.Address;
import net.mk.shoppingbackend.dto.Cart;
import net.mk.shoppingbackend.dto.User;

@Component
public class RegisterHandler implements Serializable {

	@Autowired
	private UserDAO userDAO;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterModel init() {
		return new RegisterModel();

	}

	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}

	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}

	// validation for the password
	public String validateUser(User user, MessageContext error) {
		String transitonValue = "success";

		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			transitonValue = "failure";

			error.addMessage(new MessageBuilder().error().source("confirmPassword")
					.defaultText("Password does not match the confirm password!").build());
		}

		// check the uniqueness of the email id
		if (userDAO.getByEmail(user.getEmail()) != null) {
			transitonValue = "failure";

			error.addMessage(new MessageBuilder().error().source("email")
					.defaultText("Email address already exists in our database").build());

		}

		return transitonValue;

	}

	public String saveAll(RegisterModel model) {
		String transitionvalue = "success";

		// fetch the user
		User user = model.getUser();

		if (user.getRole().equals("USER")) {

			Cart cart = new Cart();

			cart.setUser(user);
			user.setCart(cart);
		}

		// save the user
		userDAO.addUser(user);

		// get the address

		Address billing = model.getBilling();

		billing.setUser(user);
		billing.setBilling(true);

		userDAO.addAddress(billing);

		return transitionvalue;

	}
}
