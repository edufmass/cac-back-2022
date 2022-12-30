<!DOCTYPE html>

<%@page import="java.util.List"%>
<%@page import="ar.com.movius.domain.Product"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Listado</title>

<jsp:include page="../includes/style_script.jsp"></jsp:include>

</head>
<body>

	<jsp:include page="../includes/navbar.jsp"></jsp:include>


	<main class="container">
		<div class="row">
			<div class="colo">

				<h1>Listado de productos</h1>

				<%
				boolean alert = (request.getSession().getAttribute("alert") != null)
						? (boolean) request.getSession().getAttribute("alert")
						: false;

				if (alert) {
					boolean success = (boolean) request.getSession().getAttribute("success");

					if (success) {
				%>
				<div class="alert alert-success alert-dismissible" role="alert">
					<div><%=request.getSession().getAttribute("msg")%></div>
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
				<%
				} else {
				%>
				<div class="alert alert-warning alert-dismissible" role="alert">
					<div><%=request.getSession().getAttribute("msg")%></div>
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
				<%
				}

				request.getSession().removeAttribute("alert");
				request.getSession().removeAttribute("success");
				request.getSession().removeAttribute("msg");
				}
				%>

				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Código</th>
							<th scope="col">Título</th>
							<th scope="col">Precio</th>
							<th scope="col">Fecha de alta</th>
							<th scope="col">Autor</th>
							<th scope="col">Imagen</th>
							<th scope="col" class="text-center">Action</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<Product> productos = (List<Product>) request.getAttribute("productos");

						for (Product p : productos) {
						%>
						<tr>
							<th scope="row"><%=p.getId()%></th>
							<td><%=p.getCodigo()%></td>
							<td>
								<a href="<%=request.getContextPath()%>/ViewProduct?product_id=<%=p.getId()%>" >
									<%=p.getTitulo()%>
								</a>
							</td>
							<td><%=p.getPrecio()%></td>
							<td><%=p.getFechaAlta()%></td>
							<td><%=p.getAutor()%></td>
							<td><%=p.getImage()%></td>
							<td class="text-center">
								<a href="<%=request.getContextPath()%>/UploadProductPhoto?product_id=<%=p.getId()%>" ><i class="bi bi-card-image"></i></a>
								<a href="<%=request.getContextPath()%>/EditProduct?product_id=<%=p.getId()%>" ><i class="bi bi-pencil-square"></i></a>
								<a href="<%=request.getContextPath()%>/DeleteProduct?product_id=<%=p.getId()%>" ><i class="bi bi-x-square-fill"></i></a>
							</td>
						</tr>

						<%
						}
						%>
					</tbody>
				</table>

			</div>
			<!-- col -->
		</div>
		<!-- row -->

	</main>

	<jsp:include page="../includes/bottom_scripts.jsp"></jsp:include>	

</body>
</html>