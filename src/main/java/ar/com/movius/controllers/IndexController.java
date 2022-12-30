package ar.com.movius.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.movius.dao.IProductDAO;
import ar.com.movius.daoimpl.ProductDAOMariaDBImpl;
import ar.com.movius.domain.Product;

@WebServlet("/Index")
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		
		if(req.getSession().getAttribute("logged_in") == null) {
			req.getSession().setAttribute("logged_in", false);
		}
		
		IProductDAO dao = new ProductDAOMariaDBImpl();
		
		List<Product> listAll = new ArrayList<>();
		
		try {
			listAll = dao.findAll();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		System.out.println("index controller");
		System.out.println(listAll.toString());
		req.setAttribute("productos", listAll);
	
		// este bloque se usa en todos lados
		//getServletContext().getRequestDispatcher("/listado.jsp").include(req, resp);
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
