package ar.com.movius.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.movius.dao.IProductDAO;
import ar.com.movius.daoimpl.ProductDAOMariaDBImpl;
import ar.com.movius.domain.Product;

@WebServlet("/CreateProduct")
@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 1,
	maxFileSize = 1024 * 1024 * 10,
	maxRequestSize = 1024 * 1024 * 100
)
public class CreateProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getSession().getAttribute("logged_in") == null) {
			req.getSession().setAttribute("logged_in", false);
		}
		
		if(!(boolean)req.getSession().getAttribute("logged_in")) {
			req.getServletContext().getRequestDispatcher("/Index").forward(req, resp);
		}
		
		req.getServletContext().getRequestDispatcher("/products/create.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IProductDAO dao = new ProductDAOMariaDBImpl();
		
		String codigo = req.getParameter("codigo");
		String titulo = req.getParameter("titulo");
		Double precio = Double.parseDouble(req.getParameter("precio"));
		
		Date fechaAlta;
		try {
			fechaAlta = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("fecha_alta"));
		} catch (ParseException e1) {
			fechaAlta = new Date();
		}
		
		String autor = req.getParameter("autor");
		
		String image = "uploads/default.jpg";
		
		Product p = new Product(codigo, titulo, precio, fechaAlta, autor, image);
		
		try {
			dao.create(p);
			req.getSession().setAttribute("alert", true);
			req.getSession().setAttribute("success", true);
			req.getSession().setAttribute("msg", "Producto agregado correctamente");
			//resp.sendRedirect("FindAllProducts");
			req.getServletContext().getRequestDispatcher("/FindAllProducts").forward(req, resp);
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			req.getSession().setAttribute("alert", true);
			req.getSession().setAttribute("success", false);
			req.getSession().setAttribute("msg", "Ya existe el código ingresado!");
			resp.sendRedirect("CreateProduct");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
}
