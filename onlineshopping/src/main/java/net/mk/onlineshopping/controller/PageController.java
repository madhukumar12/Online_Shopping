package net.mk.onlineshopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.mk.onlineshopping.exception.ProductNotFoundException;
import net.mk.shoppingbackend.dao.CategoryDAO;
import net.mk.shoppingbackend.dao.ProductDAO;
import net.mk.shoppingbackend.dto.Category;
import net.mk.shoppingbackend.dto.Product;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		logger.info("Inside Page Controlle Method");
		mv.addObject("userClickHome", true);
		return mv;

	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;

	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}

	/*
	 * Methods to load all the categories based on the category
	 * 
	 */

	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");

		// passing list of categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClicksAllProducts", true);
		return mv;
	}

	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");

		// Category DAO to fetch a single query
		Category category = null;
		category = categoryDAO.getProduct(id);

		mv.addObject("title", category.getName());
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("category", category);
		mv.addObject("userClickCategoryProducts", true);
		return mv;

	}
	
	/*
	 *  Viewing the Product in the Single Page
	 * */
	
	@RequestMapping(value="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException{
		
		ModelAndView mv = new ModelAndView("page");
		
		Product product = productDAO.getProduct(id);
		
		if(product == null) throw new ProductNotFoundException("Product found");
		
		// update the view count
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClicksShowProduct", true);
		
		return mv;
		
	}
	
		
}
