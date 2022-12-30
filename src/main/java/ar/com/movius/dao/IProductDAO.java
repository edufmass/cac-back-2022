package ar.com.movius.dao;

import java.util.List;
import ar.com.movius.domain.Product;

public interface IProductDAO {

	public Product getById(Long id) throws Exception;
	
	public List<Product> findAll() throws Exception;
	
	public List<Product> search(String column, String key) throws Exception;
	
	public void delete(Long id) throws Exception;
	
	public void update(Product product) throws Exception;
	
	public String create(Product product) throws Exception;
}
