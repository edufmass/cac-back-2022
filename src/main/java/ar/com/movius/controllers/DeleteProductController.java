package ar.com.movius.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.movius.dao.IProductDAO;
import ar.com.movius.daoimpl.ProductDAOMariaDBImpl;
import ar.com.movius.domain.Product;

@WebServlet("/DeleteProduct")
public class DeleteProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if(req.getSession().getAttribute("logged_in") == null) {
			req.getSession().setAttribute("logged_in", false);
		}
		
		if(!(boolean)req.getSession().getAttribute("logged_in")) {
			req.getServletContext().getRequestDispatcher("/Index").forward(req, resp);
		}
		
		IProductDAO dao = new ProductDAOMariaDBImpl();
		
		Product p;
		
		try {
			Long id = Long.parseLong(req.getParameter("product_id"));
			p = dao.getById(id);
			System.out.println("id= " + id);
			if(p == null) {
				req.getSession().setAttribute("alert", true);
				req.getSession().setAttribute("success", false);
				req.getSession().setAttribute("msg", "El id no existe!");
			}
		} catch (Exception e) {
			p = null;
			req.getSession().setAttribute("alert", true);
			req.getSession().setAttribute("success", false);
			req.getSession().setAttribute("msg", "Ocurrió un error al intentar cargar el producto!");
		}
		
		
		
		req.setAttribute("product", p);
		getServletContext().getRequestDispatcher("/products/delete.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IProductDAO dao = new ProductDAOMariaDBImpl();
		
		Long product_id = Long.parseLong(req.getParameter("product_id"));
		
		try {
			dao.delete(product_id);
			req.getSession().setAttribute("alert", true);
			req.getSession().setAttribute("success", true);
			req.getSession().setAttribute("msg", "Producto eliminado correctamente");
			resp.sendRedirect("FindAllProducts");
		} catch (Exception e) {
			req.getSession().setAttribute("alert", true);
			req.getSession().setAttribute("success", false);
			req.getSession().setAttribute("msg", "Ocurrió un error al intentar eliminar el producto!");
			resp.sendRedirect("FindAllProducts");
		}
		
		
	}

}
