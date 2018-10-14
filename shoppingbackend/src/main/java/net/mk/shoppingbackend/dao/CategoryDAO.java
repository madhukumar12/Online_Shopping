package net.mk.shoppingbackend.dao;

import java.util.List;
import net.mk.shoppingbackend.dto.Category;

public abstract interface CategoryDAO
{
  public abstract List<Category> list();
  
  public abstract Category getProduct(int paramInt);
}