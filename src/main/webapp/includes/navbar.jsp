<nav class="navbar navbar-expand-lg bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/Index">CAC APP</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			<%
				if(!(boolean)request.getSession().getAttribute("logged_in")) {
				%>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Login">Admin</a></li>
				
				<%
				}
			
				if((boolean)request.getSession().getAttribute("logged_in")) {
				%>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/FindAllProducts">Listado</a></li>
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="<%=request.getContextPath()%>/CreateProduct">Nuevo</a></li>
				<li class="nav-item">
					<form class="d-flex" action="<%=request.getContextPath()%>/Logout" method="POST">
						<button class="btn btn-outline-success" type="submit">Cerrar sesión</button>
					</form>
				</li>
				<%
				}
				%>
			</ul>
			<form class="d-flex" role="search"
				action="<%=request.getContextPath()%>/SearchProducts">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search" name="searchKey">
				<select name="searchCol" class="form-select me-2">
					<option value="codigo">Código</option>
					<option value="titulo">Título</option>
					<option value="autor">Autor</option>
				</select>
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
		</div>
	</div>
</nav>