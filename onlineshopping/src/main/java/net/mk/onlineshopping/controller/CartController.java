package net.mk.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.mk.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartServicve;

	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
		ModelAndView mv = new ModelAndView("page");

		if (result != null) {
			switch (result) {
			case "updated":
				mv.addObject("message", "CartLine has been updated successfully!");
				break;
			case "added":
				mv.addObject("message", "Product has been added to cart successfully!");
				break;
			case "maximum":
				mv.addObject("message", "CartLine has reached to maximum count!");
				break;
			case "unavailable":
				mv.addObject("message", "Product is not available!");
				break;

			case "error":
				mv.addObject("message", "Something went wrong!");
				break;
			case "deleted":
				mv.addObject("message", "CartLine has been removed successfully!");
				break;

			}
		}
		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart", true);
		mv.addObject("cartLines", cartServicve.getCartLines());
		return mv;

	}

	@RequestMapping("/{cartLineId}/update")
	public String updateCart(@PathVariable int cartLineId, @RequestParam int count) {

		String response = cartServicve.manageCartLine(cartLineId, count);
		return "redirect:/cart/show?" + response;
	}

	@RequestMapping("/{cartLineId}/delete")
	public String deleteCart(@PathVariable int cartLineId) {
		String respone = cartServicve.deleteCartLineId(cartLineId);
		return "redirect:/cart/show?" + respone;

	}

	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable int productId) {
		String respone = cartServicve.addCartLine(productId);
		return "redirect:/cart/show?" + respone;

	}

}
