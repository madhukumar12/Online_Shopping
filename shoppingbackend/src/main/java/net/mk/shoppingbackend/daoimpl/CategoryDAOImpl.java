package net.mk.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;
import net.mk.shoppingbackend.dao.CategoryDAO;
import net.mk.shoppingbackend.dto.Category;
import org.springframework.stereotype.Repository;

@Repository("catetoryDAO")
public class CategoryDAOImpl
  implements CategoryDAO
{
  private static List<Category> categories = new ArrayList();
  
  static
  {
    Category category = new Category();
    category.setId(1);
    category.setName("Television");
    category.setDescripton("This is a televison category");
    category.setImageURL("CAT_1.png");
    
    categories.add(category);
    
    category = new Category();
    category.setId(2);
    category.setName("Mobile");
    category.setDescripton("This is Moblie category");
    category.setImageURL("CAT_2.png");
    
    categories.add(category);
    
    category = new Category();
    
    category.setId(3);
    category.setName("Laptop");
    category.setDescripton("This is Laptop category");
    category.setImageURL("CAT_3.png");
    categories.add(category);
  }
  
  public List<Category> list()
  {
    return categories;
  }
  
  public Category getProduct(int id)
  {
    for (Category category : categories) {
      if (category.getId() == id) {
        return category;
      }
    }
    return null;
  }
}
