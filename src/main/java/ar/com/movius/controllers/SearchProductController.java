package ar.com.movius.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.movius.dao.IProductDAO;
import ar.com.movius.daoimpl.ProductDAOMariaDBImpl;
import ar.com.movius.domain.Product;

@WebServlet("/SearchProducts")
public class SearchProductController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String searchCol = req.getParameter("searchCol");
		String searchKey = req.getParameter("searchKey");
		
		IProductDAO dao = new ProductDAOMariaDBImpl();
		
		List<Product> listAll = new ArrayList<>();
		
		try {
			listAll = dao.search(searchCol, searchKey);
		} catch (Exception e) {
			listAll = List.of(); // lista vacía
			//e.printStackTrace();
		}
		
		req.setAttribute("searchKey", searchKey);
		req.setAttribute("productos", listAll);
	
		// este bloque se usa en todos lados
		//getServletContext().getRequestDispatcher("/listado.jsp").include(req, resp);
		getServletContext().getRequestDispatcher("/products/search.jsp").forward(req, resp);
	}
}
