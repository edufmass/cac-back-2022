<!DOCTYPE html>

<%@page import="java.util.List"%>
<%@page import="ar.com.movius.domain.Product"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>INDEX</title>

<jsp:include page="./includes/style_script.jsp"></jsp:include>

</head>
<body>

	<jsp:include page="./includes/navbar.jsp"></jsp:include>


	<main>

		<section class="py-5 text-center container">
			<div class="row py-lg-5">
				<div class="col-lg-6 col-md-8 mx-auto">
					<h1 class="fw-light">Tienda online</h1>
					<p class="lead text-muted">Variedad de productos.</p>
				</div>
			</div>
		</section>

		<div class="album py-5 bg-light">
			<div class="container">

				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
				
					<%
					List<Product> products = (List<Product>) request.getAttribute("productos");

					for (Product p : products) {
					%>
					<div class="col">
						<div class="card shadow-sm">
							<!-- svg class="bd-placeholder-img card-img-top" width="100%"
								height="225" xmlns="http://www.w3.org/2000/svg" role="img"
								aria-label="Placeholder: Thumbnail"
								preserveAspectRatio="xMidYMid slice" focusable="false">
								<title>Placeholder</title><rect width="100%" height="100%"
									fill="#55595c" />
								<text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg-->
								<img src="<%=p.getImage()%>" />

							<div class="card-body">
								<h5 class="card-title"><%=p.getTitulo()%></h5>
							</div>
							<ul class="list-group list-group-flush">
							    <li class="list-group-item">Autor: <%=p.getAutor()%></li>
							    <li class="list-group-item">Precio: <strong>$<%=p.getPrecio()%></strong></li>
						  	</ul>
						  	<div class="card-body">
								<p class="card-text">
								
								</p>
								<div class="d-flex justify-content-between align-items-center">
									<div class="btn-group">
										<a class="btn btn-sm btn-outline-secondary"
										 href="<%=request.getContextPath()%>/ViewProduct?product_id=<%=p.getId()%>" >
											Ver
										</a>
										
									</div>
								</div>
							</div>
						</div>
					</div>
					<%
					}
					%>
				

				
					
				</div>
			</div>
		</div>


		

	</main>

	<jsp:include page="./includes/bottom_scripts.jsp"></jsp:include>

</body>
</html>