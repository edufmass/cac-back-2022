<!DOCTYPE html>

<%@page import="ar.com.movius.domain.Product"%>

<html lang="ens">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Create product</title>

<jsp:include page="../includes/style_script.jsp"></jsp:include>

</head>
<body>

	<jsp:include page="../includes/navbar.jsp"></jsp:include>


	<main class="container">
		<div class="row">
			<div class="col">

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

			</div>
			<!-- col -->
		</div>
		<!-- row -->
		
		<%
		if(!alert) {
			Product p = (Product)request.getAttribute("product");
		%>
		<div class="row mt-5 mb-2">
			<div class="col-md-12">
				<div
					class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
					<div class="col p-4 d-flex flex-column position-static">
						<strong class="d-inline-block mb-2 text-primary"><%=p.getCodigo()%></strong>
						<h3 class="mb-0"><%=p.getTitulo()%></h3>
						<div class="mb-1 text-muted"><%=p.getFechaAlta()%></div>
						<p class="card-text mb-auto">Precio: <%=p.getPrecio()%></p>
						<p class="card-text mb-auto">Autor: <%=p.getAutor()%></p>
						<a href="<%=request.getContextPath()%>/Index" class="stretched-link">Volver al inicio</a>
					</div>
					<div class="col-auto d-none d-lg-block">
						<img src="<%=p.getImage()%>" />
					</div>
				</div>
			</div>
		</div>
		<%
		}
		%>	

	</main>

	<jsp:include page="../includes/bottom_scripts.jsp"></jsp:include>

	<script type="text/javascript">


	document.addEventListener('DOMContentLoaded', function() {

	    const editProductForm = document.getElementById('editProductForm');
	    const btnCancel = document.getElementById('btnCancel');

	    editProductForm.addEventListener("submit", (e) => { editProductForm_submit(e) });
	    btnCancel.addEventListener("click", btnCancel_click);
	});

	const editProductForm_submit  = (e) => {
	    // ToDo
	}
	
	const btnCancel_click  = (e) => {
		window.location = "<%=request.getContextPath()%>/FindAllProducts";
	}
	
	</script>
</body>
</html>