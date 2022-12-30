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

import ar.com.movius.dao.IUserDAO;
import ar.com.movius.daoimpl.UserDAOMariaDBImpl;

@WebServlet("/Login")
public class UserLoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/users/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IUserDAO dao = new UserDAOMariaDBImpl();
		
		String user = req.getParameter("usr4455");
		String pass = req.getParameter("pass5577");
		
		try {
			boolean logged_in = dao.login(user, pass);
			if(logged_in) {
				req.getSession().setAttribute("logged_in", true);
			} else {
				req.getSession().setAttribute("alert", true);
				req.getSession().setAttribute("success", false);
				req.getSession().setAttribute("msg", "Usuario o contraseña incorrecto!");
				resp.sendRedirect("Login");
			}
			getServletContext().getRequestDispatcher("/Index").forward(req, resp);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
