package net.mk.shoppingbackend.dao;

import java.util.List;


import net.mk.shoppingbackend.dto.Category;

public  interface CategoryDAO {
	
	public  Category getProduct(int paramInt);
	public  List<Category> list();
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);



}