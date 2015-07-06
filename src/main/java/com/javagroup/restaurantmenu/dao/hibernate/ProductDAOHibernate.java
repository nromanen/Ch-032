package com.javagroup.restaurantmenu.dao.hibernate;

import com.javagroup.restaurantmenu.dao.ProductDAO;
import com.javagroup.restaurantmenu.model.Product;

public class ProductDAOHibernate 
				extends GenericDAOHibernate<Product>
 				implements ProductDAO {

	@Override
	public Class<Product> getPersistentClass() {
		return Product.class;
	}

}
