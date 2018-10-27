package net.mk.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mk.shoppingbackend.dao.ProductDAO;
import net.mk.shoppingbackend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;

	private static ProductDAO productDAO;

	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.mk.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
	}

/*	@Test
	public void testCURDProduct() {
		
		// Create operation
		product = new Product();
		product.setName("Iphone selfie");
		product.setBrand("Apple");
		product.setDescription("This is one of the best apple phone ever built");
		product.setUnitprice(90000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		
		assertEquals("something went worng while inserting a new Product",true, productDAO.add(product));
		
		// reading and updating the categoyr
		product = productDAO.getProduct(2);
		product.setName("Samsung S7 edge");
		assertEquals("Something went woring while updating the proudct", true, productDAO.update(product));
		
		assertEquals("Something went woring while deleting the records",true, productDAO.delete(product));
		
		assertEquals("Something went worng while fetching the products",6, productDAO.list().size());
				
		
	}*/
	
	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while getting active proudcts",5, productDAO.listActiveProducts().size());
	}
	
	@Test
	public void testListActiveProductsByCategoryId() {
		assertEquals("Something went worng while getting records",3, productDAO.listActiveProductsByCategory(3).size());
		assertEquals("Something went worng while getting records",2, productDAO.listActiveProductsByCategory(1).size());

		
	}
	
	@Test
	public void testGetLatestActiveProducts() {
		assertEquals("Something went wrong while getting records",4,productDAO.getLatestActiveProducts(4).size());
		
	}

}
