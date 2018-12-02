package net.mk.shoppingbackend.dao;

import java.util.List;

import net.mk.shoppingbackend.dto.Product;

public interface ProductDAO {

	public Product getProduct(int productId);

	public List<Product> list();

	public boolean add(Product product);

	public boolean update(Product product);

	public boolean delete(Product product);

	// business methods
	List<Product> listActiveProducts();

	List<Product> listActiveProductsByCategory(int categoryId);

	List<Product> getLatestActiveProducts(int count);

}
