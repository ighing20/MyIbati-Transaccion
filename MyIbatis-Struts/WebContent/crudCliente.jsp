<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="esS">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrapValidator.css" />


<style>
.modal-header, h4, .close {
	background-color: #286090;
	color: white !important;
	text-align: center;
	font-size: 30px;
}
</style>

<title>MyIbatis Struts</title>
</head>
<body>

	<div class="container">
		<h1>Crud Cliente</h1>
		<div class="col-md-23">
			<form id="idFormElimina" action="eliminaCliente">
				<input type="hidden" id="id_elimina" name="id">
			</form>

			<form accept-charset="UTF-8" action="consultaCliente"
				class="simple_form" id="defaultForm2" method="post">
				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label class="control-label" for="id_nonbre_filtro">NOMBRE</label>
							<input class="form-control" id="id_nonbre_filtro" name="filtro"
								placeholder="Ingrese el nombre" type="text" maxlength="30" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">
						<button type="submit" class="btn btn-primary" id="validateBtnw1">FILTRA</button>
						<br>&nbsp;<br>
					</div>
					<div class="col-md-3">
						<button type="button" data-toggle='modal' onclick="registrar();"
							class='btn btn-success' id="validateBtnw2">REGISTRA</button>
						<br>&nbsp;<br>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="content">

							<table id="tableCliente"
								class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>ID</th>
										<th>Nombre</th>
										<th>Apellido</th>
										<th>Direccion</th>
										<th>Telefono</th>
										<th>Actualiza</th>
										<th>Elimina</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${clientes}" var="x">
										<tr>
											<td>${x.idCliente}</td>
											<td>${x.nombre}</td>
											<td>${x.apellido}</td>
											<td>${x.direccion}</td>
											<td>${x.telefono}</td>
											<td>
												<button type='button' data-toggle='modal'
													onclick="editar('${x.idCliente}','${x.nombre}','${x.apellido}','${x.direccion}','${x.telefono}');"
													class='btn btn-success'
													style='background-color: hsla(233, 100%, 100%, 0);'>
													<img src='images/edit.gif' width='auto' height='auto' />
												</button>
											</td>
											<td>
												<button type='button' data-toggle='modal'
													onclick="eliminar('${x.idCliente}');">
													<img src='images/delete.gif' width='auto' height='auto' />
												</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
					</div>
				</div>
			</form>
		</div>

		<div class="modal fade" id="idModalRegistra">
			<div class="modal-dialog" style="width: 60%">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header" style="padding: 35px 50px">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4>
							<span class="glyphicon glyphicon-ok-sign"></span> Registro de
							Cliente
						</h4>
					</div>
					<div class="modal-body" style="padding: 20px 10px;">
						<form id="defaultForm" accept-charset="UTF-8"
							action="registraCliente" class="form-horizontal" method="post">
							<div class="panel-group" id="steps">
								<!-- Step 1 -->
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#steps"
												href="#stepOne">Datos del Cliente</a>
										</h4>
									</div>
									<div id="stepOne" class="panel-collapse collapse in">
										<div class="panel-body">
											<div class="form-group">
												<label class="col-lg-3 control-label" for="id_reg_nombre">Nombre</label>
												<div class="col-lg-5">
													<input class="form-control" id="id_reg_nombre"
														name="cliente.nombre" placeholder="Ingrese el Nombre"
														type="text" maxlength="20" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-3 control-label" for="id_reg_apellido">Apellido</label>
												<div class="col-lg-5">
													<input class="form-control" id="id_reg_apellido"
														name="cliente.apellido" placeholder="Ingrese el Apellido"
														type="text" maxlength="10" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-3 control-label" for="id_reg_direccion">Direccion</label>
												<div class="col-lg-5">
													<input class="form-control" id="id_reg_direccion"
														name="cliente.direccion"
														placeholder="Ingrese la Direccion" type="text"
														maxlength="10" />
												</div>
											</div>

											<div class="form-group">
												<label class="col-lg-3 control-label" for="id_reg_telefono">Telefono</label>
												<div class="col-lg-5">
													<input class="form-control" id="id_reg_telefono"
														name="cliente.telefono" placeholder="Ingrese el Telefono"
														type="text" maxlength="10" />
												</div>
											</div>
											<div class="form-group">
												<div class="col-lg-9 col-lg-offset-3">
													<button type="submit" class="btn btn-primary">REGISTRA</button>
												</div>
											</div>
										</div>
									</div>
								</div>

							</div>
						</form>

					</div>
				</div>
			</div>

		</div>

		<div class="modal fade" id="idModalActualiza">
			<div class="modal-dialog" style="width: 60%">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header" style="padding: 35px 50px">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4>
							<span class="glyphicon glyphicon-ok-sign"></span> Actualiza
							Cliente
						</h4>
					</div>
					<div class="modal-body" style="padding: 20px 10px;">
						<form id="defaultForm" accept-charset="UTF-8"
							action="actualizaCliente" class="form-horizontal" method="post">
							<div class="panel-group" id="steps">
								<!-- Step 1 -->
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#steps"
												href="#stepOne">Datos del Cliente</a>
										</h4>
									</div>
									<div id="stepOne" class="panel-collapse collapse in">
										<div class="panel-body">
											<div class="form-group">
												<label class="col-lg-3 control-label" for="id_ID">ID</label>
												<div class="col-lg-5">
													<input class="form-control" id="id_ID" readonly="readonly"
														name="cliente.idCliente" type="text" maxlength="8" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-3 control-label" for="id_nombre">Nombre</label>
												<div class="col-lg-5">
													<input class="form-control" id="id_nombre"
														name="cliente.nombre" placeholder="Ingrese el Nombre"
														type="text" maxlength="20" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-3 control-label" for="id_apellido">Apellido</label>
												<div class="col-lg-5">
													<input class="form-control" id="id_apellido"
														name="cliente.apellido" placeholder="Ingrese el Apellido"
														type="text" maxlength="10" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-3 control-label" for="id_direccion">Direccion</label>
												<div class="col-lg-5">
													<input class="form-control" id="id_direccion"
														name="cliente.direccion"
														placeholder="Ingrese la Direccion" type="text"
														maxlength="10" />
												</div>
											</div>

											<div class="form-group">
												<label class="col-lg-3 control-label" for="id_telefono">Telefono</label>
												<div class="col-lg-5">
													<input class="form-control" id="id_telefono"
														name="cliente.telefono"
														placeholder="Ingrese el Telefono" type="text"
														maxlength="10" />
												</div>
											</div>



											<div class="form-group">
												<div class="col-lg-9 col-lg-offset-3">
													<button type="submit" class="btn btn-primary">ACTUALIZA</button>
												</div>
											</div>
										</div>
									</div>
								</div>

							</div>
						</form>

					</div>
				</div>
			</div>

		</div>

	</div>

	<script type="text/javascript">
		function eliminar(id) {
			$('input[id=id_elimina]').val(id);
			$('#idFormElimina').submit();
		}

		function registrar() {
			$('#idModalRegistra').modal("show");
		}

		function editar(id, nombre, apellido, direccion, telefono) {
			//document.getElementById("id_nombre").value ="ELBITA"

			$('input[id=id_ID]').val(id);
			$('input[id=id_nombre]').val(nombre);
			$('input[id=id_apellido]').val(apellido);
			$('input[id=id_direccion]').val(direccion);
			$('input[id=id_telefono]').val(telefono);
			$('#idModalActualiza').modal("show");
		}

		$(document).ready(function() {
			$('#tableCliente').DataTable();
		});
	</script>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#defaultForm')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
												excluded : ':disabled',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {

												}
											})
									.on(
											'error.form.bv',
											function(e) {
												console.log('error');

												// Active the panel element containing the first invalid element
												var $form = $(e.target), validator = $form
														.data('bootstrapValidator'), $invalidField = validator
														.getInvalidFields().eq(
																0), $collapse = $invalidField
														.parents('.collapse');

												$collapse.collapse('show');
											});
						});
	</script>

</body>
</html>
