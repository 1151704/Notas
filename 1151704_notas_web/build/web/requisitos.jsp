<%@page import="ufps.modelo.dto.Requisito"%>
<%@page import="ufps.modelo.dto.Carrera"%>
<%@page import="ufps.modelo.dto.Asignatura"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="ufps.modelo.util.Servicio"/>
<%
    List<Asignatura> asignaturasListado = controlador.asignaturaListar();
    List<Carrera> carrerasListado = controlador.carreraListar();
    List<Requisito> requisitosListado = controlador.requisitoListar();
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="includes/head.jsp" %>
        <title>Requisitos</title>
    </head>
    <body>
        <%@include file="includes/menu.jsp" %>

        <section class="main-page">
            <div class="ed-container">
                <div class="ed-item">
                    <h3 class="main-titulo">Administración de Requisitos</h3>
                </div>
                <div class="ed-item s-mb-1">
                    <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#modal-requisitoRegistrar">Registrar</button>
                </div>
                <div class="ed-item table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Carrera</th>
                                <th>Asignatura</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (requisitosListado != null && !requisitosListado.isEmpty()) {
                                    for (Requisito req : requisitosListado) {%>
                            <tr>
                                <td><%=req.getCodRequisito()%></td>
                                <td><%=req.getCarrera().getNombre()%></td>
                                <td><%=req.getAsignatura().getNombre() %></td>
                                <td>
                                    <button type="button" class="btn btn-warning" title="Editar" data-url="formularios/requisitoEditar.jsp"
                                            data-toggle="modal" data-target="#modal-requisitoEditar" data-id="<%=req.getCodRequisito()%>">
                                        <span class="far fa-edit" aria-hidden="true"></span>
                                    </button>
                                    <button type="button" class="btn btn-danger" title="Eliminar" data-url="formularios/requisitoEliminar.jsp"
                                            data-toggle="modal" data-target="#modal-requisitoEliminar" data-id="<%=req.getCodRequisito()%>">
                                        <span class="far fa-trash-alt" aria-hidden="true"></span>
                                    </button>
                                </td>
                            </tr>
                            <%}
                            } else {%>
                            <tr> 
                                <td colspan="5" align="center" >Ningún requisito registrado</td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <div class="modal fade" id="modal-requisitoRegistrar" role="dialog">
            <form action="registrarRequisito" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Registrar Requisito</h4>
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
                            <% if (asignaturasListado != null && !asignaturasListado.isEmpty()) { %>
                            <div class="form-group">
                                <label>Carrera</label>
                                <select class="form-control" name="asignatura">
                                    <% for (Asignatura asig : asignaturasListado) {%>
                                    <option value="<%=asig.getCodAsignatura()%>"><%=asig.getNombre()%></option>
                                    <% } %>
                                </select>
                            </div>
                            <% } else {%>
                            <div class="alert alert-warning" role="alert">
                                No existen asignaturas registradas
                            </div>
                            <% }%>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-success">Registrar</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal fade" id="modal-requisitoEditar" role="dialog">
            <form action="editarRequisito" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Editar Requisito</h4>
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
        <div class="modal fade" id="modal-requisitoEliminar" role="dialog">
            <form action="eliminarRequisito" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Eliminar Requisito</h4>
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
