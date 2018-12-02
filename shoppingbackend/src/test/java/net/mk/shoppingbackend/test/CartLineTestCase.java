package net.mk.shoppingbackend.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mk.shoppingbackend.dao.CartLineDAO;
import net.mk.shoppingbackend.dao.ProductDAO;
import net.mk.shoppingbackend.dao.UserDAO;
import net.mk.shoppingbackend.dto.Cart;
import net.mk.shoppingbackend.dto.CartLine;
import net.mk.shoppingbackend.dto.Product;
import net.mk.shoppingbackend.dto.User;

public class CartLineTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CartLineDAO cartLineDAO = null;
	private static ProductDAO productDAO = null;
	private static UserDAO userDAO = null;

	private Product product = null;
	private User user = null;
	private Cart cart = null;
	private CartLine cartLine = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.mk.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
		userDAO = (UserDAO) context.getBean("userDAO");
		cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");

	}

	@Test
	public void testAddNewCartLine() {
		user = userDAO.getByEmail("mk@gmail.com");
		cart = user.getCart();
		product = productDAO.getProduct(2);
		cartLine = new CartLine();

		cartLine.setBuyingPrice(product.getUnitprice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitprice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);

		assertEquals("Failed to add the cartLine", true, cartLineDAO.add(cartLine));

		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1);
		assertEquals("Failed to update the cart", true, cartLineDAO.updateCart(cart));
	}

}
