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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

		mv.addObject("userClicksManageProducsts", true);
		mv.addObject("title", "Manage Products");

		// set few of the fields
		Product newproduct = new Product();
		newproduct.setSupplierId(1);
		newproduct.setActive(true);

		mv.addObject("product", newproduct);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product Saved Successfully!.");
			}
		}

		return mv;

	}

	// handling product submission
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model, HttpServletRequest request) {

		
		// check if there are any erros
		if(results.hasErrors()) {
			 
			model.addAttribute("userClicksManageProducsts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for the Product Submissoin!");

			
			return "page";
		}
		
		logger.info(mProduct.toString());

		// create new product record
		productDAO.add(mProduct);
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";

	}

	// return categoreis for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();

	}

}
