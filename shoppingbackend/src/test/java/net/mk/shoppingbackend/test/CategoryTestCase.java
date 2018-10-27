package net.mk.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mk.shoppingbackend.dao.CategoryDAO;
import net.mk.shoppingbackend.daoimpl.CategoryDAOImpl;
import net.mk.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;

	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.mk.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");

	}

	/*
	 * @Test public void testAddCategory() { category = new Category();
	 * 
	 * category.setName("Television");
	 * category.setDescripton("This is a televison category");
	 * category.setImageURL("CAT_1.png");
	 * 
	 * assertEquals("Successfully added a category inside the table!",true,
	 * categoryDAO.add(category));
	 * 
	 * 
	 * }
	 */

	/*
	 * @Test public void testGetCategory() {
	 * 
	 * category = categoryDAO.getProduct(3);
	 * assertEquals("Successsfully fetched the product form the db", "Mobile",
	 * category.getName());
	 * 
	 * }
	 */

	/*
	 * @Test public void testUpdateCategory() {
	 * 
	 * category = categoryDAO.getProduct(3); category.setName("Cell Phone");
	 * assertEquals("Successsfully updated the product in the db", true,
	 * categoryDAO.update(category));
	 * 
	 * }
	 */

	/*
	 * @Test public void testDeteteCategory() {
	 * 
	 * category = categoryDAO.getProduct(3);
	 * assertEquals("Successsfully updated the product in the db", true,
	 * categoryDAO.delete(category));
	 * 
	 * }
	 */

	/*
	 * @Test public void testListCategory() {
	 * 
	 * assertEquals("Successsfully fethced the list of categories from the table",
	 * 2, categoryDAO.list().size());
	 * 
	 * }
	 */

	//@Test
	public void testCURDoperation() {

		// Add all the categories

		category = new Category();
		category.setName("Laptop");
		category.setDescripton("This is a laptop category");
		category.setImageURL("CAT_1.png");

		assertEquals("Successfully added a category inside the table!", true, categoryDAO.add(category));

		category = new Category();

		category.setName("Televison");
		category.setDescripton("This is a television category");
		category.setImageURL("CAT_2.png");

		assertEquals("Successfully added a category inside the table!", true, categoryDAO.add(category));

		category = new Category();

		category.setName("Mobile");
		category.setDescripton("This is a television category");
		category.setImageURL("CAT_3.png");

		assertEquals("Successfully added a category inside the table!", true, categoryDAO.add(category));

		// Fetching and updating the category
		category = categoryDAO.getProduct(14);
		category.setName("TV");
		assertEquals("Successsfully updated the product in the db", true, categoryDAO.update(category));

		// Delete the category
		assertEquals("Successsfully updated the product in the db", true, categoryDAO.delete(category));

		// Fetch the list of categories
		assertEquals("Successsfully fethced the list of categories from the table", 11, categoryDAO.list().size());

	}

}
