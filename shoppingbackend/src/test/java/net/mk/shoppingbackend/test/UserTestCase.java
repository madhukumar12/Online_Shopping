package net.mk.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mk.shoppingbackend.dao.UserDAO;
import net.mk.shoppingbackend.dto.Address;
import net.mk.shoppingbackend.dto.Cart;
import net.mk.shoppingbackend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;

	private static UserDAO userDAO;

	private User user;

	private Cart cart;
	private Address address;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.mk.shoppingbackend");
		context.refresh();

		userDAO = (UserDAO) context.getBean("userDAO");

	}

	/*
	 * @Test public void testAdd() {
	 * 
	 * user = new User(); user.setFirstName("Amithab"); user.setLastName("Bachan");
	 * user.setEmail("ab@gmail.com"); user.setContactNumber("123456789");
	 * user.setRole("USER"); user.setPassword("password");
	 * 
	 * assertEquals("Failed to Add the user", true, userDAO.addUser(user));
	 * 
	 * address = new Address();
	 * 
	 * address.setAddressLineOne("51 society, Ramnage");
	 * address.setAddressLineTwo("Near Hanuman Road"); address.setCity("Mumbai");
	 * address.setState("Maharastra"); address.setCountry("India");
	 * address.setPostalCode("4343"); address.setBilling(true);
	 * 
	 * // linking the user with the address using address
	 * address.setUserid(user.getId());
	 * 
	 * assertEquals("Failed to add Address", true, userDAO.addAddress(address));
	 * 
	 * if (user.getRole().equals("USER")) {
	 * 
	 * // Create the cart for the user cart = new Cart();
	 * 
	 * cart.setUser(user);
	 * 
	 * assertEquals("Failed to add Cart", true, userDAO.addCart(cart));
	 * 
	 * // add a shipping address for this user address = new Address();
	 * address.setAddressLineOne("51 society, Ramnage");
	 * address.setAddressLineTwo("Near Hanuman Road"); address.setCity("Mumbai");
	 * address.setState("Maharastra"); address.setCountry("India");
	 * address.setPostalCode("4343"); address.setShipping(true);
	 * 
	 * // link it with the user address.setUserid(user.getId());
	 * 
	 * assertEquals("Failed to add Shipping address",true,userDAO.addAddress(address
	 * ));
	 * 
	 * 
	 * } }
	 */

	/*
	 * @Test public void testAdd() {
	 * 
	 * user = new User(); user.setFirstName("Amithab"); user.setLastName("Bachan");
	 * user.setEmail("ab@gmail.com"); user.setContactNumber("123456789");
	 * user.setRole("USER"); user.setPassword("password");
	 * 
	 * if (user.getRole().equals("USER")) {
	 * 
	 * // Create the cart for the user cart = new Cart();
	 * 
	 * cart.setUser(user);
	 * 
	 * user.setCart(cart); }
	 * 
	 * assertEquals("Failed to add the user", true, userDAO.addUser(user));
	 * 
	 * }
	 */
	/*
	 * @Test public void testUpdateCart() {
	 * 
	 * user = userDAO.getByEmail("ab@gmail.com");
	 * 
	 * System.out.println("User details " + user); cart = user.getCart();
	 * 
	 * System.out.println("Cart Details " + cart); cart.setGrandTotal(87989878);
	 * cart.setCartLines(2);
	 * 
	 * assertEquals("Failed to updat the cart!", true, userDAO.updateCart(cart));
	 * 
	 * }
	 * 
	 */

	/*
	 * @Test public void testAddAddress() {
	 * 
	 * user = new User(); user.setFirstName("Hirthink"); user.setLastName("Roshan");
	 * user.setEmail("hr@gmail.com"); user.setContactNumber("123456789");
	 * user.setRole("USER"); user.setPassword("password");
	 * 
	 * assertEquals("Failed to Add the user", true, userDAO.addUser(user));
	 * 
	 * address = new Address();
	 * 
	 * address.setAddressLineOne("51 society, H-nagar");
	 * address.setAddressLineTwo("Near rama Road"); address.setCity("Bangalore");
	 * address.setState("Karnataka"); address.setCountry("India");
	 * address.setPostalCode("4343"); address.setBilling(true);
	 * 
	 * address.setUser(user);
	 * 
	 * assertEquals("Failed to Add billing address", true,
	 * userDAO.addAddress(address));
	 * 
	 * address = new Address();
	 * 
	 * address.setAddressLineOne("59 Rajajinagar");
	 * address.setAddressLineTwo("Near M Road"); address.setCity("Bangalore");
	 * address.setState("Karnataka"); address.setCountry("India");
	 * address.setPostalCode("4343"); address.setShipping(true);
	 * 
	 * address.setUser(user);
	 * 
	 * assertEquals("Failed to add Shipping address", true,
	 * userDAO.addAddress(address));
	 * 
	 * 
	 * }
	 */

	/*
	 * @Test public void testAddAddress() {
	 * 
	 * user = userDAO.getByEmail("hr@gmail.com");
	 * 
	 * // adding shipping address to table address = new Address();
	 * 
	 * address.setAddressLineOne("70 society, Vijarnagar");
	 * address.setAddressLineTwo("Near KR puram Road");
	 * address.setCity("Bangalore"); address.setState("Karnataka");
	 * address.setCountry("India"); address.setPostalCode("5600021");
	 * address.setBilling(true);
	 * 
	 * address.setUser(user);
	 * 
	 * assertEquals("Failed to Add billing address", true,
	 * userDAO.addAddress(address));
	 * 
	 * }
	 */

	@Test
	public void testGetAddress() {

		user = userDAO.getByEmail("hr@gmail.com");

		assertEquals("Failed to fetch the list of address and size doesn't match", 2,
				userDAO.listShippingAddress(user).size());

		assertEquals("Failed to fetch the billing address ans size doesn't match", "Bangalore",
				userDAO.getBillingAddress(user).getCity());

	}

}
