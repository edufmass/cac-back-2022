package ar.com.movius.controllers;

import ar.com.movius.dao.IProductDAO;
import ar.com.movius.daoimpl.ProductDAOMariaDBImpl;
import ar.com.movius.domain.Product;

public class GetProductController {

	public Product getProduct(Long id) {
		IProductDAO dao = new ProductDAOMariaDBImpl();
		
		Product p;
		
		try {
			p = dao.getById(id);
		} catch (Exception e) {
			p = null;
		}
		
		if(p != null) {
			return p;
		} else {
			return null;
		}
	}
}
