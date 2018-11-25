<%@page import="ufps.modelo.dto.Tutor"%>
<%@page import="ufps.modelo.dto.Alumno"%>
<%@page import="ufps.modelo.dto.Requisito"%>
<%@page import="ufps.modelo.dto.Carrera"%>
<%@page import="ufps.modelo.dto.Asignatura"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="ufps.modelo.util.Servicio"/>
<%
    List<Alumno> alumnosListado = controlador.alumnoListar();
    List<Carrera> carrerasListado = controlador.carreraListar();
    List<Tutor> tutorListado = controlador.tutorListar();
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="includes/head.jsp" %>
        <title>Tutores</title>
    </head>
    <body>
        <%@include file="includes/menu.jsp" %>

        <section class="main-page">
            <div class="ed-container">
                <div class="ed-item">
                    <h3 class="main-titulo">Administración de Tutores</h3>
                </div>
                <div class="ed-item s-mb-1">
                    <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#modal-tutorRegistrar">Registrar</button>
                </div>
                <div class="ed-item table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Alumno</th>
                                <th>Carrera</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (tutorListado != null && !tutorListado.isEmpty()) {
                                    for (Tutor tut : tutorListado) {%>
                            <tr>
                                <td><%=tut.getCodTutor()%></td>
                                <td><%=tut.getAlumno().getNombre() %></td>
                                <td><%=tut.getCarrera().getNombre()%></td>
                                <td>
                                    <button type="button" class="btn btn-warning" title="Editar" data-url="formularios/tutorEditar.jsp"
                                            data-toggle="modal" data-target="#modal-tutorEditar" data-id="<%=tut.getCodTutor()%>">
                                        <span class="far fa-edit" aria-hidden="true"></span>
                                    </button>
                                    <button type="button" class="btn btn-danger" title="Eliminar" data-url="formularios/tutorEliminar.jsp"
                                            data-toggle="modal" data-target="#modal-tutorEliminar" data-id="<%=tut.getCodTutor()%>">
                                        <span class="far fa-trash-alt" aria-hidden="true"></span>
                                    </button>
                                </td>
                            </tr>
                            <%}
                            } else {%>
                            <tr> 
                                <td colspan="5" align="center" >Ningún tutor registrado</td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <div class="modal fade" id="modal-tutorRegistrar" role="dialog">
            <form action="registrarTutor" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Registrar Tutor</h4>
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
                            <% if (alumnosListado != null && !alumnosListado.isEmpty()) { %>
                            <div class="form-group">
                                <label>Alumno</label>
                                <select class="form-control" name="alumno">
                                    <% for (Alumno asig : alumnosListado) {%>
                                    <option value="<%=asig.getCodAlumno()%>"><%=asig.getNombre()%></option>
                                    <% } %>
                                </select>
                            </div>
                            <% } else {%>
                            <div class="alert alert-warning" role="alert">
                                No existen alumnos registrados
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
        <div class="modal fade" id="modal-tutorEditar" role="dialog">
            <form action="editarTutor" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Editar Tutor</h4>
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
        <div class="modal fade" id="modal-tutorEliminar" role="dialog">
            <form action="eliminarTutor" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Eliminar Tutor</h4>
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
