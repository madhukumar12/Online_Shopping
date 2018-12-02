package net.mk.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.mk.onlineshopping.model.UserModel;
import net.mk.shoppingbackend.dao.UserDAO;
import net.mk.shoppingbackend.dto.User;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserDAO userDAO;

	private UserModel userModel = null;

	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		if (session.getAttribute("userModel") == null) {

			// Add the uesrModel
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			User user = userDAO.getByEmail(authentication.getName());
			if (user != null) {
				userModel = new UserModel();
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName() + " " + user.getLastName());

				if (userModel.getRole().equals("USER")) {
					// set the cart only if the user
					userModel.setCart(user.getCart());

					System.out.println("Cart data is " + user.getCart());
				}

				// Set the user Model in the session
				System.out.println("sesssion data is " + session.getAttribute("userModel"));
				session.setAttribute("userModel", userModel);

				return userModel;
			}

		}

		return (UserModel) session.getAttribute("userModel");

	}

}
