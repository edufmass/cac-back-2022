<!DOCTYPE html>

<%@page import="java.util.List"%>
<%@page import="ar.com.movius.domain.Product"%>

<html lang="ens">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User login</title>

<jsp:include page="../includes/style_script.jsp"></jsp:include>

</head>
<body>

	<jsp:include page="../includes/navbar.jsp"></jsp:include>


	<main class="container">
		<div class="row">
			<div class="col">

				<h1>Iniciar sesión</h1>

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


				<form id="userLoginForm" class="row g-3 needs-validation" method="POST" 
					action="<%=request.getContextPath()%>/Login" >
					<div class="col-md-4">
					</div>
					<div class="col-md-4">
						<label for="validationCustom02" class="form-label">Usuario</label>
						<input name="usr4455" placeholder="ingrese el usuario" type="text"
							class="form-control" id="validationCustom02" value="" required>
						<div class="valid-feedback">Looks good!</div>
					</div>
					<div class="col-md-4">
					</div>

					<div class="col-md-4">
					</div>
					<div class="col-md-4">
						<label for="validationCustom03" class="form-label">Contraseña</label>
						<input name="pass5577" type="password"
							class="form-control" id="validationCustom03" value="" required>
						<div class="valid-feedback">Looks good!</div>
					</div>
					<div class="col-md-4">
					</div>

					<div class="col-12 text-center">
						<button class="btn btn-primary" type="submit">Iniciar sesión</button>
						<button id="btnCancel" class="btn btn-primary" type="button">Cancelar</button>
					</div>
				</form>

			</div>
			<!-- col -->
		</div>
		<!-- row -->

	</main>

	<jsp:include page="../includes/bottom_scripts.jsp"></jsp:include>
	
	<script type="text/javascript">


	document.addEventListener('DOMContentLoaded', function() {

	    const userLoginForm = document.getElementById('userLoginForm');
	    const btnCancel = document.getElementById('btnCancel');

	    userLoginForm.addEventListener("submit", (e) => { userLoginForm_submit(e) });
	    btnCancel.addEventListener("click", btnCancel_click);
	});

	const userLoginForm_submit  = (e) => {
	    // ToDo
	}
	
	const btnCancel_click  = (e) => {
		window.location = "<%=request.getContextPath()%>/Index";
	}
	
	</script>
</body>
</html>