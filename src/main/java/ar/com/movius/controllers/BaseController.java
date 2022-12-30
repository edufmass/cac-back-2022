package ar.com.movius.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void goTo(String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().getRequestDispatcher(path).forward(req, resp);
	}
	
	public void verifyAccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("logged_in") == null) {
			req.getSession().setAttribute("logged_in", false);
		}
		
		if(!(boolean)req.getSession().getAttribute("logged_in")) {
			req.getServletContext().getRequestDispatcher("/Index").forward(req, resp);
		}
	}
}
