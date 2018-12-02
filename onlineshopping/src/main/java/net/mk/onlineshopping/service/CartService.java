package net.mk.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mk.onlineshopping.model.UserModel;
import net.mk.shoppingbackend.dao.CartLineDAO;
import net.mk.shoppingbackend.dao.ProductDAO;
import net.mk.shoppingbackend.dto.Cart;
import net.mk.shoppingbackend.dto.CartLine;
import net.mk.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private HttpSession session;

	// cart of the user who has logged in
	private Cart getCart() {
		return ((UserModel) session.getAttribute("userModel")).getCart();

	}

	// returns the entire cart line
	public List<CartLine> getCartLines() {
		Cart cart = this.getCart();
		return cartLineDAO.list(cart.getId());

	}

	public String manageCartLine(int cartLineId, int count) {

		CartLine cartLine = cartLineDAO.get(cartLineId);
		if (cartLine == null) {
			return "result=error";
		} else {

			Product product = cartLine.getProduct();
			double oldTotal = cartLine.getTotal();

			if (product.getQuantity() < count) {
				return "result=unavailable";
			}

			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitprice());
			cartLine.setTotal(product.getUnitprice() * count);
			cartLineDAO.update(cartLine);
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			return "result=updated";
		}

	}

	public String deleteCartLineId(int cartLineId) {
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if (cartLine != null) {

			// update the cart
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);

			// remove the cart line
			cartLineDAO.delete(cartLine);

			return "result=deleted";
		} else {
			return "result=error";
		}
	}

	public String addCartLine(int productId) {
		String response = null;

		Cart cart = this.getCart();
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);

		if (cartLine == null) {
			cartLine = new CartLine();

			Product product = productDAO.getProduct(productId);
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitprice());
			cartLine.setProductCount(1);

			cartLine.setTotal(product.getUnitprice());
			cartLine.setAvailable(true);

			cartLineDAO.add(cartLine);

			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);

			response = "result=added";
		} else {
			// check if the cartLine has reached the maximum count
			if (cartLine.getProductCount() < 3) {
				// update the productcount for the cart line
				response = this.manageCartLine(cartLine.getId(), cartLine.getProductCount() + 1);

			} else {
				response = "result=maximum";
			}
		}
		return response;
	}

	
}
