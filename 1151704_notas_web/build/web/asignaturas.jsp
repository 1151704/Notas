<%@page import="ufps.modelo.dto.Carrera"%>
<%@page import="ufps.modelo.dto.Asignatura"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="ufps.modelo.util.Servicio"/>
<%
    List<Asignatura> asignaturasListado = controlador.asignaturaListar();
    List<Carrera> carrerasListado = controlador.carreraListar();
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="includes/head.jsp" %>
        <title>Asignaturas</title>
    </head>
    <body>
        <%@include file="includes/menu.jsp" %>

        <section class="main-page">
            <div class="ed-container">
                <div class="ed-item">
                    <h3 class="main-titulo">Administración de Asignaturas</h3>
                </div>
                <div class="ed-item s-mb-1">
                    <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#modal-asignaturaRegistrar">Registrar</button>
                </div>
                <div class="ed-item table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Carrera</th>
                                <th>Nombre</th>
                                <th>Creditos</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (asignaturasListado != null && !asignaturasListado.isEmpty()) {
                                    for (Asignatura asignatura : asignaturasListado) {%>
                            <tr>
                                <td><%=asignatura.getCodAsignatura()%></td>
                                <td><%=asignatura.getCarrera().getNombre()%></td>
                                <td><%=asignatura.getNombre()%></td>
                                <td><%=asignatura.getCreditos()%></td>
                                <td>
                                    <button type="button" class="btn btn-warning" title="Editar" data-url="formularios/asignaturaEditar.jsp"
                                            data-toggle="modal" data-target="#modal-asignaturaEditar" data-id="<%=asignatura.getCodAsignatura()%>">
                                        <span class="far fa-edit" aria-hidden="true"></span>
                                    </button>
                                    <button type="button" class="btn btn-danger" title="Eliminar" data-url="formularios/asignaturaEliminar.jsp"
                                            data-toggle="modal" data-target="#modal-asignaturaEliminar" data-id="<%=asignatura.getCodAsignatura()%>">
                                        <span class="far fa-trash-alt" aria-hidden="true"></span>
                                    </button>
                                </td>
                            </tr>
                            <%}
                            } else {%>
                            <tr> 
                                <td colspan="5" align="center" >Ninguna carrera registrada</td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <div class="modal fade" id="modal-asignaturaRegistrar" role="dialog">
            <form action="registrarAsignatura" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Registrar Asignatura</h4>
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
                                <label>Creditos</label>
                                <input type="number" class="form-control" min="1" name="creditos" />
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
        <div class="modal fade" id="modal-asignaturaEditar" role="dialog">
            <form action="editarAsignatura" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Editar Asignatura</h4>
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
        <div class="modal fade" id="modal-asignaturaEliminar" role="dialog">
            <form action="eliminarAsignatura" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Eliminar Asignatura</h4>
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
