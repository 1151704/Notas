<%@page import="ufps.modelo.dto.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="ufps.modelo.util.Servicio"/>
<%
    List<Carrera> carrerasListado = controlador.carreraListar();
    List<Alumno> alumnosListado = controlador.alumnoListar();
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="includes/head.jsp" %>
        <title>Alumnos</title>
    </head>
    <body>
        <%@include file="includes/menu.jsp" %>

        <section class="main-page">
            <div class="ed-container">
                <div class="ed-item">
                    <h3 class="main-titulo">Administración de Alumnos</h3>
                </div>
                <div class="ed-item s-mb-1">
                    <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#modal-alumnoRegistrar">Registrar</button>
                </div>
                <div class="ed-item table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Carrera</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Dirección</th>
                                <th>Telefono</th>
                                <th>Email</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (alumnosListado != null && !alumnosListado.isEmpty()) {
                                    for (Alumno alumno : alumnosListado) {%>
                            <tr>
                                <td><%=alumno.getCodAlumno()%></td>
                                <td><%=alumno.getCarrera().getNombre()%></td>
                                <td><%=alumno.getNombre()%></td>
                                <td><%=alumno.getApellido()%></td>
                                <td><%=alumno.getDireccion()%></td>
                                <td><%=alumno.getTelefono()%></td>
                                <td><%=alumno.getEmail()%></td>
                                <td>
                                    <button type="button" class="btn btn-warning" title="Editar" data-url="formularios/alumnoEditar.jsp"
                                            data-toggle="modal" data-target="#modal-alumnoEditar" data-id="<%=alumno.getCodAlumno()%>">
                                        <span class="far fa-edit" aria-hidden="true"></span>
                                    </button>
                                    <button type="button" class="btn btn-danger" title="Eliminar" data-url="formularios/alumnoEliminar.jsp"
                                            data-toggle="modal" data-target="#modal-alumnoEliminar" data-id="<%=alumno.getCodAlumno()%>">
                                        <span class="far fa-trash-alt" aria-hidden="true"></span>
                                    </button>
                                </td>
                            </tr>
                            <%}
                            } else {%>
                            <tr> 
                                <td colspan="8" align="center" >Ningún alumno registrado</td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <div class="modal fade" id="modal-alumnoRegistrar" role="dialog">
            <form action="registrarAlumno" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Registrar Alumno</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Codigo</label>
                                <input type="text" class="form-control" maxlength="4" name="codigo" required />
                            </div>
                            <% if (carrerasListado != null && !carrerasListado.isEmpty()) { %>
                            <div class="form-group">
                                <label>Carrera</label>
                                <select class="form-control" name="carrera">
                                    <% for (Carrera car : carrerasListado) {%>
                                    <option value="<%=car.getCodigo()%>"><%=car.getNombre()%></option>
                                    <% } %>
                                </select>
                            </div>
                            <% } else {%>
                            <div class="alert alert-warning" role="alert">
                                No existen carreras registradas
                            </div>
                            <% }%>
                            <div class="form-group">
                                <label>Nombre</label>
                                <input type="text" class="form-control" name="nombre" required />
                            </div>
                            <div class="form-group">
                                <label>Apellido</label>
                                <input type="text" class="form-control" name="apellido" required />
                            </div>
                            <div class="form-group">
                                <label>Dirección</label>
                                <input type="text" class="form-control" name="direccion"  />
                            </div>
                            <div class="form-group">
                                <label>Telefono</label>
                                <input type="text" class="form-control" name="telefono"  />
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" class="form-control" name="email"  />
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-success">Registrar</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal fade" id="modal-alumnoEditar" role="dialog">
            <form action="editarAlumno" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Editar Alumno</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-success">Guardar</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal fade" id="modal-alumnoEliminar" role="dialog">
            <form action="eliminarAlumno" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Eliminar Alumno</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-success">Eliminar</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
