package net.mk.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mk.shoppingbackend.dao.ProductDAO;
import net.mk.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// Get a single record
	@Override
	public Product getProduct(int productId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// list all the products
	@Override
	public List<Product> list() {
		try {
			return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Insert a Product
	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Delete the records
	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);
			// call the update method
			return this.update(product);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active = :active";

		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts, Product.class)
				.setParameter("active", true).getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory, Product.class)
				.setParameter("active", true).setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		String selectLatestActiveProducts = "FROM Product WHERE active = :active ORDER BY id";
		return sessionFactory.getCurrentSession().createQuery(selectLatestActiveProducts, Product.class)
				.setParameter("active", true).setFirstResult(0).setMaxResults(count).getResultList();
	}

}
