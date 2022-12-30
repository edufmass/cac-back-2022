package ar.com.movius.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.movius.dao.IProductDAO;
import ar.com.movius.daoimpl.ProductDAOMariaDBImpl;
import ar.com.movius.domain.Product;

@WebServlet("/EditProduct")
public class UpdateProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
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
		getServletContext().getRequestDispatcher("/products/edit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IProductDAO dao = new ProductDAOMariaDBImpl();
		
		String titulo = req.getParameter("titulo");
		Double precio = Double.parseDouble(req.getParameter("precio"));
		
		Date fechaAlta;
		try {
			fechaAlta = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("fecha_alta"));
		} catch (ParseException e1) {
			fechaAlta = new Date();
		}
		
		String autor = req.getParameter("autor");
		
		Long id = Long.parseLong(req.getParameter("product_id"));
		Product p;
		
		try {
			p = dao.getById(id);
		} catch (Exception e) {
			p = new Product();
			req.getSession().setAttribute("alert", true);
			req.getSession().setAttribute("success", false);
			req.getSession().setAttribute("msg", "Ocurrió un error al intentar cargar el producto!");
			resp.sendRedirect("UpdateProduct");
		}
		
		p.setTitulo(titulo);
		p.setPrecio(precio);
		p.setFechaAlta(fechaAlta);
		p.setAutor(autor);
		
		try {
			dao.update(p);
			req.getSession().setAttribute("alert", true);
			req.getSession().setAttribute("success", true);
			req.getSession().setAttribute("msg", "Producto actualizado correctamente");
			resp.sendRedirect("FindAllProducts");
		} catch (Exception e) {
			req.getSession().setAttribute("alert", true);
			req.getSession().setAttribute("success", false);
			req.getSession().setAttribute("msg", "Ocurrió un error al intentar actualizar el producto!");
			resp.sendRedirect("UpdateProduct");
			System.out.println(e.toString());
		}
	}
}
