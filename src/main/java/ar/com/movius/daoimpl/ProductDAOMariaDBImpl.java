package ar.com.movius.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.movius.dao.IProductDAO;
import ar.com.movius.db.AdmConnection;
import ar.com.movius.domain.Product;

public class ProductDAOMariaDBImpl implements IProductDAO {

	@Override
	public Product getById(Long id) throws Exception {
		// 1 necsito la connection
		Connection cnn = AdmConnection.getConnection();
		// 2 armo el sql statement
		String query = "SELECT * FROM producto WHERE id = " + id;
		Statement statement = cnn.createStatement();
		// 3 resulset
		ResultSet result = statement.executeQuery(query);
		// 4 verifico si hay datos
		if(result.next()) {
			this.closeCnn(cnn);
			return this.crearProducto(result);
		}
		return null;
	}

	@Override
	public List<Product> findAll() throws Exception {
		List<Product> res = new ArrayList<Product>();
		// 1 necsito la connection
		Connection cnn = AdmConnection.getConnection();
		// 2 armo el sql statement
		String query = "SELECT * FROM producto";
		Statement statement = cnn.createStatement();
		// 3 resulset
		ResultSet result = statement.executeQuery(query);
		// 4 verifico si hay datos
		while(result.next()) {
			res.add(this.crearProducto(result));
		}
		this.closeCnn(cnn);
		return res;
	}
	
	@Override
	public List<Product> search(String column, String key) throws Exception {
		List<Product> res = new ArrayList<Product>();
		// 1 necsito la connection
		Connection cnn = AdmConnection.getConnection();
		// 2 armo el sql statement
		String query = "SELECT * FROM producto WHERE " + column + " LIKE '%" + key + "%'";
		Statement statement = cnn.createStatement();
		// 3 resulset
		ResultSet result = statement.executeQuery(query);
		// 4 verifico si hay datos
		while(result.next()) {
			res.add(this.crearProducto(result));
		}
		this.closeCnn(cnn);
		return res;
	}

	@Override
	public void delete(Long id) throws Exception {
		String res = "No se elimino nada.";
		// 1 necsito la connection
		Connection cnn = AdmConnection.getConnection();
		// 2 armo el sql statement
		String query = "DELETE FROM producto WHERE id = " + id;
		Statement statement = cnn.createStatement();
		// 3 0 for nothing or X contador
		int result = statement.executeUpdate(query);
		// 4 verifico si hay datos
		if (result > 0) {
			res = "Producto eliminado correctamente";
		}
		this.closeCnn(cnn);
		//return res;
	}

	@Override
	public void update(Product product) throws Exception {
		String res = "No se actualizo";
		// 1 necsito la connection
		Connection cnn = AdmConnection.getConnection();
		// 2 armo el sql statement
		String query = "UPDATE producto SET titulo = ?, precio = ?, autor = ?, img = ? WHERE id = ?";
		PreparedStatement statement = cnn.prepareStatement(query);
		statement.setString(1, product.getTitulo());
		statement.setDouble(2, product.getPrecio());
		statement.setString(3, product.getAutor());
		statement.setString(4, product.getImage());
		statement.setLong(5, product.getId());
		// 3 0 for nothing or X contador
		boolean result = statement.execute();
		
		// 4 verifico si hay datos
		if (result) {
			res = "Se actualizó el producto correctamente";
		}
		
		this.closeCnn(cnn);
	}

	@Override
	public String create(Product product) throws Exception {
		// 1 necsito la connection
		Connection cnn = AdmConnection.getConnection();
		// 2 armo el sql statement
		String query = "INSERT INTO producto (codigo, titulo, precio, fecha_alta, autor, img, tipo_producto_id) ";
		query += "VALUES (?,?,?,?,?,?,?)";
		PreparedStatement statement = cnn.prepareStatement(query);
		statement.setString(1, product.getCodigo());
		statement.setString(2, product.getTitulo());
		statement.setDouble(3, product.getPrecio());
		statement.setDate(4, new java.sql.Date(product.getFechaAlta().getTime()));
		statement.setString(5, product.getAutor());
		statement.setString(6, product.getImage());
		statement.setInt(7, 1);
		// 3 0 for nothing or X contador
		boolean result = statement.execute();
		
		// 4 verifico si hay datos
		if (result) {
			//System.out.println("se inserto el producto correctamente");
		} else {
			//System.out.println("NO se inserto el producto correctamente");
		}
		
		return "algo";
	}
	
	private Product crearProducto(ResultSet result) throws Exception {
		Long idDB = result.getLong("id");
		String codigo = result.getString("codigo");
		String titulo = result.getString("titulo");
		Double precio = result.getDouble("precio");
		Date fechaAlta = result.getDate("fecha_alta");
		String autor = result.getString("autor");
		String image = result.getString("img");
		
		return new Product(idDB, codigo, titulo, precio, fechaAlta, autor, image);
	}
	
	private void closeCnn(Connection cnn) throws Exception {
		cnn.close();
	}
}
