package net.mk.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mk.shoppingbackend.dao.CartLineDAO;
import net.mk.shoppingbackend.dto.Cart;
import net.mk.shoppingbackend.dto.CartLine;

@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CartLine get(int id) {
		return null;
	}

	@Override
	public boolean add(CartLine cartLine) {
		return false;
	}

	@Override
	public boolean update(CartLine cartLine) {
		return false;
	}

	@Override
	public boolean delete(CartLine cartLine) {
		return false;
	}

	@Override
	public List<CartLine> list(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId";
		return sessionFactory.getCurrentSession().createQuery(query, CartLine.class).setParameter("cartId", cartId)
				.getResultList();
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId AND available = :available";
		return sessionFactory.getCurrentSession().createQuery(query, CartLine.class).setParameter("cartId", cartId)
				.setParameter("available", true).getResultList();
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		
		try {
		String query = "FROM CartLine WHERE cartId = :cartId AND product.id = :productId";
		return sessionFactory.getCurrentSession().createQuery(query, CartLine.class).setParameter("cartId", cartId)
				.setParameter("productId", productId).getSingleResult();
		}catch (Exception ex) {
			return null;
		}
	}
	
	
	//This method is related to cart
	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		}
	}


}
