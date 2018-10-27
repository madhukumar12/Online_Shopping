package net.mk.shoppingbackend.daoimpl;

import java.util.List;
import net.mk.shoppingbackend.dao.CategoryDAO;
import net.mk.shoppingbackend.dto.Category;

import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Category> list() {

		String selectActiveCategory = "FROM Category WHERE active= :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		return query.getResultList();
	}

	public Category getProduct(int id) {
		System.out.println("Inside getProduct impl");
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	public boolean add(Category category) {

		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * updating a single category
	 */
	@Override
	public boolean update(Category category) {

		try {
			// update the category in the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try {
			// update the category in the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
