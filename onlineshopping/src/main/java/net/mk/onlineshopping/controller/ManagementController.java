package net.mk.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.mk.onlieshopping.validator.ProductValidator;
import net.mk.onlineshopping.util.FileUploadUtility;
import net.mk.shoppingbackend.dao.CategoryDAO;
import net.mk.shoppingbackend.dao.ProductDAO;
import net.mk.shoppingbackend.dto.Category;
import net.mk.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage/")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClicksManageProducts", true);
		mv.addObject("title", "Manage Products");

		// set few of the fields
		Product newproduct = new Product();
		newproduct.setSupplierId(1);
		newproduct.setActive(true);

		mv.addObject("product", newproduct);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product Saved Successfully!.");
			} else if (operation.equals("category")) {
				mv.addObject("message", "Category Saved Successfully!.");

			}
		}

		return mv;

	}

	@RequestMapping(value = "{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClicksManageProducts", true);
		mv.addObject("title", "Edit Product");
		Product product = productDAO.getProduct(id);

		// set the product data fetched from the database
		mv.addObject("product", product);
		return mv;

	}

	// handling product submission
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results,
			Model model, HttpServletRequest request) {

		if (mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, results);

		} else {
			if (!mProduct.getFile().getOriginalFilename().equals(""))
				new ProductValidator().validate(mProduct, results);

		}

		// check if there are any errors
		if (results.hasErrors()) {

			model.addAttribute("userClicksManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for the Product Submissoin!");

			return "page";
		}

		logger.info(mProduct.toString());

		if (mProduct.getId() == 0) {
			// create new product record if id is zero
			productDAO.add(mProduct);

		} else {
			// update the product record if the id is not zero
			productDAO.update(mProduct);
		}

		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}

		return "redirect:/manage/products?operation=product";

	}

	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {

		Product product = productDAO.getProduct(id);
		boolean isActive = product.isActive();

		// activating or deactivating based on the value of the active filed
		product.setActive(!product.isActive());

		productDAO.update(product);

		return (isActive) ? "Your have successfully deactivated the product with id " + product.getId()
				: "You have successfylly activated the product with id " + product.getId();

	}

	// To Handle category submission
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String handleCategorySumission(@ModelAttribute Category category) {
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";

	}

	// return categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();

	}

	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();

	}

}
