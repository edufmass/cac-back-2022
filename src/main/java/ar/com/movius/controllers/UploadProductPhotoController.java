package ar.com.movius.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ar.com.movius.dao.IProductDAO;
import ar.com.movius.daoimpl.ProductDAOMariaDBImpl;
import ar.com.movius.domain.Product;

@WebServlet("/UploadProductPhoto")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 100
	)
public class UploadProductPhotoController extends HttpServlet {
	
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
		getServletContext().getRequestDispatcher("/products/photo.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		boolean alert = (req.getSession().getAttribute("alert") != null)
				? (boolean) req.getSession().getAttribute("alert")
				: false;
		
		if(alert) {
			doGet(req, resp);
		}
		
		List<Part> fileParts = req.getParts().stream().filter(part -> "image".equals(part.getName())).collect(Collectors.toList());

		String uploadPath = getServletContext().getInitParameter("file-upload");
		File uploads = new File(uploadPath);
		
		for(Part par : fileParts) {
			String fileName = par.getSubmittedFileName();
			String[] arrName = fileName.split(".",2);
			System.out.println(arrName.toString());
			String newName = System.currentTimeMillis() + "";
			
			File file = File.createTempFile(newName, arrName[1], uploads);
			System.out.println(file.getName());
			try (InputStream input = par.getInputStream()) {
			    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
			
			IProductDAO dao = new ProductDAOMariaDBImpl();
			Long id = Long.parseLong(req.getParameter("product_id"));
			Product p;
			
			try {
				p = dao.getById(id);
			} catch (Exception e) {
				p = new Product();
				req.getSession().setAttribute("alert", true);
				req.getSession().setAttribute("success", false);
				req.getSession().setAttribute("msg", "Ocurrió un error al intentar cargar el producto!");
				resp.sendRedirect("UploadProductPhoto");
			}
			
			p.setImage("uploads/" + file.getName());
			
			try {
				dao.update(p);
				req.getSession().setAttribute("alert", true);
				req.getSession().setAttribute("success", true);
				req.getSession().setAttribute("msg", "Foto agregada correctamente");
				resp.sendRedirect("FindAllProducts");
			} catch (Exception e) {
				req.getSession().setAttribute("alert", true);
				req.getSession().setAttribute("success", false);
				req.getSession().setAttribute("msg", "Ocurrió un error al intentar agregar la imagen!");
				resp.sendRedirect("UploadProductPhoto");
				System.out.println(e.toString());
			}
			
		}
		
		
		/*String filePath = getServletContext().getInitParameter("file-upload");
		int maxFileSize = 50 * 1024;
		int maxMemSize = 4 * 1024;
		File file;
		
		boolean isMultiPart = ServletFileUpload.isMultipartContent(req);
		
		if(!isMultiPart) {
			req.getSession().setAttribute("alert", true);
			req.getSession().setAttribute("success", false);
			req.getSession().setAttribute("msg", "Ocurrió un error al intentar cargar la imagen!");
			//resp.sendRedirect("UploadProductPhoto");
			getServletContext().getRequestDispatcher("/UploadProductPhoto").forward(req, resp);
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File("/tmp"));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);
		
		try {
			var imgItem = req.getParameter("image");
			
			List fileItems = upload.parseRequest(req);
			Iterator i = fileItems.iterator();
			
			while(i.hasNext()) {
				FileItem fi = (FileItem)i.next();
				if(!fi.isFormField()) {
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					
					String[] arrName = fileName.split(".");
					String newName = System.currentTimeMillis() + "." + arrName[1];
					file = new File(filePath + newName);
					fi.write(file);
					
					IProductDAO dao = new ProductDAOMariaDBImpl();
					Long id = Long.parseLong(req.getParameter("product_id"));
					Product p;
					
					try {
						p = dao.getById(id);
					} catch (Exception e) {
						p = new Product();
						req.getSession().setAttribute("alert", true);
						req.getSession().setAttribute("success", false);
						req.getSession().setAttribute("msg", "Ocurrió un error al intentar cargar el producto!");
						resp.sendRedirect("UploadProductPhoto");
					}
					
					p.setImage(filePath + newName);
					
					try {
						dao.update(p);
						req.getSession().setAttribute("alert", true);
						req.getSession().setAttribute("success", true);
						req.getSession().setAttribute("msg", "Foto agregada correctamente");
						resp.sendRedirect("FindAllProducts");
					} catch (Exception e) {
						req.getSession().setAttribute("alert", true);
						req.getSession().setAttribute("success", false);
						req.getSession().setAttribute("msg", "Ocurrió un error al intentar agregar la imagen!");
						resp.sendRedirect("UploadProductPhoto");
						System.out.println(e.toString());
					}
				}
			}
			
		} catch (Exception e) {
			
		}*/
		
	}
}
