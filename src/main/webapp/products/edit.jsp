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

				<h1>Modificar producto</h1>

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

				<%
				if(!alert) {
					Product p = (Product)request.getAttribute("product");
				%>
				<form id="editProductForm" class="row g-3 needs-validation" method="POST" 
					action="<%=request.getContextPath()%>/EditProduct">
					
					<input type="hidden" name="product_id" value="<%=p.getId()%>" />
					
					<div class="col-md-4">
						<label for="validationCustom01" class="form-label">Código</label>
						<input name="codigo" placeholder="ingrese el código" type="text"
							class="form-control" id="validationCustom01" value="<%=p.getCodigo()%>" disabled="disabled">
						<div class="valid-feedback">Looks good!</div>
					</div>
					<div class="col-md-4">
						<label for="validationCustom02" class="form-label">Título</label>
						<input name="titulo" placeholder="ingrese el título" type="text"
							class="form-control" id="validationCustom02" value="<%=p.getTitulo()%>" required>
						<div class="valid-feedback">Looks good!</div>
					</div>
					<div class="col-md-4">
						<label for="validationCustom03" class="form-label">Precio</label>
						<input name="precio" placeholder="ingrese el precio" type="number"
							class="form-control" id="validationCustom03"
							step=".01" value="<%=p.getPrecio()%>" required>
						<div class="valid-feedback">Looks good!</div>
					</div>
					<div class="col-md-4">
						<label for="validationCustom04" class="form-label">Fecha
							de alta</label> <input name="fecha_alta" type="date" class="form-control"
							id="validationCustom04" value="<%=p.getFechaAlta()%>" required>
						<div class="valid-feedback">Looks good!</div>
					</div>
					<div class="col-md-4">
						<label for="validationCustom05" class="form-label">Autor</label> <input
							name="autor" placeholder="ingrese el autor" type="text"
							class="form-control" id="validationCustom05" value="<%=p.getAutor()%>" required>
						<div class="valid-feedback">Looks good!</div>
					</div>
					<div class="col-md-4">
						<label for="validationCustom06" class="form-label">Imagen</label>
						<div class="input-group">
							<input name="image" type="file" class="form-control"
								id="inputGroupFile04" aria-describedby="inputGroupFileAddon04"
								aria-label="Upload">
						</div>
					</div>
					<div class="col-12">
						<button class="btn btn-primary" type="submit">Actualizar
							producto</button>
						<button id="btnCancel" class="btn btn-primary" type="button">Cancelar</button>
					</div>
				</form>
				
				<%
				}
				%>

			</div>
			<!-- col -->
		</div>
		<!-- row -->

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